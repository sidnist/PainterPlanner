package com.example.projectplanner2.presentation.tabs.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class InsertHomeItemModel implements Parcelable{
    protected InsertHomeItemModel(Parcel in) {
        id = in.readInt();
        name = in.readString();
        phoneNo = in.readString();
        date = in.readString();
        paintArea = in.readString();
        typeOfPaint = in.readString();
        colorOfPaint = in.readString();
        timeRequired = in.readString();
        levelOfSurface = in.readFloat();
        hourAndLabourCost = in.readFloat();
        totalCost = in.readString();
        labourCost = in.readString();
        paintLiter = in.readString();
    }

    public static final Creator<InsertHomeItemModel> CREATOR = new Creator<InsertHomeItemModel>() {
        @Override
        public InsertHomeItemModel createFromParcel(Parcel in) {
            return new InsertHomeItemModel(in);
        }

        @Override
        public InsertHomeItemModel[] newArray(int size) {
            return new InsertHomeItemModel[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPaintArea() {
        return paintArea;
    }

    public void setPaintArea(String paintArea) {
        this.paintArea = paintArea;
    }

    public String getTypeOfPaint() {
        return typeOfPaint;
    }

    public void setTypeOfPaint(String typeOfPaint) {
        this.typeOfPaint = typeOfPaint;
    }

    public String getColorOfPaint() {
        return colorOfPaint;
    }

    public void setColorOfPaint(String colorOfPaint) {
        this.colorOfPaint = colorOfPaint;
    }

    public String getTimeRequired() {
        return timeRequired;
    }

    public void setTimeRequired(String timeRequired) {
        this.timeRequired = timeRequired;
    }

    public float getLevelOfSurface() {
        return levelOfSurface;
    }

    public void setLevelOfSurface(float levelOfSurface) {
        this.levelOfSurface = levelOfSurface;
    }

    public float getHourAndLabourCost() {
        return hourAndLabourCost;
    }

    public void setHourAndLabourCost(float hourAndLabourCost) {
        this.hourAndLabourCost = hourAndLabourCost;
    }

    public String getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(String totalCost) {
        this.totalCost = totalCost;
    }

    public String getLabourCost() {
        return labourCost;
    }

    public void setLabourCost(String labourCost) {
        this.labourCost = labourCost;
    }

    public String getPaintLiter() {
        return paintLiter;
    }

    public void setPaintLiter(String paintLiter) {
        this.paintLiter = paintLiter;
    }

    @PrimaryKey(autoGenerate = true)
    private int id;
    String name, phoneNo, date, paintArea, typeOfPaint, colorOfPaint, timeRequired;
    float levelOfSurface;
    float hourAndLabourCost;
    String totalCost, labourCost, paintLiter;

    public InsertHomeItemModel(int id, String name, String phoneNo, String date, String paintArea, String typeOfPaint, String colorOfPaint, String timeRequired, float levelOfSurface, float hourAndLabourCost, String totalCost, String labourCost, String paintLiter) {
        this.id = id;
        this.name = name;
        this.phoneNo = phoneNo;
        this.date = date;
        this.paintArea = paintArea;
        this.typeOfPaint = typeOfPaint;
        this.colorOfPaint = colorOfPaint;
        this.timeRequired = timeRequired;
        this.levelOfSurface = levelOfSurface;
        this.hourAndLabourCost = hourAndLabourCost;
        this.totalCost = totalCost;
        this.labourCost = labourCost;
        this.paintLiter = paintLiter;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeString(phoneNo);
        dest.writeString(date);
        dest.writeString(paintArea);
        dest.writeString(typeOfPaint);
        dest.writeString(colorOfPaint);
        dest.writeString(timeRequired);
        dest.writeFloat(levelOfSurface);
        dest.writeFloat(hourAndLabourCost);
        dest.writeString(totalCost);
        dest.writeString(labourCost);
        dest.writeString(paintLiter);
    }
}
