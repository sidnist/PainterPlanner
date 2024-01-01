package com.example.projectplanner2.presentation.tabs;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.NavDirections;
import androidx.navigation.fragment.NavHostFragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.example.projectplanner2.Room.QuoteDatabase;
import com.example.projectplanner2.Room.UpdateDataDao;
import com.example.projectplanner2.TabFragmentDirections;
import com.example.projectplanner2.databinding.FragmentQuoteBinding;
import com.example.projectplanner2.presentation.invoice.adapter.CustomDropdownAdapter;
import com.example.projectplanner2.presentation.invoice.model.DropDownModel;
import com.example.projectplanner2.presentation.invoice.model.InsertUpdateDataModel;
import com.example.projectplanner2.presentation.tabs.model.InsertHomeItemModel;
import com.example.projectplanner2.shared.GetPrice;
import com.google.android.material.slider.Slider;

import java.util.ArrayList;
import java.util.List;


public class QuoteFragment extends Fragment {
    FragmentQuoteBinding binding;
    InsertHomeItemModel model;
    List<InsertUpdateDataModel> dataItem;
    String selectedTypeofPaint="Washable";
    String selectedTypeofColor="White";
    String cost="$35";
    GetPrice getPrice;
    float seekBarValue=0;
    QuoteDatabase quoteDatabase;
    float totalCost =0;
    UpdateDataDao updateDao;
    CustomDropdownAdapter customDropdownAdapter;
    float addedValue = 0;
    float defaultValue = 100;
    float previousValue = 0;
    float literCost = 0;
    float finalPaintCostPerLiter = 0;
    int price =0;
    int labourCost = 10;
    int spinnerPosition = 0;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentQuoteBinding.inflate(inflater,container,false);

        getPrice = new GetPrice();
        quoteDatabase = QuoteDatabase.getInstance(getContext());
        updateDao = quoteDatabase.getUpdateDao();

        getUpdateData();

        binding.spinnerTypeOfPaint.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                selectedTypeofPaint = adapterView.getItemAtPosition(position).toString();
//                String selectedOption = myArray[position];
//                price = getPrice.getDropDownList();
                selectedTypeofPaint = dataItem.get(0).getDownModelList().get(position).getValue();
                price = Integer.valueOf(dataItem.get(0).getDownModelList().get(position).getPrice());

                finalPaintCostPerLiter = price * literCost;

            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        binding.etPercentage.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                defaultValue = Float.parseFloat(s.toString());
            }
            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        binding.btnHired.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isValidate()){
                    if(model!=null){
                        NavController navController = NavHostFragment.findNavController(QuoteFragment.this);
                        NavDirections action = TabFragmentDirections.actionTabFragmentToOrderFragment2(model);
                        navController.navigate(action);
                    }

                }

            }




        });

        binding.btnNotHired.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.etPaint.setText("");
                binding.etTimeRequired.setText("");
                binding.tvTotalPaintCost.setText("");
                binding.tvTotalLabourCost.setText("");
                binding.tvSurfacePercentage.setText("");
                binding.tvCost.setText("");
                binding.seekBar.setValue(1);
                binding.etPercentage.setText("100");
            }
        });

        View view = binding.getRoot();
        binding.seekBar.addOnChangeListener(new Slider.OnChangeListener() {
            @Override
            public void onValueChange(@NonNull Slider slider, float value, boolean fromUser) {
                seekBarValue = value;
                // Check if the new value is greater or lesser than the previous value
                if (seekBarValue > previousValue) {
                    // Increasing value
                    addedValue = 25; // Adjusted logic for increasing
                    binding.etPercentage.setText(String.valueOf(defaultValue + addedValue));
                } else {
                    // Decreasing value
                    addedValue = 25; // Adjusted logic for decreasing
                    binding.etPercentage.setText(String.valueOf(defaultValue - addedValue));
                }

                previousValue = seekBarValue;

            }
        });
        binding.spinnerColor.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                 selectedTypeofColor = adapterView.getItemAtPosition(position).toString();
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
        binding.btnGenerate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isValidate()){

                    setTextData();
                }

            }
        });
        return  view;
    }

    private void addDropDownData() {
        List<DropDownModel> listOfDropDown = new ArrayList<>();
        listOfDropDown = getPrice.getDropDownList();
        if(listOfDropDown.size() > 0){
            InsertUpdateDataModel data = new InsertUpdateDataModel(
                    1,
                    "10",
                    "10",
                    0,
                    listOfDropDown
            );
            updateDao.insert(data);
        }

    }

    @Override
    public void onResume() {
        super.onResume();
        quoteDatabase = QuoteDatabase.getInstance(getContext());
        updateDao = quoteDatabase.getUpdateDao();

        getUpdateData();
    }

    private void getUpdateData() {
         dataItem = updateDao.getUpdateData(1);

        if(dataItem.isEmpty()){
            addDropDownData();
        }
        else {

            price = Integer.valueOf(dataItem.get(0).getCostOfPaint());
            labourCost = Integer.valueOf(dataItem.get(0).getCostPerManHour());
            CustomDropdownAdapter adapter = new CustomDropdownAdapter(requireContext(), dataItem.get(0).getDownModelList());
            binding.spinnerTypeOfPaint.setAdapter(adapter);
            binding.spinnerTypeOfPaint.setSelection(dataItem.get(0).getSpinnerPosition());
            selectedTypeofPaint = dataItem.get(0).getDownModelList().get(dataItem.get(0).getSpinnerPosition()).getValue();

        }
    }
    int hourAndLabourCost = 0;

    private void setTextData() {

        float paintArea= Integer.valueOf(binding.etPaint.getText().toString());
        int totalHour = Integer.valueOf(binding.etTimeRequired.getText().toString());
        float percentage = Float.valueOf(binding.etPercentage.getText().toString());
        float finalPercentage = percentage/100;
        hourAndLabourCost = (totalHour * labourCost);
         float priceOfPaintAndPaintArea = price * paintArea;
         totalCost = ((priceOfPaintAndPaintArea) + (hourAndLabourCost)) * finalPercentage;


         binding.tvTotalPaintCost.setText("Total Paint Cost: "+"$"+price+" X "+paintArea+" = $"+priceOfPaintAndPaintArea);
         binding.tvTotalLabourCost.setText("Total Labour Cost: "+"$"+labourCost+" X "+totalHour +" = $"+hourAndLabourCost);
         binding.tvSurfacePercentage.setText("Surface Preparation: "+percentage+"%");
         binding.tvCost.setText("Total Cost: $"+totalCost);

        model = new InsertHomeItemModel(
                0,
                "",
                "",
                "",
                binding.etPaint.getText().toString().trim(),
                selectedTypeofPaint,
                selectedTypeofColor,
                binding.etTimeRequired.getText().toString().trim(),
                finalPercentage,
                0,
                String.valueOf(totalCost).toString().trim(),
                String.valueOf(labourCost).toString().trim(),
                String.valueOf(priceOfPaintAndPaintArea).toString().trim()

        );



    }


    private boolean isValidate() {
         if(binding.etPaint.getText().toString().isEmpty()){
             binding.etPaint.setError("This field is required");
             return  false;
         }
        else if(binding.etTimeRequired.getText().toString().isEmpty()){
             binding.etTimeRequired.setError("This field is required");
             return  false;
         }

        else {
            return  true;
         }


    }



}