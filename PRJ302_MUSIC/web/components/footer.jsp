<%-- 
    Document   : footer
    Created on : Jan 30, 2026, 8:58:27 AM
    Author     : NQ9
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<div id="music-player">

    <!-- LEFT -->
    <div class="player-left">
        <img id="player-cover" src="assets/img/default.png">
        <div>
            <div id="player-title">No song selected</div>
            <div id="player-artist">---</div>
        </div>

        <button id="btn-like" onclick="toggleFavorite()">❤</button>
    </div>



    <!-- CENTER -->
    <div class="player-center">

        <div class="player-controls">
            <button onclick="prevSong()">⏮</button>
            <button onclick="togglePlay()" class="btn-play">▶</button>
            <button onclick="nextSong()">⏭</button>
        </div>

        <div class="player-progress">

            <span id="current-time">0:00</span>

            <input
                type="range"
                id="progress-bar"
                min="0"
                max="0"
                step="0.01"
                value="0">

            <span id="duration">0:00</span>

        </div>

    </div>

    <audio id="audio-player"></audio>

    <!-- RIGHT -->
    <div class="player-right">

        <div class="volume-icon">
            <svg viewBox="0 0 24 24" width="20" height="20">
            <path fill="currentColor"
                  d="M3 10v4h4l5 5V5l-5 5H3zm13.5 2c0-1.77-.77-3.29-2-4.29v8.58c1.23-1 2-2.52 2-4.29zm2.5 0c0 3.04-1.64 5.64-4.07 7.03l-1.43-1.43C15.24 16.67 16 14.46 16 12s-.76-4.67-2.5-5.6l1.43-1.43C17.36 6.36 19 8.96 19 12z"/>
            </svg>
        </div>
        <input type="range"
               id="volume-slider"
               min="0"
               max="1"
               step="0.01"
               value="1">

    </div>

</div>




