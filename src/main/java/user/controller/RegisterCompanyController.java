package user.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import user.model.CompanyModel;

import java.io.IOException;

@WebServlet("/RegisterCompanyController")
public class RegisterCompanyController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public RegisterCompanyController() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmPassword");
        String companyName = request.getParameter("companyName");
        String phoneNumber = request.getParameter("phoneNumber");
        String address = request.getParameter("address");
        String industryType = request.getParameter("industryType");
        String registrationDate = request.getParameter("registrationDate");
        String website = request.getParameter("website");

        // Validate passwords match
        if (!password.equals(confirmPassword)) {
            request.setAttribute("errorMessage", "Passwords do not match.");
            RequestDispatcher dispatcher = request.getRequestDispatcher("registerCompany.jsp");
            dispatcher.forward(request, response);
            return;
        }

        try {
            CompanyModel companyModel = new CompanyModel();
            boolean isRegistered = companyModel.registerCompany(companyName, username, password, email, phoneNumber, address, industryType, registrationDate, website);

            if (isRegistered) {
                response.sendRedirect("index.jsp");
            } else {
                request.setAttribute("errorMessage", "Registration failed. Please try again.");
                RequestDispatcher dispatcher = request.getRequestDispatcher("registerCompany.jsp");
                dispatcher.forward(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "An error occurred during registration. Please try again.");
            RequestDispatcher dispatcher = request.getRequestDispatcher("registerCompany.jsp");
            dispatcher.forward(request, response);
        }
    }
}
