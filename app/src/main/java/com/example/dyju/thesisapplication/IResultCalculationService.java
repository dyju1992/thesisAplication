package com.example.dyju.thesisapplication;

import java.util.Map;

public interface IResultCalculationService {

    ResultValidationMessages getMessageFromValidationOfValues(String manipulatorName, Map<String, String> values);

    Map<String, String> getDatasForManipulator(String name);

    Map<String, String> getResultForFirstManipulator();
}
