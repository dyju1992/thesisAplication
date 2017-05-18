package com.example.dyju.thesisapplication;

import java.io.Serializable;


public class PositionVectorDto implements Serializable{

    String rx;

    String ry;

    String rz;

    public PositionVectorDto(String rx, String ry, String rz) {
        this.rx = rx;
        this.ry = ry;
        this.rz = rz;
    }

    public String getRx() {
        return rx;
    }

    public void setRx(String rx) {
        this.rx = rx;
    }

    public String getRz() {
        return rz;
    }

    public void setRz(String rz) {
        this.rz = rz;
    }

    public String getRy() {
        return ry;
    }

    public void setRy(String ry) {
        this.ry = ry;
    }
}
