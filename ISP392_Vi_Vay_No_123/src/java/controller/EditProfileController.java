/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.AccountDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Account;

/**
 *
 * @author admin
 */
public class EditProfileController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
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
    HttpSession session = request.getSession();
    Account loggedInUser = (Account) session.getAttribute("USER");

    if (loggedInUser == null) {
        response.sendRedirect("login.jsp");
        return;
    }

    // Giả sử bạn đã có phương thức getProfileByUsername trong AccountDAO
    AccountDAO accountDAO = new AccountDAO();
    Account userProfile = accountDAO.getProfileByUsername(loggedInUser.getUsername());

    if (userProfile != null) {
        request.setAttribute("userProfile", userProfile);
        request.getRequestDispatcher("/editprofile.jsp").forward(request, response);
    } else {
        response.sendRedirect("editprofile.jsp?error=UserNotFound");
    }
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
        // Lấy thông tin từ form
        String fullName = request.getParameter("fullName");
        String phone = request.getParameter("phone");
        String address = request.getParameter("address");
        String email = request.getParameter("email");

        HttpSession session = request.getSession();
        Account loggedInUser = (Account) session.getAttribute("USER");

        if (loggedInUser == null) {
        
            response.sendRedirect("login.jsp");
            return;
        }

        Account updatedAccount = new Account();
        updatedAccount.setId(loggedInUser.getId()); 
        updatedAccount.setUsername(loggedInUser.getUsername()); 
        updatedAccount.setName(fullName);
        updatedAccount.setMobileNumber(phone);
        updatedAccount.setEmailAddress(email);
        updatedAccount.setAddress(address);

        // Cập nhật thông tin vào cơ sở dữ liệu sử dụng AccountDAO
        AccountDAO accountDAO = new AccountDAO();
        boolean updateSuccess = accountDAO.editProfileByUsername(loggedInUser.getUsername(), updatedAccount);

        if (updateSuccess) {
            session.setAttribute("USER", updatedAccount);

            response.sendRedirect("profile.jsp?updateSuccess=true");
        } else {
            response.sendRedirect("editprofile.jsp?updateError=true");
        }
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
