/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.AccountDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Account;
import utils.Captcha;
import utils.Mail;
import utils.Token;

/**
 *
 * @author admin
 */
public class FotgotPasswordController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String token = request.getParameter("t");

        if (token == null) {
            request.getRequestDispatcher("client/forgotpassword.jsp").forward(request, response);
            return;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String email = request.getParameter("email");

            //String captchaResponse = request.getParameter("h-captcha-response");
            AccountDAO accountDAO = new AccountDAO();
            Account account = accountDAO.getAccountByEmail(email);
            String alert = "";
            String url1 = "";
            // Check if the username or email already exists
            if (account != null) {
                String token = new Token().generateRandomToken(18);
                String url = "http://" + request.getServerName() + ":" + request.getServerPort()
                        + request.getContextPath() + "/reset-password?t=" + token + "&e=" + email;

                accountDAO.insertToken(account.getId(), token);
                new Mail().sendEmail(email, "Reset password", "Click here to reset password: " + url);
                url1 = "/client/forgotpassword.jsp";
                alert = "An email was sent!";
            } else {
                url1 = "/client/forgotpassword.jsp";
                alert = "Email is invalid";
            }
            request.setAttribute("alert", alert);
            request.getRequestDispatcher(url1).forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
