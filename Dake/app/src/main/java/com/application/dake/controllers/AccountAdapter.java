package com.application.dake.controllers;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.application.dake.R;
import com.application.dake.models.AccountItem;

import java.util.ArrayList;

import static com.application.dake.R.layout.activity_account_placeholders;

public class AccountAdapter extends RecyclerView.Adapter<AccountAdapter.AccountViewHolder> {
    private ArrayList<AccountItem> mAccountList;
    public static class AccountViewHolder extends RecyclerView.ViewHolder{
        public ImageView imageResource;
        public TextView item;

        public AccountViewHolder( View itemView) {
            super(itemView);
            imageResource=itemView.findViewById(R.id.accountImage);
            item= itemView.findViewById(R.id.accountText);
        }
    }
    public AccountAdapter(ArrayList<AccountItem> accountItemArrayList){
        mAccountList= accountItemArrayList;
    }


    @Override
    public AccountViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(activity_account_placeholders,parent,false);
        AccountViewHolder acc= new AccountViewHolder(v);
        return acc;
    }

    @Override
    public void onBindViewHolder(AccountViewHolder holder, int position) {
        AccountItem currentItem=mAccountList.get(position);

        holder.imageResource.setImageResource(currentItem.getImageResource());
        holder.item.setText(currentItem.getItem());
    }

    @Override
    public int getItemCount() {
        return mAccountList.size();
    }
}
