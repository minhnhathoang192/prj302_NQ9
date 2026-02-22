<%-- 
    Document   : album
    Created on : Jan 30, 2026, 9:12:38 AM
    Author     : NQ9
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

        <jsp:include page="components/header.jsp"/>

        <div class="app">
            <aside class="sidebar">
                <jsp:include page="components/navbar.jsp"/>
            </aside>

            <main class="main">
                <!-- TOP BAR -->
                <div class="topbar">
                    <form action="search" method="get" style="flex:1">
                        <input type="text" name="keyword"
                               placeholder="Bạn muốn nghe nhạc gì?">
                    </form>
                    <a href="login.jsp" class="login-btn">Đăng nhập</a>
                </div>

                <!-- CONTENT -->
                <section class="content">

                    <!-- ALBUM INFO -->
                    <div class="album-info">
                        <img src="assets/img/demo.jpg" class="album-cover">

                        <div>
                            <h2>${album.name}</h2>
                            <p>${album.artistName}</p>
                            <button class="login-btn">▶ Phát tất cả</button>
                        </div>
                    </div>

                    <!-- SONG LIST -->
                    <h3>Bài hát</h3>

                    <div class="song-list">
                        <c:forEach var="s" items="${songs}">
                            <div class="song-row">
                                <span>${s.title}</span>
                                <small>${s.artistName}</small>
                            </div>
                        </c:forEach>
                    </div>

                </section>
            </main>
        </div>

        <jsp:include page="components/footer.jsp"/>

    </body>
</html>
