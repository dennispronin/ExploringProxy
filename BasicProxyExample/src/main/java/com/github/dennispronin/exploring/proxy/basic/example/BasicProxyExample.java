package com.github.dennispronin.exploring.proxy.basic.example;

public class BasicProxyExample {

    public static void main(String[] args) {
        Calculator calculator = createCalculator();
        calculator.multiply(2, 2);
    }

    public static Calculator createCalculator() {
        return new CalculatorLoggingProxy();
    }
}