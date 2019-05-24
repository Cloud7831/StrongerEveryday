package com.cloud7831.strongereveryday.Objects;

public class WorkoutItemCard {

    // Workout Item Cards are what's used to make the WorkoutActivity.

    //TODO: make an enum type for WorkoutCardType. In WorkoutContract.
    private int type; // The type of card. E.g. Video, Exercise, End Button, etc.

    private String name; // Name of the Exercise, video, etc. Possibly null if the item card doesn't use a name.
    //TODO: each card should have its own type of onClickListener.

    public WorkoutItemCard(String name){
        this.name = name;
    }

    public int getType() {
        return type;
    }

    public String getName() {
        return name;
    }
}
