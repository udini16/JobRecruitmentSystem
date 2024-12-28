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
    <title>Post a Job</title>
    <style>
        body, html {
            margin: 0;
            padding: 0;
            font-family: Arial, sans-serif;
            background-color: #f4f4f9;
        }
        .content {
            text-align: center;
            margin: 50px auto;
        }
        h1 {
            font-size: 2.5rem;
            color: #333;
        }
        form {
            max-width: 600px;
            margin: auto;
            text-align: left;
        }
        label {
            font-weight: bold;
            display: block;
            margin-top: 15px;
        }
        input, textarea, button {
            width: 100%;
            padding: 10px;
            margin: 10px 0;
            border: 1px solid #ccc;
            border-radius: 5px;
        }
        button {
            background-color: #0d6efd;
            color: white;
            border: none;
            cursor: pointer;
        }
        button:hover {
            background-color: #004bbf;
        }
    </style>
</head>
<body>
    <div class="content">
        <h1>Post a Job</h1>
        <form action="PostJobController" method="post">
            <label for="jobTitle">Job Title:</label>
            <input type="text" id="jobTitle" name="jobTitle" required>
            
            <label for="description">Job Description:</label>
            <textarea id="description" name="description" rows="4" required></textarea>
            
            <label for="requirement">Requirements:</label>
            <textarea id="requirement" name="requirement" rows="3" required></textarea>
            
            <label for="postingDate">Posting Date:</label>
            <input type="date" id="postingDate" name="postingDate" required>
            
            <label for="deadline">Application Deadline:</label>
            <input type="date" id="deadline" name="deadline" required>
            
            <button type="submit">Post Job</button>
        </form>
    </div>
</body>
</html>
