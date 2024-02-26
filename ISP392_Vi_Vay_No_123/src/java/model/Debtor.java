/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.Timestamp;
import java.util.Comparator;


/**
 *
 * @author MINIMONIE
 */
public class Debtor {
    private int id;
    private String name;
    private String address;
    private String phone;
    private String email;
    private double totalDebt;
    private Timestamp createdAt;
    private Timestamp updatedAt;
    private int account_id;
    private int creditor_account_id;
    private Timestamp deletedAt;
    private boolean isDeleted;
    public Debtor() {
    }
     
    public Debtor(int id, String name, String address, String phone, String email, double totalDebt, Timestamp createdAt, Timestamp updatedAt, int creditor_account_id) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.totalDebt = totalDebt;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.creditor_account_id = creditor_account_id;
    }

    public Debtor(int id, String name, String address, String phone, String email, double totalDebt, Timestamp createdAt, Timestamp updatedAt,int account_id, int creditor_account_id) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.totalDebt = totalDebt;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.account_id = account_id;
        this.creditor_account_id = creditor_account_id;
    }

    public Debtor(String name, String address, String phone, String email, int account_id) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.account_id = account_id;
    }

    public Debtor(String name, String address, String phone, String email, double totalDebt,int account_id) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.totalDebt = totalDebt;
        this.account_id = account_id;
    }

    public Debtor(int id, String name, String address, String phone, String email, double totalDebt, Timestamp createdAt, Timestamp updatedAt, int account_id, int creditor_account_id, Timestamp deletedAt) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.totalDebt = totalDebt;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.account_id = account_id;
        this.creditor_account_id = creditor_account_id;
        this.deletedAt = deletedAt;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getCreditor_account_id() {
        return creditor_account_id;
    }

    public void setCreditor_account_id(int creditor_account_id) {
        this.creditor_account_id = creditor_account_id;
    }

    public Timestamp getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(Timestamp deletedAt) {
        this.deletedAt = deletedAt;
    }

    public boolean isIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    @Override
    public String toString() {
        return "Debtor{" + "id=" + id + ", name=" + name + ", address=" + address + ", phone=" + phone + ", email=" + email + ", totalDebt=" + totalDebt + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + ", account_id=" + account_id + ", creditor_account_id=" + creditor_account_id + ", deletedAt=" + deletedAt + ", isDeleted=" + isDeleted + '}';
    }

    

    

}