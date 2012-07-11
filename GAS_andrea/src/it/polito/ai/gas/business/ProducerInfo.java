package it.polito.ai.gas.business;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="producer_info")
public class ProducerInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8218544179658431810L;

	@Id
	@GeneratedValue
	private int id;
	private String company_name;
	private String description;
	private String contact;
	private String address;
	private String phone_number;
	private String fax_number;
	private String email;
	@ManyToOne(cascade={CascadeType.REMOVE, CascadeType.MERGE})
	@JoinColumn(name="delegate")
	private User delegate;
	private String payment_mode;
	
	public ProducerInfo()
	{
	}
	
	public String toString() {
		return "\nPRODUCERINFO ["+id+"]:\n\t Company name: '"+ company_name +
				"\nt Description: "+ description +"\n\t Contact: "+ contact +
				"\n\t Address: "+ address +"\n\t Phone Number: "+ phone_number+
				"\n\t Fax Number: "+ fax_number+"\n\t Email: "+ email +
				"\n\t Payment mode: "+ payment_mode +
				"\n\t DELEGATE: "+ delegate;
	}
	
	// --- Getters & Setters ---
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public User getDelegate() {
		return delegate;
	}

	public void setDelegate(User delegate) {
		this.delegate = delegate;
	}

	public String getPaymentMode() {
		return payment_mode;
	}

	public void setPaymentMode(String payment_mode) {
		this.payment_mode = payment_mode;
	}
}
