package org.example.soapcalculator;

import jakarta.jws.WebService;

// CHANGE 'com' TO 'org' IN THIS LINE BELOW:
@WebService(endpointInterface = "org.example.soapcalculator.CalculatorWebService")
public class CalculatorWebServiceImpl implements CalculatorWebService {
    @Override
    public int add(int num1, int num2) {
        return num1 + num2;
    }

    @Override
    public int sub(int num1, int num2) {
        return num1 - num2;
    }

    @Override
    public int mul(int num1, int num2) {
        return num1 * num2;
    }
    @Override
    public int div(int num1, int num2) {
        return num1 / num2;
    }
}