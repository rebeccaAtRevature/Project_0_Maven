package pojo;

public class EmployeePojo {
	
	int employeeId;
	String employeeFirstName;
	String employeeLastName;
	String employeePhoneNumber;
	String employeeAddress;
	String password;

	// Constructor
	public EmployeePojo(int employeeId, String employeeFirstName, String employeeLastName, String employeePhoneNumber,
			String employeeAddress, String password) {
		super();
		this.employeeId = employeeId;
		this.employeeFirstName = employeeFirstName;
		this.employeeLastName = employeeLastName;
		this.employeePhoneNumber = employeePhoneNumber;
		this.employeeAddress = employeeAddress;
		this.password = password;
	}
	
	// Getters and Setters
	public int getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

	public String getEmployeeFirstName() {
		return employeeFirstName;
	}

	public void setEmployeeFirstName(String employeeFirstName) {
		this.employeeFirstName = employeeFirstName;
	}

	public String getEmployeeLastName() {
		return employeeLastName;
	}

	public void setEmployeeLastName(String employeeLastName) {
		this.employeeLastName = employeeLastName;
	}

	public String getEmployeePhoneNumber() {
		return employeePhoneNumber;
	}

	public void setEmployeePhoneNumber(String employeePhoneNumber) {
		this.employeePhoneNumber = employeePhoneNumber;
	}

	public String getEmployeeAddress() {
		return employeeAddress;
	}

	public void setEmployeeAddress(String employeeAddress) {
		this.employeeAddress = employeeAddress;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "EmployeePojo [employeeId=" + employeeId + ", employeeFirstName=" + employeeFirstName
				+ ", employeeLastName=" + employeeLastName + ", employeePhoneNumber=" + employeePhoneNumber
				+ ", employeeAddress=" + employeeAddress + ", password=" + password + "]";
	}
		
}
