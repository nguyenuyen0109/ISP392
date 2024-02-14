/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import dal.DBContext;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
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
    
    public List<Debtor> getAllDebtors() {
        List<Debtor> debtors = new ArrayList<>();
        String query = "SELECT * FROM debtor";
        try (PreparedStatement preparedStatement = db.getConnection().prepareStatement(query); ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String address = resultSet.getString("address");
                String phone = resultSet.getString("phone");
                String email = resultSet.getString("email");
                double totalDebt = resultSet.getDouble("totalDebt");
                java.sql.Timestamp createdAt = resultSet.getTimestamp("createdAt");
                java.sql.Timestamp updatedAt = resultSet.getTimestamp("updatedAt");
                int account_id = resultSet.getInt("account_id");
                int creditor_account_id = resultSet.getInt("creditor_account_id");
                //Account account = new Account();
                Debtor debtor;
                debtor = new Debtor(id, name, address, phone, email, totalDebt, createdAt, updatedAt, account_id, creditor_account_id);
                debtors.add(debtor);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return debtors;
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
        
     public int findTotalRecord() {
        int totalRecord = 0;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            String sql = "SELECT COUNT(*) FROM debtor";
            
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
     
     public List<Debtor> findByPage(int pageNumber) {
        List<Debtor> debtorList = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet resultSet = null;

        try {
            int offset = (pageNumber - 1) * Pagination.RECORD_PER_PAGE;
            String sql = "SELECT * FROM debtor ORDER BY id LIMIT ? OFFSET ?";
            ps = db.getConnection().prepareStatement(sql);
            ps.setInt(1, Pagination.RECORD_PER_PAGE);
            ps.setInt(2, offset);
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
                debtorList.add(debtor);
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

        return debtorList;
    }


    
    public static void main(String[] args) {
        DebtorDAO debtorDAO = new DebtorDAO();
//        for (Debtor debtor : new DebtorDAO().getAllDebtors()) {
//            System.out.println(debtor);
//        }
        
        for (Debtor debtor : debtorDAO.findByPage(2)) {
            System.out.println(debtor);
        }
    }
}
