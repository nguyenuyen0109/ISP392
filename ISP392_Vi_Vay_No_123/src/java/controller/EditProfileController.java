/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.AccountDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Account;

/**
 *
 * @author lvhn1
 */
@WebServlet(name = "EditProfileController", urlPatterns = {"/profile"})
public class EditProfileController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet EditProfileController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet EditProfileController at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Account account = (Account) request.getSession().getAttribute("USER");
        //account = new Account();
        //account.setId(2);
// Trong một servlet

        if (account == null) {
            response.sendRedirect("login");
            return;
        }

        request.getSession().setAttribute("account", account);
        request.getRequestDispatcher("/client/editprofile.jsp").forward(request, response);
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
        // Lấy đối tượng Account từ session
        Account account = (Account) request.getSession().getAttribute("account");

        // Kiểm tra nếu Account là null, chuyển hướng người dùng đến trang đăng nhập
        if (account == null) {
            response.sendRedirect("login");
            return;
        }

        // Lấy dữ liệu từ form
        String fullName = request.getParameter("name");
        String phone = request.getParameter("phone");
        String address = request.getParameter("address");
        String email = request.getParameter("email");
        String avatar = request.getParameter("avatar");

        // Cập nhật thông tin Account
        account.setName(fullName);
        account.setMobileNumber(phone);
        account.setAddress(address);
       // account.setEmailAddress(email);
        account.setAvatarUrl(avatar);

        // Lưu cập nhật vào cơ sở dữ liệu thông qua AccountDAO
        AccountDAO dao = new AccountDAO();
        
        dao.updateAccount(account);

        // Cập nhật lại đối tượng Account trong session
        request.getSession().setAttribute("account", account);

        // Chuyển hướng người dùng hoặc hiển thị thông báo thành công
        request.setAttribute("alert", "Profile updated successfully!");
        request.getRequestDispatcher("/client/editprofile.jsp").forward(request, response);
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
