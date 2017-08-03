/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ultis;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Nups
 */
public class DBConnector {
    Connection con = null;

    public DBConnector() {
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            con = DriverManager.getConnection("jdbc:derby://localhost:1527/computerdb","app","app");
            if(con != null){
                System.out.println("Connection to database");
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DBConnector.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            System.out.println("Connect Error");
            Logger.getLogger(DBConnector.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    public Connection getCon(){
        return con;
    }
}
