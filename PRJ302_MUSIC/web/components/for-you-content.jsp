<section class="for-you-page">

    <!-- BACKGROUND BLUR -->
    <div class="for-you-bg"></div>

    <!-- MAIN CARD -->
    <div class="for-you-bg-cover">
        <img id="fyBgCover"
             src="assets/img/demo-artist.jpg">
    </div>

    <div class="for-you-card">

        <!-- COVER -->
        <div class="for-you-cover">
            <img id="fyCover" src="assets/img/demo-artist.jpg" alt="">

            <!-- CLICK PLAY -->
            <div class="music-wave"
                 onclick="playSong(currentSong.audioURL, currentSong.title, currentSong.coverURL)">
                <span></span><span></span><span></span><span></span><span></span>
            </div>
        </div>

        <!-- INFO -->
        <div class="for-you-info">
            <h1 id="fyTitle">Ch?a ch?n bài</h1>
            <p id="fyArtist">---</p>
        </div>

        <!-- PLAYER -->
        <div class="for-you-player">
            <button onclick="prevSong()">?</button>
            <button onclick="togglePlay()" class="btn-play">?</button>
            <button onclick="nextSong()">?</button>
        </div>

    </div>

</section>