/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.cart;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.product.ProductDTO;
import model.product.ProductDAO;

/**
 *
 * @author PHT
 */
@WebServlet(name = "CartController", urlPatterns = {"/cart"})
public class CartController extends HttpServlet {

    private static final String MAIN = "/WEB-INF/layout/main.jsp";

    private static final String SHOP = "shop";
    private static final String SHOP_PAGE = "index";

    private static final String LOGIN = "login";
    private static final String LOGIN_PAGE = "login";

    private static final String CART = "cart";
    private static final String CART_PAGE = "cart";

    private static final String RECEIPT = "cart";
    private static final String RECEIPT_PAGE = "receipt";

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
        ProductDAO pdao = new ProductDAO();
        //hashmap to store cart
        HttpSession session = request.getSession();
        HashMap<String, Integer> cart = session.getAttribute("cart") == null ? new HashMap() : (HashMap<String, Integer>) session.getAttribute("cart");
        try {
            switch (action) {
                case "add":
                    add(request, response, cart, session, pdao);
                    break;
                case "show":
                    show(request, response, cart, session, pdao);
                    break;

                case "buy_handler": //Luu thong tin vao db
                    if (session.getAttribute("current-user") != null) {
                        String op = request.getParameter("op");
                        switch (op) {
                            case "buy":
                                buy(request, response, session, pdao);
                                break;
                            case "remove":
                                remove(request, response, cart, session, pdao);
                                break;
                            case "empty":
                                session.removeAttribute("cart");
                            case "back":
                                //Trở về trang chính shop
                                request.setAttribute("controller", SHOP);
                                request.setAttribute("action", SHOP_PAGE);
                                break;
                        }
                    } else {
                        request.setAttribute("controller", LOGIN);
                        request.setAttribute("action", LOGIN_PAGE);
                    }
                    break;
            }
        } catch (IOException | NumberFormatException | SQLException ex) {
            //Hien trang thong bao loi
            ex.printStackTrace();//in thong bao loi chi tiet cho developer
            request.setAttribute("message", ex.getMessage());
            request.setAttribute("controller", SHOP);
            request.setAttribute("action", SHOP_PAGE);
        }

        request.getRequestDispatcher(MAIN).forward(request, response);
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

    public void add(HttpServletRequest request, HttpServletResponse response, HashMap<String, Integer> cart, HttpSession session, ProductDAO pdao)
            throws ServletException, IOException, SQLException {

        String id = request.getParameter("ProId");
        int amount = Integer.parseInt(request.getParameter("amount"));
        //check for existing product
        if (cart.containsKey(id)) {
            if (cart.get(id) < pdao.read(id).getStock()) {
                //if exist +1 to amount
                int newAmount = cart.get(id) + amount;
                cart.put(id, newAmount);
                session.setAttribute("cart", cart);
            } else {
                request.setAttribute("message", "out of stock");
            }
        } else {
            //if not add new product
            cart.put(id, 1);
            session.setAttribute("cart", cart);
        }
        request.setAttribute("controller", SHOP);
        request.setAttribute("action", SHOP_PAGE);
    }

    public void show(HttpServletRequest request, HttpServletResponse response, HashMap<String, Integer> cart, HttpSession session, ProductDAO pdao) throws ServletException, IOException, SQLException {

        HashMap<ProductDTO, Integer> cartDisplay = new HashMap();
        List<ProductDTO> plist;
        List image = new ArrayList();
        double total = 0;
        plist = pdao.select();
        for (String i : cart.keySet()) {
            for (int p = 0; p <= plist.size(); p++) {
                if (plist.get(p).getId().equals(i)) {
                    //gọi hàm read lấy data product trong hashmap
                    total = total + (plist.get(p).getPrice() * cart.get(i));
                    cartDisplay.put(plist.get(p), cart.get(i));
                    image.add(plist.get(p).getImage());
                }
            }
        }
        request.setAttribute("total", total);
        request.setAttribute("image", image);
        request.setAttribute("cartDis", cartDisplay);
        request.setAttribute("controller", CART);
        request.setAttribute("action", CART_PAGE);

    }

    public void buy(HttpServletRequest request, HttpServletResponse response, HttpSession session, ProductDAO pdao)
            throws SQLException, ServletException, IOException {
        HashMap<ProductDTO, Integer> cartDisplay = (HashMap<ProductDTO, Integer>) request.getAttribute("cartDis");
        pdao.lowerStock(cartDisplay); //stock amount - cart amount
        session.removeAttribute("cart");
        request.setAttribute("controller", RECEIPT);
        request.setAttribute("action", RECEIPT_PAGE);
    }

    public void remove(HttpServletRequest request, HttpServletResponse response, HashMap<String, Integer> cart, HttpSession session, ProductDAO pdao)
            throws ServletException, IOException {
        String id = request.getParameter("productId");
        cart.remove(id);
        session.setAttribute("cart", cart);
        request.setAttribute("controller", SHOP);
        request.setAttribute("action", SHOP_PAGE);
    }
}
