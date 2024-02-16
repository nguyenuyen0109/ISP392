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

    public Notification() {
    }

    public Notification(int id, Timestamp notificationDate, String status, String description, int debtdetails_id) {
        this.id = id;
        this.notificationDate = notificationDate;
        this.status = status;
        this.description = description;
        this.debtdetails_id = debtdetails_id;
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

    @Override
    public String toString() {
        return "Notification{" + "id=" + id + ", notificationDate=" + notificationDate + ", status=" + status + ", description=" + description + ", debtdetails_id=" + debtdetails_id + '}';
    }
  
  
}
