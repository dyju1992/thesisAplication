package com.example.dyju.thesisapplication;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.Assert.*;

/**
 * Created by dyju on 2017-05-10.
 */
public class CalculationServiceTest {

    private ICalculationService calculationService;

    public CalculationServiceTest() {
        this.calculationService = new CalculationService();
    }

    @Test
    public void testCalculateVariables() throws Exception {
        String theta = "theta1(t),theta2(t),0";
        String alpha = "90,90,90";
        String d = "0,0,d3";
        String a = "0,a2,a3";
        DhDatas dhDatas = new DhDatas(theta,alpha,d,a,"test");
        HashMap<String, String> value = calculationService.calculateVariables(dhDatas);
        assertEquals("0.0", value.get("theta(1"));


    }

    @Test
    public void getr30ForDhDatasTest(){
        DhDatas dhDatas = new DhDatas();
        dhDatas.setAlpha("90,90,90");
        dhDatas.setTheta("theta1(t),theta2(t),0");
        dhDatas.setA("0,0,l3");
        dhDatas.setD("0,lambda2,lambda3(t)");
        dhDatas.setManipulatorName("test");
        String[] datas = calculationService.getr30ForDhDatas(dhDatas);
//        Assert.assertEquals("cos(theta1(t))*(l3*cos(theta2(t)+lambda3(t)*sin(theta2(t)))+sin(theta1(t))*lambda2", datas[0]);
        Assert.assertEquals("sin(theta1(t))*(l3*cos(theta2(t)+lambda3(t)*sin(theta2(t)))-cos(theta1(t))*lambda2", datas[1]);
        Assert.assertEquals("l3*sin(theta2(t))-lambda3(t)*cos(theta2(t))", datas[2]);
    }

    @Test
    public void isValidRowInVectorTest(){
        String val11 = "l3*sin(theta2(t))-lambda3(t)*cos(theta2(t))";
        String val12 = "1.0*(sin(theta2(t))*l3+-cos(theta2(t))*lambda3(t))";
        String val21 = "cos(theta1(t))*(l3*cos(theta2(t))+lambda3(t)*sin(theta2(t)))+sin(theta1(t))*lambda2";
        String val22 = "cos(theta1(t))*(cos(theta2(t))*l3+sin(theta2(t))*lambda3(t))+sin(theta1(t))*lambda2";
        String val31 = "sin(theta1(t))*(l3*cos(theta2(t))+lambda3(t)*sin(theta2(t)))-cos(theta1(t))*lambda2";
        String val32 = "sin(theta1(t))*(cos(theta2(t))*l3+sin(theta2(t))*lambda3(t))+-cos(theta1(t))*lambda2";
        Assert.assertEquals(true, calculationService.isValidRowInVector(val12, val11));
        Assert.assertEquals(true, calculationService.isValidRowInVector(val22, val21));
        Assert.assertEquals(true, calculationService.isValidRowInVector(val32, val31));
    }

    @Test
    public void getSplitedValuesTest(){
        String[] values = {"", "cos(theta1(t))", "cos(theta2(t)))", "(sin(theta1(t))"};
        ArrayList<String> valuesToCheck = calculationService.getSplitedValues(values);
        Assert.assertEquals("cos(theta1(t))", valuesToCheck.get(0));
        Assert.assertEquals("cos(theta2(t))", valuesToCheck.get(1));
        Assert.assertEquals("sin(theta1(t))", valuesToCheck.get(2));
    }

    @Test
    public void testMultipleMatrixAndVector() throws Exception {
        String[][] matrix = new String[4][4];
        String[] vector = new String[4];
        for (int i=0; i<4; i++){
            for(int j = 0; j<4; j++){
                matrix[i][j] = String.valueOf(j);
            }
        }

        for (int i = 0; i<4; i++){
            vector[i] = String.valueOf(i);
        }

        String[] newVector = calculationService.multipleMatrixAndVector(matrix, vector);
        Assert.assertEquals("14.0", newVector[1]);

    }

    @Test
    public void getBracketfromStringTest(){
        String word = "sin(theta1(t))*(cos(theta1(t))+l1)*12+(sin(theta2(t))";
        String bracket = calculationService.getBracketFromString(word);
        Assert.assertEquals("*(cos(theta1(t))+l1)", bracket);
    }

    @Test
    public void getExpressionToSimplyfyTest(){
        String word = "sin(theta1(t))*(cos(theta1(t))+l1)*12+(sin(theta2(t))";
        String bracketToSimplify = calculationService.getExpressionToSimplyfy(word);
        Assert.assertEquals("*(cos(theta1(t))+l1)*12", bracketToSimplify);
    }

    @Test
    public void test2MultipleMatrixAndVector() throws Exception {
        String[][] matrix = new String[4][4];
        String[] vector = new String[4];
        for (int i=0; i<4; i++){
            for(int j = 0; j<4; j++){
                matrix[i][j] = "l" + String.valueOf(j);
            }
        }

        for (int i = 0; i<4; i++){
            vector[i] = String.valueOf(i);
        }

        String[] newVector = calculationService.multipleMatrixAndVector(matrix, vector);
        Assert.assertEquals("l1*1+l2*2+l3*3", newVector[1]);

    }

    @Test
    public void  getMultipleValueTest(){
        String val11 = "0";
        String val12 = "12";
        String val21 = "14";
        String val22 = "12";
        String val31 = "l1";
        String val32 = "12";
        String val42 = "cos(theta2(t))*d3+sin(theta2(t))*a3";
        String val41 = "cos(theta1(t))";
        String res1 = calculationService.getMultipleValue(val11, val12);
        String res2 = calculationService.getMultipleValue(val21, val22);
        String res3 = calculationService.getMultipleValue(val31, val32);
        String res4 = calculationService.getMultipleValue(val41, val42);
        Assert.assertEquals("0.0", res1);
        Assert.assertEquals("168.0", res2);
        Assert.assertEquals("12.0*l1", res3);
        Assert.assertEquals("cos(theta1(t))*(cos(theta2(t))*d3+sin(theta2(t))*a3)", res4);
    }

    @Test
    public void getmultSimplifiedDataTest(){
        String val1 = "1*12";
        String val2 = "1*15*12";
        String val3 = "l2*l4";
        String val4 = "0*12";
        String val5 = "1*d";
        String val6 = "5*d";

        Assert.assertEquals("12.0", calculationService.getmultSimplifiedData(val1));
        Assert.assertEquals("180.0", calculationService.getmultSimplifiedData(val2));
        Assert.assertEquals("l2*l4", calculationService.getmultSimplifiedData(val3));
        Assert.assertEquals("0.0", calculationService.getmultSimplifiedData(val4));
        Assert.assertEquals("d", calculationService.getmultSimplifiedData(val5));
        Assert.assertEquals("5.0*d", calculationService.getmultSimplifiedData(val6));
    }

    @Test
    public void getAddSimplyfiedDataTest(){
        String val1 = "1+12";
        String val2 = "1+15+12";
        String val3 = "l2+l4";
        String val4 = "0+12";
        String val5 = "0+l7";
        String val6 = "0.0+0.0+d3";

        Assert.assertEquals("13.0", calculationService.getAddSimplyfiedData(val1));
        Assert.assertEquals("28.0", calculationService.getAddSimplyfiedData(val2));
        Assert.assertEquals("l2+l4", calculationService.getAddSimplyfiedData(val3));
        Assert.assertEquals("12.0", calculationService.getAddSimplyfiedData(val4));
        Assert.assertEquals("l7", calculationService.getAddSimplyfiedData(val5));
        Assert.assertEquals("d3", calculationService.getAddSimplyfiedData(val6));

    }

    @Test
    public void testSimplyfyMatrix() throws Exception {

    }

    @Test
    public void testGetSimplfiedAdddedAndMultiplyData() throws Exception {

    }

    @Test
    public void testAddedDatas() throws Exception {

    }

    @Test
    public void testIsNullValue() throws Exception {

    }

    @Test
    public void testGetSimplyfiedData() throws Exception {

    }

    @Test
    public void testMultiplyStringsAndDoubles() throws Exception {

    }
}