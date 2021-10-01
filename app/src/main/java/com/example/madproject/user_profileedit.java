package com.example.madproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputLayout;

public class user_profileedit extends AppCompatActivity {

    TextInputLayout fullname,email,phoneNo,password;
    TextView fullnameLable, usernameLable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profileedit);

        //hooks
        fullname = findViewById(R.id.full_name_profile);
        email = findViewById(R.id.email_profile);
        phoneNo = findViewById(R.id.phone_no_profile);
        password = findViewById(R.id.password_profile);
        fullnameLable = findViewById(R.id.fullname_field);
        usernameLable = findViewById(R.id.username_field);

        showAllUserData();

    }
    private void showAllUserData() {
        Intent intent = getIntent();
        String user_username = intent.getStringExtra("username");
        String user_name = intent.getStringExtra("name");
        String user_email = intent.getStringExtra("email");
        String user_phoneNo = intent.getStringExtra("phoneNo");
        String user_password = intent.getStringExtra("password");

        fullnameLable.setText(user_name);
        usernameLable.setText(user_username);
        fullname.getEditText().setText(user_name);
        fullname.getEditText().setText(user_email);
        fullname.getEditText().setText(user_phoneNo);
        fullname.getEditText().setText(user_password);
    }
}