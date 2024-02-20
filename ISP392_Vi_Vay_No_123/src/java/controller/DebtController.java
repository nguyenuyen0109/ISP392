/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

//import com.mysql.cj.protocol.Resultset;
import dao.DebtDAO;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import model.DebtDetail;

/**
 *
 * @author Admin
 */
public class DebtController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        DebtDAO dao = new DebtDAO();
         HttpSession session = request.getSession();
        // Assuming 'accountId' is stored in session, retrieve it.
        Integer accountId = (Integer) session.getAttribute("account_id");
        String debtorId = request.getParameter("debtorid");
        
        if (accountId == null) {
            // Handle case where accountId is not set in session, perhaps redirecting to a login page or showing an error message.
            response.sendRedirect("login"); // Example redirection to login page
            return; // Stop further execution in this case.
        }
        
        if (debtorId!= null && !debtorId.isEmpty()) {
        try {
            int debtorId1 = Integer.parseInt(debtorId);
            session.setAttribute("debtorid", debtorId);
        //Integer accountId = (Integer) session.getAttribute("debtor_account_id");
       // Integer debtorId = (Integer) session.getAttribute("debtor_id");
        String action = request.getParameter("action");
        List<DebtDetail> debtList;
        if (action == null) {
            debtList = dao.getDebtByIdAccountAndIdDebtor(debtorId1,accountId );
            request.setAttribute("debtList", debtList);
        } else {
            switch (action) {
                case "true":
                    debtList = dao.filterByReceivable(debtorId1,accountId, action);
                    request.setAttribute("debtList", debtList);
                    break;
                case "false":
                    debtList = dao.filterByReceivable(debtorId1,accountId, action);
                    request.setAttribute("debtList", debtList);
                    break;
                case "sortByOldest":
                    debtList = dao.sortDebtByOldest(debtorId1, accountId);
                    request.setAttribute("debtList", debtList);
                    break;
                case "sortByNewest":
                    debtList = dao.sortDebtByNewest(debtorId1,accountId);
                    request.setAttribute("debtList", debtList);

                    break;
                case "sortByHighLow":
                    debtList = dao.sortDebtByAmountHightLow(debtorId1,accountId);
                    request.setAttribute("debtList", debtList);

                    break;
                case "sortByLowHigh":
                    debtList = dao.sortDebtByAmountLowHight(debtorId1,accountId);
                    request.setAttribute("debtList", debtList);

                    break;
//                default:
//                    throw new AssertionError();
            }
        RequestDispatcher dispatch = request.getRequestDispatcher("/client/debtList.jsp");
        dispatch.forward(request, response);
        } }catch (NumberFormatException e) {
            // Handle the exception if debtorId is not a valid integer
        }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        switch (action) {
            case "add":
                add(request, response);
                break;
            case "search":
                search(request, response);
                break;
        }
    }

    private void search(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String searchQuery = request.getParameter("searchQuery");
        String searchType = request.getParameter("searchType");
         HttpSession session = request.getSession();
        // Assuming 'accountId' is stored in session, retrieve it.
        Integer accountId = (Integer) session.getAttribute("debtor_account_id");
        Integer debtorId = (Integer) session.getAttribute("debtor_id");
        List<DebtDetail> debtList;
        DebtDAO dao = new DebtDAO();
        if ("amount".equals(searchType)) {
            debtList = dao.searchDebtByAmount(debtorId,accountId,searchQuery);
            request.setAttribute("debtList", debtList);
        }else if("description".equals(searchType)){
            debtList = dao.searchDebtByDescription(debtorId,accountId, searchQuery);
            request.setAttribute("debtList", debtList);
        }
        
        request.getRequestDispatcher("client/debtList.jsp").forward(request, response);

    }

    private void add(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        // Add new debt
         HttpSession session = request.getSession();
        // Assuming 'accountId' is stored in session, retrieve it.
        Integer accountId = (Integer) session.getAttribute("debtor_account_id");
        Integer debtorId = (Integer) session.getAttribute("debtor_id");
        String description = request.getParameter("description");
        boolean debtType = Boolean.parseBoolean(request.getParameter("debtType"));
        double amount = Double.parseDouble(request.getParameter("amount"));
        int interest_rate = Integer.parseInt(request.getParameter("interest_rate"));
        DebtDAO dao = new DebtDAO();
        DebtDetail debt = new DebtDetail(description, debtType, amount, debtorId,accountId, interest_rate);
        int n = dao.addDebt(debt,accountId,debtorId);
        response.sendRedirect("debt");
    }
}
