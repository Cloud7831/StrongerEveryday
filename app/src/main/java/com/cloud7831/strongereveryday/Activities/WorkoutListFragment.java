package com.cloud7831.strongereveryday.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.cloud7831.strongereveryday.HelperClasses.WorkoutSetAdapter;
import com.cloud7831.strongereveryday.Objects.WorkoutSet;
import com.cloud7831.strongereveryday.R;

import java.util.ArrayList;

public class WorkoutListFragment extends Fragment {


    public WorkoutListFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View rootView = inflater.inflate(R.layout.fragment_workout_list, container, false);


        //TODO: When you have a workout running, don't show the list of workouts. Just take the user right to the
        //TODO: workout activity. Leaving the workout activity pauses it so that the user can return to it later.

        //TODO: add an options button to the workouts once you click on them. Suck as edit workout, or delete workout along with the start workout.


        //TODO: Delete this. This is just placeholder dummy data so I can see what it looks like with the UI.
        final ArrayList<WorkoutSet> workouts = new ArrayList<WorkoutSet>();
        workouts.add(new WorkoutSet("Chest", 3, 0));
        workouts.add(new WorkoutSet("Legs", 0, 0));
        workouts.add(new WorkoutSet("Abs", 1, 0));
        //TODO: Delete -------------------------------------------------------------------------------

        WorkoutSetAdapter workoutAdapter = new WorkoutSetAdapter(getActivity(), workouts);

        ListView listView = (ListView) rootView.findViewById(R.id.list);

        listView.setAdapter(workoutAdapter);


        // Setup item onclick listener
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Intent intent = new Intent(getContext(), WorkoutActivity.class);

                //Uri currentPetUri = ContentUris.withAppendedId(PetEntry.CONTENT_URI, id);
                //intent.setData(currentPetUri);
                //TODO: setup URIs for workouts.
                startActivity(intent);
            }
        });




        return rootView;
    }

}
