//load home
document.addEventListener("DOMContentLoaded", function () {
loadHome();
});
        function loadHome() {

        showPage("home");
                /* ===== TRENDING ARTIST ===== */
                fetch("MainController?action=getTrendingArtists")
                .then(res => res.json())
                .then(data => {
                renderTrendingArtists(data);
                });
                /* ===== MOST FAVORITE SONG ===== */
                fetch("MainController?action=getMostFavoriteSongs")
                .then(res => res.json())
                .then(data => {
                renderFavoriteSongs(data);
                });
        }

////////////////////////////////////////nhac duoc yeu thich nhat///////////////////
function renderFavoriteSongs(list){

const container = document.getElementById("favoriteSongContainer");
        container.innerHTML = "";
        list.forEach(s => {

        const cover =
                contextPath + "/StreamServlet?type=cover&file=" + s.coverImage;
                const html = `

                    <div class="home-favorite-song-item" data-id="${s.songID}">

                        <div class="home-favorite-song-cover">

                            <img src="${cover}">

                            <div class="home-favorite-play">
                                ▶
                            </div>

                        </div>

                        <div class="home-favorite-song-info">

                            <div class="home-favorite-song-title">
                                ${s.title}
                            </div>

                            <div class="home-favorite-song-likes">
                                ❤ ${s.likes}
                            </div>

                        </div>

                    </div>

`;
                container.insertAdjacentHTML("beforeend", html);
        });
}

document.addEventListener("click", function(e){

const song = e.target.closest(".home-favorite-song-item");
        if (!song) return;
        const songID = song.dataset.id;
        fetch("MainController?action=getSongByID&songID=" + songID)
        .then(res => res.json())
        .then(s => {

        const audio =
                contextPath + "/StreamServlet?type=audio&file=" + s.audioURL;
                const cover =
                contextPath + "/StreamServlet?type=cover&file=" + s.coverImage;
                playSong(audio, s.title, cover, s.songID);
        });
});
///////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////load hien thi danh sach artist
        function renderTrendingArtists(list) {

        const container = document.getElementById("homeTrendingArtistContainer");
                if (!container)
                return;
                container.innerHTML = "";
                if (!list || list.length === 0) {
        container.innerHTML = "<p>Không có nghệ sĩ</p>";
                return;
        }

        list.slice(0, 4).forEach(a => {

        const avatarURL =
                contextPath + "/StreamServlet?type=artist&file=" +
                encodeURIComponent(a.avatarURL || "");
                const songCover =
                contextPath + "/StreamServlet?type=cover&file=" +
                encodeURIComponent(a.latestCover || "default.png");
                const latestSong = a.latestSong || "Chưa có bài hát";
                const html = `
        <div class="home-artist-card" data-id="${a.artistID}">

            <img class="home-artist-bg"
                 src="${avatarURL}">

            <div class="home-artist-overlay">

                <div class="home-artist-name">
                    ${a.artistName}
                </div>
            <div class="home-artist-follow-row">
                <div class="home-artist-followers" >
                    ${a.followers ?? 0} người theo dõi
                </div>

                <button class="home-artist-follow-btn"
                        data-id="${a.artistID}">
                    Theo dõi
                </button>
            </div>

                <div class="home-artist-latest-song"
                    data-song="${a.latestSongID}">

                    <img class="home-artist-song-cover"
                         src="${songCover}">
                    <div>

                        <div class="home-artist-song-title">
                            ${latestSong}
                        </div>

                        <div class="home-artist-song-artist">
                            ${a.artistName}
                        </div>

                    </div>

                </div>

            </div>

        </div>
        `;
                container.insertAdjacentHTML("beforeend", html);
        });
                document.querySelectorAll(".home-artist-follow-btn")
                .forEach(btn => {

                const id = btn.dataset.id;
                        fetch("MainController?action=isFollowingArtist&artistID=" + id)
                        .then(res => res.json())
                        .then(data => {

                        if (data.following) {
                        btn.innerText = "Đang theo dõi";
                                btn.classList.add("following");
                        }

                        });
                });
        }

document.addEventListener("click", function (e) {

/* ===== FOLLOW BUTTON ===== */
const followBtn = e.target.closest(".home-artist-follow-btn");
        if (followBtn) {

e.stopPropagation();
        const artistID = followBtn.dataset.id;
        fetch("MainController?action=toggleFollowArtist&artistID=" + artistID)
        .then(res => res.json())
        .then(data => {

        if (data.error === "login") {
        openLogin();
                return;
        }

        if (data.following) {
        followBtn.innerText = "Đang theo dõi";
                followBtn.classList.add("following");
        } else {
        followBtn.innerText = "Theo dõi";
                followBtn.classList.remove("following");
        }

        });
        return;
        }


/* ===== PLAY SONG ===== */
const song = e.target.closest(".home-artist-latest-song, .home-artist-more-song");
        if (song) {

e.stopPropagation();
        const songID = song.dataset.song;
        if (!songID)
        return;
        fetch("MainController?action=getSongByID&songID=" + songID)
        .then(res => res.json())
        .then(s => {

        const audioURL =
                contextPath + "/StreamServlet?type=audio&file=" + s.audioURL;
                const coverURL =
                contextPath + "/StreamServlet?type=cover&file=" + s.coverImage;
                playSong(
                        audioURL,
                        s.title,
                        coverURL,
                        s.songID
                        );
        });
        return;
        }


/* ===== OPEN ARTIST PAGE ===== */
const card = e.target.closest(".home-artist-card, .home-artist-more-row");
        if (!card)
        return;
        /* nếu click vào song thì KHÔNG mở artist page */
        if (e.target.closest(".home-artist-latest-song, .home-artist-more-song")) {
return;
        }

const artistID = card.dataset.id;
        openArtistPage(artistID);
});
//more
        function loadArtistRanking() {

        showPage("home-artist-more");
                fetch("MainController?action=getTrendingArtists")
                .then(res => res.json())
                .then(data => {
                renderArtistRanking(data);
                });
        }

function renderArtistRanking(list) {

const container = document.getElementById("homeArtistMoreContainer");
        container.innerHTML = "";
        /* banner */

        /* banner */

        if (list.length > 0) {

const banner =
        contextPath + "/StreamServlet?type=artist&file=" +
        encodeURIComponent(list[0].avatarURL);
        document.querySelector(".home-artist-more-banner").style.backgroundImage
        = `url('${banner}')`;
        }

list.forEach((a, index) => {

const avatar =
        contextPath + "/StreamServlet?type=artist&file=" + a.avatarURL;
        const songCover =
        contextPath + "/StreamServlet?type=cover&file=" + a.latestCover;
        const latestSong = a.latestSong || "Chưa có bài hát";
        const html = `

<div class="home-artist-more-row" data-id="${a.artistID}">

    <div class="home-artist-more-left">

        <div class="home-artist-more-rank">
            #${index + 1}
        </div>

        <img class="home-artist-more-avatar"
             src="${avatar}">

        <div class="home-artist-more-info">

            <div class="home-artist-more-name">
                ${a.artistName}
            </div>

            <div class="home-artist-more-follow">
                ${a.followers} followers
            </div>

        </div>

    </div>


    <div class="home-artist-more-song"
         data-song="${a.latestSongID}">

        <img class="home-artist-more-song-cover"
             src="${songCover}">

        <div>

            <div class="home-artist-more-song-title">
                ${latestSong}
            </div>

            <div class="home-artist-more-song-artist">
                ${a.artistName}
            </div>

        </div>

    </div>

</div>

`;
        container.insertAdjacentHTML("beforeend", html);
        });
}

document.addEventListener("click", function (e) {

const card = e.target.closest(".home-artist-more-row");
        if (!card)
        return;
        openArtistPage(card.dataset.id);
})
        ;
////////////////////////////////////////////////////////////////////////////////////////////