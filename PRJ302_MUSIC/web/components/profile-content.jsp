<%@page contentType="text/html" pageEncoding="UTF-8"%>

<section class="profile-page">

    <!-- ===== HEADER PROFILE ===== -->
    <div class="profile-header">
        <div class="profile-avatar">
            ${sessionScope.user != null ? sessionScope.user.username.charAt(0) : "U"}
        </div>

        <div class="profile-info">
            <div class="profile-name">
                ${sessionScope.user != null ? sessionScope.user.username : "User"}
                <span class="profile-badge">Miễn phí</span>
            </div>

            <div class="profile-stats">
                <!-- Backend sẽ xử lý -->
            </div>
        </div>
    </div>

    <!-- ===== QUICK ACTION ===== -->
    <div class="profile-actions">

        <!-- YÊU THÍCH -->
        <div class="action-card"
             onclick="showPage('favorite', document.querySelector('[data-page=favorite]'))">

            <div class="action-icon heart">❤</div>
            <div>
                <div class="action-title">Yêu Thích</div>
                <div class="action-sub"></div>
            </div>
        </div>

        <!-- NGHE GẦN ĐÂY -->
        <div class="action-card"
             onclick="showPage('recent', document.querySelector('[data-page=recent]'))">

            <div class="action-icon history">⟳</div>
            <div>
                <div class="action-title">Nghe gần đây</div>
                <div class="action-sub"></div>
            </div>
        </div>

        <!-- ĐÃ TẢI LÊN -->
        <div class="action-card">
            <div class="action-icon upload">⬆</div>
            <div>
                <div class="action-title">Đã tải lên</div>
                <div class="action-sub"></div>
            </div>
        </div>

    </div>

    <!-- ===== PLAYLIST ===== -->
    <div class="profile-playlist">
        <div class="playlist-header">
            <h2>Playlist đã tạo</h2>
            <button class="playlist-add">＋</button>
        </div>

        <div class="playlist-grid">
            <!-- Backend sẽ render playlist tại đây -->
        </div>
    </div>

</section>
