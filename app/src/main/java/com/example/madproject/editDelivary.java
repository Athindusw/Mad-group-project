package com.example.madproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.MissingResourceException;

public class editDelivary extends AppCompatActivity {
    EditText dateformat;
    int year;
    int month;
    int day;
    Spinner edspinner;
    ArrayAdapter<CharSequence> adapter;
    private EditText edName,edPhone,edAddress;
    private TextView edDateTextView,edTimeView;
    private Button dtebutton,tmebutton;
    private Button sveChnge1;
    DatabaseReference db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_edit_delivary);

        dlvry dlvry1= (dlvry) getIntent().getSerializableExtra("id");
        db = FirebaseDatabase.getInstance().getReference().child("Delivery");

        edName =findViewById(R.id.edName);
        edPhone =findViewById(R.id.edPhone);
        edAddress =findViewById(R.id.edAddress);
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

        edName.setText(dlvry1.getName());
        edPhone.setText(dlvry1.getMobile());
        edAddress.setText(dlvry1.getAddress());
        edDateTextView.setText(dlvry1.getDate());
        edTimeView.setText(dlvry1.getTime());


                sveChnge1.setOnClickListener(new View.OnClickListener() {
            private Object position;

            @Override
            public void onClick(View v) {
                Map<String,Object> map=new HashMap<>();
                map.put("name",edName.getText().toString());
                map.put("mobile",edPhone.getText().toString());
                map.put("address",edAddress.getText().toString());
                map.put("date",edDateTextView.getText().toString());
                map.put("time",edTimeView.getText().toString());


                FirebaseDatabase.getInstance().getReference().child("Delivery").child(dlvry1.getId()).updateChildren(map)

                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {

                                Toast.makeText(v.getContext(), "Successfully updated", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(editDelivary.this,DisplayDelivaryDetails.class);
                                startActivity(intent);
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {

                            }
                        });
            }

            private MissingResourceException getRef(Object position) {

                return null;
            }
        });


        dtebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleDateButton();
            }
        });
        tmebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleTimeButton();
            }
        });



    }



    private void handleTimeButton() {
        Calendar calendar = Calendar.getInstance();
        int HOUR = calendar.get(Calendar.HOUR);
        int MINUTE = calendar.get(Calendar.MINUTE);

        boolean is24HourFormat = DateFormat.is24HourFormat(this);

        TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hour, int minute) {



                Calendar calendar1 = Calendar.getInstance();
                calendar1.set(Calendar.HOUR, hour);
                calendar1.set(Calendar.MINUTE, minute);

                CharSequence charSequence = DateFormat.format("hh:mm a", calendar1);
                edTimeView.setText(charSequence);
            }
        }, HOUR, MINUTE, is24HourFormat);
        timePickerDialog.show();
    }

    private void handleDateButton() {
        Calendar calendar = Calendar.getInstance();

        int YEAR = calendar.get(Calendar.YEAR);
        int MONTH = calendar.get(Calendar.MONTH);
        int DATE = calendar.get(Calendar.DATE);
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int date) {
                String dateString= year + " " + month + " " + date;
                edDateTextView.setText(dateString);

                Calendar calendar1 = Calendar.getInstance();
                calendar1.set(Calendar.YEAR, year);
                calendar1.set(Calendar.MONTH, month);
                calendar1.set(Calendar.DATE, date);

                CharSequence dateCharSequence = DateFormat.format("MMM d, yyyy", calendar1);
                edDateTextView.setText(dateCharSequence);
            }
        }, YEAR, MONTH, DATE);
        datePickerDialog.show();
    }
}