package com.example.projectplanner2.presentation.invoice;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.pdf.PdfDocument;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Environment;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.projectplanner2.R;
import com.example.projectplanner2.Room.QuoteDataDao;
import com.example.projectplanner2.Room.QuoteDatabase;
import com.example.projectplanner2.databinding.FragmentInvoiceBinding;
import com.example.projectplanner2.databinding.HomeLayoutBinding;
import com.example.projectplanner2.presentation.CustomerInformationFragmentArgs;
import com.example.projectplanner2.presentation.invoice.adapter.InvoiceAdapter;
import com.example.projectplanner2.presentation.invoice.model.DateModel;
import com.example.projectplanner2.presentation.tabs.adapter.HomeAdapter;
import com.example.projectplanner2.presentation.tabs.model.InsertHomeItemModel;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class InvoiceFragment extends Fragment implements HomeAdapter.OnItemClick {
    FragmentInvoiceBinding binding;
    ArrayList<InsertHomeItemModel> itemsList;
    InvoiceAdapter homeAdapter;
    String enteredText;
    int totalCost=0;
    double hourAndLabourCost =0;
    QuoteDatabase quoteDatabase;
    QuoteDataDao dataDao;

    DateModel dateModel;
    String startDate, endDate;


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        dateModel = InvoiceFragmentArgs.fromBundle(getArguments()).getArg();
        startDate =dateModel.getStartDate();
        endDate =dateModel.getEndDate();
        initData();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentInvoiceBinding.inflate(inflater,container,false);
        View view = binding.getRoot();
        itemsList = new ArrayList<>();
        quoteDatabase = QuoteDatabase.getInstance(getContext());
        dataDao = quoteDatabase.getDao();
        initData();
        binding.btnDownlaod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               CustomDialog dialog = new CustomDialog();
               dialog.showInputDialog(getContext());

            }
        });


        return view;
    }
    public class CustomDialog {

        public  void showInputDialog(Context context) {
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setTitle("Save As");

            // Create LinearLayout to hold EditText
            LinearLayout layout = new LinearLayout(context);
            layout.setOrientation(LinearLayout.VERTICAL);

            // Create EditText
            final EditText editText = new EditText(context);
            layout.addView(editText); // Add EditText to LinearLayout

            builder.setView(layout); // Set the LinearLayout as the dialog content

            // Set button for positive action
            builder.setPositiveButton("Save", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    enteredText = editText.getText().toString();
                    convertXMLtoPDF(enteredText);
                    // Do something with the entered text here
                    // For example, you can display it or use it in your application
                }
            });


            AlertDialog dialog = builder.create();
            dialog.show();
        }
    }
    private void initData() {
        totalCost = 0;
        itemsList.addAll(dataDao.getDataByDate(startDate,endDate));

        float labourTotalCost = 0;
        float labourAvgCost = 0;
        float paintLiter = 0;
        int hour = 0;
        float costService = 0;
        double totalAvgCost = 0;
        double totalCost = 0;
        int timerequired = 0;
        float tvQuantityPaint = 0;
        for (InsertHomeItemModel data : itemsList) {
             hour += Integer.valueOf(data.getTimeRequired());
            labourTotalCost +=  Float.valueOf(data.getLabourCost());
            tvQuantityPaint +=  Float.valueOf(data.getPaintArea());
            timerequired += Integer.valueOf(data.getTimeRequired());
            paintLiter +=  Float.valueOf(data.getPaintLiter());
            costService +=  Float.valueOf(data.getLevelOfSurface()) * 100;
            totalCost += Float.valueOf(data.getTotalCost());
            hourAndLabourCost += data.getHourAndLabourCost();



        }

            labourAvgCost = Float.valueOf(String.valueOf(hourAndLabourCost/timerequired));



        binding.tvAvg.setText(String.valueOf(labourAvgCost));
        binding.tvCost.setText(String.valueOf(hourAndLabourCost));
        binding.tvQuantity.setText(String.valueOf(timerequired));
        binding.tvQuantityPaint.setText(String.valueOf(tvQuantityPaint));
        binding.tvCostPaint.setText(String.valueOf(paintLiter));
        double roundedNumber = roundOfValue(paintLiter);
        binding.tvAvgPaint.setText(String.valueOf(paintLiter/tvQuantityPaint));
        binding.tvAvgService.setText(String.format("%.1f",costService/itemsList.size())+"%");
        totalAvgCost = (totalCost/itemsList.size());
        binding.datehead.setText(String.format("Report From %s to %s", startDate, endDate));
//        totalAvgCost = (roundedNumber+labourAvgCost);
//        totalAvgCost = roundOfValue(totalAvgCost);

        binding.tvTotalAvg.setText(String.valueOf(totalAvgCost));
        binding.tvTotalCost.setText(String.valueOf(totalCost));
        binding.tvTotalQuantity.setText(String.valueOf(itemsList.size()));



//        homeAdapter = new InvoiceAdapter(itemsList);
//        Toast.makeText(getContext(), "Count"+itemsList.size(), Toast.LENGTH_SHORT).show();
//        binding.rv.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
//        binding.rv.setHasFixedSize(true);
//        binding.rv.setAdapter(homeAdapter);
    }

    public double roundOfValue(double value){

        return Math.round((value/itemsList.size()) * 10.0) / 10.0;

    }


    private void convertXMLtoPDF(String tablename) {
        HomeLayoutBinding homeLayoutBinding;
        View view = binding.headerCard;
        DisplayMetrics displayMetrics = new DisplayMetrics();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            getContext().getDisplay().getRealMetrics(displayMetrics);
        } else {
            getActivity().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        }
        view.measure(View.MeasureSpec.makeMeasureSpec(displayMetrics.widthPixels, View.MeasureSpec.EXACTLY),
                View.MeasureSpec.makeMeasureSpec(displayMetrics.heightPixels, View.MeasureSpec.EXACTLY));
        view.layout(0, 0, displayMetrics.widthPixels, displayMetrics.heightPixels);
        PdfDocument document = new PdfDocument();
        int vewWidth = view.getMeasuredWidth();
        int vewHeight = view.getMeasuredHeight();


        PdfDocument.PageInfo pageInfo = new PdfDocument.PageInfo.Builder(vewWidth, vewHeight, 1).create();
        PdfDocument.Page page = document.startPage(pageInfo);

        Canvas canvas = page.getCanvas();
        view.draw(canvas);
        //Finish the page
        document.finishPage(page);
        File downloadDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
        String filename = tablename+".pdf";
        File file = new File(downloadDir, filename);
        try {
            FileOutputStream fos = new FileOutputStream(file);
            document.writeTo(fos);

            document.close();
            fos.close();
            Toast.makeText(getContext(), "Saved Successfully!", Toast.LENGTH_SHORT).show();
        }catch (FileNotFoundException e){
            Log.d("TAG","Error while writing "+ e.toString());

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public void onClickListener(InsertHomeItemModel data) {

    }

}