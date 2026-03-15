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
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        String url = "index.jsp";
        String error = "";
        String msg = "";
        try {
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
            userDAO udao = new userDAO();
            if(error.isEmpty()){
                if(udao.findByUserName(userName)!=null){
                    error+="userName da ton tai<br>";
                }
                if(udao.findByEmail(email)!=null){
                    error+="Email da ton tai<br>";
                }
            }
            if (error.isEmpty()) {
                hashPassword = PasswordUtils.hashPassword(password);
                userDTO user = new userDTO(null, userName, email, hashPassword, null, fullName, birthday, gender, null, null, 1, 2);
                
                if (udao.createUser(user)) {
                    msg += "tao tai khoan thanh cong!";
                } else {
                    error += "tao tai khoan that bai!";
                }
                request.setAttribute("msg", msg);
            }
            request.setAttribute("error", error);
        } catch (Exception e) {
            e.printStackTrace();
        }
        request.getRequestDispatcher(url).forward(request, response);
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
