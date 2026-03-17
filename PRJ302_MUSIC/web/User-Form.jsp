<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Register</title>

        <link rel="stylesheet"
              href="${pageContext.request.contextPath}/assets/css/manage.css">

    </head>

    <body>
        <a href="MainController?action=manage_user" class="admin-back-btn">
            ⬅ Back to Users
        </a>

        <div class="user-manage-form-container">

            <h2 class="user-manage-form-title">Create Account</h2>

            <form action="MainController" method="post"
                  class="user-manage-form">

                <input type="hidden" name="action" value="registerUser"/>

                <div class="user-manage-form-group">
                    <label>Username</label>
                    <input type="text" name="userName" required/>
                </div>

                <div class="user-manage-form-group">
                    <label>Email</label>
                    <input type="email" name="email" required/>
                </div>

                <div class="user-manage-form-group">
                    <label>Password</label>
                    <input type="password" name="password" required/>
                </div>

                <div class="user-manage-form-group">
                    <label>Full Name</label>
                    <input type="text" name="fullName"/>
                </div>

                <div class="user-manage-form-group">
                    <label>Birthday</label>
                    <input type="date" name="birthday" required/>
                </div>

                <div class="user-manage-form-group">
                    <label>Gender</label>

                    <select name="gender">
                        <option value="male">Male</option>
                        <option value="female">Female</option>
                        <option value="other">Other</option>
                    </select>

                </div>

                <div class="user-manage-form-action">
                    <input type="submit"
                           value="Register"
                           class="user-manage-form-btn"/>
                </div>

            </form>

            <p class="user-manage-form-msg">${msg}</p>
            <p class="user-manage-form-error">${error}</p>

        </div>

    </body>
</html>