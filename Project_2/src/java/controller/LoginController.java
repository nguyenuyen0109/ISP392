/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller;

import dao.AccountDAO;
import dao.CaptchaDAO;
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
 * @author MINIMONIE
 */

public class LoginController extends HttpServlet {
   
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        //request.getRequestDispatcher("client/login.jsp").forward(request, response);
//        CaptchaDAO captchaDAO = new CaptchaDAO();
//        String captcha = captchaDAO.generateCaptcha();
//        HttpSession session = request.getSession();
//        session.setAttribute("captcha", captcha);
//
//        // Redirect to login page
//        response.sendRedirect("client/login.jsp");  

//        CaptchaDAO captchaDao = new CaptchaDAO();
//        String captcha = captchaDao.generateCaptcha();
//        request.getSession().setAttribute("generatedCaptcha", captcha);
//        response.getWriter().write(captcha);
    CaptchaDAO captchaDAO = new CaptchaDAO();
    String generatedCaptcha = captchaDAO.generateCaptcha();
        System.out.println(generatedCaptcha);
    // Lưu vào session hoặc request attribute
   HttpSession session = request.getSession();
    session.setAttribute("generatedCaptcha", generatedCaptcha);
    // Chuyển hướng đến trang JSP
    request.getRequestDispatcher("client/login.jsp").forward(request, response);
    } 

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {

    HttpSession session = request.getSession();
    String username = request.getParameter("username");
    String password = request.getParameter("password");
    String inputCaptcha = request.getParameter("captcha");
    String generatedCaptcha = (String) session.getAttribute("generatedCaptcha");
    boolean isCaptchaValid = generatedCaptcha != null && generatedCaptcha.equals(inputCaptcha);
        String url="";
        
        //String generatedCaptcha = (String) session.getAttribute("captcha");
        //tim account dua tren username and password
        AccountDAO accountDAO = new AccountDAO();
        Account accountFound = accountDAO.findByUsernameAndPassword(username, password);
        System.out.println(accountFound);
        //trong th dung => chuyen ve dau do 
        if(isCaptchaValid && accountFound !=null) {
            //TODO: ....
            session.setAttribute("USER",accountFound);
            boolean isAdmin = accountDAO.isAdmin(username);
            session.setAttribute("Admin", isAdmin);
           url = "client/home.jsp";
            //if (generatedCaptcha != null && generatedCaptcha.equals(captchaInput)){
            System.out.println("Successful");
            session.setAttribute("account", accountFound);
          //   response.sendRedirect("/client/home.jsp");
    }else {
            //trong TH sai => bao ve loi o trang login
            CaptchaDAO captchaDAO = new CaptchaDAO();
            String generatedCaptchanew = captchaDAO.generateCaptcha();
            //HttpSession session = request.getSession();
            session.setAttribute("generatedCaptcha", generatedCaptchanew);
            url = "client/login.jsp";
            request.setAttribute("error", "Login failed !!");

           // request.getRequestDispatcher("/client/login.jsp").forward(request, response);


        }
        
        request.getRequestDispatcher(url).forward(request, response);
    }
    
       
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet LoginController</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet LoginController at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    } 
}


