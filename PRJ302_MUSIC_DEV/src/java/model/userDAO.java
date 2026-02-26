/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.List;
import utils.DbUtils;

/**
 *
 * @author NQ9
 */
public class userDAO {

    public userDTO login(String userName, String pass) {
        userDTO user = findByUserName(userName);
        if (user != null && user.getPassword().equals(pass)&&user.getStatus()==1) {
            return user;
        }
        return null;
    }

    private userDTO findByUserName(String UserName) {
        userDTO user = null;
        try {
            Connection conn = DbUtils.getConnection();
            String sql = "SELECT * FROM USERS WHERE userName=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, UserName);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String userID = rs.getString("userID");
                String userName = rs.getString("userName");
                String email = rs.getString("email");
                String password = rs.getString("password");
                String avatar = rs.getString("avatar");
                String fullName = rs.getString("fullName");
                Date birthday = rs.getDate("birthday");
                String gender = rs.getString("gender");
                Timestamp createDate = rs.getTimestamp("createDate");
                Timestamp lastLogin = rs.getTimestamp("lastLogin");
                int status = rs.getInt("status");
                int roleID = rs.getInt("roleID");
                user = new userDTO(userID, userName, email, password, avatar, fullName, birthday, gender, createDate, lastLogin, status, roleID);
            }
            System.out.println(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    private userDTO findByID(String id) {
        userDTO user = null;
        try {
            Connection conn = DbUtils.getConnection();
            String sql = "SELECT * FROM USERS WHERE userID=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String userID = rs.getString("userID");
                String userName = rs.getString("userName");
                String email = rs.getString("email");
                String password = rs.getString("password");
                String avatar = rs.getString("avatar");
                String fullName = rs.getString("fullName");
                Date birthday = rs.getDate("birthday");
                String gender = rs.getString("gender");
                Timestamp createDate = rs.getTimestamp("createDate");
                Timestamp lastLogin = rs.getTimestamp("lastLogin");
                int status = rs.getInt("status");
                int roleID = rs.getInt("roleID");
                user = new userDTO(userID, userName, email, password, avatar, fullName, birthday, gender, createDate, lastLogin, status, roleID);
            }
            System.out.println(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }
    
    public List<userDTO> filterByName(String name){
        return filterByName("name", name);
    }

    private boolean createUser(userDTO user) {
        try {
            Connection conn = DbUtils.getConnection();
            String sql = "INSERT INTO USERS(userName, email, password, avatar, fullName, birthday, gender, status, roleID)\n"
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, user.getUserName());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getPassword());
            ps.setString(4, user.getAvatar());
            ps.setString(5, user.getFullName());
            ps.setDate(6, user.getBirthday());
            ps.setString(7, user.getGender());
            ps.setInt(8, user.getStatus());
            ps.setInt(9, user.getRoleID());

            int rows = ps.executeUpdate();

            return rows > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    private boolean updateUserStatus(String userid, int status) {
        try {
            Connection conn = DbUtils.getConnection();
            String sql = "UPDATE USERS SET status=? WHERE userID=?";

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, status);
            ps.setString(2, userid);

            int rows = ps.executeUpdate();

            return rows > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    private boolean updateLastLogin(String userid) {
        try {
            Connection conn = DbUtils.getConnection();
            String sql = "UPDATE USERS SET lastLogin= GETDATE() WHERE userID=?";

            PreparedStatement ps = conn.prepareStatement(sql);

            
            ps.setString(1, userid);

            int rows = ps.executeUpdate();

            return rows > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    private List<userDTO> filterByName(String name, String name0) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
