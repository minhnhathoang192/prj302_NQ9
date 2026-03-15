function openRegister() {
    document.getElementById('registerModal').classList.add('active');
}

function closeRegister() {
    document.getElementById('registerModal').classList.remove('active');
}

function validateRegister() {

    const password = document.getElementById("password").value;
    const confirm = document.getElementById("confirmPassword").value;
    const error = document.getElementById("registerError");

    if (password !== confirm) {
        error.innerText = "Mật khẩu xác nhận không khớp!";
        return false;
    }

    return true;
}