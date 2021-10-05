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
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.Serializable;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.MissingResourceException;

public class EditTakeAway extends AppCompatActivity {
    EditText dateformat;
    int year;
    int month;
    int day;
    private EditText etName,etPhone;
    private TextView textDateView,textTimeView;
    private Button dtebtn1,tmebtn1;
    private Button svechng;
    DatabaseReference db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_edit_take_away);

        tkAway tAway= (tkAway) getIntent().getSerializableExtra("id");
        db = FirebaseDatabase.getInstance().getReference().child("TakeAway");

        etName =findViewById(R.id.etName);
        etPhone =findViewById(R.id.etPhone);
        textDateView =findViewById(R.id.textDateView);
        textTimeView =findViewById(R.id.textTimeView);
        dtebtn1 =findViewById(R.id.dtebtn1);
        tmebtn1 =findViewById(R.id.tmebtn1);
        svechng =findViewById(R.id.svechng);

        etName.setText(tAway.getName());
        etPhone.setText(tAway.getMobile());
        textDateView.setText(tAway.getDate());
        textTimeView.setText(tAway.getTime());


        svechng.setOnClickListener(new View.OnClickListener() {
            private Object position;

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EditTakeAway.this,DisplayTakeAwayDetails.class);
                startActivity(intent);
                Map<String,Object> map=new HashMap<>();
                map.put("name",etName.getText().toString());
                map.put("mobile",etPhone.getText().toString());
                map.put("date",textDateView.getText().toString());
                map.put("time",textTimeView.getText().toString());


                FirebaseDatabase.getInstance().getReference().child("TakeAway").child(tAway.getId()).updateChildren(map)

                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {

                                Toast.makeText(v.getContext(), "Successfully updated", Toast.LENGTH_SHORT).show();
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

        dtebtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleDateButton();
            }
        });
        tmebtn1.setOnClickListener(new View.OnClickListener() {
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
                textTimeView.setText(charSequence);
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
                textDateView.setText(dateString);

                Calendar calendar1 = Calendar.getInstance();
                calendar1.set(Calendar.YEAR, year);
                calendar1.set(Calendar.MONTH, month);
                calendar1.set(Calendar.DATE, date);

                CharSequence dateCharSequence = DateFormat.format("MMM d, yyyy", calendar1);
                textDateView.setText(dateCharSequence);
            }
        }, YEAR, MONTH, DATE);
        datePickerDialog.show();
    }
}