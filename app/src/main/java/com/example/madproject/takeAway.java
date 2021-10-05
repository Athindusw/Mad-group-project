package com.example.madproject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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

import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.sql.Time;
import java.util.Calendar;
import java.util.HashMap;
import java.util.jar.Attributes;

public class takeAway extends AppCompatActivity {
    EditText dateformat;
    int year;
    int month;
    int day;
    Spinner spinner;
    ArrayAdapter<CharSequence> adapter;
   private Button dateButton1, timeButton1;
   private Button Subbtn;
   private TextView dateTextView1, timeTextView1;
   private EditText tname,tmobile;


FirebaseDatabase mDatabase;
    DatabaseReference db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_take_away);

        tname = findViewById(R.id.tname);
        tmobile = findViewById(R.id.tmobile);
        Subbtn = findViewById(R.id.Subbtn);
        dateButton1 = findViewById(R.id.dateButton1);
        timeButton1 = findViewById(R.id.timeButton1);
        dateTextView1 = findViewById(R.id.dateTextView1);
        timeTextView1 = findViewById(R.id.timeTextView1);


        dateButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleDateButton();
            }
        });
        timeButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleTimeButton();
            }
        });


        mDatabase=FirebaseDatabase.getInstance();
        db=mDatabase.getReference().child("TakeAway");
        Subbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {




                String Name = tname.getText().toString();

                String Mobile = tmobile.getText().toString();
                String Date = dateTextView1.getText().toString();
                String Time = timeTextView1.getText().toString();

                if (Name.isEmpty() || Mobile.isEmpty() || Date.isEmpty() || Time.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Please complete the field", Toast.LENGTH_SHORT).show();
                }else if(!tmobile.getText().toString().trim().matches("^[0-9]{10}$")){
                    Toast.makeText(getApplicationContext(), "Invalid Phone number", Toast.LENGTH_SHORT).show();

                }
                else{
                    String userid=db.push().getKey();
                    tkAway tAway = new tkAway();
                    tAway.setName(Name);
                    tAway.setMobile(Mobile);
                    tAway.setDate(Date);
                    tAway.setTime(Time);


                    db.child(userid).setValue(tAway,new DatabaseReference.CompletionListener() {
                        @Override
                        public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                            if (error == null){
                                Toast.makeText(getApplicationContext(), "Successfully Saved", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(takeAway.this,DisplayTakeAwayDetails.class);
                                startActivity(intent);
                            }
                            else{
                                Toast.makeText(getApplicationContext(), "Some issues are there", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });


                }


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
                timeTextView1.setText(charSequence);
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
                dateTextView1.setText(dateString);

                Calendar calendar1 = Calendar.getInstance();
                calendar1.set(Calendar.YEAR, year);
                calendar1.set(Calendar.MONTH, month);
                calendar1.set(Calendar.DATE, date);

                CharSequence dateCharSequence = DateFormat.format("MMM d, yyyy", calendar1);
                dateTextView1.setText(dateCharSequence);
            }
        }, YEAR, MONTH, DATE);
        datePickerDialog.show();
    }

}