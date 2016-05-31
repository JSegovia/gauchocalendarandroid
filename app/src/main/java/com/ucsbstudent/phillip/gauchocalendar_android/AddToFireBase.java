package com.ucsbstudent.phillip.gauchocalendar_android;

import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.firebase.client.Firebase;

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
    //private static final String FIREBASE_URL="https://sizzling-inferno-7789.firebaseIO.com";
    //private Firebase firebaseRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_to_fire_base);
        ArrayList<String> lectures = new ArrayList<String>();

        //File sdcard = Environment.getExternalStorageDirectory();
        //File fileName = new File(sdcard,"courses.txt");


        Context context = getApplicationContext();

        String filePath = context.getFilesDir().getAbsolutePath();
        File fileName = new File(filePath, "courses.txt");


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
/*
        firebaseRef.child("string").child("ANTH").push().setValue("ANTH 2\n" +
                "M W F\n" +
                "10:00am - 10:50am\n" +
                "CAMPBHALL\n" +
                "135 / 135\n" +
                "ANTH 2\n" +
                "F\n" +
                "2:00pm - 2:50pm\n" +
                "HSSB 1215\n" +
                "9 / 25\n" +
                "ANTH 2\n" +
                "F\n" +
                "2:00pm - 2:50pm\n" +
                "HSSB 1214\n" +
                "0 / 25\n" +
                "ANTH 2\n" +
                "W\n" +
                "6:00pm - 6:50pm\n" +
                "PHELP1448\n" +
                "6 / 25\n" +
                "ANTH 2\n" +
                "W\n" +
                "8:00 am - 8:50 am\n" +
                "HSSB 1223\n" +
                "4 / 25\n" +
                "ANTH 2\n" +
                "F\n" +
                "9:00 am - 9:50 am\n" +
                "HSSB 1233\n" +
                "4 / 25\n" +
                "ANTH 2\n" +
                "W\n" +
                "5:00pm - 5:50pm\n" +
                "HSSB 2251\n" +
                "4 / 25\n" +
                "ANTH 2\n" +
                "F\n" +
                "2:00pm - 2:50pm\n" +
                "HSSB 1211\n" +
                "1 / 25\n" +
                "ANTH 2\n" +
                "M\n" +
                "7:00pm - 7:50pm\n" +
                "HSSB 1233\n" +
                "1 / 25\n" +
                "ANTH 2\n" +
                "W\n" +
                "4:00pm - 4:50pm\n" +
                "HSSB 1233\n" +
                "7 / 25\n" +
                "ANTH 2\n" +
                "M\n" +
                "5:00pm - 5:50pm\n" +
                "HSSB 2251\n" +
                "3 / 25\n" +
                "ANTH 2\n" +
                "F\n" +
                "4:00pm - 4:50pm\n" +
                "HSSB 1206\n" +
                "0 / 25\n" +
                "ANTH 2\n" +
                "T\n" +
                "8:00 am - 8:50 am\n" +
                "HSSB 1224\n" +
                "4 / 25\n" +
                "ANTH 2\n" +
                "F\n" +
                "12:00pm - 12:50pm\n" +
                "HSSB 1228\n" +
                "8 / 25\n" +
                "ANTH 2\n" +
                "M\n" +
                "8:00 am - 8:50 am\n" +
                "HSSB 1214\n" +
                "4 / 25\n" +
                "ANTH 2\n" +
                "W\n" +
                "5:00pm - 5:50pm\n" +
                "HSSB 1236\n" +
                "0 / 25\n" +
                "ANTH 2\n" +
                "T\n" +
                "6:00pm - 6:50pm\n" +
                "PHELP1448\n" +
                "5 / 25\n" +
                "ANTH 2\n" +
                "T\n" +
                "7:00pm - 7:50pm\n" +
                "HSSB 1233\n" +
                "1 / 25\n" +
                "ANTH 2\n" +
                "F\n" +
                "8:00 am - 8:50 am\n" +
                "HSSB 1207\n" +
                "1 / 25\n" +
                "ANTH 2\n" +
                "R\n" +
                "7:00pm - 7:50pm\n" +
                "GIRV 2124\n" +
                "0 / 25\n" +
                "ANTH 2\n" +
                "F\n" +
                "9:00 am - 9:50 am\n" +
                "HSSB 1232\n" +
                "0 / 25\n" +
                "ANTH 2\n" +
                "F\n" +
                "2:00pm - 2:50pm\n" +
                "HSSB 1210\n" +
                "2 / 25\n" +
                "ANTH 2\n" +
                "R\n" +
                "8:00 am - 8:50 am\n" +
                "HSSB 1231\n" +
                "1 / 25\n" +
                "ANTH 2\n" +
                "R\n" +
                "9:00 am - 9:50 am\n" +
                "HSSB 1215\n" +
                "6 / 25\n" +
                "ANTH 2\n" +
                "W\n" +
                "7:00pm - 7:50pm\n" +
                "PHELP2514\n" +
                "1 / 25\n" +
                "ANTH 2\n" +
                "F\n" +
                "2:00pm - 2:50pm\n" +
                "HSSB 1207\n" +
                "1 / 25\n" +
                "ANTH 2\n" +
                "F\n" +
                "1:00pm - 1:50pm\n" +
                "HSSB 1227\n" +
                "2 / 25\n" +
                "ANTH 2\n" +
                "R\n" +
                "1:00pm - 1:50pm\n" +
                "PHELP2536\n" +
                "14 / 25\n" +
                "ANTH 2\n" +
                "M\n" +
                "9:00 am - 9:50 am\n" +
                "HSSB 1210\n" +
                "9 / 25\n" +
                "ANTH 2\n" +
                "W\n" +
                "9:00 am - 9:50 am\n" +
                "HSSB 1215\n" +
                "17 / 25\n" +
                "ANTH 2\n" +
                "F\n" +
                "3:00pm - 3:50pm\n" +
                "HSSB 1206\n" +
                "2 / 25\n" +
                "ANTH 2\n" +
                "F\n" +
                "4:00pm - 4:50pm\n" +
                "BUCHN1934\n" +
                "0 / 25\n" +
                "ANTH 2\n" +
                "M\n" +
                "4:00pm - 4:50pm\n" +
                "HSSB 1223\n" +
                "11 / 25\n" +
                "ANTH 2\n" +
                "T\n" +
                "9:00 am - 9:50 am\n" +
                "HSSB 1223\n" +
                "6 / 25\n" +
                "ANTH 2\n" +
                "R\n" +
                "6:00pm - 6:50pm\n" +
                "PHELP3519\n" +
                "1 / 25\n" +
                "ANTH 5\n" +
                "T R\n" +
                "11:00am - 12:15pm\n" +
                "CAMPBHALL\n" +
                "56 / 200\n" +
                "ANTH 5\n" +
                "F\n" +
                "12:00pm - 12:50pm\n" +
                "HSSB 1227\n" +
                "7 / 25\n" +
                "ANTH 5\n" +
                "M\n" +
                "4:00pm - 4:50pm\n" +
                "HSSB 1214\n" +
                "6 / 25\n" +
                "ANTH 5\n" +
                "W\n" +
                "6:00pm - 6:50pm\n" +
                "HSSB 1237\n" +
                "0 / 25\n" +
                "ANTH 5\n" +
                "W\n" +
                "8:00 am - 8:50 am\n" +
                "HSSB 1215\n" +
                "2 / 25\n" +
                "ANTH 5\n" +
                "F\n" +
                "2:00pm - 2:50pm\n" +
                "HSSB 1206\n" +
                "2 / 25\n" +
                "ANTH 5\n" +
                "F\n" +
                "1:00pm - 1:50pm\n" +
                "HSSB 1206\n" +
                "0 / 25\n" +
                "ANTH 5\n" +
                "R\n" +
                "5:00pm - 5:50pm\n" +
                "HSSB 1237\n" +
                "1 / 25\n" +
                "ANTH 5\n" +
                "F\n" +
                "11:00am - 11:50am\n" +
                "ARTS 1356\n" +
                "5 / 25\n" +
                "ANTH 5\n" +
                "R\n" +
                "7:00pm - 7:50pm\n" +
                "HSSB 1237\n" +
                "0 / 25\n" +
                "ANTH 5\n" +
                "T\n" +
                "8:00 am - 8:50 am\n" +
                "HSSB 1223\n" +
                "0 / 25\n" +
                "ANTH 5\n" +
                "W\n" +
                "9:00 am - 9:50 am\n" +
                "HSSB 1210\n" +
                "5 / 25\n" +
                "ANTH 5\n" +
                "R\n" +
                "8:00 am - 8:50 am\n" +
                "HSSB 1228\n" +
                "1 / 25\n" +
                "ANTH 5\n" +
                "F\n" +
                "9:00 am - 9:50 am\n" +
                "HSSB 1231\n" +
                "0 / 25\n" +
                "ANTH 5\n" +
                "W\n" +
                "4:00pm - 4:50pm\n" +
                "HSSB 1232\n" +
                "3 / 25\n" +
                "ANTH 5\n" +
                "M\n" +
                "5:00pm - 5:50pm\n" +
                "HSSB 1236\n" +
                "0 / 25\n" +
                "ANTH 5\n" +
                "M\n" +
                "6:00pm - 6:50pm\n" +
                "HSSB 1237\n" +
                "0 / 25\n" +
                "ANTH 5\n" +
                "M\n" +
                "12:00pm - 12:50pm\n" +
                "HSSB 1223\n" +
                "8 / 25\n" +
                "ANTH 5\n" +
                "M\n" +
                "2:00pm - 2:50pm\n" +
                "NH 1111\n" +
                "2 / 25\n" +
                "ANTH 5\n" +
                "W\n" +
                "2:00pm - 2:50pm\n" +
                "BUCHN1934\n" +
                "5 / 25\n" +
                "ANTH 5\n" +
                "M\n" +
                "7:00pm - 7:50pm\n" +
                "BUCHN1934\n" +
                "2 / 25\n" +
                "ANTH 5\n" +
                "T\n" +
                "9:00 am - 9:50 am\n" +
                "HSSB 1215\n" +
                "3 / 25\n" +
                "ANTH 5\n" +
                "T\n" +
                "5:00pm - 5:50pm\n" +
                "HSSB 1232\n" +
                "3 / 25\n" +
                "ANTH 5\n" +
                "T\n" +
                "6:00pm - 6:50pm\n" +
                "HSSB 1237\n" +
                "0 / 25\n" +
                "ANTH 5\n" +
                "T\n" +
                "7:00pm - 7:50pm\n" +
                "BUCHN1934\n" +
                "0 / 25\n" +
                "ANTH 5\n" +
                "W\n" +
                "7:00pm - 7:50pm\n" +
                "HSSB 2251\n" +
                "0 / 25\n" +
                "ANTH 5\n" +
                "W\n" +
                "5:00pm - 5:50pm\n" +
                "HSSB 1232\n" +
                "1 / 25\n" +
                "ANTH 5\n" +
                "R\n" +
                "6:00pm - 6:50pm\n" +
                "HSSB 1237\n" +
                "0 / 25\n" +
                "ANTH 5\n" +
                "F\n" +
                "8:00 am - 8:50 am\n" +
                "HSSB 1206\n" +
                "0 / 25\n" +
                "ANTH 99\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "0 / 1\n" +
                "ANTH 108\n" +
                "T\n" +
                "2:00pm - 4:50pm\n" +
                "HSSB 2001A\n" +
                "30 / 30\n" +
                "ANTH 109\n" +
                "T R\n" +
                "6:30pm - 7:45pm\n" +
                "PHELP1260\n" +
                "21 / 90\n" +
                "ANTH 125\n" +
                "T R\n" +
                "11:00am - 12:15pm\n" +
                "PHELP3519\n" +
                "30 / 30\n" +
                "ANTH 148A\n" +
                "T R\n" +
                "12:30pm - 1:45pm\n" +
                "GIRV 2127\n" +
                "42 / 42\n" +
                "ANTH 153T\n" +
                "T R\n" +
                "3:30pm - 4:45pm\n" +
                "PHELP1445\n" +
                "35 / 35\n" +
                "ANTH 154\n" +
                "M W\n" +
                "11:00am - 12:15pm\n" +
                "HSSB 2001A\n" +
                "21 / 21\n" +
                "ANTH 178\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "2 / 10\n" +
                "ANTH 180A\n" +
                "M W\n" +
                "8:00 am - 9:15 am\n" +
                "HSSB 1021\n" +
                "0 / 20\n" +
                "ANTH 182\n" +
                "M\n" +
                "12:00pm - 2:45pm\n" +
                "SSMS 1301\n" +
                "11 / 20\n" +
                "ANTH 182M\n" +
                "T R\n" +
                "12:30pm - 1:45pm\n" +
                "HSSB 1021\n" +
                "18 / 30\n" +
                "ANTH 183\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "0 / 10\n" +
                "ANTH 190\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "0 / 15\n" +
                "ANTH 192AB\n" +
                "T R\n" +
                "9:30 am - 10:45am\n" +
                "ARTS 1353\n" +
                "48 / 48\n" +
                "ANTH 193A\n" +
                "M\n" +
                "3:30pm - 4:45pm\n" +
                "HSSB 2001A\n" +
                "28 / 25\n" +
                "ANTH 194\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "0 / 5\n" +
                "ANTH 194P\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "0 / 5\n" +
                "ANTH 195B\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "0 / 10\n" +
                "ANTH 198\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "0 / 5\n" +
                "ANTH 199\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "0 / 5\n" +
                "ANTH 199RA\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "0 / 5\n" );
                firebaseRef.child("string").child("ART").push().setValue("ART 1A\n" +
                "M W\n" +
                "3:30pm - 4:45pm\n" +
                "PSYCH1924\n" +
                "80 / 100\n" +
                "ART 1A\n" +
                "T\n" +
                "9:00 am - 9:50 am\n" +
                "ARTS 1344\n" +
                "20 / 20\n" +
                "ART 1A\n" +
                "R\n" +
                "11:30am - 12:20pm\n" +
                "ARTS 1344\n" +
                "20 / 20\n" +
                "ART 1A\n" +
                "R\n" +
                "12:30pm - 1:20pm\n" +
                "ARTS 1344\n" +
                "20 / 20\n" +
                "ART 1A\n" +
                "F\n" +
                "12:00pm - 12:50pm\n" +
                "ARTS 1344\n" +
                "20 / 20\n" +
                "ART 1C\n" +
                "R\n" +
                "5:00pm - 6:50pm\n" +
                "EMBARHALL\n" +
                "139 / 139\n" +
                "ART 7B\n" +
                "M\n" +
                "5:00pm - 6:50pm\n" +
                "PSYCH1902\n" +
                "29 / 75\n" +
                "ART 7B\n" +
                "W F\n" +
                "11:00am - 12:50pm\n" +
                "ARTS 1345\n" +
                "8 / 23\n" +
                "ART 7B\n" +
                "T R\n" +
                "11:00am - 12:50pm\n" +
                "ARTS 1345\n" +
                "17 / 23\n" +
                "ART 7B\n" +
                "T R\n" +
                "1:00pm - 2:50pm\n" +
                "ARTS 1345\n" +
                "4 / 23\n" +
                "ART 10\n" +
                "T R\n" +
                "1:00pm - 3:50pm\n" +
                "ARTS 2628\n" +
                "18 / 18\n" +
                "ART 12\n" +
                "W F\n" +
                "9:00 am - 11:50am\n" +
                "ARTS 0641\n" +
                "5 / 18\n" +
                "ART 14\n" +
                "T R\n" +
                "9:00 am - 11:50am\n" +
                "ARTS 2235\n" +
                "5 / 18\n" +
                "ART CS 15\n" +
                "R\n" +
                "2:30pm - 4:20pm\n" +
                "494 136\n" +
                "0 / 35\n" +
                "ART 18\n" +
                "M W\n" +
                "8:30 am - 11:00am\n" +
                "ARTS 2636\n" +
                "12 / 18\n" +
                "ART 19\n" +
                "T R\n" +
                "12:00pm - 12:50pm\n" +
                "ARTS 1237\n" +
                "5 / 60\n" +
                "ART 19\n" +
                "T R\n" +
                "10:00am - 12:50pm\n" +
                "ARTS 1237\n" +
                "2 / 30\n" +
                "ART 19\n" +
                "T R\n" +
                "12:00pm - 2:50pm\n" +
                "ARTS 1237\n" +
                "3 / 30\n" +
                "ART 22\n" +
                "T R\n" +
                "1:00pm - 3:50pm\n" +
                "ARTS 2220\n" +
                "12 / 21\n" +
                "ART 32\n" +
                "F\n" +
                "9:00 am - 12:50pm\n" +
                "ARTS 2220\n" +
                "12 / 15\n" +
                "ART 100\n" +
                "T R\n" +
                "1:00pm - 2:50pm\n" +
                "ARTS 2422\n" +
                "18 / 18\n" +
                "ART CS 102\n" +
                "T R\n" +
                "11:30am - 2:20pm\n" +
                "494 136\n" +
                "2 / 15\n" +
                "ART CS 105\n" +
                "W\n" +
                "1:00pm - 4:20pm\n" +
                "494 107\n" +
                "3 / 12\n" +
                "ART CS 105\n" +
                "R\n" +
                "6:30pm - 8:20pm\n" +
                "494 107\n" +
                "3 / 12\n" +
                "ART 105PP\n" +
                "T R\n" +
                "9:00 am - 11:50am\n" +
                "ARTS 0641\n" +
                "7 / 15\n" +
                "ART CS 112\n" +
                "T\n" +
                "4:00pm - 5:20pm\n" +
                "494 136\n" +
                "2 / 8\n" +
                "ART CS 112\n" +
                "M\n" +
                "5:30pm - 8:20pm\n" +
                "494 136\n" +
                "0 / 18\n" +
                "ART 117\n" +
                "M W\n" +
                "12:00pm - 2:30pm\n" +
                "ARTS 2636\n" +
                "18 / 18\n" +
                "ART 130\n" +
                "M W\n" +
                "5:00pm - 6:50pm\n" +
                "ARTS 1344\n" +
                "18 / 30\n" +
                "ART 136\n" +
                "M W\n" +
                "1:00pm - 3:50pm\n" +
                "ARTS 1344\n" +
                "15 / 15\n" +
                "ART 192ES\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "0 / 10\n" +
                "ART 192IA\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "0 / 10\n" +
                "ART 196\n" +
                "T\n" +
                "1:00pm - 3:50pm\n" +
                "ARTS 1344\n" +
                "0 / 10\n" +
                "ART 199\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "0 / 10\n" +
                "ART 199RA\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "0 / 10\n" +
                "ART CS 15\n" +
                "R\n" +
                "2:30pm - 4:20pm\n" +
                "494 136\n" +
                "0 / 35\n" +
                "ART CS 102\n" +
                "T R\n" +
                "11:30am - 2:20pm\n" +
                "494 136\n" +
                "2 / 15\n" +
                "ART CS 105\n" +
                "W\n" +
                "1:00pm - 4:20pm\n" +
                "494 107\n" +
                "3 / 12\n" +
                "ART CS 105\n" +
                "R\n" +
                "6:30pm - 8:20pm\n" +
                "494 107\n" +
                "3 / 12\n" +
                "ART CS 112\n" +
                "T\n" +
                "4:00pm - 5:20pm\n" +
                "494 136\n" +
                "2 / 8\n" +
                "ART CS 112\n" +
                "M\n" +
                "5:30pm - 8:20pm\n" +
                "494 136\n" +
                "0 / 18\n" );
                firebaseRef.child("string").child("ARTHI").push().setValue("ARTHI 5A\n" +
                "T R\n" +
                "5:00pm - 6:15pm\n" +
                "HFH 1104\n" +
                "100 / 100\n" +
                "ARTHI 5A\n" +
                "M\n" +
                "11:00am - 11:50am\n" +
                "ARTS 2324\n" +
                "25 / 25\n" +
                "ARTHI 5A\n" +
                "M\n" +
                "12:00pm - 12:50pm\n" +
                "ARTS 2324\n" +
                "19 / 25\n" +
                "ARTHI 5A\n" +
                "M\n" +
                "1:00pm - 1:50pm\n" +
                "ARTS 2324\n" +
                "14 / 25\n" +
                "ARTHI 5A\n" +
                "M\n" +
                "2:00pm - 2:50pm\n" +
                "ARTS 2324\n" +
                "10 / 25\n" +
                "ARTHI 5A\n" +
                "M\n" +
                "3:00pm - 3:50pm\n" +
                "ARTS 2324\n" +
                "13 / 25\n" +
                "ARTHI 5A\n" +
                "M\n" +
                "4:00pm - 4:50pm\n" +
                "ARTS 2324\n" +
                "19 / 25\n" +
                "ARTHI 6A\n" +
                "T R\n" +
                "12:30pm - 1:45pm\n" +
                "IV THEA1\n" +
                "285 / 285\n" +
                "ARTHI 6A\n" +
                "T\n" +
                "3:00pm - 3:50pm\n" +
                "ARTS 2324\n" +
                "25 / 25\n" +
                "ARTHI 6A\n" +
                "T\n" +
                "4:00pm - 4:50pm\n" +
                "ARTS 2324\n" +
                "25 / 25\n" +
                "ARTHI 6A\n" +
                "T\n" +
                "5:00pm - 5:50pm\n" +
                "ARTS 2324\n" +
                "20 / 25\n" +
                "ARTHI 6A\n" +
                "W\n" +
                "9:00 am - 9:50 am\n" +
                "ARTS 2324\n" +
                "10 / 25\n" +
                "ARTHI 6A\n" +
                "W\n" +
                "10:00am - 10:50am\n" +
                "ARTS 2324\n" +
                "25 / 25\n" +
                "ARTHI 6A\n" +
                "W\n" +
                "11:00am - 11:50am\n" +
                "ARTS 2324\n" +
                "25 / 25\n" +
                "ARTHI 6A\n" +
                "W\n" +
                "12:00pm - 12:50pm\n" +
                "ARTS 2324\n" +
                "25 / 25\n" +
                "ARTHI 6A\n" +
                "W\n" +
                "1:00pm - 1:50pm\n" +
                "ARTS 2324\n" +
                "24 / 25\n" +
                "ARTHI 6A\n" +
                "W\n" +
                "2:00pm - 2:50pm\n" +
                "ARTS 2324\n" +
                "24 / 25\n" +
                "ARTHI 6A\n" +
                "W\n" +
                "3:00pm - 3:50pm\n" +
                "ARTS 2324\n" +
                "14 / 25\n" +
                "ARTHI 6A\n" +
                "W\n" +
                "4:00pm - 4:50pm\n" +
                "ARTS 2324\n" +
                "12 / 25\n" +
                "ARTHI 6A\n" +
                "W\n" +
                "5:00pm - 5:50pm\n" +
                "ARTS 2324\n" +
                "6 / 25\n" +
                "ARTHI 6A\n" +
                "R\n" +
                "9:00 am - 9:50 am\n" +
                "ARTS 2324\n" +
                "7 / 25\n" +
                "ARTHI 6A\n" +
                "W\n" +
                "11:00am - 11:50am\n" +
                "ARTS 2622\n" +
                "0 / 10\n" +
                "ARTHI 6A\n" +
                "R\n" +
                "10:00am - 10:50am\n" +
                "ARTS 2324\n" +
                "18 / 25\n" +
                "ARTHI 6A\n" +
                "R\n" +
                "11:00am - 11:50am\n" +
                "ARTS 2324\n" +
                "25 / 25\n" +
                "ARTHI 6DS\n" +
                "M W\n" +
                "5:00pm - 6:15pm\n" +
                "TD-W 1701\n" +
                "70 / 70\n" +
                "ARTHI 6DS\n" +
                "T\n" +
                "1:00pm - 1:50pm\n" +
                "ARTS 1332\n" +
                "25 / 25\n" +
                "ARTHI 6DS\n" +
                "T\n" +
                "2:00pm - 2:50pm\n" +
                "ARTS 1332\n" +
                "24 / 25\n" +
                "ARTHI 6DS\n" +
                "T\n" +
                "3:00pm - 3:50pm\n" +
                "ARTS 1332\n" +
                "9 / 25\n" +
                "ARTHI 6DS\n" +
                "T\n" +
                "4:00pm - 4:50pm\n" +
                "ARTS 1332\n" +
                "12 / 25\n" +
                "ARTHI 6G\n" +
                "T R\n" +
                "11:00am - 12:15pm\n" +
                "PSYCH1924\n" +
                "70 / 70\n" +
                "ARTHI 6G\n" +
                "T\n" +
                "1:00pm - 1:50pm\n" +
                "ARTS 1332\n" +
                "24 / 25\n" +
                "ARTHI 6G\n" +
                "T\n" +
                "2:00pm - 2:50pm\n" +
                "ARTS 1332\n" +
                "24 / 25\n" +
                "ARTHI 6G\n" +
                "T\n" +
                "3:00pm - 3:50pm\n" +
                "ARTS 1332\n" +
                "9 / 25\n" +
                "ARTHI 6G\n" +
                "T\n" +
                "4:00pm - 4:50pm\n" +
                "ARTS 1332\n" +
                "13 / 25\n" +
                "ARTHI 109G\n" +
                "T R\n" +
                "2:00pm - 3:15pm\n" +
                "ARTS 1341\n" +
                "50 / 50\n" +
                "ARTHI 111B\n" +
                "M W\n" +
                "11:00am - 12:15pm\n" +
                "ARTS 1341\n" +
                "14 / 50\n" +
                "ARTHI 111C\n" +
                "T R\n" +
                "11:00am - 12:15pm\n" +
                "ARTS 1341\n" +
                "10 / 50\n" +
                "ARTHI 120AA\n" +
                "T R\n" +
                "9:30 am - 10:45am\n" +
                "ARTS 1341\n" +
                "15 / 50\n" +
                "ARTHI 120BB\n" +
                "M W\n" +
                "2:00pm - 3:15pm\n" +
                "ARTS 1341\n" +
                "12 / 50\n" +
                "ARTHI 121D\n" +
                "T R\n" +
                "12:30pm - 1:45pm\n" +
                "ARTS 1341\n" +
                "35 / 50\n" +
                "ARTHI 130C\n" +
                "M W\n" +
                "3:30pm - 4:45pm\n" +
                "ARTS 1341\n" +
                "13 / 50\n" +
                "ARTHI 136A\n" +
                "M W\n" +
                "9:30 am - 10:45am\n" +
                "ARTS 1341\n" +
                "21 / 50\n" +
                "ARTHI 141B\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "0 / 20\n" +
                "ARTHI 141C\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "0 / 15\n" +
                "ARTHI 141D\n" +
                "M W\n" +
                "12:30pm - 1:45pm\n" +
                "ARTS 1341\n" +
                "24 / 50\n" +
                "ARTHI 141E\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "0 / 15\n" +
                "ARTHI 141F\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "0 / 15\n" +
                "ARTHI 142D\n" +
                "T R\n" +
                "3:30pm - 4:45pm\n" +
                "ARTS 1341\n" +
                "6 / 50\n" +
                "ARTHI 142E\n" +
                "T R\n" +
                "12:30pm - 1:45pm\n" +
                "NH 1105\n" +
                "5 / 50\n" +
                "ARTHI 186B\n" +
                "T R\n" +
                "9:30 am - 10:45am\n" +
                "ARTS 2622\n" +
                "14 / 15\n" +
                "ARTHI 186H\n" +
                "R\n" +
                "11:00am - 1:50pm\n" +
                "ARTS 2622\n" +
                "7 / 15\n" +
                "ARTHI 186T\n" +
                "M\n" +
                "2:00pm - 4:50pm\n" +
                "ARTS 2622\n" +
                "2 / 15\n" +
                "ARTHI 198\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "0 / 15\n" +
                "ARTHI 199\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "0 / 50\n" +
                "ARTHI 199RA\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "0 / 15\n" +
                "AS AM 1\n" +
                "T R\n" +
                "12:30pm - 1:45pm\n" +
                "EMBARHALL\n" +
                "131 / 131\n" +
                "AS AM 1\n" +
                "R\n" +
                "2:00pm - 2:50pm\n" +
                "HSSB 3202\n" +
                "22 / 22\n" +
                "AS AM 1\n" +
                "R\n" +
                "3:00pm - 3:50pm\n" +
                "HSSB 3202\n" +
                "23 / 23\n" +
                "AS AM 1\n" +
                "R\n" +
                "4:00pm - 4:50pm\n" +
                "HSSB 3202\n" +
                "23 / 23\n" +
                "AS AM 1\n" +
                "F\n" +
                "9:00 am - 9:50 am\n" +
                "HSSB 3202\n" +
                "10 / 23\n" +
                "AS AM 1\n" +
                "F\n" +
                "10:00am - 10:50am\n" +
                "HSSB 3202\n" +
                "22 / 23\n" +
                "AS AM 1\n" +
                "F\n" +
                "11:00am - 11:50am\n" +
                "HSSB 3202\n" +
                "7 / 23\n" +
                "AS AM 1\n" +
                "F\n" +
                "12:00pm - 12:50pm\n" +
                "HSSB 3202\n" +
                "5 / 23\n" +
                "AS AM 1\n" +
                "F\n" +
                "1:00pm - 1:50pm\n" +
                "HSSB 3202\n" +
                "8 / 23\n" +
                "AS AM 1\n" +
                "F\n" +
                "2:00pm - 2:50pm\n" +
                "HSSB 3202\n" +
                "11 / 23\n" +
                "AS AM 2\n" +
                "M W F\n" +
                "1:00pm - 1:50pm\n" +
                "MUSICLLCH\n" +
                "126 / 126\n" +
                "AS AM 2\n" +
                "R\n" +
                "9:00 am - 9:50 am\n" +
                "HSSB 4201\n" +
                "10 / 23\n" +
                "AS AM 2\n" +
                "R\n" +
                "10:00am - 10:50am\n" +
                "HSSB 4201\n" +
                "23 / 23\n" +
                "AS AM 2\n" +
                "R\n" +
                "11:00am - 11:50am\n" +
                "HSSB 1228\n" +
                "22 / 23\n" +
                "AS AM 2\n" +
                "F\n" +
                "9:00 am - 9:50 am\n" +
                "HSSB 5024\n" +
                "2 / 23\n" +
                "AS AM 2\n" +
                "F\n" +
                "10:00am - 10:50am\n" +
                "HSSB 5024\n" +
                "9 / 23\n" +
                "AS AM 2\n" +
                "F\n" +
                "11:00am - 11:50am\n" +
                "HSSB 4202\n" +
                "15 / 23\n" +
                "AS AM 2\n" +
                "F\n" +
                "2:00pm - 2:50pm\n" +
                "HSSB 3201\n" +
                "14 / 23\n" +
                "AS AM 2\n" +
                "F\n" +
                "3:00pm - 3:50pm\n" +
                "HSSB 3201\n" +
                "4 / 23\n" +
                "AS AM 2\n" +
                "F\n" +
                "4:00pm - 4:50pm\n" +
                "HSSB 3201\n" +
                "2 / 23\n" +
                "AS AM 2\n" +
                "R\n" +
                "9:00 am - 9:50 am\n" +
                "HSSB 5024\n" +
                "1 / 23\n" +
                "AS AM 2\n" +
                "R\n" +
                "10:00am - 10:50am\n" +
                "HSSB 5024\n" +
                "6 / 23\n" +
                "AS AM 2\n" +
                "R\n" +
                "11:00am - 11:50am\n" +
                "HSSB 5024\n" +
                "18 / 23\n" +
                "AS AM 2\n" +
                "F\n" +
                "2:00pm - 2:50pm\n" +
                "HSSB 5024\n" +
                "0 / 10\n" +
                "AS AM 2H\n" +
                "F\n" +
                "2:00pm - 2:50pm\n" +
                "HSSB 5024\n" +
                "0 / 20\n" +
                "AS AM 5\n" +
                "T R\n" +
                "9:30 am - 10:45am\n" +
                "MUSICLLCH\n" +
                "151 / 151\n" +
                "AS AM 5\n" +
                "R\n" +
                "12:00pm - 12:50pm\n" +
                "HSSB 5024\n" +
                "22 / 23\n" +
                "AS AM 5\n" +
                "R\n" +
                "1:00pm - 1:50pm\n" +
                "HSSB 5024\n" +
                "23 / 23\n" +
                "AS AM 5\n" +
                "R\n" +
                "2:00pm - 2:50pm\n" +
                "HSSB 5024\n" +
                "22 / 23\n" +
                "AS AM 5\n" +
                "R\n" +
                "2:00pm - 2:50pm\n" +
                "HSSB 1214\n" +
                "23 / 23\n" +
                "AS AM 5\n" +
                "R\n" +
                "3:00pm - 3:50pm\n" +
                "HSSB 3201\n" +
                "7 / 23\n" +
                "AS AM 5\n" +
                "R\n" +
                "4:00pm - 4:50pm\n" +
                "HSSB 3201\n" +
                "9 / 23\n" +
                "AS AM 5\n" +
                "R\n" +
                "3:00pm - 3:50pm\n" +
                "HSSB 5024\n" +
                "3 / 23\n" +
                "AS AM 5\n" +
                "R\n" +
                "4:00pm - 4:50pm\n" +
                "HSSB 5024\n" +
                "9 / 22\n" +
                "AS AM 5\n" +
                "R\n" +
                "5:00pm - 5:50pm\n" +
                "HSSB 5024\n" +
                "10 / 23\n" +
                "AS AM 5\n" +
                "F\n" +
                "11:00am - 11:50am\n" +
                "HSSB 2201\n" +
                "15 / 20\n" +
                "AS AM 5\n" +
                "F\n" +
                "12:00pm - 12:50pm\n" +
                "HSSB 3201\n" +
                "5 / 23\n" +
                "AS AM 5\n" +
                "F\n" +
                "1:00pm - 1:50pm\n" +
                "HSSB 3201\n" +
                "3 / 23\n" +
                "AS AM 100FF\n" +
                "T R\n" +
                "2:00pm - 3:15pm\n" +
                "ARTS 1349\n" +
                "45 / 45\n" +
                "AS AM 108\n" +
                "M W\n" +
                "2:00pm - 3:15pm\n" +
                "ARTS 1356\n" +
                "8 / 45\n" +
                "AS AM 128\n" +
                "M W\n" +
                "5:00pm - 6:15pm\n" +
                "ARTS 1349\n" +
                "45 / 45\n" +
                "AS AM 154\n" +
                "M W F\n" +
                "11:00am - 11:50am\n" +
                "SH 1430\n" +
                "15 / 45\n" +
                "AS AM 160\n" +
                "M W\n" +
                "12:30pm - 1:45pm\n" +
                "PSYCH1902\n" +
                "7 / 35\n" +
                "AS AM 160\n" +
                "M\n" +
                "10:00am - 10:50am\n" +
                "HSSB 4201\n" +
                "7 / 12\n" +
                "AS AM 163\n" +
                "W F\n" +
                "11:00am - 12:15pm\n" +
                "HSSB 5024\n" +
                "14 / 25\n" +
                "AS AM 199\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "0 / 10\n" +
                "AS AM 199RA\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "0 / 10\n" +
                "ASTRO 1\n" +
                "T R\n" +
                "2:00pm - 3:15pm\n" +
                "BRDA 1610\n" +
                "54 / 54\n" +
                "ASTRO 1\n" +
                "F\n" +
                "9:00 am - 9:50 am\n" +
                "GIRV 1112\n" +
                "11 / 47\n" +
                "ASTRO 1\n" +
                "W\n" +
                "1:00pm - 1:50pm\n" +
                "SH 1430\n" +
                "23 / 47\n" +
                "ASTRO 1\n" +
                "W\n" +
                "5:00pm - 5:50pm\n" +
                "GIRV 1112\n" +
                "6 / 47\n" +
                "ASTRO 1\n" +
                "W\n" +
                "4:00pm - 4:50pm\n" +
                "GIRV 1112\n" +
                "6 / 47\n" +
                "ASTRO 1\n" +
                "W\n" +
                "6:00pm - 6:50pm\n" +
                "SH 1430\n" +
                "3 / 47\n" +
                "ASTRO 1\n" +
                "M\n" +
                "4:00pm - 4:50pm\n" +
                "GIRV 1112\n" +
                "5 / 47\n" +
                "ASTRO 1H\n" +
                "W\n" +
                "2:00pm - 3:15pm\n" +
                "PHELP1448\n" +
                "0 / 30\n" +
                "BIOL CS 10\n" +
                "W\n" +
                "3:00pm - 4:50pm\n" +
                "494 143\n" +
                "0 / 20\n" +
                "BIOL CS 12\n" +
                "R\n" +
                "8:00 am - 8:50 am\n" +
                "494 143\n" +
                "0 / 20\n" +
                "BIOL CS 101\n" +
                "T\n" +
                "9:00 am - 10:50am\n" +
                "494 143\n" +
                "0 / 20\n" +
                "BIOL CS 10\n" +
                "W\n" +
                "3:00pm - 4:50pm\n" +
                "494 143\n" +
                "0 / 20\n" +
                "BIOL CS 12\n" +
                "R\n" +
                "8:00 am - 8:50 am\n" +
                "494 143\n" +
                "0 / 20\n" +
                "BIOL CS 101\n" +
                "T\n" +
                "9:00 am - 10:50am\n" +
                "494 143\n" +
                "0 / 20\n" +
                "BMSE 199\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "0 / 50\n" +
                "BL ST 1\n" +
                "T R\n" +
                "11:00am - 12:15pm\n" +
                "HFH 1104\n" +
                "145 / 145\n" +
                "BL ST 1\n" +
                "W\n" +
                "10:00am - 10:50am\n" +
                "SH 3711\n" +
                "20 / 20\n" +
                "BL ST 1\n" +
                "W\n" +
                "11:00am - 11:50am\n" +
                "SH 3711\n" +
                "20 / 20\n" +
                "BL ST 1\n" +
                "W\n" +
                "12:00pm - 12:50pm\n" +
                "SH 3711\n" +
                "20 / 20\n" +
                "BL ST 1\n" +
                "T\n" +
                "3:00pm - 3:50pm\n" +
                "HSSB 1214\n" +
                "20 / 20\n" +
                "BL ST 1\n" +
                "W\n" +
                "8:00 am - 8:50 am\n" +
                "HSSB 1227\n" +
                "8 / 20\n" +
                "BL ST 1\n" +
                "T\n" +
                "5:00pm - 5:50pm\n" +
                "SH 3711\n" +
                "18 / 20\n" +
                "BL ST 1\n" +
                "W\n" +
                "1:00pm - 1:50pm\n" +
                "SH 3711\n" +
                "20 / 20\n" +
                "BL ST 1\n" +
                "R\n" +
                "8:00 am - 8:50 am\n" +
                "SH 3711\n" +
                "4 / 20\n" +
                "BL ST 1\n" +
                "R\n" +
                "9:00 am - 9:50 am\n" +
                "SH 3711\n" +
                "15 / 20\n" +
                "BL ST 1H\n" +
                "T\n" +
                "1:00pm - 1:50pm\n" +
                "SH 3711\n" +
                "0 / 20\n" +
                "BL ST 7\n" +
                "M W\n" +
                "3:30pm - 4:45pm\n" +
                "BUCHN1920\n" +
                "85 / 85\n" +
                "BL ST 7\n" +
                "T\n" +
                "9:00 am - 9:50 am\n" +
                "SH 3711\n" +
                "12 / 20\n" +
                "BL ST 7\n" +
                "T\n" +
                "10:00am - 10:50am\n" +
                "SH 3711\n" +
                "19 / 20\n" +
                "BL ST 7\n" +
                "T\n" +
                "11:00am - 11:50am\n" +
                "SH 3711\n" +
                "19 / 20\n" +
                "BL ST 7\n" +
                "R\n" +
                "10:00am - 10:50am\n" +
                "SH 3711\n" +
                "8 / 20\n" +
                "BL ST 7\n" +
                "R\n" +
                "11:00am - 11:50am\n" +
                "SH 3711\n" +
                "15 / 20\n" +
                "BL ST 7\n" +
                "R\n" +
                "12:00pm - 12:50pm\n" +
                "SH 3711\n" +
                "12 / 20\n" +
                "BL ST 33\n" +
                "T R\n" +
                "8:00 am - 9:15 am\n" +
                "GIRV 1004\n" +
                "45 / 45\n" +
                "BL ST 33\n" +
                "T\n" +
                "12:00pm - 12:50pm\n" +
                "SH 3711\n" +
                "10 / 10\n" +
                "BL ST 33\n" +
                "T\n" +
                "3:00pm - 3:50pm\n" +
                "PHELP2532\n" +
                "10 / 10\n" +
                "BL ST 33\n" +
                "W\n" +
                "8:00 am - 8:50 am\n" +
                "HSSB 1232\n" +
                "6 / 10\n" +
                "BL ST 33\n" +
                "W\n" +
                "2:00pm - 2:50pm\n" +
                "SH 3711\n" +
                "10 / 10\n" +
                "BL ST 33\n" +
                "W\n" +
                "3:00pm - 3:50pm\n" +
                "SH 3711\n" +
                "4 / 10\n" +
                "BL ST 33\n" +
                "W\n" +
                "4:00pm - 4:50pm\n" +
                "SH 3711\n" +
                "5 / 10\n" +
                "BL ST 38B\n" +
                "T R\n" +
                "11:00am - 12:15pm\n" +
                "GIRV 1004\n" +
                "85 / 85\n" +
                "BL ST 38B\n" +
                "T\n" +
                "5:00pm - 5:50pm\n" +
                "ARTS 1349\n" +
                "12 / 12\n" +
                "BL ST 38B\n" +
                "T\n" +
                "6:00pm - 6:50pm\n" +
                "SH 3707\n" +
                "12 / 13\n" +
                "BL ST 38B\n" +
                "W\n" +
                "8:00 am - 8:50 am\n" +
                "SH 3707\n" +
                "9 / 12\n" +
                "BL ST 38B\n" +
                "W\n" +
                "9:00 am - 9:50 am\n" +
                "SH 3707\n" +
                "13 / 13\n" +
                "BL ST 38B\n" +
                "W\n" +
                "5:00pm - 5:50pm\n" +
                "SH 3707\n" +
                "12 / 12\n" +
                "BL ST 38B\n" +
                "W\n" +
                "6:00pm - 6:50pm\n" +
                "SH 3707\n" +
                "12 / 13\n" +
                "BL ST 38B\n" +
                "R\n" +
                "8:00 am - 8:50 am\n" +
                "SH 1415\n" +
                "2 / 12\n" +
                "BL ST 38B\n" +
                "R\n" +
                "9:00 am - 9:50 am\n" +
                "SH 1415\n" +
                "13 / 13\n" +
                "BL ST 38BH\n" +
                "T\n" +
                "1:00pm - 1:50pm\n" +
                "GIRV 1106\n" +
                "0 / 7\n" +
                "BL ST 49A\n" +
                "T R\n" +
                "9:30 am - 10:45am\n" +
                "CHEM 1171\n" +
                "39 / 39\n" +
                "BL ST 49A\n" +
                "F\n" +
                "8:00 am - 8:50 am\n" +
                "HSSB 4080\n" +
                "4 / 9\n" +
                "BL ST 49A\n" +
                "F\n" +
                "9:00 am - 9:50 am\n" +
                "HSSB 4080\n" +
                "4 / 9\n" +
                "BL ST 49A\n" +
                "F\n" +
                "10:00am - 10:50am\n" +
                "HSSB 4080\n" +
                "9 / 9\n" +
                "BL ST 49A\n" +
                "M\n" +
                "8:00 am - 8:50 am\n" +
                "HSSB 4080\n" +
                "4 / 9\n" +
                "BL ST 49A\n" +
                "M\n" +
                "9:00 am - 9:50 am\n" +
                "HSSB 4080\n" +
                "9 / 9\n" +
                "BL ST 49A\n" +
                "M\n" +
                "10:00am - 10:50am\n" +
                "HSSB 4080\n" +
                "9 / 9\n" +
                "BL ST 99\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "0 / 5\n" +
                "BL ST 127\n" +
                "W F\n" +
                "9:30 am - 10:45am\n" +
                "GIRV 2128\n" +
                "45 / 65\n" +
                "BL ST 153\n" +
                "M W\n" +
                "11:00am - 12:15pm\n" +
                "GIRV 1004\n" +
                "112 / 200\n" +
                "BL ST 174\n" +
                "W F\n" +
                "2:00pm - 3:15pm\n" +
                "PHELP1425\n" +
                "28 / 65\n" +
                "BL ST 175\n" +
                "T R\n" +
                "9:30 am - 10:45am\n" +
                "NH 1006\n" +
                "8 / 120\n" +
                "BL ST 175\n" +
                "W\n" +
                "5:00pm - 7:50pm\n" +
                "BUCHN1940\n" +
                "8 / 120\n" +
                "BL ST 195A\n" +
                "R\n" +
                "9:00 am - 11:50am\n" +
                "SH 3631C\n" +
                "0 / 20\n" +
                "BL ST 197\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "0 / 12\n" +
                "BL ST 199\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "0 / 10\n" +
                "BL ST 199RA\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "0 / 5\n" +
                "CH E 5\n" +
                "M W\n" +
                "5:00pm - 6:15pm\n" +
                "LSB 1001\n" +
                "6 / 149\n" +
                "CH E 10\n" +
                "T R\n" +
                "12:30pm - 1:45pm\n" +
                "BRDA 1640\n" +
                "11 / 75\n" +
                "CH E 10\n" +
                "W\n" +
                "3:00pm - 3:50pm\n" +
                "WEBB 1100\n" +
                "11 / 75\n" +
                "CH E 99\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "0 / 25\n" +
                "CH E 119\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "1 / 5\n" +
                "CH E 120A\n" +
                "M W F\n" +
                "11:00am - 12:15pm\n" +
                "ENGR21519\n" +
                "17 / 70\n" +
                "CH E 128\n" +
                "M W F\n" +
                "2:00pm - 2:50pm\n" +
                "ENGR21519\n" +
                "24 / 70\n" +
                "CH E 132A\n" +
                "M W F\n" +
                "1:00pm - 1:50pm\n" +
                "ENGR21519\n" +
                "42 / 70\n" +
                "CH E 132A\n" +
                "F\n" +
                "9:00 am - 9:50 am\n" +
                "ESB 1003\n" +
                "12 / 30\n" +
                "CH E 132A\n" +
                "W\n" +
                "10:00am - 10:50am\n" +
                "ESB 1003\n" +
                "30 / 30\n" +
                "CH E 132B\n" +
                "T R\n" +
                "2:00pm - 3:15pm\n" +
                "ENGR21519\n" +
                "48 / 70\n" +
                "CH E 132B\n" +
                "M\n" +
                "5:00pm - 6:50pm\n" +
                "ESB 1003\n" +
                "35 / 35\n" +
                "CH E 132B\n" +
                "W\n" +
                "5:00pm - 6:50pm\n" +
                "ESB 1003\n" +
                "13 / 35\n" +
                "CH E 140B\n" +
                "T R\n" +
                "9:30 am - 10:45am\n" +
                "ENGR21519\n" +
                "33 / 70\n" +
                "CH E 152A\n" +
                "M W F\n" +
                "9:00 am - 9:50 am\n" +
                "ENGR21519\n" +
                "34 / 70\n" +
                "CH E 152A\n" +
                "M F\n" +
                "10:00am - 10:50am\n" +
                "ENGR21519\n" +
                "34 / 70\n" +
                "CH E 170\n" +
                "T R\n" +
                "11:00am - 12:15pm\n" +
                "ENGR21519\n" +
                "37 / 70\n" +
                "CH E 196\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "0 / 5\n" +
                "CH E 198\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "0 / 20\n" +
                "CHEM 1A\n" +
                "M W F\n" +
                "8:00 am - 8:50 am\n" +
                "CHEM 1179\n" +
                "0 / 25\n" +
                "CHEM 1A\n" +
                "M W F\n" +
                "9:00 am - 9:50 am\n" +
                "CHEM 1179\n" +
                "4 / 25\n" +
                "CHEM 1A\n" +
                "M W F\n" +
                "11:00am - 11:50am\n" +
                "CHEM 1179\n" +
                "4 / 25\n" +
                "CHEM 1A\n" +
                "M W F\n" +
                "12:00pm - 12:50pm\n" +
                "CHEM 1179\n" +
                "14 / 25\n" +
                "CHEM 1A\n" +
                "M W F\n" +
                "10:00am - 10:50am\n" +
                "BUCHN1910\n" +
                "11 / 25\n" +
                "CHEM 1A\n" +
                "M W F\n" +
                "10:00am - 10:50am\n" +
                "CHEM 1179\n" +
                "6 / 25\n" +
                "CHEM 1A\n" +
                "M W F\n" +
                "4:00pm - 4:50pm\n" +
                "BUCHN1910\n" +
                "2 / 25\n" +
                "CHEM 1AL\n" +
                "M\n" +
                "12:00pm - 2:30pm\n" +
                "PSB-N2637\n" +
                "2 / 2\n" +
                "CHEM 1AL\n" +
                "M\n" +
                "12:00pm - 2:30pm\n" +
                "PSB-N2638\n" +
                "2 / 2\n" +
                "CHEM 1AL\n" +
                "M\n" +
                "12:00pm - 2:30pm\n" +
                "PSB-N2653\n" +
                "0 / 2\n" +
                "CHEM 1AL\n" +
                "M\n" +
                "12:00pm - 2:30pm\n" +
                "PSB-N2654\n" +
                "1 / 2\n" +
                "CHEM 1AL\n" +
                "M\n" +
                "12:00pm - 2:30pm\n" +
                "PSB-N2664\n" +
                "0 / 2\n" +
                "CHEM 1AL\n" +
                "M\n" +
                "12:00pm - 2:30pm\n" +
                "PSB-N2666\n" +
                "2 / 2\n" +
                "CHEM 1AL\n" +
                "M\n" +
                "6:00pm - 8:30pm\n" +
                "PSB-N2637\n" +
                "2 / 2\n" +
                "CHEM 1AL\n" +
                "M\n" +
                "6:00pm - 8:30pm\n" +
                "PSB-N2654\n" +
                "2 / 2\n" +
                "CHEM 1AL\n" +
                "M\n" +
                "6:00pm - 8:30pm\n" +
                "PSB-N2664\n" +
                "1 / 2\n" +
                "CHEM 1AL\n" +
                "M\n" +
                "6:00pm - 8:30pm\n" +
                "PSB-N2638\n" +
                "1 / 2\n" +
                "CHEM 1AL\n" +
                "T\n" +
                "9:00 am - 11:30am\n" +
                "PSB-N2637\n" +
                "2 / 2\n" +
                "CHEM 1AL\n" +
                "T\n" +
                "9:00 am - 11:30am\n" +
                "PSB-N2638\n" +
                "2 / 2\n" +
                "CHEM 1AL\n" +
                "T\n" +
                "9:00 am - 11:30am\n" +
                "PSB-N2654\n" +
                "2 / 2\n" +
                "CHEM 1AL\n" +
                "T\n" +
                "9:00 am - 11:30am\n" +
                "PSB-N2664\n" +
                "2 / 2\n" +
                "CHEM 1AL\n" +
                "T\n" +
                "12:00pm - 2:30pm\n" +
                "PSB-N2637\n" +
                "1 / 2\n" +
                "CHEM 1AL\n" +
                "T\n" +
                "12:00pm - 2:30pm\n" +
                "PSB-N2638\n" +
                "1 / 2\n" +
                "CHEM 1AL\n" +
                "T\n" +
                "12:00pm - 2:30pm\n" +
                "PSB-N2654\n" +
                "1 / 2\n" +
                "CHEM 1AL\n" +
                "T\n" +
                "12:00pm - 2:30pm\n" +
                "PSB-N2664\n" +
                "0 / 2\n" +
                "CHEM 1AL\n" +
                "F\n" +
                "12:00pm - 2:30pm\n" +
                "PSB-N2664\n" +
                "0 / 2\n" +
                "CHEM 1AL\n" +
                "T\n" +
                "3:00pm - 5:30pm\n" +
                "PSB-N2637\n" +
                "2 / 2\n" +
                "CHEM 1AL\n" +
                "T\n" +
                "3:00pm - 5:30pm\n" +
                "PSB-N2638\n" +
                "2 / 2\n" +
                "CHEM 1AL\n" +
                "T\n" +
                "3:00pm - 5:30pm\n" +
                "PSB-N2654\n" +
                "2 / 2\n" +
                "CHEM 1AL\n" +
                "T\n" +
                "3:00pm - 5:30pm\n" +
                "PSB-N2664\n" +
                "0 / 2\n" +
                "CHEM 1AL\n" +
                "T\n" +
                "9:00pm - 11:30pm\n" +
                "PSB-N2653\n" +
                "0 / 2\n" +
                "CHEM 1AL\n" +
                "W\n" +
                "12:00pm - 2:30pm\n" +
                "PSB-N2637\n" +
                "2 / 2\n" +
                "CHEM 1AL\n" +
                "W\n" +
                "12:00pm - 2:30pm\n" +
                "PSB-N2638\n" +
                "0 / 2\n" +
                "CHEM 1AL\n" +
                "W\n" +
                "12:00pm - 2:30pm\n" +
                "PSB-N2653\n" +
                "1 / 2\n" +
                "CHEM 1AL\n" +
                "W\n" +
                "12:00pm - 2:30pm\n" +
                "PSB-N2654\n" +
                "0 / 2\n" +
                "CHEM 1AL\n" +
                "W\n" +
                "12:00pm - 2:30pm\n" +
                "PSB-N2664\n" +
                "0 / 2\n" +
                "CHEM 1AL\n" +
                "W\n" +
                "12:00pm - 2:30pm\n" +
                "PSB-N2666\n" +
                "0 / 2\n" +
                "CHEM 1AL\n" +
                "W\n" +
                "6:00pm - 8:30pm\n" +
                "PSB-N2637\n" +
                "0 / 2\n" +
                "CHEM 1AL\n" +
                "W\n" +
                "6:00pm - 8:30pm\n" +
                "PSB-N2664\n" +
                "0 / 2\n" +
                "CHEM 1AL\n" +
                "W\n" +
                "6:00pm - 8:30pm\n" +
                "PSB-N2654\n" +
                "1 / 2\n" +
                "CHEM 1AL\n" +
                "W\n" +
                "6:00pm - 8:30pm\n" +
                "PSB-N2638\n" +
                "0 / 2\n" +
                "CHEM 1AL\n" +
                "R\n" +
                "9:00 am - 11:30am\n" +
                "PSB-N2638\n" +
                "0 / 2\n" +
                "CHEM 1AL\n" +
                "R\n" +
                "9:00 am - 11:30am\n" +
                "PSB-N2654\n" +
                "0 / 2\n" +
                "CHEM 1AL\n" +
                "R\n" +
                "9:00 am - 11:30am\n" +
                "PSB-N2664\n" +
                "0 / 2\n" +
                "CHEM 1AL\n" +
                "R\n" +
                "9:00 am - 11:30am\n" +
                "PSB-N2637\n" +
                "0 / 2\n" +
                "CHEM 1AL\n" +
                "R\n" +
                "12:00pm - 2:30pm\n" +
                "PSB-N2637\n" +
                "1 / 2\n" +
                "CHEM 1AL\n" +
                "R\n" +
                "12:00pm - 2:30pm\n" +
                "PSB-N2638\n" +
                "0 / 2\n" +
                "CHEM 1AL\n" +
                "R\n" +
                "12:00pm - 2:30pm\n" +
                "PSB-N2654\n" +
                "0 / 2\n" +
                "CHEM 1AL\n" +
                "R\n" +
                "12:00pm - 2:30pm\n" +
                "PSB-N2664\n" +
                "1 / 2\n" +
                "CHEM 1AL\n" +
                "F\n" +
                "12:00pm - 2:30pm\n" +
                "PSB-N2666\n" +
                "1 / 2\n" +
                "CHEM 1AL\n" +
                "R\n" +
                "3:00pm - 5:30pm\n" +
                "PSB-N2637\n" +
                "2 / 2\n" +
                "CHEM 1AL\n" +
                "R\n" +
                "3:00pm - 5:30pm\n" +
                "PSB-N2638\n" +
                "2 / 2\n" +
                "CHEM 1AL\n" +
                "R\n" +
                "3:00pm - 5:30pm\n" +
                "PSB-N2654\n" +
                "2 / 2\n" +
                "CHEM 1AL\n" +
                "R\n" +
                "3:00pm - 5:30pm\n" +
                "PSB-N2664\n" +
                "2 / 2\n" +
                "CHEM 1AL\n" +
                "F\n" +
                "3:00pm - 5:30pm\n" +
                "PSB-N2664\n" +
                "0 / 2\n" +
                "CHEM 1AL\n" +
                "F\n" +
                "12:00pm - 2:30pm\n" +
                "PSB-N2637\n" +
                "0 / 2\n" +
                "CHEM 1AL\n" +
                "F\n" +
                "12:00pm - 2:30pm\n" +
                "PSB-N2638\n" +
                "0 / 2\n" +
                "CHEM 1AL\n" +
                "F\n" +
                "12:00pm - 2:30pm\n" +
                "PSB-N2654\n" +
                "0 / 2\n" +
                "CHEM 1AL\n" +
                "W\n" +
                "3:00pm - 5:30pm\n" +
                "PSB-N2653\n" +
                "0 / 2\n" +
                "CHEM 1AL\n" +
                "T\n" +
                "9:00 am - 11:30pm\n" +
                "PSB-N2666\n" +
                "0 / 2\n" +
                "CHEM 1AL\n" +
                "F\n" +
                "12:00pm - 2:30pm\n" +
                "PSB-N2653\n" +
                "1 / 2\n" +
                "CHEM 1AL\n" +
                "F\n" +
                "3:00pm - 5:30pm\n" +
                "PSB-N2653\n" +
                "0 / 2\n" +
                "CHEM 1AL\n" +
                "T\n" +
                "6:00pm - 8:30pm\n" +
                "PSB-N2637\n" +
                "2 / 2\n" +
                "CHEM 1AL\n" +
                "T\n" +
                "6:00pm - 8:30pm\n" +
                "PSB-N2638\n" +
                "0 / 2\n" +
                "CHEM 1AL\n" +
                "T\n" +
                "6:00pm - 8:30pm\n" +
                "PSB-N2654\n" +
                "0 / 2\n" +
                "CHEM 1AL\n" +
                "T\n" +
                "6:00pm - 8:30pm\n" +
                "PSB-N2664\n" +
                "0 / 2\n" +
                "CHEM 1AL\n" +
                "R\n" +
                "6:00pm - 8:30pm\n" +
                "PSB-N2637\n" +
                "0 / 2\n" +
                "CHEM 1AL\n" +
                "R\n" +
                "6:00pm - 8:30pm\n" +
                "PSB-N2638\n" +
                "0 / 2\n" +
                "CHEM 1AL\n" +
                "R\n" +
                "6:00pm - 8:30pm\n" +
                "PSB-N2654\n" +
                "0 / 2\n" +
                "CHEM 1AL\n" +
                "R\n" +
                "6:00pm - 8:30pm\n" +
                "PSB-N2664\n" +
                "0 / 2\n" +
                "CHEM 1AL\n" +
                "M\n" +
                "3:00pm - 5:30pm\n" +
                "PSB-N2653\n" +
                "0 / 2\n" +
                "CHEM 1AL\n" +
                "M\n" +
                "3:00pm - 5:30pm\n" +
                "PSB-N2637\n" +
                "0 / 2\n" +
                "CHEM 1AL\n" +
                "M\n" +
                "3:00pm - 5:30pm\n" +
                "PSB-N2638\n" +
                "1 / 2\n" +
                "CHEM 1AL\n" +
                "M\n" +
                "3:00pm - 5:30pm\n" +
                "PSB-N2654\n" +
                "0 / 2\n" +
                "CHEM 1AL\n" +
                "M\n" +
                "3:00pm - 5:30pm\n" +
                "PSB-N2664\n" +
                "0 / 2\n" +
                "CHEM 1AL\n" +
                "M\n" +
                "3:00pm - 5:30pm\n" +
                "PSB-N2666\n" +
                "0 / 2\n" +
                "CHEM 1AL\n" +
                "W\n" +
                "3:00pm - 5:30pm\n" +
                "PSB-N2637\n" +
                "2 / 2\n" +
                "CHEM 1AL\n" +
                "W\n" +
                "3:00pm - 5:30pm\n" +
                "PSB-N2638\n" +
                "0 / 2\n" +
                "CHEM 1AL\n" +
                "F\n" +
                "3:00pm - 5:30pm\n" +
                "PSB-N2637\n" +
                "0 / 2\n" +
                "CHEM 1AL\n" +
                "F\n" +
                "3:00pm - 5:30pm\n" +
                "PSB-N2638\n" +
                "0 / 2\n" +
                "CHEM 1AL\n" +
                "F\n" +
                "3:00pm - 5:30pm\n" +
                "PSB-N2654\n" +
                "0 / 2\n" +
                "CHEM 1AL\n" +
                "W\n" +
                "3:00pm - 5:30pm\n" +
                "PSB-N2664\n" +
                "0 / 2\n" +
                "CHEM 1AL\n" +
                "R\n" +
                "9:00pm - 11:30pm\n" +
                "PSB-N2666\n" +
                "0 / 2\n" +
                "CHEM 1AL\n" +
                "W\n" +
                "3:00pm - 5:30pm\n" +
                "PSB-N2666\n" +
                "0 / 24\n" +
                "CHEM 1AL\n" +
                "W\n" +
                "3:00pm - 5:30pm\n" +
                "PSB-N2654\n" +
                "2 / 24\n" +
                "CHEM 1C\n" +
                "M W F\n" +
                "4:00pm - 4:50pm\n" +
                "CHEM 1179\n" +
                "173 / 243\n" +
                "CHEM 1CL\n" +
                "M\n" +
                "6:00pm - 9:50pm\n" +
                "PSB-N2666\n" +
                "0 / 24\n" +
                "CHEM 1CL\n" +
                "T\n" +
                "1:00pm - 4:50pm\n" +
                "PSB-N2653\n" +
                "24 / 24\n" +
                "CHEM 1CL\n" +
                "T\n" +
                "6:00pm - 9:50pm\n" +
                "PSB-N2653\n" +
                "24 / 24\n" +
                "CHEM 1CL\n" +
                "W\n" +
                "6:00pm - 9:50pm\n" +
                "PSB-N2653\n" +
                "24 / 24\n" +
                "CHEM 1CL\n" +
                "R\n" +
                "1:00pm - 4:50pm\n" +
                "PSB-N2653\n" +
                "24 / 24\n" +
                "CHEM 1CL\n" +
                "R\n" +
                "6:00pm - 9:50pm\n" +
                "PSB-N2666\n" +
                "0 / 24\n" +
                "CHEM 1CL\n" +
                "R\n" +
                "1:00pm - 4:50pm\n" +
                "PSB-N2666\n" +
                "0 / 24\n" +
                "CHEM 1CL\n" +
                "R\n" +
                "6:00pm - 9:50pm\n" +
                "PSB-N2653\n" +
                "24 / 24\n" +
                "CHEM 1CL\n" +
                "M\n" +
                "6:00pm - 9:50pm\n" +
                "PSB-N2653\n" +
                "24 / 24\n" +
                "CHEM 1CL\n" +
                "T\n" +
                "6:00pm - 9:50pm\n" +
                "PSB-N2666\n" +
                "23 / 24\n" +
                "CHEM 1CL\n" +
                "W\n" +
                "6:00pm - 9:50pm\n" +
                "PSB-N2666\n" +
                "17 / 24\n" +
                "CHEM 1CL\n" +
                "T\n" +
                "1:00pm - 4:50pm\n" +
                "PSB-N2666\n" +
                "0 / 24\n" +
                "CHEM 2A\n" +
                "M W F\n" +
                "11:00am - 11:50am\n" +
                "NH 1105\n" +
                "0 / 60\n" +
                "CHEM 6AL\n" +
                "M W\n" +
                "5:00pm - 5:50pm\n" +
                "BRDA 1610\n" +
                "31 / 280\n" +
                "CHEM 6AL\n" +
                "T\n" +
                "1:00pm - 4:50pm\n" +
                "PSB-N1662\n" +
                "13 / 20\n" +
                "CHEM 6AL\n" +
                "T\n" +
                "1:00pm - 4:50pm\n" +
                "PSB-N1670\n" +
                "3 / 20\n" +
                "CHEM 6AL\n" +
                "T\n" +
                "6:00pm - 9:50pm\n" +
                "PSB-N1652\n" +
                "4 / 20\n" +
                "CHEM 6AL\n" +
                "T\n" +
                "6:00pm - 9:50pm\n" +
                "PSB-N1662\n" +
                "1 / 20\n" +
                "CHEM 6AL\n" +
                "T\n" +
                "6:00pm - 9:50pm\n" +
                "PSB-N1670\n" +
                "1 / 20\n" +
                "CHEM 6AL\n" +
                "R\n" +
                "1:00pm - 4:50pm\n" +
                "PSB-N1662\n" +
                "2 / 20\n" +
                "CHEM 6AL\n" +
                "R\n" +
                "1:00pm - 4:50pm\n" +
                "PSB-N1670\n" +
                "3 / 20\n" +
                "CHEM 6AL\n" +
                "R\n" +
                "6:00pm - 9:50pm\n" +
                "PSB-N1652\n" +
                "1 / 20\n" +
                "CHEM 6AL\n" +
                "R\n" +
                "6:00pm - 9:50pm\n" +
                "PSB-N1662\n" +
                "0 / 20\n" +
                "CHEM 6AL\n" +
                "R\n" +
                "6:00pm - 9:50pm\n" +
                "PSB-N1670\n" +
                "0 / 20\n" +
                "CHEM 6AL\n" +
                "F\n" +
                "1:00pm - 4:50pm\n" +
                "PSB-N1662\n" +
                "0 / 20\n" +
                "CHEM 6AL\n" +
                "F\n" +
                "1:00pm - 4:50pm\n" +
                "PSB-N1670\n" +
                "3 / 20\n" +
                "CHEM 6AL\n" +
                "F\n" +
                "5:00pm - 8:50pm\n" +
                "PSB-N1662\n" +
                "0 / 20\n" +
                "CHEM 6AL\n" +
                "F\n" +
                "5:00pm - 8:50pm\n" +
                "PSB-N1670\n" +
                "0 / 20\n" +
                "CHEM 6AL\n" +
                "F\n" +
                "8:00 am - 11:50am\n" +
                "PSB-N1662\n" +
                "0 / 20\n" +
                "CHEM 6AL\n" +
                "F\n" +
                "8:00 am - 11:50am\n" +
                "PSB-N1670\n" +
                "0 / 20\n" +
                "CHEM 6BH\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "0 / 10\n" +
                "CHEM 6BL\n" +
                "M W\n" +
                "1:00pm - 4:50pm\n" +
                "PSB-N1652\n" +
                "3 / 20\n" +
                "CHEM 6BL\n" +
                "M W\n" +
                "1:00pm - 4:50pm\n" +
                "PSB-N1662\n" +
                "4 / 20\n" +
                "CHEM 6BL\n" +
                "M W\n" +
                "1:00pm - 4:50pm\n" +
                "PSB-N1670\n" +
                "0 / 20\n" +
                "CHEM 6BL\n" +
                "M W\n" +
                "6:00pm - 9:50pm\n" +
                "PSB-N1652\n" +
                "6 / 20\n" +
                "CHEM 6BL\n" +
                "M W\n" +
                "6:00pm - 9:50pm\n" +
                "PSB-N1662\n" +
                "2 / 20\n" +
                "CHEM 6BL\n" +
                "M W\n" +
                "6:00pm - 9:50pm\n" +
                "PSB-N1670\n" +
                "0 / 20\n" +
                "CHEM 6BL\n" +
                "T R\n" +
                "8:00 am - 11:50am\n" +
                "PSB-N1670\n" +
                "0 / 20\n" +
                "CHEM 6BL\n" +
                "T R\n" +
                "1:00pm - 4:50pm\n" +
                "PSB-N1652\n" +
                "0 / 20\n" +
                "CHEM 6BL\n" +
                "T R\n" +
                "8:00 am - 11:50am\n" +
                "PSB-N1662\n" +
                "2 / 20\n" +
                "CHEM 6BL\n" +
                "T R\n" +
                "8:00 am - 11:50am\n" +
                "PSB-N1652\n" +
                "0 / 20\n" +
                "CHEM 6CH\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "0 / 10\n" +
                "CHEM 6CL\n" +
                "M W\n" +
                "6:00pm - 9:50pm\n" +
                "PSB-N1649\n" +
                "0 / 8\n" +
                "CHEM 6CL\n" +
                "T R\n" +
                "6:00pm - 9:50pm\n" +
                "PSB-N1649\n" +
                "8 / 8\n" +
                "CHEM 6CL\n" +
                "T R\n" +
                "2:00pm - 5:50pm\n" +
                "PSB-N1649\n" +
                "8 / 8\n" +
                "CHEM 6CL\n" +
                "T R\n" +
                "9:00 am - 12:50pm\n" +
                "PSB-N1649\n" +
                "0 / 8\n" +
                "CHEM 99\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "0 / 30\n" +
                "CHEM 109A\n" +
                "M W F\n" +
                "3:00pm - 3:50pm\n" +
                "CHEM 1179\n" +
                "268 / 285\n" +
                "CHEM 109A\n" +
                "M W F\n" +
                "12:00pm - 12:50pm\n" +
                "BUCHN1910\n" +
                "232 / 306\n" +
                "CHEM 109A\n" +
                "M W F\n" +
                "2:00pm - 2:50pm\n" +
                "CHEM 1179\n" +
                "325 / 325\n" +
                "CHEM 109AH\n" +
                "M W F\n" +
                "10:00am - 10:50am\n" +
                "CHEM 1171\n" +
                "32 / 100\n" +
                "CHEM 109B\n" +
                "M W F\n" +
                "12:00pm - 12:50pm\n" +
                "HFH 1104\n" +
                "148 / 188\n" +
                "CHEM 109C\n" +
                "M W F\n" +
                "8:00 am - 8:50 am\n" +
                "HFH 1104\n" +
                "147 / 175\n" +
                "CHEM 110L\n" +
                "M\n" +
                "3:30pm - 4:20pm\n" +
                "PSYCH1902\n" +
                "49 / 64\n" +
                "CHEM 110L\n" +
                "M W\n" +
                "6:00pm - 9:50pm\n" +
                "PSB-N2619\n" +
                "17 / 16\n" +
                "CHEM 110L\n" +
                "T R\n" +
                "2:00pm - 5:50pm\n" +
                "PSB-N2619\n" +
                "16 / 16\n" +
                "CHEM 110L\n" +
                "T R\n" +
                "6:00pm - 9:50pm\n" +
                "PSB-N2619\n" +
                "16 / 16\n" +
                "CHEM 110L\n" +
                "W F\n" +
                "2:00pm - 5:50pm\n" +
                "PSB-N2619\n" +
                "0 / 16\n" +
                "CHEM 112A\n" +
                "T R\n" +
                "9:30 am - 10:45am\n" +
                "BUCHN1940\n" +
                "90 / 104\n" +
                "CHEM 112A\n" +
                "F\n" +
                "1:00pm - 1:50pm\n" +
                "BUCHN1934\n" +
                "20 / 25\n" +
                "CHEM 112A\n" +
                "F\n" +
                "9:00 am - 9:50 am\n" +
                "PHELP1440\n" +
                "18 / 27\n" +
                "CHEM 112A\n" +
                "W\n" +
                "3:00pm - 3:50pm\n" +
                "PHELP1445\n" +
                "25 / 25\n" +
                "CHEM 112A\n" +
                "W\n" +
                "11:00am - 11:50am\n" +
                "PHELP3505\n" +
                "27 / 27\n" +
                "CHEM 113A\n" +
                "T R\n" +
                "12:30pm - 1:45pm\n" +
                "NH 1006\n" +
                "89 / 124\n" +
                "CHEM 113A\n" +
                "F\n" +
                "3:00pm - 3:50pm\n" +
                "PHELP1445\n" +
                "13 / 35\n" +
                "CHEM 113A\n" +
                "W\n" +
                "2:00pm - 2:50pm\n" +
                "GIRV 2119\n" +
                "30 / 27\n" +
                "CHEM 113A\n" +
                "F\n" +
                "3:00pm - 3:50pm\n" +
                "PHELP1440\n" +
                "11 / 27\n" +
                "CHEM 113A\n" +
                "W\n" +
                "2:00pm - 2:50pm\n" +
                "GIRV 1116\n" +
                "35 / 35\n" +
                "CHEM 115A\n" +
                "T R\n" +
                "9:30 am - 10:45am\n" +
                "PHELP2524\n" +
                "9 / 20\n" +
                "CHEM 115A\n" +
                "T\n" +
                "5:00pm - 5:50pm\n" +
                "BUCHN1934\n" +
                "9 / 20\n" +
                "CHEM 116CL\n" +
                "R\n" +
                "11:00am - 11:50am\n" +
                "PHELP1160\n" +
                "48 / 60\n" +
                "CHEM 116CL\n" +
                "M W\n" +
                "2:00pm - 5:50pm\n" +
                "PSB-N1641\n" +
                "12 / 12\n" +
                "CHEM 116CL\n" +
                "T R\n" +
                "2:00pm - 5:50pm\n" +
                "PSB-N1641\n" +
                "12 / 12\n" +
                "CHEM 116CL\n" +
                "T R\n" +
                "8:00 am - 11:50am\n" +
                "PSB-N1641\n" +
                "12 / 12\n" +
                "CHEM 116CL\n" +
                "T R\n" +
                "6:00pm - 9:50pm\n" +
                "PSB-N1641\n" +
                "12 / 12\n" +
                "CHEM 116CL\n" +
                "M W\n" +
                "6:00pm - 9:50pm\n" +
                "PSB-N1641\n" +
                "0 / 12\n" +
                "CHEM 116CL\n" +
                "W F\n" +
                "8:00 am - 11:50am\n" +
                "PSB-N1641\n" +
                "0 / 12\n" +
                "CHEM 124\n" +
                "T R\n" +
                "9:30 am - 10:45am\n" +
                "LSB 2101\n" +
                "0 / 5\n" +
                "CHEM 129\n" +
                "M W\n" +
                "11:00am - 11:50am\n" +
                "PHELP3519\n" +
                "0 / 20\n" +
                "CHEM 129\n" +
                "F\n" +
                "11:00am - 11:50am\n" +
                "PHELP1260\n" +
                "0 / 20\n" +
                "CHEM 142A\n" +
                "M W F\n" +
                "10:00am - 10:50am\n" +
                "NH 1006\n" +
                "104 / 110\n" +
                "CHEM 147\n" +
                "M W F\n" +
                "10:00am - 10:50am\n" +
                "WEBB 1100\n" +
                "47 / 75\n" +
                "CHEM 150\n" +
                "M W F\n" +
                "1:00pm - 1:50pm\n" +
                "BUCHN1940\n" +
                "99 / 144\n" +
                "CHEM 154A\n" +
                "M W F\n" +
                "9:00 am - 9:50 am\n" +
                "ELLSN2816\n" +
                "12 / 15\n" +
                "CHEM 163\n" +
                "T\n" +
                "11:00am - 12:15pm\n" +
                "PHELP1440\n" +
                "11 / 15\n" +
                "CHEM 173A\n" +
                "M W F\n" +
                "9:00 am - 9:50 am\n" +
                "WEBB 1100\n" +
                "60 / 60\n" +
                "CHEM 173A\n" +
                "M\n" +
                "11:00am - 11:50am\n" +
                "PHELP2536\n" +
                "20 / 20\n" +
                "CHEM 173A\n" +
                "M\n" +
                "11:00am - 11:50am\n" +
                "GIRV 1119\n" +
                "20 / 20\n" +
                "CHEM 173A\n" +
                "T\n" +
                "11:00am - 11:50am\n" +
                "PHELP2514\n" +
                "20 / 20\n" +
                "CHEM 173A\n" +
                "M W F\n" +
                "9:00 am - 9:50 am\n" +
                "PHELP1425\n" +
                "18 / 60\n" +
                "CHEM 173A\n" +
                "W\n" +
                "4:00pm - 4:50pm\n" +
                "PHELP2514\n" +
                "9 / 20\n" +
                "CHEM 173A\n" +
                "T\n" +
                "3:00pm - 3:50pm\n" +
                "PHELP2536\n" +
                "8 / 20\n" +
                "CHEM 173A\n" +
                "T\n" +
                "11:00am - 11:50am\n" +
                "PHELP3523\n" +
                "1 / 20\n" +
                "CHEM 176\n" +
                "T R\n" +
                "11:00am - 12:15pm\n" +
                "BUCHN1934\n" +
                "8 / 20\n" +
                "CHEM 183\n" +
                "F\n" +
                "11:00am - 11:50am\n" +
                "PHELP1508\n" +
                "8 / 40\n" +
                "CHEM 192\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "0 / 2\n" +
                "CHEM 193\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "0 / 40\n" +
                "CHEM 193\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "0 / 5\n" +
                "CHEM 199\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "0 / 40\n" +
                "CH ST 1A\n" +
                "T R\n" +
                "2:00pm - 3:15pm\n" +
                "IV THEA1\n" +
                "175 / 175\n" +
                "CH ST 1A\n" +
                "T\n" +
                "1:00pm - 1:50pm\n" +
                "PHELP2536\n" +
                "25 / 25\n" +
                "CH ST 1A\n" +
                "T\n" +
                "6:00pm - 6:50pm\n" +
                "GIRV 2119\n" +
                "3 / 25\n" +
                "CH ST 1A\n" +
                "R\n" +
                "4:00pm - 4:50pm\n" +
                "GIRV 2127\n" +
                "25 / 25\n" +
                "CH ST 1A\n" +
                "T\n" +
                "4:00pm - 4:50pm\n" +
                "HSSB 1237\n" +
                "24 / 25\n" +
                "CH ST 1A\n" +
                "R\n" +
                "5:00pm - 5:50pm\n" +
                "SH 1609\n" +
                "5 / 25\n" +
                "CH ST 1A\n" +
                "F\n" +
                "12:00pm - 12:50pm\n" +
                "GIRV 2119\n" +
                "11 / 25\n" +
                "CH ST 1A\n" +
                "F\n" +
                "10:00am - 10:50am\n" +
                "NH 1111\n" +
                "10 / 25\n" +
                "CH ST 1A\n" +
                "W\n" +
                "9:00 am - 9:50 am\n" +
                "PHELP2532\n" +
                "18 / 25\n" +
                "CH ST 1A\n" +
                "T\n" +
                "5:00pm - 5:50pm\n" +
                "ARTS 1353\n" +
                "4 / 25\n" +
                "CH ST 1A\n" +
                "W\n" +
                "6:00pm - 6:50pm\n" +
                "GIRV 2120\n" +
                "1 / 25\n" +
                "CH ST 1A\n" +
                "T\n" +
                "4:00pm - 4:50pm\n" +
                "HSSB 1236\n" +
                "13 / 25\n" +
                "CH ST 1A\n" +
                "F\n" +
                "9:00 am - 9:50 am\n" +
                "GIRV 2127\n" +
                "1 / 25\n" +
                "CH ST 1A\n" +
                "R\n" +
                "4:00pm - 4:50pm\n" +
                "GIRV 2120\n" +
                "7 / 25\n" +
                "CH ST 1A\n" +
                "W\n" +
                "5:00pm - 5:50pm\n" +
                "SH 1430\n" +
                "12 / 25\n" +
                "CH ST 1A\n" +
                "F\n" +
                "2:00pm - 2:50pm\n" +
                "HSSB 1224\n" +
                "0 / 25\n" +
                "CH ST 1A\n" +
                "W\n" +
                "6:00pm - 6:50pm\n" +
                "GIRV 2119\n" +
                "2 / 25\n" +
                "CH ST 1A\n" +
                "F\n" +
                "9:00 am - 9:50 am\n" +
                "NH 1111\n" +
                "3 / 25\n" +
                "CH ST 1A\n" +
                "F\n" +
                "2:00pm - 2:50pm\n" +
                "HSSB 1227\n" +
                "2 / 25\n" +
                "CH ST 1A\n" +
                "F\n" +
                "11:00am - 11:50am\n" +
                "NH 1109\n" +
                "4 / 25\n" +
                "CH ST 1A\n" +
                "T\n" +
                "4:00pm - 4:50pm\n" +
                "SH 1609\n" +
                "2 / 25\n" +
                "CH ST 1A\n" +
                "R\n" +
                "4:00pm - 4:50pm\n" +
                "GIRV 2119\n" +
                "3 / 25\n" +
                "CH ST 99\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "0 / 10\n" +
                "CH ST 103\n" +
                "M W\n" +
                "11:00am - 12:15pm\n" +
                "ARTS 1356\n" +
                "15 / 50\n" +
                "CH ST 103\n" +
                "T\n" +
                "2:00pm - 2:50pm\n" +
                "GIRV 2108\n" +
                "10 / 25\n" +
                "CH ST 103\n" +
                "T\n" +
                "3:00pm - 3:50pm\n" +
                "SH 1609\n" +
                "5 / 25\n" +
                "CH ST 112\n" +
                "W\n" +
                "3:00pm - 5:50pm\n" +
                "HSSB 4201\n" +
                "20 / 20\n" +
                "CH ST 114\n" +
                "T R\n" +
                "9:30 am - 10:45am\n" +
                "NH 1111\n" +
                "35 / 35\n" +
                "CH ST 154\n" +
                "T R\n" +
                "12:30pm - 1:45pm\n" +
                "PHELP1444\n" +
                "35 / 35\n" +
                "CH ST 158\n" +
                "T R\n" +
                "2:00pm - 3:15pm\n" +
                "HSSB 1223\n" +
                "17 / 20\n" +
                "CH ST 168A\n" +
                "M W\n" +
                "3:30pm - 4:45pm\n" +
                "ARTS 1349\n" +
                "10 / 10\n" +
                "CH ST 168F\n" +
                "T R\n" +
                "9:30 am - 10:45am\n" +
                "GIRV 2123\n" +
                "35 / 35\n" +
                "CH ST 168GQ\n" +
                "M\n" +
                "2:00pm - 4:45pm\n" +
                "SH 1609\n" +
                "5 / 10\n" +
                "CH ST 171\n" +
                "T R\n" +
                "12:30pm - 1:45pm\n" +
                "SH 1430\n" +
                "39 / 50\n" +
                "CH ST 171\n" +
                "W\n" +
                "3:00pm - 3:50pm\n" +
                "GIRV 2120\n" +
                "22 / 25\n" +
                "CH ST 171\n" +
                "W\n" +
                "5:00pm - 5:50pm\n" +
                "PHELP2532\n" +
                "17 / 25\n" +
                "CH ST 185\n" +
                "M\n" +
                "3:00pm - 5:50pm\n" +
                "SH 1623\n" +
                "7 / 40\n" +
                "CH ST 185L\n" +
                "M\n" +
                "6:00pm - 6:50pm\n" +
                "SH 1623\n" +
                "6 / 20\n" +
                "CH ST 195A\n" +
                "M\n" +
                "6:30pm - 9:20pm\n" +
                "ELLSN2816\n" +
                "0 / 15\n" +
                "CH ST 197HA\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "0 / 12\n" +
                "CH ST 199\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "0 / 20\n" +
                "CH ST 199RA\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "0 / 10\n" +
                "CHIN 1\n" +
                "MTWRF\n" +
                "2:00pm - 2:50pm\n" +
                "HSSB 4201\n" +
                "11 / 16\n" +
                "CHIN 1\n" +
                "MTWRF\n" +
                "10:00am - 10:50am\n" +
                "HSSB 1215\n" +
                "14 / 16\n" +
                "CHIN 1\n" +
                "MTWRF\n" +
                "11:00am - 11:50am\n" +
                "HSSB 1236\n" +
                "13 / 16\n" +
                "CHIN 1\n" +
                "MTWRF\n" +
                "12:00pm - 12:50pm\n" +
                "HSSB 1206\n" +
                "10 / 16\n" +
                "CHIN 1\n" +
                "MTWRF\n" +
                "1:00pm - 1:50pm\n" +
                "HSSB 1214\n" +
                "9 / 16\n" +
                "CHIN 1\n" +
                "MTWRF\n" +
                "9:00 am - 9:50 am\n" +
                "HSSB 1206\n" +
                "3 / 14\n" +
                "CHIN 1NH\n" +
                "MTWR\n" +
                "11:00am - 11:50am\n" +
                "HSSB 1214\n" +
                "4 / 20\n" +
                "CHIN 1NH\n" +
                "MTWR\n" +
                "1:00pm - 1:50pm\n" +
                "HSSB 2201\n" +
                "6 / 20\n" +
                "CHIN 4\n" +
                "MTWRF\n" +
                "10:00am - 10:50am\n" +
                "HSSB 1227\n" +
                "20 / 20\n" +
                "CHIN 4\n" +
                "MTWRF\n" +
                "9:00 am - 9:50 am\n" +
                "HSSB 1227\n" +
                "8 / 20\n" +
                "CHIN 4NH\n" +
                "MTWR\n" +
                "12:00pm - 12:50pm\n" +
                "HSSB 1214\n" +
                "0 / 20\n" +
                "CHIN 35\n" +
                "M W\n" +
                "12:30pm - 1:45pm\n" +
                "TD-W 2600\n" +
                "8 / 60\n" +
                "CHIN 35\n" +
                "T\n" +
                "4:00pm - 4:50pm\n" +
                "HSSB 4080\n" +
                "5 / 20\n" +
                "CHIN 35\n" +
                "T\n" +
                "5:00pm - 5:50pm\n" +
                "PHELP2536\n" +
                "1 / 20\n" +
                "CHIN 35\n" +
                "R\n" +
                "9:00 am - 9:50 am\n" +
                "HSSB 1223\n" +
                "2 / 20\n" +
                "CHIN 122A\n" +
                "MTWR\n" +
                "10:00am - 10:50am\n" +
                "ELLSN2816\n" +
                "13 / 18\n" +
                "CHIN 126A\n" +
                "M W\n" +
                "2:00pm - 3:15pm\n" +
                "GIRV 2108\n" +
                "5 / 35\n" +
                "CHIN 127A\n" +
                "M W\n" +
                "12:30pm - 1:45pm\n" +
                "HSSB 2252\n" +
                "2 / 12\n" +
                "CHIN 132A\n" +
                "M W\n" +
                "9:30 am - 10:45am\n" +
                "GIRV 1112\n" +
                "0 / 35\n" +
                "CHIN 136\n" +
                "M W\n" +
                "9:30 am - 10:45am\n" +
                "PHELP3523\n" +
                "17 / 30\n" +
                "CHIN 145\n" +
                "T\n" +
                "4:00pm - 7:00pm\n" +
                "HSSB 2252\n" +
                "7 / 15\n" +
                "CHIN 185A\n" +
                "M W\n" +
                "5:00pm - 6:15pm\n" +
                "PHELP2524\n" +
                "21 / 21\n" +
                "CHIN 197\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "0 / 2\n" +
                "CHIN 198\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "0 / 2\n" +
                "CHIN 199\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "0 / 5\n" +
                "CLASS 20A\n" +
                "M W F\n" +
                "3:00pm - 3:50pm\n" +
                "IV THEA2\n" +
                "90 / 90\n" +
                "CLASS 36\n" +
                "M W F\n" +
                "10:00am - 10:50am\n" +
                "BUCHN1930\n" +
                "80 / 80\n" +
                "CLASS 40\n" +
                "M W F\n" +
                "11:00am - 11:50am\n" +
                "CAMPBHALL\n" +
                "346 / 405\n" +
                "CLASS 40\n" +
                "M\n" +
                "1:00pm - 1:50pm\n" +
                "PHELP1444\n" +
                "28 / 28\n" +
                "CLASS 40\n" +
                "M\n" +
                "8:00 am - 8:50 am\n" +
                "PHELP1445\n" +
                "4 / 28\n" +
                "CLASS 40\n" +
                "W\n" +
                "8:00 am - 8:50 am\n" +
                "PHELP1440\n" +
                "7 / 27\n" +
                "CLASS 40\n" +
                "T\n" +
                "8:00 am - 8:50 am\n" +
                "PHELP1440\n" +
                "7 / 27\n" +
                "CLASS 40\n" +
                "T\n" +
                "6:00pm - 6:50pm\n" +
                "PHELP1440\n" +
                "9 / 28\n" +
                "CLASS 40\n" +
                "M\n" +
                "5:00pm - 5:50pm\n" +
                "PHELP1444\n" +
                "6 / 27\n" +
                "CLASS 40\n" +
                "T\n" +
                "5:00pm - 5:50pm\n" +
                "PHELP1448\n" +
                "10 / 27\n" +
                "CLASS 40\n" +
                "T\n" +
                "4:00pm - 4:50pm\n" +
                "PHELP1448\n" +
                "23 / 27\n" +
                "CLASS 40\n" +
                "W\n" +
                "1:00pm - 1:50pm\n" +
                "NH 1105\n" +
                "27 / 27\n" +
                "CLASS 40\n" +
                "M\n" +
                "8:00 am - 8:50 am\n" +
                "PHELP1440\n" +
                "0 / 27\n" +
                "CLASS 40\n" +
                "W\n" +
                "2:00pm - 2:50pm\n" +
                "PHELP2524\n" +
                "28 / 28\n" +
                "CLASS 40\n" +
                "M\n" +
                "4:00pm - 4:50pm\n" +
                "PHELP1445\n" +
                "20 / 27\n" +
                "CLASS 40\n" +
                "T\n" +
                "3:00pm - 3:50pm\n" +
                "GIRV 2127\n" +
                "28 / 28\n" +
                "CLASS 40\n" +
                "W\n" +
                "1:00pm - 1:50pm\n" +
                "PHELP1444\n" +
                "27 / 27\n" +
                "CLASS 40\n" +
                "T\n" +
                "2:00pm - 2:50pm\n" +
                "GIRV 2127\n" +
                "25 / 25\n" +
                "CLASS 40\n" +
                "W\n" +
                "2:00pm - 2:50pm\n" +
                "GIRV 2120\n" +
                "18 / 27\n" +
                "CLASS 40\n" +
                "T\n" +
                "4:00pm - 4:50pm\n" +
                "HSSB 1223\n" +
                "8 / 26\n" +
                "CLASS 40\n" +
                "M\n" +
                "5:00pm - 5:50pm\n" +
                "GIRV 2120\n" +
                "4 / 28\n" +
                "CLASS 40\n" +
                "R\n" +
                "4:00pm - 4:50pm\n" +
                "HSSB 1210\n" +
                "10 / 26\n" +
                "CLASS 40\n" +
                "T\n" +
                "4:00pm - 4:50pm\n" +
                "PHELP1444\n" +
                "8 / 27\n" +
                "CLASS 40\n" +
                "T\n" +
                "5:00pm - 5:50pm\n" +
                "PHELP3519\n" +
                "5 / 28\n" +
                "CLASS 40\n" +
                "M\n" +
                "6:00pm - 6:50pm\n" +
                "PHELP1444\n" +
                "5 / 27\n" +
                "CLASS 40\n" +
                "R\n" +
                "1:00pm - 1:50pm\n" +
                "PHELP2532\n" +
                "27 / 27\n" +
                "CLASS 40\n" +
                "R\n" +
                "8:00 am - 8:50 am\n" +
                "GIRV 2119\n" +
                "3 / 27\n" +
                "CLASS 40\n" +
                "R\n" +
                "9:00 am - 9:50 am\n" +
                "PHELP1440\n" +
                "8 / 27\n" +
                "CLASS 40\n" +
                "M\n" +
                "8:00 am - 8:50 am\n" +
                "PHELP1444\n" +
                "1 / 28\n" +
                "CLASS 40H\n" +
                "M\n" +
                "1:00pm - 1:50pm\n" +
                "HSSB 3201\n" +
                "0 / 24\n" +
                "CLASS 99\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "0 / 5\n" +
                "CLASS 102\n" +
                "T R\n" +
                "9:30 am - 10:45am\n" +
                "HSSB 1173\n" +
                "65 / 65\n" +
                "CLASS 185\n" +
                "T R\n" +
                "12:30pm - 1:45pm\n" +
                "HSSB 4201\n" +
                "7 / 24\n" +
                "CLASS 198\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "0 / 5\n" +
                "CLASS 199\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "0 / 6\n" +
                "CLASS 199RA\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "0 / 10\n" +
                "COMM 1\n" +
                "T R\n" +
                "8:00 am - 9:15 am\n" +
                "IV THEA1\n" +
                "185 / 225\n" +
                "COMM 1\n" +
                "F\n" +
                "8:00 am - 8:50 am\n" +
                "GIRV 2129\n" +
                "16 / 25\n" +
                "COMM 1\n" +
                "F\n" +
                "8:00 am - 8:50 am\n" +
                "PHELP1448\n" +
                "1 / 25\n" +
                "COMM 1\n" +
                "F\n" +
                "8:00 am - 8:50 am\n" +
                "PHELP1445\n" +
                "1 / 25\n" +
                "COMM 1\n" +
                "F\n" +
                "9:00 am - 9:50 am\n" +
                "ARTS 1353\n" +
                "12 / 25\n" +
                "COMM 1\n" +
                "F\n" +
                "9:00 am - 9:50 am\n" +
                "PHELP2516\n" +
                "6 / 25\n" +
                "COMM 1\n" +
                "F\n" +
                "9:00 am - 9:50 am\n" +
                "TD-W 2600\n" +
                "5 / 25\n" +
                "COMM 1\n" +
                "F\n" +
                "9:00 am - 9:50 am\n" +
                "ARTS 1356\n" +
                "5 / 25\n" +
                "COMM 1\n" +
                "F\n" +
                "10:00am - 10:50am\n" +
                "ARTS 1354\n" +
                "25 / 25\n" +
                "COMM 1\n" +
                "F\n" +
                "10:00am - 10:50am\n" +
                "ARTS 1349\n" +
                "8 / 25\n" +
                "COMM 1\n" +
                "F\n" +
                "10:00am - 10:50am\n" +
                "TD-W 2600\n" +
                "21 / 25\n" +
                "COMM 1\n" +
                "F\n" +
                "10:00am - 10:50am\n" +
                "ARTS 1356\n" +
                "3 / 25\n" +
                "COMM 1\n" +
                "F\n" +
                "11:00am - 11:50am\n" +
                "PHELP3505\n" +
                "12 / 25\n" +
                "COMM 1\n" +
                "F\n" +
                "11:00am - 11:50am\n" +
                "PHELP2516\n" +
                "11 / 25\n" +
                "COMM 1\n" +
                "F\n" +
                "11:00am - 11:50am\n" +
                "TD-W 2600\n" +
                "14 / 25\n" +
                "COMM 1\n" +
                "F\n" +
                "11:00am - 11:50am\n" +
                "ARTS 1349\n" +
                "4 / 25\n" +
                "COMM 1\n" +
                "F\n" +
                "12:00pm - 12:50pm\n" +
                "PHELP3523\n" +
                "6 / 25\n" +
                "COMM 1\n" +
                "F\n" +
                "12:00pm - 12:50pm\n" +
                "GIRV 2123\n" +
                "7 / 25\n" +
                "COMM 1\n" +
                "F\n" +
                "12:00pm - 12:50pm\n" +
                "GIRV 2120\n" +
                "5 / 25\n" +
                "COMM 1\n" +
                "F\n" +
                "1:00pm - 1:50pm\n" +
                "GIRV 2116\n" +
                "18 / 25\n" +
                "COMM 1\n" +
                "F\n" +
                "1:00pm - 1:50pm\n" +
                "GIRV 2115\n" +
                "2 / 25\n" +
                "COMM 1\n" +
                "F\n" +
                "1:00pm - 1:50pm\n" +
                "PHELP1448\n" +
                "3 / 25\n" +
                "COMM 88\n" +
                "T R\n" +
                "3:30pm - 4:45pm\n" +
                "NH 1006\n" +
                "100 / 100\n" +
                "COMM 88\n" +
                "R\n" +
                "5:00pm - 6:50pm\n" +
                "SSMS 1009\n" +
                "20 / 20\n" +
                "COMM 88\n" +
                "R\n" +
                "5:00pm - 6:50pm\n" +
                "GIRV 2128\n" +
                "20 / 20\n" +
                "COMM 88\n" +
                "F\n" +
                "9:00 am - 10:50am\n" +
                "PHELP2532\n" +
                "20 / 20\n" +
                "COMM 88\n" +
                "F\n" +
                "11:00am - 12:50pm\n" +
                "PHELP2532\n" +
                "20 / 20\n" +
                "COMM 88\n" +
                "F\n" +
                "12:00pm - 1:50pm\n" +
                "PHELP1444\n" +
                "10 / 20\n" +
                "COMM 88\n" +
                "F\n" +
                "1:00pm - 2:50pm\n" +
                "HSSB 1228\n" +
                "10 / 20\n" +
                "COMM 89\n" +
                "T R\n" +
                "12:30pm - 1:45pm\n" +
                "BUCHN1910\n" +
                "163 / 210\n" +
                "COMM 89\n" +
                "R\n" +
                "5:00pm - 6:50pm\n" +
                "PHELP1448\n" +
                "20 / 20\n" +
                "COMM 89\n" +
                "R\n" +
                "5:00pm - 6:50pm\n" +
                "PHELP1445\n" +
                "20 / 20\n" +
                "COMM 89\n" +
                "F\n" +
                "8:00 am - 9:50 am\n" +
                "GIRV 1119\n" +
                "9 / 20\n" +
                "COMM 89\n" +
                "F\n" +
                "8:00 am - 9:50 am\n" +
                "GIRV 2123\n" +
                "3 / 20\n" +
                "COMM 89\n" +
                "F\n" +
                "8:00 am - 9:50 am\n" +
                "GIRV 2115\n" +
                "0 / 20\n" +
                "COMM 89\n" +
                "F\n" +
                "9:00 am - 10:50am\n" +
                "GIRV 2119\n" +
                "20 / 20\n" +
                "COMM 89\n" +
                "F\n" +
                "9:00 am - 10:50am\n" +
                "GIRV 2112\n" +
                "3 / 20\n" +
                "COMM 89\n" +
                "F\n" +
                "10:00am - 11:50am\n" +
                "GIRV 2120\n" +
                "20 / 20\n" +
                "COMM 89\n" +
                "F\n" +
                "10:00am - 11:50am\n" +
                "GIRV 2116\n" +
                "9 / 20\n" +
                "COMM 89\n" +
                "F\n" +
                "10:00am - 11:50am\n" +
                "GIRV 2115\n" +
                "11 / 20\n" +
                "COMM 89\n" +
                "F\n" +
                "11:00am - 12:50pm\n" +
                "GIRV 2127\n" +
                "20 / 20\n" +
                "COMM 89\n" +
                "F\n" +
                "11:00am - 12:50pm\n" +
                "GIRV 1119\n" +
                "14 / 20\n" +
                "COMM 89\n" +
                "F\n" +
                "12:00pm - 1:50pm\n" +
                "PHELP2514\n" +
                "2 / 20\n" +
                "COMM 89\n" +
                "F\n" +
                "12:00pm - 1:50pm\n" +
                "PHELP1445\n" +
                "0 / 20\n" +
                "COMM 89\n" +
                "F\n" +
                "12:00pm - 1:50pm\n" +
                "GIRV 1108\n" +
                "12 / 20\n" +
                "COMM 101\n" +
                "M W\n" +
                "3:30pm - 4:45pm\n" +
                "SH 1431\n" +
                "11 / 60\n" +
                "COMM 103\n" +
                "M W\n" +
                "11:00am - 12:15pm\n" +
                "SSMS 1009\n" +
                "70 / 70\n" +
                "COMM 104\n" +
                "M\n" +
                "3:30pm - 6:20pm\n" +
                "SSMS 1009\n" +
                "11 / 70\n" +
                "COMM 109\n" +
                "T\n" +
                "3:30pm - 6:20pm\n" +
                "PHELP3515\n" +
                "5 / 62\n" +
                "COMM 111\n" +
                "T R\n" +
                "11:00am - 12:15pm\n" +
                "HSSB 1173\n" +
                "40 / 70\n" +
                "COMM 113\n" +
                "T R\n" +
                "8:00 am - 9:15 am\n" +
                "SSMS 1009\n" +
                "6 / 40\n" +
                "COMM 113\n" +
                "T R\n" +
                "9:30 am - 10:45am\n" +
                "SSMS 1009\n" +
                "8 / 40\n" +
                "COMM 117\n" +
                "T R\n" +
                "9:30 am - 10:45am\n" +
                "HSSB 1174\n" +
                "61 / 73\n" +
                "COMM 123\n" +
                "T R\n" +
                "11:00am - 12:15pm\n" +
                "SH 1431\n" +
                "64 / 73\n" +
                "COMM 126\n" +
                "T R\n" +
                "3:30pm - 4:45pm\n" +
                "SH 1431\n" +
                "73 / 73\n" +
                "COMM 130\n" +
                "M W\n" +
                "5:00pm - 6:15pm\n" +
                "HSSB 1173\n" +
                "14 / 60\n" +
                "COMM 131\n" +
                "M W\n" +
                "11:00am - 12:15pm\n" +
                "BUCHN1930\n" +
                "36 / 90\n" +
                "COMM 132\n" +
                "T R\n" +
                "11:00am - 12:15pm\n" +
                "SSMS 1009\n" +
                "3 / 65\n" +
                "COMM 139\n" +
                "T R\n" +
                "12:30pm - 1:45pm\n" +
                "WEBB 1100\n" +
                "29 / 75\n" +
                "COMM 151\n" +
                "M W\n" +
                "8:00 am - 9:15 am\n" +
                "PHELP3515\n" +
                "0 / 0\n" +
                "COMM 160CP\n" +
                "T R\n" +
                "2:00pm - 3:15pm\n" +
                "SSMS 1009\n" +
                "25 / 70\n" +
                "COMM 163\n" +
                "M W\n" +
                "2:00pm - 3:15pm\n" +
                "PHELP1260\n" +
                "39 / 73\n" +
                "COMM 168\n" +
                "M W\n" +
                "9:30 am - 10:45am\n" +
                "SSMS 1009\n" +
                "70 / 70\n" +
                "COMM 169\n" +
                "M W\n" +
                "12:30pm - 1:45pm\n" +
                "SSMS 1009\n" +
                "65 / 65\n" +
                "COMM 176\n" +
                "M W\n" +
                "12:30pm - 1:45pm\n" +
                "PHELP3515\n" +
                "7 / 73\n" +
                "COMM 180\n" +
                "T\n" +
                "5:00pm - 7:50pm\n" +
                "SSMS 1009\n" +
                "0 / 15\n" +
                "COMM 182\n" +
                "T R\n" +
                "12:30pm - 1:45pm\n" +
                "SSMS 1009\n" +
                "7 / 40\n" +
                "COMM 199\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "0 / 5\n" +
                "COMM 199RA\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "0 / 60\n" +
                "C LIT 30A\n" +
                "T R\n" +
                "11:00am - 12:15pm\n" +
                "CHEM 1179\n" +
                "200 / 200\n" +
                "C LIT 30A\n" +
                "R\n" +
                "4:00pm - 4:50pm\n" +
                "HSSB 1214\n" +
                "25 / 25\n" +
                "C LIT 30A\n" +
                "R\n" +
                "4:00pm - 4:50pm\n" +
                "PHELP3523\n" +
                "25 / 25\n" +
                "C LIT 30A\n" +
                "R\n" +
                "5:00pm - 5:50pm\n" +
                "PHELP3523\n" +
                "25 / 25\n" +
                "C LIT 30A\n" +
                "R\n" +
                "5:00pm - 5:50pm\n" +
                "PHELP3519\n" +
                "25 / 25\n" +
                "C LIT 30A\n" +
                "F\n" +
                "9:00 am - 9:50 am\n" +
                "PHELP3505\n" +
                "17 / 25\n" +
                "C LIT 30A\n" +
                "F\n" +
                "10:00am - 10:50am\n" +
                "PHELP1425\n" +
                "18 / 25\n" +
                "C LIT 30A\n" +
                "F\n" +
                "10:00am - 10:50am\n" +
                "PHELP3505\n" +
                "3 / 25\n" +
                "C LIT 30A\n" +
                "F\n" +
                "12:00pm - 12:50pm\n" +
                "NH 1109\n" +
                "14 / 25\n" +
                "C LIT 30A\n" +
                "F\n" +
                "12:00pm - 12:50pm\n" +
                "HSSB 2251\n" +
                "16 / 25\n" +
                "C LIT 30A\n" +
                "R\n" +
                "5:00pm - 5:50pm\n" +
                "PHELP1508\n" +
                "23 / 25\n" +
                "C LIT 30A\n" +
                "R\n" +
                "6:00pm - 6:50pm\n" +
                "PHELP1444\n" +
                "6 / 25\n" +
                "C LIT 30A\n" +
                "R\n" +
                "6:00pm - 6:50pm\n" +
                "PHELP1440\n" +
                "3 / 25\n" +
                "C LIT 30H\n" +
                "R\n" +
                "9:30 am - 10:45am\n" +
                "GIRV 2110\n" +
                "7 / 10\n" +
                "C LIT 31\n" +
                "T R\n" +
                "12:30pm - 1:45pm\n" +
                "PSYCH1924\n" +
                "70 / 70\n" +
                "C LIT 31\n" +
                "T\n" +
                "3:00pm - 3:50pm\n" +
                "PHELP3523\n" +
                "25 / 25\n" +
                "C LIT 31\n" +
                "T\n" +
                "4:00pm - 4:50pm\n" +
                "HSSB 2251\n" +
                "25 / 25\n" +
                "C LIT 31\n" +
                "T\n" +
                "5:00pm - 5:50pm\n" +
                "PHELP3523\n" +
                "17 / 25\n" +
                "C LIT 31\n" +
                "T\n" +
                "6:00pm - 6:50pm\n" +
                "PHELP1444\n" +
                "3 / 25\n" +
                "C LIT 33\n" +
                "T R\n" +
                "8:00 am - 9:15 am\n" +
                "GIRV 1004\n" +
                "40 / 40\n" +
                "C LIT 33\n" +
                "T\n" +
                "12:00pm - 12:50pm\n" +
                "SH 3711\n" +
                "5 / 10\n" +
                "C LIT 33\n" +
                "T\n" +
                "3:00pm - 3:50pm\n" +
                "PHELP2532\n" +
                "7 / 10\n" +
                "C LIT 33\n" +
                "W\n" +
                "8:00 am - 8:50 am\n" +
                "HSSB 1232\n" +
                "6 / 10\n" +
                "C LIT 33\n" +
                "W\n" +
                "2:00pm - 2:50pm\n" +
                "SH 3711\n" +
                "10 / 10\n" +
                "C LIT 33\n" +
                "W\n" +
                "3:00pm - 3:50pm\n" +
                "SH 3711\n" +
                "6 / 10\n" +
                "C LIT 33\n" +
                "W\n" +
                "4:00pm - 4:50pm\n" +
                "SH 3711\n" +
                "6 / 10\n" +
                "C LIT 100\n" +
                "T R\n" +
                "12:30pm - 1:45pm\n" +
                "ARTS 1356\n" +
                "32 / 50\n" +
                "C LIT 107\n" +
                "T R\n" +
                "9:30 am - 10:45am\n" +
                "GIRV 1115\n" +
                "20 / 20\n" +
                "C LIT 153\n" +
                "M W\n" +
                "9:30 am - 10:45am\n" +
                "GIRV 2129\n" +
                "27 / 40\n" +
                "C LIT 154\n" +
                "M W\n" +
                "2:00pm - 3:15pm\n" +
                "GIRV 2115\n" +
                "20 / 20\n" +
                "C LIT 162\n" +
                "W\n" +
                "5:00pm - 7:50pm\n" +
                "WEBB 1100\n" +
                "32 / 40\n" +
                "C LIT 186FF\n" +
                "W\n" +
                "3:00pm - 5:50pm\n" +
                "PHELP5316\n" +
                "14 / 16\n" +
                "C LIT 196H\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "0 / 0\n" +
                "C LIT 198H\n" +
                "R\n" +
                "1:00pm - 3:50pm\n" +
                "PHELP6206C\n" +
                "0 / 0\n" +
                "C LIT 199\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "0 / 10\n" +
                "CMPSC 8\n" +
                "T R\n" +
                "5:00pm - 6:15pm\n" +
                "BUCHN1920\n" +
                "21 / 125\n" +
                "CMPSC 8\n" +
                "W\n" +
                "8:00 am - 8:50 am\n" +
                "PHELP3525\n" +
                "0 / 32\n" +
                "CMPSC 8\n" +
                "W\n" +
                "9:00 am - 9:50 am\n" +
                "PHELP3525\n" +
                "7 / 31\n" +
                "CMPSC 8\n" +
                "W\n" +
                "10:00am - 10:50am\n" +
                "PHELP3525\n" +
                "8 / 31\n" +
                "CMPSC 8\n" +
                "W\n" +
                "11:00am - 11:50am\n" +
                "PHELP3525\n" +
                "6 / 31\n" +
                "CMPSC 8\n" +
                "M W\n" +
                "5:00pm - 6:15pm\n" +
                "NH 1006\n" +
                "20 / 125\n" +
                "CMPSC 8\n" +
                "F\n" +
                "11:00am - 11:50am\n" +
                "PHELP3525\n" +
                "11 / 32\n" +
                "CMPSC 8\n" +
                "F\n" +
                "12:00pm - 12:50pm\n" +
                "PHELP3525\n" +
                "8 / 31\n" +
                "CMPSC 8\n" +
                "F\n" +
                "1:00pm - 1:50pm\n" +
                "PHELP3525\n" +
                "1 / 31\n" +
                "CMPSC 8\n" +
                "F\n" +
                "2:00pm - 2:50pm\n" +
                "PHELP3525\n" +
                "0 / 31\n" +
                "CMPSC 16\n" +
                "T R\n" +
                "2:00pm - 3:15pm\n" +
                "BUCHN1910\n" +
                "47 / 200\n" +
                "CMPSC 16\n" +
                "M\n" +
                "8:00 am - 8:50 am\n" +
                "PHELP3525\n" +
                "2 / 34\n" +
                "CMPSC 16\n" +
                "M\n" +
                "9:00 am - 9:50 am\n" +
                "PHELP3525\n" +
                "4 / 34\n" +
                "CMPSC 16\n" +
                "M\n" +
                "10:00am - 10:50am\n" +
                "PHELP3525\n" +
                "11 / 33\n" +
                "CMPSC 16\n" +
                "M\n" +
                "11:00am - 11:50am\n" +
                "PHELP3525\n" +
                "11 / 33\n" +
                "CMPSC 16\n" +
                "M\n" +
                "12:00pm - 12:50pm\n" +
                "PHELP3525\n" +
                "19 / 33\n" +
                "CMPSC 24\n" +
                "M W\n" +
                "12:30pm - 1:45pm\n" +
                "PSYCH1924\n" +
                "65 / 124\n" +
                "CMPSC 24\n" +
                "T\n" +
                "8:00 am - 8:50 am\n" +
                "PHELP3525\n" +
                "0 / 32\n" +
                "CMPSC 24\n" +
                "T\n" +
                "9:00 am - 9:50 am\n" +
                "PHELP3525\n" +
                "4 / 31\n" +
                "CMPSC 24\n" +
                "T\n" +
                "10:00am - 10:50am\n" +
                "PHELP3525\n" +
                "30 / 30\n" +
                "CMPSC 24\n" +
                "T\n" +
                "11:00am - 11:50am\n" +
                "PHELP3525\n" +
                "31 / 31\n" +
                "CMPSC 32\n" +
                "M W\n" +
                "9:30 am - 10:45am\n" +
                "PSYCH1924\n" +
                "30 / 124\n" +
                "CMPSC 32\n" +
                "T\n" +
                "12:00pm - 12:50pm\n" +
                "PHELP3525\n" +
                "7 / 32\n" +
                "CMPSC 32\n" +
                "T\n" +
                "1:00pm - 1:50pm\n" +
                "PHELP3525\n" +
                "6 / 31\n" +
                "CMPSC 32\n" +
                "T\n" +
                "2:00pm - 2:50pm\n" +
                "PHELP3525\n" +
                "7 / 30\n" +
                "CMPSC 32\n" +
                "T\n" +
                "3:00pm - 3:50pm\n" +
                "PHELP3525\n" +
                "10 / 31\n" +
                "CMPSC 40\n" +
                "M W\n" +
                "2:00pm - 3:15pm\n" +
                "CHEM 1171\n" +
                "38 / 100\n" +
                "CMPSC 40\n" +
                "T\n" +
                "4:00pm - 4:50pm\n" +
                "PHELP2524\n" +
                "26 / 50\n" +
                "CMPSC 40\n" +
                "T\n" +
                "5:00pm - 5:50pm\n" +
                "PHELP2524\n" +
                "12 / 50\n" +
                "CMPSC 56\n" +
                "M W\n" +
                "12:30pm - 1:45pm\n" +
                "HSSB 1174\n" +
                "59 / 85\n" +
                "CMPSC 56\n" +
                "R\n" +
                "4:00pm - 4:50pm\n" +
                "PHELP3525\n" +
                "29 / 29\n" +
                "CMPSC 56\n" +
                "R\n" +
                "5:00pm - 5:50pm\n" +
                "PHELP3525\n" +
                "28 / 28\n" +
                "CMPSC 56\n" +
                "R\n" +
                "6:00pm - 6:50pm\n" +
                "PHELP3525\n" +
                "2 / 28\n" +
                "CMPSC 64\n" +
                "T R\n" +
                "12:30pm - 1:45pm\n" +
                "SH 1431\n" +
                "21 / 70\n" +
                "CMPSC 64\n" +
                "F\n" +
                "9:00 am - 9:50 am\n" +
                "PHELP3525\n" +
                "4 / 35\n" +
                "CMPSC 64\n" +
                "F\n" +
                "10:00am - 10:50am\n" +
                "PHELP3525\n" +
                "17 / 35\n" +
                "CMPSC 111\n" +
                "M W\n" +
                "11:00am - 12:15pm\n" +
                "PSYCH1924\n" +
                "70 / 100\n" +
                "CMPSC 111\n" +
                "F\n" +
                "12:00pm - 12:50pm\n" +
                "PHELP2516\n" +
                "35 / 50\n" +
                "CMPSC 111\n" +
                "F\n" +
                "1:00pm - 1:50pm\n" +
                "PHELP2516\n" +
                "35 / 50\n" +
                "CMPSC 130A\n" +
                "M W\n" +
                "9:30 am - 10:45am\n" +
                "PHELP3515\n" +
                "37 / 80\n" +
                "CMPSC 130A\n" +
                "F\n" +
                "2:00pm - 2:50pm\n" +
                "GIRV 2129\n" +
                "35 / 40\n" +
                "CMPSC 130A\n" +
                "F\n" +
                "3:00pm - 3:50pm\n" +
                "GIRV 2129\n" +
                "2 / 40\n" +
                "CMPSC 138\n" +
                "M W\n" +
                "3:30pm - 4:45pm\n" +
                "PHELP3526\n" +
                "45 / 80\n" +
                "CMPSC 138\n" +
                "T\n" +
                "4:00pm - 4:50pm\n" +
                "PHELP1508\n" +
                "40 / 40\n" +
                "CMPSC 138\n" +
                "T\n" +
                "5:00pm - 5:50pm\n" +
                "GIRV 2116\n" +
                "5 / 40\n" +
                "CMPSC 153A\n" +
                "M W\n" +
                "3:30pm - 4:45pm\n" +
                "BUCHN1930\n" +
                "20 / 20\n" +
                "CMPSC 153A\n" +
                "F\n" +
                "9:00 am - 9:50 am\n" +
                "PHELP2524\n" +
                "20 / 20\n" +
                "CMPSC 160\n" +
                "T R\n" +
                "12:30pm - 1:45pm\n" +
                "PHELP3515\n" +
                "78 / 78\n" +
                "CMPSC 160\n" +
                "M\n" +
                "4:00pm - 4:50pm\n" +
                "GIRV 2116\n" +
                "39 / 39\n" +
                "CMPSC 160\n" +
                "M\n" +
                "5:00pm - 5:50pm\n" +
                "GIRV 1115\n" +
                "39 / 39\n" +
                "CMPSC 174A\n" +
                "M W\n" +
                "9:30 am - 10:45am\n" +
                "HSSB 1174\n" +
                "64 / 85\n" +
                "CMPSC 174A\n" +
                "F\n" +
                "2:00pm - 2:50pm\n" +
                "GIRV 1112\n" +
                "44 / 44\n" +
                "CMPSC 174A\n" +
                "F\n" +
                "3:00pm - 3:50pm\n" +
                "GIRV 1116\n" +
                "20 / 41\n" +
                "CMPSC 176A\n" +
                "T R\n" +
                "11:00am - 12:15pm\n" +
                "WEBB 1100\n" +
                "63 / 85\n" +
                "CMPSC 176A\n" +
                "W\n" +
                "5:00pm - 5:50pm\n" +
                "GIRV 2119\n" +
                "40 / 43\n" +
                "CMPSC 176A\n" +
                "W\n" +
                "6:00pm - 6:50pm\n" +
                "GIRV 1112\n" +
                "23 / 42\n" +
                "CMPSC 177\n" +
                "T R\n" +
                "2:00pm - 3:15pm\n" +
                "PHELP3515\n" +
                "40 / 75\n" +
                "CMPSC 177\n" +
                "F\n" +
                "9:00 am - 9:50 am\n" +
                "GIRV 2129\n" +
                "5 / 38\n" +
                "CMPSC 177\n" +
                "F\n" +
                "10:00am - 10:50am\n" +
                "GIRV 2123\n" +
                "35 / 37\n" +
                "CMPSC 180\n" +
                "M W\n" +
                "2:00pm - 3:15pm\n" +
                "PHELP1444\n" +
                "28 / 35\n" +
                "CMPSC 180\n" +
                "F\n" +
                "12:00pm - 12:50pm\n" +
                "387 104\n" +
                "28 / 35\n" +
                "CMPSC 189A\n" +
                "M W\n" +
                "12:30pm - 1:45pm\n" +
                "PHELP2516\n" +
                "43 / 50\n" +
                "CMPSC 189A\n" +
                "F\n" +
                "11:00am - 12:15pm\n" +
                "PHELP2524\n" +
                "43 / 50\n" +
                "CMPSC 192\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "0 / 10\n" +
                "CMPSC 193\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "0 / 10\n" +
                "CMPSC 196\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "0 / 10\n" +
                "CMPSC 199\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "0 / 10\n" +
                "CMPTGCS 1A\n" +
                "T R\n" +
                "11:00am - 12:20pm\n" +
                "494 143\n" +
                "0 / 12\n" +
                "CMPTGCS 1L\n" +
                "M W\n" +
                "6:00pm - 8:20pm\n" +
                "494 143\n" +
                "2 / 20\n" +
                "CMPTGCS 2\n" +
                "M W\n" +
                "12:30pm - 1:50pm\n" +
                "494 143\n" +
                "0 / 15\n" +
                "CMPTGCS 1A\n" +
                "T R\n" +
                "11:00am - 12:20pm\n" +
                "494 143\n" +
                "0 / 12\n" +
                "CMPTGCS 1L\n" +
                "M W\n" +
                "6:00pm - 8:20pm\n" +
                "494 143\n" +
                "2 / 20\n" +
                "CMPTGCS 2\n" +
                "M W\n" +
                "12:30pm - 1:50pm\n" +
                "494 143\n" +
                "0 / 15\n" +
                "CNCSP 102\n" +
                "M\n" +
                "3:30pm - 4:45pm\n" +
                "NH 1006\n" +
                "62 / 120\n" +
                "CNCSP 110\n" +
                "M W\n" +
                "5:00pm - 6:15pm\n" +
                "GIRV 1004\n" +
                "111 / 120\n" +
                "CNCSP 110\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "20 / 20\n" +
                "CNCSP 110\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "20 / 20\n" +
                "CNCSP 110\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "20 / 20\n" +
                "CNCSP 110\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "20 / 20\n" +
                "CNCSP 110\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "20 / 20\n" +
                "CNCSP 110\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "11 / 20\n" +
                "CNCSP 197\n" +
                "M\n" +
                "4:00pm - 5:50pm\n" +
                "ED 1215\n" +
                "0 / 60\n" +
                "CNCSP 199\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "0 / 25\n" +
                "CNCSP 199RA\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "0 / 25\n" +
                "DANCE 42D\n" +
                "M W\n" +
                "10:00am - 11:20am\n" +
                "TD-W 1501\n" +
                "13 / 30\n" +
                "DANCE 43A\n" +
                "T R\n" +
                "3:00pm - 4:20pm\n" +
                "TD-W 1501\n" +
                "0 / 30\n" +
                "DANCE 44D\n" +
                "T R\n" +
                "9:00 am - 10:20am\n" +
                "HSSB 1151\n" +
                "0 / 30\n" +
                "DANCE 45\n" +
                "M W F\n" +
                "10:00am - 10:50am\n" +
                "IV THEA1\n" +
                "159 / 160\n" +
                "DANCE 47A\n" +
                "T R\n" +
                "9:00 am - 10:50am\n" +
                "TD-W 1501\n" +
                "0 / 35\n" +
                "DANCE 47A\n" +
                "M W F\n" +
                "8:30 am - 9:50 am\n" +
                "HSSB 1151\n" +
                "0 / 35\n" +
                "DANCE 47D\n" +
                "M W F\n" +
                "3:00pm - 4:20pm\n" +
                "TD-W 1501\n" +
                "3 / 20\n" +
                "DANCE 47D\n" +
                "T R\n" +
                "9:00 am - 10:50am\n" +
                "TD-W 1501\n" +
                "1 / 20\n" +
                "DANCE 51\n" +
                "T R\n" +
                "3:00pm - 4:50pm\n" +
                "HSSB 1151\n" +
                "4 / 30\n" +
                "DANCE 56A\n" +
                "T R\n" +
                "1:00pm - 2:50pm\n" +
                "HSSB 1151\n" +
                "0 / 35\n" +
                "DANCE 56A\n" +
                "M W F\n" +
                "12:00pm - 1:20pm\n" +
                "HSSB 1135\n" +
                "0 / 35\n" +
                "DANCE 56D\n" +
                "M W F\n" +
                "12:00pm - 1:20pm\n" +
                "HSSB 1151\n" +
                "2 / 30\n" +
                "DANCE 56D\n" +
                "T R\n" +
                "1:00pm - 2:50pm\n" +
                "HSSB 1135\n" +
                "2 / 20\n" +
                "DANCE 94\n" +
                "M W F\n" +
                "1:30pm - 2:50pm\n" +
                "HSSB 1151\n" +
                "0 / 20\n" +
                "DANCE 147A\n" +
                "M W F\n" +
                "11:30am - 12:50pm\n" +
                "TD-W 1501\n" +
                "8 / 30\n" +
                "DANCE 147D\n" +
                "M W F\n" +
                "11:30am - 12:50pm\n" +
                "TD-W 1501\n" +
                "6 / 30\n" +
                "DANCE 147PA\n" +
                "T R\n" +
                "1:00pm - 2:50pm\n" +
                "TD-W 1501\n" +
                "2 / 20\n" +
                "DANCE 149\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "0 / 50\n" +
                "DANCE 151A\n" +
                "T R\n" +
                "11:00am - 12:50pm\n" +
                "HSSB 1135\n" +
                "11 / 25\n" +
                "DANCE 156A\n" +
                "T R\n" +
                "9:00 am - 10:50am\n" +
                "HSSB 1135\n" +
                "3 / 15\n" +
                "DANCE 156A\n" +
                "M W F\n" +
                "1:30pm - 2:50pm\n" +
                "HSSB 1135\n" +
                "6 / 15\n" +
                "DANCE 156D\n" +
                "T R\n" +
                "9:00 am - 10:50am\n" +
                "HSSB 1135\n" +
                "1 / 15\n" +
                "DANCE 156D\n" +
                "M W F\n" +
                "1:30pm - 2:50pm\n" +
                "HSSB 1151\n" +
                "3 / 15\n" +
                "DANCE 158\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "0 / 15\n" +
                "DANCE 186\n" +
                "R\n" +
                "5:00pm - 6:50pm\n" +
                "HSSB 1105\n" +
                "0 / 15\n" +
                "DANCE 190\n" +
                "M W F\n" +
                "3:00pm - 4:50pm\n" +
                "HSSB 1135\n" +
                "0 / 15\n" +
                "DANCE 191\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "0 / 5\n" +
                "DANCE 193H\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "0 / 5\n" +
                "DANCE 193HA\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "0 / 5\n" +
                "DANCE 199\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "0 / 25\n" +
                "EARTH 2\n" +
                "T R\n" +
                "9:30 am - 10:45am\n" +
                "HFH 1104\n" +
                "47 / 78\n" +
                "EARTH 2\n" +
                "T\n" +
                "12:00pm - 1:50pm\n" +
                "PSB-S2724\n" +
                "13 / 22\n" +
                "EARTH 2\n" +
                "T\n" +
                "2:00pm - 3:50pm\n" +
                "PSB-S2724\n" +
                "4 / 22\n" +
                "EARTH 2\n" +
                "W\n" +
                "11:00am - 12:50pm\n" +
                "PSB-S2724\n" +
                "12 / 24\n" +
                "EARTH 2\n" +
                "W\n" +
                "9:00 am - 10:50am\n" +
                "PSB-S2724\n" +
                "3 / 24\n" +
                "EARTH 2\n" +
                "R\n" +
                "12:00pm - 1:50pm\n" +
                "PSB-S2724\n" +
                "3 / 24\n" +
                "EARTH 2\n" +
                "R\n" +
                "4:00pm - 5:50pm\n" +
                "PSB-S2724\n" +
                "5 / 24\n" +
                "EARTH 2\n" +
                "W\n" +
                "2:00pm - 3:50pm\n" +
                "PSB-S2724\n" +
                "2 / 24\n" +
                "EARTH 2\n" +
                "W\n" +
                "4:00pm - 5:50pm\n" +
                "PSB-S2724\n" +
                "5 / 24\n" +
                "EARTH 4\n" +
                "M W\n" +
                "3:30pm - 4:45pm\n" +
                "GIRV 1004\n" +
                "44 / 86\n" +
                "EARTH 4\n" +
                "T\n" +
                "3:00pm - 3:50pm\n" +
                "PSB-S2712\n" +
                "8 / 26\n" +
                "EARTH 4\n" +
                "T\n" +
                "2:00pm - 2:50pm\n" +
                "PSB-S2712\n" +
                "10 / 26\n" +
                "EARTH 4\n" +
                "T\n" +
                "4:00pm - 4:50pm\n" +
                "PSB-S2712\n" +
                "8 / 26\n" +
                "EARTH 4\n" +
                "W\n" +
                "11:00am - 11:50am\n" +
                "PSB-S2712\n" +
                "7 / 26\n" +
                "EARTH 4\n" +
                "R\n" +
                "11:00am - 11:50am\n" +
                "PSB-S2712\n" +
                "2 / 26\n" +
                "EARTH 4\n" +
                "R\n" +
                "1:00pm - 1:50pm\n" +
                "PSB-S2712\n" +
                "2 / 26\n" +
                "EARTH 4\n" +
                "T\n" +
                "1:00pm - 1:50pm\n" +
                "PSB-S2712\n" +
                "5 / 25\n" +
                "EARTH 4\n" +
                "R\n" +
                "12:00pm - 12:50pm\n" +
                "PSB-S2712\n" +
                "2 / 25\n" +
                "EARTH 4\n" +
                "T R\n" +
                "5:00pm - 6:15pm\n" +
                "WEBB 1100\n" +
                "16 / 81\n" +
                "EARTH 4\n" +
                "M\n" +
                "2:00pm - 2:50pm\n" +
                "PSB-S2712\n" +
                "1 / 27\n" +
                "EARTH 4\n" +
                "M\n" +
                "3:00pm - 3:50pm\n" +
                "PSB-S2712\n" +
                "5 / 27\n" +
                "EARTH 4\n" +
                "W\n" +
                "2:00pm - 2:50pm\n" +
                "PSB-S2712\n" +
                "10 / 27\n" +
                "EARTH 4H\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "0 / 27\n" +
                "EARTH 6\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "0 / 25\n" +
                "EARTH 7\n" +
                "T R\n" +
                "9:30 am - 10:45am\n" +
                "CAMPBHALL\n" +
                "59 / 467\n" +
                "EARTH 7\n" +
                "M\n" +
                "10:00am - 10:50am\n" +
                "PSB-S2725\n" +
                "7 / 27\n" +
                "EARTH 7\n" +
                "M\n" +
                "11:00am - 11:50am\n" +
                "PSB-S2725\n" +
                "3 / 27\n" +
                "EARTH 7\n" +
                "M\n" +
                "12:00pm - 12:50pm\n" +
                "PSB-S2725\n" +
                "2 / 27\n" +
                "EARTH 7\n" +
                "M\n" +
                "1:00pm - 1:50pm\n" +
                "PSB-S2725\n" +
                "5 / 27\n" +
                "EARTH 7\n" +
                "M\n" +
                "2:00pm - 2:50pm\n" +
                "PSB-S2725\n" +
                "2 / 27\n" +
                "EARTH 7\n" +
                "M\n" +
                "3:00pm - 3:50pm\n" +
                "PSB-S2725\n" +
                "3 / 27\n" +
                "EARTH 7\n" +
                "M\n" +
                "4:00pm - 4:50pm\n" +
                "PSB-S2725\n" +
                "0 / 27\n" +
                "EARTH 7\n" +
                "M\n" +
                "5:00pm - 5:50pm\n" +
                "PSB-S2725\n" +
                "2 / 27\n" +
                "EARTH 7\n" +
                "T\n" +
                "12:00pm - 12:50pm\n" +
                "PSB-S2725\n" +
                "7 / 27\n" +
                "EARTH 7\n" +
                "T\n" +
                "1:00pm - 1:50pm\n" +
                "PSB-S2725\n" +
                "5 / 27\n" +
                "EARTH 7\n" +
                "T\n" +
                "2:00pm - 2:50pm\n" +
                "PSB-S2725\n" +
                "3 / 27\n" +
                "EARTH 7\n" +
                "T\n" +
                "3:00pm - 3:50pm\n" +
                "PSB-S2725\n" +
                "0 / 27\n" +
                "EARTH 7\n" +
                "T\n" +
                "4:00pm - 4:50pm\n" +
                "PSB-S2725\n" +
                "0 / 27\n" +
                "EARTH 7\n" +
                "T\n" +
                "5:00pm - 5:50pm\n" +
                "PSB-S2725\n" +
                "1 / 27\n" +
                "EARTH 7\n" +
                "W\n" +
                "10:00am - 10:50am\n" +
                "PSB-S2725\n" +
                "5 / 27\n" +
                "EARTH 7\n" +
                "W\n" +
                "11:00am - 11:50am\n" +
                "PSB-S2725\n" +
                "3 / 27\n" +
                "EARTH 7\n" +
                "W\n" +
                "12:00pm - 12:50pm\n" +
                "PSB-S2725\n" +
                "1 / 27\n" +
                "EARTH 7\n" +
                "W\n" +
                "1:00pm - 1:50pm\n" +
                "PSB-S2725\n" +
                "2 / 27\n" +
                "EARTH 7\n" +
                "W\n" +
                "2:00pm - 2:50pm\n" +
                "PSB-S2725\n" +
                "3 / 27\n" +
                "EARTH 7\n" +
                "W\n" +
                "3:00pm - 3:50pm\n" +
                "PSB-S2725\n" +
                "0 / 27\n" +
                "EARTH 7\n" +
                "W\n" +
                "4:00pm - 4:50pm\n" +
                "PSB-S2725\n" +
                "0 / 27\n" +
                "EARTH 7\n" +
                "W\n" +
                "5:00pm - 5:50pm\n" +
                "PSB-S2725\n" +
                "1 / 27\n" +
                "EARTH 7\n" +
                "R\n" +
                "12:00pm - 12:50pm\n" +
                "PSB-S2725\n" +
                "2 / 27\n" +
                "EARTH 7\n" +
                "R\n" +
                "1:00pm - 1:50pm\n" +
                "PSB-S2725\n" +
                "0 / 27\n" +
                "EARTH 7\n" +
                "R\n" +
                "4:00pm - 4:50pm\n" +
                "PSB-S2725\n" +
                "2 / 27\n" +
                "EARTH 7\n" +
                "R\n" +
                "5:00pm - 5:50pm\n" +
                "PSB-S2725\n" +
                "0 / 27\n" +
                "EARTH 10\n" +
                "T R\n" +
                "3:30pm - 4:45pm\n" +
                "BUCHN1910\n" +
                "60 / 157\n" +
                "EARTH 10\n" +
                "M\n" +
                "10:00am - 10:50am\n" +
                "PSB-S2724\n" +
                "7 / 27\n" +
                "EARTH 10\n" +
                "M\n" +
                "11:00am - 11:50am\n" +
                "PSB-S2724\n" +
                "6 / 27\n" +
                "EARTH 10\n" +
                "M\n" +
                "12:00pm - 12:50pm\n" +
                "PSB-S2724\n" +
                "10 / 27\n" +
                "EARTH 10\n" +
                "M\n" +
                "1:00pm - 1:50pm\n" +
                "PSB-S2724\n" +
                "8 / 27\n" +
                "EARTH 10\n" +
                "T\n" +
                "9:00 am - 9:50 am\n" +
                "PSB-S2724\n" +
                "0 / 27\n" +
                "EARTH 10\n" +
                "T\n" +
                "10:00am - 10:50am\n" +
                "PSB-S2724\n" +
                "7 / 27\n" +
                "EARTH 10\n" +
                "T\n" +
                "11:00am - 11:50am\n" +
                "PSB-S2724\n" +
                "12 / 27\n" +
                "EARTH 10\n" +
                "M\n" +
                "4:00pm - 4:50pm\n" +
                "PSB-S2724\n" +
                "5 / 27\n" +
                "EARTH 10\n" +
                "M\n" +
                "5:00pm - 5:50pm\n" +
                "PSB-S2724\n" +
                "1 / 27\n" +
                "EARTH 10\n" +
                "R\n" +
                "9:00 am - 9:50 am\n" +
                "PSB-S2724\n" +
                "1 / 27\n" +
                "EARTH 10\n" +
                "R\n" +
                "10:00am - 10:50am\n" +
                "PSB-S2724\n" +
                "3 / 27\n" +
                "EARTH 18\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "75 / 75\n" +
                "EARTH 20\n" +
                "M W\n" +
                "2:00pm - 3:15pm\n" +
                "EMBARHALL\n" +
                "117 / 118\n" +
                "EARTH 20\n" +
                "M\n" +
                "11:00am - 11:50am\n" +
                "PSB-S2712\n" +
                "20 / 27\n" +
                "EARTH 20\n" +
                "M\n" +
                "12:00pm - 12:50pm\n" +
                "PSB-S2712\n" +
                "19 / 27\n" +
                "EARTH 20\n" +
                "T\n" +
                "11:00am - 11:50am\n" +
                "PSB-S2712\n" +
                "16 / 27\n" +
                "EARTH 20\n" +
                "T\n" +
                "12:00pm - 12:50pm\n" +
                "PSB-S2712\n" +
                "18 / 27\n" +
                "EARTH 20\n" +
                "W\n" +
                "5:00pm - 5:50pm\n" +
                "PSB-S2712\n" +
                "14 / 27\n" +
                "EARTH 20\n" +
                "M\n" +
                "5:00pm - 5:50pm\n" +
                "PSB-S2712\n" +
                "8 / 27\n" +
                "EARTH 20\n" +
                "W\n" +
                "12:00pm - 12:50pm\n" +
                "PSB-S2712\n" +
                "12 / 27\n" +
                "EARTH 20\n" +
                "R\n" +
                "4:00pm - 4:50pm\n" +
                "PSB-S2712\n" +
                "7 / 27\n" +
                "EARTH 20\n" +
                "F\n" +
                "9:00 am - 9:50 am\n" +
                "PSB-S2712\n" +
                "3 / 27\n" +
                "EARTH 98\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "0 / 25\n" +
                "EARTH 99\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "0 / 10\n" +
                "EARTH 100\n" +
                "T R\n" +
                "11:00am - 12:15pm\n" +
                "WEBB 1025\n" +
                "8 / 25\n" +
                "EARTH 100\n" +
                "W\n" +
                "11:00am - 11:50am\n" +
                "WEBB 1025\n" +
                "8 / 25\n" +
                "EARTH 102C\n" +
                "M W\n" +
                "3:30pm - 4:45pm\n" +
                "PSB-S2711\n" +
                "10 / 25\n" +
                "EARTH 102C\n" +
                "T\n" +
                "2:00pm - 4:50pm\n" +
                "PSB-S2711\n" +
                "10 / 25\n" +
                "EARTH 104A\n" +
                "T R\n" +
                "9:30 am - 10:45am\n" +
                "PSB-S2712\n" +
                "40 / 50\n" +
                "EARTH 104A\n" +
                "F\n" +
                "1:00pm - 4:50pm\n" +
                "PSB-S2712\n" +
                "21 / 25\n" +
                "EARTH 104A\n" +
                "F\n" +
                "1:00pm - 4:50pm\n" +
                "PSB-S2711\n" +
                "19 / 25\n" +
                "EARTH 106\n" +
                "M W\n" +
                "12:30pm - 1:45pm\n" +
                "WEBB 1025\n" +
                "12 / 25\n" +
                "EARTH 106\n" +
                "R\n" +
                "4:00pm - 4:50pm\n" +
                "WEBB 1015\n" +
                "12 / 25\n" +
                "EARTH 114\n" +
                "M W\n" +
                "9:30 am - 10:45am\n" +
                "PSB-S2712\n" +
                "40 / 40\n" +
                "EARTH 114\n" +
                "T\n" +
                "5:00pm - 7:50pm\n" +
                "PSB-S2711\n" +
                "20 / 20\n" +
                "EARTH 114\n" +
                "F\n" +
                "9:00 am - 11:50am\n" +
                "PSB-S2711\n" +
                "20 / 20\n" +
                "EARTH 117\n" +
                "T R\n" +
                "3:30pm - 4:45pm\n" +
                "WEBB 1025\n" +
                "25 / 25\n" +
                "EARTH 117\n" +
                "W\n" +
                "5:00pm - 7:50pm\n" +
                "PSB-S2711\n" +
                "25 / 25\n" +
                "EARTH 125\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "1 / 20\n" +
                "EARTH 130\n" +
                "M W\n" +
                "11:00am - 12:15pm\n" +
                "WEBB 1100\n" +
                "74 / 92\n" +
                "EARTH 160\n" +
                "R\n" +
                "2:00pm - 3:50pm\n" +
                "WEBB 1100\n" +
                "25 / 40\n" +
                "EARTH 187\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "0 / 5\n" +
                "EARTH 192\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "0 / 10\n" +
                "EARTH 195H\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "0 / 5\n" +
                "EARTH 196HA\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "0 / 5\n" +
                "EARTH 198\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "0 / 10\n" +
                "EARTH 199\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "0 / 10\n" +
                "EARTH 199RA\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "0 / 10\n" +
                "EACS 21\n" +
                "T R\n" +
                "2:00pm - 3:15pm\n" +
                "EMBARHALL\n" +
                "20 / 20\n" +
                "EACS 21\n" +
                "M\n" +
                "8:00 am - 8:50 am\n" +
                "GIRV 1106\n" +
                "0 / 4\n" +
                "EACS 21\n" +
                "M\n" +
                "9:00 am - 9:50 am\n" +
                "GIRV 2135\n" +
                "2 / 4\n" +
                "EACS 21\n" +
                "M\n" +
                "11:00am - 11:50am\n" +
                "GIRV 2110\n" +
                "4 / 4\n" +
                "EACS 21\n" +
                "M\n" +
                "12:00pm - 12:50pm\n" +
                "GIRV 2110\n" +
                "2 / 4\n" +
                "EACS 21\n" +
                "T\n" +
                "12:00pm - 12:50pm\n" +
                "GIRV 1106\n" +
                "1 / 4\n" +
                "EACS 21\n" +
                "T\n" +
                "1:00pm - 1:50pm\n" +
                "GIRV 2110\n" +
                "4 / 4\n" +
                "EACS 21\n" +
                "T\n" +
                "6:00pm - 6:50pm\n" +
                "HSSB 3001E\n" +
                "1 / 4\n" +
                "EACS 21\n" +
                "T\n" +
                "5:00pm - 5:50pm\n" +
                "GIRV 1106\n" +
                "1 / 4\n" +
                "EACS 21\n" +
                "W\n" +
                "8:00 am - 8:50 am\n" +
                "GIRV 2110\n" +
                "0 / 4\n" +
                "EACS 21\n" +
                "W\n" +
                "4:00pm - 4:50pm\n" +
                "HSSB 3001E\n" +
                "1 / 4\n" +
                "EACS 21\n" +
                "W\n" +
                "2:00pm - 2:50pm\n" +
                "GIRV 2127\n" +
                "2 / 4\n" +
                "EACS 21\n" +
                "W\n" +
                "3:00pm - 3:50pm\n" +
                "HSSB 2202\n" +
                "0 / 4\n" +
                "EACS 21\n" +
                "F\n" +
                "8:00 am - 8:50 am\n" +
                "GIRV 2110\n" +
                "0 / 4\n" +
                "EACS 21\n" +
                "F\n" +
                "9:00 am - 9:50 am\n" +
                "GIRV 1106\n" +
                "1 / 4\n" +
                "EACS 21\n" +
                "F\n" +
                "11:00am - 11:50am\n" +
                "HSSB 1233\n" +
                "1 / 4\n" +
                "EACS 21\n" +
                "F\n" +
                "12:00pm - 12:50pm\n" +
                "GIRV 2135\n" +
                "0 / 4\n" +
                "EEMB 3\n" +
                "M W F\n" +
                "2:00pm - 2:50pm\n" +
                "LSB 1001\n" +
                "124 / 155\n" +
                "EEMB 3Z\n" +
                "M W F\n" +
                "2:00pm - 2:50pm\n" +
                "LSB 1001\n" +
                "0 / 4\n" +
                "EEMB W 22\n" +
                "\n" +
                "\n" +
                "ONLINE\n" +
                "17 / 96\n" +
                "EEMB W 22\n" +
                "\n" +
                "\n" +
                "ONLINE\n" +
                "17 / 96\n" +
                "EEMB 40\n" +
                "M W F\n" +
                "1:00pm - 1:50pm\n" +
                "HFH 1104\n" +
                "96 / 188\n" +
                "EEMB 40\n" +
                "T\n" +
                "8:00 am - 8:50 am\n" +
                "387 104\n" +
                "2 / 24\n" +
                "EEMB 40\n" +
                "T\n" +
                "9:00 am - 9:50 am\n" +
                "PHELP2532\n" +
                "8 / 24\n" +
                "EEMB 40\n" +
                "M\n" +
                "3:00pm - 3:50pm\n" +
                "HSSB 1211\n" +
                "19 / 24\n" +
                "EEMB 40\n" +
                "T\n" +
                "11:00am - 11:50am\n" +
                "PHELP2536\n" +
                "22 / 24\n" +
                "EEMB 40\n" +
                "M\n" +
                "4:00pm - 4:50pm\n" +
                "HSSB 1228\n" +
                "6 / 24\n" +
                "EEMB 40\n" +
                "T\n" +
                "2:00pm - 2:50pm\n" +
                "HSSB 3202\n" +
                "24 / 24\n" +
                "EEMB 40\n" +
                "T\n" +
                "3:00pm - 3:50pm\n" +
                "NH 1109\n" +
                "8 / 24\n" +
                "EEMB 40\n" +
                "T\n" +
                "4:00pm - 4:50pm\n" +
                "LSB 1101\n" +
                "7 / 24\n" +
                "EEMB 84\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "0 / 1\n" +
                "EEMB 96\n" +
                "R\n" +
                "2:00pm - 4:50pm\n" +
                "HARDR1035\n" +
                "6 / 6\n" +
                "EEMB 98\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "0 / 1\n" +
                "EEMB 99\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "0 / 1\n" +
                "EEMB 106\n" +
                "T R\n" +
                "9:30 am - 10:45am\n" +
                "BRDA 1640\n" +
                "80 / 80\n" +
                "EEMB 106\n" +
                "M\n" +
                "1:00pm - 4:50pm\n" +
                "BSIF 1239\n" +
                "20 / 20\n" +
                "EEMB 106\n" +
                "T\n" +
                "1:00pm - 4:50pm\n" +
                "BSIF 1239\n" +
                "20 / 20\n" +
                "EEMB 106\n" +
                "W\n" +
                "1:00pm - 4:50pm\n" +
                "BSIF 1239\n" +
                "20 / 20\n" +
                "EEMB 106\n" +
                "R\n" +
                "1:00pm - 4:50pm\n" +
                "BSIF 1239\n" +
                "20 / 20\n" +
                "EEMB 108\n" +
                "M W F\n" +
                "10:00am - 10:50am\n" +
                "PSYCH1902\n" +
                "72 / 72\n" +
                "EEMB 108\n" +
                "M W\n" +
                "4:00pm - 6:50pm\n" +
                "BSIF 1229\n" +
                "24 / 24\n" +
                "EEMB 108\n" +
                "M W\n" +
                "7:00pm - 9:50pm\n" +
                "BSIF 1229\n" +
                "24 / 24\n" +
                "EEMB 108\n" +
                "T R\n" +
                "7:00pm - 9:50pm\n" +
                "BSIF 1229\n" +
                "24 / 24\n" +
                "EEMB 112\n" +
                "M W F\n" +
                "9:00 am - 9:50 am\n" +
                "NH 1006\n" +
                "12 / 120\n" +
                "EEMB 112\n" +
                "M W\n" +
                "1:00pm - 3:50pm\n" +
                "MLAB 1010\n" +
                "3 / 24\n" +
                "EEMB 112\n" +
                "M W\n" +
                "5:00pm - 7:50pm\n" +
                "MLAB 1010\n" +
                "5 / 24\n" +
                "EEMB 112\n" +
                "T R\n" +
                "9:00 am - 11:50am\n" +
                "MLAB 1010\n" +
                "0 / 24\n" +
                "EEMB 112\n" +
                "T R\n" +
                "1:00pm - 3:50pm\n" +
                "MLAB 1010\n" +
                "2 / 24\n" +
                "EEMB 112\n" +
                "T R\n" +
                "5:00pm - 7:50pm\n" +
                "MLAB 1010\n" +
                "2 / 24\n" +
                "EEMB 113\n" +
                "M W\n" +
                "3:30pm - 4:45pm\n" +
                "BRDA 1640\n" +
                "17 / 84\n" +
                "EEMB 113L\n" +
                "T\n" +
                "8:00 am - 10:50am\n" +
                "HARDR1013\n" +
                "5 / 12\n" +
                "EEMB 113L\n" +
                "T\n" +
                "11:00am - 1:50pm\n" +
                "HARDR1013\n" +
                "5 / 12\n" +
                "EEMB 113L\n" +
                "W\n" +
                "8:00 am - 10:50am\n" +
                "HARDR1013\n" +
                "4 / 12\n" +
                "EEMB 113L\n" +
                "W\n" +
                "11:00am - 1:50pm\n" +
                "HARDR1013\n" +
                "4 / 12\n" +
                "EEMB 113L\n" +
                "R\n" +
                "8:00 am - 10:50am\n" +
                "HARDR1013\n" +
                "6 / 12\n" +
                "EEMB 113L\n" +
                "R\n" +
                "11:00am - 1:50pm\n" +
                "HARDR1013\n" +
                "6 / 12\n" +
                "EEMB 113L\n" +
                "M\n" +
                "8:00 am - 10:50am\n" +
                "HARDR1013\n" +
                "0 / 12\n" +
                "EEMB 113L\n" +
                "M\n" +
                "11:00am - 1:50pm\n" +
                "HARDR1013\n" +
                "0 / 12\n" +
                "EEMB 113L\n" +
                "F\n" +
                "8:00 am - 10:50am\n" +
                "HARDR1013\n" +
                "0 / 12\n" +
                "EEMB 113L\n" +
                "F\n" +
                "11:00am - 1:50pm\n" +
                "HARDR1013\n" +
                "0 / 12\n" +
                "EEMB 119\n" +
                "F\n" +
                "1:00pm - 5:50pm\n" +
                "OFF CAMP\n" +
                "12 / 12\n" +
                "EEMB 119\n" +
                "W\n" +
                "2:00pm - 3:50pm\n" +
                "SH 1609\n" +
                "12 / 12\n" +
                "EEMB 119\n" +
                "W\n" +
                "5:00pm - 5:50pm\n" +
                "GIRV 2110\n" +
                "6 / 6\n" +
                "EEMB 119\n" +
                "R\n" +
                "6:00pm - 6:50pm\n" +
                "GIRV 2110\n" +
                "6 / 6\n" +
                "EEMB 120\n" +
                "T R\n" +
                "12:30pm - 1:45pm\n" +
                "BRDA 1610\n" +
                "165 / 165\n" +
                "EEMB 120\n" +
                "F\n" +
                "9:00 am - 9:50 am\n" +
                "LSB 1101\n" +
                "14 / 14\n" +
                "EEMB 120\n" +
                "F\n" +
                "3:00pm - 3:50pm\n" +
                "LSB 1101\n" +
                "14 / 14\n" +
                "EEMB 120\n" +
                "F\n" +
                "12:00pm - 12:50pm\n" +
                "GIRV 2112\n" +
                "14 / 14\n" +
                "EEMB 120\n" +
                "F\n" +
                "2:00pm - 2:50pm\n" +
                "LSB 1101\n" +
                "14 / 14\n" +
                "EEMB 120\n" +
                "T\n" +
                "8:00 am - 8:50 am\n" +
                "LSB 1101\n" +
                "14 / 14\n" +
                "EEMB 120\n" +
                "M\n" +
                "8:00 am - 8:50 am\n" +
                "HSSB 1228\n" +
                "14 / 14\n" +
                "EEMB 120\n" +
                "F\n" +
                "8:00 am - 8:50 am\n" +
                "LSB 1101\n" +
                "14 / 14\n" +
                "EEMB 120\n" +
                "F\n" +
                "1:00pm - 1:50pm\n" +
                "LSB 1101\n" +
                "14 / 14\n" +
                "EEMB 120\n" +
                "F\n" +
                "10:00am - 10:50am\n" +
                "LSB 1101\n" +
                "14 / 14\n" +
                "EEMB 120\n" +
                "M\n" +
                "9:00 am - 9:50 am\n" +
                "HSSB 1228\n" +
                "13 / 13\n" +
                "EEMB 120\n" +
                "T\n" +
                "11:00am - 11:50am\n" +
                "GIRV 1108\n" +
                "13 / 13\n" +
                "EEMB 120\n" +
                "F\n" +
                "11:00am - 11:50am\n" +
                "HSSB 1215\n" +
                "13 / 13\n" +
                "EEMB 130\n" +
                "T R\n" +
                "12:30pm - 1:45pm\n" +
                "IV THEA2\n" +
                "35 / 85\n" +
                "EEMB 130\n" +
                "W\n" +
                "10:00am - 10:50am\n" +
                "HSSB 3202\n" +
                "15 / 22\n" +
                "EEMB 130\n" +
                "W\n" +
                "11:00am - 11:50am\n" +
                "HSSB 4202\n" +
                "9 / 22\n" +
                "EEMB 130\n" +
                "W\n" +
                "12:00pm - 12:50pm\n" +
                "GIRV 2120\n" +
                "3 / 21\n" +
                "EEMB 130\n" +
                "W\n" +
                "1:00pm - 1:50pm\n" +
                "PHELP2532\n" +
                "8 / 21\n" +
                "EEMB 142A\n" +
                "T R\n" +
                "11:00am - 12:15pm\n" +
                "LSB 1001\n" +
                "129 / 129\n" +
                "EEMB 142A\n" +
                "W\n" +
                "8:00 am - 8:50 am\n" +
                "HSSB 1228\n" +
                "16 / 16\n" +
                "EEMB 142A\n" +
                "W\n" +
                "10:00am - 10:50am\n" +
                "PHELP2532\n" +
                "16 / 16\n" +
                "EEMB 142A\n" +
                "W\n" +
                "4:00pm - 4:50pm\n" +
                "LSB 1101\n" +
                "16 / 16\n" +
                "EEMB 142A\n" +
                "W\n" +
                "3:00pm - 3:50pm\n" +
                "GIRV 1119\n" +
                "16 / 16\n" +
                "EEMB 142A\n" +
                "W\n" +
                "2:00pm - 2:50pm\n" +
                "LSB 1101\n" +
                "16 / 16\n" +
                "EEMB 142A\n" +
                "W\n" +
                "1:00pm - 1:50pm\n" +
                "HSSB 3201\n" +
                "16 / 16\n" +
                "EEMB 142A\n" +
                "W\n" +
                "12:00pm - 12:50pm\n" +
                "GIRV 1108\n" +
                "17 / 17\n" +
                "EEMB 142A\n" +
                "W\n" +
                "11:00am - 11:50am\n" +
                "PHELP2532\n" +
                "16 / 16\n" +
                "EEMB 142AL\n" +
                "T R\n" +
                "2:00pm - 4:50pm\n" +
                "BSIF 1229\n" +
                "14 / 14\n" +
                "EEMB 142AL\n" +
                "F\n" +
                "3:00pm - 3:50pm\n" +
                "387 104\n" +
                "14 / 14\n" +
                "EEMB 142AL\n" +
                "T R\n" +
                "2:00pm - 4:50pm\n" +
                "BSIF 1229\n" +
                "14 / 14\n" +
                "EEMB 142AL\n" +
                "F\n" +
                "3:00pm - 3:50pm\n" +
                "387 104\n" +
                "14 / 14\n" +
                "EEMB 157\n" +
                "M W F\n" +
                "11:00am - 11:50am\n" +
                "PSYCH1902\n" +
                "33 / 42\n" +
                "EEMB 157\n" +
                "W\n" +
                "12:00pm - 12:50pm\n" +
                "GIRV 2119\n" +
                "21 / 21\n" +
                "EEMB 157\n" +
                "F\n" +
                "12:00pm - 12:50pm\n" +
                "NH 1111\n" +
                "12 / 21\n" +
                "EEMB 171\n" +
                "M W F\n" +
                "12:00pm - 12:50pm\n" +
                "CHEM 1171\n" +
                "43 / 48\n" +
                "EEMB 171\n" +
                "F\n" +
                "8:00 am - 8:50 am\n" +
                "387 103\n" +
                "7 / 12\n" +
                "EEMB 171\n" +
                "F\n" +
                "11:00am - 11:50am\n" +
                "GIRV 1115\n" +
                "12 / 12\n" +
                "EEMB 171\n" +
                "R\n" +
                "5:00pm - 5:50pm\n" +
                "ARTS 1353\n" +
                "12 / 12\n" +
                "EEMB 171\n" +
                "R\n" +
                "6:00pm - 6:50pm\n" +
                "PHELP2532\n" +
                "12 / 12\n" +
                "EEMB 184\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "0 / 0\n" +
                "EEMB 185\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "0 / 10\n" +
                "EEMB 188RE\n" +
                "M\n" +
                "6:00pm - 6:50pm\n" +
                "HARDR1013\n" +
                "11 / 15\n" +
                "EEMB 189\n" +
                "T\n" +
                "9:00 am - 11:50am\n" +
                "HARDR1015\n" +
                "3 / 7\n" +
                "EEMB 189\n" +
                "R\n" +
                "9:00 am - 11:50am\n" +
                "HARDR1015\n" +
                "3 / 7\n" +
                "EEMB 190\n" +
                "M\n" +
                "4:00pm - 5:15pm\n" +
                "LSB 1101\n" +
                "0 / 5\n" +
                "EEMB 194D\n" +
                "T\n" +
                "7:00pm - 8:50pm\n" +
                "NOBLE1231\n" +
                "0 / 20\n" +
                "EEMB 194EG\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "0 / 20\n" +
                "EEMB 194EV\n" +
                "R\n" +
                "3:00pm - 4:50pm\n" +
                "LSB 4307\n" +
                "0 / 20\n" +
                "EEMB 194M\n" +
                "W\n" +
                "4:00pm - 5:00pm\n" +
                "LSB 4307\n" +
                "0 / 25\n" +
                "EEMB 194MS\n" +
                "T\n" +
                "12:30pm - 1:45pm\n" +
                "LSB 4307\n" +
                "0 / 20\n" +
                "EEMB 194RR\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "0 / 20\n" +
                "EEMB 194T\n" +
                "M\n" +
                "2:30pm - 3:30pm\n" +
                "555 3103\n" +
                "0 / 20\n" +
                "EEMB 194TE\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "0 / 20\n" +
                "EEMB 197\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "0 / 1\n" +
                "EEMB 198\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "0 / 1\n" +
                "EEMB 199\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "0 / 1\n" +
                "ECON 1\n" +
                "M W F\n" +
                "12:00pm - 12:50pm\n" +
                "CAMPBHALL\n" +
                "209 / 595\n" +
                "ECON 1\n" +
                "W\n" +
                "5:00pm - 5:50pm\n" +
                "NH 1109\n" +
                "35 / 35\n" +
                "ECON 1\n" +
                "W\n" +
                "5:00pm - 5:50pm\n" +
                "GIRV 2127\n" +
                "13 / 35\n" +
                "ECON 1\n" +
                "W\n" +
                "5:00pm - 5:50pm\n" +
                "GIRV 1119\n" +
                "8 / 35\n" +
                "ECON 1\n" +
                "W\n" +
                "5:00pm - 5:50pm\n" +
                "GIRV 1115\n" +
                "1 / 35\n" +
                "ECON 1\n" +
                "W\n" +
                "6:00pm - 6:50pm\n" +
                "PHELP1160\n" +
                "1 / 35\n" +
                "ECON 1\n" +
                "W\n" +
                "6:00pm - 6:50pm\n" +
                "GIRV 2128\n" +
                "8 / 35\n" +
                "ECON 1\n" +
                "W\n" +
                "6:00pm - 6:50pm\n" +
                "PHELP1425\n" +
                "0 / 35\n" +
                "ECON 1\n" +
                "W\n" +
                "6:00pm - 6:50pm\n" +
                "TD-W 2600\n" +
                "1 / 35\n" +
                "ECON 1\n" +
                "R\n" +
                "5:00pm - 5:50pm\n" +
                "PHELP2524\n" +
                "5 / 35\n" +
                "ECON 1\n" +
                "R\n" +
                "5:00pm - 5:50pm\n" +
                "GIRV 1115\n" +
                "6 / 35\n" +
                "ECON 1\n" +
                "R\n" +
                "6:00pm - 6:50pm\n" +
                "PHELP3505\n" +
                "2 / 35\n" +
                "ECON 1\n" +
                "R\n" +
                "6:00pm - 6:50pm\n" +
                "GIRV 1115\n" +
                "2 / 35\n" +
                "ECON 1\n" +
                "R\n" +
                "8:00 am - 8:50 am\n" +
                "GIRV 1112\n" +
                "2 / 35\n" +
                "ECON 1\n" +
                "R\n" +
                "9:00 am - 9:50 am\n" +
                "PHELP2536\n" +
                "27 / 35\n" +
                "ECON 1\n" +
                "R\n" +
                "4:00pm - 4:50pm\n" +
                "GIRV 1119\n" +
                "30 / 35\n" +
                "ECON 1\n" +
                "T\n" +
                "4:00pm - 4:50pm\n" +
                "NH 1109\n" +
                "27 / 35\n" +
                "ECON 1\n" +
                "T\n" +
                "4:00pm - 4:50pm\n" +
                "GIRV 2127\n" +
                "35 / 35\n" +
                "ECON 1\n" +
                "T\n" +
                "5:00pm - 5:50pm\n" +
                "GIRV 1112\n" +
                "6 / 35\n" +
                "ECON 3A\n" +
                "M W\n" +
                "8:00 am - 9:15 am\n" +
                "EMBARHALL\n" +
                "175 / 240\n" +
                "ECON 3A\n" +
                "R\n" +
                "4:00pm - 4:50pm\n" +
                "GIRV 2115\n" +
                "30 / 30\n" +
                "ECON 3A\n" +
                "R\n" +
                "4:00pm - 4:50pm\n" +
                "PHELP1448\n" +
                "30 / 30\n" +
                "ECON 3A\n" +
                "R\n" +
                "5:00pm - 5:50pm\n" +
                "GIRV 1116\n" +
                "30 / 30\n" +
                "ECON 3A\n" +
                "R\n" +
                "5:00pm - 5:50pm\n" +
                "PHELP2514\n" +
                "15 / 30\n" +
                "ECON 3A\n" +
                "F\n" +
                "11:00am - 11:50am\n" +
                "PHELP1445\n" +
                "30 / 30\n" +
                "ECON 3A\n" +
                "F\n" +
                "10:00am - 10:50am\n" +
                "GIRV 1116\n" +
                "25 / 30\n" +
                "ECON 3A\n" +
                "F\n" +
                "10:00am - 10:50am\n" +
                "PHELP2514\n" +
                "6 / 30\n" +
                "ECON 3A\n" +
                "F\n" +
                "9:00 am - 9:50 am\n" +
                "GIRV 1116\n" +
                "9 / 30\n" +
                "ECON 3B\n" +
                "M W\n" +
                "5:00pm - 6:15pm\n" +
                "HFH 1104\n" +
                "147 / 180\n" +
                "ECON 3B\n" +
                "T\n" +
                "4:00pm - 4:50pm\n" +
                "NH 1122\n" +
                "29 / 30\n" +
                "ECON 3B\n" +
                "T\n" +
                "9:00 am - 9:50 am\n" +
                "NH 1122\n" +
                "7 / 30\n" +
                "ECON 3B\n" +
                "T\n" +
                "10:00am - 10:50am\n" +
                "NH 1122\n" +
                "23 / 30\n" +
                "ECON 3B\n" +
                "T\n" +
                "11:00am - 11:50am\n" +
                "NH 1122\n" +
                "29 / 30\n" +
                "ECON 3B\n" +
                "T\n" +
                "2:00pm - 2:50pm\n" +
                "NH 1122\n" +
                "30 / 30\n" +
                "ECON 3B\n" +
                "T\n" +
                "3:00pm - 3:50pm\n" +
                "NH 1122\n" +
                "29 / 30\n" +
                "ECON 3BH\n" +
                "M\n" +
                "11:00am - 11:50am\n" +
                "NH 1122\n" +
                "0 / 30\n" +
                "ECON 9\n" +
                "M W F\n" +
                "8:00 am - 8:50 am\n" +
                "GIRV 1004\n" +
                "131 / 160\n" +
                "ECON 9\n" +
                "T\n" +
                "4:00pm - 4:50pm\n" +
                "PHELP2516\n" +
                "40 / 40\n" +
                "ECON 9\n" +
                "T\n" +
                "5:00pm - 5:50pm\n" +
                "PHELP3505\n" +
                "40 / 40\n" +
                "ECON 9\n" +
                "T\n" +
                "6:00pm - 6:50pm\n" +
                "PHELP2524\n" +
                "16 / 40\n" +
                "ECON 9\n" +
                "T\n" +
                "5:00pm - 5:50pm\n" +
                "PHELP2516\n" +
                "35 / 40\n" +
                "ECON 10A\n" +
                "M W F\n" +
                "12:00pm - 12:50pm\n" +
                "MUSICLLCH\n" +
                "80 / 100\n" +
                "ECON 10A\n" +
                "M\n" +
                "4:00pm - 4:50pm\n" +
                "GIRV 2115\n" +
                "0 / 30\n" +
                "ECON 10A\n" +
                "M\n" +
                "2:00pm - 2:50pm\n" +
                "GIRV 1116\n" +
                "10 / 10\n" +
                "ECON 10A\n" +
                "M\n" +
                "3:00pm - 3:50pm\n" +
                "PHELP1448\n" +
                "10 / 10\n" +
                "ECON 10A\n" +
                "M\n" +
                "4:00pm - 4:50pm\n" +
                "PHELP2514\n" +
                "10 / 10\n" +
                "ECON 10A\n" +
                "M\n" +
                "5:00pm - 5:50pm\n" +
                "GIRV 2119\n" +
                "10 / 10\n" +
                "ECON 10A\n" +
                "M\n" +
                "5:00pm - 5:50pm\n" +
                "GIRV 2116\n" +
                "10 / 10\n" +
                "ECON 10A\n" +
                "M\n" +
                "6:00pm - 6:50pm\n" +
                "PHELP3505\n" +
                "10 / 10\n" +
                "ECON 10A\n" +
                "M\n" +
                "6:00pm - 6:50pm\n" +
                "PHELP3519\n" +
                "0 / 30\n" +
                "ECON 10A\n" +
                "M\n" +
                "2:00pm - 2:50pm\n" +
                "GIRV 2119\n" +
                "10 / 10\n" +
                "ECON 10A\n" +
                "M\n" +
                "3:00pm - 3:50pm\n" +
                "GIRV 2116\n" +
                "10 / 10\n" +
                "ECON 10A\n" +
                "M W F\n" +
                "12:00pm - 12:50pm\n" +
                "LSB 1001\n" +
                "50 / 50\n" +
                "ECON 10A\n" +
                "M\n" +
                "2:00pm - 2:50pm\n" +
                "PHELP1448\n" +
                "10 / 10\n" +
                "ECON 10A\n" +
                "M\n" +
                "3:00pm - 3:50pm\n" +
                "PHELP1445\n" +
                "10 / 10\n" +
                "ECON 10A\n" +
                "M\n" +
                "4:00pm - 4:50pm\n" +
                "PHELP1448\n" +
                "10 / 10\n" +
                "ECON 10A\n" +
                "M\n" +
                "5:00pm - 5:50pm\n" +
                "GIRV 2112\n" +
                "10 / 10\n" +
                "ECON 10A\n" +
                "M\n" +
                "6:00pm - 6:50pm\n" +
                "GIRV 2127\n" +
                "10 / 10\n" +
                "ECON 10A\n" +
                "M W F\n" +
                "12:00pm - 12:50pm\n" +
                "TD-W 1701\n" +
                "50 / 50\n" +
                "ECON 10A\n" +
                "T\n" +
                "8:00 am - 8:50 am\n" +
                "GIRV 1116\n" +
                "10 / 10\n" +
                "ECON 10A\n" +
                "T\n" +
                "8:00 am - 8:50 am\n" +
                "GIRV 2112\n" +
                "10 / 10\n" +
                "ECON 10A\n" +
                "M\n" +
                "4:00pm - 4:50pm\n" +
                "GIRV 2119\n" +
                "10 / 10\n" +
                "ECON 10A\n" +
                "T\n" +
                "10:00am - 10:50am\n" +
                "PHELP2536\n" +
                "10 / 10\n" +
                "ECON 10A\n" +
                "T\n" +
                "10:00am - 10:50am\n" +
                "GIRV 2124\n" +
                "10 / 10\n" +
                "ECON 10A\n" +
                "M W F\n" +
                "12:00pm - 12:50pm\n" +
                "BUCHN1940\n" +
                "40 / 40\n" +
                "ECON 10A\n" +
                "T\n" +
                "4:00pm - 4:50pm\n" +
                "PHELP3523\n" +
                "10 / 10\n" +
                "ECON 10A\n" +
                "T\n" +
                "4:00pm - 4:50pm\n" +
                "PHELP3519\n" +
                "10 / 10\n" +
                "ECON 10A\n" +
                "T\n" +
                "5:00pm - 5:50pm\n" +
                "GIRV 2120\n" +
                "10 / 10\n" +
                "ECON 10A\n" +
                "T\n" +
                "5:00pm - 5:50pm\n" +
                "GIRV 2119\n" +
                "10 / 10\n" +
                "ECON 100B\n" +
                "T R\n" +
                "2:00pm - 3:15pm\n" +
                "LSB 1001\n" +
                "35 / 159\n" +
                "ECON 100B\n" +
                "T\n" +
                "6:00pm - 6:50pm\n" +
                "GIRV 1112\n" +
                "3 / 40\n" +
                "ECON 100B\n" +
                "T\n" +
                "5:00pm - 5:50pm\n" +
                "NH 1105\n" +
                "15 / 40\n" +
                "ECON 100B\n" +
                "W\n" +
                "9:00 am - 9:50 am\n" +
                "GIRV 2112\n" +
                "4 / 40\n" +
                "ECON 100B\n" +
                "W\n" +
                "10:00am - 10:50am\n" +
                "PHELP1425\n" +
                "13 / 40\n" +
                "ECON 100B\n" +
                "T R\n" +
                "5:00pm - 6:15pm\n" +
                "PHELP1260\n" +
                "0 / 80\n" +
                "ECON 100B\n" +
                "W\n" +
                "9:00 am - 9:50 am\n" +
                "GIRV 1116\n" +
                "0 / 40\n" +
                "ECON 100B\n" +
                "W\n" +
                "10:00am - 10:50am\n" +
                "NH 1105\n" +
                "0 / 40\n" +
                "ECON 101\n" +
                "T R\n" +
                "12:30pm - 1:45pm\n" +
                "GIRV 1004\n" +
                "200 / 200\n" +
                "ECON 101\n" +
                "W\n" +
                "5:00pm - 5:50pm\n" +
                "GIRV 2120\n" +
                "40 / 40\n" +
                "ECON 101\n" +
                "W\n" +
                "6:00pm - 6:50pm\n" +
                "GIRV 1115\n" +
                "40 / 40\n" +
                "ECON 101\n" +
                "F\n" +
                "10:00am - 10:50am\n" +
                "GIRV 1112\n" +
                "40 / 40\n" +
                "ECON 101\n" +
                "F\n" +
                "9:00 am - 9:50 am\n" +
                "GIRV 1115\n" +
                "40 / 40\n" +
                "ECON 101\n" +
                "F\n" +
                "11:00am - 11:50am\n" +
                "GIRV 1112\n" +
                "40 / 40\n" +
                "ECON 106\n" +
                "M W F\n" +
                "9:00 am - 9:50 am\n" +
                "PSYCH1902\n" +
                "70 / 70\n" +
                "ECON 107A\n" +
                "M W F\n" +
                "8:00 am - 8:50 am\n" +
                "PHELP2516\n" +
                "11 / 50\n" +
                "ECON 117A\n" +
                "T R\n" +
                "3:30pm - 4:45pm\n" +
                "GIRV 2124\n" +
                "30 / 30\n" +
                "ECON 118\n" +
                "T R\n" +
                "2:00pm - 3:15pm\n" +
                "NH 1110\n" +
                "12 / 70\n" +
                "ECON 118\n" +
                "T R\n" +
                "3:30pm - 4:45pm\n" +
                "NH 1110\n" +
                "13 / 70\n" +
                "ECON 118H\n" +
                "T\n" +
                "6:30pm - 7:20pm\n" +
                "NH 1105\n" +
                "0 / 30\n" +
                "ECON 118H\n" +
                "R\n" +
                "6:30pm - 7:20pm\n" +
                "NH 1105\n" +
                "0 / 30\n" +
                "ECON 132A\n" +
                "M W\n" +
                "9:30 am - 10:45am\n" +
                "PHELP3505\n" +
                "29 / 60\n" +
                "ECON 134A\n" +
                "M W F\n" +
                "10:00am - 10:50am\n" +
                "LSB 1001\n" +
                "127 / 159\n" +
                "ECON 134A\n" +
                "T\n" +
                "2:00pm - 2:50pm\n" +
                "GIRV 1119\n" +
                "40 / 40\n" +
                "ECON 134A\n" +
                "T\n" +
                "3:00pm - 3:50pm\n" +
                "GIRV 2116\n" +
                "39 / 40\n" +
                "ECON 134A\n" +
                "T\n" +
                "4:00pm - 4:50pm\n" +
                "PHELP3505\n" +
                "25 / 40\n" +
                "ECON 134A\n" +
                "T\n" +
                "5:00pm - 5:50pm\n" +
                "GIRV 1115\n" +
                "23 / 40\n" +
                "ECON 135\n" +
                "T R\n" +
                "9:30 am - 10:45am\n" +
                "TD-W 2600\n" +
                "50 / 50\n" +
                "ECON 136A\n" +
                "M W\n" +
                "3:30pm - 4:45pm\n" +
                "NH 1110\n" +
                "33 / 70\n" +
                "ECON 136A\n" +
                "R\n" +
                "9:00 am - 9:50 am\n" +
                "NH 1122\n" +
                "7 / 35\n" +
                "ECON 136A\n" +
                "R\n" +
                "10:00am - 10:50am\n" +
                "NH 1122\n" +
                "26 / 35\n" +
                "ECON 136A\n" +
                "M W\n" +
                "5:00pm - 6:15pm\n" +
                "NH 1110\n" +
                "8 / 70\n" +
                "ECON 136A\n" +
                "F\n" +
                "9:00 am - 9:50 am\n" +
                "NH 1122\n" +
                "4 / 35\n" +
                "ECON 136A\n" +
                "F\n" +
                "10:00am - 10:50am\n" +
                "NH 1122\n" +
                "4 / 35\n" +
                "ECON 136AH\n" +
                "W\n" +
                "2:00pm - 2:50pm\n" +
                "NH 1122\n" +
                "0 / 30\n" +
                "ECON 136B\n" +
                "T R\n" +
                "9:30 am - 10:45am\n" +
                "NH 1110\n" +
                "63 / 70\n" +
                "ECON 136B\n" +
                "T R\n" +
                "11:00am - 12:15pm\n" +
                "NH 1110\n" +
                "70 / 70\n" +
                "ECON 136C\n" +
                "M W\n" +
                "9:30 am - 10:45am\n" +
                "PHELP1160\n" +
                "70 / 70\n" +
                "ECON 136C\n" +
                "T R\n" +
                "12:30pm - 1:45pm\n" +
                "NH 1110\n" +
                "70 / 70\n" +
                "ECON 136CH\n" +
                "M\n" +
                "11:00am - 11:50am\n" +
                "NH 1122\n" +
                "0 / 30\n" +
                "ECON 137A\n" +
                "M W\n" +
                "9:30 am - 10:45am\n" +
                "NH 1110\n" +
                "22 / 70\n" +
                "ECON 137A\n" +
                "F\n" +
                "10:00am - 10:50am\n" +
                "PHELP1445\n" +
                "13 / 30\n" +
                "ECON 137A\n" +
                "F\n" +
                "9:00 am - 9:50 am\n" +
                "PHELP2514\n" +
                "9 / 30\n" +
                "ECON 137A\n" +
                "M W\n" +
                "11:00am - 12:15pm\n" +
                "NH 1110\n" +
                "39 / 70\n" +
                "ECON 137A\n" +
                "F\n" +
                "9:00 am - 9:50 am\n" +
                "PHELP1445\n" +
                "14 / 30\n" +
                "ECON 137A\n" +
                "F\n" +
                "10:00am - 10:50am\n" +
                "PHELP1440\n" +
                "25 / 30\n" +
                "ECON 137B\n" +
                "M W\n" +
                "12:30pm - 1:45pm\n" +
                "NH 1110\n" +
                "70 / 70\n" +
                "ECON 138A\n" +
                "T R\n" +
                "5:00pm - 6:15pm\n" +
                "HSSB 1174\n" +
                "0 / 95\n" +
                "ECON 140A\n" +
                "T R\n" +
                "5:00pm - 6:15pm\n" +
                "GIRV 1004\n" +
                "200 / 200\n" +
                "ECON 140A\n" +
                "T\n" +
                "9:00 am - 9:50 am\n" +
                "PHELP2536\n" +
                "25 / 25\n" +
                "ECON 140A\n" +
                "T\n" +
                "8:00 am - 8:50 am\n" +
                "HSSB 1231\n" +
                "25 / 25\n" +
                "ECON 140A\n" +
                "F\n" +
                "11:00am - 11:50am\n" +
                "PHELP2514\n" +
                "25 / 25\n" +
                "ECON 140A\n" +
                "F\n" +
                "10:00am - 10:50am\n" +
                "NH 1105\n" +
                "25 / 25\n" +
                "ECON 140A\n" +
                "F\n" +
                "9:00 am - 9:50 am\n" +
                "PHELP2536\n" +
                "25 / 25\n" +
                "ECON 140A\n" +
                "F\n" +
                "9:00 am - 9:50 am\n" +
                "NH 1109\n" +
                "25 / 25\n" +
                "ECON 140A\n" +
                "F\n" +
                "10:00am - 10:50am\n" +
                "HSSB 2251\n" +
                "25 / 25\n" +
                "ECON 140A\n" +
                "F\n" +
                "10:00am - 10:50am\n" +
                "NH 1109\n" +
                "25 / 25\n" +
                "ECON 171\n" +
                "M W\n" +
                "12:30pm - 1:45pm\n" +
                "ARTS 1349\n" +
                "40 / 40\n" +
                "ECON 176\n" +
                "M W\n" +
                "2:00pm - 3:15pm\n" +
                "PHELP1160\n" +
                "30 / 30\n" +
                "ECON 177\n" +
                "M W F\n" +
                "10:00am - 10:50am\n" +
                "GIRV 1119\n" +
                "37 / 48\n" +
                "ECON 181\n" +
                "M W\n" +
                "3:30pm - 4:45pm\n" +
                "PHELP3515\n" +
                "69 / 80\n" +
                "ECON 182\n" +
                "M W\n" +
                "2:00pm - 3:15pm\n" +
                "NH 1110\n" +
                "70 / 70\n" +
                "ECON 182H\n" +
                "M\n" +
                "12:30pm - 1:45pm\n" +
                "PHELP1448\n" +
                "0 / 30\n" +
                "ECON 189\n" +
                "T R\n" +
                "11:00am - 12:15pm\n" +
                "BUCHN1930\n" +
                "66 / 80\n" +
                "ECON 189H\n" +
                "T\n" +
                "6:30pm - 7:20pm\n" +
                "NH 1105\n" +
                "0 / 30\n" +
                "ECON 189H\n" +
                "R\n" +
                "6:30pm - 7:20pm\n" +
                "NH 1105\n" +
                "0 / 30\n" +
                "ECON 193\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "0 / 15\n" +
                "ECON 196A\n" +
                "T\n" +
                "1:00pm - 2:50pm\n" +
                "NH 2111\n" +
                "7 / 13\n" +
                "ECON 199\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "0 / 30\n" +
                "ECON 199RA\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "0 / 30\n" +
                "ED 20\n" +
                "M\n" +
                "2:00pm - 3:15pm\n" +
                "HSSB 1174\n" +
                "12 / 95\n" +
                "ED 20\n" +
                "M\n" +
                "5:30pm - 6:50pm\n" +
                "HSSB 3202\n" +
                "3 / 19\n" +
                "ED 20\n" +
                "M\n" +
                "5:30pm - 6:50pm\n" +
                "HSSB 3201\n" +
                "2 / 19\n" +
                "ED 20\n" +
                "T\n" +
                "5:30pm - 6:50pm\n" +
                "HSSB 4201\n" +
                "4 / 19\n" +
                "ED 20\n" +
                "T\n" +
                "5:30pm - 6:50pm\n" +
                "HSSB 3202\n" +
                "2 / 19\n" +
                "ED 20\n" +
                "W\n" +
                "5:30pm - 6:50pm\n" +
                "GIRV 1108\n" +
                "1 / 19\n" +
                "ED 99\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "0 / 20\n" +
                "ED 111\n" +
                "W\n" +
                "1:00pm - 3:20pm\n" +
                "ED 1213\n" +
                "60 / 60\n" +
                "ED 111\n" +
                "F\n" +
                "9:00 am - 9:50 am\n" +
                "ED 1201\n" +
                "20 / 20\n" +
                "ED 111\n" +
                "F\n" +
                "10:00am - 10:50am\n" +
                "ED 1201\n" +
                "20 / 20\n" +
                "ED 111\n" +
                "F\n" +
                "11:00am - 11:50am\n" +
                "ED 1201\n" +
                "20 / 20\n" +
                "ED 118\n" +
                "R\n" +
                "3:30pm - 4:45pm\n" +
                "GIRV 1004\n" +
                "2 / 200\n" +
                "ED 118\n" +
                "R\n" +
                "5:00pm - 6:15pm\n" +
                "HSSB 1207\n" +
                "1 / 20\n" +
                "ED 118\n" +
                "R\n" +
                "5:00pm - 6:15pm\n" +
                "HSSB 1206\n" +
                "0 / 20\n" +
                "ED 118\n" +
                "R\n" +
                "5:00pm - 6:15pm\n" +
                "GIRV 2127\n" +
                "0 / 20\n" +
                "ED 118\n" +
                "R\n" +
                "5:00pm - 6:15pm\n" +
                "GIRV 1119\n" +
                "1 / 20\n" +
                "ED 118\n" +
                "R\n" +
                "5:00pm - 6:15pm\n" +
                "GIRV 2120\n" +
                "0 / 20\n" +
                "ED 118\n" +
                "R\n" +
                "5:00pm - 6:15pm\n" +
                "GIRV 2119\n" +
                "0 / 20\n" +
                "ED 118\n" +
                "R\n" +
                "5:00pm - 6:15pm\n" +
                "GIRV 2116\n" +
                "0 / 20\n" +
                "ED 118\n" +
                "R\n" +
                "5:00pm - 6:15pm\n" +
                "GIRV 2115\n" +
                "0 / 20\n" +
                "ED 118\n" +
                "R\n" +
                "5:00pm - 6:15pm\n" +
                "GIRV 2112\n" +
                "0 / 20\n" +
                "ED 118\n" +
                "R\n" +
                "5:00pm - 6:15pm\n" +
                "GIRV 2124\n" +
                "0 / 20\n" +
                "ED 120\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "0 / 15\n" +
                "ED 122\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "50 / 50\n" +
                "ED 123\n" +
                "M\n" +
                "6:00pm - 7:50pm\n" +
                "ED 1215\n" +
                "60 / 60\n" +
                "ED 123\n" +
                "M\n" +
                "8:00pm - 9:15pm\n" +
                "ED 1215\n" +
                "20 / 20\n" +
                "ED 123\n" +
                "M\n" +
                "8:00pm - 9:15pm\n" +
                "ED 1205\n" +
                "20 / 20\n" +
                "ED 123\n" +
                "T\n" +
                "5:30pm - 6:45pm\n" +
                "ED 1205\n" +
                "20 / 20\n" +
                "ED 124\n" +
                "M\n" +
                "4:00pm - 5:50pm\n" +
                "ED 4205\n" +
                "30 / 30\n" +
                "ED 125\n" +
                "M W\n" +
                "2:00pm - 3:15pm\n" +
                "ED 1215\n" +
                "30 / 30\n" +
                "ED 125\n" +
                "R\n" +
                "11:00am - 11:50am\n" +
                "ED 1213\n" +
                "30 / 30\n" +
                "ED 130\n" +
                "T\n" +
                "10:00am - 12:50pm\n" +
                "ED 4219\n" +
                "15 / 35\n" +
                "ED 131\n" +
                "T R\n" +
                "3:30pm - 4:45pm\n" +
                "ED 4219\n" +
                "14 / 30\n" +
                "ED 133\n" +
                "W\n" +
                "9:00 am - 11:50am\n" +
                "ED 4219\n" +
                "8 / 25\n" +
                "ED 176B\n" +
                "M\n" +
                "10:00am - 11:50am\n" +
                "ED 1215\n" +
                "45 / 45\n" +
                "ED 191W\n" +
                "T\n" +
                "2:00pm - 3:50pm\n" +
                "WEBB 1100\n" +
                "70 / 70\n" +
                "ED 191W\n" +
                "R\n" +
                "2:00pm - 3:50pm\n" +
                "ED 1217\n" +
                "35 / 35\n" +
                "ED 191W\n" +
                "R\n" +
                "2:00pm - 3:50pm\n" +
                "ED 1207\n" +
                "35 / 35\n" +
                "ED 199\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "0 / 20\n" +
                "ED 199RA\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "0 / 30\n" +
                "ECE 10A\n" +
                "M W\n" +
                "9:30 am - 10:45am\n" +
                "BUCHN1920\n" +
                "80 / 140\n" +
                "ECE 10AL\n" +
                "M\n" +
                "1:00pm - 4:50pm\n" +
                "HFH 5162C\n" +
                "6 / 20\n" +
                "ECE 10AL\n" +
                "M\n" +
                "6:30pm - 10:20pm\n" +
                "HFH 5162C\n" +
                "13 / 20\n" +
                "ECE 10AL\n" +
                "R\n" +
                "5:00pm - 8:50pm\n" +
                "HFH 5162C\n" +
                "20 / 20\n" +
                "ECE 10AL\n" +
                "F\n" +
                "2:00pm - 5:50pm\n" +
                "HFH 5162C\n" +
                "10 / 20\n" +
                "ECE 10AL\n" +
                "T\n" +
                "5:00pm - 8:50pm\n" +
                "HFH 5162C\n" +
                "20 / 20\n" +
                "ECE 10AL\n" +
                "W\n" +
                "1:00pm - 4:50pm\n" +
                "HFH 5162C\n" +
                "5 / 20\n" +
                "ECE 10AL\n" +
                "W\n" +
                "6:30pm - 10:20pm\n" +
                "HFH 5162C\n" +
                "6 / 20\n" +
                "ECE 122A\n" +
                "T R\n" +
                "3:30pm - 4:45pm\n" +
                "PHELP2514\n" +
                "14 / 20\n" +
                "ECE 122A\n" +
                "T\n" +
                "8:00 am - 10:50am\n" +
                "HFH 1140\n" +
                "14 / 20\n" +
                "ECE 130A\n" +
                "M W\n" +
                "9:30 am - 10:45am\n" +
                "PHELP1260\n" +
                "57 / 75\n" +
                "ECE 130A\n" +
                "F\n" +
                "11:00am - 12:50pm\n" +
                "HSSB 1237\n" +
                "11 / 25\n" +
                "ECE 130A\n" +
                "W\n" +
                "2:00pm - 3:50pm\n" +
                "GIRV 2123\n" +
                "23 / 25\n" +
                "ECE 130A\n" +
                "M\n" +
                "5:00pm - 6:50pm\n" +
                "PHELP1425\n" +
                "23 / 25\n" +
                "ECE 132\n" +
                "T R\n" +
                "9:30 am - 10:45am\n" +
                "PSYCH1902\n" +
                "60 / 75\n" +
                "ECE 132\n" +
                "F\n" +
                "9:00 am - 10:50am\n" +
                "PHELP3523\n" +
                "11 / 25\n" +
                "ECE 132\n" +
                "W\n" +
                "7:00pm - 8:50pm\n" +
                "PHELP1440\n" +
                "24 / 25\n" +
                "ECE 132\n" +
                "T\n" +
                "5:00pm - 6:50pm\n" +
                "PHELP1445\n" +
                "25 / 25\n" +
                "ECE 134\n" +
                "M W\n" +
                "12:30pm - 1:45pm\n" +
                "PHELP1260\n" +
                "32 / 90\n" +
                "ECE 134\n" +
                "R\n" +
                "6:00pm - 7:50pm\n" +
                "PHELP2524\n" +
                "2 / 30\n" +
                "ECE 134\n" +
                "R\n" +
                "4:00pm - 5:50pm\n" +
                "PHELP1444\n" +
                "24 / 30\n" +
                "ECE 134\n" +
                "W\n" +
                "5:00pm - 6:50pm\n" +
                "PHELP1444\n" +
                "6 / 30\n" +
                "ECE 145A\n" +
                "M W\n" +
                "12:30pm - 1:45pm\n" +
                "PHELP3519\n" +
                "4 / 20\n" +
                "ECE 145A\n" +
                "M\n" +
                "6:00pm - 8:50pm\n" +
                "HFH 5162D\n" +
                "4 / 20\n" +
                "ECE 146A\n" +
                "M W\n" +
                "11:00am - 12:15pm\n" +
                "PHELP1508\n" +
                "6 / 30\n" +
                "ECE 146A\n" +
                "R\n" +
                "7:00pm - 9:50pm\n" +
                "HFH 4152\n" +
                "4 / 10\n" +
                "ECE 146A\n" +
                "F\n" +
                "9:00 am - 11:50am\n" +
                "HFH 4152\n" +
                "0 / 10\n" +
                "ECE 146A\n" +
                "T\n" +
                "7:00pm - 9:50pm\n" +
                "HFH 4152\n" +
                "2 / 10\n" +
                "ECE 147A\n" +
                "T R\n" +
                "9:30 am - 10:45am\n" +
                "PHELP1444\n" +
                "10 / 30\n" +
                "ECE 147A\n" +
                "T\n" +
                "7:00pm - 9:50pm\n" +
                "HFH 3120\n" +
                "8 / 15\n" +
                "ECE 147A\n" +
                "R\n" +
                "7:00pm - 9:50pm\n" +
                "HFH 3120\n" +
                "2 / 15\n" +
                "ECE 153A\n" +
                "M W\n" +
                "3:30pm - 4:45pm\n" +
                "BUCHN1930\n" +
                "35 / 35\n" +
                "ECE 153A\n" +
                "F\n" +
                "9:00 am - 9:50 am\n" +
                "PHELP2524\n" +
                "35 / 35\n" +
                "ECE 154A\n" +
                "T R\n" +
                "2:00pm - 3:15pm\n" +
                "PSYCH1902\n" +
                "50 / 75\n" +
                "ECE 154A\n" +
                "F\n" +
                "9:00 am - 9:50 am\n" +
                "PHELP3519\n" +
                "8 / 25\n" +
                "ECE 154A\n" +
                "F\n" +
                "11:00am - 11:50am\n" +
                "PHELP3519\n" +
                "25 / 25\n" +
                "ECE 154A\n" +
                "F\n" +
                "10:00am - 10:50am\n" +
                "PHELP1508\n" +
                "17 / 25\n" +
                "ECE 156A\n" +
                "M W\n" +
                "5:00pm - 6:15pm\n" +
                "PHELP1260\n" +
                "55 / 90\n" +
                "ECE 156A\n" +
                "R\n" +
                "9:00 am - 11:50am\n" +
                "HFH 1124\n" +
                "12 / 22\n" +
                "ECE 156A\n" +
                "R\n" +
                "5:00pm - 7:50pm\n" +
                "HFH 1124\n" +
                "10 / 23\n" +
                "ECE 156A\n" +
                "T\n" +
                "5:00pm - 7:50pm\n" +
                "HFH 1124\n" +
                "22 / 22\n" +
                "ECE 156A\n" +
                "W\n" +
                "6:30pm - 9:20pm\n" +
                "HFH 1124\n" +
                "11 / 23\n" +
                "ECE 162A\n" +
                "M W\n" +
                "3:30pm - 4:45pm\n" +
                "NH 1111\n" +
                "3 / 24\n" +
                "ECE 162A\n" +
                "F\n" +
                "10:00am - 10:50am\n" +
                "PHELP2524\n" +
                "3 / 24\n" +
                "ECE 178\n" +
                "M W\n" +
                "9:30 am - 10:45am\n" +
                "TD-W 2600\n" +
                "20 / 60\n" +
                "ECE 178\n" +
                "F\n" +
                "1:00pm - 1:50pm\n" +
                "GIRV 2123\n" +
                "4 / 20\n" +
                "ECE 178\n" +
                "F\n" +
                "12:00pm - 12:50pm\n" +
                "GIRV 2115\n" +
                "5 / 20\n" +
                "ECE 178\n" +
                "F\n" +
                "11:00am - 11:50am\n" +
                "HSSB 1223\n" +
                "11 / 20\n" +
                "ECE 179D\n" +
                "T R\n" +
                "2:00pm - 3:15pm\n" +
                "PHELP1425\n" +
                "9 / 55\n" +
                "ECE 188A\n" +
                "T R\n" +
                "12:30pm - 1:45pm\n" +
                "PHELP1431\n" +
                "3 / 20\n" +
                "ECE 189A\n" +
                "M W\n" +
                "12:30pm - 1:45pm\n" +
                "GIRV 2123\n" +
                "14 / 42\n" +
                "ECE 192\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "0 / 10\n" +
                "ECE 193\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "0 / 10\n" +
                "ECE 196\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "0 / 20\n" +
                "ECE 199\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "0 / 10\n" +
                "ENGR 3\n" +
                "M W F\n" +
                "11:00am - 11:50am\n" +
                "LSB 1001\n" +
                "0 / 159\n" +
                "ENGR 3\n" +
                "M\n" +
                "4:00pm - 4:50pm\n" +
                "PHELP1526\n" +
                "0 / 20\n" +
                "ENGR 3\n" +
                "M\n" +
                "5:00pm - 5:50pm\n" +
                "PHELP1526\n" +
                "0 / 20\n" +
                "ENGR 3\n" +
                "T\n" +
                "3:00pm - 3:50pm\n" +
                "PHELP1526\n" +
                "0 / 20\n" +
                "ENGR 3\n" +
                "T\n" +
                "4:00pm - 4:50pm\n" +
                "PHELP1525\n" +
                "0 / 20\n" +
                "ENGR 3\n" +
                "T\n" +
                "5:00pm - 5:50pm\n" +
                "SSMS 1302\n" +
                "0 / 20\n" +
                "ENGR 3\n" +
                "W\n" +
                "2:00pm - 2:50pm\n" +
                "PHELP1526\n" +
                "0 / 20\n" +
                "ENGR 3\n" +
                "W\n" +
                "3:00pm - 3:50pm\n" +
                "PHELP1526\n" +
                "0 / 20\n" +
                "ENGR 3\n" +
                "W\n" +
                "4:00pm - 4:50pm\n" +
                "PHELP1526\n" +
                "0 / 19\n" +
                "ENGR 101\n" +
                "M W\n" +
                "3:30pm - 4:45pm\n" +
                "PHELP3523\n" +
                "30 / 30\n" +
                "ENGR 101\n" +
                "M W\n" +
                "5:00pm - 6:15pm\n" +
                "PHELP3523\n" +
                "30 / 30\n" +
                "ENGR 195A\n" +
                "F\n" +
                "1:00pm - 1:50pm\n" +
                "GIRV 2128\n" +
                "0 / 70\n" +
                "ENGR 199\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "0 / 1\n" +
                "ENGL 10\n" +
                "T R\n" +
                "5:00pm - 6:15pm\n" +
                "BUCHN1910\n" +
                "5 / 150\n" +
                "ENGL 10\n" +
                "W\n" +
                "10:00am - 10:50am\n" +
                "SH 1415\n" +
                "0 / 15\n" +
                "ENGL 10\n" +
                "W\n" +
                "11:00am - 11:50am\n" +
                "SH 1415\n" +
                "1 / 15\n" +
                "ENGL 10\n" +
                "W\n" +
                "5:00pm - 5:50pm\n" +
                "GIRV 2128\n" +
                "1 / 15\n" +
                "ENGL 10\n" +
                "W\n" +
                "6:00pm - 6:50pm\n" +
                "GIRV 2116\n" +
                "0 / 15\n" +
                "ENGL 10\n" +
                "R\n" +
                "8:00 am - 8:50 am\n" +
                "SH 2635\n" +
                "0 / 15\n" +
                "ENGL 10\n" +
                "R\n" +
                "9:00 am - 9:50 am\n" +
                "SH 2635\n" +
                "1 / 15\n" +
                "ENGL 10\n" +
                "R\n" +
                "10:00am - 10:50am\n" +
                "SH 2635\n" +
                "0 / 15\n" +
                "ENGL 10\n" +
                "R\n" +
                "11:00am - 11:50am\n" +
                "PHELP3523\n" +
                "0 / 15\n" +
                "ENGL 10\n" +
                "R\n" +
                "3:00pm - 3:50pm\n" +
                "SH 2635\n" +
                "1 / 15\n" +
                "ENGL 10\n" +
                "R\n" +
                "4:00pm - 4:50pm\n" +
                "SH 2635\n" +
                "1 / 15\n" +
                "ENGL 15\n" +
                "M W\n" +
                "11:00am - 12:15pm\n" +
                "EMBARHALL\n" +
                "44 / 160\n" +
                "ENGL 15\n" +
                "M\n" +
                "5:00pm - 5:50pm\n" +
                "SH 1415\n" +
                "13 / 20\n" +
                "ENGL 15\n" +
                "M\n" +
                "6:00pm - 6:50pm\n" +
                "SH 1415\n" +
                "0 / 20\n" +
                "ENGL 15\n" +
                "T\n" +
                "5:00pm - 5:50pm\n" +
                "SH 1415\n" +
                "2 / 20\n" +
                "ENGL 15\n" +
                "T\n" +
                "6:00pm - 6:50pm\n" +
                "SH 1415\n" +
                "4 / 20\n" +
                "ENGL 15\n" +
                "W\n" +
                "8:00 am - 8:50 am\n" +
                "SH 1415\n" +
                "1 / 20\n" +
                "ENGL 15\n" +
                "W\n" +
                "9:00 am - 9:50 am\n" +
                "SH 1415\n" +
                "8 / 20\n" +
                "ENGL 15\n" +
                "T\n" +
                "3:00pm - 3:50pm\n" +
                "SH 2635\n" +
                "10 / 20\n" +
                "ENGL 15\n" +
                "T\n" +
                "4:00pm - 4:50pm\n" +
                "SH 2635\n" +
                "6 / 20\n" +
                "ENGL 15S\n" +
                "M\n" +
                "1:00pm - 1:50pm\n" +
                "SH 2635\n" +
                "2 / 15\n" +
                "ENGL 22\n" +
                "M W F\n" +
                "2:00pm - 3:15pm\n" +
                "IV THEA1\n" +
                "137 / 300\n" +
                "ENGL 22S\n" +
                "M\n" +
                "6:00pm - 6:50pm\n" +
                "SH 2714\n" +
                "1 / 15\n" +
                "ENGL 38B\n" +
                "T R\n" +
                "11:00am - 12:15pm\n" +
                "GIRV 1004\n" +
                "31 / 100\n" +
                "ENGL 38B\n" +
                "T\n" +
                "5:00pm - 5:50pm\n" +
                "SH 2635\n" +
                "7 / 12\n" +
                "ENGL 38B\n" +
                "T\n" +
                "6:00pm - 6:50pm\n" +
                "SH 3707\n" +
                "1 / 12\n" +
                "ENGL 38B\n" +
                "W\n" +
                "8:00 am - 8:50 am\n" +
                "SH 3707\n" +
                "1 / 12\n" +
                "ENGL 38B\n" +
                "W\n" +
                "9:00 am - 9:50 am\n" +
                "SH 3707\n" +
                "11 / 12\n" +
                "ENGL 38B\n" +
                "W\n" +
                "5:00pm - 5:50pm\n" +
                "SH 3707\n" +
                "4 / 12\n" +
                "ENGL 38B\n" +
                "W\n" +
                "6:00pm - 6:50pm\n" +
                "SH 3707\n" +
                "1 / 12\n" +
                "ENGL 38B\n" +
                "R\n" +
                "8:00 am - 8:50 am\n" +
                "SH 1415\n" +
                "3 / 12\n" +
                "ENGL 38B\n" +
                "R\n" +
                "9:00 am - 9:50 am\n" +
                "SH 1415\n" +
                "3 / 12\n" +
                "ENGL 38BS\n" +
                "T\n" +
                "1:00pm - 1:50pm\n" +
                "GIRV 1106\n" +
                "0 / 7\n" +
                "ENGL 99\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "0 / 30\n" +
                "ENGL 101\n" +
                "T R\n" +
                "3:30pm - 4:45pm\n" +
                "LSB 1001\n" +
                "54 / 80\n" +
                "ENGL 101\n" +
                "T\n" +
                "5:00pm - 5:50pm\n" +
                "GIRV 2127\n" +
                "20 / 20\n" +
                "ENGL 101\n" +
                "T\n" +
                "6:00pm - 6:50pm\n" +
                "GIRV 2112\n" +
                "3 / 20\n" +
                "ENGL 101\n" +
                "R\n" +
                "10:00am - 10:50am\n" +
                "GIRV 2124\n" +
                "11 / 20\n" +
                "ENGL 101\n" +
                "R\n" +
                "11:00am - 11:50am\n" +
                "PHELP2514\n" +
                "20 / 20\n" +
                "ENGL 103A\n" +
                "T R\n" +
                "12:30pm - 1:45pm\n" +
                "PHELP3523\n" +
                "29 / 30\n" +
                "ENGL 103B\n" +
                "M W F\n" +
                "1:00pm - 1:50pm\n" +
                "LSB 1001\n" +
                "31 / 120\n" +
                "ENGL 103B\n" +
                "M\n" +
                "5:00pm - 5:50pm\n" +
                "GIRV 2123\n" +
                "6 / 20\n" +
                "ENGL 103B\n" +
                "M\n" +
                "6:00pm - 6:50pm\n" +
                "GIRV 2116\n" +
                "3 / 20\n" +
                "ENGL 103B\n" +
                "W\n" +
                "3:00pm - 3:50pm\n" +
                "GIRV 2116\n" +
                "13 / 20\n" +
                "ENGL 103B\n" +
                "W\n" +
                "4:00pm - 4:50pm\n" +
                "SH 1609\n" +
                "2 / 20\n" +
                "ENGL 103B\n" +
                "F\n" +
                "10:00am - 10:50am\n" +
                "SH 1415\n" +
                "3 / 20\n" +
                "ENGL 103B\n" +
                "F\n" +
                "11:00am - 11:50am\n" +
                "SH 1415\n" +
                "4 / 20\n" +
                "ENGL 104B\n" +
                "T R\n" +
                "2:00pm - 3:15pm\n" +
                "GIRV 1004\n" +
                "53 / 160\n" +
                "ENGL 104B\n" +
                "T\n" +
                "5:00pm - 5:50pm\n" +
                "GIRV 1119\n" +
                "17 / 20\n" +
                "ENGL 104B\n" +
                "T\n" +
                "6:00pm - 6:50pm\n" +
                "GIRV 2108\n" +
                "8 / 20\n" +
                "ENGL 104B\n" +
                "W\n" +
                "8:00 am - 8:50 am\n" +
                "SH 2635\n" +
                "0 / 20\n" +
                "ENGL 104B\n" +
                "W\n" +
                "9:00 am - 9:50 am\n" +
                "HSSB 1236\n" +
                "17 / 20\n" +
                "ENGL 104B\n" +
                "W\n" +
                "5:00pm - 5:50pm\n" +
                "SH 1415\n" +
                "4 / 20\n" +
                "ENGL 104B\n" +
                "W\n" +
                "6:00pm - 6:50pm\n" +
                "SH 1415\n" +
                "2 / 20\n" +
                "ENGL 104B\n" +
                "R\n" +
                "8:00 am - 8:50 am\n" +
                "GIRV 2112\n" +
                "1 / 20\n" +
                "ENGL 104B\n" +
                "R\n" +
                "9:00 am - 9:50 am\n" +
                "GIRV 2119\n" +
                "4 / 20\n" +
                "ENGL 104BS\n" +
                "R\n" +
                "12:30pm - 1:20pm\n" +
                "SH 2617\n" +
                "4 / 15\n" +
                "ENGL 110A\n" +
                "M W\n" +
                "5:00pm - 6:15pm\n" +
                "HSSB 2201\n" +
                "11 / 15\n" +
                "ENGL 121\n" +
                "M W\n" +
                "2:00pm - 3:15pm\n" +
                "GIRV 1112\n" +
                "30 / 30\n" +
                "ENGL 122AP\n" +
                "T R\n" +
                "12:30pm - 1:45pm\n" +
                "PHELP3519\n" +
                "30 / 30\n" +
                "ENGL 122CC\n" +
                "M W\n" +
                "12:30pm - 1:45pm\n" +
                "ARTS 1356\n" +
                "15 / 15\n" +
                "ENGL 128PT\n" +
                "M W\n" +
                "12:30pm - 1:45pm\n" +
                "GIRV 1112\n" +
                "30 / 30\n" +
                "ENGL 132MT\n" +
                "T R\n" +
                "3:30pm - 4:45pm\n" +
                "GIRV 1112\n" +
                "26 / 30\n" +
                "ENGL 146MR\n" +
                "T R\n" +
                "12:30pm - 1:45pm\n" +
                "SH 2635\n" +
                "12 / 20\n" +
                "ENGL 147OM\n" +
                "M W\n" +
                "9:30 am - 10:45am\n" +
                "SH 2635\n" +
                "15 / 20\n" +
                "ENGL 165EM\n" +
                "T R\n" +
                "11:00am - 12:15pm\n" +
                "SH 2635\n" +
                "20 / 20\n" +
                "ENGL 165FS\n" +
                "T R\n" +
                "2:00pm - 3:15pm\n" +
                "SH 1415\n" +
                "28 / 30\n" +
                "ENGL 165IF\n" +
                "T R\n" +
                "12:30pm - 1:45pm\n" +
                "GIRV 1112\n" +
                "30 / 30\n" +
                "ENGL 187AA\n" +
                "T R\n" +
                "9:30 am - 10:45am\n" +
                "SH 1430\n" +
                "30 / 30\n" +
                "ENGL 193\n" +
                "T R\n" +
                "3:30pm - 4:45pm\n" +
                "EMBARHALL\n" +
                "150 / 150\n" +
                "ENGL 193\n" +
                "T\n" +
                "5:00pm - 5:50pm\n" +
                "SH 1609\n" +
                "20 / 20\n" +
                "ENGL 193\n" +
                "T\n" +
                "6:00pm - 6:50pm\n" +
                "SH 1609\n" +
                "13 / 20\n" +
                "ENGL 193\n" +
                "W\n" +
                "8:00 am - 8:50 am\n" +
                "GIRV 2116\n" +
                "6 / 20\n" +
                "ENGL 193\n" +
                "W\n" +
                "9:00 am - 9:50 am\n" +
                "HSSB 1232\n" +
                "20 / 20\n" +
                "ENGL 193\n" +
                "W\n" +
                "3:00pm - 3:50pm\n" +
                "SH 1415\n" +
                "20 / 20\n" +
                "ENGL 193\n" +
                "W\n" +
                "4:00pm - 4:50pm\n" +
                "SH 1415\n" +
                "19 / 20\n" +
                "ENGL 193\n" +
                "R\n" +
                "8:00 am - 8:50 am\n" +
                "HSSB 1236\n" +
                "3 / 20\n" +
                "ENGL 193\n" +
                "R\n" +
                "9:00 am - 9:50 am\n" +
                "HSSB 1232\n" +
                "9 / 20\n" +
                "ENGL 193\n" +
                "R\n" +
                "12:00pm - 12:50pm\n" +
                "SH 1415\n" +
                "20 / 20\n" +
                "ENGL 193\n" +
                "R\n" +
                "1:00pm - 1:50pm\n" +
                "SH 1415\n" +
                "20 / 20\n" +
                "ENGL 193S\n" +
                "T\n" +
                "5:00pm - 5:50pm\n" +
                "SH 2617\n" +
                "3 / 15\n" +
                "ENGL 195I\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "0 / 25\n" +
                "ENGL 196\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "0 / 20\n" +
                "ENGL 197\n" +
                "T R\n" +
                "3:30pm - 4:45pm\n" +
                "SH 2617\n" +
                "12 / 15\n" +
                "ENGL 197\n" +
                "T R\n" +
                "11:00am - 12:15pm\n" +
                "SH 2617\n" +
                "0 / 15\n" +
                "ENGL 197\n" +
                "T R\n" +
                "9:30 am - 10:45am\n" +
                "SH 2714\n" +
                "2 / 15\n" +
                "ENGL 197\n" +
                "W\n" +
                "12:30pm - 2:50pm\n" +
                "SH 2617\n" +
                "6 / 15\n" +
                "ENGL 197\n" +
                "T R\n" +
                "9:30 am - 10:45am\n" +
                "SH 2617\n" +
                "11 / 15\n" +
                "ENGL 197\n" +
                "M\n" +
                "12:30pm - 2:50pm\n" +
                "SH 2617\n" +
                "16 / 15\n" +
                "ENGL 199\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "0 / 40\n" +
                "ENGL 199RA\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "0 / 40\n" +
                "ENV S 1\n" +
                "T R\n" +
                "12:30pm - 1:45pm\n" +
                "MUSICLLCH\n" +
                "257 / 257\n" +
                "ENV S 1\n" +
                "W\n" +
                "9:00 am - 9:50 am\n" +
                "HSSB 4202\n" +
                "24 / 24\n" +
                "ENV S 1\n" +
                "M\n" +
                "10:00am - 10:50am\n" +
                "HSSB 3202\n" +
                "24 / 24\n" +
                "ENV S 1\n" +
                "T\n" +
                "6:00pm - 6:50pm\n" +
                "HSSB 4202\n" +
                "7 / 24\n" +
                "ENV S 1\n" +
                "W\n" +
                "8:00 am - 8:50 am\n" +
                "HSSB 3202\n" +
                "5 / 24\n" +
                "ENV S 1\n" +
                "W\n" +
                "6:00pm - 6:50pm\n" +
                "HSSB 4202\n" +
                "4 / 24\n" +
                "ENV S 1\n" +
                "R\n" +
                "8:00 am - 8:50 am\n" +
                "HSSB 4201\n" +
                "4 / 24\n" +
                "ENV S 1\n" +
                "T\n" +
                "5:00pm - 5:50pm\n" +
                "ARTS 1356\n" +
                "15 / 24\n" +
                "ENV S 1\n" +
                "W\n" +
                "5:00pm - 5:50pm\n" +
                "ARTS 1356\n" +
                "7 / 24\n" +
                "ENV S 1\n" +
                "T\n" +
                "8:00 am - 8:50 am\n" +
                "HSSB 3202\n" +
                "2 / 24\n" +
                "ENV S 1\n" +
                "M\n" +
                "9:00 am - 9:50 am\n" +
                "HSSB 3202\n" +
                "12 / 24\n" +
                "ENV S 1\n" +
                "M\n" +
                "12:00pm - 12:50pm\n" +
                "HSSB 3201\n" +
                "24 / 24\n" +
                "ENV S 1\n" +
                "W\n" +
                "4:00pm - 4:50pm\n" +
                "GIRV 1119\n" +
                "24 / 24\n" +
                "ENV S 1\n" +
                "T\n" +
                "7:00pm - 7:50pm\n" +
                "HSSB 2251\n" +
                "3 / 24\n" +
                "ENV S 1\n" +
                "M\n" +
                "2:00pm - 2:50pm\n" +
                "NH 1109\n" +
                "24 / 24\n" +
                "ENV S 1\n" +
                "T\n" +
                "3:00pm - 3:50pm\n" +
                "HSSB 1211\n" +
                "23 / 24\n" +
                "ENV S 1\n" +
                "T\n" +
                "2:00pm - 2:50pm\n" +
                "GIRV 2119\n" +
                "24 / 24\n" +
                "ENV S 1\n" +
                "R\n" +
                "11:00am - 11:50am\n" +
                "LSB 1101\n" +
                "24 / 24\n" +
                "ENV S 1\n" +
                "M\n" +
                "6:00pm - 6:50pm\n" +
                "HSSB 4201\n" +
                "7 / 24\n" +
                "ENV S 1H\n" +
                "W\n" +
                "3:00pm - 3:50pm\n" +
                "HSSB 1207\n" +
                "0 / 26\n" +
                "ENV S 95\n" +
                "M\n" +
                "1:00pm - 3:50pm\n" +
                "HARDR1013\n" +
                "12 / 12\n" +
                "ENV S 95\n" +
                "T\n" +
                "1:00pm - 3:50pm\n" +
                "HARDR1013\n" +
                "12 / 12\n" +
                "ENV S 95\n" +
                "W\n" +
                "9:00 am - 11:50am\n" +
                "HARDR1013\n" +
                "3 / 12\n" +
                "ENV S 95\n" +
                "F\n" +
                "1:00pm - 3:50pm\n" +
                "HARDR1013\n" +
                "5 / 12\n" +
                "ENV S 96\n" +
                "R\n" +
                "2:00pm - 4:50pm\n" +
                "HARDR1035\n" +
                "4 / 6\n" +
                "ENV S 100\n" +
                "M W F\n" +
                "9:00 am - 9:50 am\n" +
                "BRDA 1610\n" +
                "185 / 216\n" +
                "ENV S 100\n" +
                "T\n" +
                "6:00pm - 6:50pm\n" +
                "GIRV 2116\n" +
                "24 / 24\n" +
                "ENV S 100\n" +
                "T\n" +
                "7:00pm - 7:50pm\n" +
                "GIRV 2116\n" +
                "10 / 24\n" +
                "ENV S 100\n" +
                "T\n" +
                "3:00pm - 3:50pm\n" +
                "HSSB 1233\n" +
                "24 / 24\n" +
                "ENV S 100\n" +
                "W\n" +
                "12:00pm - 12:50pm\n" +
                "HSSB 3201\n" +
                "24 / 24\n" +
                "ENV S 100\n" +
                "W\n" +
                "1:00pm - 1:50pm\n" +
                "PHELP2536\n" +
                "24 / 24\n" +
                "ENV S 100\n" +
                "W\n" +
                "4:00pm - 4:50pm\n" +
                "GIRV 2120\n" +
                "24 / 24\n" +
                "ENV S 100\n" +
                "R\n" +
                "2:00pm - 2:50pm\n" +
                "PHELP2536\n" +
                "24 / 24\n" +
                "ENV S 100\n" +
                "R\n" +
                "3:00pm - 3:50pm\n" +
                "HSSB 1231\n" +
                "10 / 24\n" +
                "ENV S 100\n" +
                "R\n" +
                "6:00pm - 6:50pm\n" +
                "TD-W 2600\n" +
                "21 / 24\n" +
                "ENV S 106\n" +
                "T R\n" +
                "2:00pm - 3:15pm\n" +
                "BUCHN1920\n" +
                "120 / 120\n" +
                "ENV S 106\n" +
                "W\n" +
                "9:00 am - 9:50 am\n" +
                "GIRV 1108\n" +
                "20 / 20\n" +
                "ENV S 106\n" +
                "W\n" +
                "3:00pm - 3:50pm\n" +
                "HSSB 3202\n" +
                "20 / 20\n" +
                "ENV S 106\n" +
                "W\n" +
                "4:00pm - 4:50pm\n" +
                "HSSB 4202\n" +
                "20 / 20\n" +
                "ENV S 106\n" +
                "T\n" +
                "5:00pm - 5:50pm\n" +
                "LSB 1101\n" +
                "20 / 20\n" +
                "ENV S 106\n" +
                "R\n" +
                "9:00 am - 9:50 am\n" +
                "HSSB 1236\n" +
                "20 / 20\n" +
                "ENV S 106\n" +
                "R\n" +
                "11:00am - 11:50am\n" +
                "GIRV 1108\n" +
                "20 / 20\n" +
                "ENV S 108W\n" +
                "T R\n" +
                "3:30pm - 4:45pm\n" +
                "TD-W 2600\n" +
                "18 / 18\n" +
                "ENV S 114A\n" +
                "M W\n" +
                "2:00pm - 3:15pm\n" +
                "ELLSN3621\n" +
                "12 / 12\n" +
                "ENV S 114A\n" +
                "F\n" +
                "1:00pm - 3:50pm\n" +
                "PHELP2525\n" +
                "12 / 12\n" +
                "ENV S 118\n" +
                "M W\n" +
                "6:30pm - 7:45pm\n" +
                "GIRV 1116\n" +
                "8 / 40\n" +
                "ENV S 119\n" +
                "F\n" +
                "1:00pm - 5:50pm\n" +
                "OFF CAMP\n" +
                "12 / 12\n" +
                "ENV S 119\n" +
                "W\n" +
                "2:00pm - 3:50pm\n" +
                "SH 1609\n" +
                "12 / 12\n" +
                "ENV S 119\n" +
                "W\n" +
                "5:00pm - 5:50pm\n" +
                "GIRV 2110\n" +
                "6 / 6\n" +
                "ENV S 119\n" +
                "R\n" +
                "6:00pm - 6:50pm\n" +
                "GIRV 2110\n" +
                "6 / 6\n" +
                "ENV S 120\n" +
                "T R\n" +
                "11:00am - 12:15pm\n" +
                "GIRV 2120\n" +
                "40 / 40\n" +
                "ENV S 129\n" +
                "T R\n" +
                "8:00 am - 9:15 am\n" +
                "GIRV 2129\n" +
                "22 / 40\n" +
                "ENV S 130A\n" +
                "M W\n" +
                "12:30pm - 1:45pm\n" +
                "GIRV 1115\n" +
                "34 / 40\n" +
                "ENV S 131\n" +
                "T R\n" +
                "12:30pm - 1:45pm\n" +
                "GIRV 2120\n" +
                "40 / 40\n" +
                "ENV S 146\n" +
                "T R\n" +
                "11:00am - 12:15pm\n" +
                "GIRV 2123\n" +
                "40 / 40\n" +
                "ENV S 149\n" +
                "T R\n" +
                "9:30 am - 10:45am\n" +
                "PHELP3515\n" +
                "52 / 60\n" +
                "ENV S 149\n" +
                "F\n" +
                "9:00 am - 9:50 am\n" +
                "ELLSN3621\n" +
                "12 / 20\n" +
                "ENV S 149\n" +
                "F\n" +
                "10:00am - 10:50am\n" +
                "HSSB 1237\n" +
                "20 / 20\n" +
                "ENV S 149\n" +
                "R\n" +
                "5:00pm - 5:50pm\n" +
                "SH 1430\n" +
                "20 / 20\n" +
                "ENV S 165A\n" +
                "T R\n" +
                "3:30pm - 4:45pm\n" +
                "CHEM 1171\n" +
                "34 / 95\n" +
                "ENV S 171\n" +
                "M W F\n" +
                "12:00pm - 12:50pm\n" +
                "CHEM 1171\n" +
                "21 / 48\n" +
                "ENV S 171\n" +
                "F\n" +
                "8:00 am - 8:50 am\n" +
                "387 103\n" +
                "2 / 12\n" +
                "ENV S 171\n" +
                "F\n" +
                "11:00am - 11:50am\n" +
                "GIRV 1115\n" +
                "11 / 12\n" +
                "ENV S 171\n" +
                "R\n" +
                "5:00pm - 5:50pm\n" +
                "ARTS 1353\n" +
                "8 / 12\n" +
                "ENV S 171\n" +
                "R\n" +
                "6:00pm - 6:50pm\n" +
                "PHELP2532\n" +
                "0 / 12\n" +
                "ENV S 172\n" +
                "R\n" +
                "7:00pm - 9:50pm\n" +
                "NH 1109\n" +
                "22 / 40\n" +
                "ENV S 173\n" +
                "M W F\n" +
                "11:00am - 11:50am\n" +
                "BUCHN1910\n" +
                "23 / 150\n" +
                "ENV S 178\n" +
                "M W F\n" +
                "11:00am - 11:50am\n" +
                "IV THEA2\n" +
                "32 / 60\n" +
                "ENV S 178\n" +
                "F\n" +
                "12:00pm - 12:50pm\n" +
                "HSSB 1210\n" +
                "10 / 10\n" +
                "ENV S 178\n" +
                "F\n" +
                "1:00pm - 1:50pm\n" +
                "HSSB 4202\n" +
                "8 / 10\n" +
                "ENV S 178\n" +
                "F\n" +
                "2:00pm - 2:50pm\n" +
                "GIRV 1108\n" +
                "0 / 10\n" +
                "ENV S 178\n" +
                "F\n" +
                "3:00pm - 3:50pm\n" +
                "GIRV 1108\n" +
                "0 / 10\n" +
                "ENV S 178\n" +
                "M\n" +
                "9:00 am - 9:50 am\n" +
                "GIRV 1108\n" +
                "4 / 10\n" +
                "ENV S 178\n" +
                "M\n" +
                "10:00am - 10:50am\n" +
                "HSSB 2201\n" +
                "10 / 10\n" +
                "ENV S 190\n" +
                "W\n" +
                "5:00pm - 6:15pm\n" +
                "BUCHN1910\n" +
                "130 / 225\n" +
                "ENV S 191\n" +
                "T\n" +
                "9:00 am - 11:50am\n" +
                "HARDR1015\n" +
                "6 / 8\n" +
                "ENV S 191\n" +
                "R\n" +
                "9:00 am - 11:50am\n" +
                "HARDR1015\n" +
                "6 / 8\n" +
                "ENV S 192\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "0 / 35\n" +
                "ENV S 193CL\n" +
                "M W\n" +
                "12:30pm - 1:45pm\n" +
                "ARTS 1356\n" +
                "8 / 19\n" +
                "ENV S 193CO\n" +
                "T\n" +
                "7:00pm - 8:50pm\n" +
                "LSB 1101\n" +
                "10 / 25\n" +
                "ENV S 193EC\n" +
                "M\n" +
                "2:00pm - 4:50pm\n" +
                "GIRV 2110\n" +
                "12 / 12\n" +
                "ENV S 193GE\n" +
                "T R\n" +
                "3:30pm - 4:45pm\n" +
                "GIRV 1115\n" +
                "18 / 40\n" +
                "ENV S 194GB\n" +
                "W\n" +
                "3:00pm - 4:50pm\n" +
                "BREN 4016\n" +
                "0 / 24\n" +
                "ENV S 196\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "0 / 10\n" +
                "ENV S 197\n" +
                "M\n" +
                "2:00pm - 4:20pm\n" +
                "HSSB 1236\n" +
                "0 / 26\n" +
                "ENV S 199\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "0 / 30\n" +
                "ENV S 199RA\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "0 / 30\n" +
                "ESS 2\n" +
                "T R\n" +
                "11:00am - 12:15pm\n" +
                "BUCHN1940\n" +
                "149 / 149\n" +
                "ESS 3\n" +
                "T R\n" +
                "9:30 am - 10:45am\n" +
                "IV THEA1\n" +
                "65 / 400\n" +
                "ESS 3\n" +
                "T R\n" +
                "12:30pm - 1:45pm\n" +
                "TD-W 1701\n" +
                "82 / 100\n" +
                "ESS 4A\n" +
                "M\n" +
                "9:00 am - 9:50 am\n" +
                "RECEN2103\n" +
                "40 / 40\n" +
                "ESS 4A\n" +
                "M\n" +
                "10:00am - 10:50am\n" +
                "RECENPVGYM\n" +
                "40 / 40\n" +
                "ESS 4A\n" +
                "W\n" +
                "9:00 am - 10:50am\n" +
                "RECENPVGYM\n" +
                "40 / 40\n" +
                "ESS 7\n" +
                "M\n" +
                "11:00am - 11:50am\n" +
                "RECEN2103\n" +
                "24 / 24\n" +
                "ESS 7\n" +
                "W\n" +
                "11:00am - 12:50pm\n" +
                "RGYM 2120\n" +
                "24 / 24\n" +
                "ESS 9\n" +
                "M W\n" +
                "4:00pm - 4:50pm\n" +
                "RECEN1501\n" +
                "49 / 50\n" +
                "ESS 96\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "0 / 20\n" +
                "ESS 98\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "0 / 20\n" +
                "ESS 130\n" +
                "M W\n" +
                "9:00 am - 10:15am\n" +
                "RECEN1501\n" +
                "56 / 55\n" +
                "ESS 130\n" +
                "T R\n" +
                "5:30pm - 6:45pm\n" +
                "RECEN1501\n" +
                "55 / 55\n" +
                "ESS 131\n" +
                "T R\n" +
                "9:30 am - 10:45am\n" +
                "RECEN1501\n" +
                "55 / 55\n" +
                "ESS 131\n" +
                "T R\n" +
                "12:30pm - 1:45pm\n" +
                "RECEN1501\n" +
                "55 / 55\n" +
                "ESS 140\n" +
                "W F\n" +
                "2:00pm - 3:15pm\n" +
                "RECEN1501\n" +
                "55 / 55\n" +
                "ESS 160\n" +
                "M W\n" +
                "12:00pm - 1:15pm\n" +
                "RECEN1501\n" +
                "51 / 50\n" +
                "ESS 160\n" +
                "T R\n" +
                "3:30pm - 4:45pm\n" +
                "RECEN1501\n" +
                "10 / 50\n" +
                "ESS 185\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "0 / 10\n" +
                "ESS 193\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "0 / 20\n" +
                "ESS 193\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "0 / 1\n" +
                "ESS 199\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "0 / 20\n" +
                "ES 1- 2\n" +
                "MTWRF\n" +
                "1:00pm - 5:50pm\n" +
                "BSBL FIELD\n" +
                "14 / 40\n" +
                "ES 1- 4A\n" +
                "T R\n" +
                "9:00 am - 9:50 am\n" +
                "RECENPVGYM\n" +
                "40 / 40\n" +
                "ES 1- 4B\n" +
                "T R\n" +
                "10:00am - 10:50am\n" +
                "RECENPVGYM\n" +
                "31 / 40\n" +
                "ES 1- 6A\n" +
                "M W\n" +
                "10:00am - 10:50am\n" +
                "RECENPVGYM\n" +
                "30 / 30\n" +
                "ES 1- 6A\n" +
                "M W\n" +
                "1:00pm - 1:50pm\n" +
                "RECENPVGYM\n" +
                "30 / 30\n" +
                "ES 1- 6A\n" +
                "T R\n" +
                "1:00pm - 1:50pm\n" +
                "RECENPVGYM\n" +
                "20 / 20\n" +
                "ES 1- 6B\n" +
                "M W\n" +
                "11:00am - 11:50am\n" +
                "RECENPVGYM\n" +
                "15 / 15\n" +
                "ES 1- 6B\n" +
                "M W\n" +
                "2:00pm - 2:50pm\n" +
                "RECENPVGYM\n" +
                "15 / 15\n" +
                "ES 1- 6B\n" +
                "T R\n" +
                "2:00pm - 2:50pm\n" +
                "RECENPVGYM\n" +
                "20 / 20\n" +
                "ES 1- 6C\n" +
                "M W\n" +
                "11:00am - 11:50am\n" +
                "RECENPVGYM\n" +
                "15 / 15\n" +
                "ES 1- 6C\n" +
                "M W\n" +
                "2:00pm - 2:50pm\n" +
                "RECENPVGYM\n" +
                "15 / 15\n" +
                "ES 1- 6C\n" +
                "T R\n" +
                "2:00pm - 2:50pm\n" +
                "RECENPVGYM\n" +
                "20 / 40\n" +
                "ES 1- 7A\n" +
                "T\n" +
                "10:30am - 12:20pm\n" +
                "SB HARBR\n" +
                "15 / 15\n" +
                "ES 1- 7A\n" +
                "T\n" +
                "12:30pm - 2:20pm\n" +
                "SB HARBR\n" +
                "15 / 15\n" +
                "ES 1- 7A\n" +
                "R\n" +
                "10:30am - 12:20pm\n" +
                "SB HARBR\n" +
                "15 / 15\n" +
                "ES 1- 7A\n" +
                "R\n" +
                "12:30pm - 2:20pm\n" +
                "SB HARBR\n" +
                "15 / 15\n" +
                "ES 1- 7A\n" +
                "F\n" +
                "10:30am - 12:20pm\n" +
                "SB HARBR\n" +
                "15 / 15\n" +
                "ES 1- 7B\n" +
                "F\n" +
                "12:30pm - 2:20pm\n" +
                "SB HARBR\n" +
                "15 / 15\n" +
                "ES 1- 9A\n" +
                "M\n" +
                "10:00am - 11:50am\n" +
                "ZODO BOWL\n" +
                "18 / 100\n" +
                "ES 1- 9A\n" +
                "R\n" +
                "1:00pm - 2:50pm\n" +
                "ZODO BOWL\n" +
                "43 / 100\n" +
                "ES 1- 10A\n" +
                "T R\n" +
                "8:00 am - 8:50 am\n" +
                "RGYM 1430\n" +
                "47 / 55\n" +
                "ES 1- 10A\n" +
                "M W\n" +
                "9:00 am - 9:50 am\n" +
                "RGYM 1430\n" +
                "55 / 55\n" +
                "ES 1- 10A\n" +
                "T R\n" +
                "11:00am - 11:50am\n" +
                "RGYM 1430\n" +
                "55 / 55\n" +
                "ES 1- 10B\n" +
                "T R\n" +
                "9:00 am - 9:50 am\n" +
                "RGYM 1430\n" +
                "48 / 55\n" +
                "ES 1- 10C\n" +
                "M W\n" +
                "10:00am - 10:50am\n" +
                "RGYM 1430\n" +
                "35 / 35\n" +
                "ES 1- 11\n" +
                "MTWRF\n" +
                "12:00pm - 2:45pm\n" +
                "EVENTCENTR\n" +
                "2 / 20\n" +
                "ES 1- 11\n" +
                "MTWRF\n" +
                "3:00pm - 5:30pm\n" +
                "EVENTCENTR\n" +
                "2 / 20\n" +
                "ES 1- 12\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "0 / 22\n" +
                "ES 1- 12\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "0 / 22\n" +
                "ES 1- 13A\n" +
                "T R\n" +
                "8:30 am - 9:20 am\n" +
                "RGYM 2120\n" +
                "30 / 30\n" +
                "ES 1- 13A\n" +
                "T R\n" +
                "9:30 am - 10:20am\n" +
                "RGYM 2120\n" +
                "29 / 30\n" +
                "ES 1- 14\n" +
                "MTWRF\n" +
                "2:00pm - 5:50pm\n" +
                "ICA FIELD\n" +
                "0 / 30\n" +
                "ES 1- 14\n" +
                "MTWRF\n" +
                "2:00pm - 5:50pm\n" +
                "ICA FIELD\n" +
                "1 / 50\n" +
                "ES 1- 16A\n" +
                "M W\n" +
                "11:00am - 11:50am\n" +
                "RGYM 2320\n" +
                "35 / 100\n" +
                "ES 1- 16A\n" +
                "T R\n" +
                "10:00am - 10:50am\n" +
                "RGYM 2320\n" +
                "26 / 100\n" +
                "ES 1- 16A\n" +
                "T R\n" +
                "11:00am - 11:50am\n" +
                "RGYM 2320\n" +
                "16 / 100\n" +
                "ES 1- 16A\n" +
                "T R\n" +
                "1:00pm - 1:50pm\n" +
                "RGYM 2320\n" +
                "29 / 100\n" +
                "ES 1- 16B\n" +
                "M W\n" +
                "12:00pm - 12:50pm\n" +
                "RGYM 2320\n" +
                "20 / 100\n" +
                "ES 1- 22\n" +
                "MTWRF\n" +
                "2:00pm - 5:00pm\n" +
                "GOLF COURS\n" +
                "4 / 25\n" +
                "ES 1- 24A\n" +
                "T\n" +
                "10:00am - 11:50am\n" +
                "STORKFIELD\n" +
                "20 / 24\n" +
                "ES 1- 24A\n" +
                "T\n" +
                "12:00pm - 1:50pm\n" +
                "STORKFIELD\n" +
                "16 / 24\n" +
                "ES 1- 24A\n" +
                "M\n" +
                "12:00pm - 1:50pm\n" +
                "STORKFIELD\n" +
                "29 / 30\n" +
                "ES 1- 24A\n" +
                "W\n" +
                "10:00am - 11:50am\n" +
                "STORKFIELD\n" +
                "22 / 30\n" +
                "ES 1- 24B\n" +
                "M\n" +
                "10:00am - 11:50am\n" +
                "STORKFIELD\n" +
                "12 / 24\n" +
                "ES 1- 24B\n" +
                "W\n" +
                "12:00pm - 1:50pm\n" +
                "STORKFIELD\n" +
                "9 / 24\n" +
                "ES 1- 25A\n" +
                "T R\n" +
                "9:00 am - 9:50 am\n" +
                "RGYM 1270\n" +
                "25 / 25\n" +
                "ES 1- 25A\n" +
                "T R\n" +
                "2:00pm - 2:50pm\n" +
                "RGYM 1270\n" +
                "15 / 15\n" +
                "ES 1- 25B\n" +
                "T R\n" +
                "2:00pm - 2:50pm\n" +
                "RGYM 1270\n" +
                "10 / 10\n" +
                "ES 1- 26A\n" +
                "T R\n" +
                "1:00pm - 1:50pm\n" +
                "RGYM 1270\n" +
                "15 / 15\n" +
                "ES 1- 26B\n" +
                "T R\n" +
                "1:00pm - 1:50pm\n" +
                "RGYM 1270\n" +
                "10 / 10\n" +
                "ES 1- 29A\n" +
                "M W\n" +
                "9:00 am - 9:50 am\n" +
                "RECENCOURT\n" +
                "20 / 20\n" +
                "ES 1- 29A\n" +
                "M W\n" +
                "10:00am - 10:50am\n" +
                "RECENCOURT\n" +
                "20 / 20\n" +
                "ES 1- 29B\n" +
                "M W\n" +
                "11:00am - 11:50am\n" +
                "RECENCOURT\n" +
                "12 / 18\n" +
                "ES 1- 30A\n" +
                "T R\n" +
                "10:00am - 10:50am\n" +
                "RECENFIELD\n" +
                "25 / 25\n" +
                "ES 1- 30A\n" +
                "M W\n" +
                "10:00am - 10:50am\n" +
                "RECENFIELD\n" +
                "25 / 25\n" +
                "ES 1- 30A\n" +
                "M W\n" +
                "11:00am - 11:50am\n" +
                "RECENFIELD\n" +
                "25 / 25\n" +
                "ES 1- 30B\n" +
                "T R\n" +
                "11:00am - 11:50am\n" +
                "RECENFIELD\n" +
                "25 / 25\n" +
                "ES 1- 32\n" +
                "MTWRF\n" +
                "1:00pm - 5:00pm\n" +
                "SFTBLFIELD\n" +
                "4 / 30\n" +
                "ES 1- 33\n" +
                "MTWRF\n" +
                "2:00pm - 4:00pm\n" +
                "SWIM POOL\n" +
                "11 / 25\n" +
                "ES 1- 33\n" +
                "M W F\n" +
                "6:30 am - 8:30 am\n" +
                "SWIM POOL\n" +
                "11 / 25\n" +
                "ES 1- 33\n" +
                "MTWRF\n" +
                "2:00pm - 4:00pm\n" +
                "SWIM POOL\n" +
                "6 / 35\n" +
                "ES 1- 33\n" +
                "M W F\n" +
                "6:30 am - 8:30 am\n" +
                "SWIM POOL\n" +
                "6 / 35\n" +
                "ES 1- 34A\n" +
                "T R\n" +
                "10:00am - 10:50am\n" +
                "RECENPOOL\n" +
                "14 / 30\n" +
                "ES 1- 34A\n" +
                "T R\n" +
                "11:00am - 11:50am\n" +
                "RECENPOOL\n" +
                "17 / 30\n" +
                "ES 1- 34A\n" +
                "M W\n" +
                "11:00am - 11:50am\n" +
                "RECENPOOL\n" +
                "30 / 30\n" +
                "ES 1- 34B\n" +
                "T R\n" +
                "9:00 am - 9:50 am\n" +
                "RECENPOOL\n" +
                "8 / 30\n" +
                "ES 1- 34B\n" +
                "T R\n" +
                "11:00am - 11:50am\n" +
                "RECENPOOL\n" +
                "30 / 30\n" +
                "ES 1- 34B\n" +
                "M W\n" +
                "10:00am - 10:50am\n" +
                "RECENPOOL\n" +
                "24 / 30\n" +
                "ES 1- 36\n" +
                "T R\n" +
                "1:00pm - 1:50pm\n" +
                "RECENFIELD\n" +
                "0 / 50\n" +
                "ES 1- 36\n" +
                "M W\n" +
                "10:00am - 10:50am\n" +
                "RECENFIELD\n" +
                "49 / 50\n" +
                "ES 1- 37\n" +
                "MTWRF\n" +
                "2:00pm - 4:50pm\n" +
                "RGYM CTS\n" +
                "1 / 28\n" +
                "ES 1- 37\n" +
                "MTWRF\n" +
                "2:00pm - 4:50pm\n" +
                "RECENCTS\n" +
                "2 / 32\n" +
                "ES 1- 38A\n" +
                "M W\n" +
                "10:00am - 10:50am\n" +
                "RECENCTS\n" +
                "32 / 32\n" +
                "ES 1- 38A\n" +
                "M W\n" +
                "11:00am - 11:50am\n" +
                "RECENCTS\n" +
                "32 / 32\n" +
                "ES 1- 38A\n" +
                "T R\n" +
                "10:00am - 10:50am\n" +
                "RECENCTS\n" +
                "32 / 32\n" +
                "ES 1- 38B\n" +
                "M W\n" +
                "9:00 am - 9:50 am\n" +
                "RECENCTS\n" +
                "31 / 32\n" +
                "ES 1- 38C\n" +
                "T R\n" +
                "9:00 am - 9:50 am\n" +
                "RECENCTS\n" +
                "24 / 32\n" +
                "ES 1- 40A\n" +
                "M W\n" +
                "9:00 am - 9:50 am\n" +
                "RGYM 1220\n" +
                "34 / 50\n" +
                "ES 1- 40A\n" +
                "T R\n" +
                "9:00 am - 9:50 am\n" +
                "RGYM 1220\n" +
                "35 / 50\n" +
                "ES 1- 40B\n" +
                "M W\n" +
                "10:00am - 10:50am\n" +
                "RGYM 1220\n" +
                "25 / 25\n" +
                "ES 1- 40C\n" +
                "M W\n" +
                "10:00am - 10:50am\n" +
                "RGYM 1220\n" +
                "17 / 25\n" +
                "ES 1- 40C\n" +
                "T R\n" +
                "10:00am - 10:50am\n" +
                "RGYM 1220\n" +
                "15 / 50\n" +
                "ES 1- 42\n" +
                "MTWRF\n" +
                "2:00pm - 6:00pm\n" +
                "ICA FIELD\n" +
                "3 / 40\n" +
                "ES 1- 42\n" +
                "MTWRF\n" +
                "2:00pm - 6:00pm\n" +
                "ICA FIELD\n" +
                "1 / 60\n" +
                "ES 1- 43A\n" +
                "T R\n" +
                "10:00am - 10:50am\n" +
                "RGYM 1430\n" +
                "40 / 40\n" +
                "ES 1- 43A\n" +
                "T R\n" +
                "10:00am - 10:50am\n" +
                "RECENWTRM\n" +
                "40 / 40\n" +
                "ES 1- 43A\n" +
                "M W\n" +
                "10:00am - 10:50am\n" +
                "RECENWTRM\n" +
                "40 / 40\n" +
                "ES 1- 43A\n" +
                "M W\n" +
                "2:00pm - 2:50pm\n" +
                "RECENWTRM\n" +
                "40 / 40\n" +
                "ES 1- 43B\n" +
                "M W\n" +
                "11:00am - 11:50am\n" +
                "RECENWTRM\n" +
                "40 / 40\n" +
                "ES 1- 43B\n" +
                "T R\n" +
                "9:00 am - 9:50 am\n" +
                "RECENWTRM\n" +
                "29 / 40\n" +
                "ES 1- 43B\n" +
                "T R\n" +
                "11:00am - 11:50am\n" +
                "RECENWTRM\n" +
                "33 / 40\n" +
                "ES 1- 43D\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "69 / 631\n" +
                "ES 1- 43E\n" +
                "T R\n" +
                "1:00pm - 1:50pm\n" +
                "RECENWTRM\n" +
                "40 / 40\n" +
                "ES 1- 43E\n" +
                "T R\n" +
                "2:00pm - 2:50pm\n" +
                "RECENWTRM\n" +
                "40 / 40\n" +
                "ES 1- 43E\n" +
                "M W\n" +
                "1:00pm - 1:50pm\n" +
                "RECENWTRM\n" +
                "40 / 40\n" +
                "ES 1- 43E\n" +
                "M W\n" +
                "9:00 am - 9:50 am\n" +
                "RECENWTRM\n" +
                "40 / 40\n" +
                "ES 1- 45\n" +
                "M W F\n" +
                "11:30am - 2:00pm\n" +
                "SWIM POOL\n" +
                "5 / 30\n" +
                "ES 1- 45\n" +
                "T R\n" +
                "6:30 am - 8:30 am\n" +
                "SWIM POOL\n" +
                "5 / 30\n" +
                "ES 1- 45\n" +
                "MTWRF\n" +
                "8:30 am - 11:30am\n" +
                "SWIM POOL\n" +
                "5 / 30\n" +
                "ES 1- 47\n" +
                "MTWRF\n" +
                "11:00am - 1:30pm\n" +
                "RGYM 1220\n" +
                "0 / 30\n" +
                "ES 1- 47\n" +
                "MTWRF\n" +
                "1:30pm - 4:00pm\n" +
                "RGYM 1220\n" +
                "2 / 40\n" +
                "ES 1- 48\n" +
                "MTWRF\n" +
                "1:00pm - 4:00pm\n" +
                "RECENFIELD\n" +
                "11 / 45\n" +
                "ES 1- 48\n" +
                "MTWRF\n" +
                "2:00pm - 4:00pm\n" +
                "RECENFIELD\n" +
                "2 / 30\n" +
                "ES 1- 59A\n" +
                "M W\n" +
                "11:00am - 11:50am\n" +
                "RECENPOOL\n" +
                "18 / 18\n" +
                "ES 1- 59B\n" +
                "M W\n" +
                "11:00am - 11:50am\n" +
                "RECENPOOL\n" +
                "6 / 20\n" +
                "ES 1- 99\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "2 / 20\n" +
                "ES 1- 99\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "18 / 20\n" +
                "ES 1- 99\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "1 / 20\n" +
                "ES 1- 99\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "1 / 5\n" +
                "ES 1- 99\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "3 / 20\n" +
                "ES 1- 99\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "5 / 20\n" +
                "ES 1- 99\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "0 / 5\n" +
                "ES 1- 99\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "0 / 20\n" +
                "ES 1- 99\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "1 / 20\n" +
                "ES 1- 99\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "1 / 10\n" +
                "ES 1- 99\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "2 / 20\n" +
                "ES 1- 99\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "3 / 20\n" +
                "ES 1- 99\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "2 / 20\n" +
                "ES 1- 99\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "0 / 20\n" +
                "ES 1- 99\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "1 / 7\n" +
                "ES 1- 99\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "1 / 20\n" +
                "ES 1- 99\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "0 / 20\n" +
                "ES 1- 99\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "1 / 20\n" +
                "ES 1- 99\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "0 / 20\n" +
                "ES 1- 99\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "2 / 20\n" +
                "ES 1- 99\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "0 / 20\n" +
                "ES 1- 99\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "0 / 20\n" +
                "ES 1- 99\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "0 / 20\n" +
                "ES 1- 99\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "0 / 20\n" +
                "FEMST 20\n" +
                "T R\n" +
                "3:30pm - 4:45pm\n" +
                "BRDA 1610\n" +
                "140 / 140\n" +
                "FEMST 20\n" +
                "M\n" +
                "4:00pm - 4:50pm\n" +
                "HSSB 3202\n" +
                "19 / 19\n" +
                "FEMST 20\n" +
                "M\n" +
                "5:00pm - 5:50pm\n" +
                "GIRV 1119\n" +
                "17 / 19\n" +
                "FEMST 20\n" +
                "M\n" +
                "6:00pm - 6:50pm\n" +
                "GIRV 2119\n" +
                "6 / 19\n" +
                "FEMST 20\n" +
                "W\n" +
                "9:00 am - 9:50 am\n" +
                "\n" +
                "10 / 20\n" +
                "FEMST 20\n" +
                "T\n" +
                "5:00pm - 5:50pm\n" +
                "\n" +
                "20 / 20\n" +
                "FEMST 20\n" +
                "M\n" +
                "3:00pm - 3:50pm\n" +
                "LSB 1101\n" +
                "19 / 19\n" +
                "FEMST 20\n" +
                "M\n" +
                "9:00 am - 9:50 am\n" +
                "HSSB 1223\n" +
                "6 / 20\n" +
                "FEMST 20\n" +
                "M\n" +
                "10:00am - 10:50am\n" +
                "PHELP2532\n" +
                "20 / 20\n" +
                "FEMST 20\n" +
                "W\n" +
                "8:00 am - 8:50 am\n" +
                "LSB 1101\n" +
                "5 / 19\n" +
                "FEMST 20\n" +
                "F\n" +
                "11:00am - 11:50am\n" +
                "HSSB 1227\n" +
                "5 / 20\n" +
                "FEMST 20\n" +
                "F\n" +
                "12:00pm - 12:50pm\n" +
                "HSSB 1214\n" +
                "8 / 20\n" +
                "FEMST 20\n" +
                "F\n" +
                "1:00pm - 1:50pm\n" +
                "HSSB 1224\n" +
                "5 / 20\n" +
                "FEMST 40\n" +
                "T R\n" +
                "9:30 am - 10:45am\n" +
                "EMBARHALL\n" +
                "140 / 140\n" +
                "FEMST 40\n" +
                "M\n" +
                "5:00pm - 7:50pm\n" +
                "EMBARHALL\n" +
                "140 / 140\n" +
                "FEMST 40\n" +
                "M\n" +
                "1:00pm - 1:50pm\n" +
                "SH 1430\n" +
                "19 / 19\n" +
                "FEMST 40\n" +
                "M\n" +
                "3:00pm - 3:50pm\n" +
                "GIRV 1108\n" +
                "20 / 20\n" +
                "FEMST 40\n" +
                "M\n" +
                "2:00pm - 2:50pm\n" +
                "LSB 1101\n" +
                "20 / 20\n" +
                "FEMST 40\n" +
                "W\n" +
                "8:00 am - 8:50 am\n" +
                "GIRV 1108\n" +
                "13 / 19\n" +
                "FEMST 40\n" +
                "M\n" +
                "9:00 am - 9:50 am\n" +
                "HSSB 1215\n" +
                "16 / 20\n" +
                "FEMST 40\n" +
                "M\n" +
                "1:00pm - 1:50pm\n" +
                "PHELP2536\n" +
                "20 / 20\n" +
                "FEMST 40\n" +
                "F\n" +
                "8:00 am - 8:50 am\n" +
                "GIRV 1108\n" +
                "1 / 19\n" +
                "FEMST 40\n" +
                "F\n" +
                "9:00 am - 9:50 am\n" +
                "HSSB 1224\n" +
                "2 / 20\n" +
                "FEMST 40\n" +
                "F\n" +
                "10:00am - 10:50am\n" +
                "HSSB 1224\n" +
                "8 / 20\n" +
                "FEMST 40\n" +
                "F\n" +
                "11:00am - 11:50am\n" +
                "HSSB 1228\n" +
                "8 / 19\n" +
                "FEMST 40\n" +
                "F\n" +
                "12:00pm - 12:50pm\n" +
                "HSSB 1215\n" +
                "7 / 19\n" +
                "FEMST 40\n" +
                "F\n" +
                "10:00am - 10:50am\n" +
                "HSSB 1223\n" +
                "6 / 20\n" +
                "FEMST 60\n" +
                "T R\n" +
                "8:00 am - 9:15 am\n" +
                "HFH 1104\n" +
                "81 / 105\n" +
                "FEMST 60\n" +
                "M\n" +
                "8:00 am - 8:50 am\n" +
                "GIRV 1108\n" +
                "4 / 20\n" +
                "FEMST 60\n" +
                "M\n" +
                "9:00 am - 9:50 am\n" +
                "HSSB 4202\n" +
                "12 / 20\n" +
                "FEMST 60\n" +
                "M\n" +
                "10:00am - 10:50am\n" +
                "PHELP2536\n" +
                "20 / 20\n" +
                "FEMST 60\n" +
                "W\n" +
                "2:00pm - 2:50pm\n" +
                "HSSB 3202\n" +
                "20 / 20\n" +
                "FEMST 60\n" +
                "W\n" +
                "3:00pm - 3:50pm\n" +
                "HSSB 1233\n" +
                "8 / 20\n" +
                "FEMST 60\n" +
                "W\n" +
                "4:00pm - 4:50pm\n" +
                "PHELP1448\n" +
                "9 / 20\n" +
                "FEMST 60\n" +
                "F\n" +
                "11:00am - 11:50am\n" +
                "HSSB 1224\n" +
                "3 / 20\n" +
                "FEMST 60\n" +
                "F\n" +
                "12:00pm - 12:50pm\n" +
                "HSSB 1211\n" +
                "3 / 20\n" +
                "FEMST 60\n" +
                "F\n" +
                "1:00pm - 1:50pm\n" +
                "HSSB 1223\n" +
                "2 / 20\n" +
                "FEMST 99\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "0 / 10\n" +
                "FEMST 120\n" +
                "M W\n" +
                "9:30 am - 10:45am\n" +
                "PHELP2524\n" +
                "55 / 55\n" +
                "FEMST 159B\n" +
                "M W F\n" +
                "11:00am - 11:50am\n" +
                "ARTS 1353\n" +
                "9 / 10\n" +
                "FEMST 180\n" +
                "T R\n" +
                "11:00am - 12:15pm\n" +
                "SH 4631A\n" +
                "25 / 25\n" +
                "FEMST 182\n" +
                "T R\n" +
                "12:30pm - 1:45pm\n" +
                "SH 4631A\n" +
                "11 / 25\n" +
                "FEMST 185NH\n" +
                "T R\n" +
                "3:30pm - 4:45pm\n" +
                "SH 4631A\n" +
                "20 / 25\n" +
                "FEMST 186BO\n" +
                "M W\n" +
                "11:00am - 12:15pm\n" +
                "SH 4631A\n" +
                "22 / 25\n" +
                "FEMST 195HA\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "0 / 10\n" +
                "FEMST 198\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "0 / 10\n" +
                "FEMST 199\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "0 / 10\n" +
                "FEMST 199RA\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "0 / 10\n" +
                "FAMST 46\n" +
                "M W\n" +
                "10:00am - 11:50am\n" +
                "PLLOK\n" +
                "100 / 100\n" +
                "FAMST 46\n" +
                "F\n" +
                "10:00am - 12:50pm\n" +
                "PLLOK\n" +
                "100 / 100\n" +
                "FAMST 46\n" +
                "T\n" +
                "8:00 am - 8:50 am\n" +
                "SSMS 2017\n" +
                "3 / 16\n" +
                "FAMST 46\n" +
                "T\n" +
                "9:00 am - 9:50 am\n" +
                "SSMS 2017\n" +
                "11 / 16\n" +
                "FAMST 46\n" +
                "T\n" +
                "10:00am - 10:50am\n" +
                "SSMS 2017\n" +
                "16 / 16\n" +
                "FAMST 46\n" +
                "T\n" +
                "8:00 am - 8:50 am\n" +
                "SSMS 2013\n" +
                "1 / 16\n" +
                "FAMST 46\n" +
                "T\n" +
                "9:00 am - 9:50 am\n" +
                "SSMS 2013\n" +
                "2 / 16\n" +
                "FAMST 46\n" +
                "T\n" +
                "10:00am - 10:50am\n" +
                "SSMS 2013\n" +
                "16 / 16\n" +
                "FAMST 46\n" +
                "T\n" +
                "11:00am - 11:50am\n" +
                "SSMS 2017\n" +
                "14 / 16\n" +
                "FAMST 46\n" +
                "T\n" +
                "12:00pm - 12:50pm\n" +
                "SSMS 2017\n" +
                "8 / 16\n" +
                "FAMST 46\n" +
                "T\n" +
                "1:00pm - 1:50pm\n" +
                "SSMS 2017\n" +
                "11 / 16\n" +
                "FAMST 46\n" +
                "T\n" +
                "11:00am - 11:50am\n" +
                "SSMS 2013\n" +
                "5 / 16\n" +
                "FAMST 46\n" +
                "T\n" +
                "12:00pm - 12:50pm\n" +
                "SSMS 2013\n" +
                "3 / 16\n" +
                "FAMST 46\n" +
                "T\n" +
                "1:00pm - 1:50pm\n" +
                "SSMS 2013\n" +
                "10 / 16\n" +
                "FAMST 46H\n" +
                "W\n" +
                "1:00pm - 1:50pm\n" +
                "SSMS 2017\n" +
                "2 / 15\n" +
                "FAMST 46MS\n" +
                "W\n" +
                "2:00pm - 3:50pm\n" +
                "SSMS 2017\n" +
                "3 / 18\n" +
                "FAMST 54\n" +
                "F\n" +
                "5:00pm - 6:50pm\n" +
                "PLLOK\n" +
                "160 / 160\n" +
                "FAMST 95\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "0 / 0\n" +
                "FAMST 99\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "0 / 18\n" +
                "FAMST 101A\n" +
                "T R\n" +
                "10:00am - 12:50pm\n" +
                "PLLOKTHTR\n" +
                "51 / 100\n" +
                "FAMST 101A\n" +
                "W\n" +
                "7:00pm - 9:30pm\n" +
                "BUCHN1920\n" +
                "51 / 100\n" +
                "FAMST 101A\n" +
                "T\n" +
                "2:00pm - 2:50pm\n" +
                "SSMS 2017\n" +
                "18 / 18\n" +
                "FAMST 101A\n" +
                "T\n" +
                "3:00pm - 3:50pm\n" +
                "SSMS 2017\n" +
                "6 / 18\n" +
                "FAMST 101A\n" +
                "T\n" +
                "4:00pm - 4:50pm\n" +
                "SSMS 2017\n" +
                "7 / 18\n" +
                "FAMST 101A\n" +
                "T\n" +
                "2:00pm - 2:50pm\n" +
                "SSMS 2013\n" +
                "17 / 18\n" +
                "FAMST 101A\n" +
                "T\n" +
                "3:00pm - 3:50pm\n" +
                "SSMS 2013\n" +
                "0 / 18\n" +
                "FAMST 101A\n" +
                "T\n" +
                "4:00pm - 4:50pm\n" +
                "SSMS 2013\n" +
                "3 / 18\n" +
                "FAMST 101D\n" +
                "M W\n" +
                "1:00pm - 3:50pm\n" +
                "PLLOK\n" +
                "100 / 100\n" +
                "FAMST 101D\n" +
                "R\n" +
                "8:00 am - 8:50 am\n" +
                "SSMS 2017\n" +
                "15 / 17\n" +
                "FAMST 101D\n" +
                "R\n" +
                "9:00 am - 9:50 am\n" +
                "SSMS 2017\n" +
                "17 / 17\n" +
                "FAMST 101D\n" +
                "R\n" +
                "10:00am - 10:50am\n" +
                "SSMS 2017\n" +
                "17 / 17\n" +
                "FAMST 101D\n" +
                "R\n" +
                "11:00am - 11:50am\n" +
                "SSMS 2013\n" +
                "17 / 17\n" +
                "FAMST 101D\n" +
                "R\n" +
                "12:00pm - 12:50pm\n" +
                "SSMS 2013\n" +
                "17 / 17\n" +
                "FAMST 101D\n" +
                "R\n" +
                "1:00pm - 1:50pm\n" +
                "SSMS 2013\n" +
                "17 / 17\n" +
                "FAMST 104\n" +
                "M W\n" +
                "8:30 am - 9:50 am\n" +
                "PLLOKTHTR\n" +
                "35 / 35\n" +
                "FAMST 104\n" +
                "M\n" +
                "10:00am - 10:50am\n" +
                "PLLOKSTG\n" +
                "14 / 14\n" +
                "FAMST 104\n" +
                "M\n" +
                "11:00am - 11:50am\n" +
                "PLLOKSTG\n" +
                "12 / 13\n" +
                "FAMST 104\n" +
                "M\n" +
                "12:00pm - 12:50pm\n" +
                "PLLOKSTG\n" +
                "9 / 16\n" +
                "FAMST 105\n" +
                "M W\n" +
                "9:00 am - 10:50am\n" +
                "SSMS 2303\n" +
                "12 / 12\n" +
                "FAMST 106A\n" +
                "M W\n" +
                "11:00am - 12:50pm\n" +
                "SSMS 2303\n" +
                "0 / 18\n" +
                "FAMST 106A\n" +
                "M W\n" +
                "11:00am - 12:50pm\n" +
                "SSMS 2303\n" +
                "0 / 18\n" +
                "FAMST 112\n" +
                "T R\n" +
                "9:00 am - 11:50am\n" +
                "SSMS 2303\n" +
                "8 / 8\n" +
                "FAMST 112\n" +
                "T R\n" +
                "9:00 am - 11:50am\n" +
                "PLLOKSTG\n" +
                "8 / 8\n" +
                "FAMST 119ML\n" +
                "M\n" +
                "11:00am - 12:50pm\n" +
                "SSMS 2013\n" +
                "13 / 25\n" +
                "FAMST 119ML\n" +
                "M\n" +
                "6:00pm - 11:50pm\n" +
                "IV THEA1\n" +
                "13 / 25\n" +
                "FAMST 119ML\n" +
                "F\n" +
                "6:00pm - 11:50pm\n" +
                "IV THEA1\n" +
                "13 / 25\n" +
                "FAMST 127\n" +
                "T R\n" +
                "1:00pm - 3:50pm\n" +
                "PLLOK\n" +
                "33 / 100\n" +
                "FAMST 150FT\n" +
                "T R\n" +
                "6:30pm - 8:15pm\n" +
                "BUCHN1920\n" +
                "13 / 55\n" +
                "FAMST 165GS\n" +
                "T R\n" +
                "2:00pm - 3:50pm\n" +
                "PHELP1518\n" +
                "19 / 20\n" +
                "FAMST 166MS\n" +
                "M W\n" +
                "2:00pm - 3:15pm\n" +
                "MUSIC2230\n" +
                "7 / 15\n" +
                "FAMST 169\n" +
                "M W\n" +
                "6:30pm - 9:20pm\n" +
                "BUCHN1930\n" +
                "34 / 60\n" +
                "FAMST 187AR\n" +
                "M W\n" +
                "1:00pm - 2:50pm\n" +
                "SSMS 2303\n" +
                "14 / 14\n" +
                "FAMST 187FF\n" +
                "T R\n" +
                "6:00pm - 8:50pm\n" +
                "SSMS 2013\n" +
                "5 / 14\n" +
                "FAMST 188A\n" +
                "T R\n" +
                "1:00pm - 3:50pm\n" +
                "SSMS 2303\n" +
                "0 / 18\n" +
                "FAMST 190AD\n" +
                "M W\n" +
                "1:00pm - 3:50pm\n" +
                "SSMS 2013\n" +
                "1 / 14\n" +
                "FAMST 190AD\n" +
                "M\n" +
                "1:00pm - 3:50pm\n" +
                "SSMS 2311\n" +
                "1 / 14\n" +
                "FAMST 192CT\n" +
                "T R\n" +
                "3:30pm - 6:20pm\n" +
                "BUCHN1940\n" +
                "78 / 147\n" +
                "FAMST 192CT\n" +
                "W\n" +
                "8:00 am - 8:50 am\n" +
                "SSMS 2017\n" +
                "2 / 16\n" +
                "FAMST 192CT\n" +
                "W\n" +
                "9:00 am - 9:50 am\n" +
                "SSMS 2017\n" +
                "10 / 16\n" +
                "FAMST 192CT\n" +
                "W\n" +
                "10:00am - 10:50am\n" +
                "SSMS 2017\n" +
                "15 / 15\n" +
                "FAMST 192CT\n" +
                "W\n" +
                "8:00 am - 8:50 am\n" +
                "SSMS 2013\n" +
                "0 / 15\n" +
                "FAMST 192CT\n" +
                "W\n" +
                "9:00 am - 9:50 am\n" +
                "SSMS 2013\n" +
                "1 / 15\n" +
                "FAMST 192CT\n" +
                "W\n" +
                "10:00am - 10:50am\n" +
                "SSMS 2013\n" +
                "12 / 15\n" +
                "FAMST 192CT\n" +
                "R\n" +
                "11:00am - 11:50am\n" +
                "SSMS 2017\n" +
                "15 / 15\n" +
                "FAMST 192CT\n" +
                "R\n" +
                "12:00pm - 12:50pm\n" +
                "SSMS 2017\n" +
                "8 / 15\n" +
                "FAMST 192CT\n" +
                "R\n" +
                "1:00pm - 1:50pm\n" +
                "SSMS 2017\n" +
                "15 / 15\n" +
                "FAMST 195I\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "1 / 26\n" +
                "FAMST 195I\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "0 / 0\n" +
                "FAMST 195PI\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "0 / 0\n" +
                "FAMST 199\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "0 / 18\n" +
                "FAMST 199RA\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "0 / 1\n" +
                "FR 1\n" +
                "MTWR\n" +
                "1:00pm - 1:50pm\n" +
                "PHELP1445\n" +
                "17 / 18\n" +
                "FR 1\n" +
                "MTWR\n" +
                "10:00am - 10:50am\n" +
                "PHELP1445\n" +
                "18 / 18\n" +
                "FR 1\n" +
                "MTWR\n" +
                "2:00pm - 2:50pm\n" +
                "HSSB 1224\n" +
                "8 / 18\n" +
                "FR 1\n" +
                "MTWR\n" +
                "11:00am - 11:50am\n" +
                "HSSB 1233\n" +
                "17 / 18\n" +
                "FR 1\n" +
                "MTWR\n" +
                "9:00 am - 9:50 am\n" +
                "HSSB 1224\n" +
                "6 / 18\n" +
                "FR 1\n" +
                "MTWR\n" +
                "12:00pm - 12:50pm\n" +
                "GIRV 2116\n" +
                "10 / 18\n" +
                "FR 1\n" +
                "MTWR\n" +
                "3:00pm - 3:50pm\n" +
                "HSSB 1224\n" +
                "9 / 18\n" +
                "FR 1\n" +
                "MTWR\n" +
                "4:00pm - 4:50pm\n" +
                "HSSB 1224\n" +
                "11 / 18\n" +
                "FR 2\n" +
                "MTWR\n" +
                "10:00am - 10:50am\n" +
                "HSSB 1236\n" +
                "11 / 23\n" +
                "FR 2\n" +
                "MTWR\n" +
                "2:00pm - 2:50pm\n" +
                "HSSB 1211\n" +
                "12 / 23\n" +
                "FR 3\n" +
                "MTWR\n" +
                "1:00pm - 1:50pm\n" +
                "HSSB 1206\n" +
                "18 / 22\n" +
                "FR 3\n" +
                "MTWR\n" +
                "11:00am - 11:50am\n" +
                "HSSB 1223\n" +
                "10 / 22\n" +
                "FR 4\n" +
                "MTWR\n" +
                "11:00am - 11:50am\n" +
                "GIRV 2116\n" +
                "14 / 24\n" +
                "FR 4\n" +
                "MTWR\n" +
                "3:00pm - 3:50pm\n" +
                "GIRV 2112\n" +
                "7 / 24\n" +
                "FR 4\n" +
                "MTWR\n" +
                "12:00pm - 12:50pm\n" +
                "HSSB 1211\n" +
                "10 / 24\n" +
                "FR 5\n" +
                "MTWR\n" +
                "1:00pm - 1:50pm\n" +
                "HSSB 1228\n" +
                "10 / 20\n" +
                "FR 6\n" +
                "MTWR\n" +
                "10:00am - 10:50am\n" +
                "HSSB 1233\n" +
                "11 / 20\n" +
                "FR 26\n" +
                "M W\n" +
                "9:30 am - 10:45am\n" +
                "GIRV 2124\n" +
                "8 / 26\n" +
                "FR 26\n" +
                "W\n" +
                "3:00pm - 3:50pm\n" +
                "HSSB 1223\n" +
                "8 / 26\n" +
                "FR 99\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "0 / 0\n" +
                "FR 101A\n" +
                "M W\n" +
                "12:30pm - 1:45pm\n" +
                "PHELP1440\n" +
                "7 / 26\n" +
                "FR 101C\n" +
                "M W\n" +
                "2:00pm - 3:15pm\n" +
                "GIRV 2129\n" +
                "20 / 20\n" +
                "FR 104B\n" +
                "T R\n" +
                "11:00am - 12:15pm\n" +
                "PHELP1448\n" +
                "12 / 25\n" +
                "FR 148C\n" +
                "M W\n" +
                "9:30 am - 10:45am\n" +
                "BUCHN1934\n" +
                "4 / 25\n" +
                "FR 154A\n" +
                "T R\n" +
                "9:30 am - 10:45am\n" +
                "GIRV 1115\n" +
                "11 / 20\n" +
                "FR 195H\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "0 / 0\n" +
                "FR 199\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "0 / 10\n" +
                "FR 199RA\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "0 / 10\n" +
                "GEOG 3A\n" +
                "T R\n" +
                "5:00pm - 6:15pm\n" +
                "CHEM 1179\n" +
                "118 / 164\n" +
                "GEOG 3A\n" +
                "W\n" +
                "12:00pm - 12:50pm\n" +
                "PHELP2525\n" +
                "20 / 27\n" +
                "GEOG 3A\n" +
                "W\n" +
                "2:00pm - 2:50pm\n" +
                "PHELP2525\n" +
                "15 / 27\n" +
                "GEOG 3A\n" +
                "T\n" +
                "11:00am - 11:50am\n" +
                "PHELP2525\n" +
                "23 / 27\n" +
                "GEOG 3A\n" +
                "R\n" +
                "9:00 am - 9:50 am\n" +
                "PHELP2525\n" +
                "6 / 27\n" +
                "GEOG 3A\n" +
                "R\n" +
                "1:00pm - 1:50pm\n" +
                "PHELP2525\n" +
                "17 / 27\n" +
                "GEOG 3A\n" +
                "F\n" +
                "12:00pm - 12:50pm\n" +
                "PHELP2525\n" +
                "0 / 27\n" +
                "GEOG 3A\n" +
                "M\n" +
                "10:00am - 10:50am\n" +
                "PHELP2525\n" +
                "7 / 27\n" +
                "GEOG 3A\n" +
                "M\n" +
                "5:00pm - 5:50pm\n" +
                "PHELP2525\n" +
                "11 / 27\n" +
                "GEOG 3A\n" +
                "W\n" +
                "9:00 am - 9:50 am\n" +
                "PHELP2525\n" +
                "2 / 27\n" +
                "GEOG 3A\n" +
                "M\n" +
                "2:00pm - 2:50pm\n" +
                "PHELP2525\n" +
                "8 / 27\n" +
                "GEOG 3A\n" +
                "M\n" +
                "9:00 am - 9:50 am\n" +
                "PHELP2525\n" +
                "1 / 27\n" +
                "GEOG 3A\n" +
                "W\n" +
                "10:00am - 10:50am\n" +
                "PHELP2525\n" +
                "8 / 27\n" +
                "GEOG 3B\n" +
                "T R\n" +
                "8:00 am - 9:15 am\n" +
                "EMBARHALL\n" +
                "34 / 68\n" +
                "GEOG 3B\n" +
                "T\n" +
                "10:00am - 10:50am\n" +
                "ELLSN2620\n" +
                "11 / 27\n" +
                "GEOG 3B\n" +
                "T\n" +
                "5:00pm - 5:50pm\n" +
                "ELLSN2620\n" +
                "2 / 27\n" +
                "GEOG 3B\n" +
                "W\n" +
                "4:00pm - 4:50pm\n" +
                "ELLSN2620\n" +
                "2 / 27\n" +
                "GEOG 3B\n" +
                "R\n" +
                "12:00pm - 12:50pm\n" +
                "ELLSN2620\n" +
                "5 / 27\n" +
                "GEOG 3B\n" +
                "W\n" +
                "11:00am - 11:50am\n" +
                "ELLSN2620\n" +
                "7 / 27\n" +
                "GEOG 3B\n" +
                "M\n" +
                "5:00pm - 5:50pm\n" +
                "ELLSN2620\n" +
                "1 / 27\n" +
                "GEOG 3B\n" +
                "F\n" +
                "9:00 am - 9:50 am\n" +
                "ELLSN2620\n" +
                "3 / 27\n" +
                "GEOG 3B\n" +
                "R\n" +
                "5:00pm - 5:50pm\n" +
                "ELLSN2620\n" +
                "1 / 27\n" +
                "GEOG 3B\n" +
                "M\n" +
                "4:00pm - 4:50pm\n" +
                "ELLSN2620\n" +
                "2 / 27\n" +
                "GEOG 5\n" +
                "M W F\n" +
                "1:00pm - 1:50pm\n" +
                "BUCHN1910\n" +
                "40 / 61\n" +
                "GEOG 5\n" +
                "T\n" +
                "11:00am - 11:50am\n" +
                "ELLSN2620\n" +
                "12 / 26\n" +
                "GEOG 5\n" +
                "T\n" +
                "9:00 am - 9:50 am\n" +
                "ELLSN2620\n" +
                "1 / 26\n" +
                "GEOG 5\n" +
                "M\n" +
                "2:00pm - 2:50pm\n" +
                "ELLSN2620\n" +
                "5 / 26\n" +
                "GEOG 5\n" +
                "R\n" +
                "10:00am - 10:50am\n" +
                "ELLSN2620\n" +
                "4 / 26\n" +
                "GEOG 5\n" +
                "F\n" +
                "10:00am - 10:50am\n" +
                "ELLSN2620\n" +
                "3 / 26\n" +
                "GEOG 5\n" +
                "W\n" +
                "8:00 am - 8:50 am\n" +
                "ELLSN2620\n" +
                "0 / 26\n" +
                "GEOG 5\n" +
                "M\n" +
                "6:00pm - 6:50pm\n" +
                "ELLSN2620\n" +
                "3 / 26\n" +
                "GEOG 5\n" +
                "T\n" +
                "4:00pm - 4:50pm\n" +
                "ELLSN2620\n" +
                "3 / 26\n" +
                "GEOG 5\n" +
                "T\n" +
                "1:00pm - 1:50pm\n" +
                "ELLSN2620\n" +
                "2 / 26\n" +
                "GEOG 5\n" +
                "R\n" +
                "1:00pm - 1:50pm\n" +
                "ELLSN2620\n" +
                "4 / 26\n" +
                "GEOG 5\n" +
                "W\n" +
                "9:00 am - 9:50 am\n" +
                "ELLSN2620\n" +
                "3 / 26\n" +
                "GEOG W 12\n" +
                "\n" +
                "\n" +
                "ONLINE\n" +
                "64 / 200\n" +
                "GEOG W 12\n" +
                "W\n" +
                "5:00pm - 7:50pm\n" +
                "ELLSN3620\n" +
                "11 / 29\n" +
                "GEOG W 12\n" +
                "F\n" +
                "9:00 am - 11:50am\n" +
                "ELLSN3620\n" +
                "5 / 29\n" +
                "GEOG W 12\n" +
                "F\n" +
                "2:00pm - 4:50pm\n" +
                "ELLSN3620\n" +
                "3 / 29\n" +
                "GEOG W 12\n" +
                "M\n" +
                "5:00pm - 7:50pm\n" +
                "ELLSN3620\n" +
                "14 / 29\n" +
                "GEOG W 12\n" +
                "T\n" +
                "5:00pm - 7:50pm\n" +
                "ELLSN3620\n" +
                "8 / 29\n" +
                "GEOG W 12\n" +
                "R\n" +
                "5:00pm - 7:50pm\n" +
                "ELLSN3620\n" +
                "3 / 29\n" +
                "GEOG W 12\n" +
                "M\n" +
                "2:00pm - 4:50pm\n" +
                "ELLSN3620\n" +
                "20 / 26\n" +
                "GEOG 101\n" +
                "M W\n" +
                "2:00pm - 3:15pm\n" +
                "GIRV 1115\n" +
                "24 / 48\n" +
                "GEOG 101\n" +
                "W\n" +
                "10:00am - 10:50am\n" +
                "ELLSN2609\n" +
                "23 / 24\n" +
                "GEOG 101\n" +
                "W\n" +
                "4:00pm - 4:50pm\n" +
                "ELLSN2609\n" +
                "1 / 24\n" +
                "GEOG 104\n" +
                "M W\n" +
                "3:30pm - 4:45pm\n" +
                "PHELP1260\n" +
                "31 / 72\n" +
                "GEOG 104\n" +
                "W\n" +
                "5:00pm - 5:50pm\n" +
                "ELLSN3621\n" +
                "9 / 24\n" +
                "GEOG 104\n" +
                "T\n" +
                "5:00pm - 5:50pm\n" +
                "ELLSN3621\n" +
                "8 / 24\n" +
                "GEOG 104\n" +
                "M\n" +
                "5:00pm - 5:50pm\n" +
                "ELLSN3621\n" +
                "14 / 24\n" +
                "GEOG 108\n" +
                "M W\n" +
                "11:00am - 12:15pm\n" +
                "ELLSN3621\n" +
                "21 / 48\n" +
                "GEOG 108\n" +
                "W\n" +
                "4:00pm - 4:50pm\n" +
                "ELLSN2610\n" +
                "20 / 24\n" +
                "GEOG 108\n" +
                "F\n" +
                "11:00am - 11:50am\n" +
                "ELLSN2610\n" +
                "1 / 24\n" +
                "GEOG 111A\n" +
                "M W\n" +
                "12:30pm - 1:45pm\n" +
                "ELLSN3621\n" +
                "16 / 48\n" +
                "GEOG 111A\n" +
                "T\n" +
                "9:00 am - 10:50am\n" +
                "ELLSN3620\n" +
                "16 / 24\n" +
                "GEOG 111A\n" +
                "T\n" +
                "2:00pm - 3:50pm\n" +
                "ELLSN3620\n" +
                "0 / 24\n" +
                "GEOG 112\n" +
                "T R\n" +
                "9:30 am - 10:45am\n" +
                "ELLSN3621\n" +
                "24 / 24\n" +
                "GEOG 112\n" +
                "W\n" +
                "5:20pm - 7:00pm\n" +
                "PHELP2525\n" +
                "24 / 24\n" +
                "GEOG 114A\n" +
                "M W\n" +
                "2:00pm - 3:15pm\n" +
                "ELLSN3621\n" +
                "14 / 24\n" +
                "GEOG 114A\n" +
                "F\n" +
                "1:00pm - 3:50pm\n" +
                "PHELP2525\n" +
                "14 / 12\n" +
                "GEOG 115A\n" +
                "T R\n" +
                "11:00am - 12:15pm\n" +
                "GIRV 2128\n" +
                "50 / 70\n" +
                "GEOG 115A\n" +
                "W\n" +
                "11:00am - 12:50pm\n" +
                "ELLSN2610\n" +
                "24 / 24\n" +
                "GEOG 115A\n" +
                "R\n" +
                "9:00 am - 10:50am\n" +
                "ELLSN2610\n" +
                "23 / 23\n" +
                "GEOG 115A\n" +
                "W\n" +
                "9:00 am - 10:50am\n" +
                "ELLSN2610\n" +
                "3 / 23\n" +
                "GEOG 134\n" +
                "M W\n" +
                "12:30pm - 1:45pm\n" +
                "ELLSN2620\n" +
                "21 / 24\n" +
                "GEOG 134\n" +
                "W\n" +
                "5:00pm - 6:50pm\n" +
                "ELLSN2620\n" +
                "21 / 24\n" +
                "GEOG 140\n" +
                "T R\n" +
                "11:00am - 12:15pm\n" +
                "ELLSN3621\n" +
                "13 / 27\n" +
                "GEOG 140\n" +
                "M\n" +
                "2:00pm - 2:50pm\n" +
                "ELLSN2609\n" +
                "13 / 27\n" +
                "GEOG 161\n" +
                "T R\n" +
                "9:30 am - 10:45am\n" +
                "PHELP3515\n" +
                "13 / 12\n" +
                "GEOG 161\n" +
                "F\n" +
                "9:00 am - 9:50 am\n" +
                "ELLSN3621\n" +
                "4 / 4\n" +
                "GEOG 161\n" +
                "F\n" +
                "10:00am - 10:50am\n" +
                "HSSB 1237\n" +
                "5 / 4\n" +
                "GEOG 161\n" +
                "R\n" +
                "5:00pm - 5:50pm\n" +
                "SH 1430\n" +
                "4 / 4\n" +
                "GEOG 172\n" +
                "M W\n" +
                "9:30 am - 10:45am\n" +
                "ELLSN3621\n" +
                "26 / 30\n" +
                "GEOG 172\n" +
                "F\n" +
                "9:00 am - 10:50am\n" +
                "ELLSN2610\n" +
                "26 / 30\n" +
                "GEOG 176A\n" +
                "T R\n" +
                "2:00pm - 3:15pm\n" +
                "HSSB 1173\n" +
                "51 / 72\n" +
                "GEOG 176A\n" +
                "T\n" +
                "6:00pm - 7:50pm\n" +
                "ELLSN2610\n" +
                "15 / 24\n" +
                "GEOG 176A\n" +
                "W\n" +
                "2:00pm - 3:50pm\n" +
                "ELLSN2610\n" +
                "23 / 24\n" +
                "GEOG 176A\n" +
                "R\n" +
                "5:00pm - 6:50pm\n" +
                "ELLSN2610\n" +
                "13 / 24\n" +
                "GEOG 193\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "0 / 10\n" +
                "GEOG 198\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "0 / 10\n" +
                "GEOG 199\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "0 / 10\n" +
                "GEOG 199RA\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "0 / 10\n" +
                "GER 1\n" +
                "MTWR\n" +
                "9:00 am - 9:50 am\n" +
                "GIRV 2127\n" +
                "7 / 24\n" +
                "GER 1\n" +
                "MTWR\n" +
                "10:00am - 10:50am\n" +
                "PHELP1440\n" +
                "10 / 24\n" +
                "GER 1\n" +
                "MTWR\n" +
                "2:00pm - 2:50pm\n" +
                "PHELP1440\n" +
                "13 / 24\n" +
                "GER 1\n" +
                "MTWR\n" +
                "12:00pm - 12:50pm\n" +
                "GIRV 2129\n" +
                "5 / 24\n" +
                "GER 1\n" +
                "MTWR\n" +
                "11:00am - 11:50am\n" +
                "GIRV 1116\n" +
                "17 / 24\n" +
                "GER 1\n" +
                "MTWR\n" +
                "12:00pm - 12:50pm\n" +
                "GIRV 2112\n" +
                "2 / 24\n" +
                "GER 1\n" +
                "MTWR\n" +
                "1:00pm - 1:50pm\n" +
                "PHELP1508\n" +
                "19 / 24\n" +
                "GER 4\n" +
                "MTWR\n" +
                "10:00am - 10:50am\n" +
                "GIRV 2112\n" +
                "13 / 28\n" +
                "GER 4\n" +
                "MTWR\n" +
                "2:00pm - 2:50pm\n" +
                "GIRV 2112\n" +
                "14 / 28\n" +
                "GER 4\n" +
                "MTWR\n" +
                "12:00pm - 12:50pm\n" +
                "GIRV 2115\n" +
                "5 / 28\n" +
                "GER 101A\n" +
                "T R\n" +
                "12:30pm - 1:45pm\n" +
                "GIRV 1116\n" +
                "16 / 30\n" +
                "GER 101A\n" +
                "T R\n" +
                "12:30pm - 1:45pm\n" +
                "GIRV 2123\n" +
                "0 / 20\n" +
                "GER 107A\n" +
                "M W\n" +
                "12:30pm - 1:45pm\n" +
                "GIRV 2124\n" +
                "19 / 26\n" +
                "GER 190\n" +
                "M W\n" +
                "9:30 am - 10:45am\n" +
                "PHELP6320\n" +
                "3 / 10\n" +
                "GER 197\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "0 / 5\n" +
                "GER 198\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "0 / 5\n" +
                "GER 199\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "0 / 5\n" +
                "GER 199RA\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "0 / 5\n" +
                "GLOBL 1\n" +
                "T R\n" +
                "9:30 am - 10:45am\n" +
                "BUCHN1910\n" +
                "240 / 240\n" +
                "GLOBL 1\n" +
                "W\n" +
                "7:00pm - 7:50pm\n" +
                "HSSB 1236\n" +
                "20 / 20\n" +
                "GLOBL 1\n" +
                "F\n" +
                "10:00am - 10:50am\n" +
                "HSSB 1228\n" +
                "20 / 20\n" +
                "GLOBL 1\n" +
                "T\n" +
                "7:00pm - 7:50pm\n" +
                "HSSB 4201\n" +
                "20 / 20\n" +
                "GLOBL 1\n" +
                "R\n" +
                "7:00pm - 7:50pm\n" +
                "HSSB 4202\n" +
                "20 / 20\n" +
                "GLOBL 1\n" +
                "M\n" +
                "7:00pm - 7:50pm\n" +
                "HSSB 4202\n" +
                "20 / 20\n" +
                "GLOBL 1\n" +
                "W\n" +
                "8:00 am - 8:50 am\n" +
                "HSSB 3201\n" +
                "20 / 20\n" +
                "GLOBL 1\n" +
                "R\n" +
                "7:00pm - 7:50pm\n" +
                "HSSB 4201\n" +
                "20 / 20\n" +
                "GLOBL 1\n" +
                "F\n" +
                "9:00 am - 9:50 am\n" +
                "HSSB 1228\n" +
                "20 / 20\n" +
                "GLOBL 1\n" +
                "F\n" +
                "12:00pm - 12:50pm\n" +
                "HSSB 1224\n" +
                "20 / 20\n" +
                "GLOBL 1\n" +
                "T\n" +
                "7:00pm - 7:50pm\n" +
                "HSSB 3202\n" +
                "20 / 20\n" +
                "GLOBL 1\n" +
                "R\n" +
                "6:00pm - 6:50pm\n" +
                "HSSB 3202\n" +
                "20 / 20\n" +
                "GLOBL 1\n" +
                "M\n" +
                "7:00pm - 7:50pm\n" +
                "HSSB 4201\n" +
                "20 / 20\n" +
                "GLOBL 2\n" +
                "M W\n" +
                "2:00pm - 3:15pm\n" +
                "BRDA 1610\n" +
                "240 / 240\n" +
                "GLOBL 2\n" +
                "F\n" +
                "12:00pm - 12:50pm\n" +
                "HSSB 1223\n" +
                "20 / 20\n" +
                "GLOBL 2\n" +
                "M\n" +
                "7:00pm - 7:50pm\n" +
                "HSSB 3202\n" +
                "20 / 20\n" +
                "GLOBL 2\n" +
                "W\n" +
                "7:00pm - 7:50pm\n" +
                "HSSB 4202\n" +
                "20 / 20\n" +
                "GLOBL 2\n" +
                "F\n" +
                "10:00am - 10:50am\n" +
                "HSSB 1231\n" +
                "20 / 20\n" +
                "GLOBL 2\n" +
                "F\n" +
                "11:00am - 11:50am\n" +
                "HSSB 1231\n" +
                "20 / 20\n" +
                "GLOBL 2\n" +
                "R\n" +
                "7:00pm - 7:50pm\n" +
                "HSSB 3202\n" +
                "20 / 20\n" +
                "GLOBL 2\n" +
                "M\n" +
                "7:00pm - 7:50pm\n" +
                "HSSB 3201\n" +
                "20 / 20\n" +
                "GLOBL 2\n" +
                "W\n" +
                "7:00pm - 7:50pm\n" +
                "HSSB 4201\n" +
                "20 / 20\n" +
                "GLOBL 2\n" +
                "R\n" +
                "7:00pm - 7:50pm\n" +
                "HSSB 3201\n" +
                "20 / 20\n" +
                "GLOBL 2\n" +
                "T\n" +
                "7:00pm - 7:50pm\n" +
                "HSSB 3201\n" +
                "20 / 20\n" +
                "GLOBL 2\n" +
                "R\n" +
                "6:00pm - 6:50pm\n" +
                "HSSB 3201\n" +
                "20 / 20\n" +
                "GLOBL 2\n" +
                "W\n" +
                "7:00pm - 7:50pm\n" +
                "HSSB 3202\n" +
                "20 / 20\n" +
                "GLOBL 104\n" +
                "M W\n" +
                "11:00am - 12:15pm\n" +
                "HSSB 1174\n" +
                "95 / 95\n" +
                "GLOBL 111\n" +
                "T R\n" +
                "2:00pm - 3:15pm\n" +
                "NH 1006\n" +
                "100 / 100\n" +
                "GLOBL 120\n" +
                "T R\n" +
                "11:00am - 12:15pm\n" +
                "IV THEA2\n" +
                "138 / 138\n" +
                "GLOBL 120\n" +
                "F\n" +
                "9:00 am - 9:50 am\n" +
                "HSSB 2251\n" +
                "23 / 23\n" +
                "GLOBL 120\n" +
                "M\n" +
                "7:00pm - 7:50pm\n" +
                "HSSB 1236\n" +
                "23 / 23\n" +
                "GLOBL 120\n" +
                "T\n" +
                "7:00pm - 7:50pm\n" +
                "HSSB 1236\n" +
                "23 / 23\n" +
                "GLOBL 120\n" +
                "W\n" +
                "7:00pm - 7:50pm\n" +
                "PHELP1445\n" +
                "23 / 23\n" +
                "GLOBL 120\n" +
                "R\n" +
                "7:00pm - 7:50pm\n" +
                "PHELP1445\n" +
                "23 / 23\n" +
                "GLOBL 120\n" +
                "F\n" +
                "10:00am - 10:50am\n" +
                "HSSB 1233\n" +
                "23 / 23\n" +
                "GLOBL 123\n" +
                "M W\n" +
                "3:30pm - 4:45pm\n" +
                "HFH 1104\n" +
                "48 / 48\n" +
                "GLOBL 123\n" +
                "R\n" +
                "6:00pm - 6:50pm\n" +
                "GIRV 1108\n" +
                "8 / 8\n" +
                "GLOBL 123\n" +
                "R\n" +
                "8:00 am - 8:50 am\n" +
                "HSSB 3202\n" +
                "8 / 8\n" +
                "GLOBL 123\n" +
                "F\n" +
                "12:00pm - 12:50pm\n" +
                "HSSB 4202\n" +
                "8 / 8\n" +
                "GLOBL 123\n" +
                "R\n" +
                "5:00pm - 5:50pm\n" +
                "HSSB 3202\n" +
                "8 / 8\n" +
                "GLOBL 123\n" +
                "R\n" +
                "9:00 am - 9:50 am\n" +
                "HSSB 2201\n" +
                "8 / 8\n" +
                "GLOBL 123\n" +
                "R\n" +
                "4:00pm - 4:50pm\n" +
                "HSSB 4201\n" +
                "8 / 8\n" +
                "GLOBL 130\n" +
                "T R\n" +
                "12:30pm - 1:45pm\n" +
                "BUCHN1920\n" +
                "132 / 132\n" +
                "GLOBL 130\n" +
                "T\n" +
                "7:00pm - 7:50pm\n" +
                "HSSB 4202\n" +
                "22 / 22\n" +
                "GLOBL 130\n" +
                "F\n" +
                "1:00pm - 1:50pm\n" +
                "HSSB 4201\n" +
                "22 / 22\n" +
                "GLOBL 130\n" +
                "R\n" +
                "5:00pm - 5:50pm\n" +
                "HSSB 3201\n" +
                "22 / 22\n" +
                "GLOBL 130\n" +
                "W\n" +
                "7:00pm - 7:50pm\n" +
                "PHELP1444\n" +
                "22 / 22\n" +
                "GLOBL 130\n" +
                "R\n" +
                "6:00pm - 6:50pm\n" +
                "HSSB 4201\n" +
                "22 / 22\n" +
                "GLOBL 130\n" +
                "F\n" +
                "12:00pm - 12:50pm\n" +
                "HSSB 1233\n" +
                "22 / 22\n" +
                "GLOBL 161\n" +
                "T R\n" +
                "12:30pm - 1:45pm\n" +
                "PHELP1440\n" +
                "35 / 35\n" +
                "GLOBL 162\n" +
                "W\n" +
                "5:00pm - 7:50pm\n" +
                "WEBB 1100\n" +
                "40 / 40\n" +
                "GLOBL 199\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "0 / 10\n" +
                "GREEK 1\n" +
                "MTWRF\n" +
                "10:00am - 10:50am\n" +
                "HSSB 1207\n" +
                "7 / 14\n" +
                "GREEK 100\n" +
                "M W F\n" +
                "9:00 am - 9:50 am\n" +
                "HSSB 4065\n" +
                "2 / 5\n" +
                "GREEK 110\n" +
                "T R\n" +
                "2:00pm - 3:20pm\n" +
                "HSSB 4065\n" +
                "0 / 5\n" +
                "GREEK 199\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "0 / 5\n" +
                "HEB 1\n" +
                "MTWR\n" +
                "11:00am - 11:50am\n" +
                "HSSB 1224\n" +
                "15 / 25\n" +
                "HEB 4\n" +
                "MTWR\n" +
                "2:00pm - 2:50pm\n" +
                "HSSB 1231\n" +
                "4 / 25\n" +
                "HIST 2A\n" +
                "M W\n" +
                "3:30pm - 4:45pm\n" +
                "IV THEA1\n" +
                "304 / 304\n" +
                "HIST 2A\n" +
                "T\n" +
                "8:00 am - 8:50 am\n" +
                "HSSB 4041\n" +
                "14 / 18\n" +
                "HIST 2A\n" +
                "M\n" +
                "7:00pm - 7:50pm\n" +
                "HSSB 2201\n" +
                "15 / 18\n" +
                "HIST 2A\n" +
                "T\n" +
                "8:00 am - 8:50 am\n" +
                "HSSB 3201\n" +
                "2 / 18\n" +
                "HIST 2A\n" +
                "W\n" +
                "8:00 am - 8:50 am\n" +
                "HSSB 4041\n" +
                "7 / 18\n" +
                "HIST 2A\n" +
                "R\n" +
                "8:00 am - 8:50 am\n" +
                "HSSB 4041\n" +
                "5 / 18\n" +
                "HIST 2A\n" +
                "T\n" +
                "3:00pm - 3:50pm\n" +
                "HSSB 1207\n" +
                "17 / 18\n" +
                "HIST 2A\n" +
                "T\n" +
                "4:00pm - 4:50pm\n" +
                "HSSB 4202\n" +
                "18 / 18\n" +
                "HIST 2A\n" +
                "F\n" +
                "8:00 am - 8:50 am\n" +
                "HSSB 4041\n" +
                "1 / 18\n" +
                "HIST 2A\n" +
                "W\n" +
                "8:00 am - 8:50 am\n" +
                "HSSB 4202\n" +
                "2 / 18\n" +
                "HIST 2A\n" +
                "F\n" +
                "5:00pm - 5:50pm\n" +
                "HSSB 4041\n" +
                "2 / 18\n" +
                "HIST 2A\n" +
                "W\n" +
                "10:00am - 10:50am\n" +
                "HSSB 4202\n" +
                "18 / 18\n" +
                "HIST 2A\n" +
                "F\n" +
                "6:00pm - 6:50pm\n" +
                "HSSB 4041\n" +
                "0 / 18\n" +
                "HIST 2A\n" +
                "W\n" +
                "12:00pm - 12:50pm\n" +
                "HSSB 2201\n" +
                "18 / 18\n" +
                "HIST 2A\n" +
                "M\n" +
                "6:00pm - 6:50pm\n" +
                "HSSB 4020\n" +
                "18 / 18\n" +
                "HIST 2A\n" +
                "W\n" +
                "6:00pm - 6:50pm\n" +
                "HSSB 4201\n" +
                "18 / 18\n" +
                "HIST 2A\n" +
                "W\n" +
                "7:00pm - 7:50pm\n" +
                "HSSB 1237\n" +
                "11 / 18\n" +
                "HIST 2A\n" +
                "M\n" +
                "7:00pm - 7:50pm\n" +
                "HSSB 4020\n" +
                "3 / 18\n" +
                "HIST 2A\n" +
                "R\n" +
                "6:00pm - 6:50pm\n" +
                "HSSB 4020\n" +
                "15 / 18\n" +
                "HIST 2A\n" +
                "R\n" +
                "7:00pm - 7:50pm\n" +
                "HSSB 4020\n" +
                "7 / 18\n" +
                "HIST 2A\n" +
                "F\n" +
                "8:00 am - 8:50 am\n" +
                "HSSB 2201\n" +
                "0 / 18\n" +
                "HIST 2A\n" +
                "F\n" +
                "9:00 am - 9:50 am\n" +
                "HSSB 1211\n" +
                "4 / 18\n" +
                "HIST 2A\n" +
                "F\n" +
                "10:00am - 10:50am\n" +
                "HSSB 4202\n" +
                "9 / 18\n" +
                "HIST 2A\n" +
                "F\n" +
                "11:00am - 11:50am\n" +
                "HSSB 1211\n" +
                "15 / 18\n" +
                "HIST 2A\n" +
                "F\n" +
                "12:00pm - 12:50pm\n" +
                "HSSB 2201\n" +
                "7 / 18\n" +
                "HIST 2A\n" +
                "F\n" +
                "1:00pm - 1:50pm\n" +
                "HSSB 2201\n" +
                "4 / 18\n" +
                "HIST 2A\n" +
                "R\n" +
                "2:00pm - 2:50pm\n" +
                "BUCHN1934\n" +
                "18 / 18\n" +
                "HIST 2A\n" +
                "R\n" +
                "3:00pm - 3:50pm\n" +
                "HSSB 1211\n" +
                "18 / 18\n" +
                "HIST 2A\n" +
                "R\n" +
                "4:00pm - 4:50pm\n" +
                "HSSB 1215\n" +
                "18 / 18\n" +
                "HIST 2A\n" +
                "R\n" +
                "5:00pm - 5:50pm\n" +
                "HSSB 4202\n" +
                "18 / 18\n" +
                "HIST 2A\n" +
                "W\n" +
                "8:00 am - 8:50 am\n" +
                "HSSB 4020\n" +
                "2 / 18\n" +
                "HIST 2C\n" +
                "T R\n" +
                "11:00am - 12:15pm\n" +
                "TD-W 1701\n" +
                "125 / 125\n" +
                "HIST 2C\n" +
                "T\n" +
                "2:00pm - 2:50pm\n" +
                "HSSB 1214\n" +
                "18 / 18\n" +
                "HIST 2C\n" +
                "T\n" +
                "3:00pm - 3:50pm\n" +
                "HSSB 4201\n" +
                "18 / 18\n" +
                "HIST 2C\n" +
                "T\n" +
                "4:00pm - 4:50pm\n" +
                "HSSB 4201\n" +
                "14 / 18\n" +
                "HIST 2C\n" +
                "W\n" +
                "8:00 am - 8:50 am\n" +
                "HSSB 4201\n" +
                "11 / 18\n" +
                "HIST 2C\n" +
                "R\n" +
                "8:00 am - 8:50 am\n" +
                "HSSB 4020\n" +
                "13 / 18\n" +
                "HIST 2C\n" +
                "W\n" +
                "11:00am - 11:50am\n" +
                "GIRV 2124\n" +
                "18 / 18\n" +
                "HIST 2C\n" +
                "R\n" +
                "2:00pm - 2:50pm\n" +
                "HSSB 2201\n" +
                "18 / 18\n" +
                "HIST 2C\n" +
                "R\n" +
                "3:00pm - 3:50pm\n" +
                "HSSB 1207\n" +
                "4 / 18\n" +
                "HIST 2C\n" +
                "R\n" +
                "4:00pm - 4:50pm\n" +
                "HSSB 4202\n" +
                "11 / 18\n" +
                "HIST 2CH\n" +
                "W\n" +
                "10:00am - 10:50am\n" +
                "HSSB 4041\n" +
                "0 / 15\n" +
                "HIST 5\n" +
                "M W\n" +
                "2:00pm - 3:15pm\n" +
                "GIRV 1004\n" +
                "134 / 137\n" +
                "HIST 5\n" +
                "M\n" +
                "4:00pm - 4:50pm\n" +
                "HSSB 3201\n" +
                "18 / 18\n" +
                "HIST 5\n" +
                "M\n" +
                "5:00pm - 5:50pm\n" +
                "ARTS 1356\n" +
                "18 / 18\n" +
                "HIST 5\n" +
                "T\n" +
                "8:00 am - 8:50 am\n" +
                "HSSB 4020\n" +
                "18 / 18\n" +
                "HIST 5\n" +
                "F\n" +
                "8:00 am - 8:50 am\n" +
                "HSSB 4020\n" +
                "18 / 18\n" +
                "HIST 5\n" +
                "T\n" +
                "11:00am - 11:50am\n" +
                "SH 1430\n" +
                "18 / 18\n" +
                "HIST 5\n" +
                "F\n" +
                "6:00pm - 6:50pm\n" +
                "HSSB 4020\n" +
                "7 / 18\n" +
                "HIST 5\n" +
                "F\n" +
                "7:00pm - 7:50pm\n" +
                "HSSB 4020\n" +
                "1 / 18\n" +
                "HIST 5\n" +
                "W\n" +
                "10:00am - 10:50am\n" +
                "HSSB 4201\n" +
                "18 / 18\n" +
                "HIST 5\n" +
                "W\n" +
                "11:00am - 11:50am\n" +
                "HSSB 2201\n" +
                "18 / 18\n" +
                "HIST 9\n" +
                "M\n" +
                "12:00pm - 2:50pm\n" +
                "HSSB 4080\n" +
                "6 / 20\n" +
                "HIST 17A\n" +
                "M W F\n" +
                "11:00am - 11:50am\n" +
                "MUSICLLCH\n" +
                "172 / 257\n" +
                "HIST 17A\n" +
                "M\n" +
                "1:00pm - 1:50pm\n" +
                "GIRV 2119\n" +
                "18 / 18\n" +
                "HIST 17A\n" +
                "M\n" +
                "2:00pm - 2:50pm\n" +
                "HSSB 3202\n" +
                "18 / 18\n" +
                "HIST 17A\n" +
                "M\n" +
                "3:00pm - 3:50pm\n" +
                "HSSB 2201\n" +
                "16 / 18\n" +
                "HIST 17A\n" +
                "M\n" +
                "4:00pm - 4:50pm\n" +
                "HSSB 2201\n" +
                "11 / 18\n" +
                "HIST 17A\n" +
                "M\n" +
                "5:00pm - 5:50pm\n" +
                "HSSB 4202\n" +
                "3 / 18\n" +
                "HIST 17A\n" +
                "M\n" +
                "6:00pm - 6:50pm\n" +
                "GIRV 1108\n" +
                "0 / 18\n" +
                "HIST 17A\n" +
                "T\n" +
                "8:00 am - 8:50 am\n" +
                "HSSB 2201\n" +
                "4 / 18\n" +
                "HIST 17A\n" +
                "M\n" +
                "8:00 am - 8:50 am\n" +
                "HSSB 4020\n" +
                "1 / 18\n" +
                "HIST 17A\n" +
                "M\n" +
                "8:00 am - 8:50 am\n" +
                "HSSB 4041\n" +
                "0 / 18\n" +
                "HIST 17A\n" +
                "M\n" +
                "9:00 am - 9:50 am\n" +
                "HSSB 4041\n" +
                "2 / 18\n" +
                "HIST 17A\n" +
                "W\n" +
                "9:00 am - 9:50 am\n" +
                "HSSB 4041\n" +
                "6 / 18\n" +
                "HIST 17A\n" +
                "F\n" +
                "4:00pm - 4:50pm\n" +
                "HSSB 4041\n" +
                "4 / 18\n" +
                "HIST 17A\n" +
                "T\n" +
                "2:00pm - 2:50pm\n" +
                "BUCHN1934\n" +
                "18 / 18\n" +
                "HIST 17A\n" +
                "T\n" +
                "3:00pm - 3:50pm\n" +
                "HSSB 2201\n" +
                "12 / 18\n" +
                "HIST 17A\n" +
                "T\n" +
                "4:00pm - 4:50pm\n" +
                "HSSB 2201\n" +
                "4 / 18\n" +
                "HIST 17A\n" +
                "T\n" +
                "5:00pm - 5:50pm\n" +
                "HSSB 4202\n" +
                "6 / 18\n" +
                "HIST 17A\n" +
                "W\n" +
                "8:00 am - 8:50 am\n" +
                "HSSB 2201\n" +
                "1 / 18\n" +
                "HIST 17A\n" +
                "W\n" +
                "9:00 am - 9:50 am\n" +
                "HSSB 2201\n" +
                "4 / 18\n" +
                "HIST 17A\n" +
                "W\n" +
                "1:00pm - 1:50pm\n" +
                "HSSB 4201\n" +
                "18 / 18\n" +
                "HIST 17A\n" +
                "W\n" +
                "3:00pm - 3:50pm\n" +
                "HSSB 2201\n" +
                "7 / 18\n" +
                "HIST 17A\n" +
                "W\n" +
                "4:00pm - 4:50pm\n" +
                "HSSB 2201\n" +
                "12 / 18\n" +
                "HIST 17A\n" +
                "M\n" +
                "7:00pm - 7:50pm\n" +
                "HSSB 4041\n" +
                "0 / 18\n" +
                "HIST 17A\n" +
                "W\n" +
                "6:00pm - 6:50pm\n" +
                "HSSB 3202\n" +
                "6 / 18\n" +
                "HIST 17A\n" +
                "W\n" +
                "7:00pm - 7:50pm\n" +
                "HSSB 2201\n" +
                "1 / 18\n" +
                "HIST 20\n" +
                "M W F\n" +
                "8:00 am - 8:50 am\n" +
                "LSB 1001\n" +
                "27 / 122\n" +
                "HIST 20\n" +
                "M\n" +
                "1:00pm - 1:50pm\n" +
                "HSSB 4201\n" +
                "11 / 18\n" +
                "HIST 20\n" +
                "M\n" +
                "2:00pm - 2:50pm\n" +
                "HSSB 3201\n" +
                "1 / 18\n" +
                "HIST 20\n" +
                "F\n" +
                "12:00pm - 12:50pm\n" +
                "HSSB 4041\n" +
                "0 / 18\n" +
                "HIST 20\n" +
                "T\n" +
                "12:00pm - 12:50pm\n" +
                "HSSB 2201\n" +
                "2 / 18\n" +
                "HIST 20\n" +
                "T\n" +
                "1:00pm - 1:50pm\n" +
                "HSSB 3201\n" +
                "3 / 18\n" +
                "HIST 20\n" +
                "W\n" +
                "10:00am - 10:50am\n" +
                "HSSB 2201\n" +
                "4 / 18\n" +
                "HIST 20\n" +
                "W\n" +
                "2:00pm - 2:50pm\n" +
                "HSSB 2201\n" +
                "2 / 18\n" +
                "HIST 20\n" +
                "W\n" +
                "5:00pm - 5:50pm\n" +
                "HSSB 4202\n" +
                "2 / 18\n" +
                "HIST 20\n" +
                "M\n" +
                "5:00pm - 5:50pm\n" +
                "HSSB 4201\n" +
                "2 / 18\n" +
                "HIST 46\n" +
                "T R\n" +
                "12:30pm - 1:45pm\n" +
                "BUCHN1930\n" +
                "83 / 83\n" +
                "HIST 46\n" +
                "R\n" +
                "3:00pm - 3:50pm\n" +
                "HSSB 2201\n" +
                "18 / 18\n" +
                "HIST 46\n" +
                "R\n" +
                "4:00pm - 4:50pm\n" +
                "HSSB 2201\n" +
                "18 / 18\n" +
                "HIST 46\n" +
                "R\n" +
                "5:00pm - 5:50pm\n" +
                "HSSB 4201\n" +
                "6 / 18\n" +
                "HIST 46\n" +
                "T\n" +
                "9:00 am - 9:50 am\n" +
                "HSSB 4201\n" +
                "6 / 18\n" +
                "HIST 46\n" +
                "T\n" +
                "10:00am - 10:50am\n" +
                "HSSB 4201\n" +
                "17 / 18\n" +
                "HIST 46\n" +
                "M\n" +
                "11:00am - 11:50am\n" +
                "HSSB 4080\n" +
                "18 / 18\n" +
                "HIST 49A\n" +
                "T R\n" +
                "9:30 am - 10:45am\n" +
                "CHEM 1171\n" +
                "42 / 42\n" +
                "HIST 49A\n" +
                "F\n" +
                "8:00 am - 8:50 am\n" +
                "HSSB 4080\n" +
                "3 / 9\n" +
                "HIST 49A\n" +
                "F\n" +
                "9:00 am - 9:50 am\n" +
                "HSSB 4080\n" +
                "9 / 9\n" +
                "HIST 49A\n" +
                "F\n" +
                "10:00am - 10:50am\n" +
                "HSSB 4080\n" +
                "9 / 9\n" +
                "HIST 49A\n" +
                "M\n" +
                "8:00 am - 8:50 am\n" +
                "HSSB 4080\n" +
                "3 / 9\n" +
                "HIST 49A\n" +
                "M\n" +
                "9:00 am - 9:50 am\n" +
                "HSSB 4080\n" +
                "9 / 9\n" +
                "HIST 49A\n" +
                "M\n" +
                "10:00am - 10:50am\n" +
                "HSSB 4080\n" +
                "9 / 9\n" +
                "HIST 99\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "0 / 5\n" +
                "HIST 107C\n" +
                "T R\n" +
                "2:00pm - 3:15pm\n" +
                "ARTS 1353\n" +
                "36 / 42\n" +
                "HIST 108W\n" +
                "T R\n" +
                "3:30pm - 4:45pm\n" +
                "TD-W 2600\n" +
                "30 / 42\n" +
                "HIST 115A\n" +
                "M W\n" +
                "9:30 am - 10:45am\n" +
                "ARTS 1349\n" +
                "20 / 42\n" +
                "HIST 121B\n" +
                "M W\n" +
                "2:00pm - 3:15pm\n" +
                "ARTS 1349\n" +
                "21 / 42\n" +
                "HIST 125\n" +
                "T R\n" +
                "11:00am - 12:15pm\n" +
                "ARTS 1356\n" +
                "10 / 42\n" +
                "HIST 129A\n" +
                "M W\n" +
                "2:00pm - 3:15pm\n" +
                "HSSB 4020\n" +
                "3 / 40\n" +
                "HIST 140A\n" +
                "M W\n" +
                "3:30pm - 4:45pm\n" +
                "ARTS 1353\n" +
                "30 / 42\n" +
                "HIST 146\n" +
                "M W\n" +
                "9:30 am - 10:45am\n" +
                "TD-W 1701\n" +
                "54 / 150\n" +
                "HIST 146BQ\n" +
                "T\n" +
                "9:00 am - 11:50am\n" +
                "HSSB 4041\n" +
                "8 / 20\n" +
                "HIST 151CU\n" +
                "M W\n" +
                "5:00pm - 6:15pm\n" +
                "ARTS 1353\n" +
                "13 / 42\n" +
                "HIST 159B\n" +
                "M W F\n" +
                "11:00am - 11:50am\n" +
                "ARTS 1353\n" +
                "19 / 42\n" +
                "HIST 161B\n" +
                "T R\n" +
                "11:00am - 12:15pm\n" +
                "HSSB 4020\n" +
                "40 / 40\n" +
                "HIST 161R\n" +
                "M\n" +
                "9:00 am - 11:50am\n" +
                "HSSB 4020\n" +
                "14 / 15\n" +
                "HIST 168A\n" +
                "M W\n" +
                "3:30pm - 4:45pm\n" +
                "ARTS 1349\n" +
                "24 / 32\n" +
                "HIST 168GQ\n" +
                "M\n" +
                "2:00pm - 4:45pm\n" +
                "SH 1609\n" +
                "4 / 10\n" +
                "HIST 173T\n" +
                "M W F\n" +
                "11:00am - 11:50am\n" +
                "BUCHN1910\n" +
                "36 / 156\n" +
                "HIST 175B\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "0 / 42\n" +
                "HIST 175R\n" +
                "F\n" +
                "9:00 am - 11:50am\n" +
                "HSSB 4041\n" +
                "5 / 15\n" +
                "HIST 185A\n" +
                "M W\n" +
                "5:00pm - 6:15pm\n" +
                "PHELP2524\n" +
                "21 / 21\n" +
                "HIST 187B\n" +
                "T R\n" +
                "3:30pm - 4:45pm\n" +
                "ARTS 1349\n" +
                "42 / 42\n" +
                "HIST 187R\n" +
                "W\n" +
                "1:00pm - 3:50pm\n" +
                "GIRV 2110\n" +
                "14 / 15\n" +
                "HIST 193F\n" +
                "T R\n" +
                "2:00pm - 3:15pm\n" +
                "TD-W 1701\n" +
                "120 / 150\n" +
                "HIST 194AH\n" +
                "T\n" +
                "1:00pm - 3:50pm\n" +
                "HSSB 4020\n" +
                "9 / 15\n" +
                "HIST 195IA\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "0 / 5\n" +
                "HIST 195IB\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "0 / 15\n" +
                "HIST 196\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "0 / 20\n" +
                "HIST 197IV\n" +
                "M W F\n" +
                "11:00am - 11:50am\n" +
                "HSSB 1210\n" +
                "3 / 25\n" +
                "HIST 199\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "0 / 20\n" +
                "HIST 199RA\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "0 / 20\n" +
                "INT 1\n" +
                "M\n" +
                "2:00pm - 2:50pm\n" +
                "LIB 1575\n" +
                "4 / 4\n" +
                "INT 1\n" +
                "T\n" +
                "2:00pm - 2:50pm\n" +
                "LIB 1575\n" +
                "4 / 4\n" +
                "INT 1\n" +
                "T\n" +
                "11:00am - 11:50am\n" +
                "LIB 1575\n" +
                "4 / 4\n" +
                "INT 1\n" +
                "W\n" +
                "11:00am - 11:50am\n" +
                "LIB 1575\n" +
                "4 / 4\n" +
                "INT 1\n" +
                "W\n" +
                "1:00pm - 1:50pm\n" +
                "LIB 1575\n" +
                "4 / 4\n" +
                "INT 1\n" +
                "R\n" +
                "1:00pm - 1:50pm\n" +
                "LIB 1575\n" +
                "4 / 4\n" +
                "INT 84ZB\n" +
                "R\n" +
                "3:30pm - 4:20pm\n" +
                "GIRV 1106\n" +
                "1 / 11\n" +
                "INT 84ZC\n" +
                "R\n" +
                "1:00pm - 2:50pm\n" +
                "HSSB 3201\n" +
                "4 / 20\n" +
                "INT 85\n" +
                "F\n" +
                "3:00pm - 4:30pm\n" +
                "NH 1006\n" +
                "0 / 100\n" +
                "INT 94BJ\n" +
                "MTW F\n" +
                "10:30am - 11:50am\n" +
                "PSB-N4606\n" +
                "0 / 20\n" +
                "INT 94FH\n" +
                "F\n" +
                "12:00pm - 12:50pm\n" +
                "HSSB 1236\n" +
                "0 / 20\n" +
                "INT 94GG\n" +
                "T\n" +
                "1:00pm - 2:50pm\n" +
                "ARTS 1344\n" +
                "0 / 20\n" +
                "INT 94GQ\n" +
                "W\n" +
                "2:00pm - 3:50pm\n" +
                "HSSB 1236\n" +
                "0 / 20\n" +
                "INT 94HG\n" +
                "S\n" +
                "10:00am - 12:00pm\n" +
                "ARTS 2622\n" +
                "0 / 11\n" +
                "INT 94HG\n" +
                "S\n" +
                "8:30 am - 5:30pm\n" +
                "ARTS\n" +
                "0 / 11\n" +
                "INT 94HI\n" +
                "S\n" +
                "9:00 am - 12:20pm\n" +
                "PSY-E3834\n" +
                "0 / 20\n" +
                "INT 94HZ\n" +
                "W\n" +
                "5:00pm - 5:50pm\n" +
                "TD-W 2517\n" +
                "0 / 20\n" +
                "INT 94JK\n" +
                "T\n" +
                "2:00pm - 4:20pm\n" +
                "HSSB 3201\n" +
                "0 / 20\n" +
                "INT 94JV\n" +
                "T\n" +
                "2:00pm - 2:50pm\n" +
                "HSSB 2201\n" +
                "0 / 20\n" +
                "INT 94PO\n" +
                "M\n" +
                "4:00pm - 4:50pm\n" +
                "TD-W 1530\n" +
                "0 / 20\n" +
                "INT 94QJ\n" +
                "T\n" +
                "12:30pm - 1:20pm\n" +
                "SH 2617\n" +
                "0 / 20\n" +
                "INT 94QV\n" +
                "T\n" +
                "11:00am - 11:50am\n" +
                "LSB 1101\n" +
                "0 / 20\n" +
                "INT 94RK\n" +
                "T\n" +
                "5:00pm - 5:50pm\n" +
                "HSSB 3201\n" +
                "0 / 20\n" +
                "INT 94SH\n" +
                "W\n" +
                "1:00pm - 1:50pm\n" +
                "RECEN2103\n" +
                "0 / 20\n" +
                "INT 94SR\n" +
                "W\n" +
                "12:00pm - 12:50pm\n" +
                "GIRV 2108\n" +
                "0 / 20\n" +
                "INT 94SW\n" +
                "W\n" +
                "1:00pm - 1:50pm\n" +
                "HSSB 3202\n" +
                "0 / 20\n" +
                "INT 94SX\n" +
                "M\n" +
                "2:00pm - 2:50pm\n" +
                "HSSB 2201\n" +
                "0 / 20\n" +
                "INT 94SY\n" +
                "W\n" +
                "9:00 am - 9:50 am\n" +
                "GIRV 2110\n" +
                "0 / 11\n" +
                "INT 94TC\n" +
                "W\n" +
                "4:00pm - 5:50pm\n" +
                "PSY-E1806\n" +
                "0 / 20\n" +
                "INT 94TC\n" +
                "W\n" +
                "4:00pm - 5:50pm\n" +
                "HSSB 3202\n" +
                "0 / 20\n" +
                "INT 94TD\n" +
                "W\n" +
                "3:00pm - 4:15pm\n" +
                "ED 1205\n" +
                "0 / 20\n" +
                "INT 94TE\n" +
                "M\n" +
                "1:00pm - 1:50pm\n" +
                "HSSB 3202\n" +
                "0 / 20\n" +
                "INT 94TG\n" +
                "T\n" +
                "6:00pm - 6:50pm\n" +
                "HSSB 3201\n" +
                "0 / 20\n" +
                "INT 94TH\n" +
                "T\n" +
                "1:00pm - 1:50pm\n" +
                "HSSB 4202\n" +
                "0 / 20\n" +
                "INT 94TJ\n" +
                "F\n" +
                "10:00am - 11:50am\n" +
                "GIRV 1106\n" +
                "0 / 11\n" +
                "INT 94TK\n" +
                "F\n" +
                "3:00pm - 3:50pm\n" +
                "GIRV 2108\n" +
                "0 / 20\n" +
                "INT 94TL\n" +
                "W\n" +
                "1:00pm - 1:50pm\n" +
                "PHELP1448\n" +
                "0 / 20\n" +
                "INT 94TM\n" +
                "T\n" +
                "9:00 am - 9:50 am\n" +
                "PHELP1440\n" +
                "0 / 20\n" +
                "INT 94TN\n" +
                "W\n" +
                "3:30pm - 4:20pm\n" +
                "BUCHN1934\n" +
                "0 / 20\n" +
                "INT 94TO\n" +
                "W\n" +
                "4:00pm - 5:50pm\n" +
                "HSSB 2202\n" +
                "0 / 11\n" +
                "INT 95\n" +
                "T\n" +
                "5:00pm - 5:50pm\n" +
                "NH 1006\n" +
                "0 / 100\n" +
                "INT 95\n" +
                "R\n" +
                "8:00 am - 8:50 am\n" +
                "GIRV 1106\n" +
                "0 / 15\n" +
                "INT 95\n" +
                "R\n" +
                "11:00am - 11:50am\n" +
                "GIRV 2110\n" +
                "0 / 15\n" +
                "INT 95\n" +
                "R\n" +
                "1:00pm - 1:50pm\n" +
                "GIRV 1106\n" +
                "0 / 15\n" +
                "INT 95\n" +
                "R\n" +
                "4:00pm - 4:50pm\n" +
                "GIRV 2110\n" +
                "0 / 15\n" +
                "INT 95\n" +
                "R\n" +
                "2:00pm - 2:50pm\n" +
                "GIRV 1106\n" +
                "0 / 15\n" +
                "INT 95\n" +
                "F\n" +
                "1:00pm - 1:50pm\n" +
                "GIRV 2110\n" +
                "0 / 15\n" +
                "INT 95\n" +
                "F\n" +
                "2:00pm - 2:50pm\n" +
                "GIRV 1106\n" +
                "0 / 15\n" +
                "INT 95\n" +
                "R\n" +
                "9:00 am - 9:50 am\n" +
                "GIRV 1106\n" +
                "0 / 15\n" +
                "INT 95\n" +
                "F\n" +
                "8:00 am - 8:50 am\n" +
                "GIRV 1106\n" +
                "0 / 15\n" +
                "INT 95\n" +
                "R\n" +
                "12:00pm - 12:50pm\n" +
                "GIRV 2110\n" +
                "0 / 15\n" +
                "INT 98RA\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "0 / 15\n" +
                "INT 156EE\n" +
                "T R\n" +
                "2:00pm - 3:15pm\n" +
                "HSSB 4202\n" +
                "3 / 12\n" +
                "INT 184DH\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "0 / 20\n" +
                "INT 184PD\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "0 / 20\n" +
                "INT 184ZC\n" +
                "F\n" +
                "9:00 am - 10:50am\n" +
                "HSSB 2201\n" +
                "4 / 20\n" +
                "INT 184ZL\n" +
                "M\n" +
                "4:00pm - 5:50pm\n" +
                "GIRV 1108\n" +
                "0 / 20\n" +
                "INT 184ZN\n" +
                "T\n" +
                "3:00pm - 4:50pm\n" +
                "TD-E 1101\n" +
                "5 / 12\n" +
                "INT 184ZO\n" +
                "F\n" +
                "11:00am - 12:50pm\n" +
                "HSSB 4201\n" +
                "5 / 20\n" +
                "INT 184ZP\n" +
                "W\n" +
                "3:00pm - 4:50pm\n" +
                "PHELP1530\n" +
                "0 / 20\n" +
                "INT 185CT\n" +
                "W\n" +
                "10:00am - 12:00pm\n" +
                "HSSB 6056\n" +
                "0 / 15\n" +
                "INT 185CW\n" +
                "T\n" +
                "6:30pm - 7:45pm\n" +
                "SH 2635\n" +
                "0 / 25\n" +
                "INT 185CW\n" +
                "R\n" +
                "6:30pm - 7:45pm\n" +
                "SH 2635\n" +
                "0 / 25\n" +
                "INT 185MT\n" +
                "T R\n" +
                "7:00pm - 9:00pm\n" +
                "GIRV 1112\n" +
                "1 / 30\n" +
                "INT 185PP\n" +
                "T R\n" +
                "11:00am - 12:15pm\n" +
                "HSSB 6056\n" +
                "0 / 16\n" +
                "INT 185ST\n" +
                "F\n" +
                "3:00pm - 4:50pm\n" +
                "TD-W 2600\n" +
                "0 / 26\n" +
                "INT 185UA\n" +
                "W\n" +
                "4:00pm - 7:00pm\n" +
                "HSSB 6056\n" +
                "0 / 15\n" +
                "INT 185VW\n" +
                "M\n" +
                "5:00pm - 7:00pm\n" +
                "HSSB 6056\n" +
                "2 / 10\n" +
                "INT 190MA\n" +
                "F\n" +
                "12:00pm - 1:00pm\n" +
                "477\n" +
                "0 / 20\n" +
                "INT 190MB\n" +
                "M\n" +
                "5:00pm - 6:00pm\n" +
                "477\n" +
                "0 / 20\n" +
                "INT 192DC\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "31 / 45\n" +
                "INT 199DC\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "32 / 45\n" +
                "INT 199DC\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "0 / 0\n" +
                "ITAL 1\n" +
                "MTWR\n" +
                "1:00pm - 1:50pm\n" +
                "HSSB 1224\n" +
                "9 / 20\n" +
                "ITAL 1\n" +
                "MTWR\n" +
                "11:00am - 11:50am\n" +
                "GIRV 2129\n" +
                "7 / 20\n" +
                "ITAL 1\n" +
                "MTWR\n" +
                "12:00pm - 12:50pm\n" +
                "HSSB 2251\n" +
                "2 / 20\n" +
                "ITAL 1\n" +
                "MTWR\n" +
                "2:00pm - 2:50pm\n" +
                "HSSB 1206\n" +
                "0 / 20\n" +
                "ITAL 1\n" +
                "MTWR\n" +
                "1:00pm - 1:50pm\n" +
                "GIRV 1119\n" +
                "3 / 20\n" +
                "ITAL 1\n" +
                "MTWR\n" +
                "10:00am - 10:50am\n" +
                "HSSB 1224\n" +
                "7 / 20\n" +
                "ITAL 1\n" +
                "MTWR\n" +
                "11:00am - 11:50am\n" +
                "HSSB 1206\n" +
                "1 / 20\n" +
                "ITAL 1\n" +
                "MTWR\n" +
                "2:00pm - 2:50pm\n" +
                "HSSB 1228\n" +
                "4 / 20\n" +
                "ITAL 1\n" +
                "MTWR\n" +
                "3:00pm - 3:50pm\n" +
                "HSSB 1237\n" +
                "1 / 26\n" +
                "ITAL 2\n" +
                "MTWR\n" +
                "10:00am - 10:50am\n" +
                "387 104\n" +
                "10 / 26\n" +
                "ITAL 2\n" +
                "MTWR\n" +
                "12:00pm - 12:50pm\n" +
                "HSSB 1228\n" +
                "13 / 26\n" +
                "ITAL 3\n" +
                "MTWR\n" +
                "11:00am - 11:50am\n" +
                "PHELP1445\n" +
                "22 / 26\n" +
                "ITAL 3\n" +
                "MTWR\n" +
                "9:00 am - 9:50 am\n" +
                "PHELP3519\n" +
                "9 / 26\n" +
                "ITAL 4\n" +
                "MTWR\n" +
                "10:00am - 10:50am\n" +
                "HSSB 1223\n" +
                "16 / 26\n" +
                "ITAL 4\n" +
                "MTWR\n" +
                "1:00pm - 1:50pm\n" +
                "HSSB 1223\n" +
                "14 / 26\n" +
                "ITAL 6\n" +
                "MTWR\n" +
                "9:00 am - 9:50 am\n" +
                "HSSB 1233\n" +
                "4 / 26\n" +
                "ITAL 101\n" +
                "T R\n" +
                "12:30pm - 1:45pm\n" +
                "PHELP5316\n" +
                "14 / 15\n" +
                "ITAL 119A\n" +
                "T R\n" +
                "5:00pm - 6:15pm\n" +
                "PHELP5316\n" +
                "7 / 15\n" +
                "ITAL 126A\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "0 / 30\n" +
                "ITAL 161AX\n" +
                "M W\n" +
                "2:00pm - 3:15pm\n" +
                "HFH 1104\n" +
                "23 / 140\n" +
                "ITAL 195H\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "0 / 0\n" +
                "ITAL 199\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "0 / 5\n" +
                "JAPAN 1\n" +
                "MTWRF\n" +
                "9:00 am - 9:50 am\n" +
                "HSSB 1207\n" +
                "15 / 15\n" +
                "JAPAN 1\n" +
                "MTWRF\n" +
                "10:00am - 10:50am\n" +
                "HSSB 1214\n" +
                "15 / 15\n" +
                "JAPAN 1\n" +
                "MTWRF\n" +
                "11:00am - 11:50am\n" +
                "HSSB 1207\n" +
                "15 / 15\n" +
                "JAPAN 1\n" +
                "MTWRF\n" +
                "12:00pm - 12:50pm\n" +
                "HSSB 1207\n" +
                "15 / 15\n" +
                "JAPAN 1\n" +
                "MTWRF\n" +
                "1:00pm - 1:50pm\n" +
                "HSSB 1207\n" +
                "15 / 15\n" +
                "JAPAN 1\n" +
                "MTWRF\n" +
                "11:00am - 11:50am\n" +
                "HSSB 3201\n" +
                "0 / 11\n" +
                "JAPAN 4\n" +
                "MTWRF\n" +
                "10:00am - 10:50am\n" +
                "HSSB 2202\n" +
                "13 / 15\n" +
                "JAPAN 4\n" +
                "MTWRF\n" +
                "11:00am - 11:50am\n" +
                "HSSB 2202\n" +
                "15 / 15\n" +
                "JAPAN 4\n" +
                "MTWRF\n" +
                "1:00pm - 1:50pm\n" +
                "HSSB 1211\n" +
                "20 / 20\n" +
                "JAPAN 112\n" +
                "T R\n" +
                "2:00pm - 3:15pm\n" +
                "HSSB 1174\n" +
                "36 / 75\n" +
                "JAPAN 120A\n" +
                "MTWR\n" +
                "11:00am - 11:50am\n" +
                "HSSB 1211\n" +
                "15 / 15\n" +
                "JAPAN 120A\n" +
                "MTWR\n" +
                "9:00 am - 9:50 am\n" +
                "HSSB 2202\n" +
                "6 / 15\n" +
                "JAPAN 144\n" +
                "T R\n" +
                "12:30pm - 1:45pm\n" +
                "HSSB 2252\n" +
                "4 / 15\n" +
                "JAPAN 181\n" +
                "T R\n" +
                "3:30pm - 4:45pm\n" +
                "HSSB 2202\n" +
                "6 / 12\n" +
                "JAPAN 197\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "0 / 3\n" +
                "JAPAN 198\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "0 / 10\n" +
                "JAPAN 199\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "0 / 5\n" +
                "JAPAN 199RA\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "0 / 5\n" +
                "KOR 60\n" +
                "T R\n" +
                "8:00 am - 9:15 am\n" +
                "PHELP1425\n" +
                "28 / 64\n" +
                "KOR 60\n" +
                "W\n" +
                "4:00pm - 4:50pm\n" +
                "GIRV 1106\n" +
                "15 / 15\n" +
                "KOR 60\n" +
                "W\n" +
                "5:00pm - 5:50pm\n" +
                "GIRV 1106\n" +
                "10 / 15\n" +
                "KOR 60\n" +
                "F\n" +
                "10:00am - 10:50am\n" +
                "GIRV 2135\n" +
                "1 / 15\n" +
                "KOR 60\n" +
                "F\n" +
                "11:00am - 11:50am\n" +
                "HSSB 1232\n" +
                "2 / 15\n" +
                "KOR 182A\n" +
                "M W\n" +
                "9:30 am - 10:45am\n" +
                "ARTS 1356\n" +
                "37 / 48\n" +
                "KOR 199\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "0 / 2\n" +
                "LATIN 1\n" +
                "MTWRF\n" +
                "11:00am - 11:50am\n" +
                "HSSB 2251\n" +
                "16 / 25\n" +
                "LATIN 1\n" +
                "MTWRF\n" +
                "1:00pm - 1:50pm\n" +
                "HSSB 1210\n" +
                "6 / 25\n" +
                "LATIN 1\n" +
                "MTWRF\n" +
                "9:00 am - 9:50 am\n" +
                "HSSB 1214\n" +
                "2 / 25\n" +
                "LATIN 100\n" +
                "M W F\n" +
                "9:00 am - 9:50 am\n" +
                "HSSB 4201\n" +
                "8 / 24\n" +
                "LATIN 103\n" +
                "T R\n" +
                "11:00am - 12:15pm\n" +
                "GIRV 2135\n" +
                "2 / 15\n" +
                "LATIN 116\n" +
                "M W\n" +
                "12:30pm - 1:50pm\n" +
                "HSSB 4065\n" +
                "1 / 5\n" +
                "LATIN 199\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "0 / 6\n" +
                "LAIS 10\n" +
                "T R\n" +
                "3:30pm - 4:45pm\n" +
                "HFH 1104\n" +
                "48 / 110\n" +
                "LAIS 10\n" +
                "W\n" +
                "5:00pm - 5:50pm\n" +
                "PHELP1508\n" +
                "0 / 25\n" +
                "LAIS 10\n" +
                "W\n" +
                "6:00pm - 6:50pm\n" +
                "PHELP1445\n" +
                "3 / 25\n" +
                "LAIS 10\n" +
                "R\n" +
                "10:00am - 10:50am\n" +
                "PHELP2532\n" +
                "7 / 25\n" +
                "LAIS 10\n" +
                "R\n" +
                "2:00pm - 2:50pm\n" +
                "GIRV 2108\n" +
                "18 / 25\n" +
                "LAIS 10\n" +
                "W\n" +
                "11:00am - 11:50am\n" +
                "GIRV 1119\n" +
                "13 / 25\n" +
                "LAIS 10\n" +
                "R\n" +
                "11:00am - 11:50am\n" +
                "PHELP1440\n" +
                "7 / 25\n" +
                "LAIS 195A\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "0 / 5\n" +
                "LAIS 195B\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "0 / 5\n" +
                "LAIS 199\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "0 / 10\n" +
                "LING 2G\n" +
                "M W\n" +
                "3:30pm - 5:20pm\n" +
                "PHELP1529\n" +
                "7 / 15\n" +
                "LING 3A\n" +
                "M W\n" +
                "1:00pm - 2:50pm\n" +
                "GIRV 1108\n" +
                "1 / 18\n" +
                "LING 3A\n" +
                "T R\n" +
                "9:00 am - 10:50am\n" +
                "GIRV 1108\n" +
                "0 / 18\n" +
                "LING 3A\n" +
                "M W\n" +
                "11:00am - 12:50pm\n" +
                "HSSB 1227\n" +
                "1 / 18\n" +
                "LING 3A\n" +
                "T R\n" +
                "2:00pm - 3:50pm\n" +
                "HSSB 1236\n" +
                "2 / 18\n" +
                "LING 3A\n" +
                "T R\n" +
                "4:00pm - 5:50pm\n" +
                "GIRV 1108\n" +
                "0 / 18\n" +
                "LING 3B\n" +
                "M W\n" +
                "10:00am - 11:50am\n" +
                "GIRV 1108\n" +
                "0 / 18\n" +
                "LING 3B\n" +
                "M W\n" +
                "8:00 am - 9:50 am\n" +
                "SH 1609\n" +
                "0 / 18\n" +
                "LING 3B\n" +
                "T R\n" +
                "12:00pm - 1:50pm\n" +
                "HSSB 1231\n" +
                "1 / 18\n" +
                "LING 3B\n" +
                "T R\n" +
                "9:00 am - 10:50am\n" +
                "HSSB 1228\n" +
                "0 / 18\n" +
                "LING 3B\n" +
                "M W\n" +
                "1:00pm - 2:50pm\n" +
                "HSSB 1232\n" +
                "1 / 18\n" +
                "LING 3C\n" +
                "M W\n" +
                "10:00am - 11:50am\n" +
                "SH 1609\n" +
                "3 / 18\n" +
                "LING 3C\n" +
                "T R\n" +
                "8:00 am - 9:50 am\n" +
                "GIRV 2135\n" +
                "0 / 18\n" +
                "LING 3C\n" +
                "M W\n" +
                "12:00pm - 1:50pm\n" +
                "SH 1609\n" +
                "10 / 18\n" +
                "LING 3C\n" +
                "T R\n" +
                "11:00am - 12:50pm\n" +
                "HSSB 1215\n" +
                "4 / 18\n" +
                "LING 4\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "0 / 15\n" +
                "LING 5\n" +
                "T R\n" +
                "5:00pm - 6:50pm\n" +
                "HSSB 2201\n" +
                "0 / 15\n" +
                "LING 7\n" +
                "M W\n" +
                "5:00pm - 6:50pm\n" +
                "SH 1609\n" +
                "0 / 15\n" +
                "LING 8\n" +
                "M W\n" +
                "4:00pm - 5:50pm\n" +
                "HSSB 1215\n" +
                "0 / 15\n" +
                "LING 12\n" +
                "M W\n" +
                "3:00pm - 4:50pm\n" +
                "HSSB 2251\n" +
                "7 / 18\n" +
                "LING 12\n" +
                "M W\n" +
                "10:00am - 11:50am\n" +
                "HSSB 1228\n" +
                "15 / 18\n" +
                "LING 12\n" +
                "T R\n" +
                "12:00pm - 1:50pm\n" +
                "HSSB 1236\n" +
                "18 / 18\n" +
                "LING 12\n" +
                "T R\n" +
                "9:00 am - 10:50am\n" +
                "HSSB 3202\n" +
                "18 / 18\n" +
                "LING 12\n" +
                "M W\n" +
                "2:00pm - 3:50pm\n" +
                "HSSB 1210\n" +
                "18 / 18\n" +
                "LING 12\n" +
                "M W\n" +
                "12:00pm - 1:50pm\n" +
                "HSSB 1231\n" +
                "6 / 18\n" +
                "LING 20\n" +
                "M W\n" +
                "12:30pm - 1:45pm\n" +
                "GIRV 1004\n" +
                "89 / 116\n" +
                "LING 20\n" +
                "M\n" +
                "4:00pm - 4:50pm\n" +
                "GIRV 2112\n" +
                "30 / 30\n" +
                "LING 20\n" +
                "M\n" +
                "5:00pm - 5:50pm\n" +
                "SH 3605\n" +
                "6 / 30\n" +
                "LING 20\n" +
                "M\n" +
                "6:00pm - 6:50pm\n" +
                "SH 3605\n" +
                "3 / 30\n" +
                "LING 20\n" +
                "T\n" +
                "8:00 am - 8:50 am\n" +
                "GIRV 2124\n" +
                "6 / 30\n" +
                "LING 20\n" +
                "W\n" +
                "8:00 am - 8:50 am\n" +
                "SH 3605\n" +
                "5 / 30\n" +
                "LING 20\n" +
                "T\n" +
                "10:00am - 10:50am\n" +
                "SH 3605\n" +
                "28 / 30\n" +
                "LING 20\n" +
                "T\n" +
                "5:00pm - 5:50pm\n" +
                "HSSB 1231\n" +
                "11 / 26\n" +
                "LING 50\n" +
                "T R\n" +
                "2:00pm - 3:15pm\n" +
                "HFH 1104\n" +
                "120 / 180\n" +
                "LING 50\n" +
                "W\n" +
                "8:00 am - 8:50 am\n" +
                "GIRV 1116\n" +
                "30 / 30\n" +
                "LING 50\n" +
                "W\n" +
                "9:00 am - 9:50 am\n" +
                "NH 1109\n" +
                "30 / 30\n" +
                "LING 50\n" +
                "W\n" +
                "5:00pm - 5:50pm\n" +
                "GIRV 2116\n" +
                "30 / 30\n" +
                "LING 50\n" +
                "W\n" +
                "6:00pm - 6:50pm\n" +
                "PHELP3519\n" +
                "30 / 30\n" +
                "LING 50\n" +
                "R\n" +
                "8:00 am - 8:50 am\n" +
                "SH 3605\n" +
                "0 / 30\n" +
                "LING 50\n" +
                "R\n" +
                "9:00 am - 9:50 am\n" +
                "SH 3605\n" +
                "0 / 30\n" +
                "LING 101\n" +
                "M W\n" +
                "9:30 am - 10:45am\n" +
                "SH 3605\n" +
                "29 / 35\n" +
                "LING 104\n" +
                "T\n" +
                "4:00pm - 6:20pm\n" +
                "PHELP1525\n" +
                "12 / 24\n" +
                "LING 106\n" +
                "T R\n" +
                "9:30 am - 10:45am\n" +
                "PSYCH1924\n" +
                "71 / 115\n" +
                "LING 106\n" +
                "W\n" +
                "9:00 am - 9:50 am\n" +
                "GIRV 1119\n" +
                "21 / 30\n" +
                "LING 106\n" +
                "W\n" +
                "5:00pm - 5:50pm\n" +
                "SH 3605\n" +
                "26 / 30\n" +
                "LING 106\n" +
                "W\n" +
                "6:00pm - 6:50pm\n" +
                "SH 3605\n" +
                "10 / 30\n" +
                "LING 106\n" +
                "R\n" +
                "8:00 am - 8:50 am\n" +
                "GIRV 1116\n" +
                "14 / 30\n" +
                "LING 132\n" +
                "T R\n" +
                "11:00am - 12:15pm\n" +
                "BUCHN1920\n" +
                "60 / 147\n" +
                "LING 132\n" +
                "T\n" +
                "5:00pm - 5:50pm\n" +
                "SH 3605\n" +
                "30 / 30\n" +
                "LING 132\n" +
                "T\n" +
                "6:00pm - 6:50pm\n" +
                "SH 3605\n" +
                "30 / 30\n" +
                "LING 132\n" +
                "W\n" +
                "5:00pm - 5:50pm\n" +
                "GIRV 2112\n" +
                "0 / 30\n" +
                "LING 132\n" +
                "W\n" +
                "6:00pm - 6:50pm\n" +
                "PHELP1508\n" +
                "0 / 30\n" +
                "LING 132\n" +
                "R\n" +
                "9:00 am - 9:50 am\n" +
                "GIRV 2124\n" +
                "0 / 27\n" +
                "LING 136\n" +
                "T R\n" +
                "12:30pm - 1:45pm\n" +
                "BUCHN1940\n" +
                "52 / 120\n" +
                "LING 136\n" +
                "W\n" +
                "8:00 am - 8:50 am\n" +
                "GIRV 2108\n" +
                "22 / 30\n" +
                "LING 136\n" +
                "W\n" +
                "5:00pm - 5:50pm\n" +
                "GIRV 1116\n" +
                "30 / 30\n" +
                "LING 136\n" +
                "W\n" +
                "6:00pm - 6:50pm\n" +
                "PHELP2514\n" +
                "0 / 30\n" +
                "LING 136\n" +
                "R\n" +
                "8:00 am - 8:50 am\n" +
                "GIRV 2108\n" +
                "0 / 30\n" +
                "LING 141\n" +
                "M W\n" +
                "3:30pm - 4:45pm\n" +
                "SH 3605\n" +
                "1 / 25\n" +
                "LING 185\n" +
                "M W\n" +
                "2:00pm - 3:15pm\n" +
                "NH 1006\n" +
                "60 / 120\n" +
                "LING 185\n" +
                "M\n" +
                "4:00pm - 4:50pm\n" +
                "GIRV 1116\n" +
                "30 / 30\n" +
                "LING 185\n" +
                "M\n" +
                "5:00pm - 5:50pm\n" +
                "GIRV 1116\n" +
                "30 / 30\n" +
                "LING 185\n" +
                "M\n" +
                "6:00pm - 6:50pm\n" +
                "GIRV 2123\n" +
                "0 / 30\n" +
                "LING 185\n" +
                "T\n" +
                "8:00 am - 8:50 am\n" +
                "SH 3605\n" +
                "0 / 30\n" +
                "LING 191\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "0 / 10\n" +
                "LING 191\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "0 / 10\n" +
                "LING 192\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "0 / 15\n" +
                "LING 195A\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "0 / 10\n" +
                "LING 195B\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "0 / 10\n" +
                "LING 195C\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "0 / 10\n" +
                "LING 199\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "0 / 10\n" +
                "LING 199RA\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "0 / 10\n" +
                "MATRL 100A\n" +
                "M W\n" +
                "3:30pm - 4:45pm\n" +
                "PHELP1425\n" +
                "40 / 40\n" +
                "MATRL 100A\n" +
                "M\n" +
                "1:00pm - 1:50pm\n" +
                "PHELP1160\n" +
                "20 / 20\n" +
                "MATRL 100A\n" +
                "F\n" +
                "3:00pm - 3:50pm\n" +
                "PHELP1448\n" +
                "20 / 20\n" +
                "MATRL 162A\n" +
                "M W\n" +
                "3:30pm - 4:45pm\n" +
                "NH 1111\n" +
                "1 / 15\n" +
                "MATRL 162A\n" +
                "F\n" +
                "10:00am - 10:50am\n" +
                "PHELP2524\n" +
                "1 / 15\n" +
                "MATH 3A\n" +
                "M W F\n" +
                "9:00 am - 9:50 am\n" +
                "IV THEA1\n" +
                "1 / 75\n" +
                "MATH 3A\n" +
                "T\n" +
                "8:00 am - 8:50 am\n" +
                "HSSB 1214\n" +
                "0 / 25\n" +
                "MATH 3A\n" +
                "T\n" +
                "4:00pm - 4:50pm\n" +
                "HSSB 1215\n" +
                "0 / 25\n" +
                "MATH 3A\n" +
                "T\n" +
                "5:00pm - 5:50pm\n" +
                "HSSB 1227\n" +
                "0 / 25\n" +
                "MATH 3A\n" +
                "T\n" +
                "6:00pm - 6:50pm\n" +
                "HSSB 1236\n" +
                "0 / 25\n" +
                "MATH 3A\n" +
                "T\n" +
                "7:00pm - 7:50pm\n" +
                "HSSB 1227\n" +
                "0 / 25\n" +
                "MATH 3A\n" +
                "R\n" +
                "8:00 am - 8:50 am\n" +
                "HSSB 1224\n" +
                "0 / 25\n" +
                "MATH 3A\n" +
                "R\n" +
                "7:00pm - 7:50pm\n" +
                "HSSB 1236\n" +
                "0 / 25\n" +
                "MATH 3A\n" +
                "R\n" +
                "5:00pm - 5:50pm\n" +
                "HSSB 1233\n" +
                "0 / 25\n" +
                "MATH 3A\n" +
                "R\n" +
                "6:00pm - 6:50pm\n" +
                "LSB 1101\n" +
                "0 / 25\n" +
                "MATH 3A\n" +
                "T\n" +
                "8:00 am - 8:50 am\n" +
                "HSSB 1211\n" +
                "0 / 25\n" +
                "MATH 3A\n" +
                "T\n" +
                "5:00pm - 5:50pm\n" +
                "HSSB 1223\n" +
                "0 / 25\n" +
                "MATH 3A\n" +
                "T\n" +
                "7:00pm - 7:50pm\n" +
                "HSSB 1223\n" +
                "0 / 25\n" +
                "MATH 3A\n" +
                "T\n" +
                "6:00pm - 6:50pm\n" +
                "HSSB 1228\n" +
                "0 / 25\n" +
                "MATH 3A\n" +
                "R\n" +
                "8:00 am - 8:50 am\n" +
                "HSSB 1215\n" +
                "0 / 25\n" +
                "MATH 3A\n" +
                "R\n" +
                "5:00pm - 5:50pm\n" +
                "HSSB 1232\n" +
                "1 / 25\n" +
                "MATH 3A\n" +
                "R\n" +
                "6:00pm - 6:50pm\n" +
                "HSSB 2251\n" +
                "0 / 25\n" +
                "MATH 3A\n" +
                "R\n" +
                "7:00pm - 7:50pm\n" +
                "HSSB 1233\n" +
                "0 / 25\n" +
                "MATH 3A\n" +
                "R\n" +
                "8:00 am - 8:50 am\n" +
                "HSSB 1214\n" +
                "0 / 25\n" +
                "MATH 3A\n" +
                "R\n" +
                "5:00pm - 5:50pm\n" +
                "HSSB 1231\n" +
                "0 / 25\n" +
                "MATH 3A\n" +
                "R\n" +
                "6:00pm - 6:50pm\n" +
                "HSSB 1233\n" +
                "0 / 25\n" +
                "MATH 3A\n" +
                "R\n" +
                "7:00pm - 7:50pm\n" +
                "HSSB 1227\n" +
                "0 / 25\n" +
                "MATH 3A\n" +
                "M W F\n" +
                "2:00pm - 2:50pm\n" +
                "BUCHN1910\n" +
                "1 / 75\n" +
                "MATH 3A\n" +
                "T\n" +
                "8:00 am - 8:50 am\n" +
                "HSSB 1206\n" +
                "0 / 25\n" +
                "MATH 3A\n" +
                "T\n" +
                "5:00pm - 5:50pm\n" +
                "HSSB 1214\n" +
                "1 / 25\n" +
                "MATH 3A\n" +
                "T\n" +
                "6:00pm - 6:50pm\n" +
                "HSSB 1224\n" +
                "0 / 25\n" +
                "MATH 3A\n" +
                "T\n" +
                "7:00pm - 7:50pm\n" +
                "HSSB 1210\n" +
                "0 / 25\n" +
                "MATH 3A\n" +
                "R\n" +
                "8:00 am - 8:50 am\n" +
                "HSSB 1207\n" +
                "0 / 25\n" +
                "MATH 3A\n" +
                "R\n" +
                "5:00pm - 5:50pm\n" +
                "HSSB 1224\n" +
                "0 / 25\n" +
                "MATH 3A\n" +
                "R\n" +
                "6:00pm - 6:50pm\n" +
                "HSSB 1227\n" +
                "0 / 25\n" +
                "MATH 3A\n" +
                "R\n" +
                "7:00pm - 7:50pm\n" +
                "HSSB 1223\n" +
                "0 / 25\n" +
                "MATH 3A\n" +
                "R\n" +
                "8:00 am - 8:50 am\n" +
                "HSSB 1206\n" +
                "0 / 25\n" +
                "MATH 3A\n" +
                "R\n" +
                "5:00pm - 5:50pm\n" +
                "HSSB 1223\n" +
                "0 / 25\n" +
                "MATH 3A\n" +
                "R\n" +
                "6:00pm - 6:50pm\n" +
                "HSSB 1224\n" +
                "0 / 25\n" +
                "MATH 3A\n" +
                "R\n" +
                "7:00pm - 7:50pm\n" +
                "HSSB 1211\n" +
                "0 / 25\n" +
                "MATH 3B\n" +
                "T R\n" +
                "8:00 am - 9:15 am\n" +
                "BUCHN1920\n" +
                "2 / 34\n" +
                "MATH 3B\n" +
                "M\n" +
                "12:00pm - 12:50pm\n" +
                "HSSB 1210\n" +
                "2 / 22\n" +
                "MATH 3B\n" +
                "M\n" +
                "5:00pm - 5:50pm\n" +
                "HSSB 1232\n" +
                "0 / 25\n" +
                "MATH 3B\n" +
                "M\n" +
                "6:00pm - 6:50pm\n" +
                "SH 1430\n" +
                "0 / 25\n" +
                "MATH 3B\n" +
                "M\n" +
                "7:00pm - 7:50pm\n" +
                "HSSB 1228\n" +
                "0 / 25\n" +
                "MATH 3B\n" +
                "M\n" +
                "5:00pm - 5:50pm\n" +
                "HSSB 1224\n" +
                "0 / 25\n" +
                "MATH 3B\n" +
                "M\n" +
                "6:00pm - 6:50pm\n" +
                "HSSB 2251\n" +
                "0 / 25\n" +
                "MATH 3B\n" +
                "T R\n" +
                "2:00pm - 3:15pm\n" +
                "CHEM 1179\n" +
                "6 / 79\n" +
                "MATH 3B\n" +
                "M\n" +
                "8:00 am - 8:50 am\n" +
                "HSSB 1211\n" +
                "0 / 25\n" +
                "MATH 3B\n" +
                "M\n" +
                "4:00pm - 4:50pm\n" +
                "HSSB 1211\n" +
                "0 / 25\n" +
                "MATH 3B\n" +
                "M\n" +
                "5:00pm - 5:50pm\n" +
                "HSSB 1223\n" +
                "0 / 25\n" +
                "MATH 3B\n" +
                "M\n" +
                "6:00pm - 6:50pm\n" +
                "HSSB 1236\n" +
                "0 / 25\n" +
                "MATH 3B\n" +
                "M\n" +
                "7:00pm - 7:50pm\n" +
                "HSSB 1224\n" +
                "0 / 25\n" +
                "MATH 3B\n" +
                "W\n" +
                "8:00 am - 8:50 am\n" +
                "HSSB 1211\n" +
                "0 / 25\n" +
                "MATH 3B\n" +
                "W\n" +
                "4:00pm - 4:50pm\n" +
                "HSSB 1214\n" +
                "6 / 25\n" +
                "MATH 3B\n" +
                "W\n" +
                "5:00pm - 5:50pm\n" +
                "HSSB 1223\n" +
                "0 / 25\n" +
                "MATH 3B\n" +
                "W\n" +
                "6:00pm - 6:50pm\n" +
                "NH 1109\n" +
                "0 / 27\n" +
                "MATH 3B\n" +
                "W\n" +
                "7:00pm - 7:50pm\n" +
                "HSSB 1227\n" +
                "0 / 25\n" +
                "MATH 3B\n" +
                "M\n" +
                "8:00 am - 8:50 am\n" +
                "HSSB 1210\n" +
                "0 / 25\n" +
                "MATH 3B\n" +
                "M\n" +
                "6:00pm - 6:50pm\n" +
                "HSSB 1224\n" +
                "0 / 25\n" +
                "MATH 3B\n" +
                "M\n" +
                "5:00pm - 5:50pm\n" +
                "NH 1109\n" +
                "0 / 27\n" +
                "MATH 3B\n" +
                "M\n" +
                "7:00pm - 7:50pm\n" +
                "GIRV 2129\n" +
                "0 / 27\n" +
                "MATH 3B\n" +
                "M W F\n" +
                "8:00 am - 8:50 am\n" +
                "BUCHN1920\n" +
                "0 / 35\n" +
                "MATH 3B\n" +
                "M\n" +
                "4:00pm - 4:50pm\n" +
                "GIRV 2123\n" +
                "0 / 22\n" +
                "MATH 3B\n" +
                "T\n" +
                "4:00pm - 4:50pm\n" +
                "HSSB 1210\n" +
                "0 / 25\n" +
                "MATH 3B\n" +
                "T\n" +
                "5:00pm - 5:50pm\n" +
                "HSSB 1207\n" +
                "0 / 25\n" +
                "MATH 3B\n" +
                "T\n" +
                "6:00pm - 6:50pm\n" +
                "HSSB 1223\n" +
                "0 / 25\n" +
                "MATH 3B\n" +
                "T\n" +
                "7:00pm - 7:50pm\n" +
                "NH 1109\n" +
                "0 / 25\n" +
                "MATH 3B\n" +
                "T\n" +
                "8:00 am - 8:50 am\n" +
                "NH 1109\n" +
                "0 / 25\n" +
                "MATH 4A\n" +
                "M W F\n" +
                "1:00pm - 1:50pm\n" +
                "CAMPBHALL\n" +
                "18 / 300\n" +
                "MATH 4A\n" +
                "M\n" +
                "8:00 am - 8:50 am\n" +
                "HSSB 1207\n" +
                "0 / 25\n" +
                "MATH 4A\n" +
                "M\n" +
                "5:00pm - 5:50pm\n" +
                "HSSB 1211\n" +
                "5 / 25\n" +
                "MATH 4A\n" +
                "M\n" +
                "6:00pm - 6:50pm\n" +
                "HSSB 1223\n" +
                "0 / 25\n" +
                "MATH 4A\n" +
                "M\n" +
                "7:00pm - 7:50pm\n" +
                "HSSB 1223\n" +
                "1 / 25\n" +
                "MATH 4A\n" +
                "W\n" +
                "8:00 am - 8:50 am\n" +
                "HSSB 1210\n" +
                "0 / 25\n" +
                "MATH 4A\n" +
                "W\n" +
                "4:00pm - 4:50pm\n" +
                "HSSB 1211\n" +
                "0 / 25\n" +
                "MATH 4A\n" +
                "W\n" +
                "5:00pm - 5:50pm\n" +
                "HSSB 1214\n" +
                "3 / 25\n" +
                "MATH 4A\n" +
                "W\n" +
                "6:00pm - 6:50pm\n" +
                "HSSB 1224\n" +
                "0 / 25\n" +
                "MATH 4A\n" +
                "W\n" +
                "7:00pm - 7:50pm\n" +
                "HSSB 1223\n" +
                "0 / 25\n" +
                "MATH 4A\n" +
                "W\n" +
                "8:00 am - 8:50 am\n" +
                "HSSB 1207\n" +
                "2 / 25\n" +
                "MATH 4A\n" +
                "W\n" +
                "5:00pm - 5:50pm\n" +
                "HSSB 1210\n" +
                "3 / 25\n" +
                "MATH 4A\n" +
                "W\n" +
                "6:00pm - 6:50pm\n" +
                "HSSB 1223\n" +
                "0 / 25\n" +
                "MATH 4A\n" +
                "W\n" +
                "7:00pm - 7:50pm\n" +
                "NH 1111\n" +
                "0 / 25\n" +
                "MATH 4A\n" +
                "W\n" +
                "4:00pm - 4:50pm\n" +
                "HSSB 1207\n" +
                "0 / 25\n" +
                "MATH 4A\n" +
                "M\n" +
                "8:00 am - 8:50 am\n" +
                "HSSB 1206\n" +
                "0 / 25\n" +
                "MATH 4A\n" +
                "M\n" +
                "5:00pm - 5:50pm\n" +
                "HSSB 1210\n" +
                "0 / 25\n" +
                "MATH 4A\n" +
                "M\n" +
                "6:00pm - 6:50pm\n" +
                "HSSB 1211\n" +
                "0 / 25\n" +
                "MATH 4A\n" +
                "M\n" +
                "8:00 am - 8:50 am\n" +
                "NH 1109\n" +
                "0 / 25\n" +
                "MATH 4A\n" +
                "M\n" +
                "5:00pm - 5:50pm\n" +
                "HSSB 1207\n" +
                "1 / 25\n" +
                "MATH 4A\n" +
                "M\n" +
                "4:00pm - 4:50pm\n" +
                "NH 1109\n" +
                "0 / 25\n" +
                "MATH 4A\n" +
                "M\n" +
                "7:00pm - 7:50pm\n" +
                "NH 1111\n" +
                "0 / 25\n" +
                "MATH 4A\n" +
                "M\n" +
                "6:00pm - 6:50pm\n" +
                "NH 1109\n" +
                "0 / 25\n" +
                "MATH 4A\n" +
                "W\n" +
                "8:00 am - 8:50 am\n" +
                "NH 1111\n" +
                "0 / 25\n" +
                "MATH 4A\n" +
                "W\n" +
                "5:00pm - 5:50pm\n" +
                "HSSB 1206\n" +
                "2 / 25\n" +
                "MATH 4A\n" +
                "W\n" +
                "6:00pm - 6:50pm\n" +
                "HSSB 1210\n" +
                "0 / 25\n" +
                "MATH 4A\n" +
                "W\n" +
                "7:00pm - 7:50pm\n" +
                "GIRV 2129\n" +
                "1 / 25\n" +
                "MATH 4A\n" +
                "M W F\n" +
                "9:00 am - 9:50 am\n" +
                "IV THEA2\n" +
                "11 / 45\n" +
                "MATH 4A\n" +
                "T\n" +
                "8:00 am - 8:50 am\n" +
                "NH 1111\n" +
                "4 / 25\n" +
                "MATH 4A\n" +
                "T\n" +
                "4:00pm - 4:50pm\n" +
                "HSSB 1207\n" +
                "6 / 25\n" +
                "MATH 4A\n" +
                "T\n" +
                "5:00pm - 5:50pm\n" +
                "NH 1109\n" +
                "1 / 25\n" +
                "MATH 4A\n" +
                "T\n" +
                "6:00pm - 6:50pm\n" +
                "NH 1109\n" +
                "0 / 25\n" +
                "MATH 4A\n" +
                "T\n" +
                "7:00pm - 7:50pm\n" +
                "GIRV 2129\n" +
                "0 / 25\n" +
                "MATH 4A\n" +
                "M\n" +
                "4:00pm - 4:50pm\n" +
                "PHELP2532\n" +
                "0 / 25\n" +
                "MATH 4AI\n" +
                "M W F\n" +
                "10:00am - 10:50am\n" +
                "SH 1607\n" +
                "0 / 35\n" +
                "MATH 4AI\n" +
                "F\n" +
                "9:00 am - 10:00am\n" +
                "SH 1607\n" +
                "0 / 35\n" +
                "MATH 4B\n" +
                "M W F\n" +
                "1:00pm - 1:50pm\n" +
                "TD-W 1701\n" +
                "97 / 125\n" +
                "MATH 4B\n" +
                "M\n" +
                "8:00 am - 8:50 am\n" +
                "NH 1111\n" +
                "8 / 25\n" +
                "MATH 4B\n" +
                "M\n" +
                "12:00pm - 12:50pm\n" +
                "NH 1109\n" +
                "0 / 25\n" +
                "MATH 4B\n" +
                "M\n" +
                "5:00pm - 5:50pm\n" +
                "HSSB 1206\n" +
                "25 / 25\n" +
                "MATH 4B\n" +
                "M\n" +
                "6:00pm - 6:50pm\n" +
                "HSSB 1210\n" +
                "25 / 25\n" +
                "MATH 4B\n" +
                "M\n" +
                "7:00pm - 7:50pm\n" +
                "NH 1109\n" +
                "14 / 25\n" +
                "MATH 4B\n" +
                "M\n" +
                "4:00pm - 4:50pm\n" +
                "HSSB 1207\n" +
                "25 / 25\n" +
                "MATH 4B\n" +
                "M W F\n" +
                "11:00am - 11:50am\n" +
                "TD-W 1701\n" +
                "119 / 125\n" +
                "MATH 4B\n" +
                "W\n" +
                "8:00 am - 8:50 am\n" +
                "NH 1109\n" +
                "19 / 25\n" +
                "MATH 4B\n" +
                "W\n" +
                "4:00pm - 4:50pm\n" +
                "NH 1109\n" +
                "25 / 25\n" +
                "MATH 4B\n" +
                "W\n" +
                "5:00pm - 5:50pm\n" +
                "HSSB 1207\n" +
                "25 / 25\n" +
                "MATH 4B\n" +
                "W\n" +
                "6:00pm - 6:50pm\n" +
                "HSSB 1211\n" +
                "25 / 25\n" +
                "MATH 4B\n" +
                "W\n" +
                "7:00pm - 7:50pm\n" +
                "NH 1109\n" +
                "25 / 25\n" +
                "MATH 4B\n" +
                "W\n" +
                "12:00pm - 12:50pm\n" +
                "NH 1109\n" +
                "0 / 25\n" +
                "MATH 4B\n" +
                "T R\n" +
                "8:00 am - 9:15 am\n" +
                "TD-W 1701\n" +
                "13 / 125\n" +
                "MATH 4B\n" +
                "W\n" +
                "8:00 am - 8:50 am\n" +
                "HSSB 1206\n" +
                "2 / 25\n" +
                "MATH 4B\n" +
                "W\n" +
                "4:00pm - 4:50pm\n" +
                "HSSB 1210\n" +
                "8 / 25\n" +
                "MATH 4B\n" +
                "W\n" +
                "5:00pm - 5:50pm\n" +
                "HSSB 1211\n" +
                "1 / 25\n" +
                "MATH 4B\n" +
                "W\n" +
                "6:00pm - 6:50pm\n" +
                "HSSB 1228\n" +
                "0 / 25\n" +
                "MATH 4B\n" +
                "W\n" +
                "7:00pm - 7:50pm\n" +
                "HSSB 1224\n" +
                "2 / 25\n" +
                "MATH 4B\n" +
                "W\n" +
                "12:00pm - 12:50pm\n" +
                "HSSB 1210\n" +
                "0 / 25\n" +
                "MATH 6A\n" +
                "T R\n" +
                "8:00 am - 9:15 am\n" +
                "LSB 1001\n" +
                "62 / 100\n" +
                "MATH 6A\n" +
                "T\n" +
                "7:00pm - 7:50pm\n" +
                "NH 1111\n" +
                "0 / 25\n" +
                "MATH 6A\n" +
                "T\n" +
                "5:00pm - 5:50pm\n" +
                "HSSB 1206\n" +
                "25 / 25\n" +
                "MATH 6A\n" +
                "T\n" +
                "6:00pm - 6:50pm\n" +
                "HSSB 1211\n" +
                "17 / 25\n" +
                "MATH 6A\n" +
                "R\n" +
                "6:00pm - 6:50pm\n" +
                "NH 1109\n" +
                "4 / 25\n" +
                "MATH 6A\n" +
                "R\n" +
                "7:00pm - 7:50pm\n" +
                "GIRV 2129\n" +
                "0 / 25\n" +
                "MATH 6A\n" +
                "R\n" +
                "5:00pm - 5:50pm\n" +
                "NH 1109\n" +
                "16 / 25\n" +
                "MATH 6A\n" +
                "M W F\n" +
                "2:00pm - 2:50pm\n" +
                "IV THEA2\n" +
                "100 / 100\n" +
                "MATH 6A\n" +
                "R\n" +
                "5:00pm - 5:50pm\n" +
                "HSSB 1214\n" +
                "25 / 25\n" +
                "MATH 6A\n" +
                "R\n" +
                "6:00pm - 6:50pm\n" +
                "HSSB 1211\n" +
                "25 / 25\n" +
                "MATH 6A\n" +
                "R\n" +
                "7:00pm - 7:50pm\n" +
                "NH 1111\n" +
                "25 / 25\n" +
                "MATH 6A\n" +
                "R\n" +
                "8:00 am - 8:50 am\n" +
                "NH 1111\n" +
                "25 / 25\n" +
                "MATH 6A\n" +
                "R\n" +
                "12:00pm - 12:50pm\n" +
                "PHELP2532\n" +
                "0 / 24\n" +
                "MATH 6A\n" +
                "R\n" +
                "4:00pm - 4:50pm\n" +
                "NH 1109\n" +
                "0 / 25\n" +
                "MATH 6A\n" +
                "M W F\n" +
                "8:00 am - 8:50 am\n" +
                "BUCHN1940\n" +
                "47 / 100\n" +
                "MATH 6A\n" +
                "R\n" +
                "6:00pm - 6:50pm\n" +
                "HSSB 1223\n" +
                "14 / 25\n" +
                "MATH 6A\n" +
                "R\n" +
                "5:00pm - 5:50pm\n" +
                "HSSB 1215\n" +
                "20 / 25\n" +
                "MATH 6A\n" +
                "R\n" +
                "12:00pm - 12:50pm\n" +
                "HSSB 1223\n" +
                "0 / 25\n" +
                "MATH 6A\n" +
                "R\n" +
                "8:00 am - 8:50 am\n" +
                "NH 1109\n" +
                "7 / 25\n" +
                "MATH 6A\n" +
                "R\n" +
                "4:00pm - 4:50pm\n" +
                "HSSB 1207\n" +
                "0 / 25\n" +
                "MATH 6A\n" +
                "R\n" +
                "7:00pm - 7:50pm\n" +
                "HSSB 1210\n" +
                "6 / 25\n" +
                "MATH 6B\n" +
                "M W F\n" +
                "1:00pm - 1:50pm\n" +
                "BRDA 1610\n" +
                "194 / 200\n" +
                "MATH 6B\n" +
                "M\n" +
                "4:00pm - 4:50pm\n" +
                "HSSB 1210\n" +
                "25 / 25\n" +
                "MATH 6B\n" +
                "M\n" +
                "5:00pm - 5:50pm\n" +
                "HSSB 1214\n" +
                "25 / 25\n" +
                "MATH 6B\n" +
                "M\n" +
                "6:00pm - 6:50pm\n" +
                "HSSB 1228\n" +
                "25 / 25\n" +
                "MATH 6B\n" +
                "M\n" +
                "7:00pm - 7:50pm\n" +
                "HSSB 1227\n" +
                "25 / 25\n" +
                "MATH 6B\n" +
                "W\n" +
                "4:00pm - 4:50pm\n" +
                "HSSB 1223\n" +
                "25 / 25\n" +
                "MATH 6B\n" +
                "W\n" +
                "5:00pm - 5:50pm\n" +
                "HSSB 1224\n" +
                "25 / 25\n" +
                "MATH 6B\n" +
                "W\n" +
                "6:00pm - 6:50pm\n" +
                "HSSB 1236\n" +
                "25 / 25\n" +
                "MATH 6B\n" +
                "W\n" +
                "7:00pm - 7:50pm\n" +
                "HSSB 1228\n" +
                "19 / 25\n" +
                "MATH 6B\n" +
                "M\n" +
                "5:00pm - 5:50pm\n" +
                "HSSB 1228\n" +
                "0 / 25\n" +
                "MATH 6B\n" +
                "M\n" +
                "6:00pm - 6:50pm\n" +
                "LSB 1101\n" +
                "0 / 25\n" +
                "MATH 7H\n" +
                "T\n" +
                "3:30pm - 4:45pm\n" +
                "GIRV 1119\n" +
                "0 / 25\n" +
                "MATH 8\n" +
                "M W F\n" +
                "9:00 am - 9:50 am\n" +
                "GIRV 2120\n" +
                "12 / 20\n" +
                "MATH 8\n" +
                "T R\n" +
                "6:00pm - 6:50pm\n" +
                "GIRV 2129\n" +
                "3 / 18\n" +
                "MATH 8\n" +
                "T R\n" +
                "5:00pm - 5:50pm\n" +
                "HSSB 1211\n" +
                "9 / 17\n" +
                "MATH 8\n" +
                "T R\n" +
                "12:30pm - 1:45pm\n" +
                "GIRV 1115\n" +
                "20 / 20\n" +
                "MATH 8\n" +
                "M W\n" +
                "6:00pm - 6:50pm\n" +
                "HSSB 2202\n" +
                "14 / 18\n" +
                "MATH 8\n" +
                "M W\n" +
                "7:00pm - 7:50pm\n" +
                "HSSB 1210\n" +
                "6 / 17\n" +
                "MATH 8\n" +
                "M W F\n" +
                "11:00am - 11:50am\n" +
                "GIRV 2119\n" +
                "25 / 25\n" +
                "MATH 8\n" +
                "M W\n" +
                "5:00pm - 5:50pm\n" +
                "HSSB 1231\n" +
                "18 / 18\n" +
                "MATH 8\n" +
                "M W\n" +
                "6:00pm - 6:50pm\n" +
                "GIRV 2135\n" +
                "7 / 17\n" +
                "MATH 8\n" +
                "T R\n" +
                "2:00pm - 3:15pm\n" +
                "PHELP3519\n" +
                "20 / 20\n" +
                "MATH 8\n" +
                "M W\n" +
                "6:00pm - 6:50pm\n" +
                "PHELP2536\n" +
                "17 / 18\n" +
                "MATH 8\n" +
                "M W\n" +
                "7:00pm - 7:50pm\n" +
                "HSSB 1211\n" +
                "3 / 17\n" +
                "MATH 8\n" +
                "M W F\n" +
                "1:00pm - 1:50pm\n" +
                "NH 1111\n" +
                "17 / 20\n" +
                "MATH 8\n" +
                "T R\n" +
                "5:00pm - 5:50pm\n" +
                "HSSB 1210\n" +
                "16 / 18\n" +
                "MATH 8\n" +
                "T R\n" +
                "6:00pm - 6:50pm\n" +
                "HSSB 1210\n" +
                "1 / 17\n" +
                "MATH 34A\n" +
                "M W F\n" +
                "9:00 am - 9:50 am\n" +
                "CAMPBHALL\n" +
                "150 / 150\n" +
                "MATH 34A\n" +
                "R\n" +
                "8:00 am - 8:50 am\n" +
                "HSSB 1210\n" +
                "21 / 25\n" +
                "MATH 34A\n" +
                "R\n" +
                "5:00pm - 5:50pm\n" +
                "HSSB 1227\n" +
                "25 / 25\n" +
                "MATH 34A\n" +
                "R\n" +
                "6:00pm - 6:50pm\n" +
                "HSSB 1228\n" +
                "3 / 25\n" +
                "MATH 34A\n" +
                "R\n" +
                "7:00pm - 7:50pm\n" +
                "HSSB 1224\n" +
                "3 / 25\n" +
                "MATH 34A\n" +
                "R\n" +
                "8:00 am - 8:50 am\n" +
                "HSSB 1211\n" +
                "1 / 25\n" +
                "MATH 34A\n" +
                "R\n" +
                "5:00pm - 5:50pm\n" +
                "HSSB 1228\n" +
                "8 / 25\n" +
                "MATH 34A\n" +
                "R\n" +
                "6:00pm - 6:50pm\n" +
                "HSSB 1236\n" +
                "0 / 25\n" +
                "MATH 34A\n" +
                "R\n" +
                "7:00pm - 7:50pm\n" +
                "HSSB 1228\n" +
                "2 / 25\n" +
                "MATH 34A\n" +
                "T\n" +
                "8:00 am - 8:50 am\n" +
                "HSSB 1207\n" +
                "12 / 25\n" +
                "MATH 34A\n" +
                "T\n" +
                "4:00pm - 4:50pm\n" +
                "HSSB 1211\n" +
                "0 / 25\n" +
                "MATH 34A\n" +
                "T\n" +
                "5:00pm - 5:50pm\n" +
                "HSSB 1215\n" +
                "25 / 25\n" +
                "MATH 34A\n" +
                "T\n" +
                "6:00pm - 6:50pm\n" +
                "HSSB 1227\n" +
                "10 / 25\n" +
                "MATH 34A\n" +
                "T\n" +
                "7:00pm - 7:50pm\n" +
                "HSSB 1211\n" +
                "3 / 25\n" +
                "MATH 34A\n" +
                "T\n" +
                "8:00 am - 8:50 am\n" +
                "HSSB 1210\n" +
                "2 / 25\n" +
                "MATH 34A\n" +
                "R\n" +
                "4:00pm - 4:50pm\n" +
                "PHELP2532\n" +
                "0 / 25\n" +
                "MATH 34A\n" +
                "T\n" +
                "4:00pm - 4:50pm\n" +
                "HSSB 1214\n" +
                "0 / 25\n" +
                "MATH 34A\n" +
                "T\n" +
                "5:00pm - 5:50pm\n" +
                "HSSB 1224\n" +
                "7 / 25\n" +
                "MATH 34A\n" +
                "T\n" +
                "6:00pm - 6:50pm\n" +
                "HSSB 1233\n" +
                "1 / 25\n" +
                "MATH 34A\n" +
                "T\n" +
                "7:00pm - 7:50pm\n" +
                "HSSB 1224\n" +
                "2 / 25\n" +
                "MATH 34A\n" +
                "R\n" +
                "8:00 am - 8:50 am\n" +
                "HSSB 1223\n" +
                "0 / 25\n" +
                "MATH 34A\n" +
                "R\n" +
                "5:00pm - 5:50pm\n" +
                "HSSB 1236\n" +
                "8 / 25\n" +
                "MATH 34A\n" +
                "R\n" +
                "6:00pm - 6:50pm\n" +
                "ARTS 1349\n" +
                "0 / 25\n" +
                "MATH 34A\n" +
                "R\n" +
                "7:00pm - 7:50pm\n" +
                "HSSB 2251\n" +
                "2 / 25\n" +
                "MATH 34A\n" +
                "R\n" +
                "8:00 am - 8:50 am\n" +
                "HSSB 1227\n" +
                "0 / 25\n" +
                "MATH 34A\n" +
                "R\n" +
                "5:00pm - 5:50pm\n" +
                "HSSB 2251\n" +
                "0 / 25\n" +
                "MATH 34A\n" +
                "R\n" +
                "6:00pm - 6:50pm\n" +
                "SH 1430\n" +
                "1 / 25\n" +
                "MATH 34A\n" +
                "T\n" +
                "8:00 am - 8:50 am\n" +
                "HSSB 1215\n" +
                "1 / 25\n" +
                "MATH 34A\n" +
                "T\n" +
                "5:00pm - 5:50pm\n" +
                "HSSB 1228\n" +
                "10 / 25\n" +
                "MATH 34A\n" +
                "T\n" +
                "6:00pm - 6:50pm\n" +
                "HSSB 2251\n" +
                "2 / 25\n" +
                "MATH 34A\n" +
                "T\n" +
                "7:00pm - 7:50pm\n" +
                "HSSB 1228\n" +
                "1 / 25\n" +
                "MATH 34B\n" +
                "T R\n" +
                "12:30pm - 1:45pm\n" +
                "HFH 1104\n" +
                "65 / 65\n" +
                "MATH 34B\n" +
                "W\n" +
                "12:00pm - 12:50pm\n" +
                "HSSB 1223\n" +
                "0 / 25\n" +
                "MATH 34B\n" +
                "W\n" +
                "6:00pm - 6:50pm\n" +
                "HSSB 2251\n" +
                "9 / 25\n" +
                "MATH 34B\n" +
                "W\n" +
                "5:00pm - 5:50pm\n" +
                "HSSB 1228\n" +
                "22 / 25\n" +
                "MATH 34B\n" +
                "W\n" +
                "7:00pm - 7:50pm\n" +
                "HSSB 1233\n" +
                "4 / 25\n" +
                "MATH 34B\n" +
                "W\n" +
                "4:00pm - 4:50pm\n" +
                "HSSB 1228\n" +
                "25 / 25\n" +
                "MATH 34B\n" +
                "W\n" +
                "8:00 am - 8:50 am\n" +
                "HSSB 1214\n" +
                "5 / 25\n" +
                "MATH 94\n" +
                "W\n" +
                "10:00am - 10:50am\n" +
                "SH 6617\n" +
                "0 / 20\n" +
                "MATH 101A\n" +
                "M W F\n" +
                "12:00pm - 12:50pm\n" +
                "SH 1430\n" +
                "22 / 40\n" +
                "MATH CS 101A\n" +
                "T R\n" +
                "3:00pm - 4:20pm\n" +
                "494 164B\n" +
                "1 / 25\n" +
                "MATH 103\n" +
                "M W F\n" +
                "10:00am - 10:50am\n" +
                "SH 1430\n" +
                "14 / 40\n" +
                "MATH 104A\n" +
                "T R\n" +
                "11:00am - 12:15pm\n" +
                "PSYCH1902\n" +
                "45 / 45\n" +
                "MATH 104A\n" +
                "T R\n" +
                "8:00 am - 9:15 am\n" +
                "NH 1105\n" +
                "46 / 45\n" +
                "MATH 108A\n" +
                "M W F\n" +
                "9:00 am - 9:50 am\n" +
                "NH 1105\n" +
                "45 / 45\n" +
                "MATH 108A\n" +
                "T R\n" +
                "2:00pm - 3:15pm\n" +
                "NH 1105\n" +
                "47 / 45\n" +
                "MATH 108B\n" +
                "M W F\n" +
                "2:00pm - 2:50pm\n" +
                "NH 1105\n" +
                "50 / 50\n" +
                "MATH 111A\n" +
                "M W F\n" +
                "9:00 am - 9:50 am\n" +
                "SH 1430\n" +
                "36 / 35\n" +
                "MATH 113\n" +
                "T R\n" +
                "12:30pm - 1:45pm\n" +
                "ARTS 1349\n" +
                "44 / 45\n" +
                "MATH 114\n" +
                "T R\n" +
                "3:30pm - 4:45pm\n" +
                "NH 1105\n" +
                "3 / 40\n" +
                "MATH 117\n" +
                "T R\n" +
                "9:30 am - 10:45am\n" +
                "NH 1105\n" +
                "50 / 50\n" +
                "MATH 117\n" +
                "M W F\n" +
                "1:00pm - 1:50pm\n" +
                "PHELP2524\n" +
                "50 / 50\n" +
                "MATH 118A\n" +
                "T R\n" +
                "9:30 am - 10:45am\n" +
                "ARTS 1349\n" +
                "34 / 35\n" +
                "MATH 118A\n" +
                "M W F\n" +
                "2:00pm - 2:50pm\n" +
                "GIRV 1119\n" +
                "35 / 35\n" +
                "MATH 119A\n" +
                "T R\n" +
                "3:30pm - 4:45pm\n" +
                "GIRV 2128\n" +
                "45 / 45\n" +
                "MATH CS 120\n" +
                "M W F\n" +
                "3:00pm - 4:20pm\n" +
                "494 164B\n" +
                "1 / 25\n" +
                "MATH CS 128\n" +
                "M WRF\n" +
                "11:00am - 12:50pm\n" +
                "494 164B\n" +
                "1 / 25\n" +
                "MATH CS 130A\n" +
                "T R\n" +
                "1:00pm - 2:20pm\n" +
                "494 164B\n" +
                "15 / 25\n" +
                "MATH 197A\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "0 / 0\n" +
                "MATH 199\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "0 / 0\n" +
                "MATH CS 101A\n" +
                "T R\n" +
                "3:00pm - 4:20pm\n" +
                "494 164B\n" +
                "1 / 25\n" +
                "MATH CS 120\n" +
                "M W F\n" +
                "3:00pm - 4:20pm\n" +
                "494 164B\n" +
                "1 / 25\n" +
                "MATH CS 128\n" +
                "M WRF\n" +
                "11:00am - 12:50pm\n" +
                "494 164B\n" +
                "1 / 25\n" +
                "MATH CS 130A\n" +
                "T R\n" +
                "1:00pm - 2:20pm\n" +
                "494 164B\n" +
                "15 / 25\n" +
                "ME 11\n" +
                "T\n" +
                "3:30pm - 4:45pm\n" +
                "GIRV 1004\n" +
                "4 / 120\n" +
                "ME 12S\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "7 / 43\n" +
                "ME 14\n" +
                "M W F\n" +
                "2:00pm - 2:50pm\n" +
                "BUCHN1920\n" +
                "26 / 113\n" +
                "ME 14\n" +
                "T\n" +
                "5:00pm - 5:50pm\n" +
                "PHELP1444\n" +
                "1 / 30\n" +
                "ME 14\n" +
                "R\n" +
                "5:00pm - 5:50pm\n" +
                "PHELP1440\n" +
                "5 / 30\n" +
                "ME 14\n" +
                "W\n" +
                "3:00pm - 3:50pm\n" +
                "LSB 1101\n" +
                "16 / 25\n" +
                "ME 14\n" +
                "W\n" +
                "5:00pm - 5:50pm\n" +
                "PHELP1445\n" +
                "4 / 30\n" +
                "ME 95\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "0 / 25\n" +
                "ME 97\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "0 / 30\n" +
                "ME 99\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "0 / 30\n" +
                "ME 100\n" +
                "M\n" +
                "3:30pm - 4:30pm\n" +
                "ESB 1001\n" +
                "8 / 50\n" +
                "ME 104\n" +
                "T R\n" +
                "12:30pm - 1:45pm\n" +
                "CHEM 1171\n" +
                "62 / 110\n" +
                "ME 104\n" +
                "R\n" +
                "6:00pm - 8:50pm\n" +
                "ENGR22218\n" +
                "1 / 22\n" +
                "ME 104\n" +
                "T\n" +
                "2:00pm - 4:50pm\n" +
                "ENGR22218\n" +
                "23 / 22\n" +
                "ME 104\n" +
                "T\n" +
                "6:00pm - 8:50pm\n" +
                "ENGR22218\n" +
                "8 / 22\n" +
                "ME 104\n" +
                "R\n" +
                "2:00pm - 4:50pm\n" +
                "ENGR22218\n" +
                "22 / 22\n" +
                "ME 104\n" +
                "W\n" +
                "5:00pm - 7:50pm\n" +
                "ENGR22218\n" +
                "8 / 22\n" +
                "ME 112\n" +
                "T R\n" +
                "12:30pm - 1:45pm\n" +
                "PHELP2514\n" +
                "5 / 20\n" +
                "ME 125KT\n" +
                "T R\n" +
                "9:30 am - 10:45am\n" +
                "ENGR22243\n" +
                "3 / 48\n" +
                "ME 125TM\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "0 / 20\n" +
                "ME 128\n" +
                "M W F\n" +
                "12:00pm - 12:50pm\n" +
                "ENGR22243\n" +
                "24 / 40\n" +
                "ME 140A\n" +
                "T R\n" +
                "8:00 am - 9:15 am\n" +
                "CHEM 1171\n" +
                "53 / 95\n" +
                "ME 151A\n" +
                "M W F\n" +
                "1:00pm - 1:50pm\n" +
                "CHEM 1171\n" +
                "26 / 113\n" +
                "ME 152A\n" +
                "M W F\n" +
                "11:00am - 11:50am\n" +
                "CHEM 1171\n" +
                "22 / 90\n" +
                "ME 154\n" +
                "T R\n" +
                "3:30pm - 4:45pm\n" +
                "PHELP1160\n" +
                "21 / 70\n" +
                "ME 154\n" +
                "T\n" +
                "5:00pm - 5:50pm\n" +
                "PHELP1525\n" +
                "14 / 25\n" +
                "ME 154\n" +
                "R\n" +
                "5:00pm - 5:50pm\n" +
                "PHELP1525\n" +
                "6 / 25\n" +
                "ME 154\n" +
                "F\n" +
                "9:00 am - 9:50 am\n" +
                "PHELP1525\n" +
                "1 / 25\n" +
                "ME 155B\n" +
                "M W F\n" +
                "11:00am - 11:50am\n" +
                "PHELP3523\n" +
                "16 / 30\n" +
                "ME 156A\n" +
                "M W F\n" +
                "10:00am - 10:50am\n" +
                "BUCHN1940\n" +
                "38 / 100\n" +
                "ME 157\n" +
                "T R\n" +
                "2:00pm - 3:15pm\n" +
                "ESB 1003\n" +
                "9 / 25\n" +
                "ME 158\n" +
                "T R\n" +
                "11:00am - 12:15pm\n" +
                "ESB 1003\n" +
                "24 / 24\n" +
                "ME 167\n" +
                "M W F\n" +
                "4:00pm - 5:50pm\n" +
                "PHELP1160\n" +
                "52 / 60\n" +
                "ME 189A\n" +
                "M\n" +
                "2:00pm - 3:15pm\n" +
                "WEBB 1100\n" +
                "37 / 92\n" +
                "ME 189A\n" +
                "W F\n" +
                "1:00pm - 1:50pm\n" +
                "ENGR22243\n" +
                "37 / 92\n" +
                "ME 193\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "0 / 10\n" +
                "ME 197\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "0 / 30\n" +
                "ME 199\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "0 / 30\n" +
                "MES 45\n" +
                "T R\n" +
                "5:00pm - 6:15pm\n" +
                "TD-W 1701\n" +
                "70 / 70\n" +
                "MES 45\n" +
                "W\n" +
                "3:00pm - 3:50pm\n" +
                "HSSB 4202\n" +
                "15 / 15\n" +
                "MES 45\n" +
                "W\n" +
                "4:00pm - 4:50pm\n" +
                "PHELP1445\n" +
                "14 / 15\n" +
                "MES 45\n" +
                "W\n" +
                "5:00pm - 5:50pm\n" +
                "PHELP1425\n" +
                "4 / 15\n" +
                "MES 45\n" +
                "R\n" +
                "2:00pm - 2:50pm\n" +
                "GIRV 2127\n" +
                "14 / 15\n" +
                "MES 45\n" +
                "R\n" +
                "3:00pm - 3:50pm\n" +
                "HSSB 1214\n" +
                "7 / 15\n" +
                "MES 45\n" +
                "R\n" +
                "4:00pm - 4:50pm\n" +
                "LSB 1101\n" +
                "13 / 15\n" +
                "MES 45\n" +
                "F\n" +
                "2:00pm - 2:50pm\n" +
                "ELLSN2816\n" +
                "3 / 15\n" +
                "MES 45\n" +
                "F\n" +
                "3:00pm - 3:50pm\n" +
                "ELLSN2816\n" +
                "0 / 15\n" +
                "MS 1A\n" +
                "M\n" +
                "1:00pm - 1:50pm\n" +
                "PHELP1309\n" +
                "0 / 20\n" +
                "MS 1A\n" +
                "R\n" +
                "9:00 am - 9:50 am\n" +
                "PHELP1309\n" +
                "0 / 20\n" +
                "MS 2AA\n" +
                "T\n" +
                "11:00am - 11:50am\n" +
                "PHELP1309\n" +
                "0 / 20\n" +
                "MS 2AA\n" +
                "W\n" +
                "11:00am - 11:50am\n" +
                "PHELP1309\n" +
                "0 / 20\n" +
                "MS 6\n" +
                "M\n" +
                "8:00 am - 8:50 am\n" +
                "451 123\n" +
                "0 / 10\n" +
                "MS 22\n" +
                "M W F\n" +
                "6:30 am - 7:30 am\n" +
                "SCRIMFIELD\n" +
                "7 / 50\n" +
                "MS 99\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "0 / 25\n" +
                "MS 131\n" +
                "T R\n" +
                "1:00pm - 1:50pm\n" +
                "451 123\n" +
                "0 / 25\n" +
                "MS 131\n" +
                "R\n" +
                "2:00pm - 4:50pm\n" +
                "PHELP1309\n" +
                "0 / 25\n" +
                "MS 131\n" +
                "T R\n" +
                "12:00pm - 12:50pm\n" +
                "PHELP1309\n" +
                "0 / 25\n" +
                "MS 141\n" +
                "M\n" +
                "2:30pm - 4:20pm\n" +
                "451 123\n" +
                "0 / 25\n" +
                "MS 141\n" +
                "R\n" +
                "2:00pm - 4:50pm\n" +
                "PHELP1309\n" +
                "0 / 25\n" +
                "MS 141\n" +
                "T\n" +
                "2:30pm - 4:20pm\n" +
                "PHELP1309\n" +
                "0 / 25\n" +
                "MS 190\n" +
                "T R\n" +
                "8:00 am - 8:50 am\n" +
                "451 123\n" +
                "0 / 8\n" +
                "MS 199\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "0 / 25\n" +
                "MCDB 1A\n" +
                "MTW F\n" +
                "8:00 am - 8:50 am\n" +
                "CAMPBHALL\n" +
                "551 / 860\n" +
                "MCDB 1A\n" +
                "MTW F\n" +
                "8:00 am - 8:50 am\n" +
                "BUCHN1910\n" +
                "551 / 860\n" +
                "MCDB 1A\n" +
                "M W F\n" +
                "8:00 am - 8:50 am\n" +
                "BRDA 1610\n" +
                "280 / 280\n" +
                "MCDB 1A\n" +
                "T\n" +
                "8:00 am - 8:50 am\n" +
                "BSIF 1217\n" +
                "35 / 35\n" +
                "MCDB 1A\n" +
                "T\n" +
                "9:00 am - 9:50 am\n" +
                "BSIF 1217\n" +
                "35 / 35\n" +
                "MCDB 1A\n" +
                "T\n" +
                "2:00pm - 2:50pm\n" +
                "BSIF 1217\n" +
                "35 / 35\n" +
                "MCDB 1A\n" +
                "T\n" +
                "3:00pm - 3:50pm\n" +
                "BSIF 1217\n" +
                "35 / 35\n" +
                "MCDB 1A\n" +
                "M\n" +
                "9:00 am - 9:50 am\n" +
                "BSIF 1217\n" +
                "35 / 35\n" +
                "MCDB 1A\n" +
                "M\n" +
                "10:00am - 10:50am\n" +
                "BSIF 1217\n" +
                "35 / 35\n" +
                "MCDB 1A\n" +
                "M\n" +
                "2:00pm - 2:50pm\n" +
                "BSIF 1217\n" +
                "35 / 35\n" +
                "MCDB 1A\n" +
                "M\n" +
                "3:00pm - 3:50pm\n" +
                "BSIF 1217\n" +
                "35 / 35\n" +
                "MCDB 1AL\n" +
                "R\n" +
                "9:00 am - 11:50am\n" +
                "BSIF 2112\n" +
                "22 / 22\n" +
                "MCDB 1AL\n" +
                "R\n" +
                "9:00 am - 11:50am\n" +
                "BSIF 2211\n" +
                "22 / 22\n" +
                "MCDB 1AL\n" +
                "T\n" +
                "9:00 am - 11:50am\n" +
                "BSIF 2112\n" +
                "22 / 22\n" +
                "MCDB 1AL\n" +
                "T\n" +
                "9:00 am - 11:50am\n" +
                "BSIF 2211\n" +
                "22 / 22\n" +
                "MCDB 1AL\n" +
                "R\n" +
                "6:00pm - 8:50pm\n" +
                "BSIF 2112\n" +
                "14 / 22\n" +
                "MCDB 1AL\n" +
                "T\n" +
                "12:00pm - 2:50pm\n" +
                "BSIF 2112\n" +
                "22 / 22\n" +
                "MCDB 1AL\n" +
                "T\n" +
                "3:00pm - 5:50pm\n" +
                "BSIF 2211\n" +
                "22 / 22\n" +
                "MCDB 1AL\n" +
                "T\n" +
                "3:00pm - 5:50pm\n" +
                "BSIF 2112\n" +
                "22 / 22\n" +
                "MCDB 1AL\n" +
                "W\n" +
                "9:00 am - 11:50am\n" +
                "BSIF 2112\n" +
                "22 / 22\n" +
                "MCDB 1AL\n" +
                "W\n" +
                "9:00 am - 11:50am\n" +
                "BSIF 2211\n" +
                "22 / 22\n" +
                "MCDB 1AL\n" +
                "W\n" +
                "12:00pm - 2:50pm\n" +
                "BSIF 2112\n" +
                "22 / 22\n" +
                "MCDB 1AL\n" +
                "W\n" +
                "12:00pm - 2:50pm\n" +
                "BSIF 2211\n" +
                "22 / 22\n" +
                "MCDB 1AL\n" +
                "W\n" +
                "6:00pm - 8:50pm\n" +
                "BSIF 2211\n" +
                "22 / 22\n" +
                "MCDB 1AL\n" +
                "T\n" +
                "6:00pm - 8:50pm\n" +
                "BSIF 2211\n" +
                "22 / 22\n" +
                "MCDB 1AL\n" +
                "W\n" +
                "6:00pm - 8:50pm\n" +
                "BSIF 2112\n" +
                "11 / 22\n" +
                "MCDB 1AL\n" +
                "R\n" +
                "6:00pm - 8:50pm\n" +
                "BSIF 2211\n" +
                "8 / 22\n" +
                "MCDB 1AL\n" +
                "F\n" +
                "3:00pm - 5:50pm\n" +
                "BSIF 2211\n" +
                "2 / 22\n" +
                "MCDB 1AL\n" +
                "F\n" +
                "3:00pm - 5:50pm\n" +
                "BSIF 2112\n" +
                "1 / 22\n" +
                "MCDB 1AL\n" +
                "R\n" +
                "12:00pm - 2:50pm\n" +
                "BSIF 2112\n" +
                "22 / 22\n" +
                "MCDB 1AL\n" +
                "R\n" +
                "12:00pm - 2:50pm\n" +
                "BSIF 2211\n" +
                "22 / 22\n" +
                "MCDB 1AL\n" +
                "T\n" +
                "12:00pm - 2:50pm\n" +
                "BSIF 2211\n" +
                "22 / 22\n" +
                "MCDB 1AL\n" +
                "T\n" +
                "6:00pm - 8:50pm\n" +
                "BSIF 2112\n" +
                "22 / 22\n" +
                "MCDB 1AL\n" +
                "R\n" +
                "3:00pm - 5:50pm\n" +
                "BSIF 2112\n" +
                "22 / 22\n" +
                "MCDB 1AL\n" +
                "R\n" +
                "3:00pm - 5:50pm\n" +
                "BSIF 2211\n" +
                "22 / 22\n" +
                "MCDB 1AL\n" +
                "W\n" +
                "3:00pm - 5:50pm\n" +
                "BSIF 2112\n" +
                "22 / 22\n" +
                "MCDB 1AL\n" +
                "W\n" +
                "3:00pm - 5:50pm\n" +
                "BSIF 2211\n" +
                "22 / 22\n" +
                "MCDB 1AL\n" +
                "F\n" +
                "6:00pm - 8:50pm\n" +
                "BSIF 2112\n" +
                "1 / 22\n" +
                "MCDB 1AL\n" +
                "F\n" +
                "9:00 am - 11:50am\n" +
                "BSIF 2211\n" +
                "5 / 22\n" +
                "MCDB 1AL\n" +
                "F\n" +
                "9:00 am - 11:50am\n" +
                "BSIF 2112\n" +
                "3 / 22\n" +
                "MCDB 1AL\n" +
                "F\n" +
                "12:00pm - 2:50pm\n" +
                "BSIF 2211\n" +
                "5 / 22\n" +
                "MCDB 1AL\n" +
                "F\n" +
                "12:00pm - 2:50pm\n" +
                "BSIF 2112\n" +
                "2 / 22\n" +
                "MCDB 1AL\n" +
                "T\n" +
                "9:00 am - 11:50am\n" +
                "LSB 1007\n" +
                "24 / 24\n" +
                "MCDB 1AL\n" +
                "T\n" +
                "12:00pm - 2:50pm\n" +
                "LSB 1007\n" +
                "24 / 24\n" +
                "MCDB 1AL\n" +
                "T\n" +
                "3:00pm - 5:50pm\n" +
                "LSB 1007\n" +
                "24 / 24\n" +
                "MCDB 1AL\n" +
                "T\n" +
                "6:00pm - 8:50pm\n" +
                "LSB 1007\n" +
                "24 / 24\n" +
                "MCDB 1AL\n" +
                "W\n" +
                "9:00 am - 11:50am\n" +
                "LSB 1007\n" +
                "24 / 24\n" +
                "MCDB 1AL\n" +
                "W\n" +
                "12:00pm - 2:50pm\n" +
                "LSB 1007\n" +
                "24 / 24\n" +
                "MCDB 1AL\n" +
                "W\n" +
                "3:00pm - 5:50pm\n" +
                "LSB 1007\n" +
                "24 / 24\n" +
                "MCDB 1AL\n" +
                "W\n" +
                "6:00pm - 8:50pm\n" +
                "LSB 1007\n" +
                "18 / 24\n" +
                "MCDB 1AL\n" +
                "R\n" +
                "9:00 am - 11:50am\n" +
                "LSB 1007\n" +
                "24 / 24\n" +
                "MCDB 1AL\n" +
                "R\n" +
                "12:00pm - 2:50pm\n" +
                "LSB 1007\n" +
                "24 / 24\n" +
                "MCDB 1AL\n" +
                "R\n" +
                "3:00pm - 5:50pm\n" +
                "LSB 1007\n" +
                "24 / 24\n" +
                "MCDB 1AL\n" +
                "R\n" +
                "6:00pm - 8:50pm\n" +
                "LSB 1007\n" +
                "24 / 24\n" +
                "MCDB 1AL\n" +
                "F\n" +
                "9:00 am - 11:50am\n" +
                "LSB 1007\n" +
                "7 / 24\n" +
                "MCDB 1AL\n" +
                "F\n" +
                "12:00pm - 2:50pm\n" +
                "LSB 1007\n" +
                "12 / 24\n" +
                "MCDB 1AL\n" +
                "F\n" +
                "3:00pm - 5:50pm\n" +
                "LSB 1007\n" +
                "4 / 24\n" +
                "MCDB 1AL\n" +
                "F\n" +
                "6:00pm - 8:50pm\n" +
                "LSB 1007\n" +
                "3 / 24\n" +
                "MCDB 1AZ\n" +
                "MTW F\n" +
                "8:00 am - 8:50 am\n" +
                "CAMPBHALL\n" +
                "1 / 15\n" +
                "MCDB 1AZ\n" +
                "MTW F\n" +
                "8:00 am - 8:50 am\n" +
                "BUCHN1910\n" +
                "1 / 15\n" +
                "MCDB 11\n" +
                "W\n" +
                "2:00pm - 2:50pm\n" +
                "BSIF 1217\n" +
                "0 / 35\n" +
                "MCDB 11\n" +
                "W\n" +
                "3:00pm - 3:50pm\n" +
                "BSIF 1217\n" +
                "0 / 35\n" +
                "MCDB 11\n" +
                "R\n" +
                "2:00pm - 2:50pm\n" +
                "BSIF 1217\n" +
                "0 / 35\n" +
                "MCDB 11\n" +
                "R\n" +
                "3:00pm - 3:50pm\n" +
                "BSIF 1217\n" +
                "0 / 35\n" +
                "MCDB 26\n" +
                "T R\n" +
                "11:00am - 12:15pm\n" +
                "NH 1006\n" +
                "38 / 42\n" +
                "MCDB 26\n" +
                "R\n" +
                "8:00 am - 8:50 am\n" +
                "BUCHN1934\n" +
                "6 / 22\n" +
                "MCDB 26\n" +
                "R\n" +
                "5:00pm - 5:50pm\n" +
                "BUCHN1934\n" +
                "13 / 22\n" +
                "MCDB 26\n" +
                "F\n" +
                "9:00 am - 9:50 am\n" +
                "BUCHN1934\n" +
                "4 / 22\n" +
                "MCDB 26\n" +
                "F\n" +
                "10:00am - 10:50am\n" +
                "BUCHN1934\n" +
                "4 / 22\n" +
                "MCDB 26\n" +
                "F\n" +
                "11:00am - 11:50am\n" +
                "GIRV 2124\n" +
                "6 / 22\n" +
                "MCDB 26\n" +
                "F\n" +
                "12:00pm - 12:50pm\n" +
                "BUCHN1934\n" +
                "5 / 22\n" +
                "MCDB 84\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "0 / 0\n" +
                "MCDB 90A\n" +
                "T\n" +
                "9:30 am - 10:45am\n" +
                "387 103\n" +
                "0 / 25\n" +
                "MCDB 92\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "0 / 0\n" +
                "MCDB 99\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "0 / 1\n" +
                "MCDB 101A\n" +
                "T R\n" +
                "8:00 am - 9:15 am\n" +
                "CHEM 1179\n" +
                "290 / 290\n" +
                "MCDB 101A\n" +
                "R\n" +
                "6:00pm - 6:50pm\n" +
                "BUCHN1934\n" +
                "20 / 20\n" +
                "MCDB 101A\n" +
                "R\n" +
                "7:00pm - 7:50pm\n" +
                "BUCHN1934\n" +
                "20 / 20\n" +
                "MCDB 101A\n" +
                "F\n" +
                "2:00pm - 2:50pm\n" +
                "BUCHN1934\n" +
                "20 / 20\n" +
                "MCDB 101A\n" +
                "F\n" +
                "1:00pm - 1:50pm\n" +
                "GIRV 2124\n" +
                "20 / 20\n" +
                "MCDB 101A\n" +
                "F\n" +
                "11:00am - 11:50am\n" +
                "387 103\n" +
                "21 / 21\n" +
                "MCDB 101A\n" +
                "F\n" +
                "3:00pm - 3:50pm\n" +
                "BUCHN1934\n" +
                "21 / 21\n" +
                "MCDB 101A\n" +
                "F\n" +
                "12:00pm - 12:50pm\n" +
                "GIRV 2124\n" +
                "21 / 21\n" +
                "MCDB 101A\n" +
                "F\n" +
                "1:00pm - 1:50pm\n" +
                "GIRV 2108\n" +
                "21 / 21\n" +
                "MCDB 101A\n" +
                "R\n" +
                "5:00pm - 5:50pm\n" +
                "GIRV 2108\n" +
                "21 / 21\n" +
                "MCDB 101A\n" +
                "F\n" +
                "10:00am - 10:50am\n" +
                "GIRV 2124\n" +
                "21 / 21\n" +
                "MCDB 101A\n" +
                "R\n" +
                "4:00pm - 4:50pm\n" +
                "GIRV 2112\n" +
                "21 / 21\n" +
                "MCDB 101A\n" +
                "F\n" +
                "9:00 am - 9:50 am\n" +
                "GIRV 2124\n" +
                "21 / 21\n" +
                "MCDB 101A\n" +
                "F\n" +
                "8:00 am - 8:50 am\n" +
                "BUCHN1934\n" +
                "21 / 21\n" +
                "MCDB 101A\n" +
                "W\n" +
                "4:00pm - 4:50pm\n" +
                "GIRV 2124\n" +
                "21 / 21\n" +
                "MCDB 101AH\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "0 / 25\n" +
                "MCDB 101L\n" +
                "T R\n" +
                "9:00 am - 12:50pm\n" +
                "BSIF 2239\n" +
                "17 / 18\n" +
                "MCDB 101L\n" +
                "F\n" +
                "2:00pm - 2:50pm\n" +
                "GIRV 2108\n" +
                "17 / 18\n" +
                "MCDB 101L\n" +
                "T R\n" +
                "2:00pm - 5:50pm\n" +
                "BSIF 2239\n" +
                "18 / 18\n" +
                "MCDB 101L\n" +
                "F\n" +
                "2:00pm - 2:50pm\n" +
                "GIRV 2108\n" +
                "18 / 18\n" +
                "MCDB 108A\n" +
                "M W F\n" +
                "1:00pm - 1:50pm\n" +
                "CHEM 1179\n" +
                "291 / 295\n" +
                "MCDB 108A\n" +
                "F\n" +
                "9:00 am - 9:50 am\n" +
                "GIRV 2108\n" +
                "22 / 22\n" +
                "MCDB 108A\n" +
                "F\n" +
                "8:00 am - 8:50 am\n" +
                "GIRV 2124\n" +
                "18 / 22\n" +
                "MCDB 108A\n" +
                "M\n" +
                "6:00pm - 6:50pm\n" +
                "BUCHN1934\n" +
                "22 / 22\n" +
                "MCDB 108A\n" +
                "F\n" +
                "11:00am - 11:50am\n" +
                "387 104\n" +
                "22 / 22\n" +
                "MCDB 108A\n" +
                "F\n" +
                "10:00am - 10:50am\n" +
                "GIRV 2108\n" +
                "23 / 23\n" +
                "MCDB 108A\n" +
                "T\n" +
                "6:00pm - 6:50pm\n" +
                "BUCHN1934\n" +
                "23 / 23\n" +
                "MCDB 108A\n" +
                "W\n" +
                "6:00pm - 6:50pm\n" +
                "BUCHN1934\n" +
                "23 / 23\n" +
                "MCDB 108A\n" +
                "M\n" +
                "5:00pm - 5:50pm\n" +
                "BUCHN1934\n" +
                "23 / 23\n" +
                "MCDB 108A\n" +
                "M\n" +
                "8:00 am - 8:50 am\n" +
                "BUCHN1934\n" +
                "23 / 23\n" +
                "MCDB 108A\n" +
                "F\n" +
                "11:00am - 11:50am\n" +
                "GIRV 2112\n" +
                "23 / 23\n" +
                "MCDB 108A\n" +
                "M\n" +
                "4:00pm - 4:50pm\n" +
                "BUCHN1934\n" +
                "23 / 23\n" +
                "MCDB 108A\n" +
                "T\n" +
                "5:00pm - 5:50pm\n" +
                "GIRV 2124\n" +
                "23 / 23\n" +
                "MCDB 108A\n" +
                "T\n" +
                "4:00pm - 4:50pm\n" +
                "GIRV 2112\n" +
                "23 / 23\n" +
                "MCDB 111\n" +
                "W F\n" +
                "11:00am - 12:15pm\n" +
                "IV THEA1\n" +
                "266 / 295\n" +
                "MCDB 111\n" +
                "R\n" +
                "8:00 am - 8:50 am\n" +
                "GIRV 2124\n" +
                "19 / 22\n" +
                "MCDB 111\n" +
                "T\n" +
                "5:00pm - 5:50pm\n" +
                "GIRV 2108\n" +
                "22 / 22\n" +
                "MCDB 111\n" +
                "M\n" +
                "6:00pm - 6:50pm\n" +
                "GIRV 2108\n" +
                "22 / 22\n" +
                "MCDB 111\n" +
                "R\n" +
                "5:00pm - 5:50pm\n" +
                "387 104\n" +
                "22 / 22\n" +
                "MCDB 111\n" +
                "W\n" +
                "5:00pm - 5:50pm\n" +
                "BUCHN1934\n" +
                "23 / 23\n" +
                "MCDB 111\n" +
                "R\n" +
                "6:00pm - 6:50pm\n" +
                "GIRV 2123\n" +
                "19 / 23\n" +
                "MCDB 111\n" +
                "F\n" +
                "10:00am - 10:50am\n" +
                "387 104\n" +
                "23 / 23\n" +
                "MCDB 111\n" +
                "M\n" +
                "4:00pm - 4:50pm\n" +
                "387 104\n" +
                "23 / 23\n" +
                "MCDB 111\n" +
                "T\n" +
                "8:00 am - 8:50 am\n" +
                "BUCHN1934\n" +
                "4 / 23\n" +
                "MCDB 111\n" +
                "F\n" +
                "1:00pm - 1:50pm\n" +
                "387 103\n" +
                "23 / 23\n" +
                "MCDB 111\n" +
                "F\n" +
                "9:00 am - 9:50 am\n" +
                "387 103\n" +
                "23 / 23\n" +
                "MCDB 111\n" +
                "W\n" +
                "8:00 am - 8:50 am\n" +
                "BUCHN1934\n" +
                "20 / 23\n" +
                "MCDB 111\n" +
                "W\n" +
                "4:00pm - 4:50pm\n" +
                "GIRV 2112\n" +
                "23 / 23\n" +
                "MCDB 111H\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "0 / 25\n" +
                "MCDB 112\n" +
                "T R\n" +
                "9:30 am - 10:45am\n" +
                "LSB 1001\n" +
                "144 / 144\n" +
                "MCDB 112\n" +
                "F\n" +
                "8:00 am - 8:50 am\n" +
                "BSIF 1229\n" +
                "18 / 18\n" +
                "MCDB 112\n" +
                "F\n" +
                "9:00 am - 9:50 am\n" +
                "BSIF 1229\n" +
                "18 / 18\n" +
                "MCDB 112\n" +
                "F\n" +
                "10:00am - 10:50am\n" +
                "BSIF 1229\n" +
                "18 / 18\n" +
                "MCDB 112\n" +
                "F\n" +
                "11:00am - 11:50am\n" +
                "BSIF 1229\n" +
                "18 / 18\n" +
                "MCDB 112\n" +
                "F\n" +
                "1:00pm - 1:50pm\n" +
                "BSIF 1229\n" +
                "18 / 18\n" +
                "MCDB 112\n" +
                "F\n" +
                "2:00pm - 2:50pm\n" +
                "BSIF 1229\n" +
                "18 / 18\n" +
                "MCDB 112\n" +
                "F\n" +
                "3:00pm - 3:50pm\n" +
                "BSIF 1229\n" +
                "18 / 18\n" +
                "MCDB 112\n" +
                "F\n" +
                "12:00pm - 12:50pm\n" +
                "BSIF 1229\n" +
                "18 / 18\n" +
                "MCDB 112H\n" +
                "W\n" +
                "10:00am - 10:50am\n" +
                "BIOL23119\n" +
                "0 / 25\n" +
                "MCDB 126A\n" +
                "M W F\n" +
                "9:00 am - 9:50 am\n" +
                "BUCHN1940\n" +
                "98 / 100\n" +
                "MCDB 126A\n" +
                "F\n" +
                "12:00pm - 12:50pm\n" +
                "GIRV 2108\n" +
                "25 / 25\n" +
                "MCDB 126A\n" +
                "F\n" +
                "1:00pm - 1:50pm\n" +
                "387 104\n" +
                "25 / 25\n" +
                "MCDB 126A\n" +
                "W\n" +
                "8:00 am - 8:50 am\n" +
                "GIRV 2124\n" +
                "24 / 25\n" +
                "MCDB 126A\n" +
                "F\n" +
                "2:00pm - 2:50pm\n" +
                "GIRV 2124\n" +
                "24 / 25\n" +
                "MCDB 126AL\n" +
                "T\n" +
                "9:30 am - 5:00pm\n" +
                "LSB 1003\n" +
                "16 / 16\n" +
                "MCDB 126AL\n" +
                "T\n" +
                "8:00 am - 9:15 am\n" +
                "PSYCH1902\n" +
                "16 / 16\n" +
                "MCDB 126AL\n" +
                "R\n" +
                "9:30 am - 5:00pm\n" +
                "LSB 1003\n" +
                "5 / 16\n" +
                "MCDB 126AL\n" +
                "T\n" +
                "8:00 am - 9:15 am\n" +
                "PSYCH1902\n" +
                "5 / 16\n" +
                "MCDB 126AL\n" +
                "W\n" +
                "10:00am - 5:30pm\n" +
                "LSB 1003\n" +
                "8 / 16\n" +
                "MCDB 126AL\n" +
                "T\n" +
                "8:00 am - 9:15 am\n" +
                "PSYCH1902\n" +
                "8 / 16\n" +
                "MCDB 131\n" +
                "M W F\n" +
                "10:00am - 10:50am\n" +
                "GIRV 1004\n" +
                "176 / 200\n" +
                "MCDB 131\n" +
                "M\n" +
                "4:00pm - 4:50pm\n" +
                "GIRV 2124\n" +
                "25 / 25\n" +
                "MCDB 131\n" +
                "M\n" +
                "5:00pm - 5:50pm\n" +
                "387 103\n" +
                "25 / 25\n" +
                "MCDB 131\n" +
                "M\n" +
                "3:00pm - 3:50pm\n" +
                "387 104\n" +
                "25 / 25\n" +
                "MCDB 131\n" +
                "T\n" +
                "8:00 am - 8:50 am\n" +
                "HSSB 1228\n" +
                "18 / 25\n" +
                "MCDB 131\n" +
                "T\n" +
                "5:00pm - 5:50pm\n" +
                "387 103\n" +
                "25 / 25\n" +
                "MCDB 131\n" +
                "T\n" +
                "6:00pm - 6:50pm\n" +
                "GIRV 2124\n" +
                "13 / 25\n" +
                "MCDB 131\n" +
                "T\n" +
                "12:00pm - 12:50pm\n" +
                "387 103\n" +
                "25 / 25\n" +
                "MCDB 131\n" +
                "M\n" +
                "6:00pm - 6:50pm\n" +
                "GIRV 2112\n" +
                "20 / 25\n" +
                "MCDB 131L\n" +
                "M W F\n" +
                "8:00 am - 9:50 am\n" +
                "BSIF 2229\n" +
                "5 / 20\n" +
                "MCDB 131L\n" +
                "M W F\n" +
                "11:00am - 12:50pm\n" +
                "BSIF 2229\n" +
                "20 / 20\n" +
                "MCDB 131L\n" +
                "M W F\n" +
                "2:00pm - 3:50pm\n" +
                "BSIF 2229\n" +
                "1 / 20\n" +
                "MCDB 131L\n" +
                "M W F\n" +
                "4:00pm - 5:50pm\n" +
                "BSIF 2229\n" +
                "4 / 20\n" +
                "MCDB 151\n" +
                "M W\n" +
                "3:30pm - 4:45pm\n" +
                "EMBARHALL\n" +
                "120 / 120\n" +
                "MCDB 151\n" +
                "W\n" +
                "6:00pm - 6:50pm\n" +
                "PHELP3505\n" +
                "20 / 20\n" +
                "MCDB 151\n" +
                "F\n" +
                "1:00pm - 1:50pm\n" +
                "GIRV 2112\n" +
                "20 / 20\n" +
                "MCDB 151\n" +
                "W\n" +
                "5:00pm - 5:50pm\n" +
                "387 103\n" +
                "20 / 20\n" +
                "MCDB 151\n" +
                "F\n" +
                "12:00pm - 12:50pm\n" +
                "387 103\n" +
                "20 / 20\n" +
                "MCDB 151\n" +
                "F\n" +
                "11:00am - 11:50am\n" +
                "GIRV 1116\n" +
                "20 / 20\n" +
                "MCDB 151\n" +
                "F\n" +
                "2:00pm - 2:50pm\n" +
                "387 103\n" +
                "20 / 20\n" +
                "MCDB 183\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "0 / 1\n" +
                "MCDB 184\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "0 / 1\n" +
                "MCDB 188\n" +
                "F\n" +
                "11:00am - 11:50am\n" +
                "BUCHN1934\n" +
                "0 / 5\n" +
                "MCDB 194MP\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "0 / 0\n" +
                "MCDB 194RF\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "0 / 10\n" +
                "MCDB 194X\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "0 / 5\n" +
                "MCDB 197\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "0 / 10\n" +
                "MCDB 198\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "0 / 6\n" +
                "MCDB 199\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "0 / 1\n" +
                "MUS 1\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "47 / 47\n" +
                "MUS 3A\n" +
                "M W\n" +
                "1:30pm - 2:50pm\n" +
                "MUSIC1145\n" +
                "8 / 50\n" +
                "MUS 3A\n" +
                "R\n" +
                "10:00am - 10:50am\n" +
                "MUSIC2224\n" +
                "5 / 25\n" +
                "MUS 3A\n" +
                "R\n" +
                "11:00am - 11:50am\n" +
                "MUSIC2224\n" +
                "3 / 25\n" +
                "MUS 4A\n" +
                "M W F\n" +
                "8:00 am - 8:50 am\n" +
                "MUSIC2230\n" +
                "4 / 25\n" +
                "MUS 4A\n" +
                "M W F\n" +
                "8:00 am - 8:50 am\n" +
                "MUSIC1213\n" +
                "0 / 25\n" +
                "MUS 4A\n" +
                "M W F\n" +
                "8:00 am - 8:50 am\n" +
                "MUSIC2224\n" +
                "0 / 25\n" +
                "MUS 4D\n" +
                "M W F\n" +
                "10:00am - 10:50am\n" +
                "MUSIC2230\n" +
                "5 / 15\n" +
                "MUS 4D\n" +
                "M W F\n" +
                "10:00am - 10:50am\n" +
                "MUSIC1213\n" +
                "2 / 15\n" +
                "MUS 4D\n" +
                "M W F\n" +
                "10:00am - 10:50am\n" +
                "MUSIC2224\n" +
                "3 / 15\n" +
                "MUS 5A\n" +
                "M W F\n" +
                "9:00 am - 9:50 am\n" +
                "MUSIC1145\n" +
                "15 / 40\n" +
                "MUS 5A\n" +
                "T\n" +
                "9:00 am - 9:50 am\n" +
                "MUSIC2230\n" +
                "6 / 20\n" +
                "MUS 5A\n" +
                "T\n" +
                "10:00am - 10:50am\n" +
                "MUSIC2230\n" +
                "9 / 20\n" +
                "MUS 8\n" +
                "W\n" +
                "11:00am - 12:50pm\n" +
                "MUSIC2230\n" +
                "0 / 10\n" +
                "MUS 10A\n" +
                "T R\n" +
                "11:00am - 12:20pm\n" +
                "MUSIC1145\n" +
                "1 / 50\n" +
                "MUS 10A\n" +
                "F\n" +
                "10:00am - 10:50am\n" +
                "MUSIC1207\n" +
                "1 / 25\n" +
                "MUS 10A\n" +
                "F\n" +
                "11:00am - 11:50am\n" +
                "MUSIC1207\n" +
                "0 / 25\n" +
                "MUS 11\n" +
                "M W F\n" +
                "8:00 am - 8:50 am\n" +
                "MUSIC1145\n" +
                "80 / 80\n" +
                "MUS 11\n" +
                "T\n" +
                "12:00pm - 12:50pm\n" +
                "MUSIC1232\n" +
                "14 / 14\n" +
                "MUS 11\n" +
                "T\n" +
                "10:00am - 10:50am\n" +
                "MUSIC1232\n" +
                "14 / 14\n" +
                "MUS 11\n" +
                "R\n" +
                "9:00 am - 9:50 am\n" +
                "MUSIC1232\n" +
                "6 / 14\n" +
                "MUS 11\n" +
                "T\n" +
                "9:00 am - 9:50 am\n" +
                "MUSIC1232\n" +
                "7 / 14\n" +
                "MUS 11\n" +
                "R\n" +
                "11:00am - 11:50am\n" +
                "MUSIC1232\n" +
                "5 / 14\n" +
                "MUS 11\n" +
                "T\n" +
                "11:00am - 11:50am\n" +
                "MUSIC1232\n" +
                "9 / 14\n" +
                "MUS 11\n" +
                "R\n" +
                "10:00am - 10:50am\n" +
                "MUSIC1232\n" +
                "6 / 14\n" +
                "MUS 11\n" +
                "R\n" +
                "12:00pm - 12:50pm\n" +
                "MUSIC1232\n" +
                "2 / 14\n" +
                "MUS 11\n" +
                "T\n" +
                "1:00pm - 1:50pm\n" +
                "MUSIC1232\n" +
                "5 / 14\n" +
                "MUS 11\n" +
                "T\n" +
                "2:00pm - 2:50pm\n" +
                "MUSIC1232\n" +
                "12 / 14\n" +
                "MUS 15\n" +
                "T R\n" +
                "11:00am - 12:15pm\n" +
                "MUSICLLCH\n" +
                "250 / 250\n" +
                "MUS 15\n" +
                "M\n" +
                "11:00am - 11:50am\n" +
                "MUSIC1207\n" +
                "29 / 30\n" +
                "MUS 15\n" +
                "T\n" +
                "9:00 am - 9:50 am\n" +
                "MUSIC2224\n" +
                "16 / 30\n" +
                "MUS 15\n" +
                "M\n" +
                "9:00 am - 9:50 am\n" +
                "MUSIC1207\n" +
                "3 / 30\n" +
                "MUS 15\n" +
                "M\n" +
                "10:00am - 10:50am\n" +
                "MUSIC1207\n" +
                "19 / 30\n" +
                "MUS 15\n" +
                "F\n" +
                "1:00pm - 1:50pm\n" +
                "MUSIC1207\n" +
                "11 / 30\n" +
                "MUS 15\n" +
                "W\n" +
                "10:00am - 10:50am\n" +
                "MUSIC1207\n" +
                "30 / 30\n" +
                "MUS 15\n" +
                "T\n" +
                "8:00 am - 8:50 am\n" +
                "MUSIC1213\n" +
                "2 / 30\n" +
                "MUS 15\n" +
                "R\n" +
                "10:00am - 10:50am\n" +
                "MUSIC2230\n" +
                "30 / 30\n" +
                "MUS 15\n" +
                "T\n" +
                "8:00 am - 8:50 am\n" +
                "MUSIC2224\n" +
                "0 / 30\n" +
                "MUS 15\n" +
                "M\n" +
                "12:00pm - 12:50pm\n" +
                "MUSIC1207\n" +
                "19 / 30\n" +
                "MUS 15\n" +
                "W\n" +
                "12:00pm - 12:50pm\n" +
                "MUSIC1213\n" +
                "29 / 30\n" +
                "MUS 15\n" +
                "F\n" +
                "12:00pm - 12:50pm\n" +
                "MUSIC1207\n" +
                "3 / 30\n" +
                "MUS 15\n" +
                "R\n" +
                "9:00 am - 9:50 am\n" +
                "MUSIC2230\n" +
                "3 / 30\n" +
                "MUS 15\n" +
                "R\n" +
                "1:00pm - 1:50pm\n" +
                "MUSIC2224\n" +
                "26 / 30\n" +
                "MUS 15\n" +
                "T\n" +
                "1:00pm - 1:50pm\n" +
                "MUSIC2224\n" +
                "30 / 30\n" +
                "MUS 17\n" +
                "M W F\n" +
                "10:00am - 10:50am\n" +
                "MUSIC1145\n" +
                "100 / 100\n" +
                "MUS 17\n" +
                "M\n" +
                "11:00am - 11:50am\n" +
                "MUSIC1213\n" +
                "23 / 23\n" +
                "MUS 17\n" +
                "T\n" +
                "10:00am - 10:50am\n" +
                "MUSIC1207\n" +
                "23 / 24\n" +
                "MUS 17\n" +
                "W\n" +
                "11:00am - 11:50am\n" +
                "MUSIC2230\n" +
                "22 / 23\n" +
                "MUS 17\n" +
                "R\n" +
                "10:00am - 10:50am\n" +
                "MUSIC1207\n" +
                "14 / 24\n" +
                "MUS 17\n" +
                "F\n" +
                "11:00am - 11:50am\n" +
                "MUSIC1207\n" +
                "6 / 24\n" +
                "MUS 17\n" +
                "F\n" +
                "9:00 am - 9:50 am\n" +
                "MUSIC1207\n" +
                "12 / 24\n" +
                "MUS 20A\n" +
                "M W\n" +
                "3:30pm - 4:45pm\n" +
                "MUSIC1207\n" +
                "11 / 30\n" +
                "MUS 20B\n" +
                "T R\n" +
                "9:30 am - 10:45am\n" +
                "MUSIC1213\n" +
                "7 / 20\n" +
                "MUS 20C\n" +
                "M W\n" +
                "3:30pm - 4:45pm\n" +
                "MUSIC2230\n" +
                "3 / 20\n" +
                "MUS 24\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "0 / 5\n" +
                "MUS 25\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "0 / 10\n" +
                "MUS 25\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "0 / 10\n" +
                "MUS 25\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "0 / 10\n" +
                "MUS 26A\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "0 / 3\n" +
                "MUS 26B\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "2 / 7\n" +
                "MUS 26D\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "0 / 5\n" +
                "MUS 26E\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "1 / 5\n" +
                "MUS 27A\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "0 / 5\n" +
                "MUS 27B\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "0 / 5\n" +
                "MUS 27C\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "0 / 10\n" +
                "MUS 27D\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "0 / 3\n" +
                "MUS 28A\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "0 / 5\n" +
                "MUS 28B\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "0 / 5\n" +
                "MUS 28C\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "0 / 5\n" +
                "MUS 29\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "0 / 5\n" +
                "MUS 31A\n" +
                "M W F\n" +
                "10:00am - 10:50am\n" +
                "MUSIC1232\n" +
                "0 / 12\n" +
                "MUS 31A\n" +
                "M W F\n" +
                "11:00am - 11:50am\n" +
                "MUSIC1232\n" +
                "2 / 12\n" +
                "MUS 31A\n" +
                "M W F\n" +
                "12:00pm - 12:50pm\n" +
                "MUSIC1232\n" +
                "0 / 12\n" +
                "MUS 31X\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "0 / 12\n" +
                "MUS 31X\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "0 / 6\n" +
                "MUS 32A\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "0 / 5\n" +
                "MUS 33\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "1 / 20\n" +
                "MUS 33\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "0 / 5\n" +
                "MUS 33\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "0 / 10\n" +
                "MUS 35A\n" +
                "M\n" +
                "2:30pm - 4:00pm\n" +
                "MUSICGHALL\n" +
                "0 / 5\n" +
                "MUS 51\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "0 / 10\n" +
                "MUS 51\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "0 / 10\n" +
                "MUS 52\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "0 / 10\n" +
                "MUS 52\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "0 / 10\n" +
                "MUS 52\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "0 / 10\n" +
                "MUS 88\n" +
                "T\n" +
                "11:00am - 12:50pm\n" +
                "MUSIC2230\n" +
                "0 / 10\n" +
                "MUS 94\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "0 / 15\n" +
                "MUS 97\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "0 / 15\n" +
                "MUS 97B\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "0 / 15\n" +
                "MUS 98\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "0 / 15\n" +
                "MUS 99\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "0 / 15\n" +
                "MUS CS 101\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "3 / 10\n" +
                "MUS CS 101\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "3 / 15\n" +
                "MUS 101A\n" +
                "T R\n" +
                "2:00pm - 3:20pm\n" +
                "MUSIC2230\n" +
                "0 / 20\n" +
                "MUS CS 102\n" +
                "M W\n" +
                "12:00pm - 1:20pm\n" +
                "494 154\n" +
                "1 / 15\n" +
                "MUS CS 105\n" +
                "T\n" +
                "3:00pm - 4:50pm\n" +
                "MUSIC1145\n" +
                "0 / 10\n" +
                "MUS CS 105\n" +
                "T R\n" +
                "12:30pm - 1:50pm\n" +
                "494 154\n" +
                "4 / 10\n" +
                "MUS 108\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "0 / 5\n" +
                "MUS 109LA\n" +
                "M\n" +
                "3:00pm - 5:00pm\n" +
                "MUSIC2215\n" +
                "0 / 20\n" +
                "MUS 114\n" +
                "T R\n" +
                "12:30pm - 1:45pm\n" +
                "MUSIC1145\n" +
                "120 / 120\n" +
                "MUS 114\n" +
                "T\n" +
                "8:00 am - 8:50 am\n" +
                "MUSIC1207\n" +
                "20 / 20\n" +
                "MUS 114\n" +
                "T\n" +
                "9:00 am - 9:50 am\n" +
                "MUSIC1207\n" +
                "20 / 20\n" +
                "MUS 114\n" +
                "R\n" +
                "9:00 am - 9:50 am\n" +
                "MUSIC1207\n" +
                "20 / 20\n" +
                "MUS 114\n" +
                "F\n" +
                "12:00pm - 12:50pm\n" +
                "MUSIC2224\n" +
                "20 / 20\n" +
                "MUS 114\n" +
                "F\n" +
                "11:00am - 11:50am\n" +
                "MUSIC1207\n" +
                "20 / 20\n" +
                "MUS 114\n" +
                "W\n" +
                "11:00am - 11:50am\n" +
                "MUSIC1213\n" +
                "20 / 20\n" +
                "MUS 115\n" +
                "T R\n" +
                "11:00am - 12:15pm\n" +
                "MUSIC2224\n" +
                "15 / 30\n" +
                "MUS 124\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "3 / 5\n" +
                "MUS 125\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "0 / 10\n" +
                "MUS 125\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "0 / 10\n" +
                "MUS 125\n" +
                "W\n" +
                "2:30pm - 4:00pm\n" +
                "MUSICGHALL\n" +
                "0 / 10\n" +
                "MUS 126B\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "0 / 10\n" +
                "MUS 126D\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "0 / 10\n" +
                "MUS 126E\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "0 / 10\n" +
                "MUS 127B\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "1 / 10\n" +
                "MUS 127C\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "1 / 10\n" +
                "MUS 128A\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "0 / 10\n" +
                "MUS 128B\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "0 / 5\n" +
                "MUS 128C\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "0 / 10\n" +
                "MUS 129\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "0 / 10\n" +
                "MUS 133\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "0 / 10\n" +
                "MUS 133\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "0 / 5\n" +
                "MUS 135A\n" +
                "M\n" +
                "2:30pm - 4:00pm\n" +
                "MUSICGHALL\n" +
                "0 / 10\n" +
                "MUS 152\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "0 / 10\n" +
                "MUS 152\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "0 / 10\n" +
                "MUS 152\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "0 / 10\n" +
                "MUS 158B\n" +
                "T R\n" +
                "3:30pm - 3:50pm\n" +
                "MUSIC2236\n" +
                "0 / 20\n" +
                "MUS 160A\n" +
                "M W F\n" +
                "11:00am - 11:50am\n" +
                "MUSIC2224\n" +
                "9 / 30\n" +
                "MUS 160D\n" +
                "F\n" +
                "1:00pm - 3:30pm\n" +
                "MUSIC2224\n" +
                "16 / 25\n" +
                "MUS 168G\n" +
                "M W\n" +
                "2:00pm - 3:15pm\n" +
                "MUSIC2230\n" +
                "12 / 20\n" +
                "MUS 175X\n" +
                "W\n" +
                "9:00 am - 11:50am\n" +
                "MUSICGHALL\n" +
                "17 / 20\n" +
                "MUS 183\n" +
                "T\n" +
                "1:00pm - 3:30pm\n" +
                "MUSIC2230\n" +
                "3 / 20\n" +
                "MUS 188\n" +
                "W\n" +
                "3:30pm - 5:00pm\n" +
                "MUSIC1213\n" +
                "0 / 10\n" +
                "MUS 192\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "0 / 10\n" +
                "MUS 195\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "0 / 20\n" +
                "MUS 195B\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "0 / 20\n" +
                "MUS 196\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "0 / 15\n" +
                "MUS 197\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "0 / 20\n" +
                "MUS 197B\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "0 / 10\n" +
                "MUS 198\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "0 / 10\n" +
                "MUS 199\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "0 / 0\n" +
                "MUS 199RA\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "0 / 10\n" +
                "MUS CS 101\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "3 / 10\n" +
                "MUS CS 101\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "3 / 15\n" +
                "MUS CS 102\n" +
                "M W\n" +
                "12:00pm - 1:20pm\n" +
                "494 154\n" +
                "1 / 15\n" +
                "MUS CS 105\n" +
                "T\n" +
                "3:00pm - 4:50pm\n" +
                "MUSIC1145\n" +
                "0 / 10\n" +
                "MUS CS 105\n" +
                "T R\n" +
                "12:30pm - 1:50pm\n" +
                "494 154\n" +
                "4 / 10\n" +
                "MUS A 34\n" +
                "T\n" +
                "6:00pm - 8:30pm\n" +
                "MUSICLLCH\n" +
                "4 / 60\n" +
                "MUS A 34\n" +
                "R\n" +
                "6:00pm - 7:30pm\n" +
                "MUSICGHALL\n" +
                "4 / 60\n" +
                "MUS A 36\n" +
                "M W\n" +
                "5:00pm - 6:50pm\n" +
                "MUSIC1145\n" +
                "0 / 40\n" +
                "MUS A 37\n" +
                "T\n" +
                "5:00pm - 7:50pm\n" +
                "MUSIC1145\n" +
                "0 / 50\n" +
                "MUS A 38\n" +
                "T\n" +
                "4:00pm - 5:50pm\n" +
                "MUSICLLCH\n" +
                "1 / 15\n" +
                "MUS A 38\n" +
                "RF\n" +
                "4:00pm - 6:30pm\n" +
                "MUSICLLCH\n" +
                "1 / 15\n" +
                "MUS A 40\n" +
                "M W\n" +
                "3:00pm - 4:50pm\n" +
                "MUSIC1219\n" +
                "0 / 15\n" +
                "MUS A 41\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "0 / 10\n" +
                "MUS A 42\n" +
                "M\n" +
                "5:00pm - 9:30pm\n" +
                "MUSICLLCH\n" +
                "4 / 40\n" +
                "MUS A 43\n" +
                "W\n" +
                "6:00pm - 7:00pm\n" +
                "MUSICGHALL\n" +
                "0 / 10\n" +
                "MUS A 44\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "0 / 10\n" +
                "MUS A 45\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "0 / 5\n" +
                "MUS A 45BR\n" +
                "W\n" +
                "12:00pm - 1:20pm\n" +
                "MUSIC1145\n" +
                "0 / 30\n" +
                "MUS A 45H\n" +
                "M\n" +
                "12:00pm - 12:50pm\n" +
                "MUSICGHALL\n" +
                "0 / 10\n" +
                "MUS A 45H\n" +
                "F\n" +
                "12:00pm - 12:50pm\n" +
                "MUSIC1145\n" +
                "0 / 10\n" +
                "MUS A 46\n" +
                "W\n" +
                "4:30pm - 6:00pm\n" +
                "MUSICGHALL\n" +
                "0 / 20\n" +
                "MUS A 49\n" +
                "T\n" +
                "2:00pm - 3:50pm\n" +
                "MUSICGHALL\n" +
                "0 / 16\n" +
                "MUS A 52\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "0 / 10\n" +
                "MUS A 70J\n" +
                "T R\n" +
                "5:30pm - 7:00pm\n" +
                "MUSIC1231\n" +
                "2 / 20\n" +
                "MUS A 70M\n" +
                "T\n" +
                "7:00pm - 9:50pm\n" +
                "MUSICGHALL\n" +
                "1 / 20\n" +
                "MUS A 70N\n" +
                "T\n" +
                "5:00pm - 6:30pm\n" +
                "MUSIC1207\n" +
                "2 / 10\n" +
                "MUS A 70V\n" +
                "T\n" +
                "7:00pm - 9:30pm\n" +
                "MUSIC1145\n" +
                "3 / 100\n" +
                "MUS A 134\n" +
                "T\n" +
                "6:00pm - 8:30pm\n" +
                "MUSICLLCH\n" +
                "3 / 25\n" +
                "MUS A 134\n" +
                "R\n" +
                "6:00pm - 7:30pm\n" +
                "MUSICGHALL\n" +
                "3 / 25\n" +
                "MUS A 136\n" +
                "M W\n" +
                "5:00pm - 6:50pm\n" +
                "MUSIC1145\n" +
                "2 / 30\n" +
                "MUS A 137\n" +
                "T\n" +
                "5:00pm - 7:50pm\n" +
                "MUSIC1145\n" +
                "1 / 20\n" +
                "MUS A 138\n" +
                "T\n" +
                "4:00pm - 5:50pm\n" +
                "MUSICLLCH\n" +
                "0 / 15\n" +
                "MUS A 138\n" +
                "RF\n" +
                "4:00pm - 6:30pm\n" +
                "MUSICLLCH\n" +
                "0 / 15\n" +
                "MUS A 140\n" +
                "M W\n" +
                "3:00pm - 4:50pm\n" +
                "MUSIC1219\n" +
                "0 / 20\n" +
                "MUS A 141\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "0 / 5\n" +
                "MUS A 142\n" +
                "M\n" +
                "5:00pm - 9:30pm\n" +
                "MUSICLLCH\n" +
                "4 / 100\n" +
                "MUS A 143\n" +
                "W\n" +
                "6:00pm - 7:00pm\n" +
                "MUSICGHALL\n" +
                "1 / 10\n" +
                "MUS A 144\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "0 / 10\n" +
                "MUS A 145\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "1 / 5\n" +
                "MUS A 145BR\n" +
                "W\n" +
                "12:00pm - 1:20pm\n" +
                "MUSIC1145\n" +
                "1 / 15\n" +
                "MUS A 145H\n" +
                "M\n" +
                "12:00pm - 12:50pm\n" +
                "MUSICGHALL\n" +
                "0 / 10\n" +
                "MUS A 145H\n" +
                "F\n" +
                "12:00pm - 12:50pm\n" +
                "MUSIC1145\n" +
                "0 / 10\n" +
                "MUS A 146\n" +
                "W\n" +
                "4:30pm - 6:00pm\n" +
                "MUSICGHALL\n" +
                "0 / 10\n" +
                "MUS A 149\n" +
                "T\n" +
                "2:00pm - 3:50pm\n" +
                "MUSICGHALL\n" +
                "0 / 10\n" +
                "MUS A 152\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "0 / 0\n" +
                "MUS A 170J\n" +
                "T R\n" +
                "5:30pm - 7:00pm\n" +
                "MUSIC1231\n" +
                "4 / 15\n" +
                "MUS A 170M\n" +
                "T\n" +
                "7:00pm - 9:50pm\n" +
                "MUSICGHALL\n" +
                "1 / 20\n" +
                "MUS A 170N\n" +
                "T\n" +
                "5:00pm - 6:30pm\n" +
                "MUSIC1207\n" +
                "0 / 10\n" +
                "MUS A 170V\n" +
                "T\n" +
                "7:00pm - 9:30pm\n" +
                "MUSIC1145\n" +
                "2 / 100\n" +
                "PHIL 1\n" +
                "M W F\n" +
                "10:00am - 10:50am\n" +
                "MUSICLLCH\n" +
                "215 / 215\n" +
                "PHIL 1\n" +
                "M\n" +
                "5:00pm - 5:50pm\n" +
                "PHELP1445\n" +
                "30 / 30\n" +
                "PHIL 1\n" +
                "M\n" +
                "6:00pm - 6:50pm\n" +
                "PHELP1445\n" +
                "28 / 30\n" +
                "PHIL 1\n" +
                "T\n" +
                "8:00 am - 8:50 am\n" +
                "PHELP1444\n" +
                "21 / 30\n" +
                "PHIL 1\n" +
                "T\n" +
                "9:00 am - 9:50 am\n" +
                "GIRV 2124\n" +
                "30 / 30\n" +
                "PHIL 1\n" +
                "T\n" +
                "4:00pm - 4:50pm\n" +
                "GIRV 2115\n" +
                "30 / 30\n" +
                "PHIL 1\n" +
                "T\n" +
                "5:00pm - 5:50pm\n" +
                "PHELP2514\n" +
                "30 / 30\n" +
                "PHIL 1\n" +
                "T\n" +
                "6:00pm - 6:50pm\n" +
                "GIRV 1116\n" +
                "30 / 30\n" +
                "PHIL 1\n" +
                "W\n" +
                "8:00 am - 8:50 am\n" +
                "PHELP1444\n" +
                "16 / 30\n" +
                "PHIL 1\n" +
                "R\n" +
                "8:00 am - 8:50 am\n" +
                "PHELP1440\n" +
                "0 / 30\n" +
                "PHIL 1\n" +
                "R\n" +
                "9:00 am - 9:50 am\n" +
                "387 103\n" +
                "0 / 30\n" +
                "PHIL 3\n" +
                "T R\n" +
                "5:00pm - 6:15pm\n" +
                "BRDA 1610\n" +
                "180 / 180\n" +
                "PHIL 3\n" +
                "M\n" +
                "5:00pm - 5:50pm\n" +
                "PHELP1448\n" +
                "30 / 30\n" +
                "PHIL 3\n" +
                "M\n" +
                "6:00pm - 6:50pm\n" +
                "PHELP1448\n" +
                "30 / 30\n" +
                "PHIL 3\n" +
                "T\n" +
                "8:00 am - 8:50 am\n" +
                "PHELP1445\n" +
                "14 / 30\n" +
                "PHIL 3\n" +
                "T\n" +
                "9:00 am - 9:50 am\n" +
                "GIRV 2112\n" +
                "30 / 30\n" +
                "PHIL 3\n" +
                "T\n" +
                "3:00pm - 3:50pm\n" +
                "PHELP1440\n" +
                "30 / 30\n" +
                "PHIL 3\n" +
                "W\n" +
                "8:00 am - 8:50 am\n" +
                "PHELP1445\n" +
                "11 / 30\n" +
                "PHIL 3\n" +
                "R\n" +
                "8:00 am - 8:50 am\n" +
                "PHELP1444\n" +
                "5 / 30\n" +
                "PHIL 3\n" +
                "R\n" +
                "9:00 am - 9:50 am\n" +
                "387 104\n" +
                "30 / 30\n" +
                "PHIL 4\n" +
                "M W F\n" +
                "3:00pm - 3:50pm\n" +
                "BUCHN1910\n" +
                "190 / 190\n" +
                "PHIL 4\n" +
                "M\n" +
                "5:00pm - 5:50pm\n" +
                "PHELP2514\n" +
                "30 / 30\n" +
                "PHIL 4\n" +
                "M\n" +
                "6:00pm - 6:50pm\n" +
                "PHELP2514\n" +
                "30 / 30\n" +
                "PHIL 4\n" +
                "T\n" +
                "8:00 am - 8:50 am\n" +
                "PHELP1448\n" +
                "10 / 30\n" +
                "PHIL 4\n" +
                "T\n" +
                "9:00 am - 9:50 am\n" +
                "GIRV 2116\n" +
                "30 / 30\n" +
                "PHIL 4\n" +
                "T\n" +
                "4:00pm - 4:50pm\n" +
                "GIRV 2116\n" +
                "30 / 30\n" +
                "PHIL 4\n" +
                "T\n" +
                "5:00pm - 5:50pm\n" +
                "387 104\n" +
                "30 / 30\n" +
                "PHIL 4\n" +
                "T\n" +
                "6:00pm - 6:50pm\n" +
                "GIRV 2123\n" +
                "23 / 30\n" +
                "PHIL 4\n" +
                "W\n" +
                "8:00 am - 8:50 am\n" +
                "PHELP1448\n" +
                "7 / 30\n" +
                "PHIL 4\n" +
                "R\n" +
                "8:00 am - 8:50 am\n" +
                "PHELP1445\n" +
                "0 / 30\n" +
                "PHIL 4\n" +
                "R\n" +
                "9:00 am - 9:50 am\n" +
                "GIRV 1116\n" +
                "0 / 30\n" +
                "PHIL 20A\n" +
                "M W F\n" +
                "9:00 am - 9:50 am\n" +
                "HFH 1104\n" +
                "75 / 100\n" +
                "PHIL 20A\n" +
                "M\n" +
                "5:00pm - 5:50pm\n" +
                "387 104\n" +
                "13 / 30\n" +
                "PHIL 20A\n" +
                "M\n" +
                "6:00pm - 6:50pm\n" +
                "GIRV 1119\n" +
                "7 / 30\n" +
                "PHIL 20A\n" +
                "T\n" +
                "8:00 am - 8:50 am\n" +
                "GIRV 2108\n" +
                "3 / 30\n" +
                "PHIL 20A\n" +
                "T\n" +
                "9:00 am - 9:50 am\n" +
                "GIRV 2119\n" +
                "19 / 30\n" +
                "PHIL 20A\n" +
                "T\n" +
                "4:00pm - 4:50pm\n" +
                "GIRV 2120\n" +
                "30 / 30\n" +
                "PHIL 20A\n" +
                "T\n" +
                "5:00pm - 5:50pm\n" +
                "GIRV 1116\n" +
                "3 / 30\n" +
                "PHIL 100A\n" +
                "M W F\n" +
                "2:00pm - 2:50pm\n" +
                "BUCHN1940\n" +
                "35 / 120\n" +
                "PHIL 100A\n" +
                "T\n" +
                "5:00pm - 5:50pm\n" +
                "GIRV 2112\n" +
                "23 / 30\n" +
                "PHIL 100A\n" +
                "T\n" +
                "6:00pm - 6:50pm\n" +
                "GIRV 1119\n" +
                "2 / 30\n" +
                "PHIL 100A\n" +
                "R\n" +
                "8:00 am - 8:50 am\n" +
                "PHELP1448\n" +
                "6 / 30\n" +
                "PHIL 100A\n" +
                "F\n" +
                "8:00 am - 8:50 am\n" +
                "PHELP1440\n" +
                "4 / 30\n" +
                "PHIL 100C\n" +
                "M W F\n" +
                "3:00pm - 3:50pm\n" +
                "GIRV 2128\n" +
                "42 / 60\n" +
                "PHIL 100C\n" +
                "T\n" +
                "6:00pm - 6:50pm\n" +
                "GIRV 2127\n" +
                "30 / 30\n" +
                "PHIL 100C\n" +
                "F\n" +
                "8:00 am - 8:50 am\n" +
                "PHELP1444\n" +
                "12 / 30\n" +
                "PHIL 122\n" +
                "T R\n" +
                "2:00pm - 3:15pm\n" +
                "PHELP1508\n" +
                "35 / 35\n" +
                "PHIL 130\n" +
                "T R\n" +
                "3:30pm - 4:45pm\n" +
                "GIRV 1116\n" +
                "35 / 35\n" +
                "PHIL 135\n" +
                "T R\n" +
                "9:30 am - 10:45am\n" +
                "PHELP1508\n" +
                "8 / 35\n" +
                "PHIL 139\n" +
                "T R\n" +
                "11:00am - 12:15pm\n" +
                "ARTS 1349\n" +
                "35 / 35\n" +
                "PHIL 151\n" +
                "W\n" +
                "4:00pm - 6:50pm\n" +
                "GIRV 2123\n" +
                "11 / 35\n" +
                "PHIL 152\n" +
                "M W F\n" +
                "11:00am - 11:50am\n" +
                "GIRV 2108\n" +
                "20 / 32\n" +
                "PHIL 156\n" +
                "M W F\n" +
                "1:00pm - 1:50pm\n" +
                "GIRV 1116\n" +
                "15 / 35\n" +
                "PHIL 183\n" +
                "T R\n" +
                "12:30pm - 1:45pm\n" +
                "GIRV 2128\n" +
                "53 / 60\n" +
                "PHIL 183\n" +
                "W\n" +
                "5:00pm - 5:50pm\n" +
                "PHELP1448\n" +
                "30 / 30\n" +
                "PHIL 183\n" +
                "W\n" +
                "9:00 am - 9:50 am\n" +
                "387 104\n" +
                "23 / 30\n" +
                "PHIL 192\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "0 / 20\n" +
                "PHIL 197A\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "0 / 2\n" +
                "PHIL 197B\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "0 / 2\n" +
                "PHIL 197P\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "0 / 5\n" +
                "PHIL 199\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "0 / 5\n" +
                "PHIL 199RA\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "0 / 10\n" +
                "PHYS 2\n" +
                "M W\n" +
                "9:30 am - 10:45am\n" +
                "BRDA 1640\n" +
                "8 / 94\n" +
                "PHYS 2\n" +
                "M\n" +
                "5:00pm - 6:50pm\n" +
                "PHELP1508\n" +
                "4 / 47\n" +
                "PHYS 2\n" +
                "T\n" +
                "5:00pm - 6:50pm\n" +
                "PHELP1508\n" +
                "4 / 47\n" +
                "PHYS 3\n" +
                "T R\n" +
                "8:00 am - 9:15 am\n" +
                "BRDA 1610\n" +
                "239 / 286\n" +
                "PHYS 3\n" +
                "M W\n" +
                "8:00 am - 9:15 am\n" +
                "BRDA 1640\n" +
                "94 / 94\n" +
                "PHYS 3L\n" +
                "M\n" +
                "1:00pm - 3:50pm\n" +
                "BRDA 3332Y\n" +
                "16 / 24\n" +
                "PHYS 3L\n" +
                "M\n" +
                "1:00pm - 3:50pm\n" +
                "BRDA 2316B\n" +
                "14 / 24\n" +
                "PHYS 3L\n" +
                "M\n" +
                "4:00pm - 6:50pm\n" +
                "BRDA 3332Y\n" +
                "24 / 24\n" +
                "PHYS 3L\n" +
                "M\n" +
                "4:00pm - 6:50pm\n" +
                "BRDA 2316B\n" +
                "24 / 24\n" +
                "PHYS 3L\n" +
                "M\n" +
                "7:00pm - 9:50pm\n" +
                "BRDA 3332Y\n" +
                "24 / 24\n" +
                "PHYS 3L\n" +
                "M\n" +
                "7:00pm - 9:50pm\n" +
                "BRDA 2316B\n" +
                "24 / 24\n" +
                "PHYS 3L\n" +
                "T\n" +
                "1:00pm - 3:50pm\n" +
                "BRDA 3332Y\n" +
                "24 / 24\n" +
                "PHYS 3L\n" +
                "T\n" +
                "1:00pm - 3:50pm\n" +
                "BRDA 2316B\n" +
                "24 / 24\n" +
                "PHYS 3L\n" +
                "T\n" +
                "4:00pm - 6:50pm\n" +
                "BRDA 3332Y\n" +
                "24 / 24\n" +
                "PHYS 3L\n" +
                "T\n" +
                "4:00pm - 6:50pm\n" +
                "BRDA 2316B\n" +
                "24 / 24\n" +
                "PHYS 3L\n" +
                "T\n" +
                "7:00pm - 9:50pm\n" +
                "BRDA 3332Y\n" +
                "24 / 24\n" +
                "PHYS 3L\n" +
                "T\n" +
                "7:00pm - 9:50pm\n" +
                "BRDA 2316B\n" +
                "24 / 24\n" +
                "PHYS 3L\n" +
                "W\n" +
                "1:00pm - 3:50pm\n" +
                "BRDA 3332Y\n" +
                "11 / 24\n" +
                "PHYS 3L\n" +
                "W\n" +
                "1:00pm - 3:50pm\n" +
                "BRDA 2316B\n" +
                "13 / 24\n" +
                "PHYS 3L\n" +
                "W\n" +
                "4:00pm - 6:50pm\n" +
                "BRDA 3332Y\n" +
                "24 / 24\n" +
                "PHYS 3L\n" +
                "W\n" +
                "4:00pm - 6:50pm\n" +
                "BRDA 2316B\n" +
                "24 / 24\n" +
                "PHYS 3L\n" +
                "W\n" +
                "7:00pm - 9:50pm\n" +
                "BRDA 3332Y\n" +
                "24 / 24\n" +
                "PHYS 3L\n" +
                "W\n" +
                "7:00pm - 9:50pm\n" +
                "BRDA 2316B\n" +
                "24 / 24\n" +
                "PHYS 3L\n" +
                "R\n" +
                "4:00pm - 6:50pm\n" +
                "BRDA 3332Y\n" +
                "24 / 24\n" +
                "PHYS 3L\n" +
                "R\n" +
                "4:00pm - 6:50pm\n" +
                "BRDA 2316B\n" +
                "24 / 24\n" +
                "PHYS 6A\n" +
                "M W F\n" +
                "10:00am - 10:50am\n" +
                "BRDA 1610\n" +
                "260 / 286\n" +
                "PHYS 6A\n" +
                "T R\n" +
                "11:00am - 12:15pm\n" +
                "BRDA 1640\n" +
                "90 / 92\n" +
                "PHYS 6A\n" +
                "M W F\n" +
                "12:00pm - 12:50pm\n" +
                "BRDA 1610\n" +
                "162 / 280\n" +
                "PHYS 6AL\n" +
                "M\n" +
                "1:00pm - 2:50pm\n" +
                "BRDA 2324A\n" +
                "24 / 24\n" +
                "PHYS 6AL\n" +
                "M\n" +
                "1:00pm - 2:50pm\n" +
                "BRDA 2324B\n" +
                "24 / 24\n" +
                "PHYS 6AL\n" +
                "M\n" +
                "3:00pm - 4:50pm\n" +
                "BRDA 2324A\n" +
                "24 / 24\n" +
                "PHYS 6AL\n" +
                "M\n" +
                "3:00pm - 4:50pm\n" +
                "BRDA 2324B\n" +
                "24 / 24\n" +
                "PHYS 6AL\n" +
                "M\n" +
                "5:00pm - 6:50pm\n" +
                "BRDA 2324A\n" +
                "24 / 24\n" +
                "PHYS 6AL\n" +
                "M\n" +
                "5:00pm - 6:50pm\n" +
                "BRDA 2324B\n" +
                "12 / 24\n" +
                "PHYS 6AL\n" +
                "T\n" +
                "1:00pm - 2:50pm\n" +
                "BRDA 2324A\n" +
                "24 / 24\n" +
                "PHYS 6AL\n" +
                "T\n" +
                "1:00pm - 3:50pm\n" +
                "BRDA 2324B\n" +
                "24 / 24\n" +
                "PHYS 6AL\n" +
                "T\n" +
                "3:00pm - 4:50pm\n" +
                "BRDA 2324A\n" +
                "24 / 24\n" +
                "PHYS 6AL\n" +
                "T\n" +
                "3:00pm - 4:50pm\n" +
                "BRDA 2324B\n" +
                "24 / 24\n" +
                "PHYS 6AL\n" +
                "T\n" +
                "5:00pm - 6:50pm\n" +
                "BRDA 2324A\n" +
                "22 / 24\n" +
                "PHYS 6AL\n" +
                "T\n" +
                "5:00pm - 6:50pm\n" +
                "BRDA 2324B\n" +
                "7 / 24\n" +
                "PHYS 6AL\n" +
                "W\n" +
                "1:00pm - 2:50pm\n" +
                "BRDA 2324A\n" +
                "24 / 24\n" +
                "PHYS 6AL\n" +
                "W\n" +
                "1:00pm - 2:50pm\n" +
                "BRDA 2324B\n" +
                "24 / 24\n" +
                "PHYS 6AL\n" +
                "W\n" +
                "3:00pm - 4:50pm\n" +
                "BRDA 2324A\n" +
                "20 / 24\n" +
                "PHYS 6AL\n" +
                "W\n" +
                "3:00pm - 4:50pm\n" +
                "BRDA 2324B\n" +
                "2 / 24\n" +
                "PHYS 6AL\n" +
                "W\n" +
                "5:00pm - 6:50pm\n" +
                "BRDA 2324A\n" +
                "20 / 24\n" +
                "PHYS 6AL\n" +
                "W\n" +
                "5:00pm - 6:50pm\n" +
                "BRDA 2324B\n" +
                "4 / 24\n" +
                "PHYS 6AL\n" +
                "R\n" +
                "1:00pm - 2:50pm\n" +
                "BRDA 2324A\n" +
                "24 / 24\n" +
                "PHYS 6AL\n" +
                "R\n" +
                "1:00pm - 2:50pm\n" +
                "BRDA 2324B\n" +
                "24 / 24\n" +
                "PHYS 6AL\n" +
                "R\n" +
                "3:00pm - 4:50pm\n" +
                "BRDA 2324A\n" +
                "24 / 24\n" +
                "PHYS 6AL\n" +
                "R\n" +
                "3:00pm - 4:50pm\n" +
                "BRDA 2324B\n" +
                "24 / 24\n" +
                "PHYS 6AL\n" +
                "R\n" +
                "5:00pm - 6:50pm\n" +
                "BRDA 2324A\n" +
                "9 / 24\n" +
                "PHYS 6AL\n" +
                "R\n" +
                "5:00pm - 6:50pm\n" +
                "BRDA 2324B\n" +
                "2 / 24\n" +
                "PHYS 6C\n" +
                "T R\n" +
                "9:30 am - 10:45am\n" +
                "BRDA 1610\n" +
                "110 / 108\n" +
                "PHYS 6C\n" +
                "M W F\n" +
                "11:00am - 11:50am\n" +
                "BRDA 1610\n" +
                "286 / 286\n" +
                "PHYS 6CL\n" +
                "T\n" +
                "1:00pm - 2:50pm\n" +
                "BRDA 2334A\n" +
                "24 / 24\n" +
                "PHYS 6CL\n" +
                "T\n" +
                "1:00pm - 2:50pm\n" +
                "BRDA 2334B\n" +
                "24 / 24\n" +
                "PHYS 6CL\n" +
                "T\n" +
                "3:00pm - 4:50pm\n" +
                "BRDA 2334A\n" +
                "24 / 24\n" +
                "PHYS 6CL\n" +
                "T\n" +
                "3:00pm - 4:50pm\n" +
                "BRDA 2334B\n" +
                "24 / 24\n" +
                "PHYS 6CL\n" +
                "T\n" +
                "5:00pm - 6:50pm\n" +
                "BRDA 2334A\n" +
                "24 / 24\n" +
                "PHYS 6CL\n" +
                "T\n" +
                "5:00pm - 6:50pm\n" +
                "BRDA 2334B\n" +
                "24 / 24\n" +
                "PHYS 6CL\n" +
                "W\n" +
                "1:00pm - 2:50pm\n" +
                "BRDA 2334A\n" +
                "24 / 24\n" +
                "PHYS 6CL\n" +
                "W\n" +
                "1:00pm - 2:50pm\n" +
                "BRDA 2334B\n" +
                "24 / 24\n" +
                "PHYS 6CL\n" +
                "W\n" +
                "3:00pm - 4:50pm\n" +
                "BRDA 2334A\n" +
                "24 / 24\n" +
                "PHYS 6CL\n" +
                "W\n" +
                "3:00pm - 4:50pm\n" +
                "BRDA 2334B\n" +
                "24 / 24\n" +
                "PHYS 6CL\n" +
                "W\n" +
                "5:00pm - 6:50pm\n" +
                "BRDA 2334A\n" +
                "24 / 24\n" +
                "PHYS 6CL\n" +
                "W\n" +
                "5:00pm - 6:50pm\n" +
                "BRDA 2334B\n" +
                "24 / 24\n" +
                "PHYS 6CL\n" +
                "W\n" +
                "7:00pm - 8:50pm\n" +
                "BRDA 2334A\n" +
                "24 / 24\n" +
                "PHYS 6CL\n" +
                "W\n" +
                "7:00pm - 8:50pm\n" +
                "BRDA 2334B\n" +
                "24 / 24\n" +
                "PHYS 13AH\n" +
                "W\n" +
                "2:00pm - 2:50pm\n" +
                "BRDA 3314\n" +
                "6 / 6\n" +
                "PHYS 13AH\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "0 / 0\n" +
                "PHYS 13AH\n" +
                "W\n" +
                "3:00pm - 5:50pm\n" +
                "BRDA 3314\n" +
                "3 / 3\n" +
                "PHYS 13AH\n" +
                "F\n" +
                "3:00pm - 5:50pm\n" +
                "BRDA 3314\n" +
                "3 / 3\n" +
                "PHYS CS 15A\n" +
                "W\n" +
                "2:00pm - 2:50pm\n" +
                "BRDA 3314\n" +
                "13 / 13\n" +
                "PHYS CS 15A\n" +
                "W\n" +
                "3:00pm - 5:50pm\n" +
                "BRDA 3314\n" +
                "13 / 13\n" +
                "PHYS CS 15A\n" +
                "W\n" +
                "2:00pm - 2:50pm\n" +
                "BRDA 3314\n" +
                "7 / 13\n" +
                "PHYS CS 15A\n" +
                "F\n" +
                "3:00pm - 5:50pm\n" +
                "BRDA 3314\n" +
                "7 / 13\n" +
                "PHYS 20\n" +
                "M W\n" +
                "11:00am - 12:15pm\n" +
                "BRDA 1640\n" +
                "0 / 0\n" +
                "PHYS 20\n" +
                "T\n" +
                "10:00am - 10:50am\n" +
                "PHELP3519\n" +
                "0 / 31\n" +
                "PHYS 20\n" +
                "T\n" +
                "9:00 am - 9:50 am\n" +
                "387 104\n" +
                "0 / 31\n" +
                "PHYS 20\n" +
                "T\n" +
                "12:00pm - 12:50pm\n" +
                "PHELP2536\n" +
                "0 / 31\n" +
                "PHYS 20\n" +
                "M W\n" +
                "12:30pm - 1:45pm\n" +
                "BRDA 1640\n" +
                "0 / 0\n" +
                "PHYS 20\n" +
                "R\n" +
                "2:00pm - 2:50pm\n" +
                "PHELP3523\n" +
                "0 / 47\n" +
                "PHYS 20\n" +
                "R\n" +
                "12:00pm - 12:50pm\n" +
                "PHELP2536\n" +
                "0 / 47\n" +
                "PHYS 23\n" +
                "T R\n" +
                "11:00am - 12:15pm\n" +
                "BRDA 1610\n" +
                "91 / 135\n" +
                "PHYS 23\n" +
                "F\n" +
                "9:00 am - 9:50 am\n" +
                "PHELP1508\n" +
                "16 / 45\n" +
                "PHYS 23\n" +
                "R\n" +
                "4:00pm - 4:50pm\n" +
                "PHELP1508\n" +
                "32 / 45\n" +
                "PHYS 23\n" +
                "M\n" +
                "2:00pm - 2:50pm\n" +
                "PHELP2524\n" +
                "43 / 45\n" +
                "PHYS CS 31\n" +
                "T R\n" +
                "3:30pm - 4:50pm\n" +
                "387 104\n" +
                "0 / 26\n" +
                "PHYS CS 31\n" +
                "W\n" +
                "1:00pm - 2:50pm\n" +
                "387 104\n" +
                "0 / 26\n" +
                "PHYS CS 31\n" +
                "W\n" +
                "3:00pm - 4:50pm\n" +
                "387 104\n" +
                "0 / 26\n" +
                "PHYS CS 34\n" +
                "T R\n" +
                "3:30pm - 4:50pm\n" +
                "387 103\n" +
                "9 / 26\n" +
                "PHYS CS 34\n" +
                "R\n" +
                "1:00pm - 2:50pm\n" +
                "387 103\n" +
                "9 / 26\n" +
                "PHYS CS 34\n" +
                "R\n" +
                "10:00am - 11:50am\n" +
                "387 103\n" +
                "9 / 26\n" +
                "PHYS 98\n" +
                "M\n" +
                "2:00pm - 3:50pm\n" +
                "HSSB 1223\n" +
                "0 / 25\n" +
                "PHYS 99\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "0 / 16\n" +
                "PHYS 102\n" +
                "T R\n" +
                "2:00pm - 3:15pm\n" +
                "BRDA 1640\n" +
                "89 / 93\n" +
                "PHYS 102\n" +
                "T\n" +
                "4:00pm - 4:50pm\n" +
                "PHELP1440\n" +
                "31 / 31\n" +
                "PHYS 102\n" +
                "M\n" +
                "4:00pm - 4:50pm\n" +
                "PHELP1444\n" +
                "31 / 31\n" +
                "PHYS 102\n" +
                "T\n" +
                "5:00pm - 5:50pm\n" +
                "PHELP1440\n" +
                "27 / 31\n" +
                "PHYS 103\n" +
                "T R\n" +
                "9:30 am - 10:45am\n" +
                "TD-W 1701\n" +
                "65 / 138\n" +
                "PHYS 103\n" +
                "M\n" +
                "4:00pm - 4:50pm\n" +
                "PHELP3526\n" +
                "36 / 47\n" +
                "PHYS 103\n" +
                "R\n" +
                "4:00pm - 4:50pm\n" +
                "PHELP3519\n" +
                "9 / 45\n" +
                "PHYS 103\n" +
                "M\n" +
                "5:00pm - 5:50pm\n" +
                "PHELP3526\n" +
                "20 / 46\n" +
                "PHYS 110A\n" +
                "M W\n" +
                "11:00am - 12:15pm\n" +
                "BUCHN1920\n" +
                "109 / 135\n" +
                "PHYS 110A\n" +
                "T\n" +
                "8:00 am - 8:50 am\n" +
                "PHELP1508\n" +
                "21 / 45\n" +
                "PHYS 110A\n" +
                "R\n" +
                "5:00pm - 5:50pm\n" +
                "PHELP2516\n" +
                "45 / 45\n" +
                "PHYS 110A\n" +
                "R\n" +
                "6:00pm - 6:50pm\n" +
                "PHELP2516\n" +
                "43 / 45\n" +
                "PHYS 115A\n" +
                "M W\n" +
                "12:30pm - 1:45pm\n" +
                "EMBARHALL\n" +
                "88 / 140\n" +
                "PHYS 115A\n" +
                "F\n" +
                "1:00pm - 1:50pm\n" +
                "PHELP1508\n" +
                "45 / 45\n" +
                "PHYS 115A\n" +
                "F\n" +
                "2:00pm - 2:50pm\n" +
                "PHELP2524\n" +
                "35 / 50\n" +
                "PHYS 115A\n" +
                "F\n" +
                "3:00pm - 3:50pm\n" +
                "PHELP3519\n" +
                "8 / 45\n" +
                "PHYS 119A\n" +
                "T R\n" +
                "9:30 am - 10:45am\n" +
                "BUCHN1930\n" +
                "88 / 100\n" +
                "PHYS 119A\n" +
                "F\n" +
                "2:00pm - 2:50pm\n" +
                "PHELP1440\n" +
                "35 / 35\n" +
                "PHYS 119A\n" +
                "F\n" +
                "3:00pm - 3:50pm\n" +
                "PHELP1444\n" +
                "23 / 35\n" +
                "PHYS 119A\n" +
                "F\n" +
                "1:00pm - 1:50pm\n" +
                "PHELP1440\n" +
                "30 / 30\n" +
                "PHYS 120\n" +
                "T R\n" +
                "9:30 am - 10:45am\n" +
                "PHELP2514\n" +
                "17 / 35\n" +
                "PHYS 120\n" +
                "T\n" +
                "11:00am - 11:50am\n" +
                "PHELP2524\n" +
                "17 / 35\n" +
                "PHYS 123A\n" +
                "T R\n" +
                "12:30pm - 1:45pm\n" +
                "PHELP2516\n" +
                "38 / 45\n" +
                "PHYS 123A\n" +
                "F\n" +
                "1:00pm - 1:50pm\n" +
                "PHELP3523\n" +
                "38 / 45\n" +
                "PHYS 127AL\n" +
                "T R\n" +
                "11:00am - 12:15pm\n" +
                "PHELP1425\n" +
                "16 / 60\n" +
                "PHYS 127AL\n" +
                "T\n" +
                "1:00pm - 5:50pm\n" +
                "BRDA 3233\n" +
                "7 / 15\n" +
                "PHYS 127AL\n" +
                "R\n" +
                "1:00pm - 5:50pm\n" +
                "BRDA 3233\n" +
                "4 / 15\n" +
                "PHYS 127AL\n" +
                "M\n" +
                "1:00pm - 5:50pm\n" +
                "BRDA 3233\n" +
                "1 / 15\n" +
                "PHYS 127AL\n" +
                "W\n" +
                "1:00pm - 5:50pm\n" +
                "BRDA 3233\n" +
                "4 / 15\n" +
                "PHYS 128AL\n" +
                "M W\n" +
                "1:00pm - 5:50pm\n" +
                "BRDA 3223\n" +
                "24 / 24\n" +
                "PHYS 128AL\n" +
                "T R\n" +
                "1:00pm - 5:50pm\n" +
                "BRDA 3223\n" +
                "9 / 24\n" +
                "PHYS 132\n" +
                "M W F\n" +
                "2:00pm - 2:50pm\n" +
                "PHELP3505\n" +
                "31 / 35\n" +
                "PHYS 132\n" +
                "F\n" +
                "11:00am - 11:50am\n" +
                "PHELP1444\n" +
                "31 / 35\n" +
                "PHYS 142L\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "0 / 24\n" +
                "PHYS 143L\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "0 / 24\n" +
                "PHYS 144L\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "0 / 24\n" +
                "PHYS 145L\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "0 / 24\n" +
                "PHYS 160J\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "0 / 0\n" +
                "PHYS 160K\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "0 / 0\n" +
                "PHYS 198\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "0 / 100\n" +
                "PHYS 199\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "0 / 40\n" +
                "PHYS 199RA\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "0 / 5\n" +
                "PHYS CS 15A\n" +
                "W\n" +
                "2:00pm - 2:50pm\n" +
                "BRDA 3314\n" +
                "13 / 13\n" +
                "PHYS CS 15A\n" +
                "W\n" +
                "3:00pm - 5:50pm\n" +
                "BRDA 3314\n" +
                "13 / 13\n" +
                "PHYS CS 15A\n" +
                "W\n" +
                "2:00pm - 2:50pm\n" +
                "BRDA 3314\n" +
                "7 / 13\n" +
                "PHYS CS 15A\n" +
                "F\n" +
                "3:00pm - 5:50pm\n" +
                "BRDA 3314\n" +
                "7 / 13\n" +
                "PHYS CS 31\n" +
                "T R\n" +
                "3:30pm - 4:50pm\n" +
                "387 104\n" +
                "0 / 26\n" +
                "PHYS CS 31\n" +
                "W\n" +
                "1:00pm - 2:50pm\n" +
                "387 104\n" +
                "0 / 26\n" +
                "PHYS CS 31\n" +
                "W\n" +
                "3:00pm - 4:50pm\n" +
                "387 104\n" +
                "0 / 26\n" +
                "PHYS CS 34\n" +
                "T R\n" +
                "3:30pm - 4:50pm\n" +
                "387 103\n" +
                "9 / 26\n" +
                "PHYS CS 34\n" +
                "R\n" +
                "1:00pm - 2:50pm\n" +
                "387 103\n" +
                "9 / 26\n" +
                "PHYS CS 34\n" +
                "R\n" +
                "10:00am - 11:50am\n" +
                "387 103\n" +
                "9 / 26\n" +
                "POL S 1\n" +
                "M W F\n" +
                "10:00am - 10:50am\n" +
                "HFH 1104\n" +
                "35 / 35\n" +
                "POL S 1\n" +
                "T\n" +
                "11:00am - 11:50am\n" +
                "PHELP2532\n" +
                "4 / 20\n" +
                "POL S 1\n" +
                "M\n" +
                "2:00pm - 2:50pm\n" +
                "BUCHN1934\n" +
                "5 / 20\n" +
                "POL S 1\n" +
                "M\n" +
                "3:00pm - 3:50pm\n" +
                "BUCHN1934\n" +
                "3 / 20\n" +
                "POL S 1\n" +
                "M\n" +
                "5:00pm - 5:50pm\n" +
                "PHELP2532\n" +
                "2 / 20\n" +
                "POL S 1\n" +
                "T\n" +
                "3:00pm - 3:50pm\n" +
                "HSSB 1215\n" +
                "9 / 20\n" +
                "POL S 1\n" +
                "T\n" +
                "4:00pm - 4:50pm\n" +
                "PHELP2532\n" +
                "3 / 20\n" +
                "POL S 1\n" +
                "M\n" +
                "6:00pm - 6:50pm\n" +
                "PHELP2532\n" +
                "1 / 20\n" +
                "POL S 1\n" +
                "M\n" +
                "12:00pm - 12:50pm\n" +
                "PHELP1444\n" +
                "2 / 20\n" +
                "POL S 1\n" +
                "M\n" +
                "1:00pm - 1:50pm\n" +
                "LSB 1101\n" +
                "6 / 20\n" +
                "POL S 7\n" +
                "T R\n" +
                "11:00am - 12:15pm\n" +
                "EMBARHALL\n" +
                "36 / 55\n" +
                "POL S 7\n" +
                "M\n" +
                "9:00 am - 9:50 am\n" +
                "HSSB 2201\n" +
                "7 / 20\n" +
                "POL S 7\n" +
                "M\n" +
                "10:00am - 10:50am\n" +
                "HSSB 4202\n" +
                "6 / 20\n" +
                "POL S 7\n" +
                "M\n" +
                "11:00am - 11:50am\n" +
                "HSSB 2201\n" +
                "8 / 20\n" +
                "POL S 7\n" +
                "M\n" +
                "1:00pm - 1:50pm\n" +
                "GIRV 2120\n" +
                "7 / 20\n" +
                "POL S 7\n" +
                "M\n" +
                "2:00pm - 2:50pm\n" +
                "HSSB 1215\n" +
                "5 / 20\n" +
                "POL S 7\n" +
                "M\n" +
                "3:00pm - 3:50pm\n" +
                "HSSB 3202\n" +
                "2 / 20\n" +
                "POL S 7\n" +
                "F\n" +
                "9:00 am - 9:50 am\n" +
                "SH 1609\n" +
                "0 / 20\n" +
                "POL S 7\n" +
                "F\n" +
                "10:00am - 10:50am\n" +
                "SH 1609\n" +
                "0 / 20\n" +
                "POL S 7\n" +
                "F\n" +
                "11:00am - 11:50am\n" +
                "SH 1609\n" +
                "1 / 20\n" +
                "POL S 12\n" +
                "M W F\n" +
                "11:00am - 11:50am\n" +
                "HFH 1104\n" +
                "18 / 50\n" +
                "POL S 12\n" +
                "M\n" +
                "12:00pm - 12:50pm\n" +
                "HSSB 2201\n" +
                "8 / 20\n" +
                "POL S 12\n" +
                "M\n" +
                "3:00pm - 3:50pm\n" +
                "HSSB 4201\n" +
                "1 / 20\n" +
                "POL S 12\n" +
                "M\n" +
                "2:00pm - 2:50pm\n" +
                "HSSB 1237\n" +
                "1 / 20\n" +
                "POL S 12\n" +
                "T\n" +
                "1:00pm - 1:50pm\n" +
                "PHELP1425\n" +
                "2 / 20\n" +
                "POL S 12\n" +
                "M\n" +
                "1:00pm - 1:50pm\n" +
                "GIRV 2127\n" +
                "1 / 20\n" +
                "POL S 12\n" +
                "M\n" +
                "4:00pm - 4:50pm\n" +
                "HSSB 4201\n" +
                "1 / 20\n" +
                "POL S 12\n" +
                "M\n" +
                "5:00pm - 5:50pm\n" +
                "PHELP2536\n" +
                "1 / 20\n" +
                "POL S 12\n" +
                "F\n" +
                "8:00 am - 8:50 am\n" +
                "SH 1609\n" +
                "1 / 20\n" +
                "POL S 12\n" +
                "F\n" +
                "9:00 am - 9:50 am\n" +
                "HSSB 1215\n" +
                "2 / 20\n" +
                "POL S 15\n" +
                "M W\n" +
                "3:30pm - 4:45pm\n" +
                "TD-W 1701\n" +
                "60 / 90\n" +
                "POL S 15\n" +
                "T\n" +
                "11:00am - 11:50am\n" +
                "PHELP1525\n" +
                "12 / 15\n" +
                "POL S 15\n" +
                "W\n" +
                "11:00am - 11:50am\n" +
                "PSY-E1806\n" +
                "10 / 15\n" +
                "POL S 15\n" +
                "W\n" +
                "12:00pm - 12:50pm\n" +
                "PHELP1518\n" +
                "11 / 15\n" +
                "POL S 15\n" +
                "T\n" +
                "12:00pm - 12:50pm\n" +
                "PSY-E1806\n" +
                "5 / 15\n" +
                "POL S 15\n" +
                "W\n" +
                "1:00pm - 1:50pm\n" +
                "PHELP1525\n" +
                "12 / 15\n" +
                "POL S 15\n" +
                "T\n" +
                "1:00pm - 1:50pm\n" +
                "PSY-E1806\n" +
                "10 / 15\n" +
                "POL S 99\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "0 / 20\n" +
                "POL S 106BP\n" +
                "T R\n" +
                "9:30 am - 10:45am\n" +
                "ARTS 1356\n" +
                "6 / 54\n" +
                "POL S 106BP\n" +
                "F\n" +
                "2:00pm - 2:50pm\n" +
                "HSSB 1223\n" +
                "6 / 20\n" +
                "POL S 106BP\n" +
                "F\n" +
                "3:00pm - 3:50pm\n" +
                "HSSB 1223\n" +
                "0 / 20\n" +
                "POL S 106BP\n" +
                "F\n" +
                "4:00pm - 4:50pm\n" +
                "HSSB 1223\n" +
                "0 / 20\n" +
                "POL S 106DM\n" +
                "M W\n" +
                "2:00pm - 3:15pm\n" +
                "PHELP1508\n" +
                "40 / 40\n" +
                "POL S 110PC\n" +
                "M W\n" +
                "12:30pm - 1:45pm\n" +
                "PHELP1425\n" +
                "27 / 60\n" +
                "POL S 110PC\n" +
                "M\n" +
                "2:00pm - 2:50pm\n" +
                "PHELP2532\n" +
                "20 / 20\n" +
                "POL S 110PC\n" +
                "M\n" +
                "3:00pm - 3:50pm\n" +
                "HSSB 1232\n" +
                "4 / 20\n" +
                "POL S 110PC\n" +
                "M\n" +
                "4:00pm - 4:50pm\n" +
                "HSSB 1232\n" +
                "3 / 20\n" +
                "POL S 116\n" +
                "T R\n" +
                "5:00pm - 6:15pm\n" +
                "NH 1111\n" +
                "2 / 40\n" +
                "POL S 124\n" +
                "M W\n" +
                "5:00pm - 6:15pm\n" +
                "NH 1111\n" +
                "40 / 40\n" +
                "POL S 126\n" +
                "T R\n" +
                "2:00pm - 3:15pm\n" +
                "PSYCH1924\n" +
                "90 / 120\n" +
                "POL S 126\n" +
                "T\n" +
                "4:00pm - 4:50pm\n" +
                "PHELP2536\n" +
                "20 / 20\n" +
                "POL S 126\n" +
                "W\n" +
                "9:00 am - 9:50 am\n" +
                "PHELP2536\n" +
                "14 / 20\n" +
                "POL S 126\n" +
                "W\n" +
                "4:00pm - 4:50pm\n" +
                "PHELP1444\n" +
                "20 / 20\n" +
                "POL S 126\n" +
                "T\n" +
                "5:00pm - 5:50pm\n" +
                "TD-W 2600\n" +
                "19 / 20\n" +
                "POL S 126\n" +
                "W\n" +
                "5:00pm - 5:50pm\n" +
                "PHELP2536\n" +
                "9 / 20\n" +
                "POL S 126\n" +
                "T\n" +
                "6:00pm - 6:50pm\n" +
                "PHELP3519\n" +
                "8 / 20\n" +
                "POL S 130\n" +
                "T R\n" +
                "11:00am - 12:15pm\n" +
                "PHELP3505\n" +
                "34 / 60\n" +
                "POL S 137\n" +
                "T R\n" +
                "2:00pm - 3:15pm\n" +
                "PHELP2516\n" +
                "32 / 60\n" +
                "POL S 148A\n" +
                "T R\n" +
                "11:00am - 12:15pm\n" +
                "TD-W 2600\n" +
                "51 / 60\n" +
                "POL S 148A\n" +
                "F\n" +
                "10:00am - 10:50am\n" +
                "HSSB 1206\n" +
                "20 / 20\n" +
                "POL S 148A\n" +
                "F\n" +
                "11:00am - 11:50am\n" +
                "HSSB 1214\n" +
                "17 / 20\n" +
                "POL S 148A\n" +
                "F\n" +
                "12:00pm - 12:50pm\n" +
                "SH 1609\n" +
                "14 / 20\n" +
                "POL S 157\n" +
                "M W\n" +
                "5:00pm - 6:15pm\n" +
                "BUCHN1920\n" +
                "76 / 100\n" +
                "POL S 157\n" +
                "R\n" +
                "12:00pm - 12:50pm\n" +
                "HSSB 2201\n" +
                "20 / 20\n" +
                "POL S 157\n" +
                "R\n" +
                "5:00pm - 5:50pm\n" +
                "PHELP2532\n" +
                "16 / 20\n" +
                "POL S 157\n" +
                "R\n" +
                "2:00pm - 2:50pm\n" +
                "GIRV 1119\n" +
                "20 / 20\n" +
                "POL S 157\n" +
                "R\n" +
                "3:00pm - 3:50pm\n" +
                "SH 1609\n" +
                "7 / 20\n" +
                "POL S 157\n" +
                "F\n" +
                "9:00 am - 9:50 am\n" +
                "HSSB 1223\n" +
                "8 / 20\n" +
                "POL S 157\n" +
                "F\n" +
                "10:00am - 10:50am\n" +
                "HSSB 1211\n" +
                "5 / 20\n" +
                "POL S 160\n" +
                "M W\n" +
                "12:30pm - 1:45pm\n" +
                "PSYCH1902\n" +
                "30 / 35\n" +
                "POL S 160\n" +
                "M\n" +
                "2:00pm - 2:50pm\n" +
                "387 104\n" +
                "12 / 12\n" +
                "POL S 160\n" +
                "M\n" +
                "5:00pm - 5:50pm\n" +
                "SH 1430\n" +
                "9 / 12\n" +
                "POL S 160\n" +
                "M\n" +
                "10:00am - 10:50am\n" +
                "HSSB 4201\n" +
                "9 / 12\n" +
                "POL S 170\n" +
                "T R\n" +
                "9:30 am - 10:45am\n" +
                "PHELP1160\n" +
                "69 / 70\n" +
                "POL S 170\n" +
                "T\n" +
                "2:00pm - 2:50pm\n" +
                "PHELP2532\n" +
                "25 / 25\n" +
                "POL S 170\n" +
                "T\n" +
                "5:00pm - 5:50pm\n" +
                "PHELP2532\n" +
                "20 / 20\n" +
                "POL S 170\n" +
                "T\n" +
                "2:00pm - 2:50pm\n" +
                "PHELP3523\n" +
                "24 / 25\n" +
                "POL S 175\n" +
                "M W F\n" +
                "11:00am - 11:50am\n" +
                "IV THEA2\n" +
                "60 / 60\n" +
                "POL S 175\n" +
                "F\n" +
                "12:00pm - 12:50pm\n" +
                "HSSB 1210\n" +
                "10 / 10\n" +
                "POL S 175\n" +
                "F\n" +
                "1:00pm - 1:50pm\n" +
                "HSSB 4202\n" +
                "10 / 10\n" +
                "POL S 175\n" +
                "F\n" +
                "2:00pm - 2:50pm\n" +
                "GIRV 1108\n" +
                "11 / 10\n" +
                "POL S 175\n" +
                "F\n" +
                "3:00pm - 3:50pm\n" +
                "GIRV 1108\n" +
                "8 / 10\n" +
                "POL S 175\n" +
                "M\n" +
                "9:00 am - 9:50 am\n" +
                "GIRV 1108\n" +
                "10 / 10\n" +
                "POL S 175\n" +
                "M\n" +
                "10:00am - 10:50am\n" +
                "HSSB 2201\n" +
                "11 / 10\n" +
                "POL S 186\n" +
                "M W\n" +
                "3:30pm - 4:45pm\n" +
                "HFH 1104\n" +
                "76 / 75\n" +
                "POL S 186\n" +
                "R\n" +
                "6:00pm - 6:50pm\n" +
                "GIRV 1108\n" +
                "12 / 12\n" +
                "POL S 186\n" +
                "R\n" +
                "8:00 am - 8:50 am\n" +
                "HSSB 3202\n" +
                "13 / 13\n" +
                "POL S 186\n" +
                "F\n" +
                "12:00pm - 12:50pm\n" +
                "HSSB 4202\n" +
                "13 / 12\n" +
                "POL S 186\n" +
                "R\n" +
                "5:00pm - 5:50pm\n" +
                "HSSB 3202\n" +
                "13 / 13\n" +
                "POL S 186\n" +
                "R\n" +
                "9:00 am - 9:50 am\n" +
                "HSSB 2201\n" +
                "12 / 12\n" +
                "POL S 186\n" +
                "R\n" +
                "4:00pm - 4:50pm\n" +
                "HSSB 4201\n" +
                "13 / 13\n" +
                "POL S 189\n" +
                "M W\n" +
                "9:30 am - 10:45am\n" +
                "PHELP2516\n" +
                "25 / 60\n" +
                "POL S 189\n" +
                "M\n" +
                "1:00pm - 1:50pm\n" +
                "PHELP2532\n" +
                "15 / 20\n" +
                "POL S 189\n" +
                "M\n" +
                "2:00pm - 2:50pm\n" +
                "HSSB 2251\n" +
                "7 / 20\n" +
                "POL S 189\n" +
                "M\n" +
                "3:00pm - 3:50pm\n" +
                "HSSB 4202\n" +
                "3 / 20\n" +
                "POL S 192\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "0 / 10\n" +
                "POL S 196\n" +
                "W\n" +
                "2:00pm - 4:50pm\n" +
                "ELLSN3814\n" +
                "6 / 15\n" +
                "POL S 197A\n" +
                "F\n" +
                "1:00pm - 3:50pm\n" +
                "ELLSN3814\n" +
                "0 / 30\n" +
                "POL S 199\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "0 / 50\n" +
                "POL S 199RA\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "0 / 10\n" +
                "PORT 1\n" +
                "MTWR\n" +
                "10:00am - 10:50am\n" +
                "GIRV 2127\n" +
                "5 / 25\n" +
                "PORT 1\n" +
                "MTWR\n" +
                "11:00am - 11:50am\n" +
                "GIRV 2115\n" +
                "9 / 13\n" +
                "PORT 4\n" +
                "MTWR\n" +
                "1:00pm - 1:50pm\n" +
                "GIRV 2129\n" +
                "12 / 30\n" +
                "PORT 102A\n" +
                "T R\n" +
                "12:30pm - 1:45pm\n" +
                "HSSB 3202\n" +
                "1 / 20\n" +
                "PORT 105A\n" +
                "T R\n" +
                "2:00pm - 3:15pm\n" +
                "GIRV 1108\n" +
                "1 / 20\n" +
                "PORT 195\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "0 / 10\n" +
                "PORT 199\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "0 / 5\n" +
                "PSY 1\n" +
                "T R\n" +
                "12:30pm - 1:45pm\n" +
                "CAMPBHALL\n" +
                "138 / 210\n" +
                "PSY 1\n" +
                "M\n" +
                "8:00 am - 9:15 am\n" +
                "GIRV 2129\n" +
                "2 / 33\n" +
                "PSY 1\n" +
                "M\n" +
                "9:30 am - 10:45am\n" +
                "PHELP1508\n" +
                "5 / 33\n" +
                "PSY 1\n" +
                "M\n" +
                "11:00am - 12:15pm\n" +
                "GIRV 1115\n" +
                "14 / 33\n" +
                "PSY 1\n" +
                "M\n" +
                "3:30pm - 4:45pm\n" +
                "387 103\n" +
                "6 / 33\n" +
                "PSY 1\n" +
                "M\n" +
                "5:00pm - 6:15pm\n" +
                "GIRV 1112\n" +
                "4 / 33\n" +
                "PSY 1\n" +
                "M\n" +
                "6:30pm - 7:45pm\n" +
                "GIRV 1112\n" +
                "1 / 33\n" +
                "PSY 1\n" +
                "T\n" +
                "8:00 am - 9:15 am\n" +
                "387 103\n" +
                "2 / 33\n" +
                "PSY 1\n" +
                "T\n" +
                "9:30 am - 10:45am\n" +
                "GIRV 1119\n" +
                "5 / 33\n" +
                "PSY 1\n" +
                "T\n" +
                "2:00pm - 3:15pm\n" +
                "GIRV 1115\n" +
                "24 / 33\n" +
                "PSY 1\n" +
                "T\n" +
                "3:30pm - 4:45pm\n" +
                "GIRV 2119\n" +
                "7 / 33\n" +
                "PSY 1\n" +
                "W\n" +
                "3:30pm - 4:45pm\n" +
                "387 103\n" +
                "9 / 33\n" +
                "PSY 1\n" +
                "T\n" +
                "5:00pm - 6:15pm\n" +
                "GIRV 2115\n" +
                "4 / 33\n" +
                "PSY 1\n" +
                "W\n" +
                "8:00 am - 9:15 am\n" +
                "GIRV 2129\n" +
                "1 / 33\n" +
                "PSY 1\n" +
                "M\n" +
                "12:00pm - 12:50pm\n" +
                "GIRV 2108\n" +
                "11 / 33\n" +
                "PSY 1\n" +
                "W\n" +
                "11:00am - 12:15pm\n" +
                "GIRV 1115\n" +
                "11 / 33\n" +
                "PSY 1\n" +
                "W\n" +
                "5:00pm - 6:15pm\n" +
                "387 104\n" +
                "2 / 33\n" +
                "PSY 1\n" +
                "W\n" +
                "6:30pm - 7:45pm\n" +
                "NH 1105\n" +
                "1 / 33\n" +
                "PSY 1\n" +
                "R\n" +
                "8:00 am - 9:15 am\n" +
                "GIRV 2123\n" +
                "3 / 33\n" +
                "PSY 1\n" +
                "R\n" +
                "9:30 am - 10:45am\n" +
                "GIRV 1119\n" +
                "1 / 33\n" +
                "PSY 1\n" +
                "R\n" +
                "11:00am - 12:15pm\n" +
                "GIRV 1112\n" +
                "6 / 33\n" +
                "PSY 1\n" +
                "M\n" +
                "12:30pm - 1:45pm\n" +
                "NH 1105\n" +
                "5 / 33\n" +
                "PSY 1\n" +
                "R\n" +
                "3:30pm - 4:45pm\n" +
                "GIRV 2116\n" +
                "3 / 33\n" +
                "PSY 1\n" +
                "R\n" +
                "5:00pm - 6:15pm\n" +
                "GIRV 1112\n" +
                "1 / 33\n" +
                "PSY 1\n" +
                "R\n" +
                "6:30pm - 7:45pm\n" +
                "GIRV 1116\n" +
                "2 / 33\n" +
                "PSY 1\n" +
                "F\n" +
                "8:00 am - 9:15 am\n" +
                "387 104\n" +
                "0 / 33\n" +
                "PSY 1\n" +
                "F\n" +
                "11:00am - 12:15pm\n" +
                "GIRV 2129\n" +
                "0 / 33\n" +
                "PSY 1\n" +
                "T\n" +
                "11:00am - 12:15pm\n" +
                "GIRV 1112\n" +
                "7 / 33\n" +
                "PSY 1\n" +
                "F\n" +
                "12:30pm - 1:45pm\n" +
                "GIRV 2129\n" +
                "1 / 33\n" +
                "PSY 3\n" +
                "T R\n" +
                "9:30 am - 10:45am\n" +
                "CHEM 1179\n" +
                "71 / 265\n" +
                "PSY 5\n" +
                "M W\n" +
                "3:30pm - 4:45pm\n" +
                "BUCHN1940\n" +
                "42 / 99\n" +
                "PSY 5\n" +
                "F\n" +
                "10:00am - 11:50am\n" +
                "PSY-E2822\n" +
                "4 / 32\n" +
                "PSY 5\n" +
                "R\n" +
                "2:00pm - 3:50pm\n" +
                "PSY-E1805\n" +
                "21 / 32\n" +
                "PSY 5\n" +
                "F\n" +
                "12:00pm - 1:50pm\n" +
                "PSY-E1805\n" +
                "6 / 32\n" +
                "PSY 5\n" +
                "R\n" +
                "4:00pm - 5:50pm\n" +
                "PSY-E1805\n" +
                "5 / 32\n" +
                "PSY 5\n" +
                "R\n" +
                "12:00pm - 1:50pm\n" +
                "PSY-E1805\n" +
                "6 / 32\n" +
                "PSY 7\n" +
                "T R\n" +
                "11:00am - 12:15pm\n" +
                "BUCHN1910\n" +
                "103 / 220\n" +
                "PSY 90A\n" +
                "M\n" +
                "3:30pm - 5:20pm\n" +
                "PSY-E2822\n" +
                "0 / 25\n" +
                "PSY 98\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "0 / 1\n" +
                "PSY 99\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "0 / 1\n" +
                "PSY 99P\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "0 / 1\n" +
                "PSY 102\n" +
                "W\n" +
                "5:00pm - 7:50pm\n" +
                "CHEM 1179\n" +
                "142 / 300\n" +
                "PSY 105\n" +
                "T R\n" +
                "12:30pm - 1:45pm\n" +
                "CHEM 1179\n" +
                "158 / 300\n" +
                "PSY 108\n" +
                "M W\n" +
                "3:30pm - 4:45pm\n" +
                "BRDA 1610\n" +
                "87 / 250\n" +
                "PSY 110A\n" +
                "M W\n" +
                "2:00pm - 3:15pm\n" +
                "TD-W 1701\n" +
                "106 / 150\n" +
                "PSY 111\n" +
                "M W\n" +
                "3:30pm - 4:45pm\n" +
                "LSB 1001\n" +
                "64 / 159\n" +
                "PSY 112L\n" +
                "M\n" +
                "10:00am - 11:50am\n" +
                "PSY-E3834\n" +
                "30 / 30\n" +
                "PSY 112L\n" +
                "W\n" +
                "3:00pm - 5:50pm\n" +
                "PSY-E2822\n" +
                "15 / 15\n" +
                "PSY 112L\n" +
                "W\n" +
                "6:00pm - 8:50pm\n" +
                "PSY-E2822\n" +
                "15 / 15\n" +
                "PSY 117\n" +
                "M W\n" +
                "12:30pm - 1:45pm\n" +
                "NH 1006\n" +
                "89 / 132\n" +
                "PSY 120L\n" +
                "M\n" +
                "5:00pm - 6:50pm\n" +
                "PSYCH1924\n" +
                "120 / 120\n" +
                "PSY 120L\n" +
                "W\n" +
                "12:00pm - 3:00pm\n" +
                "PSY-E1805\n" +
                "20 / 20\n" +
                "PSY 120L\n" +
                "T\n" +
                "3:30pm - 6:30pm\n" +
                "PSY-E1805\n" +
                "20 / 20\n" +
                "PSY 120L\n" +
                "W\n" +
                "3:30pm - 6:30pm\n" +
                "PSY-E1805\n" +
                "20 / 20\n" +
                "PSY 120L\n" +
                "T\n" +
                "3:30pm - 6:30pm\n" +
                "PHELP1513\n" +
                "20 / 20\n" +
                "PSY 120L\n" +
                "W\n" +
                "9:30 am - 12:30pm\n" +
                "PHELP1530\n" +
                "20 / 20\n" +
                "PSY 120L\n" +
                "W\n" +
                "3:30pm - 6:30pm\n" +
                "PHELP1513\n" +
                "20 / 20\n" +
                "PSY 134\n" +
                "T R\n" +
                "3:30pm - 4:45pm\n" +
                "TD-W 1701\n" +
                "65 / 165\n" +
                "PSY 138\n" +
                "T R\n" +
                "2:00pm - 3:15pm\n" +
                "IV THEA2\n" +
                "70 / 145\n" +
                "PSY 142\n" +
                "T\n" +
                "5:15pm - 7:45pm\n" +
                "LSB 1001\n" +
                "48 / 159\n" +
                "PSY 146\n" +
                "F\n" +
                "9:00 am - 11:50am\n" +
                "BUCHN1920\n" +
                "142 / 154\n" +
                "PSY 148\n" +
                "M\n" +
                "5:00pm - 8:15pm\n" +
                "BUCHN1910\n" +
                "69 / 336\n" +
                "PSY 154\n" +
                "T R\n" +
                "2:00pm - 3:15pm\n" +
                "BUCHN1940\n" +
                "71 / 149\n" +
                "PSY 163SJ\n" +
                "T R\n" +
                "9:30 am - 10:45am\n" +
                "GIRV 1004\n" +
                "43 / 226\n" +
                "PSY 169L\n" +
                "M\n" +
                "11:00am - 12:50pm\n" +
                "PHELP1160\n" +
                "32 / 48\n" +
                "PSY 169L\n" +
                "T R\n" +
                "9:00 am - 10:50am\n" +
                "PSYCH3113\n" +
                "8 / 8\n" +
                "PSY 169L\n" +
                "T R\n" +
                "11:00am - 12:50pm\n" +
                "PSYCH3113\n" +
                "8 / 8\n" +
                "PSY 169L\n" +
                "T R\n" +
                "1:00pm - 2:50pm\n" +
                "PSYCH3113\n" +
                "8 / 8\n" +
                "PSY 169L\n" +
                "T R\n" +
                "3:00pm - 4:50pm\n" +
                "PSYCH3113\n" +
                "8 / 8\n" +
                "PSY 197A\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "0 / 1\n" +
                "PSY 198\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "0 / 1\n" +
                "PSY 199\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "0 / 1\n" +
                "PSY 199P\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "0 / 1\n" +
                "RG ST 5\n" +
                "M W\n" +
                "3:30pm - 4:45pm\n" +
                "HSSB 1173\n" +
                "60 / 60\n" +
                "RG ST 5\n" +
                "M\n" +
                "5:00pm - 5:50pm\n" +
                "HSSB 3001E\n" +
                "15 / 15\n" +
                "RG ST 5\n" +
                "M\n" +
                "6:00pm - 6:50pm\n" +
                "HSSB 3001E\n" +
                "15 / 15\n" +
                "RG ST 5\n" +
                "W\n" +
                "9:00 am - 9:50 am\n" +
                "HSSB 3001E\n" +
                "15 / 15\n" +
                "RG ST 5\n" +
                "W\n" +
                "10:00am - 10:50am\n" +
                "HSSB 3001E\n" +
                "15 / 15\n" +
                "RG ST 10A\n" +
                "MTWR\n" +
                "11:00am - 11:50am\n" +
                "HSSB 3041\n" +
                "10 / 35\n" +
                "RG ST 10A\n" +
                "M\n" +
                "8:00 am - 8:50 am\n" +
                "HSSB 3001E\n" +
                "4 / 18\n" +
                "RG ST 10A\n" +
                "W\n" +
                "8:00 am - 8:50 am\n" +
                "HSSB 3001E\n" +
                "6 / 17\n" +
                "RG ST 10A\n" +
                "MTWR\n" +
                "1:00pm - 1:50pm\n" +
                "HSSB 3001E\n" +
                "18 / 30\n" +
                "RG ST 10A\n" +
                "F\n" +
                "10:00am - 10:50am\n" +
                "HSSB 3001E\n" +
                "12 / 15\n" +
                "RG ST 10A\n" +
                "F\n" +
                "9:00 am - 9:50 am\n" +
                "HSSB 3001E\n" +
                "6 / 15\n" +
                "RG ST 10D\n" +
                "MTWR\n" +
                "9:00 am - 9:50 am\n" +
                "HSSB 3041\n" +
                "9 / 25\n" +
                "RG ST 10D\n" +
                "F\n" +
                "9:00 am - 9:50 am\n" +
                "HSSB 3041\n" +
                "5 / 12\n" +
                "RG ST 10D\n" +
                "F\n" +
                "10:00am - 10:50am\n" +
                "HSSB 3041\n" +
                "4 / 13\n" +
                "RG ST 11A\n" +
                "T R\n" +
                "9:30 am - 10:45am\n" +
                "HSSB 3201\n" +
                "15 / 20\n" +
                "RG ST 14\n" +
                "T R\n" +
                "9:30 am - 10:45am\n" +
                "IV THEA2\n" +
                "70 / 70\n" +
                "RG ST 14\n" +
                "M\n" +
                "11:00am - 11:50am\n" +
                "GIRV 1106\n" +
                "13 / 15\n" +
                "RG ST 14\n" +
                "M\n" +
                "12:00pm - 12:50pm\n" +
                "GIRV 1106\n" +
                "7 / 15\n" +
                "RG ST 14\n" +
                "W\n" +
                "2:00pm - 2:50pm\n" +
                "GIRV 1106\n" +
                "12 / 15\n" +
                "RG ST 14\n" +
                "W\n" +
                "3:00pm - 3:50pm\n" +
                "GIRV 1106\n" +
                "15 / 15\n" +
                "RG ST 14\n" +
                "W\n" +
                "9:00 am - 9:50 am\n" +
                "GIRV 2135\n" +
                "2 / 15\n" +
                "RG ST 14\n" +
                "M\n" +
                "9:00 am - 9:50 am\n" +
                "GIRV 2110\n" +
                "4 / 15\n" +
                "RG ST 14\n" +
                "W\n" +
                "8:00 am - 8:50 am\n" +
                "GIRV 1106\n" +
                "3 / 15\n" +
                "RG ST 14\n" +
                "M\n" +
                "10:00am - 10:50am\n" +
                "GIRV 2110\n" +
                "14 / 15\n" +
                "RG ST 21\n" +
                "T R\n" +
                "2:00pm - 3:15pm\n" +
                "EMBARHALL\n" +
                "95 / 95\n" +
                "RG ST 21\n" +
                "M\n" +
                "8:00 am - 8:50 am\n" +
                "GIRV 1106\n" +
                "2 / 11\n" +
                "RG ST 21\n" +
                "M\n" +
                "9:00 am - 9:50 am\n" +
                "GIRV 2135\n" +
                "4 / 11\n" +
                "RG ST 21\n" +
                "M\n" +
                "11:00am - 11:50am\n" +
                "GIRV 2110\n" +
                "11 / 11\n" +
                "RG ST 21\n" +
                "M\n" +
                "12:00pm - 12:50pm\n" +
                "GIRV 2110\n" +
                "11 / 11\n" +
                "RG ST 21\n" +
                "T\n" +
                "12:00pm - 12:50pm\n" +
                "GIRV 1106\n" +
                "11 / 11\n" +
                "RG ST 21\n" +
                "T\n" +
                "1:00pm - 1:50pm\n" +
                "GIRV 2110\n" +
                "11 / 11\n" +
                "RG ST 21\n" +
                "T\n" +
                "6:00pm - 6:50pm\n" +
                "HSSB 3001\n" +
                "5 / 11\n" +
                "RG ST 21\n" +
                "T\n" +
                "5:00pm - 5:50pm\n" +
                "GIRV 1106\n" +
                "9 / 11\n" +
                "RG ST 21\n" +
                "W\n" +
                "8:00 am - 8:50 am\n" +
                "GIRV 2110\n" +
                "1 / 11\n" +
                "RG ST 21\n" +
                "W\n" +
                "4:00pm - 4:50pm\n" +
                "HSSB 3001E\n" +
                "3 / 11\n" +
                "RG ST 21\n" +
                "W\n" +
                "2:00pm - 2:50pm\n" +
                "GIRV 2127\n" +
                "10 / 11\n" +
                "RG ST 21\n" +
                "W\n" +
                "3:00pm - 3:50pm\n" +
                "HSSB 2202\n" +
                "2 / 11\n" +
                "RG ST 21\n" +
                "F\n" +
                "8:00 am - 8:50 am\n" +
                "GIRV 2110\n" +
                "0 / 11\n" +
                "RG ST 21\n" +
                "F\n" +
                "9:00 am - 9:50 am\n" +
                "GIRV 1106\n" +
                "8 / 11\n" +
                "RG ST 21\n" +
                "F\n" +
                "11:00am - 11:50am\n" +
                "HSSB 1233\n" +
                "5 / 11\n" +
                "RG ST 21\n" +
                "F\n" +
                "12:00pm - 12:50pm\n" +
                "GIRV 2135\n" +
                "2 / 11\n" +
                "RG ST 35\n" +
                "T R\n" +
                "9:30 am - 10:45am\n" +
                "BUCHN1920\n" +
                "33 / 95\n" +
                "RG ST 35\n" +
                "T\n" +
                "2:00pm - 2:50pm\n" +
                "GIRV 1106\n" +
                "14 / 15\n" +
                "RG ST 35\n" +
                "T\n" +
                "3:00pm - 3:50pm\n" +
                "ELLSN2816\n" +
                "3 / 15\n" +
                "RG ST 35\n" +
                "T\n" +
                "4:00pm - 4:50pm\n" +
                "ELLSN2816\n" +
                "2 / 15\n" +
                "RG ST 35\n" +
                "R\n" +
                "3:00pm - 3:50pm\n" +
                "HSSB 4201\n" +
                "3 / 15\n" +
                "RG ST 35\n" +
                "R\n" +
                "4:00pm - 4:50pm\n" +
                "ELLSN2816\n" +
                "3 / 15\n" +
                "RG ST 35\n" +
                "F\n" +
                "12:00pm - 12:50pm\n" +
                "GIRV 2110\n" +
                "6 / 15\n" +
                "RG ST 35\n" +
                "F\n" +
                "1:00pm - 1:50pm\n" +
                "GIRV 2135\n" +
                "2 / 15\n" +
                "RG ST 35\n" +
                "F\n" +
                "2:00pm - 2:50pm\n" +
                "GIRV 2110\n" +
                "0 / 15\n" +
                "RG ST 57A\n" +
                "T R\n" +
                "11:00am - 12:15pm\n" +
                "HSSB 3001E\n" +
                "12 / 24\n" +
                "RG ST 57A\n" +
                "R\n" +
                "1:00pm - 1:50pm\n" +
                "HSSB 3041\n" +
                "12 / 24\n" +
                "RG ST 57D\n" +
                "T R\n" +
                "3:00pm - 4:15pm\n" +
                "HSSB 3001E\n" +
                "1 / 20\n" +
                "RG ST 65A\n" +
                "MTWR\n" +
                "10:00am - 10:50am\n" +
                "GIRV 2135\n" +
                "2 / 15\n" +
                "RG ST 65D\n" +
                "MTWR\n" +
                "11:00am - 11:50am\n" +
                "HSSB 3202\n" +
                "0 / 15\n" +
                "RG ST 80B\n" +
                "M W F\n" +
                "10:00am - 10:50am\n" +
                "IV THEA2\n" +
                "26 / 70\n" +
                "RG ST 80B\n" +
                "M\n" +
                "3:00pm - 3:50pm\n" +
                "GIRV 1106\n" +
                "3 / 15\n" +
                "RG ST 80B\n" +
                "M\n" +
                "4:00pm - 4:50pm\n" +
                "GIRV 1106\n" +
                "5 / 15\n" +
                "RG ST 80B\n" +
                "M\n" +
                "5:00pm - 5:50pm\n" +
                "GIRV 1106\n" +
                "2 / 15\n" +
                "RG ST 80B\n" +
                "W\n" +
                "1:00pm - 1:50pm\n" +
                "GIRV 1106\n" +
                "10 / 15\n" +
                "RG ST 80B\n" +
                "W\n" +
                "2:00pm - 2:50pm\n" +
                "ELLSN2816\n" +
                "3 / 15\n" +
                "RG ST 80B\n" +
                "W\n" +
                "3:00pm - 3:50pm\n" +
                "ELLSN2816\n" +
                "1 / 15\n" +
                "RG ST 80B\n" +
                "F\n" +
                "12:00pm - 12:50pm\n" +
                "ELLSN2816\n" +
                "2 / 15\n" +
                "RG ST 80B\n" +
                "F\n" +
                "1:00pm - 1:50pm\n" +
                "HSSB 2202\n" +
                "0 / 15\n" +
                "RG ST 80BH\n" +
                "F\n" +
                "11:00am - 11:50am\n" +
                "HSSB 3001E\n" +
                "0 / 15\n" +
                "RG ST 82\n" +
                "T R\n" +
                "2:00pm - 3:15pm\n" +
                "TD-W 2600\n" +
                "60 / 60\n" +
                "RG ST 82\n" +
                "W\n" +
                "1:00pm - 1:50pm\n" +
                "GIRV 2135\n" +
                "15 / 15\n" +
                "RG ST 82\n" +
                "W\n" +
                "2:00pm - 2:50pm\n" +
                "HSSB 2202\n" +
                "15 / 15\n" +
                "RG ST 82\n" +
                "F\n" +
                "10:00am - 10:50am\n" +
                "ELLSN2816\n" +
                "15 / 15\n" +
                "RG ST 82\n" +
                "F\n" +
                "11:00am - 11:50am\n" +
                "ELLSN2816\n" +
                "15 / 15\n" +
                "RG ST 90AA\n" +
                "MTWR\n" +
                "5:00pm - 5:50pm\n" +
                "HSSB 4080\n" +
                "2 / 20\n" +
                "RG ST 90BB\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "0 / 20\n" +
                "RG ST 110F\n" +
                "M W\n" +
                "12:30pm - 1:45pm\n" +
                "HSSB 3041\n" +
                "31 / 30\n" +
                "RG ST 113\n" +
                "T R\n" +
                "3:30pm - 4:45pm\n" +
                "BUCHN1920\n" +
                "38 / 130\n" +
                "RG ST 113\n" +
                "M\n" +
                "6:30pm - 9:30pm\n" +
                "BUCHN1920\n" +
                "38 / 130\n" +
                "RG ST 114E\n" +
                "T R\n" +
                "2:00pm - 3:15pm\n" +
                "GIRV 2123\n" +
                "10 / 24\n" +
                "RG ST 130\n" +
                "T R\n" +
                "9:30 am - 10:45am\n" +
                "SH 1431\n" +
                "42 / 90\n" +
                "RG ST 138C\n" +
                "T\n" +
                "2:00pm - 4:50pm\n" +
                "HSSB 3041\n" +
                "12 / 30\n" +
                "RG ST 140E\n" +
                "M W\n" +
                "9:30 am - 10:45am\n" +
                "GIRV 2123\n" +
                "16 / 30\n" +
                "RG ST 148A\n" +
                "T R\n" +
                "10:00am - 11:25am\n" +
                "HSSB 4080\n" +
                "2 / 15\n" +
                "RG ST 148A\n" +
                "T R\n" +
                "11:30am - 11:55am\n" +
                "HSSB 4080\n" +
                "2 / 15\n" +
                "RG ST 149D\n" +
                "M W\n" +
                "9:30 am - 10:45am\n" +
                "HSSB 3201\n" +
                "1 / 24\n" +
                "RG ST 156CC\n" +
                "W\n" +
                "12:00pm - 2:50pm\n" +
                "HSSB 3030\n" +
                "0 / 10\n" +
                "RG ST 156EE\n" +
                "T R\n" +
                "2:00pm - 3:15pm\n" +
                "HSSB 4202\n" +
                "12 / 12\n" +
                "RG ST 159D\n" +
                "M W\n" +
                "9:00 am - 10:50am\n" +
                "HSSB 3024\n" +
                "2 / 15\n" +
                "RG ST 159N\n" +
                "R\n" +
                "3:00pm - 5:50pm\n" +
                "HSSB 3024\n" +
                "0 / 10\n" +
                "RG ST 187A\n" +
                "R\n" +
                "8:00 am - 10:50am\n" +
                "HSSB 3001E\n" +
                "6 / 25\n" +
                "RG ST 195\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "0 / 10\n" +
                "RG ST 199\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "0 / 30\n" +
                "RG ST 199RA\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "0 / 10\n" +
                "RENST 199\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "0 / 10\n" +
                "SLAV 1\n" +
                "M WRF\n" +
                "10:00am - 10:50am\n" +
                "PHELP3519\n" +
                "17 / 17\n" +
                "SLAV 1\n" +
                "M WRF\n" +
                "2:00pm - 2:50pm\n" +
                "HSSB 1233\n" +
                "9 / 17\n" +
                "SLAV 1\n" +
                "M WRF\n" +
                "9:00 am - 9:50 am\n" +
                "GIRV 2116\n" +
                "3 / 17\n" +
                "SLAV 4\n" +
                "M W F\n" +
                "11:00am - 11:50am\n" +
                "GIRV 2123\n" +
                "4 / 25\n" +
                "SLAV 4\n" +
                "R\n" +
                "\n" +
                "\n" +
                "4 / 25\n" +
                "SLAV 101D\n" +
                "M W F\n" +
                "1:00pm - 1:50pm\n" +
                "HSSB 1233\n" +
                "2 / 25\n" +
                "SLAV 117G\n" +
                "T R\n" +
                "9:30 am - 10:45am\n" +
                "PHELP1425\n" +
                "23 / 60\n" +
                "SLAV 164B\n" +
                "M W\n" +
                "2:00pm - 3:15pm\n" +
                "GIRV 2115\n" +
                "21 / 20\n" +
                "SLAV 182\n" +
                "M W\n" +
                "3:30pm - 4:45pm\n" +
                "PHELP1440\n" +
                "12 / 30\n" +
                "SLAV 197\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "0 / 3\n" +
                "SLAV 198\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "0 / 5\n" +
                "SLAV 199\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "0 / 10\n" +
                "SOC 1\n" +
                "T R\n" +
                "11:00am - 12:15pm\n" +
                "IV THEA1\n" +
                "174 / 174\n" +
                "SOC 1\n" +
                "T\n" +
                "7:00pm - 7:50pm\n" +
                "PHELP1444\n" +
                "8 / 24\n" +
                "SOC 1\n" +
                "F\n" +
                "9:00 am - 9:50 am\n" +
                "ARTS 1349\n" +
                "13 / 24\n" +
                "SOC 1\n" +
                "M\n" +
                "6:00pm - 6:50pm\n" +
                "HSSB 4202\n" +
                "24 / 24\n" +
                "SOC 1\n" +
                "F\n" +
                "12:00pm - 12:50pm\n" +
                "GIRV 2116\n" +
                "23 / 23\n" +
                "SOC 1\n" +
                "R\n" +
                "6:00pm - 6:50pm\n" +
                "PHELP3523\n" +
                "9 / 24\n" +
                "SOC 1\n" +
                "F\n" +
                "8:00 am - 8:50 am\n" +
                "PHELP1508\n" +
                "1 / 24\n" +
                "SOC 1\n" +
                "M\n" +
                "8:00 am - 8:50 am\n" +
                "GIRV 2124\n" +
                "1 / 23\n" +
                "SOC 1\n" +
                "F\n" +
                "1:00pm - 1:50pm\n" +
                "GIRV 2119\n" +
                "12 / 24\n" +
                "SOC 1\n" +
                "T\n" +
                "8:00 am - 8:50 am\n" +
                "PHELP3519\n" +
                "7 / 24\n" +
                "SOC 1\n" +
                "R\n" +
                "8:00 am - 8:50 am\n" +
                "PHELP1508\n" +
                "5 / 24\n" +
                "SOC 1\n" +
                "F\n" +
                "8:00 am - 8:50 am\n" +
                "HSSB 1210\n" +
                "1 / 24\n" +
                "SOC 1\n" +
                "R\n" +
                "7:00pm - 7:50pm\n" +
                "PHELP1440\n" +
                "1 / 24\n" +
                "SOC 1\n" +
                "T\n" +
                "7:00pm - 7:50pm\n" +
                "PHELP1445\n" +
                "6 / 24\n" +
                "SOC 1\n" +
                "W\n" +
                "8:00 am - 8:50 am\n" +
                "PHELP3519\n" +
                "4 / 24\n" +
                "SOC 1\n" +
                "F\n" +
                "1:00pm - 1:50pm\n" +
                "GIRV 2120\n" +
                "6 / 24\n" +
                "SOC 1\n" +
                "W\n" +
                "8:00 am - 8:50 am\n" +
                "PHELP3523\n" +
                "1 / 24\n" +
                "SOC 1\n" +
                "R\n" +
                "7:00pm - 7:50pm\n" +
                "PHELP1444\n" +
                "1 / 24\n" +
                "SOC 1\n" +
                "F\n" +
                "2:00pm - 2:50pm\n" +
                "PHELP1445\n" +
                "9 / 24\n" +
                "SOC 1\n" +
                "W\n" +
                "8:00 am - 8:50 am\n" +
                "GIRV 1119\n" +
                "6 / 24\n" +
                "SOC 1\n" +
                "M\n" +
                "8:00 am - 8:50 am\n" +
                "PHELP3519\n" +
                "2 / 24\n" +
                "SOC 1\n" +
                "M\n" +
                "2:00pm - 2:50pm\n" +
                "GIRV 2120\n" +
                "23 / 23\n" +
                "SOC 1\n" +
                "W\n" +
                "7:00pm - 7:50pm\n" +
                "BUCHN1934\n" +
                "11 / 23\n" +
                "SOC 1H\n" +
                "W\n" +
                "12:00pm - 12:50pm\n" +
                "GIRV 2110\n" +
                "2 / 15\n" +
                "SOC 98\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "0 / 1\n" +
                "SOC 99\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "0 / 1\n" +
                "SOC 105E\n" +
                "T R\n" +
                "9:30 am - 10:45am\n" +
                "GIRV 2128\n" +
                "11 / 70\n" +
                "SOC 108\n" +
                "T R\n" +
                "11:00am - 12:15pm\n" +
                "NH 1105\n" +
                "60 / 60\n" +
                "SOC 108\n" +
                "F\n" +
                "8:00 am - 8:50 am\n" +
                "GIRV 2135\n" +
                "15 / 15\n" +
                "SOC 108\n" +
                "F\n" +
                "9:00 am - 9:50 am\n" +
                "GIRV 2110\n" +
                "15 / 15\n" +
                "SOC 108\n" +
                "R\n" +
                "2:00pm - 2:50pm\n" +
                "SSMS 3017\n" +
                "15 / 15\n" +
                "SOC 108\n" +
                "R\n" +
                "3:00pm - 3:50pm\n" +
                "SSMS 3017\n" +
                "15 / 15\n" +
                "SOC 108C\n" +
                "T R\n" +
                "9:30 am - 10:45am\n" +
                "PHELP2516\n" +
                "60 / 60\n" +
                "SOC 108C\n" +
                "W\n" +
                "2:00pm - 2:50pm\n" +
                "SSMS 3017\n" +
                "15 / 15\n" +
                "SOC 108C\n" +
                "W\n" +
                "3:00pm - 3:50pm\n" +
                "SSMS 3017\n" +
                "15 / 15\n" +
                "SOC 108C\n" +
                "W\n" +
                "8:00 am - 8:50 am\n" +
                "GIRV 2127\n" +
                "15 / 15\n" +
                "SOC 108C\n" +
                "W\n" +
                "10:00am - 10:50am\n" +
                "GIRV 2110\n" +
                "15 / 15\n" +
                "SOC 118C\n" +
                "T R\n" +
                "12:30pm - 1:45pm\n" +
                "PHELP1160\n" +
                "35 / 70\n" +
                "SOC 118L\n" +
                "W\n" +
                "5:00pm - 7:50pm\n" +
                "PHELP3515\n" +
                "62 / 80\n" +
                "SOC 122\n" +
                "T R\n" +
                "8:00 am - 9:15 am\n" +
                "HSSB 1174\n" +
                "45 / 90\n" +
                "SOC 126\n" +
                "T R\n" +
                "11:00am - 12:15pm\n" +
                "PHELP3515\n" +
                "47 / 80\n" +
                "SOC 134\n" +
                "T R\n" +
                "3:30pm - 4:45pm\n" +
                "HSSB 1173\n" +
                "74 / 75\n" +
                "SOC 142\n" +
                "M W F\n" +
                "11:00am - 11:50am\n" +
                "BUCHN1940\n" +
                "61 / 149\n" +
                "SOC 144\n" +
                "M\n" +
                "9:00 am - 11:50am\n" +
                "SSMS 3017\n" +
                "20 / 20\n" +
                "SOC 144S\n" +
                "T R\n" +
                "8:00 am - 9:15 am\n" +
                "WEBB 1100\n" +
                "10 / 80\n" +
                "SOC 145\n" +
                "M W\n" +
                "9:30 am - 10:45am\n" +
                "SH 1431\n" +
                "30 / 80\n" +
                "SOC 151\n" +
                "T R\n" +
                "12:30pm - 1:45pm\n" +
                "PSYCH1902\n" +
                "43 / 77\n" +
                "SOC 152A\n" +
                "M W F\n" +
                "2:00pm - 2:50pm\n" +
                "CAMPBHALL\n" +
                "291 / 600\n" +
                "SOC 152B\n" +
                "M W F\n" +
                "1:00pm - 1:50pm\n" +
                "ELLSN2816\n" +
                "0 / 18\n" +
                "SOC 152C\n" +
                "T R\n" +
                "4:00pm - 4:50pm\n" +
                "SSMS 3017\n" +
                "0 / 20\n" +
                "SOC 154A\n" +
                "T R\n" +
                "9:30 am - 10:45am\n" +
                "PHELP1260\n" +
                "76 / 90\n" +
                "SOC 164\n" +
                "T R\n" +
                "12:30pm - 1:45pm\n" +
                "PHELP1260\n" +
                "67 / 90\n" +
                "SOC 172\n" +
                "T R\n" +
                "8:00 am - 9:15 am\n" +
                "PSYCH1924\n" +
                "51 / 100\n" +
                "SOC 185A\n" +
                "M W\n" +
                "11:00am - 12:15pm\n" +
                "PHELP3515\n" +
                "28 / 80\n" +
                "SOC 185C\n" +
                "T R\n" +
                "3:30pm - 4:45pm\n" +
                "IV THEA2\n" +
                "47 / 100\n" +
                "SOC 185S\n" +
                "T R\n" +
                "8:00 am - 9:15 am\n" +
                "PHELP1260\n" +
                "2 / 90\n" +
                "SOC 190A\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "0 / 0\n" +
                "SOC 191CA\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "0 / 15\n" +
                "SOC 196H\n" +
                "W\n" +
                "5:00pm - 7:50pm\n" +
                "SSMS 3017\n" +
                "0 / 15\n" +
                "SOC 198\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "0 / 1\n" +
                "SOC 199\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "0 / 1\n" +
                "SOC 199RA\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "0 / 40\n" +
                "SPAN 1\n" +
                "MTWR\n" +
                "9:00 am - 9:50 am\n" +
                "PHELP1445\n" +
                "22 / 22\n" +
                "SPAN 1\n" +
                "MTWR\n" +
                "11:00am - 11:50am\n" +
                "PHELP1444\n" +
                "22 / 22\n" +
                "SPAN 1\n" +
                "MTWR\n" +
                "10:00am - 10:50am\n" +
                "GIRV 1116\n" +
                "22 / 22\n" +
                "SPAN 1\n" +
                "MTWR\n" +
                "12:00pm - 12:50pm\n" +
                "PHELP1445\n" +
                "22 / 22\n" +
                "SPAN 1\n" +
                "MTWR\n" +
                "1:00pm - 1:50pm\n" +
                "GIRV 2112\n" +
                "22 / 22\n" +
                "SPAN 1\n" +
                "MTWR\n" +
                "2:00pm - 2:50pm\n" +
                "HSSB 1207\n" +
                "23 / 23\n" +
                "SPAN 1\n" +
                "MTWR\n" +
                "3:00pm - 3:50pm\n" +
                "HSSB 1228\n" +
                "22 / 23\n" +
                "SPAN 2\n" +
                "MTWR\n" +
                "2:00pm - 2:50pm\n" +
                "PHELP1445\n" +
                "22 / 22\n" +
                "SPAN 2\n" +
                "MTWR\n" +
                "10:00am - 10:50am\n" +
                "GIRV 2116\n" +
                "20 / 22\n" +
                "SPAN 2\n" +
                "MTWR\n" +
                "1:00pm - 1:50pm\n" +
                "GIRV 2116\n" +
                "21 / 22\n" +
                "SPAN 2\n" +
                "MTWR\n" +
                "11:00am - 11:50am\n" +
                "HSSB 1232\n" +
                "16 / 21\n" +
                "SPAN 2\n" +
                "MTWR\n" +
                "12:00pm - 12:50pm\n" +
                "HSSB 1224\n" +
                "5 / 21\n" +
                "SPAN 3\n" +
                "MTWR\n" +
                "1:00pm - 1:50pm\n" +
                "BUCHN1934\n" +
                "23 / 23\n" +
                "SPAN 3\n" +
                "MTWR\n" +
                "10:00am - 10:50am\n" +
                "HSSB 1206\n" +
                "23 / 23\n" +
                "SPAN 3\n" +
                "MTWR\n" +
                "11:00am - 11:50am\n" +
                "GIRV 2127\n" +
                "23 / 23\n" +
                "SPAN 4\n" +
                "MTWR\n" +
                "1:00pm - 1:50pm\n" +
                "HSSB 2251\n" +
                "19 / 19\n" +
                "SPAN 4\n" +
                "MTWR\n" +
                "11:00am - 11:50am\n" +
                "GIRV 2112\n" +
                "18 / 18\n" +
                "SPAN 4\n" +
                "MTWR\n" +
                "10:00am - 10:50am\n" +
                "GIRV 2115\n" +
                "18 / 18\n" +
                "SPAN 5\n" +
                "MTWR\n" +
                "10:00am - 10:50am\n" +
                "HSSB 1232\n" +
                "19 / 19\n" +
                "SPAN 5\n" +
                "MTWR\n" +
                "1:00pm - 1:50pm\n" +
                "GIRV 2115\n" +
                "19 / 19\n" +
                "SPAN 6\n" +
                "MTWR\n" +
                "2:00pm - 2:50pm\n" +
                "GIRV 2116\n" +
                "15 / 19\n" +
                "SPAN 6\n" +
                "MTWR\n" +
                "1:00pm - 1:50pm\n" +
                "GIRV 2108\n" +
                "7 / 19\n" +
                "SPAN 16A\n" +
                "M W F\n" +
                "9:30 am - 10:45am\n" +
                "PHELP1448\n" +
                "20 / 27\n" +
                "SPAN 16A\n" +
                "M W F\n" +
                "11:00am - 12:15pm\n" +
                "PHELP1448\n" +
                "26 / 26\n" +
                "SPAN 16B\n" +
                "M W F\n" +
                "9:30 am - 10:45am\n" +
                "PHELP1444\n" +
                "13 / 29\n" +
                "SPAN 16B\n" +
                "M W F\n" +
                "11:00am - 11:50am\n" +
                "LSB 1101\n" +
                "15 / 23\n" +
                "SPAN 25\n" +
                "M W F\n" +
                "11:00am - 12:15pm\n" +
                "PHELP1440\n" +
                "11 / 25\n" +
                "SPAN 100\n" +
                "M W\n" +
                "2:00pm - 3:15pm\n" +
                "SH 1430\n" +
                "29 / 50\n" +
                "SPAN 102L\n" +
                "T R\n" +
                "11:00am - 12:15pm\n" +
                "GIRV 1115\n" +
                "1 / 40\n" +
                "SPAN 102L\n" +
                "M W\n" +
                "8:00 am - 9:15 am\n" +
                "PHELP1508\n" +
                "1 / 40\n" +
                "SPAN 110A\n" +
                "T R\n" +
                "8:00 am - 9:15 am\n" +
                "PHELP2514\n" +
                "8 / 20\n" +
                "SPAN 110D\n" +
                "M W\n" +
                "12:30pm - 1:45pm\n" +
                "PHELP3505\n" +
                "16 / 60\n" +
                "SPAN 111A\n" +
                "T R\n" +
                "12:30pm - 1:45pm\n" +
                "PHELP3505\n" +
                "6 / 50\n" +
                "SPAN 114A\n" +
                "M W\n" +
                "9:30 am - 10:45am\n" +
                "GIRV 1115\n" +
                "2 / 40\n" +
                "SPAN 126\n" +
                "M W\n" +
                "3:30pm - 4:45pm\n" +
                "HSSB 1174\n" +
                "50 / 50\n" +
                "SPAN 139\n" +
                "M W\n" +
                "12:30pm - 1:45pm\n" +
                "ARTS 1353\n" +
                "15 / 30\n" +
                "SPAN 140B\n" +
                "M W\n" +
                "2:00pm - 3:15pm\n" +
                "PHELP3523\n" +
                "9 / 48\n" +
                "SPAN 145\n" +
                "T R\n" +
                "9:30 am - 10:45am\n" +
                "PHELP3523\n" +
                "14 / 40\n" +
                "SPAN 153\n" +
                "T R\n" +
                "12:30pm - 1:45pm\n" +
                "GIRV 2119\n" +
                "24 / 40\n" +
                "SPAN 154A\n" +
                "T R\n" +
                "3:30pm - 4:45pm\n" +
                "NH 1111\n" +
                "2 / 40\n" +
                "SPAN 169\n" +
                "M W\n" +
                "11:00am - 12:15pm\n" +
                "GIRV 1112\n" +
                "4 / 30\n" +
                "SPAN 178\n" +
                "T R\n" +
                "11:00am - 12:15pm\n" +
                "PHELP2516\n" +
                "33 / 40\n" +
                "SPAN 183R\n" +
                "M W\n" +
                "11:00am - 12:15pm\n" +
                "PHELP2524\n" +
                "0 / 30\n" +
                "SPAN 185\n" +
                "M W\n" +
                "9:30 am - 10:45am\n" +
                "GIRV 2119\n" +
                "6 / 30\n" +
                "SPAN 194\n" +
                "T R\n" +
                "9:30 am - 10:45am\n" +
                "GIRV 1112\n" +
                "4 / 40\n" +
                "SPAN 195\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "0 / 10\n" +
                "SPAN 196\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "0 / 10\n" +
                "SPAN 199\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "0 / 15\n" +
                "SHS 166\n" +
                "W\n" +
                "11:00am - 1:50pm\n" +
                "HARDR1035\n" +
                "0 / 21\n" +
                "SHS 166\n" +
                "W\n" +
                "2:30pm - 3:30pm\n" +
                "HARDR1035\n" +
                "0 / 21\n" +
                "SHS 182\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "0 / 1\n" +
                "SHS 194\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "0 / 10\n" +
                "SHS 197\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "0 / 5\n" +
                "SHS 199\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "0 / 10\n" +
                "PSTAT 5A\n" +
                "M W F\n" +
                "9:00 am - 9:50 am\n" +
                "MUSICLLCH\n" +
                "290 / 290\n" +
                "PSTAT 5A\n" +
                "T\n" +
                "9:00 am - 9:50 am\n" +
                "HSSB 1232\n" +
                "25 / 25\n" +
                "PSTAT 5A\n" +
                "R\n" +
                "9:00 am - 9:50 am\n" +
                "SSMS 1303\n" +
                "25 / 25\n" +
                "PSTAT 5A\n" +
                "T\n" +
                "9:00 am - 9:50 am\n" +
                "HSSB 1236\n" +
                "17 / 25\n" +
                "PSTAT 5A\n" +
                "R\n" +
                "9:00 am - 9:50 am\n" +
                "SSMS 1304\n" +
                "17 / 25\n" +
                "PSTAT 5A\n" +
                "T\n" +
                "10:00am - 10:50am\n" +
                "HSSB 1237\n" +
                "25 / 25\n" +
                "PSTAT 5A\n" +
                "R\n" +
                "10:00am - 10:50am\n" +
                "SSMS 1303\n" +
                "25 / 25\n" +
                "PSTAT 5A\n" +
                "M\n" +
                "12:00pm - 12:50pm\n" +
                "HSSB 1232\n" +
                "25 / 25\n" +
                "PSTAT 5A\n" +
                "W\n" +
                "12:00pm - 12:50pm\n" +
                "PHELP1525\n" +
                "25 / 25\n" +
                "PSTAT 5A\n" +
                "T\n" +
                "11:00am - 11:50am\n" +
                "HSSB 1228\n" +
                "25 / 25\n" +
                "PSTAT 5A\n" +
                "R\n" +
                "11:00am - 11:50am\n" +
                "SSMS 1303\n" +
                "25 / 25\n" +
                "PSTAT 5A\n" +
                "T\n" +
                "11:00am - 11:50am\n" +
                "HSSB 1231\n" +
                "25 / 25\n" +
                "PSTAT 5A\n" +
                "R\n" +
                "11:00am - 11:50am\n" +
                "SSMS 1304\n" +
                "25 / 25\n" +
                "PSTAT 5A\n" +
                "M\n" +
                "11:00am - 11:50am\n" +
                "GIRV 2124\n" +
                "25 / 25\n" +
                "PSTAT 5A\n" +
                "W\n" +
                "11:00am - 11:50am\n" +
                "PHELP1513\n" +
                "25 / 25\n" +
                "PSTAT 5A\n" +
                "T\n" +
                "12:00pm - 12:50pm\n" +
                "HSSB 1223\n" +
                "24 / 25\n" +
                "PSTAT 5A\n" +
                "R\n" +
                "12:00pm - 12:50pm\n" +
                "PHELP1513\n" +
                "24 / 25\n" +
                "PSTAT 5A\n" +
                "T\n" +
                "1:00pm - 1:50pm\n" +
                "PHELP2532\n" +
                "25 / 25\n" +
                "PSTAT 5A\n" +
                "R\n" +
                "1:00pm - 1:50pm\n" +
                "SSMS 1303\n" +
                "25 / 25\n" +
                "PSTAT 5A\n" +
                "T\n" +
                "3:00pm - 3:50pm\n" +
                "HSSB 1227\n" +
                "25 / 25\n" +
                "PSTAT 5A\n" +
                "R\n" +
                "3:00pm - 3:50pm\n" +
                "SSMS 1303\n" +
                "25 / 25\n" +
                "PSTAT 5A\n" +
                "T\n" +
                "4:00pm - 4:50pm\n" +
                "HSSB 1227\n" +
                "16 / 25\n" +
                "PSTAT 5A\n" +
                "R\n" +
                "4:00pm - 4:50pm\n" +
                "SSMS 1303\n" +
                "16 / 25\n" +
                "PSTAT 5A\n" +
                "T\n" +
                "4:00pm - 4:50pm\n" +
                "HSSB 1228\n" +
                "5 / 25\n" +
                "PSTAT 5A\n" +
                "R\n" +
                "4:00pm - 4:50pm\n" +
                "SSMS 1302\n" +
                "5 / 25\n" +
                "PSTAT 5A\n" +
                "T\n" +
                "5:00pm - 5:50pm\n" +
                "HSSB 1233\n" +
                "0 / 25\n" +
                "PSTAT 5A\n" +
                "R\n" +
                "5:00pm - 5:50pm\n" +
                "SSMS 1303\n" +
                "0 / 25\n" +
                "PSTAT 5A\n" +
                "T\n" +
                "5:00pm - 5:50pm\n" +
                "HSSB 1236\n" +
                "2 / 25\n" +
                "PSTAT 5A\n" +
                "R\n" +
                "5:00pm - 5:50pm\n" +
                "SSMS 1301\n" +
                "2 / 25\n" +
                "PSTAT 5A\n" +
                "M\n" +
                "5:00pm - 5:50pm\n" +
                "GIRV 2127\n" +
                "11 / 25\n" +
                "PSTAT 5A\n" +
                "W\n" +
                "5:00pm - 5:50pm\n" +
                "SSMS 1303\n" +
                "11 / 25\n" +
                "PSTAT 5A\n" +
                "M\n" +
                "5:00pm - 5:50pm\n" +
                "TD-W 2600\n" +
                "7 / 25\n" +
                "PSTAT 5A\n" +
                "W\n" +
                "5:00pm - 5:50pm\n" +
                "SSMS 1304\n" +
                "7 / 25\n" +
                "PSTAT 5A\n" +
                "M\n" +
                "6:00pm - 6:50pm\n" +
                "GIRV 1115\n" +
                "8 / 25\n" +
                "PSTAT 5A\n" +
                "W\n" +
                "6:00pm - 6:50pm\n" +
                "SSMS 1304\n" +
                "8 / 25\n" +
                "PSTAT 5H\n" +
                "W\n" +
                "5:00pm - 5:50pm\n" +
                "PHELP2514\n" +
                "1 / 30\n" +
                "PSTAT 5LS\n" +
                "M W F\n" +
                "9:00 am - 9:50 am\n" +
                "BUCHN1910\n" +
                "56 / 90\n" +
                "PSTAT 5LS\n" +
                "T\n" +
                "3:00pm - 3:50pm\n" +
                "SSMS 1301\n" +
                "11 / 25\n" +
                "PSTAT 5LS\n" +
                "R\n" +
                "3:00pm - 3:50pm\n" +
                "GIRV 2127\n" +
                "11 / 25\n" +
                "PSTAT 5LS\n" +
                "M\n" +
                "11:00am - 11:50am\n" +
                "SSMS 1304\n" +
                "21 / 25\n" +
                "PSTAT 5LS\n" +
                "W\n" +
                "11:00am - 11:50am\n" +
                "PHELP2536\n" +
                "21 / 25\n" +
                "PSTAT 5LS\n" +
                "M\n" +
                "12:00pm - 12:50pm\n" +
                "SSMS 1302\n" +
                "4 / 25\n" +
                "PSTAT 5LS\n" +
                "W\n" +
                "12:00pm - 12:50pm\n" +
                "HSSB 1232\n" +
                "4 / 25\n" +
                "PSTAT 5LS\n" +
                "M\n" +
                "2:00pm - 2:50pm\n" +
                "SSMS 1304\n" +
                "3 / 25\n" +
                "PSTAT 5LS\n" +
                "W\n" +
                "2:00pm - 2:50pm\n" +
                "HSSB 1215\n" +
                "3 / 25\n" +
                "PSTAT 5LS\n" +
                "T\n" +
                "6:00pm - 6:50pm\n" +
                "SSMS 1303\n" +
                "0 / 25\n" +
                "PSTAT 5LS\n" +
                "R\n" +
                "6:00pm - 6:50pm\n" +
                "SH 1609\n" +
                "0 / 25\n" +
                "PSTAT 5LS\n" +
                "M\n" +
                "1:00pm - 1:50pm\n" +
                "SSMS 1302\n" +
                "3 / 25\n" +
                "PSTAT 5LS\n" +
                "W\n" +
                "1:00pm - 1:50pm\n" +
                "GIRV 2119\n" +
                "3 / 25\n" +
                "PSTAT 5LS\n" +
                "M\n" +
                "1:00pm - 1:50pm\n" +
                "PHELP1513\n" +
                "2 / 25\n" +
                "PSTAT 5LS\n" +
                "W\n" +
                "1:00pm - 1:50pm\n" +
                "GIRV 2120\n" +
                "2 / 25\n" +
                "PSTAT 5LS\n" +
                "M\n" +
                "2:00pm - 2:50pm\n" +
                "PHELP1513\n" +
                "2 / 25\n" +
                "PSTAT 5LS\n" +
                "W\n" +
                "2:00pm - 2:50pm\n" +
                "HSSB 1223\n" +
                "2 / 25\n" +
                "PSTAT 5LS\n" +
                "T\n" +
                "3:00pm - 3:50pm\n" +
                "SSMS 1302\n" +
                "0 / 25\n" +
                "PSTAT 5LS\n" +
                "R\n" +
                "3:00pm - 3:50pm\n" +
                "HSSB 1215\n" +
                "0 / 25\n" +
                "PSTAT 5LS\n" +
                "T\n" +
                "2:00pm - 2:50pm\n" +
                "SSMS 1302\n" +
                "10 / 25\n" +
                "PSTAT 5LS\n" +
                "R\n" +
                "2:00pm - 2:50pm\n" +
                "NH 1109\n" +
                "10 / 25\n" +
                "PSTAT 99\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "0 / 1\n" +
                "PSTAT 109\n" +
                "M W F\n" +
                "1:00pm - 1:50pm\n" +
                "IV THEA1\n" +
                "101 / 260\n" +
                "PSTAT 109\n" +
                "M\n" +
                "7:00pm - 7:50pm\n" +
                "SSMS 1303\n" +
                "2 / 13\n" +
                "PSTAT 109\n" +
                "W\n" +
                "7:00pm - 7:50pm\n" +
                "SH 1609\n" +
                "2 / 13\n" +
                "PSTAT 109\n" +
                "M\n" +
                "2:00pm - 2:50pm\n" +
                "SSMS 1302\n" +
                "13 / 13\n" +
                "PSTAT 109\n" +
                "W\n" +
                "2:00pm - 2:50pm\n" +
                "HSSB 1237\n" +
                "13 / 13\n" +
                "PSTAT 109\n" +
                "M\n" +
                "4:00pm - 4:50pm\n" +
                "PHELP1513\n" +
                "13 / 13\n" +
                "PSTAT 109\n" +
                "W\n" +
                "4:00pm - 4:50pm\n" +
                "HSSB 1236\n" +
                "13 / 13\n" +
                "PSTAT 109\n" +
                "M\n" +
                "5:00pm - 5:50pm\n" +
                "PHELP1513\n" +
                "6 / 13\n" +
                "PSTAT 109\n" +
                "W\n" +
                "5:00pm - 5:50pm\n" +
                "TD-W 2600\n" +
                "6 / 13\n" +
                "PSTAT 109\n" +
                "M\n" +
                "6:00pm - 6:50pm\n" +
                "SSMS 1303\n" +
                "0 / 13\n" +
                "PSTAT 109\n" +
                "W\n" +
                "6:00pm - 6:50pm\n" +
                "GIRV 1119\n" +
                "0 / 13\n" +
                "PSTAT 109\n" +
                "M\n" +
                "7:00pm - 7:50pm\n" +
                "SSMS 1302\n" +
                "1 / 13\n" +
                "PSTAT 109\n" +
                "W\n" +
                "7:00pm - 7:50pm\n" +
                "GIRV 2124\n" +
                "1 / 13\n" +
                "PSTAT 109\n" +
                "T\n" +
                "8:00 am - 8:50 am\n" +
                "SSMS 1301\n" +
                "3 / 13\n" +
                "PSTAT 109\n" +
                "R\n" +
                "8:00 am - 8:50 am\n" +
                "HSSB 2251\n" +
                "3 / 13\n" +
                "PSTAT 109\n" +
                "T\n" +
                "5:00pm - 5:50pm\n" +
                "SSMS 1303\n" +
                "5 / 13\n" +
                "PSTAT 109\n" +
                "R\n" +
                "5:00pm - 5:50pm\n" +
                "LSB 1101\n" +
                "5 / 13\n" +
                "PSTAT 109\n" +
                "M\n" +
                "3:00pm - 3:50pm\n" +
                "SSMS 1303\n" +
                "12 / 13\n" +
                "PSTAT 109\n" +
                "W\n" +
                "3:00pm - 3:50pm\n" +
                "HSSB 1211\n" +
                "12 / 13\n" +
                "PSTAT 109\n" +
                "T\n" +
                "3:00pm - 3:50pm\n" +
                "SSMS 1303\n" +
                "9 / 13\n" +
                "PSTAT 109\n" +
                "R\n" +
                "3:00pm - 3:50pm\n" +
                "HSSB 1227\n" +
                "9 / 13\n" +
                "PSTAT 109\n" +
                "M\n" +
                "6:00pm - 6:50pm\n" +
                "SSMS 1304\n" +
                "1 / 13\n" +
                "PSTAT 109\n" +
                "W\n" +
                "6:00pm - 6:50pm\n" +
                "GIRV 2127\n" +
                "1 / 13\n" +
                "PSTAT 109\n" +
                "M\n" +
                "4:00pm - 4:50pm\n" +
                "SSMS 1303\n" +
                "5 / 13\n" +
                "PSTAT 109\n" +
                "W\n" +
                "4:00pm - 4:50pm\n" +
                "GIRV 2116\n" +
                "5 / 13\n" +
                "PSTAT 109\n" +
                "T\n" +
                "8:00 am - 8:50 am\n" +
                "SSMS 1303\n" +
                "0 / 13\n" +
                "PSTAT 109\n" +
                "R\n" +
                "8:00 am - 8:50 am\n" +
                "HSSB 1232\n" +
                "0 / 13\n" +
                "PSTAT 109\n" +
                "T\n" +
                "6:00pm - 6:50pm\n" +
                "PHELP1525\n" +
                "0 / 13\n" +
                "PSTAT 109\n" +
                "R\n" +
                "6:00pm - 6:50pm\n" +
                "PHELP2536\n" +
                "0 / 13\n" +
                "PSTAT 109\n" +
                "T\n" +
                "2:00pm - 2:50pm\n" +
                "SSMS 1301\n" +
                "13 / 13\n" +
                "PSTAT 109\n" +
                "R\n" +
                "2:00pm - 2:50pm\n" +
                "GIRV 1115\n" +
                "13 / 13\n" +
                "PSTAT 109\n" +
                "T\n" +
                "2:00pm - 2:50pm\n" +
                "SSMS 1303\n" +
                "13 / 13\n" +
                "PSTAT 109\n" +
                "R\n" +
                "2:00pm - 2:50pm\n" +
                "PHELP2532\n" +
                "13 / 13\n" +
                "PSTAT 109\n" +
                "T\n" +
                "7:00pm - 7:50pm\n" +
                "SSMS 1303\n" +
                "0 / 13\n" +
                "PSTAT 109\n" +
                "R\n" +
                "7:00pm - 7:50pm\n" +
                "SH 1609\n" +
                "0 / 13\n" +
                "PSTAT 109\n" +
                "T\n" +
                "4:00pm - 4:50pm\n" +
                "SSMS 1303\n" +
                "5 / 13\n" +
                "PSTAT 109\n" +
                "R\n" +
                "4:00pm - 4:50pm\n" +
                "HSSB 1211\n" +
                "5 / 13\n" +
                "PSTAT 109\n" +
                "T\n" +
                "6:00pm - 6:50pm\n" +
                "SSMS 1304\n" +
                "0 / 13\n" +
                "PSTAT 109\n" +
                "R\n" +
                "6:00pm - 6:50pm\n" +
                "PHELP2514\n" +
                "0 / 13\n" +
                "PSTAT 109\n" +
                "T\n" +
                "6:00pm - 6:50pm\n" +
                "SSMS 1302\n" +
                "0 / 13\n" +
                "PSTAT 109\n" +
                "R\n" +
                "6:00pm - 6:50pm\n" +
                "PHELP1508\n" +
                "0 / 13\n" +
                "PSTAT 120A\n" +
                "T R\n" +
                "11:00am - 12:15pm\n" +
                "CHEM 1171\n" +
                "85 / 85\n" +
                "PSTAT 120A\n" +
                "W\n" +
                "2:00pm - 2:50pm\n" +
                "HSSB 2251\n" +
                "21 / 21\n" +
                "PSTAT 120A\n" +
                "W\n" +
                "10:00am - 10:50am\n" +
                "PHELP1508\n" +
                "21 / 21\n" +
                "PSTAT 120A\n" +
                "W\n" +
                "3:00pm - 3:50pm\n" +
                "HSSB 1215\n" +
                "21 / 21\n" +
                "PSTAT 120A\n" +
                "T\n" +
                "4:00pm - 4:50pm\n" +
                "HSSB 1232\n" +
                "22 / 22\n" +
                "PSTAT 120A\n" +
                "T R\n" +
                "12:30pm - 1:45pm\n" +
                "HSSB 1174\n" +
                "73 / 80\n" +
                "PSTAT 120A\n" +
                "T\n" +
                "5:00pm - 5:50pm\n" +
                "HSSB 1237\n" +
                "20 / 20\n" +
                "PSTAT 120A\n" +
                "T\n" +
                "6:00pm - 6:50pm\n" +
                "GIRV 2120\n" +
                "20 / 20\n" +
                "PSTAT 120A\n" +
                "R\n" +
                "8:00 am - 8:50 am\n" +
                "HSSB 1233\n" +
                "13 / 20\n" +
                "PSTAT 120A\n" +
                "W\n" +
                "4:00pm - 4:50pm\n" +
                "GIRV 2119\n" +
                "20 / 20\n" +
                "PSTAT 120A\n" +
                "T R\n" +
                "5:00pm - 6:15pm\n" +
                "BUCHN1930\n" +
                "23 / 85\n" +
                "PSTAT 120A\n" +
                "W\n" +
                "9:00 am - 9:50 am\n" +
                "HSSB 1223\n" +
                "7 / 21\n" +
                "PSTAT 120A\n" +
                "R\n" +
                "10:00am - 10:50am\n" +
                "PHELP2536\n" +
                "4 / 21\n" +
                "PSTAT 120A\n" +
                "R\n" +
                "11:00am - 11:50am\n" +
                "HSSB 1231\n" +
                "4 / 21\n" +
                "PSTAT 120A\n" +
                "W\n" +
                "7:00pm - 7:50pm\n" +
                "GIRV 2108\n" +
                "8 / 22\n" +
                "PSTAT 120B\n" +
                "M W\n" +
                "11:00am - 12:15pm\n" +
                "NH 1006\n" +
                "90 / 90\n" +
                "PSTAT 120B\n" +
                "M\n" +
                "4:00pm - 4:50pm\n" +
                "HSSB 1233\n" +
                "22 / 22\n" +
                "PSTAT 120B\n" +
                "T\n" +
                "10:00am - 10:50am\n" +
                "PHELP2532\n" +
                "22 / 22\n" +
                "PSTAT 120B\n" +
                "T\n" +
                "2:00pm - 2:50pm\n" +
                "HSSB 1233\n" +
                "23 / 23\n" +
                "PSTAT 120B\n" +
                "M\n" +
                "3:00pm - 3:50pm\n" +
                "HSSB 1207\n" +
                "23 / 23\n" +
                "PSTAT 120B\n" +
                "M W\n" +
                "2:00pm - 3:15pm\n" +
                "PSYCH1924\n" +
                "73 / 90\n" +
                "PSTAT 120B\n" +
                "W\n" +
                "10:00am - 10:50am\n" +
                "PHELP2536\n" +
                "22 / 22\n" +
                "PSTAT 120B\n" +
                "T\n" +
                "12:00pm - 12:50pm\n" +
                "PHELP2532\n" +
                "19 / 22\n" +
                "PSTAT 120B\n" +
                "M\n" +
                "6:00pm - 6:50pm\n" +
                "GIRV 2120\n" +
                "9 / 23\n" +
                "PSTAT 120B\n" +
                "T\n" +
                "5:00pm - 5:50pm\n" +
                "HSSB 2251\n" +
                "23 / 23\n" +
                "PSTAT 122\n" +
                "T R\n" +
                "12:30pm - 1:45pm\n" +
                "HSSB 1173\n" +
                "75 / 75\n" +
                "PSTAT 122\n" +
                "W\n" +
                "9:00 am - 9:50 am\n" +
                "SSMS 1303\n" +
                "25 / 25\n" +
                "PSTAT 122\n" +
                "W\n" +
                "10:00am - 10:50am\n" +
                "SSMS 1304\n" +
                "25 / 25\n" +
                "PSTAT 122\n" +
                "W\n" +
                "5:00pm - 5:50pm\n" +
                "SSMS 1302\n" +
                "25 / 25\n" +
                "PSTAT 126\n" +
                "T R\n" +
                "2:00pm - 3:15pm\n" +
                "BUCHN1930\n" +
                "75 / 75\n" +
                "PSTAT 126\n" +
                "W\n" +
                "6:00pm - 6:50pm\n" +
                "SSMS 1302\n" +
                "25 / 25\n" +
                "PSTAT 126\n" +
                "W\n" +
                "11:00am - 11:50am\n" +
                "SSMS 1303\n" +
                "25 / 25\n" +
                "PSTAT 126\n" +
                "R\n" +
                "8:00 am - 8:50 am\n" +
                "SSMS 1303\n" +
                "25 / 25\n" +
                "PSTAT 126\n" +
                "M W\n" +
                "11:00am - 12:15pm\n" +
                "HSSB 1173\n" +
                "75 / 75\n" +
                "PSTAT 126\n" +
                "T\n" +
                "9:00 am - 9:50 am\n" +
                "SSMS 1302\n" +
                "25 / 25\n" +
                "PSTAT 126\n" +
                "T\n" +
                "10:00am - 10:50am\n" +
                "SSMS 1302\n" +
                "25 / 25\n" +
                "PSTAT 126\n" +
                "T\n" +
                "6:00pm - 6:50pm\n" +
                "SSMS 1301\n" +
                "25 / 25\n" +
                "PSTAT 130\n" +
                "M W\n" +
                "12:30pm - 1:45pm\n" +
                "SSMS 1303\n" +
                "40 / 40\n" +
                "PSTAT 130\n" +
                "M\n" +
                "5:00pm - 5:50pm\n" +
                "SSMS 1303\n" +
                "20 / 20\n" +
                "PSTAT 130\n" +
                "T\n" +
                "11:00am - 11:50am\n" +
                "SSMS 1302\n" +
                "20 / 20\n" +
                "PSTAT 130\n" +
                "M W\n" +
                "9:30 am - 10:45am\n" +
                "SSMS 1302\n" +
                "40 / 40\n" +
                "PSTAT 130\n" +
                "M\n" +
                "6:00pm - 6:50pm\n" +
                "SSMS 1302\n" +
                "20 / 20\n" +
                "PSTAT 130\n" +
                "T\n" +
                "4:00pm - 4:50pm\n" +
                "SSMS 1302\n" +
                "20 / 20\n" +
                "PSTAT 131\n" +
                "T R\n" +
                "5:00pm - 6:15pm\n" +
                "HSSB 1173\n" +
                "60 / 60\n" +
                "PSTAT 131\n" +
                "W\n" +
                "11:00am - 11:50am\n" +
                "SSMS 1304\n" +
                "20 / 20\n" +
                "PSTAT 131\n" +
                "W\n" +
                "12:00pm - 12:50pm\n" +
                "PHELP1513\n" +
                "20 / 20\n" +
                "PSTAT 131\n" +
                "W\n" +
                "6:00pm - 6:50pm\n" +
                "SSMS 1303\n" +
                "20 / 20\n" +
                "PSTAT 160A\n" +
                "T R\n" +
                "11:00am - 12:15pm\n" +
                "HSSB 1174\n" +
                "75 / 75\n" +
                "PSTAT 160A\n" +
                "T\n" +
                "4:00pm - 4:50pm\n" +
                "HSSB 1233\n" +
                "25 / 25\n" +
                "PSTAT 160A\n" +
                "T\n" +
                "3:00pm - 3:50pm\n" +
                "HSSB 1232\n" +
                "25 / 25\n" +
                "PSTAT 160A\n" +
                "W\n" +
                "8:00 am - 8:50 am\n" +
                "HSSB 1224\n" +
                "25 / 25\n" +
                "PSTAT 160A\n" +
                "T R\n" +
                "3:30pm - 4:45pm\n" +
                "PSYCH1902\n" +
                "75 / 75\n" +
                "PSTAT 160A\n" +
                "F\n" +
                "9:00 am - 9:50 am\n" +
                "HSSB 1236\n" +
                "25 / 25\n" +
                "PSTAT 160A\n" +
                "F\n" +
                "12:00pm - 12:50pm\n" +
                "HSSB 1231\n" +
                "25 / 25\n" +
                "PSTAT 160A\n" +
                "F\n" +
                "1:00pm - 1:50pm\n" +
                "HSSB 1215\n" +
                "25 / 25\n" +
                "PSTAT 171\n" +
                "M W\n" +
                "8:00 am - 9:15 am\n" +
                "BUCHN1930\n" +
                "70 / 80\n" +
                "PSTAT 171\n" +
                "M\n" +
                "5:00pm - 5:50pm\n" +
                "GIRV 2128\n" +
                "20 / 20\n" +
                "PSTAT 171\n" +
                "T\n" +
                "8:00 am - 8:50 am\n" +
                "HSSB 1227\n" +
                "11 / 20\n" +
                "PSTAT 171\n" +
                "M\n" +
                "2:00pm - 2:50pm\n" +
                "GIRV 2127\n" +
                "20 / 20\n" +
                "PSTAT 171\n" +
                "M\n" +
                "1:00pm - 1:50pm\n" +
                "387 104\n" +
                "19 / 20\n" +
                "PSTAT 171\n" +
                "T R\n" +
                "2:00pm - 3:15pm\n" +
                "CHEM 1171\n" +
                "80 / 80\n" +
                "PSTAT 171\n" +
                "T\n" +
                "5:00pm - 5:50pm\n" +
                "GIRV 2128\n" +
                "20 / 20\n" +
                "PSTAT 171\n" +
                "W\n" +
                "1:00pm - 1:50pm\n" +
                "GIRV 2127\n" +
                "20 / 20\n" +
                "PSTAT 171\n" +
                "W\n" +
                "7:00pm - 7:50pm\n" +
                "GIRV 2112\n" +
                "20 / 20\n" +
                "PSTAT 171\n" +
                "W\n" +
                "3:00pm - 3:50pm\n" +
                "HSSB 1232\n" +
                "20 / 20\n" +
                "PSTAT 174\n" +
                "M W\n" +
                "2:00pm - 3:15pm\n" +
                "BUCHN1930\n" +
                "60 / 60\n" +
                "PSTAT 174\n" +
                "T\n" +
                "11:00am - 11:50am\n" +
                "SSMS 1301\n" +
                "20 / 20\n" +
                "PSTAT 174\n" +
                "T\n" +
                "8:00 am - 8:50 am\n" +
                "SSMS 1302\n" +
                "20 / 20\n" +
                "PSTAT 174\n" +
                "T\n" +
                "7:00pm - 7:50pm\n" +
                "SSMS 1302\n" +
                "0 / 20\n" +
                "PSTAT 174\n" +
                "W\n" +
                "1:00pm - 1:50pm\n" +
                "PHELP1513\n" +
                "20 / 20\n" +
                "PSTAT 175\n" +
                "T R\n" +
                "8:00 am - 9:15 am\n" +
                "BUCHN1930\n" +
                "75 / 75\n" +
                "PSTAT 175\n" +
                "F\n" +
                "10:00am - 10:50am\n" +
                "HSSB 1232\n" +
                "25 / 25\n" +
                "PSTAT 175\n" +
                "F\n" +
                "11:00am - 11:50am\n" +
                "PHELP1425\n" +
                "25 / 25\n" +
                "PSTAT 175\n" +
                "F\n" +
                "12:00pm - 12:50pm\n" +
                "HSSB 1232\n" +
                "25 / 25\n" +
                "PSTAT 182T\n" +
                "T\n" +
                "6:30pm - 7:45pm\n" +
                "PHELP2514\n" +
                "3 / 30\n" +
                "PSTAT 193\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "0 / 10\n" +
                "PSTAT 195\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "6 / 6\n" +
                "PSTAT 199\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "0 / 10\n" +
                "TMP 111\n" +
                "T\n" +
                "5:00pm - 7:50pm\n" +
                "EMBARHALL\n" +
                "210 / 210\n" +
                "TMP 120\n" +
                "M W\n" +
                "3:00pm - 4:40pm\n" +
                "PHELP2516\n" +
                "45 / 45\n" +
                "TMP 120\n" +
                "M W\n" +
                "4:00pm - 5:40pm\n" +
                "PHELP3505\n" +
                "43 / 45\n" +
                "TMP 120\n" +
                "M W\n" +
                "5:00pm - 6:40pm\n" +
                "PHELP2516\n" +
                "33 / 35\n" +
                "TMP 122\n" +
                "T R\n" +
                "2:00pm - 3:50pm\n" +
                "PHELP1260\n" +
                "44 / 45\n" +
                "TMP 134\n" +
                "T R\n" +
                "3:30pm - 4:45pm\n" +
                "IV THEA1\n" +
                "169 / 300\n" +
                "TMP 191IN\n" +
                "R\n" +
                "5:00pm - 6:30pm\n" +
                "ELNGS1601\n" +
                "0 / 25\n" +
                "THTR 2B\n" +
                "M W\n" +
                "9:30 am - 10:45am\n" +
                "EMBARHALL\n" +
                "99 / 136\n" +
                "THTR 2B\n" +
                "M\n" +
                "8:00 am - 8:50 am\n" +
                "PHELP1448\n" +
                "6 / 34\n" +
                "THTR 2B\n" +
                "W\n" +
                "8:00 am - 8:50 am\n" +
                "GIRV 2112\n" +
                "26 / 34\n" +
                "THTR 2B\n" +
                "M\n" +
                "11:00am - 11:50am\n" +
                "PHELP2516\n" +
                "33 / 34\n" +
                "THTR 2B\n" +
                "W\n" +
                "11:00am - 11:50am\n" +
                "PHELP2516\n" +
                "34 / 34\n" +
                "THTR 2B\n" +
                "T\n" +
                "9:00 am - 9:50 am\n" +
                "GIRV 1116\n" +
                "0 / 34\n" +
                "THTR 2B\n" +
                "R\n" +
                "9:00 am - 9:50 am\n" +
                "GIRV 2112\n" +
                "0 / 34\n" +
                "THTR 5\n" +
                "T R\n" +
                "9:00 am - 10:50am\n" +
                "TD-E 1115\n" +
                "1 / 6\n" +
                "THTR 5\n" +
                "T R\n" +
                "1:00pm - 2:50pm\n" +
                "TD-E 1115\n" +
                "0 / 6\n" +
                "THTR 5\n" +
                "M W\n" +
                "11:00am - 12:50pm\n" +
                "TD-E 1115\n" +
                "0 / 6\n" +
                "THTR 5\n" +
                "M W\n" +
                "1:00pm - 2:50pm\n" +
                "TD-E 1115\n" +
                "1 / 6\n" +
                "THTR 5\n" +
                "T R\n" +
                "11:00am - 12:50pm\n" +
                "TD-E 1115\n" +
                "0 / 6\n" +
                "THTR 5\n" +
                "M W\n" +
                "9:00 am - 10:50am\n" +
                "TD-E 1115\n" +
                "1 / 6\n" +
                "THTR 5\n" +
                "M W\n" +
                "3:00pm - 4:50pm\n" +
                "TD-E 1115\n" +
                "1 / 6\n" +
                "THTR 5\n" +
                "T R\n" +
                "3:00pm - 4:50pm\n" +
                "TD-E 1115\n" +
                "5 / 6\n" +
                "THTR 9\n" +
                "T R\n" +
                "1:00pm - 2:50pm\n" +
                "TD-E 1101\n" +
                "41 / 40\n" +
                "THTR 10A\n" +
                "M W\n" +
                "12:30pm - 1:50pm\n" +
                "TD-W 1703\n" +
                "0 / 25\n" +
                "THTR 11A\n" +
                "M W\n" +
                "11:00am - 12:20pm\n" +
                "TD-W 1507\n" +
                "0 / 25\n" +
                "THTR 15A\n" +
                "T R\n" +
                "11:00am - 12:50pm\n" +
                "TD-W 1507\n" +
                "0 / 25\n" +
                "THTR 19\n" +
                "T R\n" +
                "11:00am - 12:50pm\n" +
                "TD-W 1530\n" +
                "8 / 42\n" +
                "THTR 22\n" +
                "T R\n" +
                "3:00pm - 4:50pm\n" +
                "TD-W 1530\n" +
                "11 / 20\n" +
                "THTR 29A\n" +
                "T R\n" +
                "1:00pm - 2:50pm\n" +
                "TD-E 1119\n" +
                "4 / 4\n" +
                "THTR 29A\n" +
                "T R\n" +
                "3:00pm - 4:50pm\n" +
                "TD-E 1119\n" +
                "4 / 4\n" +
                "THTR 29B\n" +
                "T R\n" +
                "1:00pm - 2:50pm\n" +
                "TD-E 0302\n" +
                "5 / 4\n" +
                "THTR 29B\n" +
                "T R\n" +
                "3:00pm - 4:50pm\n" +
                "TD-E 0302\n" +
                "4 / 4\n" +
                "THTR 29C\n" +
                "T R\n" +
                "1:00pm - 2:50pm\n" +
                "TD-E 1313\n" +
                "6 / 6\n" +
                "THTR 29C\n" +
                "T R\n" +
                "3:00pm - 4:50pm\n" +
                "TD-E 1313\n" +
                "6 / 6\n" +
                "THTR 29D\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "0 / 20\n" +
                "THTR 42\n" +
                "T\n" +
                "10:00am - 10:50am\n" +
                "TD-E 2609\n" +
                "1 / 10\n" +
                "THTR 42\n" +
                "F\n" +
                "6:00pm - 11:50pm\n" +
                "EMBARHALL\n" +
                "1 / 10\n" +
                "THTR 49\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "0 / 100\n" +
                "THTR 94\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "0 / 20\n" +
                "THTR 95\n" +
                "W\n" +
                "2:00pm - 3:50pm\n" +
                "HSSB 1105\n" +
                "8 / 8\n" +
                "THTR 104B\n" +
                "T R\n" +
                "11:00am - 12:50pm\n" +
                "HSSB 1105\n" +
                "6 / 8\n" +
                "THTR 110A\n" +
                "M W\n" +
                "11:00am - 12:20pm\n" +
                "TD-W 1703\n" +
                "0 / 25\n" +
                "THTR 111A\n" +
                "M W\n" +
                "9:00 am - 10:40am\n" +
                "TD-W 1507\n" +
                "0 / 25\n" +
                "THTR 112\n" +
                "M W\n" +
                "2:00pm - 3:50pm\n" +
                "TD-W 1507\n" +
                "0 / 20\n" +
                "THTR 123\n" +
                "T R\n" +
                "1:00pm - 2:50pm\n" +
                "TD-W 1530\n" +
                "2 / 20\n" +
                "THTR 140B\n" +
                "T R\n" +
                "3:00pm - 4:50pm\n" +
                "TD-W 1703\n" +
                "0 / 18\n" +
                "THTR 142\n" +
                "T\n" +
                "10:00am - 10:50am\n" +
                "TD-E 2609\n" +
                "5 / 10\n" +
                "THTR 142\n" +
                "F\n" +
                "6:00pm - 11:50pm\n" +
                "EMBARHALL\n" +
                "5 / 10\n" +
                "THTR 149\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "0 / 100\n" +
                "THTR 151A\n" +
                "T R\n" +
                "3:00pm - 4:50pm\n" +
                "TD-W 1507\n" +
                "0 / 25\n" +
                "THTR 151F\n" +
                "W\n" +
                "10:00am - 11:50am\n" +
                "TD-E 1101\n" +
                "0 / 20\n" +
                "THTR 151F\n" +
                "F\n" +
                "10:00am - 12:50pm\n" +
                "TD-E 1101\n" +
                "0 / 20\n" +
                "THTR 152A\n" +
                "T R\n" +
                "11:00am - 12:50pm\n" +
                "TD-E 1101\n" +
                "14 / 20\n" +
                "THTR 152D\n" +
                "M W\n" +
                "4:00pm - 5:50pm\n" +
                "TD-E 1101\n" +
                "0 / 20\n" +
                "THTR 153P\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "0 / 30\n" +
                "THTR 180A\n" +
                "M W\n" +
                "12:30pm - 1:45pm\n" +
                "WEBB 1100\n" +
                "43 / 60\n" +
                "THTR 188S\n" +
                "T R\n" +
                "3:30pm - 4:45pm\n" +
                "HSSB 1174\n" +
                "24 / 60\n" +
                "THTR 190\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "0 / 10\n" +
                "THTR 193H\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "0 / 5\n" +
                "THTR 193HA\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "0 / 6\n" +
                "THTR 194D\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "0 / 10\n" +
                "THTR 195\n" +
                "W\n" +
                "2:00pm - 3:50pm\n" +
                "HSSB 1105\n" +
                "14 / 16\n" +
                "THTR 195P\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "0 / 10\n" +
                "THTR 196\n" +
                "T R\n" +
                "5:00pm - 6:50pm\n" +
                "HSSB 1105\n" +
                "1 / 15\n" +
                "THTR 199\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "0 / 20\n" +
                "WRIT 1\n" +
                "M W\n" +
                "10:00am - 11:50am\n" +
                "HSSB 1231\n" +
                "5 / 4\n" +
                "WRIT 1\n" +
                "M W\n" +
                "12:00pm - 1:50pm\n" +
                "PHELP1517\n" +
                "5 / 5\n" +
                "WRIT 1\n" +
                "M W\n" +
                "8:00 am - 9:50 am\n" +
                "HSSB 1231\n" +
                "5 / 5\n" +
                "WRIT 1\n" +
                "M W\n" +
                "3:00pm - 4:50pm\n" +
                "HSSB 1231\n" +
                "5 / 5\n" +
                "WRIT 1\n" +
                "M W\n" +
                "12:00pm - 1:50pm\n" +
                "HSSB 1236\n" +
                "5 / 5\n" +
                "WRIT 1\n" +
                "M W\n" +
                "4:00pm - 5:50pm\n" +
                "PHELP1517\n" +
                "5 / 5\n" +
                "WRIT 1E\n" +
                "T R\n" +
                "9:00 am - 10:50am\n" +
                "GIRV 2108\n" +
                "0 / 5\n" +
                "WRIT 1E\n" +
                "T R\n" +
                "8:00 am - 9:50 am\n" +
                "HSSB 1237\n" +
                "0 / 5\n" +
                "WRIT 1E\n" +
                "M W\n" +
                "4:00pm - 5:50pm\n" +
                "HSSB 1237\n" +
                "0 / 5\n" +
                "WRIT 2\n" +
                "T R\n" +
                "1:00pm - 2:50pm\n" +
                "HSSB 1237\n" +
                "5 / 5\n" +
                "WRIT 2\n" +
                "M W\n" +
                "9:00 am - 10:50am\n" +
                "GIRV 2108\n" +
                "5 / 5\n" +
                "WRIT 2\n" +
                "T R\n" +
                "8:00 am - 9:50 am\n" +
                "PHELP1513\n" +
                "5 / 5\n" +
                "WRIT 2\n" +
                "M W\n" +
                "8:00 am - 9:50 am\n" +
                "387 103\n" +
                "0 / 5\n" +
                "WRIT 2\n" +
                "T R\n" +
                "4:00pm - 5:50pm\n" +
                "GIRV 2129\n" +
                "5 / 5\n" +
                "WRIT 2\n" +
                "T R\n" +
                "2:00pm - 3:50pm\n" +
                "PHELP1448\n" +
                "5 / 5\n" +
                "WRIT 2\n" +
                "M W\n" +
                "12:00pm - 1:50pm\n" +
                "PSY-E1806\n" +
                "0 / 5\n" +
                "WRIT 2\n" +
                "T R\n" +
                "2:00pm - 3:50pm\n" +
                "GIRV 2120\n" +
                "0 / 5\n" +
                "WRIT 2\n" +
                "M W\n" +
                "8:00 am - 9:50 am\n" +
                "GIRV 2115\n" +
                "0 / 5\n" +
                "WRIT 2\n" +
                "M W\n" +
                "9:00 am - 10:50am\n" +
                "LSB 1101\n" +
                "5 / 5\n" +
                "WRIT 2\n" +
                "T R\n" +
                "12:00pm - 1:50pm\n" +
                "LSB 1101\n" +
                "0 / 5\n" +
                "WRIT 2\n" +
                "T R\n" +
                "8:00 am - 9:50 am\n" +
                "GIRV 2115\n" +
                "5 / 5\n" +
                "WRIT 2\n" +
                "M W\n" +
                "11:00am - 12:50pm\n" +
                "NH 1111\n" +
                "5 / 5\n" +
                "WRIT 2\n" +
                "M W\n" +
                "5:00pm - 6:50pm\n" +
                "GIRV 2124\n" +
                "5 / 5\n" +
                "WRIT 2\n" +
                "M W\n" +
                "1:00pm - 2:50pm\n" +
                "HSSB 1227\n" +
                "5 / 5\n" +
                "WRIT 2\n" +
                "T R\n" +
                "8:00 am - 9:50 am\n" +
                "SSMS 1005\n" +
                "0 / 5\n" +
                "WRIT 2\n" +
                "M W\n" +
                "5:00pm - 6:50pm\n" +
                "HSSB 1233\n" +
                "0 / 5\n" +
                "WRIT 2\n" +
                "T R\n" +
                "3:00pm - 4:50pm\n" +
                "HSSB 1206\n" +
                "5 / 5\n" +
                "WRIT 2\n" +
                "T R\n" +
                "10:00am - 11:50am\n" +
                "GIRV 2119\n" +
                "5 / 5\n" +
                "WRIT 2\n" +
                "T R\n" +
                "2:00pm - 3:50pm\n" +
                "HSSB 2251\n" +
                "0 / 5\n" +
                "WRIT 2\n" +
                "M W\n" +
                "9:00 am - 10:50am\n" +
                "NH 1111\n" +
                "0 / 5\n" +
                "WRIT 2\n" +
                "T R\n" +
                "12:00pm - 1:50pm\n" +
                "SSMS 1302\n" +
                "0 / 5\n" +
                "WRIT 2\n" +
                "T R\n" +
                "1:00pm - 2:50pm\n" +
                "PHELP1525\n" +
                "0 / 5\n" +
                "WRIT 2\n" +
                "T R\n" +
                "11:00am - 12:50pm\n" +
                "PHELP1526\n" +
                "5 / 5\n" +
                "WRIT 2\n" +
                "T R\n" +
                "2:00pm - 3:50pm\n" +
                "GIRV 2129\n" +
                "0 / 5\n" +
                "WRIT 2\n" +
                "T R\n" +
                "11:00am - 12:50pm\n" +
                "HSSB 1210\n" +
                "0 / 5\n" +
                "WRIT 2\n" +
                "T R\n" +
                "2:00pm - 3:50pm\n" +
                "PHELP1517\n" +
                "0 / 5\n" +
                "WRIT 2\n" +
                "T R\n" +
                "1:00pm - 2:50pm\n" +
                "SH 1609\n" +
                "5 / 5\n" +
                "WRIT 2\n" +
                "M W\n" +
                "4:00pm - 5:50pm\n" +
                "GIRV 2108\n" +
                "0 / 5\n" +
                "WRIT 2\n" +
                "T R\n" +
                "3:30pm - 5:20pm\n" +
                "GIRV 2123\n" +
                "0 / 5\n" +
                "WRIT 2\n" +
                "M W\n" +
                "2:00pm - 3:50pm\n" +
                "GIRV 2124\n" +
                "5 / 5\n" +
                "WRIT 2\n" +
                "M W\n" +
                "5:00pm - 6:50pm\n" +
                "PHELP1525\n" +
                "0 / 5\n" +
                "WRIT 2\n" +
                "T R\n" +
                "5:00pm - 6:50pm\n" +
                "PHELP1425\n" +
                "0 / 5\n" +
                "WRIT 2\n" +
                "T R\n" +
                "1:00pm - 2:50pm\n" +
                "PHELP1526\n" +
                "5 / 5\n" +
                "WRIT 2E\n" +
                "T R\n" +
                "2:00pm - 3:50pm\n" +
                "SSMS 1304\n" +
                "5 / 5\n" +
                "WRIT 2E\n" +
                "T R\n" +
                "4:00pm - 5:50pm\n" +
                "SSMS 1304\n" +
                "5 / 5\n" +
                "WRIT 2E\n" +
                "M W\n" +
                "4:00pm - 5:50pm\n" +
                "SSMS 1005\n" +
                "3 / 5\n" +
                "WRIT 50\n" +
                "M W\n" +
                "10:00am - 11:50am\n" +
                "PHELP1517\n" +
                "25 / 25\n" +
                "WRIT 50E\n" +
                "T R\n" +
                "2:00pm - 3:50pm\n" +
                "GIRV 2115\n" +
                "25 / 25\n" +
                "WRIT 99\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "0 / 0\n" +
                "WRIT 105C\n" +
                "T R\n" +
                "9:30 am - 10:45am\n" +
                "GIRV 2120\n" +
                "25 / 25\n" +
                "WRIT 105C\n" +
                "M W\n" +
                "12:30pm - 1:45pm\n" +
                "387 103\n" +
                "25 / 25\n" +
                "WRIT 105G\n" +
                "M W\n" +
                "8:00 am - 9:15 am\n" +
                "SH 1432\n" +
                "25 / 25\n" +
                "WRIT 105M\n" +
                "T R\n" +
                "5:00pm - 6:15pm\n" +
                "SSMS 1005\n" +
                "25 / 25\n" +
                "WRIT 105PD\n" +
                "M W\n" +
                "11:00am - 12:15pm\n" +
                "HSSB 1215\n" +
                "16 / 25\n" +
                "WRIT 105PD\n" +
                "M W\n" +
                "12:30pm - 1:45pm\n" +
                "HSSB 1215\n" +
                "15 / 25\n" +
                "WRIT 105PS\n" +
                "M W\n" +
                "12:30pm - 1:45pm\n" +
                "SH 1432\n" +
                "25 / 25\n" +
                "WRIT 105PS\n" +
                "M W\n" +
                "11:00am - 12:15pm\n" +
                "387 104\n" +
                "25 / 25\n" +
                "WRIT 105S\n" +
                "M W\n" +
                "8:00 am - 9:15 am\n" +
                "SSMS 1005\n" +
                "1 / 25\n" +
                "WRIT 105S\n" +
                "M W\n" +
                "9:30 am - 10:45am\n" +
                "SSMS 1005\n" +
                "16 / 25\n" +
                "WRIT 105WC\n" +
                "T R\n" +
                "9:30 am - 10:45am\n" +
                "PHELP1529\n" +
                "25 / 25\n" +
                "WRIT 105WE\n" +
                "M W\n" +
                "11:00am - 12:15pm\n" +
                "387 103\n" +
                "25 / 25\n" +
                "WRIT 105WE\n" +
                "T R\n" +
                "9:30 am - 10:45am\n" +
                "SH 1609\n" +
                "25 / 25\n" +
                "WRIT 105WE\n" +
                "T R\n" +
                "11:00am - 12:15pm\n" +
                "GIRV 2124\n" +
                "25 / 25\n" +
                "WRIT 107A\n" +
                "M W\n" +
                "11:00am - 12:15pm\n" +
                "SH 1432\n" +
                "25 / 25\n" +
                "WRIT 107B\n" +
                "T R\n" +
                "2:00pm - 3:15pm\n" +
                "387 104\n" +
                "25 / 25\n" +
                "WRIT 107B\n" +
                "T R\n" +
                "8:00 am - 9:15 am\n" +
                "SH 1432\n" +
                "25 / 25\n" +
                "WRIT 107B\n" +
                "T R\n" +
                "8:00 am - 9:15 am\n" +
                "GIRV 2120\n" +
                "25 / 25\n" +
                "WRIT 107B\n" +
                "T R\n" +
                "11:00am - 12:15pm\n" +
                "SSMS 1007\n" +
                "25 / 25\n" +
                "WRIT 107B\n" +
                "T R\n" +
                "9:30 am - 10:45am\n" +
                "SSMS 1007\n" +
                "25 / 25\n" +
                "WRIT 107B\n" +
                "M W\n" +
                "3:30pm - 4:45pm\n" +
                "GIRV 2129\n" +
                "25 / 25\n" +
                "WRIT 107B\n" +
                "T R\n" +
                "11:00am - 12:15pm\n" +
                "GIRV 2108\n" +
                "25 / 25\n" +
                "WRIT 107B\n" +
                "T R\n" +
                "12:30pm - 1:45pm\n" +
                "HSSB 1233\n" +
                "25 / 25\n" +
                "WRIT 107G\n" +
                "T R\n" +
                "12:30pm - 1:45pm\n" +
                "SSMS 1304\n" +
                "25 / 25\n" +
                "WRIT 107J\n" +
                "M W\n" +
                "3:30pm - 4:45pm\n" +
                "SSMS 1007\n" +
                "25 / 25\n" +
                "WRIT 107J\n" +
                "M W\n" +
                "11:00am - 12:15pm\n" +
                "SSMS 1005\n" +
                "25 / 25\n" +
                "WRIT 107M\n" +
                "M W\n" +
                "12:30pm - 1:45pm\n" +
                "SSMS 1005\n" +
                "25 / 25\n" +
                "WRIT 107P\n" +
                "T R\n" +
                "11:00am - 12:15pm\n" +
                "PHELP1530\n" +
                "25 / 25\n" +
                "WRIT 107P\n" +
                "M W\n" +
                "2:00pm - 3:15pm\n" +
                "SSMS 1005\n" +
                "25 / 25\n" +
                "WRIT 107T\n" +
                "T R\n" +
                "12:30pm - 1:45pm\n" +
                "PHELP1517\n" +
                "25 / 25\n" +
                "WRIT 109ED\n" +
                "T R\n" +
                "9:30 am - 10:45am\n" +
                "GIRV 2129\n" +
                "12 / 25\n" +
                "WRIT 109ED\n" +
                "M W\n" +
                "12:30pm - 1:45pm\n" +
                "PHELP2514\n" +
                "12 / 25\n" +
                "WRIT 109ED\n" +
                "T R\n" +
                "2:00pm - 3:15pm\n" +
                "GIRV 2124\n" +
                "25 / 25\n" +
                "WRIT 109F\n" +
                "T R\n" +
                "2:00pm - 3:15pm\n" +
                "SSMS 1005\n" +
                "25 / 25\n" +
                "WRIT 109HP\n" +
                "T R\n" +
                "12:30pm - 1:45pm\n" +
                "SSMS 1005\n" +
                "25 / 25\n" +
                "WRIT 109HP\n" +
                "T R\n" +
                "11:00am - 12:15pm\n" +
                "SH 1432\n" +
                "25 / 25\n" +
                "WRIT 109HP\n" +
                "T R\n" +
                "12:30pm - 1:45pm\n" +
                "SH 1432\n" +
                "25 / 25\n" +
                "WRIT 109HU\n" +
                "M W\n" +
                "2:00pm - 3:15pm\n" +
                "PHELP1517\n" +
                "25 / 25\n" +
                "WRIT 109HU\n" +
                "M W\n" +
                "5:00pm - 6:15pm\n" +
                "SSMS 1007\n" +
                "0 / 25\n" +
                "WRIT 109SS\n" +
                "M W\n" +
                "11:00am - 12:15pm\n" +
                "PHELP2514\n" +
                "14 / 25\n" +
                "WRIT 109SS\n" +
                "M W\n" +
                "9:30 am - 10:45am\n" +
                "PHELP1526\n" +
                "14 / 25\n" +
                "WRIT 109SS\n" +
                "M W\n" +
                "11:00am - 12:15pm\n" +
                "PHELP1526\n" +
                "22 / 25\n" +
                "WRIT 109SS\n" +
                "M W\n" +
                "11:00am - 12:15pm\n" +
                "SSMS 1007\n" +
                "19 / 25\n" +
                "WRIT 109SS\n" +
                "M W\n" +
                "5:00pm - 6:15pm\n" +
                "HSSB 1227\n" +
                "2 / 25\n" +
                "WRIT 109SS\n" +
                "M W\n" +
                "3:30pm - 4:45pm\n" +
                "HSSB 1227\n" +
                "11 / 25\n" +
                "WRIT 109SS\n" +
                "M W\n" +
                "2:00pm - 3:15pm\n" +
                "PHELP1529\n" +
                "24 / 25\n" +
                "WRIT 109ST\n" +
                "T R\n" +
                "11:00am - 12:15pm\n" +
                "387 104\n" +
                "25 / 25\n" +
                "WRIT 109ST\n" +
                "T R\n" +
                "3:30pm - 4:45pm\n" +
                "SSMS 1007\n" +
                "25 / 25\n" +
                "WRIT 109ST\n" +
                "T R\n" +
                "5:00pm - 6:15pm\n" +
                "SSMS 1007\n" +
                "25 / 25\n" +
                "WRIT 109V\n" +
                "T R\n" +
                "12:30pm - 1:45pm\n" +
                "NH 1109\n" +
                "25 / 25\n" +
                "WRIT 199\n" +
                "\n" +
                "\n" +
                "T B A\n" +
                "0 / 10\n");
                */


        String est = courses.get(0).name;
        TextView tryharder = (TextView) findViewById(R.id.testfirebase);
        tryharder.setText(est);

        //TextView attempt2 = (TextView)findViewById(R.id.testfirebase);
        //attempt2.setText("test");

    }

}




