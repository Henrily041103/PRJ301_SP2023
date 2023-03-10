/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.user.login;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import controller.UnrecognizedActionException;
import model.account.AccountDAO;
import model.account.AccountFactory;
import model.account.AccountModel;
import model.account.AccountNotFoundException;

/**
 *
 * @author Thanh
 */
@WebServlet(name = "LoginController", urlPatterns = {"/login"})
public class LoginController extends HttpServlet {

    private static final String ERROR = "login.jsp";
    private static final String SHOP = "shop.jsp";
    private static final String REGISTER = "register.jsp";
    private static final String LOGIN = "login.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String action = request.getParameter("action");
        String url = "";

        try {
            HttpSession session = request.getSession();
            switch (action) {
                case "login":
                    url = login(request, session);
                    break;
                case "logout":
                    url = logout(session);
                    break;
                case "register":
                    url = register();
                    break;
                case "register-handler":
                    url = registerHandler(request);
                    break;
                default:
                    throw new UnrecognizedActionException();
            }
        } catch (UnrecognizedActionException e) {
            log("Error at LoginController" + e.toString());
            url = ERROR;
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
        }
    }

    private String login(HttpServletRequest request, HttpSession session) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        AccountFactory factory = new AccountFactory();
        AccountDAO dao = new AccountDAO();
        try {
            AccountModel loginUser = factory.generateExistingAccount(username, password, dao);
            session.setAttribute("current-user", loginUser);
            return SHOP;
        } catch (AccountNotFoundException | SQLException ex) {
            log("Error at Login" + ex.toString());
            return ERROR;
        }

    }

    private String logout(HttpSession session) {
        session.invalidate();
        return LOGIN;
    }
    
    private String register() {
        return REGISTER;
    }

    private String registerHandler(HttpServletRequest request) {
        String username = request.getParameter("username");
        String userId = request.getParameter("userId");
        String password = request.getParameter("password");

        AccountFactory factory = new AccountFactory();
        AccountDAO dao = new AccountDAO();
        AccountModel model = factory.generateNewAccount(username, userId);
        try {
            if (model != null) {
                factory.generateEntity(model, password, dao);
                return LOGIN;
            }
            else throw new Exception("Username, ID or password cannot be null.");
        } catch (Exception ex) {
            log("Error at REGISTER" + ex.toString());
            return ERROR;
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
