/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import utils.DbUtils;

/**
 *
 * @author NQ9
 */
public class ArtistFollowDAO {

    public boolean toggleFollow(String userID, int artistID) {
        try {
            Connection conn = DbUtils.getConnection();
            //check 
            String check = "SELECT * FROM ARTIST_FOLLOW WHERE userID = ? AND artistID = ?";
            PreparedStatement ps = conn.prepareStatement(check);
            ps.setString(1, userID);
            ps.setInt(2, artistID);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                //da follow - > unfollow
                String delete = "DELETE FROM ARTIST_FOLLOW WHERE userID = ? AND artistID = ?";
                PreparedStatement ps2 = conn.prepareStatement(delete);
                ps2.setString(1, userID);
                ps2.setInt(2, artistID);

                ps2.executeUpdate();
                return false;
            } else {
                //chua follow
                String insert = "INSERT INTO ARTIST_FOLLOW(userID,artistID) VALUES(?,?)";
                PreparedStatement ps2 = conn.prepareStatement(insert);
                ps2.setString(1, userID);
                ps2.setInt(2, artistID);
                ps2.executeUpdate();
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public int getFollowerCount(int artistID) {
        int count = 0;

        try {
            Connection conn = DbUtils.getConnection();

            String sql = "SELECT COUNT(*) FROM ARTIST_FOLLOW WHERE artistID = ?";

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, artistID);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                count = rs.getInt(1);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return count;
    }

    public boolean isFollowing(String userID, int artistID) {

        try {
            Connection conn = DbUtils.getConnection();

            String sql = "SELECT 1 FROM ARTIST_FOLLOW WHERE userID = ? AND artistID = ?";

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, userID);
            ps.setInt(2, artistID);

            ResultSet rs = ps.executeQuery();

            return rs.next();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }
}
