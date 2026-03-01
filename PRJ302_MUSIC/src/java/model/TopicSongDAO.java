/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import utils.DbUtils;

/**
 *
 * @author NQ9
 */
public class TopicSongDAO {

    public boolean addSongToTopic(int topicID, int songID) {
        try {
            Connection conn = DbUtils.getConnection();
            String sql = "INSERT INTO "
                    + "TOPIC_SONG("
                    + "topicID, "
                    + "songID, "
                    + "addedAt) "
                    + "VALUES (?, ?, GETDATE())";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, topicID);
            ps.setInt(2, songID);

            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean removeSongFromTopic(int topicID, int songID) {
        try {
            Connection conn = DbUtils.getConnection();
            String sql = "DELETE FROM "
                    + "TOPIC_SONG "
                    + "WHERE topicID = ? "
                    + "AND songID = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, topicID);
            ps.setInt(2, songID);

            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean exists(int topicID, int songID) {
        try {
            Connection conn = DbUtils.getConnection();
            String sql = "SELECT 1 FROM TOPIC_SONG WHERE topicID=? AND songID=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, topicID);
            ps.setInt(2, songID);
            ResultSet rs = ps.executeQuery();

            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<SongDTO> getSongsByTopic(int topicID) {
        List<SongDTO> list = new ArrayList<>();
        try {
            Connection conn = DbUtils.getConnection();
            String sql = "SELECT s.* "
                    + "FROM SONG s "
                    + "JOIN TOPIC_SONG ts ON s.songID = ts.songID "
                    + "WHERE ts.topicID = ? AND s.isActive = 1 "
                    + "ORDER BY ts.addedAt DESC";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, topicID);
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
