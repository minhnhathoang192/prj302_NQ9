document.addEventListener("click", function (e) {

    const card = e.target.closest(".artist-card");
    if (!card)
        return;
    const id = card.dataset.id;
    if (!id)
        return;
    openArtistPage(id);
});
function openArtistPage(id) {

    window.currentArtistID = id;
    showPage("artist", null);
    setTimeout(() => {

        fetch("MainController?action=getArtistInfo&artistID=" + id)
                .then(res => res.json())
                .then(data => {

                    const avatarURL =
                            contextPath + "/StreamServlet?type=artist&file=" + data.avatarURL;
                    const name = document.getElementById("artistName");
                    if (name) {
                        name.innerText = data.artistName;
                    }

                    const avatar = document.getElementById("artistAvatar");
                    if (avatar) {
                        avatar.src = avatarURL;
                    }

                    const cover = document.getElementById("artistCoverBg");
                    if (cover) {
                        cover.src = avatarURL;
                    }

                });
        fetch("MainController?action=getArtistFollowers&artistID=" + id)
                .then(res => res.json())
                .then(data => {

                    const el = document.getElementById("artistFollowers");
                    if (el) {
                        el.innerText = data.followers;
                    }

                });

        fetch("MainController?action=isFollowingArtist&artistID=" + id)
                .then(res => res.json())
                .then(data => {

                    const btn = document.getElementById("followArtistBtn");

                    if (data.following) {
                        btn.innerText = "Đang theo dõi";
                        btn.classList.add("following");
                    } else {
                        btn.innerText = "Theo dõi";
                        btn.classList.remove("following");
                    }

                });
        loadArtistSongs(id);
    }, 30);
}

function loadArtistSongs(id) {

    fetch("MainController?action=getSongsByArtist&artistID=" + id)
            .then(res => res.json())
            .then(data => {

                const container = document.getElementById("artistSongList");
                if (!container)
                    return;
                playlist = [];
                currentIndex = -1;
                currentPlaylistType = "artist";
                if (!data || data.length === 0) {

                    container.innerHTML = `
            <div class="artist-empty">
                <div class="empty-icon">🎵</div>
                <h3>Artist chưa có bài hát</h3>
                <p>Bài hát sẽ hiển thị ở đây</p>
            </div>
            `;
                    return;
                }

                let html = "";
                data.forEach((s, i) => {

                    const audioURL =
                            contextPath + "/StreamServlet?type=audio&file=" + s.audioURL;
                    const coverURL =
                            contextPath + "/StreamServlet?type=cover&file=" + s.coverImage;
                    // push vào playlist
                    playlist.push({
                        songID: s.songID,
                        audioURL: audioURL,
                        title: s.title,
                        coverURL: coverURL
                    });
                    html += `
<div class="artist-song-row">

<div class="song-index">${i + 1}</div>

<div class="artist-song-title">

<div class="artist-cover-box">

<img class="artist-song-cover" src="${coverURL}">

<div class="artist-cover-play"
     onclick="toggleArtistSong(${i},event)">

    <div class="artist-play-icon">▶</div>

    <div class="wave" style="display:none">
        <span></span><span></span><span></span><span></span><span></span>
    </div>

</div>

</div>

<div class="artist-song-info">
<div class="artist-song-name">${s.title}</div>
</div>

</div>

<div class="artist-song-artist">
${s.artistName ?? ""}
</div>

<div class="song-duration">
${formatDuration(s.duration)}
</div>

</div>
`;
                });
                container.innerHTML = html;
                const btn = document.querySelector(".artist-play");
                if (btn) {
                    btn.innerHTML = "▶ Phát tất cả";
                }
            });
}

function toggleArtistSong(index, e) {

    e.stopPropagation();
    const audio = getAudio();
    if (currentIndex === index && !audio.paused) {
        audio.pause();
    } else {
        playArtistSong(index);
    }

}

function playArtistSong(index) {

    currentIndex = index;
    const s = playlist[index];
    playSong(
            s.audioURL,
            s.title,
            s.coverURL,
            s.songID
            );
    // highlight row
    document.querySelectorAll(".artist-song-row")
            .forEach(r => r.classList.remove("playing"));
    const rows = document.querySelectorAll(".artist-song-row");
    if (rows[index]) {
        rows[index].classList.add("playing");
    }

// wave animation

    document.querySelectorAll(".artist-song-row .wave")
            .forEach(w => w.style.display = "none");
    document.querySelectorAll(".artist-song-row .artist-play-icon")
            .forEach(p => p.style.display = "block");
    const row = rows[index];
    if (row) {

        const wave = row.querySelector(".wave");
        const play = row.querySelector(".artist-play-icon");
        if (wave)
            wave.style.display = "flex";
        if (play)
            play.style.display = "none";
    }

}

document.addEventListener("click", function (e) {

    const btn = e.target.closest(".artist-play");
    if (!btn)
        return;
    const audio = getAudio();
    if (playlist.length === 0)
        return;
    if (currentIndex === -1) {
        playArtistSong(0);
        return;
    }

    if (!audio.paused) {
        audio.pause();
    } else {
        audio.play();
    }

});


function toggleFollowArtist() {

if (!window.currentArtistID)
        return;
        fetch("MainController?action=toggleFollowArtist&artistID=" + window.currentArtistID)

        .then(res => res.json())

        .then(data => {

        if (data.error === "login") {
        openLogin();
                return;
        }

        const btn = document.getElementById("followArtistBtn");
                if (data.following) {

        btn.innerText = "Đang theo dõi";
                btn.classList.add("following");
        } else {

        btn.innerText = "Theo dõi";
                btn.classList.remove("following");
        }

        });
        fetch("MainController?action=getArtistFollowers&artistID=" + window.currentArtistID)
        .then(res => res.json())
        .then(data => {
        document.getElementById("artistFollowers").innerText = data.followers;
        });
}