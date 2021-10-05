package com.example.madproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ProductCart extends AppCompatActivity {
    RecyclerView recyclerView;
    DatabaseReference database;
    RecyclerCartAdapter recyclerCartAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_product_cart);

        recyclerView = findViewById(R.id.recycart);
        database = FirebaseDatabase.getInstance().getReference("OrderDetails");
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));



        FirebaseRecyclerOptions<OrderDetails> options = new FirebaseRecyclerOptions.Builder<OrderDetails>()
                .setQuery(FirebaseDatabase.getInstance().getReference().child("OrderDetails"), OrderDetails.class)
                .build();

        recyclerCartAdapter = new RecyclerCartAdapter(options);

        recyclerView.setAdapter(recyclerCartAdapter);

        TextView subtot = (TextView)findViewById(R.id.tv_subtot);
        Button btn_cartsubmit = (Button)findViewById(R.id.btn_cartsubmit);

        btn_cartsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                subtot.setText("Rs." + recyclerCartAdapter.getTotal() + "0/=");
                startActivity(new Intent(ProductCart.this, FinalInvoice.class));

                Intent intent = new Intent(ProductCart.this,FinalInvoice.class);
                intent.putExtra("key",recyclerCartAdapter.getTotal());
                startActivity(intent);
            }
        });


        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Bundle bundle = getIntent().getExtras();
        if(bundle != null){
            if(bundle.getString("some") != null){

            }
        }

    }


    @Override
    protected void onStart() {
        super.onStart();
        recyclerCartAdapter.startListening();
    }



    @Override
    protected void onStop() {
        super.onStop();
        recyclerCartAdapter.stopListening();
    }
}