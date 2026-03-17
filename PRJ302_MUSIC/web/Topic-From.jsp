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
        <title>Topic Form</title>
        <link rel="stylesheet" href="assets/css/manage.css"/>
    </head>

    <body class="manage-topic-form">
        <a href="MainController?action=manage_topic" class="admin-back-btn">
            ⬅ Back to Topic
        </a>

        <div class="manage-topic-form-container">

            <h1>${mode== 'edit' ? 'Update Topic' : 'Add Topic'}</h1>

            <form action="MainController"
                  method="POST"
                  enctype="multipart/form-data"
                  class="manage-topic-form-card">

                <input type="hidden"
                       name="action"
                       value="${mode== 'edit' ? 'saveTopic' : 'addTopic'}"/>

                <c:if test="${mode == 'edit'}">

                    <div class="manage-topic-form-group">

                        <label>ID</label>

                        <input type="text"
                               name="topicID"
                               value="${t.topicID}"
                               readonly/>

                    </div>

                </c:if>

                <div class="manage-topic-form-group">

                    <label>Name</label>

                    <input type="text"
                           name="topicName"
                           value="${t.topicName}"
                           required/>

                </div>

                <div class="manage-topic-form-group">

                    <label>Description</label>

                    <textarea name="description">${t.description}</textarea>

                </div>

                <!-- COVER -->

                <div class="manage-topic-form-group">

                    <label>Cover</label>

                    <div id="coverPreview" class="manage-topic-form-cover">

                        <c:choose>

                            <c:when test="${not empty t.coverImage}">
                                <img src="${pageContext.request.contextPath}/StreamServlet?type=topic&file=${t.coverImage}">
                            </c:when>

                            <c:otherwise>
                                <span>Choose image</span>
                            </c:otherwise>

                        </c:choose>

                    </div>

                    <input type="file"
                           name="coverImage"
                           id="coverInput"
                           accept="image/*">

                </div>

                <c:if test="${mode == 'edit'}">

                    <div class="manage-topic-form-group">

                        <label>Status</label>

                        <select name="isActive">

                            <option value="1" ${t.isActive ? 'selected' : ''}>
                                Active
                            </option>

                            <option value="0" ${!t.isActive ? 'selected' : ''}>
                                Hidden
                            </option>

                        </select>

                    </div>

                </c:if>

                <button type="submit" class="manage-topic-form-btn">
                    ${mode == 'edit' ? 'Update' : 'Add'}
                </button>

            </form>

            <!-- SONG LIST -->

            <c:if test="${mode == 'edit'}">

                <h2>Songs In Topic</h2>

                <c:choose>

                    <c:when test="${empty songs}">
                        <p>No songs in this topic</p>
                    </c:when>

                    <c:otherwise>

                        <table class="manage-topic-form-table">

                            <tr>
                                <th>ID</th>
                                <th>Title</th>
                                <th>Duration</th>
                                <th>Audio</th>
                                <th>Remove</th>
                            </tr>

                            <c:forEach var="s" items="${songs}">

                                <tr>

                                    <td>${s.songID}</td>

                                    <td>${s.title}</td>

                                    <td>${s.duration}s</td>

                                    <td>

                                        <audio controls width="200">
                                            <source src="${pageContext.request.contextPath}/StreamServlet?type=audio&file=${s.audioURL}" type="audio/mpeg">
                                        </audio>

                                    </td>

                                    <td>

                                        <form action="MainController" method="post">

                                            <input type="hidden"
                                                   name="action"
                                                   value="removeSongFromTopic">

                                            <input type="hidden"
                                                   name="topicID"
                                                   value="${t.topicID}">

                                            <input type="hidden"
                                                   name="songID"
                                                   value="${s.songID}">

                                            <button class="manage-topic-form-remove-btn">
                                                Remove
                                            </button>

                                        </form>

                                    </td>

                                </tr>

                            </c:forEach>

                        </table>

                    </c:otherwise>

                </c:choose>

            </c:if>

            <p class="manage-topic-form-error">${error}</p>
            <p class="manage-topic-form-success">${msg}</p>

        </div>

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
