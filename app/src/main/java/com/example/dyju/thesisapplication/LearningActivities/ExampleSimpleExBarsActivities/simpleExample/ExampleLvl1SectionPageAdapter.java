package com.example.dyju.thesisapplication.LearningActivities.ExampleSimpleExBarsActivities.simpleExample;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;


public class ExampleLvl1SectionPageAdapter extends FragmentPagerAdapter {

    public ExampleLvl1SectionPageAdapter(FragmentManager fragmentManager){
        super(fragmentManager);
    }


    @Override
    public Fragment getItem(int position) {


        switch (position){
            case 0:
                return new FirstPageExamplesLvl1();
            case 1:
                return new FirstExampleThema();
            case 2:
                return new FirstExampleImageWithDimensionsActivity();
            case 3:
                return new DHActivity();
            case 4:
                return new TransitionMatrixAllLvl1Activity();
            case 5:
                return new EndTransitionMatrixLvl1Activity();
            case 6:
                return new ExampleResultLvl1Activity();
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
