package it.polito.ai.gas.business;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="statistics")
public class Statistics implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2121468968725411998L;
	
	@Id
	@GeneratedValue
	private int id;
	private int n_bought;

	public Statistics()
	{
	}
	
	public String toString() {
		return "\nSTATISTICS ["+ id +"]:\n\t #Bought items = "+ n_bought;
	}
	
	
	// --- Getters & Setters ---
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getNBought() {
		return n_bought;
	}

	public void setNBought(int n_bought) {
		this.n_bought = n_bought;
	}

}
