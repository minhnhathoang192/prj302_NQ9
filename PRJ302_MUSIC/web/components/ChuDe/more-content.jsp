<%-- 
    Document   : more-content
    Created on : Mar 2, 2026, 2:53:07 AM
    Author     : NQ9
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="more-container">

    <!-- TITLE -->
    <h1 class="more-title">Chủ đề</h1>

    <!-- ===== THỂ LOẠI ===== -->
    <div class="more-section">
        <div class="section-header">
            <h2>Thể loại</h2>
        </div>

        <div class="more-grid">
            <c:forEach var="t" items="${topics}">
                <div class="more-card horizontal"
                     data-topic-id="${t.topicID}"
                     onclick="showTopicPage(this)">

                    <div class="card-text">
                        ${t.topicName}
                    </div>

                    <img src="${pageContext.request.contextPath}/StreamServlet?type=topic&file=${t.coverImage}">
                </div>
            </c:forEach>
        </div>
    </div>

</div>