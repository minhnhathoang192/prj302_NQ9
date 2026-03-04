<%-- 
    Document   : search
    Created on : Mar 4, 2026, 8:24:24 PM
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

        <jsp:include page="welcome.jsp"/>

        <c:if test="${empty user}">
            <c:redirect url="login.jsp"/>
        </c:if>
        <form action="MainContoller" method="POST">
            <input type="hidden" name="action" value="search">
            Search: <input type="text" name="keyword" value="${keyword}"/>
            <input type="submit" value="search"/><br/>
        </form>

        <hr/>
        <c:choose>
            <c:when test="${empty list}">
                No data matching the search criteria found! 
            </c:when>
            <c:otherwise>
                <table border="1">
                    <thead>
                    <th>id</th>
                    <th>name</th>
                    <th>description</th>
                    <th>founded year</th>
                    <th>address</th>
                    <th>city</th>
                    <th>region</th>
                    <th>type</th>
                    <th>total Students</th>
                    <th>total Faculties</th>
                    <th></th>
                    
                </thead>
                <c:forEach items="${list}" var="u">
                    <tr>
                        <td>${u.id}</td>
                        <td>${u.name}</td>
                        <td>${u.description}</td>
                        <td>${u.foundedYear}</td>
                        <td>${u.address}</td>
                        <td>${u.city}</td>
                        <td>${u.region}</td>
                        <td>${u.type}</td>
                        <td>${u.totalStudents}</td>
                        <td>${u.totalFaculties}</td>

                        <td>
                            <form action="MainContoller" method="POST"
                                  onsubmit="return confirm('Ban co chac chan muon xoa truong dai hoc nay k?')">
                                <input type="hidden" name="action" value="deleteUniversity">
                                <input type="hidden" name="id" value="${u.id}"/><br/>
                                <input type="hidden" name="keyword" value="${keyword}"/><br/>
                                <input type="submit" value="Delete"/><br/>
                            </form>
                        </td>
                    </tr>
                </c:forEach>

            </table>
        </c:otherwise>
    </c:choose>
</body>
</html>
