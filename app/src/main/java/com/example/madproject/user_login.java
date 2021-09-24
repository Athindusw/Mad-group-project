package com.example.madproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class user_login extends AppCompatActivity {

//    Button callSignup, login_btn;
//    ImageView image;
//    TextView LogoText, sloganText;
    //Vaiables
    TextInputLayout regName,regUsername,regEmail,regPhoneNo,regPassword;
    Button regBtn, regToLoginBtn;

    FirebaseDatabase rootNode;
    DatabaseReference reference;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_login2);

        //Hooks to all xml elements in activity_user_login2
        regName = findViewById(R.id.reg_Name);
        regUsername = findViewById(R.id.reg_username);
        regEmail = findViewById(R.id.reg_email);
        regPhoneNo = findViewById(R.id.reg_phoneNo);
        regPassword = findViewById(R.id.reg_password);
        regBtn = findViewById(R.id.reg_btn);
        regToLoginBtn = findViewById(R.id.reg_login_btn);

        regBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rootNode = FirebaseDatabase.getInstance();
                //reference = rootNode.getReference( path: "users");

                reference.setValue("First data storage");
            }
        });



    }

//    private Boolean validateUsername() {
//        String val = username.getEditText().getText().toString();
//        //String noWhiteSpace = "\\A\\W{4,20}\\z";
//
//        if (val.isEmpty()) {
//            username.setError("Field can not be empty");
//            return  false;
//
//        } else {
//            username.setError(null);
//            username.setErrorEnabled(false);
//            return true;
//        }
//
//    }
//
//    private Boolean validatePassword(){
//        String val = password.getEditText().getText().toString();
//
//        if (val.isEmpty()){
//            password.setError("Field cannot be empty");
//            return false;
//        } else {
//            password.setError(null);
//            password.setErrorEnabled(false);
//            return  true;
//
//        }
//    }
//
//    public void loginUser (View view) {
//        if (!validateUsername() | !validatePassword()) {
//            return;
//        } else {
//            isUser();
//        }
//    }
//
//    private void isUser() {
//        String userEnteredUsername = username.getEditText().getText().toString().trim();
//        String userEnteredPassword = password.getEditText().getText().toString().trim();
//
//        DatabaseReference reference = FirebaseDatabase.getInstance().getReference( path  :"users");
//
//        Query checkUser = reference.orderByChild("username").equalTo(userEnteredUsername);
//
//        checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                if(dataSnapshot.exists()){
//
//                    username.setError(null);
//                    username.setErrorEnabled(false);
//
//                    String passwordFromDB = dataSnapshot.child(userEnteredUsername).child("password").getValue(String.class);
//
//                    if(passwordFromDB.equals(userEnteredPassword)){
//
//                        username.setError(null);
//                        username.setErrorEnabled(false);
//
//
//
//                        String nameFromDB = dataSnapshot.child(userEnteredUsername).child("name").getValue(String.class);
//                        String usernameFromDB = dataSnapshot.child(userEnteredUsername).child("password").getValue(String.class);
//                        String phoneNoFromDB = dataSnapshot.child(userEnteredUsername).child("phoneNo").getValue(String.class);
//                        String emailFromDB = dataSnapshot.child(userEnteredUsername).child("email").getValue(String.class);
//
//                        Intent intent = new Intent(getApplicationContext(),UserProfile.class);
//
//                        intent.getExtras( name: "name",nameFromDB);
//                        intent.getExtras( name: "username",usernameFromDB);
//                        intent.getExtras( name: "email",emailFromDB);
//                        intent.getExtras( name: "phoneNo",phoneNoFromDB);
//                        intent.getExtras( name: "password",passwordFromDB);
//
//                        startActivity(intent);
//                    }
//                    else{
//                        password.setError("wrong password");
//                        password.requestFocus();
//                    }
//                } else{
//                    username.setError("no such user exist")
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });
//    }


}