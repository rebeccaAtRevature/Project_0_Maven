package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import exceptions.SystemException;

public class DBUtil {
	
	public static final Logger LOG = LogManager.getLogger(DBUtil.class);
	
	static Connection conn;
	// Step 1 - Load the driver
	static {
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	// Step 2 - Establish the connection to the database
	// DBUtil must return the same connection again and again
	// MUST NOT return new connection every time the class is called
	
	static Connection obtainConnection(){
		LOG.info("Entering obtainConnection() in DBUtil");
		String connectionUrl = "jdbc:postgresql://127.0.0.1:5432/bank";
		String userName = "postgres";
		String password = "Beccyshmeccy95";
		
		// DESIGN PATTERN - singleton design pattern
		if(conn == null) {
			try {
				conn = DriverManager.getConnection(connectionUrl, userName, password);
				System.out.println("Connection Successful...");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		LOG.info("Exiting obtainConnection() in DBUtil");
		return conn;
	}
	
	static void closeConnection() throws SystemException {
		
		try {
			conn.close();
			System.out.println("Connection has been closed...");
		} catch (SQLException e) {
			throw new SystemException();
		}
	}
}