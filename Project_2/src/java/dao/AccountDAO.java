/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import dal.DBContext;
import model.Account;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

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
//        finally {
//            // Đóng các tài nguyên
//            try {
//                if (rs != null) {
//                    rs.close();
//                }
//                if (pstmt != null) {
//                    pstmt.close();
//                }
//                if (db.getConnection() != null) {
//                    db.getConnection().close();
//                }
//            } catch (SQLException e) {
//                System.out.println("FAIL");
//            }
//        }

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

                    return new Account(id, username, amount, password, name,
                            mobileNumber, emailAddress, address, isActive,
                            updatedAt, createdAt, avatarUrl, gender,
                            roleId);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public List<Account> getAllAccounts() {

        List<Account> accounts = new ArrayList<>();
        String query = "SELECT * FROM account";
        try (PreparedStatement preparedStatement = db.getConnection().prepareStatement(query); ResultSet resultSet = preparedStatement.executeQuery()) {
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
                account = new Account(id, username, amount, password, name,
                        mobileNumber, emailAddress, address, isActive,
                        updatedAt, createdAt, avatarUrl, gender,
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
        // Giả sử bạn đã kết nối với cơ sở dữ liệu và có thể truy vấn thông tin người dùng
        // Đây là một ví dụ giả định
        String query = "SELECT role_idrole FROM users WHERE username = ?";
        // Thực hiện truy vấn
        try (PreparedStatement preparedStatement = db.getConnection().prepareStatement(query)) {
            preparedStatement.setString(1, username);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                return resultSet.next(); // Nếu có kết quả, người dùng hợp lệ
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Account getAccount(Account acc) {
        if (acc == null || acc.getUsername() == null || acc.getPassword() == null
                || acc.getUsername().isEmpty() || acc.getPassword().isEmpty()) {
            return null; // Or throw an IllegalArgumentException
        }

        String sql = "SELECT * FROM account WHERE username = ? AND password = ?";
        try (PreparedStatement ps = db.getConnection().prepareStatement(sql)) {
            ps.setString(1, acc.getUsername());
            ps.setString(2, acc.getPassword());

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Account foundAccount = new Account();
                    foundAccount.setIdAccount(rs.getInt("idAccount"));
                    foundAccount.setUsername(rs.getString("username"));
                    foundAccount.setAmount(rs.getDouble("amount"));
                    foundAccount.setPassword(rs.getString("password"));
                    foundAccount.setName(rs.getString("name"));
                    foundAccount.setMobileNumber(rs.getString("Mobile_number"));
                    foundAccount.setEmailAddress(rs.getString("Email_address"));
                    foundAccount.setAddress(rs.getString("address"));
                    foundAccount.setIsActive(rs.getBoolean("isActive"));
                    foundAccount.setUpdateAt(rs.getTimestamp("updateAt"));
                    foundAccount.setCreateAt(rs.getTimestamp("CreateAt"));
                    foundAccount.setAvatarUrl(rs.getString("avatar_Url"));
                    foundAccount.setGender(rs.getBoolean("gender"));
                    foundAccount.setRoleIdRole(rs.getInt("role_idrole"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Account updateAccount(Account acc) {
        if (acc == null || acc.getIdAccount() == 0) {
            return null; //
        }

        String sql = "UPDATE account SET username = ?, amount = ?, password = ?, name = ?, Mobile_number = ?, Email_address = ?, address = ?, IsActive = ?, UpdateAt = CURRENT_TIMESTAMP, CreateAt = ?, avatar_Url = ?, gender = ?, role_idrole = ? WHERE idAccount = ?";
        try (PreparedStatement ps = db.getConnection().prepareStatement(sql)) {
            ps.setString(1, acc.getUsername());
            ps.setDouble(2, acc.getAmount());
            ps.setString(3, acc.getPassword());
            ps.setString(4, acc.getName());
            ps.setString(5, acc.getMobileNumber());
            ps.setString(6, acc.getEmailAddress());
            ps.setString(7, acc.getAddress());
            ps.setBoolean(8, acc.isIsActive());
            // ps.setTimestamp(9, acc.getUpdateAt()); // updateAt is set to current timestamp in SQL          
            ps.setTimestamp(9, acc.getCreateAt());
            ps.setString(10, acc.getAvatarUrl());
            ps.setBoolean(11, acc.isGender());
            ps.setInt(12, acc.getRoleIdRole());
            ps.setInt(13, acc.getIdAccount());

            int affectedRows = ps.executeUpdate();
            if (affectedRows > 0) {
                return acc;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public Account getAccountById(int accountId) {
        String sql = "SELECT * FROM account WHERE idAccount = ?";
        try (PreparedStatement ps = db.getConnection().prepareStatement(sql)) {
            ps.setInt(1, accountId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Account foundAccount = new Account();
                    foundAccount.setIdAccount(rs.getInt("idAccount"));
                    foundAccount.setUsername(rs.getString("username"));
                    foundAccount.setAmount(rs.getDouble("amount"));
                    foundAccount.setPassword(rs.getString("password"));
                    foundAccount.setName(rs.getString("name"));
                    foundAccount.setMobileNumber(rs.getString("Mobile_number"));
                    foundAccount.setEmailAddress(rs.getString("Email_address"));
                    foundAccount.setAddress(rs.getString("address"));
                    foundAccount.setIsActive(rs.getBoolean("isActive"));
                    foundAccount.setUpdateAt(rs.getTimestamp("updateAt"));
                    foundAccount.setCreateAt(rs.getTimestamp("CreateAt"));
                    foundAccount.setAvatarUrl(rs.getString("avatar_Url"));
                    foundAccount.setGender(rs.getBoolean("gender"));
                    foundAccount.setRoleIdRole(rs.getInt("role_idrole"));

                    return foundAccount;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Consider better error handling
        }
        return null;
    }

    public void changeRole(String username, String role) {
        String sql = "UPDATE account SET role = ? WHERE username = ?";

        try (PreparedStatement stm = db.getConnection().prepareStatement(sql)) {
            stm.setString(1, role);
            stm.setString(2, username);

            int affectedRows = stm.executeUpdate();
            if (affectedRows == 0) {

                Logger.getLogger(AccountDAO.class.getName()).log(Level.INFO, "No account was updated.");
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void main(String[] args) {
        AccountDAO accDAO = new AccountDAO();
//        String username = "minimonie";
//        String password = "vZ7rb1lVvH0";
//        Account acc = accDAO.findByUsernameAndPassword(username, password);
//        if (acc != null) {
//            System.out.println("Successful!");
//        } else {
//            System.out.println("Login Failed!");
//            System.out.println(accDAO.isAdmin(username));
//        }
//        Account acc = new Account(0, "thuongday", 0, "thuongne", "Nguyen Thuong", "0328832923", "phuong2532005@gmail.com", null, true, null, null, null, true);
//        int n = accDAO.insertAccount(acc);
//        if(n>0){
//            System.out.println("sucess");
//        }else{
//            System.out.println("false");
//        }
    }
}
