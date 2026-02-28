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
import model.ArtistDAO;
import model.ArtistDTO;

/**
 *
 * @author NQ9
 */
public class EditArtistController extends HttpServlet {

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
        String s_artistID = request.getParameter("artistID");

        int artistID = Integer.parseInt(s_artistID);

        ArtistDAO artistDao = new ArtistDAO();
        ArtistDTO artist = artistDao.getArtistByID(artistID);

        String url = "";

        if (action.equals("editArtist")) {
            request.setAttribute("mode", "edit");
            request.setAttribute("a", artist);
            url = "artist-form.jsp";
        } else if (action.equals("saveArtist")) {
            String msg = "";
            String error = "";
            try {
                String artistName = request.getParameter("artistName");
                String avatarURL = request.getParameter("avatarURL");
                String description = request.getParameter("description");
                String s_debutDate = request.getParameter("debutDate");
                String s_isActive = request.getParameter("isActive");

                boolean isActive = "1".equals(s_isActive);

                artistName = artistName.trim();
                if (artistName.isEmpty()) {
                    error += "Chua nhap tenn tac gia";
                }

                Date debutDate = null;
                try {
                    debutDate = Date.valueOf(s_debutDate);
                } catch (Exception e) {
                    error += "Khong hop le debutDate";
                }

                if (error.isEmpty()) {
                    artist = new ArtistDTO(artistID, artistName, avatarURL, description, debutDate, isActive);

                    if (artistDao.updateArtist(artist)) {
                        msg += "Update tac gia thanh cong!";
                    } else {
                        error += "Update tac gia that bai!";
                        request.setAttribute("a", artist);
                    }
                    request.setAttribute("msg", msg);
                }
                request.setAttribute("mode", "edit");
                request.setAttribute("error", error);
                url = "artist-form.jsp";
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
