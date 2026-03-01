<%-- 
    Document   : Topic-From
    Created on : Mar 1, 2026, 9:54:08 PM
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
        <h1>${mode== 'edit' ? 'Update Topic' : 'Add Topic'}</h1>

        <form action="MainController" method="POST" enctype="multipart/form-data">

            <input type="hidden" name="action" 
                   value="${mode== 'edit' ? 'saveTopic' : 'addTopic'}"/>

            <c:if test="${mode == 'edit'}">
                ID:
                <input type="text" name="topicID" value="${t.topicID}" readonly/> <br/>
            </c:if>

            Name:
            <input type="text" name="topicName" value="${t.topicName}" required/> <br/>

            Description:
            <textarea name="description">${t.description}</textarea> <br/>

            <!-- ===== COVER IMAGE ===== -->
            Cover:

            <div id="coverPreview" style="width:150px;height:150px;border:1px solid #ccc;display:flex;align-items:center;justify-content:center;margin-bottom:10px;">
                <c:choose>
                    <c:when test="${not empty t.coverImage}">
                        <img src="${pageContext.request.contextPath}/StreamServlet?type=topic&file=${t.coverImage}" 
                             style="width:100%;height:100%;object-fit:cover;">
                    </c:when>
                    <c:otherwise>
                        <span>Chọn ảnh</span>
                    </c:otherwise>
                </c:choose>
            </div>

            <input type="file" name="coverImage" id="coverInput" accept="image/*">

            <br/><br/>

            <c:if test="${mode == 'edit'}">
                Status:
                <select name="isActive">
                    <option value="1" ${t.isActive ? 'selected' : ''}>Active</option>
                    <option value="0" ${!t.isActive ? 'selected' : ''}>Hidden</option>
                </select>
                <br/>
            </c:if>

            <input type="submit" value="${mode == 'edit' ? 'Update' : 'Add'}">
        </form>

        <p style="color: red">${error}</p>
        <p style="color: #1ed760">${msg}</p>
    </body>
</html>

<script>
document.getElementById("coverInput").addEventListener("change", function () {
    const file = this.files[0];
    if (file) {
        const url = URL.createObjectURL(file);
        document.getElementById("coverPreview").innerHTML =
            `<img src="${url}" style="width:100%;height:100%;object-fit:cover;">`;
    }
});
</script>
