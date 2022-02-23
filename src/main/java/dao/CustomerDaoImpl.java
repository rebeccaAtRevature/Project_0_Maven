package dao;



import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import exceptions.DataNotFoundException;
import exceptions.SystemException;
import pojo.CustomerPojo;
import pojo.TransactionPojo;


public class CustomerDaoImpl implements CustomerDao{
	
	public static final Logger LOG = LogManager.getLogger(CustomerDaoImpl.class);
	
	List<CustomerPojo> allCustomers;
	
	// Constructor
	public CustomerDaoImpl() {
		allCustomers = new ArrayList<CustomerPojo>();
		allCustomers.add(new CustomerPojo(100, "P", "Sherman", "8204449999", "42 Wallabe Way", 1000, 200,"1234"));
		allCustomers.add(new CustomerPojo(101, "Mike", "Wazowski", "7343334444", "46168 Monster Ave", 1001, 3000, "2345"));
		allCustomers.add(new CustomerPojo(102, "James P.", "Suillivan", "5848881111", "46168 Monster Ave", 1002, 300, "3456"));
	}

	// LOGIN
	@Override
	public CustomerPojo customerLogin(int customerId, String customerPassword) throws SystemException {
		LOG.info("Entering customerLogin() in DAO");
		CustomerPojo customerPojo = null;
		CustomerDao customerDao = new CustomerDaoImpl();
		CustomerPojo attemptedLogin = customerDao.fetchCustomer(customerId);
	
		if (attemptedLogin.getCustomerPassword().equals(customerPassword)) {
			customerPojo = attemptedLogin;
		}
		LOG.info("Exiting customerLogin() in DAO");
		return customerPojo;
	}
	
	// VIEW ACCOUNT INFO
	@Override
	public CustomerPojo fetchCustomer(int customerId) {
		LOG.info("Entering fetchCustomer() in DAO");
		CustomerPojo returnCustomerPojo = null;
		
		// Create new ArrayList to iterate through
		List<CustomerPojo> allReturnCustomer = new ArrayList<CustomerPojo>(allCustomers);
		allReturnCustomer.removeIf(customer -> customer.getCustomerId() != customerId);
		if (allReturnCustomer.size() == 1) {
			returnCustomerPojo = allReturnCustomer.get(0);
		}
		LOG.info("Exiting fetchCustomer() in DAO");
		return returnCustomerPojo;
	}

	// TRANSFER TO ANOTHER ACCOUNT
	/*@Override
	public CustomerPojo updateCustomer(CustomerPojo customerPojo) {
		LOG.info("Entering updateCustomer() in DAO");
		CustomerPojo returnCustomerPojo = null;
		
		// Create new ArrayList to iterate through
		List<CustomerPojo> allReturnCustomer = new ArrayList<CustomerPojo>();
		allReturnCustomer.removeIf(customer -> customer.getCustomerId() != customerPojo.getCustomerId());
		if (allReturnCustomer.size() == 1) {
			returnCustomerPojo = allReturnCustomer.get(0);
		}
		LOG.info("Exiting updateCustomer() in DAO");
		return returnCustomerPojo;
	}*/

	@Override
	public List<TransactionPojo> transactionHistory() throws SystemException, DataNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TransactionPojo addTransaction(TransactionPojo transactionPojo) throws SystemException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TransactionPojo moneyTransfer(TransactionPojo transactionPojo) throws SystemException {
		// TODO Auto-generated method stub
		return null;
	}
	
}
