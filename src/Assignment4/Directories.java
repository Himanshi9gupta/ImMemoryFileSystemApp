package Assignment4;

import java.util.Date;
import java.util.HashMap;

public class Directories extends Permissions{
	Integer size; 
	Date modifiedDate;
	Date creationDate;
	String directoryName;
	String path;
	
	static HashMap<String, String> directories = new HashMap<>(); 
	
	Directories(String user, String role, String path, String directoryName, Connection connection) {
		super(user, role);
		this.path =path;
		this.directoryName = directoryName;
	}

	static HashMap<String, String> ListOfDirectories(Connection connection){ 
		String sql = "SELECT * from Directory";
		try {

			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
//			stmt.close();
			System.out.println("Directory Table :");
			{
				while (rs.next()) {
					
					directories.put(rs.getString("directoryName"), rs.getString("path"));
					
					System.out.println(rs.getInt("id") + "\t" + rs.getString("path") + "\t"
							+ rs.getString("directoryName") + "\t" + rs.getString("user"));
				}
			}	
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
		System.out.println("List of directories: " + directories);
		return directories;
	} 
	
	static String createDirectory (String directoryName,  String path, String role, Connection connection) { 
		if(!directories.containsKey(directoryName) && Permissions.hasPermissions(user, connection)) {
			int max = 1000;
			int min = 1;
			int id = (int)(Math.random() * max - min + 1) + min;
			
			long millis=System.currentTimeMillis();  
		    java.sql.Date date = new java.sql.Date(millis);   
			
			String query = id + ", '" + path + "',' " + directoryName + "', '" + date + "', '" + user + "'";
			String sql = "INSERT INTO Directory (id, Path, DirectoryName, CreatedDate, User) VALUES (" + query + ")";
			executeQuery(sql, connection);
			
			directories.put(directoryName,path);
			return "Directory created successfully.";
		}
		else {
			return "User doesn't have sufficient priviledges.";
		}
	} 

	static String deleteDirectory (String directoryName,  String path, String role, String username, Connection connection) { 
		if(directories.containsKey(directoryName) && Permissions.hasPermissions(username, connection)) {
			
			String sql = "DELETE FROM Directory WHERE directoryName = '" + directoryName + "'";
			Statement stmt2;
			try {
				stmt2 = connection.createStatement();
				stmt2.executeUpdate(sql);
				stmt2.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//executeQuery(sql, connection);
			
			directories.remove(directoryName,path);
			System.out.println("Directories list after deleting: " + directories);
			return "Directory deleted successfully.";
		}
		else {
			return "Directory not present in map!";
		}
		
	} 
	
	static void executeQuery (String sql, Connection connection) {
		try {
			
			Statement stmt = connection.createStatement();
			stmt.executeUpdate(sql);
			stmt.close();

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	
	static String updateDirectory (String directoryName,  String path, String role, Connection connection) { 
		if(directories.containsKey(directoryName) && Permissions.hasPermissions(user, connection)) {
			directories.replace(directoryName,path);
			return "Directory updated successfully.";
		}
		else {
			return "User doesn't have sufficient priviledges.";
		}
	} 
	
}
