function getAudio() {
    return document.getElementById("audio-player");
}

//function dieu kien audio
function playSong(url, title, cover, songID) {
    /// lay audio element (HTML5 audio)
    let audio = getAudio();
    
    // neu dang phat bai nay - chi play lai khong reload
    if (window.currentSongID === songID) {
        audio.play();
        return;
    }
    
    // load file audio + play
    audio.src = url;
    audio.play();
    
    // luu bai dang phat
    window.currentSongID = songID;

    //luu listent_History
    fetch("MainController?action=addListeningHistory&songID=" + songID) // AJAX gui lich su len sever 
            .catch(err => console.log("History error:", err));


    // LƯU STATE
    currentSong = {
        songID: songID,
        audioURL: url,
        title: title,
        coverURL: cover
    };

    // Update UI footer 
    document.getElementById("player-title").innerText = title; // cap nhat ten bai
    document.getElementById("player-cover").src = cover; // cap nhat hinh anh bai hat o footer

    // update for-you
    updateForYouUI();

    fetch("MainController?action=isFavorite&songID=" + songID) // check bai hat da like 
            .then(res => res.json()) // resqone - json object  
            .then(data => { // data = { liked: true/false }
                
                //  lay nut like 
                const btn = document.getElementById("btn-like"); 

                if (!btn)
                    return;
                
                // update UI like / un like 
                if (data.liked) {
                    btn.classList.add("liked");
                } else {
                    btn.classList.remove("liked");
                }

            });
            
            /*
             * 
             *  playSong()
                    ↓
                check bài cũ
                    ↓
                set audio + play
                    ↓
                lưu currentSong
                    ↓
                update UI (title + cover)
                    ↓
                gửi history (AJAX)
                    ↓
                check favorite (AJAX)
                    ↓
                update like button
             * 
             */
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



document.addEventListener("DOMContentLoaded", function () {

    const audio = getAudio();

    if (!audio)
        return;

    audio.addEventListener("play", () => {

        let buttons = document.querySelectorAll(".btn-play");
        let wave = document.querySelector(".music-wave");

        buttons.forEach(btn => btn.innerText = "⏸");

        if (wave)
            wave.classList.add("playing");

        document.querySelectorAll(".song-play-btn")
                .forEach(btn => btn.innerText = "▶");

        if (currentIndex >= 0) {
            const btn = document.querySelectorAll(".song-play-btn")[currentIndex];
            if (btn)
                btn.innerText = "⏸";
        }

        document.querySelectorAll(".pl-play, .fav-play, .album-play, .artist-play")
                .forEach(btn => {
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

        document.querySelectorAll(".pl-play, .fav-play, .album-play, .artist-play")
                .forEach(btn => {
                    btn.innerHTML = "▶ Phát tất cả";
                });

    });

    audio.addEventListener("ended", () => {
        nextSong();
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



document.addEventListener("click", function (e) {

    if (!e.target.closest("#fyPlayBtn") || e.target.closest("#progress-bar"))
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