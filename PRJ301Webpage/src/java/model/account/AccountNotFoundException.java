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
public class AccountNotFoundException extends Exception {

    public AccountNotFoundException() {
        super("Account does not exist!");
    }
    
}
