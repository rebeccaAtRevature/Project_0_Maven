package service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import dao.CustomerDao;
import dao.CustomerJdbcDaoImpl;
import exceptions.SystemException;
import pojo.CustomerPojo;

public class CustomerServiceImpl implements CustomerService{
	
	public static final Logger LOG = LogManager.getLogger(CustomerServiceImpl.class);
	
	// Create an object to call the DAO layer
	CustomerDao customerDao = new CustomerJdbcDaoImpl();
	
	// LOGOUT
	@Override
	public CustomerPojo customerLogin(int customerId, String customerPassword) throws SystemException {
		return customerDao.customerLogin(customerId, customerPassword);
	}
	
	// VIEW ACCOUNT DATA
	@Override
	public CustomerPojo fetchCustomer(int customerId) throws SystemException {
		return customerDao.fetchCustomer(customerId);
	}
	
	// TRANSFER MONEY TO ANOTHER ACCOUNT
	@Override
	public CustomerPojo moneyTransfer(int fromAccountId, int toAccountId, double transferMoney) throws SystemException {
		return customerDao.moneyTransfer(fromAccountId, toAccountId, transferMoney);
	}
	
	// Exit Application
	@Override
	public void exitApplication() throws SystemException {
		customerDao.exitApplication();		
	}

	
	
}
