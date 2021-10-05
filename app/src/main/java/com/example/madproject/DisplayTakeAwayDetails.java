package com.example.madproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
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
import com.tapadoo.alerter.Alerter;

import java.io.Serializable;

public class DisplayTakeAwayDetails extends AppCompatActivity {
     TextView twname;
     TextView twmobile;
     TextView twdate;
     TextView twtime;
     Button etEdit,etDelete,etconform;

    DatabaseReference db;


    tkAway tAway;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_display_take_away_details);

        twname=findViewById(R.id.twname);
        twmobile=findViewById(R.id.twmobile);
        twdate=findViewById(R.id.twdate);
        twtime=findViewById(R.id.twtime);
        etEdit=findViewById(R.id.etEdit);
        etDelete=findViewById(R.id.etDelete);
        etconform=findViewById(R.id.etconform);

        db = FirebaseDatabase.getInstance().getReference().child("TakeAway");

        db.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        for(DataSnapshot ds: dataSnapshot.getChildren())
                        {

                             tAway=ds.getValue(tkAway.class);
                             tAway.setId(ds.getKey());



                           String Name=tAway.getName();

                           String Mobile=tAway.getMobile();
                           String Date=tAway.getDate();
                            String Time=tAway.getTime();
                            twname.setText(Name);
                            twmobile.setText(Mobile);
                            twdate.setText(Date);
                            twtime.setText(Time);
                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

        etEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DisplayTakeAwayDetails.this,EditTakeAway.class);
                intent.putExtra("id", (Serializable) tAway);
                startActivity(intent);
            }
        });

        etDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //deleteRecord(tAway.getId());
                AlertDialog.Builder builder=new AlertDialog.Builder(DisplayTakeAwayDetails.this);
                builder.setTitle("Delete Take-Away Details");
                builder.setMessage("Delete...?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        deleteRecord(tAway.getId());

                        Toast.makeText(getApplicationContext(), "Successfully deleted", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(DisplayTakeAwayDetails.this,view.class);
                        startActivity(intent);
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                });
                builder.show();
            }
        });

        etconform.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder=new AlertDialog.Builder(DisplayTakeAwayDetails.this);
                //builder.setTitle("Delete Take-Away Details");
                builder.setMessage("Your details successfully recorded");
                builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(getApplicationContext(), "Thank you !!!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(DisplayTakeAwayDetails.this,view.class);
                        startActivity(intent);
                    }
                });

                builder.show();
            }
        });

    }

    private void deleteRecord(String key){
        db=FirebaseDatabase.getInstance().getReference("TakeAway").child(key);
        db.removeValue();


    }

}