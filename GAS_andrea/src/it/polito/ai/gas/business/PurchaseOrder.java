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
@Table(name="purchase_orders")
public class PurchaseOrder implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2373717271704617543L;
	
	@Id
	@GeneratedValue
	private int id;
	@ManyToOne(cascade={CascadeType.REMOVE, CascadeType.MERGE})
	@JoinColumn(name="order")
	private Order order;
	@ManyToOne(cascade={CascadeType.REMOVE, CascadeType.MERGE})
	@JoinColumn(name="acquirer")
	private User acquirer;
	private float quantity;
	
	public PurchaseOrder()
	{
	}
	
	public String toString() {
		return "\nPURCHASE ORDER ["+ id+"]:\n\t Order: "+ order +
				"\n\t Acquirer: "+ acquirer +"\n\t Quantity: "+ quantity;
	}
	
	
	// --- Getters & Setters ---

	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}
	public User getAcquirer() {
		return acquirer;
	}
	public void setAcquirer(User acquirer) {
		this.acquirer = acquirer;
	}
	public float getQuantity() {
		return quantity;
	}
	public void setQuantity(float quantity) {
		this.quantity = quantity;
	}

}
