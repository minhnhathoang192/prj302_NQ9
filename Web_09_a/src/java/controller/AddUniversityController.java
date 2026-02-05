/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.UniversityDAO;
import model.UniversityDTO;

/**
 *
 * @author NQ9
 */
public class AddUniversityController extends HttpServlet {

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

        String error = "";
        String msg = "";
        String url = "";
        try {
            String id = request.getParameter("id");
            String name = request.getParameter("name");
            String shortName = request.getParameter("shortName");
            String description = request.getParameter("description");
            String s_foundedYear = request.getParameter("foundedYear");
            String address = request.getParameter("address");
            String city = request.getParameter("city");
            String region = request.getParameter("region");
            String type = request.getParameter("type");
            String s_totalStudents = request.getParameter("totalStudents");
            String s_totalFaculties = request.getParameter("totalFaculties");
            boolean isDraft = request.getParameter("isDraft")!=null;

            id = id.trim();
            if (id.isEmpty()) {
                error += ("chua nhap id <br/>");
            }
            name = name.trim();
            if (name.isEmpty()) {
                error += ("chua nhap name <br/>");
            }

            shortName = shortName.trim();
            if (shortName.isEmpty()) {
                error += ("chua nhap shortName <br/>");
            }
            
            s_foundedYear = s_foundedYear.trim();
            if (s_foundedYear.isEmpty()) {
                error += ("chua nhap chua nhap nam <br/>");
            }
            address = address.trim();
            if (address.isEmpty()) {
                error += ("chua nhap dia chi <br/>");
            }
            city = city.trim();
            if (city.isEmpty()) {
                error += ("chua nhap thanh pho <br/>");
            }
            region = region.trim();
            if (region.isEmpty()) {
                error += ("chua nhap khu pho <br/>");
            }
            type = type.trim();
            if (type.isEmpty()) {
                error += ("chua nhap type <br/>");
            }
            s_totalStudents = s_totalStudents.trim();
            if (s_totalStudents.isEmpty()) {
                error += ("chua nhap so luong hocj sinh <br/>");
            }
            s_totalFaculties = s_totalFaculties.trim();
            if (s_totalFaculties.isEmpty()) {
                error += ("chua nhap chua nhap <br/>");
            }

            UniversityDAO udao = new UniversityDAO();
            UniversityDTO u = udao.searchbyID(id);
            if (u != null) {
                error += ("Id da ton tai ! vui long nhap lai<br/>");
            }

            int foundedYear = 0;
            try {
                foundedYear = Integer.parseInt(s_foundedYear);
                if (foundedYear < 0) {
                    error += ("Nam thanh lap phai la so nguyen");
                }
            } catch (Exception e) {
                error += ("Nam thanh lap phai la so nguyen");
            }
            int totalStudents = 0;
            try {
                totalStudents = Integer.parseInt(s_totalStudents);
                if (totalStudents < 0) {
                    error += ("tong so hoc sinh phai la so nguyen");
                }
            } catch (Exception e) {
                error += ("tong so hoc sinh phai la so nguyen");
            }
            int totalFaculties = 0;
            try {
                totalFaculties = Integer.parseInt(s_totalFaculties);
                if (totalFaculties < 0) {
                    error += ("SO sinh vien phai la so nguyen duong");
                }
            } catch (Exception e) {
                error += ("SO sinh vien phai la so nguyen duong");
            }


            if (error.isEmpty()) {
                u = new UniversityDTO(id, name, shortName, description, foundedYear, address, city, region, type, totalStudents, totalFaculties, isDraft);

                //khong co loi
                if (udao.add(u)) {
                    msg = "Da thanh University thanh cong";
                } else {
                    error += "Gap loi, khong the them University";
                    request.setAttribute("u", u);
                }
                request.setAttribute("msg", msg);
            }
            request.setAttribute("error", error);
            url = "University-form.jsp";
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
