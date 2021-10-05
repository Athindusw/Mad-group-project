package com.example.madproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.icu.text.Transliterator;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.tapadoo.alerter.Alerter;

import java.io.Serializable;
import java.util.MissingResourceException;

public class DisplayDelivaryDetails extends AppCompatActivity {
    private TextView dname,dphone,daddress,ddate,dtime,ddeliverycharge ;
    private Button dEdit,ddelete,dconform;
    DatabaseReference db;

    dlvry dlvry1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_display_delivary_details);

        dname=findViewById(R.id.dname);
        dphone=findViewById(R.id.dphone);
        daddress=findViewById(R.id.daddress);;
        ddate=findViewById(R.id.ddate);
        dtime=findViewById(R.id.dtime);
        ddeliverycharge=findViewById(R.id.ddeliverycharge);
        dEdit=findViewById(R.id.dEdit);
        ddelete=findViewById(R.id.ddelete);
        dconform=findViewById(R.id.dconform);



        db = FirebaseDatabase.getInstance().getReference().child("Delivery");

        db.orderByKey().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for(DataSnapshot ds: dataSnapshot.getChildren())
                {

                     dlvry1=ds.getValue(dlvry.class);
                    dlvry1.setId(ds.getKey());



                    String name=dlvry1.getName();
                    String mobile=dlvry1.getMobile();
                    String address =dlvry1.getAddress();
                    String distance = dlvry1.getDistance();
                    String date=dlvry1.getDate();
                    String time=dlvry1.getTime();

                    dname.setText(name);
                    dphone.setText(mobile);
                    daddress.setText(address);
                    ddate.setText(date);
                    dtime.setText(time);

                    //System.out.println(distance);


                    if(distance.equals("2km")){

                        ddeliverycharge.setText("Delivery Charge = Rs50.00");
                    }
                    else if(distance.equals("5km")){
                        ddeliverycharge.setText("Delivery Charge = Rs100.00");
                   }else if(distance.equals("10km")){
                        ddeliverycharge.setText("Delivery Charge = Rs200.00");
                    }else{
                        ddeliverycharge.setText("Delivery Charge = Rs200.00 +"+"        "+"Rs20 per km");
                    }

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        dEdit.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DisplayDelivaryDetails.this,editDelivary.class);
                intent.putExtra("id", (Serializable) dlvry1);
                startActivity(intent);

            }
        });

       ddelete.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               AlertDialog.Builder builder=new AlertDialog.Builder(DisplayDelivaryDetails.this);
               builder.setTitle("Delete Delivery Details");
               builder.setMessage("Delete...?");
               builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                   @Override
                   public void onClick(DialogInterface dialogInterface, int i) {

                       deleteRecord(dlvry1.getId());

                       Toast.makeText(getApplicationContext(), "Successfully deleted", Toast.LENGTH_SHORT).show();
                       Intent intent = new Intent(DisplayDelivaryDetails.this,view.class);
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

       dconform.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {

               AlertDialog.Builder builder=new AlertDialog.Builder(DisplayDelivaryDetails.this);

               builder.setMessage("Your details successfully recorded");
               builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {

                   @Override
                   public void onClick(DialogInterface dialogInterface, int i) {
                       Toast.makeText(getApplicationContext(), "Thank you !!!", Toast.LENGTH_SHORT).show();
                       Intent intent = new Intent(DisplayDelivaryDetails.this,view.class);
                       startActivity(intent);


                   }
               });

               builder.show();


           }
       });



    }

    private void deleteRecord(String key){
        db=FirebaseDatabase.getInstance().getReference("Delivery").child(key);
        db.removeValue();
        Toast.makeText(getApplicationContext(), "Deleted", Toast.LENGTH_SHORT).show();

    }

}