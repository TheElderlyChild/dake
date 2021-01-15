package com.application.dake.controllers;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.application.dake.R;
import com.application.dake.models.MenuItem;
import com.application.dake.models.Restaurant;
import com.application.dake.views.MenuItemSelectFragment;
import com.application.dake.views.RestaurantPreview;

import java.util.List;

public class MenuItemAdapter extends RecyclerView.Adapter<MenuItemAdapter.ViewHolder> {
    private List<MenuItem> localDataSet;
    private Context context;
    private static final String TAG = "Menu Item Adapter";
    private RecyclerView mRecyclerView;
    private final View.OnClickListener mOnClickListener = new View.OnClickListener(){
        @Override
        public void onClick(View view) {
            int itemPosition = mRecyclerView.getChildLayoutPosition(view);
            MenuItem item = localDataSet.get(itemPosition);
            RestaurantPreview activity = (RestaurantPreview) context;
            FragmentManager fm = activity.getSupportFragmentManager();
            MenuItemSelectFragment menuItemSelectFragment =
                    MenuItemSelectFragment.newInstance(item.getName(), item.getDescription(), String.valueOf(item.getPrice()));
            menuItemSelectFragment.show(fm, "fragment_menu_item");
        }
    };

    public MenuItemAdapter(Context context, List<MenuItem> localDataSet) {
        this.localDataSet = localDataSet;
        this.context = context;
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
        view.setOnClickListener(mOnClickListener);
        return new MenuItemAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MenuItemAdapter.ViewHolder holder, int position) {
        holder.getTextName().setText(localDataSet.get(position).getName());
        holder.getTextPrice().setText(String.valueOf(localDataSet.get(position).getPrice()));
        holder.getTextDescription().setText(localDataSet.get(position).getDescription());
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);

        mRecyclerView = recyclerView;
    }


    @Override
    public int getItemCount() {
        return localDataSet.size();
    }
}
