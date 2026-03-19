<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<section class="profile-page">

    <!-- ===== HEADER PROFILE ===== -->
    <div class="profile-header">
        <div class="profile-avatar">
            <c:choose>
                <c:when test="${not empty sessionScope.user and not empty sessionScope.user.userName}">
                    ${sessionScope.user.userName.substring(0,1)}
                </c:when>
                <c:otherwise>
                    U
                </c:otherwise>
            </c:choose>
        </div>

        <div class="profile-info">
            <div class="profile-name">
                <c:choose>
                    <c:when test="${not empty sessionScope.user and not empty sessionScope.user.userName}">
                        ${sessionScope.user.userName}
                    </c:when>
                    <c:otherwise>
                        User
                    </c:otherwise>
                </c:choose>
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

<!--         ĐÃ TẢI LÊN 
        <div class="action-card">
            <div class="action-icon upload">⬆</div>
            <div>
                <div class="action-title">Đã tải lên</div>
                <div class="action-sub"></div>
            </div>
        </div>-->

    </div>

    <!-- ===== PLAYLIST ===== -->
    <!-- ===== USER PLAYLIST ===== -->
    <div class="user-playlist-section">

        <div class="user-playlist-header">
            <h2 class="user-playlist-title">Playlist đã tạo</h2>
            <button class="user-playlist-add" onclick="openPlaylist()">＋</button>
        </div>

        <!-- JS render playlist -->
        <div class="user-playlist-grid" id="playlistGrid">

            <p class="user-playlist-empty" id="playlistEmpty">
                Đang tải playlist...
            </p>

        </div>

    </div>

</section>


