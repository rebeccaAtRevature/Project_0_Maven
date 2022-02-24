package dao;

import java.io.IOException;
import java.util.List;

import exceptions.DataNotFoundException;
import exceptions.SystemException;
import pojo.CustomerPojo;
import pojo.EmployeePojo;

public interface EmployeeDao {
	
	// EMPLOYEE OPERATIONS
	// *****************************************************
	
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
