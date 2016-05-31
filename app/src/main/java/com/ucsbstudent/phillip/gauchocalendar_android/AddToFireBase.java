package com.ucsbstudent.phillip.gauchocalendar_android;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;


public class AddToFireBase extends AppCompatActivity {

    ArrayList<Lecture> newCourses = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_to_fire_base);

        try {
            FileInputStream fis = new FileInputStream("/C:\\Users\\Phillip\\Android Projects\\gauchocalendarandroid\\app\\src\\main\\res\\raw\\parsedcourses");
            ObjectInputStream ois = new ObjectInputStream(fis);
            newCourses = (ArrayList) ois.readObject();
            ois.close();
            fis.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
            return;
        } catch (ClassNotFoundException c) {
            System.out.println("Class not found");
            c.printStackTrace();
            return;
        }

        //String test = newCourses.get(0).getName();
        //String test2 = "test";
        TextView tryharder = (TextView) findViewById(R.id.testfirebase);
        tryharder.setText("test");
    }

    public void makeit(){
        String est = newCourses.get(5).getLocation();
        TextView attempt2 = (TextView)findViewById(R.id.testfirebase);
        attempt2.setText("test");
        attempt2.setText(est);
    }

}




