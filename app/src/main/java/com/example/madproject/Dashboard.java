package com.example.madproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
//import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputLayout;

public class Dashboard extends AppCompatActivity {

    Button callSignUp, login_btn;
    ImageView image;
    TextView logoText, sloganText;
    TextInputLayout username, password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_dashboard);

        callSignUp = findViewById(R.id.signup_Screen);

        callSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Dashboard.this,user_signup.class);
                startActivity(intent);
            }
        });

    }

//    private Boolean validateUsername() {
//        String val = username.getEditText().getText().toString();
//
//        if(val.isEmpty()) {
//            regName.setError("Field cannot be empty");
//            return false;
//        }
//        else{
//            regName.setError(null);
//            username.setErrorEnable(false);
//            return true;
//        }
//    };

//    private Boolean validatePassword() {
//        String val = password.getEditText().getText().toString();
//
//        if(val.isEmpty()) {
//            regName.setError("Field cannot be empty");
//            return false;
//        }
//        else{
//            regName.setError(null);
//            username.setErrorEnable(false);
//            return true;
//        }
//    };
//
//    public void loginUser(View view) {
//        //validate Login Info
//        if (!validateUsername() | !validatePassword()) {
//            return;
//        }
//        else{
//            isUser();
//        }
//
//    }
//    private void isUser(){
//        String userEnteredUsername = username.getEditText().toString().train();
//    }
//}
