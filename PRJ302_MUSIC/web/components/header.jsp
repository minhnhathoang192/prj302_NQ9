<%-- 
    Document   : header
    Created on : Jan 30, 2026, 8:58:16 AM
    Author     : NQ9
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<div class="header">
    <!-- LEFT -->
    <div class="header-left">
        <button class="nav-btn" onclick="goBack()">←</button>
        <button class="nav-btn" onclick="goForward()">→</button>
    </div>

    <!-- CENTER SEARCH -->
    <div class="header-center">
        <form action="search" method="get">
            <input type="text"
                   name="keyword"
                   placeholder="Bạn muốn nghe nhạc gì?"
                   value="${param.keyword}">
        </form>
    </div>

    <!-- RIGHT -->
    <div class="header-right">
        <button class="vip-btn" onclick="showPage('vip')">FREE VIP</button>
        <button onclick="openLogin()" class="login-btn-header">Đăng nhập</button>
        <div class="settings-wrapper">
            <button class="header-icon" onclick="toggleSettings()">⚙️</button>

            <div class="settings-menu" id="settingsMenu">
                <div class="settings-item">
                    🌐 <span>Language</span>
                    <span class="arrow">›</span>
                </div>

                <div class="settings-item">
                    🛟 <span>Hướng dẫn và hỗ trợ</span>
                </div>

                <div class="settings-item">
                    💬 <span>Góp ý</span>
                </div>
            </div>
        </div>

    </div>
</div>
