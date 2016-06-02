package com.ucsbstudent.phillip.gauchocalendar_android;

import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Parcelable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.Firebase;

import org.w3c.dom.Text;

import java.io.Serializable;
import java.util.ArrayList;

public class InputEvents extends AppCompatActivity {

    private static final String FIREBASE_URL = "https://sizzling-inferno-7789.firebaseIO.com";
    private Firebase firebaseRef;
    public int globali;


    public ArrayList<CustomEventClass> customE = new ArrayList<>();


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
        firebaseRef = new Firebase(FIREBASE_URL);

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


        //Spinner for Hours1
        spinner = (Spinner) findViewById(R.id.spinnerhour1);
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

        //Spinner for Minutes1
        spinner = (Spinner) findViewById(R.id.spinnermin1);
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

        //Spinner for AMPM1
        spinner = (Spinner) findViewById(R.id.spinnerAmPm1);
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
        Button butn = (Button) findViewById(R.id.gocalendar);

        if (customE.isEmpty()){

                butn.setError("Conflicts in your schedule");
                return;

        }
        intent.putParcelableArrayListExtra("custom", customE);
        //gives array of customEvent objects
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


                Spinner spinner5 = (Spinner) findViewById(R.id.spinnerhour1);
                String hourF = spinner5.getSelectedItem().toString();
                Spinner spinner6 = (Spinner) findViewById(R.id.spinnermin1);
                String minF = spinner6.getSelectedItem().toString();
                Spinner spinner7 = (Spinner) findViewById(R.id.spinnerAmPm1);
                String ampmF = spinner7.getSelectedItem().toString();


                if (TextUtils.isEmpty(nameEvent) || TextUtils.isEmpty(nameLocation)) {
                    EditEventName.setError("Please enter an event title");
                    EditLocation.setError("Please specify the location");
                    return;
                }

                inflatedEditRow(nameEvent, nameLocation, weekdaytext, hour, min, ampm, hourF, minF, ampmF);
                v.setVisibility(View.VISIBLE);

                int weekdayInt = spinner.getSelectedItemPosition();
                int hourInt = spinner1.getSelectedItemPosition();
                int minInt = spinner2.getSelectedItemPosition();
                int ampmInt = spinner3.getSelectedItemPosition();
                int hourIntF = spinner5.getSelectedItemPosition();
                int minIntF = spinner6.getSelectedItemPosition();
                int ampmIntF = spinner7.getSelectedItemPosition();


                CustomEventClass temp = new CustomEventClass(nameEvent, nameLocation,
                        weekdaytext, weekdayInt, hourInt, minInt, ampm, hourIntF, minIntF, ampmF);
                //firebaseRef.child("TestCustomEvent").push().setValue(temp);
                customE.add(temp);
                //firebaseRef.child("tester").push().setValue(customEvents.get(0));

            }
        };
    }

    // onClick handler for the "X" button of each row
    public void onDeleteClicked(View v) {
        // remove the row by calling the getParent on button
        TextView nameofEvent = (TextView) findViewById(R.id.nameofEvent);
        String test = nameofEvent.getText().toString();
        TextView location = (TextView) findViewById(R.id.nameofLocation);
        location.setText(test);
        for (int i = 0; i < customE.size(); i++) {
            if (customE.get(i).getEventTitle().equals(test)) {
                customE.remove(i);
                break;
            }
        }
        linearLayoutEvents.removeView((View) v.getParent());
        firebaseRef.child("TestCustomEvent").removeValue();

        for (int i = 0; i < customE.size(); i++) {
            firebaseRef.child("TestCustomEvent").push().setValue(customE.get(i));
        }
    }

    // Helper for inflating a row
    private void inflatedEditRow(String nameEvent, String nameLoc, String weekday, String hr, String min, String ampm,
                                 String hr2, String min2, String ampm2) {


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
        final TextView nameofDateTime2 = (TextView) rowView
                .findViewById(R.id.DateandTime2);

        if (nameEvent != null && !nameEvent.isEmpty()) {
            nameofEvent.setText("Event: " + nameEvent);
            nameofLocation.setText("Location: " + nameLoc);
            nameofDateTime.setText("Start: " + weekday + " " + hr + ":" + min + " " + ampm);
            nameofDateTime2.setText("End: " + weekday + " " + hr2 + ":" + min2 + " " + ampm2);


        } else {
            mEmptyView = rowView;
            deleteButton.setVisibility(View.INVISIBLE);
        }

        // Inflate at the end of all rows but before the "Add new" button
        linearLayoutEvents.addView(rowView, linearLayoutEvents.getChildCount() - 1);
        linearLayoutEvents.setId(globali);
        globali++;

    }


    public void showclasses(View v) {
        Spinner spin = (Spinner) findViewById(R.id.spinnerhour);
        String name = spin.getSelectedItem().toString();

        LinearLayout test = (LinearLayout) findViewById(R.id.listCourses);

        LinearLayout.LayoutParams lparamslect = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        lparamslect.setMargins(40,15,15,15);

        LinearLayout.LayoutParams lparamssect = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        lparamslect.setMargins(10,10,10,10);


        CheckBox checkBox = new CheckBox(getApplicationContext());
        checkBox.setText("CMPSC8   KOC    TWR    11:00am - 12:20pm   BRDA 1640");
        checkBox.setTextColor(getResources().getColor(R.color.black));
        checkBox.setLayoutParams(lparamslect);
        test.addView(checkBox);

        CheckBox checkBox1 = new CheckBox(getApplicationContext());
        checkBox1.setText("CMPSC8       F    11:00am - 12:20pm  PHELP3525");
        checkBox1.setTextColor(getResources().getColor(R.color.black));
        checkBox1.setLayoutParams(lparamssect);
        test.addView(checkBox1);

        CheckBox checkBox2 = new CheckBox(getApplicationContext());
        checkBox2.setText("CMPSC8       F    12:30pm - 1:50pm  PHELP3525");
        checkBox2.setTextColor(getResources().getColor(R.color.black));
        checkBox2.setLayoutParams(lparamssect);
        test.addView(checkBox2);

        CheckBox checkBox3 = new CheckBox(getApplicationContext());
        checkBox3.setText("CMPSC8       F    2:00pm - 3:20pm  PHELP3525");
        checkBox3.setTextColor(getResources().getColor(R.color.black));
        checkBox3.setLayoutParams(lparamssect);
        test.addView(checkBox3);

        CheckBox checkBox4 = new CheckBox(getApplicationContext());
        checkBox4.setText("CMPSC8   TBA    MWF    12:30pm - 1:50pm  PHELP1260");
        checkBox4.setTextColor(getResources().getColor(R.color.black));
        checkBox4.setLayoutParams(lparamslect);
        test.addView(checkBox4);

        CheckBox checkBox5 = new CheckBox(getApplicationContext());
        checkBox5.setText("CMPSC8      T    11:00am - 12:20pm  PHELP3525");
        checkBox5.setTextColor(getResources().getColor(R.color.black));
        checkBox5.setLayoutParams(lparamssect);
        test.addView(checkBox5);

        CheckBox checkBox6 = new CheckBox(getApplicationContext());
        checkBox6.setText("CMPSC8      T    12:30pm - 1:50pm  PHELP3525");
        checkBox6.setTextColor(getResources().getColor(R.color.black));
        checkBox6.setLayoutParams(lparamssect);
        test.addView(checkBox6);

        CheckBox checkBox7 = new CheckBox(getApplicationContext());
        checkBox7.setText("CMPSC8      T    2:00pm - 3:20pm  PHELP3525");
        checkBox7.setTextColor(getResources().getColor(R.color.black));
        checkBox7.setLayoutParams(lparamssect);
        test.addView(checkBox7);

        CheckBox checkBox8 = new CheckBox(getApplicationContext());
        checkBox8.setText("CMPSC8   TBA    MWF    3:30pm - 4:50pm  PHELP3526");
        checkBox8.setTextColor(getResources().getColor(R.color.black));
        checkBox8.setLayoutParams(lparamslect);
        test.addView(checkBox8);

        CheckBox checkBox9 = new CheckBox(getApplicationContext());
        checkBox9.setText("CMPSC8      T    3:30pm - 4:50pm  PHELP3525");
        checkBox9.setTextColor(getResources().getColor(R.color.black));
        checkBox9.setLayoutParams(lparamssect);
        test.addView(checkBox9);

        CheckBox checkBox10 = new CheckBox(getApplicationContext());
        checkBox10.setText("CMPSC8      T    5:00pm - 6:20pm  PHELP3525");
        checkBox10.setTextColor(getResources().getColor(R.color.black));
        checkBox10.setLayoutParams(lparamssect);
        test.addView(checkBox10);

        CheckBox checkBox11 = new CheckBox(getApplicationContext());
        checkBox11.setText("CMPSC16   TBA    T R    12:30pm - 1:50pm  PHELP1260");
        checkBox11.setTextColor(getResources().getColor(R.color.black));
        checkBox11.setLayoutParams(lparamslect);
        test.addView(checkBox11);

        CheckBox checkBox12 = new CheckBox(getApplicationContext());
        checkBox12.setText("CMPSC16      M    9:00am - 10:15am  PHELP3525");
        checkBox12.setTextColor(getResources().getColor(R.color.black));
        checkBox12.setLayoutParams(lparamssect);
        test.addView(checkBox12);

        CheckBox checkBox13 = new CheckBox(getApplicationContext());
        checkBox13.setText("CMPSC16      M    10:30am - 11:45am  PHELP3525");
        checkBox13.setTextColor(getResources().getColor(R.color.black));
        checkBox13.setLayoutParams(lparamssect);
        test.addView(checkBox13);

        CheckBox checkBox14 = new CheckBox(getApplicationContext());
        checkBox14.setText("CMPSC40   CAPPELLO    TWR    2:00pm - 3:20pm  PHELP3526");
        checkBox14.setTextColor(getResources().getColor(R.color.black));
        checkBox14.setLayoutParams(lparamslect);
        test.addView(checkBox14);

        CheckBox checkBox15 = new CheckBox(getApplicationContext());
        checkBox15.setText("CMPSC40      F   11:00am - 12:25pm  PHELP3524");
        checkBox15.setTextColor(getResources().getColor(R.color.black));
        checkBox15.setLayoutParams(lparamssect);
        test.addView(checkBox15);

        CheckBox checkBox16 = new CheckBox(getApplicationContext());
        checkBox16.setText("CMPSC40      F    12:30pm - 1:50pm  PHELP3524");
        checkBox16.setTextColor(getResources().getColor(R.color.black));
        checkBox16.setLayoutParams(lparamssect);
        test.addView(checkBox16);

        CheckBox checkBox17 = new CheckBox(getApplicationContext());
        checkBox17.setText("CMPSC56   CONRAD    TWR    9:30am - 10:50pm  PHELP3526");
        checkBox17.setTextColor(getResources().getColor(R.color.black));
        checkBox17.setLayoutParams(lparamslect);
        test.addView(checkBox17);

        CheckBox checkBox18 = new CheckBox(getApplicationContext());
        checkBox18.setText("CMPSC56      F    9:00am - 10:20am  PHELP3525");
        checkBox18.setTextColor(getResources().getColor(R.color.black));
        checkBox18.setLayoutParams(lparamssect);
        test.addView(checkBox18);

        CheckBox checkBox19 = new CheckBox(getApplicationContext());
        checkBox19.setText("CMPSC56      F    10:30am - 11:50am  PHELP3525");
        checkBox19.setTextColor(getResources().getColor(R.color.black));
        checkBox19.setLayoutParams(lparamssect);
        test.addView(checkBox19);

        CheckBox checkBox20 = new CheckBox(getApplicationContext());
        checkBox20.setText("CMPSC138   VAN DAM    MWF   11:00am - 12:20pm  PHELP2510");
        checkBox20.setTextColor(getResources().getColor(R.color.black));
        checkBox20.setLayoutParams(lparamslect);
        test.addView(checkBox20);

        CheckBox checkBox21 = new CheckBox(getApplicationContext());
        checkBox21.setText("CMPSC138      R    11:00am - 12:20pm  PHELP2510");
        checkBox21.setTextColor(getResources().getColor(R.color.black));
        checkBox21.setLayoutParams(lparamssect);
        test.addView(checkBox21);

        CheckBox checkBox22 = new CheckBox(getApplicationContext());
        checkBox22.setText("CMPSC192    TBA    TBA");
        checkBox22.setTextColor(getResources().getColor(R.color.black));
        checkBox22.setLayoutParams(lparamslect);
        test.addView(checkBox22);

        CheckBox checkBox23 = new CheckBox(getApplicationContext());
        checkBox23.setText("CMPSC193    TBA   TBA");
        checkBox23.setTextColor(getResources().getColor(R.color.black));
        checkBox23.setLayoutParams(lparamslect);
        test.addView(checkBox23);

        CheckBox checkBox24 = new CheckBox(getApplicationContext());
        checkBox24.setText("CMPSC196     TBA   TBA");
        checkBox24.setTextColor(getResources().getColor(R.color.black));
        checkBox24.setLayoutParams(lparamslect);
        test.addView(checkBox24);

        Button btnadd = new Button(getApplicationContext());
        btnadd.setText("Add Class");
        btnadd.setLayoutParams(lparamslect);
        test.addView(btnadd);

    }


}
