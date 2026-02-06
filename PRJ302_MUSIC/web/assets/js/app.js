function showPage(page, el) {

    // 1. Ẩn tất cả page
    document.querySelectorAll('.page').forEach(p => {
        p.classList.remove('active');
    });

    // 2. Hiện page được chọn
    const target = document.getElementById('page-' + page);
    if (target) {
        target.classList.add('active');
    }

    // 3. Hint active menu
    document.querySelectorAll('.nav-item').forEach(item => {
        item.classList.remove('active');
    });

    if (el) {
        el.classList.add('active');
    }
}
