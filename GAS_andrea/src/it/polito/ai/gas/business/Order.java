package it.polito.ai.gas.business;

import java.io.Serializable;
import java.text.DateFormat;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="orders")
public class Order implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6417151432582392108L;
	
	@Id
	@GeneratedValue
	private int id;
	@ManyToOne(cascade={CascadeType.REMOVE, CascadeType.MERGE})
	@JoinColumn(name="product")
	private Product product;
	private Date start_date;
	private Date end_date;

	public Order()
	{
	}
	
	public String toString() {
		return "ORDER ["+ id +"]:\n\t Product: "+ product +
				"\n\t Start date: "+ DateFormat.getInstance().format(start_date) +
				"\n\t End date: "+ DateFormat.getInstance().format(end_date);
	}
	
	
	// --- Getters & Setters ---

	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public Date getStartDate() {
		return start_date;
	}
	public void setStartDate(Date start_date) {
		this.start_date = start_date;
	}
	public Date getEndDate() {
		return end_date;
	}
	public void setEndDate(Date end_date) {
		this.end_date = end_date;
	}
	
	

}
