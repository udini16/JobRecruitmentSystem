<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.sql.*" %>
<%
    String companyName = (String) session.getAttribute("companyName");

    // Redirect to login page if the user is not logged in
    if (companyName == null) {
        response.sendRedirect("login.jsp");
        return;
    }

    // Database connection setup
    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;

    String query = "SELECT jobID, jobTitle, description, requirement, postingDate, deadline " +
                   "FROM job_postings WHERE companyName = ?";

    try {
        conn = db.connection.ConnectionManager.getConnection(); // Adjust with your ConnectionManager setup
        pstmt = conn.prepareStatement(query);
        pstmt.setString(1, companyName);
        rs = pstmt.executeQuery();
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Edit a Job</title>
    <style>
        body, html {
            margin: 0;
            padding: 0;
            font-family: Arial, sans-serif;
            background-color: #f4f4f9;
        }
        table {
            width: 90%;
            margin: 20px auto;
            border-collapse: collapse;
            text-align: left;
        }
        th, td {
            padding: 10px;
            border: 1px solid #ccc;
        }
        th {
            background-color: #0d6efd;
            color: white;
        }
        .edit-button {
            background-color: #0d6efd;
            color: white;
            border: none;
            padding: 10px 15px;
            text-decoration: none;
            border-radius: 5px;
            cursor: pointer;
        }
        .edit-button:hover {
            background-color: #004bbf;
        }
    </style>
</head>
<body>
    <h1 style="text-align: center;">Edit a Job</h1>
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
                        <button type="submit" class="edit-button">Edit</button>
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
    } catch (SQLException e) {
        e.printStackTrace();
    } finally {
        if (rs != null) rs.close();
        if (pstmt != null) pstmt.close();
        if (conn != null) conn.close();
    }
%>
