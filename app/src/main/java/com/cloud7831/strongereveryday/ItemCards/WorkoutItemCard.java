package com.cloud7831.strongereveryday.ItemCards;

import com.cloud7831.strongereveryday.Data.WorkoutContract.WorkoutCardType;

public interface WorkoutItemCard {

    // WorkoutActivity Item Cards are what's used to make the WorkoutActivity.

    public WorkoutCardType getType();

    public boolean hasName(); // Indicades if the WorkoutItemCard has a name it needs to display.
}
