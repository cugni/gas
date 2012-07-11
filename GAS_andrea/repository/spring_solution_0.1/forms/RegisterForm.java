package it.polito.ai.gas.forms;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;

public class RegisterForm {

	private String command;
	@NotEmpty @Size(min = 1, max = 20)
	private String username;
	@NotEmpty @Size(min = 1, max = 32)
	private String password;
	private int role;
	@NotEmpty @Size(min = 1, max = 20)
	private String name;
	@NotEmpty @Size(min = 1, max = 20)
	private String surname;
	// --- Creano 'birth_date'
	@NotNull @Range(min = 0, max = 31)
	private int day;
	@NotNull @Range(min = 1, max = 12)
	private int month;
	@NotNull @Range(min = 1900, max = 2000)
	private int year;
	// ---

	@Size(max = 40)
	private String company_name;
	@Size(max = 40)
	private String description;
	@Size(max = 20)
	private String contact;
	@Size(max = 40)
	private String address;
	@Size(max = 15)
	private String phone_number;
	@Size(max = 15)
	private String fax_number;
	@Email
	@Size(max = 30)
	private String email;
	@Size(max = 20)
	private String delegate;
	@Size(max = 20)
	private String payment_mode;
	
	
	// --- Getters & Setters ---
	
	public String getUsername() {
		return username;
	}

	
	public void setUsername(String name) {
		this.username = name;
	}

	
	public String getPassword() {
		return password;
	}

	
	public void setPassword(String password) {
		this.password = password;
	}

	
	public int getRole() {
		return role;
	}

	
	public void setRole(int role) {
		this.role = role;
	}

	
	public String getName() {
		return name;
	}

	
	public void setName(String name) {
		this.name = name;
	}

	
	public String getSurname() {
		return surname;
	}

	
	public void setSurname(String surname) {
		this.surname = surname;
	}
	

	public int getYear() {
		return year;
	}


	public void setYear(int year) {
		this.year = year;
	}


	public int getMonth() {
		return month;
	}


	public void setMonth(int month) {
		this.month = month;
	}


	public int getDay() {
		return day;
	}


	public void setDay(int day) {
		this.day = day;
	}

	public String getCompanyName() {
		return company_name;
	}

	public void setCompanyName(String company_name) {
		this.company_name = company_name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhoneNumber() {
		return phone_number;
	}

	public void setPhoneNumber(String phone_number) {
		this.phone_number = phone_number;
	}

	public String getFaxNumber() {
		return fax_number;
	}

	public void setFaxNumber(String fax_number) {
		this.fax_number = fax_number;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDelegate() {
		return delegate;
	}

	public void setDelegate(String delegate) {
		this.delegate = delegate;
	}

	public String getPaymentMode() {
		return payment_mode;
	}

	public void setPaymentMode(String payment_mode) {
		this.payment_mode = payment_mode;
	}


	public String getCommand() {
		return command;
	}


	public void setCommand(String command) {
		this.command = command;
	}

}
