package com.application.dake.views;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.application.dake.R;
import com.application.dake.models.Restaurant;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class RestaurantPreview extends AppCompatActivity {

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private Restaurant restaurant;
    private TextView textName;
    private TextView textAddress;
    private TextView textCategory;
    private static final String TAG = "RestaurantPreview";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_preview);

        textName = findViewById(R.id.textName);
        textAddress = findViewById(R.id.textAddress);
        textCategory = findViewById(R.id.textCategory);

        Intent intent = getIntent();
        String id=intent.getStringExtra("id");
        DocumentReference dr = db.collection("restaurants").document(id);
        dr.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>(){
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document=task.getResult();
                    if (document.exists()) {
                        Log.d(TAG, "DocumentSnapshot data: " + document.getData());
                        Restaurant restaurant = document.toObject(Restaurant.class).withId(document.getId());
                        textName.setText(restaurant.getName());
                        textAddress.setText(restaurant.getAddress());
                        textCategory.setText(restaurant.getDescription());
                    } else {
                        Log.d(TAG, "No such document");
                    }
                } else {
                    Log.w(TAG, "Error getting document: ", task.getException());
                }
            };
        });
    }

}