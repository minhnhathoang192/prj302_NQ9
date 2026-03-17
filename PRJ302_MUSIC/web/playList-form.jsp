<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <title>Playlist Form</title>
        <link rel="stylesheet" href="assets/css/manage.css"/>
    </head>

    <body>
        <a href="MainController?action=manage_playlist" class="admin-back-btn">
            ⬅ Back to Playlist
        </a>

        <div class="manage-playlist-form">

            <form action="MainController"
                  method="post"
                  class="manage-playlist-form-container">

                <input type="hidden"
                       name="action"
                       value="${mode == 'edit' ? 'savePlaylist' : 'addPlaylist'}"/>

                <input type="hidden"
                       name="source"
                       value="admin"/>

                <div class="manage-playlist-form-card">

                    <!-- LEFT -->
                    <div class="manage-playlist-form-left">

                        <div class="manage-playlist-form-cover-preview">
                            <span>Playlist</span>
                        </div>

                    </div>

                    <!-- RIGHT -->
                    <div class="manage-playlist-form-right">

                        <h2>

                            <c:choose>

                                <c:when test="${mode == 'edit'}">
                                    📃 Edit Playlist
                                </c:when>

                                <c:otherwise>
                                    📃 Add Playlist
                                </c:otherwise>

                            </c:choose>

                        </h2>

                        <!-- ID -->
                        <c:if test="${mode == 'edit'}">

                            <div class="manage-playlist-form-group">

                                <label>ID</label>

                                <input type="text"
                                       value="${p.playListID}"
                                       readonly>

                                <input type="hidden"
                                       name="playListID"
                                       value="${p.playListID}">

                            </div>

                        </c:if>

                        <!-- NAME -->
                        <div class="manage-playlist-form-group">

                            <label>Playlist Name</label>

                            <input type="text"
                                   name="playListName"
                                   value="${p.playListName}"
                                   required>

                        </div>

                        <!-- VISIBILITY -->
                        <div class="manage-playlist-form-group">

                            <label>Visibility</label>

                            <select name="isPublic">

                                <option value="1"
                                        ${p.isPublic ? 'selected' : ''}>
                                    Public
                                </option>

                                <option value="0"
                                        ${!p.isPublic ? 'selected' : ''}>
                                    Private
                                </option>

                            </select>

                        </div>

                        <button type="submit"
                                class="manage-playlist-form-btn">

                            <c:choose>

                                <c:when test="${mode == 'edit'}">
                                    💾 Update Playlist
                                </c:when>

                                <c:otherwise>
                                    ➕ Add Playlist
                                </c:otherwise>

                            </c:choose>

                        </button>

                        <p class="manage-playlist-form-success">${msg}</p>
                        <p class="manage-playlist-form-error">${error}</p>

                    </div>

                </div>

            </form>

            <!-- SONG LIST -->

            <c:if test="${mode == 'edit'}">

                <hr>

                <h3>Songs In Playlist</h3>

                <c:choose>

                    <c:when test="${empty songs}">
                        <p>No songs in this playlist</p>
                    </c:when>

                    <c:otherwise>

                        <table class="manage-playlist-form-table">

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
                                                   value="removeSongFromPlaylist">

                                            <input type="hidden"
                                                   name="playListID"
                                                   value="${p.playListID}">

                                            <input type="hidden"
                                                   name="songID"
                                                   value="${s.songID}">

                                            <button type="submit"
                                                    class="manage-playlist-form-remove-btn">
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

        </div>

    </body>
</html>