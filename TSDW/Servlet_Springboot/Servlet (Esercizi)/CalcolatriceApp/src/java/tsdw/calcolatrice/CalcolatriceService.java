/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tsdw.calcolatrice;

import static java.lang.Math.pow;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author Gabriele
 */
@WebService(serviceName = "CalcolatriceService")
public class CalcolatriceService {

    /**
     * Web service operation
     */
    @WebMethod(operationName = "Somma")
    public double Somma(@WebParam(name = "x") double x, @WebParam(name = "y") double y) {
        //TODO write your implementation code here:
        return x+y;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "Differenza")
    public double Differenza(@WebParam(name = "x") double x, @WebParam(name = "y") double y) {
        //TODO write your implementation code here:
        return x-y;
    }
    
    @WebMethod(operationName = "Prodotto")
    public double Prodotto(@WebParam(name = "x") double x, @WebParam(name = "y") double y) {
        //TODO write your implementation code here:
        return x*y;
    }
    
    @WebMethod(operationName = "Divisione")
    public double Divisione(@WebParam(name = "x") double x, @WebParam(name = "y") double y) {
        //TODO write your implementation code here:
        return x/y;
    }
    
    @WebMethod(operationName = "Potenza")
    public double Potenza(@WebParam(name = "x") double x, @WebParam(name = "y") double y) {
        //TODO write your implementation code here:
        return pow(x, y);
    }
}
