package pojo;

import java.sql.Timestamp;

public class TransactionPojo {
	


	private Timestamp timestamp;
	private int transactionId;
	private int accountIdFrom;
	private int accountIdTo;
	private double transferAmount;
	private double newBalanceFrom;
	private double newBalanceTo;
	private String accountFromFirstName;
	private String accountFromLastName;

	public TransactionPojo() {}
	
	public TransactionPojo( int accountIdFrom, int accountIdTo, double transferAmount) {
		this.accountIdFrom = accountIdFrom;
		this.accountIdTo = accountIdTo;
		this.transferAmount = transferAmount;

	}

	public TransactionPojo(Timestamp timestamp, String accountFromFirstName, String accountFromLastName, double transferAmount, double newBalanceFrom, double newBalanceTo) {
		super();
		this.timestamp = timestamp;
		this.accountFromFirstName = accountFromFirstName;
		this.accountFromLastName = accountFromLastName;
		this.transferAmount = transferAmount;
		this.newBalanceFrom = newBalanceFrom;
		this.newBalanceTo = newBalanceTo;
	}

	public Timestamp getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}

	public int getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(int transactionId) {
		this.transactionId = transactionId;
	}

	public int getAccountIdFrom() {
		return accountIdFrom;
	}

	public void setAccountIdFrom(int accountIdFrom) {
		this.accountIdFrom = accountIdFrom;
	}

	public int getAccountIdTo() {
		return accountIdTo;
	}

	public void setAccountIdTo(int accountIdTo) {
		this.accountIdTo = accountIdTo;
	}

	public double getTransferAmount() {
		return transferAmount;
	}

	public void setTransferAmount(double transferAmount) {
		this.transferAmount = transferAmount;
	}

	public double getNewBalanceFrom() {
		return newBalanceFrom;
	}

	public void setNewBalanceFrom(double newBalanceFrom) {
		this.newBalanceFrom = newBalanceFrom;
	}

	public double getNewBalanceTo() {
		return newBalanceTo;
	}

	public void setNewBalanceTo(double newBalanceTo) {
		this.newBalanceTo = newBalanceTo;
	}
	
	public String getAccountFromFirstName() {
		return accountFromFirstName;
	}
	
	public void setAccountFromFirstName(String accountFromFirstName) {
		this.accountFromFirstName = accountFromFirstName;
	}
	
	public String getAccountFromLastName() {
		return accountFromLastName;
	}
	
	public void setAccountFromLastName(String accountFromLastName) {
		this.accountFromLastName = accountFromLastName;
	}

	@Override
	public String toString() {
		return "TransactionPojo [timestamp=" + timestamp + ", transactionId=" + transactionId + ", accountIdFrom="
				+ accountIdFrom + ", accountIdTo=" + accountIdTo + ", transferAmount=" + transferAmount
				+ ", newBalanceFrom=" + newBalanceFrom + ", newBalanceTo=" + newBalanceTo + ", accountFromFirstName="
				+ accountFromFirstName + ", accountFromLastName=" + accountFromLastName + "]";
	}
	
	
}
