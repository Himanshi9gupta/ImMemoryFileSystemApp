package Assignment4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.sql.Connection;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;

public class Controller {
	static HashMap<String, String> userInputMap = new HashMap<>();
	static HashMap<String, String> fileSystemInputMap = new HashMap<>();
	static HashMap<String, String> filesMap = new HashMap<>();
	public void apiMethod(Connection connection) throws IOException {
				int serverPort = 8080;

		HttpServer server = HttpServer.create(new InetSocketAddress(serverPort), 0);

		// add user api
		server.createContext("/user", (exchange -> {
			InputStream requestBody = exchange.getRequestBody();
			BufferedReader reader = new BufferedReader(new InputStreamReader(requestBody));

			StringBuilder requestData = new StringBuilder();
			String line = reader.readLine();

			if (line != null && !line.isEmpty()) {
				requestData.append(line);
				String jsonString = requestData.substring(1, requestData.length() - 1);

				String[] keyValuePairs = jsonString.split(",");// Split each line into key and value parts

				for (String pair : keyValuePairs) {
					String[] keyValue = pair.split(":");
					if (keyValue.length == 2) {
						String key = keyValue[0].trim().replace("\"", "");
						String value = keyValue[1].trim().replace("\"", "");
						userInputMap.put(key, value);
					}
				}
			}
			System.out.println(userInputMap);

			// setting headers
			setHeaders(exchange);
			
			exchange.sendResponseHeaders(200, 0);

			String name = userInputMap.get("name");
			String lastName = userInputMap.get("lastName");
			String role = userInputMap.get("role");
			
			Users user = new Users(name, role);
			String response = user.addUser(name, lastName, role, connection);
			FileSystem fileSystem = new FileSystem("root", role, "/", name, connection);
			Directories.createDirectory("root", "/", role, connection).toString();
			Permissions.setPermissions(name, role);

			OutputStream output = exchange.getResponseBody();
			output.write(response.getBytes());
			output.flush();
			exchange.close();
		}));

		// Second file system create api
		server.createContext("/fileSystem/create", (exchange -> {
			InputStream requestBody = exchange.getRequestBody();
			BufferedReader reader = new BufferedReader(new InputStreamReader(requestBody));

			StringBuilder requestData = new StringBuilder();

			String line = reader.readLine();

			if (line != null) {
				if (!line.isEmpty()) {
					requestData.append(line);
					String jsonString = requestData.substring(1, requestData.length() - 1);

					String[] keyValuePairs = jsonString.split(",");// Split each line into key and value parts

					for (String pair : keyValuePairs) {
						String[] keyValue = pair.split(":");
						if (keyValue.length == 2) {
							String key = keyValue[0].trim().replace("\"", "");
							String value = keyValue[1].trim().replace("\"", "");
							fileSystemInputMap.put(key, value);
						}
					}
				}
			}
			System.out.println(fileSystemInputMap);

			// setting headers
			setHeaders(exchange);
			
			exchange.sendResponseHeaders(200, 0);
			String name = userInputMap.get("name");
			String role = userInputMap.get("role");

			String path = fileSystemInputMap.get("path");
			String directoryName = fileSystemInputMap.get("directoryName");


			FileSystem fileSystem = new FileSystem(directoryName, role, path, name, connection);
//			Directories.createDirectory(directoryName, path, role, connection);
			String response = Directories.createDirectory(directoryName, path, role, connection).toString();

			OutputStream output = exchange.getResponseBody();
			output.write(response.getBytes());
			output.flush();
			exchange.close();
		}));

		// file system delete api
		server.createContext("/fileSystem/delete", (exchange -> {
			InputStream requestBody = exchange.getRequestBody();
			BufferedReader reader = new BufferedReader(new InputStreamReader(requestBody));

			// setting headers
			setHeaders(exchange);

			exchange.sendResponseHeaders(200, 0);
			String name = userInputMap.get("name");
			String role = userInputMap.get("role");

			String path = fileSystemInputMap.get("path");
			String directoryName = fileSystemInputMap.get("directoryName");

			String response = Directories.deleteDirectory(directoryName, path, role, name, connection);

			OutputStream output = exchange.getResponseBody();
			output.write(response.getBytes());
			output.flush();
			exchange.close();
		}));

		// create files
		server.createContext("/fileSystem/directory/createFile", (exchange -> {
			InputStream requestBody = exchange.getRequestBody();
			BufferedReader reader = new BufferedReader(new InputStreamReader(requestBody));

			StringBuilder requestData = new StringBuilder();

			String line = reader.readLine();

			if (line != null) {
				if (!line.isEmpty()) {
					requestData.append(line);
					String jsonString = requestData.substring(1, requestData.length() - 1);

					String[] keyValuePairs = jsonString.split(",");// Split each line into key and value parts

					for (String pair : keyValuePairs) {
						String[] keyValue = pair.split(":");
						if (keyValue.length == 2) {
							String key = keyValue[0].trim().replace("\"", "");
							String value = keyValue[1].trim().replace("\"", "");
							filesMap.put(key, value);
						}
					}
				}
			}
			System.out.println(filesMap);
			// setting headers
			setHeaders(exchange);

			exchange.sendResponseHeaders(200, 0);
			String name = userInputMap.get("name");
			String role = userInputMap.get("role");

			String filePath = filesMap.get("filepath");
			String fileName = filesMap.get("fileName");

			//FileSystem fileSystem = new FileSystem(fileName, role, filePath, name, connection);
//			Directories.createDirectory(directoryName, path, role, connection);
			Files files = new Files(fileName, filePath,name, role , connection);
			String response = files.createFile(fileName, filePath, name, role, connection).toString();

			OutputStream output = exchange.getResponseBody();
			output.write(response.getBytes());
			output.flush();
			exchange.close();
		}));

		server.createContext("/fileSystem/directory/deleteFile", (exchange -> {
			InputStream requestBody = exchange.getRequestBody();
			BufferedReader reader = new BufferedReader(new InputStreamReader(requestBody));

			// setting headers
			setHeaders(exchange);

			exchange.sendResponseHeaders(200, 0);
			String name = userInputMap.get("name");
			String role = userInputMap.get("role");

			String path = filesMap.get("filepath");
			String fname = filesMap.get("fileName");

			Files files = new Files(fname, path,name, role , connection);
			String response = files.createFile(fname, path, name, role, connection).toString();

			OutputStream output = exchange.getResponseBody();
			output.write(response.getBytes());
			output.flush();
			exchange.close();
		}));


		// file system update api
		server.createContext("/fileSystem/updateFile", (exchange -> {
			InputStream requestBody = exchange.getRequestBody();
			BufferedReader reader = new BufferedReader(new InputStreamReader(requestBody));

			// setting headers
			setHeaders(exchange);

			exchange.sendResponseHeaders(200, 0);
			String name = userInputMap.get("name");
			String role = userInputMap.get("role");

			String path = fileSystemInputMap.get("path");
			String directoryName = fileSystemInputMap.get("directoryName");

			String response = Directories.updateDirectory(directoryName, path, role, name, connection);

			OutputStream output = exchange.getResponseBody();
			output.write(response.getBytes());
			output.flush();
			exchange.close();
		}));


		server.createContext("/fileSystem/directory/updateFile", (exchange -> {
			InputStream requestBody = exchange.getRequestBody();
			BufferedReader reader = new BufferedReader(new InputStreamReader(requestBody));

			// setting headers
			setHeaders(exchange);

			exchange.sendResponseHeaders(200, 0);
			String fname = userInputMap.get("name");
			String role = userInputMap.get("role");

			String path = fileSystemInputMap.get("path");
			String name = fileSystemInputMap.get("directoryName");

			//String response = Directories.updateDirectory(directoryName, path, role, name, connection);
			Files files = new Files(fname, path,name, role , connection);
			String response = files.updateFile(fname, path, name, role, connection).toString();

			OutputStream output = exchange.getResponseBody();
			output.write(response.getBytes());
			output.flush();
			exchange.close();
		}));

		server.setExecutor(null); // creates a default executor
		server.start();
		System.out.println("Server started at http://localhost:8080/");
	}

	private void setHeaders(HttpExchange exchange) {
		exchange.getResponseHeaders().set("Content-Type", "application/json");
		exchange.getResponseHeaders().set("Access-Control-Allow-Origin", "*");
		exchange.getResponseHeaders().set("Access-Control-Allow-Methods", "GET, POST, OPTIONS");
		exchange.getResponseHeaders().set("Access-Control-Allow-Headers",
				"Origin, X-Requested-With, Content-Type, Accept");
		
	}

}
