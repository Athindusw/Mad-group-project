package com.example.madproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;

public class userfeedback extends AppCompatActivity {

    TextView tvFeedback,fName,fEmail, fFeedback;
    Button fSend;
    RatingBar rbstars;
    DatabaseReference rootNode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_feedback);

        tvFeedback = findViewById(R.id.tvFeedback);
        rbstars = findViewById(R.id.rbStars);
        fName = findViewById(R.id.Fname);
        fEmail = findViewById(R.id.Femail);
        fFeedback = findViewById(R.id.Ffeedback);
        fSend = findViewById(R.id.Fsend);

        rootNode = FirebaseDatabase.getInstance().getReference().child("Feedback");

        fSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                insertFeedbackData();

            }
        });

        rbstars.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {

                if(rating==0){
                    tvFeedback.setText("Very Dissatisfied");
                }
                else if(rating==1){
                    tvFeedback.setText("Dissatisfied");
                }
                else if(rating==2 || rating==3){
                    tvFeedback.setText("OK");
                }
                else if(rating==4){
                    tvFeedback.setText("Satisfied");
                }
                else if(rating==5){
                    tvFeedback.setText("Very Satisfied");
                }

            }
        });
    }
    private void insertFeedbackData(){

        String name1 = fName.getText().toString().trim();
        String email1 = fEmail.getText().toString().trim();
        String feedback1 = fFeedback.getText().toString().trim();

        Feedbackdata feedbackdata = new Feedbackdata(name1,email1,feedback1);

        rootNode.push().setValue(feedbackdata);
        Toast.makeText(userfeedback.this,"Data inserted", Toast.LENGTH_SHORT).show();


    }


}