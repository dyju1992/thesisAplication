package com.example.dyju.thesisapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.dyju.thesisapplication.LearningActivities.AboutInverseActivity;
import com.example.dyju.thesisapplication.LearningActivities.HowCalcInverseExBars.HowCalculateInverseExActivity;
import com.example.dyju.thesisapplication.LearningActivities.InverseExampleBarsActivities.ExampleInverseActivity;
import com.example.dyju.thesisapplication.LearningActivities.ForWhoInverseExerciseActivity;

public class InverseExerciseActivity extends AppCompatActivity {

    Button exampleButton;
    Button aboutReverseButton;
    Button forWhoButton;
    Button howCalculate;


    @Override
    protected void onCreate(Bundle savedInstanceState){
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inverse_exercise_activity);
        init();

    }

    protected void init(){

        exampleButton = (Button)findViewById(R.id.example);
        aboutReverseButton = (Button)findViewById(R.id.aboutReverse);
        forWhoButton = (Button)findViewById(R.id.forWho);
        howCalculate = (Button)findViewById(R.id.howCalculate);

        exampleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(InverseExerciseActivity.this, ExampleInverseActivity.class);
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
                Intent intent = new Intent(InverseExerciseActivity.this, ForWhoInverseExerciseActivity.class);
                startActivity(intent);

            }
        });

        howCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(InverseExerciseActivity.this, HowCalculateInverseExActivity.class);
                startActivity(intent);
            }
        });
    }
}
