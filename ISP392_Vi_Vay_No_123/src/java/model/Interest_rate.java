/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.security.Timestamp;

/**
 *
 * @author MINIMONIE
 */
public class Interest_rate {
    private int id;
    private int id_debtDetails;
    private double interest_rate;
    private Timestamp date_of_application;

    public Interest_rate() {
    }

    public Interest_rate(int id, int id_debtDetails, double interest_rate, Timestamp date_of_application) {
        this.id = id;
        this.id_debtDetails = id_debtDetails;
        this.interest_rate = interest_rate;
        this.date_of_application = date_of_application;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_debtDetails() {
        return id_debtDetails;
    }

    public void setId_debtDetails(int id_debtDetails) {
        this.id_debtDetails = id_debtDetails;
    }

    public double getInterest_rate() {
        return interest_rate;
    }

    public void setInterest_rate(double interest_rate) {
        this.interest_rate = interest_rate;
    }

    public Timestamp getDate_of_application() {
        return date_of_application;
    }

    public void setDate_of_application(Timestamp date_of_application) {
        this.date_of_application = date_of_application;
    }

    @Override
    public String toString() {
        return "Interest_rate{" + "id=" + id + ", id_debtDetails=" + id_debtDetails + ", interest_rate=" + interest_rate + ", date_of_application=" + date_of_application + '}';
    }
    
}
