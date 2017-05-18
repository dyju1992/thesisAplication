package com.example.dyju.thesisapplication.ProUsersClasses;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.dyju.thesisapplication.CalculationService;
import com.example.dyju.thesisapplication.DHForUserDto;
import com.example.dyju.thesisapplication.DhDatas;
import com.example.dyju.thesisapplication.ICalculationService;
import com.example.dyju.thesisapplication.R;
import com.example.dyju.thesisapplication.TransitionMatrix;

import java.util.HashMap;
import java.util.Map;

import DatabasePackage.DbHandlerMatrix;
import UsersPackage.User;


public class ManipulatorDetailsActivity extends AppCompatActivity {

    private ICalculationService calculationService;
    TextView welcomeText;
    TextView alphaData;
    TextView thetaData;
    TextView aData;
    TextView dData;
    TextView rxResult;
    TextView ryResult;
    TextView rzResult;
    Button okButton;
    Button deleteManipulator;

    public ManipulatorDetailsActivity() {
        this.calculationService = new CalculationService();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manipulator_details_activity);
        DhDatas dhDatas = (DhDatas) getIntent().getSerializableExtra("dhDatas");
        User user = (User) getIntent().getSerializableExtra("user");

        init(dhDatas, user);
    }

    public void init(final DhDatas dhDatas, final User user){
        welcomeText = (TextView)findViewById(R.id.welcomeText);
        welcomeText.setText("Manipulator: " + dhDatas.getManipulatorName());
        alphaData = (TextView)findViewById(R.id.alphaValues);
        thetaData = (TextView)findViewById(R.id.thetaValues);
        aData = (TextView)findViewById(R.id.aValues);
        dData = (TextView)findViewById(R.id.dValues);
        rxResult = (TextView)findViewById(R.id.rxValue);
        ryResult = (TextView)findViewById(R.id.ryValues);
        rzResult = (TextView)findViewById(R.id.rzValues);
        okButton = (Button)findViewById(R.id.backToManipulatorList);
        deleteManipulator = (Button)findViewById(R.id.deletemanipulator);
        Map<String, String> values = calculationService.calculateVariables(dhDatas);
        alphaData.setText("Alpha: "+dhDatas.getAlpha());
        thetaData.setText("Theta: "+dhDatas.getTheta());
        aData.setText("a: "+ dhDatas.getA());
        dData.setText("d: "+ dhDatas.getD());
        rxResult.setText("rx: "+values.get("rx"));
        ryResult.setText("ry: "+values.get("ry"));
        rzResult.setText("rz: "+values.get("rz"));

        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToManipulatorsListView(user);
            }
        });

        deleteManipulator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deletemanipulatorFromDb(dhDatas.getManipulatorName(), user);
            }
        });
    }

    public void deletemanipulatorFromDb(String manipulatorName, User user){
        DbHandlerMatrix dbHandlerMatrix = new DbHandlerMatrix(this);
        dbHandlerMatrix.deleteManipulatorFromDb(manipulatorName, String.valueOf(user.getID()));
        goToManipulatorsListView(user);
    }

    public void goToManipulatorsListView(User user){
        Intent intent = new Intent(ManipulatorDetailsActivity.this, ShowManipulatorsActivity.class);
        intent.putExtra("user", user);
        startActivity(intent);
    }

    protected HashMap<String, String> calculateValues(DhDatas dhDatas){
        return calculationService.calculateVariables(dhDatas);
    }



}
