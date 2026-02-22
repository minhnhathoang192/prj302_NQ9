function openLogin() {
    document.getElementById('loginModal').classList.add('active');
}

function closeLogin() {
    document.getElementById('loginModal').classList.remove('active');
}


// click ngoài để đóng
document.addEventListener('click', (e) => {
    const modal = document.getElementById('loginModal');
    if (e.target === modal)
        closeLogin();
});
