package com.example.dyju.thesisapplication;

/**
 * Created by dyju on 2017-04-30.
 */
public class MultipleTransitionMatrixDto {

    String[][] firstMatrix;

    String[][] secondMatrix;

    public MultipleTransitionMatrixDto(String[][] firstMatrix, String[][] secondMatrix) {
        this.firstMatrix = firstMatrix;
        this.secondMatrix = secondMatrix;
    }

    public String[][] getFirstMatrix() {
        return firstMatrix;
    }

    public void setFirstMatrix(String[][] firstMatrix) {
        this.firstMatrix = firstMatrix;
    }

    public String[][] getSecondMatrix() {
        return secondMatrix;
    }

    public void setSecondMatrix(String[][] secondMatrix) {
        this.secondMatrix = secondMatrix;
    }
}
