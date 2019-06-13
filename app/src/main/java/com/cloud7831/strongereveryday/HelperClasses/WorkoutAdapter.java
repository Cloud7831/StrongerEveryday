package com.cloud7831.strongereveryday.HelperClasses;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.cloud7831.strongereveryday.Data.WorkoutContract;
import com.cloud7831.strongereveryday.Data.WorkoutContract.WorkoutCardType;
import com.cloud7831.strongereveryday.ItemCards.ExerciseItemCard;
import com.cloud7831.strongereveryday.ItemCards.WorkoutItemCard;
import com.cloud7831.strongereveryday.Objects.Exercise;
import com.cloud7831.strongereveryday.OnClickListeners.ExerciseNotesToggleOnClickListener;
import com.cloud7831.strongereveryday.R;

import java.util.ArrayList;


public class WorkoutAdapter extends ArrayAdapter<WorkoutItemCard>{

        public WorkoutAdapter(Activity context, ArrayList<WorkoutItemCard> cards){
            super(context, 0, cards);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent){
            View listItemView = convertView;

            WorkoutItemCard currentCard = getItem(position);
            boolean isNewCard = false;
            WorkoutContract.WorkoutCardType cardType = currentCard.getType();

            if(listItemView == null){
                // This is the first time making this item card or the item card was recycled and needs to be recreated.
                isNewCard = true;

                // The layout for the card type needs to be created based on the type.
                switch(cardType){
                    case TITLE:
                        listItemView = LayoutInflater.from(getContext()).inflate(R.layout.title_card_layout, parent, false);
                        break;
                    case EXERCISE:
                        listItemView = LayoutInflater.from(getContext()).inflate(R.layout.exercise_card_layout, parent, false);
                        break;
                    default:
                        Log.e("WorkoutAdapter", "Unknown card type when creating view.");
                        return listItemView;
                }
            }

            // Fill in the details of the item card.

            if(currentCard.hasName()){
                TextView nameView = listItemView.findViewById(R.id.name_text_view);
                nameView.setText(currentCard.getName());
            }

            // TODO: Use a switch statement to cycle through the WorkoutCardTypes.
            switch (cardType){
                case EXERCISE:
                    // All the logic for the ExerciseItemCard
                    ExerciseItemCard card = (ExerciseItemCard) currentCard;

                    Exercise exercise = card.getExercise();


                    // Set the text in the notesView. Notes are not visible by default.
                    TextView notesView = listItemView.findViewById(R.id.notes_text_view);
                    notesView.setText(exercise.getUserNotes());
                    notesView.setVisibility(View.GONE);

                    // Set the exercise to toggle the visibility of the Exercises notes when the title is clicked.
                    // TODO: may want to test if it's better to use the adapter's "onItemClickListener" instead.
                    TextView nameView = listItemView.findViewById(R.id.name_text_view);
                    nameView.setClickable(true);
                    nameView.setOnClickListener(new ExerciseNotesToggleOnClickListener(card.getExercise(), notesView));

                    TextView scoreView = listItemView.findViewById(R.id.exercise_score_text_view);
                    int score = card.getExercise().getScore();
                    if(score == Exercise.NULL_VALUE){
                        scoreView.setVisibility(View.GONE);
                    }
                    else{
                        scoreView.setText(Integer.toString(score));
                    }


                    if(isNewCard){
                        // We only want to create the table once when the card is make the first time.
                        // Otherwise I get a bug where it creates infinite rows if I scroll up and down.
                        createExerciseTable(this.getContext(), listItemView, card);
                    }
                    break;
                default:
                    break;
            }

            return listItemView; // Return the view for the Item Card we just created.
        }


        private void createExerciseTable(Context context, View listItemView, ExerciseItemCard card){
            // Uses the data from the exercise to construct the table of sets, reps and weights.

            TableLayout table = (TableLayout) listItemView.findViewById(R.id.exercise_table_view);

            // Create a row for each set. Add in the info for reps and weight
            double[] weights = card.getExercise().getWeightPerSet();
            int[] reps = card.getExercise().getRepsPerSet();
            for(int i = 0; i < card.getExercise().getNumSets(); i++){

                final int TEXT_PADDING = 4; // padding for top, bottom and right.

                TableRow row = new TableRow(this.getContext());
                row.generateViewId();
                row.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT));
                row.setBackgroundColor(context.getResources().getColor(R.color.darkmodeBackground));

                TextView setNumber = new TextView(context);
                setNumber.generateViewId();
                setNumber.setText((i + 1) + "."); // i is zero indexing, sets should be 1 indexing.
                setNumber.setTextAppearance(context, R.style.ExerciseTableEntry); // correct font size and colours.
                setNumber.setPadding(70, TEXT_PADDING,TEXT_PADDING,TEXT_PADDING);
                row.addView(setNumber);

                TextView weightText= new TextView(context);
                weightText.generateViewId();
                weightText.setText(weights[i] + " lbs");
                weightText.setTextAppearance(context, R.style.ExerciseTableEntry); // correct font size and colours.
                weightText.setPadding(70, TEXT_PADDING, TEXT_PADDING, TEXT_PADDING);
                row.addView(weightText);

                TextView repNumber = new TextView(context);
                repNumber.generateViewId();
                repNumber.setText("x" + reps[i]);
                repNumber.setTextAppearance(context, R.style.ExerciseTableEntry); // correct font size and colours.
                repNumber.setPadding(150, TEXT_PADDING, TEXT_PADDING, TEXT_PADDING);
                row.addView(repNumber);

                table.addView(row, new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT));
            }
        }

}
