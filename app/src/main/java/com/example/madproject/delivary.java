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
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import android.widget.EditText;

import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

public class delivary<spinner> extends AppCompatActivity {
    EditText dateformat;
    int year;
    int month;
    int day;
    Spinner spinner;
    ArrayAdapter<CharSequence> adapter;
   private Button dateButton, timeButton, submit1;
   private TextView dateTextView, timeTextView;
   private EditText Name1, Mobile1, Address1;

    DatabaseReference db1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_delivary);


        Name1 = findViewById(R.id.Name1);
        Mobile1 = findViewById(R.id.Mobile1);
        Address1 = findViewById(R.id.Address1);
        submit1 = findViewById(R.id.submit1);
        spinner = (Spinner) findViewById(R.id.edspinner);
        adapter = ArrayAdapter.createFromResource(this, R.array.names, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);





        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                Toast.makeText(getBaseContext(), parent.getItemAtPosition(position) + " Selected", Toast.LENGTH_LONG).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        dateButton = findViewById(R.id.dateButton);
        timeButton = findViewById(R.id.timeButton);
        dateTextView = findViewById(R.id.dateTextView);
        timeTextView = findViewById(R.id.timeTextView);

        dateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleDateButton();
            }
        });
        timeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleTimeButton();
            }
        });

        db1 = FirebaseDatabase.getInstance().getReference().child("Delivery");
        submit1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String Name = Name1.getText().toString();
                String Mobile = Mobile1.getText().toString();
                String Address = Address1.getText().toString();
                String Distance = spinner.getSelectedItem().toString();
                String Date = dateTextView.getText().toString();
                String Time = timeTextView.getText().toString();

                if (Name.isEmpty() || Mobile.isEmpty() || Address.isEmpty() || Distance.isEmpty() || Date.isEmpty() || Time.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Please complete the field", Toast.LENGTH_SHORT).show();
                }else if(!Mobile1.getText().toString().trim().matches("^[0-9]{10}$")){
                    Toast.makeText(getApplicationContext(), "Invalid Phone number", Toast.LENGTH_SHORT).show();

                }
                else{
                    String userid=db1.push().getKey();
                    dlvry dlvry1 = new dlvry();
                    dlvry1.setName(Name);
                    dlvry1.setMobile(Mobile);
                    dlvry1.setAddress(Address);
                    dlvry1.setDistance(Distance);
                    dlvry1.setDate(Date);
                    dlvry1.setTime(Time);


                    db1.child(userid).setValue(dlvry1,new DatabaseReference.CompletionListener() {
                        @Override
                        public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                            if (error == null){
                                Toast.makeText(getApplicationContext(), "Successfully Saved", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(delivary.this,DisplayDelivaryDetails.class);
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
                timeTextView.setText(charSequence);
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
                dateTextView.setText(dateString);

                Calendar calendar1 = Calendar.getInstance();
                calendar1.set(Calendar.YEAR, year);
                calendar1.set(Calendar.MONTH, month);
                calendar1.set(Calendar.DATE, date);

                CharSequence dateCharSequence = DateFormat.format("MMM d, yyyy", calendar1);
                dateTextView.setText(dateCharSequence);
            }
        }, YEAR, MONTH, DATE);
        datePickerDialog.show();
    }


}



