/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.createcontroller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import sample.product.ProductDAO;
import sample.product.ProductDTO;
import sample.product.ProductError;

/**
 *
 * @author Thanh
 */
@WebServlet(name = "CreateProController", urlPatterns = {"/CreateProController"})
public class CreateProController extends HttpServlet {

    private static final String ERROR = "createpro.jsp";
    private static final String SUCCESS = "admin.jsp";
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        ProductDAO dao = new ProductDAO();
        ProductError productError = new ProductError();
        try {
            String productID = request.getParameter("productID");
            String name = request.getParameter("name");
            Double price = Double.parseDouble(request.getParameter("price"));
            Integer quantity = Integer.parseInt(request.getParameter("quantity"));
            
            boolean checkValidation = true;
            if (productID.length() > 50 || productID.length() < 2) {
                productError.setProductID("Product ID must be in [2,10]");
                checkValidation =false;
            }
//            boolean checkDuplicate = dao.checkDuplicate(productID);
//            if (checkDuplicate) {
//                productError.setProductID("Duplicate Product ID");
//                checkValidation = false;
//            }
            if (name.length() > 50 || name.length() < 2) {
                productError.setName("Product Name must be in [2,50]");
                checkValidation = false;
            }
            if ( price < 10000 ){
                productError.setPrice("Price must be > 10000");
                checkValidation = false;
            }
            if (quantity < 1) {
                productError.setQuantity("Quantity must be > 1");
                checkValidation = false;
            }
            if (checkValidation) {
                ProductDTO product = new ProductDTO(productID,name,price,quantity);
//                boolean checkInsert = dao.insert(product);
                boolean checkInsert = dao.insertV2(product);
                if (checkInsert) {
                    url = SUCCESS;
                }
            } else {
                request.setAttribute("PRODUCT_ERROR", productError);
            }
        } catch (Exception e) {
            log("Error at CreateProController" + e.toString());
            if (e.toString().contains("duplicate")) {
                productError.setProductID("Duplicate productID");
                request.setAttribute("PRODUCT_ERROR", productError);
            }
            
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
