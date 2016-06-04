package com.ucsbstudent.phillip.gauchocalendar_android;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.client.AuthData;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Phillip on 5/5/2016.
 */
public class  StudentLogin extends AppCompatActivity {
    private static final String FIREBASE_URL = "https://sizzling-inferno-7789.firebaseIO.com";
    private Firebase firebaseRef;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_login);
        Firebase.setAndroidContext(this);
        firebaseRef = new Firebase(FIREBASE_URL);
        Firebase.setAndroidContext(this);
        EditText user = (EditText) findViewById(R.id.TextFieldUSERNAME);
        final String use = user.getText().toString();

        findViewById(R.id.loginbtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                EditText username = (EditText) findViewById(R.id.TextFieldUSERNAME);
                EditText password = (EditText) findViewById(R.id.TextFieldPASSWORD);
                final String user_name = username.getText().toString();
                final String pass_word = password.getText().toString();
                final String email = user_name + "@umail.ucsb.edu";

                firebaseRef.authWithPassword(email, pass_word, new Firebase.AuthResultHandler() {
                    @Override
                    public void onAuthenticated(AuthData authData) {
                        Map<String, String> map = new HashMap<String, String>();
                        map.put("password", authData.getProvider());
                        if (authData.getProviderData().containsKey("email")) {
                            map.put("email", authData.getProviderData().get("email").toString());
                        }
                        firebaseRef.child("Users").child(user_name).child("courseCalendar").child("0").child("ClassRoom").setValue("Broida");
                        Toast.makeText(StudentLogin.this, "Login Successful", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(v.getContext(), Information.class);
                        intent.putExtra("username",use);
                        startActivity(intent);

                    }

                    @Override
                    public void onAuthenticationError(FirebaseError firebaseError) {
                        switch (firebaseError.getCode()) {
                            case FirebaseError.USER_DOES_NOT_EXIST:
                                Toast.makeText(StudentLogin.this, "User Does Not Exist", Toast.LENGTH_LONG).show();
                                break;
                            case FirebaseError.INVALID_PASSWORD:
                                Toast.makeText(StudentLogin.this, "Invalid Username/Password", Toast.LENGTH_LONG).show();
                                break;
                            default:
                                Toast.makeText(StudentLogin.this, "Please Enter Username and Password", Toast.LENGTH_SHORT).show();
                                break;
                        }
                    }
                });


                username.setText("");
                password.setText("");
            }
        });
        findViewById(R.id.LoginButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                EditText username = (EditText) findViewById(R.id.TextFieldUSERNAME);
                EditText password = (EditText) findViewById(R.id.TextFieldPASSWORD);
                final String user_name = username.getText().toString();
                final String pass_word = password.getText().toString();
                final String email = user_name + "@umail.ucsb.edu";
                int testInt = 0;
                String testString = "test";
                CustomEventClass testEvent = new CustomEventClass(testString, testString, testString, testInt, testInt, testInt, testString, testInt, testInt, testString);
                LectureOrSection testLect = new LectureOrSection(testString, testString,testString, testString, testString);

                final ArrayList<CustomEventClass> customCalendar = new ArrayList<CustomEventClass>() {};
                final ArrayList<LectureOrSection> courseCalendar = new ArrayList<LectureOrSection>() {};
                customCalendar.add(testEvent);
                courseCalendar.add(testLect);

                final StudentProfile student = new StudentProfile(user_name, pass_word, email, customCalendar, courseCalendar);


                firebaseRef.createUser(student.email, student.password, new Firebase.ValueResultHandler<Map<String, Object>>() {
                    @Override
                    public void onSuccess(Map<String, Object> result) {
                        firebaseRef.child("Users").child(user_name).setValue(student);
                        Toast.makeText(StudentLogin.this, "Account Created!", Toast.LENGTH_LONG).show();

                    }
                    @Override
                    public void onError(FirebaseError firebaseError) {
                        switch (firebaseError.getCode()) {
                            case FirebaseError.EMAIL_TAKEN:
                                Toast.makeText(StudentLogin.this, "User Already Exists", Toast.LENGTH_LONG).show();
                                break;
                            case FirebaseError.INVALID_PASSWORD:
                                Toast.makeText(StudentLogin.this, "Invalid Username/Password", Toast.LENGTH_LONG).show();
                                break;
                            default:
                                Toast.makeText(StudentLogin.this, "Please Enter Username and Password", Toast.LENGTH_SHORT).show();
                                break;
                        }
                    }
                });
            }
/**
 public void buttonLogin(View view) {
 EditText username = (EditText) findViewById(R.id.TextFieldUSERNAME);
 EditText password = (EditText) findViewById(R.id.TextFieldPASSWORD);
 String user = username.getText().toString();
 String pass = password.getText().toString();
 if (TextUtils.isEmpty(user) || TextUtils.isEmpty(pass)) {
 username.setError("Please enter your credentials");
 password.setError("Please enter your credentials");
 return;
 }
 Intent intent = new Intent(this, Information.class);
 startActivity(intent);
 }
 */
        });
    }
}