/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.account;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Admin
 */
public class UserModel extends AccountModel {
    
    private static List<String> USER_FUNCTIONS;

    public UserModel(String userId, String username) {
        super(userId, username);
        USER_FUNCTIONS = new ArrayList();
        USER_FUNCTIONS.add("user/edit");
        USER_FUNCTIONS.add("cart/checkout");
    }

    public UserModel(AccountEntity entity) {
        super(entity);
    }

    public UserModel() {
    }
    
    @Override
    public String getRole() {
        return "US";
    }

    @Override
    public boolean checkRole(String url) {
        String controller = url.substring(url.indexOf("/")+1, url.lastIndexOf("/"));
        return !controller.equals("edit");
    }

    @Override
    public String getPage() {
        return "shop.jsp";
    }

    @Override
    public String getButton() {
        return "<a href='<c:url value='/cart/add.do' />' class='btn btn-outline-dark'> Add to cart</a>";
    }
    
}
