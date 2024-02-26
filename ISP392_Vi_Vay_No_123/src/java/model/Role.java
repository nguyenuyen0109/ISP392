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
public class Role {
    private String name;
    private Timestamp updateAt;
    private Timestamp createAt;
    private Timestamp deleteAt;
    private boolean isDeleted;

    public Role() {
    }

    public Role(String name, Timestamp updateAt, Timestamp createAt, Timestamp deleteAt) {
        this.name = name;
        this.updateAt = updateAt;
        this.createAt = createAt;
        this.deleteAt = deleteAt;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Timestamp getDeleteAt() {
        return deleteAt;
    }

    public void setDeleteAt(Timestamp deleteAt) {
        this.deleteAt = deleteAt;
    }

    public Role(String name, Timestamp updateAt, Timestamp createAt, Timestamp deleteAt, boolean isDeleted) {
        this.name = name;
        this.updateAt = updateAt;
        this.createAt = createAt;
        this.deleteAt = deleteAt;
        this.isDeleted = isDeleted;
    }

    public boolean isIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    @Override
    public String toString() {
        return "Role{" + "name=" + name + ", updateAt=" + updateAt + ", createAt=" + createAt + ", deleteAt=" + deleteAt + ", isDeleted=" + isDeleted + '}';
    }

    
    
}
