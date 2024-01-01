package com.example.projectplanner2.presentation.tabs.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectplanner2.databinding.HomeLayoutBinding;
//import com.example.projectplanner2.presentation.tabs.model.HomeItem;
import com.example.projectplanner2.presentation.tabs.model.InsertHomeItemModel;

import java.util.ArrayList;


public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder> {
    public HomeAdapter(OnItemClick onClickItem, ArrayList<InsertHomeItemModel> listItem) {
        this.onClickItem = onClickItem;
        this.listItem = listItem;
    }
    OnItemClick onClickItem;

    HomeLayoutBinding binding;
    ArrayList<InsertHomeItemModel> listItem;
    @NonNull
    @Override
    public HomeAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = HomeLayoutBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeAdapter.ViewHolder holder, int position) {

        InsertHomeItemModel item = listItem.get(position);
        holder.binding.tvCost.setText(item.getTotalCost());
        holder.binding.tvDate.setText(item.getDate());
        holder.binding.tvName.setText(item.getName());

        holder.binding.row.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickItem.onClickListener(item);
            }
        });


    }

    @Override
    public int getItemCount() {
        return listItem.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        HomeLayoutBinding binding;


        public ViewHolder(HomeLayoutBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;
        }
    }

   public interface OnItemClick{
        void  onClickListener(InsertHomeItemModel data);
    }

}
