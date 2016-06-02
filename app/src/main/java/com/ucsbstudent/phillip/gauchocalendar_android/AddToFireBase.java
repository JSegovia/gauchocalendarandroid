package com.ucsbstudent.phillip.gauchocalendar_android;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import android.content.res.AssetManager;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;


public class AddToFireBase extends AppCompatActivity {

    ArrayList<Lecture> courses = new ArrayList<Lecture>();
    //private static final String FIREBASE_URL="https://sizzling-inferno-7789.firebaseIO.com";
    //private Firebase firebaseRef;
    String test = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_to_fire_base);
        ArrayList<Lecture> lectures = new ArrayList<>();
        //File sdcard = Environment.getExternalStorageDirectory();
        //File fileName = new File(sdcard,"courses.txt");


        Context context = getApplicationContext();

        String filePath = context.getFilesDir().getAbsolutePath();
        File fileName = new File(filePath, "courses");


        // The name of the file to open.
        //String fileName = "C:\\Users\\Phillip\\Android Projects\\gauchocalendarandroid\\app\\src\\main\\res\\raw\\courses.txt";

        // This will reference one line at a time
        String line = null;

        try {
            // FileReader reads text files in the default encoding.
         //   FileReader fileReader =
        //            new FileReader("courses");

            // Always wrap FileReader in BufferedReader.
            BufferedReader bufferedReader =
                    new BufferedReader(new InputStreamReader(getAssets().open("courses.txt")));


            for( int i =0; i < 4; i++){
                test += bufferedReader.readLine();

            }


           // string2Lect(bufferedReader);
            //while((line = bufferedReader.readLine()) != null) {
                //lectures.add(bufferedReader.readLine());
              //  StringBuilder = bufferedReader.readLine() * 5;

              //  string2Lect()
         //   }


            // Always close files.
            bufferedReader.close();
        }
        catch(FileNotFoundException ex) {
            System.out.println(
                    "Unable to open fileTHISTHITHITHIEHGEITH '" +
                            fileName + "'");
        }
        catch(IOException ex) {
            System.out.println(
                    "Error reading file '"
                            + fileName + "'");
            // Or we could just do this:
            // ex.printStackTrace();
        }

/*




        }*/
           // test = "WHO"  + "\n"  + "WHAT" + "\n" + "WHERE \n";
        //  String est = lectures.get(5).name;
            TextView tryharder = (TextView) findViewById(R.id.testfirebase);
            tryharder.setText("NOT PRESSED");



    /*

*/
        }

    public void makeit(View view){



        /* ArrayList<String> lectures = new ArrayList<String>();

        //File sdcard = Environment.getExternalStorageDirectory();
        //File fileName = new File(sdcard,"courses.txt");


        Context context = getApplicationContext();

        String filePath = context.getFilesDir().getAbsolutePath();
        File fileName = new File(filePath, "courses.txt");


        // The name of the file to open.
        //String fileName = "C:\\Users\\Phillip\\Android Projects\\gauchocalendarandroid\\app\\src\\main\\res\\raw\\courses.txt";

        // This will reference one line at a time
        String line = null;
            AssetManager am = getAssets();
            InputStreamReader ims = null;
            BufferedReader bufferedReader = null;
        try {
            // FileReader reads text files in the default encoding.
          //  FileReader fileReader =
           //         new FileReader(fileName);


            ims  = new InputStreamReader(am.open("courses.txt"), "UTF-8");
            bufferedReader = new BufferedReader(ims);


            // Always wrap FileReader in BufferedReader.
          //  InputStream ins = getResources().openRawResource(
         //           getResources().getIdentifier("courses",
           //                 "raw", getPackageName()));


         //   BufferedReader bufferedReader =
         //           new BufferedReader(new InputStreamReader(ins));

            while((line = bufferedReader.readLine()) != null) {
                lectures.add(line);
            }

            // Always close files.
            bufferedReader.close();
        }
        catch(FileNotFoundException ex) {
            System.out.println(
                    "Unable to open fileSECONDSECONSECOND '" +
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


            for (int i = 0; i < lectures.size(); i++) {
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

*/
           // String est = lectures.get(5);
            TextView tryharder = (TextView) findViewById(R.id.testfirebase);
            tryharder.setText(test);
        }






/*

                */








public ArrayList<Lecture> string2Lect(BufferedReader br) {
ArrayList<String> stringList = new ArrayList<String>();
//ArrayList<Lecture> finalList  = new ArrayList<Lecture>();
        while (br != null) {
            try {
                stringList.add(br.readLine());

            } catch (IOException e) {
                e.printStackTrace();
            }

        }
return parse(stringList);


}

public ArrayList<Lecture> parse(ArrayList<String> courses){
        ArrayList<Lecture> returnedList = new ArrayList<Lecture>();
    ArrayList<Section> sections = new ArrayList<Section>();

        Lecture newCourse;

        int index = 1; //Start at Anth 2
        String currentLect = courses.get(index); //set to Anth 2
        String lectDays;
        String lectTimes;
        String lectLocation;
        String lectOcc;
        String sectDays;
        String sectTimes;
        String sectLocation;
        String sectOcc;
        Boolean isSect = false;


        for(int i = 0; i < courses.size(); i++){
        if(isSect == false){
        lectDays = courses.get(index+5);
        lectTimes = courses.get(index+6);
        lectLocation = courses.get(index+7);
        lectOcc = courses.get(index+8);

        newCourse = new Lecture(currentLect,lectDays, lectTimes, lectLocation, lectOcc);
        returnedList.add(newCourse);


        if(index+11 < courses.size()){
        if(courses.get(index+11).equals(currentLect)){
        isSect = true;
        }
        else{
        currentLect = courses.get(index+11);
        }
        index = index+11;
        }

        }
        else {
            sectDays = courses.get(index + 5);
            sectTimes = courses.get(index + 6);
            sectLocation = courses.get(index + 7);
            sectOcc = courses.get(index + 8);

            Section newSection = new Section(sectDays, sectTimes, sectLocation, sectOcc);
            sections.add(newSection);

            if (index + 11 < courses.size()) {
                if (!(courses.get(index + 11).equals(currentLect))) {
                    isSect = false;
                    currentLect = courses.get(index + 11);
                }

                index = index + 11;
            }
        }}
return returnedList;}
}