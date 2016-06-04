package com.ucsbstudent.phillip.gauchocalendar_android;

import com.ucsbstudent.phillip.gauchocalendar_android.notinuse.Lecture;

import java.util.ArrayList;

/**
 * Created by Phillip on 5/4/2016.
 */
public class StudentProfile {

    public String username;
    public String password;
    public String email;
    public ArrayList<CustomEventClass> calendar1 = new ArrayList<>();
    public ArrayList<LectureOrSection> calendar2 = new ArrayList<>();

    public StudentProfile(String username, String password, String email){
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

}
