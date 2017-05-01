package com.example.dyju.thesisapplication;

import java.io.Serializable;
import java.text.Format;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Created by dyju on 2017-04-30.
 */
public class MultipleTransitionMatrix extends MultipleTransitionMatrixDto implements Serializable {


    public MultipleTransitionMatrix(String[][] firstMatrix) {
        super(firstMatrix);
    }

    public MultipleTransitionMatrix() {
    }

    public MultipleTransitionMatrix multiplesMatrix(String[][] secondMatrix){
        MultipleTransitionMatrix multMatrix = mult(secondMatrix);
//        multMatrix[0][0] = firstMatrix[0][0] + "*" + secondMatrix[0][0] + "+" + firstMatrix[0][1] + secondMatrix[1][0];
//        multMatrix[0][1] = firstMatrix[0][1] + "*" + secondMatrix[0][1];
//        multMatrix[0][2] = firstMatrix[0][2] + "*" + secondMatrix[0][2];
//        multMatrix[0][3] = firstMatrix[0][3] + "*" + secondMatrix[0][3];
//
//        multMatrix[1][0] = firstMatrix[1][0] + "*" + secondMatrix[1][0];
//        multMatrix[1][1] = firstMatrix[1][1] + "*" + secondMatrix[1][1];
//        multMatrix[1][2] = firstMatrix[1][2] + "*" + secondMatrix[1][2];
//        multMatrix[1][3] = firstMatrix[1][3] + "*" + secondMatrix[1][3];
//
//        multMatrix[2][0] = firstMatrix[2][0] + "*" + secondMatrix[2][0];
//        multMatrix[2][1] = firstMatrix[2][1] + "*" + secondMatrix[2][1];
//        multMatrix[2][2] = firstMatrix[2][2] + "*" + secondMatrix[2][2];
//        multMatrix[2][3] = firstMatrix[2][3] + "*" + secondMatrix[2][3];
//
//        multMatrix[3][0] = firstMatrix[3][0] + "*" + secondMatrix[3][0];
//        multMatrix[3][1] = firstMatrix[3][1] + "*" + secondMatrix[3][1];
//        multMatrix[3][2] = firstMatrix[3][2] + "*" + secondMatrix[3][2];
//        multMatrix[3][3] = firstMatrix[3][3] + "*" + secondMatrix[3][3];


        multMatrix = simplyfyMatrix(multMatrix);

        return multMatrix;
    }

    protected MultipleTransitionMatrix simplyfyMatrix(MultipleTransitionMatrix multipleTransitionMatrix){
        String[][] matrix = new String[4][4];
        String[][] oldMatrix = multipleTransitionMatrix.getFirstMatrix();
        for(int i = 0; i<4; i++){
            for(int j = 0; j<4; j++){
                matrix[i][j] = getSimplfiedAdddedAndMultiplyData(oldMatrix[i][j]);
            }
        }

        return new MultipleTransitionMatrix(matrix);
    }

    protected String getSimplfiedAdddedAndMultiplyData(String data){
        String[] plusSplitedDatas = data.split(Pattern.quote("+"));
        ArrayList<String> simplyfiedDatas = new ArrayList<>();
        for (int i =0; i<plusSplitedDatas.length; i++){
           String simplyfiedData = getSimplyfiedData(plusSplitedDatas[i]);
           simplyfiedDatas.add(simplyfiedData);
        }

        return addedDatas(simplyfiedDatas);
    }

    protected String addedDatas(ArrayList<String> stringDatas){
        String addedValue = "";

        for(int i =0; i<stringDatas.size(); i++){
            if(!isNullValue(stringDatas.get(i))){
                if(!addedValue.equals("")) {
                    addedValue += "+";
                }
                addedValue += stringDatas.get(i);
            }
        }
        if(addedValue.equals("")){
            addedValue = "0";
        }

        return addedValue;
    }

    protected boolean isNullValue(String data){
        return data.equals("0") || data.equals("0.0") || data.equals("-0.0") || data.isEmpty();
    }

    public MultipleTransitionMatrix mult(String[][] second){
        String[][] multMatrix = new String[4][4];
        for(int i=0; i< 4; i++){
            for(int j = 0; j<4; j++) {
                multMatrix[i][j] = firstMatrix[i][0] + "*" +second[0][j] + "+" + firstMatrix[i][1] + "*" +second[1][j] + "+" + firstMatrix[i][2] + "*" +second[2][j] + "+" + firstMatrix[i][3] + "*" +second[3][j];
            }
        }
        MultipleTransitionMatrix multipleTransitionMatrix = new MultipleTransitionMatrix(multMatrix);

        return multipleTransitionMatrix;
    }

//    public String addMultArray(String[][] secondMatr){
//        ArrayList<String> firstArr = new ArrayList<>();
//        ArrayList<String> secArr = new ArrayList<>();
//    }

    protected MultipleTransitionMatrix getSimplifiedMatrix(String[][] stringMatrix){
        String[][] simpMatrix = new String[4][4];
        for(int i =0; i<4; i++){
            for(int j = 0; j<4; j++){
                simpMatrix[i][j] = getSimplyfiedData(stringMatrix[i][j]);
            }
        }



        return new MultipleTransitionMatrix(simpMatrix);
    }

    protected String multiplyStringsAndDoubles(ArrayList<String> strings, ArrayList<Double> doubles){
        Double value = 1d;
        for(Double doubleValue : doubles){
            value *=doubleValue;
        }

        if(value.equals(0.0) || value.equals(-0.0)){
            return "0.0";
        }

        String stringValue = "";
        if(strings.size()>0){
            for (int i =0; i<strings.size(); i++){
                stringValue += "*";
                stringValue +=strings.get(i);
            }
            return String.valueOf(value)+stringValue;
        }
        return String.valueOf(value);
    }

    protected String getSimplyfiedData(String data){
        String [] splitedArray = data.split(Pattern.quote("*"));

        Double value;
        ArrayList<Double> doubles = new ArrayList<>();
        ArrayList<String> strings = new ArrayList<>();
        for(int i = 0; i<splitedArray.length; i++){
            if(isNumericValue(splitedArray[i])){
                doubles.add(Double.parseDouble(splitedArray[i]));

            }else {
                strings.add(splitedArray[i]);
            }
        }
        if(doubles.size()<1){
            return data;
        }
        return multiplyStringsAndDoubles(strings, doubles);
    }

    private boolean isNumericValue(String value){
        try {
            Double.parseDouble(value);
            return true;
        }catch (NumberFormatException e){
            return false;
        }

    }
}
