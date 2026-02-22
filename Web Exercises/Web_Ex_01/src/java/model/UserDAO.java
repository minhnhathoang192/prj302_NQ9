/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import utils.DbUtils;

/**
 *
 * @author NQ9
 */
public class UserDAO {

    public UserDTO login(String id, String password) {
        UserDTO user = findById(id);
        if (user != null && user.getPassword().trim().equals(password.trim())) {
            return user;
        }
        return null;
    }

    private UserDTO findById(String id) {
        UserDTO user = null;
        try {
            Connection conn = DbUtils.getConnection();
            String sql = "SELECT * FROM tblUsers WHERE userID=?";
            PreparedStatement ps= conn.prepareStatement(sql);
            ps.setString(1, id);
            ResultSet rs= ps.executeQuery();
            if(rs.next()){
                String userID= rs.getString("userID");
                String fullName= rs.getString("fullName");
                String password= rs.getString("password");
                String roleID= rs.getString("roleID");
                boolean status= rs.getBoolean("status");
                
                user= new UserDTO(userID, fullName, password, roleID, status);
            }
        } catch (Exception e) {
            return null;
        }
        return user;
    }
}
