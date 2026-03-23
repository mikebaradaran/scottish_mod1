package qa.databases;

import java.sql.*;

public class SQL_injection {
	public static void main(String[] args) throws SQLException {

		String url = "jdbc:sqlserver://localhost\\SQLEXPRESS:1433" + ";databaseName=qastore" + ";user=bob"
				+ ";password=password123" + ";encrypt=true" + ";trustServerCertificate=true";

		try (Connection conn = DriverManager.getConnection(url)) {
			System.out.println("Connected!");
			String county ="Devon";
			// county ="Devon' or 1=1;--";
			String sql = String.format("SELECT * FROM company WHERE county = '%s'",county);
			Statement statement = conn.createStatement();
			ResultSet resultSet = statement.executeQuery(sql);

			while (resultSet.next()) {
				System.out.println(resultSet.getString(1) + 
						"," + resultSet.getString(2) +
						"," + resultSet.getString(3) +
						"," + resultSet.getString(4)
						);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
}
