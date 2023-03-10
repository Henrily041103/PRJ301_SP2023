/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.account;

//import java.util.List;

/**
 *
 * @author Admin
 */
public abstract class AccountModel {
    private String userId;
    private String username;

    public AccountModel(String userId, String username) {
        setUserId(userId);
        setUsername(username);
    }
    
    public AccountModel(AccountEntity entity) {
        setUserId(entity.getUserId());
        setUsername(entity.getUsername());
    }

    public AccountModel() {
    }

    public String getUserId() {
        return userId;
    }

    private void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public final void setUsername(String username) {
        this.username = username;
    }
    
    public abstract String getRole();
    public abstract String getPage();
    public abstract boolean checkRole(String action, String controller);
}
