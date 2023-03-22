/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.shop;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
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

    private static final String MAIN = "/WEB-INF/layouts/main.jsp";

    private static final String SHOP = "shop";
    private static final String SHOP_PAGE = "index";

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
        String action = (String) request.getAttribute("action");
        ProductDAO tf = new ProductDAO();
        String op;
        switch (action) {
            case "index":
                index(request, response, tf);
                request.getRequestDispatcher(MAIN).forward(request, response);
                break;
                
            case "create_handler": //Xu ly create form
                op = request.getParameter("op");
                if ("create".equals(op)) {
                    create(request, response, tf);
                }
                response.sendRedirect(request.getContextPath() + "/" + SHOP_PAGE + ".do");
                break;
                
            case "edit"://Hien form de sua du lieu
                edit(request, response, tf);
                break;
                
            case "edit_handler": //Luu thong tin vao db
                op = request.getParameter("op");
                if ("update".equals(op)) {
                    update(request, response, tf);
                }
                response.sendRedirect(request.getContextPath() + "/" + SHOP_PAGE + ".do");
                break;
                
            case "delete":
                delete(request, response, tf);
                response.sendRedirect(request.getContextPath() + "/" + SHOP_PAGE + ".do");
                break;
        }
        
    }

    private void index(HttpServletRequest request, HttpServletResponse response, ProductDAO tf) {
        try {
            List<ProductDTO> list = tf.select();
            int page = 1;
            int recordsPerPage = 8;
            if (request.getParameter("page") != null) {
                page = Integer.parseInt(request.getParameter("page"));
            }
            list = list.subList(recordsPerPage * (page - 1), recordsPerPage * (page - 1) + recordsPerPage > list.size() ? list.size() : recordsPerPage * (page - 1) + recordsPerPage);

            int noOfPages = (int) Math.ceil(tf.GetNoOfRecords() * 1.0 / recordsPerPage);
            request.setAttribute("list", list);
            request.setAttribute("noOfPages", noOfPages);
            request.setAttribute("currentPage", page);

        } catch (SQLException ex) {
            //Hien trang thong bao loi
            ex.printStackTrace();//in thong bao loi chi tiet cho developer
            request.setAttribute("message", ex.getMessage());
        }
    }

    private void create(HttpServletRequest request, HttpServletResponse response, ProductDAO tf) {
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
            request.setAttribute("product", product);
            tf.create(product);
            request.setAttribute("action", SHOP_PAGE);
        } catch (NumberFormatException | SQLException ex) {
            //Hien create form de nhap lai du lieu
            ex.printStackTrace();//in thong bao loi chi tiet cho developer
            request.setAttribute("action", "create");
            request.setAttribute("message", ex.getMessage());
        }
    }

    private void edit(HttpServletRequest request, HttpServletResponse response, ProductDAO tf) {
        try {
            String id = request.getParameter("id");
            ProductDTO product = tf.read(id);
            request.setAttribute("product", product);

        } catch (SQLException ex) {
            //Hien trang thong bao loi
            ex.printStackTrace();//in thong bao loi chi tiet cho developer
            request.setAttribute("message", ex.getMessage());
            request.setAttribute("action", "create");

        }
    }

    private void update(HttpServletRequest request, HttpServletResponse response, ProductDAO tf) {
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
        } catch (IOException | NumberFormatException | SQLException ex) {
            //Hien trang thong bao loi
            ex.printStackTrace();//in thong bao loi chi tiet cho developer
            request.setAttribute("message", ex.getMessage());
            request.setAttribute("action", "create");

        }
    }

    private void delete(HttpServletRequest request, HttpServletResponse response, ProductDAO tf) {
        try {
            String id = request.getParameter("id");
            tf.delete(id);
        } catch (SQLException ex) {
            //Hien trang thong bao loi
            ex.printStackTrace();//in thong bao loi chi tiet cho developer
            request.setAttribute("message", ex.getMessage());
            request.setAttribute("action", "create");

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
