package com.example.dyju.thesisapplication.ProUsersClasses;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ListViewCompat;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dyju.thesisapplication.DHForUserDto;
import com.example.dyju.thesisapplication.DhDatas;
import com.example.dyju.thesisapplication.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import DatabasePackage.DbHandlerMatrix;
import UsersPackage.User;


public class ShowManipulatorsActivity extends AppCompatActivity {

    private ListView listView;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_manipulators_activity);

        listView = (ListView)findViewById(R.id.listView1);
        final User user = (User)getIntent().getSerializableExtra("user");

        final HashMap<String, DhDatas> userManipulators = getManipulatorsForUser(user);
        ArrayList<String> manipulatorsName = getManipulatorsNameFromMap(userManipulators);
        adapter = new ArrayAdapter<String>(this, R.layout.one_row, manipulatorsName);
        if(manipulatorsName.size()>0) {
            listView.setAdapter(adapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    String name = (String) ((TextView) view).getText();
                    Intent intent = new Intent(ShowManipulatorsActivity.this, ManipulatorDetailsActivity.class);
                    intent.putExtra("dhDatas", userManipulators.get(name));
                    intent.putExtra("user", user);
                    startActivity(intent);
                }
            });
        }else{
            Toast.makeText(this, "Nie ma żadnego manipulatora dla tego użytkownika", Toast.LENGTH_LONG).show();
        }
    }

    protected HashMap<String, DhDatas> getManipulatorsForUser(User user){
        DbHandlerMatrix dbHandlerMatrix = new DbHandlerMatrix(this);
        ArrayList<DHForUserDto> manipulators = dbHandlerMatrix.getDhForUser(user);
        return getMappedManipulatorsData(manipulators);
    }

    protected HashMap<String, DhDatas> getMappedManipulatorsData(ArrayList<DHForUserDto> manipulators){
        HashMap<String, DhDatas> mappedManipulators = new HashMap<>();
        for(DHForUserDto manipulator : manipulators){
            mappedManipulators.put(manipulator.getDhDatas().getManipulatorName(), manipulator.getDhDatas());
        }
        return mappedManipulators;
    }

    protected ArrayList<String> getManipulatorsNameFromMap(HashMap<String, DhDatas> values){
        ArrayList<String> names = new ArrayList<>();
        for (String name : values.keySet()){
            names.add(name);
        }
        return names;
    }



}
