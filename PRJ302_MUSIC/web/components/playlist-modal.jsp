<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div id="playlistModal" class="modal">

    <div class="modal-content playlist-modal">

        <div class="modal-header">
            <h2>Tạo playlist mới</h2>
            <span class="close-btn" onclick="closePlaylist()">✕</span>
        </div>

        <form id="playlistForm">

            <input type="hidden" name="action" value="addPlaylist"/>

            <input
                type="text"
                name="playListName"
                class="playlist-input"
                placeholder="Nhập tên playlist"
                maxlength="100"
                required
                >

            <input type="hidden" name="source" value="user"/>

            <div class="playlist-radio">

                <label>
                    <input type="radio" name="isPublic" value="1" checked>
                    Công khai
                </label>

                <label>
                    <input type="radio" name="isPublic" value="0">
                    Riêng tư
                </label>

            </div>

            <div class="playlist-actions">

                <button type="button" onclick="closePlaylist()" class="btn-cancel">
                    Hủy
                </button>

                <button type="submit" class="btn-save">
                    Lưu
                </button>

            </div>

        </form>

    </div>

</div>