<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<div class="search-container">

    <h1 class="search-title">Kết quả tìm kiếm</h1>

    <!-- TABS -->
    <div class="search-tabs">
        <span class="tab active" onclick="switchTab('all', this)">Tất cả</span>
        <span class="tab" onclick="switchTab('song', this)">Bài hát</span>
        <span class="tab" onclick="switchTab('playlist', this)">Playlist</span>
        <span class="tab" onclick="switchTab('artist', this)">Nghệ sĩ</span>
        <span class="tab" onclick="switchTab('album', this)">Album</span>
    </div>

    <!-- ================= ALL ================= -->
    <div id="tab-all" class="tab-content active">

        <!-- SONG -->
        <h3>Bài hát</h3>
        <div class="songs-grid">
            <c:if test="${empty songs}">
                <p class="empty-msg">
                    Không có playlist
                </p>
            </c:if>

            <c:forEach var="s" items="${songs}">
                <div class="song-item">
                    <div class="song-thumb">
                        <img src="${pageContext.request.contextPath}/StreamServlet?type=cover&file=${s.coverImage}">
                        <div class="play-btn"
                             onclick="playSong('${pageContext.request.contextPath}/StreamServlet?type=audio&file=${s.audioURL}')">
                            ▶
                        </div>
                    </div>
                    <div class="song-info">
                        <div class="song-title">${s.title}</div>
                    </div>
                </div>
            </c:forEach>
        </div>

        <!-- PLAYLIST -->
        <h3>Playlist</h3>
        <div class="songs-grid">
            <c:if test="${empty playlists}">
                <p class="empty-msg">
                    Không có playlist
                </p>
            </c:if>

            <c:forEach var="p" items="${playlists}">
                <div class="song-item">
                    <div class="song-thumb">
                        <img src="${pageContext.request.contextPath}/StreamServlet?type=cover&file=${p.coverImage}">
                    </div>
                    <div class="song-info">
                        <div class="song-title">${p.name}</div>
                    </div>
                </div>
            </c:forEach>
        </div>

        <!-- ARTIST -->
        <h3>Nghệ sĩ</h3>
        <div class="songs-grid">

            <c:if test="${empty artists}">
                <p class="empty-msg">Không có nghệ sĩ</p>
            </c:if>

            <c:forEach var="a" items="${artists}">
                <div class="song-item">
                    <div class="song-thumb">
                        <img src="${pageContext.request.contextPath}/StreamServlet?type=avatar&file=${a.avatar}">
                    </div>
                    <div class="song-info">
                        <div class="song-title">${a.name}</div>
                    </div>
                </div>
            </c:forEach>
        </div>

        <!-- ALBUM -->
        <h3>Album</h3>
        <div class="songs-grid">

            <c:if test="${empty albums}">
                <p class="empty-msg">Không có album</p>
            </c:if>

            <c:forEach var="al" items="${albums}">
                <div class="song-item">
                    <div class="song-thumb">
                        <img src="${pageContext.request.contextPath}/StreamServlet?type=cover&file=${al.coverImage}">
                    </div>
                    <div class="song-info">
                        <div class="song-title">${al.name}</div>
                    </div>
                </div>
            </c:forEach>
        </div>

    </div>

</div>

<!-- AUDIO -->
<audio id="audioPlayer"></audio>

<script>

    window.switchTab = function (tab, el) {

        window.location.hash = "search-" + tab;

        document.querySelectorAll(".tab-content").forEach(e => e.classList.remove("active"));
        document.querySelectorAll(".tab").forEach(e => e.classList.remove("active"));

        document.getElementById("tab-" + tab).classList.add("active");
        if (el)
            el.classList.add("active");
    };

    document.addEventListener("DOMContentLoaded", function () {
        var audioPlayer = document.getElementById("audioPlayer");

        window.playSong = function (src) {
            if (!audioPlayer)
                return;

            if (audioPlayer.src.includes(src) && !audioPlayer.paused) {
                audioPlayer.pause();
                return;
            }

            audioPlayer.src = src;
            audioPlayer.play();
        };
    });
</script>