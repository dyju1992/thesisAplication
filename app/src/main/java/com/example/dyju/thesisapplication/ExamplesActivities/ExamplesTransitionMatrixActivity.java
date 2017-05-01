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


    Validator validator;
    Button ex1Button;
    @NotEmpty(message = "Pole nr 1 musi byź wypełnione")
    EditText editText1;
    @NotEmpty(message = "Pole nr 2 musi byź wypełnione")
    EditText editText2;
    TextView textView1;
    TextView textView2;

    String[][] matrix;

    int rowValue1 = getRandomValue();
    int rowValue2 = getRandomValue();
    int columnValue1 = getRandomValue();
    int columnvalue2 = getRandomValue();


    public void init(DHForUserDto dhForUserDto){
        editText1 = (EditText)findViewById(R.id.firstEditValue);
        editText2 = (EditText)findViewById(R.id.secondEditValue);
        TransitionMatrix transitionMatrix = getTransitionMatrix(dhForUserDto);
        ArrayList<String[][]> transitionmatrixList = new ArrayList<>();
        for(int i =2; i<transitionMatrix.getA().length; i++){
            transitionmatrixList.add(transitionMatrix.getTransitionMatrixForData1(i));
        }
        MultipleTransitionMatrix multipleTransitionMatrix = new MultipleTransitionMatrix(transitionMatrix.getTransitionMatrixForData1(1));
//        for (String[][] transMatrix : transitionmatrixList){
//            multipleTransitionMatrix = multipleTransitionMatrix.multiplesMatrix(transMatrix);
//        }
        multipleTransitionMatrix = multipleTransitionMatrix.multiplesMatrix(transitionmatrixList.get(0));
        matrix = multipleTransitionMatrix.getFirstMatrix();

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



    protected void onCreate(Bundle savedInstancedSave){
        super.onCreate(savedInstancedSave);
        setContentView(R.layout.examples1_transition_matrix_activity);
        DHForUserDto dhForUserDto = (DHForUserDto) getIntent().getSerializableExtra("dhDatas");
        addDataToTextView();
        init(dhForUserDto);
    }

    private void addDataToTextView(){
        textView1 = (TextView) findViewById(R.id.firstTextViewValue);
        textView1.setText("Wprowadź wartość z wiersza: " + String.valueOf(rowValue1) + " i kolumny: " + String.valueOf(columnValue1));
        textView2 = (TextView) findViewById(R.id.secondTextViewValue);
        textView2.setText("Wprowadź wartość z wiersza: " + String.valueOf(rowValue2) + " i kolumny: " + String.valueOf(columnvalue2));

    }

    private int getRandomValue(){
        int value = 1 + (int)(Math.random() * 4);
        return value;
    }

    private TransitionMatrix getTransitionMatrix(DHForUserDto dhForUserDto){
        DhDatas dhDatas = dhForUserDto.getDhDatas();
        String[] theta = dhDatas.getTheta().split(",");
        String[] alpha = dhDatas.getAlpha().split(",");
        String[] d = dhDatas.getD().split(",");
        String[] a = dhDatas.getA().split(",");

        return new TransitionMatrix(theta, alpha, d, a);
    }

    @Override
    public void onValidationSucceeded() {
        if(!matrix[rowValue1-1][columnValue1-1].equals(editText1.getText())){
            Toast.makeText(this, "Wprowadź ponownie wartość w pierwszym polu", Toast.LENGTH_SHORT).show();

        }else {
            if(!matrix[rowValue2-1][columnvalue2-1].equals(editText2.getText())){
                Toast.makeText(this, "Wprowadź ponownie wartość w drugim polu", Toast.LENGTH_SHORT).show();
            }else{
                Intent intent = new Intent(ExamplesTransitionMatrixActivity.this, ResultExample1Activity.class);
                intent.putExtra("matrix", matrix);
                startActivity(intent);
            }
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
