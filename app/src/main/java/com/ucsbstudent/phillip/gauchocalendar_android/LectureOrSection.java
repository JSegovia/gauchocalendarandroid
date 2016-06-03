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

        this.ClassRoom = ClassRoom;
        this.TimeOfDay = TimeOfDay;
        //If ampm = 0 then its AM
        //If ampm = 1 then its PM
        int ampmS = 0;

        String[] temp = TimeOfDay.split("\\s+");  // gets start time
        String startTime = temp[0];               //start time string in startTime "10:00am"
        String start = "";
        if(startTime.contains("a")){              // "start" gets value before am  "10:00"
            ampmS = 0;                            // set int ampmS = 0 for "am"
            String[] onlycolonStartAM = startTime.split("a");
            start = onlycolonStartAM[0];
        }
        if(startTime.contains("p")){              // "start" gets value before pm  "10:00"
            ampmS = 1;                            // set int ampm@ = 1 for "pm"
            String[] onlycolonPM = startTime.split("p");
            start = onlycolonPM[0];
        }
        String[] starthour3 = start.split(":");
        String starthour2 = starthour3[0];           // starthour2 = string of hour "10"
        String startmin1 = start.substring(start.lastIndexOf(":")+1);  // startmin = string of min "00"

        String starttrimhr = starthour2.trim();
        String starttrimmin = startmin1.trim();

        float starthour=0;
                float startmin=0;

                if(ampmS == 0){
                    if(starttrimhr.equals("1")){
                        starthour = 1;
                    }
                    if(starttrimhr.equals("2")){
                        starthour = 2;
                    }
                    if(starttrimhr.equals("3")){
                        starthour = 3;
                    }
                    if(starttrimhr.equals("4")){
                        starthour = 4;
                    }
                    if(starttrimhr.equals("5")){
                        starthour = 5;
            }
            if(starttrimhr.equals("6")){
                starthour = 6;
            }
            if(starttrimhr.equals("7")){
                starthour = 7;
            }
            if(starttrimhr.equals("8")){
                starthour = 8;
            }
            if(starttrimhr.equals("9")){
                starthour = 9;
            }
            if(starttrimhr.equals("10")){
                starthour = 10;
            }
            if(starttrimhr.equals("11")){
                starthour = 11;
            }
            if(starttrimhr.equals("12")){
                starthour = 0;
            }
        }

        if(ampmS == 1){
            if(starttrimhr.equals("12")){
                starthour = 12;
            }
            if(starttrimhr.equals("1")){
                starthour = 13;
            }
            if(starttrimhr.equals("2")){
                starthour = 14;
            }
            if(starttrimhr.equals("3")){
                starthour = 15;
            }
            if(starttrimhr.equals("4")){
                starthour = 16;
            }
            if(starttrimhr.equals("5")){
                starthour = 17;
            }
            if(starttrimhr.equals("6")){
                starthour = 18;
            }
            if(starttrimhr.equals("7")){
                starthour = 19;
            }
            if(starttrimhr.equals("8")){
                starthour = 20;
            }
            if(starttrimhr.equals("9")){
                starthour = 21;
            }
            if(starttrimhr.equals("10")){
                starthour = 22;
            }
            if(starttrimhr.equals("11")){
                starthour = 23;
            }
        }

        if(starttrimmin.equals("00")){
            startmin = 0;
        }
        if(starttrimmin.equals("10")){
            startmin = 10/60;
        }
        if(starttrimmin.equals("15")){
            startmin = 15/60;
        }
        if(starttrimmin.equals("30")){
            startmin = 30/60;
        }
        if(starttrimmin.equals("45")){
            startmin = 45/60;
        }
        if(starttrimmin.equals("50")){
            startmin = 50/60;
        }

        float finalstarttime = starthour + startmin;
        this.StartTime = finalstarttime;



        // begin same process for end time
        //If ampm = 0 then its AM
        //If ampm = 1 then its PM
        int ampmF = 0;

        String endTime = TimeOfDay.substring(TimeOfDay.lastIndexOf(" ")+1);   // string endtime = "10"00pm"

        String end = "";
        if(endTime.contains("a")){                  // gets value before am "10:00"
            ampmF = 0;                              // set int ampmF = 0 for "am"
            String[] endmin = endTime.split("a");
            end = endmin[0];
        }
        if(endTime.contains("p")){                  // gets value before pm "10:00"
            ampmF = 1;                              // set int ampmF = 1 for "pm"
            String[] endmin = endTime.split("p");
            end = endmin[0];
        }
        String[] endhour3 = end.split(":");
        String endhour2 = endhour3[0];              // endhour2 = string of hour "10"

        String endmin1 = end.substring(end.lastIndexOf(":")+1);    // endmin = string of min "00"

        String endtrimhr = endhour2.trim();
        String endtrimmin = endmin1.trim();

        float endhour=0;
        float endmin=0;

        if(ampmF == 0){
            if(endtrimhr.equals("1")){
                endhour = 1;
            }
            if(endtrimhr.equals("2")){
                endhour = 2;
            }
            if(endtrimhr.equals("3")){
                endhour = 3;
            }
            if(endtrimhr.equals("4")){
                endhour = 4;
            }
            if(endtrimhr.equals("5")){
                endhour = 5;
            }
            if(endtrimhr.equals("6")){
                endhour = 6;
            }
            if(endtrimhr.equals("7")){
                endhour = 7;
            }
            if(endtrimhr.equals("8")){
                endhour = 8;
            }
            if(endtrimhr.equals("9")){
                endhour = 9;
            }
            if(endtrimhr.equals("10")){
                endhour = 10;
            }
            if(endtrimhr.equals("11")){
                endhour = 11;
            }
            if(endtrimhr.equals("12")){
                endhour = 0;
            }
        }

        if(ampmF == 1){
            if(endtrimhr.equals("12")){
                endhour = 12;
            }
            if(endtrimhr.equals("1")){
                endhour = 13;
            }
            if(endtrimhr.equals("2")){
                endhour= 14;
            }
            if(endtrimhr.equals("3")){
                endhour = 15;
            }
            if(endtrimhr.equals("4")){
                endhour = 16;
            }
            if(endtrimhr.equals("5")){
                endhour = 17;
            }
            if(endtrimhr.equals("6")){
                endhour = 18;
            }
            if(endtrimhr.equals("7")){
                endhour = 19;
            }
            if(endtrimhr.equals("8")){
                endhour = 20;
            }
            if(endtrimhr.equals("9")){
                endhour = 21;
            }
            if(endtrimhr.equals("10")){
                endhour = 22;
            }
            if(endtrimhr.equals("11")){
                endhour = 23;
            }
        }

        if(endtrimmin.equals("00")){
            endmin = 0;
        }
        if(endtrimmin.equals("10")){
            endmin = 10/60;
        }
        if(endtrimmin.equals("15")){
            endmin = 15/60;
        }
        if(endtrimmin.equals("30")){
            endmin = 30/60;
        }
        if(endtrimmin.equals("45")){
            endmin = 45/60;
        }
        if(endtrimmin.equals("50")){
            endmin = 50/60;
        }

        float finalendtime = starthour + startmin;
        this.StartTime = finalendtime;


        this.EndTime = finalendtime;


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
