package com.example.dyju.thesisapplication;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Created by dyju on 2017-04-30.
 */
public class MultipleTransitionMatrixTest {

    @Test
    public void testMultiplesMatrix() throws Exception {

    }

    @Test
    public void testGetSimplifiedMatrix() throws Exception {

    }

    @Test
    public void testMultiplyStringsAndDoubles() throws Exception {
        ArrayList<Double> doubles = new ArrayList<>();
        ArrayList<String> strings = new ArrayList<>();
        doubles.add(12d);
        doubles.add(14d);
        strings.add("cos(theta1(t))");
        strings.add("sin(theta2(t))");

        ArrayList<Double> doubles1 = new ArrayList<>();
        ArrayList<String> strings1 = new ArrayList<>();
        doubles1.add(12d);
        doubles1.add(14d);

        MultipleTransitionMatrix multipleTransitionMatrix = new MultipleTransitionMatrix();
        String result = multipleTransitionMatrix.multiplyStringsAndDoubles(strings,doubles);
        String result1 = multipleTransitionMatrix.multiplyStringsAndDoubles(strings1,doubles1);
        assertEquals("168.0*cos(theta1(t))*sin(theta2(t))", result);
        assertEquals("168.0", result1);

    }

    @Test
    public void testGetSimplyfiedData() throws Exception {
        String data = "12";
        String data1 = "12*5";
        String data2 = "0.0*cos(theta1(t))";
        String data3 = "cos(theta1(t))*sin(alpha2(t))";
        String data4 = "12*2*cos(theta1(t))";
        String data5 = "0*0";
        MultipleTransitionMatrix multipleTransitionMatrix = new MultipleTransitionMatrix();

        String r1 = multipleTransitionMatrix.getSimplyfiedData(data);
        String r2 = multipleTransitionMatrix.getSimplyfiedData(data1);
        String r3 = multipleTransitionMatrix.getSimplyfiedData(data2);
        String r4 = multipleTransitionMatrix.getSimplyfiedData(data3);
        String r5 = multipleTransitionMatrix.getSimplyfiedData(data4);
        String r6= multipleTransitionMatrix.getSimplyfiedData(data5);

        assertEquals("12.0", r1);
        assertEquals("60.0", r2);
        assertEquals("0.0", r3);
        assertEquals("cos(theta1(t))*sin(alpha2(t))", r4);
        assertEquals("24.0*cos(theta1(t))", r5);
        assertEquals("0.0", r6);

    }
}