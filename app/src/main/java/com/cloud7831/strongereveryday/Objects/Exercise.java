package com.cloud7831.strongereveryday.Objects;

import android.app.Activity;
import android.util.Log;

import com.cloud7831.strongereveryday.Data.JSONUtils;
import com.cloud7831.strongereveryday.Data.WorkoutContract;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileInputStream;

public class Exercise {
    // Exercise objects contain all the information about a particular exercise.
    // Exercises can be used in multiple workouts (for example, you might want benchpress
    // in both the "Chest" and "Full Body" WorkoutSets.


    // Default values for an Exercise object if they were left unspecified.
    private static final int NUM_SETS_DEFAULT = 3;

    private static final int REPS_PER_SET_DEFAULT = 10;
    private static final int MIN_REPS_DEFAULT = 6;
    private static final int MAX_REPS_DEFAULT = 15;

    private static final double WEIGHT_PER_SET_DEFAULT = 10.0;





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

    private int score;              // Some exercises have a score associated with them.

    public Exercise lookupExercise(Activity activity, String filename){
        // Retrieve the exercise's JSON data from the file.
        JSONObject exerciseJSON = JSONUtils.loadJSON(activity, filename);

        // Convert from JSON to exercise.









        //        //TODO: use the exercise name to lookup the exercise info from filesystem
//        this.exerciseName = exerciseName;
//
//
//        //TODO: Delete this later. This is just dummy data for testing
//        numSets = 3;
//        repsPerSet = new int[]{12, 10, 8};
//        weightPerSet = new double[]{162.5, 50, 50};
//        maxRepsPerSet = 15;
//        score = 1000;


        return new Exercise();
    }

    public Exercise(){

    }

    public Exercise(String exerciseName, Integer completed, Integer score, String notes, Integer numSets, Integer[] repsPerSet, Double[] weights, Integer maxReps, Integer minReps, Double maxWeight){

        this.exerciseName = exerciseName;
        userNotes = notes;
        if(numSets == null){
            this.numSets = NUM_SETS_DEFAULT;
        }
        else{
            this.numSets = numSets;
        }



        //TODO: use the exercise name to lookup the exercise info from filesystem


        //TODO: Delete this later. This is just dummy data for testing
        numSets = 3;
        repsPerSet = new int[]{12, 10, 8};
        weightPerSet = new double[]{162.5, 50, 50};
        maxRepsPerSet = 15;
        score = 1000;
    }

    private double roundWeight(double weight){
        // Used to make weights into increments of 0.5 lbs.
        return Math.round(weight*2)/2;
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

    public int getScore(){
        return score;
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
}
