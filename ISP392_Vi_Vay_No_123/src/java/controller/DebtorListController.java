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
                add(request);
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

    private void add(HttpServletRequest request) {
       
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
        //tim xem co bao nhieu record va listTeddyBear by page
        String action = request.getParameter("action") == null
                ? "defaultFindAll"
                : request.getParameter("action");
        switch (action) {
            case "searchName":
                //tim kiem co bnh record

                //tim ve listTeddy
                break;
            case "searchAddress":

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
