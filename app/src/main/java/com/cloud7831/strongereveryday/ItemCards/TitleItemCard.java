package com.cloud7831.strongereveryday.ItemCards;
import com.cloud7831.strongereveryday.Data.WorkoutContract.WorkoutCardType;

public class TitleItemCard implements WorkoutItemCard {

    private String name;

    public TitleItemCard(String title){
        name = title;
    }


    @Override
    public WorkoutCardType getType() {
        return WorkoutCardType.TITLE;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean hasName() {
        // A title card always has a name.
        return true;
    }
}

