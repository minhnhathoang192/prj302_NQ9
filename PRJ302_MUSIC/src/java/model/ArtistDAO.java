package model;

import java.sql.*;
import java.util.*;
import utils.DbUtils;
import java.sql.Date;

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
            String[] tokens = keyword.trim().split("\\s+");
            StringBuilder sql = new StringBuilder("SELECT DISTINCT a.*"
                    + "            FROM ARTIST a"
                    + "            LEFT JOIN SONG_ARTIST sa ON a.artistID = sa.artistID"
                    + "            LEFT JOIN SONG s ON sa.songID = s.songID"
                    + "            LEFT JOIN ALBUM_ARTIST aa ON a.artistID = aa.artistID"
                    + "            LEFT JOIN ALBUM al ON aa.albumID = al.albumID"
                    + "            WHERE a.isActive = 1 AND (");
            for (int i = 0; i < tokens.length; i++) {
                if (i > 0) {
                    sql.append(" OR ");
                }
                sql.append("(a.artistName LIKE ? OR s.title LIKE ? OR al.albumName LIKE ?)");
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
                /*
                private int artistID;
                private String artistName, avatarURL, description;
                private Date debutDate;
                private boolean isActive;
                 */
                list.add(mapRow(rs));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<SongDTO> getSongByArtist(int artistID) {

        List<SongDTO> list = new ArrayList<>();
        try {
            Connection conn = DbUtils.getConnection();

            String sql
                    = "SELECT s.songID, s.title, s.duration, s.audioURL, "
                    + "s.lyric, s.releaseDate, s.coverImage, s.isActive "
                    + "FROM SONG s "
                    + "JOIN SONG_ARTIST sa ON s.songID = sa.songID "
                    + "WHERE sa.artistID = ? AND s.isActive = 1 "
                    + "ORDER BY s.releaseDate DESC";

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, artistID);

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

    public List<ArtistDTO> getTrendingArtists() {
        List<ArtistDTO> list = new ArrayList<>();
        try {
            Connection conn = DbUtils.getConnection();
            String sql
                    = "SELECT TOP 30 "
                    + " a.artistID, "
                    + " a.artistName,"
                    + " a.avatarURL, "
                    + " COUNT(f.userID) AS followers, "
                    + " s.songID AS latestSongID, "
                    + " s.title AS latestSong, "
                    + " s.coverImage AS latestCover "
                    + ""
                    + " FROM ARTIST a "
                    + ""
                    + " LEFT JOIN ARTIST_FOLLOW f "
                    + " ON a.artistID = f.artistID "
                    + ""
                    + " OUTER APPLY ( "
                    + "    SELECT TOP 1 "
                    + "        s.songID, "
                    + "        s.title, "
                    + "        s.coverImage "
                    + "    FROM SONG s "
                    + "    JOIN SONG_ARTIST sa "
                    + "        ON s.songID = sa.songID "
                    + "    WHERE sa.artistID = a.artistID "
                    + "    ORDER BY s.releaseDate DESC "
                    + " ) s "
                    + ""
                    + " GROUP BY "
                    + " a.artistID,"
                    + " a.artistName, "
                    + " a.avatarURL, "
                    + " s.songID, "
                    + " s.title, "
                    + " s.coverImage "
                    + ""
                    + " ORDER BY followers DESC ";

            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ArtistDTO a = new ArtistDTO();

                a.setArtistID(rs.getInt("artistID"));
                a.setArtistName(rs.getString("artistName"));
                a.setAvatarURL(rs.getString("avatarURL"));
                
                a.setFollowers(rs.getInt("followers"));
                
                a.setLatestSongID(rs.getInt("latestSongID"));
                a.setLatestSong(rs.getString("latestSong"));
                a.setLatestCover(rs.getString("latestCover"));

                list.add(a);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
