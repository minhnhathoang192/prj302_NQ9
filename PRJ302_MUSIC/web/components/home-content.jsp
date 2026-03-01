<%-- 
    Document   : home-content
    Created on : Feb 7, 2026, 1:01:12 AM
    Author     : NQ9
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<section class="content">

    <!-- ===== BANNER SLIDER ===== -->
    <section class="banner-slider" id="slider">
        <button class="slide-btn left">‹</button>

        <div class="banner-track">
            <img src="assets/img/banner1.png"/>
            <img src="assets/img/banner2.png"/>
            <img src="assets/img/banner3.png"/>
        </div>

        <button class="slide-btn right">›</button>
    </section>


    <!-- ===== CHỦ ĐỀ ===== -->
    <section class="topic-section">
        <div class="section-header">
            <h2>Chủ đề</h2>
            <span class="more" onclick="showMorePage(this)">Thêm</span>
        </div>

        <div class="topic-grid">

            <div class="topic-card" data-topic-id="1" onclick="showTopicPage(this)">

                <img src="${pageContext.request.contextPath}/StreamServlet?type=topic&file=1772400913421_Chill.png">

                <div class="topic-name">Chill Out</div>
            </div>

            <div class="topic-card" data-topic-id="2" onclick="showTopicPage(this)">

                <img src="${pageContext.request.contextPath}/StreamServlet?type=topic&file=1772400882497_bolero.png">
                <div class="topic-name">Bolero</div>
            </div>

            <div class="topic-card" data-topic-id="3" onclick="showTopicPage(this)">

                <img src="${pageContext.request.contextPath}/StreamServlet?type=topic&file=1772400926220_buon.png">       
                <div class="topic-name">Buồn</div>
            </div>

            <div class="topic-card" data-topic-id="4" onclick="showTopicPage(this)">

                <img src="${pageContext.request.contextPath}/StreamServlet?type=topic&file=1772400940287_cafe.png">            
                <div class="topic-name">Cafe Sáng</div>
            </div>

            <div class="topic-card" data-topic-id="5" onclick="showTopicPage(this)">

                <img src="${pageContext.request.contextPath}/StreamServlet?type=topic&file=1772400952321_nhachoa.png">            
                <div class="topic-name">Nhạc Hoa</div>
            </div>

            <div class="topic-card" data-topic-id="6" onclick="showTopicPage(this)">

                <img src="${pageContext.request.contextPath}/StreamServlet?type=topic&file=1772400965316_nhactre.png">            
                <div class="topic-name">Nhạc Trẻ</div>
            </div>

            <div class="topic-card" data-topic-id="7" onclick="showTopicPage(this)">

                <img src="${pageContext.request.contextPath}/StreamServlet?type=topic&file=1772400978372_Pop.png">            
                <div class="topic-name">Pop</div>
            </div>

            <div class="topic-card" data-topic-id="8" onclick="showTopicPage(this)">

                <img src="${pageContext.request.contextPath}/StreamServlet?type=topic&file=1772400992996_rap.png">            
                <div class="topic-name">Rap Việt</div>
            </div>

            <div class="topic-card" data-topic-id="9" onclick="showTopicPage(this)">
                <img src="${pageContext.request.contextPath}/StreamServlet?type=topic&file=1772401004821_Remix.png">            
                <div class="topic-name">Remix</div>
            </div>

            <div class="topic-card" data-topic-id="10" onclick="showTopicPage(this)">

                <img src="${pageContext.request.contextPath}/StreamServlet?type=topic&file=1772401022531_TinhYeu.png">            
                <div class="topic-name">Tình Yêu</div>
            </div>

            <div class="topic-card" data-topic-id="11" onclick="showTopicPage(this)">

                <img src="${pageContext.request.contextPath}/StreamServlet?type=topic&file=1772401041625_nhacvui.png">            
                <div class="topic-name">Vui Nhộn</div>
            </div>

            <div class="topic-card" data-topic-id="13" onclick="showTopicPage(this)">

                <img src="${pageContext.request.contextPath}/StreamServlet?type=topic&file=1772401053781_tiktok.png">            
                <div class="topic-name">TikTok</div>
            </div>

        </div>
    </section>


    <!-- ===== BẢNG XẾP HẠNG ===== -->
    <section class="ranking-section">
        <div class="section-header">
            <h2>Bảng Xếp Hạng</h2>
            <span class="more">Thêm</span>
        </div>

        <div class="ranking-row">
            <div class="ranking-card">Top 50 Nhạc Việt</div>
            <div class="ranking-card">Top 50 Nhạc Hoa</div>
            <div class="ranking-card">Top 50 YouTube</div>
        </div>
    </section>


    <!-- ===== NGHỆ SĨ THỊNH HÀNH ===== -->
    <section class="artist-section">
        <div class="section-header">
            <h2>Nghệ Sĩ Thịnh Hành</h2>
            <span class="more">Thêm</span>
        </div>

        <div class="artist-row">
            <div class="artist-card">Trọng Nhân</div>
            <div class="artist-card">Tiểu Mỹ</div>
            <div class="artist-card">Maydays</div>
            <div class="artist-card">Dương Domic</div>
            <div class="artist-card">Low G</div>
        </div>
    </section>

</section>
