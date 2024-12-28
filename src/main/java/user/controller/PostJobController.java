package user.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import user.model.JobModel;

import java.io.IOException;

@WebServlet("/PostJobController")
public class PostJobController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String jobTitle = request.getParameter("jobTitle");
        String description = request.getParameter("description");
        String requirement = request.getParameter("requirement");
        String postingDate = request.getParameter("postingDate");
        String deadline = request.getParameter("deadline");
        String companyName = (String) request.getSession().getAttribute("companyName");

        JobModel jobModel = new JobModel();
        boolean isPosted = jobModel.postJob(jobTitle, description, requirement, postingDate, deadline, companyName);

        if (isPosted) {
            response.sendRedirect("companyHomepage.jsp?success=1");
        } else {
            response.sendRedirect("companyHomepage.jsp?error=1");
        }
    }
}
