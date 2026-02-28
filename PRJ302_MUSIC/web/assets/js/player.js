let audio = document.getElementById("audio-player");

function playSong(url, title, cover) {
    audio.src = url;
    audio.play();

    document.getElementById("player-title").innerText = title;
    document.getElementById("player-cover").src = cover;
}

function togglePlay() {
    if (!audio.src) {
        alert("Chưa chọn bài!");
        return;
    }

    if (audio.paused) {
        audio.play();
    } else {
        audio.pause();
    }
}