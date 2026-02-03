/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import sun.security.pkcs11.Secmod;
import utils.DbUtils;

/**
 *
 * @author NQ9
 */
public class userDAO {
    public userDTO login(String id, String pass){
        userDTO user= findbyID(id);
        if(user!=null&&user.getPassword().equals(pass)){
            return user;
        }
        return null;
    }

    private userDTO findbyID(String id) {
        userDTO user= null;
        try {
            Connection conn= DbUtils.getConnection();
            String sql= "SELECT * FROM tblUsers WHERE userID=?";
            PreparedStatement ps= conn.prepareStatement(sql);
            ps.setString(1, id);
            ResultSet rs= ps.executeQuery();
            while (rs.next()) {                
                String userID= rs.getString("userID");
                String fullName= rs.getString("fullName");
                String password= rs.getString("password");
                String roleID= rs.getString("roleID");
                boolean isDraft= rs.getBoolean("isDraft");
                user= new userDTO(userID, fullName, password, roleID, isDraft);
            }
        } catch (Exception e) {
            return null;
        }
        return user;
    }
}
