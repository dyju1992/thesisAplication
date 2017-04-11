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

        TransitionMatrix transitionMatrix = new TransitionMatrix();
        ArrayList<String> theta = new ArrayList<>();
        ArrayList<String> alpha = new ArrayList<>();
        ArrayList<String> d = new ArrayList<>();
        ArrayList<String> a = new ArrayList<>();

        theta.add("180");
        theta.add("theta2");
        theta.add("270");
        theta.add("theta4");

        alpha.add("alpha1");
        alpha.add("90");
        alpha.add("alpha3");
        alpha.add("180");

        d.add("d1");
        d.add("d2");
        d.add("d3");
        d.add("d4");

        a.add("a1");
        a.add("a2");
        a.add("a3");
        a.add("a4");
        transitionMatrix.setA(transitionMatrix.getAngleListInRadius(a));
        transitionMatrix.setAlpha(transitionMatrix.getAngleListInRadius(alpha));
        transitionMatrix.setD(transitionMatrix.getAngleListInRadius(d));
        transitionMatrix.setTheta(transitionMatrix.getAngleListInRadius(theta));

        Object[][] trans = transitionMatrix.getTransitionMatrixForData(2);



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
}