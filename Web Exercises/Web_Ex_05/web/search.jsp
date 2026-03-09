<%-- 
    Document   : search
    Created on : Mar 5, 2026, 1:09:21 PM
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

        <form action="MainController" method="post">
            <input type="hidden" name="action" value="search"/>
            Input Name: <input type="text" name="keyword" value="${keyword}"/><br/>
            <input type="submit" value="search"/><br/>
        </form>

        <hr/>

        <c:choose>
            <c:when test="${empty list}">
                No Matching the Search
            </c:when>
            <c:otherwise>
                <table border="1">
                    <thead>
                    <th>id</th>
                    <th>name</th>
                    <th>short Name</th>
                    <th>description</th>
                    <th>founded year</th>
                    <th>address</th>
                    <th>city</th>
                    <th>region</th>
                    <th>type</th>
                    <th>Students</th>
                    <th>Faculties</th>
                    <th></th>
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
                        <td>${u.type}</td>
                        <td>${u.totalStudents}</td>
                        <td>${u.totalFaculties}</td>

                        <td>
                            <form action="MainController" method="post"
                                  onsubmit="return confirm('Ban co muon xoa?')">
                                <input type="hidden" name="action" value="deleteUni"/>
                                <input type="hidden" name="id" value="${u.id}"/><br/>
                                <input type="hidden" name="keyword" value="${keyword}"/><br/>
                                <input type="submit" value="delete"/><br/>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </c:otherwise>
    </c:choose>
</body>
</html>
