package com.cloud7831.strongereveryday.Data;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public final class JSONUtils {

    private JSONUtils(){

    }



    //TODO: saving and loading should be done as an async task (loader).
    public static JSONObject loadJSON(String filename){
        //TODO: Take in the filename of a json file and then convert it to a JSONObject.
        return new JSONObject();
    }

    public static void saveJSON(String filename, JSONObject data){
        //TODO: Take in the filename of a json file and a JSONObject and then save the data.
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
            setsJSON.put("weigh", new JSONArray(tempWeight));
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

}
