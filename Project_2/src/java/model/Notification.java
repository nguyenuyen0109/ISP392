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

public class Notification {
    private int idNotification;
    private Timestamp notificationDate;
    private String status;
    private String description;
    private int debtdetailsIdDebtDetails;
    private int debtdetailsDebtorAccountIdAccount;
    private int debtdetailsDebtorIdDebtor;

    // Constructors
    public Notification() {
        // Default constructor
    }

    public Notification(int idNotification, Timestamp notificationDate, String status, String description, int debtdetailsIdDebtDetails, int debtdetailsDebtorAccountIdAccount, int debtdetailsDebtorIdDebtor) {
        this.idNotification = idNotification;
        this.notificationDate = notificationDate;
        this.status = status;
        this.description = description;
        this.debtdetailsIdDebtDetails = debtdetailsIdDebtDetails;
        this.debtdetailsDebtorAccountIdAccount = debtdetailsDebtorAccountIdAccount;
        this.debtdetailsDebtorIdDebtor = debtdetailsDebtorIdDebtor;
    }

    // Getters and Setters
    public int getIdNotification() {
        return idNotification;
    }

    public void setIdNotification(int idNotification) {
        this.idNotification = idNotification;
    }

    public Timestamp getNotificationDate() {
        return notificationDate;
    }

    public void setNotificationDate(Timestamp notificationDate) {
        this.notificationDate = notificationDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getDebtdetailsIdDebtDetails() {
        return debtdetailsIdDebtDetails;
    }

    public void setDebtdetailsIdDebtDetails(int debtdetailsIdDebtDetails) {
        this.debtdetailsIdDebtDetails = debtdetailsIdDebtDetails;
    }

    public int getDebtdetailsDebtorAccountIdAccount() {
        return debtdetailsDebtorAccountIdAccount;
    }

    public void setDebtdetailsDebtorAccountIdAccount(int debtdetailsDebtorAccountIdAccount) {
        this.debtdetailsDebtorAccountIdAccount = debtdetailsDebtorAccountIdAccount;
    }

    public int getDebtdetailsDebtorIdDebtor() {
        return debtdetailsDebtorIdDebtor;
    }

    public void setDebtdetailsDebtorIdDebtor(int debtdetailsDebtorIdDebtor) {
        this.debtdetailsDebtorIdDebtor = debtdetailsDebtorIdDebtor;
    }

    @Override
    public String toString() {
        return "Notification{" + "idNotification=" + idNotification + ", notificationDate=" + notificationDate + ", status=" + status + ", description=" + description + ", debtdetailsIdDebtDetails=" + debtdetailsIdDebtDetails + ", debtdetailsDebtorAccountIdAccount=" + debtdetailsDebtorAccountIdAccount + ", debtdetailsDebtorIdDebtor=" + debtdetailsDebtorIdDebtor + '}';
    }

    
}


