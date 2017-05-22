package com.example.dyju.thesisapplication;

import android.annotation.SuppressLint;
import android.util.Log;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;


public class ResultCalculationService implements IResultCalculationService {

    private static final String TAG = "ResultCalculationService";

    @Override
    public ResultValidationMessages getMessageFromValidationOfValues(String manipulatorName, Map<String, String> values) {
        Map<String, String> validResult = getValidResultForManipulator(manipulatorName);

        return getMessage(manipulatorName, values, validResult);
    }

    public ResultValidationMessages getMessage(String manipulatorName, Map<String, String> values, Map<String, String> validResult){
        switch (manipulatorName){
            case "Manipulator_1":
                if(!theta1IsValid(values.get("theta1(t):"), validResult)){
                    return ResultValidationMessages.INVALIDFIRSTVALUE;
                }
                if(!theta2IsValid(values.get("theta2(t):"), validResult)){
                    return ResultValidationMessages.INVALIDSECONDVALUE;
                }
                if(!lambda3IsValid(values.get("lambda3:"), validResult)){
                    return ResultValidationMessages.INVALIDTHIRDVALUE;
                }
                return ResultValidationMessages.OK;
            case "Manipulator_2":
                if(!theta1IsValid(values.get("theta1(t):"), validResult)){
                    return ResultValidationMessages.INVALIDFIRSTVALUE;
                }
                if(!lambda2IsValid(values.get("lambda2(t):"), validResult)){
                    return ResultValidationMessages.INVALIDSECONDVALUE;
                }
                if(!theta3IsValid(values.get("theta3(t):"), validResult)){
                    return ResultValidationMessages.INVALIDTHIRDVALUE;
                }
                return ResultValidationMessages.OK;
            default:
                if(!validResult.get("rx").equals(values.get("rx:"))){
                    return ResultValidationMessages.INVALIDFIRSTVALUE;
                }
                if(!validResult.get("ry").equals(values.get("ry:"))){
                    return ResultValidationMessages.INVALIDSECONDVALUE;
                }
                if(!validResult.get("rz").equals(values.get("rz:"))){
                    return ResultValidationMessages.INVALIDTHIRDVALUE;
                }
                return ResultValidationMessages.OK;
        }
    }
    private Boolean theta1IsValid(String writtenValue, Map<String, String> validResult){
        return validResult.get("theta11").equals(writtenValue) || validResult.get("theta12").equals(writtenValue);
    }

    private Boolean theta2IsValid(String writtenValue, Map<String, String> validResult){
        return validResult.get("theta21").equals(writtenValue) || validResult.get("theta22").equals(writtenValue) || validResult.get("theta23").equals(writtenValue) || validResult.get("theta24").equals(writtenValue);
    }
    private Boolean theta3IsValid(String writtenValue, Map<String, String> validResult){
        return validResult.get("theta31").equals(writtenValue);
    }
    private Boolean lambda3IsValid(String writtenValue, Map<String, String> validResult){
        return validResult.get("lambda31").equals(writtenValue) || validResult.get("lambda32").equals(writtenValue) || validResult.get("lambda33").equals(writtenValue) || validResult.get("lambda34").equals(writtenValue);
    }
    private Boolean lambda2IsValid(String writtenValue, Map<String, String> validResult){
        return validResult.get("lambda21").equals(writtenValue) || validResult.get("lambda22").equals(writtenValue);
    }


    @Override
    public Map<String,String> getDatasForManipulator(String name) {
        Map<String, String> datas = new HashMap<>();
        switch (name){
            case "Manipulator_1":
                datas.put("rx", "1");
                datas.put("ry", "1");
                datas.put("rz", "2");
                datas.put("l3", "0.1");
                datas.put("lambda2", "0.2");
                break;

            case "Manipulator_2":
                datas.put("rx", "1");
                datas.put("ry", "1");
                datas.put("rz", "2");
                datas.put("lambda3", "0.5");
                datas.put("l3", "1");
                break;

            case "Manipulator_4":
                datas.put("theta1", "60");
                datas.put("theta2", "30");
                datas.put("lambda3", "2");
                datas.put("l3", "0.1");
                datas.put("lambda2", "0.2");
                break;

            case "Manipulator_5":
                datas.put("theta1", "30");
                datas.put("theta3", "60");
                datas.put("lambda2", "2");
                datas.put("lambda3", "0.5");
                datas.put("l3", "1");
                break;
        }
        return datas;
    }


    private Map<String, String> getValidResultForManipulator(String manipulatorName){
        Map<String, String> validResult = new HashMap<>();

        switch (manipulatorName){
            case "Manipulator_1":
                validResult.putAll(getResultForFirstManipulator());
                break;

            case "Manipulator_2":
                validResult.putAll(getResultForSecondManipulator());
                break;

            case "Manipulator_4":
                validResult.putAll(getResultForFourthmanipulator());
                break;

            case "Manipulator_5":
                validResult.putAll(getResultForFiveManipulator());
                break;

        }
        return validResult;
    }

    @SuppressLint("LongLogTag")
    public Map<String, String> getResultForFirstManipulator(){
        Map<String, String> datas = getDatasForManipulator("Manipulator_1");
        Map<String, String> values = new HashMap<>();
        try {
            Double rx = Double.valueOf(datas.get("rx"));
            Double ry = Double.valueOf(datas.get("ry"));
            Double rz = Double.valueOf(datas.get("rz"));
            Double l3 = Double.valueOf(datas.get("l3"));
            Double lambda2 = Double.valueOf(datas.get("lambda2"));
            Double theta11 = 2 * Math.atan((-2*rx - 2 * Math.sqrt(Math.pow(rx, 2)+Math.pow(ry, 2)+Math.pow(lambda2, 2)))/(2*(ry-lambda2)));
            Double theta12 = 2 * Math.atan((-2*rx + 2 * Math.sqrt(Math.pow(rx, 2)+Math.pow(ry, 2)+Math.pow(lambda2, 2)))/(2*(ry-lambda2)));
            Double A = rx * Math.cos(theta11) + ry*Math.sin(theta11) - l3;
            Double A1 = rx * Math.cos(theta12) + ry*Math.sin(theta12) - l3;;
            Double theta21 = 2 * Math.atan((-2*rz - 2 * Math.sqrt(Math.pow(rz, 2) - Math.pow(A, 2)))/(-2*(rx * Math.cos(theta11) + ry*Math.sin(theta11) + l3)));
            Double theta22 = 2 * Math.atan((-2*rz + 2 * Math.sqrt(Math.pow(rz, 2) - Math.pow(A, 2)))/(-2*(rx * Math.cos(theta11) + ry*Math.sin(theta11) + l3)));
            Double theta23 = 2 * Math.atan((-2*rz - 2 * Math.sqrt(Math.pow(rz, 2) - Math.pow(A1, 2)))/(-2*(rx * Math.cos(theta11) + ry*Math.sin(theta11) + l3)));
            Double theta24 = 2 * Math.atan((-2*rz + 2 * Math.sqrt(Math.pow(rz, 2) - Math.pow(A1, 2)))/(-2*(rx * Math.cos(theta11) + ry*Math.sin(theta11) + l3)));
            Double lambda31 = (- rz + l3*Math.sin(theta21))/Math.cos(theta21);
            Double lambda32 = (- rz + l3*Math.sin(theta22))/Math.cos(theta22);
            Double lambda33 = (- rz + l3*Math.sin(theta23))/Math.cos(theta23);
            Double lambda34 = (- rz + l3*Math.sin(theta24))/Math.cos(theta24);
            values.put("theta11", String.valueOf(round(Math.toDegrees(theta11),2)));
            values.put("theta12", String.valueOf(round(Math.toDegrees(theta12), 2)));
            values.put("theta21", String.valueOf(round(Math.toDegrees(theta21),2)));
            values.put("theta22", String.valueOf(round(Math.toDegrees(theta22),2)));
            values.put("theta23", String.valueOf(round(Math.toDegrees(theta23), 2)));
            values.put("theta24", String.valueOf(round(Math.toDegrees(theta24), 2)));
            values.put("lambda31", String.valueOf(lambda31>0? round(lambda31, 2) : "wartość minusowa"));
            values.put("lambda32", String.valueOf(lambda32>0? round(lambda32, 2) : "wartość minusowa"));
            values.put("lambda33", String.valueOf(lambda33>0? round(lambda33, 2) : "wartość minusowa"));
            values.put("lambda34", String.valueOf(lambda34>0? round(lambda34, 2) : "wartość minusowa"));
        }catch (Exception e){
            Log.d(TAG, "Parse exception");
        }

        return values;
    }


    public Map<String, String> getResultForSecondManipulator(){
        Map<String, String> datas = getDatasForManipulator("Manipulator_2");
        Map<String, String> values = new HashMap<>();
        Double rx = Double.valueOf(datas.get("rx"));
        Double ry = Double.valueOf(datas.get("ry"));
        Double rz = Double.valueOf(datas.get("rz"));
        Double l3 = Double.valueOf(datas.get("l3"));
        Double lambda3 = Double.valueOf(datas.get("lambda3"));
        Double theta11 = 2 * Math.atan((-2*ry - 2 * Math.sqrt(Math.pow(rx, 2)+Math.pow(ry, 2)-Math.pow(lambda3, 2)))/(2*(rx+lambda3)));
        Double theta12 = 2 * Math.atan((-2*ry + 2 * Math.sqrt(Math.pow(rx, 2)+Math.pow(ry, 2)-Math.pow(lambda3, 2)))/(2*(rx+lambda3)));
        Double theta3 = Math.acos(rz-l3);
        Double lambda21 = ((rx-Math.cos(theta11)*lambda3)/Math.sin(theta11))-l3*Math.sin(theta3);
        Double lambda22 = ((rx-Math.cos(theta12)*lambda3)/Math.sin(theta12))-l3*Math.sin(theta3);
        values.put("theta11", String.valueOf(round(Math.toDegrees(theta11),2)));
        values.put("theta12", String.valueOf(round(Math.toDegrees(theta12), 2)));
        values.put("theta31", String.valueOf(round(Math.toDegrees(theta3),2)));
        values.put("lambda21", String.valueOf(lambda21>0? round(lambda22, 2) : "wartość minusowa"));
        values.put("lambda22", String.valueOf(lambda22>0? round(lambda22, 2) : "wartość minusowa"));


        return values;
    }

    public Map<String, String> getResultForFourthmanipulator(){
        Map<String, String> datas = getDatasForManipulator("Manipulator_4");
        Map<String, String> values = new HashMap<>();
        Double theta1 = Math.toRadians(Double.valueOf(datas.get("theta1")));
        Double theta2 = Math.toRadians(Double.valueOf(datas.get("theta1")));
        Double lambda3 = Double.valueOf(datas.get("lambda3"));
        Double l3 = Double.valueOf(datas.get("l3"));
        Double lambda2 = Double.valueOf(datas.get("lambda2"));

        Double rx = Math.cos(theta1)*(l3*Math.cos(theta2)+lambda3*Math.sin(theta2))+Math.sin(theta1)*lambda2;
        Double ry = Math.sin(theta1)*(l3*Math.cos(theta2)+lambda3*Math.sin(theta2))-Math.cos(theta1)*lambda2;
        Double rz = l3*Math.sin(theta2)-lambda3*Math.cos(theta2);
        values.put("rx", String.valueOf(round(rx, 2)));
        values.put("ry", String.valueOf(round(ry, 2)));
        values.put("rz", String.valueOf(round(rz, 2)));
        return values;
    }

    public Map<String, String> getResultForFiveManipulator(){
        Map<String, String> datas = getDatasForManipulator("Manipulator_5");
        Map<String, String> values = new HashMap<>();
        Double theta1 = Math.toRadians(Double.valueOf(datas.get("theta1")));
        Double theta3 = Math.toRadians(Double.valueOf(datas.get("theta3")));
        Double lambda2 = Double.valueOf(datas.get("lambda2"));
        Double lambda3 = Double.valueOf(datas.get("lambda3"));
        Double l3 = Double.valueOf(datas.get("l3"));

        Double rx = Math.cos(theta1)*lambda3 + Math.sin(theta1)*(l3*Math.sin(theta3)+lambda2);
        Double ry = Math.sin(theta1)*lambda3 - Math.cos(theta1)*(l3*Math.sin(theta3)+lambda2);
        Double rz = l3*Math.cos(theta3);
        values.put("rx", String.valueOf(round(rx, 2)));
        values.put("ry", String.valueOf(round(ry, 2)));
        values.put("rz", String.valueOf(round(rz, 2)));
        return values;
    }

    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }



}
