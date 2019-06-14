package com.cloud7831.strongereveryday.Objects;

public class WorkoutSet {
    // A workout set is a collection of exercises.
    // A workout set can be user made, but there are also some basic ones, such as Abs, Chest, etc.

    private int daysLastCompleted; // The amount of days that have gone by since this workout set has been completed.
    private String workoutName; // E.g. Abs, Chest, Legs, etc.
    private int colour; // Colour of the WorkoutSet when displayed in a list.
//    private ExerciseID[] exercises;

    public WorkoutSet(String name, int days){
        // TODO: Create a database to store the basic info of WorkoutSets so that they can appear in the Workout List.
        // TODO: WorkoutSets themselves contain more information (Which exercises are in them, etc)
        // TODO: that doesn't need to be shown in a list.

        // TODO: A WorkoutSet is stored as a JSON.
        // TODO: A WorkoutSet contains a list of Exercises.
        // TODO: A WorkoutSet can contain a WorkoutSet (can't be recursive, though.)


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
