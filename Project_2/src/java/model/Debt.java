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
public class Debt {
    private int IdDebt;
    private int account_idAccount;
    private String Name;
    private String Address;
    private String Email;
    private String Phone;
    private double TotalDebt;
    private Timestamp CreatedAt;
    private Timestamp UpdatedAt;

    public Debt() {
    }

    public Debt(int IdDebt, int account_idAccount, String Name, String Address, String Email, String Phone, double TotalDebt, Timestamp CreatedAt, Timestamp UpdatedAt) {
        this.IdDebt = IdDebt;
        this.account_idAccount = account_idAccount;
        this.Name = Name;
        this.Address = Address;
        this.Email = Email;
        this.Phone = Phone;
        this.TotalDebt = TotalDebt;
        this.CreatedAt = CreatedAt;
        this.UpdatedAt = UpdatedAt;
    }

    public int getIdDebt() {
        return IdDebt;
    }

    public void setIdDebt(int IdDebt) {
        this.IdDebt = IdDebt;
    }

    public int getAccount_idAccount() {
        return account_idAccount;
    }

    public void setAccount_idAccount(int account_idAccount) {
        this.account_idAccount = account_idAccount;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String Address) {
        this.Address = Address;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String Phone) {
        this.Phone = Phone;
    }

    public double getTotalDebt() {
        return TotalDebt;
    }

    public void setTotalDebt(double TotalDebt) {
        this.TotalDebt = TotalDebt;
    }

    public Timestamp getCreatedAt() {
        return CreatedAt;
    }

    public void setCreatedAt(Timestamp CreatedAt) {
        this.CreatedAt = CreatedAt;
    }

    public Timestamp getUpdatedAt() {
        return UpdatedAt;
    }

    public void setUpdatedAt(Timestamp UpdatedAt) {
        this.UpdatedAt = UpdatedAt;
    }

    @Override
    public String toString() {
        return "Debtor{" + "IdDebt=" + IdDebt + ", account_idAccount=" + account_idAccount + ", Name=" + Name + ", Address=" + Address + ", Email=" + Email + ", Phone=" + Phone + ", TotalDebt=" + TotalDebt + ", CreatedAt=" + CreatedAt + ", UpdatedAt=" + UpdatedAt + '}';
    }
    
}
