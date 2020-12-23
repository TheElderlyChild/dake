package com.application.dake.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.application.dake.R;

public class SignUpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
    }

    public void gotoSignUpDetails(View view){
        Intent signUpDetailsIntent = new Intent(this, SignUpDetails.class);
        startActivity(signUpDetailsIntent);
    }

    public void gotoLogin(View view){
        Intent LoginActivityIntent = new Intent(this, LoginActivity.class);
        startActivity(LoginActivityIntent);
    }
}