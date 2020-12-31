package com.application.dake.controllers;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.application.dake.R;
import com.application.dake.models.Restaurant;

public class Restaurant_infoAdapter extends RecyclerView.Adapter<Restaurant_infoAdapter.ViewHolder> {
    private Restaurant[] localDataSet;

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView name;
        private final TextView address;
        private final ImageView picture;
        private final TextView rating;
        private final TextView type;

        public ViewHolder(View view) {
            super(view);
            // Define click listener for the ViewHolder's View

            name = (TextView) view.findViewById(R.id.restaurantName);
            address= (TextView) view.findViewById(R.id.restaurantAddress);
            picture= (ImageView) view.findViewById(R.id.restaurantImage);
            rating= (TextView) view.findViewById(R.id.restaurantRating);
            type=(TextView) view.findViewById(R.id.restaurantFoodType);
        }
        public TextView getName() {
            return name;
        }

        public TextView getAddress() {
            return address;
        }

        public ImageView getPicture() {
            return picture;
        }

        public TextView getRating() {
            return rating;
        }

        public TextView getType() {
            return type;
        }
    }

    /**
     * Initialize the dataset of the Adapter.
     *
     * @param dataSet String[] containing the data to populate views to be used
     * by RecyclerView.
     */
    public Restaurant_infoAdapter(Restaurant[] dataSet) {
        localDataSet = dataSet;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.restaurant_info, viewGroup, false);

        return new ViewHolder(view);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        viewHolder.getName().setText(localDataSet[position].getName());
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return localDataSet.length;
    }
}
