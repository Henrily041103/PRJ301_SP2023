/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.searchcontroller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import sample.user.UserDAO;
import sample.user.UserDTO;
import sample.utils.StringUtil;

/**
 *
 * @author Thanh
 */
@WebServlet(name = "SearchController", urlPatterns = {"/SearchController"})
public class SearchController extends HttpServlet {

    private static final String ERROR = "admin.jsp";
    private static final String SUCCESS = "admin.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
/*          String search = request.getParameter("search");
            UserDAO dao = new UserDAO();
            List<UserDTO> listUser = dao.getListUser(search);
            if (listUser.size() > 0) {
                request.setAttribute("LIST_USER", listUser);
                url = SUCCESS;
            }
*/
            UserDAO dao = new UserDAO();
            List<UserDTO> list = dao.getUserList();           
            String search = StringUtil.removeAccent(request.getParameter("search")).trim().toLowerCase();
            if (search == null) {
                request.getSession().setAttribute("LIST_USER", list);
                url = SUCCESS;          
            } else {
                List<UserDTO> listUserFilter = new ArrayList<>();
                for (UserDTO user : list) {
                    String name = StringUtil.removeAccent(user.getName()).toLowerCase();
                    if (name.contains(search)) {
                        listUserFilter.add(user);
                    }
                }
                if (listUserFilter.isEmpty()) {
                    request.getSession().setAttribute("EMPTY_LIST", "No result");
                    request.getSession().setAttribute("LIST_USER", null);
                } else {
                    request.setAttribute("LIST_USER", listUserFilter);
                    url = SUCCESS;
                }
            }
        } catch (Exception e) {
            log("Error at SearchController: " + e.toString());
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