package com.example.dyju.thesisapplication.ExamplesActivities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dyju.thesisapplication.DHForUserDto;
import com.example.dyju.thesisapplication.DhDatas;
import com.example.dyju.thesisapplication.IResultCalculationService;
import com.example.dyju.thesisapplication.MultipleTransitionMatrix;
import com.example.dyju.thesisapplication.R;
import com.example.dyju.thesisapplication.ResultCalculationService;
import com.example.dyju.thesisapplication.ResultValidationMessages;
import com.example.dyju.thesisapplication.TransitionMatrix;
import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.regex.Pattern;


public class ResultInverseExampleActivity extends AppCompatActivity implements Validator.ValidationListener {

    Button checkResultButton;
    TextView firstVariable;
    TextView secondVariable;
    @NotEmpty(message = "Wprowadź wartość")
    EditText firstResult;
    @NotEmpty(message = "Wprowadź wartość")
    EditText secondResult;
    TextView thirdVariable;
    @NotEmpty(message = "Wprowadź wartość")
    EditText thirdResult;

    DhDatas dhDatas;
    Validator validator;
    IResultCalculationService resultCalculationService;

    public ResultInverseExampleActivity() {
        this.resultCalculationService = new ResultCalculationService();
    }

    protected void onCreate(Bundle savedInstancedSave){
        super.onCreate(savedInstancedSave);
        setContentView(R.layout.result_ex1_activity);
        DhDatas dhDatas = (DhDatas) getIntent().getSerializableExtra("dhDatas");
        this.dhDatas = dhDatas;
        init();
    }

    public void init() {
        firstVariable = (TextView)findViewById(R.id.firstVariable);
        secondVariable = (TextView)findViewById(R.id.secondVariable);
        firstResult = (EditText)findViewById(R.id.firstResult);
        secondResult = (EditText)findViewById(R.id.secondResult);
        thirdVariable = (TextView)findViewById(R.id.thirdVariable);
        thirdResult = (EditText)findViewById(R.id.thirdResult);
        checkResultButton = (Button)findViewById(R.id.resulrCheckButton);
        setTextInEditText(dhDatas.getManipulatorName());
        validator = new Validator(this);
        validator.setValidationListener(this);
        checkResultButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                validator.validate();

            }
        });
    }

    private void setTextInEditText(String manipulatorName){
        switch (manipulatorName){
            case "Manipulator_1":
                firstVariable.setText("theta1(t):");
                secondVariable.setText("theta2(t):");
                thirdVariable.setText("lambda3:");
                break;

            case "Manipulator_2":
                firstVariable.setText("theta1(t):");
                secondVariable.setText("lambda2(t):");
                thirdVariable.setText("theta3(t):");
                break;

            default:
                firstVariable.setText("rx:");
                secondVariable.setText("ry:");
                thirdVariable.setText("rz:");
        }

    }

    @Override
    public void onValidationSucceeded() {

        Map<String, String> values = new HashMap<String, String>();
        values.put(String.valueOf(firstVariable.getText()), String.valueOf(firstResult.getText()));
        values.put(String.valueOf(secondVariable.getText()), String.valueOf(secondResult.getText()));
        values.put(String.valueOf(thirdVariable.getText()), String.valueOf(thirdResult.getText()));
        ResultValidationMessages validateMessage = resultCalculationService.getMessageFromValidationOfValues(dhDatas.getManipulatorName(), values);
        if(validateMessage.equals(ResultValidationMessages.OK)){
            Toast.makeText(this, "Wykonałeś poprawnie zadanie. Gratuluje!!!", Toast.LENGTH_SHORT).show();
        }
        else{
            showMessage(validateMessage);
        }

    }

    private void showMessage(ResultValidationMessages validationMessages){
        switch (validationMessages){
            case INVALIDFIRSTVALUE:
                View view = (EditText) findViewById(R.id.firstResult);
                String message = "Podana wartość jest nieprawidłowa";
                ((EditText) view).setError(message);
                break;

            case INVALIDSECONDVALUE:
                View view1 = (EditText) findViewById(R.id.secondResult);
                String message1 = "Podana wartość jest nieprawidłowa";
                ((EditText) view1).setError(message1);
                break;
            case INVALIDTHIRDVALUE:
                View res3 = (EditText) findViewById(R.id.thirdResult);
                String message2 = "Podana wartość jest nieprawidłowa";
                ((EditText) res3).setError(message2);
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

//    protected List<String> getDataToCheck(String[][] matrix){
//        Set<String> datasToCheck = new HashSet<>();
//        List<String> datas = new ArrayList<>();
//
//        for(int i = 0; i<4; i++){
//            for(int j = 0; j<4; j++){
//                datasToCheck.addAll(splitedDatasToCheck(matrix[i][j]));
//            }
//        }
//
//        for (String data : datasToCheck){
//            datas.add(data);
//        }
//
//        return datas;
//    }


//    protected List<String> splitedDatasToCheck(String data){
//        ArrayList<String> list = new ArrayList<>();
//        String[] plusSplited = data.split(Pattern.quote("+"));
//        for(int i = 0; i<plusSplited.length; i++){
//            String[] multSplited = plusSplited[i].split(Pattern.quote("*"));
//            for(int j = 0; j<multSplited.length; j++){
//                if(multSplited[j].contains("(t)")){
//                    list.add(multSplited[j]);
//                }
//            }
//        }
//        return getDatasFromTrygonometricFunctions(list);
//    }

//    protected List<String> getDatasFromTrygonometricFunctions(List<String> datas){
//        ArrayList<String> resultList = new ArrayList<>();
//        for(String data : datas){
//            int start = data.indexOf("(")+1;
//            int end = data.indexOf(")")+1;
//
//            resultList.add(data.substring(start, end));
//        }
//        return resultList;
//    }








}
