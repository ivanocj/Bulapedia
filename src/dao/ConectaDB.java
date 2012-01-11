package dao;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;

public class ConectaDB {
	public Connection conecta() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		Connection connection = null;
		try {
			connection = DriverManager
					.getConnection("jdbc:mysql://localhost:3306/test",
							"root", "bulapedia");
			return connection;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return connection;

	}

	public void desconecta(Connection connection) throws SQLException {
		if (!connection.isClosed()) {
			connection.close();
		}
	}
}
