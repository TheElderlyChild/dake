package com.application.dake.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.application.dake.R;

public class SplashScreen extends AppCompatActivity {

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
        setContentView(R.layout.screen_splash);
        gotoSignUp();
        finish();
    }

    public void gotoSignUp(){
        Intent signUpIntent = new Intent(this, HomePage.class);
        startActivity(signUpIntent);
    }

}