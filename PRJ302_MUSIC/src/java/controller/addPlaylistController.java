/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.PlayListtDAO;
import model.PlayListtDTO;
import model.userDTO;

/**
 *
 * @author NQ9
 */
public class addPlaylistController extends HttpServlet {

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

        String source = request.getParameter("source");

        HttpSession session = request.getSession();

        String msg = "";
        String error = "";

        Map<String, Object> result = new HashMap<>();

        try {

            String playListName = request.getParameter("playListName");
            String s_isPublic = request.getParameter("isPublic");

            userDTO user = (userDTO) session.getAttribute("user");

            int userID = Integer.parseInt(user.getUserID());

            if (playListName == null || playListName.trim().isEmpty()) {

                error = "Vui lòng nhập tên playlist";

            } else {

                playListName = playListName.trim();

                boolean isPublic = "1".equals(s_isPublic);

                PlayListtDAO pdao = new PlayListtDAO();

                PlayListtDTO playlist = new PlayListtDTO(0, playListName, userID, isPublic, null);

                if (pdao.createPlaylist(playlist)) {

                    msg = "Thêm playlist thành công";

                    result.put("success", true);
                    result.put("playlist", playlist);

                } else {

                    error = "Thêm playlist thất bại";

                    result.put("success", false);
                    result.put("message", error);

                }

            }

        } catch (Exception e) {

            e.printStackTrace();

            error = "Lỗi hệ thống";

            result.put("success", false);
            result.put("message", error);

        }

        // ===== ADMIN =====
        if ("admin".equals(source)) {

            request.setAttribute("msg", msg);
            request.setAttribute("error", error);

            request.getRequestDispatcher("playList-form.jsp").forward(request, response);

        } // ===== USER (FETCH) =====
        else {

            response.setContentType("application/json;charset=UTF-8");

            Gson gson = new Gson();

            response.getWriter().print(gson.toJson(result));

        }

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
