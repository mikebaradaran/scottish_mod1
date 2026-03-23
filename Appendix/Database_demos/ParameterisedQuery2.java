package qa.databases;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ParameterisedQuery2 {
	public static void main(String[] args) {
		String url = "jdbc:sqlserver://localhost\\SQLEXPRESS:1433"
			    + ";databaseName=qastore"
			    + ";user=bob"
			    + ";password=password123"
			    + ";encrypt=true"
			    + ";trustServerCertificate=true";


		// SQL query with placeholders (?)
		String sql = "SELECT * FROM company WHERE county=?";

		try (Connection conn = DriverManager.getConnection(url);
				PreparedStatement stmt = conn.prepareStatement(sql)){

			// Set parameter value (index starts at 1)
			stmt.setString(1, "Devon");
			// stmt.setString(1, "Devon or 1=1");  // SQL injection does not work!

			try (ResultSet rs = stmt.executeQuery()) {
				while (rs.next()) {
					int id = rs.getInt("company_no");
					String name = rs.getString("company_name");
					String country = rs.getString("county");
					System.out.printf("No: %d, Name: %s, Country: %s", id, name, country);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
