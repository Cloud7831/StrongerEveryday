package com.cloud7831.strongereveryday.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.widget.ListView;

import com.cloud7831.strongereveryday.Data.JSONUtils;
import com.cloud7831.strongereveryday.HelperClasses.WorkoutAdapter;
import com.cloud7831.strongereveryday.ItemCards.ExerciseItemCard;
import com.cloud7831.strongereveryday.ItemCards.WorkoutItemCard;
import com.cloud7831.strongereveryday.Objects.Exercise;
import com.cloud7831.strongereveryday.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class WorkoutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout);

        //TODO: make a workout_list_item_card class that can hold the items needed in the UI. For example, a workout can have things such as a score, a video, a title card, an exercise card, a finish workout card, etc.
        // This is to remove the shadow on the support action bar.
        getSupportActionBar().setElevation(0);
        getSupportActionBar().setTitle("Chest WorkoutActivity"); //TODO: set the title to the name of the workout.


        //TODO: Delete this. This is just placeholder dummy data so I can see what it looks like with the UI.
        // All of this code is just test data for now. None of it will be here during the final version.
        JSONObject testExercise = JSONUtils.createExerciseJSON("Inclined Press");

        JSONUtils.saveJSON(this, testExercise);

        testExercise = JSONUtils.loadJSON(this, "Inclined Press Exercise");


        final ArrayList<WorkoutItemCard> cards = new ArrayList<WorkoutItemCard>();
        try{
            cards.add(new ExerciseItemCard(new Exercise(testExercise.getJSONObject("data").getString("name"))));
        }
        catch(JSONException e){
            Log.e("JSONTest", "Problem reading from the test JSON: ", e);
        }
        cards.add(new ExerciseItemCard(new Exercise("Benchpress")));
        cards.add(new ExerciseItemCard(new Exercise("Squats")));
        cards.add(new ExerciseItemCard(new Exercise("Curls")));
        cards.add(new ExerciseItemCard(new Exercise("Rows")));
        cards.add(new ExerciseItemCard(new Exercise("EZ Bar Curls")));
        cards.add(new ExerciseItemCard(new Exercise("Inclined Press")));
        //TODO: Delete -------------------------------------------------------------------------------


        WorkoutAdapter workoutAdapter = new WorkoutAdapter(this, cards);

        //TODO: set the listView object in the workout.xml
        ListView listView = (ListView) findViewById(R.id.list);

        listView.setAdapter(workoutAdapter);

    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu options from the res/menu/menu_editor.xml file.
//        // This adds menu items to the app bar.
//        getMenuInflater().inflate(R.menu.menu_editor, menu);
//        return true;
//    }

}
