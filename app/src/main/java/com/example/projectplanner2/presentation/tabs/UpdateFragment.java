package com.example.projectplanner2.presentation.tabs;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Toast;

import com.example.projectplanner2.Room.QuoteDatabase;
import com.example.projectplanner2.Room.UpdateDataDao;
import com.example.projectplanner2.presentation.invoice.adapter.CustomDropdownAdapter;
import com.example.projectplanner2.presentation.invoice.model.DropDownModel;
import com.example.projectplanner2.presentation.invoice.model.InsertUpdateDataModel;
import com.example.projectplanner2.databinding.FragmentUpdateBinding;
import com.example.projectplanner2.shared.GetPrice;

import java.util.ArrayList;
import java.util.List;

public class UpdateFragment extends Fragment {
    FragmentUpdateBinding binding;

    List<DropDownModel> dropDownModels = new ArrayList<>();

    QuoteDatabase quoteDatabase;
    List<InsertUpdateDataModel> dataItem;
    List<DropDownModel> dropDownModelsList;
    UpdateDataDao updateDao;
    String selectedTypeofPaint = "Washable";
    GetPrice getPrice = new GetPrice();
    float addedValue = 0;
    float defaultValue = 0;
    float previousValue = 0;
    float listerCost = 0;
    float finalValue = 0;
    int price = 0;
    int spinnerPosition = 0;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentUpdateBinding.inflate(inflater, container, false);
        quoteDatabase = QuoteDatabase.getInstance(getContext());
        updateDao = quoteDatabase.getUpdateDao();
//        getData();
        getUpdateData();

        listerCost = Integer.valueOf(binding.etCpstPerLister.getText().toString());
        binding.etCpstPerLister.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().length() == 0) {
                    binding.etCpstPerLister.setError("Value should be grater then O");

                } else {
                    listerCost = Float.valueOf(s.toString());
                }
            }

            @Override
            public void afterTextChanged(Editable s) {


            }
        });
        binding.spinnerTypeOfPaint.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                selectedTypeofPaint = adapterView.getItemAtPosition(position).toString();
//                price = getPrice.getDropDownList();
                spinnerPosition = position;
                binding.etCpstPerLister.setText(String.valueOf(dataItem.get(0).getDownModelList().get(position).getPrice()));
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        View view = binding.getRoot();

        binding.btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isValidate()) {
                    setTextData();
//
                }
            }
        });


        return view;
    }

//    private void addData() {
//        dropDownModels = getPrice.getDropDownList();
////        updateDao.insert();
//
//    }

//    private void getData() {
//        List<InsertUpdateDataModel> dataItem = updateDao.getUpdateData(1);
//
//        if(dataItem.isEmpty()){
//
//        }
//        else {
//            binding.spinnerTypeOfPaint.setSelection(dataItem.get(0).getSpinnerPosition());
//            binding.etManHour.setText(dataItem.get(0).getCostPerManHour());
//            binding.etCpstPerLister.setText(dataItem.get(0).getCostOfPaint());
//        }
//    }

    private void setTextData() {
        dropDownModelsList.get(spinnerPosition).setPrice(binding.etCpstPerLister.getText().toString());
        InsertUpdateDataModel dataItemDao = new InsertUpdateDataModel(
                1,
                binding.etCpstPerLister.getText().toString().trim(),
                binding.etManHour.getText().toString().trim(),
                spinnerPosition,
                dropDownModelsList
        );

        if (dataItemDao != null) {
            updateDao.insert(dataItemDao);
            List<InsertUpdateDataModel> dataItem = updateDao.getUpdateData(1);
            Toast.makeText(requireContext(), "Updated Successfully!", Toast.LENGTH_SHORT).show();

        }
    }
    private void getUpdateData() {
        dataItem = updateDao.getUpdateData(1);

        if(dataItem.isEmpty()){
        }
        else {
            dropDownModelsList = dataItem.get(0).getDownModelList();
            price = Integer.valueOf(dataItem.get(0).getCostOfPaint());
            CustomDropdownAdapter adapter = new CustomDropdownAdapter(requireContext(), dataItem.get(0).getDownModelList());
            binding.spinnerTypeOfPaint.setAdapter(adapter);
            binding.spinnerTypeOfPaint.setSelection(dataItem.get(0).getSpinnerPosition());
        }
    }

    private boolean isValidate() {

        if (binding.etCpstPerLister.getText().toString().isEmpty()) {
            binding.etCpstPerLister.setError("This field is required");
            return false;
        } else if (binding.etManHour.getText().toString().isEmpty()) {
            binding.etManHour.setError("This field is required");
            return false;

        } else {
            return true;
        }
    }
}