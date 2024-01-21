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
    private int idRole;
    private String name;
    private Timestamp createAt;
    private Timestamp updateAt;

    public Role() {
    }

    public Role(int idRole, String name, Timestamp createAt, Timestamp updateAt) {
        this.idRole = idRole;
        this.name = name;
        this.createAt = createAt;
        this.updateAt = updateAt;
    }

    public int getIdRole() {
        return idRole;
    }

    public void setIdRole(int idRole) {
        this.idRole = idRole;
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

    public Timestamp getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(Timestamp updateAt) {
        this.updateAt = updateAt;
    }

    @Override
    public String toString() {
        return "Role{" + "idRole=" + idRole + ", name=" + name + ", createAt=" + createAt + ", updateAt=" + updateAt + '}';
    }

    
}


