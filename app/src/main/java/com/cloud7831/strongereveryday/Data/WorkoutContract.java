package com.cloud7831.strongereveryday.Data;

import android.content.ContentResolver;
import android.net.Uri;
import android.provider.BaseColumns;

public final class WorkoutContract {

    public static final String CONTENT_AUTHORITY = "com.cloud7831.strongereveryday";
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);
    public static final String PATH_WORKOUTS = "workouts";

    public static abstract class WorkoutEntry implements BaseColumns {
        public static final Uri WORKOUT_CONTENT_URI = Uri.withAppendedPath(BASE_CONTENT_URI, PATH_WORKOUTS);

        /**
         * The MIME type of the {@link #WORKOUT_CONTENT_URI} for a list of workouts.
         */
        public static final String CONTENT_LIST_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_WORKOUTS;

        /**
         * The MIME type of the {@link #WORKOUT_CONTENT_URI} for a single pet.
         */
        public static final String CONTENT_ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_WORKOUTS;


        public final static String TABLE_NAME = "workouts";

        public final static String _ID = BaseColumns._ID;
        public final static String WORKOUT_NAME = "name";
        public final static String WORKOUT_LAST_COMPLETED = "last completed";

    }

    public enum WorkoutCardType{
        EXERCISE,
        TITLE,
        ADD_EXERCISE,
        VIDEO,
        ENDCARD;
    }

}
