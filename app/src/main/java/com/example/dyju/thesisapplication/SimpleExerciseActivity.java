package com.example.dyju.thesisapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.dyju.thesisapplication.LearningActivities.HowCalcSimpleExBars.HowCalculateSimpleExActivity;
import com.example.dyju.thesisapplication.LearningActivities.SimpleExampleBarsActivity.ExampleSimpleActivity;
import com.example.dyju.thesisapplication.SimpleExerciseLearning.AboutSimpleExerciseActivity;
import com.example.dyju.thesisapplication.SimpleExerciseLearning.ForWhoSimpleExercise;

public class SimpleExerciseActivity extends AppCompatActivity {

    Button forWho;
    Button aboutSimpleEx;
    Button simpleExercise;
    Button howCalc;


    @Override
    protected void onCreate(Bundle savedInstancedSave){
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstancedSave);
        setContentView(R.layout.simple_exercise_activity);
        init();
    }

    protected void init(){
        forWho = (Button)findViewById(R.id.forWho);
        aboutSimpleEx = (Button)findViewById(R.id.aboutSimpleEx);
        simpleExercise = (Button)findViewById(R.id.example);
        howCalc = (Button)findViewById(R.id.howCalculateSimpleExample);

        forWho.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SimpleExerciseActivity.this, ForWhoSimpleExercise.class);
                startActivity(intent);
            }
        });

        aboutSimpleEx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SimpleExerciseActivity.this, AboutSimpleExerciseActivity.class);
                startActivity(intent);
            }
        });

        simpleExercise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SimpleExerciseActivity.this, ExampleSimpleActivity.class);
                startActivity(intent);
            }
        });

        howCalc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SimpleExerciseActivity.this, HowCalculateSimpleExActivity.class);
                startActivity(intent);
            }
        });
    }
}
