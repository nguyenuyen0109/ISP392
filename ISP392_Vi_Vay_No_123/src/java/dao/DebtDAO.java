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
import model.Debtor;
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

    public int addDebt(DebtDetail debt) {
        int n = 0;
        String sql = "insert into debtdetails ("
                + "description, "
                + "debtType, "
                + "amount, "
                + "debtor_id, "
                + "debtor_account_id, "
                + "interest_rate_id)\n"
                + "values (?,?,?,?,?,?);";
        try {
            PreparedStatement ps = db.getConnection().prepareStatement(sql);
            ps.setString(1, debt.getDescription());
            ps.setBoolean(2, debt.isDeptType()); //true=ngta vay, false= mình nợ
            ps.setDouble(3, debt.getAmount());
            ps.setInt(4, debt.getDebtor_id());
            ps.setInt(5, debt.getDebtor_account_id());
            ps.setInt(6, debt.getInterest_rate_id());
            n = ps.executeUpdate();
            System.out.println(sql);
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return n;
    }

//    public List<DebtDetail> getDebtByIdAccountAndIdDebtor(int idDebtor) {
//        List<DebtDetail> debtList = new ArrayList<>();
//        String sql = "select * from debtdetails where debtor_id=";
//        try {
//            PreparedStatement ps = db.getConnection().prepareStatement(sql);
//            ps.setInt(1, idDebtor);
//            ResultSet rs = ps.executeQuery();
//            while(rs.next()) {
//                int id = rs.getInt("id");
//                String description = rs.getString("description");
//                boolean debtType = rs.getBoolean("debtType");
//                double amount = rs.getDouble("amount");
//                Timestamp createAt = rs.getTimestamp("createdAt");
//                DebtDetail debt = new DebtDetail(id, description, debtType, amount, createAt);
//                debtList.add(debt);
//                
//            }
//        } catch (SQLException ex) {
//            System.out.println(ex);
//        }
//
//        return debtList;
//    }
//    
//    public List<DebtDetail> sortDebtByOldest(int idDebtor){
//        List<DebtDetail> list = getDebtByIdAccountAndIdDebtor(idDebtor);
//        // Sắp xếp theo ngày tạo cũ nhất (tăng dần)
//        Collections.sort(list, Comparator.comparing(DebtDetail::getCreatAt));
//        return list;
//    }
//    
//    public List<DebtDetail> sortDebtByNewest(int idDebtor){
//        List<DebtDetail> list = getDebtByIdAccountAndIdDebtor(idDebtor);
//        // Sắp xếp theo ngày tạo mới nhất (giảm dần)
//        Collections.sort(list, Collections.reverseOrder(Comparator.comparing(DebtDetail::getCreatAt)));
//        return list;
//    }
//
//    public List<DebtDetail> sortDebtByAmountHightLow(int idDebtor){
//        List<DebtDetail> list = getDebtByIdAccountAndIdDebtor(idDebtor);
//        Collections.sort(list, new Comparator<DebtDetail>(){
//            @Override
//            public int compare(DebtDetail o1, DebtDetail o2) {
//                return Double.compare(o2.getAmount(), o1.getAmount());
//            }
//        });
//        return list;
//    }
//    
//    public List<DebtDetail> sortDebtByAmountLowHight(int idDebtor){
//        List<DebtDetail> list = getDebtByIdAccountAndIdDebtor(idDebtor);
//        Collections.sort(list, new Comparator<DebtDetail>(){
//            @Override
//            public int compare(DebtDetail o1, DebtDetail o2) {
//                return Double.compare(o1.getAmount(), o2.getAmount());
//            }
//        });
//        return list;
//    }
    
    public List<DebtDetail> searchDebtByAmount(String input){
        List<DebtDetail> debtList = new ArrayList<>();
//        String sql = "SELECT * FROM debtdetails WHERE amount LIKE '%"+ inputAmount +"%'";
        //String sql = "SELECT * FROM debtdetails WHERE amount = "+input;
        String sql1 = "SELECT * FROM debtdetails WHERE debtType = "+ input;
        try {
            PreparedStatement ps = db.getConnection().prepareStatement(sql1);
            //ps.setInt(1, idDebtor);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                int id = rs.getInt("id");
                String description = rs.getString("description");
                boolean debtType = rs.getBoolean("debtType");
                double amount = rs.getDouble("amount");
                Timestamp createAt = rs.getTimestamp("createdAt");
                DebtDetail debt = new DebtDetail(id, description, debtType, amount, createAt);
                debtList.add(debt);
                
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }

        return debtList;
    }
    
    public int findTotalRecord() {
        int totalRecord = 0;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            String sql = "SELECT COUNT(*) FROM debtdetails";
            
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
     
     public List<DebtDetail> findByPage(int pageNumber) {
        List<DebtDetail> debtList = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet resultSet = null;

        try {
            int offset = (pageNumber - 1) * Pagination.RECORD_PER_PAGE;
            String sql = "SELECT * FROM debtdetails ORDER BY id LIMIT ? OFFSET ?";
            ps = db.getConnection().prepareStatement(sql);
            ps.setInt(1, Pagination.RECORD_PER_PAGE);
            ps.setInt(2, offset);
            resultSet = ps.executeQuery();

            while (resultSet.next()) {
                DebtDetail debt = new DebtDetail();
                debt.setId(resultSet.getInt("id"));
                debt.setDescription(resultSet.getString("description"));
                debt.setDeptType(resultSet.getBoolean("debtType"));
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

     
//   public int findTotalRecordBySortByOldest() {
//    int totalRecords = 0;
//    PreparedStatement ps = null;
//    ResultSet resultSet = null;
//
//    try {
//        String sql = "SELECT COUNT(*) AS totalRecords FROM debtdetails";
//        ps = db.getConnection().prepareStatement(sql);
//        resultSet = ps.executeQuery();
//
//        if (resultSet.next()) {
//            totalRecords = resultSet.getInt("totalRecords");
//        }
//    } catch (SQLException e) {
//        e.printStackTrace();
//    }
//
//    return totalRecords;
//}

public List<DebtDetail> findByPageAndSortByOldest(int pageNumber) {
    List<DebtDetail> debtList = new ArrayList<>();
    PreparedStatement ps = null;
    ResultSet resultSet = null;

    try {
        int offset = (pageNumber - 1) * Pagination.RECORD_PER_PAGE;
        String sql = "SELECT * FROM debtdetails ORDER BY createdAt ASC LIMIT ? OFFSET ?";
        ps = db.getConnection().prepareStatement(sql);
        ps.setInt(1, Pagination.RECORD_PER_PAGE);
        ps.setInt(2, offset);
        resultSet = ps.executeQuery();

        while (resultSet.next()) {
            DebtDetail debt = new DebtDetail();
            debt.setId(resultSet.getInt("id"));
            debt.setDescription(resultSet.getString("description"));
            debt.setDeptType(resultSet.getBoolean("debtType"));
            debt.setAmount(resultSet.getDouble("amount"));
            debt.setCreatAt(resultSet.getTimestamp("createdAt"));
            debtList.add(debt);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }

    return debtList;
}


public List<DebtDetail> findByPageAndSortByNewest(int page) {
        List<DebtDetail> debtList = new ArrayList<>();
        int offset = (page - 1) * Pagination.RECORD_PER_PAGE;

        try {
            String sql = "SELECT * FROM debtdetails ORDER BY createdAt DESC LIMIT ? OFFSET ?";
            PreparedStatement statement = db.getConnection().prepareStatement(sql);
            statement.setInt(1, Pagination.RECORD_PER_PAGE);
            statement.setInt(2, offset);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                DebtDetail debt = new DebtDetail();
                debt.setId(resultSet.getInt("id"));
                debt.setDescription(resultSet.getString("description"));
                debt.setDeptType(resultSet.getBoolean("debtType"));
                debt.setAmount(resultSet.getDouble("amount"));
                debt.setCreatAt(resultSet.getTimestamp("createdAt"));
                debtList.add(debt);
            }

            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return debtList;
    }

public List<DebtDetail> findByPageAndSortDebtByAmountHighLow(int page) {
        List<DebtDetail> debtList = new ArrayList<>();
        int offset = (page - 1) * Pagination.RECORD_PER_PAGE;

        try {
            String sql = "SELECT * FROM debtdetails ORDER BY amount DESC LIMIT ? OFFSET ?";
            PreparedStatement statement = db.getConnection().prepareStatement(sql);
            statement.setInt(1, Pagination.RECORD_PER_PAGE);
            statement.setInt(2, offset);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                DebtDetail debt = new DebtDetail();
                debt.setId(resultSet.getInt("id"));
                debt.setDescription(resultSet.getString("description"));
                debt.setDeptType(resultSet.getBoolean("debtType"));
                debt.setAmount(resultSet.getDouble("amount"));
                debt.setCreatAt(resultSet.getTimestamp("createdAt"));
                debtList.add(debt);
            }

            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return debtList;
    }

public List<DebtDetail> findByPageAndSortDebtByAmountLowHigh(int page) {
        List<DebtDetail> debtList = new ArrayList<>();
        int offset = (page - 1) * Pagination.RECORD_PER_PAGE;

        try {
            String sql = "SELECT * FROM debtdetails ORDER BY amount ASC LIMIT ? OFFSET ?";
            PreparedStatement statement = db.getConnection().prepareStatement(sql);
            statement.setInt(1, Pagination.RECORD_PER_PAGE);
            statement.setInt(2, offset);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                DebtDetail debt = new DebtDetail();
                debt.setId(resultSet.getInt("id"));
                debt.setDescription(resultSet.getString("description"));
                debt.setDeptType(resultSet.getBoolean("debtType"));
                debt.setAmount(resultSet.getDouble("amount"));
                debt.setCreatAt(resultSet.getTimestamp("createdAt"));
                debtList.add(debt);
            }

            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return debtList;
    }
public int findTotalRecordByDebtType(boolean debtType) {
        int totalRecord = 0;

        try {
            String sql = "SELECT COUNT(*) FROM debtdetails WHERE debtType = ?";
            PreparedStatement statement = db.getConnection().prepareStatement(sql);
            statement.setBoolean(1, debtType);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                totalRecord = resultSet.getInt(1);
            }

            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return totalRecord;
    }

public List<DebtDetail> findByPageByDebtType(boolean debtType, int page) {
        List<DebtDetail> debtList = new ArrayList<>();
        int offset = (page - 1) * Pagination.RECORD_PER_PAGE;

        try {
            String sql = "SELECT * FROM debtdetails WHERE debtType = ? ORDER BY id LIMIT ? OFFSET ?";
            PreparedStatement statement = db.getConnection().prepareStatement(sql);
            statement.setBoolean(1, debtType);
            statement.setInt(2, Pagination.RECORD_PER_PAGE);
            statement.setInt(3, offset);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                DebtDetail debt = new DebtDetail();
                debt.setId(resultSet.getInt("id"));
                debt.setDescription(resultSet.getString("description"));
                debt.setDeptType(resultSet.getBoolean("debtType"));
                debt.setAmount(resultSet.getDouble("amount"));
                debt.setCreatAt(resultSet.getTimestamp("createdAt"));
                debtList.add(debt);
            }

            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return debtList;
    }

public int findTotalRecordByDescription(String description) {
        int totalRecord = 0;

        try {
            String sql = "SELECT COUNT(*) FROM debtdetails WHERE description LIKE ?";
            PreparedStatement statement = db.getConnection().prepareStatement(sql);
            statement.setString(1, "%" + description + "%");

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                totalRecord = resultSet.getInt(1);
            }

            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return totalRecord;
    }

 public List<DebtDetail> findByPageByDescription(String description, int page) {
        List<DebtDetail> debtList = new ArrayList<>();
        int offset = (page - 1) * Pagination.RECORD_PER_PAGE;

        try {
            String sql = "SELECT * FROM debtdetails WHERE description LIKE ? ORDER BY id LIMIT ? OFFSET ?";
            PreparedStatement statement = db.getConnection().prepareStatement(sql);
            statement.setString(1, "%" + description + "%");
            statement.setInt(2, Pagination.RECORD_PER_PAGE);
            statement.setInt(3, offset);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                DebtDetail debt = new DebtDetail();
                debt.setId(resultSet.getInt("id"));
                debt.setDescription(resultSet.getString("description"));
                debt.setDeptType(resultSet.getBoolean("debtType"));
                debt.setAmount(resultSet.getDouble("amount"));
                debt.setCreatAt(resultSet.getTimestamp("createdAt"));
                debtList.add(debt);
            }

            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return debtList;
    }
 
 public int findTotalRecordByAmount(double amount) {
    int totalRecord = 0;
    PreparedStatement statement = null;
    ResultSet resultSet = null;

    try {
        String sql = "SELECT COUNT(*) FROM debtdetails WHERE amount = ?";
        statement = db.getConnection().prepareStatement(sql);
        statement.setDouble(1, amount);
        resultSet = statement.executeQuery();

        if (resultSet.next()) {
            totalRecord = resultSet.getInt(1);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    } finally {
        // Đóng kết nối và tài nguyên
        // (resultSet, statement, connection) ở đây
    }

    return totalRecord;
}
 
 public List<DebtDetail> findByPageByAmount(double amount, int pageNumber) {
    List<DebtDetail> debtList = new ArrayList<>();
    PreparedStatement ps = null;
    ResultSet resultSet = null;

    try {
        int offset = (pageNumber - 1) * Pagination.RECORD_PER_PAGE;
        String sql = "SELECT * FROM debtdetails WHERE amount = ? ORDER BY id LIMIT ? OFFSET ?";
        ps = db.getConnection().prepareStatement(sql);
        ps.setDouble(1, amount);
        ps.setInt(2, Pagination.RECORD_PER_PAGE);
        ps.setInt(3, offset);
        resultSet = ps.executeQuery();

        while (resultSet.next()) {
            DebtDetail debt = new DebtDetail();
            debt.setId(resultSet.getInt("id"));
            debt.setDescription(resultSet.getString("description"));
            debt.setDeptType(resultSet.getBoolean("debtType"));
            debt.setAmount(resultSet.getDouble("amount"));
            debt.setCreatAt(resultSet.getTimestamp("createdAt"));
            debtList.add(debt);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    } finally {
        // Đóng kết nối và tài nguyên
        // (resultSet, ps, connection) ở đây
    }

    return debtList;
}

    public static void main(String[] args) {
        DebtDAO dao = new DebtDAO();
//        int n = dao.addDebt( new DebtDetail("Thuong dang buon ngu y", true, 14000, 7, 2, 1));
//        if(n>0){
//            System.out.println("sucess");
//        }else{
//            System.out.println("sai tè le");
//        }
//        for (DebtDetail debtDetail : dao.findTotalRecordByReceivable(true)) {
//            System.out.println(debtDetail);
//        }

    }
}
