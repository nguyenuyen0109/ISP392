/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dal.DBContext;
import dao.AccountDAO;
import dao.DebtDAO;
import dao.DebtorDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.util.List;
import model.Account;
import model.DebtDetail;
import model.Debtor;
import model.PageControl;
import utils.Pagination;

/**
 *
 * @author admin
 */
public class DashBoardAdminController extends HttpServlet {

    private final AccountDAO acc = new AccountDAO();
    private final DebtorDAO debtorDao = new DebtorDAO();
    DebtDAO debtDAO = new DebtDAO();
    DBContext db = DBContext.getInstance();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        PageControl pageControl = new PageControl();
        Integer accountId = (Integer) session.getAttribute("account_id");

        if (accountId == null) {
            response.sendRedirect("login");
            return;
        }
        String action = request.getParameter("action") == null
                ? "defaultFindAll"
                : request.getParameter("action");
        AccountDAO acc = new AccountDAO();
        DebtorDAO debtorDao = new DebtorDAO();
        switch (action) {
            case "active":
            case "deactive":
            case "accountOldest":
            case "accountNewest":
            case "mostDebtor":
            case "adminSearch":
                handleListAccounts(request, response, pageControl);
                break;
            case "viewDetail":
                handleViewAccountDetail(request, response, acc, debtorDao);
                break;
            case "adminViewDebtor":
            case "sortByOldest":
            case "sortByNewest":
            case "sortByHighLow":
            case "sortByLowHigh":
            case "search":
                handleAdminViewDebtor(request, response, pageControl);
                break;
            case "adminViewDebt":
            case "sortByOldestDebt":
            case "sortByNewestDebt":
            case "sortByHighLowDebt":
            case "sortByLowHighDebt":
            case "ReceivableOfDebt":
            case "DebtOfDebt":
            case "LoanOfDebt":
            case "LendOfDebt":
            case "searchDebt":
                handleAdminViewDebt(request, response, pageControl);
                break;
            default:
                handleListAccounts(request, response, pageControl);
                break;
            // Các trường hợp khác
//            }
        }
    }

    private void handleListAccounts(HttpServletRequest request, HttpServletResponse response, PageControl pageControl) throws ServletException, IOException {
        List<Account> accounts = paginationAccount(request, pageControl);
        request.setAttribute("accounts", accounts);
        request.setAttribute("pageControl", pageControl);
        request.getRequestDispatcher("client/accountList.jsp").forward(request, response);
    }

    private void handleViewAccountDetail(HttpServletRequest request, HttpServletResponse response, AccountDAO acc, DebtorDAO debtorDao) throws ServletException, IOException {
        int idAccount = Integer.parseInt(request.getParameter("idAccount"));
        Account accDetail = acc.getAccountById(idAccount);

        ResultSet roleName = db.getData("select * from account a\n"
                + "inner join role r on a.role_id=r.id\n"
                + "where a.id = '" + idAccount + "'");

        int totalDebtor = debtorDao.findTotalRecord(idAccount);
        request.setAttribute("accDetail", accDetail);
        request.setAttribute("totalDebtor", totalDebtor);
        request.setAttribute("roleName", roleName);
        request.getRequestDispatcher("client/accountDetail.jsp").forward(request, response);
    }

    private void handleAdminViewDebtor(HttpServletRequest request, HttpServletResponse response, PageControl pageControl) throws ServletException, IOException {
        int idAccount = Integer.parseInt(request.getParameter("idAccounts"));
        List<Debtor> listDebtor = paginationDebtor(request, pageControl);
        request.setAttribute("idAccount", idAccount);
        request.setAttribute("listDebtor", listDebtor);
        request.setAttribute("pageControl", pageControl);
        System.out.println(pageControl);
        request.getRequestDispatcher("client/adminDebtor.jsp").forward(request, response);
    }

    private void handleAdminViewDebt(HttpServletRequest request, HttpServletResponse response, PageControl pageControl) throws ServletException, IOException {
        int idAccount = Integer.parseInt(request.getParameter("idAccountDebtor"));
        int idDebtor = Integer.parseInt(request.getParameter("debtorid"));
        List<DebtDetail> listDebt = paginationDebt(request, pageControl);
        request.setAttribute("idAccount", idAccount);
        request.setAttribute("idDebtor", idDebtor);
        request.setAttribute("listDebt", listDebt);
        request.setAttribute("pageControl", pageControl);
        System.out.println(pageControl);
        request.getRequestDispatcher("client/adminDebt.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    private List<Account> paginationAccount(HttpServletRequest request, PageControl pageControl) {
        //get page
        String pageRaw = request.getParameter("page");
        AccountDAO accDAO = new AccountDAO();
        //valid page
        int page;
        try {
            page = Integer.parseInt(pageRaw);
        } catch (Exception e) {
            page = 1;
        }
        int totalRecord = 0;
        List<Account> accountList = null;
        String action = request.getParameter("action") == null
                ? "defaultFindAll"
                : request.getParameter("action");
        switch (action) {
            case "active":
                totalRecord = accDAO.totalRecordIsActive(true);
                accountList = accDAO.filterAccount(true, page);
                pageControl.setUrlPattern("dashboardadmin?action=active&");
                break;
            case "deactive":
                totalRecord = accDAO.totalRecordIsActive(false);
                accountList = accDAO.filterAccount(false, page);
                pageControl.setUrlPattern("dashboardadmin?action=deactive&");                
                break;
            case "accountOldest":
                totalRecord = accDAO.totalAccount();
                accountList = accDAO.accountOldest(page);
                pageControl.setUrlPattern("dashboardadmin?action=accountOldest&");
                break;
            case "accountNewest":
                totalRecord = accDAO.totalAccount();
                accountList = accDAO.accountNewest(page);
                pageControl.setUrlPattern("dashboardadmin?action=accountNewest&");
                break;
            case "mostDebtor":
                accountList = accDAO.haveMostDebtor();
                pageControl.setUrlPattern("dashboardadmin?action=mostDebtor&");
                break;
            case "adminSearch":
                String keyword = request.getParameter("searchString");
                totalRecord = accDAO.totalRecordSearchUsername(keyword);
                accountList = accDAO.getListByUsername(keyword, page);
                pageControl.setUrlPattern("dashboardadmin?action=adminSearch&searchString="+keyword+"&");
                break;
            default:
                //phan trang o trang home
                //tim ve totalRecord
                totalRecord = accDAO.totalAccount();
                accountList = accDAO.getAllAccounts(page);
                pageControl.setUrlPattern("dashboardadmin?action=adminViewAccount&");
                break;
        }

        //tim xem tong co bao nhieu page
        int totalPage = (totalRecord % Pagination.RECORD_PER_PAGE) == 0
                ? (totalRecord / Pagination.RECORD_PER_PAGE)
                : (totalRecord / Pagination.RECORD_PER_PAGE) + 1;
        //set nhung gia tri vao pageControl
        pageControl.setPage(page);
        pageControl.setTotalPage(totalPage);
        pageControl.setTotalRecord(totalRecord);

        return accountList;

    }

    private List<Debtor> paginationDebtor(HttpServletRequest request, PageControl pageControl) {
        int idAccount = Integer.parseInt(request.getParameter("idAccounts"));
        String pageRaw = request.getParameter("page");
        DebtorDAO debtorDAO = new DebtorDAO();
        //valid page
        int page;
        try {
            page = Integer.parseInt(pageRaw);
        } catch (Exception e) {
            page = 1;
        }
        int totalRecord = 0;
        List<Debtor> listDebtor = null;
        //get action hien tai muon lam gi
        //tim xem co bao nhieu record va listDebtor by page
        String action = request.getParameter("action") == null
                ? "defaultFindAll"
                : request.getParameter("action");
        switch (action) {
            case "search":
//                String searchType = request.getParameter("searchType");
                String keyword = request.getParameter("searchQuery");
//                switch (searchType) {
//                    case "name":
//                        totalRecord = debtorDAO.findTotalRecordByName(idAccount, keyword);
//                        listDebtor = debtorDAO.findByPageByName(idAccount, keyword, page);
//                        pageControl.setUrlPattern("dashboardadmin?action=search&searchType=name&searchQuery=" + keyword + "&idAccount=" + idAccount + "&");
//                        break;
//                    case "address":
//                        totalRecord = debtorDAO.findTotalRecordByAddress(idAccount, keyword);
//                        listDebtor = debtorDAO.findByPageByAddress(idAccount, keyword, page);
//                        pageControl.setUrlPattern("dashboardadmin?action=search&searchType=address&searchQuery=" + keyword + "&idAccount=" + idAccount + "&");
//                        break;
//                    case "phone":
//                        totalRecord = debtorDAO.findTotalRecordByPhone(idAccount, keyword);
//                        listDebtor = debtorDAO.findByPageByPhone(idAccount, keyword, page);
//                        pageControl.setUrlPattern("dashboardadmin?action=search&searchType=phone&searchQuery=" + keyword + "&idAccount=" + idAccount + "&");
//                        break;
//                    case "email":
//                        totalRecord = debtorDAO.findTotalRecordByEmail(idAccount, keyword);
//                        listDebtor = debtorDAO.findByPageByEmail(idAccount, keyword, page);
//                        pageControl.setUrlPattern("dashboardadmin?action=search&searchType=email&searchQuery=" + keyword + "&idAccount=" + idAccount + "&");
//                        break;
//                    default:
//                        // Xử lý mặc định nếu không có lựa chọn nào phù hợp
                        totalRecord = debtorDAO.findTotalRecord(idAccount);
                        //tim ve danh sach debt o trang chi dinh
                        listDebtor = debtorDAO.findByPage(idAccount, page);
                        pageControl.setUrlPattern("dashboardadmin?action=adminViewDebtor&idAccounts=" + idAccount + "&");
//                        break;
//                }
                break;
            case "sortByOldest":
//                int idAccount = Integer.parseInt(request.getParameter("idAccount"));
                totalRecord = debtorDAO.findTotalRecord(idAccount);
                listDebtor = debtorDAO.findByPageAndSortByOldest(idAccount, page);
                pageControl.setUrlPattern("dashboardadmin?action=sortByOldest&idAccounts=" + idAccount + "&");
                //request.setAttribute("debtList", debtList);
                break;

            case "sortByNewest":
                totalRecord = debtorDAO.findTotalRecord(idAccount);
                listDebtor = debtorDAO.findByPageAndSortByNewest(idAccount, page);
                pageControl.setUrlPattern("dashboardadmin?action=sortByNewest&idAccounts=" + idAccount + "&");
                //request.setAttribute("debtList", debtList);

                break;
            case "sortByHighLow":
                totalRecord = debtorDAO.findTotalRecord(idAccount);
                listDebtor = debtorDAO.findByPageAndSortDebtByAmountHighLow(idAccount, page);
                pageControl.setUrlPattern("dashboardadmin?action=sortByHighLow&idAccounts=" + idAccount + "&");
                //request.setAttribute("debtList", debtList);

                break;
            case "sortByLowHigh":
                totalRecord = debtorDAO.findTotalRecord(idAccount);
                listDebtor = debtorDAO.findByPageAndSortDebtByAmountLowHigh(idAccount, page);
                pageControl.setUrlPattern("dashboardadmin?action=sortByLowHigh&idAccounts=" + idAccount + "&");
                //request.setAttribute("debtList", debtList);

                break;

            default:
                //phan trang o trang home
                //tim ve totalRecord
                totalRecord = debtorDAO.findTotalRecord(idAccount);
                //tim ve danh sach teddybear o trang chi dinh
                listDebtor = debtorDAO.findByPage(idAccount, page);
                pageControl.setUrlPattern("dashboardadmin?action=adminViewDebtor&idAccounts=" + idAccount + "&");
        }

        //tim xem tong co bao nhieu page
        int totalPage = (totalRecord % Pagination.RECORD_PER_PAGE) == 0
                ? (totalRecord / Pagination.RECORD_PER_PAGE)
                : (totalRecord / Pagination.RECORD_PER_PAGE) + 1;
        //set nhung gia tri vao pageControl
        pageControl.setPage(page);
        pageControl.setTotalPage(totalPage);
        pageControl.setTotalRecord(totalRecord);

        return listDebtor;
    }

    private List<DebtDetail> paginationDebt(HttpServletRequest request, PageControl pageControl) {
        int accountId = Integer.parseInt(request.getParameter("idAccountDebtor"));
        int debtorId = Integer.parseInt(request.getParameter("debtorid"));
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
        String action = request.getParameter("action") == null
                ? "defaultFindAll"
                : request.getParameter("action");
        switch (action) {
            case "searchDebt":
//                String searchType = request.getParameter("searchType");
                String keyword = request.getParameter("searchQuery");
                totalRecord = debtDAO.findTotalRecordToSearch(accountId, debtorId, keyword);
                debtList = debtDAO.findByPageToSearch(accountId, debtorId, keyword, page);
                pageControl.setUrlPattern("dashboardadmin?action=searchDebt&idAccountDebtor=" + accountId + "&debtorid=" + debtorId + "&");
                break;
            case "sortByOldestDebt":
                totalRecord = debtDAO.findTotalRecord(accountId, debtorId);
                debtList = debtDAO.findByPageAndSortByOldest(accountId, debtorId, page);
                pageControl.setUrlPattern("dashboardadmin?action=sortByOldestDebt&idAccountDebtor=" + accountId + "&debtorid=" + debtorId + "&");
                //request.setAttribute("debtList", debtList);
                break;

            case "sortByNewestDebt":
                totalRecord = debtDAO.findTotalRecord(accountId, debtorId);
                debtList = debtDAO.findByPageAndSortByNewest(accountId, debtorId, page);
                pageControl.setUrlPattern("dashboardadmin?action=sortByNewestDebt&idAccountDebtor=" + accountId + "&debtorid=" + debtorId + "&");
                //request.setAttribute("debtList", debtList);

                break;
            case "sortByHighLowDebt":
                totalRecord = debtDAO.findTotalRecord(accountId, debtorId);
                debtList = debtDAO.findByPageAndSortDebtByAmountHighLow(accountId, debtorId, page);
                pageControl.setUrlPattern("dashboardadmin?action=sortByHighLowDebt&idAccountDebtor=" + accountId + "&debtorid=" + debtorId + "&");
                //request.setAttribute("debtList", debtList);

                break;
            case "sortByLowHighDebt":
                totalRecord = debtDAO.findTotalRecord(accountId, debtorId);
                debtList = debtDAO.findByPageAndSortDebtByAmountLowHigh(accountId, debtorId, page);
                pageControl.setUrlPattern("dashboardadmin?action=sortByLowHighDebt&idAccountDebtor=" + accountId + "&debtorid=" + debtorId + "&");
                //request.setAttribute("debtList", debtList);

                break;
            case "ReceivableOfDebt":
                totalRecord = debtDAO.findTotalRecordByDebtType(accountId, debtorId, 3);
                debtList = debtDAO.findByPageByDebtType(accountId, debtorId, 3, page);
                pageControl.setUrlPattern("dashboardadmin?action=ReceivableOfDebt&idAccountDebtor=" + accountId + "&debtorid=" + debtorId + "&");
                //request.setAttribute("debtList", debtList);

                break;
            case "DebtOfDebt":
                totalRecord = debtDAO.findTotalRecordByDebtType(accountId, debtorId, 4);
                debtList = debtDAO.findByPageByDebtType(accountId, debtorId, 4, page);
                pageControl.setUrlPattern("dashboardadmin?action=DebtOfDebt&idAccountDebtor=" + accountId + "&debtorid=" + debtorId + "&");
                //request.setAttribute("debtList", debtList);
                break;
            case "LoanOfDebt":
                totalRecord = debtDAO.findTotalRecordByDebtType(accountId, debtorId, 1);
                debtList = debtDAO.findByPageByDebtType(accountId, debtorId, 1, page);
                pageControl.setUrlPattern("dashboardadmin?action=LoanOfDebt&idAccountDebtor=" + accountId + "&debtorid=" + debtorId + "&");
                //request.setAttribute("debtList", debtList);
                break;
            case "LendOfDebt":
                totalRecord = debtDAO.findTotalRecordByDebtType(accountId, debtorId, 2);
                debtList = debtDAO.findByPageByDebtType(accountId, debtorId, 2, page);
                pageControl.setUrlPattern("dashboardadmin?action=LendOfDebt&idAccountDebtor=" + accountId + "&debtorid=" + debtorId + "&");
                //request.setAttribute("debtList", debtList);
                break;
            default:
                //phan trang o trang home
                //tim ve totalRecord
                totalRecord = debtDAO.findTotalRecord(accountId, debtorId);
                debtList = debtDAO.findByPage(accountId, debtorId, page);
                pageControl.setUrlPattern("dashboardadmin?action=adminViewDebt&idAccountDebtor=" + accountId + "&debtorid=" + debtorId + "&");
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

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
