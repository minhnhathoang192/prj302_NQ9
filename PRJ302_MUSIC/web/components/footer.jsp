<%-- 
    Document   : footer
    Created on : Jan 30, 2026, 8:58:27 AM
    Author     : NQ9
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<div id="music-player">

    <!-- LEFT: cover + title -->
    <div class="player-left">
        <img id="player-cover" src="assets/img/default.png">
        <div>
            <div id="player-title">No song selected</div>
            <div id="player-artist">---</div>
        </div>
    </div>

    <!-- CENTER: controls -->
    <div class="player-center">
        <button onclick="prevSong()">⏮</button>
        <button onclick="togglePlay()">▶</button>
        <button onclick="nextSong()">⏭</button>
    </div>
    
    <audio id="audio-player"></audio>

    <!-- RIGHT: volume -->
    <div class="player-right">
        🔊
    </div>

    <!-- AUDIO -->
    <audio id="audio-player"></audio>

</div>



<!--silder-->
<script src="assets/js/slider.js"></script>
<script src="assets/js/login.js"></script>
<script src="assets/js/forgot.js"></script>
<script src="assets/js/for-you.js"></script>
<script src="assets/js/app.js"></script>
<script src="assets/js/player.js"></script>

