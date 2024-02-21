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
        DebtDAO dao = new DebtDAO();
        PageControl pageControl = new PageControl();
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
            session.setAttribute("debtorid", debtorId1);
        //Integer accountId = (Integer) session.getAttribute("debtor_account_id");
       // Integer debtorId = (Integer) session.getAttribute("debtor_id");
         }catch (NumberFormatException e) {
            // Handle the exception if debtorId is not a valid integer
        }
        }
        List<DebtDetail> debtList = pagination(request, pageControl);
        session.setAttribute("debtList", debtList);
        request.setAttribute("pageControl", pageControl);
        System.out.println(pageControl);
        RequestDispatcher dispatch = request.getRequestDispatcher("/client/debtList.jsp");
        dispatch.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Add new debt
         HttpSession session = request.getSession();
        // Assuming 'accountId' is stored in session, retrieve it.
        Integer accountId = (Integer) session.getAttribute("account_id");
        Integer debtorId = (Integer) session.getAttribute("debtorid");
        String description = request.getParameter("description");
        boolean debtType = Boolean.parseBoolean(request.getParameter("debtType"));
        double amount = Double.parseDouble(request.getParameter("amount"));
        int interest_rate = Integer.parseInt(request.getParameter("interest_rate"));
        DebtDAO dao = new DebtDAO();
        DebtDetail debt = new DebtDetail(description, debtType, amount, debtorId,accountId, interest_rate);
        int n = dao.addDebt(debt,accountId,debtorId);
        response.sendRedirect("debt");
    }

//    private void search(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        String searchQuery = request.getParameter("searchQuery");
//        String searchType = request.getParameter("searchType");
//         HttpSession session = request.getSession();
//        // Assuming 'accountId' is stored in session, retrieve it.
//        Integer accountId = (Integer) session.getAttribute("debtor_account_id");
//        Integer debtorId = (Integer) session.getAttribute("debtor_id");
//        List<DebtDetail> debtList;
//        DebtDAO dao = new DebtDAO();
//        if ("amount".equals(searchType)) {
//            debtList = dao.searchDebtByAmount(debtorId,accountId,searchQuery);
//            request.setAttribute("debtList", debtList);
//        }else if("description".equals(searchType)){
//            debtList = dao.searchDebtByDescription(debtorId,accountId, searchQuery);
//            request.setAttribute("debtList", debtList);
//        }
//        
//        request.getRequestDispatcher("client/debtList.jsp").forward(request, response);
//
//    }

//    private void add(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
//        // Add new debt
//         HttpSession session = request.getSession();
//        // Assuming 'accountId' is stored in session, retrieve it.
//        Integer accountId = (Integer) session.getAttribute("debtor_account_id");
//        Integer debtorId = (Integer) session.getAttribute("debtor_id");
//        String description = request.getParameter("description");
//        boolean debtType = Boolean.parseBoolean(request.getParameter("debtType"));
//        double amount = Double.parseDouble(request.getParameter("amount"));
//        int interest_rate = Integer.parseInt(request.getParameter("interest_rate"));
//        DebtDAO dao = new DebtDAO();
//        DebtDetail debt = new DebtDetail(description, debtType, amount, debtorId,accountId, interest_rate);
//        int n = dao.addDebt(debt,accountId,debtorId);
//        response.sendRedirect("debt");
//    }
    private List<DebtDetail> pagination(HttpServletRequest request, PageControl pageControl) {
        HttpSession session = request.getSession();
        Integer accountId = (Integer) session.getAttribute("account_id");
        Integer debtorId = (Integer) session.getAttribute("debtorid");
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
                        totalRecord = debtDAO.findTotalRecordByDescription(accountId, debtorId, keyword);
                        debtList = debtDAO.findByPageByDescription(accountId, debtorId, keyword, page);
                        pageControl.setUrlPattern("debt?action=search&searchType=description&searchQuery=" + keyword + "&");
                        break;
                    case "amount":
                        double amount;
                        try {
                        amount = Double.parseDouble(keyword);
                    } catch (NumberFormatException e) {
                        pageControl.setUrlPattern("debt?");
                        break;
                    }
                        totalRecord = debtDAO.findTotalRecordByAmount(accountId, debtorId, Double.parseDouble(keyword));
                        debtList = debtDAO.findByPageByAmount(accountId, debtorId, Double.parseDouble(keyword), page);
                        pageControl.setUrlPattern("debt?action=search&searchType=amount&searchQuery=" + keyword + "&");
                        break;
                    default:
                        //neu ko chon 
                        totalRecord = debtDAO.findTotalRecord(accountId, debtorId);
                        //tim ve danh sach debt o trang chi dinh
                        debtList = debtDAO.findByPage(accountId, debtorId, page);
                        pageControl.setUrlPattern("debt?");
                        break;
                }
                break;
                case "sortByOldest":
                     totalRecord = debtDAO.findTotalRecord(accountId, debtorId);
                    debtList = debtDAO.findByPageAndSortByOldest(accountId, debtorId, page);
                    pageControl.setUrlPattern("debt?action=sortByOldest&");
                    //request.setAttribute("debtList", debtList);
                    break;
                    
                case "sortByNewest":
                     totalRecord = debtDAO.findTotalRecord(accountId, debtorId);
                     debtList = debtDAO.findByPageAndSortByNewest(accountId, debtorId, page);
                    pageControl.setUrlPattern("debt?action=sortByNewest&");
                    //request.setAttribute("debtList", debtList);

                    break;
                case "sortByHighLow":
                     totalRecord = debtDAO.findTotalRecord(accountId, debtorId);
                    debtList = debtDAO.findByPageAndSortDebtByAmountHighLow(accountId, debtorId, page);
                    pageControl.setUrlPattern("debt?action=sortByHighLow&");
                    //request.setAttribute("debtList", debtList);

                    break;
                case "sortByLowHigh":
                    totalRecord = debtDAO.findTotalRecord(accountId, debtorId);
                    debtList = debtDAO.findByPageAndSortDebtByAmountLowHigh(accountId, debtorId, page);
                    pageControl.setUrlPattern("debt?action=sortByLowHigh&");
                    //request.setAttribute("debtList", debtList);

                    break;
                case "Receivable":
                    totalRecord = debtDAO.findTotalRecordByDebtType(accountId, debtorId, true);
                    debtList = debtDAO.findByPageByDebtType(accountId, debtorId, true, page);
                    pageControl.setUrlPattern("debt?action=Receivable&");
                    //request.setAttribute("debtList", debtList);
                    
                    break;
                case "Debt":
                    totalRecord = debtDAO.findTotalRecordByDebtType(accountId, debtorId, false);
                    debtList = debtDAO.findByPageByDebtType(accountId, debtorId, false, page);
                    pageControl.setUrlPattern("debt?action=Debt&");
                    //request.setAttribute("debtList", debtList);
                    break;
            default:
                //phan trang o trang home
                //tim ve totalRecord
                totalRecord = debtDAO.findTotalRecord(accountId, debtorId);
                debtList = debtDAO.findByPage(accountId, debtorId, page);
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
