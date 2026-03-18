/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modal.universityDao;
import modal.universityDto;

/**
 *
 * @author NQ9
 */
public class updateController extends HttpServlet {

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

        String id = request.getParameter("id");
        String keyword = request.getParameter("keyword");
        String action = request.getParameter("action");
        String url = "";

        universityDao dao = new universityDao();
        universityDto u = dao.searchById(id);

        if (action.equals("update")) {
            request.setAttribute("u", u);
            url = "update-user.jsp";
        } else if (action.equals("save")) {
            String error = "";
            String msg = "";

            try {
                String shortName = request.getParameter("shortName");
                String name = request.getParameter("name");
                String description = request.getParameter("description");
                int foundedYear = Integer.parseInt(request.getParameter("foundedYear"));
                String address = request.getParameter("address");
                String city = request.getParameter("city");
                String region = request.getParameter("region");
                String type = request.getParameter("type");
                int totalStudents = Integer.parseInt(request.getParameter("totalStudents"));
                int totalFaculties = Integer.parseInt(request.getParameter("totalFaculties"));
                boolean status = "1".equals(request.getParameter("status"));

                if (error.isEmpty()) {
                    u = new universityDto(id, name, shortName, description, foundedYear, address, city, region, type, totalStudents, totalFaculties, true, status);
                    if (dao.updateUni(u)) {
                        msg += "update thanh cong!";
                    } else {
                        error += "update that bai!";
                        request.setAttribute("u", u);
                    }
                }
                request.setAttribute("error", error);
                request.setAttribute("msg", msg);
                url = "update-user.jsp";
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        request.getRequestDispatcher(url).forward(request, response);
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
