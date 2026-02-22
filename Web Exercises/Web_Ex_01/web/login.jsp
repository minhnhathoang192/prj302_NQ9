<%-- 
    Document   : login
    Created on : Feb 22, 2026, 5:00:49 PM
    Author     : NQ9
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Dang Nhap</h1>
        <form action="MainController" method="POST">
            <input type="hidden" name="action" value="login"/><br/>
            UserName: <input type="text" name="txtUserID" required="required"/><br/>
            password: <input type="password" name="txtPassword" required="required"/><br/>
            <input type="submit" value="login"/><br/>
        </form>
        
        <c:if test="${not empty error}">
            <span style="color: red">${error}</span>
    </c:if>
    </body>
</html>
