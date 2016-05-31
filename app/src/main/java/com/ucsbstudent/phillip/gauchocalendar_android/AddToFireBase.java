package com.ucsbstudent.phillip.gauchocalendar_android;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

public class AddToFireBase extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_to_fire_base);

        ArrayList<Lecture> newCourses= new ArrayList<Lecture>();
        try
        {
            FileInputStream fis = new FileInputStream("/Users/christiannewkirk/AndroidStudioProjects/gauchocalendarandroid/app/src/main/res/raw/parsedcourses.txt");
            ObjectInputStream ois = new ObjectInputStream(fis);
            newCourses = (ArrayList) ois.readObject();
            ois.close();
            fis.close();
        }catch(IOException ioe){
            ioe.printStackTrace();
            return;
        }catch(ClassNotFoundException c){
            System.out.println("Class not found");
            c.printStackTrace();
            return;
        }
    }
}
