package com.example.dyju.thesisapplication;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Created by dyju on 2017-04-04.
 */
public class TransitionMatrixTest extends TransitionMatrix {

    @Test
    public void testGetTransitionMatrixForData() throws Exception {


        String[] theta = new String[4];
        String[] alpha = new String[4];
        String[] d = new String[4];
        String[] a = new String[4];

        theta[0] = "180";
        theta[1] = "theta2(t)";
        theta[2] = "270";
        theta[3] = "theta4(t)";

        alpha[0] = "alpha1(t)";
        alpha[1] = "90";
        alpha[2] = "alpha3(t)";
        alpha[3] = "180";

        d[0] = "10";
        d[1] = "10";
        d[2] = "10";
        d[3] = "d";

        a[0] = "10";
        a[1] = "a";
        a[2] = "10";
        a[3] = "10";

        TransitionMatrix transitionMatrix = new TransitionMatrix(theta, alpha, d, a);
        String[][] transitionMatrix0 = transitionMatrix.getAMatrixForData(0);
        String[][] transitionMatrix01 = transitionMatrix.getAMatrixForData(1);
        String[][] transitionMatrix12 = transitionMatrix.getAMatrixForData(2);
        String[][] transitionMatrix23 = transitionMatrix.getAMatrixForData(3);

        MultipleTransitionMatrix multipleTransitionMatrix = new MultipleTransitionMatrix(transitionMatrix0);
//        String [][] multMatr1 = multipleTransitionMatrix.multiplesMatrix(transitionMatrix01);
        ArrayList<String[][]> arrayList = new ArrayList<>();
//        arrayList.add(transitionMatrix0);
        arrayList.add(transitionMatrix01);
        arrayList.add(transitionMatrix12);
        arrayList.add(transitionMatrix23);

        for(int i = 0; i<arrayList.size(); i++){
            multipleTransitionMatrix = multipleTransitionMatrix.multiplesMatrix(arrayList.get(i));
        }

        System.out.print("wewfw");


//        transitionMatrix.setA(transitionMatrix.getAngleListInRadius(a));
//        transitionMatrix.setAlpha(transitionMatrix.getAngleListInRadius(alpha));
//        transitionMatrix.setD(transitionMatrix.getAngleListInRadius(d));
//        transitionMatrix.setTheta(transitionMatrix.getAngleListInRadius(theta));

//        String abc = new String("asdf");
//        String q = new String("asdf");
//
//        System.out.println("abc==q: " + (abc==q));
//        System.out.println("abc.equals(q): " + abc.equals(q));


    }

    @Test
    public void getAngleListInRadiusTest(){
        ArrayList<String> alpha = new ArrayList<>();
        alpha.add("90");
        alpha.add("alpha2");
        alpha.add("180");
        alpha.add("alpha4");


        ArrayList<Object> alphasInRad = getAngleListInRadius(alpha);

    }

    @Test
    public void getVariablesToMatrix10Test(){
        String theta = "theta(t)";
        String alpha = "alpha(t)";
        String theta1 = "30";
        String alpha1 = "60";
        String theta2 = "0";
        String alpha2 = "alpha(t)";
        String theta3 = "30";
        String alpha3 = "alpha(t)";
        TransitionMatrix transitionMatrix = new TransitionMatrix();
        String t1 = transitionMatrix.getVariablesToMatrix10(theta, alpha);
        String t2 = transitionMatrix.getVariablesToMatrix10(theta1, alpha1);
        String t3 = transitionMatrix.getVariablesToMatrix10(theta2, alpha2);
        String t4 = transitionMatrix.getVariablesToMatrix10(theta3, alpha3);
        assertEquals("sin(theta(t))*cos(alpha(t))", t1);
        assertEquals("0.25", t2);
        assertEquals("0", t3);
        assertEquals("0.5*cos(alpha(t))", t4);

    }

    @Test
    public void getVariablesToMatrix11Test(){
        String theta = "theta(t)";
        String alpha = "alpha(t)";
        String theta1 = "60";
        String alpha1 = "0";
        String theta2 = "90";
        String alpha2 = "alpha(t)";
        String theta3 = "0";
        String alpha3 = "alpha(t)";

        TransitionMatrix transitionMatrix = new TransitionMatrix();
        String t = transitionMatrix.getCosThetaCosAlpha(theta, alpha);
        String t1 = transitionMatrix.getCosThetaCosAlpha(theta1, alpha1);
        String t2 = transitionMatrix.getCosThetaCosAlpha(theta2, alpha2);
        String t3 = transitionMatrix.getCosThetaCosAlpha(theta3, alpha3);
        assertEquals("cos(theta(t))*cos(alpha(t))", t);
        assertEquals("0.5", t1);
        assertEquals("0", t2);
        assertEquals("1.0*cos(alpha(t))", t3);
    }


    @Test
    public void getVariablesToMatrix12Test(){
        String alpha = "alpha(t)";
        String alpha1 = "30";
        String alpha2 = "0";
        TransitionMatrix transitionMatrix = new TransitionMatrix();
        String t = transitionMatrix.getVariablesToMatrix12(alpha);
        String t1 = transitionMatrix.getVariablesToMatrix12(alpha1);
        String t2 = transitionMatrix.getVariablesToMatrix12(alpha2);
        assertEquals("-sin(alpha(t))", t);
        assertEquals("-0.5", t1);
        assertEquals("0", t2);

    }

    @Test
    public void getVariablesToMatrix13Test(){
        String alpha = "0";
        String alpha1 = "alpha(t)";
        String alpha2 = "90";
        String d = "12";
        String d1 = "0";
        TransitionMatrix transitionMatrix = new TransitionMatrix();
        String t = transitionMatrix.getVariablesToMatrix13(alpha, d);
        String t1 = transitionMatrix.getVariablesToMatrix13(alpha1, d1);
        String t2 = transitionMatrix.getVariablesToMatrix13(alpha1, d);
        String t3 = transitionMatrix.getVariablesToMatrix13(alpha2, d);
        assertEquals("0", t);
        assertEquals("0", t1);
        assertEquals("-sin(alpha(t))*12.0", t2);
        assertEquals("-12.0", t3);


    }

    @Test
    public void getvariablesToMatrix20Test(){
        String theta = "90";
        String alpha = "30";
        String theta1 = "theta(t)";
        String alpha1 = "0";
        String theta2 = "theta(t)";
        String alpha2 = "alpha(t)";
        TransitionMatrix transitionMatrix = new TransitionMatrix();
        String d = transitionMatrix.getSinThetaSinAlpha(theta, alpha);
        String d2 = transitionMatrix.getSinThetaSinAlpha(theta1, alpha1);
        assertEquals("sin(theta(t))*sin(alpha(t))", transitionMatrix.getSinThetaSinAlpha(theta2, alpha2));
        assertEquals("0.5", d);
        assertEquals("0", d2);

    }

    @Test
    public void getVariablesToMatrix21Test(){
        String theta = "0";
        String alpha = "30";
        String theta1 = "theta(t)";
        String alpha1 = "0";
        String theta2 = "theta(t)";
        String alpha2 = "alpha(t)";
        TransitionMatrix transitionMatrix = new TransitionMatrix();
        String d = transitionMatrix.getVariablesToMatrix21(theta, alpha);
        String d1 = transitionMatrix.getVariablesToMatrix21(theta1, alpha1);
        String d2 = transitionMatrix.getVariablesToMatrix21(theta2, alpha2);
        assertEquals("0.5", d);
        assertEquals("0", d1);
        assertEquals("cos(theta(t))*sin(alpha(t))", d2);

    }

    @Test
    public void getCosinusFromValueTest(){
        String v1 = "60";
        String v2 = "90";
        String v3 = "theta(t)";
        TransitionMatrix transitionMatrix = new TransitionMatrix();
        Object d1 = transitionMatrix.getCosinusFromValue(v1);
        Object d2 = transitionMatrix.getCosinusFromValue(v2);
        Object d3 = transitionMatrix.getCosinusFromValue(v3);

        assertEquals("0.5", String.valueOf(d1));
        assertEquals("0.0", String.valueOf(d2));
        assertEquals("cos(theta(t))", d3);
    }

    @Test
    public void getSinusFromValueTest(){
        String v1 = "30";
        String v2 = "90";
        String v3 = "theta(t)";
        TransitionMatrix transitionMatrix = new TransitionMatrix();
        Object d1 = transitionMatrix.getSinusFromValue(v1);
        Object d2 = transitionMatrix.getSinusFromValue(v2);
        Object d3 = transitionMatrix.getSinusFromValue(v3);

        assertEquals("0.5", String.valueOf(d1));
        assertEquals("1.0", String.valueOf(d2));
        assertEquals("sin(theta(t))", d3);

    }

    @Test
    public void getMinusSinusFromValueTest(){
        String v1 = "30";
        String v2 = "90";
        String v3 = "theta(t)";
        TransitionMatrix transitionMatrix = new TransitionMatrix();
        Object d1 = transitionMatrix.getMinusSinusFromValue(v1);
        Object d2 = transitionMatrix.getMinusSinusFromValue(v2);
        Object d3 = transitionMatrix.getMinusSinusFromValue(v3);

        assertEquals("-0.5", String.valueOf(d1));
        assertEquals("-1.0", String.valueOf(d2));
        assertEquals("-sin(theta(t))", d3);

    }

    @Test
    public void getDimensionTest(){
        String d1 = "12";
        String d2 = "d(t)";
        TransitionMatrix transitionMatrix = new TransitionMatrix();
        Object r1 = transitionMatrix.getDimension(d1);
        Object r2 = transitionMatrix.getDimension(d2);

        assertEquals("12.0", String.valueOf(r1));
        assertEquals("d(t)", String.valueOf(r2));
    }


}