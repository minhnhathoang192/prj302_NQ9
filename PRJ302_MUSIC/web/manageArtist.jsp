<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <title>Manage Artist</title>
        <link rel="stylesheet" href="assets/css/manage.css">
    </head>

    <body>

        <div class="manage-artist">

            <h2>Manage Artist</h2>

            <!-- ADD -->
            <a href="artist-form.jsp" class="manage-artist-add-btn">Add Artist</a>
            <a href="admin.jsp" class="admin-back-btn">
                ⬅ Back to Admin
            </a>

            <!-- SEARCH -->
            <form action="MainController" method="get" class="manage-artist-search">

                <input type="hidden" name="action" value="manage_artist"/>

                <input type="text"
                       name="keyword"
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

                    <table class="manage-artist-table">

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

                                <td>${a.artistID}</td>

                                <td>${a.artistName}</td>

                                <td>

                                    <c:choose>

                                        <c:when test="${empty a.avatarURL}">
                                            <span>No Image</span>
                                        </c:when>

                                        <c:otherwise>
                                            <img src="${pageContext.request.contextPath}/StreamServlet?type=artist&file=${a.avatarURL}" width="60"/>
                                        </c:otherwise>

                                    </c:choose>

                                </td>

                                <td>${a.description}</td>

                                <td>${a.debutDate}</td>

                                <td>

                                    <c:choose>

                                        <c:when test="${a.isActive}">
                                            <span class="manage-artist-active">Active</span>
                                        </c:when>

                                        <c:otherwise>
                                            <span class="manage-artist-hidden">Hidden</span>
                                        </c:otherwise>

                                    </c:choose>

                                </td>

                                <td>

                                    <a class="manage-artist-edit-btn"
                                       href="MainController?action=editArtist&artistID=${a.artistID}">
                                        Edit
                                    </a>

                                </td>

                                <td>

                                    <c:if test="${a.isActive}">

                                        <form action="MainController"
                                              method="post"
                                              onsubmit="return confirm('Delete this artist?');">

                                            <input type="hidden" name="action" value="deleteArtist"/>

                                            <input type="hidden" name="artistID" value="${a.artistID}"/>

                                            <input type="submit"
                                                   class="manage-artist-delete-btn"
                                                   value="Delete"/>

                                        </form>

                                    </c:if>

                                    <c:if test="${!a.isActive}">
                                        <span class="manage-artist-deleted">Deleted</span>
                                    </c:if>

                                </td>

                            </tr>

                        </c:forEach>

                    </table>

                </c:otherwise>

            </c:choose>

        </div>

    </body>
</html>