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
public class Feedback {

    private int id;
    private int account_id;
    private String feedback;
    private double rate;
    private String img;
    private Timestamp createAt;
    private Timestamp deleteAt;
    private boolean isDeleted;

    public Feedback() {
    }

    public Feedback(int id, int account_id, String feedback, double rate, String img, Timestamp createAt, Timestamp deleteAt, boolean isDeleted) {
        this.id = id;
        this.account_id = account_id;
        this.feedback = feedback;
        this.rate = rate;
        this.img = img;
        this.createAt = createAt;
        this.deleteAt = deleteAt;
        this.isDeleted = isDeleted;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAccount_id() {
        return account_id;
    }

    public void setAccount_id(int account_id) {
        this.account_id = account_id;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public Timestamp getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Timestamp createAt) {
        this.createAt = createAt;
    }

    public Timestamp getDeleteAt() {
        return deleteAt;
    }

    public void setDeleteAt(Timestamp deleteAt) {
        this.deleteAt = deleteAt;
    }

    public boolean isIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    @Override
    public String toString() {
        return "Feedback{" + "id=" + id + ", account_id=" + account_id + ", feedback=" + feedback + ", rate=" + rate + ", img=" + img + ", createAt=" + createAt + ", deleteAt=" + deleteAt + ", isDeleted=" + isDeleted + '}';
    }

}
