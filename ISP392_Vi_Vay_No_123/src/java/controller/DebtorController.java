/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.DebtorDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import model.Debtor;

/**
 *
 * @author ADMIN
 */
public class DebtorController extends HttpServlet {

    DebtorDAO debtor = new DebtorDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        // Assuming 'accountId' is stored in session, retrieve it.
        Integer accountId = (Integer) session.getAttribute("account_id");

        if (accountId == null) {
            // Handle case where accountId is not set in session, perhaps redirecting to a login page or showing an error message.
            response.sendRedirect("login"); // Example redirection to login page
            return; // Stop further execution in this case.
        }

        // Now use this accountId to get data from the database
        List<Debtor> listDebtor = debtor.getAllDebtors(accountId);
        //get du lieu tu db

        //set list debtor vao session
        session.setAttribute("listDebtor", listDebtor);

        request.getRequestDispatcher("client/debtor.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action") == null
                ? ""
                : request.getParameter("action");
        switch (action) {
            case "search":
                search(request, response);
                break;
            case "add":
                add(request, response);
                break;
            default:
                response.sendRedirect("debtor");
                break;

        }

    }

    private void search(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Search
        String searchName = request.getParameter("searchName");
        String searchAddress = request.getParameter("searchAddress");
        String searchPhone = request.getParameter("searchPhone");
        String searchMail = request.getParameter("searchMail");
        List<Debtor> listDebtor = new ArrayList<>();
        HttpSession session = request.getSession();
        Integer accountid = (Integer) session.getAttribute("account_id");
        String cond = "";
        if (searchName != null && !searchName.isEmpty()) {
            cond += " name like '%" + searchName + "%'";
            //listDebtor = debtor.getDebtorsByName(searchName);
        }
        if (searchAddress != null && !searchAddress.isEmpty()) {
            if (cond != null && !cond.isEmpty()) {
                cond += " AND ";
            }
            cond += " address like '%" + searchAddress + "%'";
            //listDebtor = debtor.getDebtorsByAddress(searchAddress);
        }
        if (searchPhone != null && !searchPhone.isEmpty()) {
            if (cond != null && !cond.isEmpty()) {
                cond += " AND ";
            }
            cond += " phone like '%" + searchPhone + "%'";
        }

        if (searchMail != null && !searchMail.isEmpty()) {
            if (cond != null && !cond.isEmpty()) {
                cond += " AND ";
            }
            cond += " email like '%" + searchMail + "%'";
        }

        listDebtor = debtor.getDebtorsByName(accountid,cond);
//if (searchName != null && !searchName.isEmpty()) {
//    listDebtor = debtor.getDebtorsByName(searchName);
//} else if (searchAddress != null && !searchAddress.isEmpty()) {
//    listDebtor = debtor.getDebtorsByAddress(searchAddress);
//} else if (searchPhone != null && !searchPhone.isEmpty()) {
//    listDebtor = debtor.getDebtorsByPhone(searchPhone);
//} else if (searchMail != null && !searchMail.isEmpty()) {
//    listDebtor = debtor.getDebtorsByEmail(searchMail);
//}

        request.getSession().setAttribute("listDebtor", listDebtor);
        request.getRequestDispatcher("client/debtor.jsp").forward(request, response);
    }

    private void add(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        // Add new debtor

        String name = request.getParameter("name");
        String address = request.getParameter("address");
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");
        double totalDebt = Double.parseDouble(request.getParameter("totalDebt"));
        HttpSession session = request.getSession();
        Integer accountid = (Integer) session.getAttribute("account_id");
        Debtor newDebtor = new Debtor();
        newDebtor.setName(name);
        newDebtor.setAddress(address);
        newDebtor.setPhone(phone);
        newDebtor.setEmail(email);
        newDebtor.setTotalDebt(totalDebt);
        newDebtor.setAccount_id(accountid);

        // Save the new debtor
        boolean success = debtor.addDebtor(newDebtor);
        System.out.println(success);
        if (success) {
            response.sendRedirect("debtor");
            request.setAttribute("successMessage", "Debtor added successfully!");
        } else {
            request.setAttribute("errorMessage", "Debt cannot be added.");
            request.getRequestDispatcher("client/debtor.jsp").forward(request, response);

        }
    }

}
