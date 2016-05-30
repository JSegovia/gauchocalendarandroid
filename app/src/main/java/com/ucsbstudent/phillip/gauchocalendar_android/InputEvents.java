package com.ucsbstudent.phillip.gauchocalendar_android;

import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class InputEvents extends AppCompatActivity {

    Spinner spinner;
    ArrayAdapter<CharSequence> adapter;

    private LinearLayout linearLayoutEvents;
    private View mEmptyView;

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
        mButton.setOnClickListener(onCLickAddcustom());


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


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        // TODO: Handle screen rotation:
        // encapsulate information in a parcelable object, and save it
        // into the state bundle.

    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        // TODO: Handle screen rotation:
        // restore the saved items and inflate each one with inflateEditRow;

    }

    private View.OnClickListener onCLickAddcustom() {
        return new View.OnClickListener() {

            String henry = "henry";
            @Override
            public void onClick(View v) {
                String nameEvent = EditEventName.getText().toString();
                String nameLocation = EditLocation.getText().toString();
                Spinner spinner = (Spinner) findViewById(R.id.spinnerweekday);
                String weekdaytext = spinner.getSelectedItem().toString();
                Spinner spinner1 = (Spinner) findViewById(R.id.spinnerhour);
                String hour = spinner1.getSelectedItem().toString();
                Spinner spinner2 = (Spinner) findViewById(R.id.spinnermin);
                String min = spinner2.getSelectedItem().toString();
                Spinner spinner3 = (Spinner) findViewById(R.id.spinnerAmPm);
                String ampm = spinner3.getSelectedItem().toString();

                inflatedEditRow(nameEvent,nameLocation,weekdaytext,hour, min, ampm);
                v.setVisibility(View.VISIBLE);

            }
        };
    }

    // onClick handler for the "X" button of each row
    public void onDeleteClicked(View v) {
        // remove the row by calling the getParent on button
        linearLayoutEvents.removeView((View) v.getParent());
    }

    // Helper for inflating a row
    private void inflatedEditRow(String nameEvent, String nameLoc, String weekday, String hr, String min, String ampm) {



        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View rowView = inflater.inflate(R.layout.row, null);
        final ImageButton deleteButton = (ImageButton) rowView
                .findViewById(R.id.buttonDelete);
        final TextView nameofEvent = (TextView) rowView
                .findViewById(R.id.nameofEvent);
        final TextView nameofLocation = (TextView) rowView
                .findViewById(R.id.nameofLocation);
        final TextView nameofDateTime = (TextView) rowView
                .findViewById(R.id.DateandTime);

        if (nameEvent != null && !nameEvent.isEmpty()) {
            nameofEvent.setText("Event: " + nameEvent);
            nameofLocation.setText("Location: " + nameLoc);
            nameofDateTime.setText(weekday + " " + hr + ":" + min + " " + ampm);

        } else {
            mEmptyView = rowView;
            deleteButton.setVisibility(View.INVISIBLE);
        }

        // Inflate at the end of all rows but before the "Add new" button
        linearLayoutEvents.addView(rowView, linearLayoutEvents.getChildCount() - 1);

    }

}
