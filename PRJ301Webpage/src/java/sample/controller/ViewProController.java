/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import sample.product.ProductDAO;
import sample.product.ProductDTO;
import sample.utils.StringUtil;

/**
 *
 * @author Thanh
 */
@WebServlet(name = "ViewProController", urlPatterns = {"/ViewProController"})

public class ViewProController extends HttpServlet {

    private static final String ERROR = "shopping.jsp" ;
    private static final String SUCCESS = "shopping.jsp";
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
        ProductDAO prodDao = new ProductDAO();
            List<ProductDTO> listProd = prodDao.getProductList();
            String viewpro = StringUtil.removeAccent(request.getParameter("viewpro")).trim().toLowerCase();
            if (viewpro == null) {
                request.getSession().setAttribute("VIEW_PRODUCT", listProd);
                url = SUCCESS;
            } else {
                List<ProductDTO> listProdFilter = new ArrayList<>();
                for (ProductDTO product : listProd) {
                    String name = StringUtil.removeAccent(product.getName()).toLowerCase();
                    if (name.contains(viewpro)) {
                        listProdFilter.add(product);
                    }
                }
                if (listProdFilter.isEmpty()) {
                    request.getSession().setAttribute("EMPTY_LIST", "No result");
                    request.getSession().setAttribute("VIEW_PRODUCT", null);
                } else {
                    request.setAttribute("VIEW_PRODUCT", listProdFilter);
                    url = SUCCESS;
                }
            }
        } catch (Exception e) {
            log("Error at ViewProController: " + e.toString());
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
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
