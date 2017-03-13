package com.example.dyju.thesisapplication;

import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by dyju on 2017-03-13.
 */
public class LearningLvl1Activity extends AppCompatActivity {


    protected void init(){
        System.out.print("dziala");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.learning_lvl1_activity);
        init();

    }
}
