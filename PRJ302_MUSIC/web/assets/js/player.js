function getAudio() {
    return document.getElementById("audio-player");
}

function playSong(url, title, cover) {
    let audio = getAudio();

    audio.src = url;
    audio.play();

    // footer
    document.getElementById("player-title").innerText = title;
    document.getElementById("player-cover").src = cover;

    // for-you
    let fyTitle = document.getElementById("fyTitle");
    let fyCover = document.getElementById("fyCover");
    let fyBg = document.getElementById("fyBgCover");

    if (fyTitle) fyTitle.innerText = title;
    if (fyCover) fyCover.src = cover;
    if (fyBg) fyBg.src = cover;
}

function togglePlay() {
    let audio = getAudio();

    if (!audio.src) {
        alert("Chưa chọn bài!");
        return;
    }

    let buttons = document.querySelectorAll(".btn-play");
    let wave = document.querySelector(".music-wave");

    if (audio.paused) {
        audio.play();

        buttons.forEach(btn => btn.innerText = "⏸");
        if (wave) wave.classList.add("playing");

    } else {
        audio.pause();

        buttons.forEach(btn => btn.innerText = "▶");
        if (wave) wave.classList.remove("playing");
    }
}

let audio = getAudio();

audio.addEventListener("play", () => {
    let buttons = document.querySelectorAll(".btn-play");
    let wave = document.querySelector(".music-wave");

    buttons.forEach(btn => btn.innerText = "⏸");
    if (wave) wave.classList.add("playing");
});

audio.addEventListener("pause", () => {
    let buttons = document.querySelectorAll(".btn-play");
    let wave = document.querySelector(".music-wave");

    buttons.forEach(btn => btn.innerText = "▶");
    if (wave) wave.classList.remove("playing");
});

let playlist = [];
let currentIndex = -1;

function nextSong() {
    if (playlist.length === 0) return;

    currentIndex = (currentIndex + 1) % playlist.length;

    let s = playlist[currentIndex];
    playSong(s.audioURL, s.title, s.coverURL);
}


function prevSong() {
    if (playlist.length === 0) return;

    currentIndex = (currentIndex - 1 + playlist.length) % playlist.length;

    let s = playlist[currentIndex];
    playSong(s.audioURL, s.title, s.coverURL);
}

getAudio().addEventListener("ended", () => {
    nextSong();
});