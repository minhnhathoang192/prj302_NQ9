function openRegister() { 
    document.getElementById('registerModal').classList.add('active'); // them active class hien modal
}

function closeRegister() {
    document.getElementById('registerModal').classList.remove('active'); // Dom xoa active an modal
}

function validateRegister() {
    
    // lay du lieu
    const password = document.getElementById("password").value;
    const confirm = document.getElementById("confirmPassword").value;
    const error = document.getElementById("registerError");

    if (password !== confirm) {
        //check mk 
        error.innerText = "Mật khẩu xác nhận không khớp!";
        return false;
    }

    return true;
}
// mo modal -> Lay du lieu tu form -> validate -> neu ok insetDB 