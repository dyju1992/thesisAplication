package com.example.dyju.thesisapplication;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

import DatabasePackage.DbHandler;
import UsersPackage.User;

public class MainActivity extends AppCompatActivity {

    public Button button;
    public Button addNewUserButton;
    public Button showPrzemekUser;
    public Button regButton;
    public void init(){
        button = (Button)findViewById(R.id.goTOApplication);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MenuActivity.class);
                startActivity(intent);
            }
        });
//        addNewUserButton = (Button)findViewById(R.id.addNewUser);
//        addNewUserButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                addNewUser();
//            }
//        });

//        regButton = (Button)findViewById(R.id.registerButton);
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
//                startActivity(intent);
//            }
//        });
        showPrzemekUser = (Button)findViewById(R.id.showPrzemek);
        showPrzemekUser.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                ArrayList<User> userArrayList = showAllUser();
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }


    public ArrayList<User> showAllUser(){
        DbHandler dbHandler = new DbHandler(this, null, null, 1);
        SQLiteDatabase db = dbHandler.getWritableDatabase();
//        dbHandler.onUpgrade(db, 1,2);
        return dbHandler.getAllUsersFromDb();
    }


    public User showUser(String name){
        DbHandler dbHandler = new DbHandler(this, null, null, 1);
        return dbHandler.findUser(name);

    }
}
