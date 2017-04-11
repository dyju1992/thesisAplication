package com.example.dyju.thesisapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

import DatabasePackage.DbHandler;
import DatabasePackage.DbHandlerMatrix;
import UsersPackage.User;


public class ExamplesDhActivity extends AppCompatActivity {

    EditText alpha;
    EditText theta;
    EditText d;
    EditText a;
    EditText nameOfmanipulator;
    Button nextButton;

    private IExamplesFacade facade;

    public ExamplesDhActivity() {
        facade = new ExamplesFacade();
    }

    //    public ExamplesDhActivity(ExamplesFacade facade) {
//        this.facade = facade;
//    }

    public void init(final User user, String manipulatorName){
        alpha = (EditText) findViewById(R.id.alpha);
        theta = (EditText) findViewById(R.id.theta);
        d = (EditText) findViewById(R.id.dLength);
        a = (EditText) findViewById(R.id.aLength);
        nameOfmanipulator = (EditText) findViewById(R.id.manipulatorName);
        nextButton = (Button) findViewById(R.id.nextButton);
        if(!manipulatorName.isEmpty()){
            nameOfmanipulator.setText(manipulatorName);
            nameOfmanipulator.setEnabled(false);
        }

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DhDatas dhDatas = new DhDatas(String.valueOf(alpha.getText()), String.valueOf(theta.getText()), String.valueOf(d.getText()), String.valueOf(a.getText()), String.valueOf(nameOfmanipulator.getText()));
                if(facade.dhDatasAreEntered(dhDatas) && user != null){
                    DHForUserDto dhForUserDto = new DHForUserDto(user.getID(), dhDatas);
                    facade.validateData();
//                    facade.addDhToDatabase(dhForUserDto);
                    addDatasToTable(dhForUserDto);
                    ArrayList<DhDatas> manipulatorDatasForUser = getManpulatorsDatasForUser(user);
                    System.out.println("adssasdde");
                }
            }
        });

    }

    public ArrayList<DhDatas> getManpulatorsDatasForUser(User user){
        DbHandlerMatrix dbHandlerMatrix = new DbHandlerMatrix(this);
        return facade.getManpulatorsDatasForUser(user, dbHandlerMatrix);
    }

    public void addDatasToTable(DHForUserDto dhForUserDto){
        DbHandlerMatrix dbHandlerMatrix = new DbHandlerMatrix(this);
        facade.addDhToDatabase(dhForUserDto, dbHandlerMatrix);
    }


    protected void onCreate(Bundle savedInstancedSave){
        super.onCreate(savedInstancedSave);
        setContentView(R.layout.examples_dh_activity);
        User user = (User)getIntent().getSerializableExtra("user");
        String manipulatorName = String.valueOf(getIntent().getSerializableExtra("exName"));
        addMockDatasToDb(manipulatorName);
        init(user, manipulatorName);
    }

    public void addMockDatasToDb(String manipulatorName){
        if(!manipulatorName.isEmpty()){
            DbHandlerMatrix dbHandlerMatrix = new DbHandlerMatrix(this);
            facade.addMockDataToDb(manipulatorName, dbHandlerMatrix);
        }
    }



}
