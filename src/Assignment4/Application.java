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

	@SuppressWarnings("static-access")
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		// starting DB connection
		Connect connect = new Connect();
		Connection connection = connect.dbConnect();
		
		String sql = "SELECT * from users";

		try {
			// Connection connection = connect.dbConnect();
			Statement stmt = connection.createStatement();
			// stmt.executeUpdate(sql);
			ResultSet rs = stmt.executeQuery(sql);
			// stmt.close();
			{
				// looping through the records
				while (rs.next()) {
					System.out.println(rs.getInt("id") + "\t" + rs.getString("firstName") + "\t"
							+ rs.getString("lastName") + "\t" + rs.getString("role"));
				}
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

		// Controller has all the apis for add user, create user and delete user
		// creating an instance and calling api methods
		Controller controller = new Controller();
		controller.apiMethod(connection);

	}

}

//
//CREATE TABLE Directory (
//	    id            INT (100)      PRIMARY KEY,
//	    Path          TEXT (1000),
//	    DirectoryName VARCHAR (1000),
//	    CreatedDate   DATE (1000),
//	    UpdatedDate   DATE (1000),
//	    User          VARCHAR (1000) 
//	);
