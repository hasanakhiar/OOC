package org.example.soapcalculator;

import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;

@WebService
public interface CalculatorWebService {
    @WebMethod
    int add(@WebParam(name = "num1") int num1, @WebParam(name = "num2") int num2);
    @WebMethod
    int sub(@WebParam(name = "num1") int num1, @WebParam(name = "num2") int num2);
    @WebMethod
    int mul(@WebParam(name = "num1") int num1, @WebParam(name = "num2") int num2);
    @WebMethod
    int div(@WebParam(name = "num1") int num1, @WebParam(name = "num2") int num2);
}