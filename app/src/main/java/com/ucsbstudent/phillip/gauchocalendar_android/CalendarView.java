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

            btn.setBackgroundColor(getResources().getColor(R.color.com_facebook_blue));

            int weeknumber = customarray.get(i).getWeekdayInt();
            weeknumber++;

            float hournumber = customarray.get(i).getStartime();
            int start = (int) hournumber;
            int theShiftL = weeknumber * bw;
            int theShiftT = (hour * (start)) + (start*2) + hour;

            if (start == 11) {
                theShiftT = 0;
            }
            if (start == 23) {
                theShiftT = 12 * hour + 24;
            }

            double minS = (double) customarray.get(i).getSmin();
            int pixel =(int) (height / (minS*2));

            theShiftT = theShiftT + pixel;

            //Finding endtime location.
            float hournumberF = customarray.get(i).getEndtime();
            int end = (int) hournumber;
            int theShiftB = (hour * (start)) + (start*2) + hour;

            if (end == 11) {
                theShiftB = 0;
            }
            if (end == 23) {
                theShiftB = 12 * hour + 24;
            }

            double minE = (double) customarray.get(i).getSmin();
            int pixelE =(int) (height / (minS*2));

            theShiftT = theShiftT + pixelE;



            RelativeLayout.LayoutParams paramss = new RelativeLayout.LayoutParams(bw, hour);
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

