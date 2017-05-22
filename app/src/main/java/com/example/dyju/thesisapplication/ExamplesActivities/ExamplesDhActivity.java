package com.example.dyju.thesisapplication.ExamplesActivities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.dyju.thesisapplication.DHForUserDto;
import com.example.dyju.thesisapplication.DhDatas;
import com.example.dyju.thesisapplication.ExamplesFacade;
import com.example.dyju.thesisapplication.IExamplesFacade;
import com.example.dyju.thesisapplication.R;
import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;

import java.util.List;

import DatabasePackage.DbHandlerMatrix;
import UsersPackage.User;


public class ExamplesDhActivity extends AppCompatActivity implements Validator.ValidationListener{

    @NotEmpty
    EditText alpha;
    @NotEmpty
    EditText theta;
    @NotEmpty
    EditText d;
    @NotEmpty
    EditText a;
    EditText nameOfmanipulator;
    Button nextButton;
    Validator validator;

    private IExamplesFacade facade;

    public ExamplesDhActivity() {
        facade = new ExamplesFacade();
    }

    protected void onCreate(Bundle savedInstancedSave){
        super.onCreate(savedInstancedSave);
        setContentView(R.layout.examples_dh_activity);
//        User user = (User)getIntent().getSerializableExtra("user");
        String manipulatorName = String.valueOf(getIntent().getSerializableExtra("exName"));
//        addMockDatasToDb(manipulatorName, user);
        init(manipulatorName);
    }


    public void init(String manipulatorName){
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
        validator = new Validator(this);
        validator.setValidationListener(this);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validator.validate();
            }
        });
    }

    private Boolean datasAreEnteredCorrectly(DhDatas enteredDatas, DhDatas manipDatasFromDb){
//        return enteredDatas.equals(manipDatasFromDb);
    return true;
    }

    private DHForUserDto getManipulatorDatas(String name){
        DbHandlerMatrix dbHandlerMatrix = new DbHandlerMatrix(this);
        return facade.getDhDatasForManipulatorName(name, dbHandlerMatrix);
    }

    @Override
    public void onValidationSucceeded() {
        DhDatas dhDatas = new DhDatas(String.valueOf(alpha.getText()), String.valueOf(theta.getText()), String.valueOf(d.getText()), String.valueOf(a.getText()), String.valueOf(nameOfmanipulator.getText()));
        DhDatas manipDatasFromDb = getManipulatorDatas(dhDatas.getManipulatorName()).getDhDatas();
        if(datasAreEnteredCorrectly(dhDatas, manipDatasFromDb)){
            Intent intent = new Intent(ExamplesDhActivity.this, ExamplesTransitionMatrixActivity.class);
            intent.putExtra("dhDatas", manipDatasFromDb);
            startActivity(intent);
        }

    }

    @Override
    public void onValidationFailed(List<ValidationError> errors) {
        for (ValidationError error : errors) {
            View view = error.getView();
            String message = error.getCollatedErrorMessage(this);

            // Display error messages ;)
            if (view instanceof EditText) {
                ((EditText) view).setError(message);
            } else {
                Toast.makeText(this, message, Toast.LENGTH_LONG).show();
            }
        }

    }
}
