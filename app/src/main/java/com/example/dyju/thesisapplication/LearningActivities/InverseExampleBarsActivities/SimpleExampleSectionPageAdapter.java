package com.example.dyju.thesisapplication.LearningActivities.InverseExampleBarsActivities;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;


public class SimpleExampleSectionPageAdapter extends FragmentPagerAdapter {

    public SimpleExampleSectionPageAdapter(FragmentManager fragmentManager){
        super(fragmentManager);
    }


    @Override
    public Fragment getItem(int position) {


        switch (position){
            case 0:
                return new InverseExampleFirstPageActivity();
            case 1:
                return new FirstExampleThema();
            case 2:
                return new FirstExampleImageWithDimensionsActivity();
            case 3:
                return new DHActivity();
            case 4:
                return new TransitionMatrices();
            case 5:
                return new R30VectorCalculationActivity();
            case 6:
                return new InverseExampleResultActivity();
        }
        return null;
    }

    @Override
    public int getCount() {
        return 7;
    }


    @Override
    public CharSequence getPageTitle(int position){
        switch (position){
            case 0:
                return "Współrzędne Denavita Hartenberga";
        }
        return null;
    }
}
