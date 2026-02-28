/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@MultipartConfig(
        fileSizeThreshold = 1024 * 1024,
        maxFileSize = 1024 * 1024 * 50,
        maxRequestSize = 1024 * 1024 * 100
)
/**
 *
 * @author NQ9
 */
public class MainController extends HttpServlet {

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
        String url = "index.jsp";

        if (action == null) {
            url = "index.jsp";
        } else if (action.equals("login")) {
            url = "loginController";
        } else if (action.equals("logout")) {
            url = "logoutController";
        } else if (action.equals("search")) {
            url = "searchSongController";
        } else if (action.equals("adminDashboard")) {
            url = "adminController";
        } else if (action.equals("manage_user")) {
            url = "manageUserController";
        } else if (action.equals("manage_song")) {
            url = "manageSongController";
        } else if (action.equals("manage_album")) {
            url = "manageAlbumController";
        } else if (action.equals("manage_artist")) {
            url = "manageAtistController";
        } else if (action.equals("manage_comment")) {
            url = "manageCommentController";
        } else if (action.equals("deleteUser")) {
            url = "deleteUserController";
        } else if (action.equals("editUser") || action.equals("saveUser")) {
            url = "EditUserController";
        } else if (action.equals("registerUser")) {
            url = "addUserController";
        } else if (action.equals("deleteSong")) {
            url = "deleteSongController";
        } else if (action.equals("editSong") || action.equals("saveSong")) {
            url = "EditSongController";
        } else if (action.equals("addSong")) {
            url = "/addSongController";
        } else if (action.equals("deleteAlbum")) {
            url = "deleteAlbumController";
        } else if (action.equals("addAlbum")) {
            url = "addAlbumController";
        } else if (action.equals("editAlbum") || action.equals("saveAlbum")) {
            url = "EditAlbumController";
        } else if (action.equals("deleteArtist")) {
            url = "deleteArtistController";
        } else if (action.equals("editArtist") || action.equals("saveArtist")) {
            url = "EditArtistController";
        } else if (action.equals("addArtist")) {
            url = "AddArtistController";
        } else if (action.equals("getRandomPlaylist")) {
            url = "RamdomSongController";
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
