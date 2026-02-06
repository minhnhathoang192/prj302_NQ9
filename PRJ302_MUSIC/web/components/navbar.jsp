<%@page contentType="text/html" pageEncoding="UTF-8"%>

<div class="nav-wrap">

    <!-- LOGO -->
    <div class="nav-logo">
        <span class="logo-icon">QNQ</span>
        <div class="logo-text">
            <strong>Mạng Xã Hội Âm Nhạc QNQ</strong>
        </div>
    </div>

    <!-- MAIN MENU -->
    <ul class="nav-menu">
        <li class="nav-item active" onclick="showPage('home', this)">
            <span class="icon">🎵</span>
            <span>Khám Phá</span>
        </li>
        <li class="nav-item" onclick="showPage('for-you', this)">
            <span class="icon">📊</span>
            <span>Dành Cho Bạn</span>
        </li>
        <li class="nav-item" onclick="showPage('profile', this)">
            <span class="icon">👤</span>
            <span>Của Tui</span>
        </li>
    </ul>

    <!-- LOGIN -->
    <div class="nav-login">
        <p>Đăng nhập để khám phá nhạc hay</p>
        <button onclick="openLogin()" class="login-btn-nav">Đăng nhập</button>
    </div>

</div>
