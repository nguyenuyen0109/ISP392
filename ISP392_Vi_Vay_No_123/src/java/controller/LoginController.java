/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.AccountDAO;
import utils.Hash;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.OutputStream;
import javax.imageio.ImageIO;
import model.Account;
import utils.Captcha;
import utils.Mail;
import utils.Token;

/**
 *
 * @author MINIMONIE
 */
public class LoginController extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String token = request.getParameter("t");

        if (token == null) {
            request.getRequestDispatcher("client/login.jsp").forward(request, response);
            return;
        }

        String checkToken = (String) request.getSession().getAttribute("waiting_token");

        if (token.equals(checkToken)) {

            HttpSession session = request.getSession();

            session.removeAttribute("waiting_token");

            session.setAttribute("USER", session.getAttribute("waiting_USER"));
            session.setAttribute("Admin", session.getAttribute("waiting_Admin"));

            response.sendRedirect("client/home.jsp");
            return;

        } else {
            request.setAttribute("toast", "Invalid token");
            request.getRequestDispatcher("/client/login.jsp").forward(request, response);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        String hashedPassword = new Hash().hashPassword(password);

        AccountDAO accountDAO = new AccountDAO();
        Account accountFound = accountDAO.findByUsernameAndPassword(username, hashedPassword);
        String url = "";
        if (accountFound != null) {
            session.setAttribute("USER", accountFound);
            boolean isAdmin = accountDAO.isAdmin(username);
            session.setAttribute("Admin", isAdmin);

            // Thay vì gửi email, bạn có thể đăng nhập người dùng ngay lập tức
            response.sendRedirect("client/homepage.jsp"); // Đảm bảo rằng URL này chính xác
            return;
        } else {
            request.setAttribute("msg", "Invalid username or password");
            request.getRequestDispatcher("/client/login.jsp").forward(request, response);
        }

        request.getRequestDispatcher(url).forward(request, response);
    }

//    protected void doPost(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//
//        HttpSession session = request.getSession();
//        String username = request.getParameter("username");
//        String password = request.getParameter("password");
//        String hashedPassword = MD5.getMd5(password);
//        String inputCaptcha = request.getParameter("captcha");
//        String generatedCaptcha = (String) session.getAttribute("generatedCaptcha");
//        boolean isCaptchaValid = generatedCaptcha != null && generatedCaptcha.equals(inputCaptcha);
//        String url = "";
//
//        //String generatedCaptcha = (String) session.getAttribute("captcha");
//        //tim account dua tren username and password
//        AccountDAO accountDAO = new AccountDAO();
//        Account accountFound = accountDAO.findByUsernameAndPassword(username, password);
//        System.out.println(accountFound);
//        //trong th dung => chuyen ve dau do 
//        if (isCaptchaValid && accountFound != null) {
//            //TODO: ....
//            session.setAttribute("USER", accountFound);
//            boolean isAdmin = accountDAO.isAdmin(username);
//            session.setAttribute("Admin", isAdmin);
//            url = "client/home.jsp";
//            //if (generatedCaptcha != null && generatedCaptcha.equals(captchaInput)){
//            System.out.println("Successful");
//            session.setAttribute("account", accountFound);
//            //   response.sendRedirect("/client/home.jsp");
//        } else {
////            //trong TH sai => bao ve loi o trang login
////            CaptchaDAO captchaDAO = new CaptchaDAO();
////            String generatedCaptchanew = captchaDAO.generateCaptcha();
////            //HttpSession session = request.getSession();
////            session.setAttribute("generatedCaptcha", generatedCaptchanew);
//            url = "client/login.jsp";
//            request.setAttribute("error", "Login failed !!");
//
//            // request.getRequestDispatcher("/client/login.jsp").forward(request, response);
//        }
//
//        request.getRequestDispatcher(url).forward(request, response);
//    }*/
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
            out.println("<h1>Servlet LoginController at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

}
