<%@page contentType="text/html" pageEncoding="UTF-8"%>

<div class="search-container">

    <h1 class="search-title">Kết quả tìm kiếm</h1>

    <!-- TABS -->
    <div class="search-tabs">
        <span class="tab active" onclick="switchTab('all', this)">Tất cả</span>
        <span class="tab" onclick="switchTab('song', this)">Bài hát</span>
        <span class="tab" onclick="switchTab('playlist', this)">Playlist</span>
        <span class="tab" onclick="switchTab('artist', this)">Nghệ sĩ</span>
        <span class="tab" onclick="switchTab('album', this)">Album</span>
    </div>

    <!-- ALL -->
    <div id="tab-all" class="tab-content active">

        <!-- SONG -->
        <div class="search-section-header">
            <h3>Bài hát</h3>
            <span class="search-more" onclick="switchTab('song', document.querySelector('.tab[data-tab=&quot;song&quot;]'))">
                Thêm
            </span>
        </div>
        <div class="songs-grid song-grid-all" id="searchResultContainer"></div>

        <!-- PLAYLIST -->
        <div class="search-section-header">
            <h3>Playlist</h3>
            <span class="search-more" onclick="switchTab('playlist', document.querySelector('.tab[data-tab=&quot;playlist&quot;]'))">
                Thêm
            </span>
        </div>
        <div class="songs-grid" id="searchPlaylistContainer"></div>

        <!-- ARTIST -->
        <div class="search-section-header">
            <h3>Nghệ sĩ</h3>
            <span class="search-more" onclick="switchTab('artist', document.querySelector('.tab[data-tab=&quot;artist&quot;]'))">
                Thêm
            </span>
        </div>
        <div class="songs-grid" id="searchArtistContainer"></div>

        <!-- ALBUM -->
        <div class="search-section-header">
            <h3>Album</h3>
            <span class="search-more" onclick="switchTab('album', document.querySelector('.tab[data-tab=&quot;album&quot;]'))">
                Thêm
            </span>
        </div>
        <div class="songs-grid" id="searchAlbumContainer"></div>

    </div>

    <!-- SONG -->
    <div id="tab-song" class="tab-content">

        <h3>Bài hát</h3>
        <div class="songs-grid" id="searchSongOnly"></div>

    </div>

    <!-- PLAYLIST -->
    <div id="tab-playlist" class="tab-content">

        <h3>Playlist</h3>
        <div class="songs-grid" id="searchPlaylistOnly"></div>

    </div>

    <!-- ARTIST -->
    <div id="tab-artist" class="tab-content">

        <h3>Nghệ sĩ</h3>
        <div class="songs-grid" id="searchArtistOnly"></div>

    </div>

    <!-- ALBUM -->
    <div id="tab-album" class="tab-content">

        <h3>Album</h3>
        <div class="songs-grid" id="searchAlbumOnly"></div>

    </div>

</div>

<script>

    window.switchTab = function (tab, el) {

        document.querySelectorAll(".tab-content")
                .forEach(e => e.classList.remove("active"));

        document.querySelectorAll(".tab")
                .forEach(e => e.classList.remove("active"));

        const target = document.getElementById("tab-" + tab);

        if (target) {
            target.classList.add("active");
        }

        if (el) {
            el.classList.add("active");
        } else {

            // auto set tab active nếu click bằng JS
            const tabBtn = document.querySelector(`.tab[onclick*="${tab}"]`);
            if (tabBtn)
                tabBtn.classList.add("active");

        }
    };

</script>