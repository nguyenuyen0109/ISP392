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
import utils.Captcha;

/**
 *
 * @author admin
 */
public class ChangepasswordController extends HttpServlet {

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

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Captcha c = new Captcha();
        String text = c.generateCaptchaText();
        BufferedImage captchaImage = c.generateCaptchaImage(text);
//        ByteArrayOutputStream baos = new ByteArrayOutputStream();
//        ImageIO.write(captchaImage, "png", baos);
//        byte[] imageBytes = baos.toByteArray();
//        String base64Image = Base64.getEncoder().encodeToString(imageBytes);
//        request.setAttribute("captchaImageBase64", base64Image);
//        request.getRequestDispatcher("/client/.jsp").forward(request, response);
        response.setContentType("image/png");
        session.setAttribute("captchaText", text);
        OutputStream outputStream = response.getOutputStream();
        ImageIO.write(captchaImage, "png", outputStream);
        outputStream.close();
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
        String newPassword = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmPassword");
        String capcha = request.getParameter("capcha");
        HttpSession session = request.getSession();
        String generatedCapcha = (String) session.getAttribute("CAPCHA");
        if (generatedCapcha != null && generatedCapcha.equals(capcha)) {
            if (newPassword != null && newPassword.equals(confirmPassword)) {
                String username = (String) session.getAttribute("username");
                AccountDAO accountDAO = new AccountDAO();
                if(accountDAO.updatePassword(username, newPassword)){
                    response.sendRedirect("changepassword.jsp");
                }else{
                    request.setAttribute("error", "Falied to change password.");
                    request.getRequestDispatcher("changepassword.jsp").forward(request, response);
                }
            }else{
                request.setAttribute("error", "Passwords do not match.");
                request.getRequestDispatcher("changepassword.jsp").forward(request, response);
            }
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
