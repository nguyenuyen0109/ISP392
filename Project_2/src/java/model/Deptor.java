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
public class Deptor {
    private int idDeptor;
    private String name;
    private String address;
    private String phone;
    private String email;
    private double totalDebt;
    private Timestamp createdAt;
    private Timestamp updatedAt;
    private int account_id;
    private int history_payment_id;

    public Deptor() {
    }

    public Deptor(int idDeptor, String name, String address, String phone, String email, double totalDebt, Timestamp createdAt, Timestamp updatedAt, int account_id, int history_payment_id) {
        this.idDeptor = idDeptor;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.totalDebt = totalDebt;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.account_id = account_id;
        this.history_payment_id = history_payment_id;
    }

    public int getIdDeptor() {
        return idDeptor;
    }

    public void setIdDeptor(int idDeptor) {
        this.idDeptor = idDeptor;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public double getTotalDebt() {
        return totalDebt;
    }

    public void setTotalDebt(double totalDebt) {
        this.totalDebt = totalDebt;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

    public int getAccount_id() {
        return account_id;
    }

    public void setAccount_id(int account_id) {
        this.account_id = account_id;
    }

    public int getHistory_payment_id() {
        return history_payment_id;
    }

    public void setHistory_payment_id(int history_payment_id) {
        this.history_payment_id = history_payment_id;
    }

    @Override
    public String toString() {
        return "Deptor{" + "idDeptor=" + idDeptor + ", name=" + name + ", address=" + address + ", phone=" + phone + ", email=" + email + ", totalDebt=" + totalDebt + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + ", account_id=" + account_id + ", history_payment_id=" + history_payment_id + '}';
    }
    
}
