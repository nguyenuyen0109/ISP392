/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.FeedbackDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import model.Feedback;
import model.PageControl;
import utils.Pagination;

/**
 *
 * @author admin
 */
public class FeedbackController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("USER") == null) {
            request.getRequestDispatcher("/client/login.jsp").forward(request, response);
            return;
        }
        Integer account_id = (Integer) session.getAttribute("account_id");
        if (account_id == null) {

            request.getRequestDispatcher("/client/login.jsp").forward(request, response);

            return;
        }
        try {
            double rating = Double.parseDouble(request.getParameter("rating"));
            String opinion = request.getParameter("opinion");

            Feedback feedback = new Feedback();
            FeedbackDAO dao = new FeedbackDAO();
            feedback.setAccount_id(account_id);
            feedback.setRate(rating);
            feedback.setFeedback(opinion);
            java.sql.Timestamp Timestamp = new java.sql.Timestamp(System.currentTimeMillis());
            feedback.setCreateAt(Timestamp);
            feedback.setIsDeleted(false);
            dao.addFeedback(feedback);

            request.getRequestDispatcher("/client/homepage.jsp").forward(request, response);

        } catch (Exception e) {

            throw new ServletException(e);
        }
    }
}
