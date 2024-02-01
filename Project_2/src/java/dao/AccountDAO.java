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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

                account.setId(idAccount);
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
                    int roleId = resultSet.getInt("role_id");

                    return new Account(id, username, roleId, password, name,
                            mobileNumber, emailAddress, address, isActive,
                            updatedAt, createdAt, avatarUrl, gender);
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
        String password = "123";
        Account acc = accDAO.findByUsernameAndPassword(username, password);
        if (acc != null) {
            System.out.println("Successful!");
        } else {
            System.out.println("Login Failed!");
            System.out.println(accDAO.isAdmin(username));
        }
        String email = "phuong2532005@gmail.com";
        String phone = "0123456789";
        System.out.println(accDAO.isEmailExist(email));
        System.out.println(accDAO.isEmailValid(email));
        System.out.println(accDAO.isPhoneExist(phone));
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
                int roleId = resultSet.getInt("role_id");

                //Account account = new Account();
                Account account;
                account = new Account(id, username, roleId, password, name,
                        mobileNumber, emailAddress, address, isActive,
                        updatedAt, createdAt, avatarUrl, gender);
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
                    int roleId = resultSet.getInt("role_id");
                    return roleId == 1; // Assuming 1 is the roleId for admin
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle exception appropriately
        }
        return false;
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
                    foundAccount.setId(rs.getInt("idAccount"));
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
                    foundAccount.setRole_id(rs.getInt("role_id"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Account updateAccount(Account acc) {
        if (acc == null || acc.getId()== 0) {
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
            ps.setInt(12, acc.getRole_id());
            ps.setInt(13, acc.getId());

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
                    foundAccount.setId(rs.getInt("idAccount"));
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
                    foundAccount.setRole_id(rs.getInt("role_id"));

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
    private static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@(.+)$";

    // Hàm kiểm tra định dạng email
    public boolean isEmailValid(String email) {
        Pattern pattern = Pattern.compile(EMAIL_REGEX);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
    
    
    public boolean isEmailExist(String email) {
        String query = "SELECT COUNT(*) FROM account WHERE Email_address = ?";
        try (PreparedStatement preparedStatement = db.getConnection().prepareStatement(query)) { 
            preparedStatement.setString(1, email);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt(1) > 0;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Xử lý ngoại lệ tùy theo yêu cầu của ứng dụng
        }

        return false;
    }
    
    public boolean isPhoneExist(String phone) {
        String query = "SELECT COUNT(*) FROM account WHERE Mobile_number = ?";
        try (PreparedStatement preparedStatement = db.getConnection().prepareStatement(query)) {
            
            preparedStatement.setString(1, phone);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt(1) > 0;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Xử lý ngoại lệ tùy theo yêu cầu của ứng dụng
        }

        return false;
    }
    
    public boolean checkUsernameAndEmailExists(String username, String emailAddress) {
    PreparedStatement pstmt = null;
    ResultSet rs = null;

        try {
            // Chuẩn bị câu truy vấn SQL
            String query = "SELECT * FROM account WHERE username = ? AND Email_address = ?";
            pstmt = db.getConnection().prepareStatement(query);
            pstmt.setString(1, username);
            pstmt.setString(2, emailAddress);

            // Thực hiện truy vấn
            rs = pstmt.executeQuery();

            // Nếu có kết quả trả về, người dùng hợp lệ
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            // Đóng tất cả các tài nguyên
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    
    public boolean updatePassword(String username, String newPassword) {
        
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            // Chuẩn bị câu truy vấn SQL
            String query = "UPDATE account SET Password = ? WHERE username = ?";
            pstmt = db.getConnection().prepareStatement(query);
            pstmt.setString(1, newPassword);
            pstmt.setString(2, username);

            // Thực hiện truy vấn
            int rowsAffected = pstmt.executeUpdate();

            // Nếu có ít nhất một hàng được cập nhật, trả về true
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            // Đóng tất cả các tài nguyên
            try {
                if (pstmt != null) pstmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
