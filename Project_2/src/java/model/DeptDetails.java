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
public class DeptDetails {
    int idDebtDetails;
     int DebtId;
     String Note;
     Boolean DebtType;
     Double Amount;
     String Image;
     Date DebtCreatedAt;
     int debtor_IdDebtor;
     int notification_idnotification;
     int interest_rate_id;

    public DeptDetails() {
    }

    public DeptDetails(int idDebtDetails, int DebtId, String Note, Boolean DebtType, Double Amount, String Image, Date DebtCreatedAt, int debtor_IdDebtor, int notification_idnotification, int interest_rate_id) {
        this.idDebtDetails = idDebtDetails;
        this.DebtId = DebtId;
        this.Note = Note;
        this.DebtType = DebtType;
        this.Amount = Amount;
        this.Image = Image;
        this.DebtCreatedAt = DebtCreatedAt;
        this.debtor_IdDebtor = debtor_IdDebtor;
        this.notification_idnotification = notification_idnotification;
        this.interest_rate_id = interest_rate_id;
    }

    public int getIdDebtDetails() {
        return idDebtDetails;
    }

    public void setIdDebtDetails(int idDebtDetails) {
        this.idDebtDetails = idDebtDetails;
    }

    public int getDebtId() {
        return DebtId;
    }

    public void setDebtId(int DebtId) {
        this.DebtId = DebtId;
    }

    public String getNote() {
        return Note;
    }

    public void setNote(String Note) {
        this.Note = Note;
    }

    public Boolean getDebtType() {
        return DebtType;
    }

    public void setDebtType(Boolean DebtType) {
        this.DebtType = DebtType;
    }

    public Double getAmount() {
        return Amount;
    }

    public void setAmount(Double Amount) {
        this.Amount = Amount;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String Image) {
        this.Image = Image;
    }

    public Date getDebtCreatedAt() {
        return DebtCreatedAt;
    }

    public void setDebtCreatedAt(Date DebtCreatedAt) {
        this.DebtCreatedAt = DebtCreatedAt;
    }

    public int getDebtor_IdDebtor() {
        return debtor_IdDebtor;
    }

    public void setDebtor_IdDebtor(int debtor_IdDebtor) {
        this.debtor_IdDebtor = debtor_IdDebtor;
    }

    public int getNotification_idnotification() {
        return notification_idnotification;
    }

    public void setNotification_idnotification(int notification_idnotification) {
        this.notification_idnotification = notification_idnotification;
    }

    public int getInterest_rate_id() {
        return interest_rate_id;
    }

    public void setInterest_rate_id(int interest_rate_id) {
        this.interest_rate_id = interest_rate_id;
    }

    @Override
    public String toString() {
        return "DeptDetails{" + "idDebtDetails=" + idDebtDetails + ", DebtId=" + DebtId + ", Note=" + Note + ", DebtType=" + DebtType + ", Amount=" + Amount + ", Image=" + Image + ", DebtCreatedAt=" + DebtCreatedAt + ", debtor_IdDebtor=" + debtor_IdDebtor + ", notification_idnotification=" + notification_idnotification + ", interest_rate_id=" + interest_rate_id + '}';
    }
     
     
}
