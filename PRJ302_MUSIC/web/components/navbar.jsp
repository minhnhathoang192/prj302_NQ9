<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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
        <li class="nav-item" onclick="loadForYou(this)">
            <span class="icon">📊</span>
            <span>Dành Cho Bạn</span>
        </li>
        <li class="nav-item" onclick="handleProfileClick('profile',this)">
            <span class="icon">👤</span>
            <span>Của Tui</span>
        </li>
    </ul>


    <div class="nav-section">
        MY LIBRARY
    </div>

    <ul class="nav-menu">
        <li class="nav-item" onclick="handleProfileClick('favorite', this)">
            <span class="icon">❤</span>
            <span>Yêu thích</span>
        </li>
        <li class="nav-item" onclick="handleProfileClick('recent', this)">
            <span class="icon">🕒</span>
            <span>Nghe gần đây</span>
        </li>
    </ul>

    <!-- LOGIN -->
    <div class="nav-login">
        <c:if test="${empty sessionScope.user}">
            <div class="nav-login">
                <p>Đăng nhập để khám phá nhạc hay</p>
                <button onclick="openLogin()" class="login-btn-nav">
                    Đăng nhập
                </button>
            </div>
        </c:if>
    </div>

</div>
