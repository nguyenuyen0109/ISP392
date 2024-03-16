/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import com.mysql.cj.xdevapi.Session;
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
import model.PageControl;
import model.PagingModel;
import utils.Pagination;

/**
 *
 * @author ADMIN
 */
public class DebtorController extends HttpServlet {

    DebtorDAO debtor = new DebtorDAO();
    private final String ACCOUNT_DETAIL = "/client/accountDetail.jsp";  
    private final String DEBTOR = "/client/debtor.jsp"; 
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PageControl pageControl = new PageControl();
        HttpSession session = request.getSession();
        // Assuming 'accountId' is stored in session, retrieve it.
        Integer accountId = (Integer) session.getAttribute("account_id");

        if (accountId == null) {
            // Handle case where accountId is not set in session, perhaps redirecting to a login page or showing an error message.
            response.sendRedirect("login"); // Example redirection to login page
            return; // Stop further execution in this case.
        }
        String act = request.getParameter("act");
        if (act != null && "delete".equals(act)) {
            handleDelete(request);
            response.getWriter().write("SUCCESS");
            return;
        }

        // Now use this accountId to get data from the database
        List<Debtor> listDebtor = pagination(request, pageControl);
        //get du lieu tu db

        //set list debtor vao session
        session.setAttribute("listDebtor", listDebtor);
        request.setAttribute("pageControl", pageControl);
        System.out.println(pageControl);

        request.getRequestDispatcher("client/debtor.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //set enconding UTF-8
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        //TAO SESSION       
        HttpSession session = request.getSession();
        String action = request.getParameter("action") == null
                ? ""
                : request.getParameter("action");
        switch (action) {
            case "update":
                update(request);
                break;
            case "add":
                add(request, response);
                break;
            default:
                throw new AssertionError();
        }
        response.sendRedirect("debtor");
    }

    private void add(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        // Add new debtor
        String name = request.getParameter("name");
        String address = request.getParameter("address");
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");
        double totalDebt = 0;
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
            //response.sendRedirect("debtor");
            request.setAttribute("successMessage", "Debtor added successfully!");
        } else {
            request.setAttribute("errorMessage", "Debt cannot be added.");
            request.getRequestDispatcher("client/debtor.jsp").forward(request, response);

        }
    }
    
    //delete
    private void handleDelete(HttpServletRequest request) {
        try {
            int debtorId = Integer.parseInt(request.getParameter("debtorId"));
            if (debtorId > 0) {
                DebtorDAO debtorDAO = new DebtorDAO();
                debtorDAO.deleteDebtor(debtorId);
            }
        } catch (Exception e) {
        }
    }

    private void update(HttpServletRequest request) {
        Debtor debtor = new Debtor();
        //get du lieu
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String address = request.getParameter("address");
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");

        //setter parametter
        debtor.setId(id);
        debtor.setName(name);
        debtor.setAddress(address);
        debtor.setPhone(phone);
        debtor.setEmail(email);

        //update
        DebtorDAO debtorDAO = new DebtorDAO();
        debtorDAO.updateDebtor(debtor);

    }

    private List<Debtor> pagination(HttpServletRequest request, PageControl pageControl) {
        // tao session
        HttpSession session = request.getSession();
        Integer accountId = (Integer) session.getAttribute("account_id");
        //get page

        String pageRaw = request.getParameter("page");
        DebtorDAO debtorDAO = new DebtorDAO();
        //valid page
        int page;
        try {
            page = Integer.parseInt(pageRaw);
        } catch (Exception e) {
            page = 1;
        }
        
        String searchBy = request.getParameter("searchBy");
        String sortby = request.getParameter("sortby");
        String keyword = request.getParameter("keyword");

        request.setAttribute("searchBy", searchBy);
        request.setAttribute("sortby", sortby);
        request.setAttribute("keyword", keyword);

        String queryString = "debtor?";
        if (keyword != null) {
            queryString += "keyword=" + keyword;
        }

        if (searchBy != null) {
            queryString += "&searchBy=" + searchBy;
        }
        if (sortby != null) {
            queryString += "&sortby=" + sortby;
        }
        PagingModel<Debtor> pagingModel = debtorDAO.DebtorPagingnation(accountId, page, searchBy, sortby, keyword);

        //set nhung gia tri vao pageControl
        pageControl.setPage(page);
        pageControl.setUrlPattern(queryString);
        pageControl.setTotalPage(pagingModel.getTotalPage());
        pageControl.setTotalRecord(pageControl.getTotalRecord());

        return pagingModel.getData();

       }

}
