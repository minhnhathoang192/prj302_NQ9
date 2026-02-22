function openForgot() {
    var loginModal = document.getElementById("loginModal");
    var forgotModal = document.getElementById("forgotModal");

    if (loginModal) {
        loginModal.classList.remove("active");
    }

    if (forgotModal) {
        forgotModal.classList.add("active");
    }
}

function closeForgot() {
    var forgotModal = document.getElementById("forgotModal");

    if (forgotModal) {
        forgotModal.classList.remove("active");
    }
}
