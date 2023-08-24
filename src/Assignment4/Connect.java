package Assignment4;
import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.*;

import java.beans.Statement;
import java.sql.*;


public class Connect {
	 static Connection conn = null;
	public static Connection dbConnect() throws ClassNotFoundException {
       
        try {
            // db parameters
            String url = "jdbc:sqlite:/Users/himanshigupta/eclipse-workspace/SDE_Examples/src/Assignment4/sqlite/db/uwSystemDesign.db";
            
            // create a connection to the database
            Class.forName("org.sqlite.JDBC");
       
            conn = DriverManager.getConnection(url);
            //JOptionPane.showMessageDialog(null, url);
            JOptionPane.showMessageDialog(null, "Connection to SQLite has been established.", "message", JOptionPane.CLOSED_OPTION);
            System.out.println("Connection to SQLite has been established.");
            return conn;
            
        }catch (SQLException e) {
            System.out.println(e.getMessage());
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
    }
}
