<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<h2> Manage Songs</h2>

<!-- ADD -->
<a href="upload-content.jsp"> Add Song</a>

<!-- SEARCH -->
<form action="MainController" method="get">
    <input type="hidden" name="action" value="manage_song"/>

    <input type="text" name="keyword"
           value="${keyword}"
           placeholder="Search by title or ID"/>

    <button type="submit">Search</button>
</form>

<p style="color: green">${sessionScope.msg}</p>
<p style="color: red">${sessionScope.error}</p>

<c:remove var="msg" scope="session"/>
<c:remove var="error" scope="session"/>

<hr/>

<c:choose>
    <c:when test="${empty song}">
        <p>No song found!</p>
    </c:when>

    <c:otherwise>
        <table border="1" cellpadding="8">
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

                    <!--Audio preview -->
                    <td>
                        <audio controls width="150">
                            <source src="${s.audioURL}" type="audio/mpeg">
                        </audio>
                    </td>

                    <td>${s.releaseDate}</td>

                    <!-- Cover -->
                    <td>
                        <img src="${s.coverImage}" width="60"/>
                    </td>

                    <!--Status -->
                    <td>
                        <c:choose>
                            <c:when test="${s.isActive}">
                                Active
                            </c:when>
                            <c:otherwise>
                                Inactive
                            </c:otherwise>
                        </c:choose>
                    </td>

                    <!<!-- Artist -->
                    <td>
                        <c:forEach var="a" items="${artistsBySong[s.songID]}">
                            ${a.artistName}<br/>
                        </c:forEach>
                    </td>

                    <!<!-- addArtist -->
                    <td>
                        <form action="MainController" method="post">
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

                    <!-- Albums -->
                    <td>
                        <c:forEach var="al" items="${albumsBySong[s.songID]}">
                            ${al.albumName}<br/>
                        </c:forEach>
                    </td>

                    <!-- addAlbum -->
                    <td>
                        <form action="MainController" method="post">
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


                    <!-- UPDATE -->
                    <td>
                        <a href="MainController?action=editSong&songID=${s.songID}">
                            Edit
                        </a>
                    </td>

                    <td>
                        <form action="MainController" method="post"
                              onsubmit="return confirm('Are you sure to delete this song?');">

                            <input type="hidden" name="action" value="deleteSong"/>
                            <input type="hidden" name="songID" value="${s.songID}"/>

                            <input type="submit" value="Delete"/>
                        </form>
                    </td>

                </tr>
            </c:forEach>
        </table>
    </c:otherwise>
</c:choose>