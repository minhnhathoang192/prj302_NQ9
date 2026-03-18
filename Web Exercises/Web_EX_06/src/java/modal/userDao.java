/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import utils.DbUtils;

/**
 *
 * @author NQ9
 */
public class userDao {
    public userDto login(String id, String pass){
        userDto user= searchByID(id);
        if(user!=null&&user.getPassword().equals(pass.trim())){
            return user;
        }
        return null;
    }

    private userDto searchByID(String id) {
        userDto user= null;
        try {
            Connection conn= DbUtils.getConnection();
            PreparedStatement ps= conn.prepareStatement(""
                    + "SELECT * FROM tblUsers WHERE userID = ?");
            ps.setString(1, id);
            ResultSet rs= ps.executeQuery();
            if(rs.next()){
                String userID = rs.getString("userID");
                String fullName = rs.getString("fullName");
                String password = rs.getString("password");
                String roleID = rs.getString("roleID");
                boolean status = rs.getBoolean("status");
                user = new userDto(userID, fullName, password, roleID, status);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }
}
