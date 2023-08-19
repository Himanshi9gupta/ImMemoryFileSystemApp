package Assignment4;

import java.beans.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.UUID;

public class Users extends Permissions{
	String user;
	String role;
	static HashMap<String,String> userList = new HashMap<>();
	String id = UUID.randomUUID().toString();
	Connect conn = new Connect();
	
	public Users(String user, String role) {
		super(user, role);
	}
	
	 public String addUser(String user, String role){
		if(!userList.containsKey(user)) {
			id = UUID.randomUUID().toString();
			userList.put(user, id);
			  String sql = "insert into users values(" + id+ ","+user+", lastName,"+role+")";
				try {
					Statement stmt = (Statement) conn.conn.createStatement();
					ResultSet rs = ((java.sql.Statement) stmt).executeQuery(sql);
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		System.out.println("UserList: " + userList);
		return "User added successfully in the list.";
	} 
	
	public String removeUser(String user){
	
		if(userList.containsKey(id)) {
			userList.remove(user, id);
		}
		return "User removed successfully from the list.";
	}
	
}
