package Assignment4;

import java.io.IOException;
//import java.sql.Connection;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.sql.Statement;
import java.util.HashMap;

public class Application {
	static HashMap<String, String> userInputMap = new HashMap<>();
	static HashMap<String, String> fileSystemInputMap = new HashMap<>();

	public static void main(String[] args) throws IOException, ClassNotFoundException {
		// starting DB connection
		/*Connect connect = new Connect();
		Connection connection = connect.dbConnect();
		System.out.println("hgvhgcvhgvjhbkjn");
		String sql = "SELECT * from Directory";
		
		try {
			
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
//			stmt.close();

			{
				while (rs.next()) {
					System.out.println(rs.getInt(0));
					System.out.println(rs.getInt("id") + "\t" + rs.getString("path") + "\t"
							+ rs.getString("directoryName") + "\t" + rs.getString("user"));
				}
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

		// Controller has all the apis for add user, create user and delete user
		// creating an instance and calling api methods
		Controller controller = new Controller();
		controller.apiMethod(connection);*/

	}

}
