package com.example.dyju.thesisapplication.LearningActivities.SimpleExampleBarsActivity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dyju.thesisapplication.R;


public class SimpleExampleThema extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){

        View view = inflater.inflate(R.layout.first_example_thema_activity, container, false);
        return view;


    }
}
