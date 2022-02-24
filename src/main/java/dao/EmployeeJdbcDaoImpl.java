package dao;

import java.io.IOException;
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
import pojo.EmployeePojo;

public class EmployeeJdbcDaoImpl implements EmployeeDao {
	
	public static final Logger LOG = LogManager.getLogger(EmployeeJdbcDaoImpl.class);


	@Override
	public CustomerPojo addCustomer(CustomerPojo customerPojo) throws SystemException {
		LOG.info("Entering addCustomer() in Dao");
		// Step 2 - pass connection from DBUtil to conn
		Connection conn = DBUtil.obtainConnection();
		// fetch the book with the maximum bookId
//		
		try {
			// Create a statement
			Statement stmt = conn.createStatement();
			
			// Add customer information to SQL table
			String query2 = "INSERT INTO customer_details(customer_first_name, customer_last_name, customer_phone_number, customer_address, account_balance, customer_password) VALUES( '"+customerPojo.getCustomerFirstName()+"', '"+customerPojo.getCustomerLastName()+"', '"+customerPojo.getCustomerPhoneNumber()+"', '"+customerPojo.getCustomerAddress()+"', "+customerPojo.getAccountBalance()+", "+customerPojo.getCustomerPassword()+")";
			@SuppressWarnings("unused")
			int rows = stmt.executeUpdate(query2);
			
			// Add customer ID and account ID to customer POJO
			String query = "SELECT * FROM customer_details WHERE customer_phone_number='"+customerPojo.getCustomerPhoneNumber()+"'";
			ResultSet rs = stmt.executeQuery(query);
			if(rs.next()) {
				customerPojo.setCustomerId(rs.getInt(1));
				customerPojo.setAccountId(rs.getInt(6));
			}
			
		} catch (SQLException e) {
			
			throw new SystemException();
		}		
		
		LOG.info("Exiting addCustomer() in Dao");
		return customerPojo;
	}

	@Override
	public List<CustomerPojo> fetchAllCustomers() throws SystemException, DataNotFoundException {
		LOG.info("Entering fetchAllCustomer() in Dao");
		
		Connection conn = DBUtil.obtainConnection();
		
		List<CustomerPojo> allCustomers = new ArrayList<CustomerPojo>();
		
		try {

			Statement stmt = conn.createStatement();
			String query = "SELECT * FROM customer_details";
			ResultSet rs = stmt.executeQuery(query);
			// iterate through the result set
			while(rs.next()) {
				// copy each record into a CustomerPojo object
				CustomerPojo customerPojo = new CustomerPojo(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getInt(6), rs.getDouble(7), rs.getString(8));
				// add the book pojo object to the collection
				allCustomers.add(customerPojo);
			}
			
		} catch (SQLException e){
			
			throw new SystemException();
		} 		
		if (allCustomers.isEmpty()) {
			throw new DataNotFoundException("No Customers found in database.");
		}
		
		LOG.info("Exiting fetchAllCustomer() in Dao");
		return allCustomers;
	}

	@Override
	public EmployeePojo fetchEmployee(int employeeId) throws SystemException {
		LOG.info("Entering fetchEmployee() in Dao");
		EmployeePojo employeePojo = null;
		// Step 2 - pass the connection from DBUtil to conn
		Connection conn = DBUtil.obtainConnection();
		
		try {
			// Create a statement
			Statement stmt = conn.createStatement();
			String query = "SELECT * FROM employee_details WHERE employee_id="+employeeId;
			ResultSet rs = stmt.executeQuery(query);
			if(rs.next()) {
				
				employeePojo = new EmployeePojo(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6));
			}
			
		} catch (SQLException e){
			throw new SystemException();
		}
		
		LOG.info("Exiting fetchEmployee() in Dao");
		return employeePojo;
	}

}
