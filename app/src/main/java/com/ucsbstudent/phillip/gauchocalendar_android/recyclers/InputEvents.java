package com.ucsbstudent.phillip.gauchocalendar_android.recyclers;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.ucsbstudent.phillip.gauchocalendar_android.CalendarView;
import com.ucsbstudent.phillip.gauchocalendar_android.CustomEventClass;
import com.ucsbstudent.phillip.gauchocalendar_android.LectureOrSection;
import com.ucsbstudent.phillip.gauchocalendar_android.R;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class InputEvents extends AppCompatActivity {

    private static final String FIREBASE_URL = "https://sizzling-inferno-7789.firebaseIO.com";
    private Firebase firebaseRef;
    public int globali;


    public ArrayList<CustomEventClass> customE = new ArrayList<>();

    public static ArrayList< ArrayList<LectureOrSection> > departments =
            new ArrayList<ArrayList<LectureOrSection>>();


    ArrayList<LectureOrSection> coursesFall2016 = new ArrayList<LectureOrSection>();
    ArrayList<String> stringListF16 = new ArrayList<String>();


    Spinner spinner;
    ArrayAdapter<CharSequence> adapter;

    private LinearLayout linearLayoutEvents;
    private View mEmptyView;

    private EditText EditEventName;
    private EditText EditLocation;
    private Button mButton;
    public View recentview;
    private Button seecalendar;


    classAdapter adapter123;
    StringBuffer sb=null;

    customAdapter adapter321;
    StringBuffer bsc=null;

    String Department;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_events);
        //firebaseRef = new Firebase(FIREBASE_URL);
        //Toolbar toolbar = (Toolbar) findViewById(R.id.);
        //getSupportActionBar(toolbar);



        // Make ArrayList stringListF16
        Context context = getApplicationContext();

        String filePath = context.getFilesDir().getAbsolutePath();

        File fileName = new File(filePath, "fall2016");
        try {

            BufferedReader bufferedReader =
                    new BufferedReader(new InputStreamReader(getAssets().open("fall2016.txt")));

            // This puts all the strings into an array of strings
            String temp;
            while ((temp = bufferedReader.readLine()) != null) {
                try {
                    stringListF16.add(temp);
                    stringListF16.add(bufferedReader.readLine());


                } catch (IOException e) {
                    e.printStackTrace();
                }
            }




            // Always close files.
            bufferedReader.close();
        } catch (FileNotFoundException ex) {
            System.out.println(
                    "Unable to open fileTHISTHITHITHIEHGEITH '" +
                            fileName + "'");
        } catch (IOException ex) {
            System.out.println(
                    "Error reading file '"
                            + fileName + "'");
            // Or we could just do this:
            // ex.printStackTrace();
        }

        //^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^





        //adapter123 = new classAdapter(this, getClasses());

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
                //Toast.makeText(getBaseContext(), parent.getItemIdAtPosition(position) + " selected", Toast.LENGTH_LONG).show();
                String temp = parent.getSelectedItem().toString();
                Department = temp;
                TextView test = (TextView)findViewById(R.id.isitloaded);
                test.setText(Department);
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
                //Toast.makeText(getBaseContext(), parent.getItemIdAtPosition(position) + " selected", Toast.LENGTH_LONG).show();
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
                //Toast.makeText(getBaseContext(), parent.getItemIdAtPosition(position) + " selected", Toast.LENGTH_LONG).show();
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
                //Toast.makeText(getBaseContext(), parent.getItemIdAtPosition(position) + " selected", Toast.LENGTH_LONG).show();
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
                //Toast.makeText(getBaseContext(), parent.getItemIdAtPosition(position) + " selected", Toast.LENGTH_LONG).show();
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
                //Toast.makeText(getBaseContext(), parent.getItemIdAtPosition(position) + " selected", Toast.LENGTH_LONG).show();
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
                //Toast.makeText(getBaseContext(), parent.getItemIdAtPosition(position) + " selected", Toast.LENGTH_LONG).show();
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
                //Toast.makeText(getBaseContext(), parent.getItemIdAtPosition(position) + " selected", Toast.LENGTH_LONG).show();
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
               //Toast.makeText(getBaseContext(), parent.getItemIdAtPosition(position) + " selected", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        //Load courses into array of strings


    }

/*
    public void dosomething(View v){
        Spinner depart = (Spinner)findViewById(R.id.spinnersubject);
        Department = spinner.getSelectedItem().toString();
    }
*/

    public void showclasses(View v){

        Button fail = (Button)findViewById(R.id.buttonsearch);
        if(coursesFall2016.size() == 0) {
            fail.setError("Please load classes");
            return;
        }

        Button find = (Button)findViewById(R.id.addtoclasslist);
        find.setVisibility(View.VISIBLE);
        TextView clear = (TextView)findViewById(R.id.isitloaded);
        clear.setText("");

        adapter123 = new classAdapter(this, getClasses());

        sb = new StringBuffer();
        for (LectureOrSection p : adapter123.checkedclasses){
            sb.append(p.getNamofLS());
            sb.append("\n");
        }

        if(adapter123.checkedclasses.size() > 0){
            Toast.makeText(InputEvents.this,sb.toString(),Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(InputEvents.this,"PLease check classes",Toast.LENGTH_SHORT).show();
        }

        //RECYCLER
        RecyclerView rv = (RecyclerView)findViewById(R.id.classRecycler);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setItemAnimator(new DefaultItemAnimator());

        //SET ADAPTER
        rv.setAdapter(adapter123);


    }


    public ArrayList<LectureOrSection> getClasses() {
        ArrayList<LectureOrSection> courses = new ArrayList<>();

        Spinner dept = (Spinner)findViewById(R.id.spinnersubject);
        //int index = dept.getSelectedItemPosition();

        String d = dept.getSelectedItem().toString();

        String currentclass= d;
        //coursesFall2016.get(0).getNamofLS();

        int i = 0;
        while(!(coursesFall2016.get(i).getNamofLS().contains(currentclass))) {
            i++;

        }


        int index = i;
        while(coursesFall2016.get(index).getNamofLS().contains(currentclass)){
            courses.add(coursesFall2016.get(index));
            index++;
        }

        return courses;


    }


    public void loadinArray(View v){

        if (coursesFall2016.size() ==0) {

            for (int i = 0; i < (stringListF16.size() - 1); i += 5) {
                String lectsect = stringListF16.get(i);
                String coursename = stringListF16.get(i+1);
                String weekdays = stringListF16.get(i + 2);
                String time = stringListF16.get(i + 3);
                String location = stringListF16.get(i + 4);


                LectureOrSection temporary = new LectureOrSection(lectsect,coursename, weekdays,
                        time, location);

                coursesFall2016.add(temporary);
            }



            TextView test = (TextView) findViewById(R.id.isitloaded);
            test.setText("Done:" + coursesFall2016.get(5).NameofLS);

        }
        else {


            AlertDialog alertDialog = new AlertDialog.Builder(this).create(); //Read Update
            alertDialog.setTitle("Already Loaded");
            alertDialog.setMessage("You don't need to load classes again");

            alertDialog.show();  //<-- See This!
        }
    }


    public void goCalendar(View view) {

        Intent intent = new Intent(this, CalendarView.class);
        Button butn = (Button) findViewById(R.id.gocalendar);
/*
        ArrayList<Float> starttime = new ArrayList<>();
        ArrayList<Float> endtime = new ArrayList<>();

        for(int i=0; i < customE.size(); i++) {
            float smin = (customE.get(i).getSmin())/60;
            float emin = (customE.get(i).getEmin())/60;

            float shour = customE.get(i).getShour();
            float ehour = customE.get(i).getEhour();

            float starting = shour + smin;
            float ending   = ehour + emin;

            if (starttime.size() == 0) {
                starttime.add(starting);
                endtime.add(ending);
            } else{
                int previous = starttime.size();
                for (int j=0; j < previous; j++){
                    if (starttime.get(j) < starting && starting < endtime.get(j) &&
                        starttime.get(j) < ending && ending < endtime.get(j)){
                        //throw exception

                        butn.setError("events conflict");

                    } else{
                        starttime.add(starting);
                        starttime.add(ending);
                    }
                }
            }
        }
*/
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


                int weekdayInt = spinner.getSelectedItemPosition();
                int hourInt = spinner1.getSelectedItemPosition();
                int minInt = spinner2.getSelectedItemPosition();
                int ampmInt = spinner3.getSelectedItemPosition();
                int hourIntF = spinner5.getSelectedItemPosition();
                int minIntF = spinner6.getSelectedItemPosition();
                int ampmIntF = spinner7.getSelectedItemPosition();

                CustomEventClass temp = new CustomEventClass(nameEvent, nameLocation,
                        weekdaytext, weekdayInt, hourInt, minInt, ampm, hourIntF, minIntF, ampmF);

                customE.add(temp);

                /*
                RecyclerView recyclerView = (RecyclerView)findViewById(R.id.classRecycler2);
                recyclerView.setHasFixedSize(true);

                LinearLayoutManager layoutManager = new LinearLayoutManager(this);
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setItemAnimator(new DefaultItemAnimator());

                adapter321 = new customAdapter(this, getPersonalEvents());
                recyclerView.setAdapter(adapter321);
*/
/*
                adapter321 = new customAdapter(this, getPersonalEvents());

                bsc = new StringBuffer();
                for (CustomEventClass p : adapter321.personalevents){
                    sb.append(p.getEventTitle());
                    sb.append("\n");
                }

                if(adapter321.personalevents.size() > 0){
                    Toast.makeText(InputEvents.this,bsc.toString(),Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(InputEvents.this,"PLease check classes",Toast.LENGTH_SHORT).show();
                }

                //RECYCLER
                RecyclerView rv = (RecyclerView)findViewById(R.id.classRecycler2);
                rv.setLayoutManager(new LinearLayoutManager(this));
                rv.setItemAnimator(new DefaultItemAnimator());

                //SET ADAPTER
                rv.setAdapter(adapter321);
*/


                inflatedEditRow(nameEvent, nameLocation, weekdaytext, hour, min, ampm, hourF, minF, ampmF);
                v.setVisibility(View.VISIBLE);


                //firebaseRef.child("TestCustomEvent").push().setValue(temp);

                //firebaseRef.child("tester").push().setValue(customEvents.get(0));

            }
        };
    }

    public ArrayList<CustomEventClass> getPersonalEvents(){
        ArrayList<CustomEventClass> temp = new ArrayList<>();

        temp = customE;

        return temp;
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
        //firebaseRef.child("TestCustomEvent").removeValue();

        for (int i = 0; i < customE.size(); i++) {
        //    firebaseRef.child("TestCustomEvent").push().setValue(customE.get(i));
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

    ArrayList<LectureOrSection> transfer = new ArrayList<LectureOrSection>();

    public void insertclasses(View viewit){
        for (int i=0; i < classAdapter.checkedclasses.size(); i++){
            transfer.add(classAdapter.checkedclasses.get(i));

            String name = classAdapter.checkedclasses.get(i).getNamofLS();
            String classrom = classAdapter.checkedclasses.get(i).getClassRoom();
            String weekdays = classAdapter.checkedclasses.get(i).getDaysOfWeek();
            String time = classAdapter.checkedclasses.get(i).getTimeofDay();
            String empty = "";
            inflatedEditRow(name,classrom,weekdays,time,empty,empty,empty,empty,empty);
            viewit.setVisibility(View.VISIBLE);
        }
    }


}
