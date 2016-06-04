package com.ucsbstudent.phillip.gauchocalendar_android;

import java.util.ArrayList;

/**
 * Created by Phillip on 5/4/2016.
 */
public class StudentProfile {

    public String username;
    public String password;
    public String email;

    public ArrayList<CustomEventClass> calendarcustom1 = new ArrayList<>();
    public ArrayList<LectureOrSection> calendarclass1 = new ArrayList<>();

    public ArrayList<CustomEventClass> calendarcustom2 = new ArrayList<>();
    public ArrayList<LectureOrSection> calendarclass2 = new ArrayList<>();


    public StudentProfile(String username, String password, String email,
                          ArrayList<CustomEventClass> calendarcustom1, ArrayList<LectureOrSection> calendarclass1){
        this.username = username;
        this.password = password;
        this.email = email;
        this.calendarclass1 = calendarclass1;
        this.calendarcustom1 = calendarcustom1;
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
        return calendarcustom1;
    }

    public ArrayList<LectureOrSection> getCalendarclass1() {
        return calendarclass1;
    }


}
