<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<section class="for-you-page">

    <!-- BACKGROUND BLUR -->
    <div class="for-you-bg"></div>

    <!-- BACKGROUND COVER -->
    <div class="for-you-bg-cover">
        <img id="fyBgCover" src="assets/img/default.png">
    </div>

    <!-- MAIN CARD -->
    <div class="for-you-card">

        <!-- COVER -->
        <div class="for-you-cover">

            <img id="fyCover" src="assets/img/default.png" alt="cover">

            <!-- PLAY / PAUSE -->
            <div class="music-wave" id="fyPlayBtn">
                <span></span>
                <span></span>
                <span></span>
                <span></span>
                <span></span>
            </div>

        </div>

        <!-- INFO -->
        <div class="for-you-info">
            <h1 id="fyTitle">Chưa chọn bài</h1>
            <p id="fyArtist">---</p>
        </div>

        <!-- PLAYER CONTROLS -->
        <div class="for-you-player">
            <button onclick="prevSong()">⏮</button>
            <button onclick="togglePlay()" class="btn-play">▶</button>
            <button onclick="nextSong()">⏭</button>
        </div>

    </div>

</section>