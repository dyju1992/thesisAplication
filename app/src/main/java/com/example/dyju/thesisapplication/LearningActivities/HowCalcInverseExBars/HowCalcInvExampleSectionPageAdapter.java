package com.example.dyju.thesisapplication.LearningActivities.HowCalcInverseExBars;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.dyju.thesisapplication.LearningActivities.InverseExampleBarsActivities.DHActivity;
import com.example.dyju.thesisapplication.LearningActivities.InverseExampleBarsActivities.ExampleResultLvl1Activity;
import com.example.dyju.thesisapplication.LearningActivities.InverseExampleBarsActivities.FirstExampleImageWithDimensionsActivity;
import com.example.dyju.thesisapplication.LearningActivities.InverseExampleBarsActivities.FirstExampleThema;
import com.example.dyju.thesisapplication.LearningActivities.InverseExampleBarsActivities.FirstPageExamplesLvl1;
import com.example.dyju.thesisapplication.LearningActivities.InverseExampleBarsActivities.R30VectorCalculationActivity;
import com.example.dyju.thesisapplication.LearningActivities.InverseExampleBarsActivities.TransitionMatrixAllLvl1Activity;


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
