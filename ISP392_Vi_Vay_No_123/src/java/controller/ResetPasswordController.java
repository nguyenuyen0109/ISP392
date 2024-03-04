/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.AccountDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Account;
import utils.Hash;
import utils.Validate;

/**
 *
 * @author lvhn1
 */
@WebServlet(name = "ResetPassword", urlPatterns = {"/reset-password"})
public class ResetPasswordController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String email = request.getParameter("email");
        String token = request.getParameter("token");

        request.setAttribute("email", email);
        request.setAttribute("token", token);

        request.getRequestDispatcher("client/resetpassword.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String email = request.getParameter("email");
        String token = request.getParameter("token");

        String password = request.getParameter("password");
        String retypePassword = request.getParameter("confirmPassword");

        Account user = new AccountDAO().getAccountByEmail(email);
        AccountDAO dao = new AccountDAO();
        String alert = "";
        String url = "";
        if (Validate.isValidPassword(password)) {
            if (password.equals(retypePassword)) {
                if (user == null) {
                    alert = "Email not found";
                } else if (dao.checkToken(email, token) != null) {
                    user.setPassword(new Hash().hashPassword(password));
                    new AccountDAO().updatePassword(user.getUsername(), new Hash().hashPassword(password));
                    new AccountDAO().ResettToken(user.getId());
                    alert = "Reset password success";
                    url = "client/login.jsp";

                } else {
                    alert = "Invalid token!";
                    url = "client/resetpassword.jsp";
                }

            }
        } else {
             url = "client/resetpassword.jsp";
            alert = "Fail to reset password";
        }
        request.setAttribute("alert", alert);
        request.setAttribute("token", token);
        request.setAttribute("email", email);

        request.getRequestDispatcher(url).forward(request, response);
    }
}
