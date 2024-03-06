/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.Timestamp;
import java.util.Date;

/**
 *
 * @author MINIMONIE
 */
public class DebtDetail {

    private int id;
    private String description;
    private int debtType;
    private double amount;
    private String image;
    private Timestamp createAt;
    private int debtor_IdDebtor;
    private String debtorName;
    private int idAccount;
    private double interestRate;
    private double due;
    private boolean status;
    private Timestamp deletedAt;
    private boolean isDeleted;
    private int debtTypeId;
    private String debtTypeName;
    private double totalAmount;
    private Date debtIssuance;

    public DebtDetail() {
    }

    public DebtDetail(int id, String description, int debtType, double amount, Timestamp createAt) {
        this.id = id;
        this.description = description;
        this.debtType = debtType;
        this.amount = amount;
        this.createAt = createAt;
    }

    public DebtDetail(String description, int debtType, double amount, String image, int debtor_IdDebtor, int idAccount, double interestRate, double due) {
        this.description = description;
        this.debtType = debtType;
        this.amount = amount;
        this.image = image;
        this.debtor_IdDebtor = debtor_IdDebtor;
        this.idAccount = idAccount;
        this.interestRate = interestRate;
        this.due = due;
    }

    public DebtDetail(int id, String description, double amount, String image, Timestamp createAt, int debtor_IdDebtor, int idAccount, double interestRate, double due, boolean status, Timestamp deletedAt, boolean isDeleted, int debtTypeId, String debtTypeName, double totalAmount, Date debtIssuance) {
        this.id = id;
        this.description = description;
        this.amount = amount;
        this.image = image;
        this.createAt = createAt;
        this.debtor_IdDebtor = debtor_IdDebtor;
        this.idAccount = idAccount;
        this.interestRate = interestRate;
        this.due = due;
        this.status = status;
        this.deletedAt = deletedAt;
        this.isDeleted = isDeleted;
        this.debtTypeId = debtTypeId;
        this.debtTypeName = debtTypeName;
        this.totalAmount = totalAmount;
        this.debtIssuance = debtIssuance;
    }

    public DebtDetail(int id, String description, double amount, String image, Timestamp createAt, int debtor_IdDebtor, int idAccount, double interestRate, double due, Timestamp deletedAt, boolean isDeleted, int debtTypeId, double totalAmount, Date debtIssuance) {
        this.id = id;
        this.description = description;
        this.amount = amount;
        this.image = image;
        this.createAt = createAt;
        this.debtor_IdDebtor = debtor_IdDebtor;
        this.idAccount = idAccount;
        this.interestRate = interestRate;
        this.due = due;
        this.deletedAt = deletedAt;
        this.isDeleted = isDeleted;
        this.debtTypeId = debtTypeId;
        this.totalAmount = totalAmount;
        this.debtIssuance = debtIssuance;
    }

    public DebtDetail(String description, double amount, String image, int debtor_IdDebtor, int idAccount, double interestRate, double due, String debtTypeName, Date debtIssuance) {
        this.description = description;
        this.amount = amount;
        this.image = image;
        this.debtor_IdDebtor = debtor_IdDebtor;
        this.idAccount = idAccount;
        this.interestRate = interestRate;
        this.due = due;
        this.debtTypeName = debtTypeName;
        this.debtIssuance = debtIssuance;
    }

    public DebtDetail(String description, double amount, String image, int debtor_IdDebtor, int idAccount, double interestRate, double due, int debtTypeId, Date debtIssuance) {
        this.description = description;
        this.amount = amount;
        this.image = image;
        this.debtor_IdDebtor = debtor_IdDebtor;
        this.idAccount = idAccount;
        this.interestRate = interestRate;
        this.due = due;
        this.debtTypeId = debtTypeId;
        this.debtIssuance = debtIssuance;
    }

    public DebtDetail(int id, String description, double amount, String image, Timestamp createAt, int debtor_IdDebtor, int debtTypeId, double interest, double due) {
        this.id = id;
        this.description = description;
        this.amount = amount;
        this.image = image;
        this.createAt = createAt;
        this.debtor_IdDebtor = debtor_IdDebtor;
        this.debtTypeId = debtTypeId;
        this.interestRate = interest;
        this.due = due;
    }

    public DebtDetail(int id, String description, int debtTypeId, double amount, String image,
            Timestamp createAt, int idDebtor, int accountid, double interest, double due) {
        this.id = id;
        this.description = description;
        this.debtTypeId = debtTypeId;
        this.amount = amount;
        this.image = image;
        this.createAt = createAt;
        this.debtor_IdDebtor = idDebtor;
        this.idAccount = accountid;
        this.interestRate = interest;
        this.due = due;
    }

    public DebtDetail(String description, int debtType, double amount,
            int debtorId, int accountId, 
            int interest_rate) {
        this.description = description;
this.debtTypeId = debtType;
        this.amount = amount;
        this.image = image;
        this.createAt = createAt;
        this.debtor_IdDebtor = debtor_IdDebtor;
        this.idAccount = accountId;
        this.interestRate = interestRate;
        
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getDebtType() {
        return debtType;
    }

    public void setDebtType(int debtType) {
        this.debtType = debtType;
    }

    public Timestamp getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Timestamp createAt) {
        this.createAt = createAt;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getDebtor_IdDebtor() {
        return debtor_IdDebtor;
    }

    public void setDebtor_IdDebtor(int debtor_IdDebtor) {
        this.debtor_IdDebtor = debtor_IdDebtor;
    }

    public String getDebtorName() {
        return debtorName;
    }

    public void setDebtorName(String debtorName) {
        this.debtorName = debtorName;
    }

    public int getIdAccount() {
        return idAccount;
    }

    public void setIdAccount(int idAccount) {
        this.idAccount = idAccount;
    }

    public double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }

    public double getDue() {
        return due;
    }

    public void setDue(double due) {
        this.due = due;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Timestamp getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(Timestamp deletedAt) {
        this.deletedAt = deletedAt;
    }

    public DebtDetail(int id, String description, int debtType, double amount, String image, Timestamp createAt, int debtor_IdDebtor, int idAccount, double interestRate, double due, boolean status, Timestamp deletedAt, boolean isDeleted) {
        this.id = id;
        this.description = description;
        this.debtType = debtType;
        this.amount = amount;
        this.image = image;
        this.createAt = createAt;
        this.debtor_IdDebtor = debtor_IdDebtor;
        this.idAccount = idAccount;
        this.interestRate = interestRate;
        this.due = due;
        this.status = status;
        this.deletedAt = deletedAt;
        this.isDeleted = isDeleted;
    }

    public boolean isIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    public int getDebtTypeId() {
        return debtTypeId;
    }

    public void setDebtTypeId(int debtTypeId) {
        this.debtTypeId = debtTypeId;
    }

    public String getDebtTypeName() {
        return debtTypeName;
    }

    public void setDebtTypeName(String debtTypeName) {
        this.debtTypeName = debtTypeName;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Date getDebtIssuance() {
        return debtIssuance;
    }

    public void setDebtIssuance(Date debtIssuance) {
        this.debtIssuance = debtIssuance;
    }

    @Override
    public String toString() {
        return "DebtDetail{" + "id=" + id + ", description=" + description + ", amount=" + amount + ", image=" + image + ", createAt=" + createAt + ", debtor_IdDebtor=" + debtor_IdDebtor + ", idAccount=" + idAccount + ", interestRate=" + interestRate + ", due=" + due + ", status=" + status + ", deletedAt=" + deletedAt + ", isDeleted=" + isDeleted + ", debtTypeId=" + debtTypeId + ", totalAmount=" + totalAmount + ", debtIssuance=" + debtIssuance + '}';
    }

}
