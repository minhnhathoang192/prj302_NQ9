/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import model.TopicDAO;
import model.TopicDTO;

/**
 *
 * @author NQ9
 */

@MultipartConfig(
    fileSizeThreshold = 1024 * 1024,
    maxFileSize = 1024 * 1024 * 50,
    maxRequestSize = 1024 * 1024 * 100
)
public class EditTopicController extends HttpServlet {

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
        String s_topicID = request.getParameter("topicID");

        int topicID = Integer.parseInt(s_topicID);

        TopicDAO tdao = new TopicDAO();
        TopicDTO topic = tdao.getTopicByID(topicID);

        String url = "";

        if (action.equals("editTopic")) {
            request.setAttribute("mode", "edit");
            request.setAttribute("t", topic);
            url = "Topic-From.jsp";
        } else if (action.equals("saveTopic")) {

            String msg = "";
            String error = "";

            try {
                String topicName = request.getParameter("topicName");
                String description = request.getParameter("description");
                String s_isActive = request.getParameter("isActive");

                boolean isActive = "1".equals(s_isActive);

                if (topicName == null || topicName.trim().isEmpty()) {
                    error += "Chưa nhập tên chủ đề<br/>";
                }

                // ===== GET TOPIC CŨ =====
                TopicDTO oldTopic = tdao.getTopicByID(topicID);
                String coverFileName = oldTopic.getCoverImage(); // giữ ảnh cũ

                // ===== UPLOAD FILE =====
                Part coverPart = request.getPart("coverImage");

                String basePath = "C:/Users/NQ9/Documents/GitHub/PRJ302_MUSIC/music_uploads/topic";
                File dir = new File(basePath);
                if (!dir.exists()) {
                    dir.mkdirs();
                }

                if (coverPart != null && coverPart.getSize() > 0) {

                    String originalName = coverPart.getSubmittedFileName();

                    if (!originalName.toLowerCase().matches(".*\\.(jpg|jpeg|png|webp)$")) {
                        error += "Chỉ chấp nhận file ảnh (jpg, png, webp)<br/>";
                    } else {
                        coverFileName = System.currentTimeMillis() + "_" + originalName;
                        coverPart.write(basePath + File.separator + coverFileName);
                    }
                }

                // ===== UPDATE =====
                if (error.isEmpty()) {

                    TopicDTO newTopic = new TopicDTO(
                            topicID,
                            topicName,
                            description,
                            coverFileName,
                            isActive,
                            null
                    );

                    if (tdao.UpdateTopic(newTopic)) {
                        msg = "Update chủ đề thành công!";
                    } else {
                        error = "Update không thành công!";
                        request.setAttribute("t", newTopic);
                    }

                    request.setAttribute("msg", msg);
                }

                request.setAttribute("mode", "edit");
                request.setAttribute("error", error);

                url = "Topic-From.jsp";

            } catch (Exception e) {
                e.printStackTrace();
            }

            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
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
