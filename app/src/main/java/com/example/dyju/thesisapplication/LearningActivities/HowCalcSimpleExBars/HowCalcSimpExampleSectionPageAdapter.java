package com.example.dyju.thesisapplication.LearningActivities.HowCalcSimpleExBars;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.dyju.thesisapplication.LearningActivities.HowCalcInverseExBars.HowCalInvExamplesFirstPage;
import com.example.dyju.thesisapplication.LearningActivities.HowCalcInverseExBars.HowCalcExampleImageWithDimensionsActivity;
import com.example.dyju.thesisapplication.LearningActivities.HowCalcInverseExBars.HowCalcInvDHActivity;
import com.example.dyju.thesisapplication.LearningActivities.HowCalcInverseExBars.HowCalcInvExampleResultActivity;
import com.example.dyju.thesisapplication.LearningActivities.HowCalcInverseExBars.HowCalcInvExampleTransitionMatricesActivity;
import com.example.dyju.thesisapplication.LearningActivities.HowCalcInverseExBars.HowCalcInvExampleVectorCalculationActivity;


public class HowCalcSimpExampleSectionPageAdapter extends FragmentPagerAdapter {

    public HowCalcSimpExampleSectionPageAdapter(FragmentManager fragmentManager){
        super(fragmentManager);
    }


    @Override
    public Fragment getItem(int position) {


        switch (position){
            case 0:
                return new HowCalSimpExamplesFirstPage();
            case 1:
                return new HowCalcSimExampleImageWithDimensionsActivity();
            case 2:
                return new HowCalcSimpExDHActivity();
            case 3:
                return new HowCalcSimpExampleTransitionMatricesActivity();
            case 4:
                return new HowCalcSimpExampleVectorCalculationActivity();
            case 5:
                return new HowCalcSimpExampleResultActivity();
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
