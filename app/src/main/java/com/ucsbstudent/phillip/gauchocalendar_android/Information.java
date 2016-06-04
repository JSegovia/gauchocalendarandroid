package com.ucsbstudent.phillip.gauchocalendar_android;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.ucsbstudent.phillip.gauchocalendar_android.recyclers.InputEvents;

public class Information extends AppCompatActivity {

    String user_name = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);
        Intent intent = getIntent();
        String username = intent.getExtras().getString("username");
        user_name = username;
    }

    public void start(View view){
        Intent intent = new Intent(this, InputEvents.class);
        startActivity(intent);
        intent.putExtra("username", user_name);
    }

}
