package com.ucsbstudent.phillip.gauchocalendar_android;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import android.content.res.AssetManager;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;


public class AddToFireBase extends AppCompatActivity {

    ArrayList<LectureOrSection> coursesFall2016 = new ArrayList<LectureOrSection>();
    //private static final String FIREBASE_URL="https://sizzling-inferno-7789.firebaseIO.com";
    //private Firebase firebaseRef;
    String test = "";
    ArrayList<String> stringListTest = new ArrayList<String>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_to_fire_base);

        Context context = getApplicationContext();

        String filePath = context.getFilesDir().getAbsolutePath();

        File fileName = new File(filePath, "courses");
        try {

            BufferedReader bufferedReader =
                    new BufferedReader(new InputStreamReader(getAssets().open("courses.txt")));

            // This puts all the strings into an array of strings
            String temp;
            while ((temp = bufferedReader.readLine()) != null) {
                try {
                    stringListTest.add(temp);
                    stringListTest.add(bufferedReader.readLine());


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


        // test = "WHO"  + "\n"  + "WHAT" + "\n" + "WHERE \n";
        //  String est = lectures.get(5).name;
        TextView tryharder = (TextView) findViewById(R.id.testfirebase);
        tryharder.setText("NOT PRESSED");


    }

    // don't even need this button, we only need one button to make this work

    public void makeit(View view) {


        String attempt = Float.toString(coursesFall2016.get(0).getStartTime());
        TextView tester = (TextView) findViewById(R.id.testfirebase);
        tester.setText(attempt);

    }


    public void buildobjects(View v) {
        for (int i = 0; i < (stringListTest.size()-1); i += 5) {
            String coursename = stringListTest.get(i);
            String weekdays = stringListTest.get(i + 1);
            String time = stringListTest.get(i + 2);
            String location = stringListTest.get(i + 3);
            String enrolled = stringListTest.get(i + 4);

            LectureOrSection temporary = new LectureOrSection(coursename, weekdays,
                    time, location, enrolled);

            coursesFall2016.add(temporary);
        }


        //TextView tester2 = (TextView)findViewById(R.id.testfirebase);
        //Float start = coursesFall2016.get(0).getStartTime();
        //String time = Float.toString(start);
        //tester2.setText(time);
/*
        String pray;
        TextView tester2 = (TextView)findViewById(R.id.testfirebase);
        //pray = coursesFall2016.get(0).getNamofLS();
        int size = coursesFall2016.size();
        pray = Integer.toString(size);
        pray += " <- actual size, loop size - > ";
        int loopsize = ((stringListTest.size())/5);
        pray += Integer.toString(loopsize);
        tester2.setText(pray);
*/
    }
}







    // No longer needed
    /*
    public void string2Lect(BufferedReader br) {


        ArrayList<String> stringList = new ArrayList<String>();
//ArrayList<Lecture> finalList  = new ArrayList<Lecture>();
        while (br != null) {
            try {
                stringList.add(br.readLine());

            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        parse(stringList);


    }

public void parse(ArrayList<String> lectures){

    Lecture newCourse;

    int counter = -1;//used for the index of courses

    int index = 0; //Start at Anth 2
    String currentLect = lectures.get(index); //set to Anth 2
    String lectDays;
    String lectTimes;
    String lectLocation;
    String lectOcc;
    String sectDays;
    String sectTimes;
    String sectLocation;
    String sectOcc;
    Boolean isSect = false;


    for(int i = 0; i < lectures.size(); i++) {
        if (isSect == false) {
            lectDays = lectures.get(index + 1);
            lectTimes = lectures.get(index + 2);
            lectLocation = lectures.get(index + 3);
            lectOcc = lectures.get(index + 4);

            newCourse = new Lecture(currentLect, lectDays, lectTimes, lectLocation, lectOcc);
            courses.add(newCourse);
            counter++;

            if (index + 5 >= lectures.size()) {
                break;
            }

            if (lectures.get(index + 5).equals(currentLect)) {

                isSect = true;
            } else {

                currentLect = lectures.get(index + 5);
            }

            index = index + 5;

        } else {
            sectDays = lectures.get(index + 1);
            sectTimes = lectures.get(index + 2);
            sectLocation = lectures.get(index + 3);
            sectOcc = lectures.get(index + 4);


            courses.get(counter).addSection(sectDays, sectTimes, sectLocation, sectOcc);

            if (index + 5 >= lectures.size()) {
                break;
            }

            if (!(lectures.get(index + 5).equals(currentLect))) {
                isSect = false;
                currentLect = lectures.get(index + 5);
            }

            index = index + 5;
        }
    }

    }
    */


