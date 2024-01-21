/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author admin
 */
public class PaymentMethod {
    private int idPaymentMethod;
    private String methodName;

    // Constructors
    public PaymentMethod() {
        // Default constructor
    }

    public PaymentMethod(int idPaymentMethod, String methodName) {
        this.idPaymentMethod = idPaymentMethod;
        this.methodName = methodName;
    }

    // Getters and Setters
    public int getIdPaymentMethod() {
        return idPaymentMethod;
    }

    public void setIdPaymentMethod(int idPaymentMethod) {
        this.idPaymentMethod = idPaymentMethod;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }
}

