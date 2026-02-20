<%-- 
    Document   : header
    Created on : Jan 30, 2026, 8:58:16 AM
    Author     : NQ9
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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
        <div id="authArea">

            <c:choose>

                <c:when test="${not empty sessionScope.user}">
                    <div class="avatar-header" onclick="toggleUserMenu()">

                        <c:choose>
                            <c:when test="${not empty sessionScope.user.avatar}">
                                <img src="assets/avatar/${sessionScope.user.avatar}" alt="avatar">
                            </c:when>

                            <c:otherwise>
                                <span>
                                    ${sessionScope.user.userName.substring(0,1)}
                                </span>
                            </c:otherwise>
                        </c:choose>

                    </div>

                    <div class="user-dropdown" id="userDropdown">

                        <div class="dropdown-item"
                             onclick="showPage('profile', document.querySelector('[data-page=profile]'))">
                            👤 Hồ sơ
                        </div>

                        <div class="dropdown-item"
                             onclick="window.location = 'MainController?action=logout'">
                            🚪 Log out
                        </div>

                    </div>


                </c:when>

                <c:otherwise>
                    <button onclick="openLogin()" class="login-btn-header">
                        Đăng nhập
                    </button>
                </c:otherwise>

            </c:choose>

        </div>
        <div class="settings-wrapper">
            <button class="header-icon" onclick="toggleSettings()">⚙</button>

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
