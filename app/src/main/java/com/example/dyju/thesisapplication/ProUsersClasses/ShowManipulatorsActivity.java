package com.example.dyju.thesisapplication.ProUsersClasses;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.dyju.thesisapplication.R;

import UsersPackage.User;

/**
 * Created by dyju on 2017-02-23.
 */
public class ShowManipulatorsActivity extends AppCompatActivity {

    public void init(){
        System.out.println("dziala");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pro_menu_activity);
        User user = (User)getIntent().getSerializableExtra("user");


        init();
    }



}
