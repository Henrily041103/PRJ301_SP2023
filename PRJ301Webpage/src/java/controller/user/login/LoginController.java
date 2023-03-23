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
import model.account.AccountAlreadyExistsException;
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

    private static final String MAIN = "/WEB-INF/layout/main.jsp";

    private static final String SHOP = "shop";
    private static final String SHOP_PAGE = "index";

    private static final String REGISTER = "login";
    private static final String REGISTER_PAGE = "register";

    private static final String LOGIN = "login";
    private static final String LOGIN_PAGE = "login";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String action = request.getParameter("action");
        String controller = request.getParameter("controller");
//        System.out.println("controller: "+controller);
//        System.out.println("action: "+action);
        try {
            HttpSession session = request.getSession();
            switch (action) {
                case "login-handler":
                    login_handler(request, session);
                    break;
                case "register-handler":
                    registerHandler(request);
                    break;
                case "logout":
                    session.invalidate();
                    request.setAttribute("controller", LOGIN);
                    request.setAttribute("action", LOGIN_PAGE);
                    break;
            }
        } catch (AccountNotFoundException | SQLException | AccountAlreadyExistsException e) {
            request.setAttribute("message", e.getMessage());
            request.setAttribute("controller", LOGIN);
            request.setAttribute("action", LOGIN_PAGE);
        } finally {
            request.getRequestDispatcher(MAIN).forward(request, response);
        }
    }

    private void login_handler(HttpServletRequest request, HttpSession session) throws AccountNotFoundException, SQLException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        AccountFactory factory = new AccountFactory();
        AccountDAO dao = new AccountDAO();
        AccountModel loginUser = factory.generateExistingAccount(username, password, dao);
        session.setAttribute("current-user", loginUser);

        request.setAttribute("controller", SHOP);
        request.setAttribute("action", SHOP_PAGE);
    }

    private void registerHandler(HttpServletRequest request)
            throws AccountNotFoundException, AccountAlreadyExistsException, SQLException {
        String username = request.getParameter("username");
        String userId = request.getParameter("userId");
        String password = request.getParameter("password");

        AccountFactory factory = new AccountFactory();
        AccountDAO dao = new AccountDAO();
        AccountModel model = factory.generateNewAccount(username, userId);
        if (model != null) {
            factory.generateEntity(model, password, dao);
            request.setAttribute("controller", SHOP);
            request.setAttribute("action", SHOP_PAGE);
        } else {
            throw new AccountNotFoundException();
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
