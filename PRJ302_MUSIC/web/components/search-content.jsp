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

    <!-- ================= ALL ================= -->
    <div id="tab-all" class="tab-content active">

        <!-- SONG -->
        <h3>Bài hát</h3>
        <div class="songs-grid" id="searchResultContainer">
            <!-- JS sẽ render kết quả ở đây -->
        </div>

        <!-- PLAYLIST -->
        <h3>Playlist</h3>
        <div class="songs-grid" id="searchPlaylistContainer">
            <!-- Nâng cấp sau -->
        </div>

        <!-- ARTIST -->
        <h3>Nghệ sĩ</h3>
        <div class="songs-grid" id="searchArtistContainer">
            <!-- Nâng cấp sau -->
        </div>

        <!-- ALBUM -->
        <h3>Album</h3>
        <div class="songs-grid" id="searchAlbumContainer">
            <!-- Nâng cấp sau -->
        </div>

    </div>

</div>

<script>

window.switchTab = function (tab, el) {

    // Không cần hash nữa nếu muốn SPA thuần
    // window.location.hash = "search-" + tab;

    document.querySelectorAll(".tab-content").forEach(e => e.classList.remove("active"));
    document.querySelectorAll(".tab").forEach(e => e.classList.remove("active"));

    let target = document.getElementById("tab-" + tab);
    if (target) target.classList.add("active");

    if (el) el.classList.add("active");
};

</script>