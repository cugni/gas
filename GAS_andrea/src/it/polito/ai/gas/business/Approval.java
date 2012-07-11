package it.polito.ai.gas.business;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="approvals")
public class Approval implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7688919713256770619L;
	
	@Id
	@GeneratedValue
	private int id;
	@OneToOne(cascade={CascadeType.MERGE, CascadeType.REMOVE})
	@JoinColumn(name="user")
	private User user;
	private boolean approved;
	@ManyToOne(cascade={CascadeType.MERGE, CascadeType.REMOVE})
	@JoinColumn(name="admin")
	private User admin;

	public Approval()
	{
	}
	
	public String toString() {
		return "APPROVAL ["+ id+"]: User: '"+ user.getUsername() +"' | Approved: '"+
				(approved ? "yes" : "no") +	"' | Admin: '"+
				(admin == null ? "" : admin.getUsername()) +"'";
	}

	
	// --- Getters & Setters ---

	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public boolean isApproved() {
		return approved;
	}

	public void setApproved(boolean approved) {
		this.approved = approved;
	}

	public User getAdmin() {
		return admin;
	}

	public void setAdmin(User admin) {
		this.admin = admin;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}


}
