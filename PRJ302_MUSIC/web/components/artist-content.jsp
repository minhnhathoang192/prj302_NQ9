<%-- 
    Document   : artist-content
    Created on : Mar 10, 2026, 7:41:56 PM
    Author     : NQ9
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<section class="artist-page">

    <!-- ===== COVER ===== -->
    <div class="artist-cover">

        <!-- background blur -->
        <img id="artistCoverBg" class="artist-cover-bg" src="assets/img/default-avatar.png">

        <!-- overlay -->
        <div class="artist-overlay"></div>

        <!-- artist info -->
        <div class="artist-header">

            <div class="artist-avatar-box">
                <img id="artistAvatar" class="artist-avatar">
            </div>

            <div class="artist-info">

                <h1 id="artistName">Artist Name</h1>

                <div class="artist-followers">
                    <span id="artistFollowers">0</span> followers
                </div>

                <div class="artist-actions">

                    <button class="artist-play-btn artist-play">
                        ▶ Phát tất cả
                    </button>

                    <button class="artist-follow-btn" id="followArtistBtn"
                            onclick="toggleFollowArtist()">
                        Theo dõi
                    </button>

                </div>

            </div>

        </div>

    </div>


    <!-- ===== SONG TABLE ===== -->
    <div class="artist-songs">

        <h2>Bài hát</h2>

        <div class="artist-table">

            <div class="artist-table-header">
                <div>#</div>
                <div>Tiêu đề</div>
                <div>Nghệ sĩ</div>
                <div>Thời gian</div>
            </div>

            <div id="artistSongList">

                <!-- empty -->
                <div class="artist-empty">

                    <div class="empty-icon">🎵</div>

                    <h3>Artist chưa có bài hát</h3>

                    <p>Bài hát sẽ hiển thị ở đây</p>

                </div>

            </div>

        </div>

    </div>

</section>
