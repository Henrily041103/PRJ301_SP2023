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
public class AccountEntity {
    private String userId;
    private String username;
    private String password;
    private String role;

    public AccountEntity() {
    }

    public AccountEntity(String userId, String username, String password, String role) {
        setUsername(username);
        setUserId(userId);
        setPassword(password);
        setRole(role);
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

    private void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    private void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    private void setRole(String role) {
        this.role = role;
    }
    
}
