// ================== GLOBAL ==================
let selectedTopics = []; // mang luu topic user da chon 

// ================== INIT ==================
window.addEventListener("DOMContentLoaded", () => {

    const modal = document.getElementById("topicModal"); // lay modal topic trong DOM
    if (!modal)
        return;

//   check login lay tu data-logged-in="${not empty sessionScope.user}" set o trong index.jsp
    const isLoggedIn = document.body.dataset.loggedIn === "true";


    if (isLoggedIn) {
        //rest trang thai topic (de login xong hoi lai tu dau)
        sessionStorage.removeItem("askedTopic");
        sessionStorage.removeItem("userTopics");
    }
    // chua chon topic  hien modal 
    if (!sessionStorage.getItem("askedTopic")) {
        modal.classList.add("active"); // mo modal
        
        /*
         * Css an hien modal  
         * @returns {undefined}
         * 
         * .tm-modal {
            display: none;
            }

            .tm-modal.active {
                display: flex; 
    }
         */
    }
    //load trang => check login => reset => nếu chưa chọn => hiện modal
});

//change MODAL
function changeTopic() {

    if (!confirm("Bạn muốn chọn lại chủ đề nhạc?")) {
        return;
    }
    //xoa topic da chon
    selectedTopics = []; // rest mang
    //xoa topic da luu
    sessionStorage.removeItem("userTopics");
    //reset UI card
    document.querySelectorAll(".tm-card").forEach(card => {
        card.classList.remove("active");
    });
    //mo lai modal
    document.getElementById("topicModal").classList.add("active");
}

// ================== CLOSE MODAL ==================
function closeTopicModal() {
    const modal = document.getElementById("topicModal");
    if (modal)
        modal.classList.remove("active"); // an modal 
}

// click ngoài để đóng
document.addEventListener("click", (e) => {
    const modal = document.getElementById("topicModal");
    if (e.target === modal) {
        closeTopicModal();
    }
});

// ================== SELECT MULTIPLE ==================

//user chon topic 
function toggleTopic(el) {
    //lay id topic 
    const id = el.dataset.id;

    // luu state 
    if (el.classList.contains("active")) { // neu da chon 
        el.classList.remove("active"); // 
        selectedTopics = selectedTopics.filter(t => t !== id);  // xoa id khoi mang
    } else {
        // add vao mang 
        el.classList.add("active");
        selectedTopics.push(id);
    }
}

// ================== SUBMIT ==================
// user an tiep tuc 
function submitTopics() {

    if (selectedTopics.length === 0) {
        alert("Chọn ít nhất 1 chủ đề!");
        return;
    }

    sessionStorage.setItem("userTopics", JSON.stringify(selectedTopics)); // luu topic usser da chon 

    //goi api
    // lay nhac 
    loadPlaylistByTopics(selectedTopics);

    // danh dau 
    sessionStorage.setItem("askedTopic", "true");
    //dong modal;
    closeTopicModal();
}

// ================== SKIP ==================
function skipTopic() {

    fetch("MainController?action=getRandomTopic") // G?i API lay topic Ramdom GET MainController?action=getRandomTopic
            .then(res => res.json()) // convert respon thanh json object
            .then(topic => { //topic = object nhận từ server

                // khong co topic dung
                if (!topic) {
                    alert("Không có topic!");
                    return;
                }

                // luu topic ramdom vao mang 
                // JSON.stringify([topic.topicID] convert thanh array 
                sessionStorage.setItem("userTopics", JSON.stringify([topic.topicID]));

                // load nhac theo topic 
                loadPlaylistByTopic(topic.topicID);

                //danh dau hoi 
                sessionStorage.setItem("askedTopic", "true");
                // dong topic 
                closeTopicModal();
            })
            .catch(err => console.error("Lỗi random topic:", err)); // in loi debug 

    /*
     * 
     * @param {type} topicIds
     * @returns {undefined}
     * User bấm skip 
     ↓
     fetch getRandomTopic
     ↓
     nhận topic random
     ↓
     lưu sessionStorage
     ↓
     gọi loadPlaylistByTopic()
     ↓
     đóng modal
     ↓
     play nhạc ?
     */
}

// ================== LOAD MULTIPLE TOPICS ==================
function loadPlaylistByTopics(topicIds) { // nhan ds topic user da chon 

    /*
     * Goi  API 
     * ${topicIds.join(",")} - chuyen array thanh String 
     * GET MainController?action=getSongsByMultipleTopics&topicIds=1,2,3
     */
    fetch(`MainController?action=getSongsByMultipleTopics&topicIds=${topicIds.join(",")}`)
            .then(res => res.json()) // chyen resqone - json object 
            .then(list => { //list = danh sach bai hat tu sever 

                //check ds ton tai 
                if (!list || list.length === 0) {
                    alert("❌ Không có bài hát!");
                    return;
                }

                //neu co goi function build player + play nhac 
                buildAndLoadPlayer(list);
            })
            .catch(err => console.error("❌ Lỗi multi-topic:", err)); // bat loi 

    /*
     User chọn topic
     ↓
     submitTopics()
     ↓
     loadPlaylistByTopics()
     ↓
     fetch API
     ↓
     nhận JSON
     ↓
     build player
     ↓
     play nhạc 
     */
}

// ================== LOAD SINGLE TOPIC (OPTIONAL) ==================
function loadPlaylistByTopic(topicID) { // nhan topic da duoc chon

    fetch(`MainController?action=getSongsByTopic&topicID=${topicID}`) //Goi API gui request GET MainController?action=getSongsByTopic&topicID=5
            .then(res => res.json()) // gui respon - js object 
            .then(list => { // ds bai hat 

//                console.log("🔥 LIST NHẬN ĐƯỢC:", list);
//
//                if (list) {
//                    console.log("🔥 LIST LENGTH:", list.length);
//                } else {
//                    console.log("🔥 LIST đang là null hoặc undefined");
//                }
                
                //check litst
                if (!list || list.length === 0) {
                    alert("❌ Không có bài!");
                    return;
                }
                
                // goi func build + play nhac 
                buildAndLoadPlayer(list);
            })
            .catch(err => console.error("❌ Lỗi topic:", err)); // bao loi debug 
    
    //// loadPlaylistByTopic() -  fetch API - nhan json - check du lieu -- build - play nhac 
}

// ================== BUILD PLAYER ==================
function buildAndLoadPlayer(list) { // nhan ds bai hat 

    // nếu playlist đang chạy thì KHÔNG rebuild
    //    if (window.isPlayerInitialized) return; 

    // lay context path tu jsp 
    ///data-context="${pageContext.request.contextPath}"
    // build URL dung
    let contextPath = document.body.dataset.context;

    playlist = list.map(song => ({ // map json // tao mang playlist
            songID: song.songID, // id bai hat
            audioURL: contextPath + "/StreamServlet?type=audio&file=" + song.audioURL, // build link srteam audio tu sever 
            ///audio/song.mp3 - StreamServlet?type=audio&file=song.mp3
            title: song.title, // ten bai hat 
            coverURL: contextPath + "/StreamServlet?type=cover&file=" + song.coverImage // hinh anh bai hat 
        }));

    currentIndex = 0; // bat dau tu bai dau tien 

    playPlaylistSong(currentIndex); // goi function play 

    // đánh dấu player đã khởi tạo
    window.isPlayerInitialized = true;
    
    /*
     JSON list
        ↓
    map → playlist
        ↓
    set index = 0
        ↓
    play bài đầu 
     */
}