package com.cloud7831.strongereveryday.HelperClasses;

import android.app.Activity;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.cloud7831.strongereveryday.Objects.WorkoutSet;
import com.cloud7831.strongereveryday.R;

import java.util.ArrayList;

public class WorkoutSetAdapter extends ArrayAdapter<WorkoutSet> {

    public WorkoutSetAdapter(Activity context, ArrayList<WorkoutSet> workouts){
        super(context, 0, workouts);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        View listItemView = convertView;
        if(listItemView == null){
            //false because we don't want to attach the list item view to the parent list view just yet.
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.workout_list_item, parent, false);
        }

        WorkoutSet currentWorkout = getItem(position);



        //Find the view where we want to store the Default Translation. Using list_item.xml.
        TextView workoutNameTextView = (TextView) listItemView.findViewById(R.id.workout_name_text_view);
        workoutNameTextView.setText(currentWorkout.getWorkoutName());

        //TODO: finish the rest of this.
//
//        ImageView iconView = (ImageView) listItemView.findViewById(R.id.image);
//        if(currentWord.hasImage()) {
//            iconView.setVisibility(View.VISIBLE);
//            iconView.setImageResource(currentWord.getImageResourceID());
//        }
//        else{
//            iconView.setVisibility(View.GONE);
//        }
//
//        View textContainer = (View) listItemView.findViewById(R.id.text_container);
//
//        textContainer.setBackgroundColor(ContextCompat.getColor(getContext(), colour));
//


        return listItemView;//We only return one view, and that's listItem view which contains all the other views we want.
    }


}
