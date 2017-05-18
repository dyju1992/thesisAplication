package com.example.dyju.thesisapplication.ProUsersClasses;

import com.example.dyju.thesisapplication.DhDatas;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;


public class ManipulatorDetailsActivityTest {

    @Test
    public void testCalculateValues() throws Exception {

        String theta = "theta1(t),theta2(t),0";
        String alpha = "90,90,90";
        String a = "0,a1,a2";
        String d = "0,0,d3(t)";

        ManipulatorDetailsActivity manipulatorDetailsActivity = new ManipulatorDetailsActivity();
        DhDatas dhDatas = new DhDatas(theta, alpha, d, a, "mainpulator");
        Map<String, String> values = manipulatorDetailsActivity.calculateValues(dhDatas);


    }
}