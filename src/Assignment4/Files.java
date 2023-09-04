package Assignment4;

import java.sql.Connection;
import java.util.Date;
import java.util.HashMap;

public abstract class Files implements FilesInfo{
	String fileName;
	String user;
	Integer size;
	Date modifiedDate;
	Date createdDate;
	String path;
	HashMap<String, String> files = new HashMap<>(); 
	public Files(String fileName, String path, String user) {
		this.fileName = fileName;
		this.path = path;
		this.user = user;
	}

	@Override
	public String getFileName() {
		return this.fileName;
	}

	@Override
	public String getUser() {
		return this.user;
	}

	@Override
	public Integer getSize() {
		return this.size;
	}

	@Override
	public String getpath() {
		return this.path;
	}

	@Override
	public Date getModifiedDate() {
		return this.modifiedDate;
	}

	@Override
	public Date getCreatedDate() {
		return this.createdDate;
	}
	
	void createFile(String path, String user,String name, Connection connection ) {
		if(!files.containsKey(name) && Permissions.hasPermissions(user, connection)) {
			files.put(name,path);
			System.out.println("File created successfully.");
		}
		else {
			System.out.println("User doesn't have sufficient priviledges.");
		}

	}
	
	void deleteDirectory (String path, String user, String name, Connection connection ) { 
		if(files.containsKey(name) && Permissions.hasPermissions(user, connection)) {
			files.remove(name,path);
			System.out.println("File deleted successfully.");
		}
		else {
			System.out.println("User doesn't have sufficient priviledges.");
		}
	} 
	void updateDirectory (String path, String user, String name, Connection connection ) { 
		if(files.containsKey(name) && Permissions.hasPermissions(user, connection)) {
			files.replace(name,path);
			System.out.println("File updated successfully.");
		}
		else {
			System.out.println("User doesn't have sufficient priviledges.");
		}
	}

}
