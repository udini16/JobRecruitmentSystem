<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String username = (String) session.getAttribute("username");
    String companyName = (String) session.getAttribute("companyName");

    // Redirect to login page if the user is not logged in
    if (username == null) {
        response.sendRedirect("login.jsp");
        return;
    }
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Company Homepage</title>
    <style>
        body, html {
            margin: 0;
            padding: 0;
            font-family: Arial, sans-serif;
            background-color: #f4f4f9;
        }
        .navbar {
            background-color: #0d6efd;
            color: white;
            padding: 10px 20px;
            display: flex;
            justify-content: space-between;
            align-items: center;
            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.2);
        }
        .navbar a {
            color: white;
            text-decoration: none;
            margin: 0 15px;
            font-size: 16px;
        }
        .navbar a.active {
            font-weight: bold;
            text-decoration: underline;
        }
        .navbar .user-info {
            font-weight: bold;
        }
        .content {
            text-align: center;
            margin: 50px auto;
        }
        h1 {
            font-size: 2.5rem;
            color: #333;
        }
        p {
            font-size: 1.2rem;
            color: #555;
        }
        .action-button {
            display: inline-block;
            margin: 20px;
            padding: 15px 30px;
            background-color: #0d6efd;
            color: white;
            text-decoration: none;
            font-size: 1.1rem;
            font-weight: bold;
            border-radius: 5px;
            transition: background-color 0.3s;
        }
        .action-button:hover {
            background-color: #004bbf;
        }
        footer {
            position: fixed;
            bottom: 0;
            width: 100%;
            background-color: #0d6efd;
            color: white;
            text-align: center;
            padding: 10px 0;
        }
    </style>
</head>
<body>
    <div class="navbar">
        <div class="nav-links">
            <a href="companyHomepage.jsp" class="active">Home</a>
            <a href="profile.jsp">Profile</a>
            <a href="settings.jsp">Settings</a>
        </div>
        <div class="user-info">
            Welcome, <%= companyName %>!
        </div>
        <div class="logout">
            <a href="LogoutController">Logout</a>
        </div>
    </div>
    <div class="content">
        <h1>Welcome to Your Company Homepage</h1>
        <p>Here you can manage your company's activities, post job listings, and more.</p>
        
        <!-- Buttons for posting and editing jobs -->
        <a href="postJob.jsp" class="action-button">Post a Job</a>
        <a href="editJob.jsp" class="action-button">Edit a Job</a>
    </div>

    <footer>
        <p>&copy; 2024 Your Company Name. All rights reserved.</p>
    </footer>
</body>
</html>
