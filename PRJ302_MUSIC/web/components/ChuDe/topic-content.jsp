<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<div class="topic-container">

    <!-- TITLE -->
    <div class="topic-header">
        <h2 class="topic-title">${topicName}</h2>
        <p class="topic-desc">Danh sách bài hát trong chủ đề</p>
    </div>

    <!-- EMPTY -->
    <c:if test="${empty songs}">
        <div class="empty">
            <p>Chưa có bài hát nào trong chủ đề này 🎧</p>
        </div>
    </c:if>

    <!-- SONG GRID -->
    <div class="song-grid">

        <c:forEach var="s" items="${songs}">
            <div class="song-card">

                <div class="song-cover">
                    <img src="${pageContext.request.contextPath}/StreamServlet?type=cover&file=${s.coverImage}">

                    <div class="play-btn"
                         onclick="playSong(
                                         '${pageContext.request.contextPath}/StreamServlet?type=audio&file=${s.audioURL}',
                                                         '${s.title}',
                                                         '${pageContext.request.contextPath}/StreamServlet?type=cover&file=${s.coverImage}'
                                                                         )">
                        ▶
                    </div>
                </div>

                <div class="song-title">${s.title}</div>
                <div class="song-meta">Music</div>

            </div>
        </c:forEach>

    </div>

</div>