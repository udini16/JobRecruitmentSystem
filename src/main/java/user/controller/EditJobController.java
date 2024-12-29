package user.controller;

import db.connection.ConnectionManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/EditJobController")
public class EditJobController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private static final String JOB_DETAILS_QUERY = "SELECT * FROM job WHERE jobID = ?";
    private static final String EDIT_JOB_DETAILS_PAGE = "editJobDetails.jsp";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String jobID = request.getParameter("jobID");

        // Validate jobID
        if (jobID == null || jobID.isEmpty()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Job ID is missing");
            return;
        }

        try {
            Integer.parseInt(jobID); // Validate numeric format
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid job ID format");
            return;
        }

        // Fetch job details
        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(JOB_DETAILS_QUERY)) {

            pstmt.setString(1, jobID);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    // Set job details as request attributes
                    request.setAttribute("jobID", jobID); // Pass as String
                    request.setAttribute("jobTitle", rs.getString("jobTitle"));
                    request.setAttribute("description", rs.getString("description"));
                    request.setAttribute("requirement", rs.getString("requirement"));
                    request.setAttribute("postingDate", rs.getDate("postingDate").toString());
                    request.setAttribute("deadline", rs.getDate("deadline").toString());

                    // Forward to editJobDetails.jsp
                    request.getRequestDispatcher(EDIT_JOB_DETAILS_PAGE).forward(request, response);
                } else {
                    response.sendError(HttpServletResponse.SC_NOT_FOUND, "Job not found");
                }
            }
        } catch (SQLException e) {
            // Log the exception and send an error response
            e.printStackTrace(); // Replace with a logging framework in production
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Database error occurred while fetching job details");
        }
    }

}
