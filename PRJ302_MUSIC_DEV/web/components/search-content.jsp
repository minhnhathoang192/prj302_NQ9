<%-- 
    Document   : search-content
    Created on : Feb 21, 2026, 10:53:46 PM
    Author     : NQ9
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<p>Size: ${searchResult.size()}</p>
<div class="search-container">

    <h1 class="search-title">Search Results</h1>

    <div class="songs-section">

        <div class="songs-header">
            <h2>Songs</h2>
        </div>

        <div class="songs-grid">

            <c:if test="${empty searchResult}">
                <p>Không tìm thấy kết quả.</p>
            </c:if>

            <c:forEach var="s" items="${searchResult}">
                <div class="song-item">

                    <div class="song-thumb">
                        <img src="${pageContext.request.contextPath}/StreamServlet?type=cover&file=${s.coverImage}">

                        <!-- PLAY BUTTON -->
                        <div class="play-btn"
                             onclick="playSong('${pageContext.request.contextPath}/StreamServlet?type=audio&file=${s.audioURL}')">
                            ▶
                        </div>
                    </div>

                    <div class="song-info">
                        <div class="song-title">${s.title}</div>
                        <div class="song-artist">${s.artistName}</div>
                    </div>

                </div>
            </c:forEach>

        </div>

    </div>

</div>

<!-- GLOBAL AUDIO PLAYER -->
<audio id="audioPlayer"></audio>

<script>
    document.addEventListener("DOMContentLoaded", function () {
        var audioPlayer2 = document.getElementById("audioPlayer");

        window.playSong = function (src) {

            if (!audioPlayer2)
                return;

            if (audioPlayer2.src.includes(src) && !audioPlayer2.paused) {
                audioPlayer2.pause();
                return;
            }

            audioPlayer2.src = src;
            audioPlayer2.play();
        };
    });
</script>
