package com.application.dake.views;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import com.application.dake.R;
import com.google.firebase.auth.FirebaseAuth;
import com.hbb20.CountryCodePicker;

public class SignUpActivity extends AppCompatActivity {
    private EditText firstName;
    private EditText lastName;
    private EditText userName;
    private EditText phoneNumber;
    private EditText passCode;
    private EditText email;
    private EditText confirmPassCode;
    private CountryCodePicker areaCode;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        mAuth=FirebaseAuth.getInstance();
    }


    public void signUp(String firstName, String lastName, String userName, String email, String phoneNumber, String passCode, String confirmPassCode, String areaCode){
        if(!validateString(firstName)){
            Toast.makeText(SignUpActivity.this,
                    "Please type in a first name",
                    Toast.LENGTH_LONG).show();
            return;
        }
        if(!validateString(lastName)){
            Toast.makeText(SignUpActivity.this,
                    "Please type in a last name ",
                    Toast.LENGTH_LONG).show();
            return;
        }
        if(!validateString(email)){
            Toast.makeText(SignUpActivity.this,
                    "Please type in a email",
                    Toast.LENGTH_LONG).show();
            return;
        }
        if(!validateString(passCode)){
            Toast.makeText(SignUpActivity.this,
                    "Please type in a password ",
                    Toast.LENGTH_LONG).show();
            return;
        }
        if(!equalPasswords(passCode, confirmPassCode)){
            Toast.makeText(SignUpActivity.this,
                    "Passwords are not equal",
                    Toast.LENGTH_LONG).show();
            return;
        }
        if(!validateString(phoneNumber)){
            Toast.makeText(SignUpActivity.this,
                    "Please type in a phone number ",
                    Toast.LENGTH_LONG).show();
            return;
        }
    }


    private boolean validateString(String v){
        boolean result=true;
        if(v.equals("") || v==null){
            result=false;
        }

        return result;
    }


    private boolean equalPasswords(String password, String passwordConfirm){
        boolean result=true;
        if(password!=passwordConfirm){
            return false;
        }
        return result;
    }


    private boolean emailUsedBefore(){
        //Fix later.
        return true;
    }

}