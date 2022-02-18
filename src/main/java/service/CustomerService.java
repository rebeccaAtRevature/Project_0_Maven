package service;

import exceptions.SystemException;
import pojo.CustomerPojo;

public interface CustomerService {
	
	// CUSTOMER OPERATIONS
		// ****************************************************************
		// LOGIN
		CustomerPojo customerLogin(int customerId, String customerPassword) throws SystemException;
		
		// VIEW ACCOUNT DETAILS
		CustomerPojo fetchCustomer(int customerId) throws SystemException;
		
		// TRANSFER MONEY TO ANOTHER ACCOUNT
		CustomerPojo moneyTransfer(int fromAccountId, int toAccountId, double transferMoney) throws SystemException;

		
		// LOGOUT
		
		// CRUD OPERATIONS
		// ****************************************************************
		
		
}
