package com.cloud7831.strongereveryday.Data;

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
        return new JSONObject();
    }

}
