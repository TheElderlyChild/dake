package com.application.dake.views;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.application.dake.R;
import com.application.dake.controllers.RestaurantInfoAdapter;
import com.application.dake.models.Restaurant;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class HomePage extends AppCompatActivity {
    private ImageView profileIcon;
    private ImageView searchIcon;
    private LinearLayout restaurantCards;
    private static final String TAG = "HomePage";
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

        cr.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        Log.d(TAG, document.getId() + " => " + document.getData());
                        Restaurant restaurant = document.toObject(Restaurant.class);
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
}
