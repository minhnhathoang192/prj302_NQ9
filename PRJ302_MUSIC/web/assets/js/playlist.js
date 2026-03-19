// ===== MODAL =====
function openPlaylist() {
    document.getElementById("playlistModal").classList.add("active");
}

function closePlaylist() {
    document.getElementById("playlistModal").classList.remove("active");
}

// click ngoài modal
document.addEventListener("click", function (e) {
    const modal = document.getElementById("playlistModal");
    if (e.target === modal)
        closePlaylist();
});
// ===== LOAD USER PLAYLIST =====
function loadUserPlaylists() {

    fetch("MainController?action=loadProfile")
            .then(res => res.json())
            .then(data => {

                const grid = document.getElementById("playlistGrid");
                if (!grid)
                    return;
                grid.innerHTML = "";
                if (data.length === 0) {
                    grid.innerHTML = "<p>Bạn chưa có playlist nào</p>";
                    return;
                }

                data.forEach(p => {

                    grid.insertAdjacentHTML("beforeend", `
                    <div class="playlist-card"
                         data-id="${p.playListID}"
                         data-name="${p.playListName}">

                        <div class="playlist-cover">
                            <img src="assets/img/default-playlist.png">
                        </div>

                        <div class="playlist-name">
                            ${p.playListName}
                        </div>

                        <div class="playlist-count">
                            0 bài hát
                        </div>

                    </div>
                `);
                });
            });
}


/////////////////////////////////// ===== CLICK PLAYLIST =====///////////////////////////////////////
document.addEventListener("click", function (e) {

    const card = e.target.closest(".playlist-card, .search-playlist-card");
    if (!card)
        return;
    const id = card.dataset.id;
    if (!id)
        return;
    openPlaylistPage(id);
});
// ===== OPEN PLAYLIST PAGE =====
function openPlaylistPage(id) {

    window.currentPlaylistID = id;
    showPage("playlist", null);
    setTimeout(() => {

        fetch("MainController?action=getPlaylistInfo&playlistID=" + id)
                .then(res => res.json())
                .then(data => {

                    const titleEl = document.getElementById("playlistTitle");
                    if (titleEl) {
                        titleEl.textContent = data.playListName;
                    }

                });
        loadPlaylistSongs(id);
    }, 30);
}


// ===== LOAD SONGS IN PLAYLIST =====
function loadPlaylistSongs(id) {

    fetch("MainController?action=getSongsInPlaylist&playlistID=" + id)
            .then(res => res.json())
            .then(data => {

                if (window.currentPlaylistID !== id)
                    return;
                const container = document.getElementById("playlistSongs");
                if (!container)
                    return;
                // update song count
                const count = document.getElementById("playlistSongCount");
                if (count) {
                    count.innerText = data.length;
                }
                // reset playlist cho player
                playlist = [];
                currentIndex = -1;
                currentPlaylistType = "playlist";
                if (data.length === 0) {

                    container.innerHTML = `
                        <div class="pl-empty">
                        <div class="pl-empty-icon">📦</div>
                        <h3>Playlist trống</h3>
                        <p>Hãy thêm bài hát vào playlist</p>
                        <button class="pl-add-song-btn">Thêm bài hát</button>
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
                    // push vào playlist array để next/prev hoạt động
                    playlist.push({
                        songID: s.songID,
                        audioURL: audioURL,
                        title: s.title,
                        coverURL: coverURL
                    });
                    html += `
                        <div class="pl-song-row">

                            <div class="song-index">${i + 1}</div>

                            <div class="pl-song-title">

                                <div class="pl-cover-box">

                                    <img class="pl-song-cover" src="${coverURL}">

                                    <div class="pl-play-btn" onclick="togglePlaylistSong(${i}, event)">

                                        <div class="play-icon">▶</div>

                                        <div class="wave">
                                            <span></span>
                                            <span></span>
                                            <span></span>
                                            <span></span>
                                            <span></span>
                                        </div>

                                    </div>

                                </div>

                                <div class="pl-song-info">
                                    <div class="pl-song-name">${s.title}</div>
                                </div>

                            </div>

                            <div class="song-artist">${s.artistName ?? ""}</div>

                            <div class="song-duration">${formatDuration(s.duration)}</div>

                            <div class="pl-delete"
                                onclick="removeSongFromPlaylist(${s.songID}, event)">
                                🗑
                            </div>

                        </div>
                        `;
                });
                container.innerHTML = html;
                const btn = document.querySelector(".pl-play");
                if (btn) {
                    btn.innerHTML = "▶ Phát tất cả";
                }
            });
}

//////////////////////////////////////////////////////////////////////////////////////////////
//

// ===== FORMAT TIME =====
function formatDuration(sec) {

    const m = Math.floor(sec / 60);
    const s = sec % 60;
    return m + ":" + (s < 10 ? "0" + s : s);
}


// ===== ADD PLAYLIST UI =====
function addPlaylistToUI(p) {

    const grid = document.getElementById("playlistGrid");
    if (!grid)
        return;
    grid.insertAdjacentHTML("afterbegin", `
        <div class="playlist-card"
             data-id="${p.playListID}"
             data-name="${p.playListName}">

            <div class="playlist-cover">
                <img src="assets/img/default-playlist.png">
            </div>

            <div class="playlist-name">
                ${p.playListName}
            </div>

            <div class="playlist-count">
                0 bài hát
            </div>

        </div>
    `);
}


// ===== DOM READY =====
document.addEventListener("DOMContentLoaded", function () {

    loadUserPlaylists();
    const form = document.getElementById("playlistForm");
    if (!form)
        return;
    form.addEventListener("submit", function (e) {

        e.preventDefault();
        const formData = new FormData(this);
        fetch("MainController", {
            method: "POST",
            body: formData
        })
                .then(res => res.json())
                .then(data => {

                    if (data.success) {

                        addPlaylistToUI(data.playlist);
                        closePlaylist();
                    } else {
                        alert("Tạo playlist thất bại");
                    }

                });
    });
});
function playPlaylistSong(index) {
    
    if (!playlist[index]) return;

    currentIndex = index;
    const s = playlist[index];
    playSong(
            s.audioURL,
            s.title,
            s.coverURL,
            s.songID
            );
    const rows = document.querySelectorAll(".pl-song-row");
    rows.forEach(r => r.classList.remove("playing"));
    if (rows[index]) {
        rows[index].classList.add("playing");
    }

}

document.addEventListener("click", function (e) {

    if (e.target.closest("#music-player")) return;

    const btn = e.target.closest(".pl-play");
    if (!btn) return;

    const audio = getAudio();
    if (playlist.length === 0) return;

    if (currentIndex === -1) {
        playPlaylistSong(0);
        return;
    }

    if (!audio.paused) {
        audio.pause();
    } else {
        audio.play();
    }

});

function togglePlaylistSong(index, e) {

    e.stopPropagation();
    const audio = getAudio();
    if (currentIndex === index && !audio.paused) {
        audio.pause();
    } else {
        playPlaylistSong(index);
    }

}

//xoa
function removeSongFromPlaylist(songID, event) {

    event.stopPropagation();
    if (!confirm("Xóa bài này khỏi playlist?"))
        return;
    fetch("MainController?action=removeSongFromPlaylistFromUser"
            + "&playlistID=" + currentPlaylistID
            + "&songID=" + songID)

            .then(res => res.text())

            .then(() => {

                loadPlaylistSongs(currentPlaylistID);
            });
}



//        playlist-addsong-modal

function openAddSongModal() {

    document.getElementById("addSongModal").classList.add("active");
    loadFavoriteForAdd();
}

function closeAddSongModal() {

    document.getElementById("addSongModal").classList.remove("active");
}


//load song 
function loadFavoriteForAdd() {

    fetch("MainController?action=getFavoriteSongs")

            .then(res => res.json())

            .then(data => {

                const container = document.getElementById("addSongList");
                let html = "";
                data.forEach((s, i) => {

                    const coverURL =
                            contextPath + "/StreamServlet?type=cover&file=" + s.coverImage;
                    html += `

<div class="add-song-row">

<div>${i + 1}</div>

<div class="add-song-title">

<img src="${coverURL}" class="add-song-cover">

<span>${s.title}</span>

</div>

<div>${s.artistName ?? ""}</div>

<div class="add-btn"
onclick="addSongToPlaylist(${s.songID})">
Thêm vào
</div>

</div>

`;
                });
                container.innerHTML = html;
            })

}

//add
function addSongToPlaylist(songID) {

    fetch("MainController?action=addSongToPlaylistFromUser"
            + "&playlistID=" + currentPlaylistID
            + "&songID=" + songID)

            .then(res => res.text())

            .then(() => {

                loadPlaylistSongs(currentPlaylistID);
            })

}


///more 
function togglePlaylistMenu(event) {

    event.stopPropagation();

    const menu = document.getElementById("playlistMenu");

    menu.classList.toggle("active");

}

// click ngoài menu thì đóng

document.addEventListener("click", function () {

    const menu = document.getElementById("playlistMenu");
    if (menu) {
        menu.classList.remove("active");
    }

})
        ;

// chinh sua playlist
function editPlaylist() {

    document.getElementById("playlistMenu").classList.remove("active");

    document.getElementById("editPlaylistModal")
            .classList.add("active");

// load tên playlist hiện tại
    document.getElementById("editPlaylistName").value =
            document.getElementById("playlistTitle").innerText;

}

//dong modal
function closeEditPlaylist() {

    document.getElementById("editPlaylistModal")
            .classList.remove("active");

}

function savePlaylistEdit() {

    const name =
            document.getElementById("editPlaylistName").value;
    const privacy =
            document.querySelector("input[name='privacy']:checked").value;
    fetch("MainController?action=editPlaylistFromUser"
            + "&playlistID=" + currentPlaylistID
            + "&name=" + encodeURIComponent(name)
            + "&privacy=" + privacy)

            .then(res => res.json())
            .then(data => {

                if (data) {
                    alert("Cập nhật playlist thành công");
                    document.getElementById("playlistTitle").innerText = name;
                    closeEditPlaylist();
                } else {
                    alert("Không thể cập nhật playlist");
                }

            });
}

//chinh sua

//xoa playlist
function deletePlaylist() {

    if (!confirm("Bạn có chắc muốn xóa playlist này?"))
        return;
    fetch("MainController?action=deletePlaylistFromUser&playlistID=" + currentPlaylistID)
            .then(res => res.text())
            .then(() => {

                alert("Đã xóa playlist");
                showPage("profile");
                loadUserPlaylists();
            });
}

