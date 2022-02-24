package junit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import dao.CustomerDao;
import dao.CustomerJdbcDaoImpl;
import dao.EmployeeDao;
import dao.EmployeeJdbcDaoImpl;
import exceptions.SystemException;
import pojo.CustomerPojo;
import pojo.EmployeePojo;
import service.CustomerService;
import service.CustomerServiceImpl;
import service.EmployeeService;
import service.EmployeeServiceImpl;

public class LoginTest {
	
	//	USE MOCKITO TO "MOCK" fetchCustomer() AND fetchEmployee() METHODS 
	//  TO AVOID ENTERING THE DAO AND ONLY TEST THE SERVICE LAYER
	
	// Create a sample implementation of our DAO
	CustomerDao customerDao = mock(CustomerJdbcDaoImpl.class);
	EmployeeDao employeeDao = mock(EmployeeJdbcDaoImpl.class);
	
	
	static CustomerService customerService;
	static EmployeeService employeeService;
	
	@BeforeAll
	public static void setUp() {
		// Instantiate Service Layer Objects
		customerService = new CustomerServiceImpl();
		employeeService = new EmployeeServiceImpl();
	}
	
	@Test
	public void testCustomerLogin() {
		
		try {
			CustomerPojo actualResult = customerService.customerLogin(1,"1234");
			CustomerPojo expectedResult = new CustomerPojo(1, "Mike", "Wazowski",  "(546)213-1562" , "123 Monster Ave." , 1 , 2530.0, "1234");
			when(customerDao.fetchCustomer(1)).thenReturn(new CustomerPojo(1, "Mike", "Wazowski",  "(546)213-1562" , "123 Monster Ave." , 1 , 2530.0, "1234"));
			assertEquals(actualResult,expectedResult);
			//verify(customerDao).customerLogin(1, "1234");
			
		} catch (SystemException e) {
			e.printStackTrace();
		} 
		
	}
	
	@Test
	public void testEmployeeLogin() {
		
		try {
			EmployeePojo actualResult = employeeService.employeeLogin(100,"1234");
			EmployeePojo expectedResult = new EmployeePojo(100, "Celia", "Mae",  "(546)309-3823" , "566 Weelia Ct." , "1234");
			when(employeeDao.fetchEmployee(100)).thenReturn(new EmployeePojo(100, "Celia", "Mae",  "(546)309-3823" , "566 Weelia Ct." , "1234"));
			assertEquals(actualResult,expectedResult);
			//verify(employeeDao).employeeLogin(100, "1234");
			
		} catch (SystemException e) {
			e.printStackTrace();
		} 
		
	}

}
