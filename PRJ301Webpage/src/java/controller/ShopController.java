/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.product.ProductDAO;
import model.product.ProductDTO;

/**
 *
 * @author PHT
 */
@WebServlet(name = "ShopController", urlPatterns = {"/shop"})
public class ShopController extends HttpServlet {

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
        String controller = (String) request.getAttribute("controller");
        String action = (String) request.getAttribute("action");
        ProductDAO tf = new ProductDAO();
        switch (action) {
            case "index":
                try {
                    List<ProductDTO> list = tf.select();
                    request.setAttribute("list", list);
                    request.getRequestDispatcher("/WEB-INF/layouts/main.jsp").forward(request, response);
                } catch (SQLException ex) {
                    //Hien trang thong bao loi
                    ex.printStackTrace();//in thong bao loi chi tiet cho developer
                    request.setAttribute("message", ex.getMessage());
                    request.setAttribute("controller", "error");
                    request.setAttribute("action", "error");
                    request.getRequestDispatcher("/WEB-INF/layouts/main.jsp").forward(request, response);
                }
                break;

            case "create"://Hien form de nhap du lieu moi
                request.getRequestDispatcher("/WEB-INF/layouts/main.jsp").forward(request, response);
                break;
            case "create_handler": {//Xu ly create form
                String op = request.getParameter("op");
                switch (op) {
                    case "create":
                        try {
                            String proId = request.getParameter("id");
                            String brandId = request.getParameter("brand");
                            String type = request.getParameter("type");
                            double price = Double.parseDouble(request.getParameter("price"));
                            int sale = Integer.parseInt(request.getParameter("sale"));
                            int stock = Integer.parseInt(request.getParameter("stock"));
                            String ageGroup = request.getParameter("ageGroup");
                            String size = request.getParameter("size");
                            String color = request.getParameter("color");

                            ProductDTO product = new ProductDTO(proId, brandId, type, price, sale, stock, ageGroup, size, color);
                            //Luu toy vao request de bao ton trang thai cua form
                            request.setAttribute("product", product);
                            //C?p nh?t d? li?u v�o db
                            tf.create(product);
                            //Hi?n th? danh s�ch c�c m?u tin c?a table toy
                            response.sendRedirect(request.getContextPath() + "/index.do");
                        } catch (Exception ex) {
                            //Hien create form de nhap lai du lieu
                            ex.printStackTrace();//in thong bao loi chi tiet cho developer
                            request.setAttribute("action", "create");
                            request.setAttribute("message", ex.getMessage());
                            request.getRequestDispatcher("/WEB-INF/layouts/main.jsp").forward(request, response);
                        }
                        break;
                    case "cancel":
                        //Hi?n th? danh s�ch c�c m?u tin c?a table toy
                        response.sendRedirect(request.getContextPath() + "/index.do");
                        break;
                }
            }
            break;
            case "edit"://Hien form de sua du lieu
                try {
                    //??c m?u tin c?n s?a v�o ??i t??ng toy
                    String id = request.getParameter("id");
                    ProductDTO product = tf.read(id);
                    //L?u toy v�o request ?? truy?n cho view edit.jsp
                    request.setAttribute("product", product);
                    //Chuy?n request & response ??n view edit.jsp ?? x? ly ti?p
                    request.getRequestDispatcher("/WEB-INF/layouts/main.jsp").forward(request, response);
                } catch (SQLException ex) {
                    //Hien trang thong bao loi
                    ex.printStackTrace();//in thong bao loi chi tiet cho developer
                    request.setAttribute("message", ex.getMessage());
                    request.setAttribute("controller", "error");
                    request.setAttribute("action", "error");
                    request.getRequestDispatcher("/WEB-INF/layouts/main.jsp").forward(request, response);
                }
                break;
            case "edit_handler": {//Luu thong tin vao db
                String op = request.getParameter("op");
                switch (op) {
                    case "update":
                        try {
                            String proId = request.getParameter("id");
                            String brandId = request.getParameter("brand");
                            String type = request.getParameter("type");
                            double price = Double.parseDouble(request.getParameter("price"));
                            int sale = Integer.parseInt(request.getParameter("sale"));
                            int stock = Integer.parseInt(request.getParameter("stock"));
                            String ageGroup = request.getParameter("ageGroup");
                            String size = request.getParameter("size");
                            String color = request.getParameter("color");

                            ProductDTO product = new ProductDTO(proId, brandId, type, price, sale, stock, ageGroup, size, color);
                            tf.update(product);
                            //Hi?n th? danh s�ch c�c m?u tin c?a table toy
                            response.sendRedirect(request.getContextPath() + "/index.do");
                        } catch (Exception ex) {
                            //Hien trang thong bao loi
                            ex.printStackTrace();//in thong bao loi chi tiet cho developer
                            request.setAttribute("message", ex.getMessage());
                            request.setAttribute("controller", "error");
                            request.setAttribute("action", "error");
                            request.getRequestDispatcher("/WEB-INF/layouts/main.jsp").forward(request, response);
                        }
                        break;
                    case "cancel":
                        //Hi?n th? danh s�ch c�c m?u tin c?a table toy
                        response.sendRedirect(request.getContextPath() + "/index.do");
                        break;
                }
            }
            break;
            case "delete":
                try {
                    String id = request.getParameter("id");
                    tf.delete(id);
                    //Chuyen den trang /toy?op=list
                    response.sendRedirect(request.getContextPath() + "/index.do");
                } catch (SQLException ex) {
                    //Hien trang thong bao loi
                    ex.printStackTrace();//in thong bao loi chi tiet cho developer
                    request.setAttribute("message", ex.getMessage());
                    request.setAttribute("controller", "error");
                    request.setAttribute("action", "error");
                    request.getRequestDispatcher("/WEB-INF/layouts/main.jsp").forward(request, response);
                }
                break;
        }
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
        int page = 1;
        int recordsPerPage = 8;
        if (request.getParameter("page") != null) {
            page = Integer.parseInt(request.getParameter("page"));
        }
        ProductDAO product = new ProductDAO();
        List<ProductDTO> list = new ArrayList();
        try {
            list = product.select();
            list = list.subList(recordsPerPage * (page - 1), recordsPerPage * (page - 1) + recordsPerPage > list.size() ? list.size() : recordsPerPage * (page - 1) + recordsPerPage);
        } catch (SQLException ex) {
            Logger.getLogger(ShopController.class.getName()).log(Level.SEVERE, null, ex);
        }
        int noOfRecords = 0;
        try {
            noOfRecords = product.GetNoOfRecords();
        } catch (SQLException ex) {
            Logger.getLogger(ShopController.class.getName()).log(Level.SEVERE, null, ex);
        }
        int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
        request.setAttribute("list", list);
        request.setAttribute("noOfPages", noOfPages);
        request.setAttribute("currentPage", page);
        request.getRequestDispatcher("/WEB-INF/layouts/main.jsp").forward(request, response);
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
