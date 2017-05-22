package com.example.dyju.thesisapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.dyju.thesisapplication.LearningActivities.AboutInverseActivity;
import com.example.dyju.thesisapplication.LearningActivities.AboutManipulatorBars.AboutManipulatorActivity;
import com.example.dyju.thesisapplication.LearningActivities.ExampleLvl1BarsActivities.ExampleLvl1Activity;
import com.example.dyju.thesisapplication.LearningActivities.ForWhoLvl1Activity;

public class InverseExerciseActivity extends AppCompatActivity {

    Button exampleButton;
    Button aboutManipulatorButton;
    Button aboutReverseButton;
    Button forWhoButton;


    protected void init(){

        exampleButton = (Button)findViewById(R.id.example);
        aboutManipulatorButton = (Button)findViewById(R.id.aboutManipulator);
        aboutReverseButton = (Button)findViewById(R.id.aboutReverse);
        forWhoButton = (Button)findViewById(R.id.forWho);

        exampleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(InverseExerciseActivity.this, ExampleLvl1Activity.class);
                startActivity(intent);
            }
        });

        aboutManipulatorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(InverseExerciseActivity.this, AboutManipulatorActivity.class);
                startActivity(intent);
            }
        });

        aboutReverseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(InverseExerciseActivity.this, AboutInverseActivity.class);
                startActivity(intent);
            }
        });

        forWhoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(InverseExerciseActivity.this, ForWhoLvl1Activity.class);
                startActivity(intent);

            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.learning_lvl1_activity);
        init();

    }
}
