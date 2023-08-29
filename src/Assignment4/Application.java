package Assignment4;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

public class Application {
	static HashMap<String, String> userInputMap = new HashMap<>();
	static HashMap<String, String> fileSystemInputMap = new HashMap<>();

	public static void main(String[] args) throws IOException, ClassNotFoundException {
				// starting DB connection
		Connect connect = new Connect();
		Connection connection = connect.dbConnect();

		String sql = "SELECT * from Directory";
		String user = "SELECT * from users";
		String deleteUser = "DELETE FROM Directory WHERE path = 'null'";
		try {

			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
//			stmt.close();
			System.out.println("Directory Table :");
			{
				while (rs.next()) {
					System.out.println(rs.getInt("id") + "\t" + rs.getString("path") + "\t"
							+ rs.getString("directoryName") + "\t" + rs.getString("user"));
				}
			}
			
			Statement stmt1 = connection.createStatement();
			ResultSet dl = stmt1.executeQuery(user);
			System.out.println("Users Table :");
			{
				while (dl.next()) {
					System.out.println(dl.getInt("id") + "\t" + dl.getString("firstName") + "\t"
							+ dl.getString("lastName") + "\t" + dl.getString("role"));
				}
			}
			
			
			Statement stmt2 = connection.createStatement();
			stmt2.executeUpdate(deleteUser);
			stmt2.close();
			
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

		// Controller has all the apis for add user, create user and delete user
		// creating an instance and calling api methods
		Controller controller = new Controller();
		controller.apiMethod(connection);

	}

}
