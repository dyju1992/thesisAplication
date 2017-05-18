package com.example.dyju.thesisapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;
import com.mobsandgeeks.saripaar.annotation.Select;

import java.util.List;

import DatabasePackage.DbHandler;
import UsersPackage.AccountVersion;
import UsersPackage.User;


public class RegisterActivity extends AppCompatActivity implements Validator.ValidationListener {

    Button registerNewUser;

    @NotEmpty(message = "Proszę podać nazwę użytkowanika")
    EditText name;

    @NotEmpty(message = "Proszę podać hasło")
    EditText password;

//    @NotEmpty(message = "Proszę wybrać rodzaj konta")
    Spinner accountVersion;

    Validator validator;


    public void init(){
        name = (EditText)findViewById(R.id.nameInput);
        password = (EditText)findViewById(R.id.passwordInput);
        accountVersion = (Spinner)findViewById(R.id.version_spinner);
        registerNewUser = (Button)findViewById(R.id.registerNewUser);
        validator = new Validator(this);
        validator.setValidationListener(this);
        registerNewUser.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                validator.validate();
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_activity);
        init();
    }

    public void addNewUser(String name, String password, AccountVersion accountVersion){
        DbHandler dbHandler = new DbHandler(this, null, null, 2);
        User user = new User(name, password, accountVersion);

        dbHandler.addNewUserToDb(user);
    }

    @Override
    public void onValidationSucceeded() {
        String userName = String.valueOf(name.getText());
        String userPassword = String.valueOf(password.getText());
        String version = String.valueOf(accountVersion.getSelectedItem());
        AccountVersion accountVersion = AccountVersion.EDUCATION.getName().equals(version)? AccountVersion.EDUCATION : AccountVersion.PRO;
        if(isNotUsedUserName(userName)) {
            addNewUser(userName, userPassword, accountVersion);
            Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
            startActivity(intent);
        }else{
            Toast.makeText(this, "Change your user name", Toast.LENGTH_LONG).show();
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

    public Boolean isNotUsedUserName(String name){
        DbHandler dbHandler = new DbHandler(this, null, null, 1);
        User user =  dbHandler.findUser(name);
        return user==null?true : false;
    }
}
