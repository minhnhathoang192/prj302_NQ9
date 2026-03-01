/* =========================
 SIMPLE SPA NAVIGATION
 ========================= */

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

function handleProfileClick(page, el) {

    var isLoggedIn = document.body.getAttribute("data-logged-in");

    if (isLoggedIn === "true") {
        showPage(page, el);
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

    fetch('MainController?action=getRandomPlaylist')
            .then(res => res.json())
            .then(list => {

                console.log("Playlist:", list);

                if (!list || list.length === 0) {
                    alert("Không có bài hát!");
                    return;
                }

                let contextPath = document.body.dataset.context;

                playlist = list.map(song => ({
                        audioURL: contextPath + "/StreamServlet?type=audio&file=" + song.audioURL,
                        title: song.title,
                        coverURL: contextPath + "/StreamServlet?type=cover&file=" + song.coverImage
                    }));

                currentIndex = 0;

                let s = playlist[currentIndex];

                document.getElementById("fyTitle").innerText = s.title;
                document.getElementById("fyCover").src = s.coverURL;
                document.getElementById("fyBgCover").src = s.coverURL;

                // ✅ PLAY LUÔN (QUAN TRỌNG)
                playSong(s.audioURL, s.title, s.coverURL);

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

const topicImages = {
    1: "chillout.jpg",
    13: "tiktok.jpg",
    6: "nhactre.jpg",
    5: "nhachoa.jpg",
    4: "cafesang.jpg",
    9: "remix.jpg",
    2: "bolero.jpg",
    8: "rapviet.jpg",
    11: "vuinhon.jpg",
    3: "buon.jpg",
    7: "pop.jpg",
    10: "tinhyeu.jpg"
};

document.querySelectorAll(".topic-card").forEach(card => {
    const id = card.getAttribute("data-topic-id");
    const file = topicImages[id];

    if (file) {
        const url = document.body.dataset.context + 
            "/StreamServlet?type=cover&file=" + file;

        card.style.backgroundImage = `url('${url}')`;
        card.style.backgroundSize = "cover";
        card.style.backgroundPosition = "center";
    }
});