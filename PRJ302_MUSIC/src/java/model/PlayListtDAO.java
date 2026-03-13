/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import utils.DbUtils;

/**
 *
 * @author NQ9
 */
public class PlayListtDAO {

    public PlayListtDTO getPlaylistByID(int id) {
        PlayListtDTO playlist = null;
        try {
            Connection conn = DbUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(
                    "SELECT * FROM PLAYLIST WHERE playListID=?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int playListID = rs.getInt("playListID");
                String playListName = rs.getString("playListName");
                int userID = rs.getInt("userID");
                boolean isPublic = rs.getBoolean("isPublic");
                Timestamp createDate = rs.getTimestamp("createDate");
                playlist = new PlayListtDTO(playListID, playListName, userID, isPublic, createDate);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return playlist;
    }

    public List<PlayListtDTO> getAllPlayList(String keyword) {
        List<PlayListtDTO> list = new ArrayList<>();
        try {
            Connection conn = DbUtils.getConnection();
            String sql = "SELECT * FROM PLAYLIST ";
            if (keyword != null && !keyword.trim().isEmpty()) {
                sql += " WHERE playListName LIKE ? OR CAST(playListID AS VARCHAR) LIKE ? ";
            }

            PreparedStatement ps = conn.prepareStatement(sql);

            if (keyword != null && !keyword.trim().isEmpty()) {
                ps.setString(1, "%" + keyword + "%");
                ps.setString(2, "%" + keyword + "%");
            }

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int playListID = rs.getInt("playListID");
                String playListName = rs.getString("playListName");
                int userID = rs.getInt("userID");
                boolean isPublic = rs.getBoolean("isPublic");
                Timestamp createDate = rs.getTimestamp("createDate");
                PlayListtDTO p = new PlayListtDTO(playListID, playListName, userID, isPublic, createDate);
                list.add(p);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    public int countSongsInPlaylist(int playListID) {
        try {
            Connection conn = DbUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(
                    "SELECT COUNT(*) FROM PLAYLIST_SONG WHERE playListID=?");
            ps.setInt(1, playListID);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public boolean createPlaylist(PlayListtDTO playlist) {

        try {

            Connection conn = DbUtils.getConnection();

            String sql = "INSERT INTO PLAYLIST(playListName,userID,isPublic) VALUES(?,?,?)";

            PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            ps.setString(1, playlist.getPlayListName());
            ps.setInt(2, playlist.getUserID());
            ps.setBoolean(3, playlist.isIsPublic());

            int rows = ps.executeUpdate();

            if (rows > 0) {

                ResultSet rs = ps.getGeneratedKeys();

                if (rs.next()) {
                    int newID = rs.getInt(1);
                    playlist.setPlayListID(newID);   //  gán ID mới
                }

                return true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public List<SongDTO> getSongsInPlaylist(int playListID) {

        List<SongDTO> list = new ArrayList<>();

        try {
            Connection conn = DbUtils.getConnection();

            String sql = "SELECT s.* "
                    + "FROM SONG s "
                    + "JOIN PLAYLIST_SONG ps ON s.songID = ps.songID "
                    + "WHERE ps.playListID = ? "
                    + "ORDER BY ps.addDate DESC";

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, playListID);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                SongDTO song = new SongDTO(
                        rs.getInt("songID"),
                        rs.getString("title"),
                        rs.getInt("duration"),
                        rs.getString("audioURL"),
                        rs.getString("lyric"),
                        rs.getDate("releaseDate"),
                        rs.getString("coverImage"),
                        rs.getBoolean("isActive")
                );

                list.add(song);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
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
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean editPlaylist(int playlistID, String userID, String name, boolean isPublic) {

        try {

            Connection conn = DbUtils.getConnection();

            String sql = "UPDATE PLAYLIST "
                    + "SET playlistName=?, isPublic=? "
                    + "WHERE playlistID=? AND userID=?";

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, name);
            ps.setBoolean(2, isPublic);
            ps.setInt(3, playlistID);
            ps.setString(4, userID);

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

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
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean deletePlaylist(int playlistID, String userID) {

        try {

            Connection conn = DbUtils.getConnection();

            // xóa bài trong playlist trước
            String sql1 = "DELETE FROM PLAYLIST_SONG WHERE playlistID=?";
            PreparedStatement ps1 = conn.prepareStatement(sql1);
            ps1.setInt(1, playlistID);
            ps1.executeUpdate();

            // xóa playlist
            String sql2 = "DELETE FROM PLAYLIST WHERE playlistID=? AND userID=?";
            PreparedStatement ps2 = conn.prepareStatement(sql2);

            ps2.setInt(1, playlistID);
            ps2.setString(2, userID);

            return ps2.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public List<PlayListtDTO> getPlaylistsByUser(int userID) {

        List<PlayListtDTO> list = new ArrayList<>();

        try {

            Connection conn = DbUtils.getConnection();

            String sql = "SELECT * FROM PLAYLIST WHERE userID=? ORDER BY createDate DESC";

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, userID);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                PlayListtDTO p = new PlayListtDTO(
                        rs.getInt("playListID"),
                        rs.getString("playListName"),
                        rs.getInt("userID"),
                        rs.getBoolean("isPublic"),
                        rs.getTimestamp("createDate")
                );

                list.add(p);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    public boolean addSongToPlaylist(int playListID, int songID) {
        try {

            Connection conn = DbUtils.getConnection();

            String sql = "INSERT INTO PLAYLIST_SONG(playListID, songID) VALUES(?,?)";

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, playListID);
            ps.setInt(2, songID);

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean checkSongExists(int playListID, int songID) {

        try {

            Connection conn = DbUtils.getConnection();

            String sql = "SELECT 1 FROM PLAYLIST_SONG WHERE playListID=? AND songID=?";

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, playListID);
            ps.setInt(2, songID);

            ResultSet rs = ps.executeQuery();

            return rs.next();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public List<PlayListtDTO> searchPlaylists(String keyword) {
        List<PlayListtDTO> list = new ArrayList<>();
        try {
            Connection conn = DbUtils.getConnection();
            String[] tokens = keyword.trim().split("\\s+");
            StringBuilder sql = new StringBuilder("SELECT DISTINCT p.*"
                    + "            FROM PLAYLIST p"
                    + "            LEFT JOIN PLAYLIST_SONG ps ON p.playListID = ps.playListID"
                    + "            LEFT JOIN SONG s ON ps.songID = s.songID"
                    + "            WHERE p.isPublic = 1 AND (");
            
            for (int i = 0; i < tokens.length; i++) {
                if(i>0) sql.append(" OR ");
                sql.append("(p.playListName LIKE ? OR s.title LIKE ?)");
            }
            
            sql.append(")");
            
            PreparedStatement ps= conn.prepareStatement(sql.toString());
            
            int index = 1;
            for (String t : tokens) {
                String like = "%" + t + "%";
                ps.setString(index++, like);
                ps.setString(index++, like);
            }
            
            ResultSet rs= ps.executeQuery();
            while(rs.next()){
                int playListID= rs.getInt("playListID");
                String playListName= rs.getString("playListName");
                int userID = rs.getInt("userID");
                boolean isPublic = rs.getBoolean("isPublic");
                Timestamp createDate= rs.getTimestamp("createDate");
                PlayListtDTO p= new PlayListtDTO(playListID, playListName, userID, isPublic, createDate);
                list.add(p);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
