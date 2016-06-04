package com.ucsbstudent.phillip.gauchocalendar_android;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import java.util.ArrayList;


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


        int bw = width/8;
        int min30 = height/30;
        int min15 = height/60;
        int hour = height/15;


        for (int i = 0; i < customarray.size(); i++) {

            String name = customarray.get(i).getEventTitle();
            Button btn = new Button(this);

            btn.setText(name);
            btn.setBackgroundColor(getResources().getColor(R.color.com_facebook_blue));

            int weeknumber = customarray.get(i).getWeekdayInt();
            weeknumber++;

            float hournumber = customarray.get(i).getStartime();
            int start = (int) hournumber;
            int theShiftL = weeknumber * bw;
            int theShiftT = (hour * (start)) + (start * 2) + hour;

            if (start == 11) {
                theShiftT = 0;
            }
            if (start == 23) {
                theShiftT = 12 * hour + 24;
            }

            int pixel;
            int minS = customarray.get(i).getSmin();
            if(minS == 0){
                pixel = 0;
            }
            else if (minS != 0 && minS < 15) {
                pixel = (((height/30)/2)/2);
            }else if (15 <= minS && minS < 30){
                pixel = ((height/30)/2);
            }else if(30 <= minS && minS <45){
                pixel = (height/30);
            }else if (45 <= minS && minS <55){
                pixel = ((3*(height/30))/4);
            }else{
                pixel = hour;
            }
            theShiftT = theShiftT + pixel;


            //^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^6
            float hourend = customarray.get(i).getEndtime();
            int end = (int) hourend;
            int theShiftB = (hour * (end)) + (end * 2) + hour;

            if (end == 11) {
                theShiftB = 0;
            }
            if (end == 23) {
                theShiftB = 12 * hour + 24;
            }

            int pixelB;
            int minE = customarray.get(i).getEmin();
            if(minE ==0){
                pixelB =0;
            }
            else if (minE != 0 && minE <= 15) {
                pixelB = (((height/30)/2)/2);
            }else if (15 < minE && minE < 30){
                pixelB = ((height/30)/2);
            }else if(30 <= minE && minE <=45){
                pixelB = (height/30);
            }else if (45 < minE && minE <=55){
                pixelB = ((3*(height/30))/4);
            }else{
                pixelB = hour;
            }

            theShiftB = theShiftB + pixelB - hour;

            int please = theShiftB - theShiftL;


            RelativeLayout.LayoutParams paramss = new RelativeLayout.LayoutParams(bw, please);
            paramss.leftMargin = theShiftL;
            paramss.topMargin = theShiftT;

            useit.addView(btn, paramss);


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

