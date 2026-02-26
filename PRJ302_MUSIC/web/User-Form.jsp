<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
    <title>Register</title>
</head>
<body>

<h2>Register Account</h2>

<form action="MainController" method="post">

    <input type="hidden" name="action" value="registerUser"/>

    <!-- Username -->
    Username:
    <input type="text" name="userName" required/> <br/>

    <!-- Email -->
    Email:
    <input type="email" name="email" required/> <br/>

    <!-- Password -->
    Password:
    <input type="password" name="password" required/> <br/>

    <!-- Full Name -->
    Full Name:
    <input type="text" name="fullName"/> <br/>
    
    Birthday:
    <input type="date" name="birthday" required/> <br/>

    <!-- Gender -->
    Gender:
    <select name="gender">
        <option value="male">Male</option>
        <option value="female">Female</option>
        <option value="other">Other</option>
    </select> <br/><br/>

    <input type="submit" value="Register"/>

</form>

<p style="color: green">${msg}</p>
<p style="color: red">${error}</p>

</body>
</html>