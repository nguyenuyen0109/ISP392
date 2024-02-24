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
public class Notification {
  private int id;
  private Timestamp notificationDate;
  private String status;
  private String description;
  private int debtdetails_id;
  private int debtor_id;
  private int account_id;
  private Timestamp deleteAt;

    public Notification() {
    }

    public Notification(int id, Timestamp notificationDate, String status, String description, int debtdetails_id) {
        this.id = id;
        this.notificationDate = notificationDate;
        this.status = status;
        this.description = description;
        this.debtdetails_id = debtdetails_id;
    }

    public Notification(int id, Timestamp notificationDate, String status, String description, int debtdetails_id, int debtor_id, int account_id, Timestamp deleteAt) {
        this.id = id;
        this.notificationDate = notificationDate;
        this.status = status;
        this.description = description;
        this.debtdetails_id = debtdetails_id;
        this.debtor_id = debtor_id;
        this.account_id = account_id;
        this.deleteAt = deleteAt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getDebtdetails_id() {
        return debtdetails_id;
    }

    public void setDebtdetails_id(int debtdetails_id) {
        this.debtdetails_id = debtdetails_id;
    }

    public int getDebtor_id() {
        return debtor_id;
    }

    public void setDebtor_id(int debtor_id) {
        this.debtor_id = debtor_id;
    }

    public int getAccount_id() {
        return account_id;
    }

    public void setAccount_id(int account_id) {
        this.account_id = account_id;
    }

    public Timestamp getDeleteAt() {
        return deleteAt;
    }

    public void setDeleteAt(Timestamp deleteAt) {
        this.deleteAt = deleteAt;
    }

    @Override
    public String toString() {
        return "Notification{" + "id=" + id + ", notificationDate=" + notificationDate + ", status=" + status + ", description=" + description + ", debtdetails_id=" + debtdetails_id + ", debtor_id=" + debtor_id + ", account_id=" + account_id + ", deleteAt=" + deleteAt + '}';
    }

    
  
  
}
