package com.example.projectplanner2.presentation.invoice.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.projectplanner2.R;
import com.example.projectplanner2.presentation.invoice.model.DropDownModel;

import java.util.List;

public class CustomDropdownAdapter extends BaseAdapter {

    private Context context;
    private List<DropDownModel> itemList; // Replace with your list data type

    public CustomDropdownAdapter(Context context, List<DropDownModel> itemList) {
        this.context = context;
        this.itemList = itemList;
    }

    @Override
    public int getCount() {
        return itemList.size();
    }

    @Override
    public Object getItem(int position) {
        return itemList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(R.layout.custom_dropdown_item, parent, false);
        }

        TextView textView = convertView.findViewById(R.id.custom_dropdown_text);
        textView.setText(itemList.get(position).getValue());

        return convertView;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(R.layout.custom_dropdown_item, parent, false);
        }

        TextView textView = convertView.findViewById(R.id.custom_dropdown_text);
        textView.setText(itemList.get(position).getValue());

        return convertView;
    }
}
