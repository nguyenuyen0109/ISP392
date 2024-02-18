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
        String action = request.getParameter("action");
        List<DebtDetail> debtList;
        if (action == null) {
            debtList = dao.getDebtByIdAccountAndIdDebtor(7);
            request.setAttribute("debtList", debtList);
        } else {
            switch (action) {
                case "true":
                    debtList = dao.filterByReceivable(7, action);
                    request.setAttribute("debtList", debtList);
                    break;
                case "false":
                    debtList = dao.filterByReceivable(7, action);
                    request.setAttribute("debtList", debtList);
                    break;
                case "sortByOldest":
                    debtList = dao.sortDebtByOldest(7);
                    request.setAttribute("debtList", debtList);
                    break;
                case "sortByNewest":
                    debtList = dao.sortDebtByNewest(7);
                    request.setAttribute("debtList", debtList);

                    break;
                case "sortByHighLow":
                    debtList = dao.sortDebtByAmountHightLow(7);
                    request.setAttribute("debtList", debtList);

                    break;
                case "sortByLowHigh":
                    debtList = dao.sortDebtByAmountLowHight(7);
                    request.setAttribute("debtList", debtList);

                    break;
//                default:
//                    throw new AssertionError();
            }
        }
        RequestDispatcher dispatch = request.getRequestDispatcher("/client/debtList.jsp");
        dispatch.forward(request, response);
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
        List<DebtDetail> debtList;
        DebtDAO dao = new DebtDAO();
        if ("amount".equals(searchType)) {
            debtList = dao.searchDebtByAmount(7,searchQuery);
            request.setAttribute("debtList", debtList);
        }else if("description".equals(searchType)){
            debtList = dao.searchDebtByDescription(7, searchQuery);
            request.setAttribute("debtList", debtList);
        }
        
        request.getRequestDispatcher("client/debtList.jsp").forward(request, response);

    }

    private void add(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        // Add new debt
        int idDebtor = 7;
        int idAccount = 2;
        String description = request.getParameter("description");
        boolean debtType = Boolean.parseBoolean(request.getParameter("debtType"));
        double amount = Double.parseDouble(request.getParameter("amount"));
        int interest_rate = Integer.parseInt(request.getParameter("interest_rate"));
        DebtDAO dao = new DebtDAO();
        DebtDetail debt = new DebtDetail(description, debtType, amount, idDebtor, idAccount, interest_rate);
        int n = dao.addDebt(debt);
        response.sendRedirect("debt");
    }
}
