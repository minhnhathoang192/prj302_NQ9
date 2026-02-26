<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<h2>Edit Song</h2>

<form action="MainController" method="post">

    <input type="hidden" name="action" value="${mode== 'edit' ? 'saveSong' : 'addSong'}"/>

    ID:
    <input type="text" name="songID" value="${s.songID}" ${mode== 'edit' ? 'readonly' : ''}/> <br/>

    Title:
    <input type="text" name="title" value="${s.title}"/> <br/>

    Duration:
    <input type="number" name="duration" value="${s.duration}"/> <br/>

    Audio URL:
    <input type="text" name="audioURL" value="${s.audioURL}"/> <br/>

    Lyric:
    <textarea name="lyric">${s.lyric}</textarea> <br/>

    Release Date:
    <input type="date" name="releaseDate" value="${s.releaseDate}"/> <br/>

    Cover Image:
    <input type="text" name="coverImage" value="${s.coverImage}"/> <br/><br/>

    <c:choose>
        <c:when test="${mode == 'edit'}">
            <input type="submit" value="Update"/>
        </c:when>
        <c:otherwise>
            <input type="submit" value="add"/>
        </c:otherwise>
    </c:choose>
</form>

<p style="color: green">${msg}</p>
<p style="color: red">${error}</p>