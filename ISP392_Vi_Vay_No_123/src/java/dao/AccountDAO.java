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
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.sql.Timestamp;
import utils.Pagination;

/**
 *
 * @author minimonie
 */
public class AccountDAO {

    DBContext db;

    public AccountDAO() {
        db = DBContext.getInstance();
    }

    private static final int MIN_LENGTH = 8;
    private static final int MAX_LENGTH = 20;
    private static final Pattern HAS_NUMBER = Pattern.compile("\\d");
    private static final Pattern HAS_UPPER_CASE = Pattern.compile("[A-Z]");
    private static final Pattern HAS_LOWER_CASE = Pattern.compile("[a-z]");
    private static final Pattern HAS_SPECIAL_CHAR = Pattern.compile("[^a-zA-Z0-9]");

    public boolean validatePassword(String password) {
        if (password == null) {
            return false;
        }
        int length = password.length();
        if (length < MIN_LENGTH || length > MAX_LENGTH) {
            return false;
        }
        if (!HAS_NUMBER.matcher(password).find()) {
            return false;
        }
        if (!HAS_UPPER_CASE.matcher(password).find()) {
            return false;
        }
        if (!HAS_LOWER_CASE.matcher(password).find()) {
            return false;
        }
        if (!HAS_SPECIAL_CHAR.matcher(password).find()) {
            return false;
        }
        return true;
    }

    public int updateIsActive(String username, boolean isActive) {
        int n = 0;
        String sql = "UPDATE account SET isActive = ? WHERE username = ? ";
        try (PreparedStatement ps = db.getConnection().prepareStatement(sql)) {
            ps.setBoolean(1, isActive);
            ps.setString(2, username);
            n = ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
        return n;
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
                int id = rs.getInt("id");
                String un = rs.getString("username");
                String pass = rs.getString("password");
                String name = rs.getString("name");
                String email = rs.getString("emailAddress");
                String mobileNumber = rs.getString("mobileNumber");
                String address = rs.getString("address");
                String avatarURL = rs.getString("avatarURL");

                account.setId(id);
                account.setUsername(un);
                account.setPassword(pass);
                account.setName(name);
                account.setEmailAddress(email);
                account.setMobileNumber(mobileNumber);
                account.setAddress(address);
                account.setRole_id(rs.getInt("role_id"));
                account.setAvatarUrl(avatarURL);
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
                    int id = resultSet.getInt("id");
                    String password = resultSet.getString("password");
                    String name = resultSet.getString("name");
                    String mobileNumber = resultSet.getString("mobileNumber");
                    String emailAddress = resultSet.getString("emailAddress");
                    String address = resultSet.getString("address");
                    boolean isActive = resultSet.getBoolean("isActive");
                    java.sql.Timestamp updatedAt = resultSet.getTimestamp("updateAt");
                    java.sql.Timestamp createdAt = resultSet.getTimestamp("createAt");
                    String avatarUrl = resultSet.getString("avatarUrl");
                    boolean gender = resultSet.getBoolean("gender");
                    int roleId = resultSet.getInt("role_id");
                    java.sql.Timestamp deleteAt = resultSet.getTimestamp("deleteAt");
                    String token = resultSet.getString("token");
                    Date expiretime = resultSet.getDate("expiretime");
                    boolean isDeleted = resultSet.getBoolean("isDeleted");

                    return new Account(id, username, password, name, mobileNumber, emailAddress,
                            address, isActive, updatedAt, createdAt, avatarUrl, gender, roleId, deleteAt, isDeleted, token, expiretime);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public List<Account> haveMostDebtor() {
        List<Account> accounts = new ArrayList<>();
        String query = "SELECT * from account a\n"
                + "inner join debtor d On a.id=d.account_id\n"
                + "group by a.id, a.username\n"
                + "having count(d.id)=(\n"
                + "    Select MAX(totalDebtor)\n"
                + "    from (\n"
                + "        select count(d.id) as totalDebtor\n"
                + "        from account a\n"
                + "        inner join debtor d ON a.id = d.account_id\n"
                + "        where a.isActive = true\n"
                + "        group by a.id, a.username)TotalDebtor);";
        try (PreparedStatement preparedStatement = db.getConnection().prepareStatement(query)) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String username = resultSet.getString("username");
                    String password = resultSet.getString("password");
                    String name = resultSet.getString("name");
                    String mobileNumber = resultSet.getString("mobileNumber");
                    String emailAddress = resultSet.getString("emailAddress");
                    String address = resultSet.getString("address");
                    boolean isActive = resultSet.getBoolean("isActive");
                    java.sql.Timestamp updatedAt = resultSet.getTimestamp("updateAt");
                    java.sql.Timestamp createdAt = resultSet.getTimestamp("createAt");
                    String avatarUrl = resultSet.getString("avatarUrl");
                    boolean gender = resultSet.getBoolean("gender");
                    int roleId = resultSet.getInt("role_id");
                    java.sql.Timestamp deleteAt = resultSet.getTimestamp("deleteAt");
                    String token = resultSet.getString("token");
                    Date expiretime = resultSet.getDate("expiretime");
                    boolean isDeleted = resultSet.getBoolean("isDeleted");

                    Account account = new Account(id, username, password, name, mobileNumber, emailAddress,
                            address, isActive, updatedAt, createdAt, avatarUrl, gender, roleId, deleteAt, isDeleted, token, expiretime);
                    accounts.add(account);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return accounts;
    }

    public Account getAccountByEmail(String email) {
        String query = "SELECT * FROM account WHERE emailAddress = ?";
        try (PreparedStatement preparedStatement = db.getConnection().prepareStatement(query)) {
            preparedStatement.setString(1, email);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    int id = resultSet.getInt("id");

                    String avatarUrl = resultSet.getString("avatarUrl");
                    String mobileNumber = resultSet.getString("mobileNumber");
                    String emailAddress = resultSet.getString("emailAddress");
                    String password = resultSet.getString("Password");
                    String name = resultSet.getString("Name");
                    String address = resultSet.getString("Address");
                    boolean isActive = resultSet.getBoolean("IsActive");
                    java.sql.Timestamp updatedAt = resultSet.getTimestamp("UpdateAt");
                    java.sql.Timestamp createdAt = resultSet.getTimestamp("CreateAt");
                    boolean gender = resultSet.getBoolean("gender");
                    int roleId = resultSet.getInt("role_id");
                    String username = resultSet.getString("username");
                    return new Account(id, username, password, name,
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

    public String checkToken(String email, String token) {
        String query = "SELECT emailAddress FROM account WHERE emailAddress = ? and token = ? and  expiretime > CURRENT_TIME()";
        try (PreparedStatement preparedStatement = db.getConnection().prepareStatement(query)) {
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, token);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    System.out.println("có email");
                    return resultSet.getString("emailAddress");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public int totalAccount() {
        int totalRecord = 0;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            String sql = "SELECT COUNT(*) FROM account WHERE isActive = true ";
            statement = db.getConnection().prepareStatement(sql);
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                totalRecord = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return totalRecord;
    }

    public List<Account> getAllAccounts(int page) {
        List<Account> accounts = new ArrayList<>();
        String query = "SELECT * FROM account WHERE isActive = true LIMIT ? OFFSET ? ";
        try (PreparedStatement preparedStatement = db.getConnection().prepareStatement(query)) {
            int offset = (page - 1) * Pagination.RECORD_PER_PAGE;
            preparedStatement.setInt(1, Pagination.RECORD_PER_PAGE);
            preparedStatement.setInt(2, offset);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String username = resultSet.getString("username");
                    String password = resultSet.getString("password");
                    String name = resultSet.getString("name");
                    String mobileNumber = resultSet.getString("mobileNumber");
                    String emailAddress = resultSet.getString("emailAddress");
                    String address = resultSet.getString("address");
                    boolean isActive = resultSet.getBoolean("isActive");
                    java.sql.Timestamp updatedAt = resultSet.getTimestamp("updateAt");
                    java.sql.Timestamp createdAt = resultSet.getTimestamp("createAt");
                    String avatarUrl = resultSet.getString("avatarUrl");
                    boolean gender = resultSet.getBoolean("gender");
                    int roleId = resultSet.getInt("role_id");
                    java.sql.Timestamp deleteAt = resultSet.getTimestamp("deleteAt");
                    String token = resultSet.getString("token");
                    Date expiretime = resultSet.getDate("expiretime");
                    boolean isDeleted = resultSet.getBoolean("isDeleted");

                    Account account = new Account(id, username, password, name, mobileNumber, emailAddress,
                            address, isActive, updatedAt, createdAt, avatarUrl, gender, roleId, deleteAt, isDeleted, token, expiretime);
                    accounts.add(account);
                }
            } catch (Exception e) {
                e.printStackTrace();
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
                    + "mobileNumber,"
                    + "emailAddress,"
                    + "Username,"
                    + "Password,"
                    + "Address,"
                    + "IsActive,"
                    + "avatarUrl,"
                    + "Gender,"
                    + "role_id"
                    + ")"
                    + "VALUES(?,?,?,?,?,?,?,?,?,2);";

            try {
                PreparedStatement ps = db.getConnection().prepareStatement(sql);
                ps.setString(1, acc.getName());
                ps.setString(2, acc.getMobileNumber());
                ps.setString(3, acc.getEmailAddress());
                ps.setString(4, acc.getUsername());
                ps.setString(5, acc.getPassword());
                ps.setString(6, acc.getAddress());
                ps.setBoolean(7, acc.isIsActive());
                ps.setString(8, acc.getAvatarUrl());
                ps.setBoolean(9, acc.isGender());
                n = ps.executeUpdate();
                System.out.println(sql);
            } catch (Exception ex) {
                System.out.println(ex);
            }
        }
        return n;
    }

    public void insertToken(int id, String token) {
        Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());

        // Add 30 minutes to the current timestamp
        long millisecondsIn30Minutes = 30 * 60 * 1000;
        Timestamp next30MinutesTimestamp = new Timestamp(currentTimestamp.getTime() + millisecondsIn30Minutes);
        String sql = "update account set \n"
                + " token = '" + token + "'" + ", expiretime = ?"
                + " where id = " + id;
        System.out.println(sql);
        try {
            PreparedStatement ps = db.getConnection().prepareStatement(sql);
            ps.setTimestamp(1, next30MinutesTimestamp);
            ps.executeUpdate();
            System.out.println(sql);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void ResettToken(int id) {
        String sql = "update account set \n"
                + " token = ''" + ""
                + " where id = " + id;
        System.out.println(sql);
        try {
            PreparedStatement ps = db.getConnection().prepareStatement(sql);
            ps.executeUpdate();
            System.out.println(sql);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
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
        String query = "SELECT role_id FROM account WHERE username = ?";
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
                    foundAccount.setId(rs.getInt("id"));
                    foundAccount.setUsername(rs.getString("username"));
                    foundAccount.setPassword(rs.getString("password"));
                    foundAccount.setName(rs.getString("name"));
                    foundAccount.setMobileNumber(rs.getString("mobileNumber"));
                    foundAccount.setEmailAddress(rs.getString("emailAddress"));
                    foundAccount.setAddress(rs.getString("address"));
                    foundAccount.setIsActive(rs.getBoolean("isActive"));
                    foundAccount.setUpdateAt(rs.getTimestamp("updateAt"));
                    foundAccount.setCreateAt(rs.getTimestamp("CreateAt"));
                    foundAccount.setAvatarUrl(rs.getString("avatarUrl"));
                    foundAccount.setGender(rs.getBoolean("gender"));
                    foundAccount.setRole_id(rs.getInt("role_id"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

//    public Account updateAccount(Account acc) {
//        if (acc == null || acc.getId() == 0) {
//            return null; //
//        }
//
//        String sql = "UPDATE account SET username = ?, password = ?, name = ?, mobileNumber = ?, emailAddress = ?, address = ?, IsActive = ?, UpdateAt = CURRENT_TIMESTAMP,  avatarUrl = ?, gender = ?, role_id = ? WHERE id = ?";
//        try (PreparedStatement ps = db.getConnection().prepareStatement(sql)) {
//            ps.setString(1, acc.getUsername());
//            ps.setString(2, acc.getPassword());
//            ps.setString(3, acc.getName());
//            ps.setString(4, acc.getMobileNumber());
//            ps.setString(5, acc.getEmailAddress());
//            ps.setString(6, acc.getAddress());
//            ps.setBoolean(7, acc.isIsActive());        
//            ps.setString(9, acc.getAvatarUrl());
//            ps.setBoolean(10, acc.isGender());
//            ps.setInt(11, acc.getRole_id());
//            ps.setInt(12, acc.getId());
//
//            int affectedRows = ps.executeUpdate();
//            if (affectedRows > 0) {
//                return acc;
//            }
//        } catch (SQLException e) {
//            System.out.println(e);
//        }
//        return null;
//    }
    public Account updateAccount(Account acc) {
        if (acc == null || acc.getId() == 0) {
            return null; //
        }

        String sql = "UPDATE account SET name = ?, mobileNumber = ?, emailAddress = ?, address = ?, avatarUrl = ?, updateAt= ?  WHERE id = ?";
        try (PreparedStatement ps = db.getConnection().prepareStatement(sql)) {
            ps.setString(1, acc.getName());
            ps.setString(2, acc.getMobileNumber());
            ps.setString(3, acc.getEmailAddress());
            ps.setString(4, acc.getAddress());
            ps.setString(5, acc.getAvatarUrl());
            ps.setInt(7, acc.getId());
            // Lấy thời gian hiện tại và chuyển đổi thành Timestamp
            Timestamp currentTimestamp = new Timestamp(new Date().getTime());
            ps.setTimestamp(6, currentTimestamp);

            int affectedRows = ps.executeUpdate();
            if (affectedRows > 0) {
                return acc;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return acc;
    }

    public Account getAccountById(int accountId) {
        String sql = "SELECT * FROM account WHERE id = ?";
        try (PreparedStatement ps = db.getConnection().prepareStatement(sql)) {
            ps.setInt(1, accountId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Account foundAccount = new Account();
                    foundAccount.setId(rs.getInt("id"));
                    foundAccount.setUsername(rs.getString("username"));
                    foundAccount.setPassword(rs.getString("password"));
                    foundAccount.setName(rs.getString("name"));
                    foundAccount.setMobileNumber(rs.getString("mobileNumber"));
                    foundAccount.setEmailAddress(rs.getString("emailAddress"));
                    foundAccount.setAddress(rs.getString("address"));
                    foundAccount.setIsActive(rs.getBoolean("isActive"));
                    foundAccount.setUpdateAt(rs.getTimestamp("updateAt"));
                    foundAccount.setCreateAt(rs.getTimestamp("CreateAt"));
                    foundAccount.setAvatarUrl(rs.getString("avatarUrl"));
                    foundAccount.setGender(rs.getBoolean("gender"));
                    foundAccount.setRole_id(rs.getInt("role_id"));

                    return foundAccount;
                }
            }
        } catch (SQLException e) {
            // Consider better error handling

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
        String query = "SELECT COUNT(*) FROM account WHERE emailAddress = ?";
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
        String query = "SELECT COUNT(*) FROM account WHERE mobileNumber = ?";
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

    public static boolean isValidPhoneNumber(String phoneNumber) {
        // Regex for a valid phone number
        String regex = "^[0-9]{1}[0-9\\-\\s]{9,14}$";

        // Compile the ReGex
        Pattern p = Pattern.compile(regex);

        // If the phone number is empty
        // return false
        if (phoneNumber == null) {
            return false;
        }

        // Pattern class contains matcher() method
        // to find matching between given phone number
        // and regular expression
        return p.matcher(phoneNumber).matches();
    }

    public boolean checkUsernameAndEmailExists(String username, String emailAddress) {
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            // Chuẩn bị câu truy vấn SQL
            String query = "SELECT * FROM account WHERE username = ? or emailAddress = ?";
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
                if (pstmt != null) {
                    pstmt.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean isStaff(String username) {
        String query = "SELECT role_id FROM account WHERE username = ?";
        try (PreparedStatement statement = db.getConnection().prepareStatement(query)) {
            statement.setString(1, username);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    int roleId = resultSet.getInt("role_id");
                    return roleId == 3; // Giả sử 3 là roleId cho staff
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Xử lý ngoại lệ tùy theo yêu cầu của ứng dụng
        }
        return false;
    }

    public boolean editProfileByUsername(String username, Account updatedAccount) {
        if (username == null || updatedAccount == null) {
            return false;
        }
        String sql = "UPDATE account SET "
                + "Name = ?, "
                + "mobileNumber = ?, "
                + "updateAt = ?,"
                + "Address = ?, "
                + "avatarUrl = ?, "
                + "WHERE username = ?";

        try (PreparedStatement ps = db.getConnection().prepareStatement(sql)) {
            ps.setString(1, updatedAccount.getName());
            ps.setString(2, updatedAccount.getMobileNumber());
            Timestamp currentTimestamp = new Timestamp(new Date().getTime());
            ps.setTimestamp(3, currentTimestamp);
            ps.setString(4, updatedAccount.getAddress());
            ps.setString(5, updatedAccount.getAvatarUrl());
            ps.setBoolean(6, updatedAccount.isGender());
            ps.setString(7, username);

            int affectedRows = ps.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public String getPasswordById(int accountId) {
        String password = null;
        String sql = "SELECT password FROM account WHERE id = ?";
        try (PreparedStatement ps = db.getConnection().prepareStatement(sql)) {
            ps.setInt(1, accountId);
            ResultSet rs = ps.executeQuery();
            // Lấy kết quả
            if (rs.next()) {
                password = rs.getString("password");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return password;
    }

    public String getEmailAddressById(int accountId) {
        String email = null;
        String sql = "SELECT emailAddress FROM account WHERE id = ?";
        try (PreparedStatement ps = db.getConnection().prepareStatement(sql)) {
            ps.setInt(1, accountId);
            ResultSet rs = ps.executeQuery();
            // Lấy kết quả
            if (rs.next()) {
                email = rs.getString("emailAddress");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return email;
    }
    public int totalRecordIsActive(boolean status) {
        int totalRecord = 0;
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        try {
            String sql = "SELECT COUNT(*) AS total FROM account WHERE isActive = ?";
            ps = db.getConnection().prepareStatement(sql);
            ps.setBoolean(1, status);
            resultSet = ps.executeQuery();

            if (resultSet.next()) {
                totalRecord = resultSet.getInt("total");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Đóng resultSet và preparedStatement tại đây
        }

        return totalRecord;
    }

    public List<Account> filterAccount(boolean status, int page) {
        List<Account> accounts = new ArrayList<>();

        String sql = "select * from account where isActive = ? LIMIT ? OFFSET ?";
        try (PreparedStatement preparedStatement = db.getConnection().prepareStatement(sql)) {
            int offset = (page - 1) * Pagination.RECORD_PER_PAGE;
            preparedStatement.setBoolean(1, status);
            preparedStatement.setInt(2, Pagination.RECORD_PER_PAGE);
            preparedStatement.setInt(3, offset);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String username = resultSet.getString("username");
                    String password = resultSet.getString("password");
                    String name = resultSet.getString("name");
                    String mobileNumber = resultSet.getString("mobileNumber");
                    String emailAddress = resultSet.getString("emailAddress");
                    String address = resultSet.getString("address");
                    java.sql.Timestamp updatedAt = resultSet.getTimestamp("updateAt");
                    java.sql.Timestamp createdAt = resultSet.getTimestamp("createAt");
                    String avatarUrl = resultSet.getString("avatarUrl");
                    boolean gender = resultSet.getBoolean("gender");
                    int roleId = resultSet.getInt("role_id");
                    java.sql.Timestamp deleteAt = resultSet.getTimestamp("deleteAt");
                    String token = resultSet.getString("token");
                    Date expiretime = resultSet.getDate("expiretime");
                    boolean isDeleted = resultSet.getBoolean("isDeleted");

                    Account account = new Account(id, username, password, name, mobileNumber, emailAddress,
                            address, status, updatedAt, createdAt, avatarUrl, gender, roleId, deleteAt, isDeleted, token, expiretime);
                    accounts.add(account);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return accounts;
    }

    public List<Account> accountOldest(int page) {
        return accountCreate(page, " createAt ASC ");

    }

    public List<Account> accountNewest(int page) {
        return accountCreate(page, " createAt DESC ");
    }

    private List<Account> accountCreate(int page, String orderBy) {
        List<Account> accounts = new ArrayList<>();

        String sql = "select * from account WHERE isActive = true order by " + orderBy + " LIMIT ? OFFSET ? ";
        try (PreparedStatement preparedStatement = db.getConnection().prepareStatement(sql)) {
            int offset = (page - 1) * Pagination.RECORD_PER_PAGE;
            preparedStatement.setInt(1, Pagination.RECORD_PER_PAGE);
            preparedStatement.setInt(2, offset);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String username = resultSet.getString("username");
                    String password = resultSet.getString("password");
                    String name = resultSet.getString("name");
                    String mobileNumber = resultSet.getString("mobileNumber");
                    String emailAddress = resultSet.getString("emailAddress");
                    String address = resultSet.getString("address");
                    boolean isActive = resultSet.getBoolean("isActive");
                    java.sql.Timestamp updatedAt = resultSet.getTimestamp("updateAt");
                    java.sql.Timestamp createdAt = resultSet.getTimestamp("createAt");
                    String avatarUrl = resultSet.getString("avatarUrl");
                    boolean gender = resultSet.getBoolean("gender");
                    int roleId = resultSet.getInt("role_id");
                    java.sql.Timestamp deleteAt = resultSet.getTimestamp("deleteAt");
                    String token = resultSet.getString("token");
                    Date expiretime = resultSet.getDate("expiretime");
                    boolean isDeleted = resultSet.getBoolean("isDeleted");

                    Account account = new Account(id, username, password, name, mobileNumber, emailAddress,
                            address, isActive, updatedAt, createdAt, avatarUrl, gender, roleId, deleteAt, isDeleted, token, expiretime);
                    accounts.add(account);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return accounts;
    }
    
    public int totalRecordSearchUsername(String input) {
        int totalRecord = 0;
        PreparedStatement ps = null;
        ResultSet resultSet = null;

        try {
            String sql = "SELECT COUNT(*) AS total FROM account where username like ? or "
                    + "name like ? or mobileNumber like ? or emailAddress like ? or createAt like ? ";
            ps = db.getConnection().prepareStatement(sql);
            ps.setString(1, "%"+input+"%");
            ps.setString(2, "%"+input+"%");
            ps.setString(3, "%"+input+"%");
            ps.setString(4, "%"+input+"%");
            ps.setString(5, "%"+input+"%");
            resultSet = ps.executeQuery();
            if (resultSet.next()) {
                totalRecord = resultSet.getInt("total");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Đóng resultSet và preparedStatement tại đây
        }

        return totalRecord;
    }

    public List<Account> getListByUsername(String input, int page) {
        List<Account> accounts = new ArrayList<>();
        String sql = "select * from account where username like ? or "
                + "name like ? or mobileNumber like ? or emailAddress like ? or createAt like ? order by id LIMIT ? OFFSET ?";
        try (PreparedStatement preparedStatement = db.getConnection().prepareStatement(sql)) {
            int offset = (page - 1) * Pagination.RECORD_PER_PAGE;
            preparedStatement.setString(1, "%"+input+"%");
            preparedStatement.setString(2, "%"+input+"%");
            preparedStatement.setString(3, "%"+input+"%");
            preparedStatement.setString(4, "%"+input+"%");
            preparedStatement.setString(5, "%"+input+"%");
            preparedStatement.setInt(6, Pagination.RECORD_PER_PAGE);
            preparedStatement.setInt(7, offset);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String username = resultSet.getString("username");
                    String password = resultSet.getString("password");
                    String name = resultSet.getString("name");
                    String mobileNumber = resultSet.getString("mobileNumber");
                    String emailAddress = resultSet.getString("emailAddress");
                    String address = resultSet.getString("address");
                    boolean isActive = resultSet.getBoolean("isActive");
                    java.sql.Timestamp updatedAt = resultSet.getTimestamp("updateAt");
                    java.sql.Timestamp createdAt = resultSet.getTimestamp("createAt");
                    String avatarUrl = resultSet.getString("avatarUrl");
                    boolean gender = resultSet.getBoolean("gender");
                    int roleId = resultSet.getInt("role_id");
                    java.sql.Timestamp deleteAt = resultSet.getTimestamp("deleteAt");
                    String token = resultSet.getString("token");
                    Date expiretime = resultSet.getDate("expiretime");
                    boolean isDeleted = resultSet.getBoolean("isDeleted");

                    Account account = new Account(id, username, password, name, mobileNumber, emailAddress,
                            address, isActive, updatedAt, createdAt, avatarUrl, gender, roleId, deleteAt, isDeleted, token, expiretime);
                    accounts.add(account);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return accounts;
    }

}
