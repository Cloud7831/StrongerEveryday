package com.cloud7831.strongereveryday.Objects;

public class WorkoutSet {
    // A workout set is a collection of exercises.
    // A workout set can be user made, but there are also some basic ones, such as Abs, Chest, etc.

    private int daysLastCompleted; // The amount of days that have gone by since this workout set has been completed.
    private String workoutName; // E.g. Abs, Chest, Legs, etc.
//    private ExerciseID[] exercises;

    public WorkoutSet(String name, int days){
        daysLastCompleted = days;
        workoutName = name;
    }

    public String getWorkoutName(){
        return workoutName;
    }

    public int getDaysLastCompleted(){
        return daysLastCompleted;
    }

}
