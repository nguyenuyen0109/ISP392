/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.Timestamp;

/**
 *
 * @author admin
 */
public class Report {
    private int id;
    private String desciption;
    private String img;
    private Timestamp createAt;
    private int debtdetails_id;
    private int debtdetails_debtor_id;

    public Report() {
    }

    public Report(int id, String desciption, String img, Timestamp createAt, int debtdetails_id, int debtdetails_debtor_id) {
        this.id = id;
        this.desciption = desciption;
        this.img = img;
        this.createAt = createAt;
        this.debtdetails_id = debtdetails_id;
        this.debtdetails_debtor_id = debtdetails_debtor_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDesciption() {
        return desciption;
    }

    public void setDesciption(String desciption) {
        this.desciption = desciption;
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

    public int getDebtdetails_id() {
        return debtdetails_id;
    }

    public void setDebtdetails_id(int debtdetails_id) {
        this.debtdetails_id = debtdetails_id;
    }

    public int getDebtdetails_debtor_id() {
        return debtdetails_debtor_id;
    }

    public void setDebtdetails_debtor_id(int debtdetails_debtor_id) {
        this.debtdetails_debtor_id = debtdetails_debtor_id;
    }
    
    
}
