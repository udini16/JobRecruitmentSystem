<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String jobID = (String) request.getAttribute("jobID");
    String jobTitle = (String) request.getAttribute("jobTitle");
    String description = (String) request.getAttribute("description");
    String requirement = (String) request.getAttribute("requirement");
    String postingDate = (String) request.getAttribute("postingDate");
    String deadline = (String) request.getAttribute("deadline");
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Edit Job</title>
    <style>
        body, html {
            margin: 0;
            padding: 0;
            font-family: Arial, sans-serif;
            background-color: #f4f4f9;
        }
        .container {
            max-width: 600px;
            margin: 50px auto;
            padding: 20px;
            background: white;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        }
        h1 {
            text-align: center;
            font-size: 1.8rem;
            color: #333;
        }
        form {
            display: flex;
            flex-direction: column;
        }
        label {
            margin-top: 15px;
            font-weight: bold;
            color: #555;
        }
        input, textarea, button {
            margin-top: 5px;
            padding: 10px;
            font-size: 1rem;
            border: 1px solid #ddd;
            border-radius: 5px;
        }
        button {
            background-color: #0d6efd;
            color: white;
            border: none;
            cursor: pointer;
            margin-top: 20px;
        }
        button:hover {
            background-color: #004bbf;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Edit Job Details</h1>
        <form action="UpdateJobController" method="post">
            <input type="hidden" name="jobID" value="<%= jobID %>">

            <label for="jobTitle">Job Title:</label>
            <input type="text" id="jobTitle" name="jobTitle" value="<%= jobTitle %>" required>

            <label for="description">Job Description:</label>
            <textarea id="description" name="description" rows="4" required><%= description %></textarea>

            <label for="requirement">Requirements:</label>
            <textarea id="requirement" name="requirement" rows="3" required><%= requirement %></textarea>

            <label for="postingDate">Posting Date:</label>
            <input type="date" id="postingDate" name="postingDate" value="<%= postingDate %>" required>

            <label for="deadline">Application Deadline:</label>
            <input type="date" id="deadline" name="deadline" value="<%= deadline %>" required>

            <button type="submit">Update Job</button>
        </form>
    </div>
</body>
</html>
