<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <title>Playlist Form</title>
        <link rel="stylesheet" href="assets/css/style.css"/>
    </head>

    <body>

        <div class="upload-page">

            <form action="MainController"
                  method="post"
                  class="upload-form">

                <input type="hidden"
                       name="action"
                       value="${mode == 'edit' ? 'savePlaylist' : 'addPlaylist'}"/>
                <input type="hidden" name="source" value="admin"/>

                <div class="upload-card">

                    <!-- LEFT -->
                    <div class="upload-left">

                        <div class="cover-preview">
                            <span>Playlist</span>
                        </div>

                    </div>

                    <!-- RIGHT -->
                    <div class="upload-right">

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

                            <div class="form-group">

                                <label>ID</label>

                                <input type="text"
                                       value="${p.playListID}"
                                       readonly>

                                <input type="hidden"
                                       name="playListID"
                                       value="${p.playListID}">

                            </div>

                        </c:if>

                        <!-- PLAYLIST NAME -->
                        <div class="form-group">

                            <label>Playlist Name</label>

                            <input type="text"
                                   name="playListName"
                                   value="${p.playListName}"
                                   required>

                        </div>

                        <!-- PUBLIC / PRIVATE -->
                        <div class="form-group">

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

                        <button type="submit" class="upload-btn">

                            <c:choose>

                                <c:when test="${mode == 'edit'}">
                                    💾 Update Playlist
                                </c:when>

                                <c:otherwise>
                                    ➕ Add Playlist
                                </c:otherwise>

                            </c:choose>

                        </button>

                        <p class="success-msg">${msg}</p>
                        <p class="error-msg">${error}</p>

                    </div>

                </div>

            </form>

            <!-- SONG LIST (ONLY EDIT MODE) -->

            <c:if test="${mode == 'edit'}">

                <hr>

                <h3>Songs In Playlist</h3>

                <c:choose>

                    <c:when test="${empty songs}">
                        <p>No songs in this playlist</p>
                    </c:when>

                    <c:otherwise>

                        <table border="1" cellpadding="8">

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
                                            <source src="${s.audioURL}" type="audio/mpeg">
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

                                            <button type="submit">Remove</button>

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