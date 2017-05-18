package com.example.dyju.thesisapplication;

import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.*;


public class ResultCalculationServiceTest {
    IResultCalculationService resultCalculationService;

    public ResultCalculationServiceTest() {
        this.resultCalculationService = new ResultCalculationService();
    }

    @Test
    public void testGetMessageFromValidationOfValues() throws Exception {

    }

    @Test
    public void testGetDatasForManipulator() throws Exception {

    }

    @Test
    public void testGetResultForFirstManipulator() throws Exception {
        Map<String, String> result = resultCalculationService.getResultForFirstManipulator();
        System.out.println(result.get("theta11"));
        System.out.println(result.get("theta12"));
        System.out.println(result.get("theta21"));
        System.out.println(result.get("theta22"));
        System.out.println(result.get("theta23"));
        System.out.println(result.get("theta24"));
        System.out.println(result.get("lambda31"));
        System.out.println(result.get("lambda32"));
        System.out.println(result.get("lambda33"));
        System.out.println(result.get("lambda34"));

    }
}