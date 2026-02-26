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

        String url = "";

        if (action.equals("editSong")) {

            request.setAttribute("s", song);
            request.setAttribute("mode", "edit");
            url = "editSong.jsp";

        } else if (action.equals("saveSong")) {

            String error = "";
            String msg = "";

            try {
                String title = request.getParameter("title");
                String audioURL = request.getParameter("audioURL");
                String lyric = request.getParameter("lyric");
                String coverImage = request.getParameter("coverImage");
                String s_duration = request.getParameter("duration");
                String s_release = request.getParameter("releaseDate");

                int duration = Integer.parseInt(s_duration);
                Date releaseDate = Date.valueOf(s_release);

                if (error.isEmpty()) {
                    song = new SongDTO(
                            songID,
                            title,
                            duration,
                            audioURL,
                            lyric,
                            releaseDate,
                            coverImage,
                            true
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
