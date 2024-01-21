/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Date;

/**
 *
 * @author MINIMONIE
 */
public class History_Payment {
    int idHistory_Payment;
    double amount;
    Date payment_date;

    public History_Payment() {
    }

    public History_Payment(int idHistory_Payment, double amount, Date payment_date) {
        this.idHistory_Payment = idHistory_Payment;
        this.amount = amount;
        this.payment_date = payment_date;
    }

    public int getIdHistory_Payment() {
        return idHistory_Payment;
    }

    public void setIdHistory_Payment(int idHistory_Payment) {
        this.idHistory_Payment = idHistory_Payment;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Date getPayment_date() {
        return payment_date;
    }

    public void setPayment_date(Date payment_date) {
        this.payment_date = payment_date;
    }

    @Override
    public String toString() {
        return "History_Payment{" + "idHistory_Payment=" + idHistory_Payment + ", amount=" + amount + ", payment_date=" + payment_date + '}';
    }
    
}
