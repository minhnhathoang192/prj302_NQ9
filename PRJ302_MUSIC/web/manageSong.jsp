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