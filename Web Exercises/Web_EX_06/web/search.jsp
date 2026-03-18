<%-- 
    Document   : search
    Created on : Mar 19, 2026, 12:52:07 AM
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
            <h1>
                WelCome ${user.fullName}
            </h1>
            
            <hr/>
            <form action="MainController" method="post">
                <input type="hidden" name="action" value="search"/>
                Input name: <input type="text" name="keyword" value="${keyword}"/><br/>
                <input type="submit" value="Search"/><br/>
            </form>
                
                <hr/>
                <c:choose>
                    <c:when test="${empty list}">
                        <span style="color: red">No data matching the search</span>
                    </c:when>
                    <c:otherwise>
                        <table border="1">
                            <thead>
                                <tr>
                                    <th>ID</th>
                                    <th>Name</th>
                                    <th>Short Name</th>
                                    <th>Description</th>
                                    <th>founded Year</th>
                                    <th>address</th>
                                    <th>City</th>
                                    <th>Region</th>
                                    <th>Type</th>
                                    <th>Total Students</th>
                                    <th>Total Faculties</th>
                                    <th>Status</th>
                                    <th></th>
                                </tr>
                            </thead>
                            
                            <c:forEach items="${list}" var="u">
                                <tr>
                                    <td>${u.id}</td>
                                    <td>${u.name}</td>
                                    <td>${u.shortName}</td>
                                    <td>${u.description}</td>
                                    <td>${u.foundedYear}</td>
                                    <td>${u.address}</td>
                                    <td>${u.city}</td>
                                    <td>${u.region}</td>
                                    <td>${u.type}</td>
                                    <td>${u.totalStudents}</td>
                                    <td>${u.totalFaculties}</td>
                                    <td>${u.status}</td>
                                    <td>
                                        <form action="MainController" method="post">
                                            <input type="hidden" name="action" value="update"/>
                                            <input type="hidden" name="id" value="${u.id}"/>
                                            <input type="hidden" name="keyword" value="${keyword}"/>
                                            <input type="submit" value="Update"/>
                                        </form>
                                    </td>
                                </tr>
                            </c:forEach>
                        </table>
                    </c:otherwise>
                </c:choose>
        </c:when>
        <c:otherwise>
            <c:redirect url="login.jsp"/>
        </c:otherwise>
    </c:choose>
    </body>
</html>
