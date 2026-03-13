package model;

import java.sql.*;
import java.util.*;
import utils.DbUtils;

public class AlbumDAO {

    private AlbumDTO mapRow(ResultSet rs) throws SQLException {
        return new AlbumDTO(
                rs.getInt("albumID"),
                rs.getString("albumName"),
                rs.getString("coverImage"),
                rs.getDate("releaseDate"),
                rs.getBoolean("isActive")
        );
    }

    public List<AlbumDTO> getAllActiveAlbums() {
        List<AlbumDTO> list = new ArrayList<>();
        try {
            Connection conn = DbUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM ALBUM WHERE isActive=1");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(mapRow(rs));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<AlbumDTO> getAllAlbums(String keyword) {
        List<AlbumDTO> list = new ArrayList<>();
        try {
            Connection conn = DbUtils.getConnection();
            String sql = " SELECT * FROM ALBUM ";

            if (keyword != null && !keyword.trim().isEmpty()) {
                sql += " WHERE albumName LIKE ? OR CAST(albumID AS VARCHAR) LIKE ? ";
            }

            sql += "ORDER BY albumID DESC";

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

    public AlbumDTO getAlbumByID(int albumID) {
        try {
            Connection conn = DbUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM ALBUM WHERE albumID=?");
            ps.setInt(1, albumID);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return mapRow(rs);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean createAlbum(AlbumDTO album) {
        try {
            Connection conn = DbUtils.getConnection();
            String sql = "INSERT INTO ALBUM(albumName, coverImage, releaseDate, isActive) VALUES(?,?,?,1)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, album.getAlbumName());
            ps.setString(2, album.getCoverImage());
            ps.setDate(3, album.getReleaseDate());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean updateAlbum(AlbumDTO album) {
        try {
            Connection conn = DbUtils.getConnection();
            String sql = "UPDATE ALBUM SET albumName=?, coverImage=?, releaseDate=?, isActive=? WHERE albumID=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, album.getAlbumName());
            ps.setString(2, album.getCoverImage());
            ps.setDate(3, album.getReleaseDate());
            ps.setBoolean(4, album.isIsActive());
            ps.setInt(5, album.getAlbumID());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<SongDTO> getSongsInAlbum(int albumID) {
        List<SongDTO> list = new ArrayList<>();
        try {
            Connection conn = DbUtils.getConnection();
            String sql = "SELECT s.* FROM SONG s "
                    + "JOIN ALBUM_SONG als ON s.songID = als.songID "
                    + "WHERE als.albumID=? AND s.isActive=1";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, albumID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new SongDTO(
                        rs.getInt("songID"), rs.getString("title"), rs.getInt("duration"),
                        rs.getString("audioURL"), rs.getString("lyric"),
                        rs.getDate("releaseDate"), rs.getString("coverImage"), rs.getBoolean("isActive")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<AlbumDTO> searchAlbums(String keyword) {
        List<AlbumDTO> list = new ArrayList<>();
        try {
            Connection conn = DbUtils.getConnection();
            String[] tokens = keyword.trim().split("\\s+");
            StringBuilder sql = new StringBuilder("SELECT DISTINCT al.*"
                    + "            FROM ALBUM al"
                    + "            LEFT JOIN ALBUM_SONG als ON al.albumID = als.albumID"
                    + "            LEFT JOIN SONG s ON als.songID = s.songID"
                    + "            LEFT JOIN ALBUM_ARTIST aa ON al.albumID = aa.albumID"
                    + "            LEFT JOIN ARTIST a ON aa.artistID = a.artistID"
                    + "            WHERE al.isActive = 1 AND (");

            for (int i = 0; i < tokens.length; i++) {
                if (i > 0) {
                    sql.append(" OR ");
                }
                sql.append("(al.albumName LIKE ? OR s.title LIKE ? OR a.artistName LIKE ?)");
            }

            sql.append(")");

            PreparedStatement ps = conn.prepareStatement(sql.toString());
            int index = 1;
            for (String t : tokens) {
                String like = "%" + t + "%";
                ps.setString(index++, like);
                ps.setString(index++, like);
                ps.setString(index++, like);
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

    public boolean updateAlbumStatus(int albumID, int status) {
        try {
            Connection conn = DbUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement("UPDATE ALBUM SET isActive=? WHERE albumID=?");
            ps.setInt(1, status);
            ps.setInt(2, albumID);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
