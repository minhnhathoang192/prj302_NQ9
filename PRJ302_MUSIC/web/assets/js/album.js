// ===== CLICK ALBUM =====

document.addEventListener("click", function (e) {

    const card = e.target.closest(".album-card");
    if (!card)
        return;
    const id = card.dataset.id;
    if (!id)
        return;
    openAlbumPage(id);
});
function openAlbumPage(id) {

    window.currentAlbumID = id;
    showPage("album", null);
    setTimeout(() => {

        fetch("MainController?action=getAlbumInfo&albumID=" + id)
                .then(res => res.json())
                .then(data => {

                const title = document.getElementById("albumTitle");
                        if (title){
                title.innerText = data.albumName;
                }

                const year = document.getElementById("albumYear");
                        if (year && data.releaseDate){
                year.innerText = new Date(data.releaseDate).getFullYear();
                }

                const cover = document.getElementById("albumCover");
                        if (cover){
                cover.src = contextPath + "/StreamServlet?type=album&file=" + data.coverImage;
                }

                });
                loadAlbumSongs(id);
    }, 30);
}

function loadAlbumSongs(id) {

    fetch("MainController?action=getSongsInAlbum&albumID=" + id)

            .then(res => res.json())

            .then(data => {

                const container = document.getElementById("albumSongs");
                const count = document.getElementById("albumSongCount");
                if (count) {
                    count.innerText = data.length + (data.length > 1 ? " bài hát" : " bài hát");
                }
                if (!container)
                    return;
                playlist = [];
                currentIndex = -1;
                currentPlaylistType = "album";
                let html = "";
                data.forEach((s, i) => {

                    const audioURL =
                            contextPath + "/StreamServlet?type=audio&file=" + s.audioURL;
                    const coverURL =
                            contextPath + "/StreamServlet?type=cover&file=" + s.coverImage;
                    //push
                    playlist.push({
                        songID: s.songID,
                        audioURL: audioURL,
                        title: s.title,
                        coverURL: coverURL
                    });
                    html += `
                <div class="album-song-row">

                <div>${i + 1}</div>

                <div class="album-song-title">

                <div class="album-cover-box">

                <img class="album-song-cover" src="${coverURL}">

                <div class="album-cover-play"
                     onclick="toggleAlbumSong(${i},event)">

                <div class="play-icon">▶</div>

                <div class="wave" style="display:none">
                <span></span>
                <span></span>
                <span></span>
                <span></span>
                <span></span>
                </div>

                </div>

                </div>

                <div class="album-song-info">
                <div class="album-song-name">${s.title}</div>
                </div>

                </div>

                <div class="album-song-artist">${s.artistName ?? ""}</div>

                <div>${formatDuration(s.duration)}</div>

                </div>
                `;
                });
                container.innerHTML = html;
                const btn = document.querySelector(".album-play");
                if (btn) {
                    btn.innerHTML = "▶ Phát tất cả";
                }
            });
}


///playsong
function playAlbumSong(index) {

    currentIndex = index;
    const s = playlist[index];
    playSong(
            s.audioURL,
            s.title,
            s.coverURL,
            s.songID
            );
// highlight

    document.querySelectorAll(".album-song-row")
            .forEach(r => r.classList.remove("playing"));
    const rows = document.querySelectorAll(".album-song-row");
    if (rows[index]) {
        rows[index].classList.add("playing");
    }

// animation

    document.querySelectorAll(".album-song-row .wave")
            .forEach(w => w.style.display = "none");
    document.querySelectorAll(".album-song-row .play-icon")
            .forEach(p => p.style.display = "block");
    const row = rows[index];
    if (row) {

        const wave = row.querySelector(".wave");
        const play = row.querySelector(".play-icon");
        if (wave)
            wave.style.display = "flex";
        if (play)
            play.style.display = "none";
    }

}

function toggleAlbumSong(index, e) {

    e.stopPropagation();
    const audio = getAudio();
    if (currentIndex === index && !audio.paused) {
        audio.pause();
    } else {
        playAlbumSong(index);
    }
}

document.addEventListener("click", function (e) {

    const btn = e.target.closest(".album-play");
    if (!btn)
        return;
    const audio = getAudio();
    if (playlist.length === 0)
        return;
    if (currentIndex === -1) {
        playAlbumSong(0);
        return;
    }

    if (!audio.paused) {
        audio.pause();
    } else {
        audio.play();
    }

})
        ;