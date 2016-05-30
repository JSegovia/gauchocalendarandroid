package com.ucsbstudent.phillip.gauchocalendar_android;

import android.app.FragmentManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.client.Firebase;

import java.util.Map;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;

/**
 * Created by Phillip on 5/5/2016.
 */
public class  StudentLogin extends AppCompatActivity {
private static final String FIREBASE_URL="https://sizzling-inferno-7789.firebaseIO.com";
private Firebase firebaseRef;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_login);
        Firebase.setAndroidContext(this);
        firebaseRef = new Firebase(FIREBASE_URL);
        //firebaseRef.child("Users");

        findViewById(R.id.LoginButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText username = (EditText) findViewById(R.id.TextFieldUSERNAME);
                EditText password = (EditText) findViewById(R.id.TextFieldPASSWORD);
                String user_name = username.getText().toString();
                String pass_word = password.getText().toString();
                String email = user_name + "@umail.ucsb.edu";

                //if (user_name.matches("") || pass_word.matches("") ||
                //        user_name == "Enter Umail Username" || pass_word == "Enter your Password") {


                    StudentProfile student = new StudentProfile(user_name, pass_word, email);
                    firebaseRef.child("Users").push().setValue(student);
                    username.setText("");
                    password.setText("");
                    Toast.makeText(StudentLogin.this, "Account Inserted", Toast.LENGTH_LONG).show();
                //}

                Intent intent = new Intent(v.getContext(), Information.class);
                startActivity(intent);
            }
        });
    }



    public void buttonLogin(View view){
        Intent intent = new Intent(this, Information.class);
        startActivity(intent);

    }

}
