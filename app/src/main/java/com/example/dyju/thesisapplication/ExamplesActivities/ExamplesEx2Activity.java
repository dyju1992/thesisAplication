package com.example.dyju.thesisapplication.ExamplesActivities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.dyju.thesisapplication.R;

import UsersPackage.User;


public class ExamplesEx2Activity extends AppCompatActivity {

    Button toDhButton;

    protected void onCreate(Bundle savedInstancedSave){
        super.onCreate(savedInstancedSave);
        setContentView(R.layout.examples_ex2_activity);
        init();
    }

    public void init(){

        toDhButton = (Button) findViewById(R.id.toDhButton);
        toDhButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(ExamplesEx2Activity.this, ExamplesDhActivity.class);
                intent.putExtra("exName", "Manipulator_2");
                startActivity(intent);
            }
        });

    }

}
