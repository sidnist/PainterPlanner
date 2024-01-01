package com.example.projectplanner2.presentation.invoice.model;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import java.util.List;

import com.example.projectplanner2.shared.Converters;
import com.google.firebase.crashlytics.buildtools.reloc.com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import java.lang.reflect.Type;

@Entity
public class InsertUpdateDataModel {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String costOfPaint;
    private String costPerManHour;
    private int spinnerPosition;
    @TypeConverters(Converters.class)
    private List<DropDownModel> downModelList;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCostOfPaint() {
        return costOfPaint;
    }

    public void setCostOfPaint(String costOfPaint) {
        this.costOfPaint = costOfPaint;
    }

    public String getCostPerManHour() {
        return costPerManHour;
    }

    public void setCostPerManHour(String costPerManHour) {
        this.costPerManHour = costPerManHour;
    }

    public int getSpinnerPosition() {
        return spinnerPosition;
    }

    public void setSpinnerPosition(int spinnerPosition) {
        this.spinnerPosition = spinnerPosition;
    }

    public List<DropDownModel> getDownModelList() {
        return downModelList;
    }

    public void setDownModelList(List<DropDownModel> downModelList) {
        this.downModelList = downModelList;
    }

    public InsertUpdateDataModel(int id, String costOfPaint, String costPerManHour, int spinnerPosition, List<DropDownModel> downModelList) {
        this.id = id;
        this.costOfPaint = costOfPaint;
        this.costPerManHour = costPerManHour;
        this.spinnerPosition = spinnerPosition;
        this.downModelList = downModelList;
    }
// Store the list as a JSON string

}
