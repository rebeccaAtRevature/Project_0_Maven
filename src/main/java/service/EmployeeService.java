package service;

import java.io.IOException;
import java.util.List;

import exceptions.DataNotFoundException;
import exceptions.SystemException;
import pojo.CustomerPojo;
import pojo.EmployeePojo;

public interface EmployeeService {

	// EMPLOYEE OPERATIONS
		// *****************************************************
		
		// LOGIN
		EmployeePojo employeeLogin(int employeeId, String employeePassword) throws SystemException;
		
		// REGISTER A CUSTOMER
		CustomerPojo addCustomer(CustomerPojo customerPojo) throws SystemException;
		
		// LIST ALL COUSTOMER INFORMATION
		List<CustomerPojo> fetchAllCustomers() throws SystemException, DataNotFoundException;


		// LOGOUT
		
		
		// CRUD OPERATIONS
		// *****************************************************
		
		// READ
		EmployeePojo fetchEmployee(int employeeId) throws SystemException;
	
}
