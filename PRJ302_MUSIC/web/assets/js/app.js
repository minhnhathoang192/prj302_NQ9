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
