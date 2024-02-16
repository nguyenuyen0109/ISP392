/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.security.Timestamp;
import java.util.ArrayList;
import java.util.List;

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
    private java.sql.Timestamp createdAt;
    private java.sql.Timestamp updatedAt;
    private int account_id;
    private int history_payment_id;

    public Debtor() {
    }

    public Debtor(int id, String name, String address, String phone, String email, double totalDebt, java.sql.Timestamp createdAt, java.sql.Timestamp updatedAt, int account_id, int history_payment_id) {
        this.id = id;
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

        /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

    public Debtor(String name, String address, String phone, String email, double totalDebt, int account_id) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.totalDebt = totalDebt;
        this.account_id = account_id;
    }

   

    public int getIdDebtor() {
        return id;
    }

    public void setIdDebtor(int id) {
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

    public java.sql.Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(java.sql.Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public java.sql.Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(java.sql.Timestamp updatedAt) {
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
        return "Debtor{" + "id=" + id + ", name=" + name + ", address=" + address + ", phone=" + phone + ", email=" + email + ", totalDebt=" + totalDebt + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + ", account_id=" + account_id + ", history_payment_id=" + history_payment_id + '}';
    }
    
}
