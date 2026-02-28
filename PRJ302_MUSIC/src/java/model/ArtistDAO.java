package model;

import java.sql.*;
import java.util.*;
import utils.DbUtils;

public class ArtistDAO {

    private ArtistDTO mapRow(ResultSet rs) throws SQLException {
        return new ArtistDTO(
                rs.getInt("artistID"),
                rs.getString("artistName"),
                rs.getString("avatarURL"),
                rs.getString("description"),
                rs.getDate("debutDate"),
                rs.getBoolean("isActive")
        );
    }

    public List<ArtistDTO> getAllActiveArtists() {
        List<ArtistDTO> list = new ArrayList<>();
        try {
            Connection conn = DbUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM ARTIST WHERE isActive=1");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(mapRow(rs));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<ArtistDTO> getAllArtist(String keyword) {
        List<ArtistDTO> list = new ArrayList<>();

        try {
            Connection conn = DbUtils.getConnection();

            String sql = "SELECT * FROM ARTIST";

            if (keyword != null && !keyword.trim().isEmpty()) {
                sql += " WHERE artistName LIKE ? OR CAST(artistID AS VARCHAR) LIKE ?";
            }

            PreparedStatement ps = conn.prepareStatement(sql);

            if (keyword != null && !keyword.trim().isEmpty()) {
                ps.setString(1, "%" + keyword + "%");
                ps.setString(2, "%" + keyword + "%");
            }

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                list.add(mapRow(rs));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    public ArtistDTO getArtistByID(int artistID) {
        try {
            Connection conn = DbUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM ARTIST WHERE artistID=?");
            ps.setInt(1, artistID);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return mapRow(rs);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean createArtist(ArtistDTO artist) {
        try {
            Connection conn = DbUtils.getConnection();
            String sql = "INSERT INTO ARTIST(artistName, avatarURL, description, debutDate, isActive) VALUES(?,?,?,?,1)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, artist.getArtistName());
            ps.setString(2, artist.getAvatarURL());
            ps.setString(3, artist.getDescription());
            ps.setDate(4, artist.getDebutDate());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean updateArtist(ArtistDTO artist) {
        try {
            Connection conn = DbUtils.getConnection();
            String sql = "UPDATE ARTIST SET artistName=?, avatarURL=?, description=?, debutDate=?, isActive=? WHERE artistID=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, artist.getArtistName());
            ps.setString(2, artist.getAvatarURL());
            ps.setString(3, artist.getDescription());
            ps.setDate(4, artist.getDebutDate());
            ps.setBoolean(5, artist.isIsActive());
            ps.setInt(6, artist.getArtistID());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean updateArtistStatus(int artistID, int status) {
        try {
            Connection conn = DbUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement("UPDATE ARTIST SET isActive=? WHERE artistID=?");
            ps.setInt(1, status);
            ps.setInt(2, artistID);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<ArtistDTO> searchArtists(String keyword) {
        List<ArtistDTO> list = new ArrayList<>();
        try {
            Connection conn = DbUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(
                    "SELECT * FROM ARTIST WHERE isActive=1 AND artistName LIKE ?");
            ps.setString(1, "%" + keyword + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(mapRow(rs));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
