<%@page contentType="text/html" pageEncoding="UTF-8"%>

<div class="recent-wrapper">

    <h1 class="recent-title">Recently Played</h1>

    <div class="recent-tabs">
        <span class="tab active">Bài hát</span>
    </div>

    <!-- TABLE -->
    <div class="recent-table">

        <!-- HEADER -->
        <div class="recent-header">
            <div>#</div>
            <div>Tiêu đề</div>
            <div>Nghệ sĩ</div>
            <div>Thời gian</div>
        </div>

        <!-- DATA -->
        <div id="recentList">

            <!-- EMPTY STATE -->
            <div class="recent-empty">
                <div class="empty-icon">🎵</div>
                <h3>Chưa có bài hát nào được nghe</h3>
                <p>Khi bạn phát một bài hát, nó sẽ xuất hiện ở đây</p>
            </div>

        </div>

    </div>

</div>