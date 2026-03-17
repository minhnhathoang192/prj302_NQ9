<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <title>Artist Form</title>
        <link rel="stylesheet" href="assets/css/manage.css"/>
    </head>
    <body>
        <a href="MainController?action=manage_artist" class="admin-back-btn">
            ⬅ Back to Artist
        </a>

        <div class="manage-artist-form">

            <form action="MainController"
                  method="post"
                  class="manage-artist-form-container"
                  enctype="multipart/form-data">

                <!-- ACTION -->
                <input type="hidden"
                       name="action"
                       value="${mode == 'edit' ? 'saveArtist' : 'addArtist'}"/>

                <div class="manage-artist-form-card">

                    <!-- LEFT: Avatar preview -->
                    <div class="manage-artist-form-left">

                        <div class="manage-artist-form-avatar" id="avatarPreview">

                            <c:choose>

                                <c:when test="${not empty a.avatarURL}">
                                    <img src="${a.avatarURL}" />
                                </c:when>

                                <c:otherwise>
                                    <span>Chọn ảnh nghệ sĩ</span>
                                </c:otherwise>

                            </c:choose>

                        </div>

                        <input type="file"
                               name="avatarURL"
                               id="avatarInput"
                               accept="image/*"
                               hidden>

                        <button type="button"
                                class="manage-artist-form-avatar-btn"
                                onclick="document.getElementById('avatarInput').click()">
                            Chọn ảnh
                        </button>

                    </div>

                    <!-- RIGHT -->
                    <div class="manage-artist-form-right">

                        <h2>

                            <c:choose>

                                <c:when test="${mode == 'edit'}">
                                    🎤 Edit Artist
                                </c:when>

                                <c:otherwise>
                                    🎤 Add Artist
                                </c:otherwise>

                            </c:choose>

                        </h2>

                        <!-- ID -->
                        <c:if test="${mode == 'edit'}">

                            <div class="manage-artist-form-group">

                                <label>ID</label>

                                <input type="text"
                                       value="${a.artistID}"
                                       readonly>

                                <input type="hidden"
                                       name="artistID"
                                       value="${a.artistID}">

                            </div>

                        </c:if>

                        <!-- NAME -->
                        <div class="manage-artist-form-group">

                            <label>Artist Name</label>

                            <input type="text"
                                   name="artistName"
                                   value="${a.artistName}"
                                   required>

                        </div>

                        <!-- DEBUT -->
                        <div class="manage-artist-form-group">

                            <label>Debut Date</label>

                            <input type="date"
                                   name="debutDate"
                                   value="${a.debutDate}"
                                   required>

                        </div>

                        <!-- DESCRIPTION -->
                        <div class="manage-artist-form-group">

                            <label>Description</label>

                            <textarea name="description">${a.description}</textarea>

                        </div>

                        <!-- STATUS -->
                        <c:if test="${mode == 'edit'}">

                            <div class="manage-artist-form-group">

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

                        <button type="submit"
                                class="manage-artist-form-btn">

                            <c:choose>

                                <c:when test="${mode == 'edit'}">
                                    💾 Update Artist
                                </c:when>

                                <c:otherwise>
                                    ➕ Add Artist
                                </c:otherwise>

                            </c:choose>

                        </button>

                        <p class="manage-artist-form-success">${msg}</p>
                        <p class="manage-artist-form-error">${error}</p>

                    </div>

                </div>

            </form>

        </div>

        <script>
            document.getElementById("avatarInput").addEventListener("change", function (e) {
                const file = e.target.files[0];
                if (!file)
                    return;

                const reader = new FileReader();
                reader.onload = function (event) {
                    document.getElementById("avatarPreview").innerHTML =
                            "<img src='" + event.target.result + "'>";
                };
                reader.readAsDataURL(file);
            });
        </script>

    </body>
</html>