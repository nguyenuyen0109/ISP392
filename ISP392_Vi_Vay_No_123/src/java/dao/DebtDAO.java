/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;
import com.mysql.cj.xdevapi.Collection;
import dal.DBContext;
import model.DebtDetail;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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

    public int addDebt(DebtDetail debt,int accountid, int debtorid) {
        
        int n = 0;
        String sql = "insert into debtdetails ("
                + "description, "
                + "debtType, "
                + "amount, "
                + "interest_rate_id,"
                + "debtor_id,"
                + "debtor_account_id,"
                + "values (?,?,?,?,?,?);"
                + "where debtor_id = ? and account_debtor_id = ? ";
        try (PreparedStatement ps = db.getConnection().prepareStatement(sql)) {
            ps.setString(1, debt.getDescription());
            ps.setBoolean(2, debt.isDebtType()); //true=ngta vay, false= mình nợ
            ps.setDouble(3, debt.getAmount());
            ps.setInt(4,  debt.getInterest_rate_id());
            ps.setInt(5,debtorid);
            ps.setInt(6, accountid);
            n = ps.executeUpdate();
            System.out.println(sql);
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return n;
    }
    public List<DebtDetail> getDebtByIdAccountAndIdDebtor(int idDebtor, int accountid) {
        List<DebtDetail> debtList = new ArrayList<>();
        String sql = "select * from debtdetails where debtor_id=? and debtor_account_id = ?";
        try (PreparedStatement preparedStatement = db.getConnection().prepareStatement(sql)) {
            preparedStatement.setInt(2, accountid);
            preparedStatement.setInt(1, idDebtor);
            try (ResultSet rs = preparedStatement.executeQuery()) {
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String description = rs.getString("description");
                    boolean debtType = rs.getBoolean("debtType");
                    double amount = rs.getDouble("amount");
                    Timestamp createAt = rs.getTimestamp("createdAt");
                    DebtDetail debt = new DebtDetail(id, description, debtType, amount, createAt);
                    debtList.add(debt);
                }
            }} catch (SQLException ex) {
                System.out.println(ex);
            }
            return debtList;
        }

//    public List<DebtDetail> sortDebtByOldest(int idDebtor,int accountid) {
//        List<DebtDetail> list = getDebtByIdAccountAndIdDebtor(idDebtor,accountid);
//        // Sắp xếp theo ngày tạo cũ nhất (tăng dần)
//        Collections.sort(list, Comparator.comparing(DebtDetail::getCreatAt));
//        return list;
//    }
//
//    public List<DebtDetail> sortDebtByNewest(int idDebtor,int accountid) {
//        List<DebtDetail> list = getDebtByIdAccountAndIdDebtor(idDebtor, accountid);
//        // Sắp xếp theo ngày tạo mới nhất (giảm dần)
//        Collections.sort(list, Collections.reverseOrder(Comparator.comparing(DebtDetail::getCreatAt)));
//        return list;
//    }
//
//    public List<DebtDetail> sortDebtByAmountHightLow(int idDebtor,int accountid) {
//        List<DebtDetail> list = getDebtByIdAccountAndIdDebtor(idDebtor,accountid);
//        Collections.sort(list, new Comparator<DebtDetail>() {
//            @Override
//            public int compare(DebtDetail o1, DebtDetail o2) {
//                return Double.compare(o2.getAmount(), o1.getAmount());
//            }
//        });
//        return list;
//    }
//
//    public List<DebtDetail> sortDebtByAmountLowHight(int idDebtor,int accountid) {
//        List<DebtDetail> list = getDebtByIdAccountAndIdDebtor(idDebtor, accountid);
//        Collections.sort(list, new Comparator<DebtDetail>() {
//            @Override
//            public int compare(DebtDetail o1, DebtDetail o2) {
//                return Double.compare(o1.getAmount(), o2.getAmount());
//            }
//        });
//        return list;
//    }

    public List<DebtDetail> searchDebtByAmount(int idDebtor,int accountid,String input) {
        List<DebtDetail> debtList = new ArrayList<>();
//        String sql = "SELECT * FROM debtdetails WHERE amount LIKE '%"+ inputAmount +"%'";
        String sql = "select * from debtdetails where debtor_id= ? and debtor_account_id = ?  and amount like ?";
        try (PreparedStatement preparedStatement = db.getConnection().prepareStatement(sql)) {
            preparedStatement.setInt(1, idDebtor);
            preparedStatement.setInt(2, accountid);
            preparedStatement.setString(3, "%"+input+"%");
            try (ResultSet rs = preparedStatement.executeQuery()) {
            while (rs.next()) {
                int id = rs.getInt("id");
                String description = rs.getString("description");
                boolean debtType = rs.getBoolean("debtType");
                double amount = rs.getDouble("amount");
                Timestamp createAt = rs.getTimestamp("createdAt");
                DebtDetail debt = new DebtDetail(id, description, debtType, amount, createAt);
                debtList.add(debt);
            }
        }} catch (SQLException ex) {
            System.out.println(ex);
        }
        return debtList;
    }

    public List<DebtDetail> searchDebtByDescription(int idDebtor,int accountid, String input) {
        List<DebtDetail> debtList = new ArrayList<>();
        String sql = "select * from debtdetails where debtor_id= ? and debtor_account_id = ? and description like ?";
        try (PreparedStatement preparedStatement = db.getConnection().prepareStatement(sql)) {
            preparedStatement.setInt(2, accountid);
            preparedStatement.setInt(1, idDebtor);
            preparedStatement.setString(3, "%"+input+"%");
            try (ResultSet rs = preparedStatement.executeQuery()) {
            while (rs.next()) {
                int id = rs.getInt("id");
                String description = rs.getString("description");
                boolean debtType = rs.getBoolean("debtType");
                double amount = rs.getDouble("amount");
                Timestamp createAt = rs.getTimestamp("createdAt");
                DebtDetail debt = new DebtDetail(id, description, debtType, amount, createAt);
                debtList.add(debt);

            }
        } }catch (SQLException ex) {
            System.out.println(ex);
        }

        return debtList;
    }

    public List<DebtDetail> filterByReceivable(int idDebtor, int accountid, String action) {
        List<DebtDetail> debtList = new ArrayList<>();
        String sql = "select * from debtdetails where debtor_id= and debtor_account_id and debtType like ?";
        try (PreparedStatement preparedStatement = db.getConnection().prepareStatement(sql)) {
            preparedStatement.setInt(2, accountid);
            preparedStatement.setInt(1, idDebtor);
            preparedStatement.setString(3, "%"+action+"%");
            try (ResultSet rs = preparedStatement.executeQuery()) {
            while (rs.next()) {
                int id = rs.getInt("id");
                String description = rs.getString("description");
                boolean debtType = rs.getBoolean("debtType");
                double amount = rs.getDouble("amount");
                Timestamp createAt = rs.getTimestamp("createdAt");
                DebtDetail debt = new DebtDetail(id, description, debtType, amount, createAt);
                debtList.add(debt);
            }
        }} catch (SQLException ex) {
            System.out.println(ex);
        }

        return debtList;
    }
    
    public int findTotalRecord(int accountId, int debtorId) {
    int totalRecord = 0;
    PreparedStatement statement = null;
    ResultSet resultSet = null;

    try {
        String sql = "SELECT COUNT(*) FROM debtdetails WHERE debtor_account_id = ? AND debtor_id = ?";
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
        String sql = "SELECT * FROM debtdetails WHERE debtor_account_id = ? AND debtor_id = ? ORDER BY id LIMIT ? OFFSET ?";
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
            debt.setDebtType(resultSet.getBoolean("debtType"));
            debt.setAmount(resultSet.getDouble("amount"));
            debt.setCreatAt(resultSet.getTimestamp("createdAt"));
            debtList.add(debt);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    } finally {
        try {
            if (resultSet != null) resultSet.close();
            if (ps != null) ps.close();
            // Không cần đóng connection ở đây để sử dụng lại
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    return debtList;
}

public int findTotalRecordByDescription(int accountId, int debtorId, String keyword) {
    int totalRecord = 0;

    PreparedStatement ps = null;
    ResultSet resultSet = null;

    try {
   
        String sql = "SELECT COUNT(*) AS totalRecord " +
                     "FROM debtdetails " +
                     "WHERE debtor_account_id = ? AND debtor_id = ? AND description LIKE ?";
        ps = db.getConnection().prepareStatement(sql);
        ps.setInt(1, accountId);
        ps.setInt(2, debtorId);
        ps.setString(3, "%" + keyword + "%");

        resultSet = ps.executeQuery();

        if (resultSet.next()) {
            totalRecord = resultSet.getInt("totalRecord");
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }

    return totalRecord;
}

public List<DebtDetail> findByPageByDescription(int accountId, int debtorId, String keyword, int page) {
    List<DebtDetail> debtList = new ArrayList<>();

    PreparedStatement ps = null;
    ResultSet resultSet = null;

    try {
        
        int offset = (page - 1) * Pagination.RECORD_PER_PAGE;
        String sql = "SELECT * " +
                     "FROM debtdetails " +
                     "WHERE debtor_account_id = ? AND debtor_id = ? AND description LIKE ? " +
                     "ORDER BY id " +
                     "LIMIT ? OFFSET ?";
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
            debt.setDebtType(resultSet.getBoolean("debtType"));
            debt.setAmount(resultSet.getDouble("amount"));
            debt.setCreatAt(resultSet.getTimestamp("createdAt"));
            debtList.add(debt);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }

    return debtList;
}

public int findTotalRecordByAmount(int accountId, int debtorId, double amount) {
    String sql = "SELECT COUNT(*) AS total FROM debtdetails WHERE debtor_account_id = ? AND debtor_id = ? AND amount = ?";
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
        String sql = "SELECT * FROM debtdetails WHERE debtor_account_id = ? AND debtor_id = ? AND amount = ? ORDER BY id LIMIT ? OFFSET ?";
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
                    debt.setDebtType(resultSet.getBoolean("debtType"));
                    debt.setAmount(resultSet.getDouble("amount"));
                    debt.setCreatAt(resultSet.getTimestamp("createdAt"));
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
        String sql = "SELECT * FROM debtdetails WHERE debtor_account_id = ? AND debtor_id = ? ORDER BY createdAt ASC LIMIT ? OFFSET ?";
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
            debt.setDebtType(resultSet.getBoolean("debtType"));
            debt.setAmount(resultSet.getDouble("amount"));
            debt.setCreatAt(resultSet.getTimestamp("createdAt"));
            debtList.add(debt);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    } finally {
        // Đóng resultSet, preparedStatement và kết nối tại đây
    }

    return debtList;
}

public List<DebtDetail> findByPageAndSortByNewest(int accountId, int debtorId, int page) {
    List<DebtDetail> debtList = new ArrayList<>();
    PreparedStatement ps = null;
    ResultSet resultSet = null;

    try {
        int offset = (page - 1) * Pagination.RECORD_PER_PAGE;
        String sql = "SELECT * FROM debtdetails WHERE debtor_account_id = ? AND debtor_id = ? ORDER BY createdAt DESC LIMIT ? OFFSET ?";
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
            debt.setDebtType(resultSet.getBoolean("debtType"));
            debt.setAmount(resultSet.getDouble("amount"));
            debt.setCreatAt(resultSet.getTimestamp("createdAt"));
            debtList.add(debt);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    } finally {
        // Đóng resultSet, preparedStatement và kết nối tại đây
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
        String sql = "SELECT * FROM debtdetails WHERE debtor_account_id = ? AND debtor_id = ? ORDER BY " + orderBy + " LIMIT ? OFFSET ?";
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
            debt.setDebtType(resultSet.getBoolean("debtType"));
            debt.setAmount(resultSet.getDouble("amount"));
            debt.setCreatAt(resultSet.getTimestamp("createdAt"));
            debtList.add(debt);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }

    return debtList;
}

public int findTotalRecordByDebtType(int accountId, int debtorId, boolean isDebtType) {
    int totalRecord = 0;
    PreparedStatement ps = null;
    ResultSet resultSet = null;

    try {
        String sql = "SELECT COUNT(*) AS total FROM debtdetails WHERE debtor_account_id = ? AND debtor_id = ? AND debtType = ?";
        ps = db.getConnection().prepareStatement(sql);
        ps.setInt(1, accountId);
        ps.setInt(2, debtorId);
        ps.setBoolean(3, isDebtType);
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

public List<DebtDetail> findByPageByDebtType(int accountId, int debtorId, boolean isDebtType, int page) {
    List<DebtDetail> debtList = new ArrayList<>();
    PreparedStatement ps = null;
    ResultSet resultSet = null;

    try {
        int offset = (page - 1) * Pagination.RECORD_PER_PAGE;
        String sql = "SELECT * FROM debtdetails WHERE debtor_account_id = ? AND debtor_id = ? AND debtType = ? LIMIT ? OFFSET ?";
        ps = db.getConnection().prepareStatement(sql);
        ps.setInt(1, accountId);
        ps.setInt(2, debtorId);
        ps.setBoolean(3, isDebtType);
        ps.setInt(4, Pagination.RECORD_PER_PAGE);
        ps.setInt(5, offset);
        resultSet = ps.executeQuery();

        while (resultSet.next()) {
            DebtDetail debt = new DebtDetail();
            debt.setId(resultSet.getInt("id"));
            debt.setDescription(resultSet.getString("description"));
            debt.setDebtType(resultSet.getBoolean("debtType"));
            debt.setAmount(resultSet.getDouble("amount"));
            debt.setCreatAt(resultSet.getTimestamp("createdAt"));
            debtList.add(debt);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    } finally {
        // Đóng resultSet và preparedStatement tại đây
    }

    return debtList;
}


    public static void main(String[] args) {
        DebtDAO dao = new DebtDAO();
        
//        List<DebtDetail> list = dao.searchDebtByDescription(1,2, "test");
//        for (DebtDetail debtDetail : list) {
//            System.out.println(debtDetail);
//        }
//        System.out.println("-------List---------");
//        List<DebtDetail> list1 = dao.getDebtByIdAccountAndIdDebtor(1, 2);
//        for (DebtDetail debtDetail : list1) {
//            System.out.println(debtDetail);
//        }
//        List<DebtDetail> list2 = dao.sortDebtByAmountHightLow(1, 2);
//        for (DebtDetail debtDetail : list2) {
//            System.out.println(debtDetail);
//        }
    List<DebtDetail> list = dao.searchDebtByDescription(1,2, "test");
       for (DebtDetail debtDetail : list) {
            System.out.println(debtDetail);
        }
    System.out.println("-------List---------");
    
//    List<DebtDetail> list2 = dao.sortDebtByAmountHightLow(1, 2);
//        for (DebtDetail debtDetail : list2) {
//            System.out.println(debtDetail);
//        }
    }
}
