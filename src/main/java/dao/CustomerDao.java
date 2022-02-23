package dao;

import java.util.List;

import exceptions.DataNotFoundException;
import exceptions.SystemException;
import pojo.CustomerPojo;
import pojo.TransactionPojo;

public interface CustomerDao {
	
	// CUSTOMER OPERATIONS
	// ****************************************************************
	// LOGIN
	CustomerPojo customerLogin(int customerId, String customerPassword) throws SystemException;
	
	// VIEW ACCOUNT DETAILS
	CustomerPojo fetchCustomer(int customerId) throws SystemException;
	
	// TRANSFER MONEY TO ANOTHER ACCOUNT
	TransactionPojo moneyTransfer(TransactionPojo transactionPojo) throws SystemException;

	// VIEW TRANSACTION HISTORY
	List<TransactionPojo> transactionHistory(CustomerPojo customerPojo) throws SystemException, DataNotFoundException;
	TransactionPojo addTransaction(TransactionPojo transactionPojo) throws SystemException;
	// LOGOUT
	
	// Exit Application
	default void exitApplication() throws SystemException {
		DBUtil.closeConnection();
	}
	
	// CRUD OPERATIONS
	// ****************************************************************
	
	
}
