package com.ucsbstudent.phillip.gauchocalendar_android.recyclers;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.ucsbstudent.phillip.gauchocalendar_android.CustomEventClass;
import com.ucsbstudent.phillip.gauchocalendar_android.R;

import java.util.ArrayList;

/**
 * Created by Phillip on 6/3/2016.
 */
public class customAdapter extends RecyclerView.Adapter<customHolder>{

    Context c;
    ArrayList<CustomEventClass> personalevents;

    public customAdapter(Context c, ArrayList<CustomEventClass>personalevents){
        this.c = c;
        this.personalevents = personalevents;
    }

    public customAdapter(View.OnClickListener onClickListener, ArrayList<CustomEventClass> personalEvents) {
    }

    @Override
    public customHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.modelcustomevent,null);
        customHolder holder = new customHolder(v);
        return holder;
    }

    //DATA IS BOUND TO VIEWS
    @Override
    public void onBindViewHolder(customHolder holder, int position) {
        holder.Title.setText(personalevents.get(position).getEventTitle());
        holder.location.setText(personalevents.get(position).getLocation());
        String starting = personalevents.get(position).getShour() + ":" +
                personalevents.get(position).getSmin() + " " +
                personalevents.get(position).getSampm();
        holder.starttime.setText(starting);
        String ending = personalevents.get(position).getEhour() + ":" +
                personalevents.get(position).getEmin() + " " +
                personalevents.get(position).getEampm();
        holder.endtime.setText(ending);


        holder.setCustomClickListener(new customClickListener() {
            @Override
            public void onItemDelete(View v, int pos) {

                /*
                Button btn = (Button) v;
                if(btn.isPressed()){
                    personalevents.remove(personalevents.get(pos));
                }
                */
            }
        });

    }

    @Override
    public int getItemCount() {
        return personalevents.size();
    }
}
