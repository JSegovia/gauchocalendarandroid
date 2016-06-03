package com.ucsbstudent.phillip.gauchocalendar_android;

import android.content.DialogInterface;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import java.util.Random;

import java.util.ArrayList;
import java.util.concurrent.locks.ReadWriteLock;

import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;
import static android.widget.LinearLayout.LayoutParams.*;


public class CalendarView extends AppCompatActivity implements View.OnClickListener {

    //private static final int MY_BUTTON = 9000;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar_view);


        DisplayMetrics displaymetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        int height = displaymetrics.heightPixels;
        int width = displaymetrics.widthPixels;


        ArrayList<CustomEventClass> customarray = getIntent().getParcelableArrayListExtra("custom");
        //To-DO get array from class selection


        /*
        RelativeLayout Sun = (RelativeLayout) findViewById(R.id.relativeLayoutSunday);
        RelativeLayout Mon = (RelativeLayout) findViewById(R.id.relativeLayoutMonDay);
        RelativeLayout Tue = (RelativeLayout) findViewById(R.id.relativeLayoutTueDay);
        RelativeLayout Wed = (RelativeLayout) findViewById(R.id.relativeLayoutWedDay);
        RelativeLayout Thu = (RelativeLayout) findViewById(R.id.relativeLayoutThuDay);
        RelativeLayout Fri = (RelativeLayout) findViewById(R.id.relativeLayoutFriDay);
        RelativeLayout Sat = (RelativeLayout) findViewById(R.id.relativeLayoutSatDay);
        */

        RelativeLayout useit = (RelativeLayout) findViewById(R.id.relativeLayout242);


        int bw = width/6;
        int bh = width/3;
        RelativeLayout.LayoutParams testparam = new RelativeLayout.LayoutParams(bw, bh);


        testparam.leftMargin = bw;
        testparam.addRule(RelativeLayout.BELOW, R.id.line1am);

        Button myButton = new Button(this);
        myButton.setBackgroundColor(getResources().getColor(R.color.com_facebook_blue));


        useit.addView(myButton, testparam);

        for (int i = 0; i < customarray.size(); i++) {

            String personal = "Pers";
            String name = customarray.get(i).getEventTitle();
            Button btn = new Button(this);
            btn.setText(name);
            btn.setTextColor(Color.parseColor("White"));
            btn.setBackgroundColor(getResources().getColor(R.color.com_facebook_blue));


            RelativeLayout.LayoutParams paramss = new RelativeLayout.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);


            int starthour = customarray.get(i).getShour();
            int endhour = customarray.get(i).getEhour();

            String sampm = customarray.get(i).getSampm();
            String eampm = customarray.get(i).getEampm();

            int weeknumber = customarray.get(i).getWeekdayInt();
            weeknumber++;

            weeknumber = weeknumber + width;
            paramss.leftMargin = weeknumber;
            paramss.addRule(RelativeLayout.BELOW, R.id.line1am);

            useit.addView(btn, paramss);


            /*
            if (starthour == 12) {
                if (sampm.equals("AM")) {
                    paramss.addRule(RelativeLayout.BELOW, R.id.line12am);
                } else {
                    paramss.addRule(RelativeLayout.BELOW, R.id.line12pm);
                }
            }
            if (starthour == 11) {
                if (sampm.equals("AM")) {
                    paramss.addRule(RelativeLayout.BELOW, R.id.line11am);
                } else {
                    paramss.addRule(RelativeLayout.BELOW, R.id.line11pm);
                }
            }
            if (starthour == 10) {
                if (sampm.equals("AM")) {
                    paramss.addRule(RelativeLayout.BELOW, R.id.line10am);
                } else {
                    paramss.addRule(RelativeLayout.BELOW, R.id.line10pm);
                }
            }
            if (starthour == 9) {
                if (sampm.equals("AM")) {
                    paramss.addRule(RelativeLayout.BELOW, R.id.line9am);
                } else {
                    paramss.addRule(RelativeLayout.BELOW, R.id.line9pm);
                }
            }
            if (starthour == 8) {
                if (sampm.equals("AM")) {
                    paramss.addRule(RelativeLayout.BELOW, R.id.line8am);
                } else {
                    paramss.addRule(RelativeLayout.BELOW, R.id.line8pm);
                }
            }
            if (starthour == 7) {
                if (sampm.equals("AM")) {
                    paramss.addRule(RelativeLayout.BELOW, R.id.line7am);
                } else {
                    paramss.addRule(RelativeLayout.BELOW, R.id.line7pm);
                }
            }
            if (starthour == 6) {
                if (sampm.equals("AM")) {
                    paramss.addRule(RelativeLayout.BELOW, R.id.line6am);
                } else {
                    paramss.addRule(RelativeLayout.BELOW, R.id.line6pm);
                }
            }
            if (starthour == 5) {
                if (sampm.equals("AM")) {
                    paramss.addRule(RelativeLayout.BELOW, R.id.line5am);
                } else {
                    paramss.addRule(RelativeLayout.BELOW, R.id.line5pm);
                }
            }
            if (starthour == 4) {
                if (sampm.equals("AM")) {
                    paramss.addRule(RelativeLayout.BELOW, R.id.line4am);
                } else {
                    paramss.addRule(RelativeLayout.BELOW, R.id.line4pm);
                }
            }
            if (starthour == 3) {
                if (sampm.equals("AM")) {
                    paramss.addRule(RelativeLayout.BELOW, R.id.line3am);
                } else {
                    paramss.addRule(RelativeLayout.BELOW, R.id.line3pm);
                }
            }
            if (starthour == 2) {
                if (sampm.equals("AM")) {
                    paramss.addRule(RelativeLayout.BELOW, R.id.line2am);
                } else {
                    paramss.addRule(RelativeLayout.BELOW, R.id.line2pm);
                }
            }
            if (starthour == 1) {
                if (sampm.equals("AM")) {
                    paramss.addRule(RelativeLayout.BELOW, R.id.line1am);
                } else {
                    paramss.addRule(RelativeLayout.BELOW, R.id.line1pm);
                }
            }
            */
/*
            int weeknumber = customarray.get(i).getWeekdayInt();
            weeknumber++;

            weeknumber = weeknumber + width;
            paramss.leftMargin = weeknumber;
            paramss.addRule(RelativeLayout.BELOW, R.id.line1am);

            heightsetter.addView(btn, paramss);
*/


        }

    }

    @Override
    public void onClick(View v) {

    }
}

