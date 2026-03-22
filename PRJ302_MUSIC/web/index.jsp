<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Home</title>
        <link rel="stylesheet" href="assets/css/style.css"/>
        <link rel="stylesheet" href="assets/css/search.css"/>
    </head>
    <body data-logged-in="${not empty sessionScope.user}" data-context="${pageContext.request.contextPath}">
        
        <jsp:include page="components/header.jsp"/>

        <div class="app">
            <aside class="sidebar">
                <jsp:include page="components/navbar.jsp"/>
            </aside>

            <main class="main">
                <div id="mainContent">

                    <div id="page-home" class="page">
                        <jsp:include page="components/home-content.jsp"/>
                    </div>

                    <div id="page-for-you" class="page">
                        <jsp:include page="components/for-you-content.jsp"/>
                    </div>

                    <div id="page-profile" class="page">
                        <jsp:include page="components/profile-content.jsp"/>
                    </div>

                    <div id="page-favorite" class="page">
                        <jsp:include page="components/favorite-content.jsp"/>
                    </div>

                    <div id="page-recent" class="page">
                        <jsp:include page="components/recent-content.jsp"/>
                    </div>

                    <div id="page-vip" class="page">
                        <jsp:include page="components/vip-content.jsp"/>
                    </div>

                    <div id="page-search" class="page">
                        <jsp:include page="components/search-content.jsp"/>
                    </div>

                    <div id="page-playlist" class="page">
                        <jsp:include page="components/playlist-content.jsp"/>
                    </div>

                    <div id="page-album" class="page">
                        <jsp:include page="components/album-content.jsp"/>
                    </div>

                    <div id="page-artist" class="page">
                        <jsp:include page="components/artist-content.jsp"/>
                    </div>

                    <div id="page-home-artist-more" class="page">
                        <jsp:include page="components/home-artist-more-content.jsp"/>
                    </div>
     

                    <div id="page-more" class="page">
                    </div>
                </div>
            </main>

        </div>

        <jsp:include page="components/footer.jsp"/>
        <jsp:include page="components/login-modal.jsp"/>
        <jsp:include page="components/forgot-modal.jsp"/>
        <jsp:include page="components/create-account-modal.jsp"/>
        <jsp:include page="components/topic-modal.jsp"/>
        <jsp:include page="components/playlist-modal.jsp"/>
        <jsp:include page="components/playlist-addsong-modal.jsp"/>
        <jsp:include page="components/edit-playlist-modal.jsp"/>

        <!--silder-->
        <script src="assets/js/silder.js"></script>
        <script src="assets/js/login.js"></script>
        <script src="assets/js/forgot.js"></script>
        <script src="assets/js/app.js"></script>
        <script src="assets/js/player.js"></script>
        <script src="assets/js/topic-modal.js"></script>
        <script src="assets/js/playlist.js"></script>
        <script src="assets/js/footer.js"></script>
        <script src="assets/js/recent.js"></script>
        <script src="assets/js/album.js"></script>
        <script src="assets/js/artist.js"></script>
        <script src="assets/js/home.js"></script>
        <script src="assets/js/create-account.js"></script>

        <c:if test="${not empty message}">
            <!--auto mo lai modal neu co loi-->
            <script>
                document.addEventListener("DOMContentLoaded", function () {
                    openLogin();
                });
            </script>
        </c:if>

        <c:if test="${not empty error || not empty msg}">
            <!--auto mo lai modal neu co loi-->
            <script>
                document.addEventListener("DOMContentLoaded", function () {
                    openRegister();
                });
            </script>
        </c:if>

        <c:if test="${not empty errorEmail || not empty msgEmail}">
            <!--auto mo lai modal neu co loi-->
            <script>
                document.addEventListener("DOMContentLoaded", function () {
                    openForgot();
                });
            </script>
        </c:if>
    </body>
</html>
