<%-- 
    Document   : welcome
    Created on : Feb 3, 2026, 11:38:21 PM
    Author     : NQ9
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="model.userDTO"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
            <c:if test="${not empty user}">
                <h1>
                    WELCOME, ${user.fullName}
                </h1>

                <a href="MainController?action=logout">Logout</a><br/>
                <a href="searchController">Search</a>
            </c:if>

                <c:if test="${empty user}">
                    <c:redirect url="login.jsp"/>
                </c:if>
    </body>
</html>
