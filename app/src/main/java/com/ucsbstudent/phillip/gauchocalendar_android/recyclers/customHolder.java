package com.ucsbstudent.phillip.gauchocalendar_android.recyclers;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.ucsbstudent.phillip.gauchocalendar_android.R;

/**
 * Created by Phillip on 6/3/2016.
 */
public class customHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
    TextView Title, location, starttime, endtime;
    Button btn;

    com.ucsbstudent.phillip.gauchocalendar_android.recyclers.customClickListener customClickListener;

    public customHolder(View item){
        super(item);

        Title = (TextView)item.findViewById(R.id.nameofEvent);
        location = (TextView)item.findViewById(R.id.nameofLocation);
        starttime = (TextView)item.findViewById(R.id.DateandTime);
        endtime = (TextView)item.findViewById(R.id.DateandTime2);

        btn.setOnClickListener(this);
    }

    public void setCustomClickListener(customClickListener ic){
        this.customClickListener=ic;
    }

    @Override
    public void onClick(View view){
        this.customClickListener.onItemDelete(view,getLayoutPosition());}

}


