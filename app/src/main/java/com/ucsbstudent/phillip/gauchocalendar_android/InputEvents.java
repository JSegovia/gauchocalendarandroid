package com.ucsbstudent.phillip.gauchocalendar_android;

import android.app.ActionBar;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class InputEvents extends AppCompatActivity {

    Spinner spinner;
    ArrayAdapter<CharSequence> adapter;

    private LinearLayout linearLayoutEvents;
    private EditText EditEventName;
    private EditText EditLocation;
    private Button mButton;
    public View recentview;
    private Button seecalendar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_events);

        linearLayoutEvents = (LinearLayout) findViewById(R.id.listEvents);
        linearLayoutEvents.setOrientation(LinearLayout.VERTICAL);
        EditEventName = (EditText) findViewById(R.id.insertTitle);
        EditLocation = (EditText) findViewById(R.id.insertLocation);
        mButton = (Button) findViewById(R.id.btnAddCustom);
        mButton.setOnClickListener(onCLick());


        seecalendar = (Button) findViewById(R.id.gocalendar);
        recentview = seecalendar;


        // Spinner for Classes
        spinner = (Spinner) findViewById(R.id.spinnersubject);
        adapter = ArrayAdapter.createFromResource(this, R.array.departments, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getBaseContext(), parent.getItemIdAtPosition(position) + " selected", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });


        //Spinner for quarter
        spinner = (Spinner) findViewById(R.id.spinnerQuarter);
        adapter = ArrayAdapter.createFromResource(this, R.array.Quarter, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getBaseContext(), parent.getItemIdAtPosition(position) + " selected", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        //Spinner for Weekday
        spinner = (Spinner) findViewById(R.id.spinnerweekday);
        adapter = ArrayAdapter.createFromResource(this, R.array.Weekday, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getBaseContext(), parent.getItemIdAtPosition(position) + " selected", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        //Spinner for Hours
        spinner = (Spinner) findViewById(R.id.spinnerhour);
        adapter = ArrayAdapter.createFromResource(this, R.array.Hours, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getBaseContext(), parent.getItemIdAtPosition(position) + " selected", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        //Spinner for Minutes
        spinner = (Spinner) findViewById(R.id.spinnermin);
        adapter = ArrayAdapter.createFromResource(this, R.array.Min, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getBaseContext(), parent.getItemIdAtPosition(position) + " selected", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        //Spinner for AMPM
        spinner = (Spinner) findViewById(R.id.spinnerAmPm);
        adapter = ArrayAdapter.createFromResource(this, R.array.AmPm, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getBaseContext(), parent.getItemIdAtPosition(position) + " selected", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    public void goCalendar(View view) {
        Intent intent = new Intent(this, CalendarView.class);
        startActivity(intent);

    }



    private View.OnClickListener onCLick(){
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linearLayoutEvents.addView(createNewEvent(EditEventName.getText().toString()));
                linearLayoutEvents.addView(createNewLocation(EditLocation.getText().toString()));
            }
        };
    }

    private TextView createNewEvent(String text){
        LinearLayout.LayoutParams lparams= new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        lparams.topMargin=20;
        final TextView eventtextbox = new TextView(this);
        eventtextbox.setLayoutParams(lparams);
        eventtextbox.setText("Event: " + text);
        eventtextbox.setBackgroundColor(getResources().getColor(R.color.com_facebook_blue));
        eventtextbox.setTextColor(Color.parseColor("White"));
        eventtextbox.setTextSize(20);
        return eventtextbox;
    }

    private TextView createNewLocation(String textlocation){
        LinearLayout.LayoutParams layoutParams= new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        final TextView locationtextbox = new TextView(this);
        locationtextbox.setLayoutParams(layoutParams);
        locationtextbox.setText("Location: " + textlocation);
        locationtextbox.setBackgroundColor(getResources().getColor(R.color.com_facebook_blue));
        locationtextbox.setTextColor(Color.parseColor("White"));
        locationtextbox.setTextSize(15);
        return locationtextbox;
    }
}
