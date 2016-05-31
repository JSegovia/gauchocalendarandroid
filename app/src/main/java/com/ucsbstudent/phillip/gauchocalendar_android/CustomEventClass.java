package com.ucsbstudent.phillip.gauchocalendar_android;

/**
 * Created by Phillip on 5/30/2016.
 */
public class CustomEventClass {

    public String EventTitle;
    public String Location;
    public String Weekday;
    public int weekdayInt;
    public int hour;
    public int min;
    public String ampm;

    public CustomEventClass(String EventTitle, String Location, String Weekday,
                            int weekdayInt, int hour, int min, String ampm){
        this.EventTitle = EventTitle;
        this.Location = Location;
        this.Weekday = Weekday;
        this.weekdayInt = weekdayInt;
        this.hour = hour;
        this.min = min;
        this.ampm = ampm;
    }

    public void setEventTitle(String title) { this.EventTitle = title;}
    public void setLocation(String loc) { this.Location = loc; }
    public void setWeekday(String day){
        this.Weekday = day;
    }
    public void setWeekdayInt(int dayInt){ this.weekdayInt = dayInt;}
    public void setHour(int hr) {this.hour = hr;}
    public void setMin(int minute){ this.min = minute;}
    public void setAmpm(String mornAfter){this.ampm = mornAfter;}

    public String getEventTitle() {
        return EventTitle;
    }
    public String getLocation() {return Location; }
    public  String getWeekday(){
        return Weekday;
    }
    public int getWeekdayInt() {return weekdayInt;}
    public int getHour() {return hour;}
    public int getMin(){ return min;}
    public String getAmpm(){return ampm;}
}