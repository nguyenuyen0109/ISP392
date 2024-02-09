/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 *
 * @author admin
 */
public class VerifyCaptchaController extends HttpServlet {

    private final String LOGIN_PAGE = "/client/login.jsp";
    private final String REGISTER_PAGE = "/client/register.jsp";
    private final String FORGOT_PAGE = "/client/forgotpassword.jsp";
    private final String CHANGEPASSWORD_PAGE = "/client/changepassword.jsp";
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
            out.println("<title>Servlet VerifyCaptcha</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet VerifyCaptcha at " + request.getContextPath() + "</h1>");
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
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();
        String uri = request.getParameter("uri");
        String jsp_uri;
        if (uri.equalsIgnoreCase(LOGIN_PAGE)) {
            uri = "/login";
            jsp_uri = LOGIN_PAGE;
        } else if (uri.equalsIgnoreCase(REGISTER_PAGE)) {
            uri = "/register";
            jsp_uri = REGISTER_PAGE;
        } else if(uri.equalsIgnoreCase(FORGOT_PAGE)){
            uri = "/forgot";
            jsp_uri = FORGOT_PAGE;
        } else{
            uri = "/changepassword";
            jsp_uri = CHANGEPASSWORD_PAGE;
        }
        String captchaInput = (String) request.getParameter("captcha");
        String captchaText = (String) session.getAttribute("captchaText");
        if (!captchaInput.equalsIgnoreCase(captchaText)) {
            request.setAttribute("msg", "Invalid captcha!");
            request.getRequestDispatcher(jsp_uri).forward(request, response);
        }
        request.getRequestDispatcher(uri).forward(request, response);
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
