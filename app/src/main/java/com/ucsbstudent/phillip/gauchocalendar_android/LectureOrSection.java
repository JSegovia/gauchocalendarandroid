package com.ucsbstudent.phillip.gauchocalendar_android;

/**
 * Created by Phillip on 6/2/2016.
 */
public class LectureOrSection {

    public String NameofLS;
    public String DaysOfWeek;
    public String TimeOfDay;
    public String ClassRoom;
    public float StartTime;
    public float EndTime;

    // never going to be printed since no real time update
    public String NumEnrolled;

    public LectureOrSection(String Name, String DaysOfWeek, String TimeOfDay, String ClassRoom,
                            String NumEnrolled){
        this.NameofLS = Name;
        this.DaysOfWeek = DaysOfWeek;
        this.TimeOfDay = TimeOfDay;
        this.ClassRoom = ClassRoom;

        //String startTime = tokens[0];
        //String endTime   = tokens[1];

        //String[] valuesS = startTime.split(":",2);
        //String[] valuesE = endTime.split(":",2);

        //float starthour = Float.parseFloat(valuesS[0]);
        //float endhour   = Float.parseFloat(valuesE[0]);

        //String startmin  = valuesS[1].substring(1,2);
        //String startampm = valuesS[1].substring(3,4);

        //String endmin  = valuesE[1].substring(1,2);
        //String endampm = valuesE[1].substring(3,4);

        //float Smin = Float.parseFloat(startmin);
        //float Emin = Float.parseFloat(endmin);

        //if (startampm.equals("pm")){
        //    starthour = starthour*2;
        //}
        //if (endampm.equals("pm")){
        //    endhour = endhour*2;
        //}

        //Float startingtime = starthour + Smin;
        //Float endingtime = endhour + Emin;

        //String[][] arr = new String[numl]




        // gonna use military time and keep track as a float
        this.StartTime = 0;
        this.EndTime   = 0;

        //not going to be used
        this.NumEnrolled = NumEnrolled;
    }

    public void setNamofLS(String name){ this.NameofLS = name;}
    public void setDaysOfWeek(String weekday){this.DaysOfWeek = weekday;}
    public void setTimeofDay(String time){this.TimeOfDay = time;}
    public void setClassRoom(String classroom){this.ClassRoom = classroom;}
    public void setStartTime(int start){this.StartTime = start;}
    public void setEndTime(int end){this.EndTime = end;}
    public void setNumEnroled(String enroll){this.NumEnrolled = enroll;}

    public String  getNamofLS(){return NameofLS; }
    public String getDaysOfWeek(){return DaysOfWeek;}
    public String getTimeofDay(){return TimeOfDay;}
    public String getClassRoom(){return ClassRoom;}
    public float getStartTime(){return StartTime;}
    public float getEndTime(){return EndTime;}
    public String getNumEnroled(){return NumEnrolled;}





}
