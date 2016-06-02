package com.ucsbstudent.phillip.gauchocalendar_android;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Phillip on 6/1/2016.
 */
public class buildcourses {

    ArrayList<Lecture> courses = new ArrayList<Lecture>();

    public void string2Lect(BufferedReader br) {
        ArrayList<String> stringList = new ArrayList<String>();
//ArrayList<Lecture> finalList  = new ArrayList<Lecture>();
        while (br != null) {
            try {
                stringList.add(br.readLine());

            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        parse(stringList);



    }




    public void parse(ArrayList<String> lectures){

        Lecture newCourse;

        int counter = -1;//used for the index of courses

        int index = 0; //Start at Anth 2
        String currentLect = lectures.get(index); //set to Anth 2
        String lectDays;
        String lectTimes;
        String lectLocation;
        String lectOcc;
        String sectDays;
        String sectTimes;
        String sectLocation;
        String sectOcc;
        Boolean isSect = false;


        for(int i = 0; i < lectures.size(); i++) {
            if (isSect == false) {
                lectDays = lectures.get(index + 1);
                lectTimes = lectures.get(index + 2);
                lectLocation = lectures.get(index + 3);
                lectOcc = lectures.get(index + 4);

                newCourse = new Lecture(currentLect, lectDays, lectTimes, lectLocation, lectOcc);
                courses.add(newCourse);
                counter++;

                if (index + 5 >= lectures.size()) {
                    break;
                }

                if (lectures.get(index + 5).equals(currentLect)) {

                    isSect = true;
                } else {

                    currentLect = lectures.get(index + 5);
                }

                index = index + 5;

            } else {
                sectDays = lectures.get(index + 1);
                sectTimes = lectures.get(index + 2);
                sectLocation = lectures.get(index + 3);
                sectOcc = lectures.get(index + 4);


                courses.get(counter).addSection(sectDays, sectTimes, sectLocation, sectOcc);

                if (index + 5 >= lectures.size()) {
                    break;
                }

                if (!(lectures.get(index + 5).equals(currentLect))) {
                    isSect = false;
                    currentLect = lectures.get(index + 5);
                }

                index = index + 5;
            }
        }

    }

}
