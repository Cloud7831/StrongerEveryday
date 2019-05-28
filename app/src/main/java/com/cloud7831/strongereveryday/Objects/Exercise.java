package com.cloud7831.strongereveryday.Objects;

import com.cloud7831.strongereveryday.Data.WorkoutContract;

public class Exercise {
    // Exercise objects contain all the information about a particular exercise.
    // Exercises can be used in multiple workouts (for example, you might want benchpress
    // in both the "Chest" and "Full Body" WorkoutSets.


    private String exerciseName; // Name of the exercise.
    private String userNotes; // Notes that the user has written for this exercise.
    // Integers for Sets, Reps, and Weight.

    // Sets --------------------------
    private int numSets;            // How many sets the user aims for each workout.
    // Reps --------------------------
    private int[] repsPerSet;       // Current amount of reps per set the user should aim for. Should be in between the min and max.
    private int minRepsPerSet;      // The minimum amount of reps a user should aim for with this exercise.
    private int maxRepsPerSet;      // The maximum amount of reps a user should aim for with this exercise.
    // Weight ------------------------
    private double[] weightPerSet;  // Current weight per set the user uses. Note that only 0.5 lbs increments are allowed.
    private double maxWeight;       // The most the user has been able to use for this exercise.

    public Exercise(String exerciseName){
        //TODO: use the exercise name to lookup the exercise info from filesystem
        this.exerciseName = exerciseName;


        //TODO: Delete this later. This is just dummy data for testing
        numSets = 3;
        repsPerSet = new int[]{12, 10, 8};
        weightPerSet = new double[]{62.5, 50, 50};
        maxRepsPerSet = 15;
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

    public int[] getRepsPerSet() {
        return repsPerSet;
    }

    public int getMinRepsPerSet() {
        return minRepsPerSet;
    }

    public int getMaxRepsPerSet() {
        return maxRepsPerSet;
    }

    public double[] getWeightPerSet() {
        return weightPerSet;
    }

    public double getMaxWeight() {
        return maxWeight;
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

    public void setRepsPerSet(int[] repsPerSet) {
        this.repsPerSet = repsPerSet;
    }

    public void setMinRepsPerSet(int minRepsPerSet) {
        this.minRepsPerSet = minRepsPerSet;
    }

    public void setMaxRepsPerSet(int maxRepsPerSet) {
        this.maxRepsPerSet = maxRepsPerSet;
    }

    public void setWeightPerSet(double[] weight) {
        // Note: weight is stored in 0.5 lbs increments.
        for (int i = 0; i<weight.length;i++){
            // Make everything into nice 0.5 increments.
            weightPerSet[i] = roundWeight(weight[i]);
        }
    }

    public void setMaxWeight(double maxWeightAchieved) {
        maxWeight = roundWeight(maxWeightAchieved);
    }
    /* --------------------------------- Getters and Setters End --------------------------------- */

    private double roundWeight(double weight){
        // Used to make weights into increments of 0.5 lbs.
        return Math.round(weight*2)/2;
    }
}
