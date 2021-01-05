package com.application.dake.views;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import com.application.dake.R;
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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
    }
    public void signUp(String firstName, String lastName, String userName, String email, String phoneNumber, String passCode, String confirmPassCode ){

    }

    private boolean validateString(String v){
        boolean result=true;
        if(v.equals("") || v==null){
            Toast.makeText(SignUpActivity.this,
                    "Please type in a password",
                    Toast.LENGTH_LONG).show();
            result=false;
        }

        return result;
    }
    private boolean equalPasswords(){

    }
    private boolean emailUsedBefore(){

    }

}