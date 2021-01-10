package com.application.dake.views;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.application.dake.R;
import com.application.dake.controllers.Restaurant_infoAdapter;
import com.application.dake.models.Restaurant;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class HomePage extends AppCompatActivity {
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

        Restaurant_infoAdapter adapter = new Restaurant_infoAdapter(this, restaurants);
        card.setLayoutManager(new LinearLayoutManager(this));
        card.setAdapter(adapter);
        restaurantCards.addView(card);

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
}
