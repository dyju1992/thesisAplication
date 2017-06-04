package com.example.dyju.thesisapplication;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;


public class CalculationService implements ICalculationService {

    @Override
    public HashMap<String, String> calculateVariables(DhDatas dhDatas){
        HashMap<String, String> values = new HashMap<>();
//        String[] splitedTheta = dhDatas.getTheta().split(",");
//        String[] splitedAlpha = dhDatas.getAlpha().split(",");
//        String[] splitedA = dhDatas.getA().split(",");
//        String[] splitedD = dhDatas.getD().split(",");
//
//        TransitionMatrix transitionMatrix = new TransitionMatrix(splitedTheta, splitedAlpha, splitedD, splitedA);
//        String[] r33 = {"0", "0", "0", "1"};
//        String[][] firstMatrix = transitionMatrix.getAMatrixForData(0);
//        String[][] secondMatrix = transitionMatrix.getAMatrixForData(1);
//        String[][] thirdMatrix = transitionMatrix.getAMatrixForData(2);
//        String[] r32 = multipleMatrixAndVector(thirdMatrix, r33);
//        String[] r31 = multipleMatrixAndVector(secondMatrix, r32);
//        String[] r30 = multipleMatrixAndVector(firstMatrix, r31);
        String[] vector = getPositionVectorForDhDatas(dhDatas);

        values.put("rx", vector[0]);
        values.put("ry", vector[1]);
        values.put("rz", vector[2]);


        return values;

    }

    @Override
    public String[] getPositionVectorForDhDatas(DhDatas dhDatas){
        String[] splitedTheta = dhDatas.getTheta().split(",");
        String[] splitedAlpha = dhDatas.getAlpha().split(",");
        String[] splitedA = dhDatas.getA().split(",");
        String[] splitedD = dhDatas.getD().split(",");

        TransitionMatrix transitionMatrix = new TransitionMatrix(splitedTheta, splitedAlpha, splitedD, splitedA);
        String[] r = {"0", "0", "0", "1"};
        List<String[][]> matrices = new ArrayList<>();
        for (int i =0; i<splitedA.length; i++){
            matrices.add(0, transitionMatrix.getAMatrixForData(i));
        }
        for(String[][] matrix : matrices){
            r = multipleMatrixAndVector(matrix, r);
        }
        return r;
    }

    @Override
    public HashMap<String, String> getValueForr30(String[] r30, PositionVectorDto positionVectorDto){
        HashMap<String, String> value = new HashMap<>();
        String firstEquation = getEquation(r30[0], positionVectorDto.getRx());
        String secondEquation = getEquation(r30[1], positionVectorDto.getRy());
        String thirdEquation = getEquation(r30[2], positionVectorDto.getRz());
        String expFromFirstEq = getExpressionToSimplyfy(firstEquation);
        String expFromSecondtEq = getExpressionToSimplyfy(secondEquation);
        String expFromThirdEq = getExpressionToSimplyfy(thirdEquation);
//        String simpifiedEq = getEq(expFromFirstEq, expFromSecondtEq, expFromThirdEq);




        return value;
    }

//    public String getEq(String firstEq, String secondEq, String thirdEq){
//        if(firstEq.equals(secondEq)){
//            return
//        }

//    }


    @Override
    public String getTheLargestRowInTwoString(String first, String second){

        return null;
    }

    @Override
    public String getExpressionToSimplyfy(String word){
        if(word.contains("*(")) {
            String bracketToSimplify = getBracketFromString(word);
            if (word.contains(bracketToSimplify + "*")) {
                String multValueToBracket = getMultValueToBracket(word.substring(word.indexOf(bracketToSimplify) + bracketToSimplify.length()));
                return bracketToSimplify + multValueToBracket;
            }
            return bracketToSimplify;
        }else return "";
    }

    private String getMultValueToBracket(String substring){
        if(substring.contains("+")){
            return substring.split(Pattern.quote("+"))[0];
        }else
            return substring.split(Pattern.quote("-"))[0];
    }

    @Override
    public String getBracketFromString(String word){
        int bracketStartPosition = word.indexOf("*(");
        String substrStart = word.substring(bracketStartPosition);
        char[] chars = substrStart.toCharArray();
        int openedBrackets = 0;
        int endPosition = 0;
        for(int i =0; i<substrStart.length(); i++){
            if(chars[i] == ')'){
                if(openedBrackets == 1){
                    endPosition++;
                    break;
                }else{
                    endPosition++;
                    openedBrackets--;
                }
            }else if(chars[i] == '('){
                openedBrackets++;
                endPosition++;
            }else{
                endPosition++;
            }
        }

        return substrStart.substring(0, endPosition);
    }

    private boolean equationHasValueInBracket(String equation){
        return equation.contains("s");

    }

    private String getEquation(String firstparam, String secondParam){
        return firstparam + "=" + secondParam;
    }

    @Override
    public String[] multipleMatrixAndVector(String[][] matrix, String[] vector){
        String[] newVector = new String[4];
        for(int i =0; i<4; i++){
            String value = getMultipleValue(matrix[i][0], vector[0])+ "+" + getMultipleValue(matrix[i][1], vector[1]) + "+" + getMultipleValue(matrix[i][2], vector[2]) + "+" + getMultipleValue(matrix[i][3], vector[3]);
            newVector[i] = getAddSimplyfiedData(value);
        }

        return newVector;
    }

    @Override
    public String getMultipleValue(String var1, String var2){
        if(var1.equals("0") || var2.equals("0") || var1.equals("0.0") || var2.equals("0.0")){
            return "0.0";
        }

        Boolean advancedMult = false;
        try {
            Double val1 = Double.valueOf(var1);
            Double val2 = Double.valueOf(var2);
            return String.valueOf(val1*val2);
        }catch (Exception e){
            if(var1.contains("+")) {
                var1 = "("+var1+")";
                advancedMult = true;
            }
            if(var2.contains("+")) {
                var2 = "("+var2+")";
                advancedMult = true;
            }
            if(advancedMult) return var1+"*"+var2;
            return getmultSimplifiedData(var1 + "*" + var2);
        }

    }


    @Override
    public Boolean isValidRowInVector(String calculatedValue, String writtenValue){
        String[] splitedCalculatedValues = calculatedValue.split("\\*|\\+|\\-");
        ArrayList<String> splitedValuesToCheck = getSplitedValues(splitedCalculatedValues);
        for(String value : splitedValuesToCheck){
            if(!writtenValue.contains(value)){
                return false;
            }else{
                writtenValue = writtenValue.substring(0, writtenValue.indexOf(value))+writtenValue.substring(writtenValue.indexOf(value)+value.length(),writtenValue.length());
            }
        }
        return true;
    }


    @Override
    public ArrayList<String> getSplitedValues(String[] values){
        ArrayList<String> valuesToCheck = new ArrayList<>();
        for(int i = 0; i<values.length; i++){
            if(!values[i].equals("") && !values[i].isEmpty() && !values[i].equals("1.0")){
                String value = getValueToCheck(values[i]);
                valuesToCheck.add(value);
            }
        }

        return valuesToCheck;

    }

    public String getValueToCheck(String value){
        int openedBrackets = 0;
        int endsBrackets = 0;
        char[] val = value.toCharArray();
        for (int i = 0; i<val.length; i++){
            if(val[i] == ')'){
                endsBrackets++;
            }else if(val[i] == '('){
                openedBrackets++;
            }
        }
        if(openedBrackets>endsBrackets){
            value = value.substring(1, value.length());
        }else if(openedBrackets<endsBrackets){
            value = value.substring(0, value.length()-1);
        }
        return value;
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


    @Override
    public String getmultSimplifiedData(String data){
        String pattern = "*";
        return getSimplyfiedData(data, pattern);

    }


    @Override
    public String getAddSimplyfiedData(String data){
        String pattern = "+";
        return getSimplyfiedData(data, pattern);
    }

    public String getSimplyfiedData(String data, String pattern){
        String [] splitedArray = data.split(Pattern.quote(pattern));

        Double value;
        ArrayList<Double> doubles = new ArrayList<>();
        ArrayList<String> strings = new ArrayList<>();
        for(int i = 0; i<splitedArray.length; i++){
            if(isNumericValue(splitedArray[i])){
                doubles.add(Double.parseDouble(splitedArray[i]));

            }else {
                if(!splitedArray[i].equals("")) {
                    strings.add(splitedArray[i]);
                }
            }
        }
//        if(doubles.size()<1){
//            return data;
//        }

        return getSimpDataFromValidPattern(doubles, strings, pattern);
    }

    private String getSimpDataFromValidPattern(List<Double> doubles, List<String> strings, String pattern){
        switch (pattern){
            case "+":
                return addStringsAndDoubles(strings, doubles);
            default:
                return multStringsAndDoubles(strings, doubles);
        }

    }

    private String multStringsAndDoubles(List<String> strings, List<Double> doubles){
        Double numValue = 1d;
        for(Double num : doubles){
            numValue *= num;
        }

        if(numValue.equals(0.0) || numValue.equals(-0.0)){
            return "0.0";
        }
        if(numValue.equals(1.0)){
            return getMultipleStringValue(strings).substring(1);
        }

        if(strings.size()>0){
            String stringValue = getMultipleStringValue(strings);
            return String.valueOf(numValue)+stringValue;
        }

        return String.valueOf(numValue);
    }


    private String getMultipleStringValue(List<String> strValues){
        String stringValue = "";
        for (String strVal : strValues){
            stringValue += "*";
            if(stringValue.contains("+")){
                stringValue +="("+strVal+")";
            }else {
                stringValue += strVal;
            }
        }
        return stringValue;
    }

    private boolean isNumericValue(String value){
        try {
            Double.parseDouble(value);
            return true;
        }catch (NumberFormatException e){
            return false;
        }

    }

    protected String addStringsAndDoubles(List<String> strings, List<Double> doubles){
        Double value = 0d;
        for(Double doubleValue : doubles){
            value +=doubleValue;
        }

        if(value.equals(0.0) || value.equals(-0.0)){
            return getStringValue(strings).equals("") ? "0.0" : getStringValue(strings);
        }

        String stringValue = getStringValue(strings);
        if (stringValue.equals("")){
            return String.valueOf(value);
        }
        return String.valueOf(value)+ "+" + stringValue;
    }

    private String getStringValue(List<String> strings){
        String stringValue = "";
        if(strings.size()>0){
            for (int i =0; i<strings.size(); i++){
//                stringValue += "+";
                stringValue +=strings.get(i);
                if(i<strings.size()-1){
                   stringValue +="+";
                }
            }
            return stringValue;
        }else return "";
    }
}
