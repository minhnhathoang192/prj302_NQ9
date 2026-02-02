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
    public ArrayList<UniversityDTO> searchbyID(String id){
        return searchByColum("id", id);
    }
    public ArrayList<UniversityDTO> searchbyName(String name){
        return searchByColum("name", name);
    }
    public ArrayList<UniversityDTO> filterbyName(String name){
        return filterByName("name",name);
    }

    private ArrayList<UniversityDTO> searchByColum(String colum, String value) {
        ArrayList<UniversityDTO> result= new ArrayList<>();
        try {
            Connection conn= DbUtils.getConnection();
            String sql= "SELECT * FROM tblUniversity WHERE " + colum + "=?";
            PreparedStatement pst= conn.prepareStatement(sql);
            pst.setString(1, value);
            ResultSet rs= pst.executeQuery();
            while (rs.next()) {                
                String id= rs.getString("id");
                String name= rs.getString("name");
                String shortName= rs.getString("shortName");
                String description= rs.getString("description");
                int foundedYear = rs.getInt("foundedYear");
                String address= rs.getString("address");
                String city= rs.getString("city");
                String region= rs.getString("region");
                String type= rs.getString("type");
                int totalStudents= rs.getInt("totalStudents");
                int totalFaculties= rs.getInt("totalFaculties");
                boolean isDraft= rs.getBoolean("isDraft");
                UniversityDTO u= new UniversityDTO(id, name, shortName, description, foundedYear, address, city, region, type, totalStudents, totalFaculties, isDraft);
                result.add(u);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    private ArrayList<UniversityDTO> filterByName(String colum, String value) {
        ArrayList<UniversityDTO> result= new ArrayList<>();
        try {
            Connection conn= DbUtils.getConnection();
            String sql= "SELECT * FROM tblUniversity WHERE " + colum + "LIKE =?";
            PreparedStatement pst= conn.prepareStatement(sql);
            pst.setString(1, "%"+value+"%");
            ResultSet rs= pst.executeQuery();
            while (rs.next()) {                
                String id= rs.getString("id");
                String name= rs.getString("name");
                String shortName= rs.getString("shortName");
                String description= rs.getString("description");
                int foundedYear= rs.getInt("foundedYear");
                String address= rs.getString("address");
                String city= rs.getString("city");
                String region= rs.getString("region");
                String type= rs.getString("type");
                int totalStudents=rs.getInt("totalStudents");
                int totalFaculties= rs.getInt("totalFaculties");
                boolean isDraft= rs.getBoolean("isDraft");
                UniversityDTO u= new UniversityDTO(id, name, shortName, description, foundedYear, address, city, region, type, totalStudents, totalFaculties, isDraft);
                result.add(u);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public boolean sodtDelete(String id) {
        try {
            Connection conn= DbUtils.getConnection();
            String sql="UPDATE tblUniversity SET status=0 WHERE id=?";
            PreparedStatement ps= conn.prepareStatement(sql);
            ps.setString(1, id);
            System.out.println(id +"-" +sql);
            return ps.executeUpdate()>0;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return false;
    }
}
