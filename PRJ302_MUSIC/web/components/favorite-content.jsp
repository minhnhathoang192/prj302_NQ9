<%@page contentType="text/html" pageEncoding="UTF-8"%>

<section class="favorite-page">

    <!-- HEADER -->
    <div class="favorite-header">

        <div class="favorite-cover">
            <div class="heart-icon">❤️</div>
        </div>

        <div class="favorite-info">
            <span class="playlist-label">
                Playlist · <span id="favoriteCount">0</span> Songs
            </span>

            <h1>Favorite Songs</h1>
            <p>Your liked music</p>

            <div class="favorite-actions">
                <button class="pl-btn fav-play">
                    ▶ Phát tất cả
                </button>
                <button class="favorite-btn">⬇ Download</button>
            </div>

        </div>

    </div>


    <!-- SONG TABLE -->
    <div class="favorite-table">

        <div class="favorite-table-header">
            <div>#</div>
            <div>Title</div>
            <div>Artist</div>
            <div>Time</div>
        </div>

        <!-- JS render songs here -->
        <div id="favoriteSongs"></div>

    </div>


    <!-- EMPTY STATE -->
    <div class="favorite-empty" id="favoriteEmpty">

        <div class="empty-icon">📦</div>
        <h3>It's empty here</h3>
        <p>Choose some songs to add to this playlist</p>

        <button class="btn-add">
            Add songs
        </button>

    </div>

</section>
