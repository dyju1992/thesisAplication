package com.example.dyju.thesisapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.Email;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;

import java.util.List;

import DatabasePackage.DbHandler;
import Services.ILoginActivityService;
import Services.LoginActivityService;
import UsersPackage.User;


public class LoginActivity extends AppCompatActivity implements Validator.ValidationListener {

    Validator validator;
    Button loginButton;
    @NotEmpty(message = "Please enter your login")
    EditText login;

    @NotEmpty(message = "Please enter your password")
    EditText password;

    LoginActivityService loginActivityService;
//    private LoginActivityService loginActivityService;

//    public LoginActivity(LoginActivityService loginActivityService) {
//        this.loginActivityService = loginActivityService;
//    }


    public void init(){

        loginButton = (Button)findViewById(R.id.loginUser);
        password = (EditText)findViewById(R.id.password);
        login = (EditText)findViewById(R.id.login);
        validator = new Validator(this);
        validator.setValidationListener(this);
        loginButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                validator.validate();
            }
        });
    }


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        init();
    }

    public User showUser(String name){
        DbHandler dbHandler = new DbHandler(this, null, null, 1);
        return dbHandler.findUser(name);

    }


    @Override
    public void onValidationSucceeded() {
        try{
                    User user = showUser(getNameValue());
                    String pass = getPasswordFromTextEdit();
                    if(pass.equals(user.get_password())){
                        Toast.makeText(this, "Your datas are valid", Toast.LENGTH_SHORT).show();
                        loginToValidVersionMenu(user);
                    }
                }catch (NullPointerException e){
                    Log.e("getUser", "user with this name is not registered");
                    Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                    startActivity(intent);
                }


    }

    public void loginToValidVersionMenu(User user){
        switch (user.getAccountVersion().getName()){
            case "Education":
                Intent intent = new Intent(LoginActivity.this, EducationMenuActivity.class);
                intent.putExtra("user", user);
                startActivity(intent);
                break;

            case "PRO":
                Intent intentPro = new Intent(LoginActivity.this, ProMenuActivity.class);
                intentPro.putExtra("user", user);
                startActivity(intentPro);
                break;
            default:
                Toast.makeText(this, "Your account is invalid", Toast.LENGTH_LONG).show();
                break;
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

    public String getNameValue(){
        return String.valueOf(login.getText());
    }

    public String getPasswordFromTextEdit(){
        return String.valueOf(password.getText());
    }
}