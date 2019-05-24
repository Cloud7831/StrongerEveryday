package com.cloud7831.strongereveryday.HelperClasses;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.cloud7831.strongereveryday.Objects.WorkoutItemCard;
import com.cloud7831.strongereveryday.Objects.WorkoutSet;
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
                listItemView = LayoutInflater.from(getContext()).inflate(R.layout.workout_list_item, parent, false);
            }

            return listItemView;//We only return one view, and that's listItemView which contains all the other views we want. I think?
        }



}
