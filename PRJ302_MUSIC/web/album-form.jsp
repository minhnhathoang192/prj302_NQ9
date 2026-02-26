<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
    <title>Album Form</title>
</head>
<body>

<h2>${mode == 'edit' ? 'Edit Album' : ' Add Album'}</h2>

<form action="MainController" method="post">

    <!-- action -->
    <input type="hidden" name="action"
           value="${mode == 'edit' ? 'saveAlbum' : 'addAlbum'}"/>

    <!-- ID -->
    <c:if test="${mode == 'edit'}">
        ID:
        <input type="text" name="albumID" value="${a.albumID}" readonly/> <br/>
    </c:if>

    <!-- Album Name -->
    Album Name:
    <input type="text" name="albumName" value="${a.albumName}" required/> <br/>

    <!-- Release Date -->
    Release Date:
    <input type="date" name="releaseDate" value="${a.releaseDate}" required/> <br/>

    <!-- Cover Image -->
    Cover Image (URL):
    <input type="text" name="coverImage" value="${a.coverImage}"/> <br/>

    <!-- Status (chỉ admin edit mới thấy) -->
    <c:if test="${mode == 'edit'}">
        Status:
        <select name="isActive">
            <option value="1" ${a.isActive ? 'selected' : ''}>Active</option>
            <option value="0" ${!a.isActive ? 'selected' : ''}>Hidden</option>
        </select>
        <br/>
    </c:if>

    <br/>

    <!-- Submit -->
    <input type="submit"
           value="${mode == 'edit' ? 'Update' : 'Add'}"/>

</form>

<!-- Message -->
<p style="color: green">${msg}</p>
<p style="color: red">${error}</p>

</body>
</html>