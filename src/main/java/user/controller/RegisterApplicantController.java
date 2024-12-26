package user.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import user.model.UserModel;

import java.io.IOException;

@WebServlet("/RegisterApplicantController")
public class RegisterApplicantController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public RegisterApplicantController() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmPassword");
        String firstName = request.getParameter("firstName");

        // Validate passwords match
        if (!password.equals(confirmPassword)) {
            request.setAttribute("errorMessage", "Passwords do not match.");
            RequestDispatcher dispatcher = request.getRequestDispatcher("registerApplicant.jsp");
            dispatcher.forward(request, response);
            return;
        }

        try {
            UserModel userModel = new UserModel();
            boolean isRegistered = userModel.registerUser(firstName, username, email, password);

            if (isRegistered) {
                response.sendRedirect("index.jsp");
            } else {
                request.setAttribute("errorMessage", "Registration failed. Please try again.");
                RequestDispatcher dispatcher = request.getRequestDispatcher("registerApplicant.jsp");
                dispatcher.forward(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "An error occurred during registration. Please try again.");
            RequestDispatcher dispatcher = request.getRequestDispatcher("registerApplicant.jsp");
            dispatcher.forward(request, response);
        }
    }
}
