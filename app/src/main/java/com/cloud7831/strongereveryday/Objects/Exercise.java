package com.cloud7831.strongereveryday.Objects;

import com.cloud7831.strongereveryday.Data.WorkoutContract;

public class Exercise {
    // Exercise objects contain all the information about a particular exercise.
    // Exercises can be used in multiple workouts (for example, you might want benchpress
    // in both the "Chest" and "Full Body" WorkoutSets.


    private String exerciseName; // Name of the exercise.
    private String userNotes; // Notes that the user has written for this exercise.
    // Integers for Sets, Reps, and Weight.

    private int numSets; // How many sets the user aims for each workout.
    private int repsPerSet; // Current amount of reps per set the user should aim for. Should be in between the min and max.
    private int minRepsPerSet; // The minimum amount of reps a user should aim for with this exercise.
    private int maxRepsPerSet; // The maximum amount of reps a user should aim for with this exercise.
    private int weightPerSet; // Current weight per set the user uses. Note that this has int typing, but needs to store 0.5 lbs increments.
    private int maxWeightAchieved; // The most the user has been able to use for this exercise.

    public Exercise(String exerciseName){
        //TODO: use the exercise name to lookup the exercise info from filesystem
        this.exerciseName = exerciseName;
    }


    /* ----------------------------------- Getters and Setters ----------------------------------- */

    public String getExerciseName() {
        return exerciseName;
    }

    public String getUserNotes() {
        return userNotes;
    }

    public int getNumSets() {
        return numSets;
    }

    public int getRepsPerSet() {
        return repsPerSet;
    }

    public int getMinRepsPerSet() {
        return minRepsPerSet;
    }

    public int getMaxRepsPerSet() {
        return maxRepsPerSet;
    }

    public int getWeightPerSet() {
        return weightPerSet;
    }

    public int getMaxWeightAchieved() {
        return maxWeightAchieved;
    }

    public void setExerciseName(String exerciseName) {
        this.exerciseName = exerciseName;
    }

    public void setUserNotes(String userNotes) {
        this.userNotes = userNotes;
    }

    public void setNumSets(int numSets) {
        this.numSets = numSets;
    }

    public void setRepsPerSet(int repsPerSet) {
        this.repsPerSet = repsPerSet;
    }

    public void setMinRepsPerSet(int minRepsPerSet) {
        this.minRepsPerSet = minRepsPerSet;
    }

    public void setMaxRepsPerSet(int maxRepsPerSet) {
        this.maxRepsPerSet = maxRepsPerSet;
    }

    public void setWeightPerSet(int weightPerSet) {
        // Note: weight is stored as an int, but needs to account for 0.5 lbs.
        // Therefore, multiply everything by 2, and then divide when you need to use it.
        this.weightPerSet = weightPerSet * 2;
    }

    public void setMaxWeightAchieved(int maxWeightAchieved) {
        // Note: weight is stored as an int, but needs to account for 0.5 lbs.
        // Therefore, multiply everything by 2, and then divide when you need to use it.
        this.maxWeightAchieved = maxWeightAchieved * 2;
    }
}
