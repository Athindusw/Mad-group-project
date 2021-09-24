package com.example.madproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.RatingBar;
import android.widget.TextView;

public class userfeedback extends AppCompatActivity {

    TextView tvFeedback;
    RatingBar rbstars;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_feedback);

        tvFeedback = findViewById(R.id.tvFeedback);
        rbstars = findViewById(R.id.rbStars);


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
}