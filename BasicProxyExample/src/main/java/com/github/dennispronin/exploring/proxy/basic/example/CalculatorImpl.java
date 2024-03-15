package com.github.dennispronin.exploring.proxy.basic.example;

public class CalculatorImpl implements Calculator {

    @Override
    public int multiply(int operand1, int operand2) {
        return operand1 * operand2;
    }
}