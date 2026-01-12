<%-- 
    Document   : a
    Created on : Jan 8, 2026, 5:08:56 PM
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
            userDTO user= (userDTO)request.getAttribute("user");
            %>
            Welcome <%=user.getFullName()%><br/>
    </body>
</html>
