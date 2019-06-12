package com.cloud7831.strongereveryday.HelperClasses;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.cloud7831.strongereveryday.Data.WorkoutContract.WorkoutCardType;
import com.cloud7831.strongereveryday.ItemCards.ExerciseItemCard;
import com.cloud7831.strongereveryday.ItemCards.WorkoutItemCard;
import com.cloud7831.strongereveryday.Objects.Exercise;
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

            //TODO: I probably need a different layout per card type, and then inflate the layout based on the type of card.
            if(listItemView == null){
                // This is the first time making the card.
                isNewCard = true;
                listItemView = LayoutInflater.from(getContext()).inflate(R.layout.exercise_card_layout, parent, false);
            }

            //TODO: an exercise card should be an abstract class. Each exercise card needs to have certain functions, such as getName()
            //TODO: so that I don't need to have a bunch of if/else statements to do certain actions depending on the type of card I have.
            if(currentCard.hasName()){
                TextView nameView = (TextView) listItemView.findViewById(R.id.name_text_view);
                nameView.setText(currentCard.getName());
            }

            // TODO: Use a switch statement to cycle through the WorkoutCardTypes.
            if(currentCard.getType() == WorkoutCardType.EXERCISE){
                // All the logic for the ExerciseItemCard
                ExerciseItemCard card = (ExerciseItemCard) currentCard;
                TextView scoreView = (TextView) listItemView.findViewById(R.id.exercise_score_text_view);
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
            }

            return listItemView;//We only return one view, and that's listItemView which contains all the other views we want. I think?
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
