package com.example.madproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.tapadoo.alerter.Alerter;
import com.tapadoo.alerter.OnHideAlertListener;
import com.tapadoo.alerter.OnShowAlertListener;

public class FinalInvoice extends AppCompatActivity {
    DatabaseReference dbRef;
    EditText et_mail;
    Order odr;
    RadioButton delivery,takeaway;
    int i = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_final_invoice);


        TextView txtsub = (TextView)findViewById(R.id.txt_subtotval) ;
        Button btn_back = (Button)findViewById(R.id.btn_back);
        Button btn_place = (Button)findViewById(R.id.btn_place);

        String TotalAmount = getIntent().getStringExtra("key");
        txtsub.setText("Rs." + TotalAmount + "0/=");



        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(FinalInvoice.this, ProductCart.class));
            }
        });


        et_mail = findViewById(R.id.et_mail);
        delivery = findViewById(R.id.rabtn_deli);
        takeaway = findViewById(R.id.rabtn_take);

        odr = new Order();

        dbRef = FirebaseDatabase.getInstance().getReference().child("Order");
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    i = (int)snapshot.getChildrenCount();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        btn_place.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if(TextUtils.isEmpty(et_mail.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Enter an Email Address", Toast.LENGTH_SHORT).show();
                    else if(!delivery.isChecked() && !takeaway.isChecked()){
                        Toast.makeText(getApplicationContext(), "Please Select Order Type", Toast.LENGTH_SHORT).show();
                    }
                    else if(!et_mail.getText().toString().trim()
                            .matches("^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")){
                        Toast.makeText(getApplicationContext(), "Please Enter a Valid Email", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        odr.setEmail(et_mail.getText().toString().trim());
                        odr.setTotalAmount(txtsub.getText().toString());
                        dbRef.child(String.valueOf(i + 1)).setValue(odr);
                        if (delivery.isChecked()) {
                            odr.setOrderType(delivery.getText().toString());
                            dbRef.child(String.valueOf(i + 1)).setValue(odr);
                        } else {
                            odr.setOrderType(takeaway.getText().toString());
                            dbRef.child(String.valueOf(i + 1)).setValue(odr);
                        }

                        showAlerter(v);
                    }
                }
                catch(NumberFormatException e) {
                    Toast.makeText(getApplicationContext(), "Invalid Email", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void showAlerter(View v)
    {
        Alerter.create(this)
                .setTitle("Order Placed Successfully")
                .setText("Thank You !!")
                .setIcon(
                        R.drawable.ic_baseline_check_circle_24)
                .setBackgroundColorRes(
                        R.color.limeOrange)
                .setDuration(3000)
                .show();
    }
}
