package com.ucsbstudent.phillip.gauchocalendar_android;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

/**
 * Created by Phillip on 6/2/2016.
 */
public class classHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    TextView name,days,time,location;
    CheckBox chk;

    classClickListener classClickListener;

    public classHolder(View itemView){
        super(itemView);

        name = (TextView)itemView.findViewById(R.id.NameCourse);
        days = (TextView)itemView.findViewById(R.id.weekdays);
        time = (TextView)itemView.findViewById(R.id.timeDay);
        location = (TextView)itemView.findViewById(R.id.loc);
        chk = (CheckBox)itemView.findViewById(R.id.chk);

        chk.setOnClickListener(this);
    }

    public void setClassClickListener(classClickListener ic){
        this.classClickListener=ic;
    }

    @Override
    public void onClick(View v) {
        this.classClickListener.onItemClick(v,getLayoutPosition());
    }
}
