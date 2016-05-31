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
            Button btn = new Button(this);
            btn.setText(personal);
            btn.setTextColor(Color.parseColor("White"));
            btn.setBackgroundColor(getResources().getColor(R.color.com_facebook_blue));
            btn.setOnClickListener(this);


            RelativeLayout.LayoutParams paramss = new RelativeLayout.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            paramss.addRule(RelativeLayout.ALIGN_PARENT_LEFT, RelativeLayout.TRUE);

            int hour = customarray.get(i).getHour();
            int min = customarray.get(i).getMin();
            String ampm = customarray.get(i).getAmpm();

            int margin = hour*40;
            margin = margin + min;

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


        String hello = "test";

        Button btn = new Button(this);
        btn.setText(hello);
        btn.setTextColor(Color.parseColor("green"));
        btn.setBackgroundColor(getResources().getColor(R.color.com_facebook_blue));
        btn.setOnClickListener(this);


        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.addRule(RelativeLayout.ALIGN_PARENT_LEFT, RelativeLayout.TRUE);
        params.topMargin = 40;


        Sat.addView(btn, params);

        // btn.setLayoutParams(new LayoutParams());
        // TextView tv = new TextView(this);
        // tv.setText("Dynamic Text!");
        // Sat.addView(tv);


    }


    @Override
    public void onClick(View v) {
        AlertDialog alertDialog = new AlertDialog.Builder(this).create(); //Read Update
        alertDialog.setTitle("hi");
        alertDialog.setMessage("this is my app");

        alertDialog.show();  //<-- See This!
    }
}


