package com.example.dyju.thesisapplication.ProUsersClasses;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.dyju.thesisapplication.R;

import UsersPackage.User;

/**
 * Created by dyju on 2017-02-23.
 */
public class ResultActivity extends AppCompatActivity {

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
