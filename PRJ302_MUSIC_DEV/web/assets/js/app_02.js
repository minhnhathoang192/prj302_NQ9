/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */


/* =========================
 INIT SAU KHI DOM LOAD
 ========================= */
document.addEventListener("DOMContentLoaded", function () {

    /* =========================
     SEARCH DROPDOWN
     ========================= */
    var input = document.querySelector(".search-box input");

    if (input) {
        input.addEventListener("focus", function () {
            var dropdown = document.getElementById("searchDropdown");
            if (dropdown) {
                dropdown.classList.add("show");
            }
        });
    }

    document.addEventListener("click", function (e) {
        var searchBox = document.querySelector(".search-box");
        var dropdown = document.getElementById("searchDropdown");

        if (!dropdown || !searchBox) return;

        if (!searchBox.contains(e.target)) {
            dropdown.classList.remove("show");
        }
    });

});


/* =========================
 SPA NAVIGATION
 ========================= */

function showPage(page, el, addToHistory) {

    if (addToHistory === undefined) {
        addToHistory = true;
    }

    var currentHash = window.location.hash.replace("#", "");

    if (addToHistory && currentHash !== page) {
        window.location.hash = page;
    }

    var pages = document.querySelectorAll(".page");
    for (var i = 0; i < pages.length; i++) {
        pages[i].classList.remove("active");
    }

    var target = document.getElementById("page-" + page);
    if (target) {
        target.classList.add("active");
    }

    var navItems = document.querySelectorAll(".nav-item");
    for (var j = 0; j < navItems.length; j++) {
        navItems[j].classList.remove("active");
    }

    if (el) {
        el.classList.add("active");
    }
}

window.onhashchange = function () {
    var page = window.location.hash.replace("#", "") || "home";
    showPage(page, null, false);
};

window.onload = function () {
    var page = window.location.hash.replace("#", "") || "home";
    showPage(page, null, false);
};


/* =========================
 SETTINGS MENU
 ========================= */

function toggleSettings() {
    var menu = document.getElementById("settingsMenu");
    if (menu) {
        menu.classList.toggle("active");
    }
}

document.addEventListener("click", function (e) {
    var wrapper = document.querySelector(".settings-wrapper");
    var menu = document.getElementById("settingsMenu");

    if (wrapper && !wrapper.contains(e.target)) {
        if (menu) {
            menu.classList.remove("active");
        }
    }
});


/* =========================
 USER MENU
 ========================= */

function toggleUserMenu() {
    var menu = document.getElementById("userDropdown");
    if (menu) {
        menu.classList.toggle("active");
    }
}


/* =========================
 AUDIO (KHÔNG TRÙNG BIẾN)
 ========================= */

(function () {

    var audio = document.getElementById("audioPlayer");
    if (!audio) return;

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
    var volumeLevel = 70;

    function loadSong(index) {
        audio.src = playlist[index].src;
        audio.load();
    }

    window.togglePlay = function () {
        if (audio.paused) {
            audio.play();
            if (playBtn) playBtn.innerHTML = "⏸";
        } else {
            audio.pause();
            if (playBtn) playBtn.innerHTML = "▶";
        }
    };

    window.prevSong = function () {
        currentIndex = (currentIndex - 1 + playlist.length) % playlist.length;
        loadSong(currentIndex);
        audio.play();
    };

    window.nextSong = function () {
        currentIndex = (currentIndex + 1) % playlist.length;
        loadSong(currentIndex);
        audio.play();
    };

    audio.addEventListener("timeupdate", function () {
        if (!audio.duration) return;

        var percent = (audio.currentTime / audio.duration) * 100;

        if (progressFill) progressFill.style.width = percent + "%";
        if (progressThumb) progressThumb.style.left = percent + "%";
        if (currentTimeEl) currentTimeEl.innerText = formatTime(audio.currentTime);
    });

    audio.addEventListener("loadedmetadata", function () {
        if (durationEl) durationEl.innerText = formatTime(audio.duration);
    });

    function formatTime(time) {
        var m = Math.floor(time / 60);
        var s = Math.floor(time % 60);
        if (s < 10) s = "0" + s;
        return m + ":" + s;
    }

    function setVolume(percent) {
        volumeLevel = Math.max(0, Math.min(100, percent));
        audio.volume = volumeLevel / 100;
    }

    var volumeIcon = document.getElementById("volumeIcon");

    if (volumeIcon) {
        volumeIcon.addEventListener("click", function () {
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
    }

    loadSong(currentIndex);
    setVolume(volumeLevel);

})();