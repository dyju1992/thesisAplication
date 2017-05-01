package com.example.dyju.thesisapplication;

import java.util.ArrayList;

import DatabasePackage.DbHandler;
import DatabasePackage.DbHandlerMatrix;
import UsersPackage.User;

/**
 * Created by dyju on 2017-04-09.
 */
public interface IExamplesFacade {

    Boolean dhDatasAreEntered(DhDatas dhDatas);

    void addDhToDatabase(DHForUserDto dhForUserDto, DbHandlerMatrix dbHandlerMatrix);

    ArrayList<DHForUserDto> getManpulatorsDatasForUser(User user, DbHandlerMatrix dbHandlerMatrix);

    void addMockDataToDb(String manipulatorName, DbHandlerMatrix dbHandlerMatrix);

    DHForUserDto getDhDatasForManipulatorName(String manipulatorName, DbHandlerMatrix dbHandlerMatrix);




}
