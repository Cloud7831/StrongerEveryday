package com.cloud7831.strongereveryday.Activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.cloud7831.strongereveryday.R;

public class WorkoutEditorActivity extends AppCompatActivity {

    private Uri currentWorkoutUri;

    private boolean hasChanged; // True if the user has modified data.
    private boolean editMode; // Edit existing workout = true. Create new workout = false.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.workout_editor_layout);

        Intent intent = getIntent();
        currentWorkoutUri = intent.getData();

        if (currentWorkoutUri == null){
            editMode = false;
            // This is a new workout, so change the app bar to say "Create Workout".
            setTitle(getString(R.string.workout_editor_activity_title_create_workout));
        }
        else{
            editMode = true;
            // This is an existing workout, so change the app bar to say "Edit [WorkoutName]".
            setTitle("Edit Workout"); //TODO: Workout should be replaced with the workout's name.
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu options from the res/menu/menu_editor.xml file.
        // This adds menu items to the app bar.
        getMenuInflater().inflate(R.menu.menu_workout_editor, menu);
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
                builder.setMessage("Are you sure you want to delete this workout?");
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
                                // TODO: add delete Workout function.
                                finish();
                            }
                        });

                AlertDialog alertDialog = builder.create();
                alertDialog.show();

                return true;
            // Respond to a click on the "Up" arrow button in the app bar
            case android.R.id.home:
                if(!hasChanged){
                    // If the workout hasn't been modified, navigate up to parent activity.
                    NavUtils.navigateUpFromSameTask(this);
                    return true;
                }
                else{
                    // There are unsaved changes potentially so warn the user.
                    DialogInterface.OnClickListener discardButtonClickListener =
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    NavUtils.navigateUpFromSameTask(WorkoutEditorActivity.this);
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
}
