package com.example.projectplanner2.Room;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class InsertQuoteData {
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

    public String getLevelOfSurface() {
        return levelOfSurface;
    }

    public void setLevelOfSurface(String levelOfSurface) {
        this.levelOfSurface = levelOfSurface;
    }

    public String getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(String totalCost) {
        this.totalCost = totalCost;
    }

    public InsertQuoteData(int id, String name, String phoneNo, String date, String paintArea, String typeOfPaint, String colorOfPaint, String timeRequired, String levelOfSurface, String totalCost) {
        this.id = id;
        this.name = name;
        this.phoneNo = phoneNo;
        this.date = date;
        this.paintArea = paintArea;
        this.typeOfPaint = typeOfPaint;
        this.colorOfPaint = colorOfPaint;
        this.timeRequired = timeRequired;
        this.levelOfSurface = levelOfSurface;
        this.totalCost = totalCost;
    }

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String name;
    private String phoneNo;
    private String date;
    private String paintArea;
    private String typeOfPaint;
    private String colorOfPaint;
    private String timeRequired;
    private String levelOfSurface;
    private String totalCost;
}
