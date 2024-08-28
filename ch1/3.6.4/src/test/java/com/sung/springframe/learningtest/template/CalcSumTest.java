package com.sung.springframe.learningtest.template;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;

import org.junit.jupiter.api.BeforeEach; // JUnit 5의 BeforeEach를 사용
import org.junit.jupiter.api.Test;

public class CalcSumTest {
    Calculator calculator;
    String numFilepath;
    
    @BeforeEach
    public void setUp() { // JUnit 5에서는 BeforeEach로 변경
        this.calculator = new Calculator();
        // '/'의 의미는 상대결로(최상위 경로)
        this.numFilepath = getClass().getResource("/numbers.txt").getPath();
    }
    
    @Test 
    public void sumOfNumbers() throws IOException {
        assertEquals(calculator.calcSum(this.numFilepath), 10);
    }
    
    @Test 
    public void multiplyOfNumbers() throws IOException {
        assertEquals(calculator.calcMultiply(this.numFilepath), 24);
    }
    
    @Test
    public void concatenateStrings() throws IOException {
        assertEquals(calculator.concatenate(this.numFilepath), "1234");
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub

    }

}
