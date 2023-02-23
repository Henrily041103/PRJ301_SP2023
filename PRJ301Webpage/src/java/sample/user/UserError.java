/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.user;

/**
 *
 * @author Thanh
 */
public class UserError {
    private String userID = "";
    private String name = "";
    private String roleID = "";
    private String password = "";
    private String address="";
    private String phone="";
    private String confirm ="";

    public UserError() {
    }
    public UserError(String userID, String name, String roleID, String password, String address, String phone,String confirm) {
        this.userID = userID;
        this.name = name;
        this.roleID = roleID;
        this.password = password;
        this.address = address;
        this.phone = phone;
        this.confirm= confirm;
    }
    public String getUserID() {
        return userID;
    }

    public void setUserID(String UserID) {
        this.userID = UserID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRoleID() {
        return roleID;
    }

    public void setRoleID(String roleID) {
        this.roleID = roleID;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getConfirm() {
        return confirm;
    }

    public void setConfirm(String confirm) {
        this.confirm = confirm;
    }
    
}
