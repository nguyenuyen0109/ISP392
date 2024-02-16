/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import dal.DBContext;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import model.DebtDetail;

/**
 *
 * @author Admin
 */
public class DebtDAO {
    DBContext db;

    public DebtDAO() {
        db = DBContext.getInstance();
    }
    
    public List<DebtDetail> getDebtByIdAccountAndIdDebtor(int idDebtor) {
        List<DebtDetail> debtList = new ArrayList<>();
        String sql = "select * from debtdetails where debtor_id=?";
        try {
            PreparedStatement ps = db.getConnection().prepareStatement(sql);
            ps.setInt(1, idDebtor);
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
    
    public List<DebtDetail> sortDebtByOldest(int idDebtor){
        List<DebtDetail> list = getDebtByIdAccountAndIdDebtor(idDebtor);
        // Sắp xếp theo ngày tạo cũ nhất (tăng dần)
        Collections.sort(list, Comparator.comparing(DebtDetail::getCreatAt));
        return list;
    }
    
    public List<DebtDetail> sortDebtByNewest(int idDebtor){
        List<DebtDetail> list = getDebtByIdAccountAndIdDebtor(idDebtor);
        // Sắp xếp theo ngày tạo mới nhất (giảm dần)
        Collections.sort(list, Collections.reverseOrder(Comparator.comparing(DebtDetail::getCreatAt)));
        return list;
    }

    public List<DebtDetail> sortDebtByAmountHightLow(int idDebtor){
        List<DebtDetail> list = getDebtByIdAccountAndIdDebtor(idDebtor);
        Collections.sort(list, new Comparator<DebtDetail>(){
            @Override
            public int compare(DebtDetail o1, DebtDetail o2) {
                return Double.compare(o2.getAmount(), o1.getAmount());
            }
        });
        return list;
    }
    
    public List<DebtDetail> sortDebtByAmountLowHight(int idDebtor){
        List<DebtDetail> list = getDebtByIdAccountAndIdDebtor(idDebtor);
        Collections.sort(list, new Comparator<DebtDetail>(){
            @Override
            public int compare(DebtDetail o1, DebtDetail o2) {
                return Double.compare(o1.getAmount(), o2.getAmount());
            }
        });
        return list;
    }
    
    public List<DebtDetail> filterByReceivable(int idDebtor, String action){
        List<DebtDetail> debtList = new ArrayList<>();
        String sql = "select * from debtdetails where debtor_id="+idDebtor+" and debtType= "+action ;
        try {
            PreparedStatement ps = db.getConnection().prepareStatement(sql);
//            ps.setInt(1, idDebtor);
//            ps.setString(2, action);
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
}
