package model;

import java.sql.*;
import java.util.*;
import utils.DbUtils;

public class PlaylistDAO {

    private PlaylistDTO mapRow(ResultSet rs) throws SQLException {
        return new PlaylistDTO(
            rs.getInt("playListID"),
            rs.getString("playListName"),
            rs.getInt("userID"),
            rs.getBoolean("isPublic"),
            rs.getTimestamp("createDate")
        );
    }

    public List<PlaylistDTO> getPlaylistsByUser(int userID) {
        List<PlaylistDTO> list = new ArrayList<>();
        try {
            Connection conn = DbUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(
                "SELECT * FROM PLAYLIST WHERE userID=? ORDER BY createDate DESC");
            ps.setInt(1, userID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) list.add(mapRow(rs));
        } catch (Exception e) { e.printStackTrace(); }
        return list;
    }

    public PlaylistDTO getPlaylistByID(int playListID) {
        try {
            Connection conn = DbUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(
                "SELECT * FROM PLAYLIST WHERE playListID=?");
            ps.setInt(1, playListID);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) return mapRow(rs);
        } catch (Exception e) { e.printStackTrace(); }
        return null;
    }

    public boolean createPlaylist(String name, int userID, boolean isPublic) {
        try {
            Connection conn = DbUtils.getConnection();
            String sql = "INSERT INTO PLAYLIST(playListName, userID, isPublic) VALUES(?,?,?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, name);
            ps.setInt(2, userID);
            ps.setBoolean(3, isPublic);
            return ps.executeUpdate() > 0;
        } catch (Exception e) { e.printStackTrace(); }
        return false;
    }

    public boolean updatePlaylist(int playListID, String name, boolean isPublic) {
        try {
            Connection conn = DbUtils.getConnection();
            String sql = "UPDATE PLAYLIST SET playListName=?, isPublic=? WHERE playListID=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, name);
            ps.setBoolean(2, isPublic);
            ps.setInt(3, playListID);
            return ps.executeUpdate() > 0;
        } catch (Exception e) { e.printStackTrace(); }
        return false;
    }

    public boolean deletePlaylist(int playListID) {
        try {
            Connection conn = DbUtils.getConnection();
            // Xóa các bài hát trong playlist trước
            PreparedStatement ps1 = conn.prepareStatement(
                "DELETE FROM PLAYLIST_SONG WHERE playListID=?");
            ps1.setInt(1, playListID);
            ps1.executeUpdate();
            // Xóa playlist
            PreparedStatement ps2 = conn.prepareStatement(
                "DELETE FROM PLAYLIST WHERE playListID=?");
            ps2.setInt(1, playListID);
            return ps2.executeUpdate() > 0;
        } catch (Exception e) { e.printStackTrace(); }
        return false;
    }

    public boolean addSongToPlaylist(int playListID, int songID) {
        try {
            Connection conn = DbUtils.getConnection();
            String sql = "IF NOT EXISTS (SELECT 1 FROM PLAYLIST_SONG WHERE playListID=? AND songID=?) "
                + "INSERT INTO PLAYLIST_SONG(playListID, songID) VALUES(?,?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, playListID);
            ps.setInt(2, songID);
            ps.setInt(3, playListID);
            ps.setInt(4, songID);
            return ps.executeUpdate() > 0;
        } catch (Exception e) { e.printStackTrace(); }
        return false;
    }

    public boolean removeSongFromPlaylist(int playListID, int songID) {
        try {
            Connection conn = DbUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(
                "DELETE FROM PLAYLIST_SONG WHERE playListID=? AND songID=?");
            ps.setInt(1, playListID);
            ps.setInt(2, songID);
            return ps.executeUpdate() > 0;
        } catch (Exception e) { e.printStackTrace(); }
        return false;
    }

    public List<SongDTO> getSongsInPlaylist(int playListID) {
        List<SongDTO> list = new ArrayList<>();
        try {
            Connection conn = DbUtils.getConnection();
            String sql = "SELECT s.* FROM SONG s "
                + "JOIN PLAYLIST_SONG ps ON s.songID = ps.songID "
                + "WHERE ps.playListID=? AND s.isActive=1 ORDER BY ps.addDate DESC";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, playListID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new SongDTO(
                    rs.getInt("songID"), rs.getString("title"), rs.getInt("duration"),
                    rs.getString("audioURL"), rs.getString("lyric"),
                    rs.getDate("releaseDate"), rs.getString("coverImage"), rs.getBoolean("isActive")
                ));
            }
        } catch (Exception e) { e.printStackTrace(); }
        return list;
    }

    public int countSongsInPlaylist(int playListID) {
        try {
            Connection conn = DbUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(
                "SELECT COUNT(*) FROM PLAYLIST_SONG WHERE playListID=?");
            ps.setInt(1, playListID);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) return rs.getInt(1);
        } catch (Exception e) { e.printStackTrace(); }
        return 0;
    }
}
