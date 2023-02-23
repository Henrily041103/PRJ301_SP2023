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
import sample.user.UserDAO;
import sample.user.UserDTO;
import sample.user.UserError;

/**
 *
 * @author Thanh
 */
@WebServlet(name = "CreateController", urlPatterns = {"/CreateController"})
public class CreateController extends HttpServlet {

    private static final String ERROR = "create.jsp";
    private static final String SUCCESS = "login.jsp";
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        UserDAO dao = new UserDAO();
        UserError userError = new UserError();
        try {
            String userID = request.getParameter("userID");
            String name = request.getParameter("name");
            String roleID = request.getParameter("roleID");
            String password = request.getParameter("password");
            String confirm = request.getParameter("confirm");
            String address=request.getParameter("address");
            String phone=request.getParameter("phone");
//          UserError userError = new UserError();
            boolean checkValidation = true;
            if (userID.length() > 10 || userID.length() < 2) {
                userError.setUserID("User ID must be in [2,10]");
                checkValidation =false;
            }
            boolean checkDuplicate = dao.checkDuplicate(userID);
            if (checkDuplicate) {
                userError.setUserID("Duplicate userID");
                checkValidation = false;
            }
            if (name.length() > 50 || name.length() < 3) {
                userError.setName("User Name must be in [3,50]");
                checkValidation = false;
            }
            
            if (!"AD".equals(roleID) && !"US".equals(roleID)){
                userError.setRoleID("Role ID must be AD or US");
                checkValidation = false;
            }
            
            if (phone.length()>11|| phone.length()<10 ){
                userError.setPhone("User Phone must be in [10,11] ");
                checkValidation = false;
            }
            if (!password.equals(confirm)) {
                userError.setConfirm("");
                checkValidation = false;
            }
            if (checkValidation) {
                UserDTO user = new UserDTO(userID, name, roleID, password, address, phone);
//                boolean checkInsert = dao.insert(user);
                boolean checkInsert = dao.insertV2(user);
                if (checkInsert) {
                    url = SUCCESS;
                }
            } else {
                request.setAttribute("USER_ERROR", userError);
            }
        } catch (Exception e) {
            log("Error at CreateController" + e.toString());
            if (e.toString().contains("duplicate")) {
                userError.setUserID("Duplicate userID");
                request.setAttribute("USER_ERROR", userError);
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
