package com.ucsbstudent.phillip.gauchocalendar_android;

import android.content.DialogInterface;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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


public class  CalendarView extends AppCompatActivity implements View.OnClickListener {

    //private static final int MY_BUTTON = 9000;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar_view);

        ArrayList<CustomEventClass> customarray = getIntent().getParcelableArrayListExtra("custom");


        RelativeLayout Sun = (RelativeLayout) findViewById(R.id.relativeLayoutSunday);
        RelativeLayout Mon = (RelativeLayout) findViewById(R.id.relativeLayoutMonDay);
        RelativeLayout Tue = (RelativeLayout) findViewById(R.id.relativeLayoutTueDay);
        RelativeLayout Wed = (RelativeLayout) findViewById(R.id.relativeLayoutWedDay);
        RelativeLayout Thu = (RelativeLayout) findViewById(R.id.relativeLayoutThuDay);
        RelativeLayout Fri = (RelativeLayout) findViewById(R.id.relativeLayoutFriDay);
        RelativeLayout Sat = (RelativeLayout) findViewById(R.id.relativeLayoutSatDay);


        for (int i=0; i < customarray.size(); i++){

            String personal = "Pers";
            String name = customarray.get(i).getEventTitle().toString();
            Button btn = new Button(this);
            btn.setText(name);
            btn.setTextColor(Color.parseColor("White"));
            btn.setBackgroundColor(getResources().getColor(R.color.com_facebook_blue));
            btn.setOnClickListener(this);


            RelativeLayout.LayoutParams paramss = new RelativeLayout.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            paramss.addRule(RelativeLayout.ALIGN_PARENT_LEFT, RelativeLayout.TRUE);

            int hour = customarray.get(i).getShour();
            int min = customarray.get(i).getSmin();
            String ampm = customarray.get(i).getSampm();

            int size;
            int mini=50;
            int maxi=500;

            size = mini + (int)(Math.random()*maxi);
            btn.setHeight(size);

            int margin;
            if(hour ==0){
                margin = 60;
            }
            else{
                margin = hour*60;
            }

            margin = margin + (min/2);

            if (ampm.equals("AM")){
                margin = margin *2;
            }
            paramss.topMargin = margin;

            if(customarray.get(i).getWeekdayInt() == 0){
                Sun.addView(btn,paramss);
            }

            if(customarray.get(i).getWeekdayInt() == 1){
                Mon.addView(btn,paramss);
            }

            if(customarray.get(i).getWeekdayInt() == 2){
                Tue.addView(btn,paramss);
            }

            if(customarray.get(i).getWeekdayInt() == 3){
                Wed.addView(btn,paramss);
            }

            if(customarray.get(i).getWeekdayInt() == 4){
                Thu.addView(btn,paramss);
            }

            if(customarray.get(i).getWeekdayInt() == 5){
                Fri.addView(btn,paramss);
            }

            if(customarray.get(i).getWeekdayInt() == 6){
                Sat.addView(btn,paramss);
            }

        }


        String hello = "CMPSC8";

        Button btn1 = new Button(this);
        btn1.setText(hello);
        //btn.setTextColor(Color.parseColor("green"));
        btn1.setBackgroundColor(getResources().getColor(R.color.com_facebook_blue));
        btn1.setOnClickListener(this);



        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.addRule(RelativeLayout.ALIGN_PARENT_LEFT, RelativeLayout.TRUE);
        //params.topMargin = 660;
        params.addRule(RelativeLayout.BELOW, R.id.twoam);
        Button btn2 = new Button(this);
        btn2.setText(hello);
        //btn.setTextColor(Color.parseColor("green"));
        btn2.setBackgroundColor(getResources().getColor(R.color.com_facebook_blue));
        btn2.setOnClickListener(this);

        Button btn3 = new Button(this);
        btn3.setText(hello);
        //btn.setTextColor(Color.parseColor("green"));
        btn3.setBackgroundColor(getResources().getColor(R.color.com_facebook_blue));
        btn3.setOnClickListener(this);

        Tue.addView(btn1, params);
        Wed.addView(btn2,params);
        Thu.addView(btn3,params);

        // btn.setLayoutParams(new LayoutParams());
        // TextView tv = new TextView(this);
        // tv.setText("Dynamic Text!");
        // Sat.addView(tv);


    }


    @Override
    public void onClick(View v) {
        AlertDialog alertDialog = new AlertDialog.Builder(this).create(); //Read Update
        alertDialog.setTitle("CMPSC8");
        alertDialog.setMessage("TWR    11:00am - 12:20pm   BRDA 1640");

        alertDialog.show();  //<-- See This!
    }
}


