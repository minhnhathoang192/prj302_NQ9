<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <title>Album Form</title>
        <link rel="stylesheet" href="assets/css/manage.css"/>
    </head>

    <body class="manage-album-form">

        <a href="MainController?action=manage_album"  class="admin-back-btn">
            ⬅ Back to Album
        </a>

        <div class="manage-album-form-container">

            <form action="MainController"
                  method="post"
                  enctype="multipart/form-data"
                  class="manage-album-form-card">

                <input type="hidden"
                       name="action"
                       value="${mode == 'edit' ? 'saveAlbum' : 'addAlbum'}"/>

                <div class="manage-album-form-layout">

                    <!-- LEFT -->

                    <div class="manage-album-form-left">

                        <div class="manage-album-form-cover" id="coverPreview">

                            <c:choose>

                                <c:when test="${not empty a.coverImage}">
                                    <img src="${pageContext.request.contextPath}/StreamServlet?type=album&file=${a.coverImage}">
                                </c:when>

                                <c:otherwise>
                                    <span>Choose album cover</span>
                                </c:otherwise>

                            </c:choose>

                        </div>

                        <input type="file"
                               name="coverImage"
                               id="coverInput"
                               accept="image/*"
                               hidden>

                        <button type="button"
                                class="manage-album-form-cover-btn"
                                onclick="document.getElementById('coverInput').click()">
                            Choose Image
                        </button>

                    </div>

                    <!-- RIGHT -->

                    <div class="manage-album-form-right">

                        <h2>

                            <c:choose>

                                <c:when test="${mode == 'edit'}">
                                    💿 Edit Album
                                </c:when>

                                <c:otherwise>
                                    💿 Add Album
                                </c:otherwise>

                            </c:choose>

                        </h2>

                        <c:if test="${mode == 'edit'}">

                            <div class="manage-album-form-group">

                                <label>ID</label>

                                <input type="text"
                                       value="${a.albumID}"
                                       readonly>

                                <input type="hidden"
                                       name="albumID"
                                       value="${a.albumID}">

                            </div>

                        </c:if>

                        <div class="manage-album-form-group">

                            <label>Album Name</label>

                            <input type="text"
                                   name="albumName"
                                   value="${a.albumName}"
                                   required>

                        </div>

                        <div class="manage-album-form-group">

                            <label>Release Date</label>

                            <input type="date"
                                   name="releaseDate"
                                   value="${a.releaseDate}"
                                   required>

                        </div>

                        <c:if test="${mode == 'edit'}">

                            <div class="manage-album-form-group">

                                <label>Status</label>

                                <select name="isActive">

                                    <option value="1" ${a.isActive ? 'selected' : ''}>
                                        Active
                                    </option>

                                    <option value="0" ${!a.isActive ? 'selected' : ''}>
                                        Hidden
                                    </option>

                                </select>

                            </div>

                        </c:if>

                        <button type="submit"
                                class="manage-album-form-btn">

                            <c:choose>

                                <c:when test="${mode == 'edit'}">
                                    💾 Update Album
                                </c:when>

                                <c:otherwise>
                                    ➕ Add Album
                                </c:otherwise>

                            </c:choose>

                        </button>

                        <p class="manage-album-form-success">${msg}</p>
                        <p class="manage-album-form-error">${error}</p>

                    </div>

                </div>

            </form>

        </div>

        <script>

            document.getElementById("coverInput").addEventListener("change", function (e) {

                const file = e.target.files[0];

                if (!file)
                    return;

                const reader = new FileReader();

                reader.onload = function (event) {

                    document.getElementById("coverPreview").innerHTML =
                            "<img src='" + event.target.result + "'>";

                };

                reader.readAsDataURL(file);

            });

        </script>

    </body>
</html>