/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.DebtDAO;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import model.DebtDetail;

/**
 *
 * @author Admin
 */
public class DebtController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        DebtDAO dao = new DebtDAO();
        String action = request.getParameter("action");
        List<DebtDetail> debtList;
        if (action == null) {
            debtList = dao.getDebtByIdAccountAndIdDebtor(1);
            request.setAttribute("debtList", debtList);
//            RequestDispatcher dispatch = request.getRequestDispatcher("/client/debtlist.jsp");
//            dispatch.forward(request, response);
        } else {
            switch (action) {
                case "true":
                    debtList = dao.filterByReceivable(1, action);
                    request.setAttribute("debtList", debtList);
                    break;
                case "false":
                    debtList = dao.filterByReceivable(1, action);
                    request.setAttribute("debtList", debtList);
                    break;
                case "sortByOldest":
                    debtList = dao.sortDebtByOldest(1);
                    request.setAttribute("debtList", debtList);
                    break;
                case "sortByNewest":
                    debtList = dao.sortDebtByNewest(1);
                    request.setAttribute("debtList", debtList);

                    break;
                case "sortByHighLow":
                    debtList = dao.sortDebtByAmountHightLow(1);
                    request.setAttribute("debtList", debtList);

                    break;
                case "sortByLowHigh":
                    debtList = dao.sortDebtByAmountLowHight(1);
                    request.setAttribute("debtList", debtList);

                    break;
//                default:
//                    throw new AssertionError();
            }
        }
        RequestDispatcher dispatch = request.getRequestDispatcher("/client/debtList.jsp");
        dispatch.forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int idDebtor = 4;
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

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
