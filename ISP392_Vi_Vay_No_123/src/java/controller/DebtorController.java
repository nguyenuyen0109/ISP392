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
import utils.Pagination;

/**
 *
 * @author ADMIN
 */
public class DebtorController extends HttpServlet {

    DebtorDAO debtor = new DebtorDAO();

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
            //response.sendRedirect("debtor");
            request.setAttribute("successMessage", "Debtor added successfully!");
        } else {
            request.setAttribute("errorMessage", "Debt cannot be added.");
            request.getRequestDispatcher("client/debtor.jsp").forward(request, response);

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
        int totalRecord = 0;
        List<Debtor> listDebtor = null;
        //get action hien tai muon lam gi
        //tim xem co bao nhieu record va listDebtor by page
        String action = request.getParameter("action") == null
                ? "defaultFindAll"
                : request.getParameter("action");
        switch (action) {
            case "search":
                String searchType = request.getParameter("searchType");
                String keyword = request.getParameter("searchQuery");
                switch (searchType) {
                    case "name":
                        totalRecord = debtorDAO.findTotalRecordByName(accountId, keyword);
                        listDebtor = debtorDAO.findByPageByName(accountId, keyword, page);
                        pageControl.setUrlPattern("debtor?action=search&searchType=name&searchQuery=" + keyword + "&");
                        break;
                    case "address":
                        totalRecord = debtorDAO.findTotalRecordByAddress(accountId, keyword);
                        listDebtor = debtorDAO.findByPageByAddress(accountId, keyword, page);
                        pageControl.setUrlPattern("debtor?action=search&searchType=address&searchQuery=" + keyword + "&");
                        break;
                    case "phone":
                        totalRecord = debtorDAO.findTotalRecordByPhone(accountId, keyword);
                        listDebtor = debtorDAO.findByPageByPhone(accountId, keyword, page);
                        pageControl.setUrlPattern("debtor?action=search&searchType=phone&searchQuery=" + keyword + "&");
                        break;
                    case "email":
                        totalRecord = debtorDAO.findTotalRecordByEmail(accountId, keyword);
                        listDebtor = debtorDAO.findByPageByEmail(accountId, keyword, page);
                        pageControl.setUrlPattern("debtor?action=search&searchType=email&searchQuery=" + keyword + "&");
                        break;
                    default:
                        // Xử lý mặc định nếu không có lựa chọn nào phù hợp
                        totalRecord = debtorDAO.findTotalRecord(accountId);
                        //tim ve danh sach debt o trang chi dinh
                        listDebtor = debtorDAO.findByPage(accountId, page);
                        pageControl.setUrlPattern("debtor?");
                        break;
                }
                break;
            case "sortByOldest":
                    totalRecord = debtorDAO.findTotalRecord(accountId);
                    listDebtor = debtorDAO.findByPageAndSortByOldest(accountId, page);
                    pageControl.setUrlPattern("debtor?action=sortByOldest&");
                    //request.setAttribute("debtList", debtList);
                    break;
                    
                case "sortByNewest":
                    totalRecord = debtorDAO.findTotalRecord(accountId);
                    listDebtor = debtorDAO.findByPageAndSortByNewest(accountId, page);
                    pageControl.setUrlPattern("debtor?action=sortByNewest&");
                    //request.setAttribute("debtList", debtList);

                    break;
                case "sortByHighLow":
                    totalRecord = debtorDAO.findTotalRecord(accountId);
                    listDebtor = debtorDAO.findByPageAndSortDebtByAmountHighLow(accountId, page);
                    pageControl.setUrlPattern("debtor?action=sortByHighLow&");
                    //request.setAttribute("debtList", debtList);

                    break;
                case "sortByLowHigh":
                    totalRecord = debtorDAO.findTotalRecord(accountId);
                    listDebtor = debtorDAO.findByPageAndSortDebtByAmountLowHigh(accountId, page);
                    pageControl.setUrlPattern("debtor?action=sortByLowHigh&");
                    //request.setAttribute("debtList", debtList);

                    break;

            default:
                //phan trang o trang home
                //tim ve totalRecord
                totalRecord = debtorDAO.findTotalRecord(accountId);
                //tim ve danh sach teddybear o trang chi dinh
                listDebtor = debtorDAO.findByPage(accountId, page);
                pageControl.setUrlPattern("debtor?");
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

}
