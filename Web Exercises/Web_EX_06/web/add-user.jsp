<%-- 
    Document   : add-user
    Created on : Mar 19, 2026, 1:26:34 AM
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
            <c:when test="${not empty admin}">
                <h1>
                    WelCome ${admin.fullName}
                </h1>

                <hr/>

                <form action="MainController" method="post">
                    <input type="hidden" name="action" value="add"/>
                    ID: <input type="text" name="id" value="${u.id}" required/><br/>
                    Name: <input type="text" name="name" value="${u.name}" required/><br/>
                    short Name: <input type="text" name="shortName" value="${u.shortName}" required/><br/>
                    description: <textarea name="description">${u.description}</textarea>
                    founded Year: <input type="number" name="foundedYear" value="${u.foundedYear}" required/><br/>
                    address: <input type="text" name="address" value="${u.address}" required/><br/>
                    city: <input type="text" name="city" value="${u.city}" required/><br/>
                    region: <input type="text" name="region" value="${u.region}" required/><br/>
                    type: <input type="text" name="type" value="${u.type}" required/><br/>
                    Total Students: <input type="number" step="1" min="0" name="totalStudents" value="${u.totalStudents}" required/><br/>
                    Total Faculties: <input type="number" step="1" min="0" name="totalFaculties" value="${u.totalFaculties}" required/><br/>
                    Status: 
                    <select name="status" required>
                        <option value="1" ${u.status ? 'selected' : ''}>true</option>
                        <option value="0" ${!u.status ? 'selected' : ''}>False</option>
                    </select>

                    <button type="submit">Add</button>
                </form>

                <c:if test="${not empty error}">
                    <span style="color: red">${error}</span>
                </c:if>
                <c:if test="${not empty msg}">
                    <span style="color: green">${msg}</span>
                </c:if>
            </c:when>
            <c:otherwise>
                <c:redirect url="e403.jsp"/>
            </c:otherwise>
        </c:choose>
    </body>
</html>
