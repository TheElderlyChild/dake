package com.application.dake.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import com.application.dake.R;
import com.application.dake.controllers.Restaurant_infoAdapter;
import com.application.dake.models.Restaurant;

public class HomePage extends AppCompatActivity {
    private RecyclerView restaurants;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        //restaurants = (RecyclerView) findViewById(R.id.scrollView2);
     Restaurant[] a;
        a=new Restaurant[5];
        Restaurant b;

        for(int i=0;i<5;i++){
            b= new Restaurant("le bistro",i,null, null,null );
            a[i]=b;
        }
        restaurants.setLayoutManager(new LinearLayoutManager(this));
        Restaurant_infoAdapter okay= new Restaurant_infoAdapter(a);
        restaurants.setAdapter(okay);
    }
}
