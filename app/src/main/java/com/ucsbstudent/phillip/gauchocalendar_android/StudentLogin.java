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


        findViewById(R.id.loginbtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                EditText username = (EditText) findViewById(R.id.TextFieldUSERNAME);
                EditText password = (EditText) findViewById(R.id.TextFieldPASSWORD);
                String user_name = username.getText().toString();
                String pass_word = password.getText().toString();
                String email = user_name + "@umail.ucsb.edu";
                final StudentProfile student = new StudentProfile(user_name, pass_word, email);


                firebaseRef.authWithPassword(student.email, student.password, new Firebase.AuthResultHandler() {
                    @Override
                    public void onAuthenticated(AuthData authData) {
                        Map<String, String> map = new HashMap<String, String>();
                        map.put("password", authData.getProvider());
                        if (authData.getProviderData().containsKey("email")) {
                            map.put("email", authData.getProviderData().get("email").toString());
                        }
                        firebaseRef.child("Users").child(authData.getUid()).setValue(student);
                        Toast.makeText(StudentLogin.this, "Login Successful", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(v.getContext(), Information.class);
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
                String user_name = username.getText().toString();
                String pass_word = password.getText().toString();
                String email = user_name + "@umail.ucsb.edu";
                final StudentProfile student = new StudentProfile(user_name, pass_word, email);

                firebaseRef.createUser(student.email, student.password, new Firebase.ValueResultHandler<Map<String, Object>>() {
                    @Override
                    public void onSuccess(Map<String, Object> result) {
                        Toast.makeText(StudentLogin.this, "Account Created!", Toast.LENGTH_LONG).show();
                        Toast.makeText(StudentLogin.this, "UID: " + result.get("uid"), Toast.LENGTH_SHORT).show();

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
