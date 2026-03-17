<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <title>Edit Song</title>
        <link rel="stylesheet" href="assets/css/manage.css">
    </head>
    <body>
        <a href="MainController?action=manage_song" class="admin-back-btn">
            ⬅ Back to Songs
        </a>

        <div class="song-manage-edit">

            <form action="MainController" method="post"
                  enctype="multipart/form-data"
                  class="song-manage-edit-form">

                <input type="hidden" name="action" value="saveSong"/>
                <input type="hidden" name="songID" value="${s.songID}"/>

                <div class="song-manage-edit-card">

                    <!-- LEFT SIDE -->
                    <div class="song-manage-edit-left">

                        <div class="song-manage-edit-cover-preview" id="coverPreview">

                            <c:choose>
                                <c:when test="${not empty s.coverImage}">
                                    <img src="${s.coverImage}">
                                </c:when>
                                <c:otherwise>
                                    <span>Chọn ảnh bìa</span>
                                </c:otherwise>
                            </c:choose>

                        </div>

                        <input type="file"
                               name="coverImage"
                               id="coverInput"
                               accept="image/*"
                               hidden>

                        <button type="button"
                                class="song-manage-edit-cover-btn"
                                onclick="document.getElementById('coverInput').click()">

                            Chọn ảnh bìa

                        </button>

                    </div>

                    <!-- RIGHT SIDE -->
                    <div class="song-manage-edit-right">

                        <h2>Chỉnh sửa bài hát</h2>

                        <div class="song-manage-edit-group">
                            <label>ID</label>
                            <input type="text" value="${s.songID}" readonly>
                        </div>

                        <div class="song-manage-edit-group">
                            <label>Tên bài hát</label>
                            <input type="text" name="title" value="${s.title}" required>
                        </div>

                        <div class="song-manage-edit-row">

                            <div class="song-manage-edit-group">
                                <label>Thời lượng (giây)</label>
                                <input type="number" name="duration" value="${s.duration}">
                            </div>

                            <div class="song-manage-edit-group">
                                <label>Ngày phát hành</label>
                                <input type="date" name="releaseDate" value="${s.releaseDate}">
                            </div>

                        </div>

                        <div class="song-manage-edit-group">
                            <label>File nhạc (.mp3)</label>
                            <input type="file" name="audioURL" accept=".mp3">
                            <p class="song-manage-edit-note">
                                Để trống nếu không muốn thay file nhạc
                            </p>
                        </div>

                        <div class="song-manage-edit-group">
                            <label>Lời bài hát</label>
                            <textarea name="lyric">${s.lyric}</textarea>
                        </div>

                        <div class="song-manage-edit-group">
                            <label>Trạng thái</label>

                            <select name="isActive">

                                <option value="1" ${s.isActive ? 'selected' : ''}>
                                    Hiển thị
                                </option>

                                <option value="0" ${!s.isActive ? 'selected' : ''}>
                                    Ẩn
                                </option>

                            </select>

                        </div>

                        <button type="submit" class="song-manage-edit-btn">
                            💾 Cập nhật bài hát
                        </button>

                        <p class="song-manage-edit-success">${msg}</p>
                        <p class="song-manage-edit-error">${error}</p>

                    </div>

                </div>

            </form>

            <h3>Artists In Song</h3>

            <c:choose>

                <c:when test="${empty artists}">
                    <p>No artists for this song</p>
                </c:when>

                <c:otherwise>

                    <table class="song-manage-edit-table">

                        <tr>
                            <th>ID</th>
                            <th>Name</th>
                            <th>Remove</th>
                        </tr>

                        <c:forEach var="a" items="${artists}">

                            <tr>

                                <td>${a.artistID}</td>

                                <td>${a.artistName}</td>

                                <td>

                                    <form action="MainController" method="post">

                                        <input type="hidden" name="action" value="removeArtistFromSong">

                                        <input type="hidden" name="songID" value="${s.songID}">

                                        <input type="hidden" name="artistID" value="${a.artistID}">

                                        <button type="submit">Remove</button>

                                    </form>

                                </td>

                            </tr>

                        </c:forEach>

                    </table>

                </c:otherwise>

            </c:choose>


            <h3>Albums Containing Song</h3>

            <c:choose>

                <c:when test="${empty albums}">
                    <p>This song is not in any album</p>
                </c:when>

                <c:otherwise>

                    <table class="song-manage-edit-table" >

                        <tr>
                            <th>ID</th>
                            <th>Album Name</th>
                            <th>Remove</th>
                        </tr>

                        <c:forEach var="al" items="${albums}">

                            <tr>

                                <td>${al.albumID}</td>

                                <td>${al.albumName}</td>

                                <td>

                                    <form action="MainController" method="post">

                                        <input type="hidden" name="action" value="removeSongFromAlbum">

                                        <input type="hidden" name="songID" value="${s.songID}">

                                        <input type="hidden" name="albumID" value="${al.albumID}">

                                        <button type="submit">Remove</button>

                                    </form>

                                </td>

                            </tr>

                        </c:forEach>

                    </table>

                </c:otherwise>

            </c:choose>

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