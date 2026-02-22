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

/* ===========================
 AUDIO SETUP
 =========================== */

var audio = document.getElementById("audioPlayer");
var progressFill = document.getElementById("progressFill");
var progressThumb = document.getElementById("progressThumb");
var currentTimeEl = document.getElementById("currentTime");
var durationEl = document.getElementById("duration");
var playBtn = document.getElementById("playBtn");

var playlist = [
    {src: "assets/music/song1.mp3"},
    {src: "assets/music/song2.mp3"}
];

var currentIndex = 0;
var volumeLevel = 70; // 0-100

/* ===========================
 LOAD SONG
 =========================== */

function loadSong(index) {
    audio.src = playlist[index].src;
    audio.load();
}

/* ===========================
 PLAY / PAUSE
 =========================== */

function togglePlay() {
    if (audio.paused) {
        audio.play();
        playBtn.innerHTML = "⏸";
    } else {
        audio.pause();
        playBtn.innerHTML = "▶";
    }
}

function prevSong() {
    currentIndex = (currentIndex - 1 + playlist.length) % playlist.length;
    loadSong(currentIndex);
    audio.play();
}

function nextSong() {
    currentIndex = (currentIndex + 1) % playlist.length;
    loadSong(currentIndex);
    audio.play();
}

/* ===========================
 PROGRESS BAR
 =========================== */

audio.addEventListener("timeupdate", function () {
    if (!audio.duration)
        return;

    var percent = (audio.currentTime / audio.duration) * 100;
    progressFill.style.width = percent + "%";
    progressThumb.style.left = percent + "%";

    currentTimeEl.innerText = formatTime(audio.currentTime);
});

audio.addEventListener("loadedmetadata", function () {
    durationEl.innerText = formatTime(audio.duration);
});

function seek(e) {
    var rect = e.currentTarget.getBoundingClientRect();
    var percent = (e.clientX - rect.left) / rect.width;
    audio.currentTime = percent * audio.duration;
}

function formatTime(time) {
    var minutes = Math.floor(time / 60);
    var seconds = Math.floor(time % 60);
    if (seconds < 10)
        seconds = "0" + seconds;
    return minutes + ":" + seconds;
}

/* ===========================
 VOLUME POPUP
 =========================== */

function toggleVolume() {
    document.getElementById("volumePopup")
            .classList.toggle("active");
}

window.addEventListener("click", function (e) {
    var wrapper = document.querySelector(".volume-wrapper");
    if (wrapper && !wrapper.contains(e.target)) {
        document.getElementById("volumePopup")
                .classList.remove("active");
    }
});

/* ===========================
 VOLUME CONTROL
 =========================== */

function setVolume(percent) {
    volumeLevel = Math.max(0, Math.min(100, percent));
    audio.volume = volumeLevel / 100;
    updateVolumeIcon();
}

function updateVolumeIcon() {

    var w1 = document.querySelector(".w1");
    var w2 = document.querySelector(".w2");
    var w3 = document.querySelector(".w3");
    var muteLine = document.getElementById("muteLine");

    if (!w1 || !w2 || !w3 || !muteLine)
        return;

    // reset
    w1.style.opacity = 0;
    w2.style.opacity = 0;
    w3.style.opacity = 0;
    muteLine.style.opacity = 0;

    if (audio.volume === 0) {
        muteLine.style.opacity = 1;
    } else if (audio.volume <= 0.33) {
        w1.style.opacity = 1;
    } else if (audio.volume <= 0.66) {
        w1.style.opacity = 1;
        w2.style.opacity = 1;
    } else {
        w1.style.opacity = 1;
        w2.style.opacity = 1;
        w3.style.opacity = 1;
    }
}

/* ===========================
 CLICK TRACK VOLUME
 =========================== */

var volumeTrack = document.querySelector(".volume-track");

if (volumeTrack) {
    volumeTrack.addEventListener("click", function (e) {
        var rect = this.getBoundingClientRect();
        var percent = (rect.bottom - e.clientY) / rect.height;
        setVolume(percent * 100);
    });
}

/* ===========================
 CLICK ICON MUTE
 =========================== */

document.getElementById("volumeIcon")
        .addEventListener("click", function () {

            if (audio.volume > 0) {
                audio.dataset.lastVolume = audio.volume;
                setVolume(0);
            } else {
                var last = audio.dataset.lastVolume
                        ? audio.dataset.lastVolume * 100
                        : 70;
                setVolume(last);
            }
        });

/* ===========================
 INIT
 =========================== */

loadSong(currentIndex);
setVolume(volumeLevel);

