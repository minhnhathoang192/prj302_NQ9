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
public class addAlbumController extends HttpServlet {

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
        
        String url="album-form.jsp";
        String msg="";
        String error="";
        try {
            String albumName= request.getParameter("albumName");
            String coverImage= request.getParameter("coverImage");
            String s_releaseDate= request.getParameter("releaseDate");
            
            albumName= albumName.trim();
            if(albumName.isEmpty()){
                error+="chua nhap albumName";
            }
            Date releaseDate=null;
            try {
                releaseDate = Date.valueOf(s_releaseDate);
            } catch (Exception e) {
                error+="Ngay khong hop le";
            }
            AlbumDAO adao= new AlbumDAO();
            if(error.isEmpty()){
                AlbumDTO album= new AlbumDTO(0, albumName, coverImage, releaseDate, true);
                
                if(adao.createAlbum(album)){
                    msg+="Tao album thanh cong";
                }else{
                    error+="tao khong thanh cong";
                    request.setAttribute("s", album);
                }
                request.setAttribute("msg", msg);
            }
            request.setAttribute("error", error);
            url = "album-form.jsp";
        } catch (Exception e) {
            e.printStackTrace();
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
