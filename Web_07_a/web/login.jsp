<%-- 
    Document   : login
    Created on : Jan 20, 2026, 12:09:39 AM
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
            Username: <input type="text" name="txtUserName"/><br/>
            Password: <input type="password" name="txtPassword"/><br/>
            <input type="submit" value="login"/>
        </form>
        <c:if test="not empty message">
            <span style="color: red">${message}</span>
        </c:if>

    </body>
</html>
