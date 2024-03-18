/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import dal.DBContext;
import model.Feedback;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import utils.Pagination;


/**
 *
 * @author admin
 */
public class FeedbackDAO {

    private DBContext db;

    public FeedbackDAO() {
        db = DBContext.getInstance();
    }

    public boolean addFeedback(Feedback feedback) {

        String sql = "INSERT INTO feedback "
                + "( id,account_id, feedback, rate, img, createAt, isDeleted) "
                + "VALUES (?,?, ?, ?, ?, ?, ?)";

        // Get current timestamp
        java.sql.Timestamp currentTimestamp = new java.sql.Timestamp(System.currentTimeMillis());

        try (PreparedStatement statement = db.getConnection().prepareStatement(sql)) {
            statement.setInt(1, feedback.getId());
            statement.setInt(2, feedback.getAccount_id());
            statement.setString(3, feedback.getFeedback());
            statement.setDouble(4, feedback.getRate());
            statement.setString(5, feedback.getImg());
            statement.setTimestamp(6, currentTimestamp);
            statement.setBoolean(7, false);

            int rowsInserted = statement.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void main(String[] args) {
        // Tạo một đối tượng Feedback
        Feedback feedback = new Feedback();
        feedback.setId(1);
        feedback.setAccount_id(2);
        feedback.setFeedback("Great service!");
        feedback.setRate(4);
        FeedbackDAO feedbackDAO = new FeedbackDAO();

        // Thêm phản hồi vào cơ sở dữ liệu
        boolean isSuccess = feedbackDAO.addFeedback(feedback);

        // Kiểm tra kết quả thêm phản hồi
        if (isSuccess) {
            System.out.println("Feedback added successfully!");
        } else {
            System.out.println("Failed to add feedback.");
        }
    }

    public int findTotalRecord() {
        int totalRecords = 0;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            // Chuẩn bị truy vấn để lấy tổng số bản ghi
            String sql = "SELECT COUNT(*) FROM feedback";
            statement = db.getConnection().prepareStatement(sql);

            // Thực thi truy vấn
            resultSet = statement.executeQuery();

            // Lấy tổng số bản ghi từ kết quả
            if (resultSet.next()) {
                totalRecords = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            // Xử lý ngoại lệ SQL
            e.printStackTrace();
        }

        return totalRecords;
    }

     public List<Feedback> findByPage(int pageNumber) {
        List<Feedback> listFeedback = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet resultSet = null;

        try {
            int offset = (pageNumber - 1) * Pagination.RECORD_PER_PAGE;
            String sql = "SELECT * FROM feedback ORDER BY id LIMIT ? OFFSET ?";
            ps = db.getConnection().prepareStatement(sql);
            ps.setInt(1, Pagination.RECORD_PER_PAGE);
            ps.setInt(2, offset);
            resultSet = ps.executeQuery();

            while (resultSet.next()) {
                Feedback feedback = new Feedback();
                feedback.setId(resultSet.getInt("id"));
                feedback.setRate(resultSet.getDouble("rate"));
                feedback.setCreateAt(resultSet.getTimestamp("createAt"));
                feedback.setFeedback(resultSet.getString("feedback"));
                listFeedback.add(feedback);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listFeedback;
    }

    public List<Feedback> findByPageAndSortByOldest(int pageNumber) {
        List<Feedback> listFeedback = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet resultSet = null;

        try {
            int offset = (pageNumber - 1) * Pagination.RECORD_PER_PAGE;
            String sql = "SELECT * FROM feedback ORDER BY createAt ASC LIMIT ? OFFSET ?";
            ps = db.getConnection().prepareStatement(sql);
            ps.setInt(1, Pagination.RECORD_PER_PAGE);
            ps.setInt(2, offset);
            resultSet = ps.executeQuery();

            while (resultSet.next()) {
                Feedback feedback = new Feedback();
                feedback.setId(resultSet.getInt("id"));
                feedback.setRate(resultSet.getDouble("rate"));
                feedback.setCreateAt(resultSet.getTimestamp("createAt"));
                feedback.setFeedback(resultSet.getString("feedback"));
                listFeedback.add(feedback);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listFeedback;
    }

    public List<Feedback> findByPageAndSortByNewest(int pageNumber) {
        List<Feedback> listFeedback = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet resultSet = null;

        try {
            int offset = (pageNumber - 1) * Pagination.RECORD_PER_PAGE;
            String sql = "SELECT * FROM feedback ORDER BY createAt DESC LIMIT ? OFFSET ?";
            ps = db.getConnection().prepareStatement(sql);
            ps.setInt(1, Pagination.RECORD_PER_PAGE);
            ps.setInt(2, offset);
            resultSet = ps.executeQuery();

            while (resultSet.next()) {
                Feedback feedback = new Feedback();
                feedback.setId(resultSet.getInt("id"));
                feedback.setRate(resultSet.getDouble("rate"));
                feedback.setCreateAt(resultSet.getTimestamp("createAt"));
                feedback.setFeedback(resultSet.getString("feedback"));
                listFeedback.add(feedback);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listFeedback;
    }

    public List<Feedback> findByPageAndSortByLowRate() {
        List<Feedback> listFeedback = new ArrayList<>();
        try {
            try (PreparedStatement statement = db.getConnection().prepareStatement(
                    "SELECT * FROM feedback ORDER BY rate ASC")) {

                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        Feedback feedback = new Feedback();
                        feedback.setId(resultSet.getInt("id"));
                        feedback.setRate(resultSet.getDouble("rate"));
                        feedback.setCreateAt(resultSet.getTimestamp("createAt"));
                        feedback.setFeedback(resultSet.getString("feedback"));

                        listFeedback.add(feedback);
                    }
                }
            }
        } catch (SQLException e) {
            // Xử lý hoặc ghi log cho exception
            e.printStackTrace();
        }
        return listFeedback;
    }

     public List<Feedback> findByPageAndSortByHightRate() {
        List<Feedback> listFeedback = new ArrayList<>();
        try {
            try (PreparedStatement statement = db.getConnection().prepareStatement(
                    "SELECT * FROM feedback ORDER BY rate DESC")) {

                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        Feedback feedback = new Feedback();
                        feedback.setId(resultSet.getInt("id"));
                        feedback.setRate(resultSet.getDouble("rate"));
                        feedback.setCreateAt(resultSet.getTimestamp("createAt"));
                        feedback.setFeedback(resultSet.getString("feedback"));

                        listFeedback.add(feedback);
                    }
                }
            }
        } catch (SQLException e) {
            // Xử lý hoặc ghi log cho exception
            e.printStackTrace();
        }
        return listFeedback;
    }

}
