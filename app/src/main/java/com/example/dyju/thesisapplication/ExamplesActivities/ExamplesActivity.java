package com.example.dyju.thesisapplication.ExamplesActivities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.dyju.thesisapplication.R;

import UsersPackage.User;

public class ExamplesActivity extends AppCompatActivity {

    Button inverseEx1Button;
    Button inverseEx2Button;
    Button inverseEx3Button;
    Button simpleEx1Button;
    Button simpleEx2Button;
    Button simpleEx3Button;


    protected void onCreate(Bundle savedInstancedSave){
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstancedSave);
        setContentView(R.layout.examples_activity);
        User user = (User) getIntent().getSerializableExtra("user");
        init(user);
    }
    public void init(final User user){

        inverseEx1Button = (Button) findViewById(R.id.inverseEx1);
        inverseEx2Button = (Button) findViewById(R.id.inverseEx2);
        inverseEx3Button = (Button) findViewById(R.id.inverseEx3);
        simpleEx1Button = (Button) findViewById(R.id.simpleEx1);
        simpleEx2Button = (Button) findViewById(R.id.simpleEx2);
        simpleEx3Button = (Button) findViewById(R.id.simpleEx3);
        inverseEx1Button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(ExamplesActivity.this, ExamplesEx1Activity.class);
                startActivity(intent);
            }
        });

        inverseEx2Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ExamplesActivity.this, ExamplesEx2Activity.class);
                startActivity(intent);
            }
        });

        inverseEx3Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ExamplesActivity.this, ExamplesEx3Activity.class);
                startActivity(intent);
            }
        });

        simpleEx1Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ExamplesActivity.this, ExamplesEx4Activity.class);
                startActivity(intent);
            }
        });

        simpleEx2Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ExamplesActivity.this, ExamplesEx5Activity.class);
                startActivity(intent);
            }
        });

        simpleEx3Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ExamplesActivity.this, ExamplesEx6Activity.class);
                startActivity(intent);
            }
        });
    }

    private void setViewByIdsToButtons(){
        inverseEx1Button = (Button) findViewById(R.id.inverseEx1);
        inverseEx2Button = (Button) findViewById(R.id.inverseEx2);
        inverseEx3Button = (Button) findViewById(R.id.inverseEx3);
        simpleEx1Button = (Button) findViewById(R.id.simpleEx1);
        simpleEx2Button = (Button) findViewById(R.id.simpleEx2);
        simpleEx3Button = (Button) findViewById(R.id.simpleEx3);
    }
}
