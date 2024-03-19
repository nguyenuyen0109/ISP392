/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;


import dal.DBContext;
import model.DebtDetail;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
//import java.util.Collections;
//import java.util.Comparator;
import java.util.Date;
import java.util.List;
import utils.Pagination;

/**
 *
 * @author Admin
 */
public class DebtDAO {

    DBContext db;

    public DebtDAO() {
        db = DBContext.getInstance();
    }

    public int addDebt(DebtDetail debt, int accountid, int debtorid) {
        int n = 0;
        String sql = "insert into debtdetails ("
                + "description, "
                + "debtTypeId, "
                + "image, "
                + "amount, "
                + "interestRate,"
                + "due,"
                + "debtissuance,"
                + "debtor_id,"
                + "debtor_account_id)"
                + "values (?,?,?,?,?,?,?,?,?);";
        try (PreparedStatement ps = db.getConnection().prepareStatement(sql)) {
            ps.setString(1, debt.getDescription());
            ps.setInt(2, debt.getDebtTypeId()); //true=Debt, false= Receivable
            ps.setString(3, debt.getImage());
            ps.setDouble(4, debt.getAmount());
            ps.setDouble(5, debt.getInterestRate());
            ps.setDouble(6, debt.getDue());
            ps.setDate(7, (java.sql.Date) debt.getDebtIssuance());
            ps.setInt(8, debtorid);
            ps.setInt(9, accountid);
            n = ps.executeUpdate();
            System.out.println(sql);
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return n;
    }

    public List<DebtDetail> getDebtByIdAccountAndIdDebtor(int idDebtor, int accountid) {
        List<DebtDetail> debtList = new ArrayList<>();
        String sql = "select * from debtdetails where debtor_id=? and debtor_account_id = ? and isDeleted = 0 ";
        try (PreparedStatement preparedStatement = db.getConnection().prepareStatement(sql)) {
            preparedStatement.setInt(1, idDebtor);
            preparedStatement.setInt(2, accountid);
            try (ResultSet rs = preparedStatement.executeQuery()) {
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String description = rs.getString("description");
                    String image = rs.getString("image");
                    int debtTypeId = rs.getInt("debtTypeId");
                    Timestamp createAt = rs.getTimestamp("createdAt");
                    double amount = rs.getDouble("amount");
                    double interest = rs.getDouble("interestRate");
                    double due = rs.getDouble("due");
                    Timestamp deleteAt = rs.getTimestamp("deleteAt");
                    boolean isDeleted = rs.getBoolean("isDeleted");
                    double totalAmount = rs.getDouble("totalAmount");
                    Date debtIssuance = rs.getDate("debtissuance");
                    DebtDetail debt = new DebtDetail(id, description, amount, image, createAt, idDebtor, accountid,
                            interest, due, deleteAt, isDeleted, debtTypeId, totalAmount, debtIssuance);
                    debtList.add(debt);
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return debtList;
    }

    public List<DebtDetail> searchDebtByAmount(int idDebtor, int accountid, String input) {
        List<DebtDetail> debtList = new ArrayList<>();
//        String sql = "SELECT * FROM debtdetails WHERE amount LIKE '%"+ inputAmount +"%'";
        String sql = "select * from debtdetails where debtor_id= ? and debtor_account_id = ?  and amount like ? and isDeleted = 0 ";
        try (PreparedStatement preparedStatement = db.getConnection().prepareStatement(sql)) {
            preparedStatement.setInt(1, idDebtor);
            preparedStatement.setInt(2, accountid);
            preparedStatement.setString(3, "%" + input + "%");
            try (ResultSet rs = preparedStatement.executeQuery()) {

                while (rs.next()) {
                    int id = rs.getInt("id");
                    String description = rs.getString("description");
                    int debtType = rs.getInt("debtTypeId");
                    double amount = rs.getDouble("amount");
                    String image = rs.getString("image");
                    Timestamp createAt = rs.getTimestamp("createdAt");
                    double interest = rs.getDouble("interestRate");
                    double due = rs.getDouble("due");
                    DebtDetail debt = new DebtDetail(id, description, debtType, amount, image, createAt, idDebtor, accountid, interest, due);
                    debtList.add(debt);
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return debtList;
    }

    public List<DebtDetail> searchDebtByDescription(int idDebtor, int accountid, String input) {
        List<DebtDetail> debtList = new ArrayList<>();
        String sql = "select * from debtdetails where debtor_id= ? and debtor_account_id = ? and description like ? and isDeleted = 0 ";
        try (PreparedStatement preparedStatement = db.getConnection().prepareStatement(sql)) {
            preparedStatement.setInt(2, accountid);
            preparedStatement.setInt(1, idDebtor);
            preparedStatement.setString(3, "%" + input + "%");
            try (ResultSet rs = preparedStatement.executeQuery()) {
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String description = rs.getString("description");
                    int debtType = rs.getInt("debtTypeId");
                    double amount = rs.getDouble("amount");
                    String image = rs.getString("image");
                    Timestamp createAt = rs.getTimestamp("createdAt");
                    double interest = rs.getDouble("interestRate");
                    double due = rs.getDouble("due");
                    DebtDetail debt = new DebtDetail(id, description, debtType, amount, image, createAt, idDebtor, accountid, interest, due);
                    debtList.add(debt);
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }

        return debtList;
    }

    public List<DebtDetail> filterByReceivable(int idDebtor, int accountid, String action) {
        List<DebtDetail> debtList = new ArrayList<>();
        String sql = "select * from debtdetails where debtor_id= and debtor_account_id and debtTypeId like ? and isDeleted = 0 ";
        try (PreparedStatement preparedStatement = db.getConnection().prepareStatement(sql)) {
            preparedStatement.setInt(2, accountid);
            preparedStatement.setInt(1, idDebtor);
            preparedStatement.setString(3, "%" + action + "%");
            try (ResultSet rs = preparedStatement.executeQuery()) {
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String description = rs.getString("description");
                    int debtType = rs.getInt("debtTypeId");
                    double amount = rs.getDouble("amount");
                    String image = rs.getString("image");
                    Timestamp createAt = rs.getTimestamp("createdAt");
                    double interest = rs.getDouble("interestRate");
                    double due = rs.getDouble("due");
                    DebtDetail debt = new DebtDetail(id, description, debtType, amount, image, createAt, idDebtor, accountid, interest, due);
                    debtList.add(debt);
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }

        return debtList;
    }

    public int findTotalRecord(int accountId, int debtorId) {
        int totalRecord = 0;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            String sql = "SELECT COUNT(*) FROM debtdetails WHERE debtor_account_id = ? AND debtor_id = ? and isDeleted = 0 ";
            statement = db.getConnection().prepareStatement(sql);
            statement.setInt(1, accountId);
            statement.setInt(2, debtorId);
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                totalRecord = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return totalRecord;
    }

    public List<DebtDetail> findByPage(int accountId, int debtorId, int page) {
        List<DebtDetail> debtList = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet resultSet = null;

        try {
            int offset = (page - 1) * Pagination.RECORD_PER_PAGE;
            String sql = "SELECT * FROM debtdetails ddt "
                    + "INNER JOIN debttype dt ON ddt.debtTypeId = dt.id "
                    + "INNER JOIN debtor d ON ddt.debtor_id=d.id "
                    + "WHERE debtor_account_id = ? AND debtor_id = ?  and isDeleted = 0 ORDER BY ddt.id LIMIT ? OFFSET ?";
            ps = db.getConnection().prepareStatement(sql);
            ps.setInt(1, accountId);
            ps.setInt(2, debtorId);
            ps.setInt(3, Pagination.RECORD_PER_PAGE);
            ps.setInt(4, offset);
            resultSet = ps.executeQuery();

            while (resultSet.next()) {
                DebtDetail debt = new DebtDetail();
                debt.setId(resultSet.getInt("id"));
                debt.setDescription(resultSet.getString("description"));
                debt.setDebtorName(resultSet.getString("d.name"));
                debt.setDebtTypeName(resultSet.getString("name"));
                debt.setAmount(resultSet.getDouble("amount"));
                debt.setCreateAt(resultSet.getTimestamp("createdAt"));
                //viet them
                debt.setInterestRate(resultSet.getDouble("interestRate"));
                debt.setDue(resultSet.getDouble("due"));
                debt.setImage(resultSet.getString("image"));
                debt.setDeletedAt(resultSet.getTimestamp("deleteAt"));
                debt.setIsDeleted(resultSet.getBoolean("isDeleted"));
                debt.setTotalAmount(resultSet.getDouble("totalAmount"));
                debt.setDebtIssuance(resultSet.getDate("debtissuance"));
                debtList.add(debt);
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

        return debtList;
    }

    public int findTotalRecordToSearch(int accountId, int debtorId, String keyword) {
        int totalRecord = 0;

        PreparedStatement ps = null;
        ResultSet resultSet = null;

        try {

            String sql = "Select Count(*) as totalRecord from debtdetails where debtor_account_id=? and debtor_id=? \n"
                    + "                                                  and (description like ? or amount = ? ) and isDeleted = 0 ";
            ps = db.getConnection().prepareStatement(sql);
            ps.setInt(1, accountId);
            ps.setInt(2, debtorId);
            ps.setString(3, "%" + keyword + "%");
            ps.setString(4, keyword);

            resultSet = ps.executeQuery();

            if (resultSet.next()) {
                totalRecord = resultSet.getInt("totalRecord");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return totalRecord;
    }

    public List<DebtDetail> findByPageToSearch(int accountId, int debtorId, String keyword, int page) {
        List<DebtDetail> debtList = new ArrayList<>();
        String sql = "SELECT * "
                + "FROM debtdetails ddt "
                + "INNER JOIN debttype dt ON ddt.debtTypeId = dt.id "
                + "INNER JOIN debtor d ON ddt.debtor_id=d.id "
                + "WHERE debtor_account_id = ? AND debtor_id = ? AND (ddt.description LIKE ? OR amount = ? ) and isDeleted = 0 "
                + "ORDER BY ddt.id "
                + "LIMIT ? OFFSET ?";
        try (PreparedStatement ps = db.getConnection().prepareStatement(sql)) {
            int offset = (page - 1) * Pagination.RECORD_PER_PAGE;
            ps.setInt(1, accountId);
            ps.setInt(2, debtorId);
            ps.setString(3, "%" + keyword + "%");
            ps.setString(4, keyword);
            ps.setInt(5, Pagination.RECORD_PER_PAGE);
            ps.setInt(6, offset);
            try (ResultSet resultSet = ps.executeQuery()) {

                while (resultSet.next()) {
                    DebtDetail debt = new DebtDetail();
                    debt.setId(resultSet.getInt("id"));
                    debt.setDescription(resultSet.getString("description"));
                    debt.setDebtorName(resultSet.getString("d.name"));
                    debt.setDebtTypeName(resultSet.getString("name"));
                    debt.setAmount(resultSet.getDouble("amount"));
                    debt.setCreateAt(resultSet.getTimestamp("createdAt"));
                    //viet them
                    debt.setInterestRate(resultSet.getDouble("interestRate"));
                    debt.setDue(resultSet.getDouble("due"));
                    debt.setImage(resultSet.getString("image"));
                    debt.setDeletedAt(resultSet.getTimestamp("deleteAt"));
                    debt.setIsDeleted(resultSet.getBoolean("isDeleted"));
                    debt.setTotalAmount(resultSet.getDouble("totalAmount"));
                    debt.setDebtIssuance(resultSet.getDate("debtissuance"));
                    debtList.add(debt);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return debtList;
    }

    public List<DebtDetail> findByPageByDescription(int accountId, int debtorId, String keyword, int page) {
        List<DebtDetail> debtList = new ArrayList<>();

        PreparedStatement ps = null;
        ResultSet resultSet = null;

        try {

            int offset = (page - 1) * Pagination.RECORD_PER_PAGE;
            String sql = "SELECT * "
                    + "FROM debtdetails ddt INNER JOIN debttype dt ON ddt.debtTypeId = dt.id "
                    + "WHERE debtor_account_id = ? AND debtor_id = ? AND description LIKE ? and isDeleted = 0 "
                    + "ORDER BY ddt.id "
                    + "LIMIT ? OFFSET ?";
            ps = db.getConnection().prepareStatement(sql);
            ps.setInt(1, accountId);
            ps.setInt(2, debtorId);
            ps.setString(3, "%" + keyword + "%");
            ps.setInt(4, Pagination.RECORD_PER_PAGE);
            ps.setInt(5, offset);

            resultSet = ps.executeQuery();

            while (resultSet.next()) {
                DebtDetail debt = new DebtDetail();
                debt.setId(resultSet.getInt("id"));
                debt.setDescription(resultSet.getString("description"));
                debt.setDebtTypeId(resultSet.getInt("debtTypeId"));
                debt.setAmount(resultSet.getDouble("amount"));
                debt.setCreateAt(resultSet.getTimestamp("createdAt"));
                debtList.add(debt);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return debtList;
    }

    public int findTotalRecordByAmount(int accountId, int debtorId, double amount) {
        String sql = "SELECT COUNT(*) AS total FROM debtdetails WHERE debtor_account_id = ? AND debtor_id = ? AND amount = ? and isDeleted = 0 ";
        try (
                PreparedStatement ps = db.getConnection().prepareStatement(sql)) {
            ps.setInt(1, accountId);
            ps.setInt(2, debtorId);
            ps.setDouble(3, amount);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("total");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public List<DebtDetail> findByPageByAmount(int accountId, int debtorId, double amount, int page) {
        List<DebtDetail> debtList = new ArrayList<>();
        int offset = (page - 1) * Pagination.RECORD_PER_PAGE;
        String sql = "SELECT * FROM debtdetails ddt INNER JOIN debttype dt ON ddt.debtTypeId = dt.id WHERE debtor_account_id = ? AND debtor_id = ? AND amount = ? and ddt.isDeleted = 0 ORDER BY ddt.id LIMIT ? OFFSET ?";
        try (PreparedStatement ps = db.getConnection().prepareStatement(sql)) {
            ps.setInt(1, accountId);
            ps.setInt(2, debtorId);
            ps.setDouble(3, amount);
            ps.setInt(4, Pagination.RECORD_PER_PAGE);
            ps.setInt(5, offset);
            try (ResultSet resultSet = ps.executeQuery()) {
                while (resultSet.next()) {
                    DebtDetail debt = new DebtDetail();
                    debt.setId(resultSet.getInt("id"));
                    debt.setDescription(resultSet.getString("description"));
                    debt.setDebtTypeId(resultSet.getInt("debtTypeId"));

                    debt.setAmount(resultSet.getDouble("amount"));
                    debt.setCreateAt(resultSet.getTimestamp("createdAt"));
                    debtList.add(debt);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return debtList;
    }

    public List<DebtDetail> findByPageAndSortByOldest(int accountId, int debtorId, int page) {
        List<DebtDetail> debtList = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        try {
            int offset = (page - 1) * Pagination.RECORD_PER_PAGE;
            String sql = "SELECT * FROM debtdetails ddt\n"
                    + "INNER JOIN debttype dt ON ddt.debtTypeId = dt.id "
                    + "INNER JOIN debtor d ON ddt.debtor_id=d.id "
                    + "WHERE debtor_account_id = ? AND debtor_id = ? and isDeleted = 0 ORDER BY ddt.createdAt ASC LIMIT ? OFFSET ?";
            ps = db.getConnection().prepareStatement(sql);
            ps.setInt(1, accountId);
            ps.setInt(2, debtorId);
            ps.setInt(3, Pagination.RECORD_PER_PAGE);
            ps.setInt(4, offset);
            resultSet = ps.executeQuery();
            while (resultSet.next()) {
                DebtDetail debt = new DebtDetail();
                debt.setId(resultSet.getInt("id"));
                debt.setDescription(resultSet.getString("description"));
                debt.setDebtorName(resultSet.getString("d.name"));
                debt.setDebtTypeName(resultSet.getString("name"));
                debt.setAmount(resultSet.getDouble("amount"));
                debt.setCreateAt(resultSet.getTimestamp("createdAt"));
                //viet them
                debt.setInterestRate(resultSet.getDouble("interestRate"));
                debt.setDue(resultSet.getDouble("due"));
                debt.setImage(resultSet.getString("image"));
                debt.setDeletedAt(resultSet.getTimestamp("deleteAt"));
                debt.setIsDeleted(resultSet.getBoolean("isDeleted"));
                debt.setTotalAmount(resultSet.getDouble("totalAmount"));
                debt.setDebtIssuance(resultSet.getDate("debtissuance"));
                debtList.add(debt);
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
        return debtList;
    }

    public List<DebtDetail> findByPageAndSortByNewest(int accountId, int debtorId, int page) {
        List<DebtDetail> debtList = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        try {
            int offset = (page - 1) * Pagination.RECORD_PER_PAGE;
            String sql = "SELECT * FROM debtdetails ddt\n"
                    + "INNER JOIN debttype dt ON ddt.debtTypeId = dt.id "
                    + "INNER JOIN debtor d ON ddt.debtor_id=d.id "
                    + "WHERE debtor_account_id = ? AND debtor_id = ? and isDeleted = 0 ORDER BY ddt.createdAt DESC LIMIT ? OFFSET ?";
            ps = db.getConnection().prepareStatement(sql);
            ps.setInt(1, accountId);
            ps.setInt(2, debtorId);
            ps.setInt(3, Pagination.RECORD_PER_PAGE);
            ps.setInt(4, offset);
            resultSet = ps.executeQuery();
            while (resultSet.next()) {
                DebtDetail debt = new DebtDetail();
                debt.setId(resultSet.getInt("id"));
                debt.setDescription(resultSet.getString("description"));
                debt.setDebtorName(resultSet.getString("d.name"));
                debt.setDebtTypeName(resultSet.getString("name"));
                debt.setAmount(resultSet.getDouble("amount"));
                debt.setCreateAt(resultSet.getTimestamp("createdAt"));
                //viet them
                debt.setInterestRate(resultSet.getDouble("interestRate"));
                debt.setDue(resultSet.getDouble("due"));
                debt.setImage(resultSet.getString("image"));
                debt.setDeletedAt(resultSet.getTimestamp("deleteAt"));
                debt.setIsDeleted(resultSet.getBoolean("isDeleted"));
                debt.setTotalAmount(resultSet.getDouble("totalAmount"));
                debt.setDebtIssuance(resultSet.getDate("debtissuance"));
                debtList.add(debt);
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
        return debtList;
    }

    public List<DebtDetail> findByPageAndSortDebtByAmountHighLow(int accountId, int debtorId, int page) {
        return findByPageAndSortDebtAmount(accountId, debtorId, page, "amount DESC");
    }

    public List<DebtDetail> findByPageAndSortDebtByAmountLowHigh(int accountId, int debtorId, int page) {
        return findByPageAndSortDebtAmount(accountId, debtorId, page, "amount ASC");

    }

    private List<DebtDetail> findByPageAndSortDebtAmount(int accountId, int debtorId, int page, String orderBy) {
        List<DebtDetail> debtList = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        try {
            int offset = (page - 1) * Pagination.RECORD_PER_PAGE;
            String sql = "SELECT * FROM debtdetails ddt\n"
                    + "INNER JOIN debttype dt ON ddt.debtTypeId = dt.id "
                    + "INNER JOIN debtor d ON ddt.debtor_id=d.id "
                    + "WHERE debtor_account_id = ? AND debtor_id = ? and isDeleted = 0 ORDER BY " + orderBy + " LIMIT ? OFFSET ?";
            ps = db.getConnection().prepareStatement(sql);
            ps.setInt(1, accountId);
            ps.setInt(2, debtorId);
            ps.setInt(3, Pagination.RECORD_PER_PAGE);
            ps.setInt(4, offset);
            resultSet = ps.executeQuery();

            while (resultSet.next()) {
                DebtDetail debt = new DebtDetail();
                debt.setId(resultSet.getInt("id"));
                debt.setDescription(resultSet.getString("description"));
                debt.setDebtorName(resultSet.getString("d.name"));
                debt.setDebtTypeName(resultSet.getString("name"));
                debt.setAmount(resultSet.getDouble("amount"));
                debt.setCreateAt(resultSet.getTimestamp("createdAt"));
                //viet them
                debt.setInterestRate(resultSet.getDouble("interestRate"));
                debt.setDue(resultSet.getDouble("due"));
                debt.setImage(resultSet.getString("image"));
                debt.setDeletedAt(resultSet.getTimestamp("deleteAt"));
                debt.setIsDeleted(resultSet.getBoolean("isDeleted"));
                debt.setTotalAmount(resultSet.getDouble("totalAmount"));
                debt.setDebtIssuance(resultSet.getDate("debtissuance"));
                debtList.add(debt);
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
        return debtList;
    }

    public int findTotalRecordByDebtType(int accountId, int debtorId, int idDebtType) {
        int totalRecord = 0;
        PreparedStatement ps = null;
        ResultSet resultSet = null;

        try {
            String sql = "SELECT COUNT(*) AS total FROM debtdetails WHERE debtor_account_id = ? AND debtor_id = ? AND debtTypeId = ? and isDeleted = 0 ";
            ps = db.getConnection().prepareStatement(sql);
            ps.setInt(1, accountId);
            ps.setInt(2, debtorId);
            ps.setInt(3, idDebtType);
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

    public List<DebtDetail> findByPageByDebtType(int accountId, int debtorId, int idDebtType, int page) {
        List<DebtDetail> debtList = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet resultSet = null;

        try {
            int offset = (page - 1) * Pagination.RECORD_PER_PAGE;
            String sql = "SELECT * FROM debtdetails ddt "
                    + "INNER JOIN debttype dt ON ddt.debtTypeId = dt.id "
                    + "INNER JOIN debtor d ON ddt.debtor_id=d.id "
                    + "WHERE debtor_account_id = ? AND debtor_id = ? AND ddt.debtTypeId = ? and isDeleted = 0 LIMIT ? OFFSET ?";
            ps = db.getConnection().prepareStatement(sql);
            ps.setInt(1, accountId);
            ps.setInt(2, debtorId);
            ps.setInt(3, idDebtType);
            ps.setInt(4, Pagination.RECORD_PER_PAGE);
            ps.setInt(5, offset);
            resultSet = ps.executeQuery();

            while (resultSet.next()) {
                DebtDetail debt = new DebtDetail();
                debt.setId(resultSet.getInt("id"));
                debt.setDescription(resultSet.getString("description"));
                debt.setDebtorName(resultSet.getString("d.name"));
                debt.setDebtTypeName(resultSet.getString("name"));
                debt.setAmount(resultSet.getDouble("amount"));
                debt.setCreateAt(resultSet.getTimestamp("createdAt"));
                //viet them
                debt.setInterestRate(resultSet.getDouble("interestRate"));
                debt.setDue(resultSet.getDouble("due"));
                debt.setImage(resultSet.getString("image"));
                debt.setDeletedAt(resultSet.getTimestamp("deleteAt"));
                debt.setIsDeleted(resultSet.getBoolean("isDeleted"));
                debt.setTotalAmount(resultSet.getDouble("totalAmount"));
                debt.setDebtIssuance(resultSet.getDate("debtissuance"));
                debtList.add(debt);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Đóng resultSet và preparedStatement tại đây
        }

        return debtList;
    }

    public int isDeleteDebt(String id) {
        int n = 0;
        String sql = "update debtdetails set isDeleted = 1 where id = " + id;
        try (PreparedStatement ps = db.getConnection().prepareStatement(sql)) {
            n = ps.executeUpdate();
            System.out.println(sql);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return n;
    }

}
