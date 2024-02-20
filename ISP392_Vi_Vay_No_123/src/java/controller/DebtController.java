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
import jakarta.servlet.http.HttpSession;
import java.util.List;
import model.DebtDetail;
import model.PageControl;
import utils.Pagination;

/**
 *
 * @author Admin
 */
public class DebtController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //set encoding UTF8
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        
        
        
        DebtDAO dao = new DebtDAO();
        PageControl pageControl = new PageControl();
        
        //tao session
        HttpSession session = request.getSession();
        
        //get du lieu tu db len
        List<DebtDetail> debtList = pagination(request, pageControl);
        //set list debt vao session
            session.setAttribute("debtList", debtList);
            request.setAttribute("pageControl", pageControl);
            System.out.println(pageControl);
        
        RequestDispatcher dispatch = request.getRequestDispatcher("/client/debtlist.jsp");
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
        //add new debt
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

    private List<DebtDetail> pagination(HttpServletRequest request, PageControl pageControl) {
     //get page
        String pageRaw = request.getParameter("page");
        DebtDAO debtDAO = new DebtDAO();
        //valid page
        int page;
        try {
            page = Integer.parseInt(pageRaw);
        } catch (Exception e) {
            page = 1;
        }
        int totalRecord = 0;
        List<DebtDetail> debtList = null;
        //get action hien tai muon lam gi
        //tim xem co bao nhieu record va listDebt by page
        String action = request.getParameter("action") == null
                ? "defaultFindAll"
                : request.getParameter("action");
        switch (action) {
            case "search":
                String searchType = request.getParameter("searchType");
                String keyword = request.getParameter("searchQuery");
                switch (searchType) {
                    case "description":
                        totalRecord = debtDAO.findTotalRecordByDescription(keyword);
                        debtList = debtDAO.findByPageByDescription(keyword, page);
                        pageControl.setUrlPattern("debt?action=search&searchType=description&searchQuery=" + keyword + "&");
                        break;
                    case "amount":
                        totalRecord = debtDAO.findTotalRecordByAmount(Integer.parseInt(keyword));
                        debtList = debtDAO.findByPageByAmount(Integer.parseInt(keyword), page);
                        pageControl.setUrlPattern("debt?action=search&searchType=amount&searchQuery=" + keyword + "&");
                        break;
                    default:
                        // Xử lý mặc định nếu không có lựa chọn nào phù hợp
                        totalRecord = debtDAO.findTotalRecord();
                        //tim ve danh sach debt o trang chi dinh
                        debtList = debtDAO.findByPage(page);
                        pageControl.setUrlPattern("debt?");
                        break;
                }
                break;
                case "sortByOldest":
                    totalRecord = debtDAO.findTotalRecord();
                    debtList = debtDAO.findByPageAndSortByOldest(page);
                    pageControl.setUrlPattern("debt?action=sortByOldest&");
                    //request.setAttribute("debtList", debtList);
                    break;
                    
                case "sortByNewest":
                    totalRecord = debtDAO.findTotalRecord();
                    debtList = debtDAO.findByPageAndSortByNewest(page);
                    pageControl.setUrlPattern("debt?action=sortByNewest&");
                    //request.setAttribute("debtList", debtList);

                    break;
                case "sortByHighLow":
                    totalRecord = debtDAO.findTotalRecord();
                    debtList = debtDAO.findByPageAndSortDebtByAmountHighLow(page);
                    pageControl.setUrlPattern("debt?action=sortByHighLow&");
                    //request.setAttribute("debtList", debtList);

                    break;
                case "sortByLowHigh":
                    totalRecord = debtDAO.findTotalRecord();
                    debtList = debtDAO.findByPageAndSortDebtByAmountLowHigh(page);
                    pageControl.setUrlPattern("debt?action=sortByLowHigh&");
                    //request.setAttribute("debtList", debtList);

                    break;
                case "Receivable":
                    totalRecord = debtDAO.findTotalRecordByDebtType(true);
                    debtList = debtDAO.findByPageByDebtType(true, page);
                    pageControl.setUrlPattern("debt?action=Receivable&");
                    //request.setAttribute("debtList", debtList);
                    
                    break;
                case "Debt":
                    totalRecord = debtDAO.findTotalRecordByDebtType(false);
                    debtList = debtDAO.findByPageByDebtType(false, page);
                    pageControl.setUrlPattern("debt?action=Debt&");
                    //request.setAttribute("debtList", debtList);
                    break;
            default:
                //phan trang o trang home
                //tim ve totalRecord
                totalRecord = debtDAO.findTotalRecord();
                //tim ve danh sach teddybear o trang chi dinh
                debtList = debtDAO.findByPage(page);
                pageControl.setUrlPattern("debt?");
        }
        
        //tim xem tong co bao nhieu page
         int totalPage = (totalRecord % Pagination.RECORD_PER_PAGE) == 0
                ? (totalRecord / Pagination.RECORD_PER_PAGE)
                : (totalRecord / Pagination.RECORD_PER_PAGE) + 1;
        //set nhung gia tri vao pageControl
        pageControl.setPage(page);
        pageControl.setTotalPage(totalPage);
        pageControl.setTotalRecord(totalRecord);
        
        return debtList;
    }
    


}
