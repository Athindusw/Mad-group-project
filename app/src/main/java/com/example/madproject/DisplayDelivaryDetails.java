package com.example.madproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class DisplayDelivaryDetails extends AppCompatActivity {
    private TextView dname,dphone,daddress,ddate,dtime ;
    DatabaseReference db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_display_delivary_details);

        dname=findViewById(R.id.dname);
        dphone=findViewById(R.id.dphone);
        daddress=findViewById(R.id.daddress);
        ddate=findViewById(R.id.ddate);
        dtime=findViewById(R.id.dtime);


        db = FirebaseDatabase.getInstance().getReference().child("Delivery");

        db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for(DataSnapshot ds: dataSnapshot.getChildren())
                {
                    dlvry dlvry1=ds.getValue(dlvry.class);

                    String name=dlvry1.getName();
                    String mobile=dlvry1.getMobile();
                    String address =dlvry1.getAddress();
                    String date=dlvry1.getDate();
                    String time=dlvry1.getTime();
                    dname.setText(name);
                    dphone.setText(mobile);
                    daddress.setText(address);
                    ddate.setText(date);
                    dtime.setText(time);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}