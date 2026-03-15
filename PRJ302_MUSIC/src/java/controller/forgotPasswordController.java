/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.UUID;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.userDAO;
import model.userDTO;
import utils.EmailUtils;

/**
 *
 * @author NQ9
 */
public class forgotPasswordController extends HttpServlet {

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
        try {
            String email = request.getParameter("email");
            userDAO udao = new userDAO();
            userDTO user = udao.findByEmail(email);
            if (user != null) {
                //tao token
                String token = UUID.randomUUID().toString();
                //luu token vao db
                boolean save = udao.saveRestToken(email, token);

                if (save) {
                    //tao link resst
                    String link = request.getScheme() + "://"
                            + request.getServerName() + ":"
                            + request.getServerPort()
                            + request.getContextPath()
                            + "/components/rest-password.jsp?token=" + token;

                    //noi dung email
                    String subject = "Reset Password - QNQ Music";

                    String content = "Click vào link bên dưới để đặt lại mật khẩu:\n\n"
                            + link
                            + "\n\nLink sẽ hết hạn sau 15 phút.";

                    //gui email
                    EmailUtils.sendEmail(email, subject, content);

                    request.setAttribute("msgEmail", "Link Rest password da duoc gui den email!");
                }else{
                    request.setAttribute("errorEmail", "loi he thong email");
                }
            } else {
                request.setAttribute("errorEmail", "email khong ton tai!");
            }
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
