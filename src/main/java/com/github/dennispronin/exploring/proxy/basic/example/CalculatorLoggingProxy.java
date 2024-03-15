package com.github.dennispronin.exploring.proxy.basic.example;

public class CalculatorLoggingProxy implements Calculator {

    private final Calculator calculator;

    public CalculatorLoggingProxy() {
        this.calculator = new CalculatorImpl();
    }

    @Override
    public int multiply(int operand1, int operand2) {
        logOperands(operand1, operand2);
        int result = calculator.multiply(operand1, operand2);
        logResult(result);
        return result;
    }

    private void logOperands(int operand1, int operand2) {
        System.out.printf("Multiplying: %s and %s%n", operand1, operand2);
    }

    private void logResult(int result) {
        System.out.printf("Result: %s%n", result);
    }
}