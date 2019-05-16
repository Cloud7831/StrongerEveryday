package com.cloud7831.strongereveryday.Data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ExerciseDbHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "StrongerEveryday.db";

    public ExerciseDbHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db){

        //TODO: I probably need a total of three tables.
        //TODO: one for the workouts, one for the exercises and one for the workout data.

        //TODO: how do I deal with the problem of not knowing how many columns will be in my database?
        // Like, I have a workout (say chest) and I have X exercises. How do I know how many columns I'll need?
        // Not only that, it seems super wasteful if I have one workout with
        //TODO: make a table for storing the MyFit scores, plus weight, etc.
        //TODO: I think it's best to do the workouts/excercise data as a json. That way it can scale how I want it to.


        //String SQL_CREATE_WORKOUTS_TABLE =

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1){

    }

}
