<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.sql.*, db.connection.ConnectionManager" %>
<%
    String companyID = (String) session.getAttribute("companyID");
	
	System.out.println("Session companyID: " + companyID);

	
    if (companyID == null) {
        response.sendRedirect("login.jsp");
        return;
    }

    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;

    String query = "SELECT j.jobID, j.jobTitle, j.description, j.requirement, j.postingDate, j.deadline " +
                   "FROM job j " +
                   "JOIN jobopportunities jo ON j.jobID = jo.jobID " +
                   "WHERE jo.companyID = ?";

    boolean hasJobs = false;

    try {
        conn = ConnectionManager.getConnection();
        pstmt = conn.prepareStatement(query);
        pstmt.setString(1, companyID);
        out.println("Executing Query: " + pstmt.toString()); // Debug output
        rs = pstmt.executeQuery();

        if (rs.isBeforeFirst()) { // Check for results
            hasJobs = true;
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Edit a Job</title>
</head>
<body>
    <h1>Edit a Job</h1>
    <table>
        <thead>
            <tr>
                <th>Job Title</th>
                <th>Description</th>
                <th>Requirements</th>
                <th>Posting Date</th>
                <th>Deadline</th>
                <th>Action</th>
            </tr>
        </thead>
        <tbody>
        <%
            while (rs.next()) {
        %>
            <tr>
                <td><%= rs.getString("jobTitle") %></td>
                <td><%= rs.getString("description") %></td>
                <td><%= rs.getString("requirement") %></td>
                <td><%= rs.getDate("postingDate") %></td>
                <td><%= rs.getDate("deadline") %></td>
                <td>
                    <form action="EditJobController" method="get">
                        <input type="hidden" name="jobID" value="<%= rs.getInt("jobID") %>">
                        <button type="submit">Edit</button>
                    </form>
                </td>
            </tr>
        <%
            }
        %>
        </tbody>
    </table>
</body>
</html>
<%
        } else {
            out.println("<p>No jobs found for company ID: " + companyID + "</p>"); // Debug output
        }
    } catch (SQLException e) {
        e.printStackTrace();
        out.println("<p>Error occurred: " + e.getMessage() + "</p>");
    } finally {
        try {
            if (rs != null) rs.close();
            if (pstmt != null) pstmt.close();
            if (conn != null) conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
%>
