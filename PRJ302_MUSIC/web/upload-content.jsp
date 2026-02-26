<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Upload</title>
        <link rel="stylesheet" href="assets/css/style.css"/>
    </head>
    <body>

        <div class="upload-page">

            <form action="MainController" method="post"
                  enctype="multipart/form-data" class="upload-form">

                <input type="hidden" name="action" value="addSong">
                
                <div class="upload-card">

                    <div class="upload-left">
                        <div class="cover-preview" id="coverPreview">
                            <span>Chọn ảnh bìa</span>
                        </div>

                        <input type="file" name="coverImage" id="coverInput"
                               accept="image/*" hidden >

                        <button type="button" class="cover-btn"
                                onclick="document.getElementById('coverInput').click()">
                            Chọn ảnh bìa
                        </button>
                    </div>

                    <div class="upload-right">

                        <h2>Đăng tải bài hát</h2>



                        <div class="form-group">
                            <label>Tên bài hát</label>
                            <input type="text" name="title" required>
                        </div>

                        <div class="form-row">
                            <div class="form-group">
                                <label>Thời lượng (giây)</label>
                                <input type="number" name="duration" min="1">
                            </div>

                            <div class="form-group">
                                <label>Ngày phát hành</label>
                                <input type="date" name="releaseDate" required>
                            </div>
                        </div>

                        <div class="form-group">
                            <label>File nhạc (.mp3)</label>
                            <input type="file" name="audioURL" accept=".mp3" required>
                        </div>

                        <div class="form-group">
                            <label>Lời bài hát</label>
                            <textarea name="lyric"></textarea>
                        </div>

                        <div class="form-group">
                            <label>Trạng thái</label>
                            <select name="isActive" required>
                                <option value="1">Hiển thị</option>
                                <option value="0">Ẩn</option>
                            </select>
                        </div>

                        <button type="submit" class="upload-btn">
                            ⬆ Đăng tải bài hát
                        </button>


                        <p class="success-msg">${msg}</p>
                        <p class="error-msg">${error}</p>

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