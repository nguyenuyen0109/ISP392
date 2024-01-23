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
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

/**
 *
 * @author MINIMONIE
 */
public class ResendOtpController extends HttpServlet {
   
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String email = request.getParameter("email");

        // Tạo OTP mới
        String newOtp = generateRandomSixDigit();

        // Lưu OTP mới vào session
        request.getSession().setAttribute(email + "_otp", newOtp);

        // Gửi OTP mới tới email
        boolean emailSent = sendEmail(email, "Your OTP", "Your new OTP is: " + newOtp);

        // Gửi phản hồi về cho client
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write("{\"emailSent\": " + emailSent + "}");
    }

    private String generateRandomSixDigit() {
        // Sinh mã OTP 6 chữ số
        return String.valueOf((int)(Math.random() * 900000) + 100000);
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
