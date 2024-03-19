/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.Connection;
import dal.DBContext;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.Timestamp;
import java.util.Collections;
import java.util.Comparator;
import model.DebtDetail;
import model.Debtor;
import utils.Pagination;

/**
 *
 * @author ADMIN
 */
public class DebtorDAO {

    DBContext db;

    public DebtorDAO() {
        db = DBContext.getInstance();
    }

    public boolean addDebtor(Debtor debtor) {
        String sql = "INSERT INTO debtor "
                + "(name, address, phone, email,totalDebt,account_id ) VALUES (?, ?, ?, ?, ?, ?)";
        Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis()); // Tạo một biến Timestamp duy nhất

        try (PreparedStatement statement = db.getConnection().prepareStatement(sql)) {
            statement.setString(1, debtor.getName());
            statement.setString(2, debtor.getAddress());
            statement.setString(3, debtor.getPhone());
            statement.setString(4, debtor.getEmail());
            statement.setDouble(5, debtor.getTotalDebt());
            statement.setInt(6, debtor.getAccount_id());
            int rowsInserted = statement.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

    }

    public boolean updateDebtor(Debtor debtor) {
        boolean updated = false;
        String query = "UPDATE debtor SET name=?,"
                + " address=?,"
                + " phone=?,"
                + " email=?"
                + " WHERE id=?";
        try (PreparedStatement preparedStatement = db.getConnection().prepareStatement(query)) {
            preparedStatement.setString(1, debtor.getName());
            preparedStatement.setString(2, debtor.getAddress());
            preparedStatement.setString(3, debtor.getPhone());
            preparedStatement.setString(4, debtor.getEmail());
            preparedStatement.setInt(5, debtor.getId());

            int rowsUpdated = preparedStatement.executeUpdate();
            updated = rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return updated;
    }

    public List<Debtor> getAllDebtors(int account_id) {
        List<Debtor> debtors = new ArrayList<>();
        String query = "SELECT * FROM debtor where account_id = ? and isDeleted = false";

        try (PreparedStatement preparedStatement = db.getConnection().prepareStatement(query)) {
            preparedStatement.setInt(1, account_id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String name = resultSet.getString("name");
                    String address = resultSet.getString("address");
                    String phone = resultSet.getString("phone");
                    String email = resultSet.getString("email");
                    double totalDebt = resultSet.getDouble("totalDebt");
                    java.sql.Timestamp createdAt = resultSet.getTimestamp("createdAt");
                    java.sql.Timestamp updatedAt = resultSet.getTimestamp("updatedAt");

//                int account_id = resultSet.getInt("account_id");
                    int creditor_account_id = resultSet.getInt("creditor_account_id");
                    //Account account = new Account();
                    Debtor debtor;
                    debtor = new Debtor(id, name, address, phone, email, totalDebt, createdAt, updatedAt, account_id, creditor_account_id);
                    debtors.add(debtor);

                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return debtors;
    }

    public int findTotalRecordBySearch(int accountid, String search) {
        int totalRecord = 0;
        String sql = "SELECT COUNT(*) FROM debtor WHERE account_id = ? and isDeleted = false and (address like ? or "
                + "                         name like ? or "
                + "                         email like ? or "
                + "                         phone like ?)";
        try (PreparedStatement preparedStatement = db.getConnection().prepareStatement(sql)) {
            search = "%" + search + "%";
            preparedStatement.setInt(1, accountid);
            preparedStatement.setString(2, search);
            preparedStatement.setString(3, search);
            preparedStatement.setString(4, search);
            preparedStatement.setString(5, search);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    totalRecord = resultSet.getInt(1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return totalRecord;
    }

    public List<Debtor> findByPageBySearch(int accountid, int pageNumber, String search) {
        List<Debtor> debtorList = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet resultSet = null;

        try {
            int offset = (pageNumber - 1) * Pagination.RECORD_PER_PAGE;
            String sql = "SELECT * FROM debtor WHERE account_id = ? and isDeleted = false and (address like ? or "
                    + "                         name like ? or "
                    + "                         email like ? or "
                    + "                         phone like ?) ORDER BY id LIMIT ? OFFSET ?";
            ps = db.getConnection().prepareStatement(sql);
            search = "%" + search + "%";
            ps.setInt(1, accountid);
            ps.setString(2, search);
            ps.setString(3, search);
            ps.setString(4, search);
            ps.setString(5, search);
            ps.setInt(6, Pagination.RECORD_PER_PAGE);
            ps.setInt(7, offset);
            resultSet = ps.executeQuery();

            while (resultSet.next()) {
                Debtor debtor = new Debtor();
                debtor.setId(resultSet.getInt("id"));
                debtor.setName(resultSet.getString("name"));
                debtor.setAddress(resultSet.getString("address"));
                debtor.setPhone(resultSet.getString("phone"));
                debtor.setEmail(resultSet.getString("email"));
                debtor.setTotalDebt(resultSet.getDouble("totalDebt"));
                debtor.setCreatedAt(resultSet.getTimestamp("createdAt"));
                debtor.setUpdatedAt(resultSet.getTimestamp("updatedAt"));
                debtor.setAccount_id(accountid);
                debtorList.add(debtor);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (ps != null) {
                    ps.close();
                }
                // Không cần đóng connection ở đây để sử dụng lại
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return debtorList;
    }

    public int findTotalRecord(int accountid) {
        int totalRecord = 0;
        String sql = "SELECT COUNT(*) FROM debtor where account_id = ? and isDeleted = false";
        try (PreparedStatement preparedStatement = db.getConnection().prepareStatement(sql)) {
            preparedStatement.setInt(1, accountid);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    totalRecord = resultSet.getInt(1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return totalRecord;
    }

    public List<Debtor> findByPage(int accountid, int pageNumber) {
        List<Debtor> debtorList = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet resultSet = null;

        try {
            int offset = (pageNumber - 1) * Pagination.RECORD_PER_PAGE;
            String sql = "SELECT * FROM debtor WHERE account_id = ? and isDeleted = false ORDER BY id LIMIT ? OFFSET ?";
            ps = db.getConnection().prepareStatement(sql);
            ps.setInt(1, accountid);
            ps.setInt(2, Pagination.RECORD_PER_PAGE);
            ps.setInt(3, offset);
            resultSet = ps.executeQuery();

            while (resultSet.next()) {
                Debtor debtor = new Debtor();
                debtor.setId(resultSet.getInt("id"));
                debtor.setName(resultSet.getString("name"));
                debtor.setAddress(resultSet.getString("address"));
                debtor.setPhone(resultSet.getString("phone"));
                debtor.setEmail(resultSet.getString("email"));
                debtor.setTotalDebt(resultSet.getDouble("totalDebt"));
                debtor.setCreatedAt(resultSet.getTimestamp("createdAt"));
                debtor.setUpdatedAt(resultSet.getTimestamp("updatedAt"));
                debtor.setAccount_id(accountid);
                debtorList.add(debtor);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (ps != null) {
                    ps.close();
                }
                // Không cần đóng connection ở đây để sử dụng lại
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return debtorList;
    }

    public List<Debtor> findByPageAndSortByNewest(int accountid, int page) {
        List<Debtor> debtorList = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet resultSet = null;

        try {
            int offset = (page - 1) * Pagination.RECORD_PER_PAGE;
            String sql = "SELECT * FROM debtor WHERE account_id = ? and isDeleted = false ORDER BY createdAt DESC LIMIT ? OFFSET ?";
            ps = db.getConnection().prepareStatement(sql);
            ps.setInt(1, accountid);
            ps.setInt(2, Pagination.RECORD_PER_PAGE);
            ps.setInt(3, offset);
            resultSet = ps.executeQuery();

            while (resultSet.next()) {
                Debtor debtor = new Debtor();
                debtor.setId(resultSet.getInt("id"));
                debtor.setName(resultSet.getString("name"));
                debtor.setAddress(resultSet.getString("address"));
                debtor.setPhone(resultSet.getString("phone"));
                debtor.setEmail(resultSet.getString("email"));
                debtor.setTotalDebt(resultSet.getDouble("totalDebt"));
                debtor.setCreatedAt(resultSet.getTimestamp("createdAt"));
                debtor.setUpdatedAt(resultSet.getTimestamp("updatedAt"));
                debtor.setAccount_id(accountid);
                debtorList.add(debtor);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (ps != null) {
                    ps.close();
                }
                // Không cần đóng connection ở đây để sử dụng lại
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return debtorList;
    }

    public List<Debtor> findByPageAndSortByOldest(int accountid, int page) {
        List<Debtor> debtorList = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet resultSet = null;

        try {
            int offset = (page - 1) * Pagination.RECORD_PER_PAGE;
            String sql = "SELECT * FROM debtor WHERE account_id = ? and isDeleted = false ORDER BY createdAt ASC LIMIT ? OFFSET ?";
            ps = db.getConnection().prepareStatement(sql);
            ps.setInt(1, accountid);
            ps.setInt(2, Pagination.RECORD_PER_PAGE);
            ps.setInt(3, offset);
            resultSet = ps.executeQuery();

            while (resultSet.next()) {
                Debtor debtor = new Debtor();
                debtor.setId(resultSet.getInt("id"));
                debtor.setName(resultSet.getString("name"));
                debtor.setAddress(resultSet.getString("address"));
                debtor.setPhone(resultSet.getString("phone"));
                debtor.setEmail(resultSet.getString("email"));
                debtor.setTotalDebt(resultSet.getDouble("totalDebt"));
                debtor.setCreatedAt(resultSet.getTimestamp("createdAt"));
                debtor.setUpdatedAt(resultSet.getTimestamp("updatedAt"));
                debtor.setAccount_id(accountid);
                debtorList.add(debtor);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (ps != null) {
                    ps.close();
                }
                // Không cần đóng connection ở đây để sử dụng lại
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return debtorList;
    }

    public List<Debtor> findByPageAndSortDebtByAmountHighLow(int accountid, int page) {
        List<Debtor> debtorList = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet resultSet = null;

        try {
            int offset = (page - 1) * Pagination.RECORD_PER_PAGE;
            String sql = "SELECT * FROM debtor WHERE account_id = ? and isDeleted = false ORDER BY totalDebt DESC LIMIT ? OFFSET ?";
            ps = db.getConnection().prepareStatement(sql);
            ps.setInt(1, accountid);
            ps.setInt(2, Pagination.RECORD_PER_PAGE);
            ps.setInt(3, offset);
            resultSet = ps.executeQuery();

            while (resultSet.next()) {
                Debtor debtor = new Debtor();
                debtor.setId(resultSet.getInt("id"));
                debtor.setName(resultSet.getString("name"));
                debtor.setAddress(resultSet.getString("address"));
                debtor.setPhone(resultSet.getString("phone"));
                debtor.setEmail(resultSet.getString("email"));
                debtor.setTotalDebt(resultSet.getDouble("totalDebt"));
                debtor.setCreatedAt(resultSet.getTimestamp("createdAt"));
                debtor.setUpdatedAt(resultSet.getTimestamp("updatedAt"));
                debtor.setAccount_id(accountid);
                debtorList.add(debtor);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (ps != null) {
                    ps.close();
                }
                // Không cần đóng connection ở đây để sử dụng lại
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return debtorList;
    }

    public List<Debtor> findByPageAndSortDebtByAmountLowHigh(int accountid, int page) {
        List<Debtor> debtorList = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet resultSet = null;

        try {
            int offset = (page - 1) * Pagination.RECORD_PER_PAGE;
            String sql = "SELECT * FROM debtor WHERE account_id = ? and isDeleted = false ORDER BY totalDebt ASC LIMIT ? OFFSET ?";
            ps = db.getConnection().prepareStatement(sql);
            ps.setInt(1, accountid);
            ps.setInt(2, Pagination.RECORD_PER_PAGE);
            ps.setInt(3, offset);
            resultSet = ps.executeQuery();

            while (resultSet.next()) {
                Debtor debtor = new Debtor();
                debtor.setId(resultSet.getInt("id"));
                debtor.setName(resultSet.getString("name"));
                debtor.setAddress(resultSet.getString("address"));
                debtor.setPhone(resultSet.getString("phone"));
                debtor.setEmail(resultSet.getString("email"));
                debtor.setTotalDebt(resultSet.getDouble("totalDebt"));
                debtor.setCreatedAt(resultSet.getTimestamp("createdAt"));
                debtor.setUpdatedAt(resultSet.getTimestamp("updatedAt"));
                debtor.setAccount_id(accountid);
                debtorList.add(debtor);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (ps != null) {
                    ps.close();
                }
                // Không cần đóng connection ở đây để sử dụng lại
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return debtorList;
    }

    public int findTotalRecordByName(int accountid, String keyword) {
        int totalRecord = 0;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            String sql = "SELECT COUNT(*) FROM debtor WHERE account_id = ? AND name LIKE ? and isDeleted = false";
            statement = db.getConnection().prepareStatement(sql);
            statement.setInt(1, accountid);
            statement.setString(2, "%" + keyword + "%");
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                totalRecord = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return totalRecord;
    }

    public List<Debtor> findByPageByName(int accountid, String keyword, int page) {
        List<Debtor> debtorList = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet resultSet = null;

        try {
            int offset = (page - 1) * Pagination.RECORD_PER_PAGE;
            String sql = "SELECT * FROM debtor WHERE account_id = ? AND name LIKE ? and isDeleted = false ORDER BY id LIMIT ? OFFSET ?";
            ps = db.getConnection().prepareStatement(sql);
            ps.setInt(1, accountid);
            ps.setString(2, "%" + keyword + "%");
            ps.setInt(3, Pagination.RECORD_PER_PAGE);
            ps.setInt(4, offset);
            resultSet = ps.executeQuery();

            while (resultSet.next()) {
                Debtor debtor = new Debtor();
                debtor.setId(resultSet.getInt("id"));
                debtor.setName(resultSet.getString("name"));
                debtor.setAddress(resultSet.getString("address"));
                debtor.setPhone(resultSet.getString("phone"));
                debtor.setEmail(resultSet.getString("email"));
                debtor.setTotalDebt(resultSet.getDouble("totalDebt"));
                debtor.setCreatedAt(resultSet.getTimestamp("createdAt"));
                debtor.setUpdatedAt(resultSet.getTimestamp("updatedAt"));
                debtor.setAccount_id(accountid);
                debtorList.add(debtor);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (ps != null) {
                    ps.close();
                }
                // Không cần đóng connection ở đây để sử dụng lại
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return debtorList;
    }

    public int findTotalRecordByAddress(int accountid, String keyword) {
        int totalRecord = 0;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            String sql = "SELECT COUNT(*) FROM debtor WHERE account_id = ? AND address LIKE ? and isDeleted = false";
            statement = db.getConnection().prepareStatement(sql);
            statement.setInt(1, accountid);
            statement.setString(2, "%" + keyword + "%");
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                totalRecord = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return totalRecord;
    }

    public List<Debtor> findByPageByAddress(int accountid, String keyword, int page) {
        List<Debtor> debtorList = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet resultSet = null;

        try {
            int offset = (page - 1) * Pagination.RECORD_PER_PAGE;
            String sql = "SELECT * FROM debtor WHERE account_id = ? AND address LIKE ? and isDeleted = false ORDER BY id LIMIT ? OFFSET ?";
            ps = db.getConnection().prepareStatement(sql);
            ps.setInt(1, accountid);
            ps.setString(2, "%" + keyword + "%");
            ps.setInt(3, Pagination.RECORD_PER_PAGE);
            ps.setInt(4, offset);
            resultSet = ps.executeQuery();

            while (resultSet.next()) {
                Debtor debtor = new Debtor();
                debtor.setId(resultSet.getInt("id"));
                debtor.setName(resultSet.getString("name"));
                debtor.setAddress(resultSet.getString("address"));
                debtor.setPhone(resultSet.getString("phone"));
                debtor.setEmail(resultSet.getString("email"));
                debtor.setTotalDebt(resultSet.getDouble("totalDebt"));
                debtor.setCreatedAt(resultSet.getTimestamp("createdAt"));
                debtor.setUpdatedAt(resultSet.getTimestamp("updatedAt"));
                debtor.setAccount_id(accountid);
                debtorList.add(debtor);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (ps != null) {
                    ps.close();
                }
                // Không cần đóng connection ở đây để sử dụng lại
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return debtorList;
    }

    public int findTotalRecordByPhone(int accountid, String keyword) {
        int totalRecord = 0;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            String sql = "SELECT COUNT(*) FROM debtor WHERE account_id = ? AND phone LIKE ? and isDeleted = false";
            statement = db.getConnection().prepareStatement(sql);
            statement.setInt(1, accountid);
            statement.setString(2, "%" + keyword + "%");
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                totalRecord = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return totalRecord;
    }

    public List<Debtor> findByPageByPhone(int accountid, String keyword, int page) {
        List<Debtor> debtorList = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet resultSet = null;

        try {
            int offset = (page - 1) * Pagination.RECORD_PER_PAGE;
            String sql = "SELECT * FROM debtor WHERE account_id = ? AND phone LIKE ? and isDeleted = false ORDER BY id LIMIT ? OFFSET ?";
            ps = db.getConnection().prepareStatement(sql);
            ps.setInt(1, accountid);
            ps.setString(2, "%" + keyword + "%");
            ps.setInt(3, Pagination.RECORD_PER_PAGE);
            ps.setInt(4, offset);
            resultSet = ps.executeQuery();

            while (resultSet.next()) {
                Debtor debtor = new Debtor();
                debtor.setId(resultSet.getInt("id"));
                debtor.setName(resultSet.getString("name"));
                debtor.setAddress(resultSet.getString("address"));
                debtor.setPhone(resultSet.getString("phone"));
                debtor.setEmail(resultSet.getString("email"));
                debtor.setTotalDebt(resultSet.getDouble("totalDebt"));
                debtor.setCreatedAt(resultSet.getTimestamp("createdAt"));
                debtor.setUpdatedAt(resultSet.getTimestamp("updatedAt"));
                debtor.setAccount_id(accountid);
                debtorList.add(debtor);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (ps != null) {
                    ps.close();
                }
                // Không cần đóng connection ở đây để sử dụng lại
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return debtorList;
    }

    public int findTotalRecordByEmail(int accountid, String keyword) {
        int totalRecord = 0;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            String sql = "SELECT COUNT(*) FROM debtor WHERE account_id = ? AND email LIKE ? and isDeleted = false";
            statement = db.getConnection().prepareStatement(sql);
            statement.setInt(1, accountid);
            statement.setString(2, "%" + keyword + "%");
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                totalRecord = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return totalRecord;
    }

    public List<Debtor> findByPageByEmail(int accountid, String keyword, int page) {
        List<Debtor> debtorList = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet resultSet = null;

        try {
            int offset = (page - 1) * Pagination.RECORD_PER_PAGE;
            String sql = "SELECT * FROM debtor WHERE account_id = ? AND email LIKE ? and isDeleted = false ORDER BY id LIMIT ? OFFSET ?";
            ps = db.getConnection().prepareStatement(sql);
            ps.setInt(1, accountid);
            ps.setString(2, "%" + keyword + "%");
            ps.setInt(3, Pagination.RECORD_PER_PAGE);
            ps.setInt(4, offset);
            resultSet = ps.executeQuery();

            while (resultSet.next()) {
                Debtor debtor = new Debtor();
                debtor.setId(resultSet.getInt("id"));
                debtor.setName(resultSet.getString("name"));
                debtor.setAddress(resultSet.getString("address"));
                debtor.setPhone(resultSet.getString("phone"));
                debtor.setEmail(resultSet.getString("email"));
                debtor.setTotalDebt(resultSet.getDouble("totalDebt"));
                debtor.setCreatedAt(resultSet.getTimestamp("createdAt"));
                debtor.setUpdatedAt(resultSet.getTimestamp("updatedAt"));
                debtor.setAccount_id(accountid);
                debtorList.add(debtor);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (ps != null) {
                    ps.close();
                }
                // Không cần đóng connection ở đây để sử dụng lại
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return debtorList;
    }

    public int deleteDebtor(int debtorId) {
        String sql = "update debtor "
                + " set deleteAt = now(), "
                + " isDeleted = true "
                + " where id = ? ";
        try (PreparedStatement statement = db.getConnection().prepareStatement(sql)) {
            statement.setInt(1, debtorId);
            return statement.executeUpdate();
        } catch (SQLException e) {
        } finally {
            return 0;
        }
    }

    public double calculateAndUpdateTotalDebt() {
        double totalDebt = 0;
        String selectSql = "SELECT debtor_id, SUM(amount) AS totalDebt FROM debtdetails WHERE isDeleted = false GROUP BY debtor_id";
        String updateSql = "UPDATE Debtor SET totalDebt = " + selectSql + "WHERE id = ?";
        try (Connection conn = db.getConnection(); PreparedStatement selectPs = conn.prepareStatement(selectSql); PreparedStatement updatePs = conn.prepareStatement(updateSql)) {

            try (ResultSet rs = selectPs.executeQuery()) {
                while (rs.next()) {
                    int debtorId = rs.getInt("debtor_id");
                    totalDebt = rs.getDouble("totalDebt");

                    // Update totalDebt for the current debtor
                    updatePs.setDouble(1, totalDebt);
                    updatePs.setInt(2, debtorId);
                    int rowsAffected = updatePs.executeUpdate();
                    if (rowsAffected > 0) {
                        System.out.println("Total debt updated successfully for debtor with ID: " + debtorId);
                    } else {
                        System.out.println("Failed to update total debt for debtor with ID: " + debtorId);
                    }
                }
            }
        } catch (SQLException ex) {
            System.out.println("Error calculating and updating total debt: " + ex.getMessage());
        }
        return totalDebt;
    }

    public static void main(String[] args) {
        DebtorDAO debtorDAO = new DebtorDAO();

        // Call calculateAndUpdateTotalDebt method to update total debt for all debtors
        double totalDebt = debtorDAO.calculateAndUpdateTotalDebt();
        System.out.println("Total debt calculated and updated for all debtors: " + totalDebt);
    }

}
