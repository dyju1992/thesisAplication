package com.example.dyju.thesisapplication.LearningActivities.ExampleLvl1BarsActivities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dyju.thesisapplication.R;

/**
 * Created by dyju on 2017-03-21.
 */
public class DHActivity extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dh_lvl1_activity, container, false);

        return view;

    }
}
