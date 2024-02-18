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

/**
 *
 * @author Admin
 */
public class DebtDAO {

    DBContext db;

    public DebtDAO() {
        db = DBContext.getInstance();
    }

    public int addDebt(DebtDetail debt,int account_) {
        int n = 0;
        String sql = "insert into debtdetails ("
                + "description, "
                + "debtType, "
                + "amount, "
                + "debtor_id, "
                + "debtor_account_id, "
                + "interest_rate_id)\n"
                + "values (?,?,?,?,?,?);"
                + "";
        try {
            PreparedStatement ps = db.getConnection().prepareStatement(sql);
            ps.setString(1, debt.getDescription());
            ps.setBoolean(2, debt.isDebtType()); //true=ngta vay, false= mình nợ
            ps.setDouble(3, debt.getAmount());
            ps.setInt(4, debt.getDebtor_IdDebtor());
            ps.setInt(5, debt.getIdAccount());
            ps.setInt(6, debt.getInterest_rate_id());
            n = ps.executeUpdate();
            System.out.println(sql);
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return n;
    }

    public List<DebtDetail> getDebtByIdAccountAndIdDebtor(int idDebtor, int account_id) {
        List<DebtDetail> debtList = new ArrayList<>();
        String sql = "select * from debtdetails where debtor_id=? and account_id = ?";
        try (PreparedStatement preparedStatement = db.getConnection().prepareStatement(sql)) {
            preparedStatement.setInt(2, account_id);
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
    
    

    public List<DebtDetail> sortDebtByOldest(int idDebtor,int accountid) {
        List<DebtDetail> list = getDebtByIdAccountAndIdDebtor(idDebtor,accountid);
        // Sắp xếp theo ngày tạo cũ nhất (tăng dần)
        Collections.sort(list, Comparator.comparing(DebtDetail::getCreatAt));
        return list;
    }

    public List<DebtDetail> sortDebtByNewest(int idDebtor,int accountid) {
        List<DebtDetail> list = getDebtByIdAccountAndIdDebtor(idDebtor, accountid);
        // Sắp xếp theo ngày tạo mới nhất (giảm dần)
        Collections.sort(list, Collections.reverseOrder(Comparator.comparing(DebtDetail::getCreatAt)));
        return list;
    }

    public List<DebtDetail> sortDebtByAmountHightLow(int idDebtor,int accountid) {
        List<DebtDetail> list = getDebtByIdAccountAndIdDebtor(idDebtor,accountid);
        Collections.sort(list, new Comparator<DebtDetail>() {
            @Override
            public int compare(DebtDetail o1, DebtDetail o2) {
                return Double.compare(o2.getAmount(), o1.getAmount());
            }
        });
        return list;
    }

    public List<DebtDetail> sortDebtByAmountLowHight(int idDebtor,int accountid) {
        List<DebtDetail> list = getDebtByIdAccountAndIdDebtor(idDebtor, accountid);
        Collections.sort(list, new Comparator<DebtDetail>() {
            @Override
            public int compare(DebtDetail o1, DebtDetail o2) {
                return Double.compare(o1.getAmount(), o2.getAmount());
            }
        });
        return list;
    }

    public List<DebtDetail> searchDebtByAmount(int idDebtor,int accountid,String input) {
        List<DebtDetail> debtList = new ArrayList<>();
//        String sql = "SELECT * FROM debtdetails WHERE amount LIKE '%"+ inputAmount +"%'";
        String sql = "select * from debtdetails where debtor_id= ? and account_id = ?  and amount like '%?%'";
        try (PreparedStatement preparedStatement = db.getConnection().prepareStatement(sql)) {
            preparedStatement.setInt(1, idDebtor);
            preparedStatement.setInt(2, accountid);
            preparedStatement.setString(3, input);
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
        String sql = "select * from debtdetails where debtor_id= ? and debtor_account_id = ? and input like '%?%'";
        try (PreparedStatement preparedStatement = db.getConnection().prepareStatement(sql)) {
            preparedStatement.setInt(2, accountid);
            preparedStatement.setInt(1, idDebtor);
            preparedStatement.setString(3, input);
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
        String sql = "select * from debtdetails where debtor_id= and account_id and debtType=?";
        try (PreparedStatement preparedStatement = db.getConnection().prepareStatement(sql)) {
            preparedStatement.setInt(2, accountid);
            preparedStatement.setInt(1, idDebtor);
            preparedStatement.setString(3, action);
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

    public static void main(String[] args) {
        DebtDAO dao = new DebtDAO();
        List<DebtDetail> list = dao.searchDebtByDescription(7,6, "test");
        for (DebtDetail debtDetail : list) {
            System.out.println(debtDetail);
        }

    }
}
