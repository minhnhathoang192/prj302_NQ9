/* =========================
 SIMPLE SPA NAVIGATION
 ========================= */
const contextPath = document.body.dataset.context;

function showPage(page, el, addToHistory) {

    // mặc định là có push history
    if (addToHistory === undefined) {
        addToHistory = true;
    }

    var currentHash = window.location.hash.replace("#", "");

    // Nếu khác page hiện tại thì mới đổi hash
    if (addToHistory && currentHash !== page) {
        window.location.hash = page;
    }

    // Ẩn tất cả page
    var pages = document.querySelectorAll(".page");
    for (var i = 0; i < pages.length; i++) {
        pages[i].classList.remove("active");
    }

    // Hiện page cần
    var target = document.getElementById("page-" + page);
    if (target) {
        target.classList.add("active");
    }

    // Bỏ active menu
    var navItems = document.querySelectorAll(".nav-item");
    for (var j = 0; j < navItems.length; j++) {
        navItems[j].classList.remove("active");
    }

    if (el) {
        el.classList.add("active");
    }
}


/* =========================
 HASH CHANGE LISTENER
 ========================= */

window.onhashchange = function () {

    var page = window.location.hash.replace("#", "");

    if (!page) {
        page = "home";
    }

    showPage(page, null, false);
};


/* =========================
 FIRST LOAD
 ========================= */

window.onload = function () {

    var page = window.location.hash.replace("#", "");

    if (!page) {
        page = "home";
    }

    showPage(page, null, false);
};


/* =========================
 RECENT TAB SWITCH
 ========================= */

function switchRecent(el, type) {

    var tabs = document.querySelectorAll(".tab");
    for (var i = 0; i < tabs.length; i++) {
        tabs[i].classList.remove("active");
    }

    el.classList.add("active");

    var container = document.getElementById("recentList");

    container.innerHTML =
            '<div class="recent-empty">' +
            '<div class="empty-icon">📂</div>' +
            '<h3>Chưa có ' + type + '</h3>' +
            '<p>Khi bạn sử dụng ' + type + ', nó sẽ xuất hiện ở đây</p>' +
            '</div>';
}


/* =========================
 HEADER NAV BUTTONS
 ========================= */

function goBack() {
    window.history.back();
}

function goForward() {
    window.history.forward();
}
function toggleSettings() {
    var menu = document.getElementById("settingsMenu");
    menu.classList.toggle("active");
}

// click ra ngoài thì đóng
document.addEventListener("click", function (e) {
    var wrapper = document.querySelector(".settings-wrapper");
    var menu = document.getElementById("settingsMenu");

    if (wrapper && !wrapper.contains(e.target)) {
        menu.classList.remove("active");
    }
});


function loginSuccess(username) {

    // Lưu trạng thái
    localStorage.setItem("loggedIn", "true");
    localStorage.setItem("username", username);

    // Ẩn nút login
    document.getElementById("loginBtn").style.display = "none";

    // Hiện avatar
    var avatar = document.getElementById("userAvatar");
    avatar.style.display = "flex";

    // Lấy chữ cái đầu
    avatar.innerText = username.charAt(0).toUpperCase();
}

window.addEventListener("load", function () {

    var loggedIn = localStorage.getItem("loggedIn");

    if (loggedIn === "true") {

        var username = localStorage.getItem("username");

        document.getElementById("loginBtn").style.display = "none";

        var avatar = document.getElementById("userAvatar");
        avatar.style.display = "flex";
        avatar.innerText = username.charAt(0).toUpperCase();
    }
});

function loadUserPlaylists() {

    fetch("MainController?action=loadProfile")
            .then(res => res.json())
            .then(data => {

                const grid = document.getElementById("playlistGrid");
                const empty = document.getElementById("playlistEmpty");

                grid.innerHTML = "";

                if (data.length === 0) {
                    grid.innerHTML = "<p>Bạn chưa có playlist nào</p>";
                    return;
                }

                data.forEach(p => {

                    grid.insertAdjacentHTML("beforeend", `
                    <div class="playlist-card"
                         data-id="${p.playListID}"
                         data-name="${p.playListName}">

                        <div class="playlist-cover">
                            <img src="assets/img/default-playlist.png">
                        </div>

                        <div class="playlist-name">
                            ${p.playListName}
                        </div>

                        <div class="playlist-count">
                            0 bài hát
                        </div>

                    </div>
                `);

                });

            });

}

function handleProfileClick(page, el) {

    var isLoggedIn = document.body.getAttribute("data-logged-in");

    if (isLoggedIn === "true") {

        showPage(page, el);

        if (page === "profile") {
            loadUserPlaylists();
        }

        if (page === "favorite") {
            loadFavoriteSongs();
        }

        if (page === "recent") {
            loadRecentSongs();
        }

    } else {
        openLogin();
    }
}

function toggleUserMenu() {
    var menu = document.getElementById("userDropdown");
    menu.classList.toggle("active");
}

function loadForYou(el) {

    // đổi UI tab
    showPage('for-you', el);

    let savedTopics = sessionStorage.getItem("userTopics");

    if (savedTopics) {
        let topicIDs = JSON.parse(savedTopics);

        loadPlaylistByTopics(topicIDs);
        return;
    }

    if (currentSong) {
        updateForYouUI();
        return;
    }


    fetch('MainController?action=getRandomPlaylist')
            .then(res => res.json())
            .then(list => {

                if (!list || list.length === 0) {
                    alert("Không có bài hát!");
                    return;
                }

                buildAndLoadPlayer(list);

            })
            .catch(err => {
                console.error("Lỗi loadForYou:", err);
            });
}


//Topic-Page

function showTopicPage(el) {

    const topicID = el.getAttribute("data-topic-id");

    console.log("CLICK topicID:", topicID); // 👈 check

    if (!topicID) {
        alert("Thiếu topicID!");
        return;
    }

    const page = "topic-" + topicID;

    showPage(page, el);

    let container = document.getElementById("page-" + page);

    if (!container) {
        container = document.createElement("div");
        container.id = "page-" + page;
        container.className = "page";
        document.getElementById("mainContent").appendChild(container);
    }

    const context = document.body.dataset.context;

    container.innerHTML = "Loading...";

    fetch(context + "/MainController?action=loadTopic&topicID=" + topicID)
            .then(res => res.text())
            .then(html => {
                container.innerHTML = html;
            });
}

function showMorePage(el) {
    showPage('more', el);

    const container = document.getElementById("page-more");
    const context = document.body.dataset.context;

    container.innerHTML = "Loading...";

    fetch(context + "/MainController?action=loadMore")
            .then(res => res.text())
            .then(html => {
                container.innerHTML = html;
            });
}

//search song
document.getElementById("searchForm").addEventListener("submit", function (e) {

    e.preventDefault(); // 🚨 chặn reload

    let keyword = document.querySelector("input[name='keyword']").value.trim();

    if (!keyword)
        return;

    fetch("MainController?action=searchAjax&keyword=" + encodeURIComponent(keyword))
            .then(res => res.json())
            .then(data => {

                renderSongs(data.songs);
                renderArtists(data.artists);
                renderAlbums(data.albums);
                renderPlaylists(data.playlists);

                showPage("search");

            })
            .catch(err => console.error("Search error:", err));
});

function renderSongs(list) {

    const containerAll = document.getElementById("searchResultContainer");
    const containerSong = document.getElementById("searchSongOnly");
    if (!containerAll || !containerSong)
        return;
    containerAll.innerHTML = "";
    containerSong.innerHTML = "";
    if (!list || list.length === 0) {
        containerAll.innerHTML = "<p>Không có bài hát</p>";
        containerSong.innerHTML = "<p>Không có bài hát</p>";
        return;
    }

// ===== TAB ALL (GIỚI HẠN 6) =====
    const limitedSongs = list.slice(0, 6);
    limitedSongs.forEach((s, index) => {

        const html = `
        <div class="search-song-card" data-id="${s.songID}">
            <div class="search-song-cover">
                <img src="StreamServlet?type=cover&file=${encodeURIComponent(s.coverImage)}">
            </div>

            <div class="search-song-meta">
                <div class="search-song-title">${s.title}</div>
                <div class="search-song-artist">${s.artistName ?? ""}</div>
            </div>
        </div>
        `;
        const wrapper = document.createElement("div");
        wrapper.innerHTML = html;
        const node = wrapper.firstElementChild;
        node.onclick = () => {

            playlist = list.map(song => ({
                    songID: song.songID,
                    audioURL: "StreamServlet?type=audio&file=" + encodeURIComponent(song.audioURL),
                    title: song.title,
                    coverURL: "StreamServlet?type=cover&file=" + encodeURIComponent(song.coverImage)
                }));
            currentIndex = index;
            const current = playlist[currentIndex];
            playSong(
                    current.audioURL,
                    current.title,
                    current.coverURL,
                    current.songID
                    );
        };
        containerAll.appendChild(node);
    });
    // ===== TAB SONG (HIỆN TẤT CẢ) =====
    list.forEach((s, index) => {

        const html = `
        <div class="search-song-card" data-id="${s.songID}">
            <div class="search-song-cover">
                <img src="StreamServlet?type=cover&file=${encodeURIComponent(s.coverImage)}">
            </div>

            <div class="search-song-meta">
                <div class="search-song-title">${s.title}</div>
                <div class="search-song-artist">${s.artistName ?? ""}</div>
            </div>
        </div>
        `;
        const wrapper = document.createElement("div");
        wrapper.innerHTML = html;
        const node = wrapper.firstElementChild;
        node.onclick = () => {

            playlist = list.map(song => ({
                    songID: song.songID,
                    audioURL: "StreamServlet?type=audio&file=" + encodeURIComponent(song.audioURL),
                    title: song.title,
                    coverURL: "StreamServlet?type=cover&file=" + encodeURIComponent(song.coverImage)
                }));
            currentIndex = index;
            const current = playlist[currentIndex];
            playSong(
                    current.audioURL,
                    current.title,
                    current.coverURL,
                    current.songID
                    );
        };
        containerSong.appendChild(node);
    });
}

function renderArtists(list) {

    const containerAll = document.getElementById("searchArtistContainer");
    const containerArtist = document.getElementById("searchArtistOnly");
    if (!containerAll || !containerArtist)
        return;
    containerAll.innerHTML = "";
    containerArtist.innerHTML = "";
    if (!list || list.length === 0) {
        containerAll.innerHTML = "<p>Không có nghệ sĩ</p>";
        containerArtist.innerHTML = "<p>Không có nghệ sĩ</p>";
        return;
    }

    list.forEach((a, index) => {

        const avatarURL =
                contextPath + "/StreamServlet?type=artist&file=" +
                encodeURIComponent(a.avatarURL || "");
        const html = `
        <div class="artist-card" data-id="${a.artistID}">

            <div class="artist-avatar">
                <img src="${avatarURL}">
            </div>

            <div class="artist-name">
                ${a.artistName}
            </div>

        </div>
        `;
        /* TAB ALL → chỉ 6 nghệ sĩ */
        if (index < 6) {
            containerAll.insertAdjacentHTML("beforeend", html);
        }

        /* TAB ARTIST → tất cả */
        containerArtist.insertAdjacentHTML("beforeend", html);
    });
}

function renderAlbums(list) {

const containerAll = document.getElementById("searchAlbumContainer");
        const containerAlbum = document.getElementById("searchAlbumOnly");
        if (!containerAll || !containerAlbum) return;
        containerAll.innerHTML = "";
        containerAlbum.innerHTML = "";
        if (!list || list.length === 0) {
containerAll.innerHTML = "<p>Không có album</p>";
        containerAlbum.innerHTML = "<p>Không có album</p>";
        return;
        }

list.forEach((al, index) => {

const coverURL =
        contextPath + "/StreamServlet?type=album&file=" +
        encodeURIComponent(al.coverImage || "");
        const html = `
        <div class="album-card" data-id="${al.albumID}">

            <div class="album-cover">
                <img src="${coverURL}">
            </div>

            <div class="album-title">
                ${al.albumName}
            </div>

        </div>
        `;
        /* TAB ALL → 6 album */
        if (index < 6){
containerAll.insertAdjacentHTML("beforeend", html);
        }

/* TAB ALBUM → tất cả */
containerAlbum.insertAdjacentHTML("beforeend", html);
        });
}

function renderPlaylists(list) {

const containerAll = document.getElementById("searchPlaylistContainer");
        const containerOnly = document.getElementById("searchPlaylistOnly");
        if (!containerAll) return;
        containerAll.innerHTML = "";
        if (containerOnly) containerOnly.innerHTML = "";
        if (!list || list.length === 0) {
containerAll.innerHTML = "<p>Không có playlist</p>";
        if (containerOnly) containerOnly.innerHTML = "<p>Không có playlist</p>";
        return;
        }

list.forEach((p, index) => {

const coverURL =
        contextPath + "/StreamServlet?type=playlist&file=" +
        encodeURIComponent(p.coverImage || "");
        const html = `
        <div class="playlist-card" data-id="${p.playListID}">

            <div class="playlist-cover">
                <img src="${coverURL}">
            </div>

            <div class="playlist-title">
                ${p.playListName}
            </div>

        </div>
        `;
        /* TAB ALL → chỉ 6 playlist */
        if (index < 6){
containerAll.insertAdjacentHTML("beforeend", html);
        }

/* TAB PLAYLIST → tất cả */
if (containerOnly) {
containerOnly.insertAdjacentHTML("beforeend", html);
        }

});
}


