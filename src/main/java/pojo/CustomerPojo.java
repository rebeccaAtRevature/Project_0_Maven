package pojo;

import java.util.Objects;

public class CustomerPojo {
	
	private int customerId;
	private String customerFirstName;
	private String customerLastName;
	private String customerPhoneNumber;
	private String customerAddress;
	private int accountId;
	private double accountBalance;
	private String customerPassword;
	
	public CustomerPojo() {
		
	}

	// Constructor using fields
	public CustomerPojo(int customerId, String customerFirstName, String customerLastName, String customerPhoneNumber,
			String customerAddress, int accountId, double accountBalance, String customerPassword) {
		super();
		this.customerId = customerId;
		this.customerFirstName = customerFirstName;
		this.customerLastName = customerLastName;
		this.customerPhoneNumber = customerPhoneNumber;
		this.customerAddress = customerAddress;
		this.accountId = accountId;
		this.accountBalance = accountBalance;
		this.customerPassword = customerPassword;
	}


	// Getters and Setters
	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public String getCustomerFirstName() {
		return customerFirstName;
	}

	public void setCustomerFirstName(String customerFirstName) {
		this.customerFirstName = customerFirstName;
	}

	public String getCustomerLastName() {
		return customerLastName;
	}

	public void setCustomerLastName(String customerLastName) {
		this.customerLastName = customerLastName;
	}

	public String getCustomerPhoneNumber() {
		return customerPhoneNumber;
	}

	public void setCustomerPhoneNumber(String customerPhoneNumber) {
		this.customerPhoneNumber = customerPhoneNumber;
	}

	public String getCustomerAddress() {
		return customerAddress;
	}

	public void setCustomerAddress(String customerAddress) {
		this.customerAddress = customerAddress;
	}

	public int getAccountId() {
		return accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

	public double getAccountBalance() {
		return accountBalance;
	}

	public void setAccountBalance(double accountBalance) {
		this.accountBalance = accountBalance;
	}

	public String getCustomerPassword() {
		return customerPassword;
	}

	public void setCustomerPassword(String customerPassword) {
		this.customerPassword = customerPassword;
	}

	@Override
	public String toString() {
		return "CustomerPojo [customerId=" + customerId + ", customerFirstName=" + customerFirstName
				+ ", customerLastName=" + customerLastName + ", customerPhoneNumber=" + customerPhoneNumber
				+ ", customerAddress=" + customerAddress + ", accountId=" + accountId + ", accountBalance="
				+ accountBalance + ", customerPassword=" + customerPassword + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(accountId, customerAddress, customerFirstName, customerId, customerLastName,
				customerPassword, customerPhoneNumber);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CustomerPojo other = (CustomerPojo) obj;
		return accountId == other.accountId && Objects.equals(customerAddress, other.customerAddress)
				&& Objects.equals(customerFirstName, other.customerFirstName) && customerId == other.customerId
				&& Objects.equals(customerLastName, other.customerLastName)
				&& Objects.equals(customerPassword, other.customerPassword)
				&& Objects.equals(customerPhoneNumber, other.customerPhoneNumber);
	}
		
	
}
