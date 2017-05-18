package com.example.dyju.thesisapplication;

import java.util.ArrayList;

/**
 * Created by dyju on 2017-04-04.[
 */
public class TransitionMatrix {

    String[] theta;
    String[] alpha;
    String[] d;
    String[] a;

    public TransitionMatrix() {
    }

    public TransitionMatrix(String[] theta, String[] alpha, String[] d, String[] a) {
        this.theta = theta;
        this.alpha = alpha;
        this.d = d;
        this.a = a;
    }

    public String[] getTheta() {
        return theta;
    }

    public void setTheta(String[] theta) {
        this.theta = theta;
    }

    public String[] getAlpha() {
        return alpha;
    }

    public void setAlpha(String[] alpha) {
        this.alpha = alpha;
    }

    public String[] getD() {
        return d;
    }

    public void setD(String[] d) {
        this.d = d;
    }

    public String[] getA() {
        return a;
    }

    public void setA(String[] a) {
        this.a = a;
    }

    public String[][] getAMatrixForData(int i){
        String[][] transitionMatrix = new String[4][4];

        transitionMatrix[0][0] = String.valueOf(getCosinusFromValue(theta[i]));
        transitionMatrix[0][1] = getVariableMinusSinCos(theta[i], alpha[i]);
        transitionMatrix[0][2] = getSinThetaSinAlpha(theta[i], alpha[i]);
        transitionMatrix[0][3] = getCosValMultDimension(theta[i], a[i]);

        transitionMatrix[1][0] = String.valueOf(getSinusFromValue(theta[i]));
        transitionMatrix[1][1] = getCosThetaCosAlpha(theta[i], alpha[i]);
        transitionMatrix[1][2] = getVariableMinusCosThetaSinAlpha(theta[i], alpha[i]);
        transitionMatrix[1][3] = getSinValMultDimension(theta[i], a[i]);

        transitionMatrix[2][0] = String.valueOf(0);
        transitionMatrix[2][1] = String.valueOf(getSinusFromValue(alpha[i]));
        transitionMatrix[2][2] = String.valueOf(getCosinusFromValue(alpha[i]));
        transitionMatrix[2][3] = d[i];

        transitionMatrix[3][0] = String.valueOf(0);
        transitionMatrix[3][1] = String.valueOf(0);
        transitionMatrix[3][2] = String.valueOf(0);
        transitionMatrix[3][3] = String.valueOf(1);
        return transitionMatrix;
    }

    public String[][] getTransitionMatrixForData1(int i){
        String[][] transitionMatrix = new String[4][4];

//        transitionMatrix[0][0] = theta.get(i) instanceof Double ? Math.cos((Double) theta.get(i)) : "cos("+ theta.get(i) +")";
        transitionMatrix[0][0] = String.valueOf(getCosinusFromValue(theta[i]));
//        transitionMatrix[0][1] = theta.get(i) instanceof Double ? -Math.sin((Double) theta.get(i)) : "-sin("+theta.get(i)+")";
        transitionMatrix[0][1] = String.valueOf(getMinusSinusFromValue(theta[i]));
//        transitionMatrix[0][2] = 0;
        transitionMatrix[0][2] = String.valueOf(0);
//        transitionMatrix[0][3] = alpha.get(0) instanceof Double ? -Math.sin((Double) alpha.get(i-1)) : "-sin("+alpha.get(i-1)+")";
        transitionMatrix[0][3] = String.valueOf(a[i-1]);
//
//        transitionMatrix[1][0] = "sin("+theta.get(i)+")*cos("+alpha.get(i-1)+")";
        transitionMatrix[1][0] = getVariablesToMatrix10(theta[i], alpha[i-1]);
//        transitionMatrix[1][1] = "cos("+theta.get(i)+")*cos("+alpha.get(i-1)+")";
        transitionMatrix[1][1] = getCosThetaCosAlpha(theta[i], alpha[i-1]);
//        transitionMatrix[1][2] = "-sin("+alpha.get(i-1)+")";
        transitionMatrix[1][2] = String.valueOf(getMinusSinusFromValue(alpha[i-1]));
//        transitionMatrix[1][3] = "-sin("+alpha.get(i-1)+")*"+d.get(i);
        transitionMatrix[1][3] = getVariablesToMatrix13(alpha[i-1], d[i]);
//
//        transitionMatrix[2][0] = "sin("+theta.get(i)+")*sin("+alpha.get(i-1)+")";
        transitionMatrix[2][0] = getSinThetaSinAlpha(theta[i], alpha[i-1]);
//        transitionMatrix[2][1] = "cos("+theta.get(i)+")*sin("+alpha.get(i-1)+")";
        transitionMatrix[2][1] = getVariablesToMatrix21(theta[i], alpha[i-1]);
//        transitionMatrix[2][2] = "cos("+alpha.get(i-1)+")";
        transitionMatrix[2][2] = String.valueOf(getCosinusFromValue(alpha[i-1]));
//        transitionMatrix[2][3] = "cos("+alpha.get(i-1)+")*"+d.get(i);
        transitionMatrix[2][3] = getCosValMultDimension(alpha[i-1], d[i]);
//
        transitionMatrix[3][0] = String.valueOf(0);
        transitionMatrix[3][1] = String.valueOf(0);
        transitionMatrix[3][2] = String.valueOf(0);
        transitionMatrix[3][3] = String.valueOf(1);
//
        return transitionMatrix;
//
//
//
    }

//    protected String[][] getTransitionMatrixFor0Index(){
//        String[][] transitionMatrix = new String[4][4];
//
////        transitionMatrix[0][0] = theta.get(i) instanceof Double ? Math.cos((Double) theta.get(i)) : "cos("+ theta.get(i) +")";
//        transitionMatrix[0][0] = String.valueOf(getCosinusFromValue(theta[0]));
////        transitionMatrix[0][1] = theta.get(i) instanceof Double ? -Math.sin((Double) theta.get(i)) : "-sin("+theta.get(i)+")";
//        transitionMatrix[0][1] = String.valueOf(getMinusSinusFromValue(theta[0]));
////        transitionMatrix[0][2] = 0;
//        transitionMatrix[0][2] = String.valueOf(0);
////        transitionMatrix[0][3] = alpha.get(0) instanceof Double ? -Math.sin((Double) alpha.get(i-1)) : "-sin("+alpha.get(i-1)+")";
//        transitionMatrix[0][3] = String.valueOf(0);
////
////        transitionMatrix[1][0] = "sin("+theta.get(i)+")*cos("+alpha.get(i-1)+")";
//        transitionMatrix[1][0] = getVariablesToMatrix10(theta[0], "0");
////        transitionMatrix[1][1] = "cos("+theta.get(i)+")*cos("+alpha.get(i-1)+")";
//        transitionMatrix[1][1] = getCosThetaCosAlpha(theta[0], "0");
////        transitionMatrix[1][2] = "-sin("+alpha.get(i-1)+")";
//        transitionMatrix[1][2] = String.valueOf(getMinusSinusFromValue("0"));
////        transitionMatrix[1][3] = "-sin("+alpha.get(i-1)+")*"+d.get(i);
//        transitionMatrix[1][3] = getVariablesToMatrix13("0", d[0]);
////
////        transitionMatrix[2][0] = "sin("+theta.get(i)+")*sin("+alpha.get(i-1)+")";
//        transitionMatrix[2][0] = getSinThetaSinAlpha(theta[0], "0");
////        transitionMatrix[2][1] = "cos("+theta.get(i)+")*sin("+alpha.get(i-1)+")";
//        transitionMatrix[2][1] = getVariablesToMatrix21(theta[0], "0");
////        transitionMatrix[2][2] = "cos("+alpha.get(i-1)+")";
//        transitionMatrix[2][2] = String.valueOf(getCosinusFromValue("0"));
////        transitionMatrix[2][3] = "cos("+alpha.get(i-1)+")*"+d.get(i);
//        transitionMatrix[2][3] = getCosValMultDimension("0", d[0]);
////
//        transitionMatrix[3][0] = String.valueOf(0);
//        transitionMatrix[3][1] = String.valueOf(0);
//        transitionMatrix[3][2] = String.valueOf(0);
//        transitionMatrix[3][3] = String.valueOf(1);
//
//        return transitionMatrix;
//    }

    public String getAngleInRadius(String angle){
        try{
            Double angleInRad = Math.toRadians(Double.valueOf(String.valueOf(angle)));
            return String.valueOf(angleInRad);
        }catch (Exception e){
            return angle;
        }
    }

    public ArrayList<Object> getAngleListInRadius(ArrayList<String> angleList){
        ArrayList<Object> anglesInRad = new ArrayList<>();
        for (String angle : angleList){
            try{
                Double angleInRad = Math.toRadians(Double.valueOf(angle));
                anglesInRad.add(angleInRad);


            }catch (Exception e){
                System.out.println("Variable angle: "+angle);
                anglesInRad.add(angle);
            }
        }
        return anglesInRad;
    }


    public String getVariableMinusSinCos(String theta, String alpha){
        Object minSinTheta = getMinusSinusFromValue(theta);
        Object cosAlpha = getCosinusFromValue(alpha);
        String value = getMultipleValues(minSinTheta, cosAlpha);
        return checkingValue(value);
    }

    protected String getVariableMinusCosThetaSinAlpha(String theta, String alpha){
        Object minCosTheta = getMinusCosinusFromValue(theta);
        Object sinAlpha = getSinusFromValue(alpha);
        String value = getMultipleValues(minCosTheta, sinAlpha);
        return checkingValue(value);
    }

    public String getVariablesToMatrix10(String theta, String alpha){
        Object sinTheta = getSinusFromValue(theta);
        Object cosAlpha = getCosinusFromValue(alpha);
        String value = getMultipleValues(sinTheta, cosAlpha);
        return checkingValue(value);
    }

    public String getCosThetaCosAlpha(String theta, String alpha){
        Object cosTheta = getCosinusFromValue(theta);
        Object cosAlpha = getCosinusFromValue(alpha);
        String value = getMultipleValues(cosTheta, cosAlpha);
        return checkingValue(value);
    }

    public String getVariablesToMatrix12(String alpha){
        Object sinAlpha = getSinusFromValue(alpha);
//        if(String.valueOf(sinAlpha).contains("0.0")){
//            return "0";
//        }
        String result = String.valueOf("-"+sinAlpha);
        return checkingValue(result);
    }

    public String getVariablesToMatrix13(String alpha, String d){
        Object minSinAlpha = getMinusSinusFromValue(alpha);
        Object dimension = String.valueOf(getDimension(d)).equals("0")? "0.0" : getDimension(d);
        String result = getMultipleValues(minSinAlpha, dimension);
        return checkingValue(result);
    }

    protected String getSinThetaSinAlpha(String theta, String alpha){
        Object sinTheta = getSinusFromValue(theta);
        Object sinAlpha = getSinusFromValue(alpha);
        String value = getMultipleValues(sinTheta, sinAlpha);
        return checkingValue(value);
    }

    protected String getVariablesToMatrix21(String theta, String alpha){
        Object cosTheta = getCosinusFromValue(theta);
        Object sinAlpha = getSinusFromValue(alpha);
        String value = getMultipleValues(cosTheta, sinAlpha);
        return checkingValue(value);
    }

    public String getCosValMultDimension(String alpha, String d){
        Object cosAlpha = getCosinusFromValue(alpha);
        Object dimension = String.valueOf(getDimension(d)).equals("0")? "0.0" : getDimension(d);
        String result = getMultipleValues(cosAlpha, dimension);
        return checkingValue(result);
    }

    public String getSinValMultDimension(String alpha, String d){
        Object sinAlpha = getSinusFromValue(alpha);
        Object dimension = String.valueOf(getDimension(d)).equals("0")? "0.0" : getDimension(d);
        String result = getMultipleValues(sinAlpha, dimension);
        return checkingValue(result);
    }

    public Object getCosinusFromValue(String value){
        return value.contains("(t)")? "cos("+value+")" : round(Math.cos(Double.parseDouble(getAngleInRadius(value))), 2);
    }

    public Object getMinusCosinusFromValue(String value){
        return value.contains("(t)") ? "-cos("+value+")" : round(-Math.cos(Double.parseDouble(getAngleInRadius(value))), 2);
    }

    public Object getMinusSinusFromValue(String value){
        return value.contains("(t)") ? "-sin("+value+")" : round(-Math.sin(Double.parseDouble(getAngleInRadius(value))), 2);
    }

    public Object getSinusFromValue(String value){
        return value.contains("(t)") ? "sin("+value+")" : round(Math.sin(Double.parseDouble(getAngleInRadius(value))), 2);
    }

    protected Object getDimension(String dim){
        try {
            return round(Double.parseDouble(dim), 2);
        }catch (Exception e){
            return dim;
        }
    }

    private String getMultipleValues(Object theta, Object alpha){
        if(theta instanceof Double && alpha instanceof Double){
            return String.valueOf((Double) theta * (Double) alpha);
        }else {
            return String.valueOf(theta) + "*" + String.valueOf(alpha);
        }
    }

    private String checkingValue(String value){
        if(valueIsNotNull(value)){
            return value;
        }
        return "0";
    }

    private boolean valueIsNotNull(String value){
        return !value.contains("0.0");
    }

    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }




}
