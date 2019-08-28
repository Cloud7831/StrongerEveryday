package com.cloud7831.strongereveryday.Activities;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;

import com.cloud7831.strongereveryday.R;

import java.util.List;

public class WorkoutAddElementDialog extends DialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        //TODO: make a custom dialog layout by using layout inflater to inflate a better looking view.

        builder.setTitle(getString(R.string.workout_add_element_dialog_title)); //TODO: might need to use resources before getString.

        // create the list of options
        String[] options = getResources().getStringArray(R.array.workout_add_element_dialog_options);
        // add a list
        builder.setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int index){
                switch(index){
                    case 0: //TODO: Add Exercise
                    case 1: //TODO: Existing Exercise
                    case 2: //TODO: Video
                    case 3: //TODO: Title
                        default:

                }
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();

        return dialog;
    }

}
