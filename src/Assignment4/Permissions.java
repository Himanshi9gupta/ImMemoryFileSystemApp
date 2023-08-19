package Assignment4;

import java.util.HashMap;

public abstract class Permissions{
	static String user;
	String role;
	boolean hasPermission;
	public static final String admin = "ADMIN";
	public static final String regular = "REGULAR";
	static HashMap<String, String> userPermission = new HashMap<>(); 
	
	Permissions (String user, String role) { 
		this.user = user;
		this.role = role;
		if(!userPermission.containsKey(user)) {
			userPermission.put(user, role.toUpperCase());
		}
	} 
	
	public static HashMap<String, String> setPermissions(String user, String role) {
		if(!userPermission.containsKey(user) && role.equalsIgnoreCase(admin)) {
			userPermission.put(user, role.toUpperCase());
		}
		else {
			userPermission.replace(user, role.toUpperCase());
		}
		System.out.println("userpermission" + userPermission);
		return userPermission;
	}
	
	public static boolean hasPermissions(String user) { 
		String role = userPermission.get(user);
		if(userPermission.containsKey(user) && role.equalsIgnoreCase(admin)){ 
			return true; 
		} 
		return false;	
	}
	
	public static void revokePermissions(String user){ 
		if(hasPermissions(user)){ 
			userPermission.replace(user, regular); 
		} 

	} 

}
