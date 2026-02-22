/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.SongDAO;
import model.SongDTO;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.Part;


@MultipartConfig(
    fileSizeThreshold = 1024 * 1024,
    maxFileSize = 1024 * 1024 * 50,
    maxRequestSize = 1024 * 1024 * 100
)

/**
 *
 * @author NQ9
 */
public class addSongController extends HttpServlet {

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

        String msg = "";
        String error = "";
        String url = "upload-content.jsp";

        String uploadPath = getServletContext().getRealPath("")
                + File.separator + "assets";
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdir();
        }

        try {

            String title = request.getParameter("title");
            String s_duration = request.getParameter("duration");
            String lyric = request.getParameter("lyric");
            String s_releaseDate = request.getParameter("releaseDate");
            String s_isActive = request.getParameter("isActive");

            if (title == null || title.trim().isEmpty()) {
                error += "Chưa nhập tên bài hát<br/>";
            }

            int duration = 0;
            try {
                duration = Integer.parseInt(s_duration);
                if (duration <= 0) {
                    error += "Thời lượng phải > 0<br/>";
                }
            } catch (Exception e) {
                error += "Thời lượng không hợp lệ<br/>";
            }

            Date releaseDate = null;
            try {
                releaseDate = Date.valueOf(s_releaseDate);
            } catch (Exception e) {
                error += "Ngày phát hành không hợp lệ<br/>";
            }

            boolean isActive = "1".equals(s_isActive);

            String basePath = "C:/Users/NQ9/Documents/GitHub/PRJ302_MUSIC/music_uploads";

            File audioDir = new File(basePath + "/audio");
            if (!audioDir.exists()) {
                audioDir.mkdirs();
            }

            File coverDir = new File(basePath + "/cover");
            if (!coverDir.exists()) {
                coverDir.mkdirs();
            }

            // ===== AUDIO =====
            Part audioPart = request.getPart("audioURL");
            String audioFileName = null;

            if (audioPart == null || audioPart.getSize() == 0) {
                error += "Chưa chọn file nhạc<br/>";
            } else {
                String originalName = audioPart.getSubmittedFileName();

                if (!originalName.toLowerCase().endsWith(".mp3")
                        && !originalName.toLowerCase().endsWith(".m4a")) {
                    error += "Chỉ chấp nhận file .mp3 hoặc .m4a<br/>";
                } else {
                    audioFileName = System.currentTimeMillis() + "_" + originalName;
                    audioPart.write(basePath + "/audio/" + audioFileName);
                }
            }

            // ===== COVER =====
            Part coverPart = request.getPart("coverImage");
            String coverFileName = null;

            if (coverPart != null && coverPart.getSize() > 0) {
                String coverName = coverPart.getSubmittedFileName();
                coverFileName = System.currentTimeMillis() + "_" + coverName;
                coverPart.write(basePath + "/cover/" + coverFileName);
            }

            if (error.isEmpty()) {
                SongDAO sDao = new SongDAO();

                SongDTO song = new SongDTO(
                        0,
                        title,
                        duration,
                        audioFileName, // luôn dùng /
                        lyric,
                        releaseDate,
                        coverFileName,
                        isActive
                );

                if (sDao.createSong(song)) {
                    msg = "Thêm nhạc thành công!";
                } else {
                    error = "Lỗi khi lưu database!";
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            error = "Lỗi hệ thống!";
        }

        request.setAttribute("msg", msg);
        request.setAttribute("error", error);
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
