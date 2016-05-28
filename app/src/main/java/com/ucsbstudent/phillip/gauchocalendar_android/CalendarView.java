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

import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;
import static android.widget.LinearLayout.LayoutParams.*;


public class  CalendarView extends AppCompatActivity implements View.OnClickListener {

    //private static final int MY_BUTTON = 9000;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar_view);

        String hello = "test";

        RelativeLayout Sat = (RelativeLayout) findViewById(R.id.relativeLayoutSatDay);
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


