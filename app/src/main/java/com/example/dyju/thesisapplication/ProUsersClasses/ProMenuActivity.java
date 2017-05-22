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


public class ProMenuActivity extends AppCompatActivity {

    TextView welcomeText;
    Button showManipulatorsBtn;
    Button addNewManipulatorBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pro_menu_activity);
        User user = (User)getIntent().getSerializableExtra("user");
        showManipulatorsBtn = (Button)findViewById(R.id.showManipulators);
        addNewManipulatorBtn = (Button)findViewById(R.id.addNewManipulator);
        init(user);
    }

    public void init(final User user)
    {
        welcomeText = (TextView)findViewById(R.id.welcomeText);
        welcomeText.setText("Witaj " + user.get_name());
        showManipulatorsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProMenuActivity.this, ShowManipulatorsActivity.class);
                intent.putExtra("user", user);
                startActivity(intent);
            }
        });

        addNewManipulatorBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProMenuActivity.this, AddNewManipulatorActivity.class);
                intent.putExtra("user", user);
                startActivity(intent);
            }
        });
    }
}
