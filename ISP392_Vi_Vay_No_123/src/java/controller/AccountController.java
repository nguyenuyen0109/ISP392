/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.DBContext;
import dao.AccountDAO;
import dao.DebtDAO;
import dao.DebtorDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.net.Authenticator;
import java.sql.ResultSet;
import java.security.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Account;
import model.DebtDetail;
import model.Debtor;

/**
 *
 * @author admin
 */
@WebServlet(name = "AccountController", urlPatterns = {"/account"})
public class AccountController extends HttpServlet {

    private final AccountDAO accountDAO = new AccountDAO();
    private final DebtorDAO debtorDao = new DebtorDAO();
    private final DebtDAO debtDao = new DebtDAO();
    DBContext db = DBContext.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        HttpSession session = req.getSession();
//        // Assuming 'accountId' is stored in session, retrieve it.
//        Integer accountId = (Integer) session.getAttribute("account_id");
//
//        if (accountId == null) {
//            // Handle case where accountId is not set in session, perhaps redirecting to a login page or showing an error message.
//            resp.sendRedirect("login"); // Example redirection to login page
//            return; // Stop further execution in this case.
//        }
//        String action = req.getParameter("action");
//        if (action == null || action.equals("list")) {
//            List<Account> accounts = accountDAO.getAllAccounts();
//            req.setAttribute("accounts", accounts);
//            req.getRequestDispatcher("client/accountList.jsp").forward(req, resp);
//        }else if (action.equals("viewDetail")) {
//            int idAccount = Integer.parseInt(req.getParameter("idAccount"));
//            Account accDetail = accountDAO.getAccountById(idAccount);
//            ResultSet roleName = db.getData("select * from account a\n"
//                    + "inner join role r on a.role_id=r.id\n"
//                    + "where a.id = '" + idAccount +"'");
//            int totalDebtor = debtorDao.findTotalRecord(idAccount);
//            req.setAttribute("accDetail", accDetail);
//            req.setAttribute("totalDebtor", totalDebtor);
//            req.setAttribute("roleName", roleName);
//            req.getRequestDispatcher("client/accountDetail.jsp").forward(req, resp);
//        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action != null) {
            if (action.equals("create")) {
                createAccount(req, resp);
            } else if (action.equals("update")) {
                updateAccount(req, resp);
            } else if (action.equals("updateIsActive")) {
                int idAccount = Integer.parseInt(req.getParameter("idAccount"));
                String userName = req.getParameter("userName");
                boolean isActive = Boolean.parseBoolean(req.getParameter("status"));
                accountDAO.updateIsActive(userName, isActive);
                resp.sendRedirect("dashboardadmin?action=viewDetail&idAccount=" + idAccount);
            } else if (action.equals("deleted")) {
                int idAccount = Integer.parseInt(req.getParameter("idAccountOfDebtor"));
                int debtorId = Integer.parseInt(req.getParameter("idDebtor"));
                debtDao.isDeleteDebt(req.getParameter("debtId"), req.getParameter("idDebtor"));
                resp.sendRedirect("dashboardadmin?action=adminViewDebt&idAccountDebtor=" + idAccount + "&debtorid=" + debtorId);
            } else if (action.equals("addDebtor")) {
                String name = req.getParameter("name");
                String address = req.getParameter("address");
                String phone = req.getParameter("phone");
                String email = req.getParameter("email");
                double totalDebt = 0;
                HttpSession session = req.getSession();
                int idAccount = Integer.parseInt(req.getParameter("idAccounts"));
                Debtor newDebtor = new Debtor();
                newDebtor.setName(name);
                newDebtor.setAddress(address);
                newDebtor.setPhone(phone);
                newDebtor.setEmail(email);
                newDebtor.setTotalDebt(totalDebt);
                newDebtor.setAccount_id(idAccount);

                // Save the new debtor
                boolean success = debtorDao.addDebtor(newDebtor);
                resp.sendRedirect("dashboardadmin?action=adminViewDebtor&idAccounts=" + idAccount);
            } else if (action.equals("adminAddDebt")) {
                int accountId = Integer.parseInt(req.getParameter("idAccounts"));
                int debtorId = Integer.parseInt(req.getParameter("idDebtor"));
                String description = req.getParameter("description");
                int debtTypeId = Integer.parseInt(req.getParameter("debtType"));
                String image = req.getParameter("Image");
                double amount = Double.parseDouble(req.getParameter("amount"));
                double interest_rate = Double.parseDouble(req.getParameter("interest"));
                double due = Double.parseDouble(req.getParameter("due"));
                String dateIssuanceStr = req.getParameter("dateIssuance");
                Date dateUtil = null; // Sử dụng java.util.Date để parse hoặc khởi tạo
                if (dateIssuanceStr != null && !dateIssuanceStr.isEmpty()) {
                    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                    try {
                        dateUtil = formatter.parse(dateIssuanceStr);
                    } catch (ParseException ex) {
                        Logger.getLogger(DebtController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {
                    dateUtil = new Date(); // ngay hien tai
                }
                java.sql.Date dateSql = new java.sql.Date(dateUtil.getTime()); //chuyen tu util.Date sang sql.Date de luu vao database
                DebtDAO dao = new DebtDAO();
                DebtDetail debt = new DebtDetail(description, amount, image, debtorId, accountId,
                        interest_rate, due, debtTypeId, dateSql);
                int n = dao.addDebt(debt, accountId, debtorId);
                resp.sendRedirect("dashboardadmin?action=adminViewDebt&idAccountDebtor=" + accountId + "&debtorid=" + debtorId);
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

            if (updatedAccount != null) {
                resp.sendRedirect("account?updateSuccess");
            } else {
                resp.sendRedirect("account?updateFailed");
            }

        } else {
            resp.sendRedirect("account?updateFailed");
        }
    }

}
