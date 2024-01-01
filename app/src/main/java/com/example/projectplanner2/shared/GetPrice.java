package com.example.projectplanner2.shared;

import com.example.projectplanner2.presentation.invoice.model.DropDownModel;

import java.util.ArrayList;
import java.util.List;

public class GetPrice {

    List<DropDownModel> dropDownModels = new ArrayList<>();
    public List<DropDownModel> getDropDownList() {

        dropDownModels.add(new DropDownModel("Washable","35"));
        dropDownModels.add(new DropDownModel("Odourless","40"));
        dropDownModels.add(new DropDownModel("Metallic","35"));
        dropDownModels.add(new DropDownModel("Mosquito-repelling","45"));
        dropDownModels.add(new DropDownModel("Basic","30"));
        dropDownModels.add(new DropDownModel("Weather-proof","50"));
        return dropDownModels;
    }
}
