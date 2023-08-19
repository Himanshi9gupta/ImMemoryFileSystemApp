package Assignment4;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Application {
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
	                                   rs.getString("lastName"));
	            }
	            
	        } catch (SQLException e) {
	            System.out.println(e.getMessage());
	        }
		 
		 // Controller has all the apis for add user, create user and delete user
		 // creating an instance and calling api methods 
		 Controller controller = new Controller();
		 controller.apiMethod();
		 
		 
		 
		 
		 
//		 int serverPort = 8080;
//	        HttpServer server = HttpServer.create(new InetSocketAddress(serverPort), 0);
//	        
//	        server.createContext("/user", (exchange -> {
//	        	  InputStream requestBody = exchange.getRequestBody();  
//	                BufferedReader reader = new BufferedReader(new InputStreamReader(requestBody));
//	                
//
//	                StringBuilder requestData = new StringBuilder();
//	                
//	                String line = reader.readLine();
//	                
//	                System.out.println("------------------------------------------------------------------------");   
//	                
//	                if (line != null) {
//	                	 if (!line.isEmpty()) {
//                	        requestData.append(line);
//                	        String jsonString = requestData.substring(1, requestData.length() - 1);
//
//                	        String[] keyValuePairs = jsonString.split(",");// Split each line into key and value parts
//                	       
//                	        for (String pair : keyValuePairs) {
//                	            String[] keyValue = pair.split(":");
//                	            if (keyValue.length == 2) {
//                	                String key = keyValue[0].trim().replace("\"", "");
//                	                String value = keyValue[1].trim().replace("\"", "");
//                	                userInputMap.put(key, value);
//                	            }
//                	        }
//	                	 }
//	                 } System.out.println(userInputMap);
//  
//	             // setting headers
//	            exchange.getResponseHeaders().set("Content-Type", "application/json");
//	            exchange.getResponseHeaders().set("Access-Control-Allow-Origin", "*");
//	            exchange.getResponseHeaders().set("Access-Control-Allow-Methods", "GET, POST, OPTIONS");
//	            exchange.getResponseHeaders().set("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
//	            
//	            
//		        exchange.sendResponseHeaders(200, 0);
//		        String name = userInputMap.get("name");
//		        String role = userInputMap.get("role");
//		        Users user = new Users(name, role);
//		        String response = user.addUser(name);
//		 		Permissions.setPermissions(name, role);
//				
//	            OutputStream output = exchange.getResponseBody();
//	            output.write(response.getBytes());
//	            output.flush();
//	            exchange.close();
//	        }));
//	        
//	        
//	        // Second API
//	        server.createContext("/fileSystem/create", (exchange -> {
//	        	  InputStream requestBody = exchange.getRequestBody();  
//	                BufferedReader reader = new BufferedReader(new InputStreamReader(requestBody));
//
//	                StringBuilder requestData = new StringBuilder();
//	                
//	                String line = reader.readLine();
//	                
//	                System.out.println("------------------------------------------------------------------------");   
//	                
//	                if (line != null) {
//	                	 if (!line.isEmpty()) {
//              	        requestData.append(line);
//              	        String jsonString = requestData.substring(1, requestData.length() - 1);
//
//              	        String[] keyValuePairs = jsonString.split(",");// Split each line into key and value parts
//              	       
//              	        for (String pair : keyValuePairs) {
//              	            String[] keyValue = pair.split(":");
//              	            if (keyValue.length == 2) {
//              	                String key = keyValue[0].trim().replace("\"", "");
//              	                String value = keyValue[1].trim().replace("\"", "");
//              	              fileSystemInputMap.put(key, value);
//              	            }
//              	        }
//	                	 }
//	                 } 
//	                System.out.println(fileSystemInputMap);
//
//	             // setting headers
//	            exchange.getResponseHeaders().set("Content-Type", "application/json");
//	            exchange.getResponseHeaders().set("Access-Control-Allow-Origin", "*");
//	            exchange.getResponseHeaders().set("Access-Control-Allow-Methods", "GET, POST, OPTIONS");
//	            exchange.getResponseHeaders().set("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
//	            
//	            
//		        exchange.sendResponseHeaders(200, 0);
//		        String name = userInputMap.get("name");
//		        
//		        String role = userInputMap.get("role");
//		        
//		        String path = fileSystemInputMap.get("path");
//		        String directoryName = fileSystemInputMap.get("directoryName");
//
//		 		FileSystem fileSystem = new FileSystem(directoryName, role, path, name);
//				//fileSystem.createDirectory(directoryName, path, role);
//				String response =fileSystem.createDirectory(directoryName, path, role) + fileSystem.ListOfDirectories().toString();
//		 		  
//	            OutputStream output = exchange.getResponseBody();
//	            output.write(response.getBytes());
//	            output.flush();
//	            exchange.close();
//	        }));
//	        
//	        
//	        server.createContext("/fileSystem/delete", (exchange -> {
//	        	  InputStream requestBody = exchange.getRequestBody();  
//	                BufferedReader reader = new BufferedReader(new InputStreamReader(requestBody));
//
//	             // setting headers
//	            exchange.getResponseHeaders().set("Content-Type", "application/json");
//	            exchange.getResponseHeaders().set("Access-Control-Allow-Origin", "*");
//	            exchange.getResponseHeaders().set("Access-Control-Allow-Methods", "GET, POST, OPTIONS");
//	            exchange.getResponseHeaders().set("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
//	            
//		        exchange.sendResponseHeaders(200, 0);
//		        String name = userInputMap.get("name");
//		        String role = userInputMap.get("role");
//		        
//		        String path = fileSystemInputMap.get("path");
//		        String directoryName = fileSystemInputMap.get("directoryName");
//
//		 		FileSystem fileSystem = new FileSystem(directoryName, role,path, name);
//				
//				String response =fileSystem.deleteDirectory(directoryName, path, role, name);
//		 		  
//	            OutputStream output = exchange.getResponseBody();
//	            output.write(response.getBytes());
//	            output.flush();
//	            exchange.close();
//	        }));
//	        
//	        server.setExecutor(null); // creates a default executor
//	        server.start();
//	        System.out.println("Server started at http://localhost:8080/");
	        
	}
	

}
