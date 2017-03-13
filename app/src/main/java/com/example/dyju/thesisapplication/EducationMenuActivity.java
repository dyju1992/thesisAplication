package com.example.dyju.thesisapplication;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import UsersPackage.User;

/**
 * Created by dyju on 2017-02-23.
 */
public class EducationMenuActivity extends AppCompatActivity {

    TextView welcomeText;

    Button learningLvl1Btn;

    Button learningLvl2Btn;

    Button examplesbtn;

    public void init(){

        learningLvl1Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EducationMenuActivity.this, LearningLvl1Activity.class);
                startActivity(intent);
            }
        });

        learningLvl2Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EducationMenuActivity.this, LearningLvl2Activity.class);
                startActivity(intent);

            }
        });

        examplesbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EducationMenuActivity.this, ExamplesActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.education_menu_activity);
        User user = (User)getIntent().getSerializableExtra("user");
        setPageByUserData(user);
        init();
    }

    private void setPageByUserData(User user) {
        welcomeText = (TextView)findViewById(R.id.welcomeText);
        welcomeText.setText("Witaj " + user.get_name()+" na koncie Edukacyjnym. Wybierz co chcesz zrobić:");
        learningLvl1Btn = (Button)findViewById(R.id.learningLevel1Button);
        learningLvl2Btn = (Button)findViewById(R.id.learningLevel2Button);
        examplesbtn = (Button)findViewById(R.id.examplesButton);


//        welcomeText.setTextColor(Color.parseColor("#FFFFFF"));
    }

}
