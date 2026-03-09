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
public class listening_historyDAO {

    //add 
    public boolean addHistory(String userID, int songID) {
        try {
            String sql
                    = "MERGE LISTENING_HISTORY AS target "
                    + "USING (SELECT ? AS userID, ? AS songID) AS source "
                    + "ON target.userID = source.userID AND target.songID = source.songID "
                    + "WHEN MATCHED THEN "
                    + "    UPDATE SET listenedAt = GETDATE() "
                    + "WHEN NOT MATCHED THEN "
                    + "    INSERT (userID, songID, listenedAt) "
                    + "    VALUES (source.userID, source.songID, GETDATE());";

            Connection con = DbUtils.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, userID);
            ps.setInt(2, songID);

            return ps.executeUpdate() > 0;
        } catch (Exception e) {
        }
        return false;
    }

    // loadpage
    public List<SongDTO> getListeningHistory(String userID) {
        List<SongDTO> list = new ArrayList<>();
        try {
            Connection conn = DbUtils.getConnection();
            String sql
                    = "SELECT TOP 50 s.songID, s.title, s.duration, s.audioURL, s.lyric, "
                    + "s.releaseDate, s.coverImage, s.isActive "
                    + "FROM SONG s "
                    + "JOIN ( "
                    + "    SELECT songID, MAX(listenedAt) AS lastListen "
                    + "    FROM LISTENING_HISTORY "
                    + "    WHERE userID = ? "
                    + "    GROUP BY songID "
                    + ") h ON s.songID = h.songID "
                    + "WHERE s.isActive = 1 "
                    + "ORDER BY h.lastListen DESC";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, userID);
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
