/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.time.LocalDate;
import javafx.scene.chart.PieChart;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.userDAO;
import model.userDTO;
import utils.PasswordUtils;

/**
 *
 * @author NQ9
 */
public class EditUserController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");
        String userID = request.getParameter("userID");
        userDAO udao = new userDAO();
        userDTO user = udao.findByID(userID);
        String url = "";
        if (action.equals("editUser")) {
            //lay id va hien thi form
            request.setAttribute("u", user);
            request.setAttribute("mode", "edit");
            url = "editUser.jsp";
        } else if (action.equals("saveUser")) {
            //sau khi chinh lay gia tri moiw va chinh
            String error = "";
            String msg = "";
            /*
                private String userID, userName, email, password, avatar, fullName;
                private Date birthday;
                private String gender;
                private Timestamp createDate, lastLogin;
                private int status, roleID;
             */
            try {
                String userName= request.getParameter("userName");
                String email= request.getParameter("email");
                String password= request.getParameter("password");
                String finalPassword = user.getPassword();
                String avatar= request.getParameter("avatar");
                String fullName= request.getParameter("fullName");
                String s_birthday= request.getParameter("birthday");
                String gender= request.getParameter("gender");
                String s_status= request.getParameter("status"); 
                String s_roleID= request.getParameter("roleID");
                
                userName = userName.trim();
                if(userName.isEmpty()){
                    error+="Chua nhap User name";
                }
                email = email.trim();
                if(email.isEmpty()){
                    error+="Chua nhap User email";
                }
                password = password.trim();
                if(password!=null && !password.isEmpty()){
                    finalPassword =  PasswordUtils.hashPassword(password);
                }
                fullName = fullName.trim();
                if(fullName.isEmpty()){
                    error+="Chua nhap User fullName";
                }
                s_birthday = s_birthday.trim();
                if(s_birthday.isEmpty()){
                    error+="Chua nhap User ngay sinh";
                }
                gender = gender.trim();
                if(gender.isEmpty()){
                    error+="Chua nhap User gender";
                }
                s_status = s_status.trim();
                if(s_status.isEmpty()){
                    error+="Chua nhap User status";
                }
                s_roleID = s_roleID.trim();
                if(s_roleID.isEmpty()){
                    error+="Chua nhap User roleID";
                }
                
                Date birthday = Date.valueOf(s_birthday);
                int status= Integer.parseInt(s_status);
                int roleID= Integer.parseInt(s_roleID);
                
                if(error.isEmpty()){
                    user = new userDTO(userID, userName, email, finalPassword, avatar, fullName, birthday, gender, null, null, status, roleID);
                    
                    if(udao.updateUser(user)){
                        msg+="Update Thanh Cong!";
                    }else{
                        error+="Updat khong thanh cong!";
                        request.setAttribute("u", user);
                    }
                    request.setAttribute("msg", msg);
                }
                request.setAttribute("mode", "edit");
                request.setAttribute("error", error);
                url="editUser.jsp";
            } catch (Exception e) {
                e.printStackTrace();
            }
            
            RequestDispatcher rd= request.getRequestDispatcher(url);
            rd.forward(request, response);
        }
        
            RequestDispatcher rd= request.getRequestDispatcher(url);
            rd.forward(request, response);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
