package model;

import java.sql.*;
import java.util.*;
import utils.DbUtils;

public class ArtistSongDAO {

    private ArtistSongDTO mapRow(ResultSet rs) throws SQLException {
        return new ArtistSongDTO(
            rs.getInt("songID"),
            rs.getInt("artistID")
        );
    }

    public List<ArtistSongDTO> getAll() {
        List<ArtistSongDTO> list = new ArrayList<>();
        try (Connection conn = DbUtils.getConnection();
             PreparedStatement ps = conn.prepareStatement("SELECT * FROM SONG_ARTIST");
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                list.add(mapRow(rs));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<ArtistSongDTO> getByArtistID(int artistID) {
        List<ArtistSongDTO> list = new ArrayList<>();
        try (Connection conn = DbUtils.getConnection();
             PreparedStatement ps = conn.prepareStatement("SELECT * FROM SONG_ARTIST WHERE artistID=?")) {
            ps.setInt(1, artistID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(mapRow(rs));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<ArtistSongDTO> getBySongID(int songID) {
        List<ArtistSongDTO> list = new ArrayList<>();
        try (Connection conn = DbUtils.getConnection();
             PreparedStatement ps = conn.prepareStatement("SELECT * FROM SONG_ARTIST WHERE songID=?")) {
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

    public boolean addArtistToSong(int songID, int artistID) {
        try (Connection conn = DbUtils.getConnection();
             PreparedStatement ps = conn.prepareStatement(
                     "INSERT INTO SONG_ARTIST(songID, artistID) VALUES(?,?)")) {
            ps.setInt(1, songID);
            ps.setInt(2, artistID);
            
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean update(ArtistSongDTO dto) {
        try (Connection conn = DbUtils.getConnection();
             PreparedStatement ps = conn.prepareStatement(
                     "UPDATE ArtistSong SET artistID=?, songID=?, isActive=? WHERE artistSongID=?")) {
            ps.setInt(1, dto.getArtistID());
            ps.setInt(2, dto.getSongID());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean delete(int artistSongID) {
        try (Connection conn = DbUtils.getConnection();
             PreparedStatement ps = conn.prepareStatement("DELETE FROM SONG_ARTIST WHERE artistSongID=?")) {
            ps.setInt(1, artistSongID);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public boolean exists(int songID, int artistID){
        try {
            Connection conn= DbUtils.getConnection();
            String sql= "SELECT 1 FORM SONG_ARTIST WHERE songID= ? AND artistID= ?";
            PreparedStatement ps= conn.prepareStatement(sql);
            ps.setInt(1, songID);
            ps.setInt(2, artistID);
            ResultSet rs= ps.executeQuery();
            
            return rs.next();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
