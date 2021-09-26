package com.example.madproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.security.AccessController;
import java.security.KeyStore;
import java.util.MissingResourceException;

public class DisplayTakeAwayDetails extends AppCompatActivity {
     TextView txt1;
     TextView txt2;
     TextView txt3;
     TextView txt4;
     Button dDltbtn;

    DatabaseReference db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_display_take_away_details);

        txt1=findViewById(R.id.txt1);
        txt2=findViewById(R.id.txt2);
        txt3=findViewById(R.id.txt3);
        txt4=findViewById(R.id.txt4);
        dDltbtn=findViewById(R.id.tDltbtn);
//
//
//        button8.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
        db = FirebaseDatabase.getInstance().getReference().child("TakeAway");

        db.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        for(DataSnapshot ds: dataSnapshot.getChildren())
                        {
                            tkAway tAway=ds.getValue(tkAway.class);

                            String name=tAway.getName();
//                            int mobile= Integer.parseInt(tAway.getMobile());
                            String mobile=tAway.getMobile();
                            String date=tAway.getDate();
                            String time=tAway.getTime();
                            txt1.setText(name);
                        txt2.setText(mobile);
                      txt3.setText(date);
                        txt4.setText(time);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
        dDltbtn.setOnClickListener(new View.OnClickListener() {
            private Object position;

            @Override
            public void onClick(View v) {
                FirebaseDatabase.getInstance().getReference().child("Delivery")
                        .child(getRef(position).getKey()).removeValue();
               
                Toast.makeText(v.getContext(),"Successfully Deleted", Toast.LENGTH_SHORT).show();

            }

            private MissingResourceException getRef(Object position) {
                return null;
            }


        });

//            }
//        });


    }

}