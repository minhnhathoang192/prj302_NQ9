<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<h2>Manage Album</h2>

<!-- ADD -->
<a href="album-form.jsp"> Add Album</a>

<!-- SEARCH -->
<form action="MainController" method="get">
    <input type="hidden" name="action" value="manage_album"/>

    <input type="text" name="keyword"
           value="${keyword}"
           placeholder="Search by name or ID"/>

    <button type="submit">Search</button>
</form>

<hr/>

<c:choose>
    <c:when test="${empty album}">
        <p>No album found!</p>
    </c:when>

    <c:otherwise>
        <table border="1" cellpadding="8">
            <tr>
                <th>ID</th>
                <th>Album Name</th>
                <th>Cover</th>
                <th>Release Date</th>
                <th>Status</th>
                <th>Edit</th>
                <th>Delete</th>
            </tr>

            <c:forEach var="a" items="${album}">
                <tr>

                    <td>${a.albumID}</td>

                    <td>${a.albumName}</td>

                    <!-- ? Cover -->
                    <td>
                        <c:choose>
                            <c:when test="${empty a.coverImage}">
                                <span>No Image</span>
                            </c:when>
                            <c:otherwise>
                                <img src="${a.coverImage}" width="60"/>
                            </c:otherwise>
                        </c:choose>
                    </td>

                    <td>${a.releaseDate}</td>

                    <!-- ? Status -->
                    <td>
                        <c:choose>
                            <c:when test="${a.isActive}">
                                Active
                            </c:when>
                            <c:otherwise>
                                Hidden
                            </c:otherwise>
                        </c:choose>
                    </td>

                    <!-- EDIT -->
                    <td>
                        <a href="MainController?action=editAlbum&albumID=${a.albumID}">
                            Edit
                        </a>
                    </td>

                    <!-- DELETE -->
                    <td>
                        <c:if test="${a.isActive}">
                            <form action="MainController" method="post"
                                  onsubmit="return confirm('Delete this album?');">

                                <input type="hidden" name="action" value="deleteAlbum"/>
                                <input type="hidden" name="albumID" value="${a.albumID}"/>

                                <input type="submit" value="Delete"/>
                            </form>
                        </c:if>

                        <c:if test="${!a.isActive}">
                            <span>Deleted</span>
                        </c:if>
                    </td>

                </tr>
            </c:forEach>
        </table>
    </c:otherwise>
</c:choose>