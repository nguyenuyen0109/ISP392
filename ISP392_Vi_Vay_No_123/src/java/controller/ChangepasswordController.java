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
import java.awt.image.BufferedImage;
//import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
//import java.util.Base64;
import javax.imageio.ImageIO;
import model.Account;
import utils.Captcha;
import utils.Hash;
import utils.Validate;

/**
 *
 * @author admin
 */
public class ChangePasswordController extends HttpServlet {

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
            out.println("<title>Servlet ChangpasswordController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ChangpasswordController at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        // Assuming 'accountId' is stored in session, retrieve it.
        Integer accountId = (Integer) session.getAttribute("account_id");
//        accountId = 

        if (accountId == null) {
            // Handle case where accountId is not set in session, perhaps redirecting to a login page or showing an error message.
            response.sendRedirect("login"); // Example redirection to login page
            return; // Stop further execution in this case.
        }
        request.getRequestDispatcher("/client/changepassword.jsp").forward(request, response);
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        String newPassword = request.getParameter("password");
//        String confirmPassword = request.getParameter("confirmPassword");
        if (request.getSession().getAttribute("USER") == null) {
            response.sendRedirect("login");
        }
        try {
            String oldPassword = request.getParameter("oldPassword");
            String newpassword = request.getParameter("password");
            String confirmPassword = request.getParameter("confirmPassword");
            String capcha = request.getParameter("capcha");
            HttpSession session = request.getSession();
            String generatedCapcha = (String) session.getAttribute("CAPCHA");
            Integer accountId = (Integer) session.getAttribute("account_id");
            String username = (String) session.getAttribute("username");
            AccountDAO accountDAO = new AccountDAO();
            if(!Validate.isValidPassword(newpassword)){
            request.setAttribute("alert", "Invalid information");
            return;
            }
            if(!newpassword.equals(confirmPassword)){
                request.setAttribute("alert","Password do not match");
            }
            if(new Hash().hashPassword(oldPassword).equals(new Hash().hashPassword(newpassword))){
                request.setAttribute("alert", "New password must be different from old password!");
                request.getRequestDispatcher("/client/changepassword.jsp").forward(request, response);
            }
            if (new Hash().hashPassword(oldPassword).equals(accountDAO.getPasswordById(accountId))) {
                if (accountDAO.updatePassword(username, new Hash().hashPassword(newpassword))) {
                    request.setAttribute("alert", "Change password successfully!");
                    Thread.sleep(5000);
                    session.removeAttribute(String.valueOf(accountId));
                    request.getRequestDispatcher("/client/login.jsp").forward(request, response);
                } else {
                    request.setAttribute("alert", "Falied to change password.");
                    request.getRequestDispatcher("/client/changepassword.jsp").forward(request, response);
                }
            } else {
                request.setAttribute("alert", "Wrong old password.");
                request.getRequestDispatcher("/client/changepassword.jsp").forward(request, response);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
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

