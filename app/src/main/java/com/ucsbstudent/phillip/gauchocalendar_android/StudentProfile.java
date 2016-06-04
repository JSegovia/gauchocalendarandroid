package com.ucsbstudent.phillip.gauchocalendar_android;

import com.firebase.client.Firebase;

import java.util.ArrayList;

/**
 * Created by Phillip on 5/4/2016.
 */

public class StudentProfile {
    private static final String FIREBASE_URL = "https://sizzling-inferno-7789.firebaseIO.com";
    private Firebase firebaseRef = new Firebase(FIREBASE_URL);
    public String username;
    public String password;
    public String email;

    public ArrayList<CustomEventClass> customCalendar = new ArrayList<>();
    public ArrayList<LectureOrSection> courseCalendar= new ArrayList<>();


    public StudentProfile(String username, String password, String email, ArrayList<CustomEventClass> customCalendar,
                          ArrayList<LectureOrSection> courseCalendar) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.customCalendar = customCalendar;
        this.courseCalendar = courseCalendar;
    }

    public String getUsername() {
        return username;
    }
    public String getPassword() {
        return password;
    }
    public  String getEmail(){
        return email;
    }

    public ArrayList<CustomEventClass> getCustomCalendar() {
        return customCalendar;
    }

    public ArrayList<LectureOrSection> getCourseCalendar() {
        return courseCalendar;
    }


}
