package com.example.projectplanner2.presentation.invoice.model;

public class DropDownModel {
    String value, price;
    public String getValue() {
        return value;
    }
    public void setValue(String value) {
        this.value = value;
    }
    public String getPrice() {
        return price;
    }
    public void setPrice(String price) {
        this.price = price;
    }
    public DropDownModel(String value, String price) {
        this.value = value;
        this.price = price;
    }
}
