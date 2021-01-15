package com.application.dake.controllers;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.application.dake.R;
import com.application.dake.models.MenuItem;

import java.util.List;

public class MenuItemAdapter extends RecyclerView.Adapter<MenuItemAdapter.ViewHolder> {
    private List<MenuItem> localDataSet;
    private static final String TAG = "Menu Item Adapter";

    public MenuItemAdapter(List<MenuItem> localDataSet) {
        this.localDataSet = localDataSet;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView textName;
        private final TextView textDescription;
        private final TextView textPrice;

        public ViewHolder(@NonNull View view) {
            super(view);
            textName = view.findViewById(R.id.textName);
            textDescription = view.findViewById(R.id.textDescription);
            textPrice = view.findViewById(R.id.textPrice);
        }

        public TextView getTextName() {
            return textName;
        }

        public TextView getTextDescription() {
            return textDescription;
        }

        public TextView getTextPrice() {
            return textPrice;
        }
    }

    @NonNull
    @Override
    public MenuItemAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.menu_item, parent, false);
        return new MenuItemAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MenuItemAdapter.ViewHolder holder, int position) {
        holder.getTextName().setText(localDataSet.get(position).getName());
        holder.getTextPrice().setText(String.valueOf(localDataSet.get(position).getPrice()));
        holder.getTextDescription().setText(localDataSet.get(position).getDescription());
    }

    @Override
    public int getItemCount() {
        return localDataSet.size();
    }
}
