package com.ucsbstudent.phillip.gauchocalendar_android;

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;


public class AddToFireBase extends AppCompatActivity {

    ArrayList<Lecture> courses = new ArrayList<Lecture>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_to_fire_base);
        ArrayList<String> lectures = new ArrayList<String>();

        File sdcard = Environment.getExternalStorageDirectory();
        File fileName = new File(sdcard,"courses.txt");





        // The name of the file to open.
        //String fileName = "C:\\Users\\Phillip\\Android Projects\\gauchocalendarandroid\\app\\src\\main\\res\\raw\\courses.txt";

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


            String est = courses.get(5).name;
            TextView tryharder = (TextView) findViewById(R.id.testfirebase);
            tryharder.setText(est);
        }


    /*
    ArrayList<Lecture> newCourses = new ArrayList<Lecture>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_to_fire_base);


        try {
            FileInputStream fis = new FileInputStream("/C:\\Users\\Phillip\\Android Projects\\gauchocalendarandroid\\app\\src\\main\\res\\raw\\parsedcourses.txt");
            ObjectInputStream ois = new ObjectInputStream(fis);
            newCourses = (ArrayList) ois.readObject();
            ois.close();
            fis.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
            return;
        } catch (ClassNotFoundException c) {
            System.out.println("Class not found");
            c.printStackTrace();
            return;
        }

        //String test = newCourses.get(0).getName();
        //String test2 = "test";
        String est = newCourses.get(5).name;
        TextView tryharder = (TextView) findViewById(R.id.testfirebase);
        tryharder.setText(est);
        Log.d("Watermellon", est);

*/
    }

    public void makeit(View view){

        String est = courses.get(0).name;
        TextView tryharder = (TextView) findViewById(R.id.testfirebase);
        tryharder.setText(est);

        //TextView attempt2 = (TextView)findViewById(R.id.testfirebase);
        //attempt2.setText("test");

    }

}




