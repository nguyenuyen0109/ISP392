/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author admin
 */
import java.sql.Timestamp;

public class InterestRate {
    private int id;
    private double interestRate;
    private Timestamp dateOfApplication;

    // Constructors
    public InterestRate() {
        // Default constructor
    }

    public InterestRate(int id, double interestRate, Timestamp dateOfApplication) {
        this.id = id;
        this.interestRate = interestRate;
        this.dateOfApplication = dateOfApplication;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }

    public Timestamp getDateOfApplication() {
        return dateOfApplication;
    }

    public void setDateOfApplication(Timestamp dateOfApplication) {
        this.dateOfApplication = dateOfApplication;
    }
}
