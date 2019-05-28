package com.cloud7831.strongereveryday.Data;

import android.net.Uri;

public final class WorkoutContract {

    public static final String CONTENT_AUTHORITY = "com.cloud7831.strongereveryday";
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);
    public static final String PATH_WORKOUT = "workout";
    public static final Uri WORKOUT_CONTENT_URI = Uri.withAppendedPath(BASE_CONTENT_URI, PATH_WORKOUT);

    public enum WorkoutCardType{
        EXERCISE,
        VIDEO,
        ENDCARD;
    }

}
