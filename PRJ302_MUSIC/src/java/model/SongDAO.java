/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;
import utils.DbUtils;

/**
 *
 * @author NQ9
 *
 * -------------------1.getAllActiveSongs()
 *
 * Lấy toàn bộ bài hát đang active.
 *
 * -------------------2.getAllSongs()
 *
 * Dành cho admin (bao gồm inactive).
 *
 * -------------------3.getSongByID(int songID)
 *
 * Lấy chi tiết 1 bài hát.
 *
 * -------------------4.createSong(SongDTO song)
 *
 * Admin thêm bài hát.
 *
 * -------------------5.updateSong(SongDTO song)
 *
 * Admin cập nhật title, lyric, audioURL, releaseDate…
 *
 * -------------------6.updateSongStatus(int songID, boolean isActive)
 *
 * Soft delete.
 */
public class SongDAO {

    public List<SongDTO> getAllActiveSongs() {
        List<SongDTO> song = new ArrayList<>();
        try {
            Connection conn = DbUtils.getConnection();
            String sql = "SELECT songID, title, duration, audioURL, lyric, releaseDate, coverImage, isActive"
                    + " FROM SONG WHERE isActive=1";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
//    private int songID;
//    private String title;
//    private int duration;
//    private String audioURL, lyric;
//    private Date releaseDate;
//    private String coverImage;
//    private boolean isActive;
                int songID = rs.getInt("songID");
                String title = rs.getString("title");
                int duration = rs.getInt("duration");
                String audioURL = rs.getString("audioURL");
                String lyric = rs.getString("lyric");
                Date releaseDate = rs.getDate("releaseDate");
                String coverImage = rs.getString("coverImage");
                boolean isActive = rs.getBoolean("isActive");
                SongDTO s = new SongDTO(songID, title, duration, audioURL, lyric, releaseDate, coverImage, isActive);
                song.add(s);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return song;
    }

    public List<SongDTO> getAllSongs(String keyword) {
        List<SongDTO> song = new ArrayList<>();
        try {
            Connection conn = DbUtils.getConnection();
            String sql = "SELECT songID, title, duration, audioURL, lyric, releaseDate, coverImage, isActive"
                    + " FROM SONG";

            if (keyword != null && !keyword.trim().isEmpty()) {
                sql += " WHERE title LIKE ?";
            }

            PreparedStatement ps = conn.prepareStatement(sql);

            if (keyword != null && !keyword.trim().isEmpty()) {
                ps.setString(1, "%" + keyword + "%");
            }

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int songID = rs.getInt("songID");
                String title = rs.getString("title");
                int duration = rs.getInt("duration");
                String audioURL = rs.getString("audioURL");
                String lyric = rs.getString("lyric");
                Date releaseDate = rs.getDate("releaseDate");
                String coverImage = rs.getString("coverImage");
                boolean isActive = rs.getBoolean("isActive");
                SongDTO s = new SongDTO(songID, title, duration, audioURL, lyric, releaseDate, coverImage, isActive);
                song.add(s);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return song;
    }

    public SongDTO getSongByID(int songID) {
        SongDTO song = null;
        try {
            Connection conn = DbUtils.getConnection();
            String sql = "SELECT songID, title, duration, audioURL, lyric, releaseDate, coverImage, isActive"
                    + " FROM SONG WHERE songID=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, songID);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int songIDFromDB = rs.getInt("songID");
                String title = rs.getString("title");
                int duration = rs.getInt("duration");
                String audioURL = rs.getString("audioURL");
                String lyric = rs.getString("lyric");
                Date releaseDate = rs.getDate("releaseDate");
                String coverImage = rs.getString("coverImage");
                boolean isActive = rs.getBoolean("isActive");
                song = new SongDTO(songIDFromDB, title, duration, audioURL, lyric, releaseDate, coverImage, isActive);
            }
            System.out.println(song);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return song;
    }

    public boolean createSong(SongDTO song) {
        try {
            Connection conn = DbUtils.getConnection();
            String sql = "INSERT INTO SONG(title, duration, audioURL, lyric, releaseDate, coverImage, isActive) "
                    + "VALUES(?,?,?,?,?,?,?)";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, song.getTitle());
            ps.setInt(2, song.getDuration());
            ps.setString(3, song.getAudioURL());
            ps.setString(4, song.getLyric());
            ps.setDate(5, song.getReleaseDate());
            ps.setString(6, song.getCoverImage());
            ps.setInt(7, song.isIsActive() ? 1 : 0);

            int rows = ps.executeUpdate();

            return rows > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<SongDTO> searchSongs(String keyword) {
        if (keyword == null || keyword.trim().isEmpty()) {
            return getAllActiveSongs();
        }
        return searchSongsByTitle(keyword);
    }

    public List<SongDTO> searchSongsByTitle(String keyword) {
        List<SongDTO> song = new ArrayList<>();
        try {
            Connection conn = DbUtils.getConnection();
            String sql = "SELECT songID, title, duration, audioURL, lyric, releaseDate, coverImage, isActive"
                    + " FROM SONG WHERE isActive=1 AND title LIKE ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, "%" + keyword + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int songID = rs.getInt("songID");
                String title = rs.getString("title");
                int duration = rs.getInt("duration");
                String audioURL = rs.getString("audioURL");
                String lyric = rs.getString("lyric");
                Date releaseDate = rs.getDate("releaseDate");
                String coverImage = rs.getString("coverImage");
                boolean isActive = rs.getBoolean("isActive");
                SongDTO s = new SongDTO(songID, title, duration, audioURL, lyric, releaseDate, coverImage, isActive);
                song.add(s);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return song;
    }

    public int softDeleteSong(String songID) {

        try {
            Connection conn = DbUtils.getConnection();
            String sql = "UPDATE SONG SET isActive=0 WHERE songID = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, songID);
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public boolean updateSong(SongDTO s) {
        int result = 0;
        try {
            Connection conn = DbUtils.getConnection();

            String sql = "UPDATE SONG SET "
                    + "title=?, duration=?, audioURL=?, lyric=?, "
                    + "releaseDate=?, coverImage=? "
                    + "WHERE songID=?";

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, s.getTitle());
            ps.setInt(2, s.getDuration());
            ps.setString(3, s.getAudioURL());
            ps.setString(4, s.getLyric());
            ps.setDate(5, s.getReleaseDate());
            ps.setString(6, s.getCoverImage());
            ps.setInt(7, s.getSongID());

            result = ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result > 0;
    }

    public List<SongDTO> getRandomSongs(int limit) {
        List<SongDTO> list = new ArrayList<>();
        try {
            Connection conn = DbUtils.getConnection();

            String sql = "SELECT TOP " + limit + " * FROM SONG WHERE isActive=1 ORDER BY NEWID()";
            PreparedStatement ps = conn.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int songID = rs.getInt("songID");
                String title = rs.getString("title");
                int duration = rs.getInt("duration");
                String audioURL = rs.getString("audioURL");
                String lyric = rs.getString("lyric");
                Date releaseDate = rs.getDate("releaseDate");
                String coverImage = rs.getString("coverImage");
                boolean isActive = rs.getBoolean("isActive");
                SongDTO s = new SongDTO(songID, title, duration, audioURL, lyric, releaseDate, coverImage, isActive);
                list.add(s);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
