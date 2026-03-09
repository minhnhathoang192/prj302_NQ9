<%-- 
    Document   : edit-playlist-modal
    Created on : Mar 10, 2026, 2:29:14 AM
    Author     : NQ9
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!-- EDIT PLAYLIST MODAL -->
<div id="editPlaylistModal" class="edit-modal">

    <div class="edit-box">

        <h2>Edit playlist</h2>

        <div class="edit-content">

            <!-- COVER -->
            <div class="edit-cover">
                <img id="editPlaylistCover" src="assets/img/default-playlist.png">
            </div>

            <!-- FORM -->
            <div class="edit-form">

                <input type="text"
                       id="editPlaylistName"
                       maxlength="100"
                       placeholder="Playlist name">

                <div class="privacy">

                    <p>Cài đặt playlist ở chế độ</p>

                    <label>
                        <input type="radio" name="privacy" value="public" checked>
                        Công khai
                    </label>

                    <label>
                        <input type="radio" name="privacy" value="private">
                        Riêng tư
                    </label>

                </div>

            </div>

        </div>

        <!-- BUTTONS -->
        <div class="edit-actions">

            <button class="btn-cancel" onclick="closeEditPlaylist()">
                Hủy
            </button>

            <button class="btn-save" onclick="savePlaylistEdit()">
                Lưu
            </button>

        </div>

    </div>

</div>
