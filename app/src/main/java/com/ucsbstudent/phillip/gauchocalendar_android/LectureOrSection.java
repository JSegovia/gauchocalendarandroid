package com.ucsbstudent.phillip.gauchocalendar_android;

import java.util.StringTokenizer;

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

        String newStart;
        String newEnd;
        float numStart;
        float numEnd;

        //split start and end time at '-'
        StringTokenizer tokenTime = new StringTokenizer(TimeOfDay, "-");
        String startTime = tokenTime.nextToken();
        String endTime = tokenTime.nextToken();

        //split start hr and min at ':'
        StringTokenizer tokenStartHr = new StringTokenizer(startTime, ":");
        String startHr = tokenStartHr.nextToken();
        String startMin = tokenStartHr.nextToken();

        //case: 1:00am to 9:00am
        if (startHr.length() == 1 && startMin.charAt(2) == 'a'){
            newStart = "0" + startHr + "." + startMin.charAt(0) + startMin.charAt(1);
            this.StartTime = Float.parseFloat(newStart);
        }

        //case: 12:00am and 12:00pm
        else if (startHr.length() == 2 && startHr.charAt(1) == '2') {
                if (startMin.charAt(2) == 'a') {
                    newStart = "00.00";
                } else {
                    newStart = "12.00";
                }
            this.StartTime = Float.parseFloat(newStart);
            }

        //case: 10:00am and 11:00am
        else if (startHr.length() == 2 && startMin.charAt(2) == 'a') {
                if (startHr.charAt(1) != '2') {
                    newStart = startHr + "." + startMin.charAt(0) + startMin.charAt(1);
                    this.StartTime = Float.parseFloat(newStart);
                }
            }

        else{

            //case: 1:00pm through 9:00pm
            if ((startHr.length() == 1) && startMin.charAt(2) == 'p') {
                int hour = Integer.parseInt(startHr);
                hour = hour + 12;
                String hr = Integer.toString(hour);
                newStart = hr + "." + startMin.charAt(0) + startMin.charAt(1);
                this.StartTime = Float.parseFloat(newStart);
            }
            //case: 11:00pm and 10:00pm
            if (startHr.length() == 2 && startHr.charAt(1) != '2') {
                if (startMin.charAt(2) == 'p') {
                    int hour2 = Integer.parseInt(startHr);
                    hour2 = hour2 + 12;
                    String hr2 = Integer.toString(hour2);
                    newStart = hr2 + "." + startMin.charAt(0) + startMin.charAt(1);
                    this.StartTime = Float.parseFloat(newStart);
                }
            }
        }


        //now same thing for end time

        //split end time at ':'
        StringTokenizer tokenEndHr = new StringTokenizer(startTime, ":");
        String endHr = tokenEndHr.nextToken();
        String endMin = tokenEndHr.nextToken();

        if (endHr.length() == 1 && endMin.charAt(2) == 'a'){
            newEnd = "0" + endHr + "." + endMin.charAt(0) + endMin.charAt(1);
            this.EndTime = Float.parseFloat(newEnd);
        }
        else if (endHr.length() == 2 && endHr.charAt(1) == '2') {
            if (endMin.charAt(2) == 'a') {
                newEnd = "00.00";
            } else {
                newEnd = "12.00";
            }
            this.EndTime = Float.parseFloat(newEnd);
        }
        else if (endHr.length() == 2 && endMin.charAt(2) == 'a') {
            if (endHr.charAt(1) != '2') {
                newEnd = endHr + "." + endMin.charAt(0) + endMin.charAt(1);
                this.EndTime = Float.parseFloat(newEnd);
            }
        }

        else{
            if ((endHr.length() == 1) && endMin.charAt(2) == 'p') {
                int hour3 = Integer.parseInt(endHr);
                hour3 = hour3 + 12;
                String hr3 = Integer.toString(hour3);
                newEnd = hr3 + "." + endMin.charAt(0) + endMin.charAt(1);
                this.EndTime = Float.parseFloat(newEnd);
            }
            if (endHr.length() == 2 && endHr.charAt(1) != '2') {
                if (endMin.charAt(2) == 'p') {
                    int hour4 = Integer.parseInt(endHr);
                    hour4 = hour4 + 12;
                    String hr4 = Integer.toString(hour4);
                    newEnd = hr4 + "." + endMin.charAt(0) + endMin.charAt(1);
                    this.EndTime = Float.parseFloat(newEnd);
                }
            }
        }

        //now numStart and numEnd are 24 hr floating point versions of
        //the start time and the end time


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
       // this.StartTime = 0;
        // this.EndTime   = 0;

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
