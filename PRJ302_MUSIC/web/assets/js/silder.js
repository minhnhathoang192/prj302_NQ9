document.addEventListener("DOMContentLoaded", () => {

    const track = document.querySelector('.banner-track');
    const slides = document.querySelectorAll('.banner-track img');
    const prevBtn = document.querySelector('.slide-btn.left');
    const nextBtn = document.querySelector('.slide-btn.right');
    const slider = document.querySelector('.banner-slider');

    let index = 0;
    let interval;
    const delay = 4000;

    let isSliding = false;

    function updateSlide() {
        if (isSliding)
            return;
        isSliding = true;

        track.style.transform = `translateX(-${index * 100}%)`;

        setTimeout(() => {
            isSliding = false;
        }, 600);
    }

    function nextSlide() {
        index = (index + 1) % slides.length;
        updateSlide();
    }

    function prevSlide() {
        index = (index - 1 + slides.length) % slides.length;
        updateSlide();
    }

    function startAutoSlide() {
        stopAutoSlide();
        interval = setInterval(nextSlide, delay);
    }

    function stopAutoSlide() {
        clearInterval(interval);
    }

    function resetAuto() {
        startAutoSlide();
    }

    nextBtn.addEventListener('click', () => {
        nextSlide();
        resetAuto();
    });

    prevBtn.addEventListener('click', () => {
        prevSlide();
        resetAuto();
    });

    slider.addEventListener('mouseenter', stopAutoSlide);
    slider.addEventListener('mouseleave', startAutoSlide);

    startAutoSlide();
});