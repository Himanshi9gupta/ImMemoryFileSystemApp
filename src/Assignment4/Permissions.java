package Assignment4;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

public abstract class Permissions{
	static String user;
	String role;
	boolean hasPermission;
	public static final String admin = "ADMIN";
	public static final String regular = "REGULAR";
		static HashMap<String, String> userPermission = new HashMap<>();

	Permissions(String user, String role) {
		this.user = user;
		this.role = role;
		if (!userPermission.containsKey(user)) {
			userPermission.put(user, role.toUpperCase());
		}
	}

	public static HashMap<String, String> setPermissions(String user, String role) {
		if (!userPermission.containsKey(user) && role.equalsIgnoreCase(admin)) {
			userPermission.put(user, role.toUpperCase());
		} else {
			userPermission.replace(user, role.toUpperCase());
		}
		System.out.println("userpermission" + userPermission);
		return userPermission;
	}

	public static boolean hasPermissions(String user, Connection connection) {
		String sql = "SELECT * FROM users WHERE firstName = '" + user + "'";
		try {

			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			//stmt.close();
			System.out.println(rs);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

		String role = userPermission.get(user);
		if (userPermission.containsKey(user) && role.equalsIgnoreCase(admin)) {
			return true;
		}
		return false;
	}

	public static void revokePermissions(String user, Connection connection) {
		if (hasPermissions(user, connection)) {
			userPermission.replace(user, regular);
		}

	}

}
