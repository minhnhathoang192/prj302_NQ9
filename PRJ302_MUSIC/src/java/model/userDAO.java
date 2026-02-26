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
import java.util.ArrayList;
import java.util.List;
import utils.DbUtils;

/**
 *
 * @author NQ9
 */
public class userDAO {

    public userDTO login(String userName, String pass) {
        userDTO user = findByUserName(userName);
        if (user != null && user.getPassword().equals(pass) && user.getStatus() == 1) {
            return user;
        }
        return null;
    }

    public List<userDTO> filterByName(String name) {
        List<userDTO> user = new ArrayList<>();
        try {
            Connection conn = DbUtils.getConnection();
            String sql = "SELECT userID"
                    + "      ,userName"
                    + "      ,email"
                    + "      ,password"
                    + "      ,avatar"
                    + "      ,fullName"
                    + "      ,birthday"
                    + "      ,gender"
                    + "      ,createDate"
                    + "      ,lastLogin"
                    + "      ,status"
                    + "      ,roleID"
                    + "  FROM USERS";

            //nếu có search thì thêm WHERE
            if (name != null && !name.trim().isEmpty()) {
                sql += " WHERE userName LIKE ?";
            }

            PreparedStatement ps = conn.prepareStatement(sql);

            //set param nếu có search
            if (name != null && !name.trim().isEmpty()) {
                ps.setString(1, "%" + name + "%");
            }

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
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
                userDTO u = new userDTO(userID, userName, email, password, avatar, fullName, birthday, gender, createDate, lastLogin, status, roleID);
                user.add(u);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
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

    public userDTO findByID(String id) {
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

    public boolean createUser(userDTO user) {
        try {
            Connection conn = DbUtils.getConnection();
            String sql = "INSERT INTO USERS(userName, email, password, avatar, fullName, birthday, gender, createDate, status, roleID)"
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement ps = conn.prepareStatement(sql);
            
            Timestamp now = new Timestamp(System.currentTimeMillis());

            ps.setString(1, user.getUserName());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getPassword());
            ps.setString(4, user.getAvatar());
            ps.setString(5, user.getFullName());
            ps.setDate(6, user.getBirthday());
            ps.setString(7, user.getGender());
            ps.setTimestamp(8, now);
            ps.setInt(9, user.getStatus());
            ps.setInt(10, user.getRoleID());

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

    public int softDelete(String userID) {
        try {
            Connection conn = DbUtils.getConnection();
            String sql = "UPDATE USERS SET status=0 WHERE userID=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, userID);
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public boolean updateUser(userDTO u) {
        int result = 0;
        try {
            Connection conn = DbUtils.getConnection();
            String sql = "UPDATE USERS"
                    + "   SET userName = ?"
                    + "      ,email = ?"
                    + "      ,password = ?"
                    + "      ,avatar = ?"
                    + "      ,fullName = ?"
                    + "      ,birthday = ?"
                    + "      ,gender = ?"
                    + "      ,status = ?"
                    + "      ,roleID = ?"
                    + " WHERE userID = ?";
            PreparedStatement ps = conn.prepareStatement(sql);

            /*
                private String userID, userName, email, password, avatar, fullName;
                private Date birthday;
                private String gender;
                private Timestamp createDate, lastLogin;
                private int status, roleID;
             */
            
            ps.setString(1, u.getUserName());
            ps.setString(2, u.getEmail());
            ps.setString(3, u.getPassword());
            ps.setString(4, u.getAvatar());
            ps.setString(5, u.getFullName());
            ps.setDate(6, u.getBirthday());
            ps.setString(7, u.getGender());
            ps.setInt(8, u.getStatus());
            ps.setInt(9, u.getRoleID());
            ps.setString(10, u.getUserID());
            
            result = ps.executeUpdate();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return result > 0;
    }
}
