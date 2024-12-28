package user.controller;

import db.connection.ConnectionManager; 
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/EditJobController")
public class EditJobController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public EditJobController() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String jobID = request.getParameter("jobID");

        // Check if jobID is provided
        if (jobID == null || jobID.isEmpty()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Job ID is missing");
            return;
        }

        // Database connection and query
        try (Connection conn = ConnectionManager.getConnection()) {
            String query = "SELECT * FROM job_postings WHERE jobID = ?";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, jobID);

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                // Set job details as request attributes
                request.setAttribute("jobID", rs.getInt("jobID"));
                request.setAttribute("jobTitle", rs.getString("jobTitle"));
                request.setAttribute("description", rs.getString("description"));
                request.setAttribute("requirement", rs.getString("requirement"));
                request.setAttribute("postingDate", rs.getDate("postingDate").toString());
                request.setAttribute("deadline", rs.getDate("deadline").toString());

                // Forward to editJobDetails.jsp
                request.getRequestDispatcher("editJobDetails.jsp").forward(request, response);
            } else {
                // Job not found
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Job not found");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error fetching job details");
        }
    }
}
