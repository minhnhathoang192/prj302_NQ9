/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.TopicDAO;
import model.TopicDTO;

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
            url = "homeController";
        } else if (action.equals("login")) {
            url = "loginController";
        } else if (action.equals("logout")) {
            url = "logoutController";
        } else if (action.equals("searchAjax")) {
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
        } else if (action.equals("manage_topic")) {
            url = "manageTopicController";
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
        } else if (action.equals("addTopic")) {
            url = "addTopicController";
        } else if (action.equals("editTopic") || action.equals("saveTopic")) {
            url = "EditTopicController";
        } else if (action.equals("addSongToTopic")) {
            url = "addSongToTopicController";
        } else if (action.equals("loadTopic")) {
            url = "loadTopicController";
        } else if (action.equals("loadMore")) {
            url = "loadMoreController";
        } else if (action.equals("getSongsByTopic")) {
            url = "getSongsByTopicController";
        } else if (action.equals("getRandomTopic")) {
            url = "getRandomTopicController";
        } else if (action.equals("getSongsByMultipleTopics")) {
            url = "getSongsByMultipleTopicsController";
        } else if (action.equals("addArtistToSong")) {
            url = "addArtistToSongController";
        } else if (action.equals("addAlbumToSong")) {
            url = "addAlbumToSongController";
        } else if (action.equals("manage_playlist")) {
            url = "managePlaylistController";
        } else if (action.equals("addPlaylist")) {
            url = "addPlaylistController";
        } else if (action.equals("viewPlaylist") || action.equals("savePlaylist")) {
            url = "viewPlaylistController";
        } else if (action.equals("removeSongFromPlaylist")) {
            url = "removeSongFromPlaylistController";
        } else if (action.equals("deletePlaylist")) {
            url = "deletePlaylistController";
        } else if (action.equals("loadProfile")) {
            url = "loadProfileController";
        } else if (action.equals("getSongsInPlaylist")) {
            url = "getSongsInPlaylistController";
        } else if (action.equals("getPlaylistInfo")) {
            url = "getPlaylistInfoController";
        } else if (action.equals("addSongToPlaylist")) {
            url = "addSongToPlaylistController";
        } else if (action.equals("toggleFavorite")) {
            url = "toggleFavoriteController";
        } else if (action.equals("getFavoriteSongs")) {
            url = "getFavoriteSongsController";
        } else if (action.equals("isFavorite")){
            url = "isFavoriteController";
        } else if(action.equals("addListeningHistory")){
            //add songtoListening History
            url = "addListeningHistoryController";
        } else if(action.equals("getListeningHistory")){
            //loadpage listeningHistory
            url = "getListeningHistoryController";
        } else if(action.equals("addSongToPlaylistFromUser")){
            url = "addSongToPlaylistFromUserController";
        } else if(action.equals("removeSongFromPlaylistFromUser")){
            url = "removeSongFromPlaylistFromUserController";
        } else if(action.equals("editPlaylistFromUser")){
            url = "editPlaylistFromUserController";
        } else if(action.equals("deletePlaylistFromUser")){
            url = "deletePlaylistFromUserController";
        } else if(action.equals("getAlbumInfo")){
            url = "getAlbumInfoController";
        } else if(action.equals("getSongsInAlbum")){
            url = "getSongsInAlbumController";
        } else if(action.equals("getArtistInfo")){
            url = "getArtistInfoController";
        } else if(action.equals("getSongsByArtist")){
            url = "getSongsByArtistController";
        } else if(action.equals("toggleFollowArtist")){
            url = "toggleFollowArtistController";
        } else if(action.equals("getArtistFollowers")){
            url = "getArtistFollowersController";
        } else if(action.equals("isFollowingArtist")){
            url = "isFollowingArtistController";
        } else if(action.equals("getTrendingArtists")){
            url = "getTrendingArtistsController";
        } else if(action.equals("getSongByID")){
            url = "getSongByIDController";
        } else if(action.equals("getMostFavoriteSongs")){
            url = "getMostFavoriteSongsController";
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
