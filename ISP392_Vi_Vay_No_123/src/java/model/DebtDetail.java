/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.Timestamp;;

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
    private Timestamp createAt;
    private String qr;
    private int debtor_IdDebtor;
    private int idAccount;
    private int interest_rate_id;

    public DebtDetail() {
    }

    public DebtDetail(int id, String description, boolean debtType, double amount, Timestamp createAt) {
        this.id = id;
        this.description = description;
        this.debtType = debtType;
        this.amount = amount;
        this.createAt = createAt;
    }

    public DebtDetail(int id, String description, boolean debtType, double amount, String image, Timestamp createAt, String qr, int debtor_IdDebtor, int idAccount, int interest_rate_id) {
        this.id = id;
        this.description = description;
        this.debtType = debtType;
        this.amount = amount;
        this.image = image;
        this.createAt = createAt;
        this.qr = qr;
        this.debtor_IdDebtor = debtor_IdDebtor;
        this.idAccount = idAccount;
        this.interest_rate_id = interest_rate_id;
    }

    public DebtDetail(int id, String description, boolean debtType, double amount, String image, Timestamp createAt, String qr, int debtor_IdDebtor, int interest_rate_id) {
        this.id = id;
        this.description = description;
        this.debtType = debtType;
        this.amount = amount;
        this.image = image;
        this.createAt = createAt;
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

    public boolean isDebtType() {
        return debtType;
    }

    public void setDebtType(boolean debtType) {
        this.debtType = debtType;
    }

    public Timestamp getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Timestamp createAt) {
        this.createAt = createAt;
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
        return createAt;
    }

    public void setCreatAt(Timestamp createAt) {
        this.createAt = createAt;
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

    public int getIdAccount() {
        return idAccount;
    }

    public void setIdAccount(int idAccount) {
        this.idAccount = idAccount;
    }
    
    public int getInterest_rate_id() {
        return interest_rate_id;
    }

    public void setInterest_rate_id(int interest_rate_id) {
        this.interest_rate_id = interest_rate_id;
    }

    @Override
    public String toString() {
        return "DebtDetail{" + "id=" + id + ", description=" + description + ", debtType=" + debtType + ", amount=" + amount + ", image=" + image + ", createAt=" + createAt + ", qr=" + qr + ", debtor_IdDebtor=" + debtor_IdDebtor + ", idAccount=" + idAccount + ", interest_rate_id=" + interest_rate_id + '}';
    }

    
}
