<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Applicant Registration</title>
    <style>
        /* Add your styles here */
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f9;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
        }

        .register-container {
            background-color: #e0e0e0;
            padding: 25px;
            border-radius: 16px;
            width: 35%;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
            border: 2px solid #9c27b0;
        }

        .form-group {
            margin-bottom: 15px;
        }

        input[type="text"], input[type="email"], input[type="password"] {
            width: 100%;
            padding: 10px;
            border: none;
            border-radius: 5px;
            font-size: 15px;
        }

        .register-button {
            width: 100%;
            background-color: #0d6efd;
            color: white;
            padding: 10px 20px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            font-size: 16px;
        }
    </style>
</head>
<body>
    <div class="register-container">
        <h1>Applicant Registration</h1>
        <form action="RegisterApplicantController" method="post">
            <input type="hidden" name="userType" value="applicant">
            <div class="form-group">
                <input type="text" name="firstName" placeholder="First Name" required>
            </div>
            <div class="form-group">
                <input type="text" name="username" placeholder="Username" required>
            </div>
            <div class="form-group">
                <input type="email" name="email" placeholder="Email" required>
            </div>
            <div class="form-group">
                <input type="password" name="password" placeholder="Password" required>
            </div>
            <div class="form-group">
                <input type="password" name="confirmPassword" placeholder="Confirm Password" required>
            </div>
            <button type="submit" class="register-button">Register</button>
        </form>
    </div>
</body>
</html>
