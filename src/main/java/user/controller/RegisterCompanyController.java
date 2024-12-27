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
        // Retrieve form data
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

        // **Verify and log input data**
        System.out.println("=== Verifying Input Data ===");
        System.out.println("Username: " + username);
        System.out.println("Email: " + email);
        System.out.println("Password: " + password);
        System.out.println("Confirm Password: " + confirmPassword);
        System.out.println("Company Name: " + companyName);
        System.out.println("Phone Number: " + phoneNumber);
        System.out.println("Address: " + address);
        System.out.println("Industry Type: " + industryType);
        System.out.println("Registration Date: " + registrationDate);
        System.out.println("Website: " + website);

        // Check if any critical fields are null or empty
        if (username == null || username.isEmpty() || 
            email == null || email.isEmpty() || 
            password == null || password.isEmpty() || 
            confirmPassword == null || confirmPassword.isEmpty() || 
            companyName == null || companyName.isEmpty()) {
            
            System.out.println("Error: Missing required fields.");
            request.setAttribute("errorMessage", "Please fill in all required fields.");
            RequestDispatcher dispatcher = request.getRequestDispatcher("companyRegister.jsp");
            dispatcher.forward(request, response);
            return;
        }

        // Validate passwords match
        if (!password.equals(confirmPassword)) {
            System.out.println("Error: Passwords do not match.");
            request.setAttribute("errorMessage", "Passwords do not match.");
            RequestDispatcher dispatcher = request.getRequestDispatcher("companyRegister.jsp");
            dispatcher.forward(request, response);
            return;
        }

        try {
            CompanyModel companyModel = new CompanyModel();
            boolean isRegistered = companyModel.registerCompany(companyName, username, password, email, phoneNumber, address, industryType, registrationDate, website);

            if (isRegistered) {
                System.out.println("Success: Company registered successfully.");
                response.sendRedirect("index.jsp");
            } else {
                System.out.println("Error: Company registration failed.");
                request.setAttribute("errorMessage", "Registration failed. Please try again.");
                RequestDispatcher dispatcher = request.getRequestDispatcher("companyRegister.jsp");
                dispatcher.forward(request, response);
            }
        } catch (Exception e) {
            System.out.println("Error: Exception occurred during registration.");
            e.printStackTrace();
            request.setAttribute("errorMessage", "An error occurred during registration. Please try again.");
            RequestDispatcher dispatcher = request.getRequestDispatcher("companyRegister.jsp");
            dispatcher.forward(request, response);
        }
    }

}
