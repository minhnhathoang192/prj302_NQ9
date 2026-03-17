<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Manage Song</title>

        <link rel="stylesheet" href="assets/css/manage.css">

    </head>
    <body>
        <div class="manage-song">

            <h2>Manage Songs</h2>

            <a href="upload-content.jsp" class="manage-song-add-btn">Add Song</a>
            <a href="admin.jsp" class="admin-back-btn">
                ⬅ Back to Admin
            </a>

            <form action="MainController" method="get" class="manage-song-search">
                <input type="hidden" name="action" value="manage_song"/>

                <input type="text" name="keyword"
                       value="${keyword}"
                       placeholder="Search by title or ID"/>

                <button type="submit">Search</button>
            </form>

            <p class="manage-song-success">${sessionScope.msg}</p>
            <p class="manage-song-error">${sessionScope.error}</p>

            <c:remove var="msg" scope="session"/>
            <c:remove var="error" scope="session"/>

            <hr/>

            <c:choose>
                <c:when test="${empty song}">
                    <p>No song found!</p>
                </c:when>

                <c:otherwise>

                    <table class="manage-song-table">
                        <tr>
                            <th>ID</th>
                            <th>Title</th>
                            <th>Duration</th>
                            <th>Audio</th>
                            <th>Release</th>
                            <th>Cover</th>
                            <th>Status</th>
                            <th>Artists</th>
                            <th>Add Artist</th>
                            <th>Albums</th>
                            <th>Add Album</th>
                            <th>Update</th>
                            <th>Action</th>
                        </tr>

                        <c:forEach var="s" items="${song}">
                            <tr>

                                <td>${s.songID}</td>
                                <td>${s.title}</td>
                                <td>${s.duration}s</td>

                                <td>
                                    <audio controls>
                                        <source src="${pageContext.request.contextPath}/StreamServlet?type=audio&file=${s.audioURL}" type="audio/mpeg">
                                    </audio>
                                </td>

                                <td>${s.releaseDate}</td>

                                <td>
                                    <img src="${pageContext.request.contextPath}/StreamServlet?type=cover&file=${s.coverImage}" width="60"/>
                                </td>

                                <td>
                                    <c:choose>
                                        <c:when test="${s.isActive}">
                                            <span class="manage-song-active">Active</span>
                                        </c:when>
                                        <c:otherwise>
                                            <span class="manage-song-inactive">Inactive</span>
                                        </c:otherwise>
                                    </c:choose>
                                </td>

                                <td>
                                    <c:forEach var="a" items="${artistsBySong[s.songID]}">
                                        ${a.artistName}<br/>
                                    </c:forEach>
                                </td>

                                <td>
                                    <form action="MainController" method="post" class="manage-song-inline-form">
                                        <input type="hidden" name="action" value="addArtistToSong"/>
                                        <input type="hidden" name="songID" value="${s.songID}"/>

                                        <select name="artistID">
                                            <c:forEach var="artist" items="${allArtists}">
                                                <option value="${artist.artistID}">
                                                    ${artist.artistName}
                                                </option>
                                            </c:forEach>
                                        </select>

                                        <button type="submit">Add</button>
                                    </form>
                                </td>

                                <td>
                                    <c:forEach var="al" items="${albumsBySong[s.songID]}">
                                        ${al.albumName}<br/>
                                    </c:forEach>
                                </td>

                                <td>
                                    <form action="MainController" method="post" class="manage-song-inline-form">
                                        <input type="hidden" name="action" value="addAlbumToSong"/>
                                        <input type="hidden" name="songID" value="${s.songID}"/>

                                        <select name="albumID">
                                            <c:forEach var="al" items="${allAlbums}">
                                                <option value="${al.albumID}">
                                                    ${al.albumName}
                                                </option>
                                            </c:forEach>
                                        </select>

                                        <button type="submit">Add</button>
                                    </form>
                                </td>

                                <td>
                                    <a class="manage-song-edit-btn"
                                       href="MainController?action=editSong&songID=${s.songID}">
                                        Edit
                                    </a>
                                </td>

                                <td>
                                    <form action="MainController" method="post"
                                          onsubmit="return confirm('Are you sure to delete this song?');">

                                        <input type="hidden" name="action" value="deleteSong"/>
                                        <input type="hidden" name="songID" value="${s.songID}"/>

                                        <input class="manage-song-delete-btn"
                                               type="submit" value="Delete"/>
                                    </form>
                                </td>

                            </tr>
                        </c:forEach>
                    </table>

                </c:otherwise>
            </c:choose>

        </div>
    </body>
