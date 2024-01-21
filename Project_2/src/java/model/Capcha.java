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
public class Capcha {
    int account_id;
    String capchaCode;
    Timestamp createAt;
    boolean isConfirmed;

    public Capcha() {
    }

    public Capcha(int account_id, String capchaCode, Timestamp createAt, boolean isConfirmed) {
        this.account_id = account_id;
        this.capchaCode = capchaCode;
        this.createAt = createAt;
        this.isConfirmed = isConfirmed;
    }

    public int getAccount_id() {
        return account_id;
    }

    public void setAccount_id(int account_id) {
        this.account_id = account_id;
    }

    public String getCapchaCode() {
        return capchaCode;
    }

    public void setCapchaCode(String capchaCode) {
        this.capchaCode = capchaCode;
    }

    public Timestamp getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Timestamp createAt) {
        this.createAt = createAt;
    }

    public boolean isIsConfirmed() {
        return isConfirmed;
    }

    public void setIsConfirmed(boolean isConfirmed) {
        this.isConfirmed = isConfirmed;
    }

    @Override
    public String toString() {
        return "Capcha{" + "account_id=" + account_id + ", capchaCode=" + capchaCode + ", createAt=" + createAt + ", isConfirmed=" + isConfirmed + '}';
    }
    
    
}
