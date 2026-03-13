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


// ===== AUDIO =====

const audio = document.getElementById("audio-player");

let audioCtx;
let analyser;
let dataArray;


// ===== PLAY =====

audio.addEventListener("play", () => {

    const cover = document.querySelector(".for-you-cover img");

    if (cover) {
        cover.classList.add("beat");
        cover.classList.add("beatGlow");
    }

    // khởi tạo analyzer

    if (!audioCtx) {

        audioCtx = new AudioContext();

        const source = audioCtx.createMediaElementSource(audio);

        analyser = audioCtx.createAnalyser();

        analyser.fftSize = 64;

        source.connect(analyser);
        analyser.connect(audioCtx.destination);

        dataArray = new Uint8Array(analyser.frequencyBinCount);

    }

    animateVisualizer();

});


// ===== PAUSE =====

audio.addEventListener("pause", () => {

    const cover = document.querySelector(".for-you-cover img");

    if (cover) {
        cover.classList.remove("beat");
        cover.classList.remove("beatGlow");
    }

});


// ===== VISUALIZER =====

function animateVisualizer() {

    const bars = document.querySelectorAll("#visualizer span");

    if (!analyser)
        return;

    analyser.getByteFrequencyData(dataArray);

    bars.forEach((bar, i) => {

        const value = dataArray[i] || 0;

        bar.style.height = (value / 2) + "px";

    });

    requestAnimationFrame(animateVisualizer);

}

