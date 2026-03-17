<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Edit User</title>

        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/manage.css">

    </head>

    <body>
        <a href="MainController?action=manage_user" class="admin-back-btn">
            ⬅ Back to Users
        </a>

        <div class="user-manage-edit-container">

            <h2 class="user-manage-edit-title">👤 Edit User</h2>

            <form action="MainController" method="post" class="user-manage-edit-form">

                <input type="hidden" name="action"
                       value="${mode == 'edit' ? 'saveUser' : 'addUser'}"/>

                <div class="user-manage-edit-group">
                    <label>ID</label>
                    <input type="text" name="userID"
                           value="${u.userID}"
                           ${mode== 'edit' ? 'readonly' : ''}/>
                </div>

                <div class="user-manage-edit-group">
                    <label>Username</label>
                    <input type="text" name="userName" value="${u.userName}"/>
                </div>

                <div class="user-manage-edit-group">
                    <label>Email</label>
                    <input type="text" name="email" value="${u.email}"/>
                </div>

                <div class="user-manage-edit-group">
                    <label>Password</label>
                    <input type="password"
                           name="password"
                           placeholder="Enter new password to change"/>
                </div>

                <div class="user-manage-edit-group">
                    <label>Avatar</label>
                    <input type="text" name="avatar" value="${u.avatar}"/>
                </div>

                <div class="user-manage-edit-group">
                    <label>Full Name</label>
                    <input type="text" name="fullName" value="${u.fullName}"/>
                </div>

                <div class="user-manage-edit-group">
                    <label>Birthday</label>
                    <input type="date" name="birthday" value="${u.birthday}"/>
                </div>

                <div class="user-manage-edit-group">
                    <label>Gender</label>
                    <input type="text" name="gender" value="${u.gender}"/>
                </div>

                <div class="user-manage-edit-group">
                    <label>Status</label>

                    <select name="status">
                        <option value="1" ${u.status == 1 ? 'selected' : ''}>Active</option>
                        <option value="0" ${u.status == 0 ? 'selected' : ''}>Blocked</option>
                    </select>

                </div>

                <div class="user-manage-edit-group">
                    <label>Role</label>

                    <select name="roleID">
                        <option value="1" ${u.roleID == 1 ? 'selected' : ''}>Admin</option>
                        <option value="2" ${u.roleID == 2 ? 'selected' : ''}>User</option>
                    </select>

                </div>

                <div class="user-manage-edit-actions">

                    <c:choose>
                        <c:when test="${mode == 'edit'}">
                            <input type="submit" value="Update"
                                   class="user-manage-edit-btn"/>
                        </c:when>

                        <c:otherwise>
                            <input type="submit" value="Add"
                                   class="user-manage-edit-btn"/>
                        </c:otherwise>
                    </c:choose>

                </div>

            </form>

            <p class="user-manage-edit-msg">${msg}</p>
            <p class="user-manage-edit-error">${error}</p>

        </div>

    </body>
</html>