package com.example.dyju.thesisapplication.ProUsersClasses;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.dyju.thesisapplication.ExamplesActivities.ExamplesDhActivity;
import com.example.dyju.thesisapplication.R;

import UsersPackage.User;

/**
 * Created by dyju on 2017-02-23.
 */
public class ProMenuActivity extends AppCompatActivity {

    TextView welcomeText;
    Button showManipulators;
    Button addNewManipulator;

    public void init(final User user)
    {
        setPageByUserData(user);
        showManipulators = (Button)findViewById(R.id.showManipulators);
        addNewManipulator = (Button)findViewById(R.id.addNewManipulator);
        showManipulators.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProMenuActivity.this, ShowManipulatorsActivity.class);
                intent.putExtra("user", user);
                startActivity(intent);
            }
        });

        addNewManipulator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProMenuActivity.this, ExamplesDhActivity.class);
                intent.putExtra("user", user);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pro_menu_activity);
        final User user = (User)getIntent().getSerializableExtra("user");
        init(user);
    }

    private void setPageByUserData(User user) {
        welcomeText = (TextView)findViewById(R.id.welcomeText);
        welcomeText.setText("Witaj " + user.get_name());
    }

}
