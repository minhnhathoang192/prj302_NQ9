/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modal;

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
public class universityDao {

    public universityDto searchById(String ID) {
        universityDto u = null;
        try {
            Connection conn = DbUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(""
                    + "SELECT * FROM tblUniversity WHERE id = ? ");
            ps.setString(1, ID);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String id = rs.getString("id");
                String name = rs.getString("name");
                String shortName = rs.getString("shortName");
                String description = rs.getString("description");
                int foundedYear = rs.getInt("foundedYear");
                String address = rs.getString("address");
                String city = rs.getString("city");
                String region = rs.getString("region");
                String type = rs.getString("type");
                int totalStudents = rs.getInt("totalStudents");
                int totalFaculties = rs.getInt("totalFaculties");
                boolean isDraft = rs.getBoolean("isDraft");
                boolean status = rs.getBoolean("status");
                u = new universityDto(id, name, shortName, description, foundedYear, address, city, region, type, totalStudents, totalFaculties, isDraft, status);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return u;
    }

    public List<universityDto> searchByName(String name) {
        return searchByColumn("name", name);
    }

    public List<universityDto> FilterByName(String name) {
        return filterByColumn("name", name);
    }

    private List<universityDto> searchByColumn(String column, String value) {
        List<universityDto> list = new ArrayList<>();
        try {
            Connection conn = DbUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(""
                    + "SELECT * FROM tblUniversity WHERE " + column + " = ? ");
            ps.setString(1, value);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String id = rs.getString("id");
                String name = rs.getString("name");
                String shortName = rs.getString("shortName");
                String description = rs.getString("description");
                int foundedYear = rs.getInt("foundedYear");
                String address = rs.getString("address");
                String city = rs.getString("city");
                String region = rs.getString("region");
                String type = rs.getString("type");
                int totalStudents = rs.getInt("totalStudents");
                int totalFaculties = rs.getInt("totalFaculties");
                boolean isDraft = rs.getBoolean("isDraft");
                boolean status = rs.getBoolean("status");
                universityDto u = new universityDto(id, name, shortName, description, foundedYear, address, city, region, type, totalStudents, totalFaculties, isDraft, status);
                list.add(u);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    private List<universityDto> filterByColumn(String column, String value) {
        List<universityDto> list = new ArrayList<>();
        try {
            Connection conn = DbUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(""
                    + "SELECT * FROM tblUniversity WHERE status = 0 AND " + column + " LIKE ? ");
            ps.setString(1, "%" + value + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String id = rs.getString("id");
                String name = rs.getString("name");
                String shortName = rs.getString("shortName");
                String description = rs.getString("description");
                int foundedYear = rs.getInt("foundedYear");
                String address = rs.getString("address");
                String city = rs.getString("city");
                String region = rs.getString("region");
                String type = rs.getString("type");
                int totalStudents = rs.getInt("totalStudents");
                int totalFaculties = rs.getInt("totalFaculties");
                boolean isDraft = rs.getBoolean("isDraft");
                boolean status = rs.getBoolean("status");
                universityDto u = new universityDto(id, name, shortName, description, foundedYear, address, city, region, type, totalStudents, totalFaculties, isDraft, status);
                list.add(u);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public boolean add(universityDto u) {
        try {
            Connection conn = DbUtils.getConnection();
            String sql = "INSERT into tblUniversity(id,name,shortName,description,foundedYear,address, city, region,"
                    + " type, totalStudents, totalFaculties, isDraft, status)"
                    + " VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, u.getId());
            ps.setString(2, u.getName());
            ps.setString(3, u.getShortName());
            ps.setString(4, u.getDescription());
            ps.setInt(5, u.getFoundedYear());
            ps.setString(6, u.getAddress());
            ps.setString(7, u.getCity());
            ps.setString(8, u.getRegion());
            ps.setString(9, u.getType());
            ps.setInt(10, u.getTotalStudents());
            ps.setInt(11, u.getTotalFaculties());
            ps.setBoolean(12, true);
            ps.setBoolean(13, u.isStatus());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean updateUni(universityDto u) {
        try {
            Connection conn = DbUtils.getConnection();
            String sql = "UPDATE tblUniversity "
                    + "   SET name = ? "
                    + "      ,shortName = ? "
                    + "      ,description =  ? "
                    + "      ,foundedYear = ? "
                    + "      ,address =  ? "
                    + "      ,city =  ? "
                    + "      ,region =  ? "
                    + "      ,type = ? "
                    + "      ,totalStudents = ? "
                    + "      ,totalFaculties = ? "
                    + "      ,isDraft = ? "
                    + "      ,status = ? "
                    + " WHERE id =  ? "
                    + ""
                    + "";
            PreparedStatement ps = conn.prepareStatement(sql);
            
            ps.setString(1, u.getName());
            ps.setString(2, u.getShortName());
            ps.setString(3, u.getDescription());
            ps.setInt(4, u.getFoundedYear());
            ps.setString(5, u.getAddress());
            ps.setString(6, u.getCity());
            ps.setString(7, u.getRegion());
            ps.setString(8, u.getType());
            ps.setInt(9, u.getTotalStudents());
            ps.setInt(10, u.getTotalFaculties());
            ps.setBoolean(11, true);
            ps.setBoolean(12, u.isStatus());
            ps.setString(13, u.getId());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
