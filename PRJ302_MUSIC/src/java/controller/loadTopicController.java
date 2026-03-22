/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.SongDTO;
import model.TopicDAO;
import model.TopicDTO;
import model.TopicSongDAO;
import model.TopicSongDTO;

/**
 *
 * @author NQ9
 */
public class loadTopicController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    // xu ly request tu js
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //set encoding tra ve html 
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        
        // lay parameter MainController?action=loadTopic&topicID=5
        String s_topicID = request.getParameter("topicID");
            
        // kiem tra rong 
        if (s_topicID == null || s_topicID.trim().isEmpty()) {
            response.sendError(400, "topicID is missing!"); // tra loi HTTP 404 
            return;
        }
        
        // chuyen String sang int "5" -> 5
        int topicID = Integer.parseInt(s_topicID);
        
        // Goi dao 
        TopicSongDAO tsdao = new TopicSongDAO();
        List<SongDTO> song = tsdao.getSongsByTopic(topicID); // query DB lay bai hat trong topic do 
        
        // goi dao 
        TopicDAO tdao = new TopicDAO();
        TopicDTO topic = tdao.getTopicByID(topicID);// lay ds ten topic 

        // gui list bai hat sang jsp
        request.setAttribute("songs", song);   // topic-content.jsp 
        // gui ten topic sang jsp
        request.setAttribute("topicName", topic.getTopicName());
//        request scope:
//            - songs
//            - topicName

        //render jsp - tra html fragment ve client
        request.getRequestDispatcher("components/ChuDe/topic-content.jsp")
                .forward(request, response); // sever render html roi gui ve 
    }
    
    /*
      JS fetch loadTopic
            =
    MainController
            =
    loadTopicController
            =
    DAO lấy data
            =
    setAttribute
            =
    forward JSP
            =
    HTML trả về client
    */

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
