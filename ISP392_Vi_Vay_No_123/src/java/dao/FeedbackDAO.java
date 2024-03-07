/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import dal.DBContext;
import model.Feedback;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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
}