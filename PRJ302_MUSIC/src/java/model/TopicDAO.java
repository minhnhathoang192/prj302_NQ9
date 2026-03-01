/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import utils.DbUtils;

/**
 *
 * @author NQ9
 */
public class TopicDAO {

    public List<TopicDTO> getAllTopic(String keyword) {
        List<TopicDTO> list = new ArrayList<>();

        try {
            Connection conn = DbUtils.getConnection();

            String sql = "SELECT * FROM TOPIC WHERE isActive = 1";

            if (keyword != null && !keyword.trim().isEmpty()) {
                sql += " AND (topicName LIKE ? OR CAST(topicID AS VARCHAR) LIKE ?)";
            }

            PreparedStatement ps = conn.prepareStatement(sql);

            if (keyword != null && !keyword.trim().isEmpty()) {
                ps.setString(1, "%" + keyword + "%");
                ps.setString(2, "%" + keyword + "%");
            }

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int topicID = rs.getInt("topicID");
                String topicName = rs.getString("topicName");
                String description = rs.getString("description");
                String coverImage = rs.getString("coverImage");
                boolean isActive = rs.getBoolean("isActive");
                Timestamp createdAt = rs.getTimestamp("createdAt");

                TopicDTO t = new TopicDTO(topicID, topicName, description, coverImage, isActive, createdAt);
                list.add(t);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    public TopicDTO getTopicByID(int id) {
        TopicDTO topic = null;
        try {
            Connection conn = DbUtils.getConnection();
            String sql = "SELECT * FROM TOPIC WHERE topicID = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int topicID = rs.getInt("topicID");
                String topicName = rs.getString("topicName");
                String description = rs.getString("description");
                String coverImage = rs.getString("coverImage");
                boolean isActive = rs.getBoolean("isActive");
                Timestamp createdAt = rs.getTimestamp("createdAt");
                topic = new TopicDTO(topicID, topicName, description, coverImage, isActive, createdAt);
            }
            return topic;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean addTopic(TopicDTO t) {
        try {
            Connection conn = DbUtils.getConnection();
            String sql = "INSERT INTO "
                    + "TOPIC(topicName, "
                    + "description, "
                    + "coverImage, "
                    + "isActive, "
                    + "createdAt) "
                    + "VALUES (?, ?, ?, ?, GETDATE())";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, t.getTopicName());
            ps.setString(2, t.getDescription());
            ps.setString(3, t.getCoverImage());
            ps.setBoolean(4, t.isIsActive());

            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean UpdateTopic(TopicDTO t) {
        try {
            Connection conn = DbUtils.getConnection();
            String sql = "UPDATE TOPIC SET topicName=?, "
                    + "description=?, "
                    + "coverImage=?, "
                    + "isActive=? "
                    + "WHERE topicID=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, t.getTopicName());
            ps.setString(2, t.getDescription());
            ps.setString(3, t.getCoverImage());
            ps.setBoolean(4, t.isIsActive());
            ps.setInt(5, t.getTopicID());

            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean deleteTopic(int id) {
        try {
            Connection conn = DbUtils.getConnection();
            String sql = "UPDATE TOPIC SET isActive = 0 WHERE topicID = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
