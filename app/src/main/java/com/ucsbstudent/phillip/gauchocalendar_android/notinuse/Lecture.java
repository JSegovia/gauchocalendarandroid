package com.ucsbstudent.phillip.gauchocalendar_android.notinuse;

import com.ucsbstudent.phillip.gauchocalendar_android.notinuse.Section;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by christiannewkirk on 5/30/16.
 */
public class Lecture implements Serializable{

        String name;
        String days;
        String time;
        String occupancy;
        String location;


        public ArrayList<Section> sections = new ArrayList<Section>();

        Lecture(String name, String lectureDays, String lectTime, String location, String occupancy){

            this.name = name;
            this.days = lectureDays;
            this.time = lectTime;
            this.location = location;
            this.occupancy = occupancy;

        }

        void addSection(String day, String time, String location, String occupancy){
            Section newSection = new Section(day, time, location, occupancy);

            sections.add(newSection);
        }

        String getName(){
            return this.name;
        }
        String getDays(){
            return this.days;
        }
        String getTime(){
            return this.time;
        }
        String getLocation(){
            return this.location;
        }
        String getOcc(){
            return this.occupancy;
        }

        void print(){
            System.out.println(name);
            System.out.println(days);
            System.out.println(time);
            System.out.println(occupancy);

            for(int i = 0; i < sections.size(); i++){
                sections.get(i).print();
            }
        }



}
