package com.example.dyju.thesisapplication.ExamplesActivities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.dyju.thesisapplication.R;

import UsersPackage.User;

/**
 * Created by dyju on 2017-03-13.
 */
public class ExamplesActivity extends AppCompatActivity {

    Button ex1Button;


    public void init(final User user){

        ex1Button = (Button) findViewById(R.id.buttonEx1);
        ex1Button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(ExamplesActivity.this, ExamplesEx1Activity.class);
                intent.putExtra("user", user);
                startActivity(intent);
            }
        });


    }



    protected void onCreate(Bundle savedInstancedSave){
        super.onCreate(savedInstancedSave);
        setContentView(R.layout.examples_activity);
        User user = (User) getIntent().getSerializableExtra("user");
        init(user);
    }
}
