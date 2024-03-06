/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;


import dao.AccountDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.security.Timestamp;
import java.util.List;
import model.Account;

/**
 *
 * @author admin
 */
@WebServlet(name = "AccountController", urlPatterns = {"/account"})
public class AccountController extends HttpServlet {

    private final AccountDAO accountDAO = new AccountDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");

        if (action == null || action.equals("list")) {
            List<Account> accounts = accountDAO.getAllAccounts();
            req.setAttribute("accounts", accounts);
            req.getRequestDispatcher("client/accountList.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");

        if (action != null) {
            if (action.equals("create")) {
                createAccount(req, resp);
            } else if (action.equals("update")) {
                updateAccount(req, resp);
            }
        }
    }

    private void createAccount(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String name = req.getParameter("name");
        String mobileNumber = req.getParameter("mobileNumber");
        String emailAddress = req.getParameter("emailAddress");
        String address = req.getParameter("address");
        boolean isActive = Boolean.parseBoolean(req.getParameter("isActive"));
        String avatarUrl = req.getParameter("avatarUrl");
        boolean gender = Boolean.parseBoolean(req.getParameter("gender"));
        int role = Integer.parseInt(req.getParameter("roleId"));

        Account newAccount = new Account();
        newAccount.setUsername(username);
        newAccount.setPassword(password);
        newAccount.setName(name);
        newAccount.setMobileNumber(mobileNumber);
        newAccount.setEmailAddress(emailAddress);
        newAccount.setAddress(address);
        newAccount.setIsActive(isActive);
        newAccount.setAvatarUrl(avatarUrl);
        newAccount.setGender(gender);
        newAccount.setId(role);

//        Account createdAccount = accountDAO.createAccount(newAccount);

//        if (createdAccount != null) {
//            resp.sendRedirect(req.getContextPath() + "/account");
//        } else {
//            resp.getWriter().println("Failed to create account.");
//        }
    }

    private void updateAccount(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int accountId = Integer.parseInt(req.getParameter("id"));
        String username = req.getParameter("username");
        double amount = Double.parseDouble(req.getParameter("amount"));
        String password = req.getParameter("password");
        String name = req.getParameter("name");
        String mobileNumber = req.getParameter("mobileNumber");
        String emailAddress = req.getParameter("emailAddress");
        String address = req.getParameter("address");
        boolean isActive = Boolean.parseBoolean(req.getParameter("isActive"));
        String avatarUrl = req.getParameter("avatarUrl");
        boolean gender = Boolean.parseBoolean(req.getParameter("gender"));
        int roleIdRole = Integer.parseInt(req.getParameter("roleIdRole"));
        java.sql.Timestamp updateAt = new java.sql.Timestamp(System.currentTimeMillis());

        Account existingAccount = accountDAO.getAccountById(accountId);

        if (existingAccount != null) {
            existingAccount.setUsername(username);
            existingAccount.setPassword(password);
            existingAccount.setName(name);
            existingAccount.setMobileNumber(mobileNumber);
            existingAccount.setEmailAddress(emailAddress);
            existingAccount.setAddress(address);
            existingAccount.setIsActive(isActive);
            existingAccount.setAvatarUrl(avatarUrl);
            existingAccount.setGender(gender);
            existingAccount.setId(roleIdRole);
            existingAccount.setUpdateAt(updateAt);
            Account updatedAccount = accountDAO.updateAccount(existingAccount);

            if (updatedAccount != null)
                resp.sendRedirect("account?updateSuccess");
            else resp.sendRedirect("account?updateFailed");

        } else {
            resp.sendRedirect("account?updateFailed");
        }
    }

}
