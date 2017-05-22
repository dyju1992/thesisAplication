package com.example.dyju.thesisapplication.ProUsersClasses;

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
import com.example.dyju.thesisapplication.R;
import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;

import java.util.List;
import java.util.regex.Pattern;

import DatabasePackage.DbHandlerMatrix;
import UsersPackage.User;


public class AddNewManipulatorActivity extends AppCompatActivity implements Validator.ValidationListener {

    @NotEmpty
    EditText alpha;
    @NotEmpty
    EditText theta;
    @NotEmpty
    EditText d;
    @NotEmpty
    EditText a;
    @NotEmpty
    EditText nameOfmanipulator;
    Button nextButton;
    Validator validator;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_new_manipulator_activity);
        user = (User)getIntent().getSerializableExtra("user");
        init();
    }

    public void init(){

        alpha = (EditText) findViewById(R.id.alpha);
        theta = (EditText) findViewById(R.id.theta);
        d = (EditText) findViewById(R.id.dLength);
        a = (EditText) findViewById(R.id.aLength);
        nameOfmanipulator = (EditText) findViewById(R.id.manipulatorName);
        nextButton = (Button) findViewById(R.id.nextButton);
        validator = new Validator(this);
        validator.setValidationListener(this);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validator.validate();
            }
        });
    }

    @Override
    public void onValidationSucceeded() {
        DbHandlerMatrix dbHandlerMatrix = new DbHandlerMatrix(this);
        if(userHasManipulatorWithThatName(dbHandlerMatrix)){
            Toast.makeText(this, "Zmień nazwę manipulatora.", Toast.LENGTH_LONG).show();
        }else{
            if(allDataAreNumeric() && allParametersHasGoodLength()) {
                DHForUserDto dhForUserDto = new DHForUserDto(user.getID(), new DhDatas(String.valueOf(theta.getText()), String.valueOf(alpha.getText()), String.valueOf(d.getText()), String.valueOf(a.getText()), String.valueOf(nameOfmanipulator.getText())));
                dbHandlerMatrix.addNewDataAboutmanipulator(dhForUserDto);
                Intent intent = new Intent(AddNewManipulatorActivity.this, ManipulatorDetailsActivity.class);
                intent.putExtra("dhDatas", dhForUserDto.getDhDatas());
                intent.putExtra("user", user);
                startActivity(intent);
            }
        }


    }

    private boolean allParametersHasGoodLength(){
        boolean isValid = a.getText().toString().split(Pattern.quote(",")).length==3 && theta.getText().toString().split(Pattern.quote(",")).length==3 &&
                alpha.getText().toString().split(Pattern.quote(",")).length==3 && d.getText().toString().split(Pattern.quote(",")).length==3;
        if(isValid==false){
            Toast.makeText(this, "W manipulatorze trójczłonowym powinno być po trzy parametry w notacji Denavita Hartenberga", Toast.LENGTH_LONG).show();
        }
        return isValid;
    }

    private boolean allDataAreNumeric(){
        return datasAreNumbers(alpha.getText().toString(), "alpha") && datasAreNumbers(theta.getText().toString(), "theta") &&
                datasAreNumbers(a.getText().toString(), "a") && datasAreNumbers(d.getText().toString(), "d");
    }

    private boolean datasAreNumbers(String data, String id){
        String[] splitedDatas = data.split(Pattern.quote(","));
        try{
            for(int i = 0; i<splitedDatas.length; i++){
                Double d = Double.valueOf(splitedDatas[i]);
            }
            return true;
        }catch (Exception e){
            switch (id){
                case "theta":
                    View view = (EditText)findViewById(R.id.theta);
                    String message = "Wprowadzone dane muszą być liczbowe i zapisane po przecinku";
                    ((EditText) view).setError(message);
                    return false;
                case "a":
                    View view1 = (EditText)findViewById(R.id.aLength);
                    String message1 = "Wprowadzone dane muszą być liczbowe i zapisane po przecinku";
                    ((EditText) view1).setError(message1);
                    return false;
                case "d":
                    View view2 = (EditText)findViewById(R.id.dLength);
                    String message2 = "Wprowadzone dane muszą być liczbowe i zapisane po przecinku";
                    ((EditText) view2).setError(message2);
                    return false;
                case "alpha":
                    View view3 = (EditText)findViewById(R.id.alpha);
                    String message3 = "Wprowadzone dane muszą być liczbowe i zapisane po przecinku";
                    ((EditText) view3).setError(message3);
                    return false;
                default:
                    Toast.makeText(this, "Wprowadzone dane muszą być liczbowe i zapisane po przecinku", Toast.LENGTH_LONG).show();
                    return false;
            }


        }

    }

    private boolean userHasManipulatorWithThatName(DbHandlerMatrix dbHandlerMatrix){
        return !dbHandlerMatrix.datasArentBeOnDb(String.valueOf(nameOfmanipulator.getText()), String.valueOf(user.getID()));

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
