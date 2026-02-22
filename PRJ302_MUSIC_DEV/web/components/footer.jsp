<%-- 
    Document   : footer
    Created on : Jan 30, 2026, 8:58:27 AM
    Author     : NQ9
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<div class="music-footer">

    <!-- LEFT -->
    <div class="footer-left">
        <img src="assets/img/song1.jpg" class="footer-cover">

        <div class="footer-info">
            <div class="footer-title">50 Năm Về Sau</div>
            <div class="footer-artist">meChill, Đặng Thanh Tuyền</div>
        </div>

        <button class="footer-like">♡</button>
    </div>


    <!-- CENTER -->
    <div class="footer-center">

        <div class="player-controls">
            <button onclick="prevSong()">⏮</button>
            <button class="play-btn" onclick="togglePlay()" id="playBtn">▶</button>
            <button onclick="nextSong()">⏭</button>
        </div>

        <div class="progress-wrapper">
            <span id="currentTime">0:00</span>

            <div class="progress-bar" onclick="seek(event)">
                <div class="progress-fill" id="progressFill"></div>
                <div class="progress-thumb" id="progressThumb"></div>
            </div>

            <span id="duration">0:00</span>
        </div>

    </div>

    <audio id="audioPlayer"></audio>


    <!-- RIGHT -->
    <div class="footer-right">

        <button class="icon-btn">📝</button>

        <div class="volume-icon" onclick="toggleVolume()" id="volumeIcon">

            <div class="speaker-body"></div>

            <div class="wave w1"></div>
            <div class="wave w2"></div>
            <div class="wave w3"></div>

            <div class="mute-line" id="muteLine"></div>

        </div>

    </div>

</div>



<!--silder-->
<script src="assets/js/slider.js"></script>
<script src="assets/js/login.js"></script>
<script src="assets/js/forgot.js"></script>
<script src="assets/js/for-you.js"></script>
<script src="assets/js/app.js"></script>

