package com.ucsbstudent.phillip.gauchocalendar_android;

import com.firebase.client.Firebase;
import com.firebase.client.AuthData;
import com.firebase.client.FirebaseError;

import java.util.ArrayList;

/**
 * Created by Phillip on 5/4/2016.
 */

public class StudentProfile {
    private static final String FIREBASE_URL = "https://sizzling-inferno-7789.firebaseIO.com";
    private Firebase firebaseRef;
    public String username;
    public String password;
    public String email;

    public ArrayList<CustomEventClass> calendarcustom1 = new ArrayList<>();
    public ArrayList<LectureOrSection> calendarclass1 = new ArrayList<>();

    public ArrayList<CustomEventClass> calendarcustom2 = new ArrayList<>();
    public ArrayList<LectureOrSection> calendarclass2 = new ArrayList<>();


    public StudentProfile(String username, String password, String email,){
        this.username = username;
        this.password = password;
        this.email = email;
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

    public ArrayList<CustomEventClass> getCalendarcustom1() {
        ;
    }

    public ArrayList<LectureOrSection> getCalendarclass1() {
        return calendarclass1;
    }


}
