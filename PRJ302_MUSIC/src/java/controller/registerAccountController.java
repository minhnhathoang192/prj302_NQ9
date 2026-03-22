/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
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
public class registerAccountController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    // Ham xu ly Request (POST)
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //set Encoding
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        String url = "index.jsp"; // trang tra ve 
        String error = ""; // 
        String msg = "";
        try {
            //Lay input user Nhâp
            String userName = request.getParameter("userName");
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            String confirmPassword = request.getParameter("confirmPassword");
            String fullName = request.getParameter("fullName");
            String s_birthday = request.getParameter("birthday");
            String gender = request.getParameter("gender");

            if (userName == null || userName.trim().isEmpty()) {
                error += "chua nhap userName";
            }
            if (email == null || email.trim().isEmpty()) {
                error += "chua nhap email";
            }
            if (password == null || password.trim().isEmpty()) {
                error += "chua nhap password";
            }
            if (confirmPassword == null || confirmPassword.trim().isEmpty()) {
                error += "chua nhap confirmPassword";
            }
            
            //check confim password
            if (password != null && !password.equals(confirmPassword)) {
                error += "Mật khẩu không khớp<br>";
            }
            if (fullName == null || fullName.trim().isEmpty()) {
                error += "chua nhap fullName";
            }
            Date birthday = null;
            try {
                birthday = Date.valueOf(s_birthday);
            } catch (Exception e) {
                error += "ngay khong hop le";
            }
            if (gender == null || gender.trim().isEmpty()) {
                error += "chua nhap gender";
            }

            String hashPassword = null;
            //Goi DB
            userDAO udao = new userDAO();
            if(error.isEmpty()){ //check loi
                //chek user
                if(udao.findByUserName(userName)!=null){
                    error+="userName da ton tai<br>";
                }
                //check email
                if(udao.findByEmail(email)!=null){
                    error+="Email da ton tai<br>";
                }
            }
            
            if (error.isEmpty()) {
                //bao mat password
                hashPassword = PasswordUtils.hashPassword(password);
                //tao object user
                userDTO user = new userDTO(null, userName, email, hashPassword, null, fullName, birthday, gender, null, null, 1, 2);
                
                //insert DB
                if (udao.createUser(user)) {
                    msg += "tao tai khoan thanh cong!";
                } else {
                    error += "tao tai khoan that bai!";
                }
                
                request.setAttribute("msg", msg); // bao thanh cong
            }
            request.setAttribute("error", error); // bao loi
        } catch (Exception e) {
            e.printStackTrace();
        }
        // forward giu request giua error
        request.getRequestDispatcher(url).forward(request, response);
    }
    
    /*
            User submit form
                    =
            registerAccountController
                    =
            Lấy dữ liệu
                    =
            Validate (null, password, date)
                    =
            Check trùng DB
                    =
            Nếu OK:
                hash password
                    =
                insert DB
                    =
                set msg
            Nếu lỗi:
                set error
                    =
            forward index.jsp
                    =
            JSP hiển thị error/msg
                    =
            JS mở lại modal
    */

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
