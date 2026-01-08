<%-- 
    Document   : login
    Created on : Jan 8, 2026, 4:45:05 PM
    Author     : NQ9
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form action="MainController" method="post">
            UserName: <input type="text" name="txtUserName"/><br/>
            Password: <input type="password" name="txtUserPassWord"/><br/>
            <input type="submit" value="login"/><br/>
        </form>
    </body>
</html>
