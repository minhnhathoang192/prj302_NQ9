<%-- 
    Document   : admin
    Created on : Feb 26, 2026, 2:52:04 PM
    Author     : NQ9
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
    <title>Admin Dashboard</title>

    <!-- Bootstrap -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">

    <style>
        body {
            display: flex;
        }
        .sidebar {
            width: 220px;
            height: 100vh;
            background: #1e3748;
            color: white;
            padding: 20px;
        }
        .sidebar div {
            margin: 10px 0;
            cursor: pointer;
        }
        .content {
            flex: 1;
            padding: 20px;
        }
    </style>
</head>

<body>

<!-- SIDEBAR -->
<div class="sidebar">
    <h4>⚙ Admin</h4>

    <div onclick="loadPage('user')">👤 Users</div>
    <div onclick="loadPage('song')">🎵 Songs</div>
    <div onclick="loadPage('album')">💿 Albums</div>
    <div onclick="loadPage('artist')">🎤 Artists</div>
    <div onclick="loadPage('comment')">💬 Comments</div>
</div>

<!-- CONTENT -->
<div class="content" id="contentArea">

    <h3>Welcome Admin</h3>

</div>

<script>
    function loadPage(type) {
        window.location = "MainController?action=manage_" + type;
    }
</script>

</body>
</html>
