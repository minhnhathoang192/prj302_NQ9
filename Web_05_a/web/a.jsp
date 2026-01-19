<%-- 
    Document   : a
    Created on : Jan 20, 2026, 12:16:18 AM
    Author     : NQ9
--%>

<%@page import="model.userDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            userDTO user = (userDTO) session.getAttribute("user");

            if (user != null) {
        %>
        Welcome<%=user.getFullName()%>
        <a href="MainController?action=logout">Logout</a><br/>
        Bang dieu khien <br/>
        Tinh nang 1 <br/>
        Tinh nang 2 <br/>
        Tinh nang 3 <br/>
        <%
            } else {
                response.sendRedirect("login.jsp");
            }
        %>
    </body>
</html>
