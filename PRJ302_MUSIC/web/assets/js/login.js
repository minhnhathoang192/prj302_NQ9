function openLogin() { // buoc 2 modal
    document.getElementById('loginModal').classList.add('active'); //DOM thêm class active modal hien ra 
}

function closeLogin() {
    document.getElementById('loginModal').classList.remove('active'); //DOM xoa class active 
}


// click ngoài để đóng
document.addEventListener('click', (e) => {
    const modal = document.getElementById('loginModal');
    if (e.target === modal)
        closeLogin();
});
