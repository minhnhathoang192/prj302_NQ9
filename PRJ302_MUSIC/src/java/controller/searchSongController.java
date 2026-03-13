
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.AlbumDAO;
import model.AlbumDTO;
import model.ArtistDAO;
import model.ArtistDTO;
import model.PlayListtDAO;
import model.PlayListtDTO;
import model.SongDAO;
import model.SongDTO;
import model.userDAO;

@WebServlet(name = "searchSongController", urlPatterns = {"/searchSongController"})

/**
 *
 * @author NQ9
 */
public class searchSongController extends HttpServlet {

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
        response.setContentType("application/json;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        String keyword = request.getParameter("keyword");

        SongDAO sdao = new SongDAO();
        ArtistDAO adao= new ArtistDAO();
        AlbumDAO aldao= new AlbumDAO();
        PlayListtDAO pdao =new PlayListtDAO();

        List<SongDTO> songs= sdao.searchSongs(keyword);
        List<ArtistDTO> artists = adao.searchArtists(keyword);
        List<AlbumDTO> albums= aldao.searchAlbums(keyword);
        List<PlayListtDTO> playlists= pdao.searchPlaylists(keyword);
        
        Map<String, Object> result= new HashMap<>();
        result.put("songs", songs);
        result.put("artists", artists);
        result.put("albums", albums);
        result.put("playlists", playlists);

        Gson gson= new Gson();
        response.getWriter().write(gson.toJson(result));
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
