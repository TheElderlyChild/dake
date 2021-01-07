package com.application.dake.views;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.application.dake.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;
import com.google.firebase.auth.SignInMethodQueryResult;

import java.util.List;

public class SignUpEmailActivity extends AppCompatActivity {

    private static final String TAG = "SignUpActivity";
    private Button btnSignUpEmail;
    private EditText inputEmail;
    private CheckBox checkboxUpdate;
    private FirebaseAuth mAuth;
    private boolean valid;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_email);

        btnSignUpEmail= (Button) findViewById(R.id.btnEmailSignUp);
        inputEmail = (EditText) findViewById(R.id.inputEmail);
        checkboxUpdate= (CheckBox) findViewById(R.id.checkboxUpdate);

        mAuth = FirebaseAuth.getInstance();
    }


    public void addEmailToSignUp(){

    }
}