package user.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import user.model.UserModel;
import user.model.CompanyModel;

import java.io.IOException;

@WebServlet("/LoginController")
public class LoginController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public LoginController() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.getWriter().append("Served at: ").append(request.getContextPath());
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String userType = request.getParameter("userType"); // 'applicant' or 'company'

        HttpSession session = request.getSession();

        if ("applicant".equals(userType)) {
            // Validate applicant login
            UserModel userModel = new UserModel();
            boolean isValidApplicant = userModel.validateUser(username, password);

            if (isValidApplicant) {
                session.setAttribute("username", username);
                session.setAttribute("firstName", userModel.getFirstName(username));
                session.setAttribute("userType", "applicant");
                response.sendRedirect("applicantHomepage.jsp");
            } else {
                request.setAttribute("errorMessage", "Invalid username or password for applicant.");
                RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
                dispatcher.forward(request, response);
            }
        } else if ("company".equals(userType)) {
            // Validate company login
            CompanyModel companyModel = new CompanyModel();
            boolean isValidCompany = companyModel.validateCompanyUser(username, password);

            if (isValidCompany) {
                session.setAttribute("username", username);
                session.setAttribute("companyName", companyModel.getCompanyName(username));
                session.setAttribute("userType", "company");
                response.sendRedirect("companyHomepage.jsp");
            } else {
                request.setAttribute("errorMessage", "Invalid username or password for company.");
                RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
                dispatcher.forward(request, response);
            }
        } else {
            // Handle invalid user type
            request.setAttribute("errorMessage", "Invalid user type selected.");
            RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
            dispatcher.forward(request, response);
        }
    }
}
