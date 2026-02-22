<%@page contentType="text/html" pageEncoding="UTF-8"%>


<div class="recent-wrapper">

    <h1 class="recent-title">Recently Played</h1>

    <!-- TAB -->
    <div class="recent-tabs">
        <span class="tab active" onclick="switchRecent(this, 'song')">Bài hát</span>
        <span class="tab" onclick="switchRecent(this, 'playlist')">Playlist</span>
        <span class="tab" onclick="switchRecent(this, 'album')">Album</span>
        <span class="tab" onclick="switchRecent(this, 'artist')">Nghệ sĩ</span>
    </div>


    <!-- DANH SÁCH -->
    <div id="recentList">

        <!-- EMPTY STATE -->
        <div class="recent-empty">
            <div class="empty-icon">🎵</div>
            <h3>Chưa có bài hát nào được xem</h3>
            <p>Khi bạn phát một bài hát, nó sẽ xuất hiện ở đây</p>
        </div>

        <!-- KHI CÓ DATA BACKEND SẼ ĐỔ VÀO TABLE NÀY -->
        <!--
        <div class="recent-table">
            <div class="recent-row header">
                <span>#</span>
                <span>Tiêu đề</span>
                <span>Người đăng</span>
                <span>Nghệ sĩ</span>
                <span>Thời lượng</span>
            </div>

            <div class="recent-row">
                <span>1</span>
                <span class="song-title">
                    <img src="assets/img/song1.jpg">
                    Bài Ca Cổ Động Viên
                </span>
                <span>HT Production</span>
                <span>Đan Trường</span>
                <span>05:26</span>
            </div>
        </div>
        -->

    </div>

</div>
