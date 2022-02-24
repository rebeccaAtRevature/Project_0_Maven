package service;

import java.io.IOException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import dao.CustomerDao;
import dao.CustomerJdbcDaoImpl;
import exceptions.DataNotFoundException;
import exceptions.SystemException;
import pojo.CustomerPojo;
import pojo.TransactionPojo;

public class CustomerServiceImpl implements CustomerService{
	
	public static final Logger LOG = LogManager.getLogger(CustomerServiceImpl.class);
	
	// Create an object to call the DAO layer
	CustomerDao customerDao = new CustomerJdbcDaoImpl();
	
	// LOGIN
	@Override
	public CustomerPojo customerLogin(int customerId, String customerPassword) throws SystemException {
		LOG.info("Entering customerLogin() in Dao");
		CustomerPojo customerPojo = null;
		CustomerPojo loginAttempt = customerDao.fetchCustomer(customerId);
		if (loginAttempt.getCustomerPassword().equals(customerPassword)) {
			customerPojo = loginAttempt;
		}
		LOG.info("Exiting customerLogin() in Dao");
		return customerPojo;
	}
	
	// VIEW ACCOUNT DATA
	@Override
	public CustomerPojo fetchCustomer(int customerId) throws SystemException {
		return customerDao.fetchCustomer(customerId);
	}
	
	// TRANSFER MONEY TO ANOTHER ACCOUNT
	@Override
	public TransactionPojo moneyTransfer(TransactionPojo transactionPojo) throws SystemException {
		return customerDao.moneyTransfer(transactionPojo);
	}
	
	// VIEW TRANSACTION HISTORY
	@Override
	public List<TransactionPojo> transactionHistory(CustomerPojo customerPojo) throws SystemException, DataNotFoundException {	
		return customerDao.transactionHistory(customerPojo);
	}

	@Override
	public TransactionPojo addTransaction(TransactionPojo transactionPojo) throws SystemException {
		return customerDao.addTransaction(transactionPojo);
	}

	// Exit Application
	@Override
	public void exitApplication() throws SystemException {
		customerDao.exitApplication();		
	}
	
}
