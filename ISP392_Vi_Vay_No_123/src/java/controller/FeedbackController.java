/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.FeedbackDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import model.Feedback;
import model.PageControl;
import utils.Pagination;

/**
 *
 * @author admin
 */
public class FeedbackController extends HttpServlet {

    

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PageControl pageControl = new PageControl();
        HttpSession session = request.getSession();
        Integer accountId = (Integer) session.getAttribute("account_id");

        if (accountId == null) {
            // Handle case where accountId is not set in session, perhaps redirecting to a login page or showing an error message.
            response.sendRedirect("login"); // Example redirection to login page
            return; // Stop further execution in this case.
        }
        // Now use this accountId to get data from the database
        List<Feedback> listFeedback = pagination(request, pageControl);
        //get du lieu tu db

        //set list debtor vao session
        session.setAttribute("listFeedback", listFeedback);
        request.setAttribute("pageControl", pageControl);
        System.out.println(pageControl);

        request.getRequestDispatcher("client/feedbackList.jsp").forward(request, response);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("USER") == null) {
            response.sendRedirect("login.jsp");
            return;
        }
        Integer account_id = (Integer) session.getAttribute("account_id");
        if (account_id == null) {

            response.sendRedirect("login.jsp");
            return;
        }
        try {
            double rating = Double.parseDouble(request.getParameter("rating"));
            String opinion = request.getParameter("opinion");

            Feedback feedback = new Feedback();
            FeedbackDAO dao = new FeedbackDAO();
            feedback.setAccount_id(account_id);
            feedback.setRate(rating);
            feedback.setFeedback(opinion);
            java.sql.Timestamp Timestamp = new java.sql.Timestamp(System.currentTimeMillis());
            feedback.setCreateAt(Timestamp);
            feedback.setIsDeleted(false);
            dao.addFeedback(feedback);

            response.sendRedirect("client/homepage.jsp");
        } catch (Exception e) {

            throw new ServletException(e);
        }
    }

    private List<Feedback> pagination(HttpServletRequest request, PageControl pageControl) {
        HttpSession session = request.getSession();
        Integer accountId = (Integer) session.getAttribute("account_id");
        //get page
        String pageRaw = request.getParameter("page");
        FeedbackDAO feedbackDAO = new FeedbackDAO();
        //valid page
        int page;
        try {
            page = Integer.parseInt(pageRaw);
        } catch (Exception e) {
            page = 1;
        }
        int totalRecord = 0;
        List<Feedback> listFeedback = null;
        //get action hien tai muon lam gi
        //tim xem co bao nhieu record va listDebt by page
        String action = request.getParameter("action") == null
                ? "defaultFindAll"
                : request.getParameter("action");
        switch (action) {
            case "search":
//                String searchType = request.getParameter("searchType");
                String keyword = request.getParameter("searchQuery");
//                totalRecord = feedbackDAO.findTotalRecordToSearch(accountId, debtorId, keyword);
//                listFeedback = feedbackDAO.findByPageToSearch(accountId, debtorId, keyword, page);
                pageControl.setUrlPattern("feedback?");
                break;
            case "sortByOldest":
//                totalRecord = feedbackDAO.findTotalRecord(accountId, debtorId);
//                listFeedback = feedbackDAO.findByPageAndSortByOldest(accountId, debtorId, page);
                pageControl.setUrlPattern("feedback?action=sortByOldest&");
                //request.setAttribute("debtList", debtList);
                break;

            case "sortByNewest":
//                totalRecord = feedbackDAO.findTotalRecord(accountId, debtorId);
//                listFeedback = feedbackDAO.findByPageAndSortByNewest(accountId, debtorId, page);
                pageControl.setUrlPattern("feedback?action=sortByNewest&");
                //request.setAttribute("debtList", debtList);

                break;
            default:
                //phan trang o trang home
                //tim ve totalRecord
                totalRecord = feedbackDAO.findTotalRecord();
                listFeedback = feedbackDAO.findByPage(page);
                pageControl.setUrlPattern("feedback?");
        }

        //tim xem tong co bao nhieu page
        int totalPage = (totalRecord % Pagination.RECORD_PER_PAGE) == 0
                ? (totalRecord / Pagination.RECORD_PER_PAGE)
                : (totalRecord / Pagination.RECORD_PER_PAGE) + 1;
        //set nhung gia tri vao pageControl
        pageControl.setPage(page);
        pageControl.setTotalPage(totalPage);
        pageControl.setTotalRecord(totalRecord);

        return listFeedback;
    }

}
