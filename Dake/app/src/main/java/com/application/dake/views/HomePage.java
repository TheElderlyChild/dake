package com.application.dake.views;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.application.dake.R;
import com.application.dake.controllers.RestaurantInfoAdapter;
import com.application.dake.models.Restaurant;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.widget.Autocomplete;
import com.google.android.libraries.places.widget.AutocompleteActivity;
import com.google.android.libraries.places.widget.model.AutocompleteActivityMode;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HomePage extends AppCompatActivity {
    private ImageView profileIcon;
    private ImageView searchIcon;
    private TextView addressInput;
    private LinearLayout restaurantCards;
    private static final String TAG = "HomePage";
    private static int AUTOCOMPLETE_REQUEST_CODE = 1;
    private ArrayList<Restaurant> restaurants;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        restaurantCards=(LinearLayout) findViewById(R.id.ll);
        RecyclerView card=new RecyclerView(this);
        CollectionReference cr = db.collection("restaurants");
        restaurants = new ArrayList<Restaurant>();

        addressInput=findViewById(R.id.addressInput);
        profileIcon=findViewById(R.id.profileIcon);
        searchIcon=findViewById(R.id.searchIcon);

        RestaurantInfoAdapter adapter = new RestaurantInfoAdapter(this, restaurants);
        card.setLayoutManager(new LinearLayoutManager(this));
        card.setAdapter(adapter);
        restaurantCards.addView(card);

        profileIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoAccounts(v);
            }
        });

        searchIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoSearch(v);
            }
        });

        addressInput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //selectAddress(v);
            }
        });

        cr.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        Log.d(TAG, document.getId() + " => " + document.getData());
                        Restaurant restaurant = document.toObject(Restaurant.class).withId(document.getId());
                        restaurants.add(restaurant);
                    }
                    adapter.notifyDataSetChanged();
                } else {
                    Log.w(TAG, "Error getting documents: ", task.getException());
                }
            };
        });

    }

    public void gotoAccounts(View view){
        Intent accountIntent = new Intent(this, AccountActivity.class);
        startActivity(accountIntent);
    }

    public void gotoSearch(View view){
        Intent searchIntent = new Intent(this, SearchActivity.class);
        startActivity(searchIntent);
    }

    public void selectAddress(View view){
        // Set the fields to specify which types of place data to
        // return after the user has made a selection.
        List<Place.Field> fields = Arrays.asList(Place.Field.ID, Place.Field.NAME, Place.Field.ADDRESS);

        // Start the autocomplete intent.
        Intent intent = new Autocomplete.IntentBuilder(AutocompleteActivityMode.FULLSCREEN, fields)
                .build(this);
        startActivityForResult(intent, AUTOCOMPLETE_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == AUTOCOMPLETE_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                Place place = Autocomplete.getPlaceFromIntent(data);
                addressInput.setText(place.getAddress());
                Log.i(TAG, "Place: " + place.getName() + ", " + place.getId());

            } else if (resultCode == AutocompleteActivity.RESULT_ERROR) {
                // TODO: Handle the error.
                Status status = Autocomplete.getStatusFromIntent(data);
                Log.i(TAG, status.getStatusMessage());
            } else if (resultCode == RESULT_CANCELED) {
                // The user canceled the operation.
            }
            return;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
