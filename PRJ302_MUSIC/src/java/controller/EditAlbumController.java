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
import model.AlbumDAO;
import model.AlbumDTO;

/**
 *
 * @author NQ9
 */
public class EditAlbumController extends HttpServlet {

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
        String s_albumID = request.getParameter("albumID");

        int albumID = Integer.parseInt(s_albumID);

        AlbumDAO adao = new AlbumDAO();
        AlbumDTO album = adao.getAlbumByID(albumID);

        String url = "";

        if (action.equals("editAlbum")) {
            request.setAttribute("a", album);
            request.setAttribute("mode", "edit");
            url = "album-form.jsp";
        } else if (action.equals("saveAlbum")) {

            String msg = "";
            String error = "";
            try {
                String albumName = request.getParameter("albumName");
                String coverImage = request.getParameter("coverImage");
                String s_releaseDate = request.getParameter("releaseDate");
                String s_isActive = request.getParameter("isActive");
                
                boolean isActive = "1".equals(s_isActive);

                albumName = albumName.trim();
                if (albumName.isEmpty()) {
                    error += "chua nhap albumName";
                }
                Date releaseDate = null;
                try {
                    releaseDate = Date.valueOf(s_releaseDate);
                } catch (Exception e) {
                    error += "Ngay khong hop le";
                }

                if (error.isEmpty()) {
                    album = new AlbumDTO(albumID, albumName, coverImage, releaseDate, isActive);

                    if (adao.updateAlbum(album)) {
                        msg += "update album thanh cong";
                    } else {
                        error += "update khong thanh cong";
                        request.setAttribute("a", album);
                    }
                    request.setAttribute("msg", msg);
                }
                request.setAttribute("mode", "edit");
                request.setAttribute("error", error);
                url = "album-form.jsp";
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
