package presentation;

import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import exceptions.CustomerNotFoundException;
import exceptions.SystemException;
import pojo.CustomerPojo;
import pojo.EmployeePojo;
import service.CustomerService;
import service.CustomerServiceImpl;
import service.EmployeeService;
import service.EmployeeServiceImpl;


/** MAIN MENU
 * 	---------
 * 	1. Login as employee
 *  2. Logins as a customer
 *  3. Exit
 * 
 * 	CUSTOMER MENU
 * 	-------------
 * 	1. View account details
 * 	2. Transfer money
 *  3. View transaction history
 *  4. Logout
 * 
 *  EMPLOYEE MENU
 *  -------------
 *  1. Register or create a customer
 *  2. List all customers
 *  3. Logout (go back to the main menu)
 * 	
 * 	As an employee, 
 * 	I should be able to login
 * 	I should be able to register a customer after logging in - C
 * 	I should be able to list all the customers information after logging in - R
 *  I should be able to logout
 *  
 *  As a customer, 
 *  I should be able to login 
 *  I should be able to view account details - R
 *  I should be able to transfer money to another account - 2 Updates
 *  I should be able to view transaction history - R
 *  I should be able to logout */

public class BankMain {
	
	public static final Logger LOG = LogManager.getLogger(BankMain.class);

	public static void main(String[] args) {
		
		// Create a Service objects to make calls to the service layer
		CustomerService customerService = new CustomerServiceImpl();
		EmployeeService employeeService = new EmployeeServiceImpl();
		
		Scanner scan = new Scanner(System.in);
		
		boolean BankManagementSystem = true;
		
		while(BankManagementSystem) {
			System.out.println("______________________________________________________________________________\n");
			System.out.println("Welcome to the Bank Management System!");
			System.out.println("______________________________________________________________________________\n");
			System.out.println("1) Customer Login\n2) Employee login\n3) Exit\n");
			int login = scan.nextInt();
					
			switch (login) {
			case 1:
				// Customer Login Screen
				boolean custoIdLoop = true;
				int customerId = 0;
				CustomerPojo customerMain = null;
				while (custoIdLoop) {
					System.out.println("Please Enter your customer ID: ");
					customerId = scan.nextInt();
					scan.nextLine();
					try {
						customerMain = customerService.fetchCustomer(customerId);
					} catch (SystemException e) {
						LOG.error(e);
						System.out.println(e.getMessage());
					}
					if(customerMain == null) {
						System.out.println("Please enter a valid customer ID.");
						// continue looping until existing ID is entered
					} else {
						custoIdLoop = false;
					}
				}
				
				boolean customerLoggedIn = false;
				while (customerLoggedIn == false) {
					System.out.println("Please Enter your password: ");
					String customerPswd = scan.next();

					try {
						customerMain = customerService.customerLogin(customerId, customerPswd);
					} catch (SystemException e) {
						LOG.error(e);
						System.out.println(e.getMessage());
					}

					customerLoggedIn = customerMain != null ? true:false;
				}
				
				while(customerLoggedIn) {
					// Customer Menu
					System.out.println("1. View Account\n2. Transfer Money\n3. View Transaction History\n4. Logout");
					System.out.println("______________________________________________________________________________\n");
					System.out.println("Please enter menu option : ");
					int customerOption = scan.nextInt();
					scan.nextLine();
					System.out.println("______________________________________________________________________________\n");
					
					switch (customerOption) {
					case 1:
						// View Account Information
						// Fetch Logged in User's account
						CustomerPojo fetchedAccount = null;
						try {
							fetchedAccount = customerService.fetchCustomer(customerId);
						} catch (SystemException e) {
							LOG.error(e);
							System.out.println(e.getMessage());
						}
						
						
						System.out.println("Account ID : " + fetchedAccount.getAccountId());
						System.out.println("Account Balance : " + fetchedAccount.getAccountBalance());
						System.out.println("______________________________________________________________________________\n");
						System.out.println("Please press enter to continue...");
						scan.nextLine();
						System.out.println("______________________________________________________________________________\n");
						
						break;
					case 2:
						// Transfer Money to another account
						CustomerPojo accountTransferFrom = customerMain;
						
						System.out.println("Please enter customer ID you will transfer money to: ");
						int customerIdTo = scan.nextInt();
						CustomerPojo accountTransferTo = null;
						try {
							accountTransferTo = customerService.fetchCustomer(customerIdTo);
						} catch (SystemException e) {
							LOG.error(e);
							e.printStackTrace();
						}
						if(accountTransferTo == null) {
							System.out.println("Please enter a valid customer ID.");
							break;
						}
						System.out.println("How much money will you be transfering?");
						double transferAmount = scan.nextDouble();
						
						try {
							
							customerService.moneyTransfer(accountTransferFrom.getAccountId(), accountTransferTo.getAccountId(), transferAmount);
						} catch (SystemException e) {
							LOG.error(e);
							System.out.println(e.getMessage());
						}
						
						System.out.println("Your new account balance is: " + accountTransferFrom.getAccountBalance());
						System.out.println("______________________________________________________________________________\n");
						System.out.println("Please press enter to continue...");
						scan.nextLine();
						
						break;
					case 3:
						// View Transaction History
						break;
					case 4:
						// LOGOUT
						customerLoggedIn = false;
					}
				}
				
				break;
			case 2:
				// Employee Login Screen
				boolean employIdLoop = true;
				int employeeId = 0;
				while (employIdLoop) {
					System.out.println("Please Enter your employee ID: ");
					employeeId = scan.nextInt();
					scan.nextLine();
					EmployeePojo employeeMain = null;
					try {
						employeeMain = employeeService.fetchEmployee(employeeId);
					} catch (SystemException e) {
						LOG.error(e);
						System.out.println(e.getMessage());
					}
					if(employeeMain == null) {
						System.out.println("Please enter a valid employee ID.");
						// continue looping until existing ID is entered
					} else {
						employIdLoop = false;
					}
				}
				boolean employeeLoggedIn = false;
				while (employeeLoggedIn == false) {
					System.out.println("Please Enter your password: ");
					String employeePswd = scan.next();

					EmployeePojo employeeMain = null;
					try {
						employeeMain = employeeService.employeeLogin(employeeId, employeePswd);
					} catch (SystemException e) {
						LOG.error(e);
						System.out.println(e.getMessage());
					}

					employeeLoggedIn = employeeMain != null ? true:false;
				}
				
				
				while(employeeLoggedIn) {
					// Employee Menu
					System.out.println("______________________________________________________________________________\n");
					System.out.println("Welcome to the Employee Menu!\n");
					System.out.println("1. Register A New User\n2. View All Accounts\n3. Logout\n");
					System.out.println("Please enter menu option : ");
					int employeeOption = scan.nextInt();
					scan.nextLine();
					System.out.println("______________________________________________________________________________\n");
					switch(employeeOption) {
					case 1: 
						// Register a new user
						CustomerPojo newCustomer = new CustomerPojo();
						
						System.out.println("Enter First Name : ");
						newCustomer.setCustomerFirstName(scan.nextLine());
						System.out.println("Enter Last Name : ");
						newCustomer.setCustomerLastName(scan.nextLine());
						System.out.println("Enter Starting Balance : ");
						newCustomer.setAccountBalance(scan.nextDouble());
						scan.nextLine();
						System.out.println("Enter PhoneNumber : ");
						newCustomer.setCustomerPhoneNumber(scan.next());
						System.out.println("Enter Address : ");
						scan.nextLine();
						newCustomer.setCustomerAddress(scan.nextLine());
						boolean noPassword = true;
						while(noPassword) {
							System.out.println("Choose a Password : ");
							String customerPassword1 = scan.nextLine();
							System.out.println("Please reenter Password : ");
							String customerPassword2 = scan.nextLine();
							if(customerPassword1.equals(customerPassword2)) {
								newCustomer.setCustomerPassword(customerPassword2);
								noPassword = false;
								System.out.println("Customer password is: " + newCustomer.getCustomerPassword() + "\n");
								System.out.println("Please press enter to continue...");
								scan.nextLine();
					
							} else {
								System.out.println("Passwords did not match, please try again.");
							}
							
						}
						
						CustomerPojo addedCustomer = null;
						try {
							addedCustomer = employeeService.addCustomer(newCustomer);
						} catch (SystemException e) {
							LOG.error(e);
							System.out.println(e.getMessage());
						}
						System.out.println("Account Successfully Created! \nNew Customer ID is: " + addedCustomer.getCustomerId() + "\nNew Account ID is: " + addedCustomer.getAccountId());
						
						break;
					case 2:
						// View all accounts
						List<CustomerPojo> allCustomers = null;
						try {
							allCustomers = employeeService.fetchAllCustomers();
						} catch (SystemException e) {
							LOG.error(e);
							System.out.println(e.getMessage());
						} catch (CustomerNotFoundException e) {
							LOG.error(e);
							System.out.println(e.getMessage());
						}
						Iterator<CustomerPojo> iter = allCustomers.iterator();
						System.out.println("______________________________________________________________________________\n");
						System.out.println("CUSTOMER ID\tFIRST NAME\tLAST NAME\tPHONE NUMBER\tADDRESS\t\tACCOUNT ID\tACCOUNT BALANCE");
						
						System.out.println("______________________________________________________________________________\n");
						
						allCustomers.forEach((customer) -> System.out.println(customer.getCustomerId() + "\t\t" + customer.getCustomerFirstName() + "\t\t" + customer.getCustomerLastName() + "\t\t" + customer.getCustomerPhoneNumber() + "\t" + customer.getCustomerAddress() + "\t" + customer.getAccountId() + "\t\t" + customer.getAccountBalance()) );
						System.out.println("______________________________________________________________________________\n");
						System.out.println("Please press enter to continue...");
						scan.nextLine();
						break;
					case 3:
						// LOGOUT
						employeeLoggedIn = false;
						System.out.println("You have been logged out.");
						System.out.println("Please press enter to continue...");
						scan.nextLine();
						break;
					}
					
				}
				
				break;
			case 3:
				BankManagementSystem = false;
				System.out.println("Exiting System...");
				
			}
			
		}
		scan.close();
	}

}
