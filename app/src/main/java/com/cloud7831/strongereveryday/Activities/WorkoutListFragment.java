package com.cloud7831.strongereveryday.Activities;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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


        //TODO: Delete this. This is just placeholder data so I can see what it looks like with the UI.
        final ArrayList<WorkoutSet> workouts = new ArrayList<WorkoutSet>();
        workouts.add(new WorkoutSet("Chest", 3, 0));
        workouts.add(new WorkoutSet("Legs", 3, 0));
        workouts.add(new WorkoutSet("Abs", 3, 0));
        //TODO: Delete -------------------------------------------------------------------------------

        WorkoutSetAdapter workoutAdapter = new WorkoutSetAdapter(getActivity(), workouts);

        ListView listView = (ListView) rootView.findViewById(R.id.list);

        listView.setAdapter(workoutAdapter);
        



        return rootView;
    }

}
