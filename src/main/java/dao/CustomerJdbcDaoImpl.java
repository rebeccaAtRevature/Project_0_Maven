package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import exceptions.SystemException;
import pojo.CustomerPojo;
import pojo.EmployeePojo;

public class CustomerJdbcDaoImpl implements CustomerDao{
	
	public static final Logger LOG = LogManager.getLogger(CustomerJdbcDaoImpl.class);

	@Override
	public CustomerPojo customerLogin(int customerId, String customerPassword) throws SystemException {
		LOG.info("Entering customerLogin() in Dao");
		CustomerPojo customerPojo = null;
		CustomerPojo loginAttempt = fetchCustomer(customerId);
		if (loginAttempt.getCustomerPassword().equals(customerPassword)) {
			customerPojo = loginAttempt;
		}
		LOG.info("Exiting customerLogin() in Dao");
		return null;
	}

	@Override
	public CustomerPojo fetchCustomer(int customerId) throws SystemException {
		LOG.info("Entering fetchCustomer() in Dao");
		CustomerPojo customerPojo = null;
		// Step 2 - pass the connection from DBUtil to conn
		Connection conn = DBUtil.obtainConnection();
		
		try {
			// Create a statement
			Statement stmt = conn.createStatement();
			String query = "SELECT * FROM customer_details WHERE customer_id="+customerId;
			ResultSet rs = stmt.executeQuery(query);
			if(rs.next()) {
				customerPojo = new CustomerPojo(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getInt(6), rs.getDouble(7), rs.getString(8));
			}
			
		} catch (SQLException e){
			throw new SystemException();
		}
		
		LOG.info("Exiting fetchCustomer() in Dao");
		return null;
	}

	@Override
	public CustomerPojo moneyTransfer(int fromAccountId, int toAccountId, double transferMoney) throws SystemException {
		LOG.info("Entering updateCustomer() in Dao");
		// Step 2 - pass the connection from DBUtil to conn
		Connection conn = DBUtil.obtainConnection();
		
		try {
			Statement stmt = conn.createStatement();
			
			String query1 = "UPDATE account_details Set account_balance=account_balance-"+transferMoney+" WHERE account_id="+fromAccountId;
			String query2 = "UPDATE account_details Set account_balance=account_balance+"+transferMoney+" WHERE account_id="+toAccountId;
			
			// Mark the start of the transaction
			conn.setAutoCommit(false); // Don't make any changes into the database until I say so
			int rows1 = stmt.executeUpdate(query1);
			//if(true) {throw new SQLException();}
			int rows2 = stmt.executeUpdate(query2);
			conn.commit(); // The changes are committed to the DB
			// Marks the end of the transaction 
			
		} catch (SQLException e) {
			throw new SystemException();
		}
		LOG.info("Exiting updateCustomer() in Dao");
		return null;
	}

}
