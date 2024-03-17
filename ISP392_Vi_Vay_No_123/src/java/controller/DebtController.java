/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

//import com.mysql.cj.protocol.Resultset;
import dal.DBContext;
import dao.DebtDAO;
import java.sql.ResultSet;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.security.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.text.ParseException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
        DBContext db = DBContext.getInstance();
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

        if (debtorId != null && !debtorId.isEmpty()) {
            try {
                int debtorId1 = Integer.parseInt(debtorId);
                ResultSet rsDebtorName = db.getData("select name from debtor where id = " + debtorId1);
                session.setAttribute("debtorid", debtorId1);
                session.setAttribute("debtorName", rsDebtorName);
                //Integer accountId = (Integer) session.getAttribute("debtor_account_id");
                // Integer debtorId = (Integer) session.getAttribute("debtor_id");
            } catch (NumberFormatException e) {
                // Handle the exception if debtorId is not a valid integer
            }
        }
        List<DebtDetail> debtList = pagination(request, pageControl);
        session.setAttribute("debtList", debtList);
        ResultSet rsDebttype = db.getData("select * from debttype");
        request.setAttribute("debtTypeName", rsDebttype);
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
        int debtTypeId = Integer.parseInt(request.getParameter("debtType"));
        String image = request.getParameter("Image");
        double amount = Double.parseDouble(request.getParameter("amount"));
        double interest_rate = Double.parseDouble(request.getParameter("interest"));
        double due = Double.parseDouble(request.getParameter("due"));
        String dateIssuanceStr = request.getParameter("dateIssuance");
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
                interest_rate, due, debtTypeId, dateSql );
        int n = dao.addDebt(debt, accountId, debtorId);
        response.sendRedirect("debt");
    }
    
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
//                String searchType = request.getParameter("searchType");
                String keyword = request.getParameter("searchQuery");
                totalRecord = debtDAO.findTotalRecordToSearch(accountId, debtorId, keyword);
                debtList = debtDAO.findByPageToSearch(accountId, debtorId, keyword, page);
                pageControl.setUrlPattern("debt?");
//                switch (searchType) {
//                    case "description":
//                        totalRecord = debtDAO.findTotalRecordByDescription(accountId, debtorId, keyword);
//                        debtList = debtDAO.findByPageByDescription(accountId, debtorId, keyword, page);
//                        pageControl.setUrlPattern("debt?action=search&searchType=description&searchQuery=" + keyword + "&");
//                        break;
//                    case "amount":
//                        double amount;
//                        try {
//                            amount = Double.parseDouble(keyword);
//                        } catch (NumberFormatException e) {
//                            pageControl.setUrlPattern("debt?");
//                            break;
//                        }
//                        totalRecord = debtDAO.findTotalRecordByAmount(accountId, debtorId, Double.parseDouble(keyword));
//                        debtList = debtDAO.findByPageByAmount(accountId, debtorId, Double.parseDouble(keyword), page);
//                        pageControl.setUrlPattern("debt?action=search&searchType=amount&searchQuery=" + keyword + "&");
//                        break;
//                    default:
//                        //neu ko chon 
//                        totalRecord = debtDAO.findTotalRecord(accountId, debtorId);
//                        //tim ve danh sach debt o trang chi dinh
//                        debtList = debtDAO.findByPage(accountId, debtorId, page);
//                        pageControl.setUrlPattern("debt?");
//                        break;
//                }
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
                totalRecord = debtDAO.findTotalRecordByDebtType(accountId, debtorId, 3);
                debtList = debtDAO.findByPageByDebtType(accountId, debtorId, 3, page);
                pageControl.setUrlPattern("debt?action=Receivable&");
                //request.setAttribute("debtList", debtList);

                break;
            case "Debt":
                totalRecord = debtDAO.findTotalRecordByDebtType(accountId, debtorId, 4);
                debtList = debtDAO.findByPageByDebtType(accountId, debtorId, 4, page);
                pageControl.setUrlPattern("debt?action=Debt&");
                //request.setAttribute("debtList", debtList);
                break;
            case "Loan":
                totalRecord = debtDAO.findTotalRecordByDebtType(accountId, debtorId, 1);
                debtList = debtDAO.findByPageByDebtType(accountId, debtorId, 1, page);
                pageControl.setUrlPattern("debt?action=Loan&");
                break;
            case "Len":
                totalRecord = debtDAO.findTotalRecordByDebtType(accountId, debtorId, 2);
                debtList = debtDAO.findByPageByDebtType(accountId, debtorId, 2, page);
                pageControl.setUrlPattern("debt?action=Len&");
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
