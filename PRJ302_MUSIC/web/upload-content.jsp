<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Upload</title>
        <link rel="stylesheet" href="assets/css/manage.css">
    </head>
    <body>
        <a href="MainController?action=manage_song" class="admin-back-btn">
            ⬅ Back to Songs
        </a>

        <div class="manage-song-upload">

            <form action="MainController" method="post"
                  enctype="multipart/form-data"
                  class="manage-song-upload-form">

                <input type="hidden" name="action" value="addSong">

                <div class="manage-song-upload-card">

                    <!-- LEFT -->
                    <div class="manage-song-upload-left">

                        <div class="manage-song-upload-cover-preview" id="coverPreview">
                            <span>Chọn ảnh bìa</span>
                        </div>

                        <input type="file"
                               name="coverImage"
                               id="coverInput"
                               accept="image/*"
                               hidden>

                        <button type="button"
                                class="manage-song-upload-cover-btn"
                                onclick="document.getElementById('coverInput').click()">
                            Chọn ảnh bìa
                        </button>

                    </div>

                    <!-- RIGHT -->
                    <div class="manage-song-upload-right">

                        <h2>Đăng tải bài hát</h2>

                        <div class="manage-song-upload-group">
                            <label>Tên bài hát</label>
                            <input type="text" name="title" required>
                        </div>

                        <div class="manage-song-upload-row">

                            <div class="manage-song-upload-group">
                                <label>Thời lượng (giây)</label>
                                <input type="number" name="duration" min="1">
                            </div>

                            <div class="manage-song-upload-group">
                                <label>Ngày phát hành</label>
                                <input type="date" name="releaseDate" required>
                            </div>

                        </div>

                        <div class="manage-song-upload-group">
                            <label>File nhạc (.mp3)</label>
                            <input type="file" name="audioURL" accept=".mp3" required>
                        </div>

                        <div class="manage-song-upload-group">
                            <label>Lời bài hát</label>
                            <textarea name="lyric"></textarea>
                        </div>

                        <div class="manage-song-upload-group">
                            <label>Trạng thái</label>

                            <select name="isActive" required>
                                <option value="1">Hiển thị</option>
                                <option value="0">Ẩn</option>
                            </select>

                        </div>

                        <button type="submit" class="manage-song-upload-btn">
                            ⬆ Đăng tải bài hát
                        </button>

                        <p class="manage-song-upload-success">${msg}</p>
                        <p class="manage-song-upload-error">${error}</p>

                    </div>

                </div>

            </form>

        </div>

        <script>
            document.getElementById("coverInput").addEventListener("change", function (e) {

                const file = e.target.files[0];
                if (!file)
                    return;

                const reader = new FileReader();

                reader.onload = function (event) {
                    document.getElementById("coverPreview").innerHTML =
                            "<img src='" + event.target.result + "'>";
                };

                reader.readAsDataURL(file);

            });
        </script>

    </body>
</html>