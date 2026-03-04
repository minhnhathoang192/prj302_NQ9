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
public class UniversityDAO {

    public List<UniversityDTO> searchById(String id) {
        return searchByColumn("id", id);
    }

    public List<UniversityDTO> searchByName(String name) {
        return searchByColumn("name", name);
    }

    public List<UniversityDTO> filterByName(String name) {
        return FilterByColumn("name", name);
    }

    private List<UniversityDTO> searchByColumn(String column, String value) {
        List<UniversityDTO> list = new ArrayList<>();
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
                UniversityDTO u = new UniversityDTO(id, name, shortName, description, foundedYear,
                        address, city, region, type, totalStudents, totalFaculties, isDraft, status);
                list.add(u);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    private List<UniversityDTO> FilterByColumn(String column, String value) {
        List<UniversityDTO> list = new ArrayList<>();
        try {
            Connection conn = DbUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(""
                    + "SELECT * FROM tblUniversity WHERE status=1 AND " + column + " LIKE ? ");
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
                UniversityDTO u = new UniversityDTO(id, name, shortName, description, foundedYear,
                        address, city, region, type, totalStudents, totalFaculties, isDraft, status);
                list.add(u);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public boolean softDelete(String id) {
        try {
            Connection conn = DbUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement("UPDATE tblUniversity"
                    + "   SET status = 0 WHERE id = ?");
            ps.setString(1, id);
            return ps.executeUpdate()>0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
