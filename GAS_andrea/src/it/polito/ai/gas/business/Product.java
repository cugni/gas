package it.polito.ai.gas.business;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name="products")
public class Product implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	private int id;
	private String name;
	private float cost;
	private String units;
	private float quantity;
	@ManyToOne(cascade={CascadeType.REMOVE, CascadeType.MERGE})
	@JoinColumn(name="producer")
	private User producer;
	private String description;
	private String dimensions;
	private float transport_cost;
	private float stock_quantity;
	private float min_to_buy;
	private float max_to_buy;
	private boolean available;
	
	public Product()
	{	
	}

	public String toString() {
		return "PRODUCT ["+id+"]: '"+ name + "' | Description: '"+ description +
				"' | Cost: "+ cost +" | Units: "+ units +
				" | Transport Cost: "+ transport_cost +
				" | Stock Quantity: "+ stock_quantity +
				" | Minimum to buy: "+ min_to_buy +
				" | Maximum to buy: "+ max_to_buy +
				" | Available: " + (available? "yes" : "no") +
				" | Producer: ("+ producer +")";	
	}
	
	
	// --- Getters & Setters ---
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUnits() {
		return units;
	}

	public void setUnits(String units) {
		this.units = units;
	}

	public float getCost() {
		return cost;
	}

	public void setCost(float cost) {
		this.cost = cost;
	}
	
	public User getProducer() {
		return producer;
	}

	public void setProducer(User producer) {
		this.producer = producer;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public float getTransportCost() {
		return transport_cost;
	}

	public void setTransportCost(float transport_cost) {
		this.transport_cost = transport_cost;
	}

	public float getStockQuantity() {
		return stock_quantity;
	}

	public void setStockQuantity(float stock_quantity) {
		this.stock_quantity = stock_quantity;
	}

	public float getMinToBuy() {
		return min_to_buy;
	}

	public void setMinToBuy(float min_to_buy) {
		this.min_to_buy = min_to_buy;
	}

	public float getMaxToBuy() {
		return max_to_buy;
	}

	public void setMaxToBuy(float max_to_buy) {
		this.max_to_buy = max_to_buy;
	}

	public boolean isAvailable() {
		return available;
	}

	public void setAvailable(boolean available) {
		this.available = available;
	}

	public float getQuantity() {
		return quantity;
	}

	public void setQuantity(float quantity) {
		this.quantity = quantity;
	}

	public String getDimensions() {
		return dimensions;
	}

	public void setDimensions(String dimensions) {
		this.dimensions = dimensions;
	}

}
