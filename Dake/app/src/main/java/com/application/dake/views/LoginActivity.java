package com.application.dake.views;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.application.dake.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = "LoginActivity";
    private Button btnCompleteLogin;
    private EditText inputEmail, inputPassword;
    private TextView textNewUser, textForgotPassword;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnCompleteLogin = (Button) findViewById(R.id.btnCompleteLogin);
        inputEmail = (EditText) findViewById(R.id.inputEmail);
        inputPassword = (EditText) findViewById(R.id.inputPassword);
        textNewUser = (TextView) findViewById(R.id.textNewUser);
        textForgotPassword = (TextView) findViewById(R.id.textForgotPassword);

        mAuth = FirebaseAuth.getInstance();

        textNewUser.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                gotoSignUp();
            }
        });

        btnCompleteLogin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                login(inputEmail.getText().toString(),inputPassword.getText().toString());
            }
        });
    }

    public void login(String email, String password){
        if (email.equals("") || email==null) {
            Toast.makeText(LoginActivity.this,
                    "Please type in your email",
                    Toast.LENGTH_LONG).show();
            return;
        }
        if (!validatePassword(password)){
            return;
        }
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            gotoHomePage();
                        } else {
                            // If sign in fails, display a message to the user.
                            displayLoginError(task);
                        }
                        // ...
                    }
                });
    }

    public void gotoSignUp(){
        Intent signUpIntent = new Intent(this, SignUpEmailActivity.class);
        finish();
        startActivity(signUpIntent);
    }

    public void gotoHomePage(){
        Intent homePageIntent = new Intent(this, HomePage.class);
        finish();
        startActivity(homePageIntent);
    }



    public void displayLoginError(Task<AuthResult> task){
        try
        {
            throw task.getException();
        }
        // if user enters wrong email.
        catch (FirebaseAuthInvalidUserException invalidEmail)
        {
            Log.w(TAG, "signInUserWithEmail:failure type:invalid_email", task.getException());
            Toast.makeText(LoginActivity.this,
                    "We don't recognize that email. Make sure to sign up if you are new.",
                    Toast.LENGTH_LONG).show();
        }
        // if user enters wrong password.
        catch (FirebaseAuthInvalidCredentialsException wrongPassword)
        {
            Log.w(TAG, "signInUserWithEmail:failure type:wrong_password", task.getException());
            Toast.makeText(LoginActivity.this,
                    "That's a wrong password. Please try again or use forgot password",
                    Toast.LENGTH_LONG).show();
        }
        catch (Exception e)
        {
            Log.w(TAG, "signInUserWithEmail:failure " + e.getMessage(), task.getException());
            Toast.makeText(LoginActivity.this,
                    "There seems to be an unknown error with the login. Please try again later",
                    Toast.LENGTH_LONG).show();
        }
    }

    public boolean validatePassword(String password){
        boolean result=true;
        if(password.equals("") || password==null){
            Toast.makeText(LoginActivity.this,
                    "Please type in a password",
                    Toast.LENGTH_LONG).show();
            result=false;
        }

        return result;
    }


}