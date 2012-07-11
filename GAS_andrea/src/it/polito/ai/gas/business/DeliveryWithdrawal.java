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
@Table(name="delivery_withdrawal")
public class DeliveryWithdrawal implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3140052283288158385L;
	
	@Id
	@GeneratedValue
	private int id;
	@ManyToOne(cascade={CascadeType.REMOVE, CascadeType.MERGE})
	@JoinColumn(name="order")
	private Order order;
	private Date delivery_date;
	private Date withdrawal_date;
	@ManyToOne(cascade={CascadeType.REMOVE, CascadeType.MERGE})
	@JoinColumn(name="collector")
	private User collector;
	
	public DeliveryWithdrawal()
	{
	}
	
	public String toString() {
		return "\nDELIVERY/WITHDRAWAL ["+ id +"]:\n\t Order: "+ order +
				"\n\t Delivery date: "+ delivery_date == null ? "" : DateFormat.getInstance().format(delivery_date) +
				"\n\t Withdrawal date: "+ withdrawal_date == null ? "" : DateFormat.getInstance().format(withdrawal_date) +
				"\n\t Collector: "+ collector;
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
	public Date getDeliveryDate() {
		return delivery_date;
	}
	public void setDeliveryDate(Date delivery_date) {
		this.delivery_date = delivery_date;
	}
	public Date getWithdrawalDate() {
		return withdrawal_date;
	}
	public void setWithdrawalDate(Date withdrawal_date) {
		this.withdrawal_date = withdrawal_date;
	}
	public User getCollector() {
		return collector;
	}
	public void setCollector(User collector) {
		this.collector = collector;
	}

}
