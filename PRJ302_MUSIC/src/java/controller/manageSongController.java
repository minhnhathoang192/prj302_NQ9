/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import static java.nio.file.Files.list;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.AlbumDAO;
import model.AlbumDTO;
import model.AlbumSongDAO;
import model.ArtistDAO;
import model.ArtistDTO;
import model.SongDAO;
import model.SongDTO;

/**
 *
 * @author NQ9
 */
public class manageSongController extends HttpServlet {

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

        String keyword = request.getParameter("keyword");
        SongDAO sdao = new SongDAO();
        ArtistDAO adao = new ArtistDAO();
        AlbumDAO alDao = new AlbumDAO();
        AlbumSongDAO albumSongDao= new AlbumSongDAO();
        
        List<SongDTO> song = sdao.getAllSongs(keyword);
        List<ArtistDTO> artist = adao.getAllActiveArtists();
        List<AlbumDTO> album = alDao.getAllActiveAlbums();
        
        //artist
        Map<Integer, List<ArtistDTO>> ArtistMap = new HashMap<>();

        for (SongDTO s : song) {
            ArtistMap.put(s.getSongID(), sdao.getArtistsBySong(s.getSongID()));
        }
        
        //album
        Map<Integer, List<AlbumDTO>> AlbumMap = new HashMap<>();

        for (SongDTO s : song) {
            AlbumMap.put(s.getSongID(), sdao.getAlbumBySong(s.getSongID()));
        }
        
        request.setAttribute("artistsBySong", ArtistMap);
        request.setAttribute("albumsBySong", AlbumMap);
        request.setAttribute("song", song);
        request.setAttribute("keyword", keyword);
        request.setAttribute("allArtists", artist);
        request.setAttribute("allAlbums", album);

        RequestDispatcher rd = request.getRequestDispatcher("manageSong.jsp");
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
