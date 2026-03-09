<%-- 
    Document   : login
    Created on : Mar 5, 2026, 12:42:32 PM
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
            User Name: <input type="text" name="userName" required="required"/><br/>
            Password: <input type="password" name="password" required="required"/><br/>
            <input type="submit" value="Login"/><br/>
        </form>

        <c:if test="${not empty message}">
            <span style="color: red">${message}</span>
        </c:if>
    </body>
</html>
