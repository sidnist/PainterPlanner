package com.example.projectplanner2.presentation;

import android.Manifest;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.projectplanner2.R;
import com.example.projectplanner2.Room.QuoteDataDao;
import com.example.projectplanner2.Room.QuoteDatabase;
import com.example.projectplanner2.databinding.FragmentCustomerInformationBinding;
import com.example.projectplanner2.presentation.tabs.QuoteFragment;
import com.example.projectplanner2.presentation.tabs.model.InsertHomeItemModel;


public class CustomerInformationFragment extends Fragment {
    FragmentCustomerInformationBinding binding;
    QuoteDatabase quoteDatabase;
    QuoteDataDao dataDao;
    InsertHomeItemModel argumentModel;
    int position = 1;
    float percentage = 0;
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        argumentModel = CustomerInformationFragmentArgs.fromBundle(getArguments()).getArg();
        if (argumentModel != null) {
            position = argumentModel.getId();
            Float price =  Float.valueOf(argumentModel.getPaintLiter());
            Float paintedArea =  Float.valueOf(argumentModel.getPaintArea());
            float finalPrice = price/paintedArea;
            String paintArea =  argumentModel.getPaintArea();
            String hour =  argumentModel.getTimeRequired();
            String labourCost =  argumentModel.getLabourCost();
            binding.tvCost.setText("Total Cost: $"+price);
            binding.tvPhone.setText(argumentModel.getPhoneNo());
            binding.tvName.setText(argumentModel.getName());
            binding.tvPaintArea.setText(argumentModel.getPaintArea());
            binding.tvDate.setText(argumentModel.getDate());
            binding.timeRequired.setText(hour);

            percentage = Float.valueOf(argumentModel.getLevelOfSurface()) * 100;
            binding.tvPercentage.setText(String.valueOf(percentage)+"%");

            binding.tvColorType.setText(argumentModel.getColorOfPaint());
            binding.tvPaintType.setText(argumentModel.getTypeOfPaint());

            binding.tvTotalPaintCost.setText("Total Paint Cost: "+"$"+finalPrice+" X "+ paintArea+" = $"+argumentModel.getPaintLiter());
            binding.tvTotalLabourCost.setText("Total Labour Cost: "+"$"+labourCost+" X "+ hour +" = $"+argumentModel.getHourAndLabourCost());
            binding.tvSurfacePercentage.setText("Surface Preparation: "+percentage+"%");
            binding.tvCost.setText(String.valueOf("Total Cost: $" + argumentModel.getTotalCost()));

           
        }
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentCustomerInformationBinding.inflate(inflater,container,false);
        quoteDatabase = QuoteDatabase.getInstance(getContext());
        dataDao = quoteDatabase.getDao();

        binding.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dataDao.delete(position);
                Toast.makeText(getContext(), "Deleted", Toast.LENGTH_SHORT).show();
                Navigation.findNavController(requireView()).navigateUp();
            }
        });
        binding.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(requireView()).navigateUp();
            }
        });
        return binding.getRoot();
    }


}