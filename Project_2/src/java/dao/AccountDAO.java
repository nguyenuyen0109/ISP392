/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import dal.DBContext;
import model.Account;
import java.lang.System.Logger;
import java.lang.System.Logger.Level;
import java.security.Timestamp;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author minimonie
 */
public class AccountDAO {
    DBContext db;
    
    public AccountDAO() {
        db = DBContext.getInstance();
    }
    
   public Account findByUsernameAndPassword(String username, String password) {
       PreparedStatement pstmt = null;
       ResultSet rs = null;
        try {
            //System.out.println(db.getConnection());
            String sql = "SELECT * FROM account where username = ? and password  = ?";
             pstmt = db.getConnection().prepareStatement(sql);

            // Đặt các tham số cho PreparedStatement
            pstmt.setString(1, username);
            pstmt.setString(2, password);

            // Thực hiện truy vấn
            rs = pstmt.executeQuery();
            if (rs.next()) {
                Account account = new Account();
                // Lấy dữ liệu từ ResultSet và đặt vào đối tượng account\
                int idAccount = rs.getInt("idAccount");
                String un = rs.getString("username");
                String pass = rs.getString("password");
                String name = rs.getString("name");

                account.setIdAccount(idAccount);
                account.setUsername(un);
                account.setPassword(pass);
                account.setName(name);
                // Ví dụ: account.setUsername(rs.getString("username"));
                //        account.setPassword(rs.getString("password"));
                // Thêm các trường khác tương tự
                return account;

            }
        } catch (SQLException e) {
            System.out.println("Fail");
 
    }
        return null;
   }

    public boolean validateUser(String username, String password) {
        String query = "SELECT * FROM account WHERE username = ? AND Password = ?";
        try (PreparedStatement preparedStatement = db.getConnection().prepareStatement(query)) {
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                return resultSet.next(); // Nếu có kết quả, người dùng hợp lệ
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Account getAccountByUsername(String username) {
        String query = "SELECT * FROM account WHERE username = ?";
        try (PreparedStatement preparedStatement = db.getConnection().prepareStatement(query)) {
            preparedStatement.setString(1, username);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    int id = resultSet.getInt("idAccount");
                    
                    String avatarUrl = resultSet.getString("avatarUrl");
                    String mobileNumber = resultSet.getString("Mobile_number");
                    String emailAddress = resultSet.getString("Email_address");
                    String password = resultSet.getString("Password");
                    String name = resultSet.getString("Name");
                    String address = resultSet.getString("Address");
                    boolean isActive = resultSet.getBoolean("IsActive");
                    java.sql.Timestamp updatedAt = resultSet.getTimestamp("UpdateAt");
                    java.sql.Timestamp createdAt = resultSet.getTimestamp("CreateAt");
                    double amount = resultSet.getDouble("Amount");
                   // String avatarUrl = resultSet.getString("avatarUrl");
                    boolean gender = resultSet.getBoolean("gender");
                    int roleId = resultSet.getInt("role_idrole");

                    return new Account(id,username, amount,password,name,
                            mobileNumber,emailAddress,address,isActive, 
                            updatedAt,createdAt,avatarUrl,gender,
                            roleId);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
    
    public static void main(String[] args) {
        AccountDAO accDAO = new AccountDAO();
        String username = "minimonie";
        String password = "vZ7rb1lVvH0";
        Account acc = accDAO.findByUsernameAndPassword(username, password);
        if (acc!=null) {
            System.out.println("Successful!");
        } else {
            System.out.println("Login Failed!");

        int n = accDAO.insertAccount(new Account(0, "minimonie19", 0, "123", 
                "Nguyen Uyen", "01234567", "uyn@gmail.com", null, 
                true, null, null, null, true));
        if(n>0){
            System.out.println("success");
        }else{
            System.out.println("fail");
        }
      }
    
        
        System.out.println(accDAO.isAdmin(username));
    }

    public List<Account> getAllAccounts() {
        List<Account> accounts = new ArrayList<>();
        String query = "SELECT * FROM account";
        try (PreparedStatement preparedStatement = db.getConnection().prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                int id = resultSet.getInt("idAccount");
                String username = resultSet.getString("Username");
                double amount = resultSet.getDouble("Amount");
                String password = resultSet.getString("Password");
                String name = resultSet.getString("Name");
                String mobileNumber = resultSet.getString("Mobile_number");
                String emailAddress = resultSet.getString("Email_address");
                String address = resultSet.getString("Address");
                boolean isActive = resultSet.getBoolean("IsActive");
                java.sql.Timestamp updatedAt = resultSet.getTimestamp("UpdateAt");
                    java.sql.Timestamp createdAt = resultSet.getTimestamp("CreateAt");
                String avatarUrl = resultSet.getString("avatar_Url");
                boolean gender = resultSet.getBoolean("gender");
                int roleId = resultSet.getInt("role_idrole");

                //Account account = new Account();
                
                Account account;
                account = new Account(id,username, amount,password,name,
                            mobileNumber,emailAddress,address,isActive, 
                            updatedAt,createdAt,avatarUrl,gender,
                            roleId);
                accounts.add(account);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return accounts;
    }
    public int insertAccount(Account acc) {
        String username = acc.getUsername();
        int n = 0;
        if (usernameExists(username)) {
            System.out.println("Username already exists. Please choose a different username.");
        } else {
            String sql = "INSERT INTO account\n"
                    + "(Name,"
                    + "Mobile_number,"
                    + "Email_address,"
                    + "Username,"
                    + "Password,"
                    + "Address,"
                    + "IsActive,"
                    + "Amount,"
                    + "Avatar_Url,"
                    + "Gender,"
                   + "Role_idrole"
                    + ")"
                    + "VALUES(?,?,?,?,?,?,?,?,?,?,2);";

            try {
                PreparedStatement ps = db.getConnection().prepareStatement(sql);
                ps.setString(1, acc.getName());
                ps.setString(2, acc.getMobileNumber());
                ps.setString(3, acc.getEmailAddress());
                ps.setString(4, acc.getUsername());
                ps.setString(5, acc.getPassword());
                ps.setString(6, acc.getAddress());
                ps.setBoolean(7, acc.isIsActive());
                ps.setDouble(8, acc.getAmount());
                ps.setString(9, acc.getAvatarUrl());
                ps.setBoolean(10, acc.isGender());
              //  ps.setInt(11, acc.getRoleIdRole());
                n = ps.executeUpdate();
                System.out.println(sql);
            } catch (Exception ex) {
                System.out.println(ex);
            }
        }
        return n;
    }

    public boolean usernameExists(String username) {
        try {
            String sql = "SELECT COUNT(*) FROM account WHERE Username = ?";
            try {
                PreparedStatement ps = db.getConnection().prepareStatement(sql);
                ps.setString(1, username);
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        int count = rs.getInt(1);
                        return count > 0;
                    }
                } catch (Exception ex) {
                    System.out.println(ex);
                }
            } catch (Exception ex) {
                System.out.println(ex);
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
        // Handle the exception appropriately in your application
        return false;
    }
    

    public boolean isAdmin(String username) {
        String query = "SELECT role_idrole FROM account WHERE username = ?";
        try (PreparedStatement statement = db.getConnection().prepareStatement(query)) {
            statement.setString(1, username);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    int roleId = resultSet.getInt("role_idrole");
                    return roleId == 1; // Assuming 1 is the roleId for admin
                }

            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle exception appropriately
        }
        return false;
    }
    

    }
        
       



    

    
        
    

    
    
    
   






