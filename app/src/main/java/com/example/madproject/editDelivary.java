package com.example.madproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;
import java.util.MissingResourceException;

public class editDelivary extends AppCompatActivity {
    Spinner edspinner;
    ArrayAdapter<CharSequence> adapter;
    private EditText edName,edPhone,edAddres;
    private TextView edDateTextView,edTimeView;
    private Button dtebutton,tmebutton;
    private Button sveChnge1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_edit_delivary);

        edName =findViewById(R.id.etName);
        edPhone =findViewById(R.id.edPhone);
        edAddres =findViewById(R.id.edAddress);
        edDateTextView = findViewById(R.id.edDateTextView);
        edTimeView = findViewById(R.id.edTimeView);
        dtebutton = findViewById(R.id.dtebutton);
        tmebutton = findViewById(R.id.tmebutton);
        sveChnge1= findViewById(R.id.sveChnge1);

        edspinner = (Spinner) findViewById(R.id.edspinner);
        adapter = ArrayAdapter.createFromResource(this, R.array.names, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        edspinner.setAdapter(adapter);

        edspinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                Toast.makeText(getBaseContext(), parent.getItemAtPosition(position) + " Selected", Toast.LENGTH_LONG).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }


            });
//        sveChnge1.setOnClickListener(new View.OnClickListener() {
//            private Object position;
//
//            @Override
//            public void onClick(View v) {
//                Map<String,Object> map=new HashMap<>();
//                map.put("name",edName.getText().toString());
//                map.put("mobile",edPhone.getText().toString());
//
//                FirebaseDatabase.getInstance().getReference().child("TakeAway")
//                        .child(getRef(position).getKey()).updateChildren(map)
//                        .addOnSuccessListener(new OnSuccessListener<Void>() {
//                            @Override
//                            public void onSuccess(Void aVoid) {
//
//                                Toast.makeText(v.getContext(), "Successfully updated", Toast.LENGTH_SHORT).show();
//                            }
//                        })
//                        .addOnFailureListener(new OnFailureListener() {
//                            @Override
//                            public void onFailure(@NonNull Exception e) {
//
//                            }
//                        });
//            }
//
//            private MissingResourceException getRef(Object position) {
//
//            }
//        });
    }
}