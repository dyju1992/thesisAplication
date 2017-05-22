package com.example.dyju.thesisapplication.ExamplesActivities;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.dyju.thesisapplication.R;

public class ExamplesEx5Activity extends AppCompatActivity {

    Button toDhButton;

    protected void onCreate(Bundle savedInstancedSave){
        super.onCreate(savedInstancedSave);
        setContentView(R.layout.examples_ex4_activity);
        init();
    }

    public void init(){
        toDhButton = (Button) findViewById(R.id.toDhButton);
        toDhButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(ExamplesEx5Activity.this, ExamplesDhActivity.class);
                intent.putExtra("exName", "Manipulator_4");
                startActivity(intent);
            }
        });

    }
}
