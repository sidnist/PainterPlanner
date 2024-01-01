package com.example.projectplanner2.presentation.invoice.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class DateModel implements Parcelable {
    String startDate, endDate;

    protected DateModel(Parcel in) {
        startDate = in.readString();
        endDate = in.readString();
    }

    public static final Creator<DateModel> CREATOR = new Creator<DateModel>() {
        @Override
        public DateModel createFromParcel(Parcel in) {
            return new DateModel(in);
        }

        @Override
        public DateModel[] newArray(int size) {
            return new DateModel[size];
        }
    };

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public DateModel(String startDate, String endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeString(startDate);
        dest.writeString(endDate);
    }
}
