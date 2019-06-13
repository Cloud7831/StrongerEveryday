package com.cloud7831.strongereveryday.OnClickListeners;

import android.view.View;
import android.widget.TextView;

import com.cloud7831.strongereveryday.Objects.Exercise;

public class ExerciseNotesToggleOnClickListener implements View.OnClickListener {
    Exercise exercise;
    TextView view;
    public ExerciseNotesToggleOnClickListener(Exercise e, TextView v){
        exercise = e;
        view = v;
    }

    @Override
    public void onClick(View v){

        // Toggle the visibility of the exercise's notes.
        if(exercise.getAreNotesVisible()){
            // Notes are visible so hide them.
            exercise.setAreNotesVisible(false);
            view.setVisibility(View.GONE);
        }
        else{
            exercise.setAreNotesVisible(true);
            view.setVisibility(View.VISIBLE);
        }

    }
}
