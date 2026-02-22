<%-- 
    Document   : welcome
    Created on : Feb 22, 2026, 5:25:39 PM
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
        <c:if test="${not empty user}">
            <h1>
                Welcome, ${user.fullName}
            </h1>
            
            <a href="MainController?action=logout">
                Logout
            </a>
            <a href="MainController?action=search">
                search
            </a>
        </c:if>
        
        <c:if test="${empty user}">
            <c:redirect url="login.jsp"/>
        </c:if>
    </body>
</html>
