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
    private int id;
    private String username;
    private String password;
    private String name;
    private String mobileNumber;
    private String emailAddress;
    private String address;
    private boolean isActive;
    private Timestamp updateAt;
    private Timestamp createAt;
    private String avatarUrl;
    private boolean gender; // Assuming true for one gender and false for another
    private int role_id; // Foreign key to Role
    private int salt_id;
    public Account() {
    }

    public Account(int id, String username,String password, String name, String mobileNumber, String emailAddress, String address, boolean isActive, Timestamp updateAt, Timestamp createAt, String avatarUrl, boolean gender, int role_id) {
        this.id = id;
        this.username = username;
        
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
        this.role_id = role_id;
        this.salt_id = salt_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public int getRole_id() {
        return role_id;
    }

    public void setRole_id(int role_id) {
        this.role_id = role_id;
    }

    public int getSalt_id() {
        return salt_id;
    }

    public void setSalt_id(int salt_id) {
        this.salt_id = salt_id;
    }

    
    
    
    
}


