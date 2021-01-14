package com.application.dake.controllers;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;
import com.application.dake.models.Restaurant;
import com.application.dake.R;
import com.bumptech.glide.Glide;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.List;

public class RestaurantInfoAdapter extends RecyclerView.Adapter<RestaurantInfoAdapter.ViewHolder> {
    private List<Restaurant> localDataSet;
    private Context context;
    private static final String TAG = "Adapter";

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

        /*
        public TextView getRating() {
            return rating;
        }

         */

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
    public RestaurantInfoAdapter(Context context, List<Restaurant> dataSet) {
        this.context = context;
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
        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference storageRef = storage.getReference();

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        viewHolder.getName().setText(localDataSet.get(position).getName());
        viewHolder.getAddress().setText(localDataSet.get(position).getAddress());
        viewHolder.getType().setText(localDataSet.get(position).getType());
        // Create a reference to a file from a Google Cloud Storage URI
        StorageReference gsReference = storageRef.child(localDataSet.get(position).getImageUrl());
        // Download directly from StorageReference using Glide
        // (See MyAppGlideModule for Loader registration)

        Glide.with(context /* context */)
                .load(gsReference)
                .into(viewHolder.getPicture());
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return localDataSet.size();
    }
}
