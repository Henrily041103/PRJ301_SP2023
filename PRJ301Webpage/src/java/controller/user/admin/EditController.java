/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.user.admin;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.product.ProductDAO;
import model.product.ProductDTO;

/**
 *
 * @author Admin
 */
@WebServlet(name = "EditController", urlPatterns = {"/edit"})
public class EditController extends HttpServlet {
    private static final String ERROR = "login.jsp";
    private static final String SHOP = "indexAd.jsp";
    private static final String EDIT = "edit.jsp";
    private static final String CREATE = "create.jsp";
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
        HttpSession session = request.getSession(true);
        String action = (String)request.getAttribute("action");
        String op;
        ProductDAO pdao = new ProductDAO();
        String url = ERROR;
        if (session.getAttribute("current-user")!=null) {
            switch (action) {
                case "edit":
                    url = EDIT;
                    break;
                case "edit-handler":
                    op = request.getParameter("op");
                    if (!"cancel".equals(op)) {
                        url = edit(request, pdao);
                    }
                    else url = SHOP;
                    break;
                case "create":
                    url = CREATE;
                    break;
                case "create-handler":
                    op = request.getParameter("op");
                    if (!"cancel".equals(op)) {
                        url = create(request, pdao);
                    }
                    else url = SHOP;
                    break;
                case "delete":
                    url = delete(request, pdao);
                    break;
            }
        }
        
        request.getRequestDispatcher(url).forward(request, response);
    }
    
    private String edit(HttpServletRequest request, ProductDAO pdao) {
        String id = (String)request.getAttribute("id");
        String type = request.getParameter("type");
        Double price = Double.parseDouble(request.getParameter("price"));
        Integer sale = Integer.parseInt(request.getParameter("sale"));
        Integer stock = Integer.parseInt(request.getParameter("stock"));
        String ageGroup = request.getParameter("ageGroup");
        String size = request.getParameter("size");
        String color = request.getParameter("color");
        String brand = request.getParameter("brand");
        
        try {
            pdao.update(new ProductDTO(id, brand, type, price, sale, stock, ageGroup, size, color));
        } catch (SQLException ex) {
            request.setAttribute("message", ex.getMessage());
            request.setAttribute("error", "Cannot access database.");
        }
        return SHOP;
    }
    
    private String create(HttpServletRequest request, ProductDAO pdao) {
        String id = request.getParameter("id");
        String type = request.getParameter("type");
        Double price = Double.parseDouble(request.getParameter("price"));
        Integer sale = Integer.parseInt(request.getParameter("sale"));
        Integer stock = Integer.parseInt(request.getParameter("stock"));
        String ageGroup = request.getParameter("ageGroup");
        String size = request.getParameter("ageGroup");
        String color = request.getParameter("color");
        String brand = request.getParameter("brand");
        
        try {
            pdao.update(new ProductDTO(id, brand, type, price, sale, stock, ageGroup, size, color));
            request.setAttribute("message", "Success!");
        } catch (SQLException ex) {
            request.setAttribute("error", ex.getMessage());
        }
        return SHOP;
    }
    
    private String delete(HttpServletRequest request, ProductDAO pdao) {
        String id = (String)request.getAttribute("id");
        
        try {
            pdao.delete(id);
            request.setAttribute("message", "Success!");
        } catch (SQLException ex) {
            request.setAttribute("error", ex.getMessage());
        }
        return SHOP;
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
