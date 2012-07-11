package it.polito.ai.gas.business;

import java.io.Serializable;
import java.text.DateFormat;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="users")
public class User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	enum Role { user, delegate, producer, administrator	}
	
	@Id
	private String username;
	private String password;
	private int role;
	private String name;
	private String surname;
	private Date birth_date;
	@OneToOne(cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
	@JoinColumn(name="statistics")
	private Statistics statistics;
	@OneToOne(cascade={CascadeType.MERGE, CascadeType.REMOVE})
	@JoinColumn(name="producer_info")
	private ProducerInfo producer_info;
	
	public User()
	{
	}
	
	public String toString()
	{
		return "\nUSER: '"+username+"'\n\t Role: "+ getRoleName(role) +
				"\n\t Password: "+ password +
				"\n\t Name: " + name +
				"\n\t Surname: "+ surname +
				"\n\t Birth Date: "+ DateFormat.getInstance().format(birth_date) +
				"\n\t Statistics: ("+ statistics +
				")\n\t Additional Producer Info: "+ producer_info;
	}
	
	public static Role getRoleName(int role)
	{
		return Role.values()[role];
	}
	
	public static int getRoleNumber(String role)
	{
		return Role.valueOf(role.toLowerCase()).ordinal();
	}
	
	
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

	
	public Date getBirthDate() {
		return birth_date;
	}

	
	public void setBirthDate(Date birth_date) {
		this.birth_date = birth_date;
	}

	
	public Statistics getStatistics() {
		return statistics;
	}

	
	public void setStatistics(Statistics statistics) {
		this.statistics = statistics;
	}

	
	public ProducerInfo getProducerInfo() {
		return producer_info;
	}

	
	public void setProducerInfo(ProducerInfo producer_info) {
		this.producer_info = producer_info;
	}

}
