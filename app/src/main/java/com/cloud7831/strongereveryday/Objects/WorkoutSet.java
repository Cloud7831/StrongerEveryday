package com.cloud7831.strongereveryday.Objects;

import com.cloud7831.strongereveryday.R;

public class WorkoutSet {
    // A workout set is a collection of exercises.
    // A workout set can be user made, but there are also some basic ones, such as Abs, Chest, etc.

    private int daysLastCompleted; // The amount of days that have gone by since this workout set has been completed.
    private String workoutName; // E.g. Abs, Chest, Legs, etc.
    private int colour; // Colour of the WorkoutSet when displayed in a list.
//    private ExerciseID[] exercises;

    public WorkoutSet(String name, int days, int colour){
        daysLastCompleted = days;
        workoutName = name;
        this.colour = colour;

    }

    public String getWorkoutName(){
        return workoutName;
    }

    public int getDaysLastCompleted(){
        return daysLastCompleted;
    }

}
