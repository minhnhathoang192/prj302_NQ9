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
            <img src="assets/img/banner7.png"/>
            <img src="assets/img/banner6.png"/>
            <img src="assets/img/banner5.png"/>
            <img src="assets/img/banner4.jpg"/>
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
            
<!--            data-topic-id="1" luu ID topic 
                onclick="showTopicPage(this)" - click goi JS
                User click card - goi JS - truyen element do-->
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





    <!-- ===== NGHỆ SĨ THỊNH HÀNH ===== -->
    <section class="home-artist-section">

        <div class="home-artist-header">

            <h2 class="section-header">
                Nghệ Sĩ Thịnh Hành
            </h2>

            <span class="home-artist-more" onclick="loadArtistRanking()">
                Thêm
            </span>

        </div>


        <div class="home-artist-row" id="homeTrendingArtistContainer">

            <!-- JS render artists -->

        </div>

    </section>


    <section class="favorite-song-section">

        <div class="section-header">
            <h2>Nhạc được yêu thích nhất</h2>
        </div>

        <div class="favorite-song-grid" id="favoriteSongContainer">

            <!-- JS render -->

        </div>

    </section>

    <section class="home-footer">

        <div class="footer-container">

            <!-- LOGO + ABOUT -->
            <div class="footer-col footer-about">
                <h2 class="footer-logo">QNQ Music</h2>

                <p>
                    Nền tảng nghe nhạc trực tuyến được phát triển
                    trong môn <b>PRJ302</b>.  
                    Người dùng có thể khám phá âm nhạc, tạo playlist,
                    theo dõi nghệ sĩ và thưởng thức âm nhạc cá nhân hóa.
                </p>
            </div>


            <!-- TEAM -->
            <div class="footer-col">

                <h3>Nhóm Phát Triển</h3>

                <ul>
                    <li>Hoàng Minh Nhật — Backend & Database</li>
                    <li>Quốc — DAO / DTO Development</li>
                    <li>Quý — UI Design & Music Content</li>
                </ul>

            </div>


            <!-- TECHNOLOGY -->
            <div class="footer-col">

                <h3>Công Nghệ</h3>

                <ul>
                    <li>Architecture: MVC2 with AJAX</li>
                    <li>Backend: Java Servlet</li>
                    <li>Frontend: JSP + JavaScript</li>
                    <li>Database: SQL Sever</li>
                </ul>

            </div>


            <!-- CONTACT -->
            <div class="footer-col">

                <h3>Liên Hệ</h3>

                <ul>
                    <li>Email: qnqmusic@gmail.com</li>
                    <li>Project: PRJ302 Music</li>
                    <li>FPT UNIVERSITY</li>
                </ul>

            </div>

        </div>


        <div class="footer-bottom">

            <div class="footer-line"></div>

            <p>
                © 2026 QNQ Music — Music Streaming Platform for Educational Purposes
            </p>

        </div>

    </section>

</section>
