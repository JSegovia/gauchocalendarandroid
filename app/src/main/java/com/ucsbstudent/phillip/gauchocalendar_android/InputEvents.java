package com.ucsbstudent.phillip.gauchocalendar_android;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.ArrayAdapter;
public class InputEvents extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_events);
    }
/*
    Spinner dropdown = (Spinner)findViewById(R.id.spinnersubject);
    String[] subjects = new String[]{"ANTH", "ART", "ART CS", "ARTHI", "ARTST", "AS AM"};
    ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, subjects);

    public void setAdapter(ArrayAdapter<String> adapter) {
        this.adapter = adapter;
    }
*/
    public void goCalendar(View view){
        Intent intent = new Intent(this, CalendarView.class);
        startActivity(intent);


    }



}
