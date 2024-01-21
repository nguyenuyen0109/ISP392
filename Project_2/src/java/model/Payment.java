package model;

import java.sql.Timestamp;

public class Payment {
   private int idPayment;
    private double amount;
    private Timestamp paymentDeadline;
    private Timestamp payDate;
    private int accountIdAccount;
    private int paymentMethodIdPaymentMethod;

    // Constructors
    public Payment() {
        // Default constructor
    }

    public Payment(int idPayment, double amount, Timestamp paymentDeadline, Timestamp payDate, int accountIdAccount, int paymentMethodIdPaymentMethod) {
        this.idPayment = idPayment;
        this.amount = amount;
        this.paymentDeadline = paymentDeadline;
        this.payDate = payDate;
        this.accountIdAccount = accountIdAccount;
        this.paymentMethodIdPaymentMethod = paymentMethodIdPaymentMethod;
    }

    // Getters and Setters
    public int getIdPayment() {
        return idPayment;
    }

    public void setIdPayment(int idPayment) {
        this.idPayment = idPayment;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Timestamp getPaymentDeadline() {
        return paymentDeadline;
    }

    public void setPaymentDeadline(Timestamp paymentDeadline) {
        this.paymentDeadline = paymentDeadline;
    }

    public Timestamp getPayDate() {
        return payDate;
    }

    public void setPayDate(Timestamp payDate) {
        this.payDate = payDate;
    }

    public int getAccountIdAccount() {
        return accountIdAccount;
    }

    public void setAccountIdAccount(int accountIdAccount) {
        this.accountIdAccount = accountIdAccount;
    }

    public int getPaymentMethodIdPaymentMethod() {
        return paymentMethodIdPaymentMethod;
    }

    public void setPaymentMethodIdPaymentMethod(int paymentMethodIdPaymentMethod) {
        this.paymentMethodIdPaymentMethod = paymentMethodIdPaymentMethod;
    }

    @Override
    public String toString() {
        return "Payment{" + "idPayment=" + idPayment + ", amount=" + amount + ", paymentDeadline=" + paymentDeadline + ", payDate=" + payDate + ", accountIdAccount=" + accountIdAccount + ", paymentMethodIdPaymentMethod=" + paymentMethodIdPaymentMethod + '}';
    }

    
}
