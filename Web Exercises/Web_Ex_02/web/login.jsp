<%-- 
    Document   : login
    Created on : Mar 4, 2026, 7:41:32 PM
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
        <form action="MainContoller" method="POST">
            <input type="hidden" name="action" value="login">
            UserName: <input type="text" name="userID" required="required"/><br/>
            Password <input type="password" name="password" required="required"/><br/>
            <input type="submit" value="login"/><br/>
        </form>
        <c:if test="${not empty message}">
            <span style="color: red">${message}</span>
        </c:if>
    </body>
</html>
