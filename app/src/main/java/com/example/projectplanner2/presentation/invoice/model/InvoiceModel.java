package com.example.projectplanner2.presentation.invoice.model;

public class InvoiceModel {
    String tvItem, tvQuantity, tvAvg, tvTotalCost;

    public String getTvItem() {
        return tvItem;
    }

    public void setTvItem(String tvItem) {
        this.tvItem = tvItem;
    }

    public String getTvQuantity() {
        return tvQuantity;
    }

    public void setTvQuantity(String tvQuantity) {
        this.tvQuantity = tvQuantity;
    }

    public String getTvAvg() {
        return tvAvg;
    }

    public void setTvAvg(String tvAvg) {
        this.tvAvg = tvAvg;
    }

    public String getTvTotalCost() {
        return tvTotalCost;
    }

    public void setTvTotalCost(String tvTotalCost) {
        this.tvTotalCost = tvTotalCost;
    }

    public InvoiceModel(String tvItem, String tvQuantity, String tvAvg, String tvTotalCost) {
        this.tvItem = tvItem;
        this.tvQuantity = tvQuantity;
        this.tvAvg = tvAvg;
        this.tvTotalCost = tvTotalCost;
    }
}
