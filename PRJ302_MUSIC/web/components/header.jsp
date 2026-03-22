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
        <form id="searchForm">
            <input type="hidden" name="action" value="search">
            <input type="text"
                   name="keyword"
                   placeholder="Bạn muốn nghe nhạc gì?"
                   value="${keyword}">

            <input type="submit" hidden>
        </form>
    </div>

    <!-- RIGHT -->
    <div class="header-right">
        <button class="vip-btn" onclick="changeTopic()">CHỌN TOPIC</button>
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

                        <c:if test="${sessionScope.user.roleID == 1}">
                            <div class="dropdown-item"
                                 onclick="window.location = 'MainController?action=adminDashboard'">
                                ⚙ Quản lý
                            </div>
                        </c:if>

                        <div class="dropdown-item"
                             onclick="window.location = 'MainController?action=logout'">
                            🚪 Log out
                        </div>

                    </div>


                </c:when>
                <%-- chua login sessionScope.user = null --%>
                <%-- Buoc 1 --%>
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

                <a href="userManual.jsp" class="settings-link">
                    <div class="settings-item">
                       <span >Hướng dẫn và hỗ trợ</span>
                    </div>
                </a>
            </div>
        </div>

    </div>
</div>
