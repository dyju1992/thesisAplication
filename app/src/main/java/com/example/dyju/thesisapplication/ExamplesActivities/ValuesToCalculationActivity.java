package com.example.dyju.thesisapplication.ExamplesActivities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.dyju.thesisapplication.DhDatas;
import com.example.dyju.thesisapplication.IResultCalculationService;
import com.example.dyju.thesisapplication.R;
import com.example.dyju.thesisapplication.ResultCalculationService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class ValuesToCalculationActivity extends AppCompatActivity {

    DhDatas dhDatas;
    ListView values;
    Button goToResult;
    ArrayAdapter<String> adapter;

    IResultCalculationService resultCalculationService;

    public ValuesToCalculationActivity() {
        this.resultCalculationService = new ResultCalculationService();
    }

    protected void onCreate(Bundle savedInstancedSave){
        super.onCreate(savedInstancedSave);
        setContentView(R.layout.values_to_calculation_activity);
        DhDatas dhDatas = (DhDatas) getIntent().getSerializableExtra("dhDatas");
        this.dhDatas = dhDatas;
        init();
    }

    public void init(){
        values = (ListView)findViewById(R.id.valuesList);
        goToResult = (Button)findViewById(R.id.goToExerciseResult);
        Map<String,String> constantValues = resultCalculationService.getDatasForManipulator(dhDatas.getManipulatorName());
        List<String> valuesToList = getConstantValues(constantValues);
        adapter = new ArrayAdapter<String>(this, R.layout.one_row, valuesToList);
        values.setAdapter(adapter);

        goToResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ValuesToCalculationActivity.this, ExampleResultActivity.class);
                intent.putExtra("dhDatas", dhDatas);
                startActivity(intent);
            }
        });

    }


    public List<String> getConstantValues(Map<String, String> values){
        List<String> valuesToList = new ArrayList<>();
        for(String key : values.keySet()){
            valuesToList.add(key + ": "+values.get(key));
        }

        return valuesToList;
    }
}
