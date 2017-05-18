package com.example.dyju.thesisapplication.ExamplesActivities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dyju.thesisapplication.CalculationService;
import com.example.dyju.thesisapplication.DHForUserDto;
import com.example.dyju.thesisapplication.DhDatas;
import com.example.dyju.thesisapplication.ICalculationService;
import com.example.dyju.thesisapplication.MultipleTransitionMatrix;
import com.example.dyju.thesisapplication.MultipleTransitionMatrixDto;
import com.example.dyju.thesisapplication.R;
import com.example.dyju.thesisapplication.TransitionMatrix;
import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import UsersPackage.User;


public class ExamplesTransitionMatrixActivity extends AppCompatActivity implements Validator.ValidationListener{

    ICalculationService calculationService;

    public ExamplesTransitionMatrixActivity() {
        this.calculationService = new CalculationService();
    }

    Validator validator;
    Button ex1Button;
    @NotEmpty(message = "Pole nr 1 musi byź wypełnione")
    EditText editText1;
    @NotEmpty(message = "Pole nr 2 musi byź wypełnione")
    EditText editText2;
    TextView textView1;
    TextView textView2;
    DhDatas dhDatas;


    protected void onCreate(Bundle savedInstancedSave){
        super.onCreate(savedInstancedSave);
        setContentView(R.layout.examples1_transition_matrix_activity);
        dhDatas = (DhDatas) getIntent().getSerializableExtra("dhDatas");
        addDataToTextView();
        init();
    }

    public void init(){
        editText1 = (EditText)findViewById(R.id.firstEditValue);
        editText2 = (EditText)findViewById(R.id.secondEditValue);
//        TransitionMatrix transitionMatrix = getTransitionMatrix(dhForUserDto);
//        ArrayList<String[][]> transitionmatrixList = new ArrayList<>();
//        for(int i =2; i<transitionMatrix.getA().length; i++){
//            transitionmatrixList.add(transitionMatrix.getTransitionMatrixForData1(i));
//        }
//        MultipleTransitionMatrix multipleTransitionMatrix = new MultipleTransitionMatrix(transitionMatrix.getTransitionMatrixForData1(1));
//        for (String[][] transMatrix : transitionmatrixList){
//            multipleTransitionMatrix = multipleTransitionMatrix.multiplesMatrix(transMatrix);
//        }
//        multipleTransitionMatrix = multipleTransitionMatrix.multiplesMatrix(transitionmatrixList.get(0));
//        matrix = multipleTransitionMatrix.getFirstMatrix();

        ex1Button = (Button) findViewById(R.id.nextButton);
        validator = new Validator(this);
        validator.setValidationListener(this);
        ex1Button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                validator.validate();
            }
        });


    }

    private void addDataToTextView(){
        textView1 = (TextView) findViewById(R.id.firstTextViewValue);
        textView1.setText("Wprowadź wartość z pierwszego wiersza wektora");
        textView2 = (TextView) findViewById(R.id.secondTextViewValue);
        textView2.setText("Wprowadź wartość z drugiego wiersza wektora");
    }

    @Override
    public void onValidationSucceeded() {
        String [] r30 = calculationService.getr30ForDhDatas(dhDatas);
        if(!isValidRowInVector(r30[0], editText1.getText().toString())){
            View view = (EditText) findViewById(R.id.firstEditValue);
            String message = "Wprowadź ponownie wartość w pierwszym polu";
            ((EditText) view).setError(message);
            Toast.makeText(this, "Wprowadź ponownie wartość w pierwszym polu", Toast.LENGTH_SHORT).show();
        }else {
            if(!isValidRowInVector(r30[1], editText2.getText().toString())){
                View view = (EditText) findViewById(R.id.secondEditValue);
                String message = "Wprowadź ponownie wartość w pierwszym polu";
                ((EditText) view).setError(message);
                Toast.makeText(this, "Wprowadź ponownie wartość w drugim polu", Toast.LENGTH_SHORT).show();
            }else{
                Intent intent = new Intent(ExamplesTransitionMatrixActivity.this, ResultExample1Activity.class);
                intent.putExtra("dhDatas", dhDatas);
                startActivity(intent);
            }
        }
    }

    public Boolean isValidRowInVector(String calculatedValue, String writtenValue){
        String[] splitedCalculatedValues = calculatedValue.split("\\*|\\+|\\-");
        ArrayList<String> splitedValuesToCheck = getSplitedValues(splitedCalculatedValues);
        for(String value : splitedValuesToCheck){
            if(!writtenValue.contains(value)){
                return false;
            }else{
                writtenValue = writtenValue.substring(0, writtenValue.indexOf(value))+writtenValue.substring(writtenValue.indexOf(value)+value.length(),writtenValue.length());
            }
        }
        return true;
    }

    public ArrayList<String> getSplitedValues(String[] values){
        ArrayList<String> valuesToCheck = new ArrayList<>();
        for(int i = 0; i<values.length; i++){
            if(!values[i].equals("") && !values[i].isEmpty() && !values[i].equals("1.0")){
                String value = getValueToCheck(values[i]);
                valuesToCheck.add(value);
            }
        }

        return valuesToCheck;

    }

    public String getValueToCheck(String value){
        int openedBrackets = 0;
        int endsBrackets = 0;
        char[] val = value.toCharArray();
        for (int i = 0; i<val.length; i++){
            if(val[i] == ')'){
                endsBrackets++;
            }else if(val[i] == '('){
                openedBrackets++;
            }
        }
        if(openedBrackets>endsBrackets){
            value = value.substring(1, value.length());
        }else if(openedBrackets<endsBrackets){
            value = value.substring(0, value.length()-1);
        }
        return value;
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
