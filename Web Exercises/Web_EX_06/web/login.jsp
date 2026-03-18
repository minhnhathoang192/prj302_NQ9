<%-- 
    Document   : login
    Created on : Mar 19, 2026, 12:31:06 AM
    Author     : NQ9
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form action="MainController" method="post">
            <input type="hidden" name="action" value="login"/>
            User Name: <input type="text" name="userID" required/><br/>
            Password: <input type="password" name="password" required/><br/>
            <input type="submit" value="Login"/><br/>
        </form>

        <c:if test = "${not empty mess}">
            <span style="color: red">${mess}</span>
        </c:if>
    </body>
</html>
