package com.cloud7831.strongereveryday.Data;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;

import java.security.Provider;

public class StrongerEverydayProvider extends ContentProvider {

    private static final UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);
    private static final int WORKOUTS = 100;
    private static final int WORKOUT_ID = 101;
    static {
        /*
         * The calls to addURI() go here for all of the content URI patterns that the provider should recognize.
         */
        matcher.addURI(WorkoutContract.CONTENT_AUTHORITY, WorkoutContract.PATH_WORKOUTS, WORKOUTS);
        matcher.addURI(WorkoutContract.CONTENT_AUTHORITY, WorkoutContract.PATH_WORKOUTS + "/#", WORKOUT_ID);

    }


    public static final String LOG_TAG = StrongerEverydayProvider .class.getSimpleName();
    private StrongerDbHelper db;

    @Override
    public boolean onCreate(){
        db = new StrongerDbHelper(getContext());
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder){
        // Get readable database
        SQLiteDatabase database = db.getReadableDatabase();

        // This cursor will hold the result of the query
        Cursor cursor = null;

        // Figure out if the URI matcher can match the URI to a specific code
        int match = matcher.match(uri);
        switch (match) {
            case WORKOUTS:
                // For the WORKOUTS code, query the workouts table directly with the given
                // projection, selection, selection arguments, and sort order. The cursor
                // could contain multiple rows of the workouts table.

                cursor = database.query(WorkoutContract.WorkoutEntry.TABLE_NAME, projection, selection, selectionArgs,
                        null, null, sortOrder);

                break;
            case WORKOUT_ID:
                // For every "?" in the selection, we need to have an element in the selection
                // arguments that will fill in the "?". Since we have 1 question mark in the
                // selection, we have 1 String in the selection arguments' String array.
                selection = WorkoutContract.WorkoutEntry._ID + "=?";
                selectionArgs = new String[] { String.valueOf(ContentUris.parseId(uri)) };

                // This will perform a query on the workoutss table where the _id is extracted and return a
                // Cursor containing that row of the table.
                cursor = database.query(WorkoutContract.WorkoutEntry.TABLE_NAME, projection, selection, selectionArgs,
                        null, null, sortOrder);
                break;
            default:
                throw new IllegalArgumentException("Cannot query unknown URI " + uri);
        }
        // Set notification URI on the Cursor so we know
        // what content URI the Cursor was created for.
        // If the data at this URI changes, then we know we need to update the Cursor.
        cursor.setNotificationUri(getContext().getContentResolver(), uri);


        return cursor;
    }

    @Override
    public Uri insert(Uri uri, ContentValues contentValues) {
        final int match = matcher.match(uri);
        switch (match) {
            case WORKOUTS:
                return insertWorkout(uri, contentValues);
            default:
                throw new IllegalArgumentException("Insertion is not supported for " + uri);
        }
    }

    /**
     * Insert a workout into the database with the given content values. Return the new content URI
     * for that specific row in the database.
     */
    private Uri insertWorkout(Uri uri, ContentValues values) {

        sanityCheck(values);
        SQLiteDatabase database = db.getWritableDatabase();

        long id = database.insert(WorkoutContract.WorkoutEntry.TABLE_NAME, null, values);

        if (id == -1){
            Log.e(LOG_TAG, "Failed to insert row for " + uri);
            return null;
        }

        // Notify all listeners that the data has changed for the workout content URI
        getContext().getContentResolver().notifyChange(uri, null);

        // Once we know the ID of the new row in the table,
        // return the new URI with the ID appended to the end of it
        return ContentUris.withAppendedId(uri, id);
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        final int match = matcher.match(uri);
        switch (match) {
            case WORKOUTS:
                // Delete all rows that match the selection and selection args
                return deleteWorkout(uri, selection, selectionArgs);
            case WORKOUT_ID:
                // Delete a single row given by the ID in the URI
                selection = WorkoutContract.WorkoutEntry._ID + "=?";
                selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};
                return deleteWorkout(uri, selection, selectionArgs);
            default:
                throw new IllegalArgumentException("Deletion is not supported for " + uri);
        }
    }

    @Override
    public int update(Uri uri, ContentValues contentValues, String selection,
                      String[] selectionArgs) {
        final int match = matcher.match(uri);
        if(contentValues.size() == 0){
            // No reason to try to update if there's nothing to update.
            return 0;
        }
        switch (match) {
            case WORKOUTS:
                return updateWorkout(uri, contentValues, selection, selectionArgs);
            case WORKOUT_ID:
                // For the WORKOUT_ID code, extract out the ID from the URI,
                // so we know which row to update. Selection will be "_id=?" and selection
                // arguments will be a String array containing the actual ID.
                selection = WorkoutContract.WorkoutEntry._ID + "=?";
                selectionArgs = new String[] { String.valueOf(ContentUris.parseId(uri)) };
                return updateWorkout(uri, contentValues, selection, selectionArgs);
            default:
                throw new IllegalArgumentException("Update is not supported for " + uri);
        }
    }

    /**
     * Update workouts in the database with the given content values. Apply the changes to the rows
     * specified in the selection and selection arguments (which could be 0 or 1 or more workouts).
     * Return the number of rows that were successfully updated.
     */
    private int updateWorkout(Uri uri, ContentValues values, String selection, String[] selectionArgs) {

        updateSanityCheck(values);

        SQLiteDatabase database = db.getWritableDatabase();

        int rowsAltered = database.update(WorkoutContract.WorkoutEntry.TABLE_NAME, values, selection, selectionArgs);

        if(rowsAltered > 0){
            // Notify all listeners that the data has changed for the workout content URI
            getContext().getContentResolver().notifyChange(uri, null);
        }
        return rowsAltered;
    }

    /**
     * Delete workoutss in the database. Apply the changes to the rows
     * specified in the selection and selection arguments (which could be 0 or 1 or more workouts).
     * Return the number of rows that were successfully deleted.
     */
    private int deleteWorkout(Uri uri, String selection, String[] selectionArgs) {

        SQLiteDatabase database = db.getWritableDatabase();

        int rowsAltered = database.delete(WorkoutContract.WorkoutEntry.TABLE_NAME, selection, selectionArgs);

        if(rowsAltered > 0){
            // Notify all listeners that the data has changed for the pet content URI
            getContext().getContentResolver().notifyChange(uri, null);
        }
        return rowsAltered;
    }


    @Override
    public String getType(Uri uri) {
        final int match = matcher.match(uri);
        switch (match) {
            case WORKOUTS:
                return WorkoutContract.WorkoutEntry.CONTENT_LIST_TYPE;
            case WORKOUT_ID:
                return WorkoutContract.WorkoutEntry.CONTENT_ITEM_TYPE;
            default:
                throw new IllegalStateException("Unknown URI " + uri + " with match " + match);
        }
    }

    public boolean sanityCheck(ContentValues values){

        if(TextUtils.isEmpty(values.getAsString(WorkoutContract.WorkoutEntry.WORKOUT_NAME))){
            throw new IllegalArgumentException("Workout requires a name.");
        }
        if(TextUtils.isEmpty(values.getAsString(WorkoutContract.WorkoutEntry.WORKOUT_JSON))){
            throw new IllegalArgumentException("Workout requires a valid name for the json file.");
        }

        Integer lastCompleted = values.getAsInteger(WorkoutContract.WorkoutEntry.WORKOUT_LAST_COMPLETED);
        if(lastCompleted == null){
            throw new IllegalArgumentException("Last completed had a null value.");
        }

        return true;
    }

    public boolean updateSanityCheck(ContentValues values){

        if(values.containsKey(WorkoutContract.WorkoutEntry.WORKOUT_LAST_COMPLETED)){
            Integer lastCompleted = values.getAsInteger(WorkoutContract.WorkoutEntry.WORKOUT_LAST_COMPLETED);
            if(lastCompleted != null){
                throw new IllegalArgumentException("There must be an integer value for last completed");
            }
        }
        if(values.containsKey(WorkoutContract.WorkoutEntry.WORKOUT_JSON)){
            if(TextUtils.isEmpty(values.getAsString(WorkoutContract.WorkoutEntry.WORKOUT_JSON))){
                throw new IllegalArgumentException("Workout requires a valid name for the json file.");
            }
        }
        if(values.containsKey(WorkoutContract.WorkoutEntry.WORKOUT_NAME)){
            if(TextUtils.isEmpty(values.getAsString(WorkoutContract.WorkoutEntry.WORKOUT_NAME))){
                throw new IllegalArgumentException("Workout requires a name.");
            }
        }

        return true;
    }


}
