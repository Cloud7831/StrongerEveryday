package com.cloud7831.strongereveryday.ItemCards;

import com.cloud7831.strongereveryday.Data.WorkoutContract;
import com.cloud7831.strongereveryday.Data.WorkoutContract.WorkoutCardType;
import com.cloud7831.strongereveryday.Objects.Exercise;

public class ExerciseItemCard implements WorkoutItemCard {

    private String name;
    private Exercise exer;
    private int numSets;
    private int[] reps;
    private double[] weights;


    public ExerciseItemCard(Exercise exercise){
        name = exercise.getExerciseName();
        exer = exercise;
        numSets = exercise.getNumSets();
    }


    @Override
    public WorkoutCardType getType() {
        return WorkoutCardType.EXERCISE;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean hasName() {
        // An exercise card always has a name.
        return true;
    }

    public int getScore(){
        return exer.getScore();
    }

    public int getNumSets(){
        return numSets;
    }

    public Exercise getExercise() {
        return exer;
    }

    public int[] getReps() {
        return reps;
    }

    public double[] getWeights() {
        return weights;
    }
}
