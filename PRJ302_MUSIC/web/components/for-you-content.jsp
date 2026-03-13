<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<section class="for-you-page">

    <!-- background gradient -->
    <div class="for-you-bg"></div>

    <!-- center player -->
    <div class="for-you-card">

        <!-- cover -->
        <div class="for-you-cover">

            <img id="fyCover" src="assets/img/default.png">

        </div>

        <div class="visualizer" id="visualizer">

            <span></span>
            <span></span>
            <span></span>
            <span></span>
            <span></span>
            <span></span>
            <span></span>
            <span></span>
            <span></span>
            <span></span>
            <span></span>
            <span></span>

        </div>

        <!-- info -->
        <div class="for-you-info">
            <h1 id="fyTitle">Chưa chọn bài</h1>
            <p id="fyArtist">---</p>
        </div>

        <!-- controls -->
        <div class="for-you-player">
            <button onclick="prevSong()">⏮</button>
            <button onclick="togglePlay()" class="btn-play">▶</button>
            <button onclick="nextSong()">⏭</button>
        </div>

    </div>

</section>