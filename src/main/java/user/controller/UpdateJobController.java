package user.controller;

import db.connection.ConnectionManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/UpdateJobController")
public class UpdateJobController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String jobID = request.getParameter("jobID");
        String jobTitle = request.getParameter("jobTitle");
        String description = request.getParameter("description");
        String requirement = request.getParameter("requirement");
        String postingDate = request.getParameter("postingDate");
        String deadline = request.getParameter("deadline");

        // Validate input fields
        if (jobID == null || jobID.isEmpty() ||
            jobTitle == null || jobTitle.isEmpty() ||
            description == null || description.isEmpty() ||
            requirement == null || requirement.isEmpty() ||
            postingDate == null || postingDate.isEmpty() ||
            deadline == null || deadline.isEmpty()) {

            response.sendRedirect("editJobDetails.jsp?error=Invalid input data");
            return;
        }

        // Database update query
        String query = "UPDATE job_postings SET jobTitle = ?, description = ?, requirement = ?, " +
                       "postingDate = ?, deadline = ? WHERE jobID = ?";

        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, jobTitle);
            pstmt.setString(2, description);
            pstmt.setString(3, requirement);
            pstmt.setString(4, postingDate);
            pstmt.setString(5, deadline);
            pstmt.setString(6, jobID);

            int rowsUpdated = pstmt.executeUpdate();
            if (rowsUpdated > 0) {
                response.sendRedirect("companyHomepage.jsp?success=Job updated successfully");
            } else {
                response.sendRedirect("editJobDetails.jsp?error=Job not found or update failed");
            }

        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("editJobDetails.jsp?error=Database error occurred");
        }
    }
}
