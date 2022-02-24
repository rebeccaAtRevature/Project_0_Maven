package dao;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

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
	
	static Connection obtainConnection() throws SystemException{
		LOG.info("Entering obtainConnection() in DBUtil");
		
//		FileInputStream fileStream;
//		try {
//			fileStream = new FileInputStream("C:/Users/rebel/eclipse-workspace/Project0/src/main/resources/JDBCPropertiesFile.properties");
//		} catch (FileNotFoundException e1) {
//			e1.printStackTrace();
//			throw new SystemException();
//		} 
//		Properties properties = new Properties(); 
//		try {
//			properties.load(fileStream);
//		} catch (IOException e1) {
//			e1.printStackTrace();
//			throw new SystemException();
//		}
//		String url = properties.getProperty("URL");
//		String password = properties.getProperty("CONNECTION_PASSWORD");
//		String username = properties.getProperty("CONNECTION_USERNAME");
//		String URL = System.getenv(url);
//		String CONNECTION_PASSWORD = System.getenv(password);
//		String CONNECTION_USERNAME = System.getenv(username);
		String URL = "jdbc:postgresql://127.0.0.1:5432/bank";
		String CONNECTION_USERNAME = "postgres";
		String CONNECTION_PASSWORD = "Beccyshmeccy95";
		
		// DESIGN PATTERN - singleton design pattern
		if(conn == null) {
			try {
				conn = DriverManager.getConnection(URL, CONNECTION_USERNAME, CONNECTION_PASSWORD);
			} catch (SQLException e) {
				throw new SystemException();
			}
		}
		LOG.info("Exiting obtainConnection() in DBUtil");
		return conn;
	}
	
	static void closeConnection() throws SystemException {
		if(conn != null) {
			try {
				conn.close();

			} catch (SQLException e) {
				throw new SystemException();
			}
		}
		
	}
}