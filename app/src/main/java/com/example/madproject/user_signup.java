package com.example.madproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
//import android.view.WindowManager;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class user_signup extends AppCompatActivity {

    //Variables
    TextInputLayout regName, regUsername, regEmail, regPhoneNo, regPassword;
    Button regBtn, regToLoginBtn;
    //String name, username, email, phoneNO, password;

    FirebaseDatabase rootNode;
    DatabaseReference reference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_user_signup);

        //hooks to all xml elements activity_user_signup.xml
        regName = findViewById(R.id.reg_name);
        regUsername = findViewById(R.id.reg_username);
        regEmail = findViewById(R.id.reg_email);
        regPhoneNo = findViewById(R.id.reg_phoneNo);
        regPassword = findViewById(R.id.reg_password);
        regBtn = findViewById(R.id.reg_btn);
        regToLoginBtn = findViewById(R.id.reg_login_btn);


        regBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                name = regName.getEditText().getText().toString();
//                username = regUsername.getEditText().getText().toString();
//                email = regEmail.getEditText().getText().toString();
//                phoneNO = regPhoneNo.getEditText().getText().toString();
//                password = regPassword.getEditText().getText().toString();

                rootNode = FirebaseDatabase.getInstance();
                reference = rootNode.getReference("Feedback");

                //reference.setValue("First data store in DB");

                //Get all the values
                String name = regName.getEditText().getText().toString();
                String username = regUsername.getEditText().getText().toString();
                String email = regEmail.getEditText().getText().toString();
                String phoneNO = regPhoneNo.getEditText().getText().toString();
                String password = regPassword.getEditText().getText().toString();

                UserHelper helper = new UserHelper(name,username,email,phoneNO,password);

//                Log.e("my tag","New data Inserted" +  regName.getText().toString() + regUsername.getText().toString());
//                reference.setValue("New data inserted" +  regNameS + regUsernameS + regEmailS + regPhoneNoS + regPasswordS );

                reference.child(name).setValue(helper);
                //reference.setValue(helper);

            }
        });

    }

//    private Boolean validateName(){
//        String val = regName.getEditText().getText().toString();
//
//        if(val.isEmpty()) {
//            regName.setError("Field cannot be empty");
//            return false;
//        }
//        else{
//            regName.setError(null);
//             regName.setErrorEnabled(false);
//            return true;
//        }
//    };

//
//
//    private Boolean validateUsername(){
//        String val = regName.getEditText().getText().toString();
//        String noWhiteSpace = "(?=\\s+$)";
//
//        if(val.isEmpty()) {
//            regName.setError("Field cannot be empty");
//            return false;
//        }
//        else if  (val.length()>=15){
//            regName.setError("username too long");
//            return false;
//        }
//        else if (!val.matches(noWhiteSpace)){
//            regName.setError("White Spaces not Allowed");
//            return false;
//
//        }
//        else{
//            regName.setError(null);
//            return true;
//        }
//    };
//
//    private Boolean validateEmail(){
//        String val = regName.getEditText().getText().toString();
//        String emailPattern = "[a-zA-Z0-9._-] +@ [a-z]+\\.+[a-z]+";
//
//        if(val.isEmpty()) {
//            regName.setError("Field cannot be empty");
//            return false;
//        }
//        else if(!val.matches(emailPattern)) {
//            regName.setError("Invalid email address");
//            return false;
//        }
//        else{
//            regName.setError(null);
//            return true;
//        }
//    };
//
//    private Boolean validatePhoneNo(){
//        String val = regName.getEditText().getText().toString();
//
//        if(val.isEmpty()) {
//            regName.setError("Field cannot be empty");
//            return false;
//        }
//        else{
//            regName.setError(null);
//            return true;
//        }
//    };
//
//    private Boolean validatePassword(){
//        String val = regName.getEditText().getText().toString();
//
//        if(val.isEmpty()) {
//            regName.setError("Field cannot be empty");
//            return false;
//        }
//        else{
//            regName.setError(null);
//            return true;
//        }
//    };


//    public void registerUser(View view) {
//
//        if(!validateName() | !validatePassword() | !validatePhone() | !validateEmail() | !validateUsername()) {
//            return;
//        }
//
//         name = regName.getEditText().getText().toString();
//          username =regUsername.getEditText().getText().toString();
//         email =regEmail.getEditText().getText().toString();
//         phoneNo = regPhoneNo.getEditText().getText().toString();
//         password =regPassword.getEditText().getText().toString();
//
//
//        UserHelper helper = new UserHelper(name,username,email,phoneNO,password);
//        reference.child(username).setValue(helper);
//    }
}