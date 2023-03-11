/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Thanh
 */

public class DBUtils {
    
    public static Connection getConnection() throws SQLException{
        String url = "jdbc:sqlserver://localhost;databaseName=BabyShop;user=sa;password=12345";
        Connection con = null;
        try{
            //Loading a driver
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            //Creating a connection
            con =DriverManager.getConnection(url);
        } catch (ClassNotFoundException | SQLException ex) {
            //Gay ra SQLException
            throw new SQLException(ex.getMessage());
        }
        return con;
    }
}
