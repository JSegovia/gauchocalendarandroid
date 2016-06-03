package com.ucsbstudent.phillip.gauchocalendar_android;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Phillip on 5/30/2016.
 */
public class CustomEventClass implements Parcelable {

    public String EventTitle;
    public String Location;
    public String Weekday;
    public int weekdayInt;
    public int Shour;
    public int Smin;
    public String Sampm;
    public int Ehour;
    public int Emin;
    public String Eampm;
    public float startime;
    public float endtime;

    public CustomEventClass(String EventTitle, String Location, String Weekday,
                            int weekdayInt, int hour, int min, String ampm,
                            int hourE, int minE, String ampmE){
        this.EventTitle = EventTitle;
        this.Location = Location;
        this.Weekday = Weekday;
        this.weekdayInt = weekdayInt;
        this.Shour = hour;
        this.Smin = min;
        this.Sampm = ampm;
        this.Ehour = hourE;
        this.Emin = minE;
        this.Eampm = ampmE;

        float tempHourStart = 0;

        if (ampm.equals("PM")) {
            if (hour == 12) {
                hour = 0;
            }
            tempHourStart = hour + 12;
        }

        if (ampm.equals("AM")) {
            if (hour == 12) {
                tempHourStart = 0;
            } else {
                tempHourStart = hour;
            }
        }


        float tempHourEnd = 0;

        if (ampmE.equals("PM")) {
            if (hourE == 12) {
                hourE = 0;
            }
            tempHourEnd = hourE + 12;
        }

        if (ampmE.equals("AM")) {
            if (hourE == 12) {
                tempHourEnd = 0;
            } else {
                tempHourEnd = hourE;
            }
        }

        float tempMinS = min / 60;
        float tempMinE = minE / 60;

        this.startime = tempHourStart + tempMinS;
        this.endtime = tempHourEnd + tempMinE;


    }

    public static final Creator<CustomEventClass> CREATOR = new Creator<CustomEventClass>() {
        @Override
        public CustomEventClass createFromParcel(Parcel in) {
            return new CustomEventClass(in);
        }

        @Override
        public CustomEventClass[] newArray(int size) {
            return new CustomEventClass[size];
        }
    };

    public void setEventTitle(String title) { this.EventTitle = title;}
    public void setLocation(String loc) { this.Location = loc; }
    public void setWeekday(String day){
        this.Weekday = day;
    }
    public void setWeekdayInt(int dayInt){ this.weekdayInt = dayInt;}
    public void setShour(int hr) {this.Shour = hr;}
    public void setSmin(int minute){ this.Smin = minute;}
    public void setSampm(String mornAfter){this.Sampm = mornAfter;}
    public void setEhour(int hrE) {this.Ehour = hrE;}
    public void setEmin(int minuteE){ this.Emin = minuteE;}
    public void setEampm(String mornAfterE){this.Eampm = mornAfterE;}

    public String getEventTitle() {return EventTitle;}
    public String getLocation() {return Location; }
    public  String getWeekday(){ return Weekday; }
    public int getWeekdayInt() {return weekdayInt;}
    public int getShour() {return Shour;}
    public int getSmin(){ return Smin;}
    public String getSampm(){return Sampm;}
    public int getEhour() {return Ehour;}
    public int getEmin(){ return Emin;}
    public String getEampm() {return Eampm;}
    public float getStartime() {return startime;}
    public float getEndtime() {return endtime;}



    public CustomEventClass(Parcel in){

        EventTitle = in.readString();
        Location = in.readString();
        Weekday = in.readString();
        weekdayInt = in.readInt();
        Shour = in.readInt();
        Smin = in.readInt();
        Sampm = in.readString();
        Ehour = in.readInt();
        Emin = in.readInt();
        Eampm = in.readString();
        startime = in.readFloat();
        endtime = in.readFloat();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(EventTitle);
        dest.writeString(Location);
        dest.writeString(Weekday);
        dest.writeInt(weekdayInt);
        dest.writeInt(Shour);
        dest.writeInt(Smin);
        dest.writeString(Sampm);
        dest.writeInt(Ehour);
        dest.writeInt(Emin);
        dest.writeString(Eampm);
        dest.writeFloat(startime);
        dest.writeFloat(endtime);
    }
}
