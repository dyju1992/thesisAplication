package com.example.dyju.thesisapplication;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;

import DatabasePackage.DbHandlerMatrix;
import UsersPackage.User;

/**
 * Created by dyju on 2017-04-09.
 */
public class ExamplesFacade extends AppCompatActivity implements IExamplesFacade {

    final static String examplesUserId = "-1";
    public void addDhToDatabase(){

    }

    @Override
    public Boolean dhDatasAreEntered(DhDatas dhDatas) {
        return !dhDatas.getAlpha().isEmpty() && !dhDatas.getTheta().isEmpty() && !dhDatas.getA().isEmpty() && !dhDatas.getD().isEmpty() && !dhDatas.getManipulatorName().isEmpty();
    }

    @Override
    public void addDhToDatabase(DHForUserDto dhForUserDto, DbHandlerMatrix dbHandlerMatrix) {
        dbHandlerMatrix.addNewDataAboutmanipulator(dhForUserDto);
    }

    @Override
    public ArrayList<DHForUserDto> getManpulatorsDatasForUser(User user, DbHandlerMatrix dbHandlerMatrix) {
//        dbHandlerMatrix.onCreate(dbHandlerMatrix);
        return dbHandlerMatrix.getDhForUser(user);
    }

    @Override
    public void addMockDataToDb(String manipulatorName, DbHandlerMatrix dbHandlerMatrix) {
            if(dbHandlerMatrix.datasArentBeOnDb(manipulatorName, examplesUserId)){
                dbHandlerMatrix.insertManipulatorMockDataToDb(manipulatorName);
            }

    }

    @Override
    public DHForUserDto getDhDatasForManipulatorName(String manipulatorName, DbHandlerMatrix dbHandlerMatrix){
            ArrayList<DHForUserDto> dhForUserDtoArrayList = new ArrayList<>();
        try{
            dhForUserDtoArrayList = dbHandlerMatrix.getDhForManipulatorName(manipulatorName, String.valueOf(-1));
            if(dhForUserDtoArrayList.size()==0){
                dhForUserDtoArrayList = addMockDhAndSelect(manipulatorName, dbHandlerMatrix);

            }
        }catch (Exception e){
                dhForUserDtoArrayList = addMockDhAndSelect(manipulatorName, dbHandlerMatrix);
            }
        return dhForUserDtoArrayList.get(0);

    }

    private ArrayList<DHForUserDto> addMockDhAndSelect(String name, DbHandlerMatrix dbHandlerMatrix){
        dbHandlerMatrix.insertManipulatorMockDataToDb(name);
        return dbHandlerMatrix.getDhForManipulatorName(name, String.valueOf(-1));

    }
}


//CREATE_DH_TABLE = "CREATE TABLE "+DH_TABLE+"("+ ID + " INTEGER PRIMARY KEY," +
//        USER_ID + " INTEGER," + ALPHA_ANGLES + " TEXT," + THETA_ANGLES + " TEXT," +
//        D_DIMENSIONS + " TEXT, " + A_DIMENSIONS + " TEXT, " + MANIPULATOR_NAME+" TEXT)";
