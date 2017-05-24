package com.example.dyju.thesisapplication;

import java.util.ArrayList;

import DatabasePackage.DbHandler;
import DatabasePackage.DbHandlerMatrix;
import UsersPackage.User;


public interface IExamplesFacade {

    Boolean dhDatasAreEntered(DhDatas dhDatas);

    void addDhToDatabase(DHForUserDto dhForUserDto, DbHandlerMatrix dbHandlerMatrix);

    ArrayList<DHForUserDto> getManpulatorsDatasForUser(User user, DbHandlerMatrix dbHandlerMatrix);

    void addMockDataToDb(String manipulatorName, DbHandlerMatrix dbHandlerMatrix);

    DHForUserDto getDhDatasForManipulatorName(String manipulatorName, DbHandlerMatrix dbHandlerMatrix);




}
