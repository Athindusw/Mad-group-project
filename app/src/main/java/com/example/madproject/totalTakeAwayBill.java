package com.example.madproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import com.tapadoo.alerter.Alerter;

public class totalTakeAwayBill extends AppCompatActivity {
    private Button tb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_total_take_away_bill);

        tb = findViewById(R.id.tb);

//        tb.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                showAlerter(v);
//            }
//        });
    }

//    public void showAlerter(View v)
//    {
//        Alerter.create(this)
//                .setTitle("Your details successfully recorded")
//                .setText("Thank You!!!")
////                .setIcon(
////                        R.drawable.ic_baseline_sentiment_satisfied_alt_24)
//                .setBackgroundColorRes(
//                        R.color.timeButton)
//                .setDuration(3000)
//                .show();
//    }
}