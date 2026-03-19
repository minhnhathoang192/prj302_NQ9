<%-- 
    Document   : search
    Created on : Mar 19, 2026, 3:33:44 PM
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
            
            <hr/><!-- comment -->
            <form action="MainController" method="post">
                <input type="hidden" name="action" value="search"/>
                Input: <input type="text" name="keyword" value="${keyword}"/><br/>
                <input type="submit" value="Search"/>
            </form>
                
                <hr/>
                
                <c:choose>
                    <c:when test="${empty list}">
                        <span style="color: red">No students found matching the search criteria !</span>
                    </c:when>
                    <c:otherwise>
                        <table border="1">
                            <thead>
                                <tr>
                                    <th>student ID</th>
                                    <th>full Name</th>
                                    <th>gender</th>
                                    <th>date Of Birth</th>
                                    <th>email</th>
                                    <th>phone</th>
                                    <th>address</th>
                                    <th>city</th>
                                    <th>major</th>
                                    <th>faculty</th>
                                    <th>enroll Year</th>
                                    <th>gpa</th>
                                    <th>scholarship</th>
                                    <th>status</th>
                                    <th></th>
                                </tr>
                            </thead>
                            
                            <c:forEach items="${list}" var="s">
                                <tr>
                                    <td>${s.studentID}</td>
                                    <td>${s.fullName}</td>
                                    <td>${s.gender}</td>
                                    <td>${s.dateOfBirth}</td>
                                    <td>${s.email}</td>
                                    <td>${s.phone}</td>
                                    <td>${s.address}</td>
                                    <td>${s.city}</td>
                                    <td>${s.major}</td>
                                    <td>${s.faculty}</td>
                                    <td>${s.enrollYear}</td>
                                    <td>${s.gpa}</td>
                                    <td>${s.scholarship}</td>
                                    <td>${s.status}</td>
                                    <td>
                                        <form action="MainController" method="post">
                                            <input type="hidden" name="action" value="delete"/>
                                            <input type="hidden" name="studentID" value="${s.studentID}"/>
                                            <input type="hidden" name="keyword" value="${keyword}"/>
                                            <button type="submit" onclick="return confirm('Are you sure you want to delete this student?')" >Delete</button>
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
