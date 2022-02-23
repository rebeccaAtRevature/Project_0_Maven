package service;

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
	public TransactionPojo moneyTransfer(TransactionPojo transactionPojo) throws SystemException {
		return customerDao.moneyTransfer(transactionPojo);
	}
	
	// VIEW TRANSACTION HISTORY
	@Override
	public List<TransactionPojo> transactionHistory() throws SystemException, DataNotFoundException {	
		return customerDao.transactionHistory();
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
