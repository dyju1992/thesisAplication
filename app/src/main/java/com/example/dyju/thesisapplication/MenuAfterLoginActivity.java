package com.example.dyju.thesisapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import UsersPackage.User;

/**
 * Created by dyju on 2017-02-23.
 */
public class MenuAfterLoginActivity extends AppCompatActivity {

    TextView welcomeText;

    public void init(){
        System.out.println("dziala");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_after_login_activity);
        User user = (User)getIntent().getSerializableExtra("user");
        setPageByUserData(user);
        init();
    }

    private void setPageByUserData(User user) {
        welcomeText = (TextView)findViewById(R.id.welcomeText);
        welcomeText.setText("Witaj " + user.get_name());
    }

}
