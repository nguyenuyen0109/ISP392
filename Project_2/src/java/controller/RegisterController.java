/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.AccountDAO;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Account;

public class RegisterController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
      //response.setContentType("text/html;charset=UTF-8");
      AccountDAO dao = new AccountDAO();
      String username = request.getParameter("username");
      String url;
if(dao.usernameExists(username)){
                    System.out.println("Cam co username da ton tai");
                    url = "/client/home.jsp";
                }else{
                    System.out.println("Cam co username chua ton tai");
                    String fullName = request.getParameter("name");
                    String phone = request.getParameter("phone");
                    String email = request.getParameter("email");
                    String userName = request.getParameter("username");
                    String passWord = request.getParameter("password");

                    Account acc = new Account(0, userName, 0, passWord,
                            fullName, phone, email, null, true,
                            null, null, null, true);
                    dao.insertAccount(acc);
//                    response.sendRedirect("register");
                    url = "/client/home.jsp";
                }
                request.getRequestDispatcher(url).forward(request, response);
  }
    
  }
   


