<%-- 
    Document   : playlist-addsong-modal
    Created on : Mar 10, 2026, 1:02:22 AM
    Author     : NQ9
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div id="addSongModal" class="add-song-modal">

    <div class="add-song-box">

        <div class="add-song-header">

            <span onclick="closeAddSongModal()">←</span>

            <h2>Yêu Thích</h2>

            <span onclick="closeAddSongModal()">✕</span>

        </div>

        <div id="addSongList" class="add-song-list">

        </div>

    </div>

</div>