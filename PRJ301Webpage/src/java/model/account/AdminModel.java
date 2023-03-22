/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.account;


/**
 *
 * @author Admin
 */
public class AdminModel extends AccountModel {
    
//    private static List<String> ADMIN_FUNCTIONS;

    public AdminModel(String userId, String username) {
        super(userId, username);
//        ADMIN_FUNCTIONS = new ArrayList();
    }

    public AdminModel(AccountEntity entity) {
        super(entity);
    }
    
    public AdminModel() {
    }
    
    @Override
    public String getRole() {
        return "AD";
    }

    @Override
    public boolean checkRole(String url) {
        return true;
    }

    @Override
    public String getPage() {
        return "admin.jsp";
    }

    @Override
    public String getButton() {
        return "<a href='<c:url value='/shop/edit.do' />' class='btn btn-outline-dark'> Edit Product</a>";
    }
    
}
