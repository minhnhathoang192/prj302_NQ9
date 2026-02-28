<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <title>Artist Form</title>
    </head>
    <body>

        <h2>${mode == 'edit' ? ' Edit Artist' : 'Add Artist'}</h2>

        <form action="MainController" method="post">

            <!-- action -->
            <input type="hidden" name="action"
                   value="${mode == 'edit' ? 'saveArtist' : 'addArtist'}"/>

            <!-- ID (chỉ edit mới có) -->
            <c:if test="${mode == 'edit'}">
                ID:
                <input type="text" name="artistID" value="${a.artistID}" readonly/> <br/>
            </c:if>

            <!-- Name -->
            Artist Name:
            <input type="text" name="artistName" value="${a.artistName}" required/> <br/>

            <!-- Avatar -->
            Avatar URL:
            <input type="text" name="avatarURL" value="${a.avatarURL}"/> <br/>

            <!-- Description -->
            Description:
            <textarea name="description">${a.description}</textarea> <br/>

            <!-- Debut Date -->
            Debut Date:
            <input type="date" name="debutDate" value="${a.debutDate}" required/> <br/>

            <!-- Status (chỉ edit mới hiện) -->
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