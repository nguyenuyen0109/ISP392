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

/**
 *
 * @author lvhn1
 */
@WebServlet(name="ResetPassword", urlPatterns={"/reset-password"})
public class ResetPasswordController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        
        String email = request.getParameter("e");
        String token = request.getParameter("t");
        
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
        String retypePassword = request.getParameter("retypePassword");
        
        String checkToken = (String) request.getSession().getAttribute("reset_token_" + email);
        
        Account user = new AccountDAO().getAccountByEmail(email);
        
        String msg = "";      
        String url ="";
        if (user==null) {
            msg = "Email not found";
        } else if (token.equals(checkToken)) {
            
            if (password.equals(retypePassword)) {
                
                user.setPassword(new Hash().hashPassword(password));
                new AccountDAO().updateAccount(user);
                
                request.getSession().removeAttribute("reset_token_" + email);
                
                msg = "Reset password success";
                url = "client/login.jsp";
            } else {
                msg = "2 password not match";
                url = "client/resetpassword.jsp";
            }
            
        } else {
            msg = "Invalid token!";
            url = "client/resetpassword.jsp";
        }
        
        request.setAttribute("msg", msg);
        request.setAttribute("token", token);
        request.setAttribute("email", email);
        request.setAttribute("toast", msg);

        request.getRequestDispatcher(url).forward(request, response);
    }
}
