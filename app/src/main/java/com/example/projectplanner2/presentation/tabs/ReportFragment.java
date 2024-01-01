package com.example.projectplanner2.presentation.tabs;

import android.Manifest;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.pdf.PdfDocument;
import android.os.Build;
import android.os.Bundle;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import android.os.Environment;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.projectplanner2.R;
import com.example.projectplanner2.Room.InsertQuoteData;
import com.example.projectplanner2.Room.QuoteDataDao;
import com.example.projectplanner2.Room.QuoteDatabase;
import com.example.projectplanner2.TabFragmentDirections;
import com.example.projectplanner2.databinding.FragmentReportBinding;
import com.example.projectplanner2.export_pdf.PdfExportTask;

import com.example.projectplanner2.presentation.invoice.model.DateModel;
import com.example.projectplanner2.presentation.tabs.model.InsertHomeItemModel;
import com.example.projectplanner2.shared.DatePickerHelper;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;


public class ReportFragment extends Fragment {
    FragmentReportBinding binding;
    private Calendar calendar;
    DatePickerHelper datePickerHelper;
    QuoteDatabase quoteDatabase;
    QuoteDataDao dataDao;
    final static int REQUEST_CODE = 1232;
    DatePickerHelper myDatePicker;
    String startDate, endDate;
    DateModel dateModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentReportBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        quoteDatabase = QuoteDatabase.getInstance(getContext());
        dataDao = quoteDatabase.getDao();
        calendar = Calendar.getInstance();
        datePickerHelper = new DatePickerHelper(requireContext(),binding.tvEndDate);
        datePickerHelper = new DatePickerHelper(requireContext(),binding.tvStartDate);
        askPermission();
        binding.btnEndDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDatePicker = new DatePickerHelper(getContext(), binding.tvEndDate);
                myDatePicker.showDatePicker();

            }
        });

        binding.btnXmlToPdf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isValidate()){
                    dateModel = new DateModel(binding.tvStartDate.getText().toString().trim(),binding.tvEndDate.getText().toString().trim());
                    NavController navController = NavHostFragment.findNavController(ReportFragment.this);
                    NavDirections action = TabFragmentDirections.actionTabFragmentToInvoiceFragment(dateModel);
                    navController.navigate(action);
            }}
        });
        binding.btnStartDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                showDatePicker(binding.tvStartDate);
                myDatePicker = new DatePickerHelper(getContext(), binding.tvStartDate);
                myDatePicker.showDatePicker();
            }
        });
        return view;}

        private boolean isValidate() {
            if(binding.tvStartDate.getText().toString().isEmpty()){
                binding.tvStartDate.setError("This field is required");
                return  false;
            }

            else if(binding.tvEndDate.getText().toString().isEmpty()){
                binding.tvEndDate.setError("This field is required");
                return  false;
            }

            else {
                return true;
            }
    }



//
//    private void convertXMLtoPDF() {
//
//        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_report, null);
//
//        DisplayMetrics displayMetrics = new DisplayMetrics();
//
//
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
//            getContext().getDisplay().getRealMetrics(displayMetrics);
//        } else {
//            getActivity().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
//        }
//
//        view.measure(View.MeasureSpec.makeMeasureSpec(displayMetrics.widthPixels, View.MeasureSpec.EXACTLY),
//        View.MeasureSpec.makeMeasureSpec(displayMetrics.heightPixels, View.MeasureSpec.EXACTLY));
//
//        view.layout(0, 0, displayMetrics.widthPixels, displayMetrics.heightPixels);
//
//        PdfDocument document = new PdfDocument();
//
//        int vewWidth = view.getMeasuredWidth();
//        int vewHeight = view.getMeasuredHeight();
//
//        PdfDocument.PageInfo pageInfo = new PdfDocument.PageInfo.Builder(vewWidth, vewHeight, 1).create();
//        PdfDocument.Page page = document.startPage(pageInfo);
//
//        Canvas canvas = page.getCanvas();
//        view.draw(canvas);
//
//
//        //Finish the page
//        document.finishPage(page);
//        File downloadDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
//        String filename = "table.pdf";
//        File file = new File(downloadDir, filename);
//
//        try {
//
//            FileOutputStream fos = new FileOutputStream(file);
//            document.writeTo(fos);
//            document.close();
//            fos.close();
//            Toast.makeText(getContext(), "Written successfully!!", Toast.LENGTH_SHORT).show();
//
//
//        }catch (FileNotFoundException e){
//            Log.d("TAG","Eror while writing "+ e.toString());
//
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//
//
//    }


//    private void createPDF() throws IOException {
//
//        PdfDocument pdfDocument = new PdfDocument();
//        PdfDocument.PageInfo pageInfo = new PdfDocument.PageInfo.Builder(1080, 1920, 1).create();
//        PdfDocument.Page page = pdfDocument.startPage(pageInfo);
//        Canvas canvas = page.getCanvas();
//        Paint paint = new Paint();
//        paint.setColor(Color.BLACK);
//        paint.setTextSize(24);
//        List<InsertHomeItemModel> insertHomeItemModel = dataDao.getAllData();
//
//        float x = 500;
//        float y = 900;
//        canvas.drawText("Hazrat", x, y, paint);
//        pdfDocument.finishPage(page);
//        File downloadDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
//        String filename = "quote.pdf";
//        File file = new File(downloadDir, filename);
//        FileOutputStream fos = new FileOutputStream(file);
//        pdfDocument.writeTo(fos);
//
//    }

    private void askPermission() {
        ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_CODE);
    }

//    public void exportTableToPdf(View view) {
//
//
//        // Create a PDF document
//        PdfDocument document = new PdfDocument();
//        PdfDocument.PageInfo pageInfo = new PdfDocument.PageInfo.Builder(300, 600, 1).create();
//        PdfDocument.Page page = document.startPage(pageInfo);
//
//        Canvas canvas = page.getCanvas();
////        tableLayout.draw(canvas); // Draw the table on the canvas
//
//        document.finishPage(page);
//        File downloadDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
//        String filename = "TableViewData.pdf";
//        // Save the document
//        File file = new File(downloadDir, filename);
//        try {
//            document.writeTo(new FileOutputStream(file));
//            Toast.makeText(getContext(), "Downliaded", Toast.LENGTH_SHORT).show();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        document.close();
//    }





}