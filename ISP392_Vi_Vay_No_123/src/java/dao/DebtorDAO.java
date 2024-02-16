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

/**
 *
 * @author ADMIN
 */
public class DebtorDAO {

    DBContext db;

    public DebtorDAO() {
        db = DBContext.getInstance();
    }
    public List<Debtor> getAllDebtors(int account_id) {
        List<Debtor> debtors = new ArrayList<>();
        String query = "SELECT * FROM debtor where account_id = ?";
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
    public boolean addDebtor(Debtor debtor) {
        String sql = "INSERT INTO debtor "
                + "(name, address, phone, email,totalDebt,account_id ) VALUES (?, ?, ?, ?,?,?)";
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
    public List<Debtor> sortDebtorByOldest(int account_id){
       List<Debtor> list = getAllDebtors (account_id);
        // Sắp xếp theo ngày tạo cũ nhất (tăng dần)
        Collections.sort(list, Collections.reverseOrder(Comparator.comparing(Debtor::getCreatedAt)));
        return list;
    }

    
    public List<Debtor> sortDebtorByNewest(int account_id){
        List<Debtor> list = getAllDebtors (account_id);
        // Sắp xếp theo ngày tạo mới nhất (giảm dần)
        Collections.sort(list, Collections.reverseOrder(Comparator.comparing(Debtor::getCreatedAt)));
        return list;
    }
    
    public List<Debtor> sortDebtorByTotalDebtLargest(int account_id){
        List<Debtor> list = getAllDebtors(account_id);
        Collections.sort(list, new Comparator<Debtor>(){
            @Override
            public int compare(Debtor o1, Debtor o2) {
                return Double.compare(o2.getTotalDebt(), o1.getTotalDebt());
            }
        });
        return list;
    }
    
     public List<Debtor> sortDebtorByTotalDebtSmallest(int account_id) {
        List<Debtor> list = getAllDebtors(account_id);
        Collections.sort(list, new Comparator<Debtor>(){
            @Override
            public int compare(Debtor o1, Debtor o2) {
                return Double.compare(o1.getTotalDebt(), o2.getTotalDebt());
            }
        });
        return list;
    }
     
    public List<Debtor> getDebtorsByName(String keyword) {
        //here
        List<Debtor> debtors = new ArrayList<>();
        String query = "select * from debtor\n"
                + " where " + keyword;
        try {
            PreparedStatement preparedStatement = db.getConnection().prepareStatement(query);
            //preparedStatement.setObject(1, keyword);
            ResultSet resultSet = preparedStatement.executeQuery();
            //

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

    
    public static void main(String[] args) {
        // Instantiate the DebtorDAO class
        DebtorDAO debtorDAO = new DebtorDAO();

        // Create a Debtor object with sample data
        // Assuming there is a constructor in Debtor class that takes all these parameters
        Debtor newDebtor = new Debtor(
                // Assuming the Debtor constructor does not require an ID because it's auto-incremented in the database
                "John Doe", // name
                "123 Main St, Melbourne", // address
                "0400000000", // phone
                "johndoe@example.com",
                5000,// email
                2
        //                new java.sql.Timestamp(System.currentTimeMillis()), // createdAt, assuming this will be overwritten by the database or constructor
        //                new java.sql.Timestamp(System.currentTimeMillis()), // updatedAt, assuming this will be overwritten by the constructor

        );


        // Get all debtors
        List<Debtor> allDebtors = debtorDAO.getAllDebtors(2);

        // Print the list of debtors
        if (allDebtors.isEmpty()) {
            System.out.println("No debtors found.");
        } else {
            System.out.println("List of Debtors:");
            for (Debtor debtor : allDebtors) {
                System.out.println(debtor);
            }
        }
    }
}