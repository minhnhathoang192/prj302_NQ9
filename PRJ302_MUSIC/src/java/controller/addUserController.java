/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
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
public class addUserController extends HttpServlet {

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

        userDAO udao = new userDAO();
        String url = "";
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
            String userName = request.getParameter("userName");
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            String fullName = request.getParameter("fullName");
            String s_birthday = request.getParameter("birthday");
            String gender = request.getParameter("gender");

            userName = userName.trim();
            if (userName.isEmpty()) {
                error += "Chua nhap User name";
            }
            email = email.trim();
            if (email.isEmpty()) {
                error += "Chua nhap User email";
            }
            password = password.trim();
            if (password.isEmpty()) {
                error += "Chua nhap User password";
            }
            fullName = fullName.trim();
            if (fullName.isEmpty()) {
                error += "Chua nhap User fullName";
            }

            Date birthday = null;

            if (s_birthday != null && !s_birthday.trim().isEmpty()) {
                birthday = Date.valueOf(s_birthday);
            }

            userDTO user = new userDTO();
            if(error.isEmpty()){
                if(udao.findByUserName(userName)!=null){
                    error+="userName da ton tai<br>";
                }
                if(udao.findByEmail(email)!=null){
                    error+="Email da ton tai<br>";
                }
            }
            
            String hashpass= null;

            if (error.isEmpty()) {
                hashpass =  PasswordUtils.hashPassword(password);
                user = new userDTO(null, userName, email, hashpass, null, fullName, birthday, gender, null, null, 1, 2);

                if (udao.createUser(user)) {
                    msg += "tao Thanh Cong!";
                } else {
                    error += "tao khong thanh cong!";
                    request.setAttribute("u", user);
                }
                request.setAttribute("msg", msg);
            }
            request.setAttribute("error", error);
            url = "User-Form.jsp";
        } catch (Exception e) {
            e.printStackTrace();
        }

        RequestDispatcher rd = request.getRequestDispatcher(url);
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
