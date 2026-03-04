<%-- 
    Document   : welcome
    Created on : Mar 4, 2026, 8:05:48 PM
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
    <c:choose>
        <c:when test="${not empty user}">
            <h1>WELCOME, ${user.fullName}</h1>
            
            <a href="MainContoller?action=logout"> Logout</a><br/>
            <a href="search.jsp">Search</a><br/>
        </c:when>
            
            <c:otherwise>
                <c:redirect url="login.jsp"/>
            </c:otherwise>
    </c:choose>
    </body>
</html>
