package com.example.dyju.thesisapplication.LearningActivities.AboutManipulatorBars;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;


public class AboutManipulatorSectionPageAdapter extends FragmentPagerAdapter {

    public AboutManipulatorSectionPageAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                ManipulatorDefinition manipulatorDefinition = new ManipulatorDefinition();
                return manipulatorDefinition;
            case 1:
                ManipulatorPurposes manipulatorPurposes = new ManipulatorPurposes();
                return manipulatorPurposes;
            case 2:
                ManipulatorLinks manipulatorLinks = new ManipulatorLinks();
                return manipulatorLinks;
        }
        return null;
    }

    @Override
    public int getCount() {
        // Show 3 total pages.
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "About Manipulator";
            case 1:
                return "Manipulator purposes";
            case 2:
                return "About manipulator links";
        }
        return null;
    }
}
