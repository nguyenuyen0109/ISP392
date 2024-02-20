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

public List<Debtor> findByPageAndSortByOldest(int pageNumber) {
    List<Debtor> debtorList = new ArrayList<>();
    PreparedStatement ps = null;
    ResultSet resultSet = null;

    try {
        int offset = (pageNumber - 1) * Pagination.RECORD_PER_PAGE;
        String sql = "SELECT * FROM debtor ORDER BY createdAt ASC LIMIT ? OFFSET ?";
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
    }

    return debtorList;
}

    public List<Debtor> findByPageAndSortByNewest(int page) {
        List<Debtor> debtorList = new ArrayList<>();
        int offset = (page - 1) * Pagination.RECORD_PER_PAGE;

        try {
            String sql = "SELECT * FROM debtor ORDER BY createdAt DESC LIMIT ? OFFSET ?";
            PreparedStatement statement = db.getConnection().prepareStatement(sql);
            statement.setInt(1, Pagination.RECORD_PER_PAGE);
            statement.setInt(2, offset);

            ResultSet resultSet = statement.executeQuery();

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

            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return debtorList;
    }
    
    public List<Debtor> findByPageAndSortDebtByAmountHighLow(int page) {
        List<Debtor> debtorList = new ArrayList<>();
        int offset = (page - 1) * Pagination.RECORD_PER_PAGE;

        try {
            String sql = "SELECT * FROM debtor ORDER BY totalDebt DESC LIMIT ? OFFSET ?";
            PreparedStatement statement = db.getConnection().prepareStatement(sql);
            statement.setInt(1, Pagination.RECORD_PER_PAGE);
            statement.setInt(2, offset);

            ResultSet resultSet = statement.executeQuery();

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

            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return debtorList;
    }
    
    public List<Debtor> findByPageAndSortDebtByAmountLowHigh(int page) {
        List<Debtor> debtorList = new ArrayList<>();
        int offset = (page - 1) * Pagination.RECORD_PER_PAGE;

        try {
            String sql = "SELECT * FROM debtor ORDER BY totalDebt ASC LIMIT ? OFFSET ?";
            PreparedStatement statement = db.getConnection().prepareStatement(sql);
            statement.setInt(1, Pagination.RECORD_PER_PAGE);
            statement.setInt(2, offset);

            ResultSet resultSet = statement.executeQuery();

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

            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return debtorList;
    }
    
    public int findTotalRecordByName(String name) {
    int totalRecord = 0;
    PreparedStatement statement = null;
    ResultSet resultSet = null;

    try {
        String sql = "SELECT COUNT(*) FROM debtor WHERE name LIKE ?";
        statement = db.getConnection().prepareStatement(sql);
        statement.setString(1, "%" + name + "%"); // Sử dụng dấu % để tìm kiếm bất kỳ bản ghi nào chứa tên này

        resultSet = statement.executeQuery();

        if (resultSet.next()) {
            totalRecord = resultSet.getInt(1);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }

    return totalRecord;
}
    
    public List<Debtor> findByPageByName(String name, int pageNumber) {
    List<Debtor> debtorList = new ArrayList<>();
    PreparedStatement ps = null;
    ResultSet resultSet = null;

    try {
        int offset = (pageNumber - 1) * Pagination.RECORD_PER_PAGE;
        String sql = "SELECT * FROM debtor WHERE name LIKE ? ORDER BY id LIMIT ? OFFSET ?";
        ps = db.getConnection().prepareStatement(sql);
        ps.setString(1, "%" + name + "%"); // Sử dụng dấu % để tìm kiếm bất kỳ bản ghi nào chứa tên này
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
                debtorList.add(debtor);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }

    return debtorList;
}
    
    public int findTotalRecordByAddress(String address) {
    int totalRecord = 0;
    PreparedStatement statement = null;
    ResultSet resultSet = null;

    try {
        String sql = "SELECT COUNT(*) FROM debtor WHERE address LIKE ?";
        statement = db.getConnection().prepareStatement(sql);
        statement.setString(1, "%" + address + "%"); // Sử dụng dấu % để tìm kiếm bất kỳ bản ghi nào chứa địa chỉ này
        resultSet = statement.executeQuery();

        if (resultSet.next()) {
            totalRecord = resultSet.getInt(1);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }

    return totalRecord;
}

public List<Debtor> findByPageByAddress(String address, int pageNumber) {
    List<Debtor> debtorList = new ArrayList<>();
    PreparedStatement ps = null;
    ResultSet resultSet = null;

    try {
        int offset = (pageNumber - 1) * Pagination.RECORD_PER_PAGE;
        String sql = "SELECT * FROM debtor WHERE address LIKE ? ORDER BY id LIMIT ? OFFSET ?";
        ps = db.getConnection().prepareStatement(sql);
        ps.setString(1, "%" + address + "%"); // Sử dụng dấu % để tìm kiếm bất kỳ bản ghi nào chứa địa chỉ này
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
                debtorList.add(debtor);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }

    return debtorList;
}

public int findTotalRecordByPhone(String phone) {
    int totalRecord = 0;
    PreparedStatement statement = null;
    ResultSet resultSet = null;

    try {
        String sql = "SELECT COUNT(*) FROM debtor WHERE phone LIKE ?";
        statement = db.getConnection().prepareStatement(sql);
        statement.setString(1, "%" + phone + "%"); // Sử dụng dấu % để tìm kiếm bất kỳ bản ghi nào chứa số điện thoại này
        resultSet = statement.executeQuery();

        if (resultSet.next()) {
            totalRecord = resultSet.getInt(1);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }

    return totalRecord;
}

public List<Debtor> findByPageByPhone(String phone, int pageNumber) {
    List<Debtor> debtorList = new ArrayList<>();
    PreparedStatement ps = null;
    ResultSet resultSet = null;

    try {
        int offset = (pageNumber - 1) * Pagination.RECORD_PER_PAGE;
        String sql = "SELECT * FROM debtor WHERE phone LIKE ? ORDER BY id LIMIT ? OFFSET ?";
        ps = db.getConnection().prepareStatement(sql);
        ps.setString(1, "%" + phone + "%"); // Sử dụng dấu % để tìm kiếm bất kỳ bản ghi nào chứa số điện thoại này
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
                debtorList.add(debtor);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }

    return debtorList;
}

public int findTotalRecordByEmail(String email) {
    int totalRecord = 0;
    PreparedStatement statement = null;
    ResultSet resultSet = null;

    try {
        String sql = "SELECT COUNT(*) FROM debtor WHERE email LIKE ?";
        statement = db.getConnection().prepareStatement(sql);
        statement.setString(1, "%" + email + "%"); // Sử dụng dấu % để tìm kiếm bất kỳ bản ghi nào chứa địa chỉ email này
        resultSet = statement.executeQuery();

        if (resultSet.next()) {
            totalRecord = resultSet.getInt(1);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }

    return totalRecord;
}

public List<Debtor> findByPageByEmail(String email, int pageNumber) {
    List<Debtor> debtorList = new ArrayList<>();
    PreparedStatement ps = null;
    ResultSet resultSet = null;

    try {
        int offset = (pageNumber - 1) * Pagination.RECORD_PER_PAGE;
        String sql = "SELECT * FROM debtor WHERE email LIKE ? ORDER BY id LIMIT ? OFFSET ?";
        ps = db.getConnection().prepareStatement(sql);
        ps.setString(1, "%" + email + "%"); // Sử dụng dấu % để tìm kiếm bất kỳ bản ghi nào chứa địa chỉ email này
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
                debtorList.add(debtor);
        }
    } catch (SQLException e) {
        e.printStackTrace();
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
