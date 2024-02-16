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
public class DebtDetail {
    private int id;
    private String description;
    private boolean deptType;
    private double amount;
    private String image;
    private Timestamp creatAt;
    private String qr;
    private int debtor_IdDebtor;
    private int interest_rate_id;

    public DebtDetail() {
    }

    public DebtDetail(int id, String description, boolean deptType, double amount, String image, Timestamp creatAt, String qr, int debtor_IdDebtor, int interest_rate_id) {
        this.id = id;
        this.description = description;
        this.deptType = deptType;
        this.amount = amount;
        this.image = image;
        this.creatAt = creatAt;
        this.qr = qr;
        this.debtor_IdDebtor = debtor_IdDebtor;
        this.interest_rate_id = interest_rate_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isDeptType() {
        return deptType;
    }

    public void setDeptType(boolean deptType) {
        this.deptType = deptType;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Timestamp getCreatAt() {
        return creatAt;
    }

    public void setCreatAt(Timestamp creatAt) {
        this.creatAt = creatAt;
    }

    public String getQr() {
        return qr;
    }
   
    public void setQr(String qr) {
        this.qr = qr;
    }

    public int getDebtor_IdDebtor() {
        return debtor_IdDebtor;
    }

    public void setDebtor_IdDebtor(int debtor_IdDebtor) {
        this.debtor_IdDebtor = debtor_IdDebtor;
    }

    public int getInterest_rate_id() {
        return interest_rate_id;
    }

    public void setInterest_rate_id(int interest_rate_id) {
        this.interest_rate_id = interest_rate_id;
    }

    @Override
    public String toString() {
        return "DebtDetail{" + "id=" + id + ", description=" + description + ", deptType=" + deptType + ", amount=" + amount + ", image=" + image + ", creatAt=" + creatAt + ", qr=" + qr + ", debtor_IdDebtor=" + debtor_IdDebtor + ", interest_rate_id=" + interest_rate_id + '}';
    }
    
}
