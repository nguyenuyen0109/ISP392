package controller;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Account;
import dao.AccountDAO;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpSession;
import utils.Captcha;
import utils.Hash;
import utils.Mail;
import utils.Token;

@WebServlet(name = "RegisterController", urlPatterns = {"/register"})
public class RegisterController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Retrieve form data from the request
        String username = request.getParameter("username");
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("pass");
        String rePassword = request.getParameter("re_pass");
        // Perform any validation checks here
//        if (!password.equals(rePassword)) {
//            request.setAttribute("alert", "Passwords do not match. Please try again.");
//            request.getRequestDispatcher("/client/register.jsp").forward(request, response);
//            return;
//        }

        // You can add more validation logic here
        // Create an instance of AccountDAO
        AccountDAO accountDAO = new AccountDAO();

        // Check if the username or email already exists
        if (accountDAO.checkUsernameAndEmailExists(username, email)) {
            request.setAttribute("alert", "Username or Email already exists. Please choose a different username or email.");
            request.getRequestDispatcher("/client/register.jsp").forward(request, response);
            return;
        }
        if(!password.equals(rePassword)){
            request.setAttribute("alert", "Password do not match");
        }

        // Create an Account object to store the registration information
        Account newAccount = new Account();
        newAccount.setUsername(username);
        newAccount.setName(name);
        newAccount.setEmailAddress(email);
        newAccount.setPassword(new Hash().hashPassword(password));

        String token = new Token().generateRandomToken(18);
        String url = "http://" + request.getServerName() + ":" + request.getServerPort()
                + request.getContextPath() + "/register?t=" + token + "&e=" + email;

        request.getSession().setAttribute("register_" + email, newAccount);
        request.getSession().setAttribute("register_token_" + email, token);

        new Mail().sendEmail(email, "Register", "Click here to register: " + url);

        
        request.setAttribute("alert", "An email was sent!");
         request.getRequestDispatcher("/client/register.jsp").forward(request, response);
       

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String token = request.getParameter("t");
        String email = request.getParameter("e");

        if (token == null) {
            request.getRequestDispatcher("client/register.jsp").forward(request, response);
            return;
        }
        String url= null;
        String checkToken = (String) request.getSession().getAttribute("register_token_" + email);
        Account newAccount = (Account) request.getSession().getAttribute("register_" + email);

        if (token.equals(checkToken)) {

            request.getSession().removeAttribute("register_token_" + email);
            
            new AccountDAO().insertAccount(newAccount);
            request.setAttribute("alert", "Register success");
            url = "/client/login.jsp";
        } else {
            request.setAttribute("alert", "Invalid token");
            url = "/client/register.jsp";
        }

        request.getRequestDispatcher(url).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "RegisterController Servlet";
    }
}
