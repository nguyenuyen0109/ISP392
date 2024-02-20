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
import java.util.List;
import model.Debtor;
import model.PageControl;
import utils.Pagination;

/**
 *
 * @author ADMIN
 */
public class DebtorListController extends HttpServlet {
 


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        //set encoding UTF8
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        
        DebtorDAO debtorDAO = new DebtorDAO();
        PageControl pageControl = new PageControl();
        //tao session
        HttpSession session = request.getSession();
        
        //get du lieu tu db
        List<Debtor> listDebtor = pagination(request, pageControl);
        //set list debtor vao session
        session.setAttribute("listDebtor", listDebtor);
        request.setAttribute("pageControl", pageControl);
        System.out.println(pageControl);
        //chuyen ve debtor list
        request.getRequestDispatcher("client/debtorlist.jsp").forward(request, response);
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
            case "add":
                add(request,response);
                break;
            case "update":
                update(request);
                break;

            default:
                throw new AssertionError();
        }
        response.sendRedirect("debtorlist");
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

    private void add(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        // Add new debtor
        String name = request.getParameter("name");
        String address = request.getParameter("address");
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");
        double totalDebt = Double.parseDouble(request.getParameter("totalDebt"));

        Debtor newDebtor = new Debtor();
        newDebtor.setName(name);
        newDebtor.setAddress(address);
        newDebtor.setPhone(phone);
        newDebtor.setEmail(email);
        newDebtor.setTotalDebt(totalDebt);
        newDebtor.setAccount_id(2);

        // Save the new debtor
        DebtorDAO debtor = new DebtorDAO();
        boolean success = debtor.addDebtor(newDebtor);
        System.out.println(success);
        if (success) {
//            response.sendRedirect("debtor");
            request.setAttribute("successMessage", "Debtor added successfully!");
        } else {
            request.setAttribute("errorMessage", "Debt cannot be added.");
            request.getRequestDispatcher("client/debtorlist.jsp").forward(request, response);

        }
    }
    
    private List<Debtor> pagination(HttpServletRequest request, PageControl pageControl) {
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
                        totalRecord = debtorDAO.findTotalRecordByName(keyword);
                        listDebtor = debtorDAO.findByPageByName(keyword, page);
                        pageControl.setUrlPattern("debtorlist?action=search&searchType=name&searchQuery=" + keyword + "&");
                        break;
                    case "address":
                        totalRecord = debtorDAO.findTotalRecordByAddress(keyword);
                        listDebtor = debtorDAO.findByPageByAddress(keyword, page);
                        pageControl.setUrlPattern("debtorlist?action=search&searchType=address&searchQuery=" + keyword + "&");
                        break;
                    case "phone":
                        totalRecord = debtorDAO.findTotalRecordByPhone(keyword);
                        listDebtor = debtorDAO.findByPageByPhone(keyword, page);
                        pageControl.setUrlPattern("debtorlist?action=search&searchType=phone&searchQuery=" + keyword + "&");
                        break;
                    case "email":
                        totalRecord = debtorDAO.findTotalRecordByEmail(keyword);
                        listDebtor = debtorDAO.findByPageByEmail(keyword, page);
                        pageControl.setUrlPattern("debtorlist?action=search&searchType=email&searchQuery=" + keyword + "&");
                        break;
                    default:
                        // Xử lý mặc định nếu không có lựa chọn nào phù hợp
                        totalRecord = debtorDAO.findTotalRecord();
                        //tim ve danh sach debt o trang chi dinh
                        listDebtor = debtorDAO.findByPage(page);
                        pageControl.setUrlPattern("debtorlist?");
                        break;
                }
                break;
            case "sortByOldest":
                    totalRecord = debtorDAO.findTotalRecord();
                    listDebtor = debtorDAO.findByPageAndSortByOldest(page);
                    pageControl.setUrlPattern("debtorlist?action=sortByOldest&");
                    //request.setAttribute("debtList", debtList);
                    break;
                    
                case "sortByNewest":
                    totalRecord = debtorDAO.findTotalRecord();
                    listDebtor = debtorDAO.findByPageAndSortByNewest(page);
                    pageControl.setUrlPattern("debtorlist?action=sortByNewest&");
                    //request.setAttribute("debtList", debtList);

                    break;
                case "sortByHighLow":
                    totalRecord = debtorDAO.findTotalRecord();
                    listDebtor = debtorDAO.findByPageAndSortDebtByAmountHighLow(page);
                    pageControl.setUrlPattern("debtorlist?action=sortByHighLow&");
                    //request.setAttribute("debtList", debtList);

                    break;
                case "sortByLowHigh":
                    totalRecord = debtorDAO.findTotalRecord();
                    listDebtor = debtorDAO.findByPageAndSortDebtByAmountLowHigh(page);
                    pageControl.setUrlPattern("debtorlist?action=sortByLowHigh&");
                    //request.setAttribute("debtList", debtList);

                    break;

            default:
                //phan trang o trang home
                //tim ve totalRecord
                totalRecord = debtorDAO.findTotalRecord();
                //tim ve danh sach teddybear o trang chi dinh
                listDebtor = debtorDAO.findByPage(page);
                pageControl.setUrlPattern("debtorlist?");
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