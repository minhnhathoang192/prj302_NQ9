<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <title>Manage Album</title>
        <link rel="stylesheet" href="assets/css/manage.css">
    </head>

    <body>

        <div class="manage-album">

            <h2>Manage Album</h2>

            <!-- ADD -->
            <a href="album-form.jsp" class="manage-album-add-btn">Add Album</a>
            <a href="admin.jsp" class="admin-back-btn">
                ⬅ Back to Admin
            </a>

            <!-- SEARCH -->
            <form action="MainController" method="get" class="manage-album-search">

                <input type="hidden" name="action" value="manage_album"/>

                <input type="text"
                       name="keyword"
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

                    <table class="manage-album-table">

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

                                <!-- COVER -->
                                <td>

                                    <c:choose>

                                        <c:when test="${empty a.coverImage}">
                                            <span>No Image</span>
                                        </c:when>

                                        <c:otherwise>
                                            <img src="${pageContext.request.contextPath}/StreamServlet?type=album&file=${a.coverImage}" width="60"/>
                                        </c:otherwise>

                                    </c:choose>

                                </td>

                                <td>${a.releaseDate}</td>

                                <td>

                                    <c:choose>

                                        <c:when test="${a.isActive}">
                                            <span class="manage-album-active">Active</span>
                                        </c:when>

                                        <c:otherwise>
                                            <span class="manage-album-inactive">Hidden</span>
                                        </c:otherwise>

                                    </c:choose>

                                </td>

                                <td>

                                    <a class="manage-album-edit-btn"
                                       href="MainController?action=editAlbum&albumID=${a.albumID}">
                                        Edit
                                    </a>

                                </td>

                                <td>

                                    <c:if test="${a.isActive}">

                                        <form action="MainController"
                                              method="post"
                                              onsubmit="return confirm('Delete this album?');">

                                            <input type="hidden"
                                                   name="action"
                                                   value="deleteAlbum"/>

                                            <input type="hidden"
                                                   name="albumID"
                                                   value="${a.albumID}"/>

                                            <input type="submit"
                                                   class="manage-album-delete-btn"
                                                   value="Delete"/>

                                        </form>

                                    </c:if>

                                    <c:if test="${!a.isActive}">
                                        <span class="manage-album-deleted">Deleted</span>
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