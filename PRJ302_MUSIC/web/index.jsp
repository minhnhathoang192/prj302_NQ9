<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Home</title>
        <link rel="stylesheet" href="assets/css/style.css"/>
        <c:if test="${activePage == 'search'}">
            <link rel="stylesheet" href="assets/css/search.css"/>
        </c:if>
    </head>
    <body data-logged-in="${not empty sessionScope.user}" data-context="${pageContext.request.contextPath}">
        <jsp:include page="components/header.jsp"/>

        <div class="app">
            <aside class="sidebar">
                <jsp:include page="components/navbar.jsp"/>
            </aside>

            <main class="main">
                <div id="mainContent">

                    <div id="page-home" class="page
                         ${activePage ==null || activePage== 'home' ? 'active' : ''}">
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
                    
                    <div id="page-search" class="page 
                         ${activePage == 'search' ? 'active' : ''}">
                        <jsp:include page="components/search-content.jsp"/>
                    </div>


                </div>
            </main>

        </div>

        <jsp:include page="components/footer.jsp"/>
        <jsp:include page="components/login-modal.jsp"/>
        <jsp:include page="components/forgot-modal.jsp"/>
        <script src="assets/js/app.js"></script>

        <c:if test="${not empty message}">
            <script>
                document.addEventListener("DOMContentLoaded", function () {
                    openLogin();
                });
            </script>
        </c:if>
            
            <script src="assets/js/player.js"></script>
    </body>
</html>
