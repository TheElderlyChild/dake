package com.application.dake.controllers;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.application.dake.AboutUsActivity;
import com.application.dake.HelpActivity;
import com.application.dake.NotificationsActivity;
import com.application.dake.OrdersActivity;
import com.application.dake.ProfileActivity;
import com.application.dake.R;
import com.application.dake.RewardsActivity;
import com.application.dake.models.AccountItem;

import java.util.ArrayList;

import static com.application.dake.R.layout.activity_account_placeholders;

public class AccountAdapter extends RecyclerView.Adapter<AccountAdapter.AccountViewHolder> {
    private ArrayList<AccountItem> mAccountList;
    private RecyclerView accountRecycler;
    private Context context;
    private final View.OnClickListener mOnClickListener = new View.OnClickListener(){
        @Override
        public void onClick(View view) {
            int itemPosition = accountRecycler.getChildLayoutPosition(view);
            Intent intent;
            switch(itemPosition){
                case 1:
                    intent= new Intent(context, ProfileActivity.class);
                    break;
                case 2:
                    intent= new Intent(context, OrdersActivity.class);
                    break;
                case 3:
                    intent= new Intent(context, NotificationsActivity.class);
                    break;
                case 4:
                    intent= new Intent(context, HelpActivity.class);
                    break;
                case 5:
                    intent= new Intent(context, RewardsActivity.class);
                    break;
                case 6:
                    intent= new Intent(context, AboutUsActivity.class);
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + itemPosition);
            }
            context.startActivity(intent);
        }
    };

    public AccountAdapter(ArrayList<AccountItem> accountsList) {
    }

    public static class AccountViewHolder extends RecyclerView.ViewHolder{
        private ImageView imageResource;
        private TextView item;

        public AccountViewHolder( View itemView) {
            super(itemView);
            imageResource=itemView.findViewById(R.id.accountImage);
            item= itemView.findViewById(R.id.accountText);
        }
    }

    public AccountAdapter(ArrayList<AccountItem> accountItemArrayList, Context context){
        mAccountList= accountItemArrayList;
        this.context= context;
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

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);

        accountRecycler = recyclerView;
    }

}
