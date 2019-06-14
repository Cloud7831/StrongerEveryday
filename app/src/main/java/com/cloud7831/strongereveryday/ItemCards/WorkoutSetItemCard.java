package com.cloud7831.strongereveryday.ItemCards;

public class WorkoutSetItemCard {
    // A WorkoutSetItemCard is used to show a WorkoutSet as a list item.
    // It does not contain the full information of a WorkoutSet, but instead
    // Only the information required in the WorkoutListFragment.
    // A WorkoutSet can be user made, but there are also some basic ones, such as Abs,
    // Chest, Legs, etc.

    private int daysLastCompleted; // The amount of days that have gone by since this workout set has been completed.
    private String workoutName; // E.g. Abs, Chest, Legs, etc.
    private int colour; // Colour of the WorkoutSet when displayed in a list.
//    private ExerciseID[] exercises;

    public WorkoutSetItemCard(String name, int days, int colour){
        // TODO: The info to create a WorkoutSetItemCard is stored in a database.

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