

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Manage Users</title>

        <link rel="stylesheet" href="assets/css/manage.css">

    </head>
    <body>

        <div class="user-manage-container">

            <h2 class="user-manage-title">Manage Users</h2>

            <a href="User-Form.jsp" class="user-manage-add-btn">+ Add User</a>
            <a href="admin.jsp" class="admin-back-btn">
                ⬅ Back to Admin
            </a>

            <br/><br/>

            <form action="MainController" method="post" class="user-manage-search">

                <input type="hidden" name="action" value="manage_user"/>

                <input type="text"
                       name="keyword"
                       value="${keyword}"
                       class="user-manage-search-input"
                       placeholder="Search user name"/>

                <button type="submit" class="user-manage-search-btn">Search</button>

            </form>

            <hr/>

            <c:choose>

                <c:when test="${empty USER_LIST}">
                    <p class="user-manage-empty">No user found!</p>
                </c:when>

                <c:otherwise>

                    <table class="user-manage-table">

                        <thead>
                            <tr>
                                <th>ID</th>
                                <th>User Name</th>
                                <th>Email</th>
                                <th>Password</th>
                                <th>Full Name</th>
                                <th>Birthday</th>
                                <th>Gender</th>
                                <th>Create Date</th>
                                <th>Last Login</th>
                                <th>Role</th>
                                <th>Status</th>
                                <th>Update</th>
                                <th>Delete</th>
                            </tr>
                        </thead>

                        <tbody>

                            <c:forEach items="${USER_LIST}" var="u">

                                <tr>

                                    <td>${u.userID}</td>

                                    <td class="user-manage-title-cell">${u.userName}</td>

                                    <td>${u.email}</td>

                                    <td class="user-manage-password">${u.password}</td>

                                    <td>${u.fullName}</td>

                                    <td>${u.birthday}</td>

                                    <td>${u.gender}</td>

                                    <td>${u.createDate}</td>

                                    <td>${u.lastLogin}</td>

                                    <td>
                                        <c:choose>
                                            <c:when test="${u.roleID == 1}">
                                                <span class="user-manage-role-admin">Admin</span>
                                            </c:when>
                                            <c:otherwise>
                                                <span class="user-manage-role-user">User</span>
                                            </c:otherwise>
                                        </c:choose>
                                    </td>

                                    <td>
                                        <c:choose>
                                            <c:when test="${u.status == 1}">
                                                <span class="user-manage-status-active">Active</span>
                                            </c:when>
                                            <c:otherwise>
                                                <span class="user-manage-status-inactive">Blocked</span>
                                            </c:otherwise>
                                        </c:choose>
                                    </td>

                                    <td>
                                        <a href="MainController?action=editUser&userID=${u.userID}"
                                           class="user-manage-edit">
                                            Update
                                        </a>
                                    </td>

                                    <td>

                                        <form action="MainController" method="post"
                                              onsubmit="return confirm('Are you sure to delete this user?');">

                                            <input type="hidden" name="action" value="deleteUser"/>
                                            <input type="hidden" name="userID" value="${u.userID}"/>
                                            <input type="hidden" name="keyword" value="${keyword}"/>

                                            <input type="submit"
                                                   value="Delete"
                                                   class="user-manage-delete"/>

                                        </form>

                                    </td>

                                </tr>

                            </c:forEach>

                        </tbody>

                    </table>

                </c:otherwise>

            </c:choose>

        </div>

    </body>
</html>