<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <title>Manage Topics</title>
        <link rel="stylesheet" href="assets/css/manage.css">
    </head>

    <body>

        <div class="manage-topic">

            <h2>Manage Topics</h2>

            <!-- ADD TOPIC -->
            <a href="Topic-From.jsp" class="manage-topic-add-btn">Add Topic</a>
            <a href="admin.jsp" class="admin-back-btn">
                ⬅ Back to Admin
            </a>

            <!-- SEARCH -->
            <form action="MainController" method="get" class="manage-topic-search">

                <input type="hidden" name="action" value="manage_topic"/>

                <input type="text" name="keyword"
                       value="${keyword}"
                       placeholder="Search by title or ID"/>

                <button type="submit">Search</button>

            </form>

            <p class="manage-topic-success">${sessionScope.msg}</p>
            <p class="manage-topic-error">${sessionScope.error}</p>

            <c:remove var="msg" scope="session"/>
            <c:remove var="error" scope="session"/>

            <hr/>

            <c:choose>

                <c:when test="${empty topic}">
                    <p>No topic found!</p>
                </c:when>

                <c:otherwise>

                    <table class="manage-topic-table">

                        <tr>
                            <th>ID</th>
                            <th>Name</th>
                            <th>Description</th>
                            <th>Cover</th>
                            <th>Status</th>
                            <th>Add Song</th>
                            <th>Update</th>
                            <th>Delete</th>
                        </tr>

                        <c:forEach var="t" items="${topic}">

                            <tr>

                                <td>${t.topicID}</td>

                                <td>${t.topicName}</td>

                                <td>${t.description}</td>

                                <td>
                                    <img src="${pageContext.request.contextPath}/StreamServlet?type=topic&file=${t.coverImage}" width="60"/>
                                </td>

                                <td>
                                    <c:choose>
                                        <c:when test="${t.isActive}">
                                            <span class="manage-topic-active">Active</span>
                                        </c:when>

                                        <c:otherwise>
                                            <span class="manage-topic-inactive">Inactive</span>
                                        </c:otherwise>
                                    </c:choose>
                                </td>

                                <td>

                                    <form action="MainController" method="post" class="manage-topic-inline-form">

                                        <input type="hidden" name="action" value="addSongToTopic"/>
                                        <input type="hidden" name="topicID" value="${t.topicID}"/>

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

                                <td>
                                    <a class="manage-topic-edit-btn"
                                       href="MainController?action=editTopic&topicID=${t.topicID}">
                                        Edit
                                    </a>
                                </td>

                                <td>

                                    <form action="MainController"
                                          method="post"
                                          onsubmit="return confirm('Delete this topic?');">

                                        <input type="hidden" name="action" value="deleteTopic"/>
                                        <input type="hidden" name="topicID" value="${t.topicID}"/>

                                        <input type="submit"
                                               class="manage-topic-delete-btn"
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