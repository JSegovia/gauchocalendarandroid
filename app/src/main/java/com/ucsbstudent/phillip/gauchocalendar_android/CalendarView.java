package com.ucsbstudent.phillip.gauchocalendar_android;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;
import com.ucsbstudent.phillip.gauchocalendar_android.recyclers.InputEvents;

import java.util.ArrayList;
import java.util.Random;


public class CalendarView extends AppCompatActivity implements View.OnClickListener {

    //private static final int MY_BUTTON = 9000;
    ArrayList<CustomEventClass> customarraypass = new ArrayList<>();
    ArrayList<LectureOrSection> classarraypass = new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar_view);


        DisplayMetrics displaymetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        int height = displaymetrics.heightPixels;
        int width = displaymetrics.widthPixels;


        final ArrayList<CustomEventClass> customarray = getIntent().getParcelableArrayListExtra("custom");
        ArrayList<LectureOrSection> classarray = getIntent().getParcelableArrayListExtra("classes");
        //To-DO get array from class selection

        customarraypass = customarray;
        classarraypass = classarray;

        RelativeLayout useit = (RelativeLayout) findViewById(R.id.relativeLayout242);
        LinearLayout ist = (LinearLayout)findViewById(R.id.listeventsboxes);

        int bw = width / 8;
        int min30 = height / 30;
        int min15 = height / 60;
        int hour = height / 15;


        for (int i = 0; i < customarray.size(); i++) {
            Random rand = new Random();
            int r = rand.nextInt(255);
            int g = rand.nextInt(255);
            int b = rand.nextInt(255);
            int randomColor = Color.rgb(r,g,b);


            final String name = customarray.get(i).getEventTitle();
            Button btn = new Button(this);

            String init = "";
            if(name.length() > 2){
                init = name.substring(0,1);
            }else{
                init = name;
            }
            btn.setText(init);

            final String location = customarray.get(i).getLocation();
            final String weekday = customarray.get(i).getWeekday();
            final int shr = (customarray.get(i).getShour())+1;
            final int smin = customarray.get(i).getSmin();

            final String sampm = customarray.get(i).getSampm();
            final int ehr = (customarray.get(i).getEhour())+1;
            final int emin = customarray.get(i).getEmin();
            final String eampm = customarray.get(i).getEampm();
            btn.setOnClickListener(new View.OnClickListener() {
                //AlertDialog.Builder alertbox = new AlertDialog.Builder(CalendarView.this);
                @Override
                public void onClick(View v) {
                    String mins;
                    if (smin == 0){
                        mins = "00";
                    }else{
                        mins = Integer.toString(smin);
                    }
                    String mine;
                    if (emin == 0){
                        mine = "00";
                    }else {
                        mine = Integer.toString(emin);
                    }
                    AlertDialog alertDialog = new AlertDialog.Builder(CalendarView.this).create(); //Read Update
                    alertDialog.setTitle(name);
                    alertDialog.setMessage(location + "\n" + weekday + "\n"
                    + "Start: " + shr +":" + mins + " " + sampm + "\n"
                    + "End: " + ehr + ":" + mine + " " + eampm);

                    alertDialog.show();
                }
            });

            String text = location + "\n" + weekday + "\n"
                    + "Start: " + shr +":" + smin + " " + sampm + "\n"
                    + "End: " + ehr + ":" + emin + " " + eampm;
            Button big = new Button(this);
            big.setText(text);
            big.setBackgroundColor(randomColor);

            LinearLayout.LayoutParams p = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT);
            ist.addView(big,p);


            btn.setBackgroundColor(randomColor);


            int weeknumber = customarray.get(i).getWeekdayInt();
            weeknumber++;

            float hournumber = customarray.get(i).getStartime();
            int start = (int) hournumber;
            int theShiftL = weeknumber * bw;
            int theShiftT = (hour * (start)) + (start * 2) + hour;

            if (start == 11) {
                theShiftT = 0;
            }
            if (start == 23) {
                theShiftT = 12 * hour + 24;
            }

            int pixel;
            int minS = customarray.get(i).getSmin();
            if (minS == 0) {
                pixel = 0;
            } else if (minS != 0 && minS < 15) {
                pixel = (((height / 30) / 2) / 2);
            } else if (15 <= minS && minS < 30) {
                pixel = ((height / 30) / 2);
            } else if (30 <= minS && minS < 45) {
                pixel = (height / 30);
            } else if (45 <= minS && minS < 55) {
                pixel = ((3 * (height / 30)) / 4);
            } else {
                pixel = hour;
            }
            theShiftT = theShiftT + pixel;


            //^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^6
            float hourend = customarray.get(i).getEndtime();
            int end = (int) hourend;
            int theShiftB = (hour * (end)) + (end * 2) + hour;

            if (end == 11) {
                theShiftB = 0;
            }
            if (end == 23) {
                theShiftB = 12 * hour + 24;
            }

            int pixelB;
            int minE = customarray.get(i).getEmin();
            if (minE == 0) {
                pixelB = 0;
            } else if (minE != 0 && minE <= 15) {
                pixelB = (((height / 30) / 2) / 2);
            } else if (15 < minE && minE < 30) {
                pixelB = ((height / 30) / 2);
            } else if (30 <= minE && minE <= 45) {
                pixelB = (height / 30);
            } else if (45 < minE && minE <= 55) {
                pixelB = ((3 * (height / 30)) / 4);
            } else {
                pixelB = hour;
            }

            theShiftB = theShiftB + pixelB;

            int sizeb = theShiftB - theShiftT;


            RelativeLayout.LayoutParams paramss = new RelativeLayout.LayoutParams(bw, sizeb);
            paramss.leftMargin = theShiftL;
            paramss.topMargin = theShiftT;
            paramss.bottomMargin = theShiftB;


            useit.addView(btn, paramss);

        }





        for (int j = 0; j < classarray.size(); j++) {

            Random rand = new Random();
            int r = rand.nextInt(255);
            int g = rand.nextInt(255);
            int b = rand.nextInt(255);
            int randomColor = Color.rgb(r,g,b);


            String name = classarray.get(j).getNamofLS();
            String init = "";
            if(name.length() > 2){
                init = name.substring(0,1);
            }else{
                init = name;
            }


            String name1 = classarray.get(j).getNamofLS();
            Button btn = new Button(this);
            btn.setText(init);
            btn.setBackgroundColor(randomColor);

            String name2 = classarray.get(j).getNamofLS();
            Button btn2 = new Button(this);
            btn2.setText(init);
            btn2.setBackgroundColor(randomColor);

            String name3 = classarray.get(j).getNamofLS();
            Button btn3 = new Button(this);
            btn3.setText(init);
            btn3.setBackgroundColor(randomColor);

            String name4 = classarray.get(j).getNamofLS();
            Button btn4 = new Button(this);
            btn4.setText(init);
            btn4.setBackgroundColor(randomColor);

            String name5 = classarray.get(j).getNamofLS();
            Button btn5 = new Button(this);
            btn5.setText(init);
            btn5.setBackgroundColor(randomColor);


            String weekdays = classarray.get(j).getDaysOfWeek();

            int theShiftL = 0;
            int theShiftL2 = 0;
            int theShiftL3 = 0;
            int theShiftL4 = 0;
            int theShiftL5 = 0;


            int theShiftB = 0;
            int theShiftT = 0;

            int weeknumber1 = 0;
            int weeknumber2 = 0;
            int weeknumber3 = 0;
            int weeknumber4 = 0;
            int weeknumber5 = 0;

            if (weekdays.contains("M")) {
                weeknumber1 = 2;
            }
            if (weekdays.contains("T")) {
                weeknumber2 = 3;
            }
            if (weekdays.contains("W")) {
                weeknumber3 = 4;
            }
            if (weekdays.contains("R")) {
                weeknumber4 = 5;
            }
            if (weekdays.contains("F")) {
                weeknumber5 = 6;
            }


            theShiftL = weeknumber1 * bw;
            theShiftL2 = weeknumber2 * bw;
            theShiftL3 = weeknumber3 * bw;
            theShiftL4 = weeknumber4 * bw;
            theShiftL5 = weeknumber5 * bw;


            float hournumber = classarray.get(j).getStartTime();
            int start = (int) hournumber;
            theShiftT = (hour * (start)) + (start * 2);
            if (start == 11) {
                theShiftT = 0;
            }
            if (start == 23) {
                theShiftT = 12 * hour + 24;
            }


            //^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
            float hourend = classarray.get(j).getEndTime();
            int end = (int) hourend;
            theShiftB = (hour * (end)) + (end * 2) + hour;

            if (end == 11) {
                theShiftB = 0;
            }
            if (end == 23) {
                theShiftB = 12 * hour + 24;
            }

            RelativeLayout.LayoutParams params1 = new RelativeLayout.LayoutParams(bw, ViewGroup.LayoutParams.MATCH_PARENT);
            params1.leftMargin = theShiftL;
            params1.topMargin = theShiftT;
            params1.bottomMargin = theShiftB;

            RelativeLayout.LayoutParams params2 = new RelativeLayout.LayoutParams(bw, ViewGroup.LayoutParams.MATCH_PARENT);
            params2.leftMargin = theShiftL2;
            params2.topMargin = theShiftT;
            params2.bottomMargin = theShiftB;

            RelativeLayout.LayoutParams params3 = new RelativeLayout.LayoutParams(bw, ViewGroup.LayoutParams.MATCH_PARENT);
            params3.leftMargin = theShiftL3;
            params3.topMargin = theShiftT;
            params3.bottomMargin = theShiftB;

            RelativeLayout.LayoutParams params4 = new RelativeLayout.LayoutParams(bw, ViewGroup.LayoutParams.MATCH_PARENT);
            params4.leftMargin = theShiftL4;
            params4.topMargin = theShiftT;
            params4.bottomMargin = theShiftB;

            RelativeLayout.LayoutParams params5 = new RelativeLayout.LayoutParams(bw, ViewGroup.LayoutParams.MATCH_PARENT);
            params5.leftMargin = theShiftL5;
            params5.topMargin = theShiftT;
            params5.bottomMargin = theShiftB;

            final String LectorSect = classarray.get(j).getLectorSect();
            final String coursename = classarray.get(j).getNamofLS();
            final String classroom  = classarray.get(j).getClassRoom();
            final String daysofweek = classarray.get(j).getDaysOfWeek();
            final String timeofday  = classarray.get(j).getTimeofDay();



            String text = LectorSect + " " + coursename + "\n"
                    + daysofweek + " " + timeofday + "\n"
                    + classroom;
            Button big = new Button(this);
            big.setText(text);
            big.setBackgroundColor(randomColor);

            LinearLayout.LayoutParams p = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT);
            ist.addView(big,p);

            btn.setBackgroundColor(randomColor);


            if (weeknumber1 != 0) {
                btn.setText(init);
                btn.setOnClickListener(new View.OnClickListener() {
                    //AlertDialog.Builder alertbox = new AlertDialog.Builder(CalendarView.this);
                    @Override
                    public void onClick(View v) {
                        AlertDialog alertDialog = new AlertDialog.Builder(CalendarView.this).create(); //Read Update
                        alertDialog.setTitle(LectorSect + coursename);
                        alertDialog.setMessage(daysofweek + timeofday + "\n" + classroom);
                        alertDialog.show();
                    }
                });
                useit.addView(btn, params1);
            }
            if (weeknumber2 != 0) {
                btn2.setText(init);
                btn2.setOnClickListener(new View.OnClickListener() {
                    //AlertDialog.Builder alertbox = new AlertDialog.Builder(CalendarView.this);
                    @Override
                    public void onClick(View v) {
                        AlertDialog alertDialog = new AlertDialog.Builder(CalendarView.this).create(); //Read Update
                        alertDialog.setTitle(LectorSect + coursename);
                        alertDialog.setMessage(daysofweek + timeofday + "\n" + classroom);
                        alertDialog.show();
                    }
                });
                useit.addView(btn2, params2);
            }
            if (weeknumber3 != 0) {
                btn3.setText(init);
                btn3.setOnClickListener(new View.OnClickListener() {
                    //AlertDialog.Builder alertbox = new AlertDialog.Builder(CalendarView.this);
                    @Override
                    public void onClick(View v) {
                        AlertDialog alertDialog = new AlertDialog.Builder(CalendarView.this).create(); //Read Update
                        alertDialog.setTitle(LectorSect + coursename);
                        alertDialog.setMessage(daysofweek + timeofday + "\n" + classroom);
                        alertDialog.show();
                    }
                });
                useit.addView(btn3, params3);
            }
            if (weeknumber4 != 0) {
                btn4.setText(init);
                btn4.setOnClickListener(new View.OnClickListener() {
                    //AlertDialog.Builder alertbox = new AlertDialog.Builder(CalendarView.this);
                    @Override
                    public void onClick(View v) {
                        AlertDialog alertDialog = new AlertDialog.Builder(CalendarView.this).create(); //Read Update
                        alertDialog.setTitle(LectorSect + coursename);
                        alertDialog.setMessage(daysofweek + timeofday + "\n" + classroom);
                        alertDialog.show();
                    }
                });
                useit.addView(btn4, params4);
            }
            if (weeknumber5 != 0) {
                btn5.setText(init);
                btn5.setOnClickListener(new View.OnClickListener() {
                    //AlertDialog.Builder alertbox = new AlertDialog.Builder(CalendarView.this);
                    @Override
                    public void onClick(View v) {
                        AlertDialog alertDialog = new AlertDialog.Builder(CalendarView.this).create(); //Read Update
                        alertDialog.setTitle(LectorSect + ": " + coursename);
                        alertDialog.setMessage(daysofweek + timeofday + "\n" + classroom);
                        alertDialog.show();
                    }
                });
                useit.addView(btn5, params5);
            }


        }


    }

    @Override
    public void onClick(View v) {

    }

    public void reschedule(View v) {
        Intent intent = new Intent(this, InputEvents.class);
        intent.putParcelableArrayListExtra("custom", customarraypass);
        intent.putParcelableArrayListExtra("classes", classarraypass);
        startActivity(intent);
    }


}

