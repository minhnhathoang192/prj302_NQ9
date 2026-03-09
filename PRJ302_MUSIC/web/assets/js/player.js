function getAudio() {
    return document.getElementById("audio-player");
}

function playSong(url, title, cover, songID) {
    let audio = getAudio();

    audio.src = url;
    audio.play();

    window.currentSongID = songID;
    
    //luu listent_History
    fetch("MainController?action=addListeningHistory&songID=" + songID)
            .catch(err => console.log("History error:", err));


    // LƯU STATE
    currentSong = {
        songID: songID,
        audioURL: url,
        title: title,
        coverURL: cover
    };

    // footer
    document.getElementById("player-title").innerText = title;
    document.getElementById("player-cover").src = cover;

    // update for-you
    updateForYouUI();

    fetch("MainController?action=isFavorite&songID=" + songID)
            .then(res => res.json())
            .then(data => {

                const btn = document.getElementById("btn-like");

                if (!btn)
                    return;

                if (data.liked) {
                    btn.innerText = "❤";
                } else {
                    btn.innerText = "🤍";
                }

            });
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
        if (wave)
            wave.classList.add("playing");

    } else {
        audio.pause();

        buttons.forEach(btn => btn.innerText = "▶");
        if (wave)
            wave.classList.remove("playing");
    }
}



let audio = getAudio();

audio.addEventListener("play", () => {

    let buttons = document.querySelectorAll(".btn-play");
    let wave = document.querySelector(".music-wave");

    buttons.forEach(btn => btn.innerText = "⏸");

    if (wave)
        wave.classList.add("playing");

    // reset icon playlist
    document.querySelectorAll(".song-play-btn")
            .forEach(btn => btn.innerText = "▶");

    // icon bài đang phát
    if (currentIndex >= 0) {
        const btn = document.querySelectorAll(".song-play-btn")[currentIndex];
        if (btn)
            btn.innerText = "⏸";
    }

    document.querySelectorAll(".pl-play, .fav-play").forEach(btn => {
        btn.innerHTML = "⏸ Tạm dừng";
    });

});

audio.addEventListener("pause", () => {

    let buttons = document.querySelectorAll(".btn-play");
    let wave = document.querySelector(".music-wave");

    buttons.forEach(btn => btn.innerText = "▶");

    if (wave)
        wave.classList.remove("playing");

    if (currentIndex >= 0) {
        const btn = document.querySelectorAll(".song-play-btn")[currentIndex];
        if (btn)
            btn.innerText = "▶";
    }

    document.querySelectorAll(".pl-play, .fav-play").forEach(btn => {
        btn.innerHTML = "▶ Phát tất cả";
    });

});

let playlist = [];
let currentIndex = -1;

function nextSong() {

    if (playlist.length === 0)
        return;

    currentIndex = (currentIndex + 1) % playlist.length;

    playPlaylistSong(currentIndex);

}


function prevSong() {

    if (playlist.length === 0)
        return;

    currentIndex = (currentIndex - 1 + playlist.length) % playlist.length;

    playPlaylistSong(currentIndex);

}

getAudio().addEventListener("ended", () => {
    nextSong();
});

document.addEventListener("click", function (e) {

    const btn = e.target.closest("#fyPlayBtn");

    if (!btn)
        return;

    if (!currentSong) {
        alert("Chưa có bài hát!");
        return;
    }

    playSong(
            currentSong.audioURL,
            currentSong.title,
            currentSong.coverURL,
            currentSong.songID
            );

});

function updateForYouUI() {

    if (!currentSong)
        return;

    let fyTitle = document.getElementById("fyTitle");
    let fyCover = document.getElementById("fyCover");
    let fyBg = document.getElementById("fyBgCover");

    if (fyTitle)
        fyTitle.innerText = currentSong.title;
    if (fyCover)
        fyCover.src = currentSong.coverURL;
    if (fyBg)
        fyBg.src = currentSong.coverURL;
}

function syncPlayButtons() {

    const audio = getAudio();

    document.querySelectorAll(".pl-play").forEach(btn => {

        if (audio.src && !audio.paused) {
            btn.innerHTML = "⏸ Tạm dừng";
        } else {
            btn.innerHTML = "▶ Phát tất cả";
        }

    });

}