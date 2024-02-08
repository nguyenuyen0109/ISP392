package controller;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Handles user logout by invalidating the current session and redirecting to the login page.
 */
@WebServlet(name = "LogoutController", urlPatterns = {"/logout"})
public class LogoutController extends HttpServlet {

    /**
     * Invalidates the user session and redirects to the login page.
     * This method supports both GET and POST requests to allow flexible logout triggers.
     *
     * @param request  The servlet request
     * @param response The servlet response
     * @throws ServletException If a servlet-specific error occurs
     * @throws IOException      If an I/O error is encountered
     */
    protected void processLogout(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Attempt to retrieve an existing session without creating a new one
        HttpSession session = request.getSession(false);

        // Check if a session exists and invalidate it to clear any stored attributes
        if (session != null) {
            session.invalidate();
        }

        // Redirect the user to the login page after successful logout
        response.sendRedirect("login.jsp"); // Redirect URL can be adjusted as per application routing
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processLogout(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processLogout(request, response);
    }

    @Override
    public String getServletInfo() {
        return "LogoutController facilitates user logout by session invalidation and redirection to login.";
    }
}
