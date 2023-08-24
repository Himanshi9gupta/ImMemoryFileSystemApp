package Assignment4;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import com.sun.net.httpserver.HttpServer;

public class Application {
	static HashMap<String, String> userInputMap = new HashMap<>();
	static HashMap<String, String> fileSystemInputMap = new HashMap<>();
	@SuppressWarnings("static-access")
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		// starting DB connection
		Connect connection = new Connect();
		
		 String sql = "SELECT * from users";
		 
		 try (
				 Connection conn = connection.dbConnect();
				 java.sql.Statement stmt  = conn.createStatement();
	             ResultSet rs    = stmt.executeQuery(sql)){
	            
	            // looping through the records
	            while (rs.next()) {
	                System.out.println(rs.getInt("id") +  "\t" + 
	                                   rs.getString("firstName") + "\t" +
	                                   rs.getString("lastName") + "\t" +
	                                   rs.getString("role"));
	                
	                
	            }
	        } catch (SQLException e) {
	            System.out.println(e.getMessage());
	        }
		 
		 // Controller has all the apis for add user, create user and delete user
		 // creating an instance and calling api methods 
		 Controller controller = new Controller();
		 controller.apiMethod();
	        
	}
	

}
