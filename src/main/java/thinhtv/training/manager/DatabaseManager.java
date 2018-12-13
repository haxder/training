package thinhtv.training.manager;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

abstract class DatabaseManager {
	public static Connection connect = null;
	public static ResultSet resutls = null;
	public static CallableStatement stm = null;

	public void databaseConnect() throws ClassNotFoundException, SQLException {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		connect = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:thinhtv", "training", "12345");
	}

	public void databaseDisconnect() {
		try {
			stm.close();
			connect.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
