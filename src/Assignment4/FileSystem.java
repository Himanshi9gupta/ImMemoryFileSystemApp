package Assignment4;

public class FileSystem extends Directories{
	
	private Directories root;
	private Object loggedInUser;
	static FileSystem instance;
	String user;
	String role;
	String path;
	String name;
	public FileSystem(String user, String role, String path, String name) {
		super(user, role, path, name);
		this.user = user;
		this.name = name;
		this.role = role;
		this.path = path;
		this.initFileSystem();
		 
	}

	private void initFileSystem() {
	      this.root = new Directories(name, role, path, user);
	      this.loggedInUser = user;
	      }
	
	void login(String user) {
	      this.loggedInUser = user;
	 }
	
	public static FileSystem getInstance() {  
		
		if (instance == null) {  
			throw new IllegalStateException("FileSystem must be initialized before use.");  
		}  
		return instance;  
	} 
	 
	public static void initializeFileSystem(String user, String name, String role, String path) { 
		if (instance == null) { 
		 	instance = new FileSystem(name, role, path, user); 
		 	getInstance();
		} else {  
			throw new IllegalStateException("FileSystem has already been initialized.");  
		} 
	 }  

}
