package com.cloud7831.strongereveryday.Data;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public final class JSONUtils {

    private final static String EXERCISE_JSON_TYPE = "Exercise"; // Signifies the JSON is an exercise.

    private JSONUtils(){

    }



    //TODO: saving and loading should be done as an async task (loader).
    public static JSONObject loadJSON(Activity activity, String filename){
        //TODO: Take in the filename of a json file and then convert it to a JSONObject.

        FileInputStream inputStream;
        String jsonText = "";
        JSONObject returnJSON;
        try{
            inputStream = activity.openFileInput(filename);
            //TODO: I should probably be using a buffered reader. This method is probably wrong.
            int i = inputStream.read();
            while(i != -1){
                jsonText += (char)i; //TODO: should I really be reading values in as an int and then converting to char?
                i = inputStream.read(); // Get the next character.
            }
            inputStream.close();

            returnJSON = new JSONObject(jsonText);

        }
        catch(JSONException e){
            Log.e("JSONUtils", "Problem loading a JSON: ", e);
            returnJSON = new JSONObject();
        }
        catch(Exception e){
            // TODO: should handle things such as not having enough space to save the data.
            e.printStackTrace();
            returnJSON = new JSONObject();
        }

        return returnJSON;
    }

    public static void saveJSON(Activity activity, JSONObject data){
        //TODO: Take in a JSONObject and then save the data.
        //TODO: saving should be done as an async task.

        String filename;
        FileOutputStream outputStream;
        try{
            filename = getFileName(data);
            outputStream = activity.openFileOutput(filename, activity.MODE_PRIVATE);
            outputStream.write(data.toString().getBytes());
            outputStream.close();
        }
        catch(Exception e){
            // TODO: should handle things such as not having enough space to save the data.
            e.printStackTrace();
        }

        return;
    }

    public static JSONObject createExerciseJSON(String name){
        //TODO: Take in the parameters of an exercise and then convert it to the JSON format.

        JSONObject exerciseJSON = new JSONObject();
        try{
            // Create the meta data
            // This data is used in workouts and other elements of the UI
            JSONObject dataJSON = new JSONObject();
            dataJSON.put("name", name);
            dataJSON.put("type", EXERCISE_JSON_TYPE); // Signifies the JSON is an exercise.
            dataJSON.put("completed", 0);//TODO: value should be a date. 0 is a placeholder value.
            dataJSON.put("score", 0);//TODO: score is only used in some exercises. 0 is a temp placeholder.
            dataJSON.put("notes", "These are the user's notes."); //TODO: placeholder data. Remove later.

            exerciseJSON.put("data", dataJSON);

            // Create the exercise data
            JSONObject setsJSON = new JSONObject();
            setsJSON.put("number of sets", 3); //TODO: 3 is placeholder data.
            int[] tempReps = new int[]{15, 12, 10};//TODO: placeholder data, remove later.
            setsJSON.put("reps", new JSONArray(tempReps));
            double[] tempWeight = new double[]{150, 95, 142.5};//TODO: placeholder data, remove later.
            setsJSON.put("weights", new JSONArray(tempWeight));
            setsJSON.put("max reps", 15); //TODO: placeholder data. Remove later.
            setsJSON.put("min reps", 5); //TODO: placeholder data. Remove later.
            setsJSON.put("weight increment", 2.5); //TODO: placeholder data. Remove later.

            exerciseJSON.put("sets", setsJSON);

        }
        catch(JSONException e){
            Log.e("JSONUtils", "Problem creating the exercise JSON: ", e);
        }

        return exerciseJSON;
    }


    public static String getFileName(JSONObject json){
        // Constructs the filename of a JSONObject.

        String filename = "";
        JSONObject data;
        try{
            data = json.getJSONObject("data");
            filename = data.getString("name") + " " + data.getString("type");
        }
        catch(JSONException e){
            Log.e("JSONUtils", "JSON didn't have a data object.", e);
            return filename;
        }
        return filename;
    }


    /**
     * Example Exercise JSON format
     *
     * {
     *     "data":{
     *         "name":STRING,
     *         "type":STRING,
     *         "completed":INT,      (could also be a date or string)
     *         "score":INT,
     *         "notes":STRING
     *     },
     *     "sets":{
     *         "number of sets":INT,
     *         "reps":INT[],
     *         "weights":DOUBLE[],
     *         "max reps":INT,
     *         "min reps":INT,
     *         "weight increment":DOUBLE
     *     }
     * }
     *
     *
     *
     */
}
