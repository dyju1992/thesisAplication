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
                if(!isCorrectlyCalculate("theta1", validResult, values.get("theta1(t):"))){
                    return ResultValidationMessages.INVALIDFIRSTVALUE;
                }
                if(!isCorrectlyCalculate("theta2", validResult, values.get("theta2(t):"))){
                    return ResultValidationMessages.INVALIDSECONDVALUE;
                }
                if(!isCorrectlyCalculate("lambda3",validResult, values.get("lambda3:"))){
                    return ResultValidationMessages.INVALIDTHIRDVALUE;
                }
                return ResultValidationMessages.OK;
            case "Manipulator_2":
                if(!isCorrectlyCalculate("theta1", validResult, values.get("theta1(t):"))){
                    return ResultValidationMessages.INVALIDFIRSTVALUE;
                }
                if(!isCorrectlyCalculate("lambda2", validResult, values.get("lambda2(t):"))){
                    return ResultValidationMessages.INVALIDSECONDVALUE;
                }
                if(!isCorrectlyCalculate("theta3", validResult, values.get("theta3(t):"))){
                    return ResultValidationMessages.INVALIDTHIRDVALUE;
                }
                return ResultValidationMessages.OK;

            case "Manipulator_3":
                if(!isCorrectlyCalculate("lambda1", validResult, values.get("lambda1(t):"))){
                    return ResultValidationMessages.INVALIDFIRSTVALUE;
                }
                if(!isCorrectlyCalculate("theta2", validResult, values.get("theta2(t):"))){
                    return ResultValidationMessages.INVALIDSECONDVALUE;
                }
                if(!isCorrectlyCalculate("theta3", validResult, values.get("theta3(t):"))){
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

    public boolean isCorrectlyCalculate(String exp, Map<String, String> valuesFromDb, String value){
        for(String key : valuesFromDb.keySet()){
            if(key.contains(exp) && valuesFromDb.get(key).equals(value)){
                return true;
            }
        }
        return false;
    }

    @Override
    public Map<String,String> getDatasForManipulator(String name) {
        Map<String, String> datas = new HashMap<>();
        switch (name){
            case "Manipulator_1":
                datas.put("rx", "-0.7");
                datas.put("ry", "-1.6");
                datas.put("rz", "1.1");
                datas.put("l3", "0.1");
                datas.put("lambda2", "0.2");
                datas.put("lambda1", "0.3");
                break;

            case "Manipulator_2":
                datas.put("rx", "1");
                datas.put("ry", "1");
                datas.put("rz", "2");
                datas.put("lambda3", "0.5");
                datas.put("l3", "1");
                break;

            case "Manipulator_3":
                datas.put("rx", "1");
                datas.put("ry", "1");
                datas.put("rz", "2");
                datas.put("lambda2", "0.5");
                datas.put("lambda3", "1");
                datas.put("l3", "1");
                break;

            case "Manipulator_4":
                datas.put("theta1", "60");
                datas.put("theta2", "30");
                datas.put("lambda3", "2");
                datas.put("l3", "0.1");
                datas.put("lambda2", "0.2");
                datas.put("lambda1", "0.3");
                break;

            case "Manipulator_5":
                datas.put("theta1", "30");
                datas.put("theta3", "60");
                datas.put("lambda2", "2");
                datas.put("lambda3", "0.5");
                datas.put("l3", "1");
                break;

            case "Manipulator_6":
                datas.put("theta2", "30");
                datas.put("theta3", "60");
                datas.put("lambda2", "2");
                datas.put("lambda3", "0.5");
                datas.put("lambda1", "0.5");
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

            case "Manipulator_3":
                validResult.putAll(getResultForThirdmanipulator());
                break;

            case "Manipulator_4":
                validResult.putAll(getResultForFourthmanipulator());
                break;

            case "Manipulator_5":
                validResult.putAll(getResultForFiveManipulator());
                break;

            case "Manipulator_6":
                validResult.putAll(getResultForSixManipulator());
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
            Double lambda1 = Double.valueOf(datas.get("lambda1"));
            Double theta11 = 2 * Math.atan((2*rx - 2 * Math.sqrt(Math.pow(rx, 2)+Math.pow(ry, 2)-Math.pow(lambda2, 2)))/(2*(lambda2 - ry)));
            Double theta12 = 2 * Math.atan((-2*rx + 2 * Math.sqrt(Math.pow(rx, 2)+Math.pow(ry, 2)-Math.pow(lambda2, 2)))/(2*(lambda2 - ry)));
            Double A = rx * Math.cos(theta11) + ry*Math.sin(theta11);
            Double A1 = rx * Math.cos(theta12) + ry*Math.sin(theta12);
            Double theta21 = Math.acos((l3-rz)/A);
            Double theta22 = Math.acos((l3-rz)/A1);
//            Double theta23 = 2 * Math.atan((-2*rz - 2 * Math.sqrt(Math.pow(rz, 2) - Math.pow(A1, 2)))/(-2*(rx * Math.cos(theta11) + ry*Math.sin(theta11) + l3)));
//            Double theta24 = 2 * Math.atan((-2*rz + 2 * Math.sqrt(Math.pow(rz, 2) - Math.pow(A1, 2)))/(-2*(rx * Math.cos(theta11) + ry*Math.sin(theta11) + l3)));
            Double lambda31 = (rz-l3*Math.sin(theta21)-lambda1)/Math.cos(theta21);
            Double lambda32 = (rz-l3*Math.sin(theta22)-lambda1)/Math.cos(theta22);
//            Double lambda33 = (- rz + l3*Math.sin(theta23))/Math.cos(theta23);
//            Double lambda34 = (- rz + l3*Math.sin(theta24))/Math.cos(theta24);
            values.put("theta11", String.valueOf(round(Math.toDegrees(theta11),2)));
            values.put("theta12", String.valueOf(round(Math.toDegrees(theta12), 2)));
            values.put("theta21", String.valueOf(round(Math.toDegrees(theta21),2)));
            values.put("theta22", String.valueOf(round(Math.toDegrees(theta22),2)));
//            values.put("theta23", String.valueOf(round(Math.toDegrees(theta23), 2)));
//            values.put("theta24", String.valueOf(round(Math.toDegrees(theta24), 2)));
            values.put("lambda31", String.valueOf(lambda31));
            values.put("lambda32", String.valueOf(lambda32));
//            values.put("lambda33", String.valueOf(lambda33>0? round(lambda33, 2) : "wartość minusowa"));
//            values.put("lambda34", String.valueOf(lambda34>0? round(lambda34, 2) : "wartość minusowa"));
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

    public Map<String, String> getResultForThirdmanipulator(){
        Map<String, String> datas = getDatasForManipulator("Manipulator_3");
        Map<String, String> values = new HashMap<>();
        Double rx = Double.valueOf(datas.get("rx"));
        Double ry = Double.valueOf(datas.get("ry"));
        Double rz = Double.valueOf(datas.get("rz"));
        Double l3 = Double.valueOf(datas.get("l3"));
        Double lambda3 = Double.valueOf(datas.get("lambda3"));
        Double lambda2 = Double.valueOf(datas.get("lambda2"));

        Double theta3 = Double.valueOf(Math.asin((rx-lambda2)/l3));
        Double theta21 = 2 * Math.atan((2*lambda3 - Math.sqrt(4*(Math.pow(lambda3, 2) - Math.pow(ry, 2)+Math.pow(l3, 2)+Math.pow(l3, 2)*Math.pow(Math.cos(theta3), 2))))/2*(l3 * Math.cos(theta3 + ry)));
        Double theta22 = 2 * Math.atan((2*lambda3 + Math.sqrt(4*(Math.pow(lambda3, 2) - Math.pow(ry, 2)+Math.pow(l3, 2)+Math.pow(l3, 2)*Math.pow(Math.cos(theta3), 2))))/2*(l3 * Math.cos(theta3 + ry)));
        Double lambda11 = rz + Math.cos(theta21)*lambda3 - l3*Math.cos(theta3)*Math.sin(theta21);
        Double lambda12 = rz + Math.cos(theta22)*lambda3 - l3*Math.cos(theta3)*Math.sin(theta22);
        values.put("theta3", String.valueOf(round(Math.toDegrees(theta3), 2)));
        values.put("theta21", String.valueOf(round(Math.toDegrees(theta21), 2)));
        values.put("theta22", String.valueOf(round(Math.toDegrees(theta22), 2)));
        values.put("lambda11", String.valueOf(round(lambda11, 2)));
        values.put("lambda12", String.valueOf(round(lambda12, 2)));
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
        Double lambda1 = Double.valueOf(datas.get("lambda1"));

        Double rx = Math.cos(theta1)*(l3*Math.cos(theta2)-lambda3*Math.sin(theta2))+Math.sin(theta1)*lambda2;
        Double ry = Math.sin(theta1)*(l3*Math.cos(theta2)-lambda3*Math.sin(theta2))-Math.cos(theta1)*lambda2;
        Double rz = l3*Math.sin(theta2)+lambda3*Math.cos(theta2);
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

    private Map<String, String> getResultForSixManipulator(){
        Map<String, String> datas = getDatasForManipulator("Manipulator_6");
        Map<String, String> values = new HashMap<>();
        Double theta2 = Math.toRadians(Double.valueOf(datas.get("theta2")));
        Double theta3 = Math.toRadians(Double.valueOf(datas.get("theta3")));
        Double lambda1 = Double.valueOf(datas.get("lambda1"));
        Double lambda2 = Double.valueOf(datas.get("lambda2"));
        Double lambda3 = Double.valueOf(datas.get("lambda3"));
        Double l3 = Double.valueOf(datas.get("l3"));
        Double rx = l3 * Math.sin(theta3) + lambda2;
        Double ry = l3 * Math.cos(theta3) * Math.cos(theta2) + Math.sin(theta2) * lambda3;
        Double rz = l3 * Math.cos(theta3) * Math.sin(theta2) - Math.cos(theta2) * lambda3 + lambda1;
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
