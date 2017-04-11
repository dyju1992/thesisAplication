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

    public void addDhToDatabase(){

    }

    @Override
    public void validateData() {

        System.out.println("Dziala, yupppppiiiii");
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
    public ArrayList<DhDatas> getManpulatorsDatasForUser(User user, DbHandlerMatrix dbHandlerMatrix) {
//        dbHandlerMatrix.onCreate(dbHandlerMatrix);
        return dbHandlerMatrix.getDhForUser(user);
    }

    @Override
    public void addMockDataToDb(String manipulatorName, DbHandlerMatrix dbHandlerMatrix) {
        if(dbHandlerMatrix.datasArentBeOnDb(dbHandlerMatrix, manipulatorName)){
//            dbHandlerMatrix.insertManipulatorMockDataToDb(manipulatorName, dbHandlerMatrix);
        }
    }

}
