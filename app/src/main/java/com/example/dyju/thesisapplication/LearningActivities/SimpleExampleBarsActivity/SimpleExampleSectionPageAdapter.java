package com.example.dyju.thesisapplication.LearningActivities.SimpleExampleBarsActivity;

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
                return new SimpleExamplesFirstPage();
            case 1:
                return new SimpleExampleThema();
            case 2:
                return new SimpleExampleImageWithDimensionsActivity();
            case 3:
                return new SimpleDhActivity();
            case 4:
                return new SimpleExampleTransitionMatricesActivity();
            case 5:
                return new SimpleExampleR30VectorActivity();
            case 6:
                return new SimpleExampleResultActivity();
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
