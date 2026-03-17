<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
    <head>
        <title>Manage Playlists</title>
        <link rel="stylesheet" href="assets/css/manage.css">
    </head>

    <body>

        <div class="manage-playlist">

            <h2>Manage Playlists</h2>

            <!-- ADD PLAYLIST -->
            <a href="playList-form.jsp" class="manage-playlist-add-btn">
                ➕ Add Playlist
            </a>
            <a href="admin.jsp" class="admin-back-btn">
                ⬅ Back to Admin
            </a>

            <!-- SEARCH -->
            <form action="MainController" method="get" class="manage-playlist-search">

                <input type="hidden" name="action" value="manage_playlist"/>

                <input type="text"
                       name="keyword"
                       value="${keyword}"
                       placeholder="Search by playlist name or ID"/>

                <button type="submit">Search</button>

            </form>

            <p class="manage-playlist-success">${sessionScope.msg}</p>
            <p class="manage-playlist-error">${sessionScope.error}</p>

            <c:remove var="msg" scope="session"/>
            <c:remove var="error" scope="session"/>

            <hr/>

            <c:choose>

                <c:when test="${empty playlists}">
                    <p>No playlist found!</p>
                </c:when>

                <c:otherwise>

                    <table class="manage-playlist-table">

                        <tr>
                            <th>ID</th>
                            <th>Name</th>
                            <th>UserID</th>
                            <th>Public</th>
                            <th>Create Date</th>
                            <th>Songs</th>
                            <th>Add Song</th>
                            <th>View</th>
                            <th>Delete</th>
                        </tr>

                        <c:forEach var="p" items="${playlists}">

                            <tr>

                                <!-- ID -->
                                <td>${p.playListID}</td>

                                <!-- NAME -->
                                <td>${p.playListName}</td>

                                <!-- USER -->
                                <td>${p.userID}</td>

                                <!-- PUBLIC -->
                                <td>

                                    <c:choose>
                                        <c:when test="${p.isPublic}">
                                            <span class="manage-playlist-public">
                                                Public
                                            </span>
                                        </c:when>

                                        <c:otherwise>
                                            <span class="manage-playlist-private">
                                                Private
                                            </span>
                                        </c:otherwise>
                                    </c:choose>

                                </td>

                                <!-- DATE -->
                                <td>${p.createDate}</td>

                                <!-- SONG COUNT -->
                                <td>${songCountByPlaylist[p.playListID]}</td>

                                <!-- ADD SONG -->
                                <td>

                                    <form action="MainController"
                                          method="post"
                                          class="manage-playlist-inline-form">

                                        <input type="hidden"
                                               name="action"
                                               value="addSongToPlaylist"/>

                                        <input type="hidden"
                                               name="playlistID"
                                               value="${p.playListID}"/>

                                        <select name="songID">

                                            <c:forEach var="s" items="${allSongs}">
                                                <option value="${s.songID}">
                                                    ${s.title}
                                                </option>
                                            </c:forEach>

                                        </select>

                                        <button type="submit">Add</button>

                                    </form>

                                </td>

                                <!-- VIEW -->
                                <td>

                                    <a class="manage-playlist-view-btn"
                                       href="MainController?action=viewPlaylist&playListID=${p.playListID}">
                                        View
                                    </a>

                                </td>

                                <!-- DELETE -->
                                <td>

                                    <form action="MainController"
                                          method="post"
                                          onsubmit="return confirm('Delete this playlist?');">

                                        <input type="hidden"
                                               name="action"
                                               value="deletePlaylist"/>

                                        <input type="hidden"
                                               name="id"
                                               value="${p.userID}"/>

                                        <input type="hidden"
                                               name="playListID"
                                               value="${p.playListID}"/>

                                        <input type="submit"
                                               class="manage-playlist-delete-btn"
                                               value="Delete"/>

                                    </form>

                                </td>

                            </tr>

                        </c:forEach>

                    </table>

                </c:otherwise>

            </c:choose>

        </div>

    </body>
</html>