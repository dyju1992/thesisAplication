package com.example.dyju.thesisapplication;

import java.util.ArrayList;

import DatabasePackage.DbHandlerMatrix;
import UsersPackage.User;

/**
 * Created by dyju on 2017-04-09.
 */
public interface IExamplesFacade {

    void validateData();

    Boolean dhDatasAreEntered(DhDatas dhDatas);

    void addDhToDatabase(DHForUserDto dhForUserDto, DbHandlerMatrix dbHandlerMatrix);

    ArrayList<DhDatas> getManpulatorsDatasForUser(User user, DbHandlerMatrix dbHandlerMatrix);

    void addMockDataToDb(String manipulatorName, DbHandlerMatrix dbHandlerMatrix);




}
