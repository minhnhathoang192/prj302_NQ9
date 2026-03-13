package model;

import java.sql.*;
import java.util.*;
import utils.DbUtils;

public class AlbumSongDAO {

    private AlbumSongDTO mapRow(ResultSet rs) throws SQLException {
        return new AlbumSongDTO(
            rs.getInt("albumSongID"),
            rs.getInt("albumID"),
            rs.getInt("songID"),
            rs.getBoolean("isActive")
        );
    }

    public List<AlbumSongDTO> getAll() {
        List<AlbumSongDTO> list = new ArrayList<>();
        try (Connection conn = DbUtils.getConnection();
             PreparedStatement ps = conn.prepareStatement("SELECT * FROM AlbumSong");
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                list.add(mapRow(rs));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public AlbumSongDTO getByID(int albumSongID) {
        try (Connection conn = DbUtils.getConnection();
             PreparedStatement ps = conn.prepareStatement("SELECT * FROM AlbumSong WHERE albumSongID=?")) {
            ps.setInt(1, albumSongID);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return mapRow(rs);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<AlbumSongDTO> getByAlbumID(int albumID) {
        List<AlbumSongDTO> list = new ArrayList<>();
        try (Connection conn = DbUtils.getConnection();
             PreparedStatement ps = conn.prepareStatement("SELECT * FROM AlbumSong WHERE albumID=?")) {
            ps.setInt(1, albumID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(mapRow(rs));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<AlbumSongDTO> getBySongID(int songID) {
        List<AlbumSongDTO> list = new ArrayList<>();
        try (Connection conn = DbUtils.getConnection();
             PreparedStatement ps = conn.prepareStatement("SELECT * FROM AlbumSong WHERE songID=?")) {
            ps.setInt(1, songID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(mapRow(rs));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public boolean add(int albumID, int songID) {
        try (Connection conn = DbUtils.getConnection();
             PreparedStatement ps = conn.prepareStatement(
                     "INSERT INTO ALBUM_SONG(albumID, songID) VALUES(?,?)")) {
            ps.setInt(1, albumID);
            ps.setInt(2, songID);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean update(AlbumSongDTO dto) {
        try (Connection conn = DbUtils.getConnection();
             PreparedStatement ps = conn.prepareStatement(
                     "UPDATE AlbumSong SET albumID=?, songID=?, isActive=? WHERE albumSongID=?")) {
            ps.setInt(1, dto.getAlbumID());
            ps.setInt(2, dto.getSongID());
            ps.setBoolean(3, dto.isIsActive());
            ps.setInt(4, dto.getAlbumSongID());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean delete(int albumSongID) {
        try (Connection conn = DbUtils.getConnection();
             PreparedStatement ps = conn.prepareStatement("DELETE FROM AlbumSong WHERE albumSongID=?")) {
            ps.setInt(1, albumSongID);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public boolean exists(int albumID, int songID){
        try {
            Connection conn= DbUtils.getConnection();
            String sql= "SELECT 1 FORM ALBUM_SONG WHERE albumID= ? AND songID= ?";
            PreparedStatement ps= conn.prepareStatement(sql);
            ps.setInt(1, albumID);
            ps.setInt(2, songID);
            ResultSet rs= ps.executeQuery();
            
            return rs.next();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
