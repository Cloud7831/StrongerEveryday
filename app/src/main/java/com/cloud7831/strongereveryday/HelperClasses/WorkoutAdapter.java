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

import com.cloud7831.strongereveryday.Data.WorkoutContract;
import com.cloud7831.strongereveryday.Data.WorkoutContract.WorkoutCardType;
import com.cloud7831.strongereveryday.ItemCards.ExerciseItemCard;
import com.cloud7831.strongereveryday.ItemCards.WorkoutItemCard;
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

            //TODO: I probably need a different layout per card type, and then inflate the layout based on the type of card.
            if(listItemView == null){
                //false because we don't want to attach the list item view to the parent list view just yet.
                //TODO: make a exercise_card_layout.
                listItemView = LayoutInflater.from(getContext()).inflate(R.layout.exercise_card_layout, parent, false);
            }

            //TODO: an exercise card should be an abstract class. Each exercise card needs to have certain functions, such as getName()
            //TODO: so that I don't need to have a bunch of if/else statements to do certain actions depending on the type of card I have.
            if(currentCard.hasName()){
                TextView nameView = (TextView) listItemView.findViewById(R.id.exercise_name_text_view);
                nameView.setText(currentCard.getName());
            }

            if(currentCard.getType() == WorkoutCardType.EXERCISE){
                // All the logic for the ExerciseItemCard
                ExerciseItemCard card = (ExerciseItemCard) currentCard;
                TextView scoreView = (TextView) listItemView.findViewById(R.id.exercise_score_text_view);
                scoreView.setText(Integer.toString(card.getScore()));

                //createExerciseTable(this.getContext(), listItemView, card);
            }

            return listItemView;//We only return one view, and that's listItemView which contains all the other views we want. I think?
        }


        private void createExerciseTable(Context context, View listItemView, ExerciseItemCard card){
            // Uses the data from the exercise to construct the table of sets, reps and weights.

            TableLayout table = (TableLayout) listItemView.findViewById(R.id.exercise_table_view);

            // Create a row for each set. Add in the info for reps and weight
            double[] weights = card.getWeights();
            int[] reps = card.getReps();
            for(int i = 0; i < card.getNumSets(); i++){

                //TODO: lookup how I should actually set the IDs so they don't collide.
                TableRow row = new TableRow(this.getContext());
                row.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT));
                row.setId(10+i);
                row.setBackgroundColor(context.getResources().getColor(R.color.darkmodeBackground));

                TextView setNumber = new TextView(context);
                setNumber.setId(50+i);
                setNumber.setText(i + ".");
                setNumber.setTextColor(context.getResources().getColor(R.color.darkmodeDarkText));
                setNumber.setPadding(5,5,5,5);
                row.addView(setNumber);

                TextView weightNumber = new TextView(context);
                weightNumber.setId(90+i);
                weightNumber.setText(weights[i] + "lbs");
                weightNumber.setTextColor(context.getResources().getColor(R.color.darkmodeDarkText));
                weightNumber.setPadding(5,5,5,5);
                row.addView(weightNumber);

                TextView repNumber = new TextView(context);
                repNumber.setId(130+i);
                repNumber.setText("x" + reps[i]);
                repNumber.setTextColor(context.getResources().getColor(R.color.darkmodeDarkText));
                repNumber.setPadding(5,5,5,5);
                row.addView(repNumber);

                table.addView(row, new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT));
            }
        }

}
