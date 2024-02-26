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
public class Feedback {
    private int id;
    private int debtdetails_id;
    private int debtor_id;
    private String status;
    private String description;
    private String img;
    private Timestamp createAt;
    private Timestamp deleteAt;
    private boolean isDeleted;

    public Feedback() {
    }

    public Feedback(int id, int debtdetails_id, int debtor_id, String status, String description, String img, Timestamp createAt, Timestamp deleteAt, boolean isDeleted) {
        this.id = id;
        this.debtdetails_id = debtdetails_id;
        this.debtor_id = debtor_id;
        this.status = status;
        this.description = description;
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
        return "Feedback{" + "id=" + id + ", debtdetails_id=" + debtdetails_id + ", debtor_id=" + debtor_id + ", status=" + status + ", description=" + description + ", img=" + img + ", createAt=" + createAt + ", deleteAt=" + deleteAt + ", isDeleted=" + isDeleted + '}';
    }
    
}
