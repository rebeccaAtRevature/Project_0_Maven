package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import exceptions.DataNotFoundException;
import exceptions.SystemException;
import pojo.CustomerPojo;
import pojo.TransactionPojo;

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
		return customerPojo;
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
		return customerPojo;
	}

	@SuppressWarnings("unused")
	@Override
	public TransactionPojo moneyTransfer(TransactionPojo transactionPojo) throws SystemException {
		LOG.info("Entering moneyTransfer() in Dao");
		
		
		// Step 2 - pass the connection from DBUtil to conn
		Connection conn = DBUtil.obtainConnection();
		
		try {
			Statement stmt = conn.createStatement();
			
			String query1 = "UPDATE customer_details Set account_balance=account_balance-"+transactionPojo.getTransferAmount()+" WHERE account_id="+transactionPojo.getAccountIdFrom();
			String query2 = "UPDATE customer_details Set account_balance=account_balance+"+transactionPojo.getTransferAmount()+" WHERE account_id="+transactionPojo.getAccountIdTo();
			
			// Mark the start of the transaction
			conn.setAutoCommit(false); // Don't make any changes into the database until I say so
			int rows1 = stmt.executeUpdate(query1);
			//if(true) {throw new SQLException();}
			int rows2 = stmt.executeUpdate(query2);
			// The changes are committed to the DB
			// Marks the end of the transaction 
			
			
			// Read any automatically generated values from PostgresSQL and assign them to the transaction POJO
			String query3 = "SELECT * FROM customer_details WHERE customer_id='"+transactionPojo.getAccountIdFrom()+"'";
			ResultSet rs1 = stmt.executeQuery(query3);
			if(rs1.next()) {
				transactionPojo.setNewBalanceFrom(rs1.getDouble(7));
			}
			String query4 = "SELECT * FROM customer_details WHERE customer_id='"+transactionPojo.getAccountIdTo()+"'";
			ResultSet rs2 = stmt.executeQuery(query4);
			if(rs2.next()) {
				transactionPojo.setNewBalanceTo(rs2.getDouble(7));
			}
			
			addTransaction(transactionPojo);
			// Committing after transaction is added to database to insure all the tables 
			conn.commit();
			
		} catch (SQLException e) {
			
			// If the transaction was incomplete then undo
			try {
				conn.rollback();
				System.out.println("Transaction failed...");
			} catch (SQLException e1) {
				throw new SystemException();
			}
			
			throw new SystemException();
			
		} 
		
		LOG.info("Exiting moneyTransfer() in Dao");
		return transactionPojo;
	}

	@Override
	public List<TransactionPojo> transactionHistory(CustomerPojo customerPojo) throws SystemException, DataNotFoundException {
		LOG.info("Entering transactionHistory() in Dao");
		
		Connection conn = DBUtil.obtainConnection();
		
		List<TransactionPojo> allTransactions = new ArrayList<TransactionPojo>();
		
		try {

			Statement stmt = conn.createStatement();
			String query = "SELECT transaction_date_and_time, customer_first_name, customer_last_name, transfer_amount, new_balance_from, new_balance_to FROM customer_details INNER JOIN transaction_history ON account_transfer_from=account_id WHERE customer_first_name='"+customerPojo.getCustomerFirstName()+"'ORDER BY transaction_history.transaction_date_and_time";
			ResultSet rs = stmt.executeQuery(query);
			// iterate through the result set
			while(rs.next()) {
				// copy each record into a transactionPojo object
				TransactionPojo transactionPojo = new TransactionPojo(rs.getTimestamp(1), rs.getString(2), rs.getString(3), rs.getDouble(4), rs.getDouble(5), rs.getDouble(6));
				// add the transaction POJO object to the collection
				allTransactions.add(transactionPojo);
			}
			
		} catch (SQLException e){
			throw new SystemException();
		} 		
		if (allTransactions.isEmpty()) {
			throw new DataNotFoundException("No transactions were found in the database.");
		}
		
		LOG.info("Exiting transactionHistory() in Dao");
		return allTransactions;
	}
	
	@Override
	public TransactionPojo addTransaction(TransactionPojo transactionPojo) throws SystemException {
		LOG.info("Entering addTransaction() in Dao");
		// Step 2 - pass connection from DBUtil to conn
		Connection conn = DBUtil.obtainConnection();
		
		try {
			// Create a statement
			Statement stmt = conn.createStatement();
			// Add customer information to SQL table
			String query2 = "INSERT INTO transaction_history(account_transfer_from, account_transfer_to, transfer_amount, new_balance_from, new_balance_to) VALUES( "+transactionPojo.getAccountIdFrom()+", "+transactionPojo.getAccountIdTo()+", "+transactionPojo.getTransferAmount()+", "+transactionPojo.getNewBalanceFrom()+", "+transactionPojo.getNewBalanceTo()+")";
			
			@SuppressWarnings("unused")
			int rows = stmt.executeUpdate(query2);
			
			
		} catch (SQLException e) {
			
			throw new SystemException();
		}		
		
		LOG.info("Exiting addTransaction() in Dao");
		return transactionPojo;
	}
	

}
