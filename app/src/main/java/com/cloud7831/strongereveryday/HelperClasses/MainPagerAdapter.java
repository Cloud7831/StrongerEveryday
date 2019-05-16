package com.cloud7831.strongereveryday.HelperClasses;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.cloud7831.strongereveryday.Activities.HomeFragment;
import com.cloud7831.strongereveryday.Activities.WorkoutListFragment;
import com.cloud7831.strongereveryday.R;

public class MainPagerAdapter extends FragmentPagerAdapter {
    final int TAB_COUNT = 2; // number of tabs on our main page
    private Context context;

    public MainPagerAdapter(FragmentManager fm, Context c){
        super(fm);
        context = c;
    }



    @Override
    public Fragment getItem(int position){
        if (position == 0){
            return new HomeFragment();
        }
//        else if (position == 1){
//            return new WorkoutListFragment();
//        }
        else {
            //TODO: set an error if we somehow scroll too many times.
            return new WorkoutListFragment();
        }
    }

    @Override
    public int getCount(){
        return TAB_COUNT;
    }

    @Override
    public CharSequence getPageTitle(int position){
        if (position == 0){
            return context.getString(R.string.tab_home);
        }
        else if (position == 1){
            return context.getString(R.string.tab_workoutlist);
        }
        else {
            //TODO: this should return an error!
            return context.getString(R.string.tab_workoutlist);
        }
    }
}
