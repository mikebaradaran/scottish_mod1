package qa.databases;

import java.sql.*;

public class Program {
	public static void main(String[] args) throws SQLException {

		String url = "jdbc:sqlserver://localhost\\SQLEXPRESS:1433"
			    + ";databaseName=qastore"
			    + ";user=bob"
			    + ";password=password123"
			    + ";encrypt=true"
			    + ";trustServerCertificate=true";


		try (Connection conn = DriverManager.getConnection(url)) {
			System.out.println("Connected!");
			String sql = "SELECT * FROM company";
			Statement statement = conn.createStatement();
			ResultSet resultSet = statement.executeQuery(sql);

			while (resultSet.next()) {
				System.out.println(resultSet.getString(1) +","+ resultSet.getString(2));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

//		Connection connection = DriverManager.getConnection(connectionUrl);
//		String sql = "SELECT company_name FROM company";
//
//		Statement statement = connection.createStatement();
//		ResultSet resultSet = statement.executeQuery(sql);
//
//		while (resultSet.next()) {
//			System.out.println(resultSet.getString(1));
//		}
//		connection.close();
	}
}
