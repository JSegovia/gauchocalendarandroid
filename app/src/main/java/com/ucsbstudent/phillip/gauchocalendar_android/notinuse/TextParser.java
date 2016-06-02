package com.ucsbstudent.phillip.gauchocalendar_android.notinuse;

/**
 * Created by Phillip on 5/31/2016.
 */
import com.ucsbstudent.phillip.gauchocalendar_android.notinuse.Lecture;

import java.io.*;
import java.util.ArrayList;

public class TextParser {
    public static ArrayList<Lecture> courses = new ArrayList<Lecture>();
    public static void main(String [] args) {
        ArrayList<String> lectures = new ArrayList<String>();
        // The name of the file to open.
        String fileName = "C:\\Users\\Phillip\\Android Projects\\gauchocalendarandroid\\app\\src\\main\\res\\raw\\courses.txt";

        // This will reference one line at a time
        String line = null;

        try {
            // FileReader reads text files in the default encoding.
            FileReader fileReader =
                    new FileReader(fileName);

            // Always wrap FileReader in BufferedReader.
            BufferedReader bufferedReader =
                    new BufferedReader(fileReader);

            while((line = bufferedReader.readLine()) != null) {
                lectures.add(line);
            }

            // Always close files.
            bufferedReader.close();
        }
        catch(FileNotFoundException ex) {
            System.out.println(
                    "Unable to open file '" +
                            fileName + "'");
        }
        catch(IOException ex) {
            System.out.println(
                    "Error reading file '"
                            + fileName + "'");
            // Or we could just do this:
            // ex.printStackTrace();
        }
        parse(lectures);

    }

    static void parse(ArrayList<String> lectures){


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


        for(int i = 0; i < lectures.size(); i++){
            if(isSect == false){
                lectDays = lectures.get(index+1);
                lectTimes = lectures.get(index+2);
                lectLocation = lectures.get(index+3);
                lectOcc = lectures.get(index+4);

                newCourse = new Lecture(currentLect,lectDays, lectTimes, lectLocation, lectOcc);
                courses.add(newCourse);
                counter++;

                if(index+5 >= lectures.size()){
                    break;
                }

                if(lectures.get(index+5).equals(currentLect)){

                    isSect = true;
                }
                else{

                    currentLect = lectures.get(index+5);
                }

                index = index+5;

            }
            else{
                sectDays = lectures.get(index+1);
                sectTimes = lectures.get(index+2);
                sectLocation = lectures.get(index+3);
                sectOcc = lectures.get(index+4);


                courses.get(counter).addSection(sectDays, sectTimes, sectLocation, sectOcc);

                if(index+5 >= lectures.size()){
                    break;
                }

                if(!(lectures.get(index+5).equals(currentLect))){
                    isSect = false;
                    currentLect = lectures.get(index+5);
                }

                index = index+5;
            }
        }


    }
}
