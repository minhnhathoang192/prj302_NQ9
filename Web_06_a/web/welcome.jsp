<%-- 
    Document   : welcome
    Created on : Jan 22, 2026, 4:56:02 PM
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
            userDTO u= (userDTO)session.getAttribute("user");
            if(u!=null){
        %>
        <h1>WELCOME, <%=u.getFullName()%></h1>
        <a href="MainController?action=logout&">Logout</a><br/>
        <a href="search.jsp">Search</a>
        <%
            } else{
                response.sendRedirect("login.jsp");
}
        %>
    </body>
</html>
