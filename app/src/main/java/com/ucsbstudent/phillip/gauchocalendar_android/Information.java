package com.ucsbstudent.phillip.gauchocalendar_android;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Information extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);
    }

    public void start(View view){
        Intent intent = new Intent(this, InputEvents.class);
        startActivity(intent);
    }

    public void  makeArrayList(View v){
        Intent i = new Intent(this, AddToFireBase.class);
        startActivity(i);
    }
}
