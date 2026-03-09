function loadRecentSongs() {

fetch("MainController?action=getListeningHistory")
        .then(res => res.json())
        .then(data => {

        const container = document.getElementById("recentList");
                if (!container)
                return;
                if (!data || data.length === 0) {
        container.innerHTML = `
                <div class="recent-empty">
                    <div class="empty-icon">🎵</div>
                    <h3>Chưa có bài hát nào được nghe</h3>
                    <p>Khi bạn phát một bài hát, nó sẽ xuất hiện ở đây</p>
                </div>`;
                return;
        }

        let html = `<div class="recent-table">`;
                data.forEach((s, i) => {

                const audioURL =
                        contextPath + "/StreamServlet?type=audio&file=" + s.audioURL;
                        const coverURL =
                        contextPath + "/StreamServlet?type=cover&file=" + s.coverImage;
                        html += `
<div class="recent-song-row"
onclick="playSong('${audioURL}','${s.title}','${coverURL}','${s.songID}')">

<div>${i + 1}</div>

<div class="recent-song-title">

<div class="recent-cover-box">

<img src="${coverURL}" class="recent-cover">

<div class="recent-play-btn">

<div class="play-icon">▶</div>

<div class="wave">
<span></span><span></span><span></span><span></span><span></span>
</div>

</div>

</div>

<span>${s.title}</span>

</div>

<div>${s.artistName ?? ""}</div>

<div>${formatDuration(s.duration)}</div>

</div>
`;
                });
                html += `</div>`;
                container.innerHTML = html;
        })
        .catch(err => console.log(err));
        }