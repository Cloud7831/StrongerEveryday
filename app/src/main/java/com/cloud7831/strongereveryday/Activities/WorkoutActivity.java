package com.cloud7831.strongereveryday.Activities;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.cloud7831.strongereveryday.HelperClasses.MainPagerAdapter;
import com.cloud7831.strongereveryday.HelperClasses.WorkoutAdapter;
import com.cloud7831.strongereveryday.HelperClasses.WorkoutSetAdapter;
import com.cloud7831.strongereveryday.Objects.WorkoutItemCard;
import com.cloud7831.strongereveryday.Objects.WorkoutSet;
import com.cloud7831.strongereveryday.R;

import java.util.ArrayList;

public class WorkoutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout);

        //TODO: make a workout_list_item_card class that can hold the items needed in the UI. For example, a workout can have things such as a score, a video, a title card, an exercise card, a finish workout card, etc.


        // This is to remove the shadow on the support action bar.
        getSupportActionBar().setElevation(0);
        getSupportActionBar().setTitle("Chest WorkoutActivity");

        //TODO: Delete this. This is just placeholder dummy data so I can see what it looks like with the UI.
        final ArrayList<WorkoutItemCard> cards = new ArrayList<WorkoutItemCard>();
        cards.add(new WorkoutItemCard("Benchpress"));
        cards.add(new WorkoutItemCard("Squats"));
        cards.add(new WorkoutItemCard("Curls"));
        //TODO: Delete -------------------------------------------------------------------------------


        WorkoutAdapter workoutAdapter = new WorkoutAdapter(this, cards);

        //TODO: set the listView object in the workout.xml
        ListView listView = (ListView) findViewById(R.id.list);

        listView.setAdapter(workoutAdapter);



    }
}
