package com.example.dyju.thesisapplication.ProUsersClasses;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.dyju.thesisapplication.DHForUserDto;
import com.example.dyju.thesisapplication.DhDatas;
import com.example.dyju.thesisapplication.R;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by dyju on 2017-02-23.
 */
public class ManipulatorDetailsActivity extends AppCompatActivity {

    public void init(DhDatas dhDatas){
        Map<String, String> values = calculateValues(dhDatas);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.result_activity);
        DHForUserDto dhForUserDto = (DHForUserDto) getIntent().getSerializableExtra("dhDatas");
        DhDatas dhDatas = dhForUserDto.getDhDatas();

        init(dhDatas);
    }

    Map<String, String> calculateValues(DhDatas dhDatas){
        Map<String, String> values = new HashMap<>();


        return values;
    }



}
