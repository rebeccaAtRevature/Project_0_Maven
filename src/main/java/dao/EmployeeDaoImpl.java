package dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import exceptions.SystemException;
import pojo.CustomerPojo;
import pojo.EmployeePojo;

public class EmployeeDaoImpl implements EmployeeDao{
	
	public static final Logger LOG = LogManager.getLogger(EmployeeDaoImpl.class);
	
	List<CustomerPojo> allCustomers;
	List<EmployeePojo> allEmployees;
	
	public EmployeeDaoImpl() {
		allCustomers = new ArrayList<CustomerPojo>();
		allEmployees = new ArrayList<EmployeePojo>();
		
		allEmployees.add(new EmployeePojo(200, "Heather", "Martinez", "1234567890", "123 3rd Street", "!@#$"));
		allEmployees.add(new EmployeePojo(201, "Ethan", "Wu", "2345678901", "2938 Wisteria Lane", "@#$%"));
		allEmployees.add(new EmployeePojo(202, "Shannon" , "Wojtla", "3456789012", "987 Panda St.","#$%^" ));
	}
	// LOGIN
	@Override
	public EmployeePojo employeeLogin(int employeeId, String employeePassword) throws SystemException {
		LOG.info("Entering employeeLogin() in DAO");
		EmployeePojo employeePojo = null;
		EmployeeDao employeeDao = new EmployeeDaoImpl();
		EmployeePojo attemptedLogin = employeeDao.fetchEmployee(employeeId);
		
		if (attemptedLogin.getPassword().equals(employeePassword)) {
			employeePojo = attemptedLogin;
		}
		LOG.info("Exiting employeeLogin() in DAO");
		return employeePojo;
	}

	// REGISTER A CUSTOMER
	@Override
	public CustomerPojo addCustomer(CustomerPojo customerPojo) {
		LOG.info("Entering addCustomer() in DAO");
		// Create new customer ID & account ID
		int customerId;
		int accountId;
		if (allCustomers.size() == 0) {
			customerId = 100;
			accountId = 10000;
		} else {
			// Obtain the ID of the previous customer added and add one to obtain new value
			customerId = allCustomers.get(allCustomers.size()-1).getCustomerId() + 1;
			// Obtain the ID of the previous account added and add one to obtain new value
			accountId = allCustomers.get(allCustomers.size()-1).getAccountId() + 1;
		}
		customerPojo.setCustomerId(customerId);
		customerPojo.setAccountId(accountId);
		
		allCustomers.add(customerPojo);
		
		LOG.info("Exiting addCustomer() in DAO");
		return customerPojo;
	}

	// LIST ALL COUSTOMER INFORMATION
	@Override
	public List<CustomerPojo> fetchAllCustomers() {
		LOG.info("Entering fetchAllCustomer() in DAO");
		
		
		LOG.info("Exiting fetchAllCustomer() in DAO");
		return allCustomers;
	}
	
	// Employee CRUD
	
	// READ
	@Override
	public EmployeePojo fetchEmployee(int employeeId) {
		LOG.info("Entering fetchEmployee() in DAO");
		EmployeePojo returnEmployeePojo = null;
	
		// Create new ArrayList to iterate through
		List<EmployeePojo> allReturnEmployee = new ArrayList<EmployeePojo>(allEmployees);
		allReturnEmployee.removeIf(employee -> employee.getEmployeeId() != employeeId);
		if (allReturnEmployee.size() == 1) {
			returnEmployeePojo = allReturnEmployee.get(0);
		} 
		LOG.info("Exiting fetchEmployee() in DAO");
		return returnEmployeePojo;
	}	

}
