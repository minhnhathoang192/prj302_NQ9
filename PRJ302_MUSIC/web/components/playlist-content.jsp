<%-- 
    Document   : playlist-content
    Created on : Mar 8, 2026, 8:04:27 PM
    Author     : NQ9
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<section class="pl-page">

    <!-- HEADER -->
    <div class="pl-header">

        <!-- COVER -->
        <div class="pl-cover">
            <img src="assets/img/default-playlist.png" id="playlistCover">
        </div>

        <!-- INFO -->
        <div class="pl-info">

            <span class="pl-type">Playlist</span>

            <h1 id="playlistTitle">Playlist Name</h1>

            <div class="pl-owner-row">

                <p class="pl-owner">
                    Created by you • <span id="playlistSongCount">0</span> songs
                </p>

                <div class="pl-more">

                    <button class="pl-more-btn" onclick="togglePlaylistMenu(event)">
                        ⋯
                    </button>

                    <div id="playlistMenu" class="pl-menu">

                        <div class="pl-menu-item" onclick="editPlaylist()">
                            ✏ Chỉnh sửa playlist
                        </div>

                        <div class="pl-menu-item danger" onclick="deletePlaylist()">
                            🗑 Xóa playlist
                        </div>

                    </div>

                </div>

            </div>

            <div class="pl-actions">

                <button class="pl-btn pl-play">
                    ▶ Phát tất cả
                </button>

                <button class="pl-btn pl-add" onclick="openAddSongModal()">
                    ➕ Thêm bài hát
                </button>

            </div>

        </div>

    </div>

    <!-- SONG TABLE -->
    <div class="pl-table">

        <div class="pl-table-header">
            <div>#</div>
            <div>Tiêu đề</div>
            <div>Nghệ sĩ</div>
            <div>Thời gian</div>
            <div></div>
        </div>

        <div id="playlistSongs">

            <div class="pl-empty">

                <div class="pl-empty-icon">📦</div>

                <h3>Playlist trống</h3>

                <p>Hãy thêm bài hát vào playlist</p>

                <button class="pl-add-song-btn">
                    Thêm bài hát
                </button>

            </div>

        </div>

    </div>

</section>