<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<h2>Manage Artist</h2>

<!-- ADD -->
<a href="artist-form.jsp"> Add Artist</a>

<!-- SEARCH -->
<form action="MainController" method="get">
    <input type="hidden" name="action" value="manage_artist"/>

    <input type="text" name="keyword"
           value="${keyword}"
           placeholder="Search by name or ID"/>

    <button type="submit">Search</button>
</form>

<hr/>

<c:choose>
    <c:when test="${empty ARTIST_LIST}">
        <p>No artist found!</p>
    </c:when>

    <c:otherwise>
        <table border="1" cellpadding="8">
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Avatar</th>
                <th>Description</th>
                <th>Debut Date</th>
                <th>Status</th>
                <th>Edit</th>
                <th>Delete</th>
            </tr>

            <c:forEach var="a" items="${ARTIST_LIST}">
                <tr>

                    <!-- ID -->
                    <td>${a.artistID}</td>

                    <!-- Name -->
                    <td>${a.artistName}</td>

                    <!-- Avatar -->
                    <td>
                        <c:choose>
                            <c:when test="${empty a.avatarURL}">
                                No Image
                            </c:when>
                            <c:otherwise>
                                <img src="${pageContext.request.contextPath}/StreamServlet?type=artist&file=${a.avatarURL}" width="60"/>
                            </c:otherwise>
                        </c:choose>
                    </td>

                    <!-- Description -->
                    <td>${a.description}</td>

                    <!-- Debut Date -->
                    <td>${a.debutDate}</td>

                    <!-- Status -->
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
                        <a href="MainController?action=editArtist&artistID=${a.artistID}">
                            Edit
                        </a>
                    </td>

                    <!-- DELETE -->
                    <td>
                        <c:if test="${a.isActive}">
                            <form action="MainController" method="post"
                                  onsubmit="return confirm('Delete this artist?');">

                                <input type="hidden" name="action" value="deleteArtist"/>
                                <input type="hidden" name="artistID" value="${a.artistID}"/>

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