package Assignment4;

import java.sql.*;
import java.util.Date;
import java.util.HashMap;

import static Assignment4.Directories.executeQuery;
import static Assignment4.Permissions.role;

public class Files implements FilesInfo{
	String fileName;
	String user;
	Integer size;
	Date modifiedDate;
	Date createdDate;
	String path;
	static HashMap<String, String> files = new HashMap<>();
	public Files(String fileName, String path, String user, String role, Connection connection) {
		this.fileName = fileName;
		this.path = path;
		this.user = user;
	}

	public String getFileName() {
		return this.fileName;
	}

	public String getUser() {
		return this.user;
	}

	public Integer getSize() {
		return this.size;
	}

	public String getpath() {
		return this.path;
	}

	public Date getModifiedDate() {
		return this.modifiedDate;
	}

	public Date getCreatedDate() {
		return this.createdDate;
	}

	static HashMap<String, String> ListOfFiles(Connection connection){
		String sql = "SELECT * from Files";
		try {

			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
//			stmt.close();
			System.out.println("Files Table :");
			{
				while (rs.next()) {
					files.put(rs.getString("Name"), rs.getString("path"));

					System.out.println(rs.getInt("id") + "\t" + rs.getString("path") + "\t"
							+ rs.getString("Name"));
				}
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

		System.out.println("List of directories: " + files);
		return files;
	}

	HashMap<String, String> createFile(String fileName, String filePath, String name, String role, Connection connection ) {
		if(Permissions.hasPermissions(name, role, connection)) {
			int max = 1000;
			int min = 1;
			int id = (int)(Math.random() * max - min + 1) + min;

			long millis=System.currentTimeMillis();
			java.sql.Date date = new java.sql.Date(millis);
			Date createDate = null;
			String query = id + ", '" + filePath + "',' " + fileName + "', '" + name + "'";
			String sql = "INSERT INTO files (id, path, name, user) VALUES (" + query + ")";
			try {
				PreparedStatement name2 = connection.prepareStatement(sql);
				name2.executeQuery();
			} catch (SQLException e) {
				System.out.println("sdff" + e.getMessage());
			}

			files.put(name,path);
			System.out.println( "File created successfully.");
		}
		return ListOfFiles(connection);
	}
		String deleteFile (String name, String path,String role, String user, Connection connection ) {
		if(Permissions.hasPermissions(user, role, connection)) {

			String sql = "DELETE FROM Files WHERE name = '" + name + "'";
			Statement stmt2;
			try {
				stmt2 = connection.createStatement();
				stmt2.executeUpdate(sql);
				//stmt2.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			//executeQuery(sql, connection);
			files.remove(name,path);
			System.out.println("File deleted successfully.");
			return "File deleted successfully.";
		}
		else {
		return "something went wrong when deleting directory!";
	}
	} 
	static String updateFile (String path, String user, String name, String role, Connection connection ) {
		if(Permissions.hasPermissions(user, role, connection)) {
			String sql = "UPDATE Directory SET name = '" + name + "', path= '" + path + "' WHERE name = '" + name + "'";
			Statement stmt2;
			try {
				stmt2 = connection.createStatement();
				stmt2.executeUpdate(sql);
				//stmt2.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			//executeQuery(sql, connection);

			files.replace(name,path);

			return "File updated successfully.";
		}
		else {
			return "something went wrong when updating file!";
		}	}

}
