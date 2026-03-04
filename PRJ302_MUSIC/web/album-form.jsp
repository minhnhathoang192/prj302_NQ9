<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <title>Album Form</title>
        <link rel="stylesheet" href="assets/css/style.css"/>
    </head>
    <body>

        <div class="upload-page">

            <form action="MainController"
                  method="post"
                  enctype="multipart/form-data"
                  class="upload-form">

                <!-- ACTION -->
                <input type="hidden"
                       name="action"
                       value="${mode == 'edit' ? 'saveAlbum' : 'addAlbum'}"/>

                <div class="upload-card">

                    <!-- LEFT : COVER PREVIEW -->
                    <div class="upload-left">

                        <div class="cover-preview" id="coverPreview">

                            <c:choose>
                                <c:when test="${not empty a.coverImage}">
                                    <img src="${pageContext.request.contextPath}/StreamServlet?type=cover&file=${a.coverImage}">
                                </c:when>

                                <c:otherwise>
                                    <span>Chọn ảnh album</span>
                                </c:otherwise>
                            </c:choose>

                        </div>

                        <input type="file"
                               name="coverImage"
                               id="coverInput"
                               accept="image/*"
                               hidden>

                        <button type="button"
                                class="cover-btn"
                                onclick="document.getElementById('coverInput').click()">
                            Chọn ảnh
                        </button>

                    </div>

                    <!-- RIGHT : INFO -->
                    <div class="upload-right">

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

                        <!-- ID chỉ hiện khi edit -->
                        <c:if test="${mode == 'edit'}">
                            <div class="form-group">
                                <label>ID</label>

                                <input type="text"
                                       value="${a.albumID}"
                                       readonly>

                                <input type="hidden"
                                       name="albumID"
                                       value="${a.albumID}">
                            </div>
                        </c:if>

                        <!-- ALBUM NAME -->
                        <div class="form-group">
                            <label>Album Name</label>

                            <input type="text"
                                   name="albumName"
                                   value="${a.albumName}"
                                   required>
                        </div>

                        <!-- RELEASE DATE -->
                        <div class="form-group">
                            <label>Release Date</label>

                            <input type="date"
                                   name="releaseDate"
                                   value="${a.releaseDate}"
                                   required>
                        </div>

                        <!-- STATUS -->
                        <c:if test="${mode == 'edit'}">
                            <div class="form-group">
                                <label>Status</label>

                                <select name="isActive">

                                    <option value="1"
                                            ${a.isActive ? 'selected' : ''}>
                                        Active
                                    </option>

                                    <option value="0"
                                            ${!a.isActive ? 'selected' : ''}>
                                        Hidden
                                    </option>

                                </select>
                            </div>
                        </c:if>

                        <!-- SUBMIT -->
                        <button type="submit" class="upload-btn">

                            <c:choose>
                                <c:when test="${mode == 'edit'}">
                                    💾 Update Album
                                </c:when>

                                <c:otherwise>
                                    ➕ Add Album
                                </c:otherwise>
                            </c:choose>

                        </button>

                        <p class="success-msg">${msg}</p>
                        <p class="error-msg">${error}</p>

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