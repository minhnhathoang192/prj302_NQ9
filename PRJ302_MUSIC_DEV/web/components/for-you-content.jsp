<%@page contentType="text/html" pageEncoding="UTF-8"%>

<section class="for-you-page">

    <!-- BACKGROUND BLUR -->
    <div class="for-you-bg"></div>

    <!-- MAIN CARD -->
    <div class="for-you-bg-cover">
        <img id="fyBgCover" src="assets/img/demo-artist.jpg">
    </div>

    <div class="for-you-card">

        <!-- COVER -->
        <div class="for-you-cover">
            <img id="fyCover" src="assets/img/demo-artist.jpg" alt="">
            <div class="music-wave">
                <span></span><span></span><span></span><span></span><span></span>
            </div>
        </div>

        <!-- INFO -->
        <div class="for-you-info">
            <h1 id="fyTitle">Bài hát ngẫu nhiên</h1>
            <p id="fyArtist">Tên nghệ sĩ</p>
        </div>

        <!-- PLAYER -->
        <div class="for-you-player">
            <button onclick="prevSong()">⬆</button>
            <button onclick="togglePlay()" id="playBtn">▶</button>
            <button onclick="nextSong()">⬇</button>
        </div>

        <audio id="audioPlayer"></audio>
    </div>

</section>
