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
    
    
}
