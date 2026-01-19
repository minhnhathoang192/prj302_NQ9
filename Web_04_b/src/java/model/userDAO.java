/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import utils.DbUtils;

/**
 *
 * @author NQ9
 */
public class userDAO {
    
    public userDAO(){
    }
    
    public userDTO findByID(String id){
        userDTO user= null;
        try {
            Connection conn = DbUtils.getConnection();
            String sql= "SELECT * FROM tblUsers WHERE userID=?";
            System.out.println(sql);
            PreparedStatement pst= conn.prepareStatement(sql);
            pst.setString(1, id);
            ResultSet rs= pst.executeQuery();
            
            while (rs.next()) {                
                String userID= rs.getString("userID");
                String fullName= rs.getString("fullName");
                String password= rs.getString("password");
                String roleID= rs.getString("roleID");
                boolean status= rs.getBoolean("status");
                user= new userDTO(userID, fullName, password, roleID, status);
            }
        } catch (Exception e) {
            return null;
        }
        return user;
    }
    
    public userDTO login(String userName, String passWord){
        userDTO user= findByID(userName);
        if(user!=null&&user.getPassword().equals(passWord)){
            return user;
        }
        return null;
    }
}
