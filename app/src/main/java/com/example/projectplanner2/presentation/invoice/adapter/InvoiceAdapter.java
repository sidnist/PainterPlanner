package com.example.projectplanner2.presentation.invoice.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectplanner2.databinding.InvoiceLayoutBinding;
import com.example.projectplanner2.presentation.invoice.model.InvoiceModel;
import com.example.projectplanner2.presentation.tabs.model.InsertHomeItemModel;

import java.util.ArrayList;

public class InvoiceAdapter extends RecyclerView.Adapter<InvoiceAdapter.ViewHolder> {

    ArrayList<InsertHomeItemModel> list;

    public InvoiceAdapter(ArrayList<InsertHomeItemModel> list) {
        this.list = list;
    }
    InvoiceLayoutBinding binding;
    @NonNull
    @Override
    public InvoiceAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = InvoiceLayoutBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull InvoiceAdapter.ViewHolder holder, int position) {
        InsertHomeItemModel model = list.get(position);
        if (position==0){
            holder.binding.header.setVisibility(View.VISIBLE);
        }

        else {
            holder.binding.header.setVisibility(View.GONE);

        }
        if(position == list.size() -1 ){
            holder.binding.rowFooter.setVisibility(View.VISIBLE);
        }
        else {
            holder.binding.rowFooter.setVisibility(View.GONE);
        }
        holder.binding.tvAvg.setText(model.getName());
        holder.binding.tvItem.setText(model.getPhoneNo());
        holder.binding.tvQuantity.setText(model.getTotalCost());
        holder.binding.tvCost.setText(model.getTotalCost());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        InvoiceLayoutBinding binding;
        public ViewHolder(InvoiceLayoutBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;
        }
    }
}
