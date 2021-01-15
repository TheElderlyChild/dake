package com.application.dake.views;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.application.dake.R;
import com.application.dake.controllers.MenuItemAdapter;
import com.application.dake.models.MenuItem;
import com.application.dake.models.Restaurant;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.ListResult;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;

public class RestaurantPreview extends AppCompatActivity {

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private FirebaseStorage storage = FirebaseStorage.getInstance();
    private Restaurant restaurant;
    private TextView textName;
    private TextView textAddress;
    private TextView textCategory;
    private RecyclerView recyclerMenu;
    private static final String TAG = "RestaurantPreview";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_preview);

        textName = findViewById(R.id.textName);
        textAddress = findViewById(R.id.textAddress);
        textCategory = findViewById(R.id.textCategory);
        recyclerMenu = findViewById(R.id.recyclerMenu);

        Intent intent = getIntent();
        String id=intent.getStringExtra("id");

        DocumentReference dr = db.collection("restaurants").document(id);
        Task<DocumentSnapshot> getRestaurant = dr.get();
        getRestaurant.addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>(){
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document=task.getResult();
                    if (document.exists()) {
                        //Fill in information for the restaurant
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

        //Fill in menu items of the restaurant
        List<MenuItem> menu = new ArrayList<MenuItem>();
        MenuItemAdapter adapter = new MenuItemAdapter(this, menu);
        recyclerMenu.setLayoutManager(new LinearLayoutManager(this));
        recyclerMenu.setAdapter(adapter);

        CollectionReference menuCollection = db.collection("restaurants")
                .document(id).collection("menu");
        menuCollection.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        Log.v(TAG,document.getId()+ " => " + document.getData());
                        MenuItem menuItem = document.toObject(MenuItem.class).withId(document.getId());
                        menu.add(menuItem);
                        adapter.notifyDataSetChanged();
                    }
                } else {
                    Log.w(TAG, "Error getting documents: ", task.getException());
                }
            }
        });

        //Fill in preview images of the restaurant
        /*
        List<StorageReference> previewImageList=new ArrayList<StorageReference>();

        StorageReference listRef = storage.getReference().child("restaurantPreviewImages/"+id);
        listRef.listAll().addOnSuccessListener(new OnSuccessListener<ListResult>() {
            @Override
            public void onSuccess(ListResult listResult) {
                previewImageList.addAll(listResult.getItems());
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                // Uh-oh, an error occurred!
            }
        });

         */
    }

}