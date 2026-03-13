function loadFavoriteSongs() {

    fetch("MainController?action=getFavoriteSongs")
            .then(res => res.json())
            .then(data => {

                const container = document.getElementById("favoriteSongs");
                const empty = document.getElementById("favoriteEmpty");
                const count = document.getElementById("favoriteCount");
                if (!container)
                    return;
                // reset player playlist
                playlist = [];
                currentIndex = -1;
                currentPlaylistType = "favorite"; // hoặc playlist
                if (!data || data.length === 0) {
                    empty.style.display = "block";
                    container.style.display = "none";
                    container.innerHTML = "";
                    count.innerText = 0;
                    return;
                }

                empty.style.display = "none";
                container.style.display = "block";
                count.innerText = data.length;
                let html = "";
                data.forEach((s, i) => {

                    const audioURL =
                            contextPath + "/StreamServlet?type=audio&file=" + s.audioURL;
                    const coverURL =
                            contextPath + "/StreamServlet?type=cover&file=" + s.coverImage;
                    // ⭐ push vào playlist player
                    playlist.push({
                        songID: s.songID,
                        audioURL: audioURL,
                        title: s.title,
                        coverURL: coverURL
                    });
                    html += `
<div class="fav-song-row">

<div class="song-index">${i + 1}</div>

<div class="fav-song-title">

    <div class="fav-cover-box">

        <img class="fav-cover" src="${coverURL}">

        <div class="fav-play-btn" onclick="toggleFavoriteSong(${i}, event)">
    <div class="play-icon">▶</div>
    <div class="wave" style="display:none">
        <span></span><span></span><span></span><span></span><span></span>
    </div>
</div>

    </div>

    <div class="fav-song-info">
        <div class="fav-song-name">${s.title}</div>
    </div>

</div>

<div class="fav-song-artist">${s.artistName ?? ""}</div>

<div class="song-duration">${formatDuration(s.duration)}</div>

</div>
`;
                });
                container.innerHTML = html;
                const btn = document.querySelector(".fav-play");
                if (btn) {
                    btn.innerHTML = "▶ Phát tất cả";
                }
            });
}

function toggleFavorite() {

    if (!window.currentSongID)
        return;

    fetch("MainController?action=toggleFavorite&songID=" + window.currentSongID)
            .then(res => res.json())
            .then(data => {

                const btn = document.getElementById("btn-like");

                if (data.liked) {
                    btn.classList.add("liked");
                } else {
                    btn.classList.remove("liked");
                }

            });
}

function toggleFavoriteSong(index, e) {

    e.stopPropagation();
    const audio = getAudio();
    if (currentIndex === index && !audio.paused) {

        audio.pause();
    } else {

        playFavoriteSong(index);
    }

}

function playFavoriteSong(index) {

    currentIndex = index;
    const s = playlist[index];
    playSong(
            s.audioURL,
            s.title,
            s.coverURL,
            s.songID
            );
    // highlight bài đang phát
    document.querySelectorAll(".fav-song-row")
            .forEach(r => r.classList.remove("playing"));
    const rows = document.querySelectorAll(".fav-song-row");
    if (rows[index]) {
        rows[index].classList.add("playing");
    }

//animation

    document.querySelectorAll(".fav-song-row .wave")
            .forEach(w => w.style.display = "none");
    document.querySelectorAll(".fav-song-row .play-icon")
            .forEach(p => p.style.display = "block");
    const row = document.querySelectorAll(".fav-song-row")[index];
    if (row) {
        const wave = row.querySelector(".wave");
        const play = row.querySelector(".play-icon");
        if (wave)
            wave.style.display = "flex";
        if (play)
            play.style.display = "none";
    }

}

document.addEventListener("click", function (e) {

    const btn = e.target.closest(".fav-play");
    if (!btn)
        return;
    const audio = getAudio();
    if (playlist.length === 0)
        return;
    if (currentIndex === -1) {
        playFavoriteSong(0);
        return;
    }

    if (!audio.paused) {
        audio.pause();
    } else {
        audio.play();
    }

})
        ;
// thanh footer

///////////////////////////////process thanh tua nhac
document.addEventListener("DOMContentLoaded", function () {

    const audio = getAudio();
    const progressBar = document.getElementById("progress-bar");
    const currentTimeText = document.getElementById("current-time");
    const durationText = document.getElementById("duration");
    const volumeSlider = document.getElementById("volume-slider");
    const player = document.getElementById("music-player");
    if (player) {
        player.addEventListener("click", (e) => {
            e.stopImmediatePropagation();
        });
    }
    let isSeeking = false;
    if (!audio)
        return;
    // load duration
    audio.addEventListener("loadedmetadata", () => {

        if (!isFinite(audio.duration))
            return;
        progressBar.max = audio.duration;
        durationText.innerText = formatTime(audio.duration);
    });
    // update progress
    audio.addEventListener("timeupdate", () => {

        if (!isSeeking && isFinite(audio.duration)) {

            progressBar.value = audio.currentTime;
            currentTimeText.innerText = formatTime(audio.currentTime);
        }

    });
    // bắt đầu kéo
    progressBar.addEventListener("pointerdown", (e) => {
        e.stopImmediatePropagation();
        isSeeking = true;
    });
    // khi kéo
    progressBar.addEventListener("input", (e) => {

        e.stopImmediatePropagation();
        const time = Number(progressBar.value);
        currentTimeText.innerText = formatTime(time);
    });
    // thả slider
    progressBar.addEventListener("pointerup", (e) => {

        e.stopImmediatePropagation();
        const seekTime = Number(progressBar.value);
        audio.currentTime = seekTime;
        isSeeking = false;
    });
    progressBar.addEventListener("click", (e) => {
        e.stopImmediatePropagation();
    });
    // volume
    // khi kéo volume
    volumeSlider.addEventListener("input", () => {

        const value = volumeSlider.value;

        audio.volume = value;

        const percent = value * 100;

        volumeSlider.style.background =
        `linear-gradient(to right,
        rgba(255,255,255,0.8) ${percent}%,
        #535353 ${percent}%)`;

    });
    
    // set màu khi load trang
    const percent = volumeSlider.value * 100;

        volumeSlider.style.background =
    `linear-gradient(to right,
    rgba(255,255,255,0.8) ${percent}%,
    #535353 ${percent}%)`;
});
function formatTime(sec) {

let minutes = Math.floor(sec / 60);
        let seconds = Math.floor(sec % 60);
        if (seconds < 10) {
seconds = "0" + seconds;
        }

return minutes + ":" + seconds;
}