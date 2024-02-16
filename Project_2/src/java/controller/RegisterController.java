/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.AccountDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Random;
import model.Account;
import Util.MD5;

/**
 *
 * @author Admin
 */
public class RegisterController extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //response.setContentType("text/html;charset=UTF-8");
        AccountDAO dao = new AccountDAO();
        String username = request.getParameter("username");
        String url;
        if (dao.usernameExists(username)) {
            url = "/client/register.jsp";
            request.setAttribute("error", "Username Invalid");
        } else {

            String fullName = request.getParameter("name");
            String phone = request.getParameter("phone");
            String email = request.getParameter("email");
            //           String userName = request.getParameter("username");
            String passWord = request.getParameter("password");
            String confirmPass = request.getParameter("confirmPass");

            if (passWord.equals(confirmPass)
                    && !dao.isEmailExist(email)
                    && dao.isEmailValid(email)
                    && !dao.isPhoneExist(phone)) {
                String hashedPassword = MD5.getMd5(passWord);
                Account acc = new Account(0, username, 0, hashedPassword,
                        fullName, phone, email, null, true,
                        null, null, null, true);

                // dao.insertAccount(acc);
                String otp = generateRandomSixDigit();

                request.getSession().setAttribute(email + "_otp", otp);
                request.getSession().setAttribute(email + "_info", acc);

                sendEmail(email, "Confirm register", "Your OTP: " + otp);

                request.setAttribute("email", email);
                url = "/client/confirmOTP.jsp";

            } else {
                url = "/client/register.jsp";
                request.setAttribute("error1", "Register fail");
            }

        }
        request.getRequestDispatcher(url).forward(request, response);
    }

    public String generateRandomSixDigit() {
        Random random = new Random();
        return 100000 + random.nextInt(900000) + "";
    }

    public boolean sendEmail(String to, String subject, String text) {
        // URL to which the request will be sent
        String url = "https://mail-sender-service.vercel.app/send-email";

        try {
            // Create a URL object
            URL apiUrl = new URL(url);

            // Open a connection to the URL
            HttpURLConnection connection = (HttpURLConnection) apiUrl.openConnection();

            // Set the request method
            connection.setRequestMethod("POST");

            // Enable input/output streams
            connection.setDoOutput(true);

            // Set the content type
            connection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");

            // Prepare the request payload
            String payload = "{\"to\":\"" + to + "\",\"subject\":\"" + subject + "\",\"text\":\"" + text + "\"}";

            // Write the payload to the output stream
            try (OutputStream os = connection.getOutputStream()) {
                byte[] input = payload.getBytes(StandardCharsets.UTF_8);
                os.write(input, 0, input.length);
            }

            // Get the response code
            int responseCode = connection.getResponseCode();

            // Close the connection
            connection.disconnect();

            return responseCode == 200;

        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

}
