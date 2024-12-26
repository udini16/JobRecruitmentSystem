<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login Page</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #737373;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
        }

        .login-container {
            background-color: white;
            padding: 55px;
            border-radius: 20px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
            border: 2px solid #9c27b0;
            width: 100%;
            max-width: 50%;
            height: 42%; 
        }

        h2 {
            text-align: center;
            margin-bottom: 20px;
            font-size: 35px;
        }

        .input-field {
            width: 100%;
            padding: 15px;
            margin: 8px 0;
            border: 1px solid #200b0b;
            border-radius: 5px;
            box-sizing: border-box;
        }

        .login-button {
            width: 15%;
            background-color: #0d6efd;
            color: black;
            padding: 10px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            font-size: 16px;
            display: block; 
            margin: 0 auto;
        }

        .login-button:hover {
            background-color: #d9d9d9;
            color: black;
        }

        .container {
            text-align: center;
        }
    </style>
</head>
<body>
    <div class="login-container">
        <h2>WELCOME</h2>

        <!-- Display error message if login fails -->
        <%
            String errorMessage = (String) request.getAttribute("errorMessage");
            if (errorMessage != null) {
        %>
            <p style="color: red; text-align: center;"><%= errorMessage %></p>
        <%
            }
        %>

        <form action="LoginController" method="post">
            <input type="text" name="username" placeholder="Username" class="input-field" required>
            <input type="password" name="password" placeholder="Password" class="input-field" required>
            <button type="submit" class="login-button">Login</button>
        </form>
        <div>     
                <p>Do not have any account? <a href="companyRegister.jsp">Register here.</a></p>
        </div>
    </div>
</body>
</html>
