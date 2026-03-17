/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import model.AlbumDTO;
import model.ArtistDTO;
import model.SongDAO;
import model.SongDTO;

/**
 *
 * @author NQ9
 */
public class EditSongController extends HttpServlet {

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
        request.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");

        String action = request.getParameter("action");
        String s_songID = request.getParameter("songID");

        int songID = Integer.parseInt(s_songID);

        SongDAO dao = new SongDAO();
        SongDTO song = dao.getSongByID(songID);
        List<ArtistDTO> artists = dao.getArtistsBySong(songID);
        List<AlbumDTO> albums = dao.getAlbumBySong(songID);

        String url = "";

        String uploadPath = getServletContext().getRealPath("")
                + File.separator + "assets";
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdir();
        }

        if (action.equals("editSong")) {

            request.setAttribute("s", song);
            request.setAttribute("mode", "edit");
            request.setAttribute("artists", artists);
            request.setAttribute("albums", albums);
            url = "editSong.jsp";

        } else if (action.equals("saveSong")) {

            String error = "";
            String msg = "";

            try {
                String title = request.getParameter("title");
                String lyric = request.getParameter("lyric");
                String s_duration = request.getParameter("duration");
                String s_release = request.getParameter("releaseDate");
                String s_isActive = request.getParameter("isActive");
                boolean isActive = "1".equals(s_isActive);

                int duration = Integer.parseInt(s_duration);
                Date releaseDate = Date.valueOf(s_release);

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
                String audioFileName = song.getAudioURL(); // giua file cu

                if (audioPart != null && audioPart.getSize() > 0) {
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
                String coverFileName = song.getCoverImage(); // giua anh cu

                if (coverPart != null && coverPart.getSize() > 0) {
                    String coverName = coverPart.getSubmittedFileName();
                    coverFileName = System.currentTimeMillis() + "_" + coverName;
                    coverPart.write(basePath + "/cover/" + coverFileName);
                }

                if (error.isEmpty()) {
                    song = new SongDTO(
                            songID,
                            title,
                            duration,
                            audioFileName,
                            lyric,
                            releaseDate,
                            coverFileName,
                            isActive
                    );
                    if (dao.updateSong(song)) {
                        msg += "update Thanh Cong";
                    } else {
                        error += "update khong thanh Cong";
                        request.setAttribute("s", song);
                    }
                    request.setAttribute("msg", msg);
                }
                request.setAttribute("mode", "edit");
                request.setAttribute("error", error);
                url = "editSong.jsp";
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
