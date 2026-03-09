/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import utils.DbUtils;

/**
 *
 * @author NQ9
 */
public class FavoriteSongDAO {

    public boolean toggleFavorite(String userID, int songID) {

        boolean liked = false;

        try {

            Connection conn = DbUtils.getConnection();

            // kiểm tra đã tồn tại chưa
            PreparedStatement check = conn.prepareStatement(
                    "SELECT * FROM FAVORITE_SONG WHERE userID=? AND songID=?"
            );

            check.setString(1, userID);
            check.setInt(2, songID);

            ResultSet rs = check.executeQuery();

            if (rs.next()) {

                // đã tồn tại → xóa
                PreparedStatement delete = conn.prepareStatement(
                        "DELETE FROM FAVORITE_SONG WHERE userID=? AND songID=?"
                );

                delete.setString(1, userID);
                delete.setInt(2, songID);
                delete.executeUpdate();

                liked = false;

            } else {

                // chưa có → thêm
                PreparedStatement insert = conn.prepareStatement(
                        "INSERT INTO FAVORITE_SONG(userID, songID) VALUES (?,?)"
                );

                insert.setString(1, userID);
                insert.setInt(2, songID);
                insert.executeUpdate();

                liked = true;

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return liked;

    }

    public List<SongDTO> getFavoriteSongs(String userID) {

        List<SongDTO> list = new ArrayList<>();

        try {

            Connection conn = DbUtils.getConnection();

            String sql = " SELECT s.* FROM SONG s JOIN FAVORITE_SONG f ON s.songID = f.songID WHERE f.userID = ? ";

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, userID);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                SongDTO s = new SongDTO(
                        rs.getInt("songID"),
                        rs.getString("title"),
                        rs.getInt("duration"),
                        rs.getString("audioURL"),
                        rs.getString("lyric"),
                        rs.getDate("releaseDate"),
                        rs.getString("coverImage"),
                        rs.getBoolean("isActive")
                );

                list.add(s);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    public boolean isFavorite(String userID, int songID) {

        boolean liked = false;

        try {

            Connection conn = DbUtils.getConnection();

            PreparedStatement ps = conn.prepareStatement(
                    "SELECT * FROM FAVORITE_SONG WHERE userID=? AND songID=?"
            );

            ps.setString(1, userID);
            ps.setInt(2, songID);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                liked = true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return liked;
    }

}
