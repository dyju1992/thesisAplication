package com.example.dyju.thesisapplication;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public interface ICalculationService {

    HashMap<String, String> calculateVariables(DhDatas dhDatas);

    String[] multipleMatrixAndVector(String[][] matrix, String[] vector);

    String getMultipleValue(String var1, String var2);

    String getmultSimplifiedData(String data);

    String getAddSimplyfiedData(String data);

    String[] getr30ForDhDatas(DhDatas dhDatas);

    HashMap<String, String> getValueForr30(String[] r30, PositionVectorDto positionVectorDto);

    String getTheLargestRowInTwoString(String first, String second);

    String getBracketFromString(String word);

    String getExpressionToSimplyfy(String word);

    Boolean isValidRowInVector(String calculatedValue, String writtenValue);

    ArrayList<String> getSplitedValues(String[] values);
}
