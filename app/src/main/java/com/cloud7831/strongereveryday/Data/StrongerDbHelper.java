package com.cloud7831.strongereveryday.Data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.cloud7831.strongereveryday.Data.WorkoutContract.WorkoutEntry;

public class StrongerDbHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "StrongerEveryday.db";

    public StrongerDbHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db){

        //TODO: I probably need a total of two tables.
        //TODO: one for the workouts, and one for the exercises.

        //TODO: make a table for storing the MyFit scores, plus weight, etc.


        String SQL_CREATE_WORKOUTS_TABLE = "CREATE TABLE " + WorkoutEntry.TABLE_NAME + "{"
                                                           + WorkoutEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                                                           + WorkoutEntry.WORKOUT_NAME + "TEXT NOT NULL, "
                                                           + WorkoutEntry.WORKOUT_JSON  + "TEXT NOT NULL"
                                                           + WorkoutEntry.WORKOUT_LAST_COMPLETED + "INTEGER NOT NULL DEFAULT -1);"; // Last completed = -1 days means it has never been completed.
        db.execSQL(SQL_CREATE_WORKOUTS_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1){

    }

}
