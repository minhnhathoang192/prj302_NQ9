<%-- 
    Document   : album-content
    Created on : Mar 10, 2026, 5:32:17 PM
    Author     : NQ9
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<section class="album-page">

    <!-- ===== HEADER ===== -->
    <div class="album-header">

        <!-- COVER -->
        <div class="album-cover">
            <img src="assets/img/default-album.png" id="albumCover">
        </div>

        <!-- INFO -->
        <div class="album-info">

            <span class="album-type">
                Album • <span id="albumYear">2024</span> • 
                <span id="albumSongCount">0</span> Bài hát
            </span>

            <h1 id="albumTitle">Album Name</h1>

            <div class="album-artist">
                <img src="assets/img/default-avatar.png" class="album-artist-avatar">
                <span id="albumArtist">Artist</span>
            </div>

            <!-- PLAY BUTTON -->
            <div class="album-actions">

                <button class="album-play-btn album-play">
                    ▶ Phát tất cả
                </button>

            </div>

        </div>

    </div>


    <!-- ===== SONG TABLE ===== -->
    <div class="album-table">

        <div class="album-table-header">

            <div>#</div>

            <div>Tiêu đề</div>

            <div>Nghệ sĩ</div>

            <div>Thời gian</div>

        </div>


        <!-- SONG LIST -->
        <div id="albumSongs">

            <!-- EMPTY -->
            <div class="album-empty">

                <div class="empty-icon">🎵</div>

                <h3>Album chưa có bài hát</h3>

                <p>Bài hát trong album sẽ hiển thị tại đây</p>

            </div>

        </div>

    </div>

</section>
