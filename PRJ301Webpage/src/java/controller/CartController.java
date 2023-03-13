/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
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
import javax.swing.text.Document;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;
/**
 *
 * @author PHT
 */
@WebServlet(name = "ToyController", urlPatterns = {"/cart"})
public class CartController extends HttpServlet {

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
        ProductFacade pf = new ProductFacade();
        //hashmap to store cart
        HttpSession session = request.getSession();
        HashMap<String, Integer> cart = session.getAttribute("cart") == null ? new HashMap() : (HashMap<String, Integer>) session.getAttribute("cart");
        switch (action) {
            case "add":
                Add(request, response, cart, session, pf);
                break;
            case "show":
                Show(request, response, cart, session, pf);
                break;

            case "buy_handler": //Luu thong tin vao db
                if(session.getAttribute("current-user")!=null){
                String op = request.getParameter("op");              
                switch (op) {
                    case "buy":
                        try {
                            HashMap<Product, Integer> cartDisplay = request.getAttribute("cartDis");
                            pf.lowerStock(cartDisplay); //stock amount - cart amount
                            session.removeAttribute("cart");
                            request.getRequestDispatcher("/view/home/Receipt.jsp").forward(request, response);
                        } catch (Exception ex) {
                            //Hien trang thong bao loi
                            ex.printStackTrace();//in thong bao loi chi tiet cho developer
                            request.setAttribute("message", ex.getMessage());
                            request.setAttribute("controller", "error");
                            request.setAttribute("action", "error");
                            request.getRequestDispatcher("/view/home/error.jsp").forward(request, response);
                        }
                        break;
                    case "remove":
                        try {
                            String id = request.getParameter("productId");
                            cart.remove(id);
                            session.setAttribute("cart", cart);
                        } catch (Exception ex) {
                            //Hien trang thong bao loi
                            ex.printStackTrace();//in thong bao loi chi tiet cho developer
                            request.setAttribute("message", ex.getMessage());
                            request.setAttribute("controller", "error");
                            request.setAttribute("action", "error");
                            request.getRequestDispatcher("/view/home/error.jsp").forward(request, response);
                        }
                    case "empty":
                        try {
                            session.removeAttribute("cart");
                        } catch (Exception ex) {
                            //Hien trang thong bao loi
                            ex.printStackTrace();//in thong bao loi chi tiet cho developer
                            request.setAttribute("message", ex.getMessage());
                            request.setAttribute("controller", "error");
                            request.setAttribute("action", "error");
                            request.getRequestDispatcher("/view/home/error.jsp").forward(request, response);
                        }
                        break;
                    case "back":
                        //Trở về trang chính shop
                        response.sendRedirect(request.getContextPath() + "/index.do");
                        break;
                }
                }else{
                    response.sendRedirect(request.getContextPath() + "login.do");
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

    public void Add(HttpServletRequest request, HttpServletResponse response, HashMap<String, Integer> cart, HttpSession session, ProductFacade pf)
            throws ServletException, IOException {
        try {
            String id = request.getParameter("ProId");
            int amount = Integer.parseInt(request.getParameter("amount"));
            //check for existing product
            if (cart.containsKey(id)) {
                if (cart.get(id) < pf.read(id).getStock()) {
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
        } catch (Exception ex) {
            //Hien trang thong bao loi
            ex.printStackTrace();//in thong bao loi chi tiet cho developer
            request.setAttribute("message", ex.getMessage());
            request.setAttribute("controller", "error");
            request.setAttribute("action", "error");
            request.getRequestDispatcher("/view/home/error.jsp").forward(request, response);
        }
    }

    public void Show(HttpServletRequest request, HttpServletResponse response, HashMap<String, Integer> cart, HttpSession session, ProductFacade pf) throws ServletException, IOException {
        try {
            HashMap<Product, Integer> cartDisplay = new HashMap();
            List<Product> plist = new ArrayList();
            List image = new ArrayList();
            double total = 0;
            plist = pf.select();
            for (String i : cart.keySet()) {
                for (int p = 0; p <= plist.size(); p++) {
                    if (plist.get(p).getId().equals(i)) {
                        //gọi hàm read lấy data product trong hashmap
                        total = total + (plist.get(p).getPrice() * cart.get(i));
                        cartDisplay.put(plist.get(p), cart.get(i));
                        image.add(pf.getImage(plist.get.(p)));
                    }
                }
            }
            request.setAttribute("total", total);
            request.setAttribute("image", image);
            request.setAttribute("cartDis", cartDisplay);
            request.getRequestDispatcher("/view/home/cart.jsp").forward(request, response);
        } catch (Exception ex) {
            //Hien trang thong bao loi
            ex.printStackTrace();//in thong bao loi chi tiet cho developer
            request.setAttribute("message", ex.getMessage());
            request.setAttribute("controller", "error");
            request.setAttribute("action", "error");
            request.getRequestDispatcher("/view/home/error.jsp").forward(request, response);
        }
    }

}
