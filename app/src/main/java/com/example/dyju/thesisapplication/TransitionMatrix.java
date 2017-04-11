package com.example.dyju.thesisapplication;

import java.util.ArrayList;

/**
 * Created by dyju on 2017-04-04.
 */
public class TransitionMatrix {

    public TransitionMatrix() {
    }

    ArrayList<Object> theta;
    ArrayList<Object> alpha;
    ArrayList<Object> d;
    ArrayList<Object> a;

    public ArrayList<Object> getTheta() {
        return theta;
    }

    public void setTheta(ArrayList<Object> theta) {
        this.theta = theta;
    }

    public ArrayList<Object> getAlpha() {
        return alpha;
    }

    public void setAlpha(ArrayList<Object> alpha) {
        this.alpha = alpha;
    }

    public ArrayList<Object> getD() {
        return d;
    }

    public void setD(ArrayList<Object> d) {
        this.d = d;
    }

    public ArrayList<Object> getA() {
        return a;
    }

    public void setA(ArrayList<Object> a) {
        this.a = a;
    }

    public Object[][] getTransitionMatrixForData(int i){
        Object[][] transitionMatrix = new String[4][4];
        transitionMatrix[0][0] = theta.get(i) instanceof Double ? Math.cos((Double) theta.get(i)) : "cos("+ theta.get(i) +")";
        transitionMatrix[0][1] = theta.get(i) instanceof Double ? -Math.sin((Double) theta.get(i)) : "-sin("+theta.get(i)+")";
        transitionMatrix[0][2] = 0;
        transitionMatrix[0][3] = alpha.get(0) instanceof Double ? -Math.sin((Double) alpha.get(i-1)) : "-sin("+alpha.get(i-1)+")";

        transitionMatrix[1][0] = "sin("+theta.get(i)+")*cos("+alpha.get(i-1)+")";
        transitionMatrix[1][1] = "cos("+theta.get(i)+")*cos("+alpha.get(i-1)+")";
        transitionMatrix[1][2] = "-sin("+alpha.get(i-1)+")";
        transitionMatrix[1][3] = "-sin("+alpha.get(i-1)+")*"+d.get(i);

        transitionMatrix[2][0] = "sin("+theta.get(i)+")*sin("+alpha.get(i-1)+")";
        transitionMatrix[2][1] = "cos("+theta.get(i)+")*sin("+alpha.get(i-1)+")";
        transitionMatrix[2][2] = "cos("+alpha.get(i-1)+")";
        transitionMatrix[2][3] = "cos("+alpha.get(i-1)+")*"+d.get(i);

        transitionMatrix[3][0] = "0";
        transitionMatrix[3][1] = "0";
        transitionMatrix[3][2] = "0";
        transitionMatrix[3][3] = "1";

        return transitionMatrix;



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


}
