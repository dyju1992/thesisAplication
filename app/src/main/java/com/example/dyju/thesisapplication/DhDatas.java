package com.example.dyju.thesisapplication;

/**
 * Created by dyju on 2017-04-09.
 */
public class DhDatas {

    private String theta;

    private String alpha;

    private String d;

    private String a;

    private String manipulatorName;

    public String getManipulatorName() {
        return manipulatorName;
    }

    public void setManipulatorName(String manipulatorName) {
        this.manipulatorName = manipulatorName;
    }

    public DhDatas(String theta, String alpha, String d, String a, String manipulatorName) {
        this.theta = theta;
        this.alpha = alpha;
        this.d = d;
        this.a = a;
        this.manipulatorName = manipulatorName;
    }

    public DhDatas() {
    }

    public String getTheta() {
        return theta;
    }

    public void setTheta(String theta) {
        this.theta = theta;
    }

    public String getAlpha() {
        return alpha;
    }

    public void setAlpha(String alpha) {
        this.alpha = alpha;
    }

    public String getD() {
        return d;
    }

    public void setD(String d) {
        this.d = d;
    }

    public String getA() {
        return a;
    }

    public void setA(String a) {
        this.a = a;
    }
}
