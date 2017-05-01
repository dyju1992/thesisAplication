package com.example.dyju.thesisapplication;

import java.util.ArrayList;

/**
 * Created by dyju on 2017-04-26.
 */
public class TransitionMatrixHelper {

    public static final String sep = ",";

    public TransitionMatrix getTransitionMatrix(DHForUserDto dhForUserDto){
        TransitionMatrix transitionMatrix = new TransitionMatrix();
        return transitionMatrix;
    }

    private String[] getArrayFromString(String param){
        return param.split(sep);
    }
}
