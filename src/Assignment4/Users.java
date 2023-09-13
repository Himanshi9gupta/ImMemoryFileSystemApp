package Assignment4;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.UUID;

public class Users extends Permissions {
	String user;
	String role;
	static HashMap<String, String> userList = new HashMap<>();
	String id = UUID.randomUUID().toString();

	public Users(String user, String role) {
		super(user, role);
	}

	@SuppressWarnings("static-access")
	public String addUser(String user, String lastName, String role, Connection connection) {
	 if(!userList.containsKey(user)) {
		int max = 1000;
		int min = 1;
		int range = max - min + 1;
		int id = (int)(Math.random() * range) + min;
		
		String query = id + ", '" + user + "', '" + role + "'";
//		String sql2 = "INSERT INTO users (id, firstName, lastName, Role) VALUES ("+id+",'Adam', 'woods','Regular')";
		String sql = "INSERT INTO users (id, Name, Role) VALUES (" + query + ")";
		System.out.println("sql: " + sql);

		try {
			
			Statement stmt = connection.createStatement();
			stmt.executeUpdate(sql);
			stmt.close();

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	 }

		System.out.println("UserList: " + userList);
		return "User is successfully added.";
	}

	public String removeUser(String user) {
		if (userList.containsKey(id)) {
			userList.remove(user, id);
		}
		return "User removed successfully from the list.";
	}

}


//INSERT INTO Directory (id, Path, DirectoryName, CreatedDate, UpdatedDate, User) VALUES ('1', '/', 'root', '25-08-2023', null, 'Carol');





