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
public class DebtType {
    int id;
    String name;
    java.sql.Timestamp createAt;
    boolean isDelete;
    java.sql.Timestamp deleteAt;

    public DebtType() {
    }

    public DebtType(int id, String name, Timestamp createAt, boolean isDelete, Timestamp deleteAt) {
        this.id = id;
        this.name = name;
        this.createAt = createAt;
        this.isDelete = isDelete;
        this.deleteAt = deleteAt;
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

    public Timestamp getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Timestamp createAt) {
        this.createAt = createAt;
    }

    public boolean isIsDelete() {
        return isDelete;
    }

    public void setIsDelete(boolean isDelete) {
        this.isDelete = isDelete;
    }

    public Timestamp getDeleteAt() {
        return deleteAt;
    }

    public void setDeleteAt(Timestamp deleteAt) {
        this.deleteAt = deleteAt;
    }

    @Override
    public String toString() {
        return "DebtType{" + "id=" + id + ", name=" + name + ", createAt=" + createAt + ", isDelete=" + isDelete + ", deleteAt=" + deleteAt + '}';
    }
    
}
