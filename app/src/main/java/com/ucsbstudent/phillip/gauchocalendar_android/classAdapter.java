package com.ucsbstudent.phillip.gauchocalendar_android;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import java.util.ArrayList;

/**
 * Created by Phillip on 6/2/2016.
 */
public class classAdapter extends RecyclerView.Adapter<classHolder> {

    Context c;
    ArrayList<LectureOrSection> classes;
    ArrayList<LectureOrSection> checkedclasses = new ArrayList<>();

    public classAdapter(Context c, ArrayList<LectureOrSection> classes){
        this.c = c;
        this.classes = classes;
    }

    @Override
    public classHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.modelclasses,null);
        classHolder holder = new classHolder(v);
        return holder;
    }

    //DATA IS BOUND TO VIEWS
    @Override
    public void onBindViewHolder(classHolder holder, int postion){
        holder.name.setText(classes.get(postion).getNamofLS());
        holder.days.setText(classes.get(postion).getDaysOfWeek());
        holder.time.setText(classes.get(postion).getTimeofDay());
        holder.location.setText(classes.get(postion).getClassRoom());

        holder.setClassClickListener(new classClickListener() {
            @Override
            public void onItemClick(View v, int pos) {
                CheckBox chk = (CheckBox) v;

                //CHECK IF ITS CHECKED OR NOT
                if(chk.isChecked()){
                    checkedclasses.add(classes.get(pos));
                } else if(!chk.isChecked()){
                    checkedclasses.remove(classes.get(pos));
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return classes.size();
    }

}
