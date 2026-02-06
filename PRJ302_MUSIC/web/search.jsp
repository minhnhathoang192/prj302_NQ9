<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Search</title>
        <link rel="stylesheet" href="assets/css/style.css"/>
    </head>
    <body>
        <jsp:include page="components/header.jsp"/>

        <div class="app">
            <aside class="sidebar">
                <jsp:include page="components/navbar.jsp"/>
            </aside>

            <main class="main">
                <section class="content">
                    <h2>Kết quả tìm kiếm</h2>

                    <div class="card-row">
                        <c:forEach var="s" items="${songs}">
                            <div class="music-card">
                                <img src="assets/img/demo.jpg">

                                <div class="card-overlay">
                                    <span>▶</span>
                                    <span>❤️</span>
                                    <span>➕</span>
                                </div>

                                <span>${s.title}</span>
                                <small>${s.artistName}</small>
                            </div>
                        </c:forEach>

                        <c:if test="${empty songs}">
                            <p>Không tìm thấy bài hát</p>
                        </c:if>
                    </div>
                </section>
            </main>
        </div>

        <jsp:include page="components/footer.jsp"/>

    </body>
</html>
