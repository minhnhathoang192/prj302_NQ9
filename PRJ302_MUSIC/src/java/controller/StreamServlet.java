/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/StreamServlet") // map URL /StreamServlet class này 
// http:/localhost:8080/StreamServlet?type=audio&file=a.mp3
/**
 *
 * @author NQ9
 */
public class StreamServlet extends HttpServlet {

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
        // set encoding
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        
        // lay parameter
        // StreamServlet?type=audio&file=song.mp3
        // type = "audio"
        //file = "song.mp3"
        String file = request.getParameter("file");
        String type = request.getParameter("type");
        
        // thu muc goc luu file tren sever 
        String basePath = "C:/Users/NQ9/Documents/GitHub/PRJ302_MUSIC/music_uploads";
        
        /*
        Tao file objet 
        type = audio
        file = song.mp3

        - path:
        music_uploads/audio/song.mp3
        */
        File f = new File(basePath + "/" + type + "/" + file); //xac dinh the vat ly tren sever
        
        // check file neu khong ton tai tra 404
        if (!f.exists()) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        
        
        // ===== SET CONTENT TYPE =====
        if ("audio".equals(type)) {
            response.setContentType("audio/mpeg"); // danh dau là file mp3

            // quan trọng để browser cho phép tua
            response.setHeader("Accept-Ranges", "bytes"); //Header Accept-Ranges giúp browser request từng phần của file
            
        } else if ("cover".equals(type) || "topic".equals(type) || "artist".equals(type) || "album".equals(type)) { // xu ly anh

            String mime = Files.probeContentType(f.toPath()); // tu detect MIME type (image/png, image/jpeg) 
            
            //fallback neu khong detect duoc
            if (mime == null) {
                mime = "application/octet-stream";
            }
            
            //set content type cho brower 
            response.setContentType(mime);
        }

        // ===== SET FILE SIZE =====
        response.setContentLengthLong(f.length()); // gui kich thuoc cho brower tac dung hien thuc process ho tro stream

        // ===== STREAM FILE =====
        try ( OutputStream out = response.getOutputStream();  
                InputStream in = Files.newInputStream(f.toPath())) { // mo file doc + luong ghi resqone 

            byte[] buffer = new byte[8192]; // doc tung chuck 8kb // khong load toan bo file vào ram
            // luu so byte
            int bytesRead;

            // doc tung doan cho den khi het file 
            while ((bytesRead = in.read(buffer)) != -1) { 
                out.write(buffer, 0, bytesRead); // gui duu lieu ve cho client 
            }

            out.flush(); // day het du lieu ra ngoai
        }
    }
    
    /*
        Client request audio
                =
        StreamServlet
                =
        tìm file trên server
                =
        set content-type
                =
        set header (range)
                =
        đọc file từng chunk
                =
        ghi vào response
                =
        browser nhận stream 
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
