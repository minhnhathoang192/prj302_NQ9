// ================== GLOBAL ==================
let selectedTopics = [];

// ================== INIT ==================
window.addEventListener("DOMContentLoaded", () => {
    const modal = document.getElementById("topicModal");

    if (!modal) {
        console.error("❌ Không tìm thấy topicModal");
        return;
    }

    // chỉ hiện 1 lần mỗi session
    if (!sessionStorage.getItem("askedTopic")) {
        modal.classList.add("active");
    }
});

// ================== CLOSE MODAL ==================
function closeTopicModal() {
    const modal = document.getElementById("topicModal");
    if (modal)
        modal.classList.remove("active");
}

// click ngoài để đóng
document.addEventListener("click", (e) => {
    const modal = document.getElementById("topicModal");
    if (e.target === modal) {
        closeTopicModal();
    }
});

// ================== SELECT MULTIPLE ==================
function toggleTopic(el) {
    const id = el.dataset.id;

    if (el.classList.contains("active")) {
        el.classList.remove("active");
        selectedTopics = selectedTopics.filter(t => t !== id);
    } else {
        el.classList.add("active");
        selectedTopics.push(id);
    }

    console.log("🎯 Selected topics:", selectedTopics);
}

// ================== SUBMIT ==================
function submitTopics() {

    if (selectedTopics.length === 0) {
        alert("Chọn ít nhất 1 chủ đề!");
        return;
    }

    sessionStorage.setItem("userTopics", JSON.stringify(selectedTopics));

    loadPlaylistByTopics(selectedTopics);

    sessionStorage.setItem("askedTopic", "true");
    closeTopicModal();
}

// ================== SKIP ==================
function skipTopic() {

    fetch("MainController?action=getRandomTopic")
            .then(res => res.json())
            .then(topic => {

                if (!topic) {
                    alert("Không có topic!");
                    return;
                }

                sessionStorage.setItem("userTopics", JSON.stringify([topic.topicID]));

                // lấy nhạc của topic random
                loadPlaylistByTopic(topic.topicID);

                sessionStorage.setItem("askedTopic", "true");
                closeTopicModal();
            })
            .catch(err => console.error("Lỗi random topic:", err));
}

// ================== LOAD MULTIPLE TOPICS ==================
function loadPlaylistByTopics(topicIds) {

    fetch(`MainController?action=getSongsByMultipleTopics&topicIds=${topicIds.join(",")}`)
            .then(res => res.json())
            .then(list => {

                if (!list || list.length === 0) {
                    alert("❌ Không có bài hát!");
                    return;
                }

                buildAndLoadPlayer(list);
            })
            .catch(err => console.error("❌ Lỗi multi-topic:", err));
}

// ================== LOAD SINGLE TOPIC (OPTIONAL) ==================
function loadPlaylistByTopic(topicID) {

    fetch(`MainController?action=getSongsByTopic&topicID=${topicID}`)
            .then(res => res.json())
            .then(list => {

                console.log("🔥 LIST NHẬN ĐƯỢC:", list);

                if (list) {
                    console.log("🔥 LIST LENGTH:", list.length);
                } else {
                    console.log("🔥 LIST đang là null hoặc undefined");
                }

                if (!list || list.length === 0) {
                    alert("❌ Không có bài!");
                    return;
                }

                buildAndLoadPlayer(list);
            })
            .catch(err => console.error("❌ Lỗi topic:", err));
}

// ================== BUILD PLAYER ==================
function buildAndLoadPlayer(list) {

    let contextPath = document.body.dataset.context;

    playlist = list.map(song => ({
            audioURL: contextPath + "/StreamServlet?type=audio&file=" + encodeURIComponent(song.audioURL),
            title: song.title,
            coverURL: contextPath + "/StreamServlet?type=cover&file=" + encodeURIComponent(song.coverImage)
        }));

    currentIndex = 0;

    let s = playlist[currentIndex];

    // gọi playSong
    playSong(s.audioURL, s.title, s.coverURL);
}