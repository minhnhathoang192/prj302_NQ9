<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<h2>Manage Playlists</h2>

<!-- ADD PLAYLIST -->
<a href="playList-form.jsp">➕ Add Playlist</a>

<!-- SEARCH -->
<form action="MainController" method="get">
    <input type="hidden" name="action" value="manage_playlist"/>

    <input type="text"
           name="keyword"
           value="${keyword}"
           placeholder="Search by playlist name or ID"/>

    <button type="submit">Search</button>
</form>

<p style="color: green">${sessionScope.msg}</p>
<p style="color: red">${sessionScope.error}</p>

<c:remove var="msg" scope="session"/>
<c:remove var="error" scope="session"/>

<hr/>

<c:choose>

    <c:when test="${empty playlists}">
        <p>No playlist found!</p>
    </c:when>

    <c:otherwise>

        <table border="1" cellpadding="8">

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
                        Public
                    </c:when>
                    <c:otherwise>
                        Private
                    </c:otherwise>
                </c:choose>
                </td>

                <!-- DATE -->
                <td>${p.createDate}</td>

                <!-- SONG LIST -->
                <td>${songCountByPlaylist[p.playListID]}</td>

                <!-- ADD SONG -->
                <td>

                    <form action="MainController" method="post">

                        <input type="hidden" name="action" value="addSongToPlaylist"/>
                        <input type="hidden" name="playlistID" value="${p.playListID}"/>

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

                    <a href="MainController?action=viewPlaylist&playListID=${p.playListID}">
                        View
                    </a>

                </td>

                <!-- DELETE -->
                <td>

                    <form action="MainController"
                          method="post"
                          onsubmit="return confirm('Delete this playlist?');">

                        <input type="hidden" name="action" value="deletePlaylist"/>
                        <input type="hidden" name="id" value="${p.userID}"/>
                        <input type="hidden" name="playListID" value="${p.playListID}"/>

                        <input type="submit" value="Delete"/>

                    </form>

                </td>

                </tr>

            </c:forEach>

        </table>

    </c:otherwise>

</c:choose>