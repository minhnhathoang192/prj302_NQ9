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
public class universityDAO {
    public List<universityDTO> searchById(String id){
        return searchByColumn("id", id);
    }
    public List<universityDTO> searchByName(String name){
        return searchByColumn("name", name);
    }
    public List<universityDTO> filterByName(String name){
        return filterByColumn("name", name);
    }

    private List<universityDTO> searchByColumn(String column, String value) {
        List<universityDTO> list= new ArrayList<>();
        try {
            Connection conn= DbUtils.getConnection();
            PreparedStatement ps= conn.prepareStatement("SELECT * FROM tblUniversity WHERE " + column + " = ?");
            ps.setString(1, value);
            ResultSet rs= ps.executeQuery();
            while (rs.next()) {                
                String id= rs.getString("id");
                String name= rs.getString("name");
                String shortName= rs.getString("shortName");
                String description= rs.getString("description");
                int foundedYear= rs.getInt("foundedYear");
                String address= rs.getString("address");
                String city= rs.getString("city");
                String type= rs.getString("type");
                int totalStudents= rs.getInt("totalStudents");
                int totalFaculties= rs.getInt("totalFaculties");
                boolean isDraft= rs.getBoolean("isDraft");
                boolean status= rs.getBoolean("status");
                universityDTO u= new universityDTO(id, name, shortName, description, foundedYear,
                        address, city, city, type, totalStudents, totalFaculties, isDraft, status);
                list.add(u);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    private List<universityDTO> filterByColumn(String column, String value) {
        List<universityDTO> list= new ArrayList<>();
        try {
            Connection conn= DbUtils.getConnection();
            PreparedStatement ps= conn.prepareStatement("SELECT * FROM tblUniversity WHERE status = 1 AND " + column + " LIKE ?");
            ps.setString(1, "%" + value + "%");
            ResultSet rs= ps.executeQuery();
            while (rs.next()) {                
                String id= rs.getString("id");
                String name= rs.getString("name");
                String shortName= rs.getString("shortName");
                String description= rs.getString("description");
                int foundedYear= rs.getInt("foundedYear");
                String address= rs.getString("address");
                String city= rs.getString("city");
                String type= rs.getString("type");
                int totalStudents= rs.getInt("totalStudents");
                int totalFaculties= rs.getInt("totalFaculties");
                boolean isDraft= rs.getBoolean("isDraft");
                boolean status= rs.getBoolean("status");
                universityDTO u= new universityDTO(id, name, shortName, description, foundedYear,
                        address, city, city, type, totalStudents, totalFaculties, isDraft, status);
                list.add(u);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    
    public boolean softDelete(String id){
        try {
            Connection conn= DbUtils.getConnection();
            PreparedStatement ps= conn.prepareStatement("UPDATE tblUniversity SET status = 0 WHERE id = ?");
            ps.setString(1, id);
            return ps.executeUpdate()>0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
