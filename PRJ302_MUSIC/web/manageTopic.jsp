<%-- 
    Document   : manageTopic
    Created on : Mar 1, 2026, 9:41:07 PM
    Author     : NQ9
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<h2>Manage Topics</h2>

<!-- ADD TOPIC -->
<a href="Topic-From.jsp">Add Topic</a>


<!-- SEARCH -->
<form action="MainController" method="get">
    <input type="hidden" name="action" value="manage_topic"/>

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
    <c:when test="${empty topic}">
        <p>No topic found!</p>
    </c:when>

    <c:otherwise>
        <table border="1" cellpadding="8">
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>description</th>
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
                        <img src="${t.coverImage}" width="60"/>
                    </td>

                    <td>
                        <c:choose>
                            <c:when test="${t.isActive}">
                                Active
                            </c:when>
                            <c:otherwise>
                                Inactive
                            </c:otherwise>
                        </c:choose>
                    </td>

                    <!-- ? ADD SONG TO TOPIC -->
                    <td>
                        <form action="MainController" method="post">
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

                    <!-- UPDATE -->
                    <td>
                        <a href="MainController?action=editTopic&topicID=${t.topicID}">
                            Edit
                        </a>
                    </td>

                    <!-- DELETE -->
                    <td>
                        <form action="MainController" method="post"
                              onsubmit="return confirm('Delete this topic?');">

                            <input type="hidden" name="action" value="deleteTopic"/>
                            <input type="hidden" name="topicID" value="${t.topicID}"/>

                            <input type="submit" value="Delete"/>
                        </form>
                    </td>

                </tr>
            </c:forEach>
        </table>
    </c:otherwise>
</c:choose>
