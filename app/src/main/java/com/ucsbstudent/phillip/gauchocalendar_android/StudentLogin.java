package com.ucsbstudent.phillip.gauchocalendar_android;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.firebase.client.core.view.View;

/**
 * Created by Phillip on 5/5/2016.
 */
public class StudentLogin extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_login);
    }
    public void buttonSignUp(View view){
        Intent intent = new Intent(this, Information.class);
        startActivity(intent);


    }

}
