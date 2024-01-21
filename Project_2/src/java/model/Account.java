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
public class Account {
    /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
    private int idAccount;
    private String username;
    private double amount;
    private String password;
    private String name;
    private String mobileNumber;
    private String emailAddress;
    private String address;
    private boolean isActive;
    private Timestamp updateAt;
  //  private String emailConfirmationCode;
    private Timestamp createAt;
    private String avatarUrl;
    private boolean gender; // Assuming true for one gender and false for another
    private int roleIdRole; // Foreign key to Role

    public Account() {
    }

    public Account(int idAccount, String username, double amount, String password, String name, String mobileNumber, String emailAddress, String address, boolean isActive, Timestamp updateAt
                , Timestamp createAt, String avatarUrl, boolean gender, int roleIdRole) {
        this.idAccount = idAccount;
        this.username = username;
        this.amount = amount;
        this.password = password;
        this.name = name;
        this.mobileNumber = mobileNumber;
        this.emailAddress = emailAddress;
        this.address = address;
        this.isActive = isActive;
        this.updateAt = updateAt;
       // this.emailConfirmationCode = emailConfirmationCode;
        this.createAt = createAt;
        this.avatarUrl = avatarUrl;
        this.gender = gender;
        this.roleIdRole = roleIdRole;
    }

    public int getIdAccount() {
        return idAccount;
    }

    public void setIdAccount(int idAccount) {
        this.idAccount = idAccount;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public boolean isIsActive() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    public Timestamp getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(Timestamp updateAt) {
        this.updateAt = updateAt;
    }

 

    public Timestamp getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Timestamp createAt) {
        this.createAt = createAt;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public int getRoleIdRole() {
        return roleIdRole;
    }

    public void setRoleIdRole(int roleIdRole) {
        this.roleIdRole = roleIdRole;
    }

    @Override
    public String toString() {
        return "Account{" + "idAccount=" + idAccount + ", username=" + username + ", amount=" + amount + ", password=" + password + ", name=" + name + ", mobileNumber=" + mobileNumber + ", emailAddress=" + emailAddress + ", address=" + address + ", isActive=" + isActive + ", updateAt=" + updateAt + ", createAt=" + createAt + ", avatarUrl=" + avatarUrl + ", gender=" + gender + ", roleIdRole=" + roleIdRole + '}';
    }

    public Account(int idAccount, String username, double amount, String password, String name, String mobileNumber, String emailAddress, String address, boolean isActive, Timestamp updateAt, Timestamp createAt, String avatarUrl, boolean gender) {
        this.idAccount = idAccount;
        this.username = username;
        this.amount = amount;
        this.password = password;
        this.name = name;
        this.mobileNumber = mobileNumber;
        this.emailAddress = emailAddress;
        this.address = address;
        this.isActive = isActive;
        this.updateAt = updateAt;
        this.createAt = createAt;
        this.avatarUrl = avatarUrl;
        this.gender = gender;
    }

    
    
    
}


