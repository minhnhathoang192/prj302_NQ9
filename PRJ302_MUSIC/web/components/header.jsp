<%-- 
    Document   : header
    Created on : Jan 30, 2026, 8:58:16 AM
    Author     : NQ9
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

        <div class="header">
            <!-- LEFT -->
            <div class="header-left">
                <button class="nav-btn">←</button>
                <button class="nav-btn">→</button>
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
                <button class="vip-btn">FREE VIP</button>
                <button onclick="openLogin()" class="login-btn-header">Đăng nhập</button>
                <button class="header-icon">⚙️</button>
            </div>
        </div>
