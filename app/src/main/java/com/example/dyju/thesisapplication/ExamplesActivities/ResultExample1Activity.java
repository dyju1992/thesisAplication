package com.example.dyju.thesisapplication.ExamplesActivities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.dyju.thesisapplication.DHForUserDto;
import com.example.dyju.thesisapplication.DhDatas;
import com.example.dyju.thesisapplication.MultipleTransitionMatrix;
import com.example.dyju.thesisapplication.R;
import com.example.dyju.thesisapplication.TransitionMatrix;

import java.util.ArrayList;


public class ResultExample1Activity extends AppCompatActivity {


    public void init(String[][] matrix) {
    }





    protected void onCreate(Bundle savedInstancedSave){
        super.onCreate(savedInstancedSave);
        setContentView(R.layout.result_ex1_activity);
        String[][] matrices = (String[][]) getIntent().getSerializableExtra("matrix");
        init(matrices);
    }


}
