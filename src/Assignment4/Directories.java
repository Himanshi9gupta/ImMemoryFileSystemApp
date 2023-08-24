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
	
	Directories(String user, String role, String path, String directoryName) {
		super(user, role);
		this.path =path;
		this.directoryName = directoryName;
	}

	static HashMap<String, String> ListOfDirectories(){ 
		System.out.println("List of directories: " + directories);
		return directories;
	} 
	

	static String createDirectory (String directoryName,  String path, String role) { 
		if(!directories.containsKey(directoryName) && Permissions.hasPermissions(user)) {
			directories.put(directoryName,path);
			return "Directory created successfully.";
		}
		else {
			return "User doesn't have sufficient priviledges.";
		}
	} 

	static String deleteDirectory (String directoryName,  String path, String role, String username) { 
		if(directories.containsKey(directoryName) && Permissions.hasPermissions(username)) {
			directories.remove(directoryName,path);
			System.out.println("Directories list after deleting: " + directories);
			return "Directory deleted successfully.";
		}
		else {
			return "Directory not present in map!";
		}
		
	} 
	
	static String updateDirectory (String directoryName,  String path, String role) { 
		if(directories.containsKey(directoryName) && Permissions.hasPermissions(user)) {
			directories.replace(directoryName,path);
			return "Directory updated successfully.";
		}
		else {
			return "User doesn't have sufficient priviledges.";
		}
	} 
	
}
