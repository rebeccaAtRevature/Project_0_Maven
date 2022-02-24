package service;

import java.io.IOException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import dao.EmployeeDao;
import dao.EmployeeJdbcDaoImpl;
import exceptions.DataNotFoundException;
import exceptions.SystemException;
import pojo.CustomerPojo;
import pojo.EmployeePojo;

public class EmployeeServiceImpl implements EmployeeService{
	
	public static final Logger LOG = LogManager.getLogger(EmployeeServiceImpl.class);
	
	EmployeeDao employeeDao;

	public EmployeeServiceImpl() {
		employeeDao = new EmployeeJdbcDaoImpl();
	}
	
	// LOGIN
	@Override
	public EmployeePojo employeeLogin(int employeeId, String employeePassword) throws SystemException {
		LOG.info("Entering employeeLogin() in Service Layer");
		
		EmployeePojo employeePojo = null;
		EmployeePojo loginAttempt = employeeDao.fetchEmployee(employeeId);
		if (loginAttempt.getPassword().equals(employeePassword)) {
			employeePojo = loginAttempt;
		}
		
		LOG.info("Exiting employeeLogin() in Service Layer");
		return employeePojo;
		
	}
	
	// REGISTER A CUSTOMER
	public CustomerPojo addCustomer(CustomerPojo customerPojo) throws SystemException {
		return employeeDao.addCustomer(customerPojo);
	}
	
	// VIEW ALL ACCOUNTS
	public List<CustomerPojo> fetchAllCustomers() throws SystemException, DataNotFoundException {
		return employeeDao.fetchAllCustomers();		
	}
	
	// CRUD OPERATIONS
	// ***********************************************************

	// READ
	@Override
	public EmployeePojo fetchEmployee(int employeeId) throws SystemException {
		return employeeDao.fetchEmployee(employeeId);
	}
	
}
