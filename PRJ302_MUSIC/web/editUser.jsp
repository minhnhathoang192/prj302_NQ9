<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
    <title>Edit User</title>
</head>
<body>

<form action="MainController" method="post">

    <input type="hidden" name="action" value="${mode == 'edit' ? 'saveUser' : 'addUser'}"/>

    ID:
    <input type="text" name="userID" value="${u.userID}" ${mode== 'edit' ? 'readonly' : ''}/> <br/>

    Username:
    <input type="text" name="userName" value="${u.userName}"/> <br/>

    Email:
    <input type="text" name="email" value="${u.email}"/> <br/>

    Password:
    <input type="text" name="password" value="${u.password}"/> <br/>

    Avatar:
    <input type="text" name="avatar" value="${u.avatar}"/> <br/>

    Full Name:
    <input type="text" name="fullName" value="${u.fullName}"/> <br/>

    Birthday:
    <input type="date" name="birthday" value="${u.birthday}"/> <br/>

    Gender:
    <input type="text" name="gender" value="${u.gender}"/> <br/>

    Status:
    <select name="status">
        <option value="1" ${u.status == 1 ? 'selected' : ''}>Active</option>
        <option value="0" ${u.status == 0 ? 'selected' : ''}>Blocked</option>
    </select> <br/>

    Role:
    <select name="roleID">
        <option value="1" ${u.roleID == 1 ? 'selected' : ''}>Admin</option>
        <option value="2" ${u.roleID == 2 ? 'selected' : ''}>User</option>
    </select> <br/><br/>

    <c:choose>
        <c:when test="${mode == 'edit'}">
            <input type="submit" value="Update"/>
        </c:when>
        <c:otherwise>
            <input type="submit" value="add"/>
        </c:otherwise>
    </c:choose>

</form>

<p style="color: green">${msg}</p>
<p style="color: red">${error}</p>

</body>
</html>