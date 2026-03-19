/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modal;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import utils.DbUtils;

/**
 *
 * @author NQ9
 */
public class studentDao {
    public List<studentDto> FilterByName(String name) {
        return FilterByColumn("fullName", name);
    }

    private List<studentDto> FilterByColumn(String column, String value) {
        List<studentDto> list = new ArrayList<>();
        try {
            Connection conn = DbUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(""
                    + "SELECT * FROM tblStudent WHERE status = 1 AND " + column + " LIKE ? ");
            ps.setString(1, "%" + value + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String studentID = rs.getString("studentID");
                String fullName = rs.getString("fullName");
                String gender = rs.getString("gender");
                Date dateOfBirth = rs.getDate("dateOfBirth");
                String email = rs.getString("email");
                String phone = rs.getString("phone");
                String address = rs.getString("address");
                String city = rs.getString("city");
                String major = rs.getString("major");
                String faculty = rs.getString("faculty");
                int enrollYear = rs.getInt("enrollYear");
                Double gpa = rs.getDouble("gpa");
                boolean scholarship = rs.getBoolean("scholarship");
                boolean status = rs.getBoolean("status");
                studentDto s = new studentDto(studentID, fullName, gender, dateOfBirth, email, phone, address, city, major, faculty, enrollYear, gpa, scholarship, status);
                list.add(s);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public boolean softDelete(String studentID) {
        try {
            Connection conn = DbUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(""
                    + "UPDATE tblStudent "
                    + "   SET status = 0 "
                    + " WHERE studentID = ? ");
            ps.setString(1, studentID);
            return ps.executeUpdate()>0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
