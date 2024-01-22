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
import java.io.OutputStream;

import java.net.HttpURLConnection;

import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;

import java.util.Random;

/**
 *
 * @author ADMIN
 */
public class ForgotpasswordController extends HttpServlet {

    private static final String CHAR_LOWER = "abcdefghijklmnopqrstuvwxyz";
    private static final String CHAR_UPPER = CHAR_LOWER.toUpperCase();
    private static final String NUMBER = "0123456789";

    private static final String PASSWORD_ALLOW_BASE = CHAR_LOWER + CHAR_UPPER + NUMBER;

    private static final Random random = new SecureRandom();

    private static final String EMAIL_HOST = "smtp.example.com";
    private static final String EMAIL_PORT = "587";
    private static final String EMAIL_USERNAME = "minhnguyenhoang021@gmail.com";
    private static final String EMAIL_PASSWORD = "Minh03ty%$";

    AccountDAO acc = new AccountDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("client/forgotpassword.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        // get username va email
        String username = request.getParameter("username");
        String emailAddress = request.getParameter("emailAddress");

        //kiem tra username va email trong database
        if (isValidUser(username, emailAddress)) {
            // tao mat khau moi
            String newPassword = generateNewPassword();

            // day mat khau moi len database
            acc.updatePassword(username, newPassword);

            // lưu mật khẩu mới vào session
            request.setAttribute("newPassword", newPassword);

            // gui mat khau moi den email nhap            
            sendEmail(emailAddress, "Restore Password", "Your new password: " + newPassword);

            // thong bao thanh cong
            request.setAttribute("message", "A new password has been sent to your email.");
            request.getRequestDispatcher("client/login.jsp").forward(request, response);

        } else {
            // thong bao that bai
            request.setAttribute("error", "Fail to restore password.");
            request.getRequestDispatcher("client/forgotpassword.jsp").forward(request, response);
        }
    }

    private boolean isValidUser(String username, String emailAddress) {
        return acc.checkUsernameAndEmailExists(username, emailAddress);

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

    public static String generateNewPassword() {
        // tao mat khau moi
        int passwordLength = 12;

        StringBuilder password = new StringBuilder(passwordLength);

        // At least one lowercase character
        password.append(CHAR_LOWER.charAt(random.nextInt(CHAR_LOWER.length())));

        // At least one uppercase character
        password.append(CHAR_UPPER.charAt(random.nextInt(CHAR_UPPER.length())));

        // At least one digit
        password.append(NUMBER.charAt(random.nextInt(NUMBER.length())));

        // Fill in the rest of the password with random characters from PASSWORD_ALLOW_BASE
        for (int i = 4; i < passwordLength; i++) {
            password.append(PASSWORD_ALLOW_BASE.charAt(random.nextInt(PASSWORD_ALLOW_BASE.length())));
        }

        return password.toString();
    }

}
