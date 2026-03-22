<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div id="topicModal" class="tm-modal">

    <div class="tm-overlay"></div>

    <div class="tm-container">
        
        
        <div class="tm-close" onclick="skipTopic()">✕</div>

        <!-- HEADER -->
        <div class="tm-header">
            <h2 class="tm-title">🎧 Hôm nay bạn muốn nghe gì?</h2>
            <p class="tm-subtitle">Chọn một hoặc nhiều chủ đề để đề xuất nhạc phù hợp</p>
        </div>

        <!-- LIST TOPIC -->
        <div class="tm-grid">
            
            <!--sever render topic-->
            <c:forEach var="t" items="${sessionScope.topics}">
                <!--user chon topic-->
                <div class="tm-card"
                     data-id="${t.topicID}"
                     onclick="toggleTopic(this)">

                    <!-- IMAGE -->
                    <div class="tm-image-wrapper">
                        <img class="tm-img"
                             src="${pageContext.request.contextPath}/StreamServlet?type=topic&file=${t.coverImage}" />
                    </div>

                    <!-- NAME -->
                    <div class="tm-info">
                        <span class="tm-name">${t.topicName}</span>
                    </div>

                    <!-- CHECK -->
                    <div class="tm-check">✓</div>

                </div>
            </c:forEach>

        </div>

        <!-- ACTION -->
        
        <div class="tm-actions">
            <button class="tm-submit" onclick="submitTopics()">Tiếp tục →</button>
        </div>

    </div>

</div>