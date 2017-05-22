package com.example.dyju.thesisapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.dyju.thesisapplication.ExamplesActivities.ExamplesActivity;
import com.example.dyju.thesisapplication.LearningActivities.AboutManipulatorBars.AboutManipulatorActivity;

import UsersPackage.User;


public class EducationMenuActivity extends AppCompatActivity {

    TextView welcomeText;

    Button simpleExerciseBtn;

    Button inverseExerciseBtn;

    Button examplesbtn;

    Button aboutManipulatorButton;


    @Override
    protected void onCreate(Bundle savedInstanceState){
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.education_menu_activity);
        User user = (User)getIntent().getSerializableExtra("user");
        setPageByUserData(user);
        init(user);
    }

    public void init(final User user){

        aboutManipulatorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EducationMenuActivity.this, AboutManipulatorActivity.class);
                startActivity(intent);
            }
        });

        inverseExerciseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EducationMenuActivity.this, InverseExerciseActivity.class);
                startActivity(intent);
            }
        });

        simpleExerciseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EducationMenuActivity.this, SimpleExerciseActivity.class);
                startActivity(intent);

            }
        });

        examplesbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EducationMenuActivity.this, ExamplesActivity.class);
                intent.putExtra("user", user);
                startActivity(intent);
            }
        });
    }

    private void setPageByUserData(User user) {
        welcomeText = (TextView)findViewById(R.id.welcomeText);
        welcomeText.setText("Witaj " + user.get_name()+" na swoim koncie Edukacyjnym.");
        inverseExerciseBtn = (Button)findViewById(R.id.inverseExercise);
        simpleExerciseBtn = (Button)findViewById(R.id.simpleExercise);
        examplesbtn = (Button)findViewById(R.id.examplesButton);
        aboutManipulatorButton = (Button)findViewById(R.id.aboutManipulator);


//        welcomeText.setTextColor(Color.parseColor("#FFFFFF"));
    }

}
