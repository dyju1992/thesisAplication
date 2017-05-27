package com.example.dyju.thesisapplication.LearningActivities.HowCalcInverseExBars;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;


public class HowCalcInvExampleSectionPageAdapter extends FragmentPagerAdapter {

    public HowCalcInvExampleSectionPageAdapter(FragmentManager fragmentManager){
        super(fragmentManager);
    }


    @Override
    public Fragment getItem(int position) {


        switch (position){
            case 0:
                return new HowCalInvExamplesFirstPage();
            case 1:
                return new HowCalcExampleImageWithDimensionsActivity();
            case 2:
                return new HowCalcInvDHActivity();
            case 3:
                return new HowCalcInvExampleTransitionMatricesActivity();
            case 4:
                return new HowCalcInvExampleVectorCalculationActivity();
            case 5:
                return new HowCalcInvExampleResultActivity();
        }
        return null;
    }

    @Override
    public int getCount() {
        return 6;
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
