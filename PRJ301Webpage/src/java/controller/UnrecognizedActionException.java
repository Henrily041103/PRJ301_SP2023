/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

/**
 *
 * @author Admin
 */
public class UnrecognizedActionException extends Exception {

    public UnrecognizedActionException() {
        super("This action is not recognized by the system!");
    }
    
}
