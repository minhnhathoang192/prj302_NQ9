/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import utils.DbUtils;

/**
 *
 * @author NQ9
 */
public class UniversityDAO {

    public UniversityDTO searchbyID(String id) {
        ArrayList<UniversityDTO> a = searchbyColumn("id", id);
        if (a.size() > 0) {
            return a.get(0);
        }
        return null;
    }

    public ArrayList<UniversityDTO> searchbyName(String name) {
        return searchbyColumn("name", name);
    }

    public ArrayList<UniversityDTO> filterbyName(String name) {
        return filterbyColumn("name", name);
    }

    private ArrayList<UniversityDTO> searchbyColumn(String column, String value) {
        ArrayList<UniversityDTO> result = new ArrayList<>();
        try {
            Connection conn = DbUtils.getConnection();
            String sql = "SELECT * FROM tblUniversity WHERE " + column + " = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
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
                UniversityDTO user = new UniversityDTO(id, name, shortName, description, foundedYear, address, city, region, type, totalStudents, totalFaculties, isDraft);
                result.add(user);
            }
        } catch (Exception e) {
            return null;
        }
        return result;
    }

    private ArrayList<UniversityDTO> filterbyColumn(String column, String value) {
        ArrayList<UniversityDTO> result = new ArrayList<>();
        try {
            Connection conn = DbUtils.getConnection();
            String sql = "SELECT * FROM tblUniversity WHERE status=1 AND " + column + " LIKE ? ";
            PreparedStatement ps = conn.prepareStatement(sql);
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
                UniversityDTO user = new UniversityDTO(id, name, shortName, description, foundedYear, address, city, region, type, totalStudents, totalFaculties, isDraft);
                result.add(user);
            }
        } catch (Exception e) {
            return null;
        }
        return result;
    }

    public boolean softDelete(String id) {
        try {
            Connection conn = DbUtils.getConnection();
            String sql = "UPDATE tblUniversity SET status=0 WHERE id=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, id);
            System.out.println(id + "-" + sql);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return false;
    }

    public boolean add(UniversityDTO u) {
        int result = 0;
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
            ps.setBoolean(12, u.isIsDraft());
            ps.setBoolean(13, true);
            result = ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return result > 0;
    }

    public boolean upadteUni(UniversityDTO u) {
        int result = 0;
        try {
            Connection conn = DbUtils.getConnection();
            String sql = "UPDATE tblUniversity"
                    + "   SET name = ?"
                    + "      ,shortName = ?"
                    + "      ,description = ?"
                    + "      ,foundedYear = ?"
                    + "      ,address = ?"
                    + "      ,city = ?"
                    + "      ,region = ?"
                    + "      ,type = ?"
                    + "      ,totalStudents = ?"
                    + "      ,totalFaculties = ?"
                    + "      ,isDraft = ?"
                    + "      ,status = ?"
                    + " WHERE id = ?";
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
            ps.setBoolean(11, u.isIsDraft());
            ps.setBoolean(12, true);
            ps.setString(13, u.getId());
            result = ps.executeUpdate();
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return result > 0;
    }
}
