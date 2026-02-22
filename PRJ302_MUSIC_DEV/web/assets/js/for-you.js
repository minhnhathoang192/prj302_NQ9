const audio = document.getElementById("audioPlayer");
const playBtn = document.getElementById("playBtn");
const wave = document.querySelector(".music-wave");


const demoSongs = [
    {
        title: "Bài ca cổ động viên",
        artist: "Đan Trường",
        src: "assets/music/song1.mp3",
        cover: "assets/img/demo-artist.jpg"
    },
    {
        title: "Một bài ngẫu nhiên",
        artist: "Nghệ sĩ A",
        src: "assets/music/song2.mp3",
        cover: "assets/img/demo-artist2.jpg"
    }
];

let currentSong = null;

function loadRandomSong() {
    const song = demoSongs[Math.floor(Math.random() * demoSongs.length)];
    currentSong = song;

    document.getElementById("fyTitle").innerText = song.title;
    document.getElementById("fyArtist").innerText = song.artist;
    document.getElementById("fyCover").src = song.cover;
    document.getElementById("fyBgCover").src = song.cover;


    audio.src = song.src;
    audio.play();
    playBtn.innerText = "⏸";
    wave.classList.add("playing");
}

function togglePlay() {
    if (audio.paused) {
        audio.play();
        playBtn.innerText = "⏸";
        wave.classList.add("playing");
    } else {
        audio.pause();
        playBtn.innerText = "▶";
        wave.classList.remove("playing");
    }
}

function nextSong() {
    loadRandomSong();
}

function prevSong() {
    loadRandomSong();
}
