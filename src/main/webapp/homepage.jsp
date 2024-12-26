<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    // Get session attributes directly from the implicit session object
    String username = (String) session.getAttribute("username");
    String firstName = (String) session.getAttribute("firstName");

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
    <title>Homepage</title>
    <style>
        /* Add your CSS styles here */
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
        footer {
            position: fixed;
            bottom: 0;
            width: 100%;
            background-color: #0d6efd;
            color: white;
            text-align: center;
            padding: 10px 0;
        }
        @media (max-width: 768px) {
            .navbar {
                flex-direction: column;
                text-align: center;
            }
            .navbar a {
                margin: 5px 0;
            }
        }
    </style>
</head>
<body>
    <div class="navbar">
        <div class="nav-links">
            <a href="homepage.jsp" class="<%= request.getRequestURI().contains("homepage.jsp") ? "active" : "" %>">Home</a>
            <a href="profile.jsp" class="<%= request.getRequestURI().contains("profile.jsp") ? "active" : "" %>">Profile</a>
            <a href="settings.jsp" class="<%= request.getRequestURI().contains("settings.jsp") ? "active" : "" %>">Settings</a>
        </div>
        <div class="user-info">
            Welcome, <%= firstName != null ? firstName : username %>!
        </div>
        <div class="logout">
            <a href="LogoutController">Logout</a>
        </div>
    </div>
    <div class="content">
        <h1>Welcome to Your Homepage</h1>
        <p>Here, you can manage your account, view your profile, and explore more features.</p>
    </div>
    <footer>
        <p>&copy; 2024 Your Company Name. All rights reserved.</p>
    </footer>
</body>
</html>
