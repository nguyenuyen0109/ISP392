/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.Timestamp;

/**
 *
 * @author MINIMONIE
 */
public class DebtDetail {
    private int id;
    private String description;
    private boolean debtType;
    private double amount;
    private String image;
    private Timestamp creatAt;
    private String qr;
    private int debtor_id;
    private int debtor_account_id;
    private int interest_rate_id;

    public DebtDetail() {
    }

    public DebtDetail(String description, boolean debtType, double amount, int debtor_id, int debtor_account_id, int interest_rate_id) {
        this.description = description;
        this.debtType = debtType;
        this.amount = amount;
        this.debtor_id = debtor_id;
        this.debtor_account_id = debtor_account_id;
        this.interest_rate_id = interest_rate_id;
    }

    public DebtDetail(int id, String description, boolean debtType, double amount, Timestamp creatAt) {
        this.id = id;
        this.description = description;
        this.debtType = debtType;
        this.amount = amount;
        this.creatAt = creatAt;
    }

//    public DebtDetail(String description, boolean debtType, double amount, int interest_rate_id) {
//        this.description = description;
//        this.debtType = debtType;
//        this.amount = amount;
//        this.interest_rate_id = interest_rate_id;
//    }

    public DebtDetail(int id, String description, boolean debtType, double amount, String image, Timestamp creatAt, String qr, int debtor_id, int debtor_account_id, int interest_rate_id) {
        this.id = id;
        this.description = description;
        this.debtType = debtType;
        this.amount = amount;
        this.image = image;
        this.creatAt = creatAt;
        this.qr = qr;
        this.debtor_id = debtor_id;
        this.debtor_account_id = debtor_account_id;
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
        return debtType;
    }

    public void setDeptType(boolean debtType) {
        this.debtType = debtType;
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

    public int getDebtor_id() {
        return debtor_id;
    }

    public void setDebtor_id(int debtor_id) {
        this.debtor_id = debtor_id;
    }

    public int getDebtor_account_id() {
        return debtor_account_id;
    }

    public void setDebtor_account_id(int debtor_account_id) {
        this.debtor_account_id = debtor_account_id;
    }

    public int getInterest_rate_id() {
        return interest_rate_id;
    }

    public void setInterest_rate_id(int interest_rate_id) {
        this.interest_rate_id = interest_rate_id;
    }

    @Override
    public String toString() {
        return "DebtDetail{" + "id=" + id + ", description=" + description + ", debtType=" + debtType + ", amount=" + amount + ", creatAt=" + creatAt + '}';
    }
    
    
    
}
