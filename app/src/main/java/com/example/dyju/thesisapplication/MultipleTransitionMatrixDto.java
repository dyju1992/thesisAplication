package com.example.dyju.thesisapplication;

import java.io.Serializable;

/**
 * Created by dyju on 2017-04-30.
 */
public class MultipleTransitionMatrixDto implements Serializable{

    String[][] firstMatrix;

    public MultipleTransitionMatrixDto(String[][] firstMatrix) {
        this.firstMatrix = firstMatrix;
    }

    public MultipleTransitionMatrixDto() {
    }

    public String[][] getFirstMatrix() {
        return firstMatrix;
    }

    public void setFirstMatrix(String[][] firstMatrix) {
        this.firstMatrix = firstMatrix;
    }

}
