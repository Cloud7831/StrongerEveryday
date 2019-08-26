package com.cloud7831.strongereveryday.Activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.cloud7831.strongereveryday.Data.WorkoutContract.*;
import com.cloud7831.strongereveryday.R;

public class ExerciseEditorActivity extends AppCompatActivity {

    private Uri currentExerciseUri;

    private Spinner unitsSpinner;

    private boolean hasChanged; // True if the user has modified data.
    private boolean editMode; // Edit existing exercise = true. Create new exercise = false.

    private int units; // Holds the units of the exercise.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.exercise_editor_layout);

        Intent intent = getIntent();
        currentExerciseUri = intent.getData();

        if (currentExerciseUri == null){
            editMode = false;
            // This is a new exercise, so change the app bar to say "Create Exercise".
            setTitle(getString(R.string.exercise_editor_activity_title_create_exercise));
        }
        else{
            editMode = true;
            // This is an existing exercise, so change the app bar to say "Edit [ExerciseName]".
            setTitle("Edit Exercise"); //TODO: Exercise should be replaced with the exercise's name.
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu options from the res/menu/menu_editor.xml file.
        // This adds menu items to the app bar.
        getMenuInflater().inflate(R.menu.menu_exercise_editor, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // User clicked on a menu option in the app bar overflow menu
        switch (item.getItemId()) {
            case R.id.action_save:
                // TODO: save the workout to the database.

                finish();
                return true;
            // Respond to a click on the "Delete" menu option
            case R.id.action_delete:
                // TODO: add the strings to the strings resource file.
                // TODO: move this code to a helper function
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage("Are you sure you want to delete this exercise?");
                builder.setNegativeButton("No",
                        new DialogInterface.OnClickListener(){
                            public void onClick(DialogInterface dialog, int id){
                                // Dismiss the dialog and continue editing
                                if(dialog != null){
                                    dialog.dismiss();
                                }
                            }
                        });
                builder.setPositiveButton("Yes",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                // TODO: add delete Exercise function.
                                finish();
                            }
                        });

                AlertDialog alertDialog = builder.create();
                alertDialog.show();

                return true;
            // Respond to a click on the "Up" arrow button in the app bar
            case android.R.id.home:
                if(!hasChanged){
                    // If the exercise hasn't been modified, navigate up to parent activity.
                    NavUtils.navigateUpFromSameTask(this);
                    return true;
                }
                else{
                    // There are unsaved changes potentially so warn the user.
                    DialogInterface.OnClickListener discardButtonClickListener =
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    NavUtils.navigateUpFromSameTask(ExerciseEditorActivity.this);
                                }
                            };

                    showUnsavedChangesDialog(discardButtonClickListener);
                    return true;
                }

        }
        return super.onOptionsItemSelected(item);
    }

    private void showUnsavedChangesDialog(DialogInterface .OnClickListener discardButtonClickListener){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Discard your changes and quit editing?");
        builder.setPositiveButton("Discard", discardButtonClickListener);
        builder.setNegativeButton("Keep editing", new DialogInterface.OnClickListener(){
            public void onClick(DialogInterface dialog, int id){
                // User clicked the "Keep editing" button, so dismiss the dialog and continue editing
                if(dialog != null){
                    dialog.dismiss();
                }
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    @Override
    public void onBackPressed(){
        if(!hasChanged){
            // If the workout hasn't changed, return to parent activity
            super.onBackPressed();
            return;
        }

        DialogInterface.OnClickListener discardButtonClickListener =
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                    }
                };

        // Show dialog that there are unsaved changes
        showUnsavedChangesDialog(discardButtonClickListener);
    }

    /**
     * Setup the dropdown spinner that allows the user to select the units of the this exercise.
     */
    private void setupSpinner() {
        // Create adapter for spinner. The list options are from the String array it will use
        // the spinner will use the default layout
        ArrayAdapter unitsSpinnerAdapter = ArrayAdapter.createFromResource(this,
                R.array.array_unit_options, android.R.layout.simple_spinner_item);

        // Specify dropdown layout style - simple list view with 1 item per line
        unitsSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);

        // Apply the adapter to the spinner
        unitsSpinner.setAdapter(unitsSpinnerAdapter);

        // Set the integer mSelected to the constant values
        unitsSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selection = (String) parent.getItemAtPosition(position);
                if (!TextUtils.isEmpty(selection)) {
                    if (selection.equals(getString(R.string.units_seconds))){
                        units = WorkoutEntry.UNIT_SECONDS;
                    } else if (selection.equals(getString(R.string.units_kilograms))) {
                        units = WorkoutEntry.UNIT_KILOGRAMS;
                    } else if (selection.equals(getString(R.string.units_kilometers))) {
                        units = WorkoutEntry.UNIT_KILOMETERS;
                    } else if (selection.equals(getString(R.string.units_miles))) {
                        units = WorkoutEntry.UNIT_MILES;
                    } else if (selection.equals(getString(R.string.units_minutes))) {
                        units = WorkoutEntry.UNIT_MINUTES;
                    } else if (selection.equals(getString(R.string.units_pounds))) {
                        units = WorkoutEntry.UNIT_POUNDS;
                    } else {
                        units = WorkoutEntry.UNIT_NOT_APPLICABLE;
                    }
                }
            }

            // Because AdapterView is an abstract class, onNothingSelected must be defined
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                units = WorkoutEntry.UNIT_NOT_APPLICABLE; // Unknown
            }
        });
    }
}
