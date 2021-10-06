package com.example.madproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
//import android.view.WindowManager;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.io.File;

public class Dashboard extends AppCompatActivity {

    Button callSignUp, login_btn;
    ImageView image;
    TextView logoText, sloganText;
    TextInputLayout emailTxt, passwordTxt;
    ProgressBar progressBar;
    String userEnteredUsername,userEnteredPassword;

    FirebaseAuth fAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_dashboard);

        emailTxt = findViewById(R.id.email12);
        passwordTxt = findViewById(R.id.password12);
        progressBar = findViewById(R.id.reg_progressBar);
        fAuth = FirebaseAuth.getInstance();
        callSignUp = findViewById(R.id.signup_Screen);
        login_btn = findViewById(R.id.login_button);
//
//        userEnteredUsername = usernameTxt.getEditText().toString();
//        userEnteredPassword = passwordTxt.getEditText().toString();
        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = emailTxt.getEditText().getText().toString().trim();
                String password = passwordTxt.getEditText().getText().toString().trim();

                if (TextUtils.isEmpty(email)){
                    emailTxt.setError("Email is required !");
                    return;
                }
                if (TextUtils.isEmpty(password)){
                    passwordTxt.setError("Password is required !");
                    return;
                }
                if (password.length()<6){
                    passwordTxt.setError("Password must 6 characters");
                    return;
                }
                progressBar.setVisibility(View.VISIBLE);

                fAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(Dashboard.this, "Logged in successfully", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),user_profileedit.class));
                        }else {
                            Toast.makeText(Dashboard.this, "Error!" + task.getException().getMessage() ,Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);
                        }
                    }
                });

            }
        });




        callSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Dashboard.this, user_signup.class);
                startActivity(intent);


            }

        });
//        login_btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Log.e("my tag","New data Inserted" +  usernameTxt.getEditText().toString() + passwordTxt.getEditText().toString());
////                isUser();
//                Intent intent1 = new Intent(Dashboard.this, user_profileedit.class);
//                startActivity(intent1);
//            }
//        });

    }



}
