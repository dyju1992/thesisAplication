package com.example.dyju.thesisapplication.LearningActivities.InverseExampleBarsActivities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dyju.thesisapplication.R;


public class InverseExampleFirstPageActivity extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.first_page_example_inverse, container, false);
        return view;

    }

}
