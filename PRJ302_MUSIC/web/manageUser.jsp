<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<h2>Manage Users</h2>

<a href="User-Form.jsp">Add User</a>
<br/><br/>

<form action="MainController" method="post">
    <input type="hidden" name="action" value="manage_user"/>
    Input Name:
    <input type="text" name="keyword" value="${keyword}"/>
    <input type="submit" value="Search"/>
</form>

<hr/>

<c:choose>
    <c:when test="${empty USER_LIST}">
        No user found!
    </c:when>

    <c:otherwise>
        <table border="1">
            <thead>
            <th>ID</th>
            <th>User Name</th>
            <th>Email</th>
            <th>password</th>
            <th>full Name</th>
            <th>birthday</th>
            <th>gender</th>
            <th>create Date</th>
            <th>last Login</th>
            <th>Role</th>
            <th>Status</th>
            <th>Update</th>
            <th>Delete</th>
        </thead>

        <c:forEach items="${USER_LIST}" var="u">
            <tr>
                <td>${u.userID}</td>
                <td>${u.userName}</td>
                <td>${u.email}</td>
                <td>${u.password}</td>
                <td>${u.fullName}</td>
                <td>${u.birthday}</td>
                <td>${u.gender}</td>
                <td>${u.createDate}</td>
                <td>${u.lastLogin}</td>

                <td>
                    <c:choose>
                        <c:when test="${u.roleID == 1}">
                            Admin
                        </c:when>
                        <c:otherwise>
                            User
                        </c:otherwise>
                    </c:choose>
                </td>

                <td>
                    <c:choose>
                        <c:when test="${u.status == 1}">
                            Active
                        </c:when>
                        <c:otherwise>
                            Blocked
                        </c:otherwise>
                    </c:choose>
                </td>

                <!-- UPDATE -->
                <td>
                    <a href="MainController?action=editUser&userID=${u.userID}">
                        Update
                    </a>
                </td>

                <!-- DELETE -->
                <td>
                    <form action="MainController" method="post"
                          onsubmit="return confirm('Are you sure to delete this user?');">

                        <input type="hidden" name="action" value="deleteUser"/>
                        <input type="hidden" name="userID" value="${u.userID}"/>
                        <input type="hidden" name="keyword" value="${keyword}"/>

                        <input type="submit" value="Delete"/>
                    </form>
                </td>

            </tr>
        </c:forEach>
    </table>
</c:otherwise>
</c:choose>